package com.segurosbolivar.avaluos.modelo.service;

import java.io.Serializable;

import javax.ejb.Local;

@Local
public interface IDirecciones extends Serializable{
    
    String transformarDireccion(String direccion, boolean validateComplemento);

    String transformarDireccionComplementaria(String direccion);

}
