package com.segurosbolivar.avaluos.planificador.modelo.services;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadFinancieraDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadFinanciera;


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
public interface IActividadFinanciera extends Serializable {

	public List<ActividadFinanciera> listaActividadFinancieras() throws SQLException;
	public void crearActividadFinanciera(ActividadFinancieraDTO actividadFinanciera);
	public ActividadFinanciera buscarActividadFinanciera(String codigoActividad) throws Exception;
	

}