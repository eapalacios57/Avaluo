
package com.segurosbolivar.avaluos.controlador.bean.diligenciamiento;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.primefaces.component.tabview.TabView;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabEvent;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioAlertaException;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilNumero;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.cons.AvaluosCons;
import com.segurosbolivar.avaluos.controlador.bean.general.GeneralAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.general.ImpresionBean;
import com.segurosbolivar.avaluos.controlador.bean.general.ListaBean;
import com.segurosbolivar.avaluos.controlador.bean.general.PopupConfirmacionBean;
import com.segurosbolivar.avaluos.controlador.bean.seguridad.SesionBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilValidadorJsf;
import com.segurosbolivar.avaluos.modelo.cons.EstadoAvaluo;
import com.segurosbolivar.avaluos.modelo.cons.Procedencia;
import com.segurosbolivar.avaluos.modelo.cons.TipoAvaluo;
import com.segurosbolivar.avaluos.modelo.dto.AvaluoFullDTO;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.ArchivoPlano;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.Ciudad;
import com.segurosbolivar.avaluos.modelo.entity.Departamento;
import com.segurosbolivar.avaluos.modelo.entity.Dominios;
import com.segurosbolivar.avaluos.modelo.entity.IPersona;
import com.segurosbolivar.avaluos.modelo.facade.IAvaluoFacade;
import com.segurosbolivar.avaluos.modelo.facade.IReporteFacade;
import com.segurosbolivar.avaluos.modelo.prueba.ITomaTiempos;
import com.segurosbolivar.avaluos.modelo.service.IArchivo;
import com.segurosbolivar.avaluos.modelo.service.IComplementos;
import com.segurosbolivar.avaluos.modelo.service.IDirecciones;
import com.segurosbolivar.avaluos.modelo.service.impl.ArchivoImpl;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.service.impl.ArchivoImpl;

/**
 * Controlador para la vista de edici�n de aval�os. Este gestiona el
 * registro de aval�o teniendo control sobre las pantallas de las secciones
 * respectivas asociadas al diligenciamiento.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:39 a.m.
 */
@ManagedBean
@SessionScoped
public class AvaluoBean extends GeneralAbstractoBean implements Serializable {

	private static final long serialVersionUID = -6804464143699905567L;
	private boolean ocultarAuditoria;
	private boolean labelMovil = false;
	private boolean tradicional = true;
	private boolean movil = false;
	private transient Avaluo avaluo;
	private transient AvaluoFullDTO avaluoDto;
	private boolean esNuevo;
	private List<SelectItem> ciudades;
	private int tab;
	private int tabMapa;
	private boolean envioAprobacion = false;
	private boolean verMapa;
	private Dominios tipoAvaluo;
	@EJB
	public transient IArchivo archivoImpl;
	@EJB
	public transient IComplementos complementosImpl;
	@EJB
	public transient IAvaluoFacade avaluoFacade;
	@EJB
	public transient IReporteFacade reporteFacade;
	@EJB
	private transient IDirecciones direccionesSvr;
	private ListaBean lista;
	private String longitud = null;
	private String latitud = null;
	private String extentXMax = null;
	private String extentXMin = null;
	private String extentYMax = null;
	private String extentYMin = null;
	private boolean marca;
	private Long zoom;
	private String mapaBase = "osm";
	private String ubicacion;

	@EJB
	private transient ITomaTiempos Tiempo;
	private String mapaTipo = null;
	private String spatialReferenceLatestWkid = null;
	private String spatialReferenceWkid = null;
	private String centerX = null;
	private String centerY = null;
	private boolean esUnClick;
	private boolean imprimirPrevioAprobacion;
	private boolean cambioDireccion;
	private static final String MENSAJE_ESPECIFICO = "avaluoForm:errorEspecifico";
	
	private static Logger log = Logger.getLogger(AvaluoBean.class.getSimpleName());

	public static AvaluoBean getBean() {
		return UtilJsf.obtenerBackingBean("avaluoBean");
	}

	@Override
	public void inicio() {
		lista = ListaBean.getBean();
	}

	@Override
	public String getPermiso() {
		return null;
	}

	public void cambiarDireccion(ValueChangeEvent event) throws Exception {
		cambioDireccion = true;
		esUnClick = false;
		longitud = null;
		latitud = null;
		avaluo.setLatitudSFBUA(null);
		avaluo.setLongitudSFBUA(null);
		String valor = (String) event.getNewValue();
		if (UtilTexto.estaVacio(valor))
			return;
		valor = direccionesSvr.transformarDireccion(valor, true);
		
		if (UtilTexto.estaVacio(valor)||(valor.equals("ERROR"))) {
			avaluo.setDireccionInmueble((String) event.getNewValue());
			UtilJsf.mostrarMensaje("avaluoForm:edicionAvaluosTab:direccionInmueble",
					obtenerEtiqueta("men_direccionNoValida"));
			marca = false;
			if (avaluo.getCodigoDaneDepartamento() != null && avaluo.getCodigoDaneCiudad() != null) {
				
				String direccion = null; 
				if(!UtilTexto.estaVacio(avaluo.getDireccionComplementaria())) {
					direccion = avaluo.getDireccionComplementaria();
				}
				
				if(!UtilTexto.estaVacio(direccion)) {
					agregarValoresMapa(avaluoFacade.obtenerExtentCentroide(
							(avaluo.getCodigoDaneDepartamento() < 10 ? "0" : "") + avaluo.getCodigoDaneDepartamento(),
							(avaluo.getCodigoDaneDepartamento() < 10 ? "0" : "") + avaluo.getCodigoDaneCiudad(), direccion,
							avaluo.getBarrio()));
					reLocalizarMapa();
				}
			}
			return;
		} else if (valor.contains("|")) {
			StringTokenizer token = new StringTokenizer(valor, "|");
			avaluo.setDireccionInmueble(token.nextToken().trim());
			avaluo.setDireccionComplementaria(token.nextToken().trim());
		} else
			avaluo.setDireccionInmueble(valor.trim());
		((UIInput) event.getComponent()).setValue(avaluo.getDireccionInmueble());
		if (avaluo.getCodigoDaneDepartamento() != null && avaluo.getCodigoDaneCiudad() != null) {
			agregarValoresMapa(avaluoFacade.obtenerExtentCentroide(
					(avaluo.getCodigoDaneDepartamento() < 10 ? "0" : "") + avaluo.getCodigoDaneDepartamento(),
					(avaluo.getCodigoDaneDepartamento() < 10 ? "0" : "") + avaluo.getCodigoDaneCiudad(),
					avaluo.getDireccionInmueble(), avaluo.getBarrio()));
			log.info("antes de ingresar a la siguiente instancia "+latitud+" "+longitud);
			if(latitud == null && longitud == null) {
				Ciudad ciudad = ListaBean.getBean().obtenerCiudad(avaluo.getIdCiudad(), null);
				if(ciudad != null) {
					latitud = ciudad.getLatitud();
					longitud = ciudad.getLongitud();
					avaluo.setLatitudSFBUA(latitud);
					avaluo.setLongitudSFBUA(longitud);
					avaluo.setUbicacionGps(avaluo.getLatitudSFBUA() + "," + avaluo.getLongitudSFBUA());
					marca = true;
				}
			}
			reLocalizarMapa();
		}
	}

