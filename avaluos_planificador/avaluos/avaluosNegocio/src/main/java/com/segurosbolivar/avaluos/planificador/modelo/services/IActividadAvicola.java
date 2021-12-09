package com.segurosbolivar.avaluos.planificador.modelo.services;

import java.sql.SQLException;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadAvicolaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadAvicola;
import java.io.Serializable;

@Local
public interface IActividadAvicola extends Serializable{

	public ActividadAvicolaDTO getActividadAvicola( ActividadAvicolaDTO actividadAvicolaDTO) throws SQLException;
	
	public ActividadAvicola crearActividadAvicola(ActividadAvicola actividadAvicola) throws  NegocioException,javax.ejb.TransactionRolledbackLocalException;
	public void EliminarActividadAvicola(ActividadAvicola actividadAvicola) throws  NegocioException,javax.ejb.TransactionRolledbackLocalException;
	public void actualizarActAvicola(ActividadAvicolaDTO avicolaDTO) throws NegocioException;
	
}
