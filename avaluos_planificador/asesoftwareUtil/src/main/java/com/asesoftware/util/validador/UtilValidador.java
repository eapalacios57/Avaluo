package com.asesoftware.util.validador;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import com.asesoftware.util.cons.Constantes;
import com.asesoftware.util.cons.TipoComparacion;
import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.ManejadorExcepcion;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilFecha;
import com.asesoftware.util.lang.UtilNumero;
import com.asesoftware.util.lang.UtilTexto;

/**
 * Esta clase permite realizar validaciones comunes de tipos de datos basicos
 * del sistema, como enteros y fechas. tambien permite realizar las operacion en
 * que dos valores se deben comparar
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:48 a.m.
 */
public final class UtilValidador {

	// validaciones generales
	public static final int MSJ_OBLIGATORIO = 1;
	private static final int MSJ_LONGITUD_MAX = 2;
	// validaciones para fechas
	private static final int MSJ_FECHA_IGUAL = 3;
	private static final int MSJ_FECHA_DIFERENTE = 4;
	private static final int MSJ_FECHA_MAYOR_IGUAL = 5;
	private static final int MSJ_FECHA_MENOR_IGUAL = 6;
	private static final int MSJ_FECHA_MAYOR = 7;
	private static final int MSJ_FECHA_MENOR = 8;
	private static final int MSJ_DIFERENCIA_FECHAS = 26;
	// validaciones para enteros
	private static final int MSJ_ENTERO_IGUAL = 9;
	private static final int MSJ_ENTERO_DIFERENTE = 10;
	private static final int MSJ_ENTERO_MAYOR_IGUAL = 11;
	private static final int MSJ_ENTERO_MENOR_IGUAL = 12;
	private static final int MSJ_ENTERO_MAYOR = 13;
	private static final int MSJ_ENTERO_MENOR = 14;
	// validaciones para textos
	private static final int MSJ_TEXTO_IGUAL = 15;
	private static final int MSJ_TEXTO_DIFERENTE = 16;
	private static final int MSJ_TEXTO_CORREO = 17;
	private static final int MSJ_OPERACION_NO_ESPECIFICADA = 18;
	private static final int MSJ_TAMANIO_ARCHIVO = 19;
	private static final int MSJ_EXTENSION_ARCHIVO = 20;
	private static final int MSJ_ARCHIVO_VACIO = 21;
	private static final int MSJ_ENTERO_MAX = 22;
	private static final int MSJ_DECIMALES_MAX = 23;
	private static final int MSJ_NO_FORMATO = 24;

	/***
	 * formato a aplicar a la validacion de correos.
	 */
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * Manejador de excepciones por defecto.
	 */
	private static ManejadorExcepcion mgrExc = ManejadorExcepcion.getDef();

	/***
	 * Creamos un constructor privado para evitar que la clase sea instanciada,
	 * al igual que se declara final para evitar que se pueda heredar.
	 */
	private UtilValidador() {

	}

	/**
	 * Realiza las validaciones pertinentes de obligatorierdad y longitud maxima
	 * para un valor entero tipo Long.
	 * 
	 * @param valor
	 *            a verificar.
	 * @param obligatorio
	 *            determina si se validara la obligatoriedad.
	 * @param longitud
	 *            determina la longitud maxima en caracteres para el valor.
	 * @return verdadero en caso de que las validaciones funcionen
	 *         satisfactoreamente.
	 * @throws NegocioException:
	 *             en caso de que alguna de las validaciones falle. con el
	 *             codigo especificado del error producido.
	 */
	public static boolean validar(Long valor, boolean obligatorio, int longitud) throws NegocioException {
		if (obligatorio && valor == null) {
			throw mgrExc.lanzarExcepcion(MSJ_OBLIGATORIO, TipoErrorNegocio.ALERTA);
		}
		if (valor != null && valor.toString().length() > longitud) {
			throw mgrExc.lanzarExcepcion(MSJ_LONGITUD_MAX, TipoErrorNegocio.ALERTA);
		}
		return true;
	}
	
	public static boolean validar(Integer valor, boolean obligatorio, int longitud) throws NegocioException {
		if (obligatorio && valor == null) {
			throw mgrExc.lanzarExcepcion(MSJ_OBLIGATORIO, TipoErrorNegocio.ALERTA);
		}
		if (valor != null && valor.toString().length() > longitud) {
			throw mgrExc.lanzarExcepcion(MSJ_LONGITUD_MAX, TipoErrorNegocio.ALERTA);
		}
		return true;
	}

