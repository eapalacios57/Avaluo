package com.segurosbolivar.avaluos.modelo.service;

import java.io.Serializable;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;

/**
 * Expone la funcionalidad para el procesamiento y generaci�n de los archivos de
 * procedatos que se trasmiten a la ASOBANCARIA.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:44 a.m.
 */
@Local
public interface IProcedatoGeneracion extends Serializable {

	String generarTexto(Avaluo avaluos, Long linea) throws NegocioException;

}