package com.segurosbolivar.avaluos.modelo.cons;

/**
 * Permite identificar cada una de las subsecciones y el orden establecido como
 * parte de la seccion de informacion de la cosntruccion.
 * 
 * @author stilaguy
 *
 */
public enum SeccionConstruccion {

    ADICIONAL(1), DEPENDECIAS(2), SALUBRIDAD(3), ACABADOS(4), PROPIEDAD_HORIZONTAL(5), DOTACION_COMUNAL(6), NINGUNA(-1);

    /**
     * Permite identificar la subseccion de la construccion, ademas de establecer su
     * orden.
     */
    private int id;

    SeccionConstruccion(int id) {
	this.id = id;
    }

    /**
     * Permite recuperar una subseccion de la construccion en especifico enviando el
     * valor con el que se identifica si el valor no existe retornara un valor nulo.
     * 
     * @param valor
     *            identificador con el que podremos obtener la subseccion de la
     *            construccion correspondiente.
     * @return la subseccion de la construccion que corresponde al valor enviado, si
     *         no existe retornara nulo.
     */
    public static SeccionConstruccion obtener(int id) {
	for (SeccionConstruccion seccion : values()) {
	    if (seccion.id == id)
		return seccion;
	}
	return null;
    }
}
