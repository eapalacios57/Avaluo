package com.segurosbolivar.avaluos.modelo.entity;

import static org.eclipse.persistence.annotations.CacheType.SOFT;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.annotations.NamedStoredProcedureQuery;
import org.eclipse.persistence.annotations.StoredProcedureParameter;

import uk.co.jemos.podam.annotations.PodamExclude;

/**
 * The persistent class for the PGB_DEPARTAMENTOS database table.
 * 
 */
@Entity

@NamedStoredProcedureQuery(name = "Departamento.sincronizacionDane", procedureName = "Pkg_General_avaluos.sp_sincroniza_dane", returnsResultSet = false, parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, queryParameter = "pusuario", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.OUT, queryParameter = "pmensaje", type = String.class), })

@Table(name = "PGB_DEPARTAMENTOS")
@NamedQueries({
		@NamedQuery(name = "Departamento.cantidadValores", query = "SELECT COUNT( d.idDepartamento) FROM Departamento d "),
		@NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d order by d.departamento"),
		@NamedQuery(name = "Departamento.buscarPorAsobancaria", query = "SELECT d FROM Departamento d WHERE d.codAsobancaria = :codAsobancaria"),
		@NamedQuery(name = "Departamento.buscarRegion", query = "SELECT d.idRegion FROM Departamento d WHERE d.idDepartamento = :idDepartamento")
		})
@org.eclipse.persistence.annotations.Cache(type = SOFT, alwaysRefresh = false, refreshOnlyIfNewer = false, expiry = 86400000)
public class Departamento implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_DEPARTAMENTOS_PK = "idDepartamento";
	public static final String ENTIDAD_PGB_DEPARTAMENTOS_DEPARTAMENTO = "departamento";
	public static final String ENTIDAD_PGB_DEPARTAMENTOS_COD_DANE = "codDane";
	public static final String ENTIDAD_PGB_DEPARTAMENTOS_COD_DIVPOL = "codDivpol";
	public static final String ENTIDAD_PGB_DEPARTAMENTOS_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_PGB_DEPARTAMENTOS_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_PGB_DEPARTAMENTOS_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_DEPARTAMENTOS_FECHA_TRANSACCION = "fechaTransaccion";
	public static final String ENTIDAD_PGB_DEPARTAMENTOS_COD_ASOBANCARIA = "codAsobancaria";
	private static final String[] ATRIBUTOS_ENTIDAD_PGB_DEPARTAMENTOS = { ENTIDAD_PGB_DEPARTAMENTOS_PK,
			ENTIDAD_PGB_DEPARTAMENTOS_DEPARTAMENTO, ENTIDAD_PGB_DEPARTAMENTOS_FECHA_TRANSACCION,
			ENTIDAD_PGB_DEPARTAMENTOS_COD_ASOBANCARIA, ENTIDAD_PGB_DEPARTAMENTOS_COD_DIVPOL,
			ENTIDAD_PGB_DEPARTAMENTOS_USUARIO_CREACION, ENTIDAD_PGB_DEPARTAMENTOS_FECHA_CREACION,
			ENTIDAD_PGB_DEPARTAMENTOS_USUARIO_TRANSACCION, ENTIDAD_PGB_DEPARTAMENTOS_COD_DANE };

	@Id
	@SequenceGenerator(name = "PGB_DEPARTAMENTOS_IDDEPARTAMENTO_GENERATOR", sequenceName = "SEQ_PGB_DEPARTAMENTOS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGB_DEPARTAMENTOS_IDDEPARTAMENTO_GENERATOR")
	@Column(name = "ID_DEPARTAMENTO", unique = true, nullable = false, precision = 22)
	private Long idDepartamento;

	@Column(name = "COD_DANE", precision = 22)
	private Long codDane;

	@Column(name = "COD_DIVPOL", precision = 22)
	private Long codDivpol;

	@Column(name = "DEPARTAMENTO", nullable = false, length = 60)
	private String departamento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CREACION", nullable = false)
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_TRANSACCION", nullable = false)
	private Date fechaTransaccion;

	@Column(name = "USUARIO_CREACION", nullable = false, length = 15)
	private String usuarioCreacion;

	@Column(name = "USUARIO_TRANSACCION", nullable = false, length = 15)
	private String usuarioTransaccion;

	@Column(name = "COD_ASOBANCARIA", precision = 22)
	private Long codAsobancaria;
	
	@OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DEPARTAMENTO", referencedColumnName = "ID_DEPARTAMENTO", insertable = false, updatable = false)
	private List<Ciudad> ciudadesDepto;

	@OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)
	@PodamExclude
	private List<IndiceAcumulado> indices;

	@Column(name = "T_LONGITUD")
	private String longitud;

	@Column(name = "T_LATITUD")
	private String latitud;

	// protected region atributos adicionales on begin
	// Escriba en esta secci�n sus modificaciones
	@Column(name = "ID_REGION")
	private Long idRegion;
	// protected region atributos adicionales end

	public List<IndiceAcumulado> getIndices() {
		return indices;
	}

	public void setIndices(List<IndiceAcumulado> indices) {
		this.indices = indices;
	}

	public Departamento() {
		// protected region procedimientos adicionales de inicializaci�n on
		// begin
		// Escriba en esta secci�n sus modificaciones

		// protected region procedimientos adicionales de inicializaci�n end
	}

	public Departamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	@Override
	public Departamento clone() throws CloneNotSupportedException {
			return (Departamento) super.clone();
	}

	public Long getIdDepartamento() {
		return this.idDepartamento;
	}

	public void setIdDepartamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public Long getCodDane() {
		return this.codDane;
	}

	public void setCodDane(Long codDane) {
		this.codDane = codDane;
	}

	public Long getCodDivpol() {
		return this.codDivpol;
	}

	public void setCodDivpol(Long codDivpol) {
		this.codDivpol = codDivpol;
	}

	public String getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
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

	public List<Ciudad> getCiudadesDepto() {
		return this.ciudadesDepto;
	}

	public void setCiudadesDepto(List<Ciudad> ciudadesDepto) {
		this.ciudadesDepto = ciudadesDepto;
	}

	public Long getCodAsobancaria() {
		return codAsobancaria;
	}

	public void setCodAsobancaria(Long codAsobancaria) {
		this.codAsobancaria = codAsobancaria;
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

	public Long getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(Long idRegion) {
		this.idRegion = idRegion;
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
		for (final String atr : ATRIBUTOS_ENTIDAD_PGB_DEPARTAMENTOS) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	public static String[] getAtributosEntidadPgbDepartamentos() {
		return ATRIBUTOS_ENTIDAD_PGB_DEPARTAMENTOS;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idDepartamento);
		hash = 37 * hash + Objects.hashCode(this.departamento);
		hash = 37 * hash + Objects.hashCode(this.codDane);
		hash = 37 * hash + Objects.hashCode(this.codDivpol);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
		hash = 37 * hash + Objects.hashCode(this.codAsobancaria);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad PgbDepartamentos que se
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
		final Departamento other = (Departamento) obj;

		if (!Objects.equals(this.idDepartamento, other.idDepartamento)) {
			return false;
		}

		if (!Objects.equals(this.departamento, other.departamento)) {
			return false;
		}

		if (!Objects.equals(this.codDane, other.codDane)) {
			return false;
		}

		if (!Objects.equals(this.codDivpol, other.codDivpol)) {
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

	// protected region metodos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region metodos adicionales end

}