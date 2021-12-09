package com.segurosbolivar.avaluos.modelo.dto;

import java.io.Serializable;

public class ResultadoCargueMasivoDto implements Serializable {

	private static final long serialVersionUID = 8799393206142429280L;

	private long consecutivoTmp;
	private String consecutivoBatch;
	private String numeroRefCargue;
	private String nombreArchivo;
	private String fechaTransaccion;
	private String horaTransaccion;
	private String tipoCargue;
	private int total;
	private int rechazados;
	private int sinEstado;
	private int aplicados;
	private String usuarioTransaccion;
	private String empresaAvaluos;

	public String getConsecutivoBatch() {
		return consecutivoBatch;
	}

	public void setConsecutivoBatch(String consecutivoBatch) {
		this.consecutivoBatch = consecutivoBatch;
	}

	public String getNumeroRefCargue() {
		return numeroRefCargue;
	}

	public void setNumeroRefCargue(String numeroRefCargue) {
		this.numeroRefCargue = numeroRefCargue;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(String fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public String getHoraTransaccion() {
		return horaTransaccion;
	}

	public void setHoraTransaccion(String horaTransaccion) {
		this.horaTransaccion = horaTransaccion;
	}

	public String getTipoCargue() {
		return tipoCargue;
	}

	public void setTipoCargue(String tipoCargue) {
		this.tipoCargue = tipoCargue;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRechazados() {
		return rechazados;
	}

	public void setRechazados(int rechazados) {
		this.rechazados = rechazados;
	}

	public int getSinEstado() {
		return sinEstado;
	}

	public void setSinEstado(int sinEstado) {
		this.sinEstado = sinEstado;
	}

	public int getAplicados() {
		return aplicados;
	}

	public void setAplicados(int aplicados) {
		this.aplicados = aplicados;
	}

	public String getUsuarioTransaccion() {
		return usuarioTransaccion;
	}

	public void setUsuarioTransaccion(String usuarioTransaccion) {
		this.usuarioTransaccion = usuarioTransaccion;
	}

	public String getEmpresaAvaluos() {
		return empresaAvaluos;
	}

	public void setEmpresaAvaluos(String empresaAvaluos) {
		this.empresaAvaluos = empresaAvaluos;
	}

	public long getConsecutivoTmp() {
		return consecutivoTmp;
	}

	public void setConsecutivoTmp(long consecutivoTmp) {
		this.consecutivoTmp = consecutivoTmp;
	}

}
