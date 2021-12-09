package com.asesoftware.util.validador;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

import com.asesoftware.util.cons.TipoComparacion;
import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.ManejadorExcepcion;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilTexto;

/**
 * Provee funcionalidad para las operaciones del validación de campo mas
 * comunes, permite generar los mensajes con el nombre del campo .
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2018 10:30:48 a.m.
 */
public final class UtilValidadorEtiqueta {

	/**
	 * Manejador de excepciones por defecto.
	 */
	private static ManejadorExcepcion mgrExc = ManejadorExcepcion.getDef();

	/**
	 * Constructor privado para evitar la creacion de una instacia de la clase
	 */
	private UtilValidadorEtiqueta() {

	}

	/**
	 * Realiza las validaciones pertinentes de obligatoriedad y longitud maxima para
	 * un valor entero tipo Long.
	 * 
	 * @param valor               a verificar.
	 * @param obligatorio         determina si se validara la obligatoriedad.
	 * @param longitud            determina la longitud maxima en caracteres para el
	 *                            valor.
	 * @param ubicacionFormulario destino del mensaje de error si se desea
	 *                            especificar la ubicacion.
	 * @param etiquetaEspecifica  en caso de que se desee especificar el nombre del
	 *                            campo que fallo.
	 * @return verdadero si se cumplen todas las validaciones, de lo contrario se
	 *         generar un mensaje de jsf con el error.
	 * @throws NegocioException
	 */
	public static boolean validar(Long valor, boolean obligatorio, int longitud, String etiquetaEspecifica)
			throws NegocioException {
		try {
			return UtilValidador.validar(valor, obligatorio, longitud);
		} catch (NegocioException e) {
			throw new NegocioException(
					(UtilTexto.estaVacio(etiquetaEspecifica) ? "" : etiquetaEspecifica) + " " + e.getMessage());
		}
	}

	public static boolean validar(Integer valor, boolean obligatorio, int longitud, String etiquetaEspecifica)
			throws NegocioException {
		try {
			return UtilValidador.validar(valor, obligatorio, longitud);
		} catch (NegocioException e) {
			throw new NegocioException(
					(UtilTexto.estaVacio(etiquetaEspecifica) ? "" : etiquetaEspecifica) + " " + e.getMessage());

		}
	}

	public static boolean validar(BigInteger valor, boolean obligatorio, int longitud, String etiquetaEspecifica)
			throws NegocioException {
		try {
			return UtilValidador.validar(valor, obligatorio, longitud);
		} catch (NegocioException e) {
			throw new NegocioException(
					(UtilTexto.estaVacio(etiquetaEspecifica) ? "" : etiquetaEspecifica) + " " + e.getMessage());
		}
	}

	/***
	 * Realiza las operacion de validaciones pertinentes de obligatoriedad y numero
	 * de lugares entero y decimales para un valor flotante.
	 * 
	 * @param valor               a verificar
	 * @param ubicacionFormulario destino del mensaje de error si se desea
	 *                            especificar la ubicacion
	 * @param obligatorio         determina si debe validar que el valor es
	 *                            obligatorio.
	 * @param enteros             longitud de enteros.
	 * @param decimales           cantidad maxima de lugares decimales
	 * @param etiquetaEspecifica  en caso que se desee personalidad el nombre del
	 *                            campo en los mensajes de error.
	 * @return verdadero en caso de que se cumplan para el valor flotante todas las
	 *         validaciones parametrizadas.
	 */
	public static boolean validar(BigDecimal valor, boolean obligatorio, int enteros, int decimales,
			String etiquetaEspecifica) throws NegocioException {
		try {
			return UtilValidador.validar(valor, obligatorio, enteros, decimales);
		} catch (NegocioException e) {
			throw new NegocioException(
					(UtilTexto.estaVacio(etiquetaEspecifica) ? "" : etiquetaEspecifica) + " " + e.getMessage());

		}
	}

	/**
	 * Realiza las validaciones pertinentes de obligatorierdad y longitud maxima
	 * para un valor de tipo texto.
	 * 
	 * @param                     valor: a verificar.
	 * @param obligatorio         determina si se validara la obligatoriedad.
	 * @param longitud            determina la longitud maxima en caracteres para el
	 *                            valor.
	 * @param ubicacionFormulario destino del mensaje de error si se desea
	 *                            especificar la ubicacion.
	 * @param etiquetaEspecifica  en caso de que se desee especificar el nombre del
	 *                            campo que fallo.
	 * @return verdadero si se cumplen todas las validaciones, de lo contrario se
	 *         generar un mensaje de jsf con el error.
	 */
	public static boolean validar(String valor, boolean obligatorio, int longitud, String etiquetaEspecifica)
			throws NegocioException {
		try {
			return UtilValidador.validar(valor, obligatorio, longitud);
		} catch (NegocioException e) {
			throw new NegocioException(
					(UtilTexto.estaVacio(etiquetaEspecifica) ? "" : etiquetaEspecifica) + " " + e.getMessage());
		}
	}

