package com.segurosbolivar.avaluos.planificador.modelo.dto;

import java.io.Serializable;


import com.segurosbolivar.avaluos.planificador.modelo.entity.Solicitud;

import java.util.Date;

public class DocumentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long idDocumento;

	private Date fecha;
	
	private String fechaStr;

	private Date fechaCreacion;
	
	private String fechaCreacionStr;

	private String path;

	private String tipoDocumento;

	private String usuarioCreacion;

	private Solicitud solicitud;
	
	private String nombreDocumento;

	private byte[] contenidoArchivo;

	public DocumentoDTO() {
	}

	
	public byte[] getContenidoArchivo() {
		return contenidoArchivo;
	}

	public void setContenidoArchivo(byte[] contenidoArchivo) {
		this.contenidoArchivo = contenidoArchivo;
	}
	
	public String getNombreDocumento() {
		return nombreDocumento;
	}

	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	public long getIdDocumento() {
		return this.idDocumento;
	}

	public void setIdDocumento(long idDocumento) {
		this.idDocumento = idDocumento;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Solicitud getSolicitud() {
		return this.solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}


	public String getFechaCreacionStr() {
		return fechaCreacionStr;
	}


	public void setFechaCreacionStr(String fechaCreacionStr) {
		this.fechaCreacionStr = fechaCreacionStr;
	}


	public String getFechaStr() {
		return fechaStr;
	}


	public void setFechaStr(String fechaStr) {
		this.fechaStr = fechaStr;
	}		

}