package com.segurosbolivar.avaluos.controlador.bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.asesoftware.util.cons.TipoComparacion;
import com.asesoftware.util.cons.TipoErrorNegocio;
import com.segurosbolivar.avaluos.controlador.bean.general.GeneralAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilValidadorJsf;
import com.segurosbolivar.avaluos.modelo.entity.HorasCorteArchivo;
import com.segurosbolivar.avaluos.modelo.service.IParametrizacion;

/**
 * Este controlador permite gestionar la ventana en la que se registran las
 * horas de corte en que se realizan los archivos a trasmitir a la asobancaria.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:42 a.m.
 */
@ManagedBean
@SessionScoped
public class HoraCorteBean extends GeneralAbstractoBean implements Serializable{

	private static final long serialVersionUID = -4903642126321763967L;
	/**
	 * entidad que represanta una hora de corte y q permite realizar el registro y
	 * modificacion de una hora del sistema.
	 */
	private HorasCorteArchivo hora;
	/**
	 * Representa el listado de las horas de corte del archivo de ASOBANCARIA
	 */
	private LazyDataModel<HorasCorteArchivo> listado;
	/**
	 * Permite determinar si la accion a realizar sobre el formulario es crear una
	 * hora nueva o actualizarla.
	 */
	private boolean esNuevo;
	private boolean esEditanto;
	/**
	 * Implementaicon del servicio de parametrizacion
	 */
	@EJB
	public IParametrizacion parametrizacionSvc;

	/**
	 * Define la cantidad de registros por pagina
	 */
	private static final int REGISTRO_PAGINA = 10;

