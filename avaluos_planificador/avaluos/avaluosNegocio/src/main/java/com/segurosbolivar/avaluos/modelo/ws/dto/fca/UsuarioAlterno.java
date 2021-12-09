package com.segurosbolivar.avaluos.modelo.ws.dto.fca;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Usuario")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsuarioAlterno {

	@XmlAttribute(name="codigoPerfil")
	private String codigoPerfil;
	@XmlAttribute(name="nombrePerfil")
	private String nombrePerfil;
	@XmlAttribute(name="cedula")
	private String cedula;
	@XmlAttribute(name="nombres")
	private String nombres;
	@XmlAttribute(name="Email")
	private String email;

	public String getCodigoPerfil() {
		return codigoPerfil;
	}
	public void setCodigoPerfil(String codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}
	public String getNombrePerfil() {
		return nombrePerfil;
	}
	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
