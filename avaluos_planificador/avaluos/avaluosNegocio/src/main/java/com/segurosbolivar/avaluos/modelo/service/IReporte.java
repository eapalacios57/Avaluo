package com.segurosbolivar.avaluos.modelo.service;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;

/**
 * Provee los servicio para la generacion de los reportes del sistema. Crea los
 * archivos respectivos y permite su descarga e impresión.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:44 a.m.
 */
@Local
public interface IReporte extends Serializable {

	File generarReporte(String rutaReporte, String rutaExportacion, List<?> data, String nombreOrigenDatos)
			throws NegocioException;

	File generarReporteXLS(String nombreReporte, String rutaReporte, String rutaExportacion, List<?> data,
			String nombreOrigenDatos) throws NegocioException;

	File imprimir(List<Avaluo> avaluos, boolean registroFotografico, UsuarioDto usuario) throws NegocioException;
	
	byte[] generarReporteFotografico(Avaluo avaluo) throws NegocioException;

	File generarReporteExcel(String nombreReporte, String rutaReporte, String rutaExportacion, List<?> data,
			String nombreOrigenDatos) throws NegocioException;

	byte[] generarReporte(InputStream jasperStream, List<?> data, String nombreOrigenDatos) throws NegocioException;

	byte[] generarReporteXLS(InputStream jasperStream, List<?> data, String nombreOrigenDatos) throws NegocioException;

	byte[] generarReporteExcel(InputStream jasperStream, List<?> data, String nombreOrigenDatos) throws NegocioException;
}