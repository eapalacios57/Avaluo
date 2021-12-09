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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the PGB_ARCHIVOS_TMP database table.
 * 
 */
@Entity
@Table(name = "PGB_ARCHIVOS_TMP")
@NamedQueries({@NamedQuery(name = "ArchivosTmp.findAll", query = "SELECT p FROM ArchivosTmp p"),
	           @NamedQuery(name = "ArchivosTmp.obtenerPorNomArchivo", query = "SELECT p FROM ArchivosTmp p WHERE p.nombreArchivo = :nombreArchivo")})
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class ArchivosTmp implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_ARCHIVOS_TMP_PK = "idArchivo";
	public static final String ENTIDAD_PGB_ARCHIVOS_TMP_TIPO_ARCHIVO = "tipoArchivo";
	public static final String ENTIDAD_PGB_ARCHIVOS_TMP_NOMBRE_ARCHIVO = "nombreArchivo";
	public static final String ENTIDAD_PGB_ARCHIVOS_TMP_TAMANIO_ARCHIVO = "tamanioArchivo";
	public static final String ENTIDAD_PGB_ARCHIVOS_TMP_CONTENIDO = "contenido";
	public static final String ENTIDAD_PGB_ARCHIVOS_TMP_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_PGB_ARCHIVOS_TMP_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_PGB_ARCHIVOS_TMP_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_ARCHIVOS_TMP_FECHA_TRANSACCION = "fechaTransaccion";
	public static final String ENTIDAD_PGB_ARCHIVOS_TMP_CONSECUTIVO_BATCH = "consecutivoBatch";
	public static final String ENTIDAD_PGB_ARCHIVOS_TMP_ID_DOCUMENTO = "idDocumento";	
	private static final String[] ATRIBUTOS_ENTIDAD_PGB_ARCHIVOS_TMP = { ENTIDAD_PGB_ARCHIVOS_TMP_CONTENIDO,
			ENTIDAD_PGB_ARCHIVOS_TMP_USUARIO_TRANSACCION, ENTIDAD_PGB_ARCHIVOS_TMP_TIPO_ARCHIVO,
			ENTIDAD_PGB_ARCHIVOS_TMP_TAMANIO_ARCHIVO, ENTIDAD_PGB_ARCHIVOS_TMP_NOMBRE_ARCHIVO,
			ENTIDAD_PGB_ARCHIVOS_TMP_CONSECUTIVO_BATCH, ENTIDAD_PGB_ARCHIVOS_TMP_FECHA_CREACION,
			ENTIDAD_PGB_ARCHIVOS_TMP_FECHA_TRANSACCION, ENTIDAD_PGB_ARCHIVOS_TMP_PK,
			ENTIDAD_PGB_ARCHIVOS_TMP_USUARIO_CREACION, ENTIDAD_PGB_ARCHIVOS_TMP_ID_DOCUMENTO };

	@Id
	@SequenceGenerator(name = "PGB_ARCHIVOS_TMP_IDARCHIVO_GENERATOR", sequenceName = "SEQ_PGB_ARCHIVOS_TMP", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGB_ARCHIVOS_TMP_IDARCHIVO_GENERATOR")
	@Column(name = "ID_ARCHIVO", unique = true, nullable = false, precision = 5)
	private BigDecimal idArchivo;

	@Column(name = "CONSECUTIVO_BATCH", precision = 5)
	private Long consecutivoBatch;

	@Lob()
	@Column(name = "CONTENIDO")
	private byte[] contenido;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CREACION", nullable = false)
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_TRANSACCION", nullable = false)
	private Date fechaTransaccion;

	@Column(name = "NOMBRE_ARCHIVO", nullable = false, length = 40)
	private String nombreArchivo;

	@Column(name = "TAMANIO_ARCHIVO", precision = 22)
	private BigDecimal tamanioArchivo;

	@Column(name = "TIPO_ARCHIVO", precision = 22)
	private BigDecimal tipoArchivo;

	@Column(name = "USUARIO_CREACION", nullable = false, length = 15)
	private String usuarioCreacion;

	@Column(name = "USUARIO_TRANSACCION", nullable = false, length = 15)
	private String usuarioTransaccion;
	
	@Column(name = "ID_DOCUMENTO")
	private String idDocumento;

	// protected region atributos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region atributos adicionales end

	public ArchivosTmp() {
		// protected region procedimientos adicionales de inicializaci�n on
		// begin
		// Escriba en esta secci�n sus modificaciones

		// protected region procedimientos adicionales de inicializaci�n end
	}

	public BigDecimal getIdArchivo() {
		return this.idArchivo;
	}

	public void setIdArchivo(BigDecimal idArchivo) {
		this.idArchivo = idArchivo;
	}

	public Long getConsecutivoBatch() {
		return this.consecutivoBatch;
	}

	public void setConsecutivoBatch(Long consecutivoBatch) {
		this.consecutivoBatch = consecutivoBatch;
	}

	

	public byte[] getContenido() {
		return contenido;
	}

	public void setContenido(byte[] contenido) {
		this.contenido = contenido;
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

	public String getNombreArchivo() {
		return this.nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public BigDecimal getTamanioArchivo() {
		return this.tamanioArchivo;
	}

	public void setTamanioArchivo(BigDecimal tamanioArchivo) {
		this.tamanioArchivo = tamanioArchivo;
	}

	public BigDecimal getTipoArchivo() {
		return this.tipoArchivo;
	}

	public void setTipoArchivo(BigDecimal tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
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

	public String getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
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
		for (final String atr : ATRIBUTOS_ENTIDAD_PGB_ARCHIVOS_TMP) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	public static String[] getAtributosEntidadPgbArchivosTmp() {
		return ATRIBUTOS_ENTIDAD_PGB_ARCHIVOS_TMP;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idArchivo);
		hash = 37 * hash + Objects.hashCode(this.tipoArchivo);
		hash = 37 * hash + Objects.hashCode(this.nombreArchivo);
		hash = 37 * hash + Objects.hashCode(this.tamanioArchivo);
		hash = 37 * hash + Objects.hashCode(this.contenido);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
		hash = 37 * hash + Objects.hashCode(this.consecutivoBatch);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad PgbArchivosTmp que se
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
		final ArchivosTmp other = (ArchivosTmp) obj;

		if (!Objects.equals(this.idArchivo, other.idArchivo)) {
			return false;
		}

		if (!Objects.equals(this.tipoArchivo, other.tipoArchivo)) {
			return false;
		}

		if (!Objects.equals(this.nombreArchivo, other.nombreArchivo)) {
			return false;
		}

		if (!Objects.equals(this.tamanioArchivo, other.tamanioArchivo)) {
			return false;
		}

		if (!Objects.equals(this.contenido, other.contenido)) {
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

		return Objects.equals(this.consecutivoBatch, other.consecutivoBatch);

	}

	// protected region metodos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region metodos adicionales end

	
	@Override
	public ArchivosTmp clone() throws CloneNotSupportedException {
		return (ArchivosTmp) super.clone();
	}

}