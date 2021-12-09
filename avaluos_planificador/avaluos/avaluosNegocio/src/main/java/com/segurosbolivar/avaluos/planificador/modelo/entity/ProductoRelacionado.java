package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PRODUCTO_RELACIONADO database table.
 * 
 */
@Entity
@Table(name="PRODUCTO_RELACIONADO")
@Cacheable(false)
@NamedQuery(name="ProductoRelacionado.findAll", query="SELECT p FROM ProductoRelacionado p")
public class ProductoRelacionado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String codigo;

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

	//bi-directional many-to-one association to ProductoRelacionadoSolicitud
	@OneToMany(mappedBy="productoRelacionado")
	private List<ProductoRelacionadoSolicitud> productoRelacionadoSolicituds;

	public ProductoRelacionado() {
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public List<ProductoRelacionadoSolicitud> getProductoRelacionadoSolicituds() {
		return this.productoRelacionadoSolicituds;
	}

	public void setProductoRelacionadoSolicituds(List<ProductoRelacionadoSolicitud> productoRelacionadoSolicituds) {
		this.productoRelacionadoSolicituds = productoRelacionadoSolicituds;
	}

	public ProductoRelacionadoSolicitud addProductoRelacionadoSolicitud(ProductoRelacionadoSolicitud productoRelacionadoSolicitud) {
		getProductoRelacionadoSolicituds().add(productoRelacionadoSolicitud);
		productoRelacionadoSolicitud.setProductoRelacionado(this);

		return productoRelacionadoSolicitud;
	}

	public ProductoRelacionadoSolicitud removeProductoRelacionadoSolicitud(ProductoRelacionadoSolicitud productoRelacionadoSolicitud) {
		getProductoRelacionadoSolicituds().remove(productoRelacionadoSolicitud);
		productoRelacionadoSolicitud.setProductoRelacionado(null);

		return productoRelacionadoSolicitud;
	}

}