package com.segurosbolivar.avaluos.planificador.modelo.services;

import java.sql.SQLException;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadGanaderaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadGanadera;

@Local
public interface IActividadGanadera {

	public ActividadGanaderaDTO getActividadGanadera( ActividadGanaderaDTO actividadGanaderaDTO) throws SQLException;
	
	public ActividadGanadera crearActividadGanadera(ActividadGanadera actividadGanadera) throws NegocioException,javax.ejb.TransactionRolledbackLocalException;
	public void eliminarActividadGanadera(ActividadGanadera actividadGanadera) throws NegocioException,javax.ejb.TransactionRolledbackLocalException;
	public void actualizarActividadGanadera(ActividadGanaderaDTO ganaderaDTO) throws NegocioException;
	
}
