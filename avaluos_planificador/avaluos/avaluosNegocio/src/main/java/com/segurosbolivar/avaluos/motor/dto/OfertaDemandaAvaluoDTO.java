package com.segurosbolivar.avaluos.motor.dto;

import java.io.Serializable;

public class OfertaDemandaAvaluoDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long tiempoComercializacion;
	private String actualidadEdificadora;
	private String comportamiento;
	
	public OfertaDemandaAvaluoDTO() {
		
	}

	public Long getTiempoComercializacion() {
		return tiempoComercializacion;
	}

	public void setTiempoComercializacion(Long tiempoComercializacion) {
		this.tiempoComercializacion = tiempoComercializacion;
	}

	public String getActualidadEdificadora() {
		return actualidadEdificadora;
	}

	public void setActualidadEdificadora(String actualidadEdificadora) {
		this.actualidadEdificadora = actualidadEdificadora;
	}

	public String getComportamiento() {
		return comportamiento;
	}

	public void setComportamiento(String comportamiento) {
		this.comportamiento = comportamiento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
 
	
}
