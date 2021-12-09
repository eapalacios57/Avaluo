package com.segurosbolivar.avaluos.controlador.bean.empresa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.controlador.bean.general.PopupAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilValidadorJsf;
import com.segurosbolivar.avaluos.modelo.cons.EstadoRegistro;
import com.segurosbolivar.avaluos.modelo.entity.EmpresasAvaluos;
import com.segurosbolivar.avaluos.modelo.entity.PeritosEmpresa;
import com.segurosbolivar.avaluos.modelo.facade.IPeritoFacade;

/**
 * Controlador para la ventana de administraci�n de las empresas avaluadoras
 * asociadas al banco DAVIVIENDA y de los peritos asociados a las mismas.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:42 a.m.
 */
@ManagedBean
@SessionScoped
public class PeritoBean extends PopupAbstractoBean implements Serializable {

	private static final long serialVersionUID = 3354405858228658699L;
	private boolean esNuevo;
	private PeritosEmpresa peritoFiltro;
	private LazyDataModel<PeritosEmpresa> listado;
	private List<PeritosEmpresa> seleccionados;
	private EmpresasAvaluos empresa;
	private PeritosEmpresa perito;

	private boolean guardarPerito;
	/**
	 * Define la cantidad de registros por pagina
	 */
	private static final int REGISTRO_PAGINA = 10;

	@EJB
	public IPeritoFacade peritoFacade;

	@Override
	public void inicio() {
		peritoFiltro = new PeritosEmpresa();
		try {
			listado = new LazyDataModel<PeritosEmpresa>() {

				private static final long serialVersionUID = 6786042688016389737L;

				@Override
				public List<PeritosEmpresa> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, Object> filters) {
					if (peritoFiltro == null)
						return null;
					List<PeritosEmpresa> peritos = peritoFacade.consultar(peritoFiltro, first, pageSize, sortField,
							UtilJsf.resolverOrientacion(sortOrder));
					return peritos;
				}

				@Override
				@SuppressWarnings("unchecked")
				public PeritosEmpresa getRowData(String rowKey) {
					List<PeritosEmpresa> peritos = (List<PeritosEmpresa>) getWrappedData();
					for (PeritosEmpresa perito : peritos) {
						if (perito.getIdPeritoEmpresa().toString().equals(rowKey))
							return perito;
					}
					return null;
				}
			};
			listado.setPageSize(REGISTRO_PAGINA);
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public static PeritoBean getBean() {
		return UtilJsf.obtenerBackingBean("peritoBean");
	}

	@Override
	public String getPermiso() {
		return null;
	}

	@Override
	public String getPopupId() {
		return "dlg_PeritoPopup";
	}

	public void ver(EmpresasAvaluos empresa) {
		try {
			seleccionados = new ArrayList<>();
			this.empresa = empresa;
			peritoFiltro = new PeritosEmpresa();
			peritoFiltro.setEmpresasAvaluo(this.empresa);
			peritoFiltro.setEstadoAsociacion(EstadoRegistro.ACTIVO.getValor());
			listado.setRowCount(peritoFacade.cantidadRegistros(peritoFiltro));
			abrir();
			perito = null;
		} catch (Exception e) {
			procesarError(e);
		}
	}
	
	public void consultar() {
		try {
			peritoFiltro.setEmpresasAvaluo(this.empresa);
			listado.setRowCount(peritoFacade.cantidadRegistros(peritoFiltro));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void asociarPerito(EmpresasAvaluos empresa) {
		try {
			this.empresa = empresa;
			guardarPerito = false;
			perito = new PeritosEmpresa();
			abrir();
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void desactivar() {
		try {
			if (seleccionados == null || seleccionados.isEmpty())
				throw mgrExc.lanzarExcepcion(93, TipoErrorNegocio.ALERTA);
			peritoFacade.desactivarPerito(seleccionados, getUsuario());
			listado.setRowCount(peritoFacade.cantidadRegistros(peritoFiltro));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void buscarPerito(ValueChangeEvent event) {
		try {
			guardarPerito = false;
			Long valor = (Long) event.getNewValue();
			if (valor == null)
				return;
			String nombre = peritoFacade.buscarNombrePerito(valor);
			guardarPerito = !UtilTexto.estaVacio(nombre);
			perito.setNombrePerito(nombre);
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void cancelar() {
		ocultar();
	}

	public void cancelarAsociacion() {
		perito = null;
		ocultar();
	}

	public void guardar() {
		try {
			if (!validarPerito())
				return;
			if (!guardarPerito)
				throw mgrExc.lanzarExcepcion(92, TipoErrorNegocio.ALERTA);
			perito.setEmpresasAvaluo(empresa);
			perito.setIdEmpresaAvaluo(empresa.getIdEmpresaAvaluo());
			peritoFacade.guardarPerito(perito, getUsuario());
			listado.setRowCount(peritoFacade.cantidadRegistros(peritoFiltro));
			mensajeConfirmacion(obtenerEtiqueta("menGuardar"));
			ocultar();
		} catch (Exception e) {
			procesarError(e);
		}
	}

	/***
	 * Permite realizar las validaciones de los campos de un perito a asociar a una
	 * empresa.
	 * 
	 * @return
	 */
	private boolean validarPerito() {
		boolean validar = true;
		validar &= UtilValidadorJsf.validar(perito.getTipoDocumento(), "peritoForm:tipoDocumentoPerito", true, 3, null);
		validar &= UtilValidadorJsf.validar(perito.getNumeroDocumento(), "peritoForm:numeroDocumentoPerito", true, 16,
				null);
		validar &= UtilValidadorJsf.validar(perito.getNombrePerito(), "peritoForm:nombrePerito", true, 60, null);
		return validar;
	}

	/*
	 * getters y setters
	 */
	public int getRegistroPagina() {
		return REGISTRO_PAGINA;
	}

	public EmpresasAvaluos getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresasAvaluos empresa) {
		this.empresa = empresa;
	}

	public PeritosEmpresa getPerito() {
		return perito;
	}

	public void setPerito(PeritosEmpresa perito) {
		this.perito = perito;
	}

	public boolean isEsNuevo() {
		return esNuevo;
	}

	public boolean isGuardarPerito() {
		return guardarPerito;
	}

	public LazyDataModel<PeritosEmpresa> getListado() {
		return listado;
	}

	public void setListado(LazyDataModel<PeritosEmpresa> listado) {
		this.listado = listado;
	}

	public List<PeritosEmpresa> getSeleccionados() {
		return seleccionados;
	}

	public void setSeleccionados(List<PeritosEmpresa> seleccionados) {
		this.seleccionados = seleccionados;
	}

	public PeritosEmpresa getPeritoFiltro() {
		return peritoFiltro;
	}

	public void setPeritoFiltro(PeritosEmpresa peritoFiltro) {
		this.peritoFiltro = peritoFiltro;
	}
	
	

}