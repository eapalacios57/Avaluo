package com.asesoftware.util.seguridad;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.asesoftware.util.cons.Constantes;
import com.asesoftware.util.lang.UtilPropiedades;
import com.asesoftware.util.log.ConfigurarLog;

/**
 * Clase encargada de generar los procesos de encriptacion de archivos.
 * 
 * @author Frincon
 * @version 1.0
 * @created 31-ago-2017 10:30:42 a.m.
 */
public class Encriptador {

	static {
		ConfigurarLog.configurar(Constantes.RUTA_ARCHIVO, Constantes.LOGGER_PRINCIPAL);
	}
	private static UtilPropiedades propiedades = UtilPropiedades.getInstance();
	private static Logger logger = Logger.getLogger(Constantes.LOGGER_PRINCIPAL);

	public Encriptador() {
	}

	/**
	 * Metodo Encargado de encriptar un archivo a Pgp
	 * 
	 * @param rutaArchivo
	 * @return int
	 */
	public int encriptarToPgp(String rutaArchivo) {
		int code = -1;
		try {
			logger.info("Directorio al encriptar " + System.getProperty(Constantes.USER_DIRECTORY));
			String rutaSH = propiedades.obtenerPropiedad(Constantes.RUTA_EJECUCION_SH)
					+ System.getProperty(Constantes.FILE_SEPARATOR);
			if (rutaArchivo.contains("\\")) {
				rutaArchivo = rutaArchivo.replace("\\", File.separator);
				rutaSH = rutaSH.replace("\\", File.separator);
			}
			// se arma el comando a ejecutar
			String[] command = { "bash", "--login", "-i", "-li",
					rutaSH + propiedades.obtenerPropiedad(Constantes.FILE_NAME_SH), rutaArchivo };
			logger.info("Usuario PGP " + System.getProperty(Constantes.USER_NAME));

			// Ejecuta el proceso
			Process process = Runtime.getRuntime().exec(command);

			// Permite la ejecucion del proceso por 15 segundos, si en este
			// tiempo no se
			// ejecuta genera un timeout.

			long now = System.currentTimeMillis();
			long timeoutInMillis = 1000L * 15;
			long finish = now + timeoutInMillis;

			while (isAlive(process)) {
				if (System.currentTimeMillis() > finish) {
					process.destroy();
					logger.info("Supero el tiempo de espera de 15 segundos para encriptar");
				}
			}

			// recibe el codigo de salida del proceso
			code = process.waitFor();

			// recibe el string con la descripcion del error
			String linea;
			BufferedReader input = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while ((linea = input.readLine()) != null) {
				logger.info("Línea de comando encripta: " + linea);
			}
			input.close();
			input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while ((linea = input.readLine()) != null) {
				logger.info("Leyendo línea " + linea);
			}
			input.close();
			logger.info("Return code = " + code);
		} catch (Exception e) {
			logger.error("Se ha producido un error en el proceso ...", e);
		} 
		return 0;
	}

	private boolean isAlive(Process process) {
		try {
			process.exitValue();
			return false;
		} catch (IllegalThreadStateException e) {
			return true;
		}
	}

}
