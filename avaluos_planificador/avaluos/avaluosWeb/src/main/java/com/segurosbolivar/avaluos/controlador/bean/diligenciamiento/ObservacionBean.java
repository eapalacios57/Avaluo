package com.segurosbolivar.avaluos.controlador.bean.diligenciamiento;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.controlador.bean.general.GeneralAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilValidadorJsf;
import com.segurosbolivar.avaluos.modelo.entity.Archivo;
import com.segurosbolivar.avaluos.modelo.entity.ArchivoPlano;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.Observaciones;
import com.segurosbolivar.avaluos.modelo.facade.IAvaluoFacade;
import com.segurosbolivar.avaluos.modelo.service.IArchivo;
import com.segurosbolivar.avaluos.modelo.service.IDirecciones;

/**
 * Controlador para la vista de la secci�n de observaciones del aval�o. Hace
 * parte de la pantalla diligenciamiento.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:45 a.m.
 */
@ManagedBean
@SessionScoped
public class ObservacionBean extends GeneralAbstractoBean implements Serializable{

    private static final long serialVersionUID = 4465734906480700216L;
    private boolean ocultarAuditoria;
    private Observaciones observacion;
    private Avaluo avaluo;
    private UploadedFile anexo;
    private boolean archivoCargado;
    private String nombreArchivo;
    private byte[] contenido;
    private static final String MENSAJE_ESPECIFICO = "avaluoForm:errorEspecifico";
    
    @EJB
    public transient IAvaluoFacade avaluoFacade;

    @EJB
    private transient IDirecciones direccionesSvr;

    @EJB
    private transient IArchivo archivoSvr;

    public static ObservacionBean getBean() {
	return UtilJsf.obtenerBackingBean("observacionBean");
    }

    @Override
    public void inicio() {

    }

    @Override
    public String getPermiso() {
	return null;
    }

