package com.segurosbolivar.avaluos.planificador.modelo.services;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.segurosbolivar.avaluos.planificador.modelo.entity.HistActFinancSolicitud;

@Local
public interface IHistoricoActFinSolicitud {
	
	public List<HistActFinancSolicitud> buscarPorCodSolicitudYCodActividd(String codSolicitud, String codActividad)  throws SQLException;
	public List<HistActFinancSolicitud> buscarPorIdActFinSolicitud(BigDecimal idActFinSol) throws SQLException;

}
