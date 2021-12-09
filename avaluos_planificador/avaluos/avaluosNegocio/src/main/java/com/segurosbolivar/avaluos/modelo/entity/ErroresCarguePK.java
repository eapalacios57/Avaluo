package com.segurosbolivar.avaluos.modelo.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * The primary key class for the PGB_ERRORES_CARGUES database table.
 * 
 */
@Embeddable
public class ErroresCarguePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "ID_ERROR_CARGUE", unique = true, nullable = false, precision = 10)
	@Basic(optional = false)
	@NotNull
	private long idErrorCargue;

	@Column(name = "ID_MAESTRO_CARGUE", unique = true, nullable = false, precision = 10)
	@Basic(optional = false)
	@NotNull
	private long idMaestroCargue;

	@Column(name = "SECUENCIA_ARCHIVO", unique = true, nullable = false, precision = 10)
	@Basic(optional = false)
	@NotNull
	private long secuenciaArchivo;

	public ErroresCarguePK() {
	}

	public long getIdErrorCargue() {
		return this.idErrorCargue;
	}

	public void setIdErrorCargue(long idErrorCargue) {
		this.idErrorCargue = idErrorCargue;
	}

	public long getIdMaestroCargue() {
		return this.idMaestroCargue;
	}

	public void setIdMaestroCargue(long idMaestroCargue) {
		this.idMaestroCargue = idMaestroCargue;
	}

	public long getSecuenciaArchivo() {
		return this.secuenciaArchivo;
	}

	public void setSecuenciaArchivo(long secuenciaArchivo) {
		this.secuenciaArchivo = secuenciaArchivo;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idErrorCargue);
		hash = 37 * hash + Objects.hashCode(this.idMaestroCargue);
		hash = 37 * hash + Objects.hashCode(this.secuenciaArchivo);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad PgbErroresCarguesPK que
	 * se pasa como par�metro comprobando que comparten los mismos valores en
	 * cada uno de sus atributos. Solo se tienen en cuenta los atributos
	 * simples, es decir, se omiten aquellos que definen una relaci�n con otra
	 * tabla.
	 *
	 * @param obj
	 *            Instancia de la categor�a a comprobar
	 * @return Verdadero si esta instancia y la que se pasan como par�metros son
	 *         iguales.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ErroresCarguePK other = (ErroresCarguePK) obj;

		if (!Objects.equals(this.idErrorCargue, other.idErrorCargue)) {
			return false;
		}

		if (!Objects.equals(this.idMaestroCargue, other.idMaestroCargue)) {
			return false;
		}

		return Objects.equals(this.secuenciaArchivo, other.secuenciaArchivo);

	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder();
		cadena.append("idErrorCargue");
		cadena.append(this.idErrorCargue);
		cadena.append(", ");

		cadena.append("idMaestroCargue");
		cadena.append(this.idMaestroCargue);
		cadena.append(", ");

		cadena.append("secuenciaArchivo");
		cadena.append(this.secuenciaArchivo);

		return cadena.toString();
	}

}