    public void editar(Avaluo avaluo, boolean omitir) {
	try {
	    if (avaluo == null) {
		throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
	    }
	    this.avaluo = avaluo;
	    Observaciones entidad = avaluoFacade.consultarObservacion(avaluo.getIdAvaluo());
	    if (entidad == null) {
		entidad = new Observaciones();
		entidad.setIdAvaluo(avaluo.getIdAvaluo());
		entidad.setAvaluo(avaluo);
		if (getUsuario() != null && getUsuario().getUsuario() != null) {
		    entidad.setUsuarioCreacion(getUsuario().getUsuario().getCodigo());
		    entidad.setUsuarioTransaccion(getUsuario().getUsuario().getCodigo());
		}
		entidad.setFechaCreacion(new Date());
		entidad.setFechaTransaccion(new Date());
	    }
	    this.observacion = entidad;
	    archivoCargado = false;
	    ocultarAuditoria = true;
	    if (omitir)
		return;
	    if (this.observacion.getIdArchivo() == null)
		return;
	    String idDocumento = this.observacion.getArchivo().getIdDocumento();
	    if (UtilTexto.estaVacio(idDocumento))
		return;
//	    try {
		this.nombreArchivo = this.observacion.getArchivo().getNombreArchivo();
		if (UtilTexto.estaVacio(nombreArchivo))
		    return;
//		byte[] data = avaluoFacade.obtenerDocumento(this.observacion.getArchivo().getIdDocumento());		
//		if (data == null)
//		    return;
//		contenido = data;
//	    } catch (IOException ex) {
//		throw mgrExc.lanzarExcepcion(31, TipoErrorNegocio.ERROR);
//	    }
	    this.archivoCargado = true;
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    public void subirArchivo(FileUploadEvent event) {
	try {
	    anexo = event.getFile();
	    if (anexo == null) {
		eliminarArchivo();
		return;
	    }
	    archivoCargado = UtilValidadorJsf.validar(anexo, true, "avaluoForm:edicionAvaluosTab:anexo", null, new String[] { ".zip", ".ZIP" }, 1000L);
	    nombreArchivo = anexo.getFileName();
	    if (observacion.getArchivo() == null)
		observacion.setArchivo(new Archivo());
	    observacion.getArchivo().setNombreArchivo(nombreArchivo);
	    observacion.getArchivo().setTamanioArchivo(anexo.getSize());
	    observacion.getArchivo().setModificado(true);
	    contenido = anexo.getContents();
	    observacion.getArchivo().setAnexo(contenido);
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    public void eliminarArchivo() {
	archivoCargado = false;
	nombreArchivo = null;
	contenido = null;
	anexo = null;
	if (observacion.getArchivo() == null)
	    return;
	observacion.getArchivo().setAnexo(null);
	observacion.getArchivo().setTamanioArchivo(null);
	observacion.getArchivo().setNombreArchivo(null);
	observacion.getArchivo().setModificado(true);
    observacion.setIdArchivo(null);
    }
    
    public void descargarArchivo() throws NegocioException {
    	log.info("Descargar archivo BEAN, descargarArchivo, inicio ");
    	try {
    		UtilJsf.navegarNuevaPagina(avaluoFacade.obtenerDocumentoURL(this.observacion.getArchivo().getIdDocumento(), this.observacion.getArchivo().getNombreArchivo()).toString());
    		log.info("Descargar archivo por URL, descargarArchivo, fin archivo " + this.observacion.getArchivo().getIdDocumento());
    	}  catch (IOException ex) {
    		log.info("Descargar archivo por URL, descargarArchivo, error IOexc: " + ex.getMessage());
    		throw mgrExc.lanzarExcepcion(125, TipoErrorNegocio.ERROR);
	    }catch (Exception e) {
	    	log.info("Descargar archivo por URL, descargarArchivo, error gral: " + e.getMessage());
    		procesarError(e);
    	}
    }

    public void anterior() {
	try {
	    if (isEditable())
		guardar();
	    AvaluoBean.getBean().setTab(5);
	} catch (Exception e) {
	    procesarError("avaluoForm:errorEspecifico", e);
	}
    }

    public void guardar() throws NegocioException, IOException {
    	if (!validar())
    	    throw mgrExc.lanzarExcepcion(22, TipoErrorNegocio.ALERTA);
    	if(observacion.getArchivo() !=null &&  observacion.getArchivo().getNombreArchivo().length() > 200 )    			
			throw mgrExc.lanzarExcepcion(128, TipoErrorNegocio.ERROR);    	
    	String cadena = (observacion.getObservacionAvaluo().replaceAll("\r\n\r", "")).trim();	
        observacion.setObservacionAvaluo(cadena);	
    	avaluoFacade.guardar(observacion, getUsuario());
    	avaluo.setObservacion(observacion);
        UtilJsf.mostrarMensaje(MENSAJE_ESPECIFICO,obtenerEtiqueta("obs_menGuardar"));
        }

    public void cambiarDireccion(ValueChangeEvent event) {
	String valor = (String) event.getNewValue();
	if (UtilTexto.estaVacio(valor))
	    return;
	valor = direccionesSvr.transformarDireccion(valor, false);
	UIInput campoEntrada = (UIInput) event.getComponent();
	if (UtilTexto.estaVacio(valor)) {
	    campoEntrada.setValue(((String) event.getNewValue()));
	    UtilJsf.mostrarMensaje("avaluoForm:edicionAvaluosTab:msjesp", "Direccion No Cuenta con formato valido");
	    return;
	} else if (valor.contains("|")) {
	    StringTokenizer token = new StringTokenizer(valor, "|");
	    token.nextToken();
	    campoEntrada.setValue(token.nextToken());
	} else
	    campoEntrada.setValue(valor);
    }

    public void siguiente() {
	try {
		if (isEditable())
		guardar();	
		if (avaluo != null && !avaluo.isProvisional() && RegistroFotograficoBean.getBean().getEstadoFotografico() == 0)
			RegistroFotograficoBean.getBean().editar();		
	    if (!AvaluoBean.getBean().getAvaluo().isProvisional())
		AvaluoBean.getBean().setTab(7);
	} catch (Exception e) {
	    procesarError("avaluoForm:errorEspecifico", e);
	}
    }

    public boolean validar() throws IOException {
	boolean validar = true;
	validar &= UtilValidadorJsf.validar(observacion.getObservacionAvaluo(), "avaluoForm:edicionAvaluosTab:observacionAvaluo", false, 2000, null);
	validar &= UtilValidadorJsf.validar(anexo, false, "avaluoForm:edicionAvaluosTab:anexo", null, new String[] { ".zip" }, 1000L);
	validar &= UtilValidadorJsf.validarFormato(observacion.getDireccionAnexos(), "avaluoForm:edicionAvaluosTab:direccionAnexos", false, 100, "^[a-zA-Z0-9 ]*$", null,
		"Ej:CR 13 34 99");
	validar &= UtilValidadorJsf.validarFormato(observacion.getOtrasDirecciones(), "avaluoForm:edicionAvaluosTab:otrasDirecciones", false, 100, "^[a-zA-Z0-9 ]*$", null,
		"Ej:CR 13 34 99");
	return validar;
    }

    public void obligatoriedad(List<ArchivoPlano> obligatorios) {
	if (obligatorios == null || obligatorios.isEmpty())
	    return;
	UtilValidadorJsf.validar(observacion.getObservacionAvaluo(), "avaluoForm:edicionAvaluosTab:observacionAvaluo", AvaluoBean.esObligatorio(obligatorios, 212L), 2000, null);
	UtilValidadorJsf.validarFormato(observacion.getDireccionAnexos(), "avaluoForm:edicionAvaluosTab:direccionAnexos", AvaluoBean.esObligatorio(obligatorios, 194L), 100,
		"^[a-zA-Z0-9 ]*$", null, "Ej:CR 13 34 99");
	UtilValidadorJsf.validarFormato(observacion.getOtrasDirecciones(), "avaluoForm:edicionAvaluosTab:otrasDirecciones", AvaluoBean.esObligatorio(obligatorios, 213L), 100,
		"^[a-zA-Z0-9 ]*$", null, "Ej:CR 13 34 99");
	if(avaluo.isMovil()) {
	 //UtilValidadorJsf.validar(anexo, false, "avaluoForm:edicionAvaluosTab:anexo", null, new String[] { ".zip" }, 1000L);
	}
    }

    public void ocultar() {
	ocultarAuditoria = !ocultarAuditoria;
    }
    /*
     * getters y setters
     */

    public boolean isOcultarAuditoria() {
	return ocultarAuditoria;
    }

    public boolean isEstadoAprobado() {
	return AvaluoBean.getBean().isEstadoAprobado();
    }

    public void setOcultarAuditoria(boolean ocultarAuditoria) {
	this.ocultarAuditoria = ocultarAuditoria;
    }

    public Observaciones getObservacion() {
	return observacion;
    }

    public void setObservacion(Observaciones observacion) {
	this.observacion = observacion;
    }

    public UploadedFile getAnexo() {
	return anexo;
    }

    public void setAnexo(UploadedFile anexo) {
	this.anexo = anexo;
    }

    public boolean isArchivoCargado() {
	return archivoCargado;
    }

    public String getNombreArchivo() {
	return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
	this.nombreArchivo = nombreArchivo;
    }
    
    @Override
    public boolean isEditable() {
    	return AvaluoBean.getBean().isEditable();
    }

}