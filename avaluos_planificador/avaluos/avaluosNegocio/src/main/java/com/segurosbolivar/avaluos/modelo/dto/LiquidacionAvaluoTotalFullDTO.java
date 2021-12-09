package com.segurosbolivar.avaluos.modelo.dto;

import java.math.BigDecimal;
import java.util.Date;

public class LiquidacionAvaluoTotalFullDTO {

	private Long idLiqavaluoTotal;

	private Date fechaCreacion;

	private Date fechaTransaccion;

	private BigDecimal avaluoUvr;

	private BigDecimal totalAvaluo;

	private BigDecimal valorAsegurable;

	private BigDecimal valorUvrDia;

	private Long calificacion;

	private BigDecimal numCostoTransaccion;

	private String usuarioCreacion;

	private String usuarioTransaccion;

	private Long idAvaluo;

	public Long getIdLiqavaluoTotal() {
		return idLiqavaluoTotal;
	}

	public void setIdLiqavaluoTotal(Long idLiqavaluoTotal) {
		this.idLiqavaluoTotal = idLiqavaluoTotal;
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

	public BigDecimal getAvaluoUvr() {
		return avaluoUvr;
	}

	public void setAvaluoUvr(BigDecimal avaluoUvr) {
		this.avaluoUvr = avaluoUvr;
	}

	public BigDecimal getTotalAvaluo() {
		return totalAvaluo;
	}

	public void setTotalAvaluo(BigDecimal totalAvaluo) {
		this.totalAvaluo = totalAvaluo;
	}

	public BigDecimal getValorAsegurable() {
		return valorAsegurable;
	}

	public void setValorAsegurable(BigDecimal valorAsegurable) {
		this.valorAsegurable = valorAsegurable;
	}

	public BigDecimal getValorUvrDia() {
		return valorUvrDia;
	}

	public void setValorUvrDia(BigDecimal valorUvrDia) {
		this.valorUvrDia = valorUvrDia;
	}

	public Long getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Long calificacion) {
		this.calificacion = calificacion;
	}

	public BigDecimal getNumCostoTransaccion() {
		return numCostoTransaccion;
	}

	public void setNumCostoTransaccion(BigDecimal numCostoTransaccion) {
		this.numCostoTransaccion = numCostoTransaccion;
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

	public Long getIdAvaluo() {
		return idAvaluo;
	}

	public void setIdAvaluo(Long idAvaluo) {
		this.idAvaluo = idAvaluo;
	}
	
}
