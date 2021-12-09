package com.segurosbolivar.avaluos.planificador.modelo.services;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.segurosbolivar.avaluos.modelo.entity.Dominios;
import com.segurosbolivar.avaluos.planificador.modelo.dto.DominiosPlanificadorDTO;

/**
 * Se encarga de las operaciones referentes a los archivos del sistema. Desde la
 * creaci�n de la copia de seguridad con los aval�os, a la consulta de los
 * complementos y manuales del sistema.
 * 
 * Tambi�n es usado por los servicios de cargue masivo y procedato, para los
 * archivos comprimidos que deben procesarse en cada uno.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:42 a.m.
 */
@Local
public interface IDominios extends Serializable {

	public List<Dominios> getDominios() throws SQLException;
	public List<Dominios> listaDominios() throws SQLException;
	public Dominios getDominioPorCodigo(String codigoDominio) throws SQLException;
	public void crearDominios(DominiosPlanificadorDTO dominios);
	

}