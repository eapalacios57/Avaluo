package com.segurosbolivar.avaluos.modelo.service;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Modulos;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Usuario;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.UsuarioAlterno;

/**
 * Provee los servicios del sistema referentes a la seguridad y el control de
 * accesos a traves de la integraci�n con el FCA.
 *
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:44 a.m.
 */
@Local
public interface ISeguridad extends Serializable{

	Modulos consultarPermisos(String numeroIdentificacion) throws NegocioException;

	UsuarioDto consultarUsuario(String usuario) throws NegocioException;

	void ingresarCapacitacionVirtual();

	public List<UsuarioAlterno> consultarUsuarios()throws NegocioException;
	
	public List<Usuario> consultarUsuariosDetallado()throws NegocioException,RemoteException;

}