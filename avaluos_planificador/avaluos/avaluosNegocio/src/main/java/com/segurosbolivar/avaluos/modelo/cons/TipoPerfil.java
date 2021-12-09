package com.segurosbolivar.avaluos.modelo.cons;

/***
 * Permite relaciones los perfiles existentes en FCA y que son usados por el
 * proyecto de modernizacion. para realizar el respectivo control de permisos
 * 
 * 
 * @author Asesoftware
 *
 */
public enum TipoPerfil {
    PERITO_PADRE("PGBPERITO01"), 
    PERITO_HIJO("PGBPERITO02"),
    PERITO_HIJO_SEGUNDO("PGBPERITO03"), 
    USUARIO_ADMINISTRADOR("PGBADMINIST01"), 
    PERITO_COORDINADOR("PGBCOORDINA01"),
    
    //agregado por mantis 72068
    USUARIO_PROCEDATOS("PGBPROCEDATOS"),
	USUARIO_CONSULTOR("PGBCONSULTA01");
	
	
	

    /**
     * Identificador del perfil en el servicio de FCA.
     */
	private final String valor;

	private TipoPerfil(String valor) {
		this.valor = valor;
	}
	
	/*
	 * getters
	 */
	public String getValor() {
		return this.valor;
	}
}
