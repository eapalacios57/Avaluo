package com.segurosbolivar.avaluos.motor.dto;

import java.io.Serializable;

public class ObservacionesAvaluoDTO implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String direccionAnexos;
	private String otrasDirecciones;
	private String observacionesAvaluo;
	
	public ObservacionesAvaluoDTO() {
		
	}

	public String getDireccionAnexos() {
		return direccionAnexos;
	}

	public void setDireccionAnexos(String direccionAnexos) {
		this.direccionAnexos = direccionAnexos;
	}

	public String getOtrasDirecciones() {
		return otrasDirecciones;
	}

	public void setOtrasDirecciones(String otrasDirecciones) {
		this.otrasDirecciones = otrasDirecciones;
	}

	public String getObservacionesAvaluo() {
		return observacionesAvaluo;
	}

	public void setObservacionesAvaluo(String observacionesAvaluo) {
		this.observacionesAvaluo = observacionesAvaluo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
