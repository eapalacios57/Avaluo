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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import uk.co.jemos.podam.annotations.PodamExclude;

/**
 * The persistent class for the PGB_ANEXO_FOTOGRAFICO database table.
 *
 */
@Entity
@Table(name = "PGB_ANEXO_FOTOGRAFICO")
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
@NamedQueries({ @NamedQuery(name = "AnexoFotografico.findAll", query = "SELECT p FROM AnexoFotografico p"),
		@NamedQuery(name = "AnexoFotografico.idAvaluo", query = "SELECT p FROM AnexoFotografico p WHERE p.idAvaluo = :idAvaluo") })
public class AnexoFotografico implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_ANEXO_FOTOGRAFICO_PK = "idAnexoFotografico";
	public static final String ENTIDAD_PGB_ANEXO_FOTOGRAFICO_ID_AVALUO = "idAvaluo";
	public static final String ENTIDAD_PGB_ANEXO_FOTOGRAFICO_ID_IMG_FACHADA = "idImgFachada";
	public static final String ENTIDAD_PGB_ANEXO_FOTOGRAFICO_ID_DOC_ANEXOS = "idDocAnexos";
	public static final String ENTIDAD_PGB_ANEXO_FOTOGRAFICO_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_PGB_ANEXO_FOTOGRAFICO_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_PGB_ANEXO_FOTOGRAFICO_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_ANEXO_FOTOGRAFICO_FECHA_TRANSACCION = "fechaTransaccion";
	private static final String[] ATRIBUTOS_ENTIDAD_PGB_ANEXO_FOTOGRAFICO = {
			ENTIDAD_PGB_ANEXO_FOTOGRAFICO_USUARIO_TRANSACCION, ENTIDAD_PGB_ANEXO_FOTOGRAFICO_ID_IMG_FACHADA,
			ENTIDAD_PGB_ANEXO_FOTOGRAFICO_PK, ENTIDAD_PGB_ANEXO_FOTOGRAFICO_ID_DOC_ANEXOS,
			ENTIDAD_PGB_ANEXO_FOTOGRAFICO_ID_AVALUO, ENTIDAD_PGB_ANEXO_FOTOGRAFICO_FECHA_TRANSACCION,
			ENTIDAD_PGB_ANEXO_FOTOGRAFICO_FECHA_CREACION, ENTIDAD_PGB_ANEXO_FOTOGRAFICO_USUARIO_CREACION };


	@Id
	@SequenceGenerator(name = "PGB_ANEXO_FOTOGRAFICO_IDANEXOFOTOGRAFICO_GENERATOR", sequenceName = "SEQ_PGB_ANEXO_FOTOGRAFICO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGB_ANEXO_FOTOGRAFICO_IDANEXOFOTOGRAFICO_GENERATOR")
	@Column(name = "ID_ANEXO_FOTOGRAFICO", unique = true, nullable = false, precision = 10)
	private Long idAnexoFotografico;

	@PodamExclude
	@Column(name = "ID_AVALUO")
	private Long idAvaluo;

	@PodamExclude
	@Column(name = "ID_IMG_FACHADA")
	private Long idImgFachada;

	@PodamExclude
	@Column(name = "ID_DOC_ANEXOS")
	private Long idDocAnexos;

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

	// uni-directional many-to-one association to Archivo
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_DOC_ANEXOS", referencedColumnName = "ID_ARCHIVO", insertable = false, updatable = false)
	private Archivo archivoPdf;

	// uni-directional many-to-one association to Archivo
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_IMG_FACHADA", referencedColumnName = "ID_ARCHIVO", insertable = false, updatable = false)
	private Archivo archivoFoto;

	// bi-directional many-to-one association to Avaluo
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_AVALUO", referencedColumnName = "ID_AVALUO", insertable = false, updatable = false)
	private Avaluo avaluo;
	
	@Transient
	private ListaAnexosPdf fachada;
	
	@Transient
	private byte[] anexo;

	// protected region atributos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region atributos adicionales end

	public AnexoFotografico() {
		// protected region procedimientos adicionales de inicializaci�n on
		// begin
		// Escriba en esta secci�n sus modificaciones

		// protected region procedimientos adicionales de inicializaci�n end
	}

	public Long getIdAnexoFotografico() {
		return this.idAnexoFotografico;
	}

	public void setIdAnexoFotografico(Long idAnexoFotografico) {
		this.idAnexoFotografico = idAnexoFotografico;
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

	public Archivo getArchivoPdf() {
		return this.archivoPdf;
	}

	public void setArchivoPdf(Archivo archivoPdf) {
		this.archivoPdf = archivoPdf;
	}

	public Archivo getArchivoFoto() {
		return this.archivoFoto;
	}

	public void setArchivoFoto(Archivo archivoFoto) {
		this.archivoFoto = archivoFoto;
	}

	public Avaluo getAvaluo() {
		return this.avaluo;
	}

	public void setAvaluo(Avaluo avaluo) {
		this.avaluo = avaluo;
	}

	@Override
	public String toString() {
		return "AnexoFotografico [idAnexoFotografico=" + idAnexoFotografico + ", fechaCreacion=" + fechaCreacion
				+ ", fechaTransaccion=" + fechaTransaccion + ", usuarioCreacion=" + usuarioCreacion
				+ ", usuarioTransaccion=" + usuarioTransaccion + ", archivoPdf=" + archivoPdf + ", archivoFoto="
				+ archivoFoto + ", avaluo=" + avaluo + "]";
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
		for (final String atr : ATRIBUTOS_ENTIDAD_PGB_ANEXO_FOTOGRAFICO) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	@Override
	public AnexoFotografico clone() throws CloneNotSupportedException {
		return (AnexoFotografico) super.clone();
	}

	public static String[] getAtributosEntidadPgbAnexoFotografico() {
		return ATRIBUTOS_ENTIDAD_PGB_ANEXO_FOTOGRAFICO;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idAnexoFotografico);
		hash = 37 * hash + Objects.hashCode(this.idAvaluo);
		hash = 37 * hash + Objects.hashCode(this.idImgFachada);
		hash = 37 * hash + Objects.hashCode(this.idDocAnexos);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad PgbAnexoFotografico que
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
		final AnexoFotografico other = (AnexoFotografico) obj;

		if (!Objects.equals(this.idAnexoFotografico, other.idAnexoFotografico)) {
			return false;
		}

		if (!Objects.equals(this.idAvaluo, other.idAvaluo)) {
			return false;
		}

		if (!Objects.equals(this.idImgFachada, other.idImgFachada)) {
			return false;
		}

		if (!Objects.equals(this.idDocAnexos, other.idDocAnexos)) {
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

	public Long getIdAvaluo() {
		return idAvaluo;
	}

	public void setIdAvaluo(Long idAvaluo) {
		this.idAvaluo = idAvaluo;
	}

	public Long getIdImgFachada() {
		return idImgFachada;
	}

	public void setIdImgFachada(Long idImgFachada) {
		this.idImgFachada = idImgFachada;
	}

	public Long getIdDocAnexos() {
		return idDocAnexos;
	}

	public void setIdDocAnexos(Long idDocAnexos) {
		this.idDocAnexos = idDocAnexos;
	}

	public ListaAnexosPdf getFachada() {
		return fachada;
	}

	public void setFachada(ListaAnexosPdf fachada) {
		this.fachada = fachada;
	}

	public byte[] getAnexo() {
		return anexo;
	}

	public void setAnexo(byte[] anexo) {
		this.anexo = anexo;
	}
	
	

	// protected region metodos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region metodos adicionales end

}