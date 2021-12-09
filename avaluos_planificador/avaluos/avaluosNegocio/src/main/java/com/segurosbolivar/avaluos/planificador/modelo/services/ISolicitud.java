package com.segurosbolivar.avaluos.planificador.modelo.services;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.modelo.dto.PlanificadorDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Solicitud;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.solicitud.PlanificadorModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.solicitud.SolicitudAsignadaModel;

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
public interface ISolicitud extends Serializable {


	public List<Solicitud> listaSolicitudes() throws SQLException;
	public List<Solicitud> listaSolicitudes(PlanificadorDTO planificadorDTO) throws SQLException;
	public List<Solicitud> listarSolicitudesPlanificador(String numeroDocumentoPlanificador) throws SQLException;
	public List<Solicitud> listaSolicitudes(String perfil,String usuario,String criterioSolicitud) throws SQLException;
	public Solicitud getSolicitud( String cod) throws SQLException;
	public Solicitud buscarPorId(String codigoSolicitud) throws SQLException;
	public Solicitud actualziarSolicitud(SolicitudDTO solicitudDto) throws SQLException;
	public void crearSolicitud(Solicitud solicitud) throws ParseException, NegocioException, SQLException;
	public List<SolicitudAsignadaModel> consultarSolicitud(PlanificadorModel planificadorModel ) throws SQLException, NegocioException;
	public Date ultimaSolicitud(String numeroDocumentoBeneficiario) throws NegocioException;
	public void eliminarSolicitud(Solicitud solicitud) throws ParseException, NegocioException, SQLException;

}