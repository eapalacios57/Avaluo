package com.segurosbolivar.avaluos.modelo.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilNumero;
import com.asesoftware.util.lang.UtilTexto;
import com.asesoftware.util.validador.UtilValidador;
import com.segurosbolivar.avaluos.modelo.cons.EstadoAvaluo;
import com.segurosbolivar.avaluos.modelo.cons.EstadoRegistro;
import com.segurosbolivar.avaluos.modelo.cons.Procedencia;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.cons.SiNo;
import com.segurosbolivar.avaluos.modelo.cons.TipoInforme;
import com.segurosbolivar.avaluos.modelo.cons.TipoPerfil;
import com.segurosbolivar.avaluos.modelo.data.AlertaDao;
import com.segurosbolivar.avaluos.modelo.data.AnexoFotograficoDao;
import com.segurosbolivar.avaluos.modelo.data.ArchivoDao;
import com.segurosbolivar.avaluos.modelo.data.ArchivoPlanoDao;
import com.segurosbolivar.avaluos.modelo.data.AvaluoDao;
import com.segurosbolivar.avaluos.modelo.data.CargueTmpDao;
import com.segurosbolivar.avaluos.modelo.data.CiudadDao;
import com.segurosbolivar.avaluos.modelo.data.IndiceAcumuladoDao;
import com.segurosbolivar.avaluos.modelo.data.InformacionConstruccionDao;
import com.segurosbolivar.avaluos.modelo.data.ListaAnexosPdfDao;
import com.segurosbolivar.avaluos.modelo.data.ParametrizacionDao;
import com.segurosbolivar.avaluos.modelo.dto.AvaluoFechaEstadoDTO;
import com.segurosbolivar.avaluos.modelo.dto.ConsultaAvaluoDto;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.AnexoFotografico;
import com.segurosbolivar.avaluos.modelo.entity.Archivo;
import com.segurosbolivar.avaluos.modelo.entity.ArchivoPlano;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.InformacionConstruccion;
import com.segurosbolivar.avaluos.modelo.entity.ListaAnexosPdf;
import com.segurosbolivar.avaluos.modelo.entity.Parametrizacion;
import com.segurosbolivar.avaluos.modelo.entity.PeritosEmpresa;
import com.segurosbolivar.avaluos.modelo.facade.IAvaluoFacade;
import com.segurosbolivar.avaluos.modelo.facade.IIntegradorFacade;
import com.segurosbolivar.avaluos.modelo.service.IArchivo;
import com.segurosbolivar.avaluos.modelo.service.IAvaluo;
import com.segurosbolivar.avaluos.modelo.service.IComparar;
import com.segurosbolivar.avaluos.modelo.service.IComplementos;
import com.segurosbolivar.avaluos.modelo.service.IDiligenciamiento;
import com.segurosbolivar.avaluos.modelo.service.IParametrizacion;
import com.segurosbolivar.avaluos.modelo.service.IPerito;
import com.segurosbolivar.avaluos.modelo.service.IProcedato;
import com.segurosbolivar.avaluos.modelo.service.ISeguridad;
import com.segurosbolivar.avaluos.modelo.service.ITronador;
import com.segurosbolivar.avaluos.modelo.service.IValidador;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Usuario;
import com.segurosbolivar.avaluos.modelo.ws.service.correo.ISolicitudesCliente;
import com.segurosbolivar.avaluos.modelo.ws.service.correo.SolicitudesCliente;
import com.segurosbolivar.avaluos.modelo.ws.service.correo.SolicitudesClienteThread;

/**
 * Implementación del servicio de avaluo.
 *
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:39 a.m.
 */
@Stateless
public class AvaluoImpl implements IAvaluo {

    private static final long serialVersionUID = 6374532886714006883L;

    private static final Logger log = Logger.getLogger(AvaluoImpl.class);

    @EJB
    private ManejadorErroresNegocio mgrExc;
    @EJB
    private IParametrizacion parametrizacionSvc;
    @EJB
    private IDiligenciamiento diligenciamientoSvc;
    @EJB
    private IArchivo archivoSvc;
    @EJB
    private ITronador tronadorSvc;
    @EJB
    private IIntegradorFacade integradorFacade;
    @EJB
    private IValidador validador;
    @EJB
    private IPerito peritoSvc;
    @EJB
    private IProcedato procedatoSvc;
    @EJB
    private ISeguridad seguridadSvc;
    @EJB
    private AlertaDao alertaDao;
    @EJB
    private AvaluoDao avaluoDao;
    @EJB
    private ArchivoPlanoDao archivoPlanoDao;

    @EJB
    private CargueTmpDao cargueTmpDao;
    @EJB
    private InformacionConstruccionDao construccionDao;

    @EJB
    private CiudadDao ciudadDao;

    @EJB
    private ListaAnexosPdfDao anexosDao;
    

    @EJB
    private ParametrizacionDao parametrizacionDao;

    @EJB
    private IndiceAcumuladoDao indiceAcumuladoDao;

    @EJB
    private ISolicitudesCliente solicitudesClienteSvc;
    
    @EJB
    private IComparar compararSvc;
    
    @EJB
    private IAvaluoFacade avaluoFacade;
    
    @EJB
    private AnexoFotograficoDao anexoFotograficoDao;
    
