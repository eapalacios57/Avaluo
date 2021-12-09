package com.segurosbolivar.avaluos.motor.dto;

import java.io.Serializable;

public class ResponseDTO implements Serializable {	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoRespuesta;
	private String mensajeRespuesta;
	private AvaluoDTO datosRespuesta;
	
	public ResponseDTO() {}
	
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
	public AvaluoDTO getDatosRespuesta() {
		return datosRespuesta;
	}
	public void setDatosRespuesta(AvaluoDTO datosRespuesta) {
		this.datosRespuesta = datosRespuesta;
	}
	
}
