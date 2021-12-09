package com.segurosbolivar.avaluos.planificador.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.segurosbolivar.avaluos.planificador.modelo.entity.Unidad;

public class ActividadGanaderaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1420548116378583664L;

	private BigDecimal idActividadGanadera;

	private BigDecimal cantidadPotreros;

	private BigDecimal ciclosProducccionAnio;

	private String equiposDisponibles;

	private Date fechaCreacion;

	private Date fechaTransaccion;

	private String infraestructura;

	private String lugarVenta;

	private String razaCruce;

	private String tipoPradera;

	private String tipoTransportePropio;

	private String transporteAlquilado;

	private String transportePropio;

	private String usuarioCreacion;

	private String usuarioTransaccion;

	private BigDecimal idUnidadProductiva;

	private BigDecimal areaActividad;

	private BigDecimal areaActividadHa;

	private BigDecimal areaPraderaMejorada;

	private BigDecimal areaPraderaMejoradaHa;

	private Unidad areaPraderaMejoradaUnidad;

	private Unidad areaActividadUnidad;

	private String asistenciaTecnica;
	
	private String comentarios;

	public ActividadGanaderaDTO() {
	}

	public BigDecimal getIdActividadGanadera() {
		return idActividadGanadera;
	}

	public void setIdActividadGanadera(BigDecimal idActividadGanadera) {
		this.idActividadGanadera = idActividadGanadera;
	}

	public BigDecimal getCantidadPotreros() {
		return cantidadPotreros;
	}

	public void setCantidadPotreros(BigDecimal cantidadPotreros) {
		this.cantidadPotreros = cantidadPotreros;
	}

	public BigDecimal getCiclosProducccionAnio() {
		return ciclosProducccionAnio;
	}

	public void setCiclosProducccionAnio(BigDecimal ciclosProducccionAnio) {
		this.ciclosProducccionAnio = ciclosProducccionAnio;
	}

	public String getEquiposDisponibles() {
		return equiposDisponibles;
	}

	public void setEquiposDisponibles(String equiposDisponibles) {
		this.equiposDisponibles = equiposDisponibles;
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

	public String getLugarVenta() {
		return lugarVenta;
	}

	public void setLugarVenta(String lugarVenta) {
		this.lugarVenta = lugarVenta;
	}

	public String getRazaCruce() {
		return razaCruce;
	}

	public void setRazaCruce(String razaCruce) {
		this.razaCruce = razaCruce;
	}

	public String getTipoPradera() {
		return tipoPradera;
	}

	public void setTipoPradera(String tipoPradera) {
		this.tipoPradera = tipoPradera;
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

	public BigDecimal getAreaActividad() {
		return areaActividad;
	}

	public void setAreaActividad(BigDecimal areaActividad) {
		this.areaActividad = areaActividad;
	}

	public BigDecimal getAreaActividadHa() {
		return areaActividadHa;
	}

	public void setAreaActividadHa(BigDecimal areaActividadHa) {
		this.areaActividadHa = areaActividadHa;
	}

	public BigDecimal getAreaPraderaMejorada() {
		return areaPraderaMejorada;
	}

	public void setAreaPraderaMejorada(BigDecimal areaPraderaMejorada) {
		this.areaPraderaMejorada = areaPraderaMejorada;
	}

	public BigDecimal getAreaPraderaMejoradaHa() {
		return areaPraderaMejoradaHa;
	}

	public void setAreaPraderaMejoradaHa(BigDecimal areaPraderaMejoradaHa) {
		this.areaPraderaMejoradaHa = areaPraderaMejoradaHa;
	}

	public Unidad getAreaPraderaMejoradaUnidad() {
		return areaPraderaMejoradaUnidad;
	}

	public void setAreaPraderaMejoradaUnidad(Unidad areaPraderaMejoradaUnidad) {
		this.areaPraderaMejoradaUnidad = areaPraderaMejoradaUnidad;
	}

	public Unidad getAreaActividadUnidad() {
		return areaActividadUnidad;
	}

	public void setAreaActividadUnidad(Unidad areaActividadUnidad) {
		this.areaActividadUnidad = areaActividadUnidad;
	}

	public String getAsistenciaTecnica() {
		return asistenciaTecnica;
	}

	public void setAsistenciaTecnica(String asistenciaTecnica) {
		this.asistenciaTecnica = asistenciaTecnica;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

}