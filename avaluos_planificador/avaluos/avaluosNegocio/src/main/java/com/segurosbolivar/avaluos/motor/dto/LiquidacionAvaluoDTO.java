package com.segurosbolivar.avaluos.motor.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class LiquidacionAvaluoDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String	descripcionLiquidacion;
	private String	descripcionDependencia;
	private BigDecimal	area;
	private BigDecimal	valorUnitario;
	private BigDecimal	valorTotal;
	private String	garantia;
	private String	tipoVivienda;
	private String	Metodologia;
	
	public LiquidacionAvaluoDTO() {}
	

	public String getDescripcionLiquidacion() {
		return descripcionLiquidacion;
	}

	public void setDescripcionLiquidacion(String descripcionLiquidacion) {
		this.descripcionLiquidacion = descripcionLiquidacion;
	}

	public String getDescripcionDependencia() {
		return descripcionDependencia;
	}

	public void setDescripcionDependencia(String descripcionDependencia) {
		this.descripcionDependencia = descripcionDependencia;
	}

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getGarantia() {
		return garantia;
	}

	public void setGarantia(String garantia) {
		this.garantia = garantia;
	}

	public String getTipoVivienda() {
		return tipoVivienda;
	}

	public void setTipoVivienda(String tipoVivienda) {
		this.tipoVivienda = tipoVivienda;
	}

	public String getMetodologia() {
		return Metodologia;
	}

	public void setMetodologia(String metodologia) {
		Metodologia = metodologia;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


}
