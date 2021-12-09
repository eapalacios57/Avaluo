package com.segurosbolivar.avaluos.planificador.modelo.services;

import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Soporte;
import java.io.Serializable;
import java.math.BigDecimal;

@Local
public interface ISoporte extends Serializable{

	public Soporte crearSoporte(Soporte soporte) throws NegocioException,javax.ejb.TransactionRolledbackLocalException;
	public void eliminarSoporte(Soporte soporte) throws NegocioException,javax.ejb.TransactionRolledbackLocalException;
	public List<Soporte> consultaSoportesPorUndProd(Long idUnidadProductiva) throws NegocioException,javax.ejb.TransactionRolledbackLocalException;
	public List<Soporte> consultaSoportesPorCultivo(BigDecimal idCultivo) throws NegocioException,javax.ejb.TransactionRolledbackLocalException;
 	
}
