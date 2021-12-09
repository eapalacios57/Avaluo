package com.asesoftware.util.lang;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * Provee funcionalidad para la gestion de numeros, mas exactamente enteros de
 * tipo Long.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:47 a.m.
 */
public final class UtilNumero {

	private static DecimalFormat formatoMoneda = new DecimalFormat("$###,###,###.##");

	/**
	 * Permite determinar si un texto es entero.
	 * 
	 * @param valor
	 */
	public synchronized static boolean esEntero(String valor) {
		try {
			Long.parseLong(valor);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Permite convertir un texto a entero.
	 * 
	 * @param valor
	 * @return
	 */
	public static synchronized Long pasarEntero(String valor) {
		try {
			return Long.parseLong(valor);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Permite convertir un texto a BigInteger.
	 * 
	 * @param valor
	 * @return
	 */
	public static synchronized BigInteger pasarBigInteger(String valor) {
		try {
			return new BigInteger(valor);
		} catch (Exception e) {
			return null;
		}
	}

	public static synchronized BigDecimal pasarBigDecimal(String valor) {
		try {
			return new BigDecimal(valor);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Permite convertir un texto a entero.
	 * 
	 * @param valor
	 * @return
	 */
	public static synchronized Double pasarDouble(String valor) {
		try {
			return Double.parseDouble(valor);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Permite transformar un long a texto.
	 * 
	 * @param valor
	 */
	public synchronized static String pasarTexto(Long valor) {
		try {
			return Long.toString(valor);
		} catch (Exception e) {
			return "";
		}
	}

	public synchronized static String pasarTexto(Integer valor) {
		try {
			return Integer.toString(valor);
		} catch (Exception e) {
			return "";
		}
	}

	/***
	 * Permite obtener la parte entera de un bigDecimal.
	 * 
	 * @param valor:
	 *            al que obtendremos la parte entera
	 * @return la parte entera del valor en String, si esta no existe el valor
	 *         estara vacio.
	 */
	public static String obtenerParteEntera(BigDecimal valor) {
		if (valor == null)
			return "";
		String texto = valor.toPlainString();
		if (UtilTexto.estaVacio(texto))
			return "";
		if (texto.indexOf('.') >= 0)
			return texto.substring(0, texto.indexOf('.'));
		return texto;
	}

	/***
	 * Permite obtener la parte decimal de un bigDecimal.
	 * 
	 * @param valor:
	 *            al que obtendremos la parte decimal
	 * @return la parte decimal del valor en String, si esta no existe el valor
	 *         estara vacio.
	 */
	public static String obtenerParteDecimal(BigDecimal valor) {
		if (valor == null)
			return "";
		String texto = valor.toPlainString();
		if (UtilTexto.estaVacio(texto))
			return "";
		if (texto.indexOf('.') >= 0)
			return texto.substring(texto.indexOf('.') + 1, texto.length());
		return "";
	}

	public static String formatoMoneda(BigDecimal valor) {
		if (valor == null)
			valor = BigDecimal.ZERO;
		return formatoMoneda.format(valor.doubleValue());
	}

}