	public static boolean validar(BigInteger valor, boolean obligatorio, int longitud) throws NegocioException {
		if (obligatorio && valor == null) {
			throw mgrExc.lanzarExcepcion(MSJ_OBLIGATORIO, TipoErrorNegocio.ALERTA);
		}
		if (valor != null && valor.toString().length() > longitud) {
			throw mgrExc.lanzarExcepcion(MSJ_LONGITUD_MAX, TipoErrorNegocio.ALERTA);
		}
		return true;
	}

	/**
	 * Permite realizarlas validaciones de obligatorierdad y limite de
	 * pocisiones enteras y decimales para un valor flotante tipo BigDecimal.
	 * 
	 * @param valor
	 *            averificar
	 * @param obligatorio
	 *            determina si se validara si el valor es obligatorio
	 * @param enteros
	 *            numero de pocisiones enteras que puede tener el valor
	 * @param decimales
	 *            numero de pocisiones decimales para para el valor flotante.
	 * @return verdadero en caso de que las validaciones parametrizadas para el
	 *         valor flotante se cumplan.
	 * @throws NegocioException
	 *             en caso de que las validaciones fallen.
	 */
	public static boolean validar(BigDecimal valor, boolean obligatorio, int enteros, int decimales) throws NegocioException {
		if (obligatorio && valor == null) {
			throw mgrExc.lanzarExcepcion(MSJ_OBLIGATORIO, TipoErrorNegocio.ALERTA);
		}
		String parteEntera = UtilNumero.obtenerParteEntera(valor);
		String parteDecimal = UtilNumero.obtenerParteDecimal(valor);
		if (parteEntera != null && parteEntera.length() > enteros) {
			throw mgrExc.lanzarExcepcion(MSJ_ENTERO_MAX, TipoErrorNegocio.ALERTA, null, new String[] { parteEntera, Integer.toString(enteros) });
		}
		if (parteDecimal != null && parteDecimal.length() > decimales) {
			throw mgrExc.lanzarExcepcion(MSJ_DECIMALES_MAX, TipoErrorNegocio.ALERTA, null, new String[] { parteDecimal, Integer.toString(decimales) });
		}
		return true;
	}

	/**
	 * Realiza las validaciones pertinentes de obligatorierdad y longitud maxima
	 * para un valor de tipo texto.
	 * 
	 * @param valor:
	 *            a verificar.
	 * @param obligatorio:
	 *            determina si se validara la obligatoriedad.
	 * @param longitud:
	 *            determina la longitud maxima en caracteres para el valor.
	 * @return verdadero en caso de que las validaciones funcionen
	 *         satisfactoreamente.
	 * @throws NegocioException:
	 *             en caso de que alguna de las validaciones falle. con el
	 *             codigo especificado del error producido.
	 */
	public static boolean validar(String valor, boolean obligatorio, int longitud) throws NegocioException {
		if (obligatorio && UtilTexto.estaVacio(valor)) {
			throw mgrExc.lanzarExcepcion(MSJ_OBLIGATORIO, TipoErrorNegocio.ALERTA);
		}
		if (valor != null && valor.length() > longitud) {
			throw mgrExc.lanzarExcepcion(MSJ_LONGITUD_MAX, TipoErrorNegocio.ALERTA);
		}
		return true;
	}

	/**
	 * Realiza las validaciones pertinentes de obligatorierdad y longitud maxima
	 * para un valor de tipo texto que tiene el formato de correo.
	 * 
	 * @param valor:
	 *            a verificar.
	 * @param obligatorio:
	 *            determina si se validara la obligatoriedad.
	 * @param longitud:
	 *            determina la longitud maxima en caracteres para el valor.
	 * @return verdadero en caso de que las validaciones funcionen
	 *         satisfactoreamente.
	 * @throws NegocioException:
	 *             en caso de que alguna de las validaciones falle. con el
	 *             codigo especificado del error producido.
	 */
	public static boolean validarCorreo(String valor, boolean obligatorio, int longitud) throws NegocioException {
		if (obligatorio && UtilTexto.estaVacio(valor)) {
			throw mgrExc.lanzarExcepcion(MSJ_OBLIGATORIO, TipoErrorNegocio.ERROR);
		}
		if (valor != null && valor.length() > longitud) {
			throw mgrExc.lanzarExcepcion(MSJ_LONGITUD_MAX, TipoErrorNegocio.ERROR);
		}
		Pattern pattern = Pattern.compile(PATTERN_EMAIL);
		if (!UtilTexto.estaVacio(valor) && !pattern.matcher(valor).matches()) {
			throw mgrExc.lanzarExcepcion(MSJ_TEXTO_CORREO, TipoErrorNegocio.ERROR);
		}
		return true;
	}