	/**
	 * consultamos las horas e inicializamos el formulario vacio.
	 */
	@Override
	public void inicio() {
		// Creamos una clase anonima a la cual sobre escribimos el metodo LOAD()
		// para realizar la paginacion a traves de este.
		listado = new LazyDataModel<HorasCorteArchivo>() {

			private static final long serialVersionUID = 4953411898226086278L;

			@Override
			public List<HorasCorteArchivo> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				List<HorasCorteArchivo> retorno = parametrizacionSvc.consultarHoras(new HorasCorteArchivo(), first,
						pageSize, sortField, UtilJsf.resolverOrientacion(sortOrder));
				if (retorno == null || retorno.isEmpty())
					return Collections.emptyList();
				return retorno;
			}

			@Override
			@SuppressWarnings("unchecked")
			public HorasCorteArchivo getRowData(String rowKey) {
				List<HorasCorteArchivo> listado = (List<HorasCorteArchivo>) getWrappedData();
				for (HorasCorteArchivo hora : listado) {
					if (hora.getIdHorasCorteArchivo().toString().equals(rowKey))
						return hora;
				}
				return null;
			}

			@Override
			public Object getRowKey(HorasCorteArchivo object) {
				return object.getIdHorasCorteArchivo();
			}

		};
		// al listado debe siempre consultarse la cantidad de registros totales
		listado.setRowCount(parametrizacionSvc.cantidadRegistros(new HorasCorteArchivo()));
		// y determinar la cantidad de registros por pagina.
		listado.setPageSize(REGISTRO_PAGINA);
		hora = null;
	}

	/***
	 * Permite limpiar el formulario e inicializar la pantalla.
	 */
	public void limpiar() {
		try {
			cancelar();
			listado.setRowCount(parametrizacionSvc.cantidadRegistros(new HorasCorteArchivo()));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	@Override
	public String getPermiso() {
		return null;
	}

	/**
	 * Cancela el registro o edicion del formulario de hora de corte. Nos devuelve
	 * al listado principal.
	 */
	public void cancelar() {
		hora = null;
		esNuevo = false;
		esEditanto = false;
	}

	/**
	 * Permite obtener la hora seleccionada del listado para su edicion, se crea un
	 * clon del registro para evitar que se modifique el objeto sin guardarlo.
	 */
	public void editar() {
		try {
			hora = listado.getRowData();
			esNuevo = false;
			esEditanto = true;
		} catch (Exception e) {
			procesarError(e);
		}
	}

	/**
	 * Nos muestra el formulario de registro para crear una hora de corte nueva.
	 */
	public void nuevo() {
		hora = new HorasCorteArchivo();
		esNuevo = true;
		esEditanto = true;
	}

	/**
	 * Obtiene la hora de corte seleccionada por el usuario y la elimina.
	 */
	public void eliminar() {
		try {
			HorasCorteArchivo eliminar = listado.getRowData();
			if (eliminar == null)
				throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ALERTA);
			parametrizacionSvc.eliminarHora(eliminar);
			limpiar();
			mensajeConfirmacion(obtenerEtiqueta("menEliminar"));
			parametrizacionSvc.iniciarJobProcedatos();
		} catch (Exception e) {
			procesarError(e);
		}
	}

	/**
	 * Permite realizar el almacenamiento de la hora ya sea para la creacion de un
	 * registro nuevo o la actualizacion de un existente. Previamente antes de
	 * guardar el registro realiza una validacion de los campos de la hora.
	 */
	public void guardar() {
		try {
			if (hora == null || !validar(hora, null))
				return;
			if (hora != null)
				parametrizacionSvc.guardarHora(hora, getUsuario());
			limpiar();
			mensajeConfirmacion(obtenerEtiqueta("menGuardar"));
			parametrizacionSvc.iniciarJobProcedatos();
		} catch (Exception e) {
			procesarError(e);
		}
	}

	/**
	 * Permite realizar las validaciones del registro de hora antes de que el mismo
	 * sea almacenado, si alguna de las condiciones sobre el registro no funciona el
	 * sistema debera rechazar la operacion
	 * 
	 * @return verdadero si cumple con todas las validaciones.
	 */
	private boolean validar(HorasCorteArchivo hora, Long fila) {
		boolean validar = true;
		validar &= UtilValidadorJsf.validar(hora.getEstado(),
				"horaForm:HoraData:" + (fila == null ? "" : fila) + "estado", true, 1, null);
		validar &= UtilValidadorJsf.validar(hora.getHoraCorte(),
				"horaForm:HoraData:" + (fila == null ? "" : fila) + "hora", true, 2, null);
		validar &= UtilValidadorJsf.comparar(hora.getHoraCorte(), TipoComparacion.MENOR_IGUAL, 24L,
				"horaForm:HoraData:" + (fila == null ? "" : fila) + "hora", null);
		validar &= UtilValidadorJsf.comparar(hora.getHoraCorte(), TipoComparacion.MAYOR_IGUAL, 0L,
				"horaForm:HoraData:" + (fila == null ? "" : fila) + "hora", null);
		validar &= UtilValidadorJsf.validar(hora.getMinutosCorte(),
				"horaForm:HoraData:" + (fila == null ? "" : fila) + "minuto", true, 2, null);
		validar &= UtilValidadorJsf.comparar(hora.getMinutosCorte(), TipoComparacion.MENOR_IGUAL, 59L,
				"horaForm:HoraData:" + (fila == null ? "" : fila) + "minuto", null);
		validar &= UtilValidadorJsf.comparar(hora.getMinutosCorte(), TipoComparacion.MAYOR_IGUAL, 0L,
				"horaForm:HoraData:" + (fila == null ? "" : fila) + "minuto", null);
		return validar;
	}
	/*
	 * getters y setters
	 */

	public HorasCorteArchivo getHora() {
		return hora;
	}

	public void setHora(HorasCorteArchivo hora) {
		this.hora = hora;
	}

	public LazyDataModel<HorasCorteArchivo> getListado() {
		return listado;
	}

	public void setListado(LazyDataModel<HorasCorteArchivo> listado) {
		this.listado = listado;
	}

	public boolean isEsNuevo() {
		return esNuevo;
	}

	public void setEsNuevo(boolean esNuevo) {
		this.esNuevo = esNuevo;
	}

	public int getRegistroPagina() {
		return REGISTRO_PAGINA;
	}

	public boolean isEsEditanto() {
		return esEditanto;
	}

}