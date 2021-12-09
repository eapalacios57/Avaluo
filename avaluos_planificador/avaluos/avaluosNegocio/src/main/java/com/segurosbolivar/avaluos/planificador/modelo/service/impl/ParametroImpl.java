package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;


import com.segurosbolivar.avaluos.planificador.data.ParametroDao;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ParametroDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Parametro;
import com.segurosbolivar.avaluos.planificador.modelo.services.IParametro;

/**
 * Implementaci�n del servicio de archivo
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:38 a.m.
 */
@Stateless
@Local(IParametro.class)
public class ParametroImpl implements IParametro {

    private static final long serialVersionUID = -2088678805488718651L;
    
    @EJB
    private ParametroDao parametroDao;


	@Override
	public void crearParametro(ParametroDTO parametro) {
		parametroDao.crear(parametro);
		
	}

	@Override
	public List<Parametro> listaParametro() throws SQLException {
		List<Parametro> lista = new ArrayList<>();
		lista = parametroDao.listarParametros();
		
		return lista;
	}
  
}