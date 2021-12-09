package com.segurosbolivar.avaluos.modelo.service.impl;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.log4j.Logger;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.data.AvaluoDao;
import com.segurosbolivar.avaluos.modelo.data.CargueTmpDao;
import com.segurosbolivar.avaluos.modelo.data.CiudadDao;
import com.segurosbolivar.avaluos.modelo.data.DepartamentoDao;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.CargueTmp;
import com.segurosbolivar.avaluos.modelo.entity.Ciudad;
import com.segurosbolivar.avaluos.modelo.entity.Departamento;
import com.segurosbolivar.avaluos.modelo.entity.PeritosEmpresa;
import com.segurosbolivar.avaluos.modelo.facade.IIntegradorFacade;
import com.segurosbolivar.avaluos.modelo.service.IAvaluo;
import com.segurosbolivar.avaluos.modelo.service.IComparar;
import com.segurosbolivar.avaluos.modelo.service.IDiligenciamiento;
import com.segurosbolivar.avaluos.modelo.service.IPerito;
import com.segurosbolivar.avaluos.modelo.service.IProcesarAvaluo;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;

@Stateless
public class ProcesarAvaluoImpl implements IProcesarAvaluo {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4875471426838118178L;
	@EJB
    private CargueTmpDao cargueDao;
    @EJB
    private AvaluoDao avaluoDao;
    @EJB
    private CiudadDao ciudadDao;
    @EJB
    private DepartamentoDao departamentoDao;
    @EJB
    private IDiligenciamiento diligenciamientoSvc;
    @EJB
    private IAvaluo avaluoSvc;
    @EJB
    private IComparar compararSvc;
    @EJB
    private IPerito peritoSvc;

    Logger logger = Logger.getLogger(CargueMasivoImpl.class);
    @EJB
    private IIntegradorFacade integradorFacade;

    @Override
    @Asynchronous
    public Future<String> procesarLineaCargue(Long referenciaCargue, String nombreArchivo, String linea, Long numeroLinea, Long consecutivoBatch, UsuarioDto usuario,
	    boolean esConstructor) {

	try {
	    CargueTmp cargueTmp = generaCargueTemporal(referenciaCargue, nombreArchivo, linea, numeroLinea, consecutivoBatch, usuario.getUsuario().getCodigo(), esConstructor);
	    try {
		cargueDao.ejecutaCargueConstructor(cargueTmp.getTipoProyecto().compareTo(1L) == 0 ? Boolean.FALSE : Boolean.TRUE, cargueTmp, UtilConstantes.TIPO_CARGUE_MASIVO);
	    } catch (Exception e) {
		cambiaEstadoCargue("RECHAZADO", "El formato del archivo no es correcto: " + e.getMessage(), cargueTmp);
	    }

	    String consecutivoBanco = obtenerConsecutivo(linea);
	    if (consecutivoBanco.length() > 0) {
		Avaluo avaluo = avaluoDao.consultarConsecutivoBanco(new BigInteger(consecutivoBanco));
		if (avaluo != null) {
		    String asegurabilidad = integradorFacade.obtenerAsegurabilidad(avaluo.getLatitudSFBUA(), avaluo.getLongitudSFBUA());
		    avaluo.setAsegurabilidad(asegurabilidad);
		    avaluo.setAsegurabilidad("S");
		    Ciudad ciudad = ciudadDao.buscarPorAsobancaria(avaluo.getIdCiudad());
		    Departamento departamento = departamentoDao.buscarPorAsobancaria(avaluo.getIdDepartamento());
		    avaluo.setIdCiudad(ciudad.getIdCiudad());
		    avaluo.setIdDepartamento(departamento.getIdDepartamento());
		    avaluo.setCiudad(ciudad);
		    avaluo.setDepartamento(departamento);
		    PeritosEmpresa perito = peritoSvc.consultarPeritoDocumento(Long.parseLong(usuario.getUsuario().getCodigo()));
		    avaluo.setIdPeritoEmpresa(perito.getIdPeritoEmpresa().longValue());
		    avaluoDao.actualizar(avaluo);
		    validarCondiciones(avaluo, usuario, referenciaCargue, numeroLinea);
		}
	    }

	} catch (NegocioException e) {
	    return new AsyncResult<String>("Error");
	}
	return new AsyncResult<String>("Linea numero " + numeroLinea + " procesada con exito");
    }

    private CargueTmp generaCargueTemporal(Long numeroRefCargue, String nombreArchivo, String linea, Long numeroLinea, Long numeroConsecutivoBatch, String cedula,
	    boolean constructor) throws NegocioException {
	CargueTmp cargueTmp = new CargueTmp();
	cargueTmp.setNumeroRefCargue(numeroRefCargue);
	cargueTmp.setConsecutivoBatch(numeroConsecutivoBatch);
	cargueTmp.setLineaPlano(numeroLinea);
	cargueTmp.setContenidoLineaPlano(linea);
	cargueTmp.setTipoCargue(UtilConstantes.TIPO_CARGUE_MASIVO);
	cargueTmp.setEstadoCargue(UtilConstantes.TIPO_CARGUE_CARGADO);
	cargueTmp.setNombreArchivo(nombreArchivo);
	cargueTmp.setFechaTransaccion(Calendar.getInstance().getTime());
	cargueTmp.setUsuarioTransaccion(cedula);
	cargueTmp.setTipoProyecto(constructor ? new Long(2) : 1L);
	cargueDao.crear(cargueTmp);
	return cargueTmp;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void cambiaEstadoCargue(String estado, String mensaje, CargueTmp cargueTmp) throws NegocioException {
	cargueTmp.setContenidoLineaPlano(cargueTmp.getContenidoLineaPlano());
	cargueTmp.setEstadoCargue(estado);
	cargueTmp.setMensajeError(mensaje);
	cargueDao.actualizar(cargueTmp);
    }

    private void validarCondiciones(Avaluo avaluo, UsuarioDto usuario, Long numeroReferencia, Long linea) {
	CargueTmp cargue = cargueDao.obtenerCargueTmpPorLinea(numeroReferencia, linea);
	StringBuilder builder = new StringBuilder(cargue.getMensajeError() == null ? "" : cargue.getMensajeError());
	try {
	    diligenciamientoSvc.generarAlertaPrevioAprobacion(avaluo, usuario);
	    diligenciamientoSvc.enviarCorreos(avaluo, usuario);
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}
	try {
	    compararSvc.comparaAvaluo(avaluo, usuario);
	} catch (Exception e) {
	    builder.append(e.getMessage());
	}
	try {
	    cargue.setMensajeError(builder.toString());
	    cargueDao.actualizar(cargue);
	} catch (NegocioException e) {
	    logger.error(e.getMessage());
	}
    }

    private String obtenerConsecutivo(String linea) {

	StringBuilder contenido = new StringBuilder(linea);
	int i = 0;
	int tamanio = contenido.length();
	while (i < tamanio) {
	    if (contenido.charAt(i) == '|' && contenido.charAt(i + 1) == '|') {
		contenido.insert(i + 1, 0);
	    }
	    i++;
	}
	StringTokenizer tokens = new StringTokenizer(contenido.toString(), "|");
	String consecutivoBanco = "";
	int contador = 0;
	while (tokens.hasMoreTokens()) {
	    String campo = tokens.nextToken();
	    if (contador == 130) {
		consecutivoBanco = campo;
		break;
	    }
	    contador++;
	}

	return consecutivoBanco;
    }

}
