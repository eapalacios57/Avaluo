package com.segurosbolivar.avaluos.modelo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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

import uk.co.jemos.podam.annotations.PodamExclude;

/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name = "PGB_INDICE_ACUMULADO")
@NamedQueries({
		@NamedQuery(name = "IndiceAcumulado.cantidadValores", query = "SELECT COUNT( p.idIndiceAcumulado) FROM IndiceAcumulado p "),
		@NamedQuery(name = "IndiceAcumulado.findAll", query = "SELECT p FROM IndiceAcumulado p order by p.idCiudad, p.anio, p.estrato") })
public class IndiceAcumulado implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_INDICE_ACUMULADO_PK = "idIndiceAcumulado";
	public static final String ENTIDAD_PGB_INDICE_ACUMULADO_ID_DEPARTAMENTO = "idDepartamento";
	public static final String ENTIDAD_PGB_INDICE_ACUMULADO_ID_CIUDAD = "idCiudad";
	public static final String ENTIDAD_PGB_INDICE_ACUMULADO_NUM_VALOR = "numValor";
	public static final String ENTIDAD_PGB_INDICE_ACUMULADO_COD_ESTRATO = "codEstrato";
	public static final String ENTIDAD_PGB_INDICE_ACUMULADO_NUM_ANIO = "numAnio";
	public static final String ENTIDAD_PGB_INDICE_ACUMULADO_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_PGB_INDICE_ACUMULADO_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_PGB_INDICE_ACUMULADO_FECHA_TRANSACCION = "fechaTransaccion";
	public static final String ENTIDAD_PGB_INDICE_ACUMULADO_USUARIO_TRANSACCION = "usuarioTransaccion";
	private static final String[] ATRIBUTOS_ENTIDAD_PGB_INDICE_ACUMULADO = {
			ENTIDAD_PGB_INDICE_ACUMULADO_USUARIO_TRANSACCION, ENTIDAD_PGB_INDICE_ACUMULADO_PK,
			ENTIDAD_PGB_INDICE_ACUMULADO_NUM_ANIO, ENTIDAD_PGB_INDICE_ACUMULADO_USUARIO_CREACION,
			ENTIDAD_PGB_INDICE_ACUMULADO_FECHA_TRANSACCION, ENTIDAD_PGB_INDICE_ACUMULADO_COD_ESTRATO,
			ENTIDAD_PGB_INDICE_ACUMULADO_FECHA_CREACION, ENTIDAD_PGB_INDICE_ACUMULADO_ID_CIUDAD,
			ENTIDAD_PGB_INDICE_ACUMULADO_NUM_VALOR, ENTIDAD_PGB_INDICE_ACUMULADO_ID_DEPARTAMENTO };

	@Id
	@SequenceGenerator(name = "PGB_INDICE_ACUMULADO_GENERATOR", sequenceName = "SEQ_PGB_INDICE_ACUMULADO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGB_INDICE_ACUMULADO_GENERATOR")
	@Column(name = "ID_INDICE_ACUMULADO")
	private Long idIndiceAcumulado;

	@PodamExclude
	@Column(name = "ID_DEPARTAMENTO")
	private Long idDepartamento;

	@PodamExclude
	@Column(name = "ID_CIUDAD")
	private Long idCiudad;

	@Column(name = "NUM_VALOR")
	private BigDecimal valor;

	@Column(name = "COD_ESTRATO")
	private Long estrato;

	@Column(name = "NUM_ANIO")
	private Long anio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CIUDAD", referencedColumnName = "ID_CIUDAD", insertable = false, updatable = false)
	@PodamExclude
	private Ciudad ciudad;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DEPARTAMENTO", referencedColumnName = "ID_DEPARTAMENTO", insertable = false, updatable = false)
	@PodamExclude
	private Departamento departamento;

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

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end

	public IndiceAcumulado() {
		// protected region procedimientos adicionales de inicialización on
		// begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
	}

	@Override
	public IndiceAcumulado clone() throws CloneNotSupportedException {
		return (IndiceAcumulado) super.clone();
	}

	@Override
	public String toString() {
		return " INDICE_ACUMULADO [ciudad=" + idCiudad + "departamento=" + idDepartamento + ", estrato=" + estrato
				+ ", anio=" + anio + ", valor=" + valor + "]";
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {

		this.valor = valor;
	}

	/**
	 * Verifica si la entidad contiene el atributo que se pasa como parámetro
	 *
	 * @param atributo
	 *            Nombre del atributo a validar
	 * @return Verdadero si la entidad contiene al atributo.
	 */
	public static boolean contieneAtributo(String atributo) {

		boolean contiene = false;
		for (final String atr : ATRIBUTOS_ENTIDAD_PGB_INDICE_ACUMULADO) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	public static String[] getAtributosEntidadPgbIndiceAcumulado() {
		return ATRIBUTOS_ENTIDAD_PGB_INDICE_ACUMULADO;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idIndiceAcumulado);
		hash = 37 * hash + Objects.hashCode(this.idDepartamento);
		hash = 37 * hash + Objects.hashCode(this.idCiudad);
		hash = 37 * hash + Objects.hashCode(this.valor);
		hash = 37 * hash + Objects.hashCode(this.estrato);
		hash = 37 * hash + Objects.hashCode(this.anio);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad PgbIndiceAcumulado que se
	 * pasa como parámetro comprobando que comparten los mismos valores en cada uno
	 * de sus atributos. Solo se tienen en cuenta los atributos simples, es decir,
	 * se omiten aquellos que definen una relación con otra tabla.
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
		final IndiceAcumulado other = (IndiceAcumulado) obj;

		if (!Objects.equals(this.idIndiceAcumulado, other.idIndiceAcumulado)) {
			return false;
		}

		if (!Objects.equals(this.idDepartamento, other.idDepartamento)) {
			return false;
		}

		if (!Objects.equals(this.idCiudad, other.idCiudad)) {
			return false;
		}

		if (!Objects.equals(this.valor, other.valor)) {
			return false;
		}

		if (!Objects.equals(this.estrato, other.estrato)) {
			return false;
		}

		if (!Objects.equals(this.anio, other.anio)) {
			return false;
		}

		if (!Objects.equals(this.fechaCreacion, other.fechaCreacion)) {
			return false;
		}

		if (!Objects.equals(this.usuarioCreacion, other.usuarioCreacion)) {
			return false;
		}

		if (!Objects.equals(this.fechaTransaccion, other.fechaTransaccion)) {
			return false;
		}

		return Objects.equals(this.usuarioTransaccion, other.usuarioTransaccion);

	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Long getIdIndiceAcumulado() {
		return idIndiceAcumulado;
	}

	public void setIdIndiceAcumulado(Long idIndiceAcumulado) {
		this.idIndiceAcumulado = idIndiceAcumulado;
	}

	public Long getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public Long getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(Long idCiudad) {
		this.idCiudad = idCiudad;
	}

	public Long getEstrato() {
		return estrato;
	}

	public void setEstrato(Long estrato) {
		this.estrato = estrato;
	}

	public Long getAnio() {
		return anio;
	}

	public void setAnio(Long anio) {
		this.anio = anio;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioTransaccion() {
		return usuarioTransaccion;
	}

	public void setUsuarioTransaccion(String usuarioTransaccion) {
		this.usuarioTransaccion = usuarioTransaccion;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
