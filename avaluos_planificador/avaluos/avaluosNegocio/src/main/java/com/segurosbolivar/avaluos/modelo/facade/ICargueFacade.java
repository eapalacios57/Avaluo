package com.segurosbolivar.avaluos.modelo.facade;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.dto.CargueMasivoDtoBean;
import com.segurosbolivar.avaluos.modelo.dto.ConsultaCargueMasivoDto;
import com.segurosbolivar.avaluos.modelo.dto.DetalleMasivoDto;
import com.segurosbolivar.avaluos.modelo.dto.ResultadoCargueMasivoDto;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;

/**
 * Abstrae la capa de presentaci�n y los servicios para la vista de cargue
 * masivo.
 * 
 * 
 * Realiza la invocaci�n de las listas necesarias dentro de la vista, la el
 * procesamiento del archivo y la generaci�n de los avaluos respectivos del
 * cargue, registra el log y los errores presentados durante la operaci�n.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:43 a.m.
 */
@Local
public interface ICargueFacade extends Serializable {

	void cargarArchivos();

	List<ResultadoCargueMasivoDto> consultar(ConsultaCargueMasivoDto consulta, int inicio, int registroxPagina, String campoOrden,
			SentidoOrientacion sentido);

	int cantidadRegistros(ConsultaCargueMasivoDto consulta) throws NegocioException;

	Long cantidadRegistros(DetalleMasivoDto consulta) throws NegocioException;

	int consultarCiudades();

	int consultarDepartamentos();

	List<DetalleMasivoDto> consultarDetalle(DetalleMasivoDto referenciaCargue, int inicio, int registroxPagina, String campoOrden,
			SentidoOrientacion sentido);

	void consultarLiquidacion();

	void consultarOfertaDemanda();

	void consultarRegistroFotografico();

	int consultarValoresDominio();

	void guardar();

	void procesarCargue(List<CargueMasivoDtoBean> cargues, UsuarioDto usuario, boolean esConstructor)
			throws NegocioException;

	void validar();

	void validarDireccionComplementaria();

	void validarDireccionPrincipal();

	void validarEstructuraArchivos();

}