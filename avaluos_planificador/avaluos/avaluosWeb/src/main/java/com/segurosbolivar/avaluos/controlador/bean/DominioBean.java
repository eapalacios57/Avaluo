package com.segurosbolivar.avaluos.controlador.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.segurosbolivar.avaluos.controlador.bean.general.ListaBean;
import com.segurosbolivar.avaluos.controlador.bean.general.PopupAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilValidadorJsf;
import com.segurosbolivar.avaluos.modelo.entity.Dominios;
import com.segurosbolivar.avaluos.modelo.service.IParametrizacion;

/**
 * Este controlador gestiona la pantalla que permite registrar y actualizar los
 * valores dominio de todos los listado del sistema.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:41 a.m.
 */
@ManagedBean
@SessionScoped
public class DominioBean extends PopupAbstractoBean implements Serializable{

	private static final long serialVersionUID = -7881420228546722687L;
	private String dominio;
	private Dominios filtro;
	private boolean esNuevo;
	private LazyDataModel<String> listado;
	private Dominios valor;
	private LazyDataModel<Dominios> valores;
	private int cantidadValores;
	/**
	 * Define la cantidad de registros por pagina
	 */
	private static final int REGISTRO_PAGINA = 10;

	@EJB
	public IParametrizacion parametrizacionSvc;

	@Override
	public void inicio() {
		filtro=new Dominios();
		try {
			listado = new LazyDataModel<String>() {

				private static final long serialVersionUID = -563659171394051783L;

				@Override
				public List<String> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, Object> filters) {
					return parametrizacionSvc.consultarDominios(filtro,first, pageSize,"rvDomain",
							UtilJsf.resolverOrientacion(sortOrder));
				}

			};
			listado.setRowCount(parametrizacionSvc.cantidadRegistros(new Dominios()));
			listado.setPageSize(REGISTRO_PAGINA);

			valores = new LazyDataModel<Dominios>() {

				private static final long serialVersionUID = 186448530119942158L;

				@Override
				public List<Dominios> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, Object> filters) {
					List<Dominios> valoresDominios = parametrizacionSvc.consultarValoresDominioFiltro(filtro, first, pageSize,sortField,UtilJsf.resolverOrientacion(sortOrder));
//					return parametrizacionSvc.consultarValoresDominio(dominio, first, pageSize,sortField,UtilJsf.resolverOrientacion(sortOrder));
					cantidadValores = valoresDominios.size();
					return valoresDominios;
				}
			};
//			valores.setRowCount(parametrizacionSvc.cantidadRegistrosValoresFiltro(dominio,filtro));
			valores.setRowCount(cantidadValores);
			valores.setPageSize(REGISTRO_PAGINA);
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

	/***
	 * Permite limpiar el formulario e inicializar la pantalla.
	 */
	public void limpiar() {
		try {
			valores.setRowCount(parametrizacionSvc.cantidadRegistrosValores(dominio));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	@Override
	public String getPermiso() {
		return null;
	}

	@Override
	public String getPopupId() {
		return "dlg_ValorDominioPopup";
	}

	public void editar() {
		try {
			dominio = listado.getRowData();
//			valores.setRowCount(parametrizacionSvc.cantidadRegistrosValores(dominio));
			filtro.setRvDomain(dominio);
			valores.setRowCount(parametrizacionSvc.cantidadRegistrosValoresFiltro(dominio,filtro));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void cancelar() {
		dominio = null;
		filtro=new Dominios();
	}

	public void nuevoValor() {
		valor = new Dominios();
		valor.setRvDomain(dominio);
		esNuevo = true;
		abrir();
	}

	public void editarValor() {
		try {
			valor = valores.getRowData().clone();
			esNuevo = false;
			abrir();
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void eliminarValor() {
		try {
			Dominios eliminar = valores.getRowData();
			if (eliminar == null)
				throw mgrExc.lanzarExcepcion(93, TipoErrorNegocio.ALERTA);
			parametrizacionSvc.eliminarValorDominio(eliminar);
			ListaBean.getBean().actualizarLista(dominio);
			limpiar();
			mensajeConfirmacion(obtenerEtiqueta("menEliminar"));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void cancelarValor() {
		valor = null;
		ocultar();
	}

	public void guardarValor() {
		try {
			if (!validar()) {
				return;
			}
			parametrizacionSvc.guardarValorDominio(valor, getUsuario());
			ListaBean.getBean().actualizarLista(dominio);
			valor = null;
			limpiar();
			ocultar();
			mensajeConfirmacion(obtenerEtiqueta("menGuardar"));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	private boolean validar() {
		boolean validar = true;
		validar &= UtilValidadorJsf.validar(valor.getRvLowValue(), "valoDominioForm:codigo", true, 240, null);
		validar &= UtilValidadorJsf.validar(valor.getRvHighValue(), "valorDominioForm:codigoAlterno", false, 240, null);
		validar &= UtilValidadorJsf.validar(valor.getRvMeaning(), "valorDominioForm:descripcion", true, 240, null);
		return validar;
	}

	/*
	 * getters y setters
	 */
	
	public int getRegistroPagina() {
		return REGISTRO_PAGINA;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public boolean isEsNuevo() {
		return esNuevo;
	}

	public void setEsNuevo(boolean esNuevo) {
		this.esNuevo = esNuevo;
	}

	public LazyDataModel<String> getListado() {
		return listado;
	}

	public LazyDataModel<Dominios> getValores() {
		return valores;
	}

	public Dominios getValor() {
		return valor;
	}

	public void setValor(Dominios valor) {
		this.valor = valor;
	}

	public void setListado(LazyDataModel<String> listado) {
		this.listado = listado;
	}

	public void setValores(LazyDataModel<Dominios> valores) {
		this.valores = valores;
	}

	public Dominios getFiltro() {
		return filtro;
	}

	public void setFiltro(Dominios filtro) {
		this.filtro = filtro;
	}



}