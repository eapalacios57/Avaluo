package com.asesoftware.util.cons;

/**
 * Esta clase define los tipos de error que se pueden presentar en el aplicativo
 * y el nivel de prioridad de los mismos.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:47 a.m.
 */
public enum TipoErrorNegocio {
	FATAL(1), ERROR(2), ALERTA(3), INFO(4);

	private int prioridad;

	TipoErrorNegocio(int prioridad) {
		this.prioridad = prioridad;
	}

	public int getPrioridad() {
		return prioridad;
	}

}