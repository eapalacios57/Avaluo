package com.segurosbolivar.avaluos.controlador.bean.general;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.asesoftware.util.archivo.UtilArchivos;
import com.segurosbolivar.avaluos.cons.AvaluosCons;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;

/**
 * Este controlador se usa como componente general para realizar la impresión de
 * reportes del sistema.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:43 a.m.
 */
@ManagedBean(name = "impresionBean")
@SessionScoped
public class ImpresionBean extends GeneralAbstractoBean implements Serializable {

	private static final long serialVersionUID = -6377099271870013076L;
	private StreamedContent contenido;
	private String path;
	public String nombre;

	public static ImpresionBean getBean() {
		return UtilJsf.obtenerBackingBean("impresionBean");
	}

	public void imprimir(File impresion) throws IOException {
		try {
			if (impresion == null || !impresion.exists())
				return;
			path = impresion.getAbsolutePath();
			nombre=impresion.getName();
			String content = "image/jpeg";
			if(UtilArchivos.obtenerExtension(impresion.getName()).equalsIgnoreCase("PDF")) {
				content = "application/pdf";
				contenido = new DefaultStreamedContent(new FileInputStream(impresion), content , impresion.getName());
				UtilJsf.navegarNuevaPagina(UtilJsf.getContexto() + AvaluosCons.RUTA_PAG_IMPRESION);
			}else if(UtilArchivos.obtenerExtension(impresion.getName()).equalsIgnoreCase("XLSX")) {
				content = "application/vnd.ms-excel";
				contenido = new DefaultStreamedContent(new FileInputStream(impresion), content,
						impresion.getName());
			}else {
				contenido = new ByteArrayContent(IOUtils.toByteArray(new FileInputStream(impresion)), content,
						impresion.getName());
				UtilJsf.navegarNuevaPagina(UtilJsf.getContexto() + AvaluosCons.RUTA_PAG_IMPRESION);
			}
			
		} catch (Exception e) {
			procesarError(e);
		}
	}
	
	public void imprimir(String nombreArchivo, byte[] bytes) throws IOException {
		
	    String contentType = "";
	    
		switch(UtilArchivos.obtenerExtension(nombreArchivo).toUpperCase()) {
			case "PDF":
				contentType = "application/pdf";
			break;
			case "XLS":
				contentType = "application/vnd.ms-excel";
			break;
			case "XLSX":
				contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
			break;
			default:
				throw new IllegalArgumentException("Formato de archivo no permitido");
		}
		FacesContext contexto = FacesContext.getCurrentInstance();
		if (contexto.getResponseComplete())
			return;
		HttpServletResponse response = (HttpServletResponse) contexto.getExternalContext().getResponse();
		response.setContentType(contentType);
		response.setHeader("Window-target:", "_blank");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + nombreArchivo + "\"");
		response.setHeader("Cache-Control", "cache, must-revalidate");
		response.setHeader("Pragma", "public");
		ServletOutputStream outputStream = response.getOutputStream();
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		IOUtils.copy(bais, outputStream);
		contexto.responseComplete();
		this.contenido = new ByteArrayContent(bytes, contentType, nombreArchivo);
	}

		

	/*
	 * implementados
	 */
	@Override
	public void inicio() {
	}

	@Override
	public String getPermiso() {
		return null;
	}

	/*
	 * getters y setters
	 */

	public StreamedContent getContenido() {
		if (FacesContext.getCurrentInstance().getCurrentPhaseId() == PhaseId.RENDER_RESPONSE)
			return new DefaultStreamedContent();
		else
			return contenido;
	}

	public void setContenido(StreamedContent contenido) {
		this.contenido = contenido;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getNombre() {
	    return nombre;
	}

	public void setNombre(String nombre) {
	    this.nombre = nombre;
	}

}