package com.asesoftware.util.log;

import java.util.UUID;

/**
 * Descripcion: Clase encargada de almacenar el tiempo inicial de la transaccion
 * y el id de la transaccion
 *
 * @author John Fredy Rincon Laverde
 * @version 1.0.
 *
 */
public class ParametrosIniciales {

	private Long time;
	private Long uuid;

	/**
	 * Metodo encargado de registrar en el log el tiempo de procesamiento de la
	 * solicitud. Adicionalmente genera la variable de UUID mostrada en el log.
	 */
	protected ParametrosIniciales() {
		this.time = System.currentTimeMillis();
		this.uuid = UUID.randomUUID().getMostSignificantBits();
	}

	/**
	 * @return el tiempo inicial de la transaccion
	 */
	public Long getTime() {
		return time;
	}

	/**
	 * @return el identificador de la transaccion
	 */
	public Long getUuid() {
		return uuid;
	}

}