	public boolean cambiarDireccion(String valorNuevo) throws Exception {
		String valor = valorNuevo;
		if (UtilTexto.estaVacio(valor))
			return true;
		valor = direccionesSvr.transformarDireccion(valor, true);
		if (UtilTexto.estaVacio(valor)||(valor.equals("ERROR"))) {
			avaluo.setDireccionInmueble(valorNuevo);
			UtilJsf.mostrarMensaje("avaluoForm:edicionAvaluosTab:direccionInmueble",
					obtenerEtiqueta("men_direccionNoValida"));
			return false;
		} else if (valor.contains("|")) {
			StringTokenizer token = new StringTokenizer(valor, "|");
			avaluo.setDireccionInmueble(token.nextToken().trim());
			avaluo.setDireccionComplementaria(token.nextToken().trim());
		} else
			avaluo.setDireccionInmueble(valor.trim());
		return true;
	}

	public boolean cambiarDireccionComplementaria(String valorNuevo) throws Exception {
		String valor = valorNuevo;
		if (UtilTexto.estaVacio(valor))
			return true;
		valor = direccionesSvr.transformarDireccionComplementaria(valor);
		if (UtilTexto.estaVacio(valor)) {
			avaluo.setDireccionComplementaria(valorNuevo);
			UtilJsf.mostrarMensaje("avaluoForm:edicionAvaluosTab:direccionComplementaria",
					obtenerEtiqueta("men_direccionNoValida"));
			return false;
		} else if (valor.contains("|")) {
			StringTokenizer token = new StringTokenizer(valor, "|");
			token.nextToken();
			avaluo.setDireccionComplementaria(token.nextToken().trim());
		} else
			avaluo.setDireccionComplementaria(valor.trim());
		return true;
	}

	public void cambiarBarrio(ValueChangeEvent event) throws Exception {
		String valor = (String) event.getNewValue();
		if (UtilTexto.estaVacio(valor))
			return;
		avaluo.setBarrio(valor);
		((UIInput) event.getComponent()).setValue(avaluo.getBarrio());
		// El codigo divipola en la DB si es menor a 10 se guarda si el cero si es 05
		// esta guadado como 5
		// Aqui se agrega el 0 faltante si el codigo es menor a 10 para departamento.
		ArrayList<String> results = new ArrayList<String>();


		if (avaluo.getCodigoDaneDepartamento() != null && avaluo.getCodigoDaneCiudad() != null) {
			String direccion = null; 
			if(!UtilTexto.estaVacio(avaluo.getDireccionInmueble()) && UtilTexto.estaVacio(avaluo.getDireccionComplementaria())) {
				direccion = avaluo.getDireccionInmueble();
			}else if(UtilTexto.estaVacio(avaluo.getDireccionInmueble()) && !UtilTexto.estaVacio(avaluo.getDireccionComplementaria())) {
				direccion = avaluo.getDireccionComplementaria();
			}else if(!UtilTexto.estaVacio(avaluo.getDireccionInmueble()) && !UtilTexto.estaVacio(avaluo.getDireccionComplementaria())) {
				direccion = avaluo.getDireccionInmueble();
			}
			
			if(!UtilTexto.estaVacio(direccion)) {
				results = avaluoFacade.obtenerExtentCentroide(
						(avaluo.getCodigoDaneDepartamento() < 10 ? "0" : "") + avaluo.getCodigoDaneDepartamento(),
						(avaluo.getCodigoDaneDepartamento() < 10 ? "0" : "") + avaluo.getCodigoDaneCiudad(),
						direccion, avaluo.getBarrio());
				esUnClick = false;
				longitud = null;
				latitud = null;
				agregarValoresMapa(results);
				reLocalizarMapa();
			}
		}
	}
	
	public void cargarArchivoFotografico() {
		log.info("Llamado al metodo cargarArchivoFotografico codigoTab:"+ tab);
		tabMapa = tab;
		//poner un if si el avaluo es de motor entonces debería obtener el archivo fotografico de forma diferente
		if(avaluo.getIndMotor() != null && avaluo.getIndMotor().equals("S")) {
			//obtener  imagenes de s3
			try {	
				if(RegistroFotograficoBean.getBean().getFotografias() == null || RegistroFotograficoBean.getBean().getFotografias().isEmpty()) {
					log.info("Cargando  fotografias.........................");
					RegistroFotograficoBean.getBean().obtenerArchivoFotograficoS3();
				}
			} catch (Exception e) {
				RegistroFotograficoBean.getBean().obtenerArchivoFotograficoS3();
			}
		}else {
			if (avaluo != null && !avaluo.isProvisional() && tab == 7) {//pregunta que es un avaluo provisional
				try {	
					if(RegistroFotograficoBean.getBean().getFotografias() == null || RegistroFotograficoBean.getBean().getFotografias().isEmpty()) {
						log.info("Cargando  fotografias.........................");
						RegistroFotograficoBean.getBean().editar();
					}
				} catch (Exception e) {
					RegistroFotograficoBean.getBean().editar();
				}
			}
		}
		
	}

