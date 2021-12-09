package com.segurosbolivar.avaluos.modelo.cons;

/**
 * Esta enumeracion representa el estado posibles para la mayoria de registros
 * en las tablas del sistema.
 * 
 * @author stilaguy
 *
 */
public enum EstadoRegistro {
    ACTIVO("A"), INACTIVO("I");

    /**
     * Valor asociado, es el valor que se aplicara al registro en el campo estado
     * respectivo.
     */
    private final String valor;

    private EstadoRegistro(String valor) {
	this.valor = valor;
    }

    /**
     * Permite recuperar la el estado del registro en especifico enviando el valor
     * con el que se identifica si el valor no existe retornara un valor nulo.
     * 
     * @param valor
     *            identificador con el que podremos obtener el estado del registro
     *            correspondiente.
     * @return la enumeracion que corresponde al valor enviado, si no existe
     *         retornara nulo.
     */
    public static EstadoRegistro getEstado(String valor) {
	if (valor == null)
	    return null;
	for (EstadoRegistro element : values())
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
