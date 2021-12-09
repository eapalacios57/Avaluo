package com.segurosbolivar.avaluos.modelo.facade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.HistoricoLogsGeneraArch;
import com.segurosbolivar.avaluos.modelo.entity.LogsGeneraArchivo;

/**
 * Esta fachada se genera para realizar la abstracci�n entre la capa de
 * presentaci�n y los servicios de modo que desde los controladores de la vista
 * de procedatos solo se realice la inyecci�n de esta fachada y se eviten
 * mutiples llamados.
 * 
 * Realiza la invocaci�n de las listas necesarias dentro de la vista, la
 * generaci�n de los procedatos y la consulta respectiva de la informaci�n de
 * los aval�os que lo conforman.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:44 a.m.
 */
@Local
public interface IProcedatoFacade extends Serializable {

	List<LogsGeneraArchivo> consultar(LogsGeneraArchivo logsGeneraArchivoDTO, int inicio, int registroXPagina,
			String campoOrden, SentidoOrientacion sentido) throws NegocioException;

	int cantidadRegistros(LogsGeneraArchivo consulta) throws NegocioException;

	void generar(Date fechaHasta, UsuarioDto usuarioDto) throws NegocioException;

	void reversarProcedatos(List<LogsGeneraArchivo> procedatosSeleccionados) throws NegocioException;

	void cambiarEstadoProcedato(List<LogsGeneraArchivo> procedatosSeleccionados) throws NegocioException;

	void cambiarTransmitido(Long idLogsGeneraArchivo) throws NegocioException;

	List<HistoricoLogsGeneraArch> consultar(HistoricoLogsGeneraArch historico, int inicio, int registroXPagina,
			String campoOrden, SentidoOrientacion sentido) throws NegocioException;

}