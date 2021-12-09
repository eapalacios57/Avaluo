package com.segurosbolivar.avaluos.modelo.service;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;

/**
 * Provee los servicios para la gesti�n sobre los Tronador.
 * 
 * @author crivera
 * @version 1.0
 * @created 24-oct-2017 10:30:44 a.m.
 */
@Local
public interface ITronador extends Serializable{
	
	BigDecimal consultaUvr() throws NegocioException;
	String consultaUvrMotor() throws NegocioException;
	
}