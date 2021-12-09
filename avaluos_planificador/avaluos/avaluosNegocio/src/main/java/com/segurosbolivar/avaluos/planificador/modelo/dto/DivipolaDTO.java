package com.segurosbolivar.avaluos.planificador.modelo.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;

/**
 * DAO que contiene la información de la entidad DivipolaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author GeneradorCRUD
 */
@XmlRootElement
public class DivipolaDTO implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoDivipola;

	private String nombre;
	

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public DivipolaDTO(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	
	@XmlElement(name="codigoDivipola")
	public String getCodigoDivipola(){
		return this.codigoDivipola;
	}
	
	@XmlElement(name="codigoDivipola")
	public void setCodigoDivipola(String codigoDivipola){
		this.codigoDivipola = codigoDivipola;
	}
	
	@XmlElement(name="nombre")
	public String getNombre(){
		return this.nombre;
	}
	
	@XmlElement(name="nombre")
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.codigoDivipola);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad DivipolaDTO que se pasa
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
        final DivipolaDTO other = (DivipolaDTO) obj;
                
        
        return Objects.equals(this.codigoDivipola, other.codigoDivipola);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

