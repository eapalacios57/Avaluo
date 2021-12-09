package com.segurosbolivar.avaluos.modelo.ws.dto.fca;



import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Transaccion")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaccion implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 2285924397979471491L;
	/**
	 * 
	 */
	@XmlAttribute(name="label")
	private String label;
	@XmlAttribute(name="idTarea")
	private String idTarea;
	@XmlAttribute(name="transaccion")
	private String transaccion;
	@XmlAttribute(name="idTareaPadre")
	private String idTareaPadre;
	@XmlAttribute(name="orderMenu")
	private String ordenMenu;
	@XmlAttribute(name="aplicacion")
	private String aplicacion;
	@XmlAttribute(name="codigoTarea")
	private String codigoTarea;
	@XmlAttribute(name="estado")
	private String estado;
	@XmlAttribute(name="breadcrumb")
	private String breadcrumb;
	@XmlAttribute(name="clasificacion")
	private String clasificacion;
	@XmlAttribute(name="actualiza")
	private String actualiza;
	@XmlAttribute(name="adiciona")
	private String adiciona;
	@XmlAttribute(name="consulta")
	private String consulta;
	@XmlAttribute(name="ejecuta")
	private String ejecuta;
	@XmlAttribute(name="elimina")
	private String elimina;
	@XmlAttribute(name="imprime")
	private String imprime;

	public Transaccion() {
	}

	/**
	 * 
	 * @param label
	 * @param idTarea
	 * @param transaccion
	 * @param idTareaPadre
	 * @param ordenMenu
	 * @param aplicacion
	 * @param codigoTarea
	 * @param estado
	 */
	public Transaccion(String label, String idTarea, String transaccion, String idTareaPadre, String ordenMenu,
			String aplicacion, String codigoTarea, String estado) {
		super();
		this.label = label;
		this.idTarea = idTarea;
		this.transaccion = transaccion;
		this.idTareaPadre = idTareaPadre;
		this.ordenMenu = ordenMenu;
		this.aplicacion = aplicacion;
		this.codigoTarea = codigoTarea;
		this.estado = estado;
	}
	
	

	public String getBreadcrumb() {
	    return breadcrumb;
	}

	public void setBreadcrumb(String breadcrumb) {
	    this.breadcrumb = breadcrumb;
	}

	public String getClasificacion() {
	    return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
	    this.clasificacion = clasificacion;
	}

	public String getActualiza() {
	    return actualiza;
	}

	public void setActualiza(String actualiza) {
	    this.actualiza = actualiza;
	}

	public String getAdiciona() {
	    return adiciona;
	}

	public void setAdiciona(String adiciona) {
	    this.adiciona = adiciona;
	}

	public String getConsulta() {
	    return consulta;
	}

	public void setConsulta(String consulta) {
	    this.consulta = consulta;
	}

	public String getEjecuta() {
	    return ejecuta;
	}

	public void setEjecuta(String ejecuta) {
	    this.ejecuta = ejecuta;
	}

	public String getElimina() {
	    return elimina;
	}

	public void setElimina(String elimina) {
	    this.elimina = elimina;
	}

	public String getImprime() {
	    return imprime;
	}

	public void setImprime(String imprime) {
	    this.imprime = imprime;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIdTarea() {
		return idTarea;
	}

	public void setIdTarea(String idTarea) {
		this.idTarea = idTarea;
	}

	public String getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}

	public String getIdTareaPadre() {
		return idTareaPadre;
	}

	public void setIdTareaPadre(String idTareaPadre) {
		this.idTareaPadre = idTareaPadre;
	}

	public String getOrdenMenu() {
		return ordenMenu;
	}

	public void setOrdenMenu(String ordenMenu) {
		this.ordenMenu = ordenMenu;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getCodigoTarea() {
		return codigoTarea;
	}

	public void setCodigoTarea(String codigoTarea) {
		this.codigoTarea = codigoTarea;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Transaccion [label=" + label + ", idTarea=" + idTarea + ", transaccion=" + transaccion
				+ ", idTareaPadre=" + idTareaPadre + ", ordenMenu=" + ordenMenu + ", aplicacion=" + aplicacion
				+ ", codigoTarea=" + codigoTarea + ", estado=" + estado + "]";
	}



}
