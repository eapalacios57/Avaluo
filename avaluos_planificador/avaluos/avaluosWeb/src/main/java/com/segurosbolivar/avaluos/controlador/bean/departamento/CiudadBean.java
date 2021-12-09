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
import com.segurosbolivar.avaluos.modelo.entity.Ciudad;
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
public class CiudadBean extends PopupAbstractoBean implements Serializable{

	private static final long serialVersionUID = -5228402828270771408L;
	private Departamento departamento;
	private boolean esNuevo;
	private Ciudad ciudadFiltro;
	private LazyDataModel<Ciudad> listado;
	private Ciudad ciudad;
	
	@EJB
	public IParametrizacion parametrizacionSvc;
	/**
	 * Define la cantidad de registros por pagina
	 */
	private static final int REGISTRO_PAGINA = 10;

	public static CiudadBean getBean() {
		return UtilJsf.obtenerBackingBean("ciudadBean");
	}

	@Override
	public void inicio() {
		try {
			listado = new LazyDataModel<Ciudad>() {

				private static final long serialVersionUID = 186448530119942158L;

				@Override
				public List<Ciudad> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, Object> filters) {
					List<Ciudad> ciudades = parametrizacionSvc.consultarCiudades(ciudadFiltro, first, pageSize, sortField,UtilJsf.resolverOrientacion(sortOrder));
//					List<Ciudad> datos = new ArrayList<>();
//					for(Ciudad ciudad: ciudades) {
//						boolean match = true;
//						if(filters != null) {
//							
//							for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
//								try {
//			                        String filterProperty = it.next();
//			                        Object filterValue = filters.get(filterProperty);
//			                        String fieldValue = String.valueOf(UtilReflection.runGetter(filterProperty, ciudad));
//			                        if(filterValue == null || fieldValue.startsWith(filterValue.toString().toUpperCase())) {
//			                            match = true;
//			                    }
//			                    else {
//			                            match = false;
//			                            break;
//			                        }
//			                    } catch(Exception e) {
//			                        match = false;
//			                    }
//							}
//						}
//						
//						if(match) {
//			                datos.add(ciudad);
//			            }
//					}
					
//					int datosSize = datos.size();
//			        this.setRowCount(datosSize);
					
					return ciudades;
				}
			};
			listado.setPageSize(REGISTRO_PAGINA);
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
		return "dlg_CiudadPopup";
	}

	public void consultar(Departamento departamento) {
		try {
			this.departamento = departamento;
			ciudad = null;
			ciudadFiltro = new Ciudad();
			ciudadFiltro.setDepartamento(departamento);
			ciudadFiltro.setIdDepartamento(departamento.getIdDepartamento());
			listado.setRowCount(parametrizacionSvc.cantidadRegistros(ciudadFiltro));
			abrir();
		} catch (Exception e) {
			procesarError(e);
		}
	}
	
	public void consultar() {
		try {
			ciudadFiltro.setDepartamento(departamento);
			listado.setRowCount(parametrizacionSvc.cantidadRegistros(ciudadFiltro));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void nuevo() {
		ciudad = new Ciudad();
		esNuevo = true;
	}

	public void editar() {
		try {
			ciudad = listado.getRowData().clone();
			esNuevo = false;
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void eliminar() {
		try {
			Ciudad eliminar = listado.getRowData();
			if (eliminar == null)
				throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ALERTA);
			parametrizacionSvc.eliminarCiudad(eliminar);
			eliminar = null;
			listado.setRowCount(parametrizacionSvc.cantidadRegistros(ciudadFiltro));
			mensajeConfirmacion(obtenerEtiqueta("menEliminar"));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void cancelar() {
		departamento = null;
		ocultar();
	}

	public void cancelarEdicion() {
		ciudad = null;
	}

	public void guardar() {
		try {
			if (!validar()) {
				return;
			}
			ciudad.setIdDepartamento(departamento.getIdDepartamento());
			parametrizacionSvc.guardarCiudad(ciudad, getUsuario());
			listado.setRowCount(parametrizacionSvc.cantidadRegistros(ciudadFiltro));
			mensajeConfirmacion(obtenerEtiqueta("menGuardar"));
			ciudad = null;
		} catch (Exception e) {
			procesarError(e);
		}
	}

	private boolean validar() {
		boolean validar = true;
		validar &= UtilValidadorJsf.validar(ciudad.getCiudad(), "ciudadForm:ciudad", true, 22, null);
		validar &= UtilValidadorJsf.validar(ciudad.getCodAsobancaria(), "ciudadForm:codigoAsobancariaCiudad", true, 60,
				null);
		validar &= UtilValidadorJsf.validar(ciudad.getCodDane(), "ciudadForm:codigoDaneCiudad", false, 22, null);
		return validar;
	}

	/*
	 * getters y setters
	 */

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Ciudad getCiudadFiltro() {
		return ciudadFiltro;
	}

	public void setCiudadFiltro(Ciudad ciudadFiltro) {
		this.ciudadFiltro = ciudadFiltro;
	}

	public LazyDataModel<Ciudad> getCiudades() {
		return listado;
	}

	public void setCiudades(LazyDataModel<Ciudad> ciudades) {
		this.listado = ciudades;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public boolean isEsNuevo() {
		return esNuevo;
	}

	public int getRegistroPagina() {
		return REGISTRO_PAGINA;
	}

	public LazyDataModel<Ciudad> getListado() {
		return listado;
	}

	public void setListado(LazyDataModel<Ciudad> listado) {
		this.listado = listado;
	}
	
	

}