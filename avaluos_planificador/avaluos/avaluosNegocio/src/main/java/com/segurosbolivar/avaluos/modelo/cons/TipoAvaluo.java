package com.segurosbolivar.avaluos.modelo.cons;

/**
 * Representa los tipos de avaluos que se manejan.
 * 
 * @author stilaguy
 *
 */
public enum TipoAvaluo {
    PROVISIONAL(1L), // representa un avaluo temporal, el cual no cuenta con registro fotografico
    DEFINITIVO(2L),// representa un avaluo permanente, el cual debe contar con el registro
	TRADICIONAL(1L),// representa un avalúo tradicional
	MOVIL(2L);// representa un avaluo que es de tipo movil
		   // fotografico respectivo.

    /**
     * Identificador del tipo de avaluo corresponde al dominio TIPOAVALUO en la
     * tabla CG_REF_CODES
     */
    private final Long valor;

    private TipoAvaluo(Long valor) {
	this.valor = valor;
    }

    /**
     * Permite recuperar el tipo de avaluo en especifico enviando el valor con el
     * que se identifica si el valor no existe retornara un valor nulo.
     * 
     * @param valor
     *            identificador con el que podremos obtener el tipo de avaluo
     *            correspondiente.
     * @return el tipo de avaluo que corresponde al valor enviado, si no existe
     *         retornara nulo.
     */
    public static TipoAvaluo getTipoAvaluo(Long valor) {
	if (valor == null)
	    return null;
	for (TipoAvaluo element : values())
	    if (element.valor.equals(valor))
		return element;
	return null;
    }

    /*
     * getters
     */

    public Long getValor() {
	return this.valor;
    }

}
