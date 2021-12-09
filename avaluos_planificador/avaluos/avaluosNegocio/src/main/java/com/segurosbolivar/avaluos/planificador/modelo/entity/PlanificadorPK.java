package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PLANIFICADOR database table.
 * 
 */
@Embeddable
public class PlanificadorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="TIPO_DOCUMENTO_PLANIFICADOR")
	private String tipoDocumentoPlanificador;

	@Column(name="NUMERO_DOCUMENTO_PLANIFICADOR")
	private String numeroDocumentoPlanificador;

	public PlanificadorPK() {
	}
	public String getTipoDocumentoPlanificador() {
		return this.tipoDocumentoPlanificador;
	}
	public void setTipoDocumentoPlanificador(String tipoDocumentoPlanificador) {
		this.tipoDocumentoPlanificador = tipoDocumentoPlanificador;
	}
	public String getNumeroDocumentoPlanificador() {
		return this.numeroDocumentoPlanificador;
	}
	public void setNumeroDocumentoPlanificador(String numeroDocumentoPlanificador) {
		this.numeroDocumentoPlanificador = numeroDocumentoPlanificador;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PlanificadorPK)) {
			return false;
		}
		PlanificadorPK castOther = (PlanificadorPK)other;
		return 
			this.tipoDocumentoPlanificador.equals(castOther.tipoDocumentoPlanificador)
			&& this.numeroDocumentoPlanificador.equals(castOther.numeroDocumentoPlanificador);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tipoDocumentoPlanificador.hashCode();
		hash = hash * prime + this.numeroDocumentoPlanificador.hashCode();
		
		return hash;
	}
}