package com.segurosbolivar.avaluos.modelo.service.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jfree.util.Log;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.cons.Paises;
import com.segurosbolivar.avaluos.modelo.data.ParametrizacionDao;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.Parametrizacion;
import com.segurosbolivar.avaluos.modelo.facade.IIntegradorFacade;
import com.segurosbolivar.avaluos.modelo.service.ISeguridad;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Modulos;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Usuario;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.UsuarioAlterno;

/**
 * Implementaci�n del servicio de seguridad.
 *
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:47 a.m.
 */
@Stateless
public class SeguridadImpl implements ISeguridad {

	private static final long serialVersionUID = 4199785387590866662L;
	

	@EJB
	private ManejadorErroresNegocio mgrExc;

	@EJB
	private IIntegradorFacade integradorSvc;
	
	@EJB
	private ParametrizacionDao parametrizacionDao;

	@Override
	public Modulos consultarPermisos(String numeroIdentificacion) throws NegocioException {
		try {
			Parametrizacion parametroCodAplicacion = parametrizacionDao.getParametro(UtilConstantes.TIPO_PARAMETRO_WS_FCA, UtilConstantes.NOMBRE_PARAMETRO_COD_APP_AVALUOS);
			int idAplicacion = Integer.parseInt(parametroCodAplicacion.getValorparametro());
			return integradorSvc.obtenerMenuUsuarioAplicacion(numeroIdentificacion,idAplicacion , Paises.COLOMBIA.getValor());
		} catch (Exception e) {
			throw mgrExc.lanzarExcepcion(4, TipoErrorNegocio.FATAL);
		}

	}

	@Override
	public UsuarioDto consultarUsuario(String idUsuario) throws NegocioException {
		try {
			Log.info("Se llama a obtenerUsuarioPorAplicativo para el id de usuario: "+idUsuario);

			Parametrizacion parametroCodAplicacion = parametrizacionDao.getParametro(UtilConstantes.TIPO_PARAMETRO_WS_FCA, UtilConstantes.NOMBRE_PARAMETRO_COD_APP_AVALUOS);
			
			int idAplicacion = Integer.parseInt(parametroCodAplicacion.getValorparametro()); 
			
			Usuario usuario = integradorSvc.obtenerUsuarioPorAplicativo(idUsuario, idAplicacion);
			if (usuario == null || UtilTexto.estaVacio(usuario.getCodigo()) || UtilTexto.estaVacio(usuario.getCodigoPerfil())) {
				Log.info("El usuario esta vacio error");
				throw mgrExc.lanzarExcepcion(7, TipoErrorNegocio.INFO);
			}
			UsuarioDto retornar = new UsuarioDto(usuario);
			retornar.setPerfil(consultarPermisos(idUsuario));
			Log.info("Validacion exitosa de consulta de usuario");
			return retornar;
		} catch (NegocioException e) {
			throw e;
		} catch (Exception e) {
			throw mgrExc.lanzarExcepcion(6, TipoErrorNegocio.FATAL);
		}
	}

	@Override
	public void ingresarCapacitacionVirtual() {

	}

	@Override
	public List<UsuarioAlterno> consultarUsuarios() throws NegocioException {
		Parametrizacion parametroCodAplicacion = parametrizacionDao.getParametro(UtilConstantes.TIPO_PARAMETRO_WS_FCA, UtilConstantes.NOMBRE_PARAMETRO_COD_APP_AVALUOS);
		
		int codigoAplicacion = Integer.parseInt(parametroCodAplicacion.getValorparametro());
		List<UsuarioAlterno> listaUsuarios = integradorSvc.obtenerUsuariosPorAplicativo(codigoAplicacion).getUsuarios();
		if (listaUsuarios == null) {
			listaUsuarios = new ArrayList<>();
		}
		return listaUsuarios;
	}

	@Override
	public List<Usuario> consultarUsuariosDetallado() throws NegocioException, RemoteException {
		Parametrizacion parametroCodAplicacion = parametrizacionDao.getParametro(UtilConstantes.TIPO_PARAMETRO_WS_FCA, UtilConstantes.NOMBRE_PARAMETRO_COD_APP_AVALUOS);
		
		int codigoAplicacion = Integer.parseInt(parametroCodAplicacion.getValorparametro());
		List<UsuarioAlterno> listaUsuarios = integradorSvc.obtenerUsuariosPorAplicativo(codigoAplicacion).getUsuarios();
		if(listaUsuarios == null || listaUsuarios.isEmpty())
			return Collections.emptyList();
		listaUsuarios=limpiarLista(listaUsuarios);
		List<Usuario> usuarios = new ArrayList<>();
		for(UsuarioAlterno usuarioAlterno: listaUsuarios) {
			Usuario usuario = integradorSvc.obtenerUsuarioPorAplicativo(usuarioAlterno.getCedula(), codigoAplicacion);		
			usuarios.add(usuario);
		}
		return usuarios;
	}
	
	private List<UsuarioAlterno> limpiarLista( List<UsuarioAlterno> list){
		List<UsuarioAlterno> nuevaLista = new ArrayList<UsuarioAlterno>();
		for(UsuarioAlterno usuario:list) {
			if(usuario.getCedula().isEmpty() || usuario.getCedula()==null || usuario.getCedula()=="") {
				
			}else {
				nuevaLista.add(usuario);
			}
		}
		return nuevaLista;
	}
	

}