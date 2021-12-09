package com.segurosbolivar.avaluos.modelo.dto;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ColorDto {

	private int r;
	private int g;
	private int b;
	private BigDecimal a;

	public ColorDto() {
		super();
	}

	public ColorDto(int r, int g, int b, BigDecimal a) {
		super();
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	@XmlElement(name = "r")
	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	@XmlElement(name = "g")
	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	@XmlElement(name = "b")
	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	@XmlElement(name = "a")
	public BigDecimal getA() {
		return a;
	}

	public void setA(BigDecimal a) {
		this.a = a;
	}

}
