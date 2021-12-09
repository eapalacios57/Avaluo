package com.asesoftware.util.log;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import com.asesoftware.util.cons.Constantes;
import com.asesoftware.util.lang.UtilPropiedades;

/**
 * Clase encargada de realizar la configuracion de las propiedades y el log del
 * sistema.
 * 
 * @author Asesoftware
 * @version 1.0
 * @created 31-ago-2017 10:30:40 a.m.
 */
public class ConfigurarLog {

	private static String RUTA_PROPIEDADES;
	private static String NOMBRE_LOGGER;

	/**
	 * Metodo encargado de realizar la configuracion de las propiedades y el log
	 * de la aplicacion
	 *
	 * @param rutaPropiedades
	 */
	public static void configurar(String rutaPropiedades, String nombreLogger) {

		RUTA_PROPIEDADES = rutaPropiedades;
		NOMBRE_LOGGER = nombreLogger;
		UtilPropiedades.getInstance();
		configurarLog();
	}

	/**
	 * Metodo encargado de realizar la configuracion del archivo de log4j,
	 * teniendo en cuenta el tipo de configuracion en el archivo de propiedades
	 * (por XML o por PROPERTIES)
	 */
	private static void configurarLog() {

		UtilPropiedades prop = UtilPropiedades.getInstance();
		String loggerType = prop.obtenerPropiedad(Constantes.LOGGER_TYPE);

		if (Constantes.LOGGER_TYPE_XML.equalsIgnoreCase(loggerType))
			DOMConfigurator.configure(prop.obtenerPropiedad(Constantes.LOGGER_FILE));
		else if (Constantes.LOGGER_TYPE_PROPERTIES.equalsIgnoreCase(loggerType))
			PropertyConfigurator.configure(prop.obtenerPropiedad(Constantes.LOGGER_FILE));
		else
			System.err.println("Opcion invalida para la configuracion de log4j");

	}

	/**
	 * @return la RUTA_PROPIEDADES
	 */
	public static String getRUTA_PROPIEDADES() {
		return RUTA_PROPIEDADES;
	}

	/**
	 * @return la NOMBRE_LOGGER
	 */
	public static String getNOMBRE_LOGGER() {
		return NOMBRE_LOGGER;
	}

	/**
	 * Metodo encargado de retornar el tiempo inicial de la transaccion junto
	 * con el id de transaccion. Configura el log para registrar el id de
	 * transaccion
	 *
	 * @return Obejto con los parametros de tiempo inicial de la transaccion y
	 *         id de transaccion
	 */
	public static ParametrosIniciales iniciarTransaccion() {
		ParametrosIniciales param = new ParametrosIniciales();
		MDC.put("UUID", param.getUuid());
		return param;
	}

	/**
	 * Metodo encargado de generar en el Log el tiempo de procesamiento de la
	 * transaccion. Adicionalmente limpia la variable de UUID mostrada en el
	 * log.
	 *
	 * @param param
	 *            Parametros calculados al inicio de la transaccion
	 * @param logger
	 *            Logger de la aplicacion para imprimir el tiempo total de la
	 *            transaccion
	 */
	public static void cerrarTransaccion(ParametrosIniciales param, Logger logger) {
		logger.info("Tiempo de procesamiento (ms) " + (System.currentTimeMillis() - param.getTime()));
		MDC.remove("UUID");
	}

	/**
	 * Metodo encargado de resetear la configuracion del archivo de log4j,
	 * teniendo en cuenta el tipo de configuracion en el archivo de propiedades
	 * (por XML o por PROPERTIES)
	 */
	public static void resetPropiedades(String rutaPropiedades, String nombreLogger) {

		RUTA_PROPIEDADES = rutaPropiedades;
		NOMBRE_LOGGER = nombreLogger;
		configurarLog();
	}

}
