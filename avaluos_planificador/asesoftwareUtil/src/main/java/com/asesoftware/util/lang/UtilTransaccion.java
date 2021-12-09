package com.asesoftware.util.lang;


/**
 * Clase encargada de almacenar el tiempo inicial de la transaccion y el id de la
 * transaccion.
 * @author John Fredy Rincon Laverde
 * @version 1.0.
 * @created 31-ago-2017 10:30:48 a.m.
 */
public class UtilTransaccion {

	private Long time;
	private Long uuid;



	/**
	 * Metodo encargado de registrar en el log el tiempo de procesamiento de la
	 * solicitud. Adicionalmente genera la variable de UUID mostrada en el log.
	 */
	protected UtilTransaccion(){

	}

	/**
	 * @return el tiempo inicial de la transaccion
	 */
	public Long getTime(){
		return time;
	}

	/**
	 * @return el identificador de la transaccion
	 */
	public Long getUuid(){
		return uuid;
	}

}