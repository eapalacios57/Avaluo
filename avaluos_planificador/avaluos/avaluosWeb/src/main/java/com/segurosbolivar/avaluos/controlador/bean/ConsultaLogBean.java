package com.segurosbolivar.avaluos.controlador.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.segurosbolivar.avaluos.controlador.bean.general.GeneralAbstractoBean;

/**
 * Controlador para la vista que permite consultar el log del sistema.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:41 a.m.
 */
@ManagedBean
@SessionScoped
public class ConsultaLogBean extends GeneralAbstractoBean implements Serializable{

	private static final long serialVersionUID = -672235860127394128L;

	public int descargar() {
		return 0;
	}

	@Override
	public void inicio() {
		// aun sin usar esta pantalla.
	}

	@Override
	public String getPermiso() {
		return null;
	}

}