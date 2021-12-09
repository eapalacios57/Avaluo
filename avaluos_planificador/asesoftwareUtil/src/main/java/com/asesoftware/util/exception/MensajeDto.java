package com.asesoftware.util.exception;

import java.io.Serializable;

public class MensajeDto implements Serializable {
	private static final long serialVersionUID = -7944006262730434596L;

	private String titulo;
	private String descripcion;
	private String detalle;

	public MensajeDto(String descripcion) {
		this.descripcion = descripcion;
	}

	public MensajeDto(String titulo, String descripcion, String detalle) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.detalle = detalle;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

}
