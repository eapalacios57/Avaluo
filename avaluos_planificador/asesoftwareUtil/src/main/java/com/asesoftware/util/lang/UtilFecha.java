package com.asesoftware.util.lang;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Esta clase ofrece utilidad para procesar las fechas del sistema. Utiliza la
 * clase java.util.date y calendar teniendo en cuenta que el desarrollo no usa
 * una version actualizada de Java.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:47 a.m.
 */
public class UtilFecha {

	private static final SimpleDateFormat FORMATO_AAAAMMDDHH24MISS = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final SimpleDateFormat FORMATO_DDMMAAAA = new SimpleDateFormat("ddMMyyyy");

	public UtilFecha() {

	}



	/**
	 * Permite determinar cuantos meses de diferencia hay entre dos fechas. La
	 * diferencia puede ser en valores negativos. en caso de que la fecha de
	 * comparacion sea mayor a la fecha a validar.
	 * 
	 * @param fecha
	 *            a la que se le restaran los meses de la fecha de comparacion.
	 * @param fechaComparacion
	 *            que se restara a la fecha original para verificar la
	 *            diferencia en meses.
	 * @return diferencia en meses.
	 */
	public static long diferenciaEnMeses(Date fecha, Date fechaComparacion) {
		long diferenciaAnios = obtenerAnio(fecha) - obtenerAnio(fechaComparacion);
		long diferenciameses = obtenerMes(fecha) - obtenerMes(fechaComparacion);
		return diferenciameses + (diferenciaAnios * 12);
	}

	public static long compararFechaDia(Date fecha, Date fechaComparacion) {
		return truncarFecha(fecha).compareTo(truncarFecha(fechaComparacion));
	}

	public static Date truncarFecha(Date fecha) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(fecha);
		gc.set(Calendar.HOUR_OF_DAY, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MILLISECOND, 0);
		return gc.getTime();
	}

	/**
	 *
	 * @param fecha
	 */
	public static Integer obtenerAnio(Date fecha) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(fecha);
		return calendar.get(Calendar.YEAR);
	}
	/**
	 *
	 * @param fecha
	 */
	public static Integer obtenerDia(Date fecha) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(fecha);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 *
	 * @param fecha
	 */
	public static Integer obtenerMes(Date fecha) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(fecha);
		return calendar.get(Calendar.MONTH);
	}

	/**
	 *
	 * @param fecha
	 */
	public static Timestamp dateToTimestamp(Date fecha) {
		return new Timestamp(fecha.getTime());
	}

	/**
	 *
	 * @param fecha
	 */
	public static Date timestampToDate(Timestamp fecha) {
		return new Date(fecha.getTime());
	}

	public static String dateToString(String formato, Date fecha) {
		DateFormat df = new SimpleDateFormat(formato);
		return df.format(fecha);
	}

	public static Calendar parse(Timestamp fecha) {
		GregorianCalendar gcalendar = new GregorianCalendar();
		gcalendar.setTime(fecha);
		return gcalendar;
	}

	public static Calendar parse(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static Date obtenerFechaSistema() {
		return new Date();
	}

	public static boolean isFechaMayorActual(Date fechaRegistro) {
		boolean isMayor = false;

		if (fechaRegistro.before(obtenerFechaSistema())) {
			isMayor = true;
		}

		return isMayor;
	}

	public static Date sumaryRestarDias(Date fecha, int dias) {

		Date nuevaFecha = new Date();
		Calendar cal = Calendar.getInstance();

		if (fecha != null) {
			cal.setTime(fecha);
			cal.add(Calendar.DATE, dias);
			nuevaFecha = cal.getTime();
		} else {
			nuevaFecha = null;
		}

		return nuevaFecha;
	}

	public static Object fechaActualFormatoAAAAMMDDHH24MISS() {
		return FORMATO_AAAAMMDDHH24MISS.format(new Date());
	}

	/**
	 * Pemie obtener la fecha en formato dd/MM/yyyy
	 * @param fecha a procesar
	 * @return fecha en formato dd/MM/yyyy texto 
	 */
	public static String fechaAFormatoddMMyyyy(Date fecha) {
		return new SimpleDateFormat("dd/MM/yyyy").format(fecha);
	}
	/**
	 * Pemie obtener la fecha en formato dd/MM/yyyy
	 * @param fecha a procesar
	 * @return fecha en formato dd/MM/yyyy texto 
	 */
	public static String fechaAFormatoddMMyyyySinSeparadores(Date fecha) {
		return FORMATO_DDMMAAAA.format(fecha);
	}

	/**
	 * Obtiene la fecha de ejecucion de un determinado proceso
	 * 
	 * @return Date Fecha de ejecucion de un proceso
	 */
	public static java.sql.Date getProcessDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/y");
		java.sql.Date fechaConsulta = new java.sql.Date(0);

		try {
			String fechaConsultaString = dateFormat.format(new Date());
			fechaConsulta = new java.sql.Date(dateFormat.parse(fechaConsultaString).getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fechaConsulta;
	}

}