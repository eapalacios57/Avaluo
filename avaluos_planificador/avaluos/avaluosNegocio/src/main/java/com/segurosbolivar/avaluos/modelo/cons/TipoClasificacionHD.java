package com.segurosbolivar.avaluos.modelo.cons;

/**
 * Representa los valores posibles para la clasificasiones de habeas data, que
 * se usan en el servicio de consulta de filenet.
 * 
 * @author Asesoftware
 *
 */
public enum TipoClasificacionHD {

    HD003("HDA003");

    /**
     * Valor de la clasificacion que se envia hacia filenet.
     */
    private final String valor;

    private TipoClasificacionHD(String valor) {
	this.valor = valor;
    }

    /*
     * getters
     */
    public String getValor() {
	return this.valor;
    }
}
