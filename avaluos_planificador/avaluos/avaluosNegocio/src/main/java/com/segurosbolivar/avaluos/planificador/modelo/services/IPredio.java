package com.segurosbolivar.avaluos.planificador.modelo.services;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.modelo.dto.PredioDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Predio;
import java.io.Serializable;

@Local
public interface IPredio extends Serializable {

	//public Predio crearPredio(Predio predio) throws NegocioException;
	public PredioDTO buscaPredioPorId(BigDecimal codigoPredio) throws NegocioException;
	public PredioDTO actualizaPredio(PredioDTO predio) throws NegocioException;
	public List<PredioDTO> consultaPredio() throws NegocioException;
	public Predio crearPredio(Predio predio) throws NegocioException,javax.ejb.TransactionRolledbackLocalException;
	public void eliminarPredio(Predio predio) throws NegocioException,javax.ejb.TransactionRolledbackLocalException;
	
}
