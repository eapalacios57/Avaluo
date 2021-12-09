package com.segurosbolivar.avaluos.planificador.modelo.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PoligonoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private GeometriaDto geometry;
	private SimboloDto symbol;

	@XmlElement(name="geometry")
	public GeometriaDto getGeometry() {
		return geometry;
	}

	public void setGeometry(GeometriaDto geometry) {
		this.geometry = geometry;
	}

	@XmlElement(name="symbol")
	public SimboloDto getSymbol() {
		return symbol;
	}

	public void setSymbol(SimboloDto symbol) {
		this.symbol = symbol;
	}

}
