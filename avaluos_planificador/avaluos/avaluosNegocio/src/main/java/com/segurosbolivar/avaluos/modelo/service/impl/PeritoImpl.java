package com.segurosbolivar.avaluos.modelo.service.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.cons.EstadoRegistro;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.data.PeritoDao;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.PeritosEmpresa;
import com.segurosbolivar.avaluos.modelo.service.IPerito;

/**
 * Implementaci�n del servicio de perito
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:46 a.m.
 */
@Stateless
public class PeritoImpl implements IPerito {

	private static final long serialVersionUID = -5482608796155848998L;

	@EJB
	private ManejadorErroresNegocio mgrExc;

	@EJB
	private PeritoDao peritoDao;

	@Override
	public int cantidadRegistros(PeritosEmpresa peritosEmpresa) {
		return peritoDao.cantidadRegistros(peritosEmpresa);
	}

	@Override
	public List<PeritosEmpresa> consultar(PeritosEmpresa consultar, int inicio, int registrosXPagina, String campoOrden,
			SentidoOrientacion sentido) {
		return peritoDao.consultar(consultar, inicio, registrosXPagina, campoOrden, sentido);
	}

	@Override
	public PeritosEmpresa consultarPeritoDocumento(Long numeroDocumento) throws NegocioException {
		if (numeroDocumento == null || numeroDocumento.compareTo(0L) == 0)
			mgrExc.lanzarExcepcion(81L, TipoErrorNegocio.ERROR);
		PeritosEmpresa consultar = new PeritosEmpresa();
		consultar.setNumeroDocumento(numeroDocumento);
		List<PeritosEmpresa> resultado = peritoDao.consultar(consultar, 0, 2, null, null);
		if (resultado == null || resultado.isEmpty())
			return null;
		if (resultado.size() > 1)
			mgrExc.lanzarExcepcion(82L, TipoErrorNegocio.ERROR);
		return resultado.get(0);
	}

	@Override
	public void guardar(PeritosEmpresa perito, UsuarioDto usuario) throws NegocioException {
		if (usuario == null || usuario.getUsuario() == null)
			throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
		if (perito == null) {
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		}
		// consultamos si el perito ya existe con un id diferente y en cuyo caso no
		// permitiremos guardar el perito.
		PeritosEmpresa repetido = consultarPeritoDocumento(perito.getNumeroDocumento());
//		if (repetido != null && (perito.getIdPeritoEmpresa() == null || perito.getIdPeritoEmpresa().compareTo(repetido.getIdPeritoEmpresa()) != 0))
		if (repetido != null) {
			if(repetido.getEstadoAsociacion().equalsIgnoreCase(EstadoRegistro.ACTIVO.getValor()))
				throw mgrExc.lanzarExcepcion(90, TipoErrorNegocio.ERROR);
		}		
		perito.setFechaTransaccion(new Date());
		perito.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
		// si el registro no existe lo crearemos. de lo contrario solo se
		// actualizaran sus valores.
		//if ((repetido == null && perito.getIdPeritoEmpresa() == null) || peritoDao.buscar(perito.getIdPeritoEmpresa()) == null) 
		if (repetido == null && perito.getIdPeritoEmpresa() == null){
			perito.setEstadoAsociacion(EstadoRegistro.ACTIVO.getValor());
			perito.setFechaCreacion(new Date());
			perito.setUsuarioCreacion(usuario.getUsuario().getCodigo());
			peritoDao.crear(perito);
		} else {
			repetido.setEstadoAsociacion(EstadoRegistro.ACTIVO.getValor());
			repetido.setEmpresasAvaluo(perito.getEmpresasAvaluo());
			repetido.setIdEmpresaAvaluo(perito.getIdEmpresaAvaluo());
			peritoDao.actualizar(repetido);
		}
			
	}

	@Override
	public void desactivar(List<PeritosEmpresa> peritos, UsuarioDto usuario) throws NegocioException {
		if (peritos == null)
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		for (PeritosEmpresa perito : peritos) {
			perito.setEstadoAsociacion(EstadoRegistro.INACTIVO.getValor());
			perito.setFechaTransaccion(new Date());
			perito.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
			peritoDao.actualizar(perito);
		}
	}

}