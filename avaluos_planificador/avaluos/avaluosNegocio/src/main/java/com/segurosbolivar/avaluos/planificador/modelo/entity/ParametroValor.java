package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;


/**
 * The persistent class for the PARAMETRO_VALOR database table.
 * 
 */
@Entity
@Cacheable(false)
@Table(name="PARAMETRO_VALOR")
@NamedQuery(name="ParametroValor.findAll", query="SELECT p FROM ParametroValor p")
public class ParametroValor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ParametroValorPK id;

	private String descripcion;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_TRANSACCION")
	private Date fechaTransaccion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name="USUARIO_TRANSACCION")
	private String usuarioTransaccion;

	//bi-directional many-to-one association to Parametro
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="ID_PARAMETRO")
	private Parametro parametro;

	public ParametroValor() {
	}

	public ParametroValorPK getId() {
		return this.id;
	}

	public void setId(ParametroValorPK id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public Parametro getParametro() {
		return this.parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

}