	public void ejecutarValidador() {
		try {
			if (this.avaluo == null) {
				throw mgrExc.lanzarExcepcion(34, TipoErrorNegocio.ERROR);
			}
			if (esNuevo) {
				validar();
				return;
			}
			if (InformacionInmuebleBean.getBean().obtenerCategoria().compareTo(0L) == 0) {
				UtilJsf.mostrarMensaje(MENSAJE_ESPECIFICO, obtenerEtiqueta("ina_categoriaInmueble"));
				return;
			} else {
				obligatoriedad(avaluoFacade.consultarCamposObligatoriosCategoria(
						InformacionInmuebleBean.getBean().obtenerCategoria(), "PGB_AVALUOS"));
				InformacionBarrioBean.getBean().obligatoriedad(avaluoFacade.consultarCamposObligatoriosCategoria(
						InformacionInmuebleBean.getBean().obtenerCategoria(), "PGB_INFBARRIO"));
				InformacionInmuebleBean.getBean().obligatoriedad(avaluoFacade.consultarCamposObligatoriosCategoria(
						InformacionInmuebleBean.getBean().obtenerCategoria(), "PGB_INFINMUEBLE"));
				InformacionConstruccionBean.getBean().obligatoriedad(avaluoFacade.consultarCamposObligatoriosCategoria(
						InformacionInmuebleBean.getBean().obtenerCategoria(), "PGB_INFCONSTRUCCION"));
				InformacionConstruccionBean.getBean()
						.obligatoriedadSalubridad(avaluoFacade.consultarCamposObligatoriosCategoria(
								InformacionInmuebleBean.getBean().obtenerCategoria(), "PGB_CONDSALUBRIDAD"));
				OfertaDemandaBean.getBean().obligatoriedad(avaluoFacade.consultarCamposObligatoriosCategoria(
						InformacionInmuebleBean.getBean().obtenerCategoria(), "PGB_COMP_OFERTA_DEMANDA"));
				LiquidacionBean.getBean().obligatoriedad(avaluoFacade.consultarCamposObligatoriosCategoria(
						InformacionInmuebleBean.getBean().obtenerCategoria(), "PGB_LIQAVALUO"));
				ObservacionBean.getBean().obligatoriedad(avaluoFacade.consultarCamposObligatoriosCategoria(
						InformacionInmuebleBean.getBean().obtenerCategoria(), "PGB_OBSERVACIONES"));

			}
			avaluoFacade.validar(this.avaluo, getUsuario());
			// LUEGO INVOCAMOS LOS METODOS DE OBLIGATORIEDAD QUE CREEAREMOS PARA
			// CADA SECCCION. ENVIANDO EL LISTADO DE CAMPOS Y QUE ALLA COMPAREN EL LISTADO
			// ENVIANDO LA SECCION CON EL FIN DE REDUCIR LOS TIEMPOS DE RENDMIENTO ESE
			// METODO
			// QUE COMPARA DEBE SER GENERIDO DEJARLO EN EL SERVICIO O EN UTILITY

			/***/
			envioAprobacion = true;
			if (SesionBean.getBean().getPermisosEspecificos().containsKey(UtilConstantes.PERM_APROBADOR)
					&& SesionBean.getBean().getPermisosEspecificos().get(UtilConstantes.PERM_APROBADOR))
				PopupConfirmacionBean.getBean().confirmar("avaluoBean.confirmarValidacion",
						obtenerEtiqueta("titulo_confirmar_aprobacion"),
						obtenerEtiqueta("descripcion_confirmar_aprobacion"), null, "avaluoForm,encabezadoForm");
			else
				mensajeConfirmacion(obtenerEtiqueta("con_menValidar"));
		} catch (NegocioException e) {
			validador(e);
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void setConfirmarValidacion(String value) {
		aprobarAvaluo(true);
	}

	public boolean aprobarAvaluo(boolean imprimir) {
		try {
			this.imprimirPrevioAprobacion = imprimir;
//			if(this.avaluo.isProvisional()) {
//				 throw mgrExc.lanzarExcepcion(73L, TipoErrorNegocio.ERROR, null, new String[] {avaluo.getConsecutivoBanco().toString()});
//			}
			NegocioAlertaException alerta = avaluoFacade.preAprobar(avaluo, getUsuario());
			if (alerta != null)
				throw alerta;
			log.info("---Se inicia la accion de aprobacion");
			avaluoFacade.aprobar(this.avaluo, getUsuario());
			log.info("---Se termina  la accion de aprobacion");
			avaluo = avaluoFacade.consultarAvaluo(avaluo.getIdAvaluo());
			mensajeConfirmacion(obtenerEtiqueta("con_menAprobar"));//
			if (imprimirPrevioAprobacion)
				imprimir();
			log.info("---Se inicia  la accion de envio de correos");
			avaluoFacade.enviarCorreos(this.avaluo, getUsuario());
			log.info("---Se finaliza  la accion de envio de correos");
		} catch (NegocioAlertaException e) {
			PopupConfirmacionBean.getBean().confirmar("avaluoBean.confirmarAprobacion",
					obtenerEtiqueta("titulo_confirmar_pre_aprobacion"), e.obtenerTexto(), null,
					"avaluoForm,encabezadoForm");
		} catch (Exception e) {
			procesarError(e);
		}
		return false;
	}

	public void setConfirmarAprobacion(String value) {
		try {
			log.info("---Se confirma e inicia  la accion de aprobacion");
			avaluoFacade.aprobar(this.avaluo, getUsuario());
			log.info("---Se finailza  la accion de aprobacion");
			avaluo = avaluoFacade.consultarAvaluo(avaluo.getIdAvaluo());
			mensajeConfirmacion(obtenerEtiqueta("con_menAprobar"));
			if (imprimirPrevioAprobacion)
				imprimir();
			log.info("---Se inicia  la accion de envio de correos");
			avaluoFacade.enviarCorreos(this.avaluo, getUsuario());
			log.info("---Se finaliza  la accion de envio de correos");
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void aprobar() {
		aprobarAvaluo(false);
	}
	
	public void enviarAdjuntos() {		
		String id = this.avaluo.getObservacion().getArchivo().getIdDocumento();
		String nombreArchivo = this.avaluo.getObservacion().getArchivo().getNombreArchivo();		
		try {
			byte[] adjunto = avaluoFacade.obtenerDocumento(id);
			InputStream adjunt = new ByteArrayInputStream(adjunto);
			File pdf = avaluoFacade.imprimir(Collections.singletonList(avaluo), true, getUsuario());
			InputStream reporte = new FileInputStream(pdf);
			log.info("Envío del adjunto");
			archivoImpl.guardarEnBus(adjunt, nombreArchivo , getUsuario() , avaluo);
			log.info("Dirección del archivo a enviar " + pdf.getAbsolutePath());
			//archivoImpl.guardarEnBus(reporte, pdf.getName() , getUsuario() , avaluo);
			
			
		} catch (NegocioException | IOException e1) {
			procesarError(e1);
			}	
		
	}
	
	public void enviarNotificacion() throws NegocioException {
		
		complementosImpl.enviarNotificacion(this.avaluo, getUsuario());
		
	}

	public void cambiarDireccionComplementaria(ValueChangeEvent event) throws Exception {
		String valor = (String) event.getNewValue();
		cambioDireccion = true;
		esUnClick = false;
		longitud = null;
		latitud = null;
		avaluo.setLatitudSFBUA(null);
		avaluo.setLongitudSFBUA(null);
		if (UtilTexto.estaVacio(valor))
			return;
		valor = direccionesSvr.transformarDireccionComplementaria(valor);
		if (UtilTexto.estaVacio(valor)) {
			avaluo.setDireccionComplementaria((String) event.getNewValue());
			UtilJsf.mostrarMensaje("avaluoForm:edicionAvaluosTab:direccionComplementaria",
					obtenerEtiqueta("men_direccionNoValida"));
			marca = false;
			if (avaluo.getCodigoDaneDepartamento() != null && avaluo.getCodigoDaneCiudad() != null) {
				String direccionPrincipal = !UtilTexto.estaVacio(avaluo.getDireccionInmueble()) ? avaluo.getDireccionInmueble():valor;  
				if(!direccionPrincipal.isEmpty()) {
					agregarValoresMapa(avaluoFacade.obtenerExtentCentroide(
							(avaluo.getCodigoDaneDepartamento() < 10 ? "0" : "") + avaluo.getCodigoDaneDepartamento(),
							(avaluo.getCodigoDaneDepartamento() < 10 ? "0" : "") + avaluo.getCodigoDaneCiudad(), direccionPrincipal,
							avaluo.getBarrio()));
					reLocalizarMapa();
				}
				
			}
			return;
		} else if (valor.contains("|")) {
			StringTokenizer token = new StringTokenizer(valor, "|");
			token.nextToken();
			avaluo.setDireccionComplementaria(token.nextToken().trim());
		} else
			avaluo.setDireccionComplementaria(valor.trim());
		((UIInput) event.getComponent()).setValue(avaluo.getDireccionComplementaria());
		if (avaluo.getCodigoDaneDepartamento() != null && avaluo.getCodigoDaneCiudad() != null ) {
			if(UtilTexto.estaVacio(avaluo.getDireccionInmueble())) {
				agregarValoresMapa(avaluoFacade.obtenerExtentCentroide(
						(avaluo.getCodigoDaneDepartamento() < 10 ? "0" : "") + avaluo.getCodigoDaneDepartamento(),
						(avaluo.getCodigoDaneDepartamento() < 10 ? "0" : "") + avaluo.getCodigoDaneCiudad(),
						avaluo.getDireccionComplementaria(), avaluo.getBarrio()));
			}else {
				agregarValoresMapa(avaluoFacade.obtenerExtentCentroide(
						(avaluo.getCodigoDaneDepartamento() < 10 ? "0" : "") + avaluo.getCodigoDaneDepartamento(),
						(avaluo.getCodigoDaneDepartamento() < 10 ? "0" : "") + avaluo.getCodigoDaneCiudad(),
						avaluo.getDireccionInmueble(), avaluo.getBarrio()));
				
			}
			
			if(latitud == null && longitud == null) {
				Ciudad ciudad = ListaBean.getBean().obtenerCiudad(avaluo.getIdCiudad(), null);
				if(ciudad != null) {
					latitud = ciudad.getLatitud();
					longitud = ciudad.getLongitud();
					avaluo.setLatitudSFBUA(latitud);
					avaluo.setLongitudSFBUA(longitud);
					avaluo.setUbicacionGps(avaluo.getLatitudSFBUA() + "," + avaluo.getLongitudSFBUA());
					marca = true;
				}
			}
			
			reLocalizarMapa();
			
		}
			

	}

	@Deprecated
	public void cambiaDireccion(AjaxBehaviorEvent event) {
		UIInput entrada = (UIInput) event.getComponent();
		String valor = (String) entrada.getValue();
		String nuevaDireccion = null;
		boolean isDireccionComplementaria = entrada.getId().equalsIgnoreCase("direccionComplementaria");
		if (isDireccionComplementaria) {
			nuevaDireccion = direccionesSvr.transformarDireccion(valor, false);
		} else {
			nuevaDireccion = direccionesSvr.transformarDireccion(valor, true);
		}
		if (nuevaDireccion.contains("|")) {
			StringTokenizer token = new StringTokenizer(nuevaDireccion, "|");
			String primeraDireccion = token.nextToken();
			entrada.setValue(primeraDireccion);
			avaluo.setDireccionComplementaria(token.nextToken());
		} else if (!nuevaDireccion.isEmpty()) {
			entrada.setValue(nuevaDireccion);
		} else if (isDireccionComplementaria) {
			entrada.setValue(entrada.getValue());
		} else {
			entrada.setValue(nuevaDireccion);
			UtilJsf.mostrarMensaje("avaluoForm:edicionAvaluosTab:" + entrada.getId(),
					"Direccion No Cuenta con formato valido", FacesMessage.SEVERITY_ERROR,
					"Direccion No Cuenta con formato valido");
		}

	}

	public void editar(Avaluo avaluoEditar) {
		log.info("Ingreso al metodo editar de avaluos");
		try {
			tab = 0;
			verMapa = true;
			cambioDireccion = false;
			if (avaluoEditar == null)
				throw mgrExc.lanzarExcepcion(24, TipoErrorNegocio.FATAL);
			avaluo = avaluoEditar;
			this.mostrarLabel();
			this.ocultarLabel();
			ciudades = lista.cargarCiudades(avaluo.getIdDepartamento());
			limpiar();
			SesionBean.getBean().setEstaEditandoAvaluo(true);
			iniciarPestanias(false);
			Departamento avaluoDepartamento = ListaBean.getBean().obtenerDepartamento(avaluo.getIdDepartamento(), null);
			if (avaluoDepartamento != null)
				avaluo.setCodigoDaneDepartamento(avaluoDepartamento.getCodDane());
			if (avaluo.getIdCiudad() != null) {
				Ciudad ciudad = ListaBean.getBean().obtenerCiudad(avaluo.getIdCiudad(), null);
				if (ciudad != null)
					avaluo.setCodigoDaneCiudad(ciudad.getCodDane());
			}
			latitud = avaluo.getLatitudSFBUA();
			longitud = avaluo.getLongitudSFBUA();
			zoom = 16L;
			marca = true;
			esUnClick = false;
			extentXMax = null;
			extentXMin = null;
			extentYMax = null;
			extentYMin = null;
			spatialReferenceLatestWkid = null;
			spatialReferenceWkid = null;
			centerX = null;
			centerY = null;
			mapaTipo = null;
			reLocalizarMapa();
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void ocultarMapa() {
		verMapa = !verMapa;
	}

	private void iniciarPestanias(boolean omitir) {
		InformacionBarrioBean.getBean().editar(avaluo);
		InformacionInmuebleBean.getBean().editar(avaluo);
		InformacionConstruccionBean.getBean().editar(avaluo);
		OfertaDemandaBean.getBean().editar(avaluo);
		LiquidacionBean.getBean().editar(avaluo);
		ObservacionBean.getBean().editar(avaluo, omitir);
		
		if (!avaluo.isProvisional())
			RegistroFotograficoBean.getBean().setAvaluo(avaluo);

//			RegistroFotograficoBean.getBean().editar(avaluo, omitir);
		esNuevo = false;
	}

	private void limpiar() {
		tab = 0;
		ocultarAuditoria = true;
		UtilJsf.navegar(AvaluosCons.PAGINA_AVALUO);
	}

	public void nuevo() {
		//limpiar el registro fotografico
		RegistroFotograficoBean.getBean().limpiar();
		esNuevo = true;
		verMapa = true;
		cambioDireccion = false;
		marca = false;
		longitud = AvaluosCons.LONGITUD_DEFAULT;
		latitud = AvaluosCons.LATITUD_DEFAULT;
		esUnClick = false;
		avaluo = new Avaluo();
		avaluo.setNombreBanco(UtilConstantes.NOMBRE_ENTIDAD_DAVIVIENDA);
		avaluo.setFechaAvaluo(new Date());
		if (getUsuario() != null && getUsuario().getUsuario() != null) {
			avaluo.setUsuarioCreacion(getUsuario().getUsuario().getCodigo());
			avaluo.setUsuarioTransaccion(getUsuario().getUsuario().getCodigo());
		}
		avaluo.setFechaCreacion(new Date());
		avaluo.setFechaTransaccion(new Date());
		avaluo.setCodigoProcedencia(Procedencia.INDIVIDUAL.getValor());
		extentXMax = AvaluosCons.XMAX_DEFAULT;
		extentXMin = AvaluosCons.XMIN_DEFAULT;
		extentYMax = AvaluosCons.YMAX_DEFAULT;
		extentYMin = AvaluosCons.YMIN_DEFAULT;
		ciudades = new ArrayList<>();
		limpiar();
		log.info("Se obtiene el bean de sesion en nuevo avaluos");
		SesionBean.getBean().setEstaEditandoAvaluo(true);
		reLocalizarMapa();
	}

	public void cambiarDepartamento(ValueChangeEvent evento) throws Exception {
		if (evento == null || evento.getNewValue() == null)
			return;
		// se obtiene el valor del evento
		Long valor = (Long) evento.getNewValue();
		// se valida si está vacío.
		if (valor == null) {
			ciudades = new ArrayList<>();
			avaluo.setIdCiudad(null);
			avaluo.setCodigoDaneCiudad(null);
			return;
		} else if (!valor.equals(evento.getOldValue())) {
			avaluo.setIdCiudad(null);
			avaluo.setCodigoDaneCiudad(null);
		}
		// se obtiene la lista de ciudades a traves del codigo del departamento
		ciudades = lista.cargarCiudades(valor);
		// se obtiene el departamento
		Departamento departamento = ListaBean.getBean().obtenerDepartamento(valor, null);
		// validar si obtuvo los datos del departamento
		if (departamento == null)
			return;
		// se envia el codigo divipola para obtener las coordenadas y recibe un arreglo
		// de strings
		avaluo.setCodigoDaneDepartamento(departamento.getCodDane());
		longitud = null;
		latitud = null;
		avaluo.setLatitudSFBUA(null);
		avaluo.setLongitudSFBUA(null);
		avaluo.setUbicacionGps(null);
		esUnClick = false;
	}

	public void agregarValoresMapa(ArrayList<String> results) {
		boolean respuestaFallida = results == null || results.isEmpty() || results.get(0).equalsIgnoreCase("None")
				|| results.get(0).equalsIgnoreCase("Error");
		extentXMin = respuestaFallida ? AvaluosCons.XMIN_DEFAULT : results.get(0);
		extentYMin = respuestaFallida ? AvaluosCons.YMIN_DEFAULT : results.get(1);
		extentXMax = respuestaFallida ? AvaluosCons.XMAX_DEFAULT : results.get(2);
		extentYMax = respuestaFallida ? AvaluosCons.YMAX_DEFAULT : results.get(3);
		if (!respuestaFallida && results.size() > 4 && longitud == null && latitud == null) {
			log.info("ingresa respuesta exitosa mapas!!!!");
			longitud = results.get(4);
			latitud = results.get(5);
			avaluo.setLatitudSFBUA(latitud);
			avaluo.setLongitudSFBUA(longitud);
			avaluo.setUbicacionGps(avaluo.getLatitudSFBUA() + "," + avaluo.getLongitudSFBUA());
			marca = true;
		} else {
			log.info("ingresa respuesta fallida mapas!!!!");
			longitud = avaluo.getLongitudSFBUA();
			latitud = avaluo.getLatitudSFBUA();
			log.info("valor longitud mapas : "+longitud);
			log.info("valor latitud mapas :  "+latitud);
		}
		spatialReferenceLatestWkid = null;
		spatialReferenceWkid = null;
		centerX = null;
		centerY = null;
		mapaTipo = null;
	}

	/**
	 * Metodo encargado de relocalizar el mapa de acuerdo al departamento y/o ciudad
	 * seleccionado por el usuario
	 */
	public void reLocalizarMapa() {			
			if(tab ==0) {
				log.info("Inicia Llamado al metodo reLocalizarMapa.tab:" + tab);
				long tiempoInicial = System.currentTimeMillis();
		    	log.info("Tiempo de inicio localización mapa: "+tiempoInicial);				
				if(latitud == null && longitud == null) {
					if(avaluo == null) {
						longitud = AvaluosCons.LONGITUD_DEFAULT;
						latitud = AvaluosCons.LATITUD_DEFAULT;
						extentXMax = AvaluosCons.XMAX_DEFAULT;
						extentXMin = AvaluosCons.XMIN_DEFAULT;
						extentYMax = AvaluosCons.YMAX_DEFAULT;
						extentYMin = AvaluosCons.YMIN_DEFAULT;
					}else {
						ubicacion = avaluo.getUbicacionGps();
						if(ubicacion == null && esNuevo) {
							longitud = AvaluosCons.LONGITUD_DEFAULT;
							latitud = AvaluosCons.LATITUD_DEFAULT;
							extentXMax = AvaluosCons.XMAX_DEFAULT;
							extentXMin = AvaluosCons.XMIN_DEFAULT;
							extentYMax = AvaluosCons.YMAX_DEFAULT;
							extentYMin = AvaluosCons.YMIN_DEFAULT;
						}
						if(ubicacion != null) {
							String[] coordenadas = ubicacion.split(",");
							latitud = coordenadas[0];
							longitud = coordenadas[1];
							this.avaluo.setLatitudSFBUA(latitud);
							this.avaluo.setLongitudSFBUA(longitud);
							zoom = 16L;
							marca = true;
							esUnClick = false;
							extentXMax = null;
							extentXMin = null;
							extentYMax = null;
							extentYMin = null;
							spatialReferenceLatestWkid = null;
							spatialReferenceWkid = null;
							centerX = null;
							centerY = null;
							mapaTipo = null;
						}
					}		
				}
				StringBuilder javaScriptFunction = new StringBuilder();
				javaScriptFunction.append("reLocalizarMapa(");
				javaScriptFunction.append(longitud + ",");
				javaScriptFunction.append(latitud + ",");
				if (mapaTipo != null) {
					javaScriptFunction.append("\"" + mapaTipo + "\",");
				} else {
					javaScriptFunction.append(mapaTipo + ",");
				}
				javaScriptFunction.append(UtilNumero.pasarDouble(spatialReferenceLatestWkid) + ",");
				javaScriptFunction.append(UtilNumero.pasarDouble(spatialReferenceWkid) + ",");
				javaScriptFunction.append(UtilNumero.pasarDouble(centerX) + ",");
				javaScriptFunction.append(UtilNumero.pasarDouble(centerY) + ",");
				javaScriptFunction.append(UtilNumero.pasarDouble(extentXMax) + ",");
				javaScriptFunction.append(UtilNumero.pasarDouble(extentXMin) + ",");
				javaScriptFunction.append(UtilNumero.pasarDouble(extentYMax) + ",");
				javaScriptFunction.append(UtilNumero.pasarDouble(extentYMin) + ",");
				javaScriptFunction.append(zoom + ",");
				javaScriptFunction.append(marca + ",");
				javaScriptFunction.append(esUnClick + ",");
				javaScriptFunction.append("\"" + mapaBase + "\");");
				RequestContext.getCurrentInstance().execute(javaScriptFunction.toString());	
				long tiempoFinal = System.currentTimeMillis();
		    	log.info("Tiempo final localización mapa: "+tiempoFinal);	
		    	long tiempoTotal = tiempoFinal - tiempoInicial;
		    	log.info("Tiempo total mapas: "+ tiempoTotal);
				log.info("Finaliza Llamado al metodo reLocalizarMapa.tab:" + tab);
			}
	}// mapZoom, mapaTipo, mapaBase

	/**
	 * Metodo encargado de recibir las coordenadas seleccionadas por el usuario,
	 * invocado por JavaScript en el metodo mostrarCoordenadas, a traves del
	 * remoteCommand rcActualizarCoordenadas
	 * 
	 * @param String longitudSeleccionada por el usuario
	 * @param String longitudSeleccionada por el usuario
	 */
	public void enviarCoordenadasSeleccionadas(String vistaLongitud, String vistaLatitud, String vistaTipo,
			String vistaSpatialReferenceLatestWkid, String vistaSpatialReferenceWkid, String vistaCenterX,
			String vistaCenterY, String vistaExtentXmax, String vistaExtentXmin, String vistaExtentYmax,
			String vistaExtentYmin, String vistaZoom) {

		avaluo.setLatitudSFBUA(vistaLatitud);
		avaluo.setLongitudSFBUA(vistaLongitud);
		avaluo.setUbicacionGps(vistaLatitud + "," + vistaLongitud);
		/*
		 * 
		 * Setear valores obtenidos del mapa
		 * 
		 */
		marca = true;
		longitud = vistaLongitud;
		latitud = vistaLatitud;
		mapaTipo = vistaTipo;
		spatialReferenceLatestWkid = vistaSpatialReferenceLatestWkid;
		spatialReferenceWkid = vistaSpatialReferenceWkid;
		centerX = vistaCenterX;
		centerY = vistaCenterY;
		extentXMax = vistaExtentXmax;
		extentXMin = vistaExtentXmin;
		extentYMax = vistaExtentYmax;
		extentYMin = vistaExtentYmin;
		zoom = UtilNumero.pasarEntero(vistaZoom);
		esUnClick = true;
		reLocalizarMapa();

	}

	public void cambiarCiudad(ValueChangeEvent evento) throws Exception {
		Long valor = (Long) evento.getNewValue();
		if (valor == null)
			return;
		// se obtiene el ciudad
		Ciudad ciudad = ListaBean.getBean().obtenerCiudad(valor, null);
		
		marca = false;
		esUnClick = false;
		// validar si obtuvo los datos del ciudad
		if (ciudad == null)
			return;
		avaluo.setCodigoDaneCiudad(ciudad.getCodDane());
		longitud = ciudad.getLongitud();
		latitud = ciudad.getLatitud();
		avaluo.setLatitudSFBUA(longitud);
		avaluo.setLongitudSFBUA(latitud);
		avaluo.setUbicacionGps(avaluo.getLatitudSFBUA() + "," + avaluo.getLongitudSFBUA());
	}

	public void cambiarFecha(ValueChangeEvent event) {
		try {
			Date valor = (Date) event.getNewValue();
			if (valor == null) {
				avaluo.setConsecutivoBanco(null);
				return;
			}
			if (avaluo.getNumeroIdentificacion() == null)
				return;
			avaluo.setFechaAvaluo(valor);
			String textoFecha = new SimpleDateFormat("ddMMYYY").format(valor);
			avaluo.setConsecutivoBanco(new BigInteger(textoFecha + avaluo.getNumeroIdentificacion()));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void cambiarDocumento(ValueChangeEvent event) {
		try {
			Long valor = (Long) event.getNewValue();
			if (valor == null) {
				avaluo.setConsecutivoBanco(null);
				return;
			}
			if (avaluo.getFechaAvaluo() == null)
				return;
			String textoFecha = new SimpleDateFormat("ddMMyyyy").format(avaluo.getFechaAvaluo());
			avaluo.setConsecutivoBanco(new BigInteger(textoFecha + valor));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void cambiarConstructor() {
		try {
			// if (avaluo.isConstructor())
			// UtilJsf.mostrarMensaje(MENSAJE_ESPECIFICO,
			// obtenerEtiqueta("ina_menProyectoConstructor"));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void cambiarProvisional() {
		try {
			if (avaluo.isProvisional())
				UtilJsf.mostrarMensaje(MENSAJE_ESPECIFICO, obtenerEtiqueta("ina_menProvisional"));
		} catch (Exception e) {
			procesarError(e);
		}
	}
	
	
	public void cambiarMovil() {		
		
		try {
			if (avaluo.isMovil())
				UtilJsf.mostrarMensaje(MENSAJE_ESPECIFICO, obtenerEtiqueta("ina_menMovil"));
			else {
				UtilJsf.mostrarMensaje(MENSAJE_ESPECIFICO, obtenerEtiqueta("ina_menTradicional"));
				}
		} catch (Exception e) {
			procesarError(e);
		}
	}
	
	public void mostrarLabel() {
	   movil = avaluo.isMovil();
	   tradicional = avaluo.isTradicional();
		if(movil) {
		labelMovil = true;
		avaluo.isMovil();
		avaluo.setTipoAvaluo(TipoAvaluo.MOVIL.getValor());	
		tradicional = false;		 
		}else {
			tradicional = true;
			labelMovil = false;	
		}
		
		
	}
	
	public void ocultarLabel() {
		movil = avaluo.isMovil();
		tradicional = avaluo.isTradicional();	
		if (tradicional) {
		labelMovil = false;		
		avaluo.setTipoAvaluo(TipoAvaluo.TRADICIONAL.getValor());
		movil = false; 
		avaluo.setNumeroSolicitudMovil(null);
		}
	}

	public void enviarAprobacion() {
		try {
			avaluoFacade.enviarParaAprobacion(Arrays.asList(new Avaluo[] { avaluo }), getUsuario());
			mensajeConfirmacion(obtenerEtiqueta("con_menEnviadoAprobacion"));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void guardar() throws Exception {
		if (!validar())
			throw mgrExc.lanzarExcepcion(22, TipoErrorNegocio.ALERTA);
		avaluoFacade.guardar(avaluo, getUsuario());
		iniciarPestanias(!esNuevo);
		UtilJsf.mostrarMensaje(MENSAJE_ESPECIFICO, obtenerEtiqueta("ina_menGuardar"));
	}

	public void comandoAvanzar() {
		if (isEsNuevo())
			return;
		if (tab == 3)
			InformacionConstruccionBean.getBean().avanzar();
		else if (tab < 7)
			tab++;
	}

	public void comandoRetroceder() {
		if (isEsNuevo())
			return;
		if (tab == 3)
			InformacionConstruccionBean.getBean().retroceder();
		else if (tab > 0)
			tab--;
	}

	public void comandoGuardar() {
		switch (tab) {
		case 0:
			siguiente();
			break;
		case 1:
			InformacionBarrioBean.getBean().siguiente();
			break;
		case 2:
			InformacionInmuebleBean.getBean().siguiente();
			break;
		case 3:
			InformacionConstruccionBean.getBean().siguiente();
			break;
		case 4:
			OfertaDemandaBean.getBean().siguiente();
			break;
		case 5:
			LiquidacionBean.getBean().siguiente();
			break;
		case 6:
			ObservacionBean.getBean().siguiente();
			break;
		case 7:
			Tiempo.setTiempoInicial(System.currentTimeMillis());// ARINCON
			RegistroFotograficoBean.getBean().siguiente();
			break;
		default:
			break;
		}
	}

	public void comandoAnterior() {
		try {
			switch (tab) {
			case 0:
				guardar();
				break;
			case 1:
				InformacionBarrioBean.getBean().anterior();
				break;
			case 2:
				InformacionInmuebleBean.getBean().anterior();
				break;
			case 3:
				InformacionConstruccionBean.getBean().anterior();
				break;
			case 4:
				OfertaDemandaBean.getBean().anterior();
				break;
			case 5:
				LiquidacionBean.getBean().anterior();
				break;
			case 6:
				ObservacionBean.getBean().anterior();
				break;
			case 7:
				RegistroFotograficoBean.getBean().anterior();
				break;
			default:
				break;
			}
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void imprimir() {
		try {
			if (avaluo.isProvisional())
				ImpresionBean.getBean()
						.imprimir(avaluoFacade.imprimir(Collections.singletonList(avaluo), false, getUsuario()));
			else
				ImpresionBean.getBean()
						.imprimir(avaluoFacade.imprimir(Collections.singletonList(avaluo), true, getUsuario()));
			mensajeConfirmacion(obtenerEtiqueta("con_menImprimir"));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void siguiente() {
		try {
			if (!isEditable()) {
				tab = 1;
			} else {
				guardar();
				tab = 1;
			}
		} catch (Exception e) {
			procesarError(MENSAJE_ESPECIFICO, e);
		}
	}

	public void ocultar() {
		ocultarAuditoria = !ocultarAuditoria;
	}

	public boolean validar() {
		boolean validar = true;
		validar &= UtilValidadorJsf.validar(avaluo.getNombreSolicitante(),
				"avaluoForm:edicionAvaluosTab:nombreSolicitante", true, 60, null);
		validar &= UtilValidadorJsf.validar(avaluo.getIdTipoIdentificacion(),
				"avaluoForm:edicionAvaluosTab:idTipoIdentificacion", true, 13, null);
		validar &= UtilValidadorJsf.validar(avaluo.getNumeroIdentificacion(),
				"avaluoForm:edicionAvaluosTab:numeroIdentificacion", true, 10, null);
		validar &= UtilValidadorJsf.validar(avaluo.getNombreBanco(), "avaluoForm:edicionAvaluosTab:nombreBanco", true,
				30, null);
		validar &= UtilValidadorJsf.validar(avaluo.getFechaAvaluo(), "avaluoForm:edicionAvaluosTab:fechaAvaluo", true,
				null);
		validar &= UtilValidadorJsf.validar(avaluo.getConsecutivoBanco(),
				"avaluoForm:edicionAvaluosTab:consecutivoBanco", true, 22, null);
		validar &= UtilValidadorJsf.validar(avaluo.getIdObjetoAvaluo(), "avaluoForm:edicionAvaluosTab:idObjetoAvaluo",
				true, 22, null);


		validar &= UtilValidadorJsf.validar(avaluo.getDireccionInmueble(),
				"avaluoForm:edicionAvaluosTab:direccionInmueble",
				!UtilTexto.estaVacio(avaluo.getDireccionComplementaria()) ? false : true, 100, null);

		if (!UtilTexto.estaVacio(avaluo.getDireccionInmueble())) {
			if (cambioDireccion) {
				String valor = direccionesSvr.transformarDireccion(avaluo.getDireccionInmueble(), true);
				if (UtilTexto.estaVacio(valor)||(valor.equals("ERROR"))) {
					UtilJsf.mostrarMensaje("avaluoForm:edicionAvaluosTab:direccionInmueble",
							obtenerEtiqueta("men_direccionNoValida"));
					validar &= false;
				}
			} else {
				try {
					validar &= cambiarDireccion(avaluo.getDireccionInmueble());
				} catch (Exception e1) {
					UtilJsf.mostrarMensaje("avaluoForm:edicionAvaluosTab:direccionInmueble", e1.getMessage());
					validar &= false;
				}
			}
		}
		validar &= UtilValidadorJsf.validarFormato(avaluo.getDireccionInmueble(),
				"avaluoForm:edicionAvaluosTab:direccionInmueble",
				!UtilTexto.estaVacio(avaluo.getDireccionComplementaria()) ? false : true, 100, "^[a-zA-Z0-9 ]*$", null,
				" Ej:CR 13 34 99");
		validar &= UtilValidadorJsf.validar(avaluo.getDireccionComplementaria(),
				"avaluoForm:edicionAvaluosTab:direccionComplementaria",
				!UtilTexto.estaVacio(avaluo.getDireccionInmueble()) ? false : true, 100, null);

		if (!UtilTexto.estaVacio(avaluo.getDireccionComplementaria())
				&& UtilTexto.estaVacio(avaluo.getDireccionInmueble())) {
			if (cambioDireccion) {
				String valor = direccionesSvr.transformarDireccionComplementaria(avaluo.getDireccionComplementaria());
				if (UtilTexto.estaVacio(valor)) {
					UtilJsf.mostrarMensaje("avaluoForm:edicionAvaluosTab:direccionComplementaria",
							obtenerEtiqueta("men_direccionNoValida"));
					validar &= false;
				}
			} else {
				try {
					validar &= cambiarDireccionComplementaria(avaluo.getDireccionComplementaria());
				} catch (Exception e1) {
					UtilJsf.mostrarMensaje("avaluoForm:edicionAvaluosTab:direccionComplementaria", e1.getMessage());
					validar &= false;
				}
			}
		}

		validar &= UtilValidadorJsf.validar(avaluo.getBarrio(), "avaluoForm:edicionAvaluosTab:barrio", true, 30, null);
		validar &= UtilValidadorJsf.validar(avaluo.getNombreConjuntoEdificio(),
				"avaluoForm:edicionAvaluosTab:nombreConjuntoEdificio", false, 30, null);
		validar &= UtilValidadorJsf.validar(avaluo.getIdDepartamento(), "avaluoForm:edicionAvaluosTab:idDepartamento",
				true, 13, null);
		validar &= UtilValidadorJsf.validar(avaluo.getIdCiudad(), "avaluoForm:edicionAvaluosTab:idCiudad", true, 13,
				null);
		validar &= UtilValidadorJsf.validar(avaluo.getSector(), "avaluoForm:edicionAvaluosTab:sector", true, 3, null);
		validar &= UtilValidadorJsf.validar(avaluo.getTelefonoSolicitante(),
				"avaluoForm:edicionAvaluosTab:telefonoSolicitante", false, 7, null);
		validar &= UtilValidadorJsf.validar(avaluo.getCelularSolicitante(),
				"avaluoForm:edicionAvaluosTab:celularSolicitante", false, 10, null);
		validar &= UtilValidadorJsf.validarCorreo(avaluo.getCorreoSolicitante(),
				"avaluoForm:edicionAvaluosTab:correoSolicitante", false, 60, null);
		validar &= UtilValidadorJsf.validar(avaluo.getIdMetodologia(), "avaluoForm:edicionAvaluosTab:idMetodologia",
				false, 22, null);
		validar &= UtilValidadorJsf.validar(avaluo.getJustificacion(), "avaluoForm:edicionAvaluosTab:justificacion",
				false, 150, null);
		validar &= UtilValidadorJsf.validar(avaluo.getCodTipoAvaluo(), "avaluoForm:edicionAvaluosTab:tipoVivienda",
				false, 3, null);
		validar &= UtilValidadorJsf.validar(avaluo.getLatitudSFBUA(), "avaluoForm:edicionAvaluosTab:latitud", true, 30,
				null);
		validar &= UtilValidadorJsf.validar(avaluo.getLongitudSFBUA(), "avaluoForm:edicionAvaluosTab:longitud", true,
				30, null);
		
		if(avaluo.isMovil()) {
		
		validar &= UtilValidadorJsf.validar(avaluo.getNumeroSolicitudMovil(), "avaluoForm:edicionAvaluosTab:numeroSolicitud", true,
				18, null);
		}
		return validar;
	}

	public static synchronized boolean esObligatorio(List<ArchivoPlano> obligatorios, Long consecutivo) {
		if (obligatorios == null || obligatorios.isEmpty() || consecutivo == null || consecutivo.compareTo(0L) == 0)
			return false;
		for (ArchivoPlano archivoPlano : obligatorios) {
			if (consecutivo.compareTo(archivoPlano.getConsecutivoPlano()) == 0)
				return true;
		}
		return false;
	}

	public void obligatoriedad(List<ArchivoPlano> obligatorios) {
		if (obligatorios == null || obligatorios.isEmpty())
			return;
		UtilValidadorJsf.validar(avaluo.getNombreSolicitante(), "avaluoForm:edicionAvaluosTab:nombreSolicitante",
				esObligatorio(obligatorios, 209L), 60, null);
		UtilValidadorJsf.validar(avaluo.getIdTipoIdentificacion(), "avaluoForm:edicionAvaluosTab:idTipoIdentificacion",
				esObligatorio(obligatorios, 40L), 13, null);
		UtilValidadorJsf.validar(avaluo.getNumeroIdentificacion(), "avaluoForm:edicionAvaluosTab:numeroIdentificacion",
				esObligatorio(obligatorios, 140L), 10, null);
		UtilValidadorJsf.validar(avaluo.getNombreBanco(), "avaluoForm:edicionAvaluosTab:nombreBanco",
				esObligatorio(obligatorios, 0L), 30, null);
		UtilValidadorJsf.validar(avaluo.getFechaAvaluo(), "avaluoForm:edicionAvaluosTab:fechaAvaluo",
				esObligatorio(obligatorios, 56L), null);
		UtilValidadorJsf.validar(avaluo.getConsecutivoBanco(), "avaluoForm:edicionAvaluosTab:consecutivoBanco",
				esObligatorio(obligatorios, 131L), 22, null);
		UtilValidadorJsf.validar(avaluo.getIdObjetoAvaluo(), "avaluoForm:edicionAvaluosTab:idObjetoAvaluo",
				esObligatorio(obligatorios, 39L), 22, null);
		UtilValidadorJsf.validar(avaluo.getDireccionInmueble(), "avaluoForm:edicionAvaluosTab:direccionInmueble",
				esObligatorio(obligatorios, 195L), 100, null);
		UtilValidadorJsf.validar(avaluo.getDireccionComplementaria(),
				"avaluoForm:edicionAvaluosTab:direccionComplementaria", esObligatorio(obligatorios, 0L), 100, null);
		UtilValidadorJsf.validar(avaluo.getBarrio(), "avaluoForm:edicionAvaluosTab:barrio",
				esObligatorio(obligatorios, 190L), 30, null);
		UtilValidadorJsf.validar(avaluo.getNombreConjuntoEdificio(),
				"avaluoForm:edicionAvaluosTab:nombreConjuntoEdificio", esObligatorio(obligatorios, 208L), 30, null);
		UtilValidadorJsf.validar(avaluo.getIdDepartamento(), "avaluoForm:edicionAvaluosTab:idDepartamento",
				esObligatorio(obligatorios, 37L), 13, null);
		UtilValidadorJsf.validar(avaluo.getIdCiudad(), "avaluoForm:edicionAvaluosTab:idCiudad",
				esObligatorio(obligatorios, 36L), 13, null);
		UtilValidadorJsf.validar(avaluo.getSector(), "avaluoForm:edicionAvaluosTab:sector",
				esObligatorio(obligatorios, 259L), 3, null);
		UtilValidadorJsf.validar(avaluo.getTelefonoSolicitante(), "avaluoForm:edicionAvaluosTab:telefonoSolicitante",
				esObligatorio(obligatorios, 260L), 7, null);
		UtilValidadorJsf.validar(avaluo.getCelularSolicitante(), "avaluoForm:edicionAvaluosTab:celularSolicitante",
				esObligatorio(obligatorios, 261L), 10, null);
		UtilValidadorJsf.validarCorreo(avaluo.getCorreoSolicitante(), "avaluoForm:edicionAvaluosTab:correoSolicitante",
				esObligatorio(obligatorios, 262L), 60, null);
		UtilValidadorJsf.validar(avaluo.getIdMetodologia(), "avaluoForm:edicionAvaluosTab:idMetodologia",
				esObligatorio(obligatorios, 38L), 22, null);
		UtilValidadorJsf.validar(avaluo.getJustificacion(), "avaluoForm:edicionAvaluosTab:justificacion",
				esObligatorio(obligatorios, 198L), 150, null);
		UtilValidadorJsf.validar(avaluo.getCodTipoAvaluo(), "avaluoForm:edicionAvaluosTab:tipoVivienda",
				esObligatorio(obligatorios, 0L), 3, null);
		UtilValidadorJsf.validar(avaluo.getLatitudSFBUA(), "avaluoForm:edicionAvaluosTab:latitud",
				esObligatorio(obligatorios, 229L), 30, null);
		UtilValidadorJsf.validar(avaluo.getLongitudSFBUA(), "avaluoForm:edicionAvaluosTab:longitud",
				esObligatorio(obligatorios, 232L), 30, null);
	}

	/**
	 * permite determinar si un avalujo se encuentra en estado nuevo.
	 * 
	 * @return verdadero en caso de que el avaluo este en estado nuevo.
	 */
	public boolean isEstadoNuevo() {
		return avaluo == null || avaluo.getEstadoAvaluo() == null
				|| EstadoAvaluo.NUEVO.getValor().compareTo(avaluo.getEstadoAvaluo()) == 0;
	}

	/*
	 * gettes y setters
	 */
	public boolean isLabelMovil() {
		return labelMovil;
	}

	public void setLabelMovil(boolean labelMovil) {
		this.labelMovil = labelMovil;
	}
	
	public Avaluo getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(Avaluo avaluo) {
		this.avaluo = avaluo;
	}

	public boolean isEsNuevo() {
		return esNuevo;
	}

	public void setEsNuevo(boolean esNuevo) {
		this.esNuevo = esNuevo;
	}

	public boolean isEstadoAprobado() {
		return avaluo != null && avaluo.isAprobado();
	}

	public boolean isEstadoEliminado() {
		return avaluo != null && avaluo.isEliminado();
	}

	public int getTab() {
		return tab;
	}

	public void setTab(int tab) {
		this.tab = tab;
	}

	public List<SelectItem> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<SelectItem> ciudades) {
		this.ciudades = ciudades;
	}

	public boolean isOcultarAuditoria() {
		return ocultarAuditoria;
	}

	public void setOcultarAuditoria(boolean ocultarAuditoria) {
		this.ocultarAuditoria = ocultarAuditoria;
	}

	public boolean isEnvioAprobacion() {
		return envioAprobacion;
	}

	public boolean isVerMapa() {
		return verMapa;
	}

	 @Override
	public boolean isEditable() {
		return super.isEditable() && !this.isEstadoAprobado();
	}

	public AvaluoFullDTO getAvaluoDto() {
		return avaluoDto;
	}

	public void setAvaluoDto(AvaluoFullDTO avaluoDto) {
		this.avaluoDto = avaluoDto;
	}

	public boolean isTradicional() {
		return tradicional;
	}

	public void setTradicional(boolean tradicional) {
		this.tradicional = tradicional;
	}

	public boolean isMovil() {
		return movil;
	}

	public void setMovil(boolean movil) {
		this.movil = movil;
	}

}