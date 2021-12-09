package com.segurosbolivar.avaluos.modelo.service;

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
 * Provee los servicios para el cargue masivo, como la importaci�n y
 * procesamiento de lo archivos, para cargar los diferentes aval�os incluidos
 * los de proyectos constructor.
 * 
 * Se integra directamente con el servicio de aval�o para realizar la validaci�n
 * y verificaci�n de cada uno de los registros.
 * 
 * Tambi�n permite la consulta de los resultados de la operaci�n de cargue
 * masivo, de forma que sea posible el seguimiento a este proceso.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:43 a.m.
 */
@Local
public interface ICargueMasivo extends Serializable{

	void cargarArchivos();

	List<ResultadoCargueMasivoDto> consultar(ConsultaCargueMasivoDto consulta, int inicio, int registroxPagina, String campoOrden,
			SentidoOrientacion sentido);

	int cantidadRegistros(ConsultaCargueMasivoDto consulta) throws NegocioException;
	
	Long cantidadRegistros(DetalleMasivoDto consulta) throws NegocioException;
	
	List<DetalleMasivoDto> consultarDetalle(DetalleMasivoDto consulta, int inicio, int registroxPagina, String campoOrden,
			SentidoOrientacion sentido);

	void procesarCargue(CargueMasivoDtoBean cargue, UsuarioDto usuario, boolean esConstructor)throws NegocioException;

	void validarEstructuraArchivos();

}