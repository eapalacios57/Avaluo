package com.segurosbolivar.avaluos.controlador.bean.general;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.modelo.entity.Ciudad;
import com.segurosbolivar.avaluos.modelo.entity.Departamento;
import com.segurosbolivar.avaluos.modelo.entity.Dominios;
import com.segurosbolivar.avaluos.modelo.service.IParametrizacion;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;

/**
 * Este controlador permite realizar funciones sobre los principales listados
 * del sistema y obtener los valores de los dominios para los diferentes
 * formularios.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:44 a.m.
 */
@ManagedBean(name = "lista")
@ApplicationScoped
public class ListaBean extends GeneralAbstractoBean implements Serializable{

	private static final long serialVersionUID = 8945199440691480420L;

	@EJB
	public transient IParametrizacion parametrizacionSvc;

	/**
	 * Permite obtener la referencia del bean por otros backingBeans para realizar
	 * procesos de este controlador.
	 * 
	 * @return la instancia actual del controlador de lista.
	 */
	public static ListaBean getBean() {
		return UtilJsf.obtenerBackingBean("lista");
	}

	@Override
	public void inicio() {
		// No se necesita inicializar ningun listado.
	}

	@Override
	public String getPermiso() {
		return null;
	}

	public String getObtenerContexto() {
		return UtilJsf.getContexto();
	}

	/**
	 * Este metodo permite obtener una lista de dominios del sistema, si la lista
	 * del sistema no se encuentra en la cache el sistema consulta la misma desde la
	 * base de datos y la carga en memoria para mantenerla disponible en el sistema.
	 * La lista que se carga esta disponible para usarla como select item.
	 * 
	 * @param llave:
	 *            nombres o alias de la lista de dominio a cargar.
	 * @return lista de dominio.
	 */
	@SuppressWarnings("unchecked")
	public synchronized List<SelectItem> cargarLista(String llave) {
		try {
			List<?> lista = parametrizacionSvc.obtenerListadoCache(llave);
			if (lista == null || lista.isEmpty())
				return Collections.emptyList();
			if (lista.get(0) instanceof SelectItem)
				return (List<SelectItem>) lista;
			List<SelectItem> retorno = new ArrayList<>();
			if (lista.get(0) instanceof Dominios) {
				for (Dominios valor : (List<Dominios>) lista) {
					retorno.add(new SelectItem(valor.getRvLowValue(), valor.getRvMeaning()));
				}
			}
			if (lista.get(0) instanceof Departamento) {
				for (Departamento departamento : (List<Departamento>) lista) {
					retorno.add(new SelectItem(departamento.getIdDepartamento(), departamento.getDepartamento(),
							departamento.getCodDane().toString()));
				}
			}
			if (lista.get(0) instanceof Ciudad) {
				for (Ciudad ciudad : (List<Ciudad>) lista) {
					retorno.add(
							new SelectItem(ciudad.getIdCiudad(), ciudad.getCiudad(), ciudad.getCodDane().toString()));
				}
			}
			parametrizacionSvc.agregarElementoCache(llave, retorno);
			return retorno;
		} catch (NegocioException e) {
			procesarError(e);
		}
		return null;
	}

	/**
	 * Permite actualizar un listado de dominios en caso de que creamos que el mismo
	 * a sufrido cambios.
	 * 
	 * @param llave
	 *            identificador del dominio a actualizar.
	 */
	public synchronized void actualizarLista(String llave) {
		List<Dominios> dominios = parametrizacionSvc.consultarValoresDominio(llave, 0, Integer.MAX_VALUE, null, null);
		if (dominios == null)
			return;
		List<SelectItem> lista = new ArrayList<>();
		for (Dominios valor : (List<Dominios>) dominios) {
			lista.add(new SelectItem(valor.getRvLowValue(), valor.getRvMeaning()));
		}
		// se actualizar
		agregarLista(llave, lista);
	}

	public synchronized void agregarLista(String llave, List<SelectItem> listado) {
		try {
			parametrizacionSvc.agregarElementoCache(llave, listado);
		} catch (NegocioException e) {
			procesarError(e);
		}
	}

