package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ACTIVIDAD_FINANCIERA_SOLICITUD database table.
 * 
 */
@Embeddable
public class ActividadFinancieraSolicitudPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String codigoSolicitud;

	private String codigoActividad;

	public ActividadFinancieraSolicitudPK() {
	}
	public String getCodigoSolicitud() {
		return this.codigoSolicitud;
	}
	public void setCodigoSolicitud(String codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}
	public String getCodigoActividad() {
		return this.codigoActividad;
	}
	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ActividadFinancieraSolicitudPK)) {
			return false;
		}
		ActividadFinancieraSolicitudPK castOther = (ActividadFinancieraSolicitudPK)other;
		return 
			this.codigoSolicitud.equals(castOther.codigoSolicitud)
			&& this.codigoActividad.equals(castOther.codigoActividad);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoSolicitud.hashCode();
		hash = hash * prime + this.codigoActividad.hashCode();
		
		return hash;
	}
}