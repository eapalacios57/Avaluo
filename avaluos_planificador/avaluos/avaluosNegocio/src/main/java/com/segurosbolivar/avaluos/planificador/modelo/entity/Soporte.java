package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;


import java.util.Date;


/**
 * The persistent class for the SOPORTES database table.
 * 
 */
@Entity
@Cacheable(false)
@Table(name = "SOPORTES")
@NamedQueries({ @NamedQuery(name = "Soporte.findAll", query = "SELECT s FROM Soporte s"),
		@NamedQuery(name = "Soporte.findByUndProd", query = "SELECT s FROM Soporte s WHERE s.unidadProductiva.idUnidadProductiva = :idUnidadProductiva "),
		@NamedQuery(name = "Soporte.findByCultivo", query = "SELECT s FROM Soporte s WHERE s.cultivo.idCultivo = :idCultivo ")})

public class Soporte implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@SequenceGenerator(name="SeqSoporte", sequenceName="SEQ_SOPORTE", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SeqSoporte")
	@Column(name="ID_SOPORTE")
	private long idSoporte;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	private String hora;

	@Column(name = "LATITUD")
	private String latitud;

	@Column(name = "LONGITUD")
	private String longitud;

	@Column(name="PATH")
	private String path;
	
	@Column(name="COD_IMAGEN")
	private String codImagen;

	@Column(name="TIPO_SOPORTE")
	private String tipoSoporte;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	//bi-directional many-to-one association to Cultivo
	@ManyToOne
	@JoinColumn(name="ID_CULTIVO")
	private Cultivo cultivo;

	//bi-directional many-to-one association to UnidadProductiva
	@ManyToOne
	@JoinColumn(name="ID_UNIDAD_PRODUCTIVA")
	private UnidadProductiva unidadProductiva;

	public Soporte() {
	}

	public long getIdSoporte() {
		return this.idSoporte;
	}

	public void setIdSoporte(long idSoporte) {
		this.idSoporte = idSoporte;
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

	public String getHora() {
		return this.hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getLatitud() {
		return this.latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return this.longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTipoSoporte() {
		return this.tipoSoporte;
	}

	public void setTipoSoporte(String tipoSoporte) {
		this.tipoSoporte = tipoSoporte;
	}

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Cultivo getCultivo() {
		return this.cultivo;
	}

	public void setCultivo(Cultivo cultivo) {
		this.cultivo = cultivo;
	}

	public UnidadProductiva getUnidadProductiva() {
		return this.unidadProductiva;
	}

	public void setUnidadProductiva(UnidadProductiva unidadProductiva) {
		this.unidadProductiva = unidadProductiva;
	}

	public String getCodImagen() {
		return codImagen;
	}

	public void setCodImagen(String codImagen) {
		this.codImagen = codImagen;
	}
	
	

}