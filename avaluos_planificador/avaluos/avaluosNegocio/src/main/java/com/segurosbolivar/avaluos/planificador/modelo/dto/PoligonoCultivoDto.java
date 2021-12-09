package com.segurosbolivar.avaluos.planificador.modelo.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PoligonoCultivoDto {

	private List<Map<String, BigDecimal>> ubicacionFotos;
	private List<PoligonoDto> poligonos;

	@XmlElement(name = "ubicacionFotos")
	public List<Map<String, BigDecimal>> getUbicacionFotos() {
		return ubicacionFotos;
	}

	public void setUbicacionFotos(List<Map<String, BigDecimal>> ubicacionFotos) {
		this.ubicacionFotos = ubicacionFotos;
	}

	@XmlElement(name = "poligonos")
	public List<PoligonoDto> getPoligonos() {
		return poligonos;
	}

	public void setPoligonos(List<PoligonoDto> poligonos) {
		this.poligonos = poligonos;
	}

}
