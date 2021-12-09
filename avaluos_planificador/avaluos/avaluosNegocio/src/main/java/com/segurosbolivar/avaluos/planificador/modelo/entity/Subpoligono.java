package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the SUBPOLIGONO database table.
 * 
 */
@Entity
@NamedQuery(name = "Subpoligono.findAll", query = "SELECT s FROM Subpoligono s")
public class Subpoligono implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SubpoligonoPK id;

	@Column(name = "LINEA")
	private Integer linea;

	@Column(name = "X")
	private BigDecimal x;

	@Column(name = "Y")
	private BigDecimal y;

	// bi-directional many-to-one association to Poligono
	@ManyToOne
	@JoinColumn(name = "ID_POLIGONO")
	private Poligono poligono;

	public Subpoligono() {
	}

	public SubpoligonoPK getId() {
		return this.id;
	}

	public void setId(SubpoligonoPK id) {
		this.id = id;
	}

	public BigDecimal getX() {
		return this.x;
	}

	public void setX(BigDecimal x) {
		this.x = x;
	}

	public BigDecimal getY() {
		return this.y;
	}

	public void setY(BigDecimal y) {
		this.y = y;
	}

	public Poligono getPoligono() {
		return this.poligono;
	}

	public void setPoligono(Poligono poligono) {
		this.poligono = poligono;
	}

	public Integer getLinea() {
		return linea;
	}

	public void setLinea(Integer linea) {
		this.linea = linea;
	}

}