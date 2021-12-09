package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the VALOR_PORCENTAJE database table.
 * 
 */
@Embeddable
public class ValorPorcentajePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_UNIDAD_PRODUCTIVA", insertable=false, updatable=false)
	private long idUnidadProductiva;

	private String concepto;

	public ValorPorcentajePK() {
	}
	public long getIdUnidadProductiva() {
		return this.idUnidadProductiva;
	}
	public void setIdUnidadProductiva(long idUnidadProductiva) {
		this.idUnidadProductiva = idUnidadProductiva;
	}
	public String getConcepto() {
		return this.concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ValorPorcentajePK)) {
			return false;
		}
		ValorPorcentajePK castOther = (ValorPorcentajePK)other;
		return 
			(this.idUnidadProductiva == castOther.idUnidadProductiva)
			&& this.concepto.equals(castOther.concepto);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idUnidadProductiva ^ (this.idUnidadProductiva >>> 32)));
		hash = hash * prime + this.concepto.hashCode();
		
		return hash;
	}
}