package com.asesoftware.util.archivo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 * Esta clase permite la generacion de reportes de jasperReports y la creacion
 * de archivos pdfs.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:47 a.m.
 */
public class UtilPdf {

	public static final String RUTA_REPORTE = "RUTA_REPORTE";

	public Integer ObtenerNumeroPaginas(String archivo) {
		PdfDocument pdfDoc = null;
		PdfReader reader = null;
		Integer paginas = null;
		try {
			reader = new PdfReader(archivo);
			pdfDoc = new PdfDocument(reader);
			paginas = pdfDoc.getNumberOfPages();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				pdfDoc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return paginas;
	}

	public static File generarReportJasperPdf(String nombreDocumento, String rutaReporte, String rutaRepositorio,
			InputStream reporte, List<?> data) throws Exception {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put(RUTA_REPORTE, rutaReporte);
		JasperPrint reportPrint = JasperFillManager.fillReport(reporte, parametros,
				new JRBeanArrayDataSource(data.toArray()));
		File salida = new File(rutaRepositorio + nombreDocumento + ".pdf");
		FileOutputStream archivo = new FileOutputStream(salida, true);
		JasperExportManager.exportReportToPdfStream(reportPrint, archivo);
		archivo.close();
		return salida;
	}

	public static byte[] generarReportJasperPdf(InputStream reporte, Map<String, Object> parametros,
			JRDataSource dataSource) throws JRException {
		return JasperRunManager.runReportToPdf(reporte, parametros, dataSource);
	}

	public static File generarReportJasperPdf(String rutaArchivo, JasperPrint reportPrint)
			throws IOException, JRException {
		File salida = new File(rutaArchivo);
		FileOutputStream archivo = new FileOutputStream(salida, true);
		JasperExportManager.exportReportToPdfStream(reportPrint, archivo);
		archivo.close();
		return salida;
	}

	public static byte[] generarReportJasperPdf(JasperPrint reportPrint) throws IOException, JRException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(reportPrint, baos);
		byte[] bytes = baos.toByteArray();
		baos.close();
		return bytes;
	}

	public static File generarReportJasperXls(String rutaArchivo, JasperPrint reportPrint)
			throws IOException, JRException {
		File salida = new File(rutaArchivo);
		FileOutputStream archivo = new FileOutputStream(salida, true);
		JasperExportManager.exportReportToPdfStream(reportPrint, archivo);
		archivo.close();
		return salida;
	}

	public static JasperPrint generarReportJasper(InputStream reporte, Map<String, Object> parametros,
			Connection conect) throws Exception {
		JasperPrint reportPrint = JasperFillManager.fillReport(reporte, parametros, conect);
		return reportPrint;
	}

	public static JasperPrint generarReportJasper(InputStream reporte, Map<String, Object> parametros,
			JRDataSource dataSource) throws JRException {
		JasperReport reporteJasper = JasperCompileManager.compileReport(reporte);
		return JasperFillManager.fillReport(reporteJasper, parametros, dataSource);
	}

	public static File generarReportJasperExcel(String rutaReporte, JasperPrint reportPrint) throws Exception {
		File salida = new File(rutaReporte + ".xlsx");
		FileOutputStream archivo = new FileOutputStream(salida, true);
		JRXlsxExporter exporter = new JRXlsxExporter();
		exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, reportPrint);
/*		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);*/
		exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, rutaReporte + ".xlsx");
		exporter.exportReport();
		archivo.close();
		return salida;
	}

	public static byte[] generarReportJasperExcel(JasperPrint reportPrint) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JRXlsxExporter exporter = new JRXlsxExporter();
		exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, reportPrint);
/*		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);*/
		exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, baos);
		exporter.exportReport();
		byte[] bytes = baos.toByteArray();
		baos.close();
		return bytes;
	}
}
