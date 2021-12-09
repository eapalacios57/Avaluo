package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;


/**
 * The persistent class for the DOCUMENTOS database table.
 * 
 */
@Entity
@Cacheable(false)
@Table(name="DOCUMENTOS")
@NamedQuery(name="Documento.findAll", query="SELECT d FROM Documento d")
public class Documento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SeqDocumento", sequenceName="SEQ_DOCUMENTOS", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SeqDocumento")
	@Column(name="ID_DOCUMENTO")
	private long idDocumento;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Column(name="\"PATH\"")
	private String path;

	@Column(name="TIPO_DOCUMENTO")
	private String tipoDocumento;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	//bi-directional many-to-one association to Solicitud
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="CODIGO_SOLICITUD")
	private Solicitud solicitud;
	
	@Column(name="NOMBRE_DOCUMENTO")
	private String nombreDocumento;

	@Transient
	private byte[] contenidoArchivo;

	public Documento() {
	}

	
	public byte[] getContenidoArchivo() {
		return contenidoArchivo;
	}

	public void setContenidoArchivo(byte[] contenidoArchivo) {
		this.contenidoArchivo = contenidoArchivo;
	}
	
	public String getNombreDocumento() {
		return nombreDocumento;
	}

	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	public long getIdDocumento() {
		return this.idDocumento;
	}

	public void setIdDocumento(long idDocumento) {
		this.idDocumento = idDocumento;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Solicitud getSolicitud() {
		return this.solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

}