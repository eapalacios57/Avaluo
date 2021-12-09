package com.segurosbolivar.avaluos.planificador.modelo.services;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import com.asesoftware.util.exception.NegocioException;
import javax.ejb.Local;

import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudBeneficiarioDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.SolicitudBeneficiario;


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
public interface ISolicitudBeneficiario extends Serializable {

	public SolicitudBeneficiario getSolicitudBeneficiario(String codSolicitud);
	public List<SolicitudBeneficiario> listaSolicitudBeneficiario() throws SQLException;
	public void crearSolicitudBeneficiario(SolicitudBeneficiario solicitudBeneficiario) throws NegocioException;
	void eliminarSolicitudBeneficiario(SolicitudBeneficiario solicitudBeneficiario) throws NegocioException;
	

}