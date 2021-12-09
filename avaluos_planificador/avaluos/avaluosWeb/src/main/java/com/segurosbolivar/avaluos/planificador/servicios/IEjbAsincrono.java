package com.segurosbolivar.avaluos.planificador.servicios;

import java.io.Serializable;

import javax.ejb.Local;

import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion.SincronizarModel;

@Local
public interface IEjbAsincrono extends Serializable {

	void RespuestaAsincrona(SincronizarModel modelo);

	

}