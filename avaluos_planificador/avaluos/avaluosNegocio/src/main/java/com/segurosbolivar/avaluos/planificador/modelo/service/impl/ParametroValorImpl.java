package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;


import com.segurosbolivar.avaluos.planificador.data.ParametroValorDao;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ParametroValorDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ParametroValor;
import com.segurosbolivar.avaluos.planificador.modelo.services.IParametroValor;

/**
 * Implementaci�n del servicio de archivo
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:38 a.m.
 */
@Stateless
@Local(IParametroValor.class)
public class ParametroValorImpl implements IParametroValor {

    private static final long serialVersionUID = -2088678805488718651L;
    
    @EJB
    private ParametroValorDao parametroValorDao;


	@Override
	public void crearParametroValor(ParametroValorDTO parametroValor) {
		parametroValorDao.crear(parametroValor);
		
	}

	@Override
	public List<ParametroValor> listaParametroValor() throws SQLException {
		List<ParametroValor> lista = new ArrayList<>();
		lista = parametroValorDao.listarParametrosValor();
		
		return lista;
	}
	
	
  
}