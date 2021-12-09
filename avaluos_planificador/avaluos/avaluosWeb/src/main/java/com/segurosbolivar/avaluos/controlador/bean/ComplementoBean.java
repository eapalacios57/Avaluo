package com.segurosbolivar.avaluos.controlador.bean;

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
import com.segurosbolivar.avaluos.cons.AvaluosCons;
import com.segurosbolivar.avaluos.controlador.bean.general.GeneralAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.general.ListaBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilValidadorJsf;
import com.segurosbolivar.avaluos.modelo.entity.Archivo;
import com.segurosbolivar.avaluos.modelo.entity.ComplementosExcel;
import com.segurosbolivar.avaluos.modelo.facade.IComplementosFacade;

/**
 * Controlador para el módulo de manuales y complementos. Permite la carga y
 * descarga de documentos.
 * 
 * @author Luis Contreras
 * @version 1.0
 */
@ManagedBean
@SessionScoped
public class ComplementoBean extends GeneralAbstractoBean implements Serializable{

    private static final long serialVersionUID = -8101101459834208895L;
    private LazyDataModel<ComplementosExcel> listado;
    private String descripcion;
    private UploadedFile archivo;
    private String urlCapacitacion = AvaluosCons.URL_CAPACITACION;
    /**
     * Define la cantidad de registros por pagina
     */
    private static final int REGISTRO_PAGINA = 10;
    @EJB
    public IComplementosFacade complementosFacade;

    @Override
    public void inicio() {
	limpiar();
	urlCapacitacion = urlCapacitacion.replace(AvaluosCons.PARAMETRO_USUARIO_CAPACITACION, ListaBean.getBean().descDominio(AvaluosCons.DOMINIO_USER_CAPACITACION, 1));
	urlCapacitacion = urlCapacitacion.replace(AvaluosCons.PARAMETRO_PWD_CAPACITACION, ListaBean.getBean().descDominio(AvaluosCons.DOMINIO_USER_CAPACITACION, 2));
	try {
	    listado = new LazyDataModel<ComplementosExcel>() {

		private static final long serialVersionUID = 2079021544952975996L;

		@Override
		public List<ComplementosExcel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		    try {
			return complementosFacade.consultar(new ComplementosExcel(), first, pageSize, sortField, UtilJsf.resolverOrientacion(sortOrder));
		    } catch (NegocioException e) {
			procesarError(e);
			return null;
		    }
		}

	    };
	    listado.setRowCount(complementosFacade.cantidadRegistros(new ComplementosExcel()));
	    listado.setPageSize(REGISTRO_PAGINA);
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    private void limpiar() {
	descripcion = new String();
	archivo = null;
    }

    public void confirmarDescarga() {
	mensajeConfirmacion(obtenerEtiqueta("com_descargado"));
    }

    @Override
    public String getPermiso() {
	return null;
    }

    public void subirArchivos(FileUploadEvent evento) {
	try {
	    archivo = evento.getFile();
	    UtilValidadorJsf.validar(archivo, true, "complementoForm:cargaDeArchivos", null, new String[] { ".jpg", ".pdf", ".ppt", ".doc", ".docx", ".pptx" }, 1024L * 20L);// 20mb
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    public void guardar() {
	try {
	    if (!validar())
		return;
	    ComplementosExcel complemento = new ComplementosExcel();
	    complemento.setDescripcionComplemento(descripcion);
	    complemento.setArchivo(new Archivo());
	    complemento.getArchivo().setAnexo(archivo.getContents());
	    complemento.getArchivo().setNombreArchivo(archivo.getFileName());
	    complemento.getArchivo().setTamanioArchivo(archivo.getSize());
	    complemento.getArchivo().setModificado(true);
	    complementosFacade.guardar(complemento, archivo.getInputstream(), getUsuario());
	    mensajeConfirmacion("Archivo almacenado satisfactoriamente.");
	    limpiar();
	    listado.setRowCount(complementosFacade.cantidadRegistros(new ComplementosExcel()));
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    private boolean validar() throws IOException {
	boolean validar = true;
	validar &= UtilValidadorJsf.validar(descripcion, "complementoForm:descripcion", true, 250, null);
	validar &= UtilValidadorJsf.validar(archivo, true, "complementoForm:cargaDeArchivos", null, new String[] { ".jpg", ".pdf", ".ppt", ".doc", ".docx", ".pptx" }, 1024L * 20L);// 20mb
	return validar;
    }

    public void eliminar() {
	try {
	    ComplementosExcel eliminar = listado.getRowData();
	    if (eliminar == null)
		throw mgrExc.lanzarExcepcion(93, TipoErrorNegocio.ALERTA);
	    complementosFacade.eliminar(eliminar, getUsuario());
	    listado.setRowCount(complementosFacade.cantidadRegistros(new ComplementosExcel()));
	    mensajeConfirmacion(obtenerEtiqueta("menEliminar"));
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    public StreamedContent getDescargar() throws NegocioException {
	try {
	    ComplementosExcel fila = listado.getRowData();
	    if (fila == null || fila.getArchivo() == null)
		return null;
	    byte[] imagen = fila.getArchivo().getAnexo() == null && !UtilTexto.estaVacio(fila.getArchivo().getIdDocumento())
		    ? complementosFacade.obtenerImagen(fila.getArchivo().getIdDocumento())
		    : fila.getArchivo().getAnexo();
	    if (imagen == null)
		imagen = fila.getArchivo().getContenidoArchivo();
	    fila.getArchivo().setAnexo(imagen);
	    if (imagen == null)
		return null;
	    return new DefaultStreamedContent(new ByteArrayInputStream(imagen), "image/jpg", fila.getArchivo().getNombreArchivo());
	} catch (Exception e) {
	    procesarError(e);
	    return null;
	}
    }

    /*
     * getters y setters
     */

    public int getRegistroPagina() {
	return REGISTRO_PAGINA;
    }

    public String getUrlCapacitacion() {
	return urlCapacitacion;
    }

    public LazyDataModel<ComplementosExcel> getListado() {
	return listado;
    }

    public void setListado(LazyDataModel<ComplementosExcel> listado) {
	this.listado = listado;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public UploadedFile getArchivo() {
	return archivo;
    }

    public void setArchivo(UploadedFile archivo) {
	this.archivo = archivo;
    }

}