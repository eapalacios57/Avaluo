package com.segurosbolivar.avaluos.modelo.ws.dto.fca;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Transaccion;

@XmlRootElement(name = "Modulo")
@XmlAccessorType(XmlAccessType.FIELD)
public class Modulo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4360081142183854184L;
	@XmlAttribute(name="label")
	private String label;
	@XmlElement(name = "Modulo")
	private List<Modulo> modulos;
	@XmlElement(name = "Transaccion")
	private List<Transaccion> transacciones;

	public Modulo(String label, List<Transaccion> transacciones) {
		super();
		this.label = label;
		this.transacciones = transacciones;
	}

	public List<Modulo> getModulos() {
	    return modulos;
	}

	public void setModulos(List<Modulo> modulos) {
	    this.modulos = modulos;
	}

	public Modulo() {
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Transaccion> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(List<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}

	@Override
	public String toString() {
		return "Modulo [label=" + label + ", transacciones=" + transacciones + "]";
	}

}
