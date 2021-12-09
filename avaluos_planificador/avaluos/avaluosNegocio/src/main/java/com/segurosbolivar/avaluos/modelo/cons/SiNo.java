package com.segurosbolivar.avaluos.modelo.cons;

/**
 * Esta enumeracion representa los valores de SI y NO para algunos de los campos
 * en las tablas del aplicativo.
 * 
 * @author stilaguy
 *
 */
public enum SiNo {
    SI("S"), NO("N");

    /**
     * llave o valor con el que se guarda el registro en los campos de este tipo en
     * base de datos.
     */
    private final String valor;

    private SiNo(String valor) {
	this.valor = valor;
    }

    /**
     * Permite recuperar el valor SiNo en especifico enviando el valor con el que se
     * identifica si el valor no existe retornara un valor nulo.
     * 
     * @param valor
     *            identificador con el que podremos obtener el valor SiNo
     *            correspondiente.
     * @return el valor SiNo que corresponde al valor enviado, si no existe
     *         retornara nulo.
     */
    public static SiNo getEstado(String valor) {
	if (valor == null)
	    return null;
	for (SiNo element : values())
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
