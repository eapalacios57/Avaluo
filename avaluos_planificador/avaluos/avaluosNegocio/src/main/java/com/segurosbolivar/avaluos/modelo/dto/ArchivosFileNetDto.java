package com.segurosbolivar.avaluos.modelo.dto;

import java.io.Serializable;

import com.segurosbolivar.avaluos.modelo.entity.ListaAnexosPdf;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Usuario;

/**
 * Objeto que contiene integrado los atributos necesarios para el envio de
 * archivos a FileNet
 * 
 * @author arincon
 *
 */
public class ArchivosFileNetDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private ListaAnexosPdf listaAnexo;
	private UsuarioDto Usuario;

	public ArchivosFileNetDto() {
		super();
	}

	public ArchivosFileNetDto(ListaAnexosPdf listaAnexo, String IdentificacionPerito, String NombrePerito) {
		this.listaAnexo = listaAnexo;
		this.Usuario = crearUsuarioPerito(IdentificacionPerito, NombrePerito);
	}

	public ListaAnexosPdf getListaAnexo() {
		return listaAnexo;
	}

	public void setListaAnexo(ListaAnexosPdf listaAnexo) {
		this.listaAnexo = listaAnexo;
	}

	public UsuarioDto getUsuario() {
		return Usuario;
	}

	public void setUsuario(UsuarioDto usuario) {
		Usuario = usuario;
	}

	/**
	 * Permite contruir un objeto del atributo a partir de la informacion basica del perito
	 * @param IdentificacionPerito
	 * @param NombrePerito
	 * @return Usuario Perito 
	 * @author arincon
	 */
	private UsuarioDto crearUsuarioPerito(String IdentificacionPerito, String NombrePerito) {
		Usuario _Usuario = new Usuario();
		_Usuario.setNombres(NombrePerito);
		_Usuario.setCodigo(IdentificacionPerito);
		return new UsuarioDto(_Usuario);
	}

}
