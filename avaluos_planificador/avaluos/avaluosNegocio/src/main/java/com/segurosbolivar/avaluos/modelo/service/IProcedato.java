package com.segurosbolivar.avaluos.modelo.service;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.HistoricoLogsGeneraArch;
import com.segurosbolivar.avaluos.modelo.entity.LogsGeneraArchivo;

/**
 * Expone la funcionalidad para el procesamiento y generaci�n de los archivos de
 * procedatos que se trasmiten a la ASOBANCARIA.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:44 a.m.
 */
@Local
public interface IProcedato extends Serializable {

	List<LogsGeneraArchivo> consultar(LogsGeneraArchivo logsGeneraArchivoDTO, int inicio, int registroXPagina,
			String campoOrden, SentidoOrientacion sentido) throws NegocioException;

	int cantidadRegistros(LogsGeneraArchivo consulta) throws NegocioException;

	void generar(Date fechaHastaGeneracion, UsuarioDto usuario,boolean job) throws NegocioException;

	File copiaSeguridadAvaluos(List<Avaluo> avaluosSeleccionados) throws NegocioException;

	void reversar(List<LogsGeneraArchivo> procedatosSeleccionados) throws NegocioException;

	void cambiarEstadoProcedato(List<LogsGeneraArchivo> procedatosSeleccionados) throws NegocioException;

	void cambiarTransmitido(Long idLogsGeneraArchivo) throws NegocioException;

	List<HistoricoLogsGeneraArch> consultar(HistoricoLogsGeneraArch consultar, int inicio, int registroXPagina,
			String campoOrden, SentidoOrientacion sentido) throws NegocioException;

}