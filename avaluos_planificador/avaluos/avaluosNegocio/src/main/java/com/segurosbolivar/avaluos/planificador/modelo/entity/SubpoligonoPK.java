package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 * The primary key class for the SUBPOLIGONO database table.
 * 
 */
@Embeddable
public class SubpoligonoPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "ID_POLIGONO", insertable = false, updatable = false)
	private BigDecimal idPoligono;

	@Column(name = "ID_SUBPOLIGONO")
	private BigDecimal idSubpoligono;

	public SubpoligonoPK() {
	}

	public BigDecimal getIdPoligono() {
		return this.idPoligono;
	}

	public void setIdPoligono(BigDecimal idPoligono) {
		this.idPoligono = idPoligono;
	}

	public BigDecimal getIdSubpoligono() {
		return this.idSubpoligono;
	}

	public void setIdSubpoligono(BigDecimal idSubpoligono) {
		this.idSubpoligono = idSubpoligono;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubpoligonoPK)) {
			return false;
		}
		SubpoligonoPK castOther = (SubpoligonoPK)other;
		return 
			this.idPoligono.equals(castOther.idPoligono)
			&& this.idSubpoligono.equals(castOther.idSubpoligono);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPoligono.hashCode();
		hash = hash * prime + this.idSubpoligono.hashCode();
		
		return hash;
	}
}