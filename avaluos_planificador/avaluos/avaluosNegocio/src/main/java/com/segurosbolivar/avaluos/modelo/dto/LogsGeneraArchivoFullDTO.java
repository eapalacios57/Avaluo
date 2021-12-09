package com.segurosbolivar.avaluos.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class LogsGeneraArchivoFullDTO implements Serializable {

	private static final long serialVersionUID = -255604173421948139L;

	private BigDecimal idLogArchivos;

	private BigDecimal codigoError;

	private String descripcionError;

	private String enviado;

	private String estado;

	private Date fechaDesde;

	private Date fechaFinCreacion;

	private Date fechaHasta;

	private Date fechaInicioCreacion;

	private String linkDescarga;

	private String nombreArchivo;

	private String usuarioCreacion;

	private String nombrePlano;

	private String usuarioTransaccion;

	private Date fechaTransaccion;
	
	private List<AvaluoSDTO> avaluos;

	public BigDecimal getIdLogArchivos() {
		return idLogArchivos;
	}

	public void setIdLogArchivos(BigDecimal idLogArchivos) {
		this.idLogArchivos = idLogArchivos;
	}

	public BigDecimal getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(BigDecimal codigoError) {
		this.codigoError = codigoError;
	}

	public String getDescripcionError() {
		return descripcionError;
	}

	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}

	public String getEnviado() {
		return enviado;
	}

	public void setEnviado(String enviado) {
		this.enviado = enviado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaFinCreacion() {
		return fechaFinCreacion;
	}

	public void setFechaFinCreacion(Date fechaFinCreacion) {
		this.fechaFinCreacion = fechaFinCreacion;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Date getFechaInicioCreacion() {
		return fechaInicioCreacion;
	}

	public void setFechaInicioCreacion(Date fechaInicioCreacion) {
		this.fechaInicioCreacion = fechaInicioCreacion;
	}

	public String getLinkDescarga() {
		return linkDescarga;
	}

	public void setLinkDescarga(String linkDescarga) {
		this.linkDescarga = linkDescarga;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getNombrePlano() {
		return nombrePlano;
	}

	public void setNombrePlano(String nombrePlano) {
		this.nombrePlano = nombrePlano;
	}

	public String getUsuarioTransaccion() {
		return usuarioTransaccion;
	}

	public void setUsuarioTransaccion(String usuarioTransaccion) {
		this.usuarioTransaccion = usuarioTransaccion;
	}

	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public List<AvaluoSDTO> getAvaluos() {
		return avaluos;
	}

	public void setAvaluos(List<AvaluoSDTO> avaluos) {
		this.avaluos = avaluos;
	}

}
