package com.segurosbolivar.avaluos.modelo.cons;

/**
 * Representa la procedencia u origen que puede tener un avaluo.
 * 
 * @author stilaguy
 *
 */
public enum Procedencia {
    COPIA_MULTIPLE(2L), INDIVIDUAL(1L), DESCONOCIDO(6L), REPLICA(3L), TRANSCRIPCION(4L), CARGUE_MASIVO(5L);

    private final Long valor;

    /**
     * Identificador de la procedencia corresponde al dominio PROCEDENCIA_AVALUO en
     * la tabla CG_REF_CODES
     */
    private Procedencia(Long valor) {
	this.valor = valor;
    }

    /**
     * Permite recuperar una procedencia en especifico enviando el valor con el que
     * se identifica si el valor no existe retornara un valor nulo.
     * 
     * @param valor
     *            identificador con el que podremos obtener la procedencia
     *            correspondiente.
     * @return la procedencia que corresponde al valor enviado, si no existe
     *         retornara nulo.
     */
    public static Procedencia getEstado(Long valor) {
	if (valor == null)
	    return null;
	for (Procedencia element : values())
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
