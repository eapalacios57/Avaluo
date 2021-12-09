package com.asesoftware.util.lang;

import java.text.Normalizer;
import java.util.List;

/**
 * Esta clase permite proveer validaciones estandar para el procesamiento de
 * cadenas de caracteres.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:47 a.m.
 */
public final class UtilTexto {

	/**
	 * Permite determinar sin un texto se encuentra vacio.
	 * 
	 * @param texto:
	 *            a validar.
	 */
	public synchronized static boolean estaVacio(String texto) {
		if (texto == null)
			return true;
		if (texto.trim().length() <= 0)
			return true;
		return false;
	}

	public synchronized static boolean poseeCaracteresEspeciales(String texto) {
		if (texto == null)
			return false;
		return texto.length() != soloAlfaNumerico(texto).length();
	}

	public synchronized static String soloAlfaNumericoEspacio(String valor) {
		if (estaVacio(valor))
			return valor;
		return valor.replaceAll("[^A-Za-z0-9 ]", "");
	}

	public synchronized static String soloAlfaNumerico(String valor) {
		if (estaVacio(valor))
			return valor;
		return valor.replaceAll("[^A-Za-z0-9]", "");
	}

	/**
	 * Permite remover la puntuacion y los signos en un texto.
	 * 
	 * @param valor
	 */
	public synchronized static String removerPuntuacion(String valor) {
		if (estaVacio(valor))
			return valor;
		return valor.replaceAll("\\p{Punct}", "");
	}
	
	/**
	 * Permite remover los caracteres especiales de un texto
	 * @param valor
	 * @return
	 */
	public synchronized static String removerCaracteresEspeciales(String valor) {
		if (estaVacio(valor))
			return valor;
		valor = Normalizer.normalize(valor, Normalizer.Form.NFD);
		valor = valor.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	    return valor;
	}

	public static String unificar(String[] extensiones, String caracter) {
		if (extensiones == null || extensiones.length <= 0) {
			return null;
		}
		StringBuilder retorno = new StringBuilder();
		for (String string : extensiones) {
			retorno.append(string);
			retorno.append(caracter + " ");
		}
		retorno.replace(retorno.lastIndexOf(caracter), retorno.length(), "");
		retorno.append(" ");
		return retorno.toString();
	}

	public static String unificar(List<String> extensiones, String caracter) {
		if (extensiones == null || extensiones.isEmpty()) {
			return null;
		}
		StringBuilder retorno = new StringBuilder();
		for (String string : extensiones) {
			retorno.append(string);
			retorno.append(caracter + " ");
		}
		retorno.replace(retorno.lastIndexOf(caracter), retorno.length(), "");
		retorno.append(" ");
		return retorno.toString();
	}

	public static String removerSufijo(String texto, String sufijoDominios) {
		if (estaVacio(texto)) 
			return null;
		if(!texto.endsWith(sufijoDominios))
			return texto;
		return texto.substring(0,texto.lastIndexOf(sufijoDominios));
	}
	
	
}