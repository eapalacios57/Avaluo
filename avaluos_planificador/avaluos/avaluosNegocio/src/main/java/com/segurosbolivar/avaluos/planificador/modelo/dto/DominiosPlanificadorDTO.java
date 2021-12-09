package com.segurosbolivar.avaluos.planificador.modelo.dto;


import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DominiosPlanificadorDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String rvLowValue;
	
	private String rvMeaning;


	// protected region atributos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region atributos adicionales end

	public DominiosPlanificadorDTO() {
		// protected region procedimientos adicionales de inicializaci�n on
		// begin
		// Escriba en esta secci�n sus modificaciones

		// protected region procedimientos adicionales de inicializaci�n end
	}

	@XmlElement(name="rvLowValue")
	public String getRvLowValue() {
		return this.rvLowValue;
	}

	@XmlElement(name="rvLowValue")
	public void setRvLowValue(String rvLowValue) {
		this.rvLowValue = rvLowValue;
	}

	public String getRvMeaning() {
		return rvMeaning;
	}

	public void setRvMeaning(String rvMeaning) {
		this.rvMeaning = rvMeaning;
	}
	
	

}