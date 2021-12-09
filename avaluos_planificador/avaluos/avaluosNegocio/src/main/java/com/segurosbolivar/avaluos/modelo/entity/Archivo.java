package com.segurosbolivar.avaluos.modelo.entity;

import static org.eclipse.persistence.annotations.CacheType.NONE;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.inject.Named;
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
import javax.persistence.Transient;

/**
 * The persistent class for the ARCHIVOS database table.
 * 
 */
@Entity
@Table(name = "ARCHIVOS")
@NamedQueries({
		@NamedQuery(name = "getContenidoArchivoId", query = "select a.contenidoArchivo from Archivo a where a.idArchivo = :id"),
		@NamedQuery(name = "Archivo.findAll", query = "SELECT p FROM Archivo p"),
		@NamedQuery(name = "Archivo.getDocumnentoId", query = "SELECT p FROM Archivo p WHERE p.idDocumento = :idFilenet"),
		@NamedQuery(name = "Archivo.getArchivoId", query = "SELECT p FROM Archivo p WHERE p.idArchivo = :idArchivo")})
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class Archivo implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_ARCHIVOS_PK = "idArchivo";
	public static final String ENTIDAD_ARCHIVOS_TIPO_ARCHIVO = "tipoArchivo";
	public static final String ENTIDAD_ARCHIVOS_NOMBRE_ARCHIVO = "nombreArchivo";
	public static final String ENTIDAD_ARCHIVOS_TAMANIO_ARCHIVO = "tamanioArchivo";
	public static final String ENTIDAD_ARCHIVOS_CONTENIDO = "contenidoArchivo";
	public static final String ENTIDAD_ARCHIVOS_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_ARCHIVOS_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_ARCHIVOS_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_ARCHIVOS_FECHA_TRANSACCION = "fechaTransaccion";
	public static final String ENTIDAD_ARCHIVOS_ID_DOCUEMNTO = "idDocumento";
	private static final String[] ATRIBUTOS_ENTIDAD_ARCHIVOS = { ENTIDAD_ARCHIVOS_TIPO_ARCHIVO,
			ENTIDAD_ARCHIVOS_TAMANIO_ARCHIVO, ENTIDAD_ARCHIVOS_FECHA_TRANSACCION, ENTIDAD_ARCHIVOS_USUARIO_TRANSACCION,
			ENTIDAD_ARCHIVOS_FECHA_CREACION, ENTIDAD_ARCHIVOS_PK, ENTIDAD_ARCHIVOS_USUARIO_CREACION,
			ENTIDAD_ARCHIVOS_NOMBRE_ARCHIVO, ENTIDAD_ARCHIVOS_CONTENIDO };

	@Id
	@SequenceGenerator(name = "ARCHIVOS_IDARCHIVO_GENERATOR", sequenceName = "SEQ_ARCHIVOS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARCHIVOS_IDARCHIVO_GENERATOR")
	@Column(name = "ID_ARCHIVO")
	private Long idArchivo;

	@Lob()
	@Column(name = "CONTENIDO", length = 100000)
	private byte[] contenidoArchivo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_TRANSACCION")
	private Date fechaTransaccion;

	@Column(name = "NOMBRE_ARCHIVO")
	private String nombreArchivo;

	@Column(name = "TAMANIO_ARCHIVO")
	private Long tamanioArchivo;

	@Column(name = "TIPO_ARCHIVO")
	private Long tipoArchivo;

	@Column(name = "USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name = "USUARIO_TRANSACCION")
	private String usuarioTransaccion;
	@Column(name = "ID_DOCUMENTO")
	private String idDocumento;

	@Transient
	private byte[] anexo;

	@Transient
	private String descripcion;
	@Transient
	private boolean modificado;

	// protected region atributos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region atributos adicionales end

	public Archivo() {
		// protected region procedimientos adicionales de inicializaci�n on
		// begin
		// Escriba en esta secci�n sus modificaciones

		// protected region procedimientos adicionales de inicializaci�n end
	}

	@Override
	public Archivo clone() throws CloneNotSupportedException {
		return (Archivo) super.clone();
	}

	public Archivo clonar() {
		Archivo copia = new Archivo();
		copia.setIdDocumento(idDocumento);
		copia.setContenidoArchivo(contenidoArchivo);
		copia.setTipoArchivo(tipoArchivo);
		copia.setNombreArchivo(nombreArchivo);
		copia.setTamanioArchivo(tamanioArchivo);
		copia.setAnexo(anexo);
		copia.setDescripcion(descripcion);
		copia.setModificado(modificado);
		copia.setUsuarioCreacion(usuarioCreacion);
		copia.setFechaCreacion(fechaCreacion);
		copia.setUsuarioTransaccion(usuarioTransaccion);
		copia.setFechaTransaccion(fechaTransaccion);
		return copia;
	}

	public Long getIdArchivo() {
		return this.idArchivo;
	}

	public void setIdArchivo(Long idArchivo) {
		this.idArchivo = idArchivo;
	}

	public String getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}

	public byte[] getContenidoArchivo() {
		return this.contenidoArchivo;
	}

	public void setContenidoArchivo(byte[] contenidoArchivo) {
		this.contenidoArchivo = contenidoArchivo;
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

	public Long getTamanioArchivo() {
		return this.tamanioArchivo;
	}

	public void setTamanioArchivo(Long tamanioArchivo) {
		this.tamanioArchivo = tamanioArchivo;
	}

	public Long getTipoArchivo() {
		return this.tipoArchivo;
	}

	public void setTipoArchivo(Long tipoArchivo) {
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Archivo [idArchivo=" + idArchivo + "idDocumento=" + idDocumento + ", fechaCreacion=" + fechaCreacion
				+ ", fechaTransaccion=" + fechaTransaccion + ", nombreArchivo=" + nombreArchivo + ", tamanioArchivo="
				+ tamanioArchivo + ", tipoArchivo=" + tipoArchivo + ", usuarioCreacion=" + usuarioCreacion
				+ ", usuarioTransaccion=" + usuarioTransaccion + ", descripcion=" + descripcion + "]";
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
		for (final String atr : ATRIBUTOS_ENTIDAD_ARCHIVOS) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	public static String[] getAtributosEntidadArchivos() {
		return ATRIBUTOS_ENTIDAD_ARCHIVOS;
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
		hash = 37 * hash + Objects.hashCode(this.contenidoArchivo);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
		hash = 37 * hash + Objects.hashCode(this.idDocumento);
		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad Archivos que se pasa como
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
		final Archivo other = (Archivo) obj;

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

		if (!Objects.equals(this.contenidoArchivo, other.contenidoArchivo)) {
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

		return Objects.equals(this.idDocumento, other.idDocumento);

	}

	public byte[] getAnexo() {
		return anexo;
	}

	public void setAnexo(byte[] anexo) {
		this.anexo = anexo;
	}

	public boolean isModificado() {
		return modificado;
	}

	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region metodos adicionales end

}