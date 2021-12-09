package com.segurosbolivar.avaluos.controlador.bean.general;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;

@ManagedBean(name = "confirmar")
@SessionScoped
public class PopupConfirmacionBean extends PopupAbstractoBean implements Serializable {

	private static final long serialVersionUID = 4863375345645472859L;
	private String titulo;
	private String descripcion;
	private String actualizar;
	private String destinoAceptacion;
	private String destinoCancelacion;

	public static PopupConfirmacionBean getBean() {
		return UtilJsf.obtenerBackingBean("confirmar");
	}

	@Override
	public String getPopupId() {
		return "dlg_ConfirmarPopup";
	}

	@Override
	public void inicio() {
		// no aplica valor para iniciar.
	}

	public void confirmar(String destinoAceptacion, String titulo, String descripcion, String destinoCancelacion, String actualizar) {
		if (UtilTexto.estaVacio(destinoAceptacion))
			return;
		abrir();
		this.destinoAceptacion = destinoAceptacion;
		this.destinoCancelacion = destinoCancelacion;
		this.actualizar = actualizar;
		this.titulo = UtilTexto.estaVacio(titulo) ? "CONFIRMAR" : titulo;
		this.descripcion = UtilTexto.estaVacio(descripcion) ? "" : descripcion;
	}

	public void aceptar() {
		setDestinoConfirmacion(destinoAceptacion);
		this.actualizar = null;
		cerrar(null);
	}

	public void cancelar() {
		setDestinoConfirmacion(destinoCancelacion);
		this.actualizar = null;
		cerrar(null);
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

}
