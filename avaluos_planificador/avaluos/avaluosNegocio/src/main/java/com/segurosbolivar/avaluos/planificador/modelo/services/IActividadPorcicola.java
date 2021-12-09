package com.segurosbolivar.avaluos.planificador.modelo.services;


import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadPorcicolaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadPorcicola;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;

@Local
public interface IActividadPorcicola extends Serializable{

	public ActividadPorcicola crearActividadPorcicola(ActividadPorcicola actividadPorcicola) throws NegocioException,javax.ejb.TransactionRolledbackLocalException;
	public void eliminarActividadPorcicola(ActividadPorcicola actividadPorcicola) throws NegocioException,javax.ejb.TransactionRolledbackLocalException;
	public void actualizarActividadPorcicola(ActividadPorcicolaDTO porcicolaDTO) throws NegocioException;
	public ActividadPorcicolaDTO buscarActPiscicolaPorId(BigDecimal idPorcicola) throws SQLException;
	
}
