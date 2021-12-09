/**
 *
 */
package com.segurosbolivar.avaluos.modelo.ws.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.xml.ws.BindingProvider;

import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;

import com.asesoftware.util.archivo.UtilPropiedades;
import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.bolivar.avaluos.clientes.fca.WSMenuWeb;
import com.bolivar.avaluos.clientes.fca.WSMenuWebService;
import com.segurosbolivar.avaluos.modelo.data.ParametrizacionDao;
import com.segurosbolivar.avaluos.modelo.dto.MapaRiesgo;
import com.segurosbolivar.avaluos.modelo.entity.Parametrizacion;
import com.segurosbolivar.avaluos.modelo.service.impl.ManejadorErroresNegocio;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.service.util.UtilXml;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Modulos;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Perfiles;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Usuario;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Usuarios;
import java.io.Serializable;

/**
 * @author lvalbuena
 *
 */
@Stateless
@LocalBean
public class ClienteFcaWebService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -464173611222717623L;

	@EJB
	private ManejadorErroresNegocio mgrExc;

	private WSMenuWeb wSMenuWebService;
	
	@EJB
	private ParametrizacionDao parametrizacionDao;
	
	private static ResourceBundle bundle;
	static {
		try {
			bundle = UtilPropiedades.leerProperties(UtilConstantes.RUTA_PROPERTIES);
		} catch (NegocioException e) {
			Logger.getLogger(ClienteFcaWebService.class.getName()).log(Level.SEVERE,
					"No se ha podido leer el archivo de propiedades", e);
		}
	}


	/**
	 *
	 */
	@PostConstruct
	public void init() {
		try {
			Parametrizacion parametroWsdlFca = parametrizacionDao.getParametro(UtilConstantes.TIPO_PARAMETRO_WS_FCA, UtilConstantes.NOMBRE_PARAMETRO_WSDL_FCA);
			
		    WSMenuWebService wSMenuWebServicePort = new WSMenuWebService(
					new URL(parametroWsdlFca.getValorparametro()));
		    
		    this.wSMenuWebService = wSMenuWebServicePort.getWSMenuWebPort();

		} catch (MalformedURLException e) {
		    Logger.getLogger(ClienteFcaWebService.class.getName()).log(Level.SEVERE,e.getMessage() ,e);
		}

	}

	/**
	 *
	 * @param idAplicativo
	 * @return
	 * @throws NegocioException
	 */
	public Modulos obtenerInformacionAplicativo(int idAplicativo) throws NegocioException {
		BigDecimal numeroAplicativo = new BigDecimal(idAplicativo);
		return (Modulos) UtilXml.conversorXmlaObjeto(this.wSMenuWebService.aplicacion(numeroAplicativo), Modulos.class);

	}

	/**
	 *
	 * @param numIdentificacion
	 * @param aplicativo
	 * @return
	 * @throws NegocioException
	 * @throws RemoteException
	 */
	public Usuario obtenerUsuarioPorAplicativo(String numIdentificacion, int aplicativo) throws NegocioException {
		try{
		BigDecimal numeroAplicativo = new BigDecimal(aplicativo);
		String respuesta = this.wSMenuWebService.informacionUsuarioAplicacion(numIdentificacion, numeroAplicativo);
		return (Usuario) UtilXml.conversorXmlaObjeto(respuesta, Usuario.class);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *
	 * @param numeroIdentificacion
	 * @param idAplicativo
	 * @param pais
	 * @return
	 * @throws NegocioException
	 */
	public Modulos obtenerMenuUsuarioAplicacion(String numeroIdentificacion, int idAplicativo, String pais)
			throws NegocioException {
		BigDecimal numeroAplicativo = new BigDecimal(idAplicativo);
		BigDecimal identificacion = new BigDecimal(numeroIdentificacion);
		String respuesta = this.wSMenuWebService.menuUsuarioAplicacion(identificacion, numeroAplicativo, pais);
		String cdataLimpia = limpiaRespuesta(respuesta);
		return (Modulos) UtilXml.conversorXmlaObjeto(cdataLimpia, Modulos.class);
	}

	/**
	 *
	 * @param perfil
	 * @param idAplicativo
	 * @return
	 * @throws NegocioException
	 */
	public Modulos obtenerMenuPerfilAplicacion(String perfil, int idAplicativo) throws NegocioException {
		try {
			BigDecimal numeroAplicativo = new BigDecimal(idAplicativo);
			String respuesta = this.wSMenuWebService.menuPerfilAplicacion(perfil, numeroAplicativo);
			return (Modulos) UtilXml.conversorXmlaObjeto(respuesta, Modulos.class);
		} catch (Exception e) {
			throw mgrExc.lanzarExcepcion(2, TipoErrorNegocio.FATAL);
		}
	}

	/**
	 *
	 * @param idAplicativo
	 * @return
	 * @throws NegocioException
	 */
	public Usuarios obtenerUsuariosPorAplicativo(int idAplicativo) throws NegocioException {
		try {
			BigDecimal numeroAplicativo = new BigDecimal(idAplicativo);
			((BindingProvider) this.wSMenuWebService).getRequestContext().put("com.sun.xml.ws.request.timeout", 360000);
			String respuesta = this.wSMenuWebService.usuariosAplicacion(numeroAplicativo);
			return (Usuarios) UtilXml.conversorXmlaObjeto(respuesta, Usuarios.class);
		} catch (Exception e) {
			throw mgrExc.lanzarExcepcion(3, TipoErrorNegocio.FATAL);
		}
	}

	/**
	 *
	 * @param idAplicativo
	 * @return
	 * @throws NegocioException
	 */
	public Perfiles obtenerPerfilesPorAplicacion(int idAplicativo) throws NegocioException {
		try {
			BigDecimal numeroAplicativo = new BigDecimal(idAplicativo);
			String respuesta = this.wSMenuWebService.perfilesAplicacion(numeroAplicativo);
			return (Perfiles) UtilXml.conversorXmlaObjeto(respuesta, Perfiles.class);
		} catch (Exception e) {
			throw mgrExc.lanzarExcepcion(5, TipoErrorNegocio.FATAL);
		}
	}
	
	private String limpiaRespuesta(String respuesta) {
	    return respuesta.replace("&", "&amp;");
	}

	/**
	 *
	 * @param coordenadaX
	 * @param coordenadaY
	 * @return
	 * @throws NegocioException
	 */
	public MapaRiesgo obtenerParametrosAsegurabilidad(String coordenadaX, String coordenadaY) throws NegocioException {
		HttpURLConnection conn = null;
		int estado;
		StringBuilder direccion = new StringBuilder(UtilPropiedades.cargarPropiedad(bundle, UtilConstantes.ENDPOINT_ASEGURABILIDAD));
		direccion.append(UtilConstantes.CONSULTA_LATITUD);
		direccion.append(coordenadaX);
		direccion.append(UtilConstantes.CONSULTA_LONGITUD);
		direccion.append(coordenadaY);
		try
		{	
			URL url = new URL(direccion.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(UtilConstantes.METHOD_POST);
			conn.setRequestProperty(UtilConstantes.CONTENT_TYPE, UtilConstantes.APPLICATION_JSON);
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw mgrExc.lanzarExcepcion(9, TipoErrorNegocio.ALERTA);
			}
			BufferedReader buffer = new BufferedReader(
					new InputStreamReader(conn.getInputStream(), Charset.defaultCharset()));
			StringBuilder sb = new StringBuilder();
			while ((estado = buffer.read()) != -1) {
				sb.append((char) estado);
			}
			ObjectMapper mapeador = new ObjectMapper();
			mapeador.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapeador.readValue(sb.toString(), MapaRiesgo.class);
		} catch (Exception e) {
			throw mgrExc.lanzarExcepcion(8, TipoErrorNegocio.ALERTA);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

}
