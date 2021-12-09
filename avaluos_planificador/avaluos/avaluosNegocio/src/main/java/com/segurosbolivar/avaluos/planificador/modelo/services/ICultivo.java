package com.segurosbolivar.avaluos.planificador.modelo.services;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.modelo.dto.CultivoDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Cultivo;

@Local
public interface ICultivo extends Serializable  {

	public List<Cultivo> getCultivos(CultivoDTO cultivoDTO) throws SQLException;
	public Cultivo getCultivo(BigDecimal idCultivo);
	
	public Cultivo crearCultivo(Cultivo cultivo) throws NegocioException,javax.ejb.TransactionRolledbackLocalException;
	public void eliminarCultivo(Cultivo cultivo) throws NegocioException,javax.ejb.TransactionRolledbackLocalException;
	public void actualizarCultivo(CultivoDTO cultivoDTO) throws NegocioException;
	public void actualizarListaCultivos(List<CultivoDTO> cultivosDTO) throws NegocioException;
	
}

