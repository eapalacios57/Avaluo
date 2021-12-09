package com.asw.eventosbolivar.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * 
 * @author Augusto Imbett
 *
 */
public class UtilPropiedadesWeb {

	private static final Logger LOGGER = Logger.getLogger(UtilPropiedadesWeb.class.getName());

	private static final String WEB_HTTP = "HTTP";
	private static final String WEB_WWW = "WWW";
	private static final String SLASH = "/";
	private static final String WEB_PROPIEDADES = "application.properties";
	private Properties propiedades = new Properties();

	/**-------------------------------------------------------------------------------------------
	 * -------------MAPEO DE LAS CONSTANTES EN EL ARCHIVO application.properties
	 * -------------------------------------------------------------------------------------------
	 */
	//PROPIEDADES QUE REFERENCIAN A WS
	public static final String WSDL_EVENTOS = "wsdl.eventos";
	//OTRAS PROPIEDADES
	public static final String COD_PAIS = "id.codPais";
	public static final String COD_APP = "id.aplicativo";

	public UtilPropiedadesWeb() {
		// se lee el archivo .properties
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(WEB_PROPIEDADES);
			propiedades.load(inputStream);
		} catch (IOException e) {
			LOGGER.log(Level.FATAL, UtilPropiedadesWeb.class.getName(), e);
		}
	}

	/**
	 * Obtiene las propiedades del archivo
	 * @return
	 * @author Augusto Imbett
	 */
	public Properties getPropiedades() {
		return propiedades;
	}

}
