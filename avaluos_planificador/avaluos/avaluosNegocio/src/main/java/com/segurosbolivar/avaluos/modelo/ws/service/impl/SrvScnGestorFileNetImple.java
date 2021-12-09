package com.segurosbolivar.avaluos.modelo.ws.service.impl;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.davivienda.filenet.srvscngestorfilenet.v1.MsjRespOpCargarDocumento;
import com.davivienda.filenet.srvscngestorfilenet.v1.MsjSolOpCargarDocumento;
import com.davivienda.filenet.srvscngestorfilenet.v1.PortSrvScnGestorFileNetSOAP;
import com.davivienda.filenet.srvscngestorfilenet.v1.SrvScnGestorFileNet;
import com.segurosbolivar.avaluos.modelo.data.ParametrizacionDao;
import com.segurosbolivar.avaluos.modelo.entity.Parametrizacion;
import com.segurosbolivar.avaluos.modelo.service.impl.ManejadorErroresNegocio;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.utilities.CertificadoUtil;
import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.utilities.HeaderHandleWsseSecurity;
import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.utilities.SOAPLoggingHandler;

/**
 * Servicio EJB encargado de invocar el cliente del servicio web de
 * Davivienda
 * 
 * @author mhernandez
 *
 */
@Stateless
public class SrvScnGestorFileNetImple implements Serializable {

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

	// opCargarDocumento  
	private SrvScnGestorFileNet srvScnGestorFileNet;
	private PortSrvScnGestorFileNetSOAP portSrvScnGestorFileNetSOAP;	
	private HeaderHandleWsseSecurity handleHeaderWsseSecurity;

	private String rutaAlmacenLlavero;
	private String passwordAlmacenLlavero;
	private String passwordLlavero;
	
	public void init() throws Exception {
		try {
			this.srvScnGestorFileNet = new SrvScnGestorFileNet(new URL(UtilConstantes.ENDPOINT_FICTICIO_ADJUNTOS));
			
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
			
			srvScnGestorFileNet.setHandlerResolver( new HandlerResolver() {
				@Override
				public List<Handler> getHandlerChain(PortInfo portInfo) {
					List<Handler> handlerList = new ArrayList<Handler>();
				    handlerList.add(handleHeaderWsseSecurity);
				    handlerList.add(new SOAPLoggingHandler());			    
				    
				    return handlerList;
				}
			});
			
			
			this.portSrvScnGestorFileNetSOAP = this.srvScnGestorFileNet.getPortSrvScnGestorFileNetSOAP();
		} catch (Exception e) {
			Logger.getLogger(SrvScnGestorFileNetImple.class.getName()).log(Level.SEVERE,"Error por : "+e.getMessage());
			throw e;
		}

	}

	public MsjRespOpCargarDocumento enviarDocumento(MsjSolOpCargarDocumento msjSolOpCargarDocumento) throws NegocioException {
		try {	
			long tiempoInicial = System.currentTimeMillis();
			init();
			
			System.out.println("###url del ws filenet a utilizar:"+portSrvScnGestorFileNetSOAP.toString());

			CertificadoUtil.adjuntarCertificado(portSrvScnGestorFileNetSOAP, rutaAlmacenLlavero, passwordAlmacenLlavero, passwordLlavero);
			
			MsjRespOpCargarDocumento respuesta = this.portSrvScnGestorFileNetSOAP.opCargarDocumento(msjSolOpCargarDocumento);
//			this.srvScnGestorFileNet.getPortSrvScnGestorFileNetSOAP().opCargarDocumento(msjSolOpCargarDocumento);			
			Logger.getLogger(ClienteNotificacionesAseguradorasService.class.getName()).log(Level.SEVERE,
					"Tiempo de respuesta del servicio de envío de adjuntos al datapower" + (System.currentTimeMillis() - tiempoInicial)+" ms");
			return respuesta;


		} catch (Exception e) {
			Logger.getLogger(SrvScnGestorFileNetImple.class.getName()).log(Level.SEVERE,
					"Se presento  un error enviando los adjuntos al datapower", e);
			throw mgrExc.lanzarExcepcion(30, TipoErrorNegocio.ERROR, e.getMessage(), null);
		}
	}

}
