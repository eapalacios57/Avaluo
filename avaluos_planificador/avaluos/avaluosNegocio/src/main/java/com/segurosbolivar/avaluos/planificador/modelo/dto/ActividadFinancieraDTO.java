package com.segurosbolivar.avaluos.planificador.modelo.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;


/**
 * The persistent class for the ACTIVIDAD_FINANCIERA database table.
 * 
 */
@XmlRootElement
public class ActividadFinancieraDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigoActividad;

	private Date fechaCreacion;

	private Date fechaTransaccion;

	private String nombre;

	private String usuarioCreacion;

	private String usuarioTransaccion;

	private String vigencia;

	public ActividadFinancieraDTO() {
	}

	@XmlElement(name="codigoActividad")
	public String getCodigoActividad() {
		return this.codigoActividad;
	}

	@XmlElement(name="codigoActividad")
	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
	}

	@XmlElement(name="fechaCreacion")
	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	@XmlElement(name="fechaCreacion")
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@XmlElement(name="fechaTransaccion")
	public Date getFechaTransaccion() {
		return this.fechaTransaccion;
	}

	@XmlElement(name="fechaTransaccion")
	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	@XmlElement(name="nombre")
	public String getNombre() {
		return this.nombre;
	}

	@XmlElement(name="nombre")
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@XmlElement(name="usuarioCreacion")
	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	@XmlElement(name="usuarioCreacion")
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	@XmlElement(name="usuarioTransaccion")
	public String getUsuarioTransaccion() {
		return this.usuarioTransaccion;
	}

	@XmlElement(name="usuarioTransaccion")
	public void setUsuarioTransaccion(String usuarioTransaccion) {
		this.usuarioTransaccion = usuarioTransaccion;
	}

	@XmlElement(name="vigencia")
	public String getVigencia() {
		return this.vigencia;
	}

	@XmlElement(name="vigencia")
	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}

}