package com.asesoftware.util.lang;

/**
 * Provee funcionalidad para la gestion de numeros, mas exactamente enteros de
 * tipo Long.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:47 a.m.
 */
public final class UtilArreglo {


	/**
	 * Permite determinar si un texto es entero.
	 * 
	 * @param valor
	 */
	public synchronized static Object obtener(Object[] arreglo,  int posicion) {
		try {
			if(arreglo==null || arreglo.length<posicion)
				return null;
			return arreglo[posicion]; 
		} catch (Exception e) {
			return null;
		}
	}

}