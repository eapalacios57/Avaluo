package com.segurosbolivar.avaluos.controlador.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.segurosbolivar.avaluos.controlador.bean.general.PopupAbstractoBean;
import com.segurosbolivar.avaluos.modelo.facade.IPeritoFacade;

/**
 * Controlador para el popup que permite realizar la consulta de un perito desde
 * cualquier vista del sistema.
 *
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:41 a.m.
 */
@ManagedBean
@SessionScoped
public class ConsultarPeritoBean extends PopupAbstractoBean implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private List<?> listado;
	@EJB
	public IPeritoFacade peritoFacade;

	public ConsultarPeritoBean() {

	}

	public int seleccionar() {
		return 0;
	}

	@Override
	public String getPopupId() {
		return null;
	}

	@Override
	public void inicio() {

	}

}