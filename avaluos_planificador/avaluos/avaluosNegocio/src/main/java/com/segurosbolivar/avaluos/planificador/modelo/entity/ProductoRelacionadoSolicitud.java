package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;


/**
 * The persistent class for the PRODUCTO_RELACIONADO_SOLICITUD database table.
 * 
 */
@Entity
@Cacheable(false)
@Table(name="PRODUCTO_RELACIONADO_SOLICITUD")
@NamedQuery(name="ProductoRelacionadoSolicitud.findAll", query="SELECT p FROM ProductoRelacionadoSolicitud p")
public class ProductoRelacionadoSolicitud implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProductoRelacionadoSolicitudPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_TRANSACCION")
	private Date fechaTransaccion;

	private String principal;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name="USUARIO_TRANSACCION")
	private String usuarioTransaccion;

	//bi-directional many-to-one association to ProductoRelacionado
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="PRODUCTO_RELACIONADO_CODIGO")
	private ProductoRelacionado productoRelacionado;

	//bi-directional many-to-one association to Solicitud
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="CODIGO_SOLICITUD")
	private Solicitud solicitud;

	public ProductoRelacionadoSolicitud() {
	}

	public ProductoRelacionadoSolicitudPK getId() {
		return this.id;
	}

	public void setId(ProductoRelacionadoSolicitudPK id) {
		this.id = id;
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

	public String getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
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

	public ProductoRelacionado getProductoRelacionado() {
		return this.productoRelacionado;
	}

	public void setProductoRelacionado(ProductoRelacionado productoRelacionado) {
		this.productoRelacionado = productoRelacionado;
	}

	public Solicitud getSolicitud() {
		return this.solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

}