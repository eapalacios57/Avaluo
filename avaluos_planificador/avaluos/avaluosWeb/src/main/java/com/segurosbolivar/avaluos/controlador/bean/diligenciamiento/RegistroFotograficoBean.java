package com.segurosbolivar.avaluos.controlador.bean.diligenciamiento;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.jfree.util.Log;
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.asesoftware.util.archivo.UtilImagen;
import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.controlador.bean.general.GeneralAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilValidadorJsf;
import com.segurosbolivar.avaluos.modelo.entity.AnexoFotografico;
import com.segurosbolivar.avaluos.modelo.entity.Archivo;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.ListaAnexosPdf;
import com.segurosbolivar.avaluos.modelo.facade.IAvaluoFacade;
import com.segurosbolivar.avaluos.modelo.service.IArchivo;

/**
 * Controlador para la vista de la secci�n de registro fotografico, que permite
 * la gesti�n de las fotografias del inmueble del aval�o, dentro de las
 * operaciones que se realizan para cada imagen esta: ordenar, eliminar, cargar,
 * girar, etc. Hace parte de la pantalla diligenciamiento.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:46 a.m.
 */
@ManagedBean(name = "regFotograficoBean")
@SessionScoped
public class RegistroFotograficoBean extends GeneralAbstractoBean implements Serializable {

    private static final long serialVersionUID = -4110903494363054924L;
    private static final int CANTIDAD_FOTOGRAFIAS = 15;
    private Avaluo avaluo;
    private boolean ocultarAuditoria;
    private List<ListaAnexosPdf> fotografias;
    private ListaAnexosPdf fotografia;
    private AnexoFotografico registroFotografico;
    private int numeroFotografias;
    private static final String MENSAJE_ESPECIFICO = "avaluoForm:errorEspecifico";
    private int estadoFotografico = 0;
    
    @EJB
    private transient IAvaluoFacade avaluoFacade;
    
    @EJB
	private IArchivo svcArchivo;

    static {
	System.setProperty("java.awt.headless", "true");
    }

    public static RegistroFotograficoBean getBean() {
	return UtilJsf.obtenerBackingBean("regFotograficoBean");
    }

    @Override
    public void inicio() {
	// No es necesario inicializar ninguna variable
    }

    @Override
    public String getPermiso() {
	return null;
    }

    public void obtenerArchivoFotograficoS3() {
    	try{
    		if(estadoFotografico == 1)
    			return;
    		AnexoFotografico entidad = new AnexoFotografico();
    		entidad.setIdAvaluo(avaluo.getIdAvaluo());
    		entidad.setAvaluo(avaluo);
    		if (getUsuario() != null && getUsuario().getUsuario() != null) {
    		    entidad.setUsuarioCreacion(getUsuario().getUsuario().getCodigo());
    		    entidad.setUsuarioTransaccion(getUsuario().getUsuario().getCodigo());
    		}
    		entidad.setFechaCreacion(new Date());
    		entidad.setFechaTransaccion(new Date());
    		
    		ocultarAuditoria = false;
    		fotografias = new ArrayList<>();
    		
    		fotografias.addAll(avaluoFacade.consultarRegistroFotografico(this.avaluo, true));
    		numeroFotografias = CANTIDAD_FOTOGRAFIAS - fotografias.size();
    		estadoFotografico = 1;
    	}catch (Exception e) {
    	    procesarError(e);
    	}
    }
    
