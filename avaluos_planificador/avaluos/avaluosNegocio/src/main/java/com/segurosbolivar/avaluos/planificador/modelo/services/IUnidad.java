package com.segurosbolivar.avaluos.planificador.modelo.services;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.segurosbolivar.avaluos.planificador.modelo.entity.Unidad;
import java.io.Serializable;

@Local
public interface IUnidad extends Serializable{

	
	public List<Unidad> getUnidades() throws SQLException;
	
	public Unidad getUnidad(String strUnidad) throws SQLException ;
	
}
