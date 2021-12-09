package com.segurosbolivar.avaluos.modelo.cons;

/**
 * Representa un valor o estado booleano como se guarda en la base de datos.
 * como es el caso de algunos campos de estado o booleanos en las tablas del
 * aplicativo.
 * 
 * @author stilaguy
 *
 */
public enum EstadoBoleano {
    TRUE(1L), FALSE(0L);
    /**
     * Identificador del estado booleano.
     */
    private final Long valor;

    private EstadoBoleano(Long valor) {
	this.valor = valor;
    }

    /**
     * Permite recuperar la enumeracion en especifico enviando el valor con el que
     * se identifica si el valor no existe retornara un valor nulo.
     * 
     * @param valor
     *            identificador con el que podremos obtener la enumeracion
     *            correspondiente.
     * @return la enumeracion que corresponde al valor enviado, si no existe
     *         retornara nulo.
     */
    public static EstadoBoleano getEstado(Long valor) {
	if (valor == null)
	    return null;
	for (EstadoBoleano element : values())
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
