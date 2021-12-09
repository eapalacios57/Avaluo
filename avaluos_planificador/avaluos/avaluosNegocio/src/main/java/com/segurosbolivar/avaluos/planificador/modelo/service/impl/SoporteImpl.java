package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionRolledbackLocalException;
import javax.persistence.Query;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.data.SoporteDao;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Documento;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Soporte;
import com.segurosbolivar.avaluos.planificador.modelo.services.ISoporte;

@Stateless
@Local(ISoporte.class)
public class SoporteImpl implements ISoporte {

    private static final long serialVersionUID = -2088678805488718651L;
    
    @EJB
    private SoporteDao soporteDao;

	
	@Override
	public Soporte crearSoporte(Soporte soporte) throws NegocioException,javax.ejb.TransactionRolledbackLocalException {
		// TODO Auto-generated method stub
		
		soporteDao.crear(soporte);
		
		return soporte;
	}


	@Override
	public void eliminarSoporte(Soporte soporte) throws NegocioException, TransactionRolledbackLocalException {
		// TODO Auto-generated method stub
		soporteDao.eliminar(soporte);
	}


	@Override
	public List<Soporte> consultaSoportesPorUndProd(Long idUnidadProductiva)
			throws NegocioException, TransactionRolledbackLocalException {

		List<Soporte> listaSoportes = soporteDao.consultarSoportePorUndProd(idUnidadProductiva);
		
		return listaSoportes;
	}


	@Override
	public List<Soporte> consultaSoportesPorCultivo(BigDecimal idCultivo)
			throws NegocioException, TransactionRolledbackLocalException {
		List<Soporte> soportes = soporteDao.consultarSoportePorCultivo(idCultivo);
		return soportes;
	}
	


}
