package com.segurosbolivar.avaluos.planificador.data;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.HistActFinancSolicitud;

@Stateless
public class HistoricoActFinanSolicitudDao extends ManejadorCrud<HistActFinancSolicitud, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8464755267030760375L;

	@SuppressWarnings("unchecked")
	public List<HistActFinancSolicitud> buscarPorCodSolicitudYCodActividad(String codSolic, String codAct)
			throws SQLException {
		Query consulta = mp.createNamedQuery("HistActFinancSolicitud.findByCodigoSolicitudAndCodigoActividad");
		consulta.setParameter("codigoSolicitud", codSolic);
		consulta.setParameter("codigoActividad", codAct);
		return consulta.getResultList();

	}
	
	@SuppressWarnings("unchecked")
	public List<HistActFinancSolicitud> buscarPorIdActFinSol(BigDecimal idActFinSol)
			throws SQLException {
		Query consulta = mp.createNamedQuery("HistActFinancSolicitud.findByIdActFinSol");
		consulta.setParameter("idActFinSol", idActFinSol);
		return consulta.getResultList();

	}

}
