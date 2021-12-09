package com.segurosbolivar.avaluos.modelo.cons;

/**
 * Permite identificador los ramos existentes para las polizas dentro de
 * Filenet, a traves de los codigos asociados a estos ramos se realizan las
 * peticiones para los servicios que gestionan los archivo en el aplicativo
 * 
 * @author Asesoftware
 *
 */
public enum TipoRamo {

    HOGAR("23"), INCENDIO("5");

    /**
     * Valor del tipo de ramo que se envia hacia filenet.
     */
    private String valor;

    private TipoRamo(String valor) {
	this.valor = valor;
    }

    /*
     * getters
     */
    public String getValor() {
	return this.valor;
    }
}
