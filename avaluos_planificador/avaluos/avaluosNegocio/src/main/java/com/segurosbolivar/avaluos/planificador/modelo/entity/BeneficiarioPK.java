package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the BENEFICIARIO database table.
 * 
 */
@Embeddable
public class BeneficiarioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="TIPO_DOCUMENTO_BENEFICIARIO")
	private String tipoDocumentoBeneficiario;

	@Column(name="NUMERO_DOCUMENTO_BENEFICIARIO")
	private String numeroDocumentoBeneficiario;		

	public BeneficiarioPK() {
	}
	public String getTipoDocumentoBeneficiario() {
		return this.tipoDocumentoBeneficiario;
	}
	public void setTipoDocumentoBeneficiario(String tipoDocumentoBeneficiario) {
		this.tipoDocumentoBeneficiario = tipoDocumentoBeneficiario;
	}
	public String getNumeroDocumentoBeneficiario() {
		return this.numeroDocumentoBeneficiario;
	}
	public void setNumeroDocumentoBeneficiario(String numeroDocumentoBeneficiario) {
		this.numeroDocumentoBeneficiario = numeroDocumentoBeneficiario;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BeneficiarioPK)) {
			return false;
		}
		BeneficiarioPK castOther = (BeneficiarioPK)other;
		return 
			this.tipoDocumentoBeneficiario.equals(castOther.tipoDocumentoBeneficiario)
			&& this.numeroDocumentoBeneficiario.equals(castOther.numeroDocumentoBeneficiario);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tipoDocumentoBeneficiario.hashCode();
		hash = hash * prime + this.numeroDocumentoBeneficiario.hashCode();
		
		return hash;
	}
}