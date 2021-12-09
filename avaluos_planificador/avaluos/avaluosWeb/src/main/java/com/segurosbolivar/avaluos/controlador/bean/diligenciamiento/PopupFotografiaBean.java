package com.segurosbolivar.avaluos.controlador.bean.diligenciamiento;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.asesoftware.util.archivo.UtilImagen;
import com.segurosbolivar.avaluos.controlador.bean.general.PopupAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.modelo.entity.ListaAnexosPdf;

@ManagedBean(name = "popupFotografiaBean")
@SessionScoped
public class PopupFotografiaBean extends PopupAbstractoBean implements Serializable {

	private static final long serialVersionUID = -3934583709166727424L;
	private ListaAnexosPdf fotografia;

	public static PopupFotografiaBean getBean() {
		return UtilJsf.obtenerBackingBean("popupFotografiaBean");
	}

	@Override
	public String getPopupId() {
		return "dlg_fotografiaPopup";
	}

	@Override
	public void inicio() {

	}

	public void ver(ListaAnexosPdf fotografia) {
		if (fotografia == null) {
			return;
		}
		this.fotografia = fotografia;
		abrir();
	}

	public void cerrar() {
		fotografia = null;
		ocultar();
	}

	public void eliminar() {
		RegistroFotograficoBean.getBean().setFotografia(fotografia);
		RegistroFotograficoBean.getBean().eliminar();
		cerrar();
	}

	public StreamedContent getImagen() {
		return new DefaultStreamedContent(new ByteArrayInputStream(fotografia.getAnexo()), "image/jpg",
				fotografia.getArchivo().getNombreArchivo());
	}

	public void rotar() {
		try {
			fotografia.setAnexo(UtilImagen.rotar90GradosDerecha(fotografia.getAnexo()));
			fotografia.setModificado(true);
		} catch (IOException e) {
			procesarError(e);
		}
	}

	public void rotarIzquierda() {
		try {
			fotografia.setAnexo(UtilImagen.rotar90GradosIzquierda(fotografia.getAnexo()));
			fotografia.setModificado(true);
		} catch (IOException e) {
			procesarError(e);
		}
	}
	/*
	 * getters y setters
	 */

	public ListaAnexosPdf getFotografia() {
		return fotografia;
	}

	public void setFotografia(ListaAnexosPdf fotografia) {
		this.fotografia = fotografia;
	}

}
