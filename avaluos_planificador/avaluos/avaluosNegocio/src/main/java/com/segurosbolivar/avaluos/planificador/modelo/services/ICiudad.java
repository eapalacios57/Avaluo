package com.segurosbolivar.avaluos.planificador.modelo.services;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.segurosbolivar.avaluos.modelo.entity.Ciudad;

@Local
public interface ICiudad extends Serializable {

	
	public List<Ciudad> getCiudades() throws SQLException;
	
	public Ciudad getCiudad(String strCiudad, String strDepartamento) throws SQLException;
	
	public Ciudad getCiudad(BigDecimal idCiudad) throws SQLException;
	
}
