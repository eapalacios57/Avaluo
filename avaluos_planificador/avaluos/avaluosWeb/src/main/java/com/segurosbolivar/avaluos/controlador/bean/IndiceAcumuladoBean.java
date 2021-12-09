package com.segurosbolivar.avaluos.controlador.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.segurosbolivar.avaluos.controlador.bean.general.ListaBean;
import com.segurosbolivar.avaluos.controlador.bean.general.PopupAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilValidadorJsf;
import com.segurosbolivar.avaluos.modelo.entity.Ciudad;
import com.segurosbolivar.avaluos.modelo.entity.Departamento;
import com.segurosbolivar.avaluos.modelo.entity.IndiceAcumulado;
import com.segurosbolivar.avaluos.modelo.service.IParametrizacion;

/**
 * Este controlador permite gestionar la ventana en la que se registran los
 * indices acumulados del sistema los cuales permiten realizar un control en
 * cuanto al precio del inmueble.
 *
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:43 a.m.
 */
@ManagedBean
@SessionScoped
public class IndiceAcumuladoBean extends PopupAbstractoBean implements Serializable{

	private static final long serialVersionUID = -9161985642302020027L;
	private LazyDataModel<IndiceAcumulado> listado;
	private List<SelectItem> ciudades;
	/**
	 * Define la cantidad de registros por pagina
	 */
	private static final int REGISTRO_PAGINA = 10;
	private boolean esNuevo;
	private IndiceAcumulado indice;
	private IndiceAcumulado filtro;
	private Ciudad ciudad;
	private Departamento departamento;
	private List<SelectItem> estratos;
	private String estratoEscogido;
	private boolean verEstrato;
	@EJB
	private IParametrizacion parametrizacionSvc;

