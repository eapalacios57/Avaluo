package com.segurosbolivar.avaluos.controlador.bean.departamento;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.segurosbolivar.avaluos.controlador.bean.general.PopupAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilValidadorJsf;
import com.segurosbolivar.avaluos.modelo.entity.Departamento;
import com.segurosbolivar.avaluos.modelo.service.IParametrizacion;

/**
 * Controlador para la vista que permite la gesti�n de departamentos y ciudades
 * del sistema.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:41 a.m.
 */
@ManagedBean
@SessionScoped
public class DepartamentoBean extends PopupAbstractoBean implements Serializable{

	private static final long serialVersionUID = -5228402828270771408L;
	private LazyDataModel<Departamento> listado;
	private Departamento departamento;
	private Departamento filtro;
	private boolean esNuevo;
	@EJB
	public IParametrizacion parametrizacionSvc;
	/**
	 * Define la cantidad de registros por pagina
	 */
	private static final int REGISTRO_PAGINA = 10;

	@Override
	public void inicio() {
		try {
			filtro = new Departamento();
			listado = new LazyDataModel<Departamento>() {

				private static final long serialVersionUID = -563659171394051783L;

				@Override
				public List<Departamento> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, Object> filters) {
					return parametrizacionSvc.consultarDepartamentos(filtro, first, pageSize,sortField,
							UtilJsf.resolverOrientacion(sortOrder));
				}

				@Override
				@SuppressWarnings("unchecked")
				public Departamento getRowData(String rowKey) {
					List<Departamento> listado = (List<Departamento>) getWrappedData();
					for (Departamento elemento : listado) {
						if (elemento.getIdDepartamento().toString().equals(rowKey))
							return elemento;
					}
					return null;
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

	public Departamento getFiltro() {
		return filtro;
	}

	public void setFiltro(Departamento filtro) {
		this.filtro = filtro;
	}

	@Override
	public String getPermiso() {
		return null;
	}

	@Override
	public String getPopupId() {
		return "dlg_DepartamentoPopup";
	}

	public void nuevo() {
		departamento = new Departamento();
		esNuevo = true;
		abrir();
	}

	public void editar() {
		try {
			departamento = listado.getRowData().clone();
			esNuevo = false;
			abrir();
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void sincronizar() {
		try {
			parametrizacionSvc.sincronizarCiudades(getUsuario());
			listado.setRowCount(parametrizacionSvc.cantidadRegistros(new Departamento()));
			mensajeConfirmacion(obtenerEtiqueta("menSincronizacionExitosa"));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void verCiudades() {
		try {
			departamento = listado.getRowData().clone();
			if (departamento == null)
				throw mgrExc.lanzarExcepcion(93, TipoErrorNegocio.ALERTA);
			CiudadBean.getBean().consultar(departamento);
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void eliminar() {
		try {
			Departamento eliminar = listado.getRowData();
			if (eliminar == null)
				throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ALERTA);
			parametrizacionSvc.eliminarDepartamento(eliminar);
			listado.setRowCount(parametrizacionSvc.cantidadRegistros(new Departamento()));
			eliminar = null;
			mensajeConfirmacion(obtenerEtiqueta("menEliminar"));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void cancelar() {
		departamento = null;
		filtro = new Departamento();
		ocultar();
	}

	public void guardar() {
		try {
			if (!validar())
				return;
			parametrizacionSvc.guardarDepartamento(departamento, getUsuario());
			listado.setRowCount(parametrizacionSvc.cantidadRegistros(new Departamento()));
			mensajeConfirmacion(obtenerEtiqueta("menGuardar"));
			esNuevo = false;
			departamento = null;
			ocultar();
		} catch (Exception e) {
			procesarError(e);
		}
	}

	private boolean validar() {
		boolean validar = true;
		validar &= UtilValidadorJsf.validar(departamento.getCodAsobancaria(), "departamentoForm:codigoAsobancaria",
				true, 22, null);
		validar &= UtilValidadorJsf.validar(departamento.getDepartamento(), "departamentoForm:departamento", true, 60,
				null);
		validar &= UtilValidadorJsf.validar(departamento.getCodDivpol(), "departamentoForm:codigoDivpol", false, 22,
				null);
		validar &= UtilValidadorJsf.validar(departamento.getCodDane(), "departamentoForm:codigoDane", false, 22, null);
		return validar;
	}

	/*
	 * getters y setters
	 */

	public LazyDataModel<Departamento> getListado() {
		return listado;
	}

	public void setListado(LazyDataModel<Departamento> listado) {
		this.listado = listado;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public int getRegistroPagina() {
		return REGISTRO_PAGINA;
	}

	public boolean isEsNuevo() {
		return esNuevo;
	}

}