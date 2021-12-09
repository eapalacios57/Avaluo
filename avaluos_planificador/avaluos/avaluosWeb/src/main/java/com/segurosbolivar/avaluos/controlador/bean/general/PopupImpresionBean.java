package com.segurosbolivar.avaluos.controlador.bean.general;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.segurosbolivar.avaluos.controlador.bean.ReporteAvaluoBean;

@ManagedBean(name = "impresor")
@SessionScoped
public class PopupImpresionBean extends PopupAbstractoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String titulo;
	private String descripcion;
	private String actualizar;
	private String destinoAceptacion;
	private String destinoCancelacion;

	@Override
	public String getPopupId() {
		return "dlg_ImpresorPopup";
	}

	@Override
	public void inicio() {

	}
	
	public void ver(){
		setTitulo("Impresion de Documentos");
		setDescripcion("Escoja el formato de exportacion");
		abrir();
	}
	
	public void exportarPdf() {
		ocultar();
		ReporteAvaluoBean.getBean().generarReporteUsuarios();
	}
	
	public void exportarExcel() {
		ocultar();
		ReporteAvaluoBean.getBean().generarReporteUsuariosExcel();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getActualizar() {
		return actualizar;
	}

	public void setActualizar(String actualizar) {
		this.actualizar = actualizar;
	}

	public String getDestinoAceptacion() {
		return destinoAceptacion;
	}

	public void setDestinoAceptacion(String destinoAceptacion) {
		this.destinoAceptacion = destinoAceptacion;
	}

	public String getDestinoCancelacion() {
		return destinoCancelacion;
	}

	public void setDestinoCancelacion(String destinoCancelacion) {
		this.destinoCancelacion = destinoCancelacion;
	}
	
	
	
	

}
