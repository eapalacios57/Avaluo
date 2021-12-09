package com.segurosbolivar.avaluos.modelo.entity;

import static org.eclipse.persistence.annotations.CacheType.NONE;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.annotations.NamedStoredProcedureQuery;
import org.eclipse.persistence.annotations.StoredProcedureParameter;

/**
 * The persistent class for the PGB_CALENDARIOS database table.
 * 
 */

@Entity
@NamedStoredProcedureQuery(name = "ejecutaCalendario", procedureName = "Pkg_General_avaluos.sp_carga_sabado_domingo", returnsResultSet = false, parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, queryParameter = "panio", type = BigDecimal.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, queryParameter = "pusuario", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.OUT, queryParameter = "pmensaje", type = String.class), })

@Table(name = "PGB_CALENDARIOS")

@NamedQueries({
		@NamedQuery(name = "Calendario.anios", query = "select distinct c.anio from Calendario c order by c.anio"),
		@NamedQuery(name = "getFechas", query = "select c from Calendario c"),
		@NamedQuery(name = "queryPorAnio", query = "SELECT c from Calendario c where c.fechaNoHabil between :fechainicio and :fechafin"),
		@NamedQuery(name = "Calendario.findAll", query = "SELECT p FROM Calendario p") })
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class Calendario implements Serializable {
	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_CALENDARIOS_PK = "idCalendario";
	public static final String ENTIDAD_PGB_CALENDARIOS_ANIO = "anio";
	public static final String ENTIDAD_PGB_CALENDARIOS_FECHA_NO_HABIL = "fechaNoHabil";
	public static final String ENTIDAD_PGB_CALENDARIOS_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_PGB_CALENDARIOS_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_PGB_CALENDARIOS_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_CALENDARIOS_FECHA_TRANSACCION = "fechaTransaccion";
	private static final String[] ATRIBUTOS_ENTIDAD_PGB_CALENDARIOS = { ENTIDAD_PGB_CALENDARIOS_ANIO,
			ENTIDAD_PGB_CALENDARIOS_USUARIO_CREACION, ENTIDAD_PGB_CALENDARIOS_PK,
			ENTIDAD_PGB_CALENDARIOS_FECHA_TRANSACCION, ENTIDAD_PGB_CALENDARIOS_FECHA_NO_HABIL,
			ENTIDAD_PGB_CALENDARIOS_FECHA_CREACION, ENTIDAD_PGB_CALENDARIOS_USUARIO_TRANSACCION };

	@Id
	@SequenceGenerator(name = "PGB_CALENDARIOS_IDCALENDARIO_GENERATOR", sequenceName = "SEQ_PGB_CALENDARIOS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGB_CALENDARIOS_IDCALENDARIO_GENERATOR")
	@Column(name = "ID_CALENDARIO", unique = true, nullable = false, precision = 22)
	private BigDecimal idCalendario;

	@Column(name = "ANIO", precision = 22)
	private Long anio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CREACION", nullable = false)
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_NO_HABIL", nullable = false)
	private Date fechaNoHabil;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_TRANSACCION", nullable = false)
	private Date fechaTransaccion;

	@Column(name = "USUARIO_CREACION", nullable = false, length = 15)
	private String usuarioCreacion;

	@Column(name = "USUARIO_TRANSACCION", nullable = false, length = 15)
	private String usuarioTransaccion;

	// protected region atributos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region atributos adicionales end

	public Calendario() {
		// protected region procedimientos adicionales de inicializaci�n on
		// begin
		// Escriba en esta secci�n sus modificaciones

		// protected region procedimientos adicionales de inicializaci�n end
	}

	public BigDecimal getIdCalendario() {
		return this.idCalendario;
	}

	public void setIdCalendario(BigDecimal idCalendario) {
		this.idCalendario = idCalendario;
	}

	public Long getAnio() {
		return anio;
	}

	public void setAnio(Long anio) {
		this.anio = anio;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaNoHabil() {
		return this.fechaNoHabil;
	}

	public void setFechaNoHabil(Date fechaNoHabil) {
		this.fechaNoHabil = fechaNoHabil;
	}

	public Date getFechaTransaccion() {
		return this.fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioTransaccion() {
		return this.usuarioTransaccion;
	}

	public void setUsuarioTransaccion(String usuarioTransaccion) {
		this.usuarioTransaccion = usuarioTransaccion;
	}

	/**
	 * Verifica si la entidad contiene el atributo que se pasa como par�metro
	 *
	 * @param atributo
	 *            Nombre del atributo a validar
	 * @return Verdadero si la entidad contiene al atributo.
	 */
	public static boolean contieneAtributo(String atributo) {

		boolean contiene = false;
		for (final String atr : ATRIBUTOS_ENTIDAD_PGB_CALENDARIOS) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	public static String[] getAtributosEntidadPgbCalendarios() {
		return ATRIBUTOS_ENTIDAD_PGB_CALENDARIOS;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idCalendario);
		hash = 37 * hash + Objects.hashCode(this.anio);
		hash = 37 * hash + Objects.hashCode(this.fechaNoHabil);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad PgbCalendarios que se
	 * pasa como par�metro comprobando que comparten los mismos valores en cada
	 * uno de sus atributos. Solo se tienen en cuenta los atributos simples, es
	 * decir, se omiten aquellos que definen una relaci�n con otra tabla.
	 *
	 * @param obj
	 *            Instancia de la categor�a a comprobar iguales.
	 * @return Verdadero si esta instancia y la que se pasan como par�metros son
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Calendario other = (Calendario) obj;

		if (!Objects.equals(this.idCalendario, other.idCalendario)) {
			return false;
		}

		if (!Objects.equals(this.anio, other.anio)) {
			return false;
		}

		if (!Objects.equals(this.fechaNoHabil, other.fechaNoHabil)) {
			return false;
		}

		if (!Objects.equals(this.usuarioCreacion, other.usuarioCreacion)) {
			return false;
		}

		if (!Objects.equals(this.fechaCreacion, other.fechaCreacion)) {
			return false;
		}

		if (!Objects.equals(this.usuarioTransaccion, other.usuarioTransaccion)) {
			return false;
		}

		return Objects.equals(this.fechaTransaccion, other.fechaTransaccion);

	}

	// protected region metodos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region metodos adicionales end

}