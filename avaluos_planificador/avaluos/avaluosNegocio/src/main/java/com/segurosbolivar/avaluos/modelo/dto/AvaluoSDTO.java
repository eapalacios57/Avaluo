package com.segurosbolivar.avaluos.modelo.dto;

import java.util.Date;

public class AvaluoSDTO {
	
	private Long idAvaluo;
	private Date fechaAvaluo;
	private Long estadoAvaluo;
	private Long tipoAvaluo;
	
	public Date getFechaAvaluo() {
		return fechaAvaluo;
	}
	public void setFechaAvaluo(Date fechaActual) {
		this.fechaAvaluo = fechaActual;
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
	public Long getIdAvaluo() {
		return idAvaluo;
	}
	public void setIdAvaluo(Long idAvaluo) {
		this.idAvaluo = idAvaluo;
	}

}
