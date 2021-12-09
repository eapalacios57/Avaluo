package com.segurosbolivar.avaluos.modelo.entity;

import static org.eclipse.persistence.annotations.CacheType.NONE;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import uk.co.jemos.podam.annotations.PodamExclude;

/**
 * The persistent class for the PGB_ERRORES_CARGUES database table.
 * 
 */
@Entity
@Table(name = "PGB_ERRORES_CARGUES")
@NamedQuery(name = "ErroresCargue.findAll", query = "SELECT p FROM ErroresCargue p")
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class ErroresCargue implements Serializable {
	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_ERRORES_CARGUES_PK_ID_ERROR_CARGUE = "ErroresCarguePK.idErrorCargue";
	public static final String ENTIDAD_PGB_ERRORES_CARGUES_PK_ID_MAESTRO_CARGUE = "ErroresCarguePK.idMaestroCargue";
	public static final String ENTIDAD_PGB_ERRORES_CARGUES_PK_SECUENCIA_ARCHIVO = "ErroresCarguePK.secuenciaArchivo";
	public static final String ENTIDAD_PGB_ERRORES_CARGUES_DESC_ERROR_CARGUE = "descErrorCargue";
	public static final String ENTIDAD_PGB_ERRORES_CARGUES_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_ERRORES_CARGUES_FECHA_TRANSACCION = "fechaTransaccion";

	@EmbeddedId
	private ErroresCarguePK id;

	@Column(name = "DESC_ERROR_CARGUE", nullable = false, length = 250)
	private String descErrorCargue;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_TRANSACCION", nullable = false)
	private Date fechaTransaccion;

	@Column(name = "USUARIO_TRANSACCION", nullable = false, length = 15)
	private String usuarioTransaccion;

	// bi-directional many-to-one association to MaestroCargue
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "ID_MAESTRO_CARGUE", referencedColumnName = "ID_MAESTRO_CARGUE",  insertable = false, updatable = false),
			@JoinColumn(name = "SECUENCIA_ARCHIVO", referencedColumnName = "SECUENCIA_ARCHIVO",  insertable = false, updatable = false) })
	@PodamExclude
	private MaestroCargue maestroCargue;

	// protected region atributos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region atributos adicionales end

	public ErroresCargue() {
		id = new ErroresCarguePK();
		// protected region procedimientos adicionales de inicializaci�n on
		// begin
		// Escriba en esta secci�n sus modificaciones

		// protected region procedimientos adicionales de inicializaci�n end
	}

	public ErroresCarguePK getId() {
		return this.id;
	}

	public void setId(ErroresCarguePK id) {
		this.id = id;
	}

	public String getDescErrorCargue() {
		return this.descErrorCargue;
	}

	public void setDescErrorCargue(String descErrorCargue) {
		this.descErrorCargue = descErrorCargue;
	}

	public Date getFechaTransaccion() {
		return this.fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public String getUsuarioTransaccion() {
		return this.usuarioTransaccion;
	}

	public void setUsuarioTransaccion(String usuarioTransaccion) {
		this.usuarioTransaccion = usuarioTransaccion;
	}

	public MaestroCargue getMaestroCargue() {
		return this.maestroCargue;
	}

	public void setMaestroCargue(MaestroCargue maestroCargue) {
		this.maestroCargue = maestroCargue;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad ErroresCargue que se
	 * pasa como parámetro comprobando que comparten los mismos valores en cada
	 * uno de sus atributos. Solo se tienen en cuenta los atributos simples, es
	 * decir, se omiten aquellos que definen una relación con otra tabla.
	 *
	 * @param obj
	 *            Instancia de la categoría a comprobar iguales.
	 * @return Verdadero si esta instancia y la que se pasan como parámetros son
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ErroresCargue other = (ErroresCargue) obj;

		if (!Objects.equals(this.id, other.id)) {
			return false;
		}

		if (!Objects.equals(this.descErrorCargue, other.descErrorCargue)) {
			return false;
		}

		if (!Objects.equals(this.usuarioTransaccion, other.usuarioTransaccion)) {
			return false;
		}

		return Objects.equals(this.fechaTransaccion, other.fechaTransaccion);

	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}