	/**
	 * Realiza las validaciones pertinentes de obligatorierdad y longitud maxima
	 * para un valor de tipo texto que tiene el formato de correo.
	 * 
	 * @param                     valor: a verificar.
	 * @param                     obligatorio: determina si se validara la
	 *                            obligatoriedad.
	 * @param                     longitud: determina la longitud maxima en
	 *                            caracteres para el valor.
	 * @param ubicacionFormulario destino del mensaje de error si se desea
	 *                            especificar la ubicacion.
	 * @param etiquetaEspecifica  en caso de que se desee especificar el nombre del
	 *                            campo que fallo.
	 * @return verdadero si se cumplen todas las validaciones, de lo contrario se
	 *         generar un mensaje de jsf con el error.
	 */
	public static boolean validarCorreo(String valor, boolean obligatorio, int longitud, String etiquetaEspecifica)
			throws NegocioException {
		try {
			return UtilValidador.validarCorreo(valor, obligatorio, longitud);
		} catch (NegocioException e) {
			throw new NegocioException(
					(UtilTexto.estaVacio(etiquetaEspecifica) ? "" : etiquetaEspecifica) + " " + e.getMessage());
		}
	}

	public static boolean validarFormato(String valor, boolean obligatorio, int longitud, String formato,
			String etiquetaEspecifica, String formatoMensaje) throws NegocioException {
		try {
			return UtilValidador.validarFormato(valor, obligatorio, longitud, formato);
		} catch (NegocioException e) {
			throw new NegocioException(
					(UtilTexto.estaVacio(etiquetaEspecifica) ? "" : etiquetaEspecifica) + " " + e.getMessage());
		}
	}

	public static boolean validar(byte[] archivo, String nombre, boolean obligatorio, String etiquetaEspecifica,
			String[] extensiones, Long tamanio) throws NegocioException {
		try {
			if (archivo != null)
				return UtilValidador.validar(archivo, nombre, obligatorio, extensiones, tamanio);
			else if (obligatorio) {
				throw mgrExc.lanzarExcepcion(UtilValidador.MSJ_OBLIGATORIO, TipoErrorNegocio.ALERTA);
			} else
				return true;
		} catch (NegocioException e) {
			throw new NegocioException(
					(UtilTexto.estaVacio(etiquetaEspecifica) ? "" : etiquetaEspecifica) + " " + e.getMessage());
		}
	}

	/**
	 * Permite validar la obligatoriedad de una fecha tipo Timestamp
	 * 
	 * @param valor               a verificar
	 * @param obligatorio         en caso de que el valor sea obligatorio.
	 * @param ubicacionFormulario destino del mensaje de error si se desea
	 *                            especificar la ubicacion.
	 * @param etiquetaEspecifica  en caso de que se desee especificar el nombre del
	 *                            campo que fallo.
	 * @return verdadero si se cumplen todas las validaciones, de lo contrario se
	 *         generar un mensaje de jsf con el error.
	 */
	public static boolean validar(Timestamp valor, boolean obligatorio, String etiquetaEspecifica)
			throws NegocioException {
		try {
			return UtilValidador.validar(valor, obligatorio);
		} catch (NegocioException e) {
			throw new NegocioException(
					(UtilTexto.estaVacio(etiquetaEspecifica) ? "" : etiquetaEspecifica) + " " + e.getMessage());
		}
	}

	/**
	 * Permite validar la obligatorierdad de una fecha tipo Date.
	 * 
	 * @param valor               a verificar
	 * @param obligatorio         en caso de que el valor sea obligatorio.
	 * @param ubicacionFormulario destino del mensaje de error si se desea
	 *                            especificar la ubicacion.
	 * @param etiquetaEspecifica  en caso de que se desee especificar el nombre del
	 *                            campo que fallo.
	 * @return verdadero si se cumplen todas las validaciones, de lo contrario se
	 *         generar un mensaje de jsf con el error.
	 */
	public static boolean validar(Date valor, boolean obligatorio, String etiquetaEspecifica) throws NegocioException {
		try {
			return UtilValidador.validar(valor, obligatorio);
		} catch (NegocioException e) {
			throw new NegocioException(
					(UtilTexto.estaVacio(etiquetaEspecifica) ? "" : etiquetaEspecifica) + " " + e.getMessage());
		}
	}

	/**
	 * Verifica si un listado de opciones por lo menos se selecciono al menos un
	 * valor.
	 * 
	 * @param valor               listado a verificar.
	 * @param obligatorio         permite verificar su obligatoriedad.
	 * @param ubicacionFormulario destino del mensaje de error si se desea
	 *                            especificar la ubicacion.
	 * @param etiquetaEspecifica  en caso de que se desee especificar el nombre del
	 *                            campo que fallo.
	 * @return verdadero si se cumplen todas las validaciones, de lo contrario se
	 *         generar un mensaje de jsf con el error.
	 */
	public static boolean validar(String[] valor, boolean obligatorio, String etiquetaEspecifica)
			throws NegocioException {
		try {
			return UtilValidador.validar(valor, obligatorio);
		} catch (NegocioException e) {
			throw new NegocioException(
					(UtilTexto.estaVacio(etiquetaEspecifica) ? "" : etiquetaEspecifica) + " " + e.getMessage());
		}
	}

