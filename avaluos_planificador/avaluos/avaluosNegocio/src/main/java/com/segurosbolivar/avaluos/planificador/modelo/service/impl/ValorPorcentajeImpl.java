package com.segurosbolivar.avaluos.planificador.modelo.service.impl;


import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionRolledbackLocalException;


import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.data.ValorPorcentajeDao;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ValorPorcentaje;
import com.segurosbolivar.avaluos.planificador.modelo.services.IValorPorcentaje;

@Stateless
@Local(IValorPorcentaje.class)
public class ValorPorcentajeImpl implements IValorPorcentaje {

	private static final long serialVersionUID = -2088678805488718651L;

	//ValorPorcentajeFullDTOMapper valorPorcentajeFullDTOMapper=Mappers.getMapper(ValorPorcentajeFullDTOMapper.class);
	
	@EJB
	private ValorPorcentajeDao valorPorcentajeDao;


	@Override
	public ValorPorcentaje crearValorPorcentaje(ValorPorcentaje valorPorcentaje) throws NegocioException,javax.ejb.TransactionRolledbackLocalException {

		//ValorPorcentaje valorPorcentaje=valorPorcentajeFullDTOMapper.dto2entity(valorPorcentajeDTO);
		valorPorcentajeDao.crear(valorPorcentaje);
		return valorPorcentaje;
	}


	@Override
	public void eliminarValorPorcentaje(ValorPorcentaje valorPorcentaje)
			throws NegocioException, TransactionRolledbackLocalException {
		valorPorcentajeDao.eliminar(valorPorcentaje);
		
	}
	
	
	
}
