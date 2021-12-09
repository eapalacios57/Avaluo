package com.segurosbolivar.avaluos.modelo.facade.impl;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.facade.IReporteFacade;
import com.segurosbolivar.avaluos.modelo.service.IArchivo;
import com.segurosbolivar.avaluos.modelo.service.IParametrizacion;
import com.segurosbolivar.avaluos.modelo.service.IReporte;

/**
 * Implementaci�n de la fachada para gestionar los reportes del sistema.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:46 a.m.
 */
@Stateless
public class ReporteFacadeImpl implements IReporteFacade {

	private static final long serialVersionUID = 5445527798934138613L;

	@EJB
	public IArchivo archivoSvc;
	@EJB
	public IParametrizacion parametrizacionSvc;
	@EJB
	public IReporte reporteSvc;

	@Override
	public File generarReporte(String rutaReporte, String rutaExportacion, List<?> data, String nombreOrigenDatos)
			throws NegocioException {
		return reporteSvc.generarReporte(rutaReporte, rutaExportacion, data, nombreOrigenDatos);
	}

	@Override
	public File generarReporteXLS(String nombreReporte, String rutaReporte, String rutaExportacion, List<?> data,
			String nombreOrigenDatos) throws NegocioException {
		return reporteSvc.generarReporteXLS(nombreReporte, rutaReporte, rutaExportacion, data, nombreOrigenDatos);
	}

	@Override
	public File generarReporteExcel(String nombreReporte, String rutaReporte, String rutaExportacion, List<?> data,
			String nombreOrigenDatos) throws NegocioException {
		return reporteSvc.generarReporteExcel(nombreReporte, rutaReporte, rutaExportacion, data, nombreOrigenDatos);
	}

	@Override
	public byte[] generarReporte(InputStream jasperStream, List<?> data, String nombreOrigenDatos) throws NegocioException {
		return reporteSvc.generarReporte(jasperStream, data, nombreOrigenDatos);
	}

	@Override
	public byte[] generarReporteXLS(InputStream jasperStream, List<?> data, String nombreOrigenDatos)
			throws NegocioException {
		return reporteSvc.generarReporteXLS(jasperStream, data, nombreOrigenDatos);
	}

	@Override
	public byte[] generarReporteExcel(InputStream jasperStream, List<?> data, String nombreOrigenDatos)
			throws NegocioException {
		return reporteSvc.generarReporteExcel(jasperStream, data, nombreOrigenDatos);
	}

}