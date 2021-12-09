package com.segurosbolivar.avaluos.modelo.dto;

import java.util.Date;

public class ParametroValorFullDTO {
	
	private Long idParametroValor;
	
	private Long idParametro;

	private String descripcion;

	private String estado;

	private Date fechaCreacion;

	private Date fechaTransaccion;

	private String usuarioCreacion;

	private String usuarioTransaccion;

	public Long getIdParametroValor() {
		return idParametroValor;
	}

	public void setIdParametroValor(Long idParametroValor) {
		this.idParametroValor = idParametroValor;
	}

	public Long getIdParametro() {
		return idParametro;
	}

	public void setIdParametro(Long idParametro) {
		this.idParametro = idParametro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioTransaccion() {
		return usuarioTransaccion;
	}

	public void setUsuarioTransaccion(String usuarioTransaccion) {
		this.usuarioTransaccion = usuarioTransaccion;
	}	


}
