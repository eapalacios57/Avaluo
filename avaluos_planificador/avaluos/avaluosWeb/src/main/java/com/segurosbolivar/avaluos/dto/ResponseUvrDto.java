package com.segurosbolivar.avaluos.dto;

import java.io.Serializable;

public class ResponseUvrDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoRespuesta;
	private String valorUvrDia;
	private String mensajeRespuesta;
	
	
	public ResponseUvrDto() {
	}


	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}


	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}


	public String getValorUvrDia() {
		return valorUvrDia;
	}


	public void setValorUvrDia(String valorUvrDia) {
		this.valorUvrDia = valorUvrDia;
	}


	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}


	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}
	
	
	
	
}
