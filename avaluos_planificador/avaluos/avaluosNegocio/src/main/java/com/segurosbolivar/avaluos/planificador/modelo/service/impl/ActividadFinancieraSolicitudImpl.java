package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.data.ActividadFinancieraSolicitudDao;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadFinancieraSolicitud;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadFinancieraSolicitud;

/**
 * Implementaci�n del servicio de archivo
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:38 a.m.
 */
@Stateless
@Local(IActividadFinancieraSolicitud.class)
public class ActividadFinancieraSolicitudImpl implements IActividadFinancieraSolicitud {

	private static final long serialVersionUID = -2088678805488718651L;

	@EJB
	private ActividadFinancieraSolicitudDao actividadFinancieraSolicitudDao;

	/**
	 * Permite realizar el manejo de errores y la recuperacion de los mensajes
	 * respectivos segun la excepcion lanzada.
	 */
	@Override
	public List<ActividadFinancieraSolicitud> listaActividadFinancieraSolicitud() throws SQLException {
		return actividadFinancieraSolicitudDao.listarActividadFinancieraSolicitud();
	}
	
	@Override
	public void eliminarActividadFinancieraSolicitud(ActividadFinancieraSolicitud actividadFinancieraSolicitud)
			throws NegocioException, ParseException {
			actividadFinancieraSolicitudDao.eliminar(actividadFinancieraSolicitud);
	}

	@Override
	public void crearActividadFinancieraSolicitud(ActividadFinancieraSolicitud actividadFinancieraSolicitud)
			throws NegocioException, ParseException {
		// Verificar que la actividad economica no este asociada
		List<ActividadFinancieraSolicitud> lista = actividadFinancieraSolicitudDao.listaActFinSolicitudPorSolYAct(
				actividadFinancieraSolicitud.getCodigoSolicitud(), actividadFinancieraSolicitud.getCodigoActividad());

		if (lista != null && !lista.isEmpty()) {
			throw new NegocioException(TipoErrorNegocio.INFO,
					"La actividad económica seleccionada ya está asociada a la solicitud. ");
		}
		actividadFinancieraSolicitudDao.crear(actividadFinancieraSolicitud);
	}

	@Override
	public void actualizarActividadFinancieraSolicitud(ActividadFinancieraSolicitud actividadFinancieraSolicitud)
			throws NegocioException {
		// Verificar que la actividad economica no este asociada
		List<ActividadFinancieraSolicitud> lista = actividadFinancieraSolicitudDao.listaActFinSolicitudPorSolYAct(
				actividadFinancieraSolicitud.getCodigoSolicitud(), actividadFinancieraSolicitud.getCodigoActividad());

		if (lista != null && !lista.isEmpty()) {
			// Verificar que la actividad encontrada es diferente a la que se va a
			// actualizar
			for (ActividadFinancieraSolicitud actividad : lista) {
				if (!actividad.getId().equals(actividadFinancieraSolicitud.getId())) {
					throw new NegocioException(TipoErrorNegocio.INFO,
							"La actividad económica seleccionada ya está asociada a la solicitud. ");
				}
			}
		}

		actividadFinancieraSolicitudDao.actualizar(actividadFinancieraSolicitud);
	}

	@Override
	public void eliminarActividadFinancieraSolicitud(String codigoSolicitud, BigDecimal idActFinSol)
			throws NegocioException, SQLException {
		/*
		 * Validar que la solicitud quede con al menos una actividad financiera despues
		 * de la eliminacion
		 */
		List<ActividadFinancieraSolicitud> actFinancierasActuales = actividadFinancieraSolicitudDao
				.listarActFinSolicitudPorSol(codigoSolicitud);
		if (actFinancierasActuales.size() == 1) {
			throw new NegocioException(TipoErrorNegocio.INFO,
					"La solicitud debe tener relacionada al menos una actividad financiera. ");
		}

		actividadFinancieraSolicitudDao.eliminarPorId(idActFinSol);
	}

	@Override
	public List<ActividadFinancieraSolicitud> listaActFinSolicitudPorSol(String codSolicitud) throws SQLException {
		return actividadFinancieraSolicitudDao.listarActFinSolicitudPorSol(codSolicitud);
	}

}