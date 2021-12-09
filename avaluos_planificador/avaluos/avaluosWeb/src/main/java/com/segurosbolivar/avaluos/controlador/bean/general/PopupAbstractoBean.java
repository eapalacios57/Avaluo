package com.segurosbolivar.avaluos.controlador.bean.general;


import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;

/**
 * Esta clase provee funcionalidad adicional para el manejo de ventanas
 * flotantes(pop up) en el sistema.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:46 a.m.
 */
public abstract class PopupAbstractoBean extends GeneralAbstractoBean{

	private static final long serialVersionUID = 6251545104931256074L;
	private boolean ver;
	private String destinoConfirmacion;

	public abstract String getPopupId();

	public void abrir() {
		ver = true;
		UtilJsf.abrirDialogo(getPopupId());
	}

	public void ocultar() {
		ver = false;
		UtilJsf.ocultarDialogo(getPopupId());
	}

	public void cerrar(Object parametro) {
		ocultar();
		if (!UtilTexto.estaVacio(destinoConfirmacion)) {
			UtilJsf.aplicarParametro(destinoConfirmacion, parametro);
		}
	}

	@Override
	public String getPermiso() {
		return null;
	}

	public boolean isVer() {
		return ver;
	}

	public void setVer(boolean ver) {
		this.ver = ver;
	}

	public String getDestinoConfirmacion() {
		return destinoConfirmacion;
	}

	public void setDestinoConfirmacion(String destinoConfirmacion) {
		this.destinoConfirmacion = destinoConfirmacion;
	}

}