package com.segurosbolivar.avaluos.controlador.bean.general;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.asesoftware.util.exception.NegocioAlertaException;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;

@ManagedBean(name = "error")
@SessionScoped
public class PopupErrorBean extends PopupAbstractoBean implements Serializable{

	private static final long serialVersionUID = 4863375345645472859L;
	private String titulo;
	private String descripcion;
	private String detalle;
	private List<String> anidados;
	private boolean esError;
	private boolean esExitoso;
	private boolean esValidador;
	private int numeroErrores;

	public static PopupErrorBean getBean() {
		return UtilJsf.obtenerBackingBean("error");
	}

	@Override
	public String getPopupId() {
		return "dlg_ErrorPopup";
	}

	@Override
	public void inicio() {

	}

	public void ver(NegocioException error) {
		if (error == null) {
			return;
		}
		abrir();
		this.titulo = (error.getTitulo() == null ? "ERROR NO IDENTIFICADO" : error.getTitulo()) + " - " + error.getId();
		this.descripcion = error.getMessage();
		this.detalle = error.getDetalle();
		anidados = new ArrayList<>();
		procesarAnidados(error);
		esError = !(error instanceof NegocioAlertaException);
		esExitoso=false;
		esValidador = false;
	}

	public void validar(NegocioException error) {
		if (error == null) {
			return;
		}
		abrir();
		numeroErrores = 0;
		anidados = new ArrayList<>();
		procesarAnidados(error);
		this.titulo =  numeroErrores + " " + obtenerEtiqueta("tit_validador_complemento");
		this.descripcion = error.getMessage() + (error.getDetalle()!=null?error.getDetalle():"");
		esError = false;
		esExitoso=false;
		esValidador = true;
	}

	public void exito(String titulo) {
		abrir();
		this.titulo = titulo;
		this.descripcion = null;
		this.detalle = null;
		esError = false;
		esExitoso=true;
		esValidador = false;
		anidados = new ArrayList<>();
	}

	private void procesarAnidados(NegocioException error) {
		if (error.getErrores() == null || error.getErrores().isEmpty()) {
			return;
		}
		for (NegocioException anidar : error.getErrores()) {
			anidados.add(anidar.getMessage() + (UtilTexto.estaVacio(anidar.getDetalle()) ? "" : ":" + anidar.getDetalle()));
			numeroErrores++;
			log.error("Errores Anidados ");
			anidar.printStackTrace();
			procesarAnidados(anidar);
		}
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

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public List<String> getAnidados() {
		return anidados;
	}

	public void setAnidados(List<String> anidados) {
		this.anidados = anidados;
	}

	public boolean isEsError() {
		return esError;
	}

	public boolean isEsValidador() {
		return esValidador;
	}

	public int getNumeroErrores() {
		return numeroErrores;
	}

	public boolean isEsExitoso() {
		return esExitoso;
	}

}
