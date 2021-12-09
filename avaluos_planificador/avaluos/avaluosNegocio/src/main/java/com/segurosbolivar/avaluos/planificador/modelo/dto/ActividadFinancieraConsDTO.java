package com.segurosbolivar.avaluos.planificador.modelo.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



/**
 * The persistent class for the ACTIVIDAD_FINANCIERA database table.
 * 
 */
@XmlRootElement
public class ActividadFinancieraConsDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigoActividad;

	private String nombre;

	public ActividadFinancieraConsDTO() {
	}

	@XmlElement(name="codigoActividad")
	public String getCodigoActividad() {
		return this.codigoActividad;
	}

	@XmlElement(name="codigoActividad")
	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
	}

	@XmlElement(name="nombre")
	public String getNombre() {
		return this.nombre;
	}

	@XmlElement(name="nombre")
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}