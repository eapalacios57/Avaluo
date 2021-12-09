package com.segurosbolivar.avaluos.planificador.modelo.dto;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.segurosbolivar.avaluos.modelo.dto.ColorDto;

@XmlRootElement
public class LineaDto {
	
	private ColorDto color;
	private String style;
	private String type;
	private BigDecimal width;

	@XmlElement(name = "color")
	public ColorDto getColor() {
		return color;
	}

	public void setColor(ColorDto color) {
		this.color = color;
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

	@XmlElement(name = "width")
	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

}
