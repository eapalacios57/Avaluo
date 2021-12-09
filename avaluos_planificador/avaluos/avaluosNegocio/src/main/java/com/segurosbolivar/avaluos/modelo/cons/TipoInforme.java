package com.segurosbolivar.avaluos.modelo.cons;

/**
 * Representa el tipo de informe de un avaluo. Esta asociado a la marca proyecto
 * constructor y al campo A_TIPO_INFORME en la tabla PGB_AVALUOS
 * 
 * @author stilaguy
 *
 */
public enum TipoInforme {
    CREDITO(1L), PROYECTO(2L);

    /**
     * Identificador para el valor del campo A_TIPO_INFORME de la tabla PGB_AVALUOS
     */
    private final Long valor;

    private TipoInforme(Long valor) {
	this.valor = valor;
    }

    /*
     * getters
     */
    public Long getValor() {
	return this.valor;
    }

}
