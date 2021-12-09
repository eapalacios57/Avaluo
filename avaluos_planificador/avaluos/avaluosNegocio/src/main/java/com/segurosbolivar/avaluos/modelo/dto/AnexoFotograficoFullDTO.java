package com.segurosbolivar.avaluos.modelo.dto;

import java.util.Date;

public class AnexoFotograficoFullDTO {
	
	private Long idAnexoFotografico;

	private Long idAvaluo;

	private Long idImgFachada;

	private Long idDocAnexos;

	private Date fechaCreacion;

	private Date fechaTransaccion;

	private String usuarioCreacion;

	private String usuarioTransaccion;

	private ArchivoFullDTO archivoPdf;

	private ArchivoFullDTO archivoFoto;

	public Long getIdAnexoFotografico() {
		return idAnexoFotografico;
	}

	public void setIdAnexoFotografico(Long idAnexoFotografico) {
		this.idAnexoFotografico = idAnexoFotografico;
	}

	public Long getIdAvaluo() {
		return idAvaluo;
	}

	public void setIdAvaluo(Long idAvaluo) {
		this.idAvaluo = idAvaluo;
	}

	public Long getIdImgFachada() {
		return idImgFachada;
	}

	public void setIdImgFachada(Long idImgFachada) {
		this.idImgFachada = idImgFachada;
	}

	public Long getIdDocAnexos() {
		return idDocAnexos;
	}

	public void setIdDocAnexos(Long idDocAnexos) {
		this.idDocAnexos = idDocAnexos;
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

	public ArchivoFullDTO getArchivoPdf() {
		return archivoPdf;
	}

	public void setArchivoPdf(ArchivoFullDTO archivoPdf) {
		this.archivoPdf = archivoPdf;
	}

	public ArchivoFullDTO getArchivoFoto() {
		return archivoFoto;
	}

	public void setArchivoFoto(ArchivoFullDTO archivoFoto) {
		this.archivoFoto = archivoFoto;
	}	

}
