package com.segurosbolivar.avaluos.modelo.service;

import java.util.concurrent.Future;

import javax.ejb.Local;

import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import java.io.Serializable;

@Local
public interface IProcesarAvaluo extends Serializable{
	
	Future<String> procesarLineaCargue(Long referenciaCargue, String nombreArchivo, String linea, Long numeroLinea,
			Long consecutivoBatch, UsuarioDto usuario, boolean esConstructor) ;

}