	public static boolean validarFormato(String valor, boolean obligatorio, int longitud, String formato) throws NegocioException {
		if (obligatorio && UtilTexto.estaVacio(valor)) {
			throw mgrExc.lanzarExcepcion(MSJ_OBLIGATORIO, TipoErrorNegocio.ERROR);
		}
		if (valor != null && valor.length() > longitud) {
			throw mgrExc.lanzarExcepcion(MSJ_LONGITUD_MAX, TipoErrorNegocio.ERROR, null, new String[] { longitud + "" });
		}
		Pattern pattern = Pattern.compile(formato);
		if (!UtilTexto.estaVacio(valor) && !pattern.matcher(valor).matches()) {
			throw mgrExc.lanzarExcepcion(MSJ_NO_FORMATO, TipoErrorNegocio.ERROR, null, new String[] { formato });
		}
		return true;
	}

	/**
	 * Permite verificar si un texto termina en una extension determinada, se
	 * usa para verificar la extension de los archivos.
	 * 
	 * @param valor:
	 *            texto a verificar.
	 * @param extensiones:
	 *            permitidas para el texto.
	 * @return verdadero si el texto esta vacio o cumple con una de las
	 *         extensiones determinadas.
	 */
	private static boolean validarExtension(String valor, String[] extensiones) {
		if (UtilTexto.estaVacio(valor) || extensiones == null || extensiones.length <= 0) {
			return true;
		}
		for (String extension : extensiones) {
			if (valor.toUpperCase().endsWith(extension.toUpperCase()))
				return true;
		}
		return false;
	}

	/***
	 * Permite realizar la validacion sobre un archivo, valida si es obligatorio
	 * o no, y opcionalmente si cumple con la extension requerida y un limite
	 * para el tamaño.
	 * 
	 * @param archivo:
	 *            al que se realizará las validaciones pertinentes. Si el tamaño
	 *            del archivo es muy pequeño lo considera por tanto vacio.
	 * @param obligatorio:
	 *            valida si es obligatorio el archivo.
	 * @param extensiones:
	 *            determina las extensiones a las que debe pertenecer el
	 *            archivo.
	 * @param tamanio:
	 *            el limite del tamaño se define el valor en Kilobites, para
	 *            reallizar su respectiva validacion.
	 * @return: verdadero si cumple con todas las validaciones respectivas.
	 * @throws NegocioException
	 *             en caso de que no cumple con las validaciones respectivas.
	 */
	public static boolean validar(File archivo, boolean obligatorio, String[] extensiones, Long tamanio) throws NegocioException {
		if (archivo == null && obligatorio) {
			throw mgrExc.lanzarExcepcion(MSJ_OBLIGATORIO, TipoErrorNegocio.ERROR);
		} else if (archivo == null)
			return true;
		if (!validarExtension(archivo.getName(), extensiones)) {
			throw mgrExc.lanzarExcepcion(MSJ_EXTENSION_ARCHIVO, TipoErrorNegocio.ERROR, null,
					new String[] { UtilTexto.unificar(extensiones, ","), new Long(tamanio / 1024L).toString() });
		}
		if (archivo.length() < (100)) {// si el tamaño del archivo es < a 10
										// bytes se considera vacio
			throw mgrExc.lanzarExcepcion(MSJ_ARCHIVO_VACIO, TipoErrorNegocio.ERROR);
		}
		if (tamanio != null && archivo.length() > (tamanio * 1024)) {
			throw mgrExc.lanzarExcepcion(MSJ_TAMANIO_ARCHIVO, TipoErrorNegocio.ERROR, null, new String[] { new Long(tamanio / 1000L).toString() });
		}
		return true;
	}
	public static boolean validar(Object archivo,Long tamanioArchivo, String nombreArchivo,boolean obligatorio, String[] extensiones, Long tamanioLimite) throws NegocioException {
	    if (archivo == null && obligatorio) {
		throw mgrExc.lanzarExcepcion(MSJ_OBLIGATORIO, TipoErrorNegocio.ERROR);
	    } else if (archivo == null)
		return true;
	    if (!validarExtension(nombreArchivo, extensiones)) {
		throw mgrExc.lanzarExcepcion(MSJ_EXTENSION_ARCHIVO, TipoErrorNegocio.ERROR, null,
			new String[] { UtilTexto.unificar(extensiones, ","), new Long(tamanioLimite / 1024L).toString() });
	    }
	    if (tamanioArchivo < (100)) {// si el tamaño del archivo es < a 10
		// bytes se considera vacio
		throw mgrExc.lanzarExcepcion(MSJ_ARCHIVO_VACIO, TipoErrorNegocio.ERROR);
	    }
	    if (tamanioLimite != null && tamanioArchivo > (tamanioLimite * 1024)) {
		throw mgrExc.lanzarExcepcion(MSJ_TAMANIO_ARCHIVO, TipoErrorNegocio.ERROR, null, new String[] { new Long(tamanioLimite / 1000L).toString() });
	    }
	    return true;
	}