	public synchronized String descDominio(String llave, Object valor) {
		List<SelectItem> lista = cargarLista(llave);
		if (lista == null || valor == null)
			return "";
		for (SelectItem selectItem : lista) {
			if (selectItem.getValue() instanceof String && selectItem.getValue().equals(valor.toString()))
				return selectItem.getLabel();
			if (selectItem.getValue() instanceof Long && selectItem.getValue().equals(valor))
				return selectItem.getLabel();
		}
		return "";
	}

	@SuppressWarnings("unchecked")
	public List<Ciudad> obtenerCiudades() {
		try {
			return (List<Ciudad>) parametrizacionSvc
					.obtenerListadoCache(UtilConstantes.CACHE_CIUDADES + UtilConstantes.SUFIJO_DOMINIOS);
		} catch (Exception e) {
			procesarError(e);
		}
		return Collections.emptyList();
	}

	@SuppressWarnings("unchecked")
	public List<Departamento> obtenerDepartamentos() {
		try {
			return (List<Departamento>) parametrizacionSvc
					.obtenerListadoCache(UtilConstantes.CACHE_DEPARTAMENTOS + UtilConstantes.SUFIJO_DOMINIOS);
		} catch (Exception e) {
			procesarError(e);
		}
		return Collections.emptyList();
	}

	public Ciudad obtenerCiudad(Long id, Long dane) {
		if (id == null && dane == null) {
			return null;
		}
		List<Ciudad> listado = obtenerCiudades();
		if (listado == null) {
			return null;
		}
		for (Ciudad ciudad : listado) {
			if (id != null && ciudad.getIdCiudad() != null && id.compareTo(ciudad.getIdCiudad()) == 0)
				return ciudad;
			if (dane != null && ciudad.getCodDane() != null && dane.compareTo(ciudad.getCodDane()) == 0)
				return ciudad;
		}
		return null;
	}

	public Departamento obtenerDepartamento(Long id, Long dane) {
		if (id == null && dane == null) {
			return null;
		}
		List<Departamento> listado = obtenerDepartamentos();
		if (listado == null) {
			return null;
		}
		for (Departamento departamento : listado) {
			if (id != null && departamento.getIdDepartamento() != null
					&& id.compareTo(departamento.getIdDepartamento()) == 0)
				return departamento;
			if (dane != null && departamento.getCodDane() != null && dane.compareTo(departamento.getCodDane()) == 0)
				return departamento;
		}
		return null;
	}

	public List<SelectItem> cargarCiudades(Long departamento) {
		List<SelectItem> ciudades = new ArrayList<>();
		try {
			List<Ciudad> lista = obtenerCiudades();
			if (lista == null || lista.isEmpty())
				return ciudades;
			for (Ciudad ciudad : lista) {
				if (departamento == null || departamento.compareTo(ciudad.getIdDepartamento()) == 0)
					ciudades.add(
							new SelectItem(ciudad.getIdCiudad(), ciudad.getCiudad(), ciudad.getCodDane().toString()));
			}
		} catch (Exception e) {
			procesarError(e);
		}
		return ciudades;
	}

	@SuppressWarnings("unchecked")
	public List<SelectItem> cargarDependiente(String llave, String padre) {
		List<SelectItem> retornar = new ArrayList<>();
		try {
			if (UtilTexto.estaVacio(padre))
				return cargarLista(llave);
			// consultamos la lista base de los dominios. Para poder realizar el
			// filtro.
			List<Dominios> lista = (List<Dominios>) parametrizacionSvc
					.obtenerListadoCache(llave + UtilConstantes.SUFIJO_DOMINIOS);
			for (Dominios dominio : lista) {
				if (!UtilTexto.estaVacio(dominio.getRvAbbreviation())
						&& padre.trim().equals(dominio.getRvAbbreviation().trim()))
					retornar.add(new SelectItem(dominio.getRvLowValue(), dominio.getRvMeaning()));
			}
		} catch (NegocioException e) {
			procesarError(e);
		}
		return retornar;
	}

}
