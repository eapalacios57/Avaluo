package com.segurosbolivar.avaluos.modelo.cons;

/**
 * Tipo de clasificacion de la seguridad, estos valores son usados por filenet
 * para el servicio de consulta.
 * 
 * @author Asesoftware
 *
 */
public enum TipoClasificacionSeguridad {

    CSI004("CSI004");
    /**
     * Valor de la clasificacion de seguridad que se envia hacia filenet.
     */
    private final String valor;

    private TipoClasificacionSeguridad(String valor) {
	this.valor = valor;
    }

    /*
     * getters
     */
    public String getValor() {
	return this.valor;
    }
}
