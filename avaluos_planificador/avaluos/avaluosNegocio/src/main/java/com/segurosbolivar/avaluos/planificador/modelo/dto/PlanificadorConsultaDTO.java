package com.segurosbolivar.avaluos.planificador.modelo.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;




/**
 * The persistent class for the PLANIFICADOR database table.
 * 
 */
@XmlRootElement
public class PlanificadorConsultaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String primerApellido;

	private String primerNombre;

	private String segundoApellido;

	private String segundoNombre;

	public PlanificadorConsultaDTO() {
	}
		
	public PlanificadorConsultaDTO(String primerApellido, String primerNombre, String segundoApellido,
			String segundoNombre) {
		super();
		this.primerApellido = primerApellido;
		this.primerNombre = primerNombre;
		this.segundoApellido = segundoApellido;
		this.segundoNombre = segundoNombre;
	}


	@XmlElement(name="primerApellido")
	public String getPrimerApellido() {
		return this.primerApellido;
	}

	@XmlElement(name="primerApellido")
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	@XmlElement(name="primerNombre")
	public String getPrimerNombre() {
		return this.primerNombre;
	}

	@XmlElement(name="primerNombre")
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	@XmlElement(name="segundoApellido")
	public String getSegundoApellido() {
		return this.segundoApellido;
	}

	@XmlElement(name="segundoApellido")
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	@XmlElement(name="segundoNombre")
	public String getSegundoNombre() {
		return this.segundoNombre;
	}

	@XmlElement(name="segundoNombre")
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}


}