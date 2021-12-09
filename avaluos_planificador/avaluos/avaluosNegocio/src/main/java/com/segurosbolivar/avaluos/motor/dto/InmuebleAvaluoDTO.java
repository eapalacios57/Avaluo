package com.segurosbolivar.avaluos.motor.dto;

import java.io.Serializable;
import java.util.Date;

public class InmuebleAvaluoDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String	uso;
	private String	otroUso;
	private String	clase;
	private String	otraClase;
	private String	categoria;
	private String	ubicacion;
	private String	construidoUsoActual;
	private String	notaria;
	private String	numeroEscritura;
	private String	departamentoEscritura;
	private String	ciudadEscritura;
	private Date	fechaEscritura;
	private String	matriculaInmobiliaria;
	private String	matrículaInmobiliariaGaraje;
	private String	matrículaInmobiliariaDeposito;
	private String	chip;
	private String	numeroCatastral;
	
	
	public InmuebleAvaluoDTO() {
	
	}


	public String getUso() {
		return uso;
	}


	public void setUso(String uso) {
		this.uso = uso;
	}


	public String getOtroUso() {
		return otroUso;
	}


	public void setOtroUso(String otroUso) {
		this.otroUso = otroUso;
	}


	public String getClase() {
		return clase;
	}


	public void setClase(String clase) {
		this.clase = clase;
	}


	public String getOtraClase() {
		return otraClase;
	}


	public void setOtraClase(String otraClase) {
		this.otraClase = otraClase;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public String getUbicacion() {
		return ubicacion;
	}


	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}


	public String getConstruidoUsoActual() {
		return construidoUsoActual;
	}


	public void setConstruidoUsoActual(String construidoUsoActual) {
		this.construidoUsoActual = construidoUsoActual;
	}


	public String getNotaria() {
		return notaria;
	}


	public void setNotaria(String notaria) {
		this.notaria = notaria;
	}


	public String getNumeroEscritura() {
		return numeroEscritura;
	}


	public void setNumeroEscritura(String string) {
		this.numeroEscritura = string;
	}


	public String getDepartamentoEscritura() {
		return departamentoEscritura;
	}


	public void setDepartamentoEscritura(String departamentoEscritura) {
		this.departamentoEscritura = departamentoEscritura;
	}


	public String getCiudadEscritura() {
		return ciudadEscritura;
	}


	public void setCiudadEscritura(String ciudadEscritura) {
		this.ciudadEscritura = ciudadEscritura;
	}


	public Date getFechaEscritura() {
		return fechaEscritura;
	}


	public void setFechaEscritura(Date fechaEscritura) {
		this.fechaEscritura = fechaEscritura;
	}


	public String getMatriculaInmobiliaria() {
		return matriculaInmobiliaria;
	}


	public void setMatriculaInmobiliaria(String matriculaInmobiliaria) {
		this.matriculaInmobiliaria = matriculaInmobiliaria;
	}


	public String getMatrículaInmobiliariaGaraje() {
		return matrículaInmobiliariaGaraje;
	}


	public void setMatrículaInmobiliariaGaraje(String matrículaInmobiliariaGaraje) {
		this.matrículaInmobiliariaGaraje = matrículaInmobiliariaGaraje;
	}


	public String getMatrículaInmobiliariaDeposito() {
		return matrículaInmobiliariaDeposito;
	}


	public void setMatrículaInmobiliariaDeposito(String matrículaInmobiliariaDeposito) {
		this.matrículaInmobiliariaDeposito = matrículaInmobiliariaDeposito;
	}


	public String getChip() {
		return chip;
	}


	public void setChip(String chip) {
		this.chip = chip;
	}


	public String getNumeroCatastral() {
		return numeroCatastral;
	}


	public void setNumeroCatastral(String numeroCatastral) {
		this.numeroCatastral = numeroCatastral;
	}
	
	
}
