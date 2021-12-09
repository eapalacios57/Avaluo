package com.segurosbolivar.avaluos.planificador.modelo.services;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadPiscicolaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadPiscicola;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;

@Local
public interface IActividadPiscicola extends Serializable {

	public ActividadPiscicola crearActividadPiscicola(ActividadPiscicola actividadPiscicola)
			throws NegocioException, javax.ejb.TransactionRolledbackLocalException;

	public void eliminarActividadPiscicola(ActividadPiscicola actividadPiscicola)
			throws NegocioException, javax.ejb.TransactionRolledbackLocalException;
	
	public ActividadPiscicolaDTO buscarActividadPiscicola(BigDecimal idActPiscicola) throws SQLException;
	
	public void actualizarActividadPiscicola(ActividadPiscicolaDTO piscicolaDTO) throws NegocioException;

}
