package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;


import com.segurosbolivar.avaluos.modelo.entity.Dominios;
import com.segurosbolivar.avaluos.planificador.data.DominiosDao;
import com.segurosbolivar.avaluos.planificador.modelo.dto.DominiosPlanificadorDTO;
import com.segurosbolivar.avaluos.planificador.modelo.services.IDominios;

/**
 * Implementaci�n del servicio de archivo
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:38 a.m.
 */
@Stateless
@Local(IDominios.class)
public class DominiosImpl implements IDominios {

    private static final long serialVersionUID = -2088678805488718651L;
    
    @EJB
    private DominiosDao dominiosDao;


    @Override
    public List<Dominios> getDominios() throws SQLException {
		List<Dominios> lista = new ArrayList<>();
		lista = dominiosDao.getDominios();
		
		return lista;
	}

    
	@Override
	public void crearDominios(DominiosPlanificadorDTO dominiosDto) {
		dominiosDao.crear(dominiosDto);
		
	}

	@Override
	public List<Dominios> listaDominios() throws SQLException {
		List<Dominios> lista = new ArrayList<>();
		lista = dominiosDao.listarDominios();
		
		return lista;
	}


	@Override
	public Dominios getDominioPorCodigo(String codigoDominio) throws SQLException {
		Dominios dominio = new Dominios();
		
		dominio = dominiosDao.getDominioPorCodigo(codigoDominio);
		
		return dominio;
	}

}