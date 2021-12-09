package com.segurosbolivar.avaluos.planificador.modelo.services;


import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ValorPorcentaje;
import java.io.Serializable;

@Local
public interface IValorPorcentaje extends Serializable{

	public ValorPorcentaje crearValorPorcentaje(ValorPorcentaje valorPorcentaje) throws NegocioException,javax.ejb.TransactionRolledbackLocalException;
	
	public void eliminarValorPorcentaje(ValorPorcentaje valorPorcentaje) throws NegocioException,javax.ejb.TransactionRolledbackLocalException;
	
}
