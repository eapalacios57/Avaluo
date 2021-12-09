package com.segurosbolivar.avaluos.modelo.facade.impl;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.Departamento;
import com.segurosbolivar.avaluos.modelo.entity.EmpresasAvaluos;
import com.segurosbolivar.avaluos.modelo.entity.PeritosEmpresa;
import com.segurosbolivar.avaluos.modelo.facade.IPeritoFacade;
import com.segurosbolivar.avaluos.modelo.service.IArchivo;
import com.segurosbolivar.avaluos.modelo.service.IEmpresaAvaluadora;
import com.segurosbolivar.avaluos.modelo.service.IParametrizacion;
import com.segurosbolivar.avaluos.modelo.service.IPerito;
import com.segurosbolivar.avaluos.modelo.service.ISeguridad;

/**
 * Implementaci�n de la fachada para la administraci�n de los peritos y las
 * empresas avaluadoras.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:46 a.m.
 */
@Stateless
public class PeritoFacadeImpl implements IPeritoFacade {

	private static final long serialVersionUID = -9218473759649283990L;

	@EJB
	public IPerito peritoSvc;
	@EJB
	public IEmpresaAvaluadora empresaAvaluadoraSvc;
	@EJB
	public ISeguridad seguridadSvc;
	@EJB
	public IParametrizacion parametrizacionSvc;
	@EJB
	private IArchivo archivoSvc;

	@Override
	public int cantidadRegistros(EmpresasAvaluos empresasAvaluos) {
		return empresaAvaluadoraSvc.cantidadRegistros(empresasAvaluos);
	}

	@Override
	public int cantidadRegistros(PeritosEmpresa peritosEmpresa) {
		return peritoSvc.cantidadRegistros(peritosEmpresa);
	}

	@Override
	public List<EmpresasAvaluos> consultar(EmpresasAvaluos consulta, int inicio, int registrosXPagina,
			String campoOrden, SentidoOrientacion sentido) {
		return empresaAvaluadoraSvc.consultar(consulta, inicio, registrosXPagina, campoOrden, sentido);
	}

	@Override
	public List<PeritosEmpresa> consultar(PeritosEmpresa consulta, int inicio, int registrosXPagina,String campoOrden, SentidoOrientacion sentido) {
		return peritoSvc.consultar(consulta, inicio, registrosXPagina, campoOrden, sentido);
	}

	@Override
	public void guardarEmpresa(EmpresasAvaluos empresa, UsuarioDto usuario) throws NegocioException {
		empresaAvaluadoraSvc.guardar(empresa, usuario);
	}

	@Override
	public void guardarPerito(PeritosEmpresa perito, UsuarioDto usuario) throws NegocioException {
		peritoSvc.guardar(perito, usuario);
	}

	@Override
	public void desactivarPerito(List<PeritosEmpresa> peritos, UsuarioDto usuario) throws NegocioException {
		peritoSvc.desactivar(peritos, usuario);
	}

	@Override
	public void eliminarEmpresa(EmpresasAvaluos empresa) throws NegocioException {
		empresaAvaluadoraSvc.eliminar(empresa);

	}

	@Override
	public String buscarNombrePerito(Long valor) throws NegocioException {
		if (valor == null)
			return null;
		UsuarioDto usuario = seguridadSvc.consultarUsuario(valor.toString());
		if (usuario == null || usuario.getUsuario() == null)
			return null;
		return usuario.getUsuario().getNombres();
	}

	@Override
	public List<Departamento> consultarDepartamentos() throws NegocioException {
		return parametrizacionSvc.consultarDepartamentos(new Departamento(), 0, Integer.MAX_VALUE, null, null);
	}

	@Override
	public byte[] obtenerImagen(String idDocumento) throws NegocioException, IOException {
		return archivoSvc.obtenerDocumento(idDocumento);
	}

}