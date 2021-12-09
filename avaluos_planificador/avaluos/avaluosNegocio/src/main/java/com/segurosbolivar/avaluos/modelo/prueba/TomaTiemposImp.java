package com.segurosbolivar.avaluos.modelo.prueba;

import javax.ejb.Stateless;

@Stateless
public class TomaTiemposImp implements ITomaTiempos{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static long tiempo;

	@Override
	public void setTiempoInicial(long tiempoInicial) {
		tiempo = tiempoInicial;
	}

	@Override
	public long getTomaTiempos() {
		return tiempo;
	}

}
