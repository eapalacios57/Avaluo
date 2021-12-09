package com.segurosbolivar.avaluos.modelo.cons;

public enum TipoParametro {
	NOTIFICACIONES("notificacion");
	
	private final String valor;

	private TipoParametro(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return this.valor;
	    }
}
