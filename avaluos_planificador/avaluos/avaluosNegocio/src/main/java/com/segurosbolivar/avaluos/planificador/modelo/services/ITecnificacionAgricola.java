package com.segurosbolivar.avaluos.planificador.modelo.services;

import java.io.Serializable;
import java.sql.SQLException;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.modelo.dto.TecnificacionAgricolaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.TecnificacionAgricola;

@Local
public interface ITecnificacionAgricola extends Serializable {

	public TecnificacionAgricolaDTO buscarTecnificacionAgricola( TecnificacionAgricolaDTO tecnificacionAgricolaDTO) throws SQLException;
	
	public TecnificacionAgricola crearTecnificacionAgricola(TecnificacionAgricola tecnificacionAgricola ) throws NegocioException,javax.ejb.TransactionRolledbackLocalException;
	public void actualizarTecAgricola(TecnificacionAgricolaDTO tecnificacionAgricolaDTO) throws NegocioException;
	public void eliminarTecnificacionAgricola(TecnificacionAgricola tecnificacionAgricola ) throws NegocioException,javax.ejb.TransactionRolledbackLocalException;
	
}
