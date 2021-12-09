package com.segurosbolivar.avaluos.controlador.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.controlador.bean.general.PopupAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilValidadorJsf;
import com.segurosbolivar.avaluos.modelo.cons.TipoParametro;
import com.segurosbolivar.avaluos.modelo.entity.Parametrizacion;
import com.segurosbolivar.avaluos.modelo.service.IParametrizacion;

/**
 * Clase controladora encargada de realizar las configuraciones internas de la aplicacion 
 * @author Asesoftware
 *
 */
@ManagedBean
@SessionScoped
public class ParametrizacionBean extends PopupAbstractoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5626883923109737092L;
	private Parametrizacion filtro;
	private Parametrizacion parametro;
	private LazyDataModel<Parametrizacion> listado;
	private static final int REGISTRO_PAGINA = 10;

	@EJB
	public IParametrizacion parametrizacionSvc;
	
	
	@Override
	public String getPopupId() {
		return "dlg_ParametroPopup";
	}

	@Override
	public void inicio() {
		filtro = new Parametrizacion();
		filtro.setTipoparametro(TipoParametro.NOTIFICACIONES.getValor());
		try {
			listado = new LazyDataModel<Parametrizacion>() {

				private static final long serialVersionUID = 1L;
				
				@Override
				public List<Parametrizacion> load(int first, int pageSize, String sortField ,SortOrder sortOrder,
						Map<String, Object> filters) {
					List<Parametrizacion> parametros = parametrizacionSvc.consultarParametros(filtro, first, pageSize, sortField, UtilJsf.resolverOrientacion(sortOrder));
					return parametros;
				}
			};
			listado.setRowCount(parametrizacionSvc.cantidadRegistros(filtro));
			listado.setPageSize(REGISTRO_PAGINA);
		} catch (Exception e) {
			procesarError(e);
		}
		
	}
	
	public void consultar() {
		try {
			listado.setRowCount(parametrizacionSvc.cantidadRegistros(filtro));
		} catch (Exception e) {
			procesarError(e);
		}
	}
	
	public void editar() {
		try {
			parametro = listado.getRowData().clone();
			abrir();
		} catch (Exception e) {
			procesarError(e);
		}
	}
	
	public void cancelarEdicion() {
		parametro = null;
	}
	
	public void cancelar() {
		parametro = null;
		filtro = new Parametrizacion();
		ocultar();
	}
	
	public boolean validar() {
		boolean validar = true;
		validar &= UtilValidadorJsf.validar(parametro.getValorparametro(), "parametroForm:valorParametro", true, 240, null);
		if(!UtilTexto.estaVacio(parametro.getValorparametro()) && parametro.getTipoparametro().compareTo(TipoParametro.NOTIFICACIONES.getValor())==0) {
			StringTokenizer token = new StringTokenizer(parametro.getValorparametro(), ",");
			while(token.hasMoreTokens()) {
				String correo = token.nextToken();
				if(!UtilTexto.estaVacio(correo)) {
					validar &= UtilValidadorJsf.validarCorreo(correo, "parametroForm:valorParametro", false, 60, null);
				}
			}
		}
		return validar;
	}
	
	public void guardar() {
		try {
			if(!validar())
				return;
			parametrizacionSvc.guardarParametro(parametro, getUsuario());
			listado.setRowCount(parametrizacionSvc.cantidadRegistros(filtro));
			mensajeConfirmacion(obtenerEtiqueta("menGuardar"));
			parametro = null;
			ocultar();
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public Parametrizacion getFiltro() {
		return filtro;
	}

	public void setFiltro(Parametrizacion filtro) {
		this.filtro = filtro;
	}

	public Parametrizacion getParametro() {
		return parametro;
	}

	public void setParametro(Parametrizacion parametro) {
		this.parametro = parametro;
	}

	public LazyDataModel<Parametrizacion> getListado() {
		return listado;
	}

	public void setListado(LazyDataModel<Parametrizacion> listado) {
		this.listado = listado;
	}

	public IParametrizacion getParametrizacionSvc() {
		return parametrizacionSvc;
	}

	public void setParametrizacionSvc(IParametrizacion parametrizacionSvc) {
		this.parametrizacionSvc = parametrizacionSvc;
	}

	public int getRegistroPagina() {
		return REGISTRO_PAGINA;
	}
	
	
	
}
