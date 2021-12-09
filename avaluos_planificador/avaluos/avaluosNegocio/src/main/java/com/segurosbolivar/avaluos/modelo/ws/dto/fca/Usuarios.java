package com.segurosbolivar.avaluos.modelo.ws.dto.fca;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Usuarios")
@XmlAccessorType(XmlAccessType.FIELD)
public class Usuarios {

	@XmlElement(name = "Usuario")
	private List<UsuarioAlterno> usuarios;

	public List<UsuarioAlterno> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioAlterno> usuarios) {
		this.usuarios = usuarios;
	}


}
