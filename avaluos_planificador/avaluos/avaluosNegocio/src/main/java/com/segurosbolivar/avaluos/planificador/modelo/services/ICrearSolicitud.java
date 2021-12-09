package com.segurosbolivar.avaluos.planificador.modelo.services;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.ejb.Local;
import javax.persistence.NoResultException;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadFinancieraSolicitudDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.BeneficiarioDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.DocumentoDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.MedioComunicacionDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.PlanificadorDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudBeneficiarioDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudCreaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudMovimientoDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Beneficiario;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Planificador;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Solicitud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.SolicitudBeneficiario;
import com.segurosbolivar.avaluos.planificador.modelo.entity.SolicitudMovimiento;

@Local
public interface ICrearSolicitud {

	public String crearSolicitud(SolicitudCreaDTO solicitudCreaDTO) throws SQLException, NegocioException, NoResultException, Exception;
	public Planificador buscarPlanificador(String tipoDocumento, String numeroDocumento) throws SQLException, NoResultException, Exception;
	public Solicitud guardarSolicitud(SolicitudCreaDTO solicitudCreaDTO) throws SQLException, ParseException, NegocioException, NoResultException, Exception;
	public void crearMovimientoSolicitud(SolicitudCreaDTO solicitudCreaDTO,  Solicitud solicitud) throws Exception, NegocioException;
	public void crearActividadFinancieraSolicitud(List<ActividadFinancieraSolicitudDTO> listaActividadFinancieraSolicitudDTO,
			Solicitud solicitud) throws Exception;
	public void crearDocumentosSolicitud(SolicitudCreaDTO solicitudCreaDTO, Solicitud solicitud) throws SQLException, ParseException, Exception;
	public void crearActualizarBeneficirario(SolicitudCreaDTO solicitudCreaDTO,Solicitud solicitud)
			throws NoResultException, SQLException, NegocioException, ParseException, Exception;
	void crearSolicitudRollBack(String codSoliciutd) throws NegocioException, SQLException, ParseException;	
	
	
	
	
}
