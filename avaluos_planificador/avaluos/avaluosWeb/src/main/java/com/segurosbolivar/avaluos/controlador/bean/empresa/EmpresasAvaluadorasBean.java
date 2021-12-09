package com.segurosbolivar.avaluos.controlador.bean.empresa;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.controlador.bean.general.PopupAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilValidadorJsf;
import com.segurosbolivar.avaluos.modelo.entity.Archivo;
import com.segurosbolivar.avaluos.modelo.entity.EmpresasAvaluos;
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
public class EmpresasAvaluadorasBean extends PopupAbstractoBean implements Serializable{

	private static final long serialVersionUID = -8397592452639366101L;
	private LazyDataModel<EmpresasAvaluos> listado;
	private LazyDataModel<EmpresasAvaluos> valores;
	private EmpresasAvaluos empresa;
	private EmpresasAvaluos filtro;
	private boolean esNuevo;

	/**
	 * Define la cantidad de registros por pagina
	 */
	private static final int REGISTRO_PAGINA = 10;

	@EJB
	public IPeritoFacade peritoFacade;

	@Override
	public void inicio() {
		filtro = new EmpresasAvaluos();
		try {
			listado = new LazyDataModel<EmpresasAvaluos>() {

				private static final long serialVersionUID = -2678843134118216101L;

				@Override
				public List<EmpresasAvaluos> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, Object> filters) {
//						return peritoFacade.consultar(new EmpresasAvaluos(), first, pageSize, sortField,UtilJsf.resolverOrientacion(sortOrder));
					List<EmpresasAvaluos> empresas = peritoFacade.consultar(filtro, first, pageSize, sortField,
							UtilJsf.resolverOrientacion(sortOrder));
					return empresas;
				}

				@Override
				@SuppressWarnings("unchecked")
				public EmpresasAvaluos getRowData(String rowKey) {
					List<EmpresasAvaluos> empresas = (List<EmpresasAvaluos>) getWrappedData();
					for (EmpresasAvaluos empresa : empresas) {
						if (empresa.getIdEmpresaAvaluo().toString().equals(rowKey))
							return empresa;
					}
					return null;
				}

			};
			listado.setRowCount(peritoFacade.cantidadRegistros(new EmpresasAvaluos()));
			listado.setPageSize(REGISTRO_PAGINA);

			/**
			 * valores = new LazyDataModel<EmpresasAvaluos>() {
			 * 
			 * private static final long serialVersionUID = 186448530119942158L;
			 * 
			 * @Override public List<EmpresasAvaluos> load(int first, int pageSize, String
			 *           sortField, SortOrder sortOrder, Map<String, Object> filters) {
			 *           return peritoFacade.consultar(filtro, first, pageSize,sortField,
			 *           UtilJsf.resolverOrientacion(sortOrder)); } };
			 *           valores.setPageSize(REGISTRO_PAGINA);
			 **/
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void consultar() {
		try {
			listado.setRowCount(peritoFacade.cantidadRegistros(filtro));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void limpiar() {
		try {
			valores.setRowCount(peritoFacade.cantidadRegistros(empresa));
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
		return "dlg_EmpresaPopup";
	}

	public void nuevo() {
		empresa = new EmpresasAvaluos();
		empresa.setFirma(new Archivo());
		empresa.setLogo(new Archivo());
		esNuevo = true;
		abrir();
	}

	public void editar() {
		try {
			empresa = listado.getRowData().clone();
			if (empresa.getLogo() == null)
				empresa.setLogo(new Archivo());
			if (empresa.getFirma() == null)
				empresa.setFirma(new Archivo());
			if (empresa.getFirma().getContenidoArchivo() != null)
				empresa.getFirma().setModificado(true);
			if (empresa.getLogo().getContenidoArchivo() != null)
				empresa.getLogo().setModificado(true);
			esNuevo = false;
			abrir();
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public synchronized void subirFirma(FileUploadEvent event) {
		try {
			UploadedFile anexo = event.getFile();
			if (anexo == null) {
				return;
			}
			if (!UtilValidadorJsf.validar(anexo, true, "empresaForm:firma", null, new String[] { ".jpg", ".JPG" },
					10000L))
				return;
			empresa.getFirma().setAnexo(anexo.getContents());
			empresa.getFirma().setTamanioArchivo(anexo.getSize());
			empresa.getFirma().setNombreArchivo(anexo.getFileName());
			empresa.getFirma().setModificado(true);
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public synchronized void subirLogo(FileUploadEvent event) {
		try {
			UploadedFile anexo = event.getFile();
			if (anexo == null) {
				return;
			}
			if (!UtilValidadorJsf.validar(anexo, true, "empresaForm:logo", null, new String[] { ".jpg", ".JPG" },
					10000L))
				return;
			empresa.getLogo().setAnexo(anexo.getContents());
			empresa.getLogo().setTamanioArchivo(anexo.getSize());
			empresa.getLogo().setNombreArchivo(anexo.getFileName());
			empresa.getLogo().setModificado(true);
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void eliminarLogo() {
		empresa.getLogo().setAnexo(null);
		empresa.getLogo().setTamanioArchivo(null);
		empresa.getLogo().setNombreArchivo(null);
		empresa.getLogo().setModificado(true);
	}

	public void eliminarFirma() {
		empresa.getFirma().setAnexo(null);
		empresa.getFirma().setTamanioArchivo(null);
		empresa.getFirma().setNombreArchivo(null);
		empresa.getFirma().setModificado(true);
	}

	public void eliminar() {
		try {
			EmpresasAvaluos eliminar = listado.getRowData();
			if (eliminar == null)
				throw mgrExc.lanzarExcepcion(93, TipoErrorNegocio.ALERTA);
			peritoFacade.eliminarEmpresa(eliminar);
			listado.setRowCount(peritoFacade.cantidadRegistros(new EmpresasAvaluos()));
			mensajeConfirmacion(obtenerEtiqueta("menEliminar"));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void cancelar() {
		empresa = null;
		ocultar();
	}

	public void guardar() {
		try {
			if (!validar()) {
				return;
			}
			peritoFacade.guardarEmpresa(empresa, getUsuario());
			esNuevo = false;
			mensajeConfirmacion(obtenerEtiqueta("menGuardar"));
			empresa = null;
			ocultar();
		} catch (Exception e) {
			procesarError(e);
		}
	}

	private boolean validar() {
		boolean validar = true;
		validar &= UtilValidadorJsf.validar(empresa.getIdTipoIdentificacion(), "empresaForm:tipoDocumento", true, 3,
				null);
		validar &= UtilValidadorJsf.validar(empresa.getNumeroDocumento(), "empresaForm:numeroDocumento", true, 16,
				null);
		validar &= UtilValidadorJsf.validar(empresa.getDescEmpresa(), "empresaForm:descEmpresa", true, 150, null);
		validar &= UtilValidadorJsf.validar(empresa.getEstado(), "empresaForm:estado", true, 3, null);
		validar &= UtilValidadorJsf.validar(empresa.getSucursalDavivienda(), "empresaForm:sucursalDavivienda", true, 3,
				null);
		validar &= UtilValidadorJsf.validar(empresa.getRegistroSic(), "empresaForm:registroSic", true, 30, null);
		validar &= UtilValidadorJsf.validar(empresa.getRegistroPrivado(), "empresaForm:registroPrivado", true, 30,
				null);
		validar &= UtilValidadorJsf.validar(empresa.getCargaArchivoPlano(), "empresaForm:cargaArchivoPlano", true, 3,
				null);
		validar &= UtilValidadorJsf.validar(empresa.getFirma().getAnexo(), empresa.getFirma().getNombreArchivo(), true,
				"empresaForm:firma", null, new String[] { ".jpg", ".JPG" }, 1024L);
		validar &= UtilValidadorJsf.validar(empresa.getLogo().getAnexo(), empresa.getLogo().getNombreArchivo(), true,
				"empresaForm:logo", null, new String[] { ".jpg", ".JPG" }, 1024L);
		return validar;
	}

	public void asociarPerito() {
		try {
			PeritoBean.getBean().asociarPerito(listado.getRowData());
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void consultarPeritos() {
		try {
			PeritoBean.getBean().ver(listado.getRowData());
		} catch (Exception e) {
			procesarError(e);
		}
	}

	/***
	 * Permite consultar la imagen asociada a un archivo desde FILENET para que
	 * pueda ser mostrada en la pantalla.
	 * 
	 * @param archivo: del que intentaremos obtener la imagen.
	 * @return imagen del archivo solicitado.
	 * @throws NegocioException
	 * @throws IOException
	 */
	public StreamedContent obtenerImagen(Archivo archivo) throws NegocioException, IOException {
		if (archivo == null)
			return null;
		try {
			byte[] imagen = archivo.getAnexo() == null && !UtilTexto.estaVacio(archivo.getIdDocumento())
					? peritoFacade.obtenerImagen(archivo.getIdDocumento())
					: archivo.getAnexo() == null ? archivo.getContenidoArchivo() : archivo.getAnexo();
			archivo.setAnexo(imagen);
			if (imagen == null)
				return null;
			return new DefaultStreamedContent(new ByteArrayInputStream(imagen), "image/jpg",
					archivo.getNombreArchivo());
		} catch (Exception e) {
			procesarError(e);
			return null;
		}
	}

	/**
	 * Obtiene la imagen del logo para el listado de empresas.
	 * 
	 * @return imagen del logo de una empresa en especifico.
	 */
	public StreamedContent getImagenLogo() {
		try {
			EmpresasAvaluos fila = listado.getRowData();
			if (fila == null || fila.getLogo() == null)
				return null;
			return obtenerImagen(fila.getLogo());
		} catch (Exception e) {
			procesarError(e);
		}
		return null;
	}

	/**
	 * Obtiene la imagen de la firma para el listado de empresas.
	 * 
	 * @return imagen de la firma de una empresa en especifico.
	 */
	public StreamedContent getImagenFirma() {
		try {
			EmpresasAvaluos fila = listado.getRowData();
			if (fila == null || fila.getFirma() == null)
				return null;
			return obtenerImagen(fila.getFirma());
		} catch (Exception e) {
			procesarError(e);
		}
		return null;
	}

	/*
	 * getters y setters
	 */

	public EmpresasAvaluos getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresasAvaluos empresa) {
		this.empresa = empresa;
	}

	public EmpresasAvaluos getFiltro() {
		return filtro;
	}

	public void setFiltro(EmpresasAvaluos filtro) {
		this.empresa = filtro;
	}

	public boolean isEsNuevo() {
		return esNuevo;
	}

	public LazyDataModel<EmpresasAvaluos> getListado() {
		return listado;
	}

	public void setListado(LazyDataModel<EmpresasAvaluos> listado) {
		this.listado = listado;
	}

	public int getRegistroPagina() {
		return REGISTRO_PAGINA;
	}

}