package com.asesoftware.util.archivo;

import java.io.FileInputStream;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.ResourceBundle;

import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilTexto;

/**
 * esta clase permite ofrecer utilidad para el procesamiento de archivos de
 * propiedades.
 *
 * @author stilaguy
 *
 */
public final class UtilPropiedades {

	private UtilPropiedades(){

	}

	public synchronized static ResourceBundle leerProperties(String ruta) throws NegocioException {
			return ResourceBundle.getBundle((ruta));
	}

	public synchronized static Properties leerProperties(FileInputStream file) throws NegocioException {
		Properties archivo = new Properties();
		try {
			archivo.load(file);
		} catch (Exception e) {
			throw new NegocioException("No se puede leer el archivo de propiedades.");
		}
		return archivo;
	}

	public synchronized static String cargarPropiedad(Properties archivo, String id, Object[] parametros) {
		if (archivo == null || UtilTexto.estaVacio(id)) {
			return null;
		}
		return MessageFormat.format(archivo.getProperty(id), parametros);
	}

	public synchronized static String cargarPropiedad(ResourceBundle archivo, String id, Object[] parametros) {
		if (archivo == null || UtilTexto.estaVacio(id)) {
			return null;
		}
		return MessageFormat.format(archivo.getString(id), parametros);
	}

	public synchronized static String cargarPropiedad(ResourceBundle archivo, String id) {
		if (archivo == null || UtilTexto.estaVacio(id)) {
			return null;
		}
		return archivo.getString(id);
	}

}
