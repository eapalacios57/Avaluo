package com.asesoftware.util.lang;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import com.asesoftware.util.log.ConfigurarLog;

/**
 * Clase encargada de realizar la configuracion de las propiedades que son
 * utilizadas por la aplicacion
 * 
 * @author Asesoftware
 * @version 1.0
 * @created 31-ago-2017 10:30:47 a.m.
 * 
 *          utilpropiedades ser mas general y evitar el tema del logger.
 */

public class UtilPropiedades {

	private static UtilPropiedades instance;
	private static Properties properties;
	private static Logger logger = Logger.getLogger(ConfigurarLog.getNOMBRE_LOGGER());

	/**
	 * Constuctor de la clase. Carga las propiedades del Sistema
	 */
	private UtilPropiedades() {
		properties = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = getClass().getResourceAsStream(ConfigurarLog.getRUTA_PROPIEDADES());
			properties.load(inputStream);
		} catch (IOException e) {
			logger.error("No se puede leer el archivo de propiedades.", e);
		}

	}

	/**
	 * Retorna la instancia del objeto Propiedades
	 * 
	 * @return Un objeto propiedades con las propiedades del jar
	 */
	public static UtilPropiedades getInstance() {
		synchronized (UtilPropiedades.class) {
			if (instance == null) {
				instance = new UtilPropiedades();
			}
		}
		return instance;
	}

	/**
	 * Obtiene la propiedad ingresada como parametro
	 * 
	 * @param propiedad
	 *            Llave de la propiedad de la cual se desea obtener el valor
	 * @return El valor de la propiedad solicitada
	 */
	public String obtenerPropiedad(String propiedad) {
		return properties.getProperty(propiedad);
	}

	/**
	 * Obtiene la propiedad ingresada como parametro, y formatea las variables
	 * con los parametros ingresados
	 * 
	 * @param propiedad
	 *            Propiedad de la cual se desea obtener el valor
	 * @param params
	 *            Valor de los parametros para formatear la propiedad
	 * @return El valor de la propiedad solicitada
	 */
	public String obtenerPropiedad(String propiedad, Object... params) {
		String mensaje = properties.getProperty(propiedad);
		if (params != null) {
			mensaje = MessageFormat.format(mensaje, params);
		}
		return mensaje;
	}

	/**
	 * Realiza el reset de las propiedades del sistema.
	 */
	protected static void resetProperties() {
		logger.info("LLamado a proceso de actualizacion de propiedades");
		instance = null;
		getInstance();
		logger.info("Actualizacion realizada con exito!");

		Set<Object> keys = properties.keySet();
		StringBuffer propiedades = new StringBuffer();
		for (Object k : keys) {
			String key = (String) k;
			propiedades.append(key + "=" + properties.getProperty(key) + "\r\n");
		}
		logger.info("Las propiedades cargadas en el sistema son: \r\n" + propiedades.toString());
	}

}
