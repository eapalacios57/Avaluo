package com.segurosbolivar.avaluos.modelo.cons;

/**
 * representa los tipos de identificacion que se usan para las peticiones
 * realizadas en filenet.
 * 
 * @author Asesoftware
 *
 */
public enum TipoIdentificacion {

    NIT("3"), CEDULA_CIUDADANIA("1"), CEDULA_EXTRANJERIA("2"), TARJETA_IDENTIDAD("Tarjeta de Identidad"), NUIP("Numero Unico de Identificacion Personal"), CODIGO_FISCAL(
	    "Codigo Fiscal"), CARNET_DIPLOMATICO("Carnet Diplomatico"), NIT_EXTRANJERIA("NIT Extranjeria"), PASAPORTE("Pasaporte"), REGISTRO_CIVIL_DEFUNCION(
		    "Registro civil de defuncion"), REGISTRO_CIVIL_NACIMIENTO("Registro Civil de Nacimiento"), RUT_PERSONA_EXTRANJERA(
			    "RUT Persona Extranjera"), RUT_PERSONA_JURIDICA("RUT Persona Juridica"), RUT_PERSONA_NATURAL("RUT Persona Natural");
    /**
     * Valor del tipo de identificacion que se envia hacia filenet.
     */
    private final String valor;

    private TipoIdentificacion(String valor) {
	this.valor = valor;
    }

    /*
     * getters
     */
    public String getValor() {
	return this.valor;
    }
}
