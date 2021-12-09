package com.segurosbolivar.avaluos.modelo.facade.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.asesoftware.util.archivo.UtilArchivos;
import com.asesoftware.util.archivo.UtilPropiedades;
import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.dto.CargueMasivoDtoBean;
import com.segurosbolivar.avaluos.modelo.dto.ConsultaCargueMasivoDto;
import com.segurosbolivar.avaluos.modelo.dto.DetalleMasivoDto;
import com.segurosbolivar.avaluos.modelo.dto.ResultadoCargueMasivoDto;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.facade.ICargueFacade;
import com.segurosbolivar.avaluos.modelo.service.IArchivo;
import com.segurosbolivar.avaluos.modelo.service.IAvaluo;
import com.segurosbolivar.avaluos.modelo.service.ICargueMasivo;
import com.segurosbolivar.avaluos.modelo.service.IParametrizacion;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;

/**
 * Implementaci�n de la fachada para realizar el cargue masivo de aval�os.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:40 a.m.
 */
@Stateless
public class CargueFacadeImpl implements ICargueFacade {

	private static final long serialVersionUID = -1104052340867333625L;
	@EJB
	public ICargueMasivo cargueMasivoSvc;
	@EJB
	public IArchivo archivoSvc;
	@EJB
	public IParametrizacion parametrizacionSvc;
	@EJB
	public IAvaluo avaluoSvc;

	@Override
	public void cargarArchivos() {
		
	}

	@Override
	public  List<ResultadoCargueMasivoDto> consultar(ConsultaCargueMasivoDto consulta, int inicio, int registroxPagina, String campoOrden,
			SentidoOrientacion sentido) {
		return cargueMasivoSvc.consultar(consulta, inicio, registroxPagina, campoOrden, sentido);
	}

	@Override
	public int consultarCiudades() {
		return 0;
	}

	@Override
	public int consultarDepartamentos() {
		return 0;
	}

	@Override
	public List<DetalleMasivoDto> consultarDetalle(DetalleMasivoDto referencia, int inicio, int registroxPagina, String campoOrden,
			SentidoOrientacion sentido) {
		return cargueMasivoSvc.consultarDetalle(referencia, inicio, registroxPagina, campoOrden, sentido);
	}

	@Override
	public void consultarLiquidacion() {

	}

	@Override
	public void consultarOfertaDemanda() {

	}

	@Override
	public void consultarRegistroFotografico() {

	}

	@Override
	public int consultarValoresDominio() {
		return 0;
	}

	@Override
	public void guardar() {

	}

	@Override
	public void procesarCargue(List<CargueMasivoDtoBean> cargues, UsuarioDto usuario, boolean esConstructor)throws NegocioException {
		UtilArchivos.deleteFilesOlderThanNdays(UtilConstantes.DIAS_BORRADO_CARGUE, UtilPropiedades.cargarPropiedad(UtilConstantes.RSC_ERRORES, UtilConstantes.RUTA_CARGUE_MASIVO));
		for(CargueMasivoDtoBean cargue:cargues) {
			cargueMasivoSvc.procesarCargue(cargue, usuario, esConstructor);			
		}
	}

	@Override
	public void validar() {

	}

	@Override
	public void validarDireccionComplementaria() {

	}

	@Override
	public void validarDireccionPrincipal() {

	}

	@Override
	public void validarEstructuraArchivos() {

	}

	@Override
	public int cantidadRegistros(ConsultaCargueMasivoDto consulta) throws NegocioException {
		return cargueMasivoSvc.cantidadRegistros(consulta);
	}
	@Override
	public Long cantidadRegistros(DetalleMasivoDto consulta) throws NegocioException {
		return cargueMasivoSvc.cantidadRegistros(consulta);
	}

}