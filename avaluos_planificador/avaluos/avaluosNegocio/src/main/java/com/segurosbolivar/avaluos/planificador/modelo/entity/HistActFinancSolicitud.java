package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the HIST_ACT_FINANC_SOLICITUD database table.
 * 
 */
@Entity
@Table(name = "HIST_ACT_FINANC_SOLICITUD")
@NamedQueries({ @NamedQuery(name = "HistActFinancSolicitud.findAll", query = "SELECT h FROM HistActFinancSolicitud h"),
		@NamedQuery(name = "HistActFinancSolicitud.findByIdActFinSol", query = "SELECT h FROM HistActFinancSolicitud h WHERE h.idActFinSol = :idActFinSol ORDER BY h.idHistorico DESC"),
		@NamedQuery(name = "HistActFinancSolicitud.findByCodigoSolicitudAndCodigoActividad", query = "SELECT h FROM HistActFinancSolicitud h WHERE h.codigoActividad = :codigoActividad AND h.codigoSolicitud = :codigoSolicitud ORDER BY h.idHistorico DESC") })
public class HistActFinancSolicitud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "HIST_ACT_FINANC_SOLICITUD_IDHISTORICO_GENERATOR", sequenceName = "SEQ_HIST_ACT_SOL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HIST_ACT_FINANC_SOLICITUD_IDHISTORICO_GENERATOR")
	@Column(name = "ID_HISTORICO")
	private Long idHistorico;

	@Column(name = "ID_ACT_FIN_SOL")
	private BigDecimal idActFinSol;

	private BigDecimal cantidad;

	@Column(name = "CODIGO_ACTIVIDAD")
	private String codigoActividad;

	@Column(name = "CODIGO_SOLICITUD")
	private String codigoSolicitud;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_FIN")
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_INICIO")
	private Date fechaInicio;

	@Column(name = "PERIODO_GRACIA")
	private BigDecimal periodoGracia;

	private BigDecimal plazo;

	@Column(name = "RAZON_INVERSION")
	private String razonInversion;

	private String unidad;

	@Column(name = "USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name = "VALOR_CREDITO")
	private BigDecimal valorCredito;

	@Column(name = "VALOR_PROYECTO")
	private BigDecimal valorProyecto;

	@ManyToOne
	@JoinColumn(name = "CODIGO_ACTIVIDAD", insertable = false, updatable = false)
	private ActividadFinanciera actividadFinanciera;

	public HistActFinancSolicitud() {
	}

	public Long getIdHistorico() {
		return this.idHistorico;
	}

	public void setIdHistorico(Long idHistorico) {
		this.idHistorico = idHistorico;
	}

	public BigDecimal getIdActFinSol() {
		return idActFinSol;
	}

	public void setIdActFinSol(BigDecimal idActFinSol) {
		this.idActFinSol = idActFinSol;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public String getCodigoActividad() {
		return this.codigoActividad;
	}

	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
	}

	public String getCodigoSolicitud() {
		return this.codigoSolicitud;
	}

	public void setCodigoSolicitud(String codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
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
		return actividadFinanciera;
	}

	public void setActividadFinanciera(ActividadFinanciera actividadFinanciera) {
		this.actividadFinanciera = actividadFinanciera;
	}

}