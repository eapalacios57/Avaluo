package com.segurosbolivar.avaluos.modelo.ws.service.impl;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.davivienda.xml.notificacionaseguradoras.NotificacionAseguradorasHTTPService;
import com.davivienda.xml.notificacionaseguradoras.NotificacionAseguradorasPortType;
import com.davivienda.xml.notificacionaseguradoras.NotificacionAvaluosRespType;
import com.davivienda.xml.notificacionaseguradoras.NotificacionAvaluosRqType;
import com.segurosbolivar.avaluos.modelo.data.ParametrizacionDao;
import com.segurosbolivar.avaluos.modelo.entity.Parametrizacion;
import com.segurosbolivar.avaluos.modelo.service.impl.ManejadorErroresNegocio;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.utilities.CertificadoUtil;
import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.utilities.HeaderHandleWsseSecurity;
import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.utilities.SOAPLoggingHandler;

@Stateless
public class ClienteNotificacionesAseguradorasService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private ManejadorErroresNegocio mgrExc;
	
	@EJB
	private ParametrizacionDao parametrizacionDao;

	/*private static ResourceBundle bundle;
	static {
		try {
			bundle = UtilPropiedades.leerProperties(UtilConstantes.RUTA_PROPERTIES);
		} catch (NegocioException e) {
			Logger.getLogger(ClienteNotificacionesAseguradorasService.class.getName()).log(Level.SEVERE,
					"No se ha podido leer el archivo de propiedades", e);
		}
	}*/


	// notificacionAvaluos
	private NotificacionAseguradorasHTTPService notificacionAseguradoraService;
	private NotificacionAseguradorasPortType notificacionAseguradoraPort;
	private HeaderHandleWsseSecurity handleHeaderWsseSecurity;
	
	private String rutaAlmacenLlavero;
	private String passwordAlmacenLlavero;
	private String passwordLlavero;

	public void init() throws Exception {
		try {
			this.notificacionAseguradoraService = new NotificacionAseguradorasHTTPService(new URL(UtilConstantes.ENDPOINT_FICTICIO_NOTIFICACION), new QName("http://www.davivienda.com/xml/NotificacionAseguradoras", "NotificacionAseguradoras_HTTP_Service"));
			
			String aliasLlavero = null;
			
			List<Parametrizacion> ParametrosWsDavivienda = parametrizacionDao.getTiposParametro(UtilConstantes.TIPO_PARAMETRO_WS_DAVIVIENDA);
			for (Parametrizacion parametro : ParametrosWsDavivienda) {
				if(parametro.getNombreparametro().equals(UtilConstantes.NOMBRE_PARAMETRO_PATH_KEYSTORE)) {
					rutaAlmacenLlavero = parametro.getValorparametro();
				}else if(parametro.getNombreparametro().equals(UtilConstantes.NOMBRE_PARAMETRO_PASSWORD_KEYSTORE)) {
					passwordAlmacenLlavero = parametro.getValorparametro();
				}else if(parametro.getNombreparametro().equals(UtilConstantes.NOMBRE_PARAMETRO_PASSWORD_LLAVERO)) {
					passwordLlavero = parametro.getValorparametro();
				}else if(parametro.getNombreparametro().equals(UtilConstantes.NOMBRE_PARAMETRO_ALIAS_LLAVERO)) {
					aliasLlavero = parametro.getValorparametro();
				}
			}
			
			if(rutaAlmacenLlavero == null || aliasLlavero == null) {
				throw new IllegalArgumentException("Parametros "+UtilConstantes.TIPO_PARAMETRO_WS_DAVIVIENDA+" null");
			}
			
			handleHeaderWsseSecurity = new HeaderHandleWsseSecurity(rutaAlmacenLlavero, passwordAlmacenLlavero, aliasLlavero, passwordLlavero);
			
			notificacionAseguradoraService.setHandlerResolver( new HandlerResolver() {
				@Override
				public List<Handler> getHandlerChain(PortInfo portInfo) {
					List<Handler> handlerList = new ArrayList<Handler>();
				    handlerList.add(handleHeaderWsseSecurity);
				    handlerList.add(new SOAPLoggingHandler());			    
				    
				    return handlerList;
				}
			});
			
			this.notificacionAseguradoraPort = this.notificacionAseguradoraService.getNotificacionAseguradorasPort();
		} catch (Exception e) {
			Logger.getLogger(ClienteNotificacionesAseguradorasService.class.getName()).log(Level.SEVERE,"Error por : "+e.getMessage());
			throw e;

		}

	}



	public NotificacionAvaluosRespType enviarNotificacion(NotificacionAvaluosRqType notificacionAvaluosRq) throws NegocioException {
		try {			
			long tiempoInicial = System.currentTimeMillis();
			init();

			System.out.println("###url del ws notificacion a utilizar:"+notificacionAseguradoraPort.toString());

			CertificadoUtil.adjuntarCertificado(notificacionAseguradoraPort, rutaAlmacenLlavero, passwordAlmacenLlavero, passwordLlavero);
			
			//Rrequets que se vaya en lel header con autenticacion
			NotificacionAvaluosRespType	respuesta =  notificacionAseguradoraPort.notificacionAvaluos(notificacionAvaluosRq);			
			Logger.getLogger(ClienteNotificacionesAseguradorasService.class.getName()).log(Level.SEVERE,
					"Tiempo de respuesta del servicio de envío de notificación al datapower" + (System.currentTimeMillis() - tiempoInicial)+" ms");
			return respuesta;

		} catch (Exception e) {
			Logger.getLogger(ClienteNotificacionesAseguradorasService.class.getName()).log(Level.SEVERE,
					"Se presento  un error enviando la notificación al datapower", e);
			throw mgrExc.lanzarExcepcion(30, TipoErrorNegocio.ERROR, e.getMessage(), null);
		}
	}

}