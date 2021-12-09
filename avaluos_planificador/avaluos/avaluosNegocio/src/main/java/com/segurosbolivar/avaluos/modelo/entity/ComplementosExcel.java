package com.segurosbolivar.avaluos.modelo.entity;

import static org.eclipse.persistence.annotations.CacheType.NONE;

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
 * The persistent class for the PGB_COMPLEMENTOS_EXCEL database table.
 * 
 */
@Entity
@Table(name = "PGB_COMPLEMENTOS_EXCEL")
@NamedQueries({ @NamedQuery(name = "getComplementos", query = "select c from ComplementosExcel c"),
		@NamedQuery(name = "ComplementosExcel.findAll", query = "SELECT p FROM ComplementosExcel p") })
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class ComplementosExcel implements Serializable {
	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_COMPLEMENTOS_EXCEL_PK = "idComplementoExcel";
	public static final String ENTIDAD_PGB_COMPLEMENTOS_EXCEL_ID_ARCHIVO = "idArchivo";
	public static final String ENTIDAD_PGB_COMPLEMENTOS_EXCEL_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_PGB_COMPLEMENTOS_EXCEL_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_PGB_COMPLEMENTOS_EXCEL_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_COMPLEMENTOS_EXCEL_FECHA_TRANSACCION = "fechaTransaccion";
	public static final String ENTIDAD_PGB_COMPLEMENTOS_EXCEL_ORDEN_DESCARGA = "ordenDescarga";
	public static final String ENTIDAD_PGB_COMPLEMENTOS_EXCEL_DESCRIPCION_COMPLEMENTO = "descripcionComplemento";
	public static final String ENTIDAD_PGB_COMPLEMENTOS_EXCEL_TEXTO_LIBRE = "textoLibre";
	private static final String[] ATRIBUTOS_ENTIDAD_PGB_COMPLEMENTOS_EXCEL = { ENTIDAD_PGB_COMPLEMENTOS_EXCEL_PK,
			ENTIDAD_PGB_COMPLEMENTOS_EXCEL_USUARIO_TRANSACCION, ENTIDAD_PGB_COMPLEMENTOS_EXCEL_FECHA_TRANSACCION,
			ENTIDAD_PGB_COMPLEMENTOS_EXCEL_DESCRIPCION_COMPLEMENTO, ENTIDAD_PGB_COMPLEMENTOS_EXCEL_ID_ARCHIVO,
			ENTIDAD_PGB_COMPLEMENTOS_EXCEL_USUARIO_CREACION, ENTIDAD_PGB_COMPLEMENTOS_EXCEL_FECHA_CREACION,
			ENTIDAD_PGB_COMPLEMENTOS_EXCEL_ORDEN_DESCARGA, ENTIDAD_PGB_COMPLEMENTOS_EXCEL_TEXTO_LIBRE };

	@Id
	@SequenceGenerator(name = "PGB_COMPLEMENTOS_EXCEL_IDCOMPLEMENTOEXCEL_GENERATOR", sequenceName = "SEQ_PGB_COMPLEMENTOS_EXCEL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGB_COMPLEMENTOS_EXCEL_IDCOMPLEMENTOEXCEL_GENERATOR")
	@Column(name = "ID_COMPLEMENTO_EXCEL")
	private Long idComplementoExcel;

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

	@Column(name = "DESCRIPCION_COMPLEMENTO")
	private String descripcionComplemento;

	@Column(name = "ID_ARCHIVO")
	private Long  idArchivo;

	@Column(name = "ORDEN_DESCARGA")
	private String ordenDescarga;

	@Column(name = "TEXTO_LIBRE")
	private String textoLibre;

	// uni-directional many-to-one association to Archivo
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ARCHIVO", referencedColumnName = "ID_ARCHIVO", insertable = false, updatable = false)
	private Archivo archivo;

	// protected region atributos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region atributos adicionales end

	public ComplementosExcel() {
		// protected region procedimientos adicionales de inicializaci�n on
		// begin
		// Escriba en esta secci�n sus modificaciones

		// protected region procedimientos adicionales de inicializaci�n end
	}

	public Long getIdComplementoExcel() {
		return this.idComplementoExcel;
	}

	public void setIdComplementoExcel(Long idComplementoExcel) {
		this.idComplementoExcel = idComplementoExcel;
	}

	public Long getIdArchivo() {
		return idArchivo;
	}

	public void setIdArchivo(Long idArchivo) {
		this.idArchivo = idArchivo;
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

	public Archivo getArchivo() {
		return this.archivo;
	}

	public void setArchivo(Archivo archivo) {
		this.archivo = archivo;
	}

	public String getDescripcionComplemento() {
		return descripcionComplemento;
	}

	public void setDescripcionComplemento(String descripcionComplemento) {
		this.descripcionComplemento = descripcionComplemento;
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
		for (final String atr : ATRIBUTOS_ENTIDAD_PGB_COMPLEMENTOS_EXCEL) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	public static String[] getAtributosEntidadPgbComplementosExcel() {
		return ATRIBUTOS_ENTIDAD_PGB_COMPLEMENTOS_EXCEL;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idComplementoExcel);
		hash = 37 * hash + Objects.hashCode(this.idArchivo);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
		hash = 37 * hash + Objects.hashCode(this.ordenDescarga);
		hash = 37 * hash + Objects.hashCode(this.descripcionComplemento);
		hash = 37 * hash + Objects.hashCode(this.textoLibre);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad PgbComplementosExcel que
	 * se pasa como par�metro comprobando que comparten los mismos valores en
	 * cada uno de sus atributos. Solo se tienen en cuenta los atributos
	 * simples, es decir, se omiten aquellos que definen una relaci�n con otra
	 * tabla.
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
		final ComplementosExcel other = (ComplementosExcel) obj;

		if (!Objects.equals(this.idComplementoExcel, other.idComplementoExcel)) {
			return false;
		}

		if (!Objects.equals(this.idArchivo, other.idArchivo)) {
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

		if (!Objects.equals(this.ordenDescarga, other.ordenDescarga)) {
			return false;
		}

		if (!Objects.equals(this.descripcionComplemento, other.descripcionComplemento)) {
			return false;
		}

		return Objects.equals(this.textoLibre, other.textoLibre);

	}

	public String getTextoLibre() {
		return textoLibre;
	}

	public void setTextoLibre(String textoLibre) {
		this.textoLibre = textoLibre;
	}

	public String getOrdenDescarga() {
		return ordenDescarga;
	}

	public void setOrdenDescarga(String ordenDescarga) {
		this.ordenDescarga = ordenDescarga;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region metodos adicionales end

}