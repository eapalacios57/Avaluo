package com.segurosbolivar.avaluos.modelo.cons;

/**
 * Representa las categorias existentes para para los imuebles y determinar el
 * campo en la tabla de PGB_ARCHIVO_PLANO, que contiene el valor de
 * obligatoriedad para cada categoria.
 * 
 * @author stilaguy
 *
 */
public enum CategoriaInmueble {
	LOTE(12L, "obligatoriedadLote"),
	CASA_RURAL(13L, "obligatoriedadCasarural"), 
	LOTE_URBANO(15L,"obligatoriedadLoteurbano"), 
	APARTAMENTO(2L, "obligatoriedadApartamento"), 
	CASA(4L,"obligatoriedadCasa"), 
	BODEGA(5L, "obligatoriedadBodega"), 
	LOCAL(6L,"obligatoriedadLocal"), 
	OFICINA(7L, "obligatoriedadOficina");

        /**
         * Determina el valor del dominio al que corresponde cada categoria y el cual
         * sirve como identificador para recuperar la misma.
         */
	private final Long valor;
	 /**
	     * Campo de la tabla PGB_ARCHIVO_PLANO el cual determina la obligatoriedad para
	     * una categoria definida
	     */
	private final String campo; 

	private CategoriaInmueble(Long valor, String campo) {
		this.valor = valor;
		this.campo = campo;
	}

	    /**
	     * Permite obtener una categoria en especifico enviando el valor que hace parte
	     * de la identifiacion del dominio para la categoria determinada, si el valor no
	     * existe retornara un valor nulo.
	     * 
	     * @param valor
	     *            identificador con el que podremos obtener la categoria.
	     * @return la categoria del inmueble que corresponde al valor enviado, si no
	     *         existe retornara nulo.
	     */
	public static CategoriaInmueble getCategoria(Long valor) {
		if (valor == null)
			return null;
		for (CategoriaInmueble element : values())
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

	public String getCampo() {
		return this.campo;
	}

}
