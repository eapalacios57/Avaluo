package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;


import com.segurosbolivar.avaluos.planificador.data.ActividadFinancieraDao;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadFinancieraDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadFinanciera;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadFinanciera;

/**
 * Implementaci�n del servicio de archivo
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:38 a.m.
 */
@Stateless
@Local(IActividadFinanciera.class)
public class ActividadFinancieraImpl implements IActividadFinanciera {

    private static final long serialVersionUID = -2088678805488718651L;
    
    @EJB
    private ActividadFinancieraDao actividadFinancieraDao;


	@Override
	public void crearActividadFinanciera(ActividadFinancieraDTO actividadFinanciera) {
		actividadFinancieraDao.crear(actividadFinanciera);
		
	}

	@Override
	public List<ActividadFinanciera> listaActividadFinancieras() throws SQLException {
		
		return actividadFinancieraDao.listarActividadFinancieras();
	}

	@Override
	public ActividadFinanciera buscarActividadFinanciera(String codigoActividad) throws Exception {
		
    	return actividadFinancieraDao.buscarActividadFinanciera(codigoActividad);
	}

    

  
}