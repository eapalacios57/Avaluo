//
//package com.segurosbolivar.avaluos.modelo.ws.service.impl;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import javax.ejb.EJB;
//import javax.ejb.Stateless;
//import javax.xml.ws.BindingProvider;
//import javax.xml.ws.soap.MTOMFeature;
//
//import com.asesoftware.util.archivo.UtilPropiedades;
//import com.asesoftware.util.cons.TipoErrorNegocio;
//import com.asesoftware.util.exception.NegocioException;
//import com.segurosbolivar.avaluos.modelo.service.impl.ManejadorErroresNegocio;
//import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.borrar.BorrarDocumento;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.borrar.BorrarDocumentoResponse;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.borrar.BorrarDocumentoService;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.borrar.BorrarDocumentoService_Service;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.consultar.ConsultarDocumentoResponse;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.consultar.ConsultarDocumentoService;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.consultar.ConsultarDocumentoService_Service;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.consultar.FiltroDocumentos;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.consultaringresar.IngresaConsultaDocumentoResponse;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.consultaringresar.IngresaConsultaDocumentoService;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.consultaringresar.IngresaConsultaDocumentoService_Service;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.ingresar.IngresarDocumentoResponse;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.ingresar.IngresarDocumentoService;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.ingresar.IngresarDocumentoService_Service;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.ingresar.ListaDocumento;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.modificar.ModificarDocumentoResponse;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.modificar.ModificarDocumentoService;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.modificar.ModificarDocumentoService_Service;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.modificar.NuevoDocumento;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.obtener.ObtenerDocumento;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.obtener.ObtenerDocumentoResponse;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.obtener.ObtenerDocumentoService;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.obtener.ObtenerDocumentoService_Service;
//import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.utilities.HeaderHandleResolver;
//import java.io.Serializable;
//
///**
// * Servicio EJB encargado de invocar los clientes de los servicios web de
// * FileNet
// * 
// * @author lvalbuena
// *
// */
//@Stateless
//public class ClienteFileNetWebService implements Serializable {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@EJB
//	private ManejadorErroresNegocio mgrExc;
//
//	private static ResourceBundle bundle;
//	static {
//		try {
//			bundle = UtilPropiedades.leerProperties(UtilConstantes.RUTA_PROPERTIES);
//		} catch (NegocioException e) {
//			Logger.getLogger(ClienteFcaWebService.class.getName()).log(Level.SEVERE,
//					"No se ha podido leer el archivo de propiedades", e);
//		}
//	}
//
//	// CONSULTAR DOCUMENTOS
//	private ConsultarDocumentoService_Service consultarDocumentoServicePort;
//	private ConsultarDocumentoService consultarDocumentoService;
//
//	// OBTENER DOCUMENTOS
//	private ObtenerDocumentoService_Service obtenerDocumentoServicePort;
//	private ObtenerDocumentoService obtenerDocumentoService;
//
//	// INGRESAR DOCUMENTOS
//	private IngresaConsultaDocumentoService_Service ingresarConsultarDocumentoServicePort;
//	private IngresaConsultaDocumentoService ingresarConsultarDocumentoService;
//
//	// INGRESAR DOCUMENTOS
//	private IngresarDocumentoService_Service ingresarDocumentoServicePort;
//	private IngresarDocumentoService ingresarDocumentoService;
//
//	// MODIFICAR DOCUMENTOS
//	private ModificarDocumentoService_Service modificarDocumentoServicePort;
//	private ModificarDocumentoService modificarDocumentoService;
//
//	// BORRAR DOCUMENTOS
//	private BorrarDocumentoService_Service borrarDocumentoServicePort;
//	private BorrarDocumentoService borrarDocumentoService;
//
//	private String endPointRaiz;
//	private HeaderHandleResolver handleResolver;
//
//	/**
//	 * Instancia los clientes de los servicios
//	 */
//	// fmelo [29-01-2019] CAMBIO-001: Ajuste por inconsistencia en contenedor con
//	// handleResolver
//	// ******************** INICIO ******************//
//	// CAMBIO-001
//	// ******************** INICIO ******************//
//	// @PostConstruct
//	public void inicializar() {
//		endPointRaiz = UtilPropiedades.cargarPropiedad(bundle, UtilConstantes.ENDPOINT_FILENET_RAIZ);
//		handleResolver = new HeaderHandleResolver();
//	}
//	// ******************** FIN ******************//
//
//	/**
//	 * Invoca operacion opConsultarDocumento del servicio de consulya de documentos
//	 * 
//	 * @param parameters
//	 * @return
//	 */
//	public ConsultarDocumentoResponse consultarDocumento(FiltroDocumentos parameters) throws NegocioException {
//		// CONSULTAR DOCUMENTOS
//		try {
//			// CAMBIO-001
//			// ******************** INICIO ******************//
//			inicializar();
//			// ******************** FIN ******************//
//
//			this.consultarDocumentoServicePort = new ConsultarDocumentoService_Service(new URL(
//					endPointRaiz + UtilPropiedades.cargarPropiedad(bundle, UtilConstantes.ENDPOINT_FILENET_CONSULTAR)));
//			this.consultarDocumentoServicePort.setHandlerResolver(handleResolver);
//			this.consultarDocumentoService = this.consultarDocumentoServicePort.getConsultarDocumentoWSSOAP();
//			((BindingProvider) this.consultarDocumentoServicePort).getRequestContext()
//					.put("com.sun.xml.ws.request.timeout", 9000);
//			return consultarDocumentoService.opConsultarDocumento(parameters).getReturn();
//		} catch (Exception e) {
//			Logger.getLogger(ClienteFcaWebService.class.getName()).log(Level.SEVERE,
//					"Se presento  un error conectandodose a Filenet", e);
//			throw mgrExc.lanzarExcepcion(31, TipoErrorNegocio.ERROR, e.getMessage(), null);
//		}
//	}
//
//	public ObtenerDocumentoResponse obtenerDocumento(ObtenerDocumento obtenerDocumento) throws NegocioException {
//		// OBTENER DOCUMENTOS
//		try {
//			// CAMBIO-001
//			// ******************** INICIO ******************//
//			inicializar();
//			// ******************** FIN ******************//
//
//			this.obtenerDocumentoServicePort = new ObtenerDocumentoService_Service(new URL(
//					endPointRaiz + UtilPropiedades.cargarPropiedad(bundle, UtilConstantes.ENDPOINT_FILENET_OBTENER)));
//			long tiempoInicial = System.currentTimeMillis();
//			this.obtenerDocumentoServicePort.setHandlerResolver(handleResolver);
//			this.obtenerDocumentoService = this.obtenerDocumentoServicePort.getObtenerDocumentoWSSOAP();
//			((BindingProvider) this.obtenerDocumentoService).getRequestContext().put("com.sun.xml.ws.request.timeout",
//					9000);
//			ObtenerDocumentoResponse respuesta = obtenerDocumentoService.opObtenerDocumento(obtenerDocumento).getReturn();
//			Logger.getLogger(ClienteFcaWebService.class.getName()).log(Level.SEVERE,
//					"Tiempo de respuesta del servicio obtener documento " + (System.currentTimeMillis() - tiempoInicial)+" ms");
//			return respuesta;
//		} catch (Exception e) {
//			Logger.getLogger(ClienteFcaWebService.class.getName()).log(Level.SEVERE,
//					"Se presento  un error conectandodose a Filenet", e);
//			throw mgrExc.lanzarExcepcion(31, TipoErrorNegocio.ERROR,
//					e.getCause() != null ? e.getCause().getMessage() : e.getMessage(), null);
//		}
//	}
//
//	public IngresarDocumentoResponse registrarDocumento(ListaDocumento parameters) throws NegocioException {
//		try {
//			// INGRESAR DOCUMENTOS
//			// CAMBIO-001
//			// ******************** INICIO ******************//
//			inicializar();
//			// ******************** FIN ******************//
//			this.ingresarDocumentoServicePort = new IngresarDocumentoService_Service(new URL(
//					endPointRaiz + UtilPropiedades.cargarPropiedad(bundle, UtilConstantes.ENDPOINT_FILENET_INGRESAR)));
//			long tiempoInicial = System.currentTimeMillis();
//			this.ingresarDocumentoServicePort.setHandlerResolver(handleResolver);
//			/*
//			 * @arincon: Se coloca el flag de MTOM para optimizar el envio de imagenes
//			 * condificadas en base64 al servicio de FileNet
//			 */
//			this.ingresarDocumentoService = this.ingresarDocumentoServicePort
//					.getIngresarDocumentoWSSOAP(new MTOMFeature(true));
//			((BindingProvider) this.ingresarDocumentoService).getRequestContext().put("com.sun.xml.ws.request.timeout",
//					12000);
//			Logger.getLogger(ClienteFcaWebService.class.getName()).log(Level.SEVERE,
//					"Tiempo de respuesta del servicio ingresar documento " + (System.currentTimeMillis() - tiempoInicial)+" ms");
//			return ingresarDocumentoService.opIngresarDocumento(parameters).getReturn();
//			
//		} catch (Exception e) {
//			Logger.getLogger(ClienteFcaWebService.class.getName()).log(Level.SEVERE,
//					"Se presento  un error conectandodose a Filenet", e);
//			throw mgrExc.lanzarExcepcion(30, TipoErrorNegocio.ERROR, e.getMessage(), null);
//		}
//	}
//
//	public IngresaConsultaDocumentoResponse registrarDocumentos(
//			com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.consultaringresar.ListaDocumento parameters)
//			throws NegocioException {
//		try {
//			// INGRESAR DOCUMENTOS
//			// CAMBIO-001
//			// ******************** INICIO ******************//
//			inicializar();
//			// ******************** FIN ******************//
//			this.ingresarConsultarDocumentoServicePort = new IngresaConsultaDocumentoService_Service(
//					new URL(endPointRaiz + UtilPropiedades.cargarPropiedad(bundle,
//							UtilConstantes.ENDPOINT_FILENET_INGRESAR_CONSULTAR)));
//			this.ingresarConsultarDocumentoServicePort.setHandlerResolver(handleResolver);
//			/*
//			 * @arincon: Se coloca el flag de MTOM para optimizar el envio de imagenes
//			 * condificadas en base64 al servicio de FileNet
//			 */
//			this.ingresarConsultarDocumentoService = this.ingresarConsultarDocumentoServicePort
//					.getIngresaConsultaDocumentoWSSOAP(new MTOMFeature(true));
//			((BindingProvider) this.ingresarConsultarDocumentoService).getRequestContext()
//					.put("com.sun.xml.ws.request.timeout", 20000);
//			long inicioServicio = System.currentTimeMillis();
//			Logger.getLogger(ClienteFcaWebService.class.getName()).log(Level.SEVERE,
//					"Tiempo de inicio del servicio " + inicioServicio);
//			IngresaConsultaDocumentoResponse respuesta = ingresarConsultarDocumentoService
//					.opIngresaConsultaDocumento(parameters).getReturn();
//			Logger.getLogger(ClienteFcaWebService.class.getName()).log(Level.SEVERE,
//					"Tiempo de respuesta del servicio ingresarconsultarDocumento" + (System.currentTimeMillis() - inicioServicio));
//			return respuesta;
//		} catch (Exception e) {
//			Logger.getLogger(ClienteFcaWebService.class.getName()).log(Level.SEVERE,
//					"Se presento  un error conectandodose a Filenet", e);
//			throw mgrExc.lanzarExcepcion(29, TipoErrorNegocio.ERROR, e.getMessage(), null);
//		}
//	}
//
//	public ModificarDocumentoResponse modificarDocumento(NuevoDocumento parameters) throws NegocioException {
//		try {
//			// MODIFICAR DOCUMENTOS
//			// CAMBIO-001
//			// ******************** INICIO ******************//
//			inicializar();
//			// ******************** FIN ******************//
//			this.modificarDocumentoServicePort = new ModificarDocumentoService_Service(new URL(
//					endPointRaiz + UtilPropiedades.cargarPropiedad(bundle, UtilConstantes.ENDPOINT_FILENET_MODIFICAR)));
//			handleResolver.setService(modificarDocumentoServicePort);
//			this.modificarDocumentoServicePort.setHandlerResolver(handleResolver);
//			this.modificarDocumentoService = this.modificarDocumentoServicePort.getModificarDocumentoWSSOAP();
//			((BindingProvider) this.modificarDocumentoService).getRequestContext().put("com.sun.xml.ws.request.timeout",
//					9000);
//			return modificarDocumentoService.opModificarDocumento(parameters).getReturn();
//		} catch (Exception e) {
//			Logger.getLogger(ClienteFcaWebService.class.getName()).log(Level.SEVERE,
//					"Se presento  un error conectandodose a Filenet", e);
//			throw mgrExc.lanzarExcepcion(99, TipoErrorNegocio.ERROR, e.getMessage(), null);
//		}
//	}
//
//	public BorrarDocumentoResponse borrarDocumento(BorrarDocumento parameters) throws NegocioException {
//		try {
//			// BORRAR DOCUMENTOS
//			// CAMBIO-001
//			// ******************** INICIO ******************//
//			inicializar();
//			// ******************** FIN ******************//
//			this.borrarDocumentoServicePort = new BorrarDocumentoService_Service(new URL(
//					endPointRaiz + UtilPropiedades.cargarPropiedad(bundle, UtilConstantes.ENDPOINT_FILENET_BORRAR)));
//			this.borrarDocumentoServicePort.setHandlerResolver(handleResolver);
//			this.borrarDocumentoService = this.borrarDocumentoServicePort.getBorrarDocumentoWSSOAP();
//			Logger.getLogger(ClienteFcaWebService.class.getName()).log(Level.INFO,
//					"Generacion de la operacion de borrado para un id " + parameters.getIdDocumento());
//			Logger.getLogger(ClienteFcaWebService.class.getName()).log(Level.INFO,
//					"Generacion de la operacion de borrado con tipo borrado " + parameters.isBorrado());
//			((BindingProvider) this.borrarDocumentoService).getRequestContext().put("com.sun.xml.ws.request.timeout",
//					9000);
//			BorrarDocumentoResponse response = borrarDocumentoService.opBorrarDocumento(parameters).getReturn();
//			Logger.getLogger(ClienteFcaWebService.class.getName()).log(Level.INFO,
//					"Generacion de la operacion de borrado con resultado " + response.getResponseCode() + " "
//							+ response.isResult());
//			return response;
//		} catch (Exception e) {
//			Logger.getLogger(ClienteFcaWebService.class.getName()).log(Level.SEVERE,
//					"Se presento  un error conectandodose a Filenet para la operacion de eliminacion de archivos", e);
//			throw mgrExc.lanzarExcepcion(98, TipoErrorNegocio.ERROR, e.getMessage(), null);
//		}
//	}
//
//}