    @EJB
    private ArchivoDao archivoDao;
    
    @EJB
	public transient IComplementos complementosImpl;
    
    
    
    

    @Override
    public Avaluo consultarAvaluo(Long idAvaluo) throws NegocioException {
	if (idAvaluo == null)
	    throw mgrExc.lanzarExcepcion(12L, TipoErrorNegocio.ERROR);
	return avaluoDao.buscar(idAvaluo);
    }

    @Override
    public List<Avaluo> consultarAvaluo(ConsultaAvaluoDto consulta, int inicio, int registroXPagina, String campoOrden, SentidoOrientacion sentido) {
	return avaluoDao.consultar(consulta, inicio, registroXPagina, campoOrden, sentido);
    }

    @Override
    public int cantidadRegistros(ConsultaAvaluoDto consulta) {
    	String codigoPerfil="";
    	codigoPerfil = consulta.getUsuario().getUsuario().getCodigoPerfil();
    	if(TipoPerfil.PERITO_COORDINADOR.getValor().equals(codigoPerfil)||TipoPerfil.USUARIO_CONSULTOR.getValor().equals(codigoPerfil)){
    		consulta.setEstadoAvaluo(EstadoAvaluo.APROBADO.getValor());	
    	}
	return avaluoDao.cantidadRegistros(consulta);
    }

    @Override
    public void enviarParaAprobacion(List<Avaluo> avaluos, UsuarioDto usuario) throws NegocioException {
	if (usuario == null || usuario.getUsuario() == null || UtilTexto.estaVacio(usuario.getUsuario().getCodigo())) {
	    throw mgrExc.lanzarExcepcion(11L, TipoErrorNegocio.ERROR);
	}
	if (avaluos == null || avaluos.isEmpty()) {
	    throw mgrExc.lanzarExcepcion(53L, TipoErrorNegocio.ERROR);
	}
	for (Avaluo avaluo : avaluos) {
	    avaluo.setEstadoAvaluo(EstadoAvaluo.EN_APROBACION.getValor());
	    avaluo.setFechaTransaccion(new Date());
	    avaluo.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
	    avaluoDao.actualizar(avaluo);
	}
    }

    @Override
    public void reversar(List<Avaluo> avaluos, UsuarioDto usuario) throws NegocioException {
	if (usuario == null || usuario.getUsuario() == null || UtilTexto.estaVacio(usuario.getUsuario().getCodigo())) {
	    throw mgrExc.lanzarExcepcion(11L, TipoErrorNegocio.ERROR);
	}
	if (avaluos == null || avaluos.isEmpty()) {
	    throw mgrExc.lanzarExcepcion(53L, TipoErrorNegocio.ERROR);
	}
	for (Avaluo avaluo : avaluos) {
	    avaluo.setFechaEliminacion(null);
	    avaluo.setCodigoMotivoEliminacion(null);
	    avaluo.setEstadoAvaluo(EstadoAvaluo.NUEVO.getValor());
	    avaluo.setFechaTransaccion(new Date());
	    avaluo.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
	    avaluoDao.actualizar(avaluo);
	}
    }

    @Override
    public void eliminar(List<Avaluo> avaluos, UsuarioDto usuario) throws NegocioException {
	if (usuario == null || usuario.getUsuario() == null || UtilTexto.estaVacio(usuario.getUsuario().getCodigo())) {
	    throw mgrExc.lanzarExcepcion(11L, TipoErrorNegocio.ERROR);
	}
	if (avaluos == null || avaluos.isEmpty()) {
	    throw mgrExc.lanzarExcepcion(53L, TipoErrorNegocio.ERROR);
	}
	for (Avaluo avaluo : avaluos) {
	    try {
		UtilValidador.validar(avaluo.getCodigoMotivoEliminacion(), true, 3);
	    } catch (Exception e) {
		mgrExc.lanzarExcepcion(52L, TipoErrorNegocio.ERROR, e.getMessage(), new String[] { avaluo.getConsecutivoBanco().toString() });
	    }
	    avaluo.setFechaEliminacion(new Date());
	    avaluo.setEstadoAvaluo(EstadoAvaluo.ELIMINADO.getValor());
	    avaluo.setFechaTransaccion(new Date());
	    avaluo.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
	    avaluoDao.actualizar(avaluo);
	}
    }
    
    @Override
    public void eliminar(Avaluo avaluo) throws NegocioException {
	
	if (avaluo == null) {
	    throw mgrExc.lanzarExcepcion(53L, TipoErrorNegocio.ERROR);
	}
	    avaluoDao.eliminar(avaluo);
    }
    
    
    
