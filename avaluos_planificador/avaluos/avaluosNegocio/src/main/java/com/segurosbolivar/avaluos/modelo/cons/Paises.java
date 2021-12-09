package com.segurosbolivar.avaluos.modelo.cons;

/**
 * enumeracion que representa los paises para los que aplicativo esta vigente
 * 
 * @author stilaguy
 *
 */
public enum Paises {

    COLOMBIA("CO"), COSTARICA("CR");

    /**
     * Identificador del pais.
     */
    private final String valor;

    private Paises(String valor) {
	this.valor = valor;
    }

    /**
     * Permite recuperar un pais en especifico enviando el valor con el que se
     * identifica si el valor no existe retornara un valor nulo.
     * 
     * @param valor
     *            identificador con el que podremos obtener el pais correspondiente.
     * @return el pais que corresponde al valor enviado, si no existe retornara
     *         nulo.
     */
    public static Paises getPaises(String valor) {
	if (valor == null)
	    return null;
	for (Paises element : values())
	    if (element.valor.equals(valor))
		return element;
	return null;
    }

    /*
     * getters
     */
    public String getValor() {
	return this.valor;
    }

}
