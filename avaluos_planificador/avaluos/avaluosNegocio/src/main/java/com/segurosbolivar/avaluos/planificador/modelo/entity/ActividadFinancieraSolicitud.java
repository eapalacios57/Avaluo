package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the ACTIVIDAD_FINANCIERA_SOLICITUD database table.
 * 
 */
@Entity
@Cacheable(false)
@Table(name = "ACTIVIDAD_FINANCIERA_SOLICITUD")
@NamedQueries({
		@NamedQuery(name = "ActividadFinancieraSolicitud.findAll", query = "SELECT a FROM ActividadFinancieraSolicitud a"),
		@NamedQuery(name = "ActividadFinancieraSolicitud.findBySol", query = "SELECT a FROM ActividadFinancieraSolicitud a WHERE a.codigoSolicitud = :codigoSolicitud "),
		@NamedQuery(name = "ActividadFinancieraSolicitud.findBySolAndAct", query = "SELECT a FROM ActividadFinancieraSolicitud a WHERE a.codigoSolicitud = :codigoSolicitud AND a.codigoActividad = :codigoActividad")})
public class ActividadFinancieraSolicitud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ACT_FINANC_SOLICITUD_ID_GENERATOR", sequenceName = "SEQ_ACT_FINAN_SOL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACT_FINANC_SOLICITUD_ID_GENERATOR")
	@Column(name = "ID")
	private BigDecimal id;

	@Column(name = "CODIGO_SOLICITUD")
	private String codigoSolicitud;

	@Column(name = "CODIGO_ACTIVIDAD")
	private String codigoActividad;

	private BigDecimal cantidad;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_FIN")
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_INICIO")
	private Date fechaInicio;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_TRANSACCION")
	private Date fechaTransaccion;

	@Column(name="PERIODO_GRACIA")
	private BigDecimal periodoGracia;

	private BigDecimal plazo;

	private String principal;

	@Column(name="RAZON_INVERSION")
	private String razonInversion;

	private String unidad;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name="USUARIO_TRANSACCION")
	private String usuarioTransaccion;

	@Column(name="VALOR_CREDITO")
	private BigDecimal valorCredito;

	@Column(name="VALOR_PROYECTO")
	private BigDecimal valorProyecto;

	// bi-directional many-to-one association to ActividadFinanciera
	@ManyToOne
	@JoinColumn(name = "CODIGO_ACTIVIDAD", updatable = false, insertable = false)
	private ActividadFinanciera actividadFinanciera;

	@ManyToOne()
	@JoinColumn(name = "CODIGO_SOLICITUD", updatable = false, insertable = false)
	private Solicitud solicitud;

	public ActividadFinancieraSolicitud() {
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getCodigoSolicitud() {
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(String codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public String getCodigoActividad() {
		return codigoActividad;
	}

	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaTransaccion() {
		return this.fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public BigDecimal getPeriodoGracia() {
		return this.periodoGracia;
	}

	public void setPeriodoGracia(BigDecimal periodoGracia) {
		this.periodoGracia = periodoGracia;
	}

	public BigDecimal getPlazo() {
		return this.plazo;
	}

	public void setPlazo(BigDecimal plazo) {
		this.plazo = plazo;
	}

	public String getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getRazonInversion() {
		return this.razonInversion;
	}

	public void setRazonInversion(String razonInversion) {
		this.razonInversion = razonInversion;
	}

	public String getUnidad() {
		return this.unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
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

	public BigDecimal getValorCredito() {
		return this.valorCredito;
	}

	public void setValorCredito(BigDecimal valorCredito) {
		this.valorCredito = valorCredito;
	}

	public BigDecimal getValorProyecto() {
		return this.valorProyecto;
	}

	public void setValorProyecto(BigDecimal valorProyecto) {
		this.valorProyecto = valorProyecto;
	}

	public ActividadFinanciera getActividadFinanciera() {
		return this.actividadFinanciera;
	}

	public void setActividadFinanciera(ActividadFinanciera actividadFinanciera) {
		this.actividadFinanciera = actividadFinanciera;
	}

	public Solicitud getSolicitud() {
		return this.solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

}