package com.segurosbolivar.avaluos.controlador.bean;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.StreamedContent;

import com.asesoftware.util.archivo.UtilPropiedades;
import com.segurosbolivar.avaluos.controlador.bean.general.GeneralAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.general.ImpresionBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.modelo.facade.IReporteFacade;
import com.segurosbolivar.avaluos.modelo.service.IParametrizacion;
import com.segurosbolivar.avaluos.modelo.service.ISeguridad;
import com.segurosbolivar.avaluos.modelo.service.impl.ManejadorErroresNegocio;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Usuario;

/**
 * Controlador de la vista que permite la generaci�n de los reportes del
 * sistema. Interactua con la ventana de imprimir para realizar la impresi�n de
 * los mismos.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:46 a.m.
 */
@ManagedBean
@SessionScoped
public class ReporteAvaluoBean extends GeneralAbstractoBean implements Serializable{

	private static final long serialVersionUID = -7590041620708253666L;

	private static ResourceBundle propiedades = UtilConstantes.RSC_ERRORES;

	@EJB
	public IReporteFacade reporteFacade;
	@EJB
	private ISeguridad svcSeguridad;

	private StreamedContent file;

	@EJB
	private ManejadorErroresNegocio maNegocio;
	
	@EJB
	private IParametrizacion svcParametrizacion;

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	@Override
	public void inicio() {
		// No es necesario inicializar ninguna variable
	}

	public static ReporteAvaluoBean getBean() {
		return UtilJsf.obtenerBackingBean("reporteAvaluoBean");
	}

	@Override
	public String getPermiso() {
		return null;
	}

	public void generarReporteUsuarios() {
		
		String reporteUsuarios = "/"+UtilPropiedades.cargarPropiedad(propiedades, UtilConstantes.RUTA_JASPER)
				+ UtilConstantes.NOMBRE_REPORTE_USUARIOS;
		String nombreDocumento = UtilConstantes.NOMBRE_REPORTE_USUARIOS.replace(".jrxml", "");
		String rutaArchivo = nombreDocumento.trim() + ".pdf";
		InputStream jasperStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(reporteUsuarios.replace("jrxml", "jasper"));
		try {
			@SuppressWarnings("unchecked")
			List<Usuario> usuarios = (List<Usuario>) svcParametrizacion.obtenerListadoCache(UtilConstantes.CACHE_USUARIOS + UtilConstantes.SUFIJO_SERVICIOS_WEB);
			byte[] bytes = reporteFacade.generarReporte(jasperStream, usuarios, UtilConstantes.DATASOURCE_USUARIOS);
			ImpresionBean.getBean().imprimir(rutaArchivo, bytes);
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void generarReporteUsuariosExcel() {
		String reporteUsuarios = "/"+UtilPropiedades.cargarPropiedad(propiedades, UtilConstantes.RUTA_JASPER)
				+ UtilConstantes.NOMBRE_REPORTE_USUARIOS;
		String nombreDocumento = UtilConstantes.NOMBRE_REPORTE_USUARIOS.replace(".jrxml", "");
		InputStream jasperStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(reporteUsuarios.replace("jrxml", "jasper"));
		try {
			@SuppressWarnings("unchecked")
			List<Usuario> usuarios = (List<Usuario>) svcParametrizacion.obtenerListadoCache(UtilConstantes.CACHE_USUARIOS + UtilConstantes.SUFIJO_SERVICIOS_WEB);
			byte[] bytes = reporteFacade.generarReporteExcel(jasperStream, usuarios, UtilConstantes.DATASOURCE_USUARIOS);
			ImpresionBean.getBean().imprimir(nombreDocumento.trim().concat(".xlsx"), bytes);
		} catch (Exception e) {
			procesarError(e);
		}

	}

}