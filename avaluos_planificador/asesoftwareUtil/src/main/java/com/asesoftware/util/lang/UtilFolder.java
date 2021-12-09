package com.asesoftware.util.lang;

import java.util.Iterator;
import java.util.List;

/**
 * Crea ruta de acceso a de los soportes y fotos de avalúos y planificaciones
 * tipo Long.
 * 
 * @author lmarino
 * @version 1.0
 * @created 31-may-2020 22:30:47 p.m.
 */
public class UtilFolder {
	/**
	 * Permite crear ruta de almacenamiento de archivos.
	 * 
	 * @param Ruta de almacenamiento de archivos
	 */
	public static String obtenerRutaDirectorio (List<String> param) {
		String ruta = null;
		if(!param.isEmpty()) {
			Iterator<String> i = param.listIterator();
			ruta = i.next().toString();
			while(i.hasNext()) {
				ruta = ruta + "/" + i.next().toString();
			}
		}
		return ruta;
	}
}
