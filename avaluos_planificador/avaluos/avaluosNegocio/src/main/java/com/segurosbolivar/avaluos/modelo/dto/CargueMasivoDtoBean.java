package com.segurosbolivar.avaluos.modelo.dto;

import java.io.InputStream;
import java.util.Date;

public class CargueMasivoDtoBean {
	
	private String nombreTxt;
	private String nombreZip;
	private InputStream archivoTxt;
	private InputStream archivoZip;
	private Date fechaCargueTxt;
	private Date fechaCargueZip;
	private String estado;
	
	
	
	public String getNombreTxt() {
		return nombreTxt;
	}
	public void setNombreTxt(String nombreTxt) {
		this.nombreTxt = nombreTxt;
	}
	public String getNombreZip() {
		return nombreZip;
	}
	public void setNombreZip(String nombreZip) {
		this.nombreZip = nombreZip;
	}
	public InputStream getArchivoTxt() {
		return archivoTxt;
	}
	public void setArchivoTxt(InputStream archivoTxt) {
		this.archivoTxt = archivoTxt;
	}
	public InputStream getArchivoZip() {
		return archivoZip;
	}
	public void setArchivoZip(InputStream archivoZip) {
		this.archivoZip = archivoZip;
	}
	public Date getFechaCargueTxt() {
		return fechaCargueTxt;
	}
	public void setFechaCargueTxt(Date fechaCargueTxt) {
		this.fechaCargueTxt = fechaCargueTxt;
	}
	public Date getFechaCargueZip() {
		return fechaCargueZip;
	}
	public void setFechaCargueZip(Date fechaCargueZip) {
		this.fechaCargueZip = fechaCargueZip;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
