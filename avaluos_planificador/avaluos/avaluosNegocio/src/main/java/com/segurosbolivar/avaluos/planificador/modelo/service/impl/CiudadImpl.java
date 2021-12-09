package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import com.segurosbolivar.avaluos.modelo.entity.Ciudad;
import com.segurosbolivar.avaluos.planificador.data.CiudadPlanificadorDao;
import com.segurosbolivar.avaluos.planificador.modelo.services.ICiudad;

@Stateless
@Local(ICiudad.class)
public class CiudadImpl implements ICiudad {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4805885165134078483L;
	@EJB
	CiudadPlanificadorDao ciudadDao; 
	
	@Override
	public List<Ciudad> getCiudades() throws SQLException {
		return ciudadDao.getCiudades();
	}

	@Override
	public Ciudad getCiudad(String strCiudad, String strDepartamento) throws SQLException {
		return ciudadDao.getCiudad(strCiudad, strDepartamento);
	}
	
	@Override
	public Ciudad getCiudad(BigDecimal idCiudad) throws SQLException {
		return ciudadDao.getCiudad(idCiudad);
	}

	
	
}
