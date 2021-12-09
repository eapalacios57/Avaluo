package com.segurosbolivar.avaluos.planificador.modelo.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Objects;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

/**
 * DAO que contiene la información de la entidad UnidadDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author GeneradorCRUD
 */
@XmlRootElement
public class UnidadDTO implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal idUnidad;

	private String nombre;
	
	private BigDecimal factorHa;
	
	private String usuarioCreacion;
	
	private Date fechaCreacion;
	
	private String usuarioTransaccion;
	
	private Date fechaTransaccion;
	

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public UnidadDTO(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	
	@XmlElement(name="idUnidad")
	public BigDecimal getIdUnidad(){
		return this.idUnidad;
	}
	
	@XmlElement(name="idUnidad")
	public void setIdUnidad(BigDecimal idUnidad){
		this.idUnidad = idUnidad;
	}
	
	@XmlElement(name="nombre")
	public String getNombre(){
		return this.nombre;
	}
	
	@XmlElement(name="nombre")
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	@XmlElement(name="factorHa")
	public BigDecimal getFactorHa(){
		return this.factorHa;
	}
	
	@XmlElement(name="factorHa")
	public void setFactorHa(BigDecimal factorHa){
		this.factorHa = factorHa;
	}
		
	@XmlElement(name="usuarioCreacion")
	public String getUsuarioCreacion(){
		return this.usuarioCreacion;
	}
	
	@XmlElement(name="usuarioCreacion")
	public void setUsuarioCreacion(String usuarioCreacion){
		this.usuarioCreacion = usuarioCreacion;
	}
		
	@XmlElement(name="fechaCreacion")
	public Date getFechaCreacion(){
		return this.fechaCreacion;
	}
	
	@XmlElement(name="fechaCreacion")
	public void setFechaCreacion(Date fechaCreacion){
		this.fechaCreacion = fechaCreacion;
	}
		
	@XmlElement(name="usuarioTransaccion")
	public String getUsuarioTransaccion(){
		return this.usuarioTransaccion;
	}
	
	@XmlElement(name="usuarioTransaccion")
	public void setUsuarioTransaccion(String usuarioTransaccion){
		this.usuarioTransaccion = usuarioTransaccion;
	}
		
	@XmlElement(name="fechaTransaccion")
	public Date getFechaTransaccion(){
		return this.fechaTransaccion;
	}
	
	@XmlElement(name="fechaTransaccion")
	public void setFechaTransaccion(Date fechaTransaccion){
		this.fechaTransaccion = fechaTransaccion;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idUnidad);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.factorHa);
        hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
        hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
        hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
        hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad UnidadDTO que se pasa
     * como parámetro comprobando que comparten los mismos valores en cada uno
     * de sus atributos. Solo se tienen en cuenta los atributos simples, es
     * decir, se omiten aquellos que definen una relación con otra tabla.
     *
     * @param obj Instancia de la categoría a comprobar
     * iguales.
     * @return Verdadero si esta instancia y la que se pasan como parámetros son
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UnidadDTO other = (UnidadDTO) obj;
                
        if (!Objects.equals(this.idUnidad, other.idUnidad)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        
        if (!Objects.equals(this.factorHa, other.factorHa)) {
            return false;
        }
        
        if (!Objects.equals(this.usuarioCreacion, other.usuarioCreacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaCreacion, other.fechaCreacion)) {
            return false;
        }
        
        if (!Objects.equals(this.usuarioTransaccion, other.usuarioTransaccion)) {
            return false;
        }
        
        return Objects.equals(this.fechaTransaccion, other.fechaTransaccion);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

