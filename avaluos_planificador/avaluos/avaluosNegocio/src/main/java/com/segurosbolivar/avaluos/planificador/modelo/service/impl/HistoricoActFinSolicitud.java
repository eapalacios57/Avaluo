package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.segurosbolivar.avaluos.planificador.data.HistoricoActFinanSolicitudDao;
import com.segurosbolivar.avaluos.planificador.modelo.entity.HistActFinancSolicitud;
import com.segurosbolivar.avaluos.planificador.modelo.services.IHistoricoActFinSolicitud;

@Stateless
@Local(IHistoricoActFinSolicitud.class)
public class HistoricoActFinSolicitud implements IHistoricoActFinSolicitud {
	
	@EJB
	private HistoricoActFinanSolicitudDao historicoDao;

	@Override
	public List<HistActFinancSolicitud> buscarPorCodSolicitudYCodActividd(String codSolicitud, String codActividad) throws SQLException {
		return historicoDao.buscarPorCodSolicitudYCodActividad(codSolicitud, codActividad);
	}
	
	@Override
	public List<HistActFinancSolicitud> buscarPorIdActFinSolicitud(BigDecimal idActFinSol) throws SQLException {
		return historicoDao.buscarPorIdActFinSol(idActFinSol);
	}

}