	public static boolean validar(byte[] archivo, String nombre, boolean obligatorio, String[] extensiones, Long tamanio) throws NegocioException {
		if (archivo == null && obligatorio) {
			throw mgrExc.lanzarExcepcion(MSJ_OBLIGATORIO, TipoErrorNegocio.ERROR);
		} else if (archivo == null)
			return true;
		if (!validarExtension(nombre, extensiones)) {
			throw mgrExc.lanzarExcepcion(MSJ_EXTENSION_ARCHIVO, TipoErrorNegocio.ERROR, null,
					new String[] { UtilTexto.unificar(extensiones, ","), new Long(tamanio / 1024L).toString() });
		}
		if (archivo.length < (100)) {// si el tamaño del archivo es < a 10
			// bytes se considera vacio
			throw mgrExc.lanzarExcepcion(MSJ_ARCHIVO_VACIO, TipoErrorNegocio.ERROR);
		}
		if (tamanio != null && archivo.length > (tamanio * 1024)) {
			throw mgrExc.lanzarExcepcion(MSJ_TAMANIO_ARCHIVO, TipoErrorNegocio.ERROR, null, new String[] { Long.toString(tamanio / 1000L) });
		}
		return true;
	}

	/**
	 * Permite validar la olbigatorierdad de una fecha tipo Timestamp.
	 * 
	 * @param valor:
	 *            a verificar
	 * @param obligatorio:
	 *            en caso de que el valor sea obligatorio.
	 * @return verdadero si cumple con todas las validaciones.
	 * @throws NegocioException:
	 *             si las validaciones no se cumplen.
	 */
	public static boolean validar(Timestamp valor, boolean obligatorio) throws NegocioException {
		if (obligatorio && valor == null) {
			throw mgrExc.lanzarExcepcion(MSJ_OBLIGATORIO, TipoErrorNegocio.ERROR);
		}
		return true;
	}

	/**
	 * Permite validar la olbigatorierdad de una fecha tipo Date.
	 * 
	 * @param valor
	 *            a verificar
	 * @param obligatorio
	 *            en caso de que el valor sea obligatorio.
	 * @return verdadero si cumple con todas las validaciones.
	 * @throws NegocioException:
	 *             si las validaciones no se cumplen.
	 */
	public static boolean validar(Date valor, boolean obligatorio) throws NegocioException {
		if (obligatorio && valor == null) {
			throw mgrExc.lanzarExcepcion(MSJ_OBLIGATORIO, TipoErrorNegocio.ERROR);
		}
		return true;
	}

	/**
	 * Verifica si un listado de opciones por lo menos se selecciono al menos un
	 * valor.
	 * 
	 * @param valor:
	 *            listado a verificar.
	 * @param obligatorio:
	 *            permite verificar su obligatoriedad.
	 * @return en caso de que se cumplan todas las validaciones.
	 * @throws NegocioException
	 *             en caso de que el listado no cumpla con las vlaidaciones.
	 */
	public static boolean validar(String[] valor, boolean obligatorio) throws NegocioException {
		if (obligatorio && ((valor == null) || ((valor != null) && (valor.length <= 0)))) {
			throw mgrExc.lanzarExcepcion(MSJ_OBLIGATORIO, TipoErrorNegocio.ERROR);
		}
		return true;
	}

