package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ACTIVIDAD_FINANCIERA database table.
 * 
 */
@Entity
@Cacheable(false)
@Table(name="ACTIVIDAD_FINANCIERA")
@NamedQueries({
	@NamedQuery(name="ActividadFinanciera.findAll", query="SELECT a FROM ActividadFinanciera a"),
	@NamedQuery(name="ActividadFinanciera.findById", query="SELECT a FROM ActividadFinanciera a WHERE a.codigoActividad = :codigoActividad ")	
	})

public class ActividadFinanciera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CODIGO_ACTIVIDAD")
	private String codigoActividad;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_TRANSACCION")
	private Date fechaTransaccion;

	private String nombre;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name="USUARIO_TRANSACCION")
	private String usuarioTransaccion;

	private String vigencia;

	//bi-directional many-to-one association to ActividadFinancieraSolicitud
	
	@OneToMany(mappedBy="actividadFinanciera")
	private List<ActividadFinancieraSolicitud> actividadFinancieraSolicituds;
	
	@OneToMany(mappedBy="actividadFinanciera")
	private List<HistActFinancSolicitud> historicos;

	public ActividadFinanciera() {
	}

	public String getCodigoActividad() {
		return this.codigoActividad;
	}

	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getVigencia() {
		return this.vigencia;
	}

	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}

	public List<ActividadFinancieraSolicitud> getActividadFinancieraSolicituds() {
		return this.actividadFinancieraSolicituds;
	}

	public void setActividadFinancieraSolicituds(List<ActividadFinancieraSolicitud> actividadFinancieraSolicituds) {
		this.actividadFinancieraSolicituds = actividadFinancieraSolicituds;
	}

	public ActividadFinancieraSolicitud addActividadFinancieraSolicitud(ActividadFinancieraSolicitud actividadFinancieraSolicitud) {
		getActividadFinancieraSolicituds().add(actividadFinancieraSolicitud);
		actividadFinancieraSolicitud.setActividadFinanciera(this);

		return actividadFinancieraSolicitud;
	}

	public ActividadFinancieraSolicitud removeActividadFinancieraSolicitud(ActividadFinancieraSolicitud actividadFinancieraSolicitud) {
		getActividadFinancieraSolicituds().remove(actividadFinancieraSolicitud);
		actividadFinancieraSolicitud.setActividadFinanciera(null);

		return actividadFinancieraSolicitud;
	}

}