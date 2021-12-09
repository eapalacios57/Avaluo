package com.segurosbolivar.avaluos.planificador.modelo.services;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;
import java.text.ParseException;
import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadFinancieraSolicitud;

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
public interface IActividadFinancieraSolicitud extends Serializable {

    
	
	public List<ActividadFinancieraSolicitud> listaActividadFinancieraSolicitud() throws SQLException;
	public List<ActividadFinancieraSolicitud> listaActFinSolicitudPorSol(String codSolicitud) throws SQLException;
	public void crearActividadFinancieraSolicitud(ActividadFinancieraSolicitud actividadFinancieraSolicitud) throws NegocioException, ParseException;
	public void actualizarActividadFinancieraSolicitud(ActividadFinancieraSolicitud actividadFinancieraSolicitud) throws NegocioException;
	public void eliminarActividadFinancieraSolicitud(String codigoSolicitud, BigDecimal idActFinSol) throws NegocioException, SQLException;
	void eliminarActividadFinancieraSolicitud(ActividadFinancieraSolicitud actividadFinancieraSolicitud)
			throws NegocioException, ParseException;
	

}