package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PARAMETRO_VALOR database table.
 * 
 */
@Embeddable
public class ParametroValorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_PARAMETRO", insertable=false, updatable=false)
	private long idParametro;

	@Column(name="ID_PARAMETRO_VALOR")
	private long idParametroValor;

	public ParametroValorPK() {
	}
	public long getIdParametro() {
		return this.idParametro;
	}
	public void setIdParametro(long idParametro) {
		this.idParametro = idParametro;
	}
	public long getIdParametroValor() {
		return this.idParametroValor;
	}
	public void setIdParametroValor(long idParametroValor) {
		this.idParametroValor = idParametroValor;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ParametroValorPK)) {
			return false;
		}
		ParametroValorPK castOther = (ParametroValorPK)other;
		return 
			(this.idParametro == castOther.idParametro)
			&& (this.idParametroValor == castOther.idParametroValor);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idParametro ^ (this.idParametro >>> 32)));
		hash = hash * prime + ((int) (this.idParametroValor ^ (this.idParametroValor >>> 32)));
		
		return hash;
	}
}