package com.segurosbolivar.avaluos.planificador.modelo.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.segurosbolivar.avaluos.modelo.dto.ColorDto;

@XmlRootElement
public class SimboloDto {

	private ColorDto color;
	private LineaDto outline;
	private String style;
	private String type;

	@XmlElement(name = "color")
	public ColorDto getColor() {
		return color;
	}

	public void setColor(ColorDto color) {
		this.color = color;
	}

	@XmlElement(name = "outline")
	public LineaDto getOutline() {
		return outline;
	}

	public void setOutline(LineaDto outline) {
		this.outline = outline;
	}

	@XmlElement(name = "style")
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	@XmlElement(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