	private void eliminarFotografias(Avaluo avaluo) throws NegocioException {
		if (!avaluo.isProvisional())
			return;
		List<ListaAnexosPdf> existentes = anexosDao.consultaAnexosPorAvaluo(avaluo);
		AnexoFotografico anexoFotografico = anexoFotograficoDao.consultar(avaluo.getIdAvaluo());
		List<String> codigoEliminar = new ArrayList<String>();  
		if (!existentes.isEmpty()) {
			for (ListaAnexosPdf eliminar : existentes) {
				if (eliminar.getIdArchivo() == null)
					continue;
//				archivoSvc.borrarDocumento(eliminar.getIdArchivo());
				Archivo archivoEliminar = archivoDao.buscar(eliminar.getIdArchivo());
				if (archivoEliminar != null && archivoEliminar.getIdDocumento() != null)
					codigoEliminar.add(archivoEliminar.getIdDocumento());
				archivoDao.eliminarPorId(eliminar.getIdArchivo());
				anexosDao.eliminarPorId(eliminar.getIdListaAnexosPdf());
				
				if (anexoFotografico !=null && anexoFotografico.getIdImgFachada() != null
						&& anexoFotografico.getIdImgFachada().compareTo(eliminar.getIdArchivo()) == 0)
					anexoFotografico.setIdImgFachada(null);
				if (anexoFotografico !=null && anexoFotografico.getIdDocAnexos() != null
						&& anexoFotografico.getIdDocAnexos().compareTo(eliminar.getIdArchivo()) == 0)
					anexoFotografico.setIdDocAnexos(null);
			}
		}
		for (String eliminar : codigoEliminar) {
			  System.out.println("llave a eliminar: "+eliminar);
			  archivoSvc.borrarDoc(eliminar);			
		}	
	}

    @Override
	public void guardar(Avaluo avaluo, UsuarioDto usuario, Procedencia procedencia) throws NegocioException {
		if (usuario == null || usuario.getUsuario() == null || UtilTexto.estaVacio(usuario.getUsuario().getCodigo())) {
			throw mgrExc.lanzarExcepcion(11L, TipoErrorNegocio.ERROR);
		}
		if (avaluo == null) {
			throw mgrExc.lanzarExcepcion(12L, TipoErrorNegocio.ERROR);
		}
		avaluo.setFechaTransaccion(new Date());
		avaluo.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
		if (avaluo.getCodTipoAvaluo() == null) {
			avaluo.setProvisional(true);
		}
		validarRepeticionConsecutivoBanco(avaluo);
		try {
			if ((avaluo.getIdAvaluo() == null) || (avaluoDao.buscar(avaluo.getIdAvaluo()) == null)) {
				avaluo.setFechaCreacion(new Date());
				avaluo.setUsuarioCreacion(usuario.getUsuario().getCodigo());
				if (avaluo.getEstadoAvaluo() == null)
					avaluo.setEstadoAvaluo(EstadoAvaluo.NUEVO.getValor());
				avaluo.setCodigoProcedencia(procedencia.getValor());
				avaluo.setTransmitido(0L);
				PeritosEmpresa perito = verificarPerito(usuario);
				avaluo.setUsuario(perito.getEmpresasAvaluo().getNumeroDocumento().toString());
				avaluo.setIdPeritoEmpresa(perito.getIdPeritoEmpresa().longValue());
				avaluo.setCodigoBanco(UtilConstantes.CODIGO_ENTIDAD_DAVIVIENDA);
				avaluo.setTransmitido(0L);
				if (avaluo.getTipoInforme() == null)
					avaluo.setTipoInforme(TipoInforme.CREDITO.getValor());
				avaluoDao.crear(avaluo);
			} else {
				avaluoDao.actualizar(avaluo);
			//Elimina las fotografias en caso que el avaluo sea provisional.
			eliminarFotografias(avaluo);
				if (!avaluo.isConstructor())
					return;
				InformacionConstruccion construccion = diligenciamientoSvc.consultarConstruccion(avaluo.getIdAvaluo());
				if (construccion == null)
					return;
				construccion.setEstadoConservacion(1L);
				construccionDao.actualizar(construccion);
			}
		} catch (NegocioException e) {
			throw e;
		} catch (Exception e) {
			throw mgrExc.lanzarExcepcion(70, TipoErrorNegocio.ERROR, e.getMessage(), null);
		}
	}

    private PeritosEmpresa verificarPerito(UsuarioDto usuario) throws NegocioException {
	PeritosEmpresa perito = peritoSvc.consultarPeritoDocumento(Long.valueOf(usuario.getUsuario().getCodigo()));
	if (perito == null)
	    throw mgrExc.lanzarExcepcion(78, TipoErrorNegocio.ERROR);
	if (!EstadoRegistro.ACTIVO.getValor().equals(perito.getEstadoAsociacion()))
	    throw mgrExc.lanzarExcepcion(80, TipoErrorNegocio.ERROR);
	if (perito.getEmpresasAvaluo() == null)
	    throw mgrExc.lanzarExcepcion(79, TipoErrorNegocio.ERROR);
	return perito;
    }

    @Override
    public void validarRepeticionConsecutivoBanco(Avaluo avaluo) throws NegocioException {
	// validar que no se repita le consecutivo del banco
	List<Avaluo> repetidos = avaluoDao.consultarConsecutivoBanco(avaluo);
	if (repetidos != null && !repetidos.isEmpty()) {
	    if (avaluo.getIdAvaluo() == null)
	    	throw mgrExc.lanzarExcepcion(14, TipoErrorNegocio.ERROR, null, new String[] { avaluo.getConsecutivoBanco().toString() });
	    for (Avaluo repetido : repetidos) {
		if (repetido.getIdAvaluo().compareTo(avaluo.getIdAvaluo()) != 0)
		    throw mgrExc.lanzarExcepcion(14, TipoErrorNegocio.ERROR, null, new String[] { avaluo.getConsecutivoBanco().toString() });
	    }
	}
    }

