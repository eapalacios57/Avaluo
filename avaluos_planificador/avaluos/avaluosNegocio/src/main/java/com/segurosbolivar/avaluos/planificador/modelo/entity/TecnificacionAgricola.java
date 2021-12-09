package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;


/**
 * The persistent class for the TECNIFICACION_AGRICOLA database table.
 * 
 */
@Entity
@Cacheable(false)
@Table(name="TECNIFICACION_AGRICOLA")
@NamedQuery(name="TecnificacionAgricola.findAll", query="SELECT t FROM TecnificacionAgricola t")
public class TecnificacionAgricola implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SeqTecnificacionAgricola", sequenceName="SEQ_TECNIFICACION_AGRICOLA", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SeqTecnificacionAgricola")
	@Column(name="ID_TECNIFICACION_AGRICOLA")
	private long idTecnificacionAgricola;

	@Column(name="ACTIVIDAD_MECANIZADAS")
	private String actividadMecanizadas;

	@JsonIgnore
	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@JsonIgnore
	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_TRANSACCION")
	private Date fechaTransaccion;

	private String infraestructura;

	@Column(name="MAQUINARIA_DISPONIBLE")
	private String maquinariaDisponible;

	@Column(name="TIPO_TRANSPORTE_PROPIO")
	private String tipoTransportePropio;

	@Column(name="TRANSPORTE_ALQUILADO")
	private String transporteAlquilado;

	@Column(name="TRANSPORTE_PROPIO")
	private String transportePropio;

	@JsonIgnore
	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;
	
	@JsonIgnore
	@Column(name="USUARIO_TRANSACCION")
	private String usuarioTransaccion;

	//bi-directional many-to-one association to UnidadProductiva
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="ID_UNIDAD_PRODUCTIVA")
	private UnidadProductiva unidadProductiva;

	public TecnificacionAgricola() {
	}

	public long getIdTecnificacionAgricola() {
		return this.idTecnificacionAgricola;
	}

	public void setIdTecnificacionAgricola(long idTecnificacionAgricola) {
		this.idTecnificacionAgricola = idTecnificacionAgricola;
	}

	public String getActividadMecanizadas() {
		return this.actividadMecanizadas;
	}

	public void setActividadMecanizadas(String actividadMecanizadas) {
		this.actividadMecanizadas = actividadMecanizadas;
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

	public String getInfraestructura() {
		return this.infraestructura;
	}

	public void setInfraestructura(String infraestructura) {
		this.infraestructura = infraestructura;
	}

	public String getMaquinariaDisponible() {
		return this.maquinariaDisponible;
	}

	public void setMaquinariaDisponible(String maquinariaDisponible) {
		this.maquinariaDisponible = maquinariaDisponible;
	}

	public String getTipoTransportePropio() {
		return this.tipoTransportePropio;
	}

	public void setTipoTransportePropio(String tipoTransportePropio) {
		this.tipoTransportePropio = tipoTransportePropio;
	}

	public String getTransporteAlquilado() {
		return this.transporteAlquilado;
	}

	public void setTransporteAlquilado(String transporteAlquilado) {
		this.transporteAlquilado = transporteAlquilado;
	}

	public String getTransportePropio() {
		return this.transportePropio;
	}

	public void setTransportePropio(String transportePropio) {
		this.transportePropio = transportePropio;
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

	public UnidadProductiva getUnidadProductiva() {
		return this.unidadProductiva;
	}

	public void setUnidadProductiva(UnidadProductiva unidadProductiva) {
		this.unidadProductiva = unidadProductiva;
	}

}