    public void editar() {
	try {
		if(estadoFotografico == 1)
			return;
	    numeroFotografias = CANTIDAD_FOTOGRAFIAS - (fotografias == null || fotografias.isEmpty() ? 0 : fotografias.size());
	    AnexoFotografico entidad = avaluoFacade.consultarAnexo(avaluo.getIdAvaluo());
	    if (entidad == null) {
		entidad = new AnexoFotografico();
		entidad.setIdAvaluo(avaluo.getIdAvaluo());
		entidad.setAvaluo(avaluo);
		if (getUsuario() != null && getUsuario().getUsuario() != null) {
		    entidad.setUsuarioCreacion(getUsuario().getUsuario().getCodigo());
		    entidad.setUsuarioTransaccion(getUsuario().getUsuario().getCodigo());
		}
		entidad.setFechaCreacion(new Date());
		entidad.setFechaTransaccion(new Date());
	    }
	    this.registroFotografico = entidad;
	    ocultarAuditoria = true;
	    fotografias = new ArrayList<>();

	    fotografias.addAll(avaluoFacade.consultarRegistroFotografico(this.avaluo, true));
	    numeroFotografias = CANTIDAD_FOTOGRAFIAS - fotografias.size();
	    validarFotografias();
	    estadoFotografico = 1;
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    
    public void setAvaluo(Avaluo avaluo) {
	try {
	    if (avaluo == null) {
		  throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
	    }
	    this.avaluo = avaluo;
	    estadoFotografico = 0;
	} catch (Exception e) {
	    procesarError(e);
	}
    }
    
    //Metodo añadido para eliminar consecutivo duplicado de fotografia y evitar error 500
    public void validarFotografias() {
    	HashMap<Integer,ListaAnexosPdf> map =  new HashMap<Integer, ListaAnexosPdf>();
    	for(ListaAnexosPdf foto : fotografias) {
    		map.put(foto.getConsecutivoAnexo().intValue(), foto);
    	}		
    	fotografias = new ArrayList<>();
    	fotografias.addAll(map.values());
    }
    
    public void eliminarFotografias() {
	numeroFotografias = CANTIDAD_FOTOGRAFIAS;
	fotografias = new ArrayList<>();
    }

    public boolean isVerEliminar() {
	return numeroFotografias != CANTIDAD_FOTOGRAFIAS;
    }

    public boolean isVerTablero() {
	return numeroFotografias > 0;
    }

    public synchronized void subirArchivo(FileUploadEvent event) {
	try {
	    if (numeroFotografias <= 0) {
		return;
	    }
	    UploadedFile anexo = event.getFile();
	    if (anexo == null) {
		return;
	    }
	    if (!UtilValidadorJsf.validar(anexo, true, "avaluoForm:edicionAvaluosTab:cargarRegistros", null, new String[] { ".jpg", ".JPG" }, 10000L))
		return;
	    ListaAnexosPdf registro = new ListaAnexosPdf();
	    registro.setConsecutivoAnexo(Long.valueOf(fotografias.size() + 1L));
	    registro.setAnexo(anexo.getContents());
	    registro.setPortada(numeroFotografias == CANTIDAD_FOTOGRAFIAS);
	    registro.setArchivo(new Archivo());
	    registro.getArchivo().setTamanioArchivo(anexo.getSize());
	    registro.getArchivo().setNombreArchivo(anexo.getFileName());
	    registro.setModificado(true);
	    fotografias.add(registro);
	    numeroFotografias--;

	} catch (Exception e) {
	    procesarError(e);
	}
    }

    public void mensajeCargue() {
    	if(numeroFotografias == CANTIDAD_FOTOGRAFIAS) {
    		return;
    	}
    	String[] cantidadFotos = { String.valueOf(CANTIDAD_FOTOGRAFIAS - numeroFotografias) };
    	UtilJsf.ocultarDialogo("statusDialog");
    	mensajeConfirmacion(obtenerEtiqueta("ref_cargueExitoso", cantidadFotos));
    }

    public void cambiarFachada() {
	try {
	    if (fotografia == null)
		return;
	    if (fotografia.isPortada()) {
		fotografias.get(0).setPortada(true);
		fotografia.setPortada(false);
		return;
	    }
	    fotografia.setPortada(true);
	    for (ListaAnexosPdf elemento : fotografias) {
		if (!elemento.getConsecutivoAnexo().equals(fotografia.getConsecutivoAnexo()))
		    elemento.setPortada(false);
	    }
	    int pocision = fotografia.getConsecutivoAnexo().intValue() - 1;
	    if (pocision == 0)
		return;
	    fotografia.setConsecutivoAnexo(1L);
	    ListaAnexosPdf portadaOriginal = fotografias.get(0);
	    portadaOriginal.setConsecutivoAnexo(Long.valueOf(pocision) + 1L);
	    portadaOriginal.setModificado(true);
	    fotografias.set(pocision, portadaOriginal);
	    fotografias.set(0, fotografia);
	    fotografia.setModificado(true);
	    fotografia = null;
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    public void editarNombre() {
	try {
	    if (fotografia == null)
		return;
	    fotografia.setEditarNombre(true);
	    String[] archivo = fotografia.getArchivo().getNombreArchivo().split("\\.");
	    fotografia.setNombreFotografia(archivo == null || archivo.length < 1 ? fotografia.getArchivo().getNombreArchivo() : archivo[0]);
	    fotografia = null;
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    public void guardarNombre() {
	try {
	    boolean validacion = true;
	    if (fotografia == null)
		return;
	    final String tabNombre = "avaluoForm:edicionAvaluosTab:nombre_";
	    validacion &= UtilValidadorJsf.validar(fotografia.getNombreFotografia(), tabNombre + fotografia.getConsecutivoAnexo(), true, 56, null);
	    validacion &= UtilValidadorJsf.validarFormato(fotografia.getNombreFotografia(), tabNombre + fotografia.getConsecutivoAnexo(), true, 60, "^[a-zA-Z0-9 ]*$", null,
		    "Ej:foto01");
	    if (!validacion)
		return;
	    fotografia.setEditarNombre(false);
	    fotografia.setModificado(true);
	    fotografia.getArchivo().setNombreArchivo(fotografia.getNombreFotografia() + ".jpg");
	    fotografia = null;
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    public void cancelarNombre() {
	try {
	    if (fotografia == null)
		return;
	    fotografia.setEditarNombre(false);
	    fotografia = null;
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    public void arrastrar(DragDropEvent evento) {
	if (evento == null)
	    return;
	if (UtilTexto.estaVacio(evento.getDragId()) || UtilTexto.estaVacio(evento.getDropId()))
	    return;
	int pocisionInicio = Integer.parseInt(evento.getDragId().split("_")[1]);
	int pocisionFinal = Integer.parseInt(evento.getDropId().split("_")[1]);
	ListaAnexosPdf fotografiaOrigen = fotografias.get(pocisionInicio - 1);
	ListaAnexosPdf fotografiaDestino = fotografias.get(pocisionFinal - 1);
	fotografiaOrigen.setConsecutivoAnexo(Long.valueOf(pocisionFinal));
	fotografiaDestino.setConsecutivoAnexo(Long.valueOf(pocisionInicio));
	if (pocisionFinal == 1) {
	    fotografiaOrigen.setPortada(true);
	    fotografiaDestino.setPortada(false);
	}
	fotografias.set(pocisionFinal - 1, fotografiaOrigen);
	fotografias.set(pocisionInicio - 1, fotografiaDestino);
    }

    public StreamedContent consultarImagen(ListaAnexosPdf fotografia) {
	if (fotografia == null || fotografia.getAnexo() == null)
	    return null;
	return new DefaultStreamedContent(new ByteArrayInputStream(fotografia.getAnexo()), "image/jpg", fotografia.getArchivo().getNombreArchivo());
    }

    public void eliminar() {
	if (fotografia == null)
	    return;
	int pocision = fotografias.indexOf(fotografia);
	fotografias.remove(fotografia);
	if (pocision < fotografias.size()) {
	    for (; pocision < fotografias.size(); pocision++) {
		ListaAnexosPdf foto = fotografias.get(pocision);
		if (pocision == 0)
		    foto.setPortada(true);
		foto.setConsecutivoAnexo(foto.getConsecutivoAnexo() - 1);
	    }
	}
	fotografia = null;
	numeroFotografias++;
	mensajeConfirmacion(obtenerEtiqueta("ref_menEliminar"));
    }

    public void detalleFotografia(ListaAnexosPdf fotografia) {
    		log.info("Ingresa detalleFotografia");
    	try {    		    
    	    if (fotografia == null || fotografia.getAnexo() == null)
    		return;
    	    byte[] imagenOriginal = svcArchivo.obtenerDocumento(fotografia.getArchivo().getIdDocumento());
    	    fotografia.setAnexo(imagenOriginal);    	    
    	    PopupFotografiaBean.getBean().ver(fotografia);
    	    log.info("Finaliza detalleFotografia");
    	} catch (Exception e) {
    	    procesarError(e);
    	}
    }

    public void rotar() {
	try {
	    if (fotografia == null || fotografia.getAnexo() == null)
		return;
	    fotografia.setAnexo(UtilImagen.rotar90GradosDerecha(fotografia.getAnexo()));
	    fotografia.setModificado(true);
	} catch (IOException e) {
	    procesarError(e);
	}
    }

    public void rotarIzquierda() {
	try {
	    if (fotografia == null || fotografia.getAnexo() == null)
		return;
	    fotografia.setAnexo(UtilImagen.rotar90GradosIzquierda(fotografia.getAnexo()));
	    fotografia.setModificado(true);
	} catch (IOException e) {
	    procesarError(e);
	}
    }

    public void anterior() {
	try {
		guardar();
	} catch (Exception e) {
	    procesarError("avaluoForm:errorEspecifico", e);
	}
    }
    
	public void eliminarFotos() {
		try {
			avaluoFacade.eliminarRegistroFotografico(registroFotografico, avaluo);
			eliminarFotografias();
		} catch (Exception e) {
			procesarError("avaluoForm:errorEspecifico", e);
		}
	}

    public void guardar() throws NegocioException {
    	if (fotografias == null || fotografias.isEmpty())
    	    return;
    	
    	if (!validar())
	        throw mgrExc.lanzarExcepcion(22, TipoErrorNegocio.ALERTA);
	
    	long tiempoInicial = System.currentTimeMillis();
    	log.info("============Inicio de la carga de archivos===========");
	    avaluoFacade.guardar(registroFotografico, fotografias, avaluo, getUsuario());
	    System.out.println("Tiempo final de la carga de archivos: "+(System.currentTimeMillis()-tiempoInicial));
	    avaluo.setAnexosFotograficos(registroFotografico);
	    avaluo.setListaAnexosPdf(fotografias);
//	    UtilJsf.ocultarDialogo("statusDialog");
        UtilJsf.mostrarMensaje(MENSAJE_ESPECIFICO, obtenerEtiqueta("ref_menGuardar"));
        long tiempoFinal = System.currentTimeMillis();
    	log.info("============Finaliza la carga de archivos=============");
    	long tiempoTotal = tiempoFinal - tiempoInicial;
    	log.info("============Tiempo total cargando las fotos: "+ tiempoTotal+"ms");
        
    }

    public void siguiente() {
	try {
	    if (isEditable())
		guardar();
	} catch (Exception e) {
	    procesarError("avaluoForm:errorEspecifico", e);
	}
    }

    public boolean validar() throws NegocioException {
	boolean validar = true;
	if (fotografias == null || fotografias.isEmpty())
	    return true;
	for (ListaAnexosPdf foto : fotografias) {
	    validar &= UtilValidadorJsf.validar(foto.getAnexo(), foto.getArchivo().getNombreArchivo(), true, "avaluoForm:edicionAvaluosTab:imagen_" + foto.getConsecutivoAnexo(),
		    null, new String[] { ".jpg", ".JPG" }, 2200L);
	    validar &= UtilValidadorJsf.validar(foto.getArchivo().getNombreArchivo(), "avaluoForm:edicionAvaluosTab:nombre_" + foto.getConsecutivoAnexo(), true, 60, null);
	}
	return validar;
    }

    public void ocultar() {
	ocultarAuditoria = !ocultarAuditoria;
    }
    
    @Override
    public boolean isEditable() {
    	return AvaluoBean.getBean().isEditable();
    }
    
    /*
     * getters y setters
     */
    public void limpiar() {
	   	 fotografias= null;
	   	 fotografia = null;
	   	 registroFotografico = null;
	   	 numeroFotografias = 0;
	   	 estadoFotografico = 0;
	   	 avaluo = null;
    }
    
    public AnexoFotografico getRegistroFotografico() {
	return registroFotografico;
    }

    public boolean isEstadoAprobado() {
	return AvaluoBean.getBean().isEstadoAprobado();
    }

    public void setRegistroFotografico(AnexoFotografico registroFotografico) {
	this.registroFotografico = registroFotografico;
    }

    public boolean isOcultarAuditoria() {
	return ocultarAuditoria;
    }

    public List<ListaAnexosPdf> getFotografias() {
	return fotografias;
    }

    public void setFotografias(List<ListaAnexosPdf> fotografias) {
	this.fotografias = fotografias;
    }

    public int getNumeroFotografias() {
	return numeroFotografias;
    }

    public void setNumeroFotografias(int numeroFotografias) {
	this.numeroFotografias = numeroFotografias;
    }

    public ListaAnexosPdf getFotografia() {
	return fotografia;
    }

    public void setFotografia(ListaAnexosPdf fotografia) {
	this.fotografia = fotografia;
    }

	public int getEstadoFotografico() {
		return estadoFotografico;
	}

	public void setEstadoFotografico(int estadoFotografico) {
		this.estadoFotografico = estadoFotografico;
	}
    
    

}