package com.segurosbolivar.avaluos.modelo.facade;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;

/**
 * Esta fachada se genera para realizar la abstracci�n entre la capa de
 * presentaci�n y los servicios de modo que desde los controladores de la vista
 * de reportes solo se realice la inyecci�n de esta fachada y se eviten mutiples
 * llamados.
 * 
 * Realiza la invocaci�n de las listas necesarias dentro de la vista, la
 * generaci�n del archivo y la creaci�n del reporte respectivo.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:44 a.m.
 */
@Local
public interface IReporteFacade extends Serializable {

	File generarReporteXLS(String nombreReporte, String rutaReporte, String rutaExportacion, List<?> data,
			String nombreOrigenDatos) throws NegocioException;
	
	File generarReporteExcel(String nombreReporte, String rutaReporte, String rutaExportacion, List<?> data,
			String nombreOrigenDatos) throws NegocioException;

	File generarReporte(String rutaReporte, String rutaExportacion, List<?> data, String nombreOrigenDatos)
			throws NegocioException;
	
	byte[] generarReporte(InputStream jasperStream, List<?> data, String nombreOrigenDatos) throws NegocioException;

	byte[] generarReporteXLS(InputStream jasperStream, List<?> data, String nombreOrigenDatos) throws NegocioException;

	byte[] generarReporteExcel(InputStream jasperStream, List<?> data, String nombreOrigenDatos)
			throws NegocioException;
}