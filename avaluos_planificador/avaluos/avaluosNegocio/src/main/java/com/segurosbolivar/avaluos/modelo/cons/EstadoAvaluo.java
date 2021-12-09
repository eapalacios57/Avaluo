package com.segurosbolivar.avaluos.modelo.cons;

/**
 * Representa los estados por los que puede pasar un avaluo.
 * 
 * @author stilaguy
 *
 */
public enum EstadoAvaluo {
	NUEVO(2L), ELIMINADO(5L), APROBADO(3L), EN_APROBACION(4L);
    /**
     * Identificador del estado del avaluo corresponde al dominio ESTADOAVALUO en la
     * tabla CG_REF_CODES
     */
	private final Long valor;

	private EstadoAvaluo(Long valor) {
		this.valor = valor;
	}
	
	    /**
	     * Permite obtener un estado en especifico enviando el valor que hace parte
	     * de la identifiacion del dominio para el estado del avaluo determinado, si el valor no
	     * existe retornara un valor nulo.
	     * 
	     * @param valor
	     *            identificador con el que podremos obtener un estado en especifico.
	     * @return el estado del avaluo que corresponde al valor enviado, si no
	     *         existe retornara nulo.
	     */
	public static EstadoAvaluo getEstado(Long valor) {
		if (valor == null)
			return null;
		for (EstadoAvaluo element : values())
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
