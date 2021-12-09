package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SOLICITUD_BENEFICIARIO database table.
 * 
 */
@Embeddable
public class SolicitudBeneficiarioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="TIPO_DOCUMENTO_BENEFICIARIO", insertable=false, updatable=false)
	private String tipoDocumentoBeneficiario;

	@Column(name="NUMERO_DOCUMENTO_BENEFICIARIO", insertable=false, updatable=false)
	private String numeroDocumentoBeneficiario;

	@Column(name="CODIGO_SOLICITUD", insertable=false, updatable=false)
	private String codigoSolicitud;

	public SolicitudBeneficiarioPK() {
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
	public String getCodigoSolicitud() {
		return this.codigoSolicitud;
	}
	public void setCodigoSolicitud(String codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SolicitudBeneficiarioPK)) {
			return false;
		}
		SolicitudBeneficiarioPK castOther = (SolicitudBeneficiarioPK)other;
		return 
			this.tipoDocumentoBeneficiario.equals(castOther.tipoDocumentoBeneficiario)
			&& this.numeroDocumentoBeneficiario.equals(castOther.numeroDocumentoBeneficiario)
			&& this.codigoSolicitud.equals(castOther.codigoSolicitud);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tipoDocumentoBeneficiario.hashCode();
		hash = hash * prime + this.numeroDocumentoBeneficiario.hashCode();
		hash = hash * prime + this.codigoSolicitud.hashCode();
		
		return hash;
	}
}