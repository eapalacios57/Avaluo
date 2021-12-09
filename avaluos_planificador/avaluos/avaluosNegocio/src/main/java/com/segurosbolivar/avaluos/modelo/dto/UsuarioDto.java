package com.segurosbolivar.avaluos.modelo.dto;

import java.io.Serializable;

import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Modulos;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Usuario;

/**
 * Contiene toda la informaci�n del usuario que se encuentra en sesi�n y al cual
 * se le realizara el seguimiento de las acciones que realice dentro del
 * aplicativo.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:47 a.m.
 */
public class UsuarioDto implements Serializable {

	private static final long serialVersionUID = -4378211381196554667L;
	private  Usuario usuario;
	private  Modulos perfil;

	public UsuarioDto(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Modulos getPerfil() {
		return perfil;
	}

	public void setPerfil(Modulos perfil) {
		this.perfil = perfil;
	}

}