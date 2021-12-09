package com.segurosbolivar.avaluos.modelo.dto;

import java.io.Serializable;
import java.util.Date;

public class ConsultaCargueMasivoDto implements Serializable{

	private static final long serialVersionUID = 8342860630358226934L;
	private String numeroBatch;
	private String numeroReferencia;
	private String nombreTxt;
	private Date fechaCargueDesde;
	private Date fechaCargueHasta;
	private String numeroIdPerito;
	
	
	public String getNumeroBatch() {
		return numeroBatch;
	}
	public void setNumeroBatch(String numeroBatch) {
		this.numeroBatch = numeroBatch;
	}
	public String getNumeroReferencia() {
		return numeroReferencia;
	}
	public void setNumeroReferencia(String numeroReferncia) {
		this.numeroReferencia = numeroReferncia;
	}
	public String getNombreTxt() {
		return nombreTxt;
	}
	public void setNombreTxt(String nombreTxt) {
		this.nombreTxt = nombreTxt;
	}
	
	public String getNumeroIdPerito() {
		return numeroIdPerito;
	}
	public void setNumeroIdPerito(String numeroIdPerito) {
		this.numeroIdPerito = numeroIdPerito;
	}
	public void setFechaCargueHasta(Date fechaCargue) {
		this.fechaCargueHasta = fechaCargue;
	}
	public Date getFechaCargueHasta() {
		return fechaCargueHasta;
	}
	public Date getFechaCargueDesde() {
		return fechaCargueDesde;
	}
	public void setFechaCargueDesde(Date fechaCargueDesde) {
		this.fechaCargueDesde = fechaCargueDesde;
	}
	
	
}
