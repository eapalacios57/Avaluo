package com.segurosbolivar.avaluos.modelo.cons;

/***
 * Permite definir quien elabora una peticion de consulta o registro de archivos
 * en filenet.
 * 
 * @author Asesoftware
 *
 */
public enum TipoElaborador {

    PROVEEDOR("Proveedor");
    /**
     * Valor del tipo de elaborador que se envia hacia filenet.
     */
    private final String valor;

    private TipoElaborador(String valor) {
	this.valor = valor;
    }

    /*
     * getters
     */
    public String getValor() {
	return this.valor;
    }
}
