package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;


/**
 * The persistent class for the SOLICITUD_MOVIMIENTO database table.
 * 
 */
@Entity
@Cacheable(false)
@Table(name="SOLICITUD_MOVIMIENTO")
@NamedQuery(name="SolicitudMovimiento.findAll", query="SELECT s FROM SolicitudMovimiento s")
public class SolicitudMovimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SeqSolicitudMov", sequenceName="SEQ_SOLICITUD_MOV", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SeqSolicitudMov")
	@Column(name="ID_SOLICITUD_MOVIMIENTO")
	private long idSolicitudMovimiento;

	@Column(name="ESTADO_MOVIMIENTO")
	private String estadoMovimiento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_MOVIMIENTO")
	private Date fechaMovimiento;

	@Column(name="USUARIO_MOVIMIENTO")
	private String usuarioMovimiento;

	//bi-directional many-to-one association to Solicitud
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="CODIGO_SOLICITUD")
	private Solicitud solicitud;

	public SolicitudMovimiento() {
	}

	public long getIdSolicitudMovimiento() {
		return this.idSolicitudMovimiento;
	}

	public void setIdSolicitudMovimiento(long idSolicitudMovimiento) {
		this.idSolicitudMovimiento = idSolicitudMovimiento;
	}

	public String getEstadoMovimiento() {
		return this.estadoMovimiento;
	}

	public void setEstadoMovimiento(String estadoMovimiento) {
		this.estadoMovimiento = estadoMovimiento;
	}

	public Date getFechaMovimiento() {
		return this.fechaMovimiento;
	}

	public void setFechaMovimiento(Date fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

	public String getUsuarioMovimiento() {
		return this.usuarioMovimiento;
	}

	public void setUsuarioMovimiento(String usuarioMovimiento) {
		this.usuarioMovimiento = usuarioMovimiento;
	}

	public Solicitud getSolicitud() {
		return this.solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

}