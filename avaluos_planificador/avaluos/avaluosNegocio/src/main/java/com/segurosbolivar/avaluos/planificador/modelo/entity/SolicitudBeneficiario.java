package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;


/**
 * The persistent class for the SOLICITUD_BENEFICIARIO database table.
 * 
 */
@Entity
@Cacheable(false)
@Table(name="SOLICITUD_BENEFICIARIO")
@NamedQuery(name="SolicitudBeneficiario.findAll", query="SELECT s FROM SolicitudBeneficiario s")
public class SolicitudBeneficiario implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SolicitudBeneficiarioPK id;

	private String contacto;

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

	//bi-directional many-to-one association to Beneficiario
	//@JsonIgnore
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="NUMERO_DOCUMENTO_BENEFICIARIO", referencedColumnName="NUMERO_DOCUMENTO_BENEFICIARIO"),
		@JoinColumn(name="TIPO_DOCUMENTO_BENEFICIARIO", referencedColumnName="TIPO_DOCUMENTO_BENEFICIARIO")
		})
	private Beneficiario beneficiario;

	//bi-directional many-to-one association to Solicitud
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="CODIGO_SOLICITUD")
	private Solicitud solicitud;

	public SolicitudBeneficiario() {
	}

	public SolicitudBeneficiarioPK getId() {
		return this.id;
	}

	public void setId(SolicitudBeneficiarioPK id) {
		this.id = id;
	}

	public String getContacto() {
		return this.contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
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

	public Beneficiario getBeneficiario() {
		return this.beneficiario;
	}

	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}

	public Solicitud getSolicitud() {
		return this.solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

}