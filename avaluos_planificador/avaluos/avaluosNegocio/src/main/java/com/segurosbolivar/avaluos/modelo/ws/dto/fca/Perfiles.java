package com.segurosbolivar.avaluos.modelo.ws.dto.fca;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Perfil;

@XmlRootElement(name = "Perfiles")
@XmlAccessorType(XmlAccessType.FIELD)
public class Perfiles {

	@XmlElement(name = "Perfil")
	private List<Perfil> perfil;

	public Perfiles(List<Perfil> perfil) {
		super();
		this.perfil = perfil;
	}

	public Perfiles() {
	}

	public List<Perfil> getPerfile() {
		return perfil;
	}

	public void setPerfil(List<Perfil> perfil) {
		this.perfil = perfil;
	}

}
