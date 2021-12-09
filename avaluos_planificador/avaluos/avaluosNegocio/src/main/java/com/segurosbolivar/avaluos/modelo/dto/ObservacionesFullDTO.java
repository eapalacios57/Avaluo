package com.segurosbolivar.avaluos.modelo.dto;

import java.util.Date;

public class ObservacionesFullDTO {
	
	private Long idObservacion;

	private Date fechaCreacion;

	private Date fechaTransaccion;

	private String direccionAnexos;

	private String observacionAvaluo;

	private String otrasDirecciones;

	private String usuarioCreacion;

	private String usuarioTransaccion;

	private Long idAvaluo;
	
	private Long idArchivo;

	public Long getIdObservacion() {
		return idObservacion;
	}

	public void setIdObservacion(Long idObservacion) {
		this.idObservacion = idObservacion;
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

	public String getDireccionAnexos() {
		return direccionAnexos;
	}

	public void setDireccionAnexos(String direccionAnexos) {
		this.direccionAnexos = direccionAnexos;
	}

	public String getObservacionAvaluo() {
		return observacionAvaluo;
	}

	public void setObservacionAvaluo(String observacionAvaluo) {
		this.observacionAvaluo = observacionAvaluo;
	}

	public String getOtrasDirecciones() {
		return otrasDirecciones;
	}

	public void setOtrasDirecciones(String otrasDirecciones) {
		this.otrasDirecciones = otrasDirecciones;
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

	public Long getIdAvaluo() {
		return idAvaluo;
	}

	public void setIdAvaluo(Long idAvaluo) {
		this.idAvaluo = idAvaluo;
	}

	public Long getIdArchivo() {
		return idArchivo;
	}

	public void setIdArchivo(Long idArchivo) {
		this.idArchivo = idArchivo;
	}

}
