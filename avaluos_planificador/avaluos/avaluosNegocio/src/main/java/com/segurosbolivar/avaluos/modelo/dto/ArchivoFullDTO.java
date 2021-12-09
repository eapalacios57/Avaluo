package com.segurosbolivar.avaluos.modelo.dto;

import java.util.Date;

public class ArchivoFullDTO {
	
	private Long idArchivo;

	//@Lob()
	private byte[] contenidoArchivo;

	private Date fechaCreacion;

	private Date fechaTransaccion;

	private String nombreArchivo;

	private Long tamanioArchivo;

	private Long tipoArchivo;

	private String usuarioCreacion;

	private String usuarioTransaccion;

	private String idDocumento;

	public Long getIdArchivo() {
		return idArchivo;
	}

	public void setIdArchivo(Long idArchivo) {
		this.idArchivo = idArchivo;
	}

	public byte[] getContenidoArchivo() {
		return contenidoArchivo;
	}

	public void setContenidoArchivo(byte[] contenidoArchivo) {
		this.contenidoArchivo = contenidoArchivo;
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

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public Long getTamanioArchivo() {
		return tamanioArchivo;
	}

	public void setTamanioArchivo(Long tamanioArchivo) {
		this.tamanioArchivo = tamanioArchivo;
	}

	public Long getTipoArchivo() {
		return tipoArchivo;
	}

	public void setTipoArchivo(Long tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
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

	public String getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}
	
}