	/**
	 * Permite realizar la comparacion entre dos fechas tipo Timestamp, las
	 * comparaciones permitidas son IGUAL, DIFERENTE, MAYOR_QUE, MENOR_QUE,
	 * MAYOR_IGUAL, MENOR_IGUAL
	 * 
	 * @param fecha: fecha a verificar
	 * @param tipo: de comparacion a realizar
	 * @param fechaComparacion: fecha con la que se compara la fecha a verificar.
	 * @param ubicacionFormulario: destino del mensaje de error si se desea
	 *        especificar la ubicacion.
	 * @param etiquetaEspecifica: en caso de que se desee especificar el nombre del
	 *        campo que fallo.
	 * @return verdadero si el campo supera la comparacion, de lo contrario se
	 *         generar un mensaje de jsf con el error.
	 */
	public static boolean comparar(Timestamp fecha, TipoComparacion tipo, Timestamp fechaComparacion,
			String etiquetaEspecifica) throws NegocioException {
		try {
			return UtilValidador.comparar(fecha, tipo, fechaComparacion);
		} catch (NegocioException e) {
			throw new NegocioException(
					(UtilTexto.estaVacio(etiquetaEspecifica) ? "" : etiquetaEspecifica) + " " + e.getMessage());
		}
	}

	/**
	 * Permite comparar un par de fechas tipo Date
	 * 
	 * @param fecha: fecha a verificar
	 * @param tipo: de comparacion a realizar
	 * @param fechaComparacion: fecha con la que se compara la fecha a verificar.
	 * @param ubicacionFormulario: destino del mensaje de error si se desea
	 *        especificar la ubicacion.
	 * @param etiquetaEspecifica: en caso de que se desee especificar el nombre del
	 *        campo que fallo.
	 * @return verdadero si el campo supera la comparacion, de lo contrario se
	 *         generar un mensaje de jsf con el error.
	 */
	public static boolean comparar(Date fecha, TipoComparacion tipo, Date fechaComparacion, String etiquetaEspecifica)
			throws NegocioException {
		try {
			return UtilValidador.comparar(fecha, tipo, fechaComparacion);
		} catch (NegocioException e) {
			throw new NegocioException(
					(UtilTexto.estaVacio(etiquetaEspecifica) ? "" : etiquetaEspecifica) + " " + e.getMessage());
		}
	}

	public static boolean compararDiferenciaMeses(Date fecha, long diferenciaMeses, Date fechaComparacion,
			String etiquetaEspecifica) throws NegocioException {
		try {
			return UtilValidador.compararDiferenciaMeses(fecha, fechaComparacion, diferenciaMeses);
		} catch (NegocioException e) {
			throw new NegocioException(
					(UtilTexto.estaVacio(etiquetaEspecifica) ? "" : etiquetaEspecifica) + " " + e.getMessage());
		}
	}

	/**
	 * Permite realizar la comparacion entre dos valores enteros tipo Long, las
	 * comparaciones permitidas son IGUAL, DIFERENTE, MAYOR_QUE, MENOR_QUE,
	 * MAYOR_IGUAL, MENOR_IGUAL
	 * 
	 * @param valor               a verificar
	 * @param tipo                de comparacion a realizar
	 * @param comparacion         valor con el que se compara
	 * @param ubicacionFormulario destino del mensaje de error si se desea
	 *                            especificar la ubicacion.
	 * @param etiquetaEspecifica  en caso de que se desee especificar el nombre del
	 *                            campo que fallo.
	 * @return verdadero si el campo supera la comparacion, de lo contrario se
	 *         generar un mensaje de jsf con el error.
	 */
	public static boolean comparar(Long valor, TipoComparacion tipo, Long comparacion, String etiquetaEspecifica)
			throws NegocioException {
		try {
			return UtilValidador.comparar(valor, tipo, comparacion);
		} catch (NegocioException e) {
			throw new NegocioException(
					(UtilTexto.estaVacio(etiquetaEspecifica) ? "" : etiquetaEspecifica) + " " + e.getMessage());
		}
	}

	/**
	 * Permite realizar la comparacion entre dos textos, las comparaciones
	 * permitidas son IGUAL, DEFERENTE.
	 * 
	 * @param valor               a verificar
	 * @param tipo                de comparacion a realizar
	 * @param comparacion         valor con el que se compara
	 * @param ubicacionFormulario destino del mensaje de error si se desea
	 *                            especificar la ubicacion.
	 * @param etiquetaEspecifica  en caso de que se desee especificar el nombre del
	 *                            campo que fallo.
	 * @return verdadero si el campo supera la comparacion, de lo contrario se
	 *         generar un mensaje de jsf con el error.
	 */
	public static boolean comparar(String valor, TipoComparacion tipo, String comparacion, String etiquetaEspecifica)
			throws NegocioException {
		try {
			return UtilValidador.comparar(valor, tipo, comparacion);
		} catch (NegocioException e) {
			throw new NegocioException(
					(UtilTexto.estaVacio(etiquetaEspecifica) ? "" : etiquetaEspecifica) + " " + e.getMessage());
		}
	}

}
