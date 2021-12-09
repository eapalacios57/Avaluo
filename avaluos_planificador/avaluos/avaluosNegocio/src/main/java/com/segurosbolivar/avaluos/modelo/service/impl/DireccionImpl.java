package com.segurosbolivar.avaluos.modelo.service.impl;


import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.segurosbolivar.avaluos.modelo.service.IDirecciones;
import com.segurosbolivar.avaluos.modelo.service.util.BUKEstandarizacionDirsX;



@Stateless
public class DireccionImpl implements IDirecciones {

	private static final long serialVersionUID = 1840519102517000197L;
	@EJB
    private BUKEstandarizacionDirsX estandarizador;

    @Override
    public String transformarDireccion(String direccion, boolean validateComplemento) {
	return estandarizador.estandarizarDireccion(direccion, validateComplemento);
    }
    
    @Override
    public String transformarDireccionComplementaria(String direccion) {
	return estandarizador.estandarizarDireccionComplementaria(direccion);
    }
}
