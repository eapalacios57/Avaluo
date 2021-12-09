package com.segurosbolivar.avaluos.controlador.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilNumero;
import com.segurosbolivar.avaluos.controlador.bean.general.PopupAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilValidadorJsf;
import com.segurosbolivar.avaluos.modelo.entity.Calendario;
import com.segurosbolivar.avaluos.modelo.service.IParametrizacion;

/**
 * Controlador para la pantalla del calendario que registra la fechas no habiles
 * en que no se procesara el archivo de procedatos respectivo para asobancaria.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:39 a.m.
 */
@ManagedBean
@SessionScoped
public class CalendarioBean extends PopupAbstractoBean implements Serializable{

	private static final long serialVersionUID = -3697462614351903909L;
	private List<Date> fechasNuevas;
	private List<SelectItem> anios;
	private LazyDataModel<Calendario> listado;
	private Calendario calendario;
	private Date fecha;
	private Integer anioCargar;
	private boolean creando;
	private DataTable tablaFechas;
	@EJB
	public IParametrizacion parametrizacionSvc;
	/**
	 * Define la cantidad de registros por pagina
	 */
	private static final int REGISTRO_PAGINA = 10;

	@Override
	public void inicio() {
		try {
			anioCargar=null;
			fecha = new Date();
			calendario = new Calendario();
			listado = new LazyDataModel<Calendario>() {

				private static final long serialVersionUID = -563659171394051783L;

				@Override
				public List<Calendario> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, Object> filters) {
					return parametrizacionSvc.consultarFechasCalendario(calendario, first, pageSize, sortField,
							UtilJsf.resolverOrientacion(sortOrder));
				}

			};
			listado.setRowCount(parametrizacionSvc.cantidadRegistros(calendario));
			listado.setPageSize(REGISTRO_PAGINA);
			cargarAnios();
		} catch (Exception e) {
			procesarError(e);
		}

	}

	private void cargarAnios() throws NegocioException {
		anios = new ArrayList<>();
		List<Long> listadoAnios = parametrizacionSvc.consultarAniosCalendario();
		if (listadoAnios == null)
			return;
		for (Long anio : listadoAnios) {
			anios.add(new SelectItem(anio, anio.toString()));
		}
	}

	@Override
	public String getPermiso() {
		return null;
	}

	@Override
	public String getPopupId() {
		return "dlg_CalendarioPopup";
	}

	public void eliminar() {
		try {
			Calendario eliminar = listado.getRowData();
			if (eliminar == null) {
				throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ALERTA);
			}
			parametrizacionSvc.eliminarFechaCalendario(eliminar);
			eliminar = null;
			listado.setRowCount(parametrizacionSvc.cantidadRegistros(calendario));
			cargarAnios();
			mensajeConfirmacion(obtenerEtiqueta("menEliminar"));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void filtrar(ValueChangeEvent event) {
		Long valor = UtilNumero.pasarEntero((String) event.getNewValue());
		if (valor == 0L)
			valor = null;
		calendario.setAnio(valor);
		listado.setRowCount(parametrizacionSvc.cantidadRegistros(calendario));
	}

	public void nuevo() {
		fechasNuevas = new ArrayList<>();
		creando = true;
		abrir();
	}

	public void cargar() {
		try {
		if(!UtilValidadorJsf.validar(anioCargar, "calendarioConsultarForm:anioCargar",true, 4,null))
			return;
		parametrizacionSvc.cargarAnio(anioCargar,getUsuario());
		anioCargar=null;
		listado.setRowCount(parametrizacionSvc.cantidadRegistros(calendario));
		cargarAnios();
		mensajeConfirmacion(obtenerEtiqueta("menCargarAnio"));
		}catch (Exception e) {
			procesarError(e);
		}
	}

	public void agregar(SelectEvent event) {
		try {
			if (fechasNuevas != null && fechasNuevas.size() >= 10)
				throw mgrExc.lanzarExcepcion(104, TipoErrorNegocio.INFO);
			Date agregar = (Date) event.getObject();
			if (!fechasNuevas.contains(agregar))
				fechasNuevas.add(agregar);
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void quitar() {
		try {
			fechasNuevas.remove(tablaFechas.getRowData());
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void cancelar() {
		creando = false;
		ocultar();
	}

	public void guardar() {
		try {
			parametrizacionSvc.guardarFechasCalendario(fechasNuevas, getUsuario());
			listado.setRowCount(parametrizacionSvc.cantidadRegistros(calendario));
			listado.load(0, REGISTRO_PAGINA, null, null, null);
			cargarAnios();
			cancelar();
			mensajeConfirmacion(obtenerEtiqueta("menGuardar"));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	/*
	 * getters y setters
	 */

	public List<Date> getFechasNuevas() {
		return fechasNuevas;
	}

	public void setFechasNuevas(List<Date> fechasNuevas) {
		this.fechasNuevas = fechasNuevas;
	}

	public LazyDataModel<Calendario> getListado() {
		return listado;
	}

	public void setListado(LazyDataModel<Calendario> listado) {
		this.listado = listado;
	}

	public Calendario getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}

	public int getRegistroPagina() {
		return REGISTRO_PAGINA;
	}

	public DataTable getTablaFechas() {
		return tablaFechas;
	}

	public void setTablaFechas(DataTable tablaFechas) {
		this.tablaFechas = tablaFechas;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean isCreando() {
		return creando;
	}

	public List<SelectItem> getAnios() {
		return anios;
	}

	public void setAnios(List<SelectItem> anios) {
		this.anios = anios;
	}

	public Integer getAnioCargar() {
		return anioCargar;
	}

	public void setAnioCargar(Integer anioCargar) {
		this.anioCargar = anioCargar;
	}

}