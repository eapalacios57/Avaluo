package com.segurosbolivar.avaluos.modelo.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.PeritosEmpresa;

/**
 * Provee los servicios para la gesti�n sobre los peritos.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:44 a.m.
 */
@Local
public interface IPerito extends Serializable{

	/**
	 * Permite consultar la cantidad de registros de la consulta de la tabla de
	 * peritos, teniendo en cuenta los filtros aplicados.
	 * 
	 * @param peritosEmpresa
	 *            permite aplicar los filtros para la consulta de la cantidad de
	 *            registros.
	 * @return numero de registros de la consulta a realizar.
	 */
	int cantidadRegistros(PeritosEmpresa peritosEmpresa);

	/**
	 * Permite consultar una cantidad determinada de registros de la tabla de
	 * peritos, aplicando un filtro respectivo.
	 * 
	 * @param consulta
	 *            contiene los filtros asociados que vamos a aplicar a la
	 *            consulta.
	 * @param inicio:
	 *            inicio desde el que comenzamos a consultar los registros.
	 * @param registrosXPagina:
	 *            cantidad de registros que vamos a cargar desde el registro
	 *            inicial que seleccionamos.
	 * @return los peritos resultados de la consulta y que estan dentro del
	 *         limite dado para la misma.
	 */
	List<PeritosEmpresa> consultar(PeritosEmpresa consulta, int inicio, int registrosXPagina, String campoOrden, SentidoOrientacion sentido);

	
	PeritosEmpresa consultarPeritoDocumento(Long numeroDocumento) throws NegocioException;

	void guardar(PeritosEmpresa perito, UsuarioDto usuario) throws NegocioException;

	void desactivar(List<PeritosEmpresa> peritos, UsuarioDto usuario) throws NegocioException;

}