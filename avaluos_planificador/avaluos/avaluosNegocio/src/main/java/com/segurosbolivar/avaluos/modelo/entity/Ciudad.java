package com.segurosbolivar.avaluos.modelo.entity;

import static org.eclipse.persistence.annotations.CacheType.SOFT;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the PGB_CIUDADES database table.
 * 
 */
@Entity
@Table(name = "PGB_CIUDADES")
@org.eclipse.persistence.annotations.Cache(type = SOFT, alwaysRefresh = false, refreshOnlyIfNewer = false, expiry = 86400000)

@NamedQueries({ @NamedQuery(name = "Ciudad.findAll", query = "SELECT p FROM Ciudad p"),
		@NamedQuery(name = "Ciudad.buscarPorAsobancaria", query = "SELECT p FROM Ciudad p WHERE p.codAsobancaria = :codAsobancaria") })
public class Ciudad implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_CIUDADES_PK = "idCiudad";
	public static final String ENTIDAD_PGB_CIUDADES_ID_DEPARTAMENTO = "idDepartamento";
	public static final String ENTIDAD_PGB_CIUDADES_CIUDAD = "ciudad";
	public static final String ENTIDAD_PGB_CIUDADES_COD_DANE = "codDane";
	public static final String ENTIDAD_PGB_CIUDADES_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_PGB_CIUDADES_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_PGB_CIUDADES_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_CIUDADES_FECHA_TRANSACCION = "fechaTransaccion";
	public static final String ENTIDAD_PGB_CIUDADES_COD_ASOBANCARIA = "codAsobancaria";
	private static final String[] ATRIBUTOS_ENTIDAD_PGB_CIUDADES = { ENTIDAD_PGB_CIUDADES_USUARIO_CREACION,
			ENTIDAD_PGB_CIUDADES_PK, ENTIDAD_PGB_CIUDADES_COD_DANE, ENTIDAD_PGB_CIUDADES_CIUDAD,
			ENTIDAD_PGB_CIUDADES_FECHA_CREACION, ENTIDAD_PGB_CIUDADES_COD_ASOBANCARIA,
			ENTIDAD_PGB_CIUDADES_ID_DEPARTAMENTO, ENTIDAD_PGB_CIUDADES_USUARIO_TRANSACCION,
			ENTIDAD_PGB_CIUDADES_FECHA_TRANSACCION };

	@Id
	@SequenceGenerator(name = "PGB_CIUDADES_IDCIUDAD_GENERATOR", sequenceName = "SEQ_PGB_CIUDADES", initialValue = 2710,allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGB_CIUDADES_IDCIUDAD_GENERATOR")
	@Column(name = "ID_CIUDAD")
	private Long idCiudad;

	@Column(name = "CIUDAD")
	private String ciudad;

	@Column(name = "COD_ASOBANCARIA")
	private Long codAsobancaria;

	@Column(name = "COD_DANE")
	private Long codDane;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_TRANSACCION")
	private Date fechaTransaccion;

	@Column(name = "USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name = "USUARIO_TRANSACCION")
	private String usuarioTransaccion;

	@Column(name = "ID_DEPARTAMENTO")
	private Long idDepartamento;
	// bi-directional many-to-one association to Departamento
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DEPARTAMENTO", referencedColumnName = "ID_DEPARTAMENTO", insertable = false, updatable = false)
	private Departamento departamento;

	@Column(name = "T_LONGITUD")
	private String longitud;

	@Column(name = "T_LATITUD")
	private String latitud;

	@Column(name = "K_CAPITAL")
	private Long capital;

	// protected region atributos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region atributos adicionales end

	public Ciudad() {
		// protected region procedimientos adicionales de inicializaci�n on
		// begin
		// Escriba en esta secci�n sus modificaciones

		// protected region procedimientos adicionales de inicializaci�n end
	}

	@Override
	public Ciudad clone() throws CloneNotSupportedException {
		return (Ciudad) super.clone();
	}

	public Long getIdCiudad() {
		return this.idCiudad;
	}

	public void setIdCiudad(Long idCiudad) {
		this.idCiudad = idCiudad;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Long getCodAsobancaria() {
		return this.codAsobancaria;
	}

	public void setCodAsobancaria(Long codAsobancaria) {
		this.codAsobancaria = codAsobancaria;
	}

	public Long getCodDane() {
		return this.codDane;
	}

	public void setCodDane(Long codDane) {
		this.codDane = codDane;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
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
		for (final String atr : ATRIBUTOS_ENTIDAD_PGB_CIUDADES) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	public static String[] getAtributosEntidadPgbCiudades() {
		return ATRIBUTOS_ENTIDAD_PGB_CIUDADES;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idCiudad);
		hash = 37 * hash + Objects.hashCode(this.departamento);
		hash = 37 * hash + Objects.hashCode(this.ciudad);
		hash = 37 * hash + Objects.hashCode(this.codDane);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
		hash = 37 * hash + Objects.hashCode(this.codAsobancaria);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad PgbCiudades que se pasa como
	 * par�metro comprobando que comparten los mismos valores en cada uno de sus
	 * atributos. Solo se tienen en cuenta los atributos simples, es decir, se
	 * omiten aquellos que definen una relaci�n con otra tabla.
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
		final Ciudad other = (Ciudad) obj;

		if (!Objects.equals(this.idCiudad, other.idCiudad)) {
			return false;
		}

		if (!Objects.equals(this.departamento, other.departamento)) {
			return false;
		}

		if (!Objects.equals(this.ciudad, other.ciudad)) {
			return false;
		}

		if (!Objects.equals(this.codDane, other.codDane)) {
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

		if (!Objects.equals(this.fechaTransaccion, other.fechaTransaccion)) {
			return false;
		}

		return Objects.equals(this.codAsobancaria, other.codAsobancaria);

	}

	public Long getCapital() {
		return capital;
	}

	public void setCapital(Long capital) {
		this.capital = capital;
	}

	public Long getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region metodos adicionales end

}
