package com.segurosbolivar.avaluos.modelo.dto;

import java.io.Serializable;

public class DetalleMasivoDto implements Serializable {

	private static final long serialVersionUID = 6595635075867079222L;
	private Long lineaPlano;
	private String estadoCargue;
	private String contenidoLineaPlano;
	private String mensajeError;
	private String fechaTransaccion;
	private Long numeroReferencia;

	public Long getLineaPlano() {
		return lineaPlano;
	}

	public void setLineaPlano(Long lineaPlano) {
		this.lineaPlano = lineaPlano;
	}

	public String getEstadoCargue() {
		return estadoCargue;
	}

	public void setEstadoCargue(String estadoCargue) {
		this.estadoCargue = estadoCargue;
	}

	public String getContenidoLineaPlano() {
		return contenidoLineaPlano;
	}

	public void setContenidoLineaPlano(String contenidoLineaPlano) {
		this.contenidoLineaPlano = contenidoLineaPlano;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public Long getNumeroReferencia() {
		return numeroReferencia;
	}

	public void setNumeroReferencia(Long numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
	}

	public String getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(String fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

}
