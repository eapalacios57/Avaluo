package com.segurosbolivar.avaluos.planificador.servicios;

import java.io.Serializable;

public class RespuestaRest implements Serializable {	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoRespuesta;
	private String mensajeRespuesta;
	private Object datosRespuesta;
	
	
	
	public RespuestaRest(String codigoRespuesta, String mensajeRespuesta, Object datosRespuesta) {
		super();
		this.codigoRespuesta = codigoRespuesta;
		this.mensajeRespuesta = mensajeRespuesta;
		this.datosRespuesta = datosRespuesta;
	}
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}
	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}
	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}
	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}
	public Object getDatosRespuesta() {
		return datosRespuesta;
	}
	public void setDatosRespuesta(Object datosRespuesta) {
		this.datosRespuesta = datosRespuesta;
	}
	
	
	
	
}
