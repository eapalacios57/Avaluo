package com.asesoftware.util.mail.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author stilaguy
 * @version 1.0
 * @created 11-nov-2016 09:55:17 a.m.
 */
public class CorreoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String origen;
	private String asunto;
	private String mensaje;
	private List<String> destinatarios;
	private String copia;
	private String copiaOculta;
	private String archivoAdjunto;
	private String nombreArchivoAdjunto;
	private Long id;

	public CorreoDto() {

	}

	/***
	 * 
	 * @param asunto
	 * @param mensaje
	 * @param destinatarios
	 * @param archivoAdjunto
	 * @param idDocumento
	 */
	public CorreoDto(String asunto, String mensaje, List<String> destinatarios, String archivoAdjunto,
			Long id) {
		super();
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.destinatarios = destinatarios;
		this.archivoAdjunto = archivoAdjunto;
		this.id = id;
	}

	/*
	 * getters y setters
	 */

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<String> getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(List<String> destinatarios) {
		this.destinatarios = destinatarios;
	}

	public String getArchivoAdjunto() {
		return archivoAdjunto;
	}

	public void setArchivoAdjunto(String archivoAdjunto) {
		this.archivoAdjunto = archivoAdjunto;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getCopia() {
		return copia;
	}

	public void setCopia(String copia) {
		this.copia = copia;
	}

	public String getCopiaOculta() {
		return copiaOculta;
	}

	public void setCopiaOculta(String copiaOculta) {
		this.copiaOculta = copiaOculta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long idDocumento) {
		this.id = idDocumento;
	}

	public String getNombreArchivoAdjunto() {
		return nombreArchivoAdjunto;
	}

	public void setNombreArchivoAdjunto(String nombreArchivoAdjunto) {
		this.nombreArchivoAdjunto = nombreArchivoAdjunto;
	}

}