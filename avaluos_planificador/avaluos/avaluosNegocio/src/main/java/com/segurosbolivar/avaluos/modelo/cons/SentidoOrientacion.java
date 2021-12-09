package com.segurosbolivar.avaluos.modelo.cons;

/**
 * Permite determinar el sentido de orientacion en que se ejecutara el
 * ordenamiento de una consulta para un campo determinado.
 * 
 * @author stilaguy
 *
 */
public enum SentidoOrientacion {
    ASCENDENTE("ASC"), DESCENDENTE("DESC"), SIN_ESPECIFICAR("");
    /**
     * Identificador o palabra clave con el que se realizara la invocacion de la
     * operacion en lenguaje SQL.
     */
    private final String valor;

    private SentidoOrientacion(String valor) {
	this.valor = valor;
    }

    /*
     * getters
     */

    public String getValor() {
	return this.valor;
    }

}
