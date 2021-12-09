package com.segurosbolivar.avaluos.modelo.service.impl;

 
public class Nomenclatura {

	private String nombre;
	private String nomenclatura;
	private String tipo;
	private int orden;

	public Nomenclatura(String nombre, String nomenclatura, String tipo, int orden) {
		this.nombre = nombre;
		this.nomenclatura = nomenclatura;
		this.tipo = tipo;
		this.orden = orden;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Nomenclatura) {
			return ((Nomenclatura)obj).getNomenclatura().toUpperCase().trim().equals(nomenclatura.toUpperCase().trim());
		}
		return super.equals(obj);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNomenclatura() {
		return nomenclatura;
	}

	public void setNomenclatura(String nomenclatura) {
		this.nomenclatura = nomenclatura;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
