package com.segurosbolivar.avaluos.modelo.service.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.data.EmpresaAvaluadoraDao;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.EmpresasAvaluos;
import com.segurosbolivar.avaluos.modelo.service.IArchivo;
import com.segurosbolivar.avaluos.modelo.service.IEmpresaAvaluadora;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;

/**
 * Implementaci�n del servicio para la empresa avaluadora.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:41 a.m.
 */
@Stateless
public class EmpresaAvaluadoraImpl implements IEmpresaAvaluadora {

    private static final long serialVersionUID = 9141526149538857008L;
    @EJB
    private ManejadorErroresNegocio mgrExc;
    @EJB
    private IArchivo archivoSvc;
    @EJB
    private EmpresaAvaluadoraDao empresaDao;

    @Override
    public int cantidadRegistros(EmpresasAvaluos empresasAvaluos) {
	return empresaDao.cantidadRegistros(empresasAvaluos);
    }

    @Override
    public List<EmpresasAvaluos> consultar(EmpresasAvaluos empresa, int inicio, int registrosXPagina, String campoOrden, SentidoOrientacion sentido) {
	return empresaDao.consultar(empresa, inicio, registrosXPagina, campoOrden, sentido);
    }

    @Override
    public EmpresasAvaluos consultarXDocumento(Long numeroDocumento) throws NegocioException {
	if (numeroDocumento == null || numeroDocumento.compareTo(0L) == 0)
	    throw mgrExc.lanzarExcepcion(96L, TipoErrorNegocio.ERROR);
	EmpresasAvaluos consultar = new EmpresasAvaluos();
	consultar.setNumeroDocumento(numeroDocumento);
	List<EmpresasAvaluos> resultado = empresaDao.consultar(consultar, 0, 2, null, null);
	if (resultado == null || resultado.isEmpty())
	    return null;
	if (resultado.size() > 1)
	    throw mgrExc.lanzarExcepcion(95L, TipoErrorNegocio.ERROR);
	return resultado.get(0);
    }

    @Override
    public void guardar(EmpresasAvaluos empresa, UsuarioDto usuario) throws NegocioException {
	if (usuario == null || usuario.getUsuario() == null)
	    throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
	if (empresa == null)
	    throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
	empresa.setFechaTransaccion(new Date());
	empresa.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
	try {
	    // guardamos los archivos en caso de que se modificaran.
	    if (empresa.getLogo() != null) {
		if (empresa.getLogo().isModificado()) {
			String llaveArchivo = empresa.getNumeroIdentificacion().toString() + "/" + UtilConstantes.RUTA_LOGOS + "/" + empresa.getLogo().getNombreArchivo();
		    archivoSvc.guardarArchivo(empresa, empresa.getLogo(), usuario, llaveArchivo);
		}
		empresa.setIdLogo(empresa.getLogo().getIdArchivo());
	    } else
		empresa.setIdLogo(null);
	    if (empresa.getFirma() != null) {
		if (empresa.getFirma().isModificado()) {
			String llaveArchivo = empresa.getNumeroIdentificacion().toString() + "/" + UtilConstantes.RUTA_FIRMA + "/" + empresa.getFirma().getNombreArchivo();
		    archivoSvc.guardarArchivo(empresa, empresa.getFirma(), usuario, llaveArchivo);
		}
		empresa.setIdFirma(empresa.getFirma().getIdArchivo());
	    } else
		empresa.setIdFirma(null);
	    // si el registro no existe lo crearemos. de lo contrario solo se
	    // actualizaran sus valores.
	    if (empresa.getIdEmpresaAvaluo() == null || empresaDao.buscar(empresa.getIdEmpresaAvaluo()) == null) {
		empresa.setFechaCreacion(new Date());
		empresa.setUsuarioCreacion(usuario.getUsuario().getCodigo());
		empresa.setTipoDocumento("CC");
		empresaDao.crear(empresa);
	    } else
		empresaDao.actualizar(empresa);
	} catch (Exception e) {
	    throw mgrExc.lanzarExcepcion(94, TipoErrorNegocio.ERROR, e.getMessage(), null);
	}
    }

    @Override
    public void eliminar(EmpresasAvaluos eliminar) throws NegocioException {
	if (eliminar == null)
	    throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
	empresaDao.eliminar(eliminar);
    }

}