package com.segurosbolivar.avaluos.planificador.modelo.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CrearPoligonosDto {

	private BigDecimal idCultivo;
	private List<PoligonoDto> poligonos;

	@XmlElement(name = "idCultivo")
	public BigDecimal getIdCultivo() {
		return idCultivo;
	}

	public void setIdCultivo(BigDecimal idCultivo) {
		this.idCultivo = idCultivo;
	}

	@XmlElement(name = "poligonos")
	public List<PoligonoDto> getPoligonos() {
		return poligonos;
	}

	public void setPoligonos(List<PoligonoDto> poligonos) {
		this.poligonos = poligonos;
	}

}
