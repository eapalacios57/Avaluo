package com.segurosbolivar.avaluos.modelo.cons;

/**
 * Esta enumeracion lista los tipos de archivos que son posibles para un cargue
 * masivo de avaluos.
 * 
 * @author stilaguy
 *
 */
public enum TipoArchivo {

    ZIP("application/x-zip-compressed"), TEXT("text/plain");

    /**
     * Contiene el type mime para cada uno de los tipos de archivos.
     */
    private String valor;

    TipoArchivo(String valor) {
	this.valor = valor;
    }

    /*
     * getters
     */

    public String getValor() {
	return this.valor;
    }

}
