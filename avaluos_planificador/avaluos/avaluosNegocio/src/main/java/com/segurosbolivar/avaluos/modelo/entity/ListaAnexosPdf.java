package com.segurosbolivar.avaluos.modelo.entity;

import static org.eclipse.persistence.annotations.CacheType.NONE;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
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
import javax.persistence.Transient;

import org.apache.commons.codec.binary.Base64;

import com.asesoftware.util.archivo.UtilArchivos;

import uk.co.jemos.podam.annotations.PodamExclude;

/**
 * The persistent class for the PGB_LST_ANEXOS_PDF database table.
 * 
 */
@Entity
@Table(name = "PGB_LST_ANEXOS_PDF")
@NamedQueries({ @NamedQuery(name = "ListaAnexosPdf.findAll", query = "SELECT p FROM ListaAnexosPdf p"),
		@NamedQuery(name = "ListaAnexosPdf.obtenerPorAvaluo", query = "SELECT p FROM ListaAnexosPdf p WHERE p.idAvaluo = :idAvaluo ORDER BY p.consecutivoAnexo") })
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class ListaAnexosPdf implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	/**
	 * Se crea las relaciones para la consulta JPA
	 * 
	 * @author arincon
	 */
	public static final String ENTIDAD_PGB_LST_ANEXOS_PDF_FK_ID_ARCHIVO = "archivo";
	public static final String ENTIDAD_PGB_LST_ANEXOS_PDF_FK_ID_AVALUO = "avaluo";

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_LST_ANEXOS_PDF_PK = "idLstAnexosPdf";
	public static final String ENTIDAD_PGB_LST_ANEXOS_PDF_ID_AVALUO = "idAvaluo";
	public static final String ENTIDAD_PGB_LST_ANEXOS_PDF_ID_ARCHIVO = "idArchivo";
	public static final String ENTIDAD_PGB_LST_ANEXOS_PDF_CONSECUTIVO_ANEXO = "consecutivoAnexo";
	public static final String ENTIDAD_PGB_LST_ANEXOS_PDF_COLUMN1 = "column1";
	public static final String ENTIDAD_PGB_LST_ANEXOS_PDF_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_PGB_LST_ANEXOS_PDF_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_PGB_LST_ANEXOS_PDF_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_LST_ANEXOS_PDF_FECHA_TRANSACCION = "fechaTransaccion";
	private static final String[] ATRIBUTOS_ENTIDAD_PGB_LST_ANEXOS_PDF = {
			ENTIDAD_PGB_LST_ANEXOS_PDF_USUARIO_TRANSACCION, ENTIDAD_PGB_LST_ANEXOS_PDF_FECHA_TRANSACCION,
			ENTIDAD_PGB_LST_ANEXOS_PDF_ID_AVALUO, ENTIDAD_PGB_LST_ANEXOS_PDF_USUARIO_CREACION,
			ENTIDAD_PGB_LST_ANEXOS_PDF_PK, ENTIDAD_PGB_LST_ANEXOS_PDF_COLUMN1,
			ENTIDAD_PGB_LST_ANEXOS_PDF_FECHA_CREACION, ENTIDAD_PGB_LST_ANEXOS_PDF_CONSECUTIVO_ANEXO,
			ENTIDAD_PGB_LST_ANEXOS_PDF_ID_ARCHIVO };

	@Id
	@SequenceGenerator(name = "PGB_LST_ANEXOS_PDF_IDLISTAANEXOSPDF_GENERATOR", sequenceName = "SEQ_PGB_LST_ANEXOS_PDF", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGB_LST_ANEXOS_PDF_IDLISTAANEXOSPDF_GENERATOR")
	@Column(name = "ID_LST_ANEXOS_PDF")
	private Long idListaAnexosPdf;

	@Column(name = "CONSECUTIVO_ANEXO")
	private Long consecutivoAnexo;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_TRANSACCION")
	private Date fechaTransaccion;

	@Column(name = "USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name = "USUARIO_TRANSACCION")
	private String usuarioTransaccion;

	@PodamExclude
	@Column(name = "ID_ARCHIVO")
	private Long idArchivo;

	// bi-directional many-to-one association to Archivo
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ARCHIVO", referencedColumnName = "ID_ARCHIVO", insertable = false, updatable = false)
	private Archivo archivo;

	@PodamExclude
	@Column(name = "ID_AVALUO")
	private Long idAvaluo;

	// bi-directional many-to-one association to Avaluo
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_AVALUO", referencedColumnName = "ID_AVALUO", insertable = false, updatable = false)
	private Avaluo avaluo;

	@Transient
	private byte[] anexo;
	@Transient
	private boolean portada;
	@Transient
	private String nombreFotografia;
	@Transient
	private boolean editarNombre;
	@Transient
	private boolean modificado;

	// protected region atributos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region atributos adicionales end
	public ListaAnexosPdf() {
		// protected region procedimientos adicionales de inicializaci�n on
		// begin
		// Escriba en esta secci�n sus modificaciones

		// protected region procedimientos adicionales de inicializaci�n end
	}
	
	public String obtenerNombreUnico() {
		return idAvaluo+":"+(archivo==null?"":UtilArchivos.quitarExtension( archivo.getNombreArchivo()))+":"+consecutivoAnexo;
	}
	
	public String obtenerNombreFotoUnico() {
		return idAvaluo+"_"+archivo.getIdArchivo()+"_"+(archivo==null?"":consecutivoAnexo+"_"+archivo.getNombreArchivo());
	}

	@Override
	public ListaAnexosPdf clone() throws CloneNotSupportedException {
		return (ListaAnexosPdf) super.clone();
	}
	
	@Override
	public String toString() {
		return "ListaAnexosPdf [idArchivo=" + idArchivo + "idListaAnexosPdf=" + idListaAnexosPdf + ", fechaCreacion=" + fechaCreacion
				+ ", fechaTransaccion=" + fechaTransaccion + ", nombreFotografia=" + nombreFotografia + ", portada="
				+ portada + ", consecutivoAnexo=" + consecutivoAnexo + ", usuarioCreacion=" + usuarioCreacion
				+ ", usuarioTransaccion=" + usuarioTransaccion + ", idAvaluo=" + idAvaluo + "]";
	}

	public Long getIdListaAnexosPdf() {
		return this.idListaAnexosPdf;
	}

	public void setIdListaAnexosPdf(Long idListaAnexosPdf) {
		this.idListaAnexosPdf = idListaAnexosPdf;
	}

	public Long getConsecutivoAnexo() {
		return this.consecutivoAnexo;
	}

	public void setConsecutivoAnexo(Long consecutivoAnexo) {
		this.consecutivoAnexo = consecutivoAnexo;
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

	public Long getIdArchivo() {
		return idArchivo;
	}

	public void setIdArchivo(Long idArchivo) {
		this.idArchivo = idArchivo;
	}

	public Long getIdAvaluo() {
		return idAvaluo;
	}

	public void setIdAvaluo(Long idAvaluo) {
		this.idAvaluo = idAvaluo;
	}

	public Avaluo getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(Avaluo avaluo) {
		this.avaluo = avaluo;
	}

	public byte[] getAnexo() {
		return anexo;
	}

	public void setAnexo(byte[] anexo) {
		this.anexo = anexo;
	}

	/**
	 * Realiza la codificacion del registro Blob a codificacion byte Base64
	 * 
	 * @param Archivo
	 * @throws SQLException
	 * @author arincon
	 */
	public void setAnexo(Blob Archivo) throws SQLException {
		Base64 codec = new Base64();
		int blobLength = (int) Archivo.length();
		byte[] blobAsBytes = Archivo.getBytes(1, blobLength);
		this.anexo = codec.encode(blobAsBytes);
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
		for (final String atr : ATRIBUTOS_ENTIDAD_PGB_LST_ANEXOS_PDF) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	public static String[] getAtributosEntidadListaAnexosPdf() {
		return ATRIBUTOS_ENTIDAD_PGB_LST_ANEXOS_PDF;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idListaAnexosPdf);
		hash = 37 * hash + Objects.hashCode(this.idAvaluo);
		hash = 37 * hash + Objects.hashCode(this.idArchivo);
		hash = 37 * hash + Objects.hashCode(this.consecutivoAnexo);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad ListaAnexosPdf que se pasa
	 * como par�metro comprobando que comparten los mismos valores en cada uno de
	 * sus atributos. Solo se tienen en cuenta los atributos simples, es decir, se
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
		final ListaAnexosPdf other = (ListaAnexosPdf) obj;
		if (!Objects.equals(this.idListaAnexosPdf, other.idListaAnexosPdf)) {
			return false;
		}
		if (!Objects.equals(this.idAvaluo, other.idAvaluo)) {
			return false;
		}
		return true;

	}

	public boolean isPortada() {
		return portada;
	}

	public void setPortada(boolean portada) {
		this.portada = portada;
	}

	public boolean isEditarNombre() {
		return editarNombre;
	}

	public void setEditarNombre(boolean editarNombre) {
		this.editarNombre = editarNombre;
	}

	public String getNombreFotografia() {
		return nombreFotografia;
	}

	public void setNombreFotografia(String nombreFotografia) {
		this.nombreFotografia = nombreFotografia;
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