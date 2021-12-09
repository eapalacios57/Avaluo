package com.segurosbolivar.avaluos.modelo.dto;

import java.util.Date;

public class AvaluoFechaEstadoDTO {
	
	private Date fechaActual;
	private Long estadoAvaluo;
	private Long tipoAvaluo;
	private Long idUltimoAvaluo;
	private Long transmitido;
	
	public Date getFechaActual() {
		return fechaActual;
	}
	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}
	public Long getEstadoAvaluo() {
		return estadoAvaluo;
	}
	public void setEstadoAvaluo(Long estadoAvaluo) {
		this.estadoAvaluo = estadoAvaluo;
	}
	public Long getTipoAvaluo() {
		return tipoAvaluo;
	}
	public void setTipoAvaluo(Long tipoAvaluo) {
		this.tipoAvaluo = tipoAvaluo;
	}
	public Long getIdUltimoAvaluo() {
		return idUltimoAvaluo;
	}
	public void setIdUltimoAvaluo(Long idUltimoAvaluo) {
		this.idUltimoAvaluo = idUltimoAvaluo;
	}
	public Long getTransmitido() {
		return transmitido;
	}
	public void setTransmitido(Long transmitido) {
		this.transmitido = transmitido;
	}

}
