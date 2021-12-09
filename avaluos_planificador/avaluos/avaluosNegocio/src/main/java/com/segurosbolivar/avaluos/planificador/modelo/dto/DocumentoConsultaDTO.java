package com.segurosbolivar.avaluos.planificador.modelo.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



import java.util.Date;

@XmlRootElement
public class DocumentoConsultaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long idDocumento;
	private String nombreDocumento;
	private Date fecha;
	private String path;

	public DocumentoConsultaDTO() {
	}

	@XmlElement(name="idDocumento")
	public long getIdDocumento() {
		return idDocumento;
	}

	@XmlElement(name="idDocumento")
	public void setIdDocumento(long idDocumento) {
		this.idDocumento = idDocumento;
	}

	@XmlElement(name="nombreDocumento")
	public String getNombreDocumento() {
		return nombreDocumento;
	}

	@XmlElement(name="nombreDocumento")
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	@XmlElement(name="fecha")
	public Date getFecha() {
		return fecha;
	}

	@XmlElement(name="fecha")
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@XmlElement(name="path")
	public String getPath() {
		return path;
	}

	@XmlElement(name="path")
	public void setPath(String path) {
		this.path = path;
	}

	
}