package com.segurosbolivar.avaluos.modelo.ws.dto.fca;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Modulo;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Transaccion;

@XmlRootElement(name="Modulos")
@XmlAccessorType(XmlAccessType.FIELD)
public class Modulos implements Serializable{

	private static final long serialVersionUID = -7315210913148076891L;
	@XmlElement(name="Modulo")
	private List<Modulo> modulo;
	@XmlElement(name="Transaccion")
	private List<Transaccion> transaccion;

	public Modulos(List<Modulo> modulo, List<Transaccion> transaccion) {
		super();
		this.modulo = modulo;
		this.transaccion = transaccion;
	}

	public Modulos(){}


	public List<Modulo> getModulo() {
		return modulo;
	}

	public void setModulo(List<Modulo> modulo) {
		this.modulo = modulo;
	}


	public List<Transaccion> getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(List<Transaccion> transaccion) {
		this.transaccion = transaccion;
	}

}
