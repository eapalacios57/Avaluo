package com.segurosbolivar.avaluos.modelo.ws.dto.fca;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Perfil")
@XmlAccessorType(XmlAccessType.FIELD)
public class Perfil {

	@XmlAttribute(name = "codigoPerfil")
	private String codigoPerfil;
	@XmlAttribute(name = "nombrePerfil")
	private String nombrePerfil;

	public Perfil(String codigoPerfil, String nombrePerfil) {
		super();
		this.codigoPerfil = codigoPerfil;
		this.nombrePerfil = nombrePerfil;
	}

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

}
