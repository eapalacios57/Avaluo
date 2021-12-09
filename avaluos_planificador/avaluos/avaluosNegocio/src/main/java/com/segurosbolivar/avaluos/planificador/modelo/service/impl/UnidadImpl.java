package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.segurosbolivar.avaluos.planificador.data.UnidadDao;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Unidad;
import com.segurosbolivar.avaluos.planificador.modelo.services.IUnidad;

@Stateless
@Local(IUnidad.class)
public class UnidadImpl implements IUnidad {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1973513739272012856L;
	@EJB
	private UnidadDao unidadDao;
	
	@Override
	public List<Unidad> getUnidades() throws SQLException {
		return unidadDao.getUnidades();
	}

	@Override
	public Unidad getUnidad(String strUnidad) throws SQLException {
		return unidadDao.getUnidad(strUnidad);
	}

}
