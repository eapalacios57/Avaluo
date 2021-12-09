package com.segurosbolivar.avaluos.modelo.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * The primary key class for the PGB_MAESTRO_CARGUES database table.
 * 
 */
@Embeddable
public class MaestroCarguePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@NotNull
	@Column(name = "ID_MAESTRO_CARGUE", unique = true, nullable = false, precision = 10)
	private Long idMaestroCargue;

	@Basic(optional = false)
	@NotNull
	@Column(name = "SECUENCIA_ARCHIVO", unique = true, nullable = false, precision = 10)
	private Long secuenciaArchivo;

	public MaestroCarguePK() {
	}

	public MaestroCarguePK(Long idMaestroCargue, Long secuenciaArchivo) {
		this.idMaestroCargue = idMaestroCargue;
		this.secuenciaArchivo = secuenciaArchivo;
	}

	public Long getIdMaestroCargue() {
		return this.idMaestroCargue;
	}

	public void setIdMaestroCargue(Long idMaestroCargue) {
		this.idMaestroCargue = idMaestroCargue;
	}

	public Long getSecuenciaArchivo() {
		return this.secuenciaArchivo;
	}

	public void setSecuenciaArchivo(Long secuenciaArchivo) {
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

		hash = 37 * hash + Objects.hashCode(this.idMaestroCargue);
		hash = 37 * hash + Objects.hashCode(this.secuenciaArchivo);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad MaestroCarguePK que se
	 * pasa como par�metro comprobando que comparten los mismos valores en cada
	 * uno de sus atributos. Solo se tienen en cuenta los atributos simples, es
	 * decir, se omiten aquellos que definen una relaci�n con otra tabla.
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
		final MaestroCarguePK other = (MaestroCarguePK) obj;

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
		cadena.append("idMaestroCargue");
		cadena.append(this.idMaestroCargue);
		cadena.append(", ");

		cadena.append("secuenciaArchivo");
		cadena.append(this.secuenciaArchivo);

		return cadena.toString();
	}

}