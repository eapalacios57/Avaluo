package com.segurosbolivar.avaluos.modelo.cons;

/**
 * determinar el tipo de informe para un docuemnto que sera parte de la peticion
 * de filenet y sus operaciones.
 * 
 * @author Asesoftware
 *
 */
public enum TipoInformeGD {

    TECNICO("Tecnico");

    /**
     * Valor del tipo de informe que se envia hacia filenet.
     */
    private final String valor;

    private TipoInformeGD(String valor) {
	this.valor = valor;
    }

    /*
     * getters
     */
    public String getValor() {
	return this.valor;
    }

}
