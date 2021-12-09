package com.segurosbolivar.avaluos.modelo.facade.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.HistoricoLogsGeneraArch;
import com.segurosbolivar.avaluos.modelo.entity.LogsGeneraArchivo;
import com.segurosbolivar.avaluos.modelo.facade.IProcedatoFacade;
import com.segurosbolivar.avaluos.modelo.service.IAvaluo;
import com.segurosbolivar.avaluos.modelo.service.IParametrizacion;
import com.segurosbolivar.avaluos.modelo.service.IProcedato;

/**
 * Implementaci�n de la fachada para la generaci�n de los procedatos.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:46 a.m.
 */
@Stateless
public class ProcedatoFacadeImpl implements IProcedatoFacade {

	private static final long serialVersionUID = -8822677879335406552L;
	@EJB
	public IProcedato procedatoSvc;
	@EJB
	public IAvaluo avaluoSvc;
	@EJB
	public IParametrizacion parametrizacionSvc;

	@Override
	public List<LogsGeneraArchivo> consultar(LogsGeneraArchivo logsGeneraArchivoDTO, int inicio, int registroXPagina,
			String campoOrden, SentidoOrientacion sentido) throws NegocioException {
		return procedatoSvc.consultar(logsGeneraArchivoDTO, inicio, registroXPagina, campoOrden, sentido);
	}

	@Override
	public List<HistoricoLogsGeneraArch> consultar(HistoricoLogsGeneraArch historico, int inicio, int registroXPagina,
			String campoOrden, SentidoOrientacion sentido) throws NegocioException {
		return procedatoSvc.consultar(historico, inicio, registroXPagina, campoOrden, sentido);
	}

	@Override
	public int cantidadRegistros(LogsGeneraArchivo consulta) throws NegocioException {
		return procedatoSvc.cantidadRegistros(consulta);
	}

	@Override
	public void reversarProcedatos(List<LogsGeneraArchivo> procedatosSeleccionados) throws NegocioException {
		procedatoSvc.reversar(procedatosSeleccionados);
	}

	@Override
	public void cambiarEstadoProcedato(List<LogsGeneraArchivo> procedatosSeleccionados) throws NegocioException {
		procedatoSvc.cambiarEstadoProcedato(procedatosSeleccionados);
	}

	@Override
	public void cambiarTransmitido(Long idLogsGeneraArchivo) throws NegocioException {
		procedatoSvc.cambiarTransmitido(idLogsGeneraArchivo);
	}

	@Override
	public void generar(Date fechaHasta, UsuarioDto usuario) throws NegocioException {
		procedatoSvc.generar(fechaHasta, usuario,false);
	}

}