	@Override
	public void inicio() {
		try {
			// Creamos una clase anonima a la cual sobre escribimos el metodo
			// LOAD()
			// para realizar la paginacion a traves de este.
			filtro = new IndiceAcumulado();
			ciudad = new Ciudad();
			departamento = new Departamento();
			filtro.setCiudad(ciudad);
			filtro.setDepartamento(departamento);
			if(estratos == null) {
				estratos = ListaBean.getBean().cargarLista("ESTRATO_INDICE");
			}
			
			listado = new LazyDataModel<IndiceAcumulado>() {

				private static final long serialVersionUID = -7755926644842479683L;

				@Override
				public List<IndiceAcumulado> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, Object> filters) {
					List<IndiceAcumulado> indices = parametrizacionSvc.consultarIndices(filtro, first, pageSize, sortField,UtilJsf.resolverOrientacion(sortOrder));
					return indices;
				}

			};
			// al listado debe siempre consultarse la cantidad de registros totales y determinar la cantidad de registros por pagina.
			listado.setRowCount(parametrizacionSvc.cantidadRegistros(filtro));
			listado.setPageSize(REGISTRO_PAGINA);
			indice = null;
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
	
	public void cambiaEstrato(ValueChangeEvent evento) {
		if(evento.getNewValue() != null) {
			Long estratoFiltro = Long.valueOf((String)evento.getNewValue());
			this.filtro.setEstrato(estratoFiltro);
			consultar();
		}else {
			this.filtro = new IndiceAcumulado();
			listado.setRowCount(parametrizacionSvc.cantidadRegistros(filtro));
		}
	}
	
	/***
	 * Permite limpiar el formulario e inicializar la pantalla.
	 */
	public void limpiar() {
		try {
			indice = null;
			listado.setRowCount(parametrizacionSvc.cantidadRegistros(new IndiceAcumulado()));
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
		return "dlg_IndicePopup";
	}

	public IndiceAcumulado getFiltro() {
		return filtro;
	}

	public void setFiltro(IndiceAcumulado filtro) {
		this.filtro = filtro;
	}

	/**
	 * Cancela el registro o edicion del formulario de indice acumulado. Nos
	 * devuelve al listado principal.
	 */
	public void cancelar() {
		indice = null;
		ocultar();
	}

	/**
	 * Permite obtener el indice seleccionado del listado para su edicion, se crea
	 * un clon del registro para evitar que se modifique el objeto sin guardarlo.
	 */
	public void editar() {
		try {
			indice = listado.getRowData().clone();
			ciudades = ListaBean.getBean().cargarCiudades(indice.getIdDepartamento());
			esNuevo = false;
			verEstrato = indice.getIdCiudad() == 2695L;
			abrir();
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void cambiarDepartamento(ValueChangeEvent evento) {
		if (!evento.getNewValue().equals(evento.getOldValue()))
			indice.setIdCiudad(null);
		Long valor = (Long) evento.getNewValue();
		verEstrato = false;
		if (valor == null) {
			ciudades = new ArrayList<>();
			return;
		}
		ciudades = ListaBean.getBean().cargarCiudades(valor);
	}

	public void cambiarCiudad(ValueChangeEvent evento) {
		Long valor = (Long) evento.getNewValue();
		verEstrato = valor != null && valor == 2695L;
	}

	/**
	 * Nos muestra el formulario de registro para crear un nuevo indice.
	 */
	public void nuevo() {
		indice = new IndiceAcumulado();
		ciudades = new ArrayList<>();
		esNuevo = true;
		verEstrato = false;
		abrir();
	}

	/**
	 * Obtiene el indice seleccionado por el usuario y lo elimina.
	 */
	public void eliminar() {
		try {
			IndiceAcumulado eliminar = listado.getRowData();
			if (eliminar == null) {
				throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ALERTA);
			}
			parametrizacionSvc.eliminarIndice(eliminar);
			limpiar();
			mensajeConfirmacion(obtenerEtiqueta("menEliminar"));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	/**
	 * Permite realizar el almacenamiento del indice ya sea para la creacion de un
	 * registro nuevo o la actualizacion de un existente. Previamente antes de
	 * guardar el registro realiza una validacion de los campos.
	 */
	public void guardar() {
		try {
			if (!validar()) {
				return;
			}
			parametrizacionSvc.guardarIndice(indice, getUsuario());
			limpiar();
			ocultar();
			mensajeConfirmacion(obtenerEtiqueta("menGuardar"));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	/**
	 * Permite realizar las validaciones del registro de indice antes de que el
	 * mismo sea almacenado, si alguna de las condiciones sobre el registro no
	 * funciona el sistema debera rechazar la operacion
	 * 
	 * @return verdadero si cumple con todas las validaciones.
	 */
	private boolean validar() {
		boolean validar = true;
		validar &= UtilValidadorJsf.validar(indice.getAnio(), "indiceForm:anio", true, 10, null);
		validar &= UtilValidadorJsf.validar(indice.getIdDepartamento(), "indiceForm:departamento", true, 13, null);
		validar &= UtilValidadorJsf.validar(indice.getIdCiudad(), "indiceForm:ciudad", true, 13, null);
		validar &= UtilValidadorJsf.validar(indice.getValor(), "indiceForm:valor", true, 6, 2, null);
		validar &= UtilValidadorJsf.validar(indice.getEstrato(), "indiceForm:estrato", verEstrato, 12, "estrato");
		return validar;
	}
	/*
	 * getters y setters
	 */

	public LazyDataModel<IndiceAcumulado> getListado() {
		return listado;
	}

	public void setListado(LazyDataModel<IndiceAcumulado> listado) {
		this.listado = listado;
	}

	public IndiceAcumulado getIndice() {
		return indice;
	}

	public void setIndice(IndiceAcumulado indice) {
		this.indice = indice;
	}

	public int getRegistroPagina() {
		return REGISTRO_PAGINA;
	}

	public boolean isEsNuevo() {
		return esNuevo;
	}

	public List<SelectItem> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<SelectItem> ciudades) {
		this.ciudades = ciudades;
	}

	public boolean isVerEstrato() {
		return verEstrato;
	}

	public List<SelectItem> getEstratos() {
		return estratos;
	}

	public void setEstratos(List<SelectItem> estratos) {
		this.estratos = estratos;
	}

	public String getEstratoEscogido() {
		return estratoEscogido;
	}

	public void setEstratoEscogido(String estratoEscogido) {
		this.estratoEscogido = estratoEscogido;
	}
	
	
	
	

}