	/**
	 * Permite realizar las diferentes funciones de comparacion entre dos
	 * valores de fecha tipo Timestamp,los tipo de comparacion son: IGUAL,
	 * DIFERENTE, MAYOR_QUE, MENOR_QUE, MAYOR_IGUAL, MENOR_IGUAL
	 * 
	 * @param fecha:
	 *            a verificar
	 * @param tipo:
	 *            de comparacion que se realizará entre los dos valores.
	 * @param fechaComparacion:
	 *            valor con el que se contrastara la fecha a verificar.
	 * @return verdadero si cumple con la comparacion realizada.
	 * @throws NegocioException:
	 *             en caso que no supere la validacion pertinente.
	 */
	public static boolean comparar(Timestamp fecha, TipoComparacion tipo, Timestamp fechaComparacion) throws NegocioException {
		if (fecha == null || fechaComparacion == null) {
			return true;
		}
		boolean validacion = true;
		int mensaje = 0;
		switch (tipo) {
		case IGUAL:
			validacion = fecha.equals(fechaComparacion);
			mensaje = MSJ_FECHA_IGUAL;
			break;
		case DIFERENTE:
			validacion = !fecha.equals(fechaComparacion);
			mensaje = MSJ_FECHA_DIFERENTE;
			break;
		case MAYOR_QUE:
			validacion = fecha.after(fechaComparacion);
			mensaje = MSJ_FECHA_MAYOR;
			break;
		case MENOR_QUE:
			validacion = fecha.before(fechaComparacion);
			mensaje = MSJ_FECHA_MENOR;
			break;
		case MAYOR_IGUAL:
			validacion = fecha.equals(fechaComparacion) || fecha.after(fechaComparacion);
			mensaje = MSJ_FECHA_MAYOR_IGUAL;
			break;
		case MENOR_IGUAL:
			validacion = fecha.equals(fechaComparacion) || fecha.before(fechaComparacion);
			mensaje = MSJ_FECHA_MENOR_IGUAL;
			break;
		}
		if (validacion) {
			return true;
		}
		SimpleDateFormat sd = new SimpleDateFormat(Constantes.FORMATO_FECHA);
		throw mgrExc.lanzarExcepcion(mensaje, TipoErrorNegocio.ERROR, null, new String[] { sd.format(fecha), sd.format(fechaComparacion) });
	}

	/**
	 * Permite realizar las diferentes funciones de comparacion entre dos
	 * valores de fecha tipo DATE,los tipo de comparacion son: IGUAL, DIFERENTE,
	 * MAYOR_QUE, MENOR_QUE, MAYOR_IGUAL, MENOR_IGUAL
	 * 
	 * @param fecha:
	 *            a verificar
	 * @param tipo:
	 *            de comparacion que se realizará entre los dos valores.
	 * @param fechaComparacion:
	 *            valor con el que se contrastara la fecha a verificar.
	 * @throws NegocioException:
	 *             en caso que no supere la validacion pertinente.
	 */
	public static boolean comparar(Date fecha, TipoComparacion tipo, Date fechaComparacion) throws NegocioException {
		if (fecha == null || fechaComparacion == null)
			return true;
		boolean validacion = true;
		int mensaje = 0;
		switch (tipo) {
		case IGUAL:
			validacion = fecha.equals(fechaComparacion);
			mensaje = MSJ_FECHA_IGUAL;
			break;
		case DIFERENTE:
			validacion = !fecha.equals(fechaComparacion);
			mensaje = MSJ_FECHA_DIFERENTE;
			break;
		case MAYOR_QUE:
			validacion = fecha.after(fechaComparacion);
			mensaje = MSJ_FECHA_MAYOR;
			break;
		case MENOR_QUE:
			validacion = fecha.before(fechaComparacion);
			mensaje = MSJ_FECHA_MENOR;
			break;
		case MAYOR_IGUAL:
			validacion = fecha.equals(fechaComparacion) || fecha.after(fechaComparacion);
			mensaje = MSJ_FECHA_MAYOR_IGUAL;
			break;
		case MENOR_IGUAL:
			validacion = fecha.equals(fechaComparacion) || fecha.before(fechaComparacion);
			mensaje = MSJ_FECHA_MENOR_IGUAL;
			break;
		}
		if (validacion)
			return true;
		SimpleDateFormat sd = new SimpleDateFormat(Constantes.FORMATO_FECHA);
		throw mgrExc.lanzarExcepcion(mensaje, TipoErrorNegocio.ERROR, null, new String[] { sd.format(fecha), sd.format(fechaComparacion) });
	}

	public static boolean compararDiferenciaMeses(Date fecha, Date fechaComparacion, Long meses) throws NegocioException {
		if (fecha == null || fechaComparacion == null || meses == null)
			return true;
		Long diferencia = UtilFecha.diferenciaEnMeses(fecha, fechaComparacion);
		if (meses < 0)
			meses = -meses;
		if (diferencia >= 0 && diferencia < meses)
			return true;
		if (diferencia < 0 && diferencia > -meses)
			return true;
		SimpleDateFormat sd = new SimpleDateFormat(Constantes.FORMATO_FECHA);
		throw mgrExc.lanzarExcepcion(MSJ_DIFERENCIA_FECHAS, TipoErrorNegocio.ERROR, null,
				new String[] { sd.format(fecha), sd.format(fechaComparacion), meses.toString() });
	}

	/**
	 * Permite realizar las diferentes funciones de comparacion entre dos
	 * valores enteros tipo Long,los tipo de comparacion son: IGUAL, DIFERENTE,
	 * MAYOR_QUE, MENOR_QUE, MAYOR_IGUAL, MENOR_IGUAL
	 * 
	 * @param valor:
	 *            a verificar
	 * @param tipo:
	 *            de comparacion que se realizará entre los dos valores.
	 * @param comparacion:
	 *            valor con el que se contrastara el entero a verificar.
	 * @return verdadero si cumple con la comparacion realizada.
	 * @throws NegocioException:
	 *             en caso que no supere la validacion pertinente.
	 */
	public static boolean comparar(Long valor, TipoComparacion tipo, Long comparacion) throws NegocioException {
		if (valor == null || comparacion == null) {
			return true;
		}
		boolean validacion = true;
		int mensaje = 0;
		switch (tipo) {
		case IGUAL:
			validacion = valor == comparacion;
			mensaje = MSJ_ENTERO_IGUAL;
			break;
		case DIFERENTE:
			validacion = valor != comparacion;
			mensaje = MSJ_ENTERO_DIFERENTE;
			break;
		case MAYOR_QUE:
			validacion = valor > comparacion;
			mensaje = MSJ_ENTERO_MAYOR;
			break;
		case MENOR_QUE:
			validacion = valor < comparacion;
			mensaje = MSJ_ENTERO_MENOR;
			break;
		case MAYOR_IGUAL:
			validacion = valor >= comparacion;
			mensaje = MSJ_ENTERO_MAYOR_IGUAL;
			break;
		case MENOR_IGUAL:
			validacion = valor <= comparacion;
			mensaje = MSJ_ENTERO_MENOR_IGUAL;
			break;
		}
		if (validacion) {
			return true;
		}
		throw mgrExc.lanzarExcepcion(mensaje, TipoErrorNegocio.ERROR, null, new String[] { valor.toString(), comparacion.toString() });
	}

	/**
	 * Permite realizar las diferentes funciones de comparacion entre dos
	 * valores tip texto,los tipo de comparacion son: IGUAL, DIFERENTE
	 * 
	 * @param valor:
	 *            a verificar
	 * @param tipo:
	 *            de comparacion que se realizará entre los dos valores.
	 * @param comparacion:
	 *            valor con el que se contrastara el texto a verificar.
	 * @return verdadero si cumple con la comparacion realizada.
	 * @throws NegocioException:
	 *             en caso que no supere la validacion pertinente.
	 */
	public static boolean comparar(String valor, TipoComparacion tipo, String comparacion) throws NegocioException {
		if (valor == null || comparacion == null) {
			return true;
		}
		boolean validacion = true;
		int mensaje = 0;
		switch (tipo) {
		case IGUAL:
			validacion = valor.equals(comparacion);
			mensaje = MSJ_TEXTO_IGUAL;
			break;
		case DIFERENTE:
			validacion = valor != comparacion;
			mensaje = MSJ_TEXTO_DIFERENTE;
			break;
		default:
			validacion = false;
			mensaje = MSJ_OPERACION_NO_ESPECIFICADA;
			break;
		}
		if (validacion) {
			return true;
		}
		throw mgrExc.lanzarExcepcion(mensaje, TipoErrorNegocio.ERROR, null, new String[] { valor, comparacion });
	}
}
