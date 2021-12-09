package com.segurosbolivar.avaluos.planificador.modelo.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GeometriaDto {

	private String type;
	private Boolean hasM;
	private Boolean hasZ;
	private List<List<List<BigDecimal>>> rings;
	private ReferenciaEspacialDto spatialReference;

	@XmlElement(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlElement(name = "hasM")
	public Boolean getHasM() {
		return hasM;
	}

	public void setHasM(Boolean hasM) {
		this.hasM = hasM;
	}

	@XmlElement(name = "hasZ")
	public Boolean getHasZ() {
		return hasZ;
	}

	public void setHasZ(Boolean hasZ) {
		this.hasZ = hasZ;
	}

	@XmlElement(name = "rings")	
	public List<List<List<BigDecimal>>> getRings() {
		return rings;
	}

	public void setRings(List<List<List<BigDecimal>>> rings) {
		this.rings = rings;
	}

	public ReferenciaEspacialDto getSpatialReference() {
		return spatialReference;
	}

	public void setSpatialReference(ReferenciaEspacialDto spatialReference) {
		this.spatialReference = spatialReference;
	}

}