    @Override
    public List<Avaluo> aprobar(List<Avaluo> seleccionado, UsuarioDto usuario) throws NegocioException, Exception {
	NegocioException lanzar = mgrExc.lanzarExcepcion(68, TipoErrorNegocio.ALERTA);
	List<Avaluo> avaluosAprobados = new ArrayList<>();
	for (Avaluo avaluo : seleccionado) {
	    try {	
		avaluosAprobados.add(aprobar(avaluo, usuario));
	    } catch (NegocioException e) {
		lanzar.agregarError(e);
	    }
	}
	if (lanzar.getErrores() == null || lanzar.getErrores().isEmpty())
	    return avaluosAprobados;
	Collections.reverse(lanzar.getErrores());
	throw lanzar;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Avaluo aprobar(Avaluo aprobar, UsuarioDto usuario) throws Exception {
		log.info("--Se inicia la aprobacion del avaluo: " + aprobar.getConsecutivoBanco());
		NegocioException lanzar = mgrExc.lanzarExcepcion(68, TipoErrorNegocio.ALERTA);
		if (!aprobar.isPuedeAprobar())
		    throw mgrExc.lanzarExcepcion(56, TipoErrorNegocio.ALERTA, null, new String[] { aprobar.getConsecutivoBanco() + "" });
		if (aprobar.isMovil() && aprobar.getObservacion().getIdArchivo() == null)
			throw mgrExc.lanzarExcepcion(126, TipoErrorNegocio.ALERTA, null, new String[] { aprobar.getConsecutivoBanco() + "" });
		Avaluo avaluoConsulta = consultarAvaluo(aprobar.getIdAvaluo());
		if (!validador.validar(avaluoConsulta, lanzar, usuario))
		    throw lanzar;
		Long estadoAvaluoAnterior = aprobar.getEstadoAvaluo();
		avaluoConsulta.setFechaTransaccion(Calendar.getInstance().getTime());
		avaluoConsulta.setEstadoAvaluo(UtilConstantes.ESTADO_AVALUOS_APROBADO);
		avaluoConsulta.setTransmitido(UtilConstantes.NO_TRANSMITIDO);
	    
	    File pdf = null;
		// Deben ir la llamada a los metodos que van a enviar los adjuntos
	    if(aprobar.isMovil()) {
	    	log.info("--Empieza el envio de adjuntos a bus Davivienda");
	    	pdf = obtenerPdf(aprobar, usuario);
	    }
	  	try {
	  		if(aprobar.isMovil()) {
		    	log.info("--Empieza el envio de notificacion a bus Davivienda");
		    	enviarAdjunto2(aprobar, usuario,pdf);
		    	aprobar.setEstadoAvaluo(avaluoConsulta.getEstadoAvaluo());
		    	enviarNotificacion(aprobar, usuario);
		    	aprobar.setEstadoAvaluo(estadoAvaluoAnterior);
		    }
	  	}catch(Exception e) {
	  		avaluoConsulta.setEstadoAvaluo(estadoAvaluoAnterior);
	  		avaluoDao.actualizar(avaluoConsulta);
			throw new NegocioException(TipoErrorNegocio.ERROR, e.getMessage());
	  	}
	  	 log.info("--Se aprueba el avaluo: " + aprobar.getConsecutivoBanco());
		 compararSvc.confirmarAsegurabilidad(avaluoConsulta, usuario);
		 compararSvc.comparaAvaluo(avaluoConsulta, usuario);
	     return avaluoConsulta;
    }
    public File obtenerPdf(Avaluo avaluo,UsuarioDto usuario) throws NegocioException, IOException{
    	if(avaluo.getObservacion() != null
    			&& avaluo.getObservacion().getArchivo() != null
    			&& avaluo.getObservacion().getArchivo().getIdDocumento() != null) {			
			File pdf = avaluoFacade.imprimir(Collections.singletonList(avaluo), true, usuario);
			double size = (double) pdf.length();
			size = (size/1024.0)/1024.0;//253476
			DecimalFormat df = new DecimalFormat("0.00");
			if(size > 4.0) {
				log.info("++++++++++++++++++++++++++++++++++Tamaño de pdf a enviar supera los 4MB: " + size);
				throw new NegocioException(TipoErrorNegocio.ERROR, "Tamaño de pdf a enviar supera los 4MB, tamaño actual " + String.valueOf(df.format(size)) + " MB");
			}
			return pdf;
	     
    	}else {
    		throw new NegocioException(TipoErrorNegocio.ERROR, "Se debe anexar documento adjunto.");
    	}
    }
    public void enviarAdjunto2(Avaluo avaluo, UsuarioDto usuario, File pdf) throws NegocioException, IOException {
    	if(avaluo.getObservacion() != null
    			&& avaluo.getObservacion().getArchivo() != null
    			&& avaluo.getObservacion().getArchivo().getIdDocumento() != null) {
    		String id = avaluo.getObservacion().getArchivo().getIdDocumento();
	    	String nombreArchivo = avaluo.getObservacion().getArchivo().getNombreArchivo();				
			byte[] adjunto = avaluoFacade.obtenerDocumento(id);
			InputStream adjunt = new ByteArrayInputStream(adjunto);
			
			InputStream reporte = new FileInputStream(pdf);
			archivoSvc.guardarEnBus(adjunt, nombreArchivo , usuario , avaluo);
			log.info("Dirección del archivo a enviar " + pdf.getAbsolutePath());
			archivoSvc.guardarEnBus(reporte, pdf.getName() , usuario , avaluo);
			//mejora: guardar reporte o pdf del avaluo en S3 y guardar en algun lugar el id
    	}else {
    		throw new NegocioException(TipoErrorNegocio.ERROR, "Se debe anexar documento adjunto.");
    	}
	}
    
    public void enviarAdjuntos(Avaluo avaluo, UsuarioDto usuario) throws NegocioException, IOException {
    	if(avaluo.getObservacion() != null
    			&& avaluo.getObservacion().getArchivo() != null
    			&& avaluo.getObservacion().getArchivo().getIdDocumento() != null) {
    		String id = avaluo.getObservacion().getArchivo().getIdDocumento();
	    	String nombreArchivo = avaluo.getObservacion().getArchivo().getNombreArchivo();				
			byte[] adjunto = avaluoFacade.obtenerDocumento(id);
			InputStream adjunt = new ByteArrayInputStream(adjunto);
			File pdf = avaluoFacade.imprimir(Collections.singletonList(avaluo), true, usuario);
			double size = (double) pdf.length();
			size = (size/1024.0)/1024.0;//253476
			DecimalFormat df = new DecimalFormat("0.00");
			if(size > 0.0) {
			
				log.info("++++++++++++++++++++++++++++++++++Tamaño de pdf a enviar supera los 4MB: " + size);
				throw new NegocioException(TipoErrorNegocio.ERROR, "Tamaño de pdf a enviar supera los 4MB, tamaño actual " + String.valueOf(df.format(size)) + " MB");
			}
			InputStream reporte = new FileInputStream(pdf);
	        //log.info("Envío del CTL");
			archivoSvc.guardarEnBus(adjunt, nombreArchivo , usuario , avaluo);
			log.info("Dirección del archivo a enviar " + pdf.getAbsolutePath());
			archivoSvc.guardarEnBus(reporte, pdf.getName() , usuario , avaluo);
			//mejora: guardar reporte o pdf del avaluo en S3 y guardar en algun lugar el id
    	}else {
    		throw new NegocioException(TipoErrorNegocio.ERROR, "Se debe anexar documento adjunto.");
    	}
	}
    
    public void enviarNotificacion(Avaluo avaluo, UsuarioDto usuario) throws NegocioException {
    	complementosImpl.enviarNotificacion(avaluo, usuario);
    }

    @Override
    public void ejecutarJobAvaluo(AvaluoFechaEstadoDTO consulta) throws NegocioException, Exception {
	Long idUltimoAvaluo = avaluoDao.obtenerIdUltimoAvaluoRegistrado();
	consulta.setIdUltimoAvaluo(idUltimoAvaluo);
	procedatoSvc.generar(new Date(), null, true);
    }

    @Override
    public void impresionErronea(List<Avaluo> avaluos, UsuarioDto usuario) throws NegocioException {
	if (usuario == null || usuario.getUsuario() == null || UtilTexto.estaVacio(usuario.getUsuario().getCodigo())) {
	    throw mgrExc.lanzarExcepcion(11L, TipoErrorNegocio.ERROR);
	}
	if (avaluos == null || avaluos.isEmpty()) {
	    throw mgrExc.lanzarExcepcion(53L, TipoErrorNegocio.ERROR);
	}
	for (Avaluo avaluo : avaluos) {
	    avaluo.setFechaImpresion(avaluo.getFechaImpresionAnterior());
	    avaluo.setImpreso(false);
	    avaluo.setFechaTransaccion(new Date());
	    avaluo.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
	    avaluoDao.actualizar(avaluo);
	}
    }

    @Override
    public ArrayList<String> obtenerExtentCentroide(String departamentoDivipola, String ciudadDivipola, String direccion, String barrio) throws Exception {
    	log.info("ingresa a obtener centroide");
    	URL url = construirUrl(departamentoDivipola, ciudadDivipola, direccion, barrio);
	ArrayList<String> results = obtenerResults(url);
	log.info(results.toString());
	int intentos = 0;
	while (results.get(0).equalsIgnoreCase("None")) {
	    if (intentos == 0) {
		url = construirUrl(departamentoDivipola, ciudadDivipola, direccion, null);
		results = obtenerResults(url);
	    } else if (intentos == 1) {
	    	log.info("Ingreso al segundo intento de obtener coordenadas");
		url = construirUrl(departamentoDivipola, ciudadDivipola, null, null);
		results = obtenerResults(url);
	    } else if (intentos == 2) {
		url = construirUrl(departamentoDivipola, null, null, null);
		results = obtenerResults(url);
	    }
	    if (intentos > 2) {
		break;
	    }
	    intentos++;
	}
	return results;
    }

    public URL construirUrl(String departamentoDivipola, String ciudadDivipola, String direccion, String barrio) throws Exception {
	StringBuilder urlApi = new StringBuilder();
	urlApi.append(UtilConstantes.URL_API_REST_ARCGIS);
	urlApi.append(UtilConstantes.URL_API_REST_ARCGIS_DIVIPOLA);
	urlApi.append(UtilTexto.estaVacio(ciudadDivipola) ? departamentoDivipola : ciudadDivipola);
	urlApi.append(UtilConstantes.URL_API_REST_ARCGIS_DIRECCION);
	if (!UtilTexto.estaVacio(direccion)) {
	    if (direccion.contains(" ")) {
		direccion = direccion.replace(" ", "+");
	    }
	    urlApi.append(UtilTexto.removerCaracteresEspeciales(direccion));
	}
	urlApi.append(UtilConstantes.URL_API_REST_ARCGIS_BARRIO);
	if (!UtilTexto.estaVacio(barrio)) {
	    if (barrio.contains(" "))
		barrio = barrio.replace(" ", "+");
	    urlApi.append(UtilTexto.removerCaracteresEspeciales(barrio));
	}
	urlApi.append(UtilConstantes.URL_API_REST_ARCGIS_FIN);
	return new URL(urlApi.toString());
    }

    public ArrayList<String> obtenerResults(URL url) throws Exception {
	ArrayList<String> results = new ArrayList<String>();
	HttpURLConnection conn = null;
	int estadoConexion = 0;
	try {
	    conn = (HttpURLConnection) url.openConnection();
	    conn.connect();
	    estadoConexion = conn.getResponseCode();
	} catch (Exception e) {
	    log.error(e);
	}
	if (!enviarCorreoEstadoApiRest(estadoConexion, false)) {
	    results.add(0, "Error");
	    return results;
	}
	InputStreamReader in = new InputStreamReader(conn.getInputStream());
	BufferedReader br = new BufferedReader(in);
	String output;
	StringBuilder result = new StringBuilder();
	while ((output = br.readLine()) != null) {
	    result.append(output);
	}
	if (!result.toString().toLowerCase().contains("<pre>")) {
	    results.add(0, "None");
	    return results;
	}
	String json = result.substring(result.lastIndexOf("<pre>") + 5, result.lastIndexOf("</pre>"));
	ObjectMapper mapper = new ObjectMapper();
	JsonNode rootNode = mapper.readTree(json);
	JsonNode getResults = rootNode.path("results");
	if (!getResults.isArray()) {
	    results.add(0, "None");
	    return results;
	}
	for (JsonNode jsonResult : getResults) {
		log.info(jsonResult.toString());
	    String paramName = jsonResult.path("paramName").getTextValue();
	    if (paramName.equalsIgnoreCase("EXTENT")) {
		String value = jsonResult.path("value").getTextValue();
		String extentMinX = value.substring(value.lastIndexOf("EXT_MIN_X:") + 10, value.indexOf(",") - 1);
		String getMinY = value.substring(value.lastIndexOf("EXT_MIN_Y:"), value.lastIndexOf(","));
		String extentMinY = getMinY.substring(getMinY.lastIndexOf("EXT_MIN_Y:") + 10, getMinY.indexOf(",") - 1);
		String getMaxX = value.substring(value.lastIndexOf("EXT_MAX_X:"), value.lastIndexOf(",") - 1);
		String extentMaxX = getMaxX.substring(getMaxX.lastIndexOf("EXT_MAX_X:") + 10);
		String extentMaxY = value.substring(value.lastIndexOf("EXT_MAX_Y:") + 10);
		results.add(extentMinX);
		results.add(extentMinY);
		results.add(extentMaxX);
		results.add(extentMaxY);
	    } else if (paramName.equalsIgnoreCase("CENTROIDE")) {
		String value = jsonResult.path("value").getTextValue();
		String centroideLongitud = value.substring(value.lastIndexOf("COOR_X:") + 7, value.indexOf(","));
		log.info("longitud: "+centroideLongitud);
		String centroideLatitud = value.substring(value.lastIndexOf("COOR_Y:") + 7);
		if (!centroideLongitud.equalsIgnoreCase("None"))
		    results.add(centroideLongitud);
		if (!centroideLatitud.equalsIgnoreCase("None"))
		    results.add(centroideLatitud);
	    } else if (paramName.equalsIgnoreCase("EXITO_GEOCODIFICACION")) {
		String value = jsonResult.path("value").getTextValue();
		if (!value.equalsIgnoreCase("None") && value.equalsIgnoreCase("true"))
		    results.add(value);
	    }
	}
	if (results.isEmpty() || results.isEmpty())
	    results.add(0, "None");
	return results;

    }

    private boolean enviarCorreoEstadoApiRest(int estadoConexion, boolean reenviar) throws Exception {
	boolean estado = false;
	if (estadoConexion == UtilConstantes.URL_STATUS_OK) {
	    estado = true;
	}
//	String email = diligenciamientoSvc.consultarDestinatariosNotificacion();
	String email;
	String numDoc = "123456789"; // Numero de documento.
	String tipDoc = "CC"; // Tipo de documento.
	Date currentDate = new Date();
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
	DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	String fechaActual = dateFormat.format(currentDate);
	String horaActual = timeFormat.format(currentDate);
	SolicitudesCliente solicitud = null;
	// Consultar parametros del estado de consulta.
	String tipoParametro = "consultarApiArcgis";
	String nombreParametro = "status";
	Parametrizacion parametroAsegurabilidad = parametrizacionDao.getParametro(tipoParametro, nombreParametro);
	String estadoParametroActual = parametroAsegurabilidad.getValorparametro();
	if ((estadoConexion != UtilConstantes.URL_STATUS_OK)
		&& (reenviar || UtilTexto.estaVacio(estadoParametroActual) || estadoParametroActual.equalsIgnoreCase(UtilConstantes.SERVICIO_ARCGIS_ACTIVO))) {
		email = diligenciamientoSvc.consultarDestinatariosNotificacion(UtilConstantes.PARAMETRIZACION_NOTIFICACION_DESTINATARIO_GEOREFERENCIACIONCAIDA);
	    solicitud = solicitudesClienteSvc.georreferenciacionCaido(email, numDoc, tipDoc, new String[] { "Servicio de georreferenciación caido", fechaActual, horaActual });
	    parametroAsegurabilidad.setValorparametro(UtilConstantes.SERVICIO_ARCGIS_INACTIVO);
	    try {
		parametrizacionDao.actualizar(parametroAsegurabilidad);
		estado = false;
	    } catch (NegocioException e) {
		log.error(e);
	    }
	} else if ((estadoConexion == UtilConstantes.URL_STATUS_OK )
		&& estadoParametroActual.equalsIgnoreCase(UtilConstantes.SERVICIO_ARCGIS_INACTIVO)) {
	    email = diligenciamientoSvc.consultarDestinatariosNotificacion(UtilConstantes.PARAMETRIZACION_NOTIFICACION_DESTINATARIO_GEOREFERENCIACIONFUNCIONANDO);
		solicitud = solicitudesClienteSvc.georreferenciacionFuncionando(email, numDoc, tipDoc,
		    new String[] { "Servicio de georreferenciación activo", fechaActual, horaActual });
	    parametroAsegurabilidad.setValorparametro(UtilConstantes.SERVICIO_ARCGIS_ACTIVO);
	    try {
		parametrizacionDao.actualizar(parametroAsegurabilidad);
		estado = true;
	    } catch (NegocioException e) {
		log.error(e);
	    }

	}
	if (solicitud != null) {
	    SolicitudesClienteThread sct = new SolicitudesClienteThread(solicitud);
	    sct.EnviarCorreo();
	}
	return estado;
    }

    @Override
    public void ejecutarJobAsegurabilidad() throws Exception {

	List<Avaluo> avaluosPendientes = avaluoDao.obtenerAvaluosPendienteAsegurabilidad();
	List<Avaluo> avaluosActualizados = new ArrayList<>();

	boolean servicioCaido = false;

	for (Avaluo avaluoPendiente : avaluosPendientes) {
	    try {
		String asegurabilidad = integradorFacade.obtenerAsegurabilidad(avaluoPendiente.getLatitudSFBUA(), avaluoPendiente.getLongitudSFBUA());
		avaluoPendiente.setAsegurabilidad(asegurabilidad);
		avaluoDao.actualizar(avaluoPendiente);
		avaluosActualizados.add(avaluoPendiente);
	    } catch (NegocioException e) {
		servicioCaido = true;
	    }
	    try {
		PeritosEmpresa perito = peritoSvc.consultarPeritoDocumento(Long.valueOf(avaluoPendiente.getUsuarioCreacion()));
		Usuario usuarion = new Usuario();
		usuarion.setCodigo(avaluoPendiente.getUsuarioCreacion());
		UsuarioDto usuario = new UsuarioDto(usuarion);
		diligenciamientoSvc.confirmarAlerta(avaluoPendiente, UtilConstantes.ALT_ZONA_RIESGO_ALTO, usuario, avaluoPendiente.getAsegurabilidad().equals(SiNo.NO.getValor()));
		if (avaluoPendiente.getAsegurabilidad().equals(SiNo.NO.getValor())) {
		    new SolicitudesClienteThread(solicitudesClienteSvc.aprobadoZonaNoAsegurable(diligenciamientoSvc.consultarDestinatariosNotificacion(UtilConstantes.PARAMETRIZACION_NOTIFICACION_DESTINATARIO_ZONANOASEGURABLE),
			    UtilNumero.pasarTexto(avaluoPendiente.getNumeroIdentificacion()),
			    parametrizacionSvc.consultarDescripcionDominio("TIPOIDENTIFICACION", UtilNumero.pasarTexto(avaluoPendiente.getIdTipoIdentificacion())),
			    new String[] { UtilConstantes.ALT_ZONA_RIESGO_ALTO_ASUNTO, UtilNumero.pasarTexto(avaluoPendiente.getConsecutivoBanco().longValue()),
				    UtilNumero.pasarTexto(perito.getNumeroDocumento()), perito.getNombrePerito() })).EnviarCorreo();
		}
	    } catch (Exception e) {
		log.error(e);
	    }
	}
	if (avaluosPendientes != null && !avaluosPendientes.isEmpty())
	    compararYEnviarCorreo(avaluosActualizados);
	verificarEstadoAsegurabilidad(servicioCaido, true);
	try {
	    // Se intenta realizar la comprobacion del servicio de arcgis frente a el
	    // departamento de bogota.
	    HttpURLConnection conn = (HttpURLConnection) construirUrl("14", null, null, null).openConnection();
	    conn.connect();
	    int estadoConexion = conn.getResponseCode();
	    enviarCorreoEstadoApiRest(estadoConexion, true);
	} catch (Exception e) {
	    log.error(e);
	}

    }

    private void verificarEstadoAsegurabilidad(boolean servicioCaido, boolean reenviar) throws Exception {

//	String email = diligenciamientoSvc.consultarDestinatariosNotificacion();
    String email;
    String numDoc = "123456789"; // Numero de documento.
	String tipDoc = "CC"; // Tipo de documento.
	Date currentDate = new Date();
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
	DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	String fechaActual = dateFormat.format(currentDate);
	String horaActual = timeFormat.format(currentDate);
	SolicitudesCliente solicitud = null;

	// Consultar parametros del estado de asegurabilidad.

	String tipoParametro = "consultarAsegurabilidad";
	String nombreParametro = "status";
	Parametrizacion parametroAsegurabilidad = parametrizacionDao.getParametro(tipoParametro, nombreParametro);
	String estadoParametroActual = parametroAsegurabilidad.getValorparametro();
	if (servicioCaido && (reenviar || UtilTexto.estaVacio(estadoParametroActual) || estadoParametroActual.equalsIgnoreCase(UtilConstantes.ASEGURABILIDAD_ACTIVA))) {
	    email = diligenciamientoSvc.consultarDestinatariosNotificacion(UtilConstantes.PARAMETRIZACION_NOTIFICACION_DESTINATARIO_ASEGURABILIDADCAIDO);
		solicitud = solicitudesClienteSvc.asegurabilidadCaido(email, numDoc, tipDoc, new String[] { "Servicio de asegurabilidad caido", fechaActual, horaActual });
	    parametroAsegurabilidad.setValorparametro(UtilConstantes.ASEGURABILIDAD_INACTIVA);
	    try {
		parametrizacionDao.actualizar(parametroAsegurabilidad);
	    } catch (NegocioException e) {
		e.printStackTrace();
	    }
	} else if (!servicioCaido && estadoParametroActual != null && estadoParametroActual.equalsIgnoreCase(UtilConstantes.ASEGURABILIDAD_INACTIVA)) {
		email = diligenciamientoSvc.consultarDestinatariosNotificacion(UtilConstantes.PARAMETRIZACION_NOTIFICACION_DESTINATARIO_ASEGURABILIDADFUNCIONANDO);
	    solicitud = solicitudesClienteSvc.asegurabilidadPwaFuncionando(email, numDoc, tipDoc, new String[] { "Servicio de asegurabilidad activo", fechaActual, horaActual });
	    parametroAsegurabilidad.setValorparametro(UtilConstantes.ASEGURABILIDAD_ACTIVA);
	    try {
		parametrizacionDao.actualizar(parametroAsegurabilidad);
	    } catch (NegocioException e) {
		log.error(e);
	    }
	}
	if (solicitud != null) {
	    SolicitudesClienteThread sct = new SolicitudesClienteThread(solicitud);
	    sct.EnviarCorreo();
	}
    }

    private void compararYEnviarCorreo(List<Avaluo> avaluosActualizados) throws NegocioException {
	int pendientes = 0;
	int satisfactorios = 0;
	for (Avaluo avaluoActualizado : avaluosActualizados) {
	    if (avaluoActualizado.getAsegurabilidad().equalsIgnoreCase(UtilConstantes.CONCEPTO_ASEGURABILIDAD_PENDIENTE))
	    	pendientes++;
	    else
	    	satisfactorios++;
	}
	if (satisfactorios > 0 || pendientes > 0)
	    enviarCorreoTareaAsegurabilidad(satisfactorios, pendientes);

    }

    private void enviarCorreoTareaAsegurabilidad(int satisfactorios, int pendientes) throws NegocioException {
	String email = diligenciamientoSvc.consultarDestinatariosNotificacion(UtilConstantes.PARAMETRIZACION_NOTIFICACION_DESTINATARIO_ASEGURABILIDADFINALIZADA);
	String numDoc = "123456789"; // Numero de documento.
	String tipDoc = "CC"; // Tipo de documento.
	String cantidadSatisfactorios = String.valueOf(satisfactorios);
	String cantidadPendientes = String.valueOf(pendientes);
	SolicitudesCliente solicitud = solicitudesClienteSvc.tareaAsegurabilidadFinalizada(email, numDoc, tipDoc,
		new String[] { "Tarea programada, actualización concepto de asegurabilidad.", cantidadSatisfactorios, cantidadPendientes });
	try {
	    SolicitudesClienteThread sct = new SolicitudesClienteThread(solicitud);
	    sct.EnviarCorreo();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Override
    public List<ArchivoPlano> consultarCamposObligatoriosCategoria(Long categoria, String tabla) throws NegocioException {
	if (categoria == null)
	    return Collections.emptyList();
	return archivoPlanoDao.buscarObligatoriosCategoria(categoria, tabla);
    }

	@Override
	public List<Avaluo> consultarAvaluoPorMatriculaInmobiliaria(ConsultaAvaluoDto consulta, int inicio,
			int registroXPagina, String campoOrden, SentidoOrientacion sentido) throws NegocioException {
		return avaluoDao.consultarAvaluo(consulta, inicio, registroXPagina, campoOrden, sentido);
	}
    

}
