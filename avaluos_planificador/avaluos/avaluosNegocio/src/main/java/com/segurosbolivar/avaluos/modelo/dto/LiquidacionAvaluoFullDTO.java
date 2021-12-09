package com.segurosbolivar.avaluos.modelo.dto;

import java.math.BigDecimal;
import java.util.Date;

public class LiquidacionAvaluoFullDTO {
	
	private Long idLiqavaluo;

	private String descripcionDependencia;

	private Long descripcionLiquidacion;

	private Date fechaCreacion;

	private Date fechaTransaccion;

	private BigDecimal areaLiquidacion;

	private BigDecimal valor;

	private BigDecimal valorTotal;

	private String usuarioCreacion;

	private String usuarioTransaccion;

	private Long consecutivo;

	private Long idAvaluo;

	public Long getIdLiqavaluo() {
		return idLiqavaluo;
	}

	public void setIdLiqavaluo(Long idLiqavaluo) {
		this.idLiqavaluo = idLiqavaluo;
	}

	public String getDescripcionDependencia() {
		return descripcionDependencia;
	}

	public void setDescripcionDependencia(String descripcionDependencia) {
		this.descripcionDependencia = descripcionDependencia;
	}

	public Long getDescripcionLiquidacion() {
		return descripcionLiquidacion;
	}

	public void setDescripcionLiquidacion(Long descripcionLiquidacion) {
		this.descripcionLiquidacion = descripcionLiquidacion;
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

	public BigDecimal getAreaLiquidacion() {
		return areaLiquidacion;
	}

	public void setAreaLiquidacion(BigDecimal areaLiquidacion) {
		this.areaLiquidacion = areaLiquidacion;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
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

	public Long getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	public Long getIdAvaluo() {
		return idAvaluo;
	}

	public void setIdAvaluo(Long idAvaluo) {
		this.idAvaluo = idAvaluo;
	}

}
