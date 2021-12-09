package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the UNIDAD database table.
 * 
 */
@Entity
@Cacheable(false)
@NamedQuery(name="Unidad.findAll", query="SELECT u FROM Unidad u")
public class Unidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_UNIDAD")
	private long idUnidad;

	@Column(name="FACTOR_HA")
	private BigDecimal factorHa;

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

	//bi-directional many-to-one association to Cultivo
	@JsonIgnore
	@OneToMany(mappedBy="unidad")
	private List<Cultivo> cultivos;

	//bi-directional many-to-one association to UnidadProductiva
	@JsonIgnore
	@OneToMany(mappedBy="unidad")
	private List<UnidadProductiva> unidadProductivas;

	public Unidad() {
	}

	public long getIdUnidad() {
		return this.idUnidad;
	}

	public void setIdUnidad(long idUnidad) {
		this.idUnidad = idUnidad;
	}

	public BigDecimal getFactorHa() {
		return this.factorHa;
	}

	public void setFactorHa(BigDecimal factorHa) {
		this.factorHa = factorHa;
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

	public List<Cultivo> getCultivos() {
		return this.cultivos;
	}

	public void setCultivos(List<Cultivo> cultivos) {
		this.cultivos = cultivos;
	}

	public Cultivo addCultivo(Cultivo cultivo) {
		getCultivos().add(cultivo);
		cultivo.setUnidad(this);

		return cultivo;
	}

	public Cultivo removeCultivo(Cultivo cultivo) {
		getCultivos().remove(cultivo);
		cultivo.setUnidad(null);

		return cultivo;
	}

	public List<UnidadProductiva> getUnidadProductivas() {
		return this.unidadProductivas;
	}

	public void setUnidadProductivas(List<UnidadProductiva> unidadProductivas) {
		this.unidadProductivas = unidadProductivas;
	}

	public UnidadProductiva addUnidadProductiva(UnidadProductiva unidadProductiva) {
		getUnidadProductivas().add(unidadProductiva);
		unidadProductiva.setUnidad(this);

		return unidadProductiva;
	}

	public UnidadProductiva removeUnidadProductiva(UnidadProductiva unidadProductiva) {
		getUnidadProductivas().remove(unidadProductiva);
		unidadProductiva.setUnidad(null);

		return unidadProductiva;
	}

}