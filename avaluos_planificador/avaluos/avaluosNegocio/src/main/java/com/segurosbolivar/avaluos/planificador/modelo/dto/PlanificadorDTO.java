package com.segurosbolivar.avaluos.planificador.modelo.dto;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.segurosbolivar.avaluos.planificador.modelo.entity.PlanificadorPK;

import java.util.List;


/**
 * The persistent class for the PLANIFICADOR database table.
 * 
 */
@XmlRootElement
public class PlanificadorDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private PlanificadorPK id;

	private String primerApellido;

	private String primerNombre;

	private String segundoApellido;

	private String segundoNombre;
	
	private String dispositivo;
	
	private String tokenDispositivo;

	public PlanificadorDTO() {
	}

	@XmlElement(name="id")
	public PlanificadorPK getId() {
		return this.id;
	}

	public void setId(PlanificadorPK id) {
		this.id = id;
	}

	public String getPrimerApellido() {
		return this.primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getPrimerNombre() {
		return this.primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoApellido() {
		return this.segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getSegundoNombre() {
		return this.segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}

	public String getTokenDispositivo() {
		return tokenDispositivo;
	}

	public void setTokenDispositivo(String tokenDispositivo) {
		this.tokenDispositivo = tokenDispositivo;
	}

	

}