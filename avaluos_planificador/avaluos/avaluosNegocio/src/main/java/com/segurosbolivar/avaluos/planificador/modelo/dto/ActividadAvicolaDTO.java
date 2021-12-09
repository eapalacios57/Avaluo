package com.segurosbolivar.avaluos.planificador.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

public class ActividadAvicolaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7978382750835159055L;

	private BigDecimal idActividadAvicola;

	private String lineaGenetica;

	private BigDecimal areaGalponesEngorde;

	private BigDecimal areaGalponesLevante;

	private String ciclosProducccionAnio;

	private BigDecimal distancia;

	private String equipoDisponible;

	private Date fechaCreacion;

	private Date fechaTransaccion;

	private String infraestructura;

	private String integradoConQuien;

	private BigDecimal numeroAves;

	private BigDecimal numeroGalponesEngorde;

	private BigDecimal numeroGalponesLevante;

	private String productorIntegrado;

	private String proveedor;

	private String tipoTransportePropio;

	private String transporteAlquilado;

	private String transportePropio;

	private String usuarioCreacion;

	private String usuarioTransaccion;

	private BigDecimal idUnidadProductiva;

	private BigDecimal areaTotalGalpones;

	private String lugarVenta;

	private String comentarios;

	private BigDecimal numGalponesPostura;
	
	private BigDecimal areaGalponesPostura;
	
	private String asistenciaTecnica;

	public ActividadAvicolaDTO() {
	}

	public BigDecimal getIdActividadAvicola() {
		return idActividadAvicola;
	}

	public void setIdActividadAvicola(BigDecimal idActividadAvicola) {
		this.idActividadAvicola = idActividadAvicola;
	}

	public String getLineaGenetica() {
		return lineaGenetica;
	}

	public void setLineaGenetica(String lineaGenetica) {
		this.lineaGenetica = lineaGenetica;
	}

	public BigDecimal getAreaGalponesEngorde() {
		return areaGalponesEngorde;
	}

	public void setAreaGalponesEngorde(BigDecimal areaGalponesEngorde) {
		this.areaGalponesEngorde = areaGalponesEngorde;
	}

	public BigDecimal getAreaGalponesLevante() {
		return areaGalponesLevante;
	}

	public void setAreaGalponesLevante(BigDecimal areaGalponesLevante) {
		this.areaGalponesLevante = areaGalponesLevante;
	}

	public String getCiclosProducccionAnio() {
		return ciclosProducccionAnio;
	}

	public void setCiclosProducccionAnio(String ciclosProducccionAnio) {
		this.ciclosProducccionAnio = ciclosProducccionAnio;
	}

	public BigDecimal getDistancia() {
		return distancia;
	}

	public void setDistancia(BigDecimal distancia) {
		this.distancia = distancia;
	}

	public String getEquipoDisponible() {
		return equipoDisponible;
	}

	public void setEquipoDisponible(String equipoDisponible) {
		this.equipoDisponible = equipoDisponible;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public String getInfraestructura() {
		return infraestructura;
	}

	public void setInfraestructura(String infraestructura) {
		this.infraestructura = infraestructura;
	}

	public String getIntegradoConQuien() {
		return integradoConQuien;
	}

	public void setIntegradoConQuien(String integradoConQuien) {
		this.integradoConQuien = integradoConQuien;
	}

	public BigDecimal getNumeroAves() {
		return numeroAves;
	}

	public void setNumeroAves(BigDecimal numeroAves) {
		this.numeroAves = numeroAves;
	}

	public BigDecimal getNumeroGalponesEngorde() {
		return numeroGalponesEngorde;
	}

	public void setNumeroGalponesEngorde(BigDecimal numeroGalponesEngorde) {
		this.numeroGalponesEngorde = numeroGalponesEngorde;
	}

	public BigDecimal getNumeroGalponesLevante() {
		return numeroGalponesLevante;
	}

	public void setNumeroGalponesLevante(BigDecimal numeroGalponesLevante) {
		this.numeroGalponesLevante = numeroGalponesLevante;
	}

	public String getProductorIntegrado() {
		return productorIntegrado;
	}

	public void setProductorIntegrado(String productorIntegrado) {
		this.productorIntegrado = productorIntegrado;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getTipoTransportePropio() {
		return tipoTransportePropio;
	}

	public void setTipoTransportePropio(String tipoTransportePropio) {
		this.tipoTransportePropio = tipoTransportePropio;
	}

	public String getTransporteAlquilado() {
		return transporteAlquilado;
	}

	public void setTransporteAlquilado(String transporteAlquilado) {
		this.transporteAlquilado = transporteAlquilado;
	}

	public String getTransportePropio() {
		return transportePropio;
	}

	public void setTransportePropio(String transportePropio) {
		this.transportePropio = transportePropio;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioTransaccion() {
		return usuarioTransaccion;
	}

	public void setUsuarioTransaccion(String usuarioTransaccion) {
		this.usuarioTransaccion = usuarioTransaccion;
	}

	public BigDecimal getIdUnidadProductiva() {
		return idUnidadProductiva;
	}

	public void setIdUnidadProductiva(BigDecimal idUnidadProductiva) {
		this.idUnidadProductiva = idUnidadProductiva;
	}

	public String getLugarVenta() {
		return lugarVenta;
	}

	public void setLugarVenta(String lugarVenta) {
		this.lugarVenta = lugarVenta;
	}

	public BigDecimal getAreaTotalGalpones() {
		return areaTotalGalpones;
	}

	public void setAreaTotalGalpones(BigDecimal areaTotalGalpones) {
		this.areaTotalGalpones = areaTotalGalpones;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public BigDecimal getNumGalponesPostura() {
		return numGalponesPostura;
	}

	public void setNumGalponesPostura(BigDecimal numGalponesPostura) {
		this.numGalponesPostura = numGalponesPostura;
	}

	public BigDecimal getAreaGalponesPostura() {
		return areaGalponesPostura;
	}

	public void setAreaGalponesPostura(BigDecimal areaGalponesPostura) {
		this.areaGalponesPostura = areaGalponesPostura;
	}

	public String getAsistenciaTecnica() {
		return asistenciaTecnica;
	}

	public void setAsistenciaTecnica(String asistenciaTecnica) {
		this.asistenciaTecnica = asistenciaTecnica;
	}
	
	

}
