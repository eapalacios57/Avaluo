package com.segurosbolivar.avaluos.modelo.cons;

/**
 * Permite identificador los codigos de los tipos de productos asociados a las
 * polizar en Filenet y con los cuales se realizan las peticiones en los
 * servicios
 * 
 * @author Asesoftware
 *
 */
public enum TipoProducto {

    HOGAR("339"), HIPOTECARIO_DAVIVIENDA("74");

    /**
     * Valor del tipo de producto que se envia hacia filenet.
     */
    private final String valor;

    private TipoProducto(String valor) {
	this.valor = valor;
    }

    /*
     * getters
     */

    public String getValor() {
	return this.valor;
    }
}
