package com.segurosbolivar.avaluos.planificador.modelo.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.segurosbolivar.avaluos.planificador.modelo.entity.BeneficiarioPK;

import java.util.Date;
import java.util.Objects;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;


/**
 * DAO que contiene la información de la entidad BeneficiarioDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author GeneradorCRUD
 */
@XmlRootElement
public class BeneficiarioDTO implements Serializable{	

	private BeneficiarioPK id;

	private String primerNombre;
	
	private String segundoNombre;
	
	private String primerApellido;
	
	private String segundoApellido;
	
	private String codigoDivipola;
	

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public BeneficiarioDTO(){
		id = new BeneficiarioPK();
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	@XmlElement(name="id")
	public BeneficiarioPK getId(){
		return this.id;
	}
	
	@XmlElement(name="id")
	public void setId(BeneficiarioPK id){
		this.id   = id ;
	}  
	
	@XmlElement(name="primerNombre")
	public String getPrimerNombre(){
		return this.primerNombre;
	}
	
	@XmlElement(name="primerNombre")
	public void setPrimerNombre(String primerNombre){
		this.primerNombre = primerNombre;
	}
		
	@XmlElement(name="segundoNombre")
	public String getSegundoNombre(){
		return this.segundoNombre;
	}
	
	@XmlElement(name="segundoNombre")
	public void setSegundoNombre(String segundoNombre){
		this.segundoNombre = segundoNombre;
	}
		
	@XmlElement(name="primerApellido")
	public String getPrimerApellido(){
		return this.primerApellido;
	}
	
	@XmlElement(name="primerApellido")
	public void setPrimerApellido(String primerApellido){
		this.primerApellido = primerApellido;
	}
		
	@XmlElement(name="segundoApellido")
	public String getSegundoApellido(){
		return this.segundoApellido;
	}
	
	@XmlElement(name="segundoApellido")
	public void setSegundoApellido(String segundoApellido){
		this.segundoApellido = segundoApellido;
	}
		
	@XmlElement(name="codigoDivipola")
	public String getCodigoDivipola(){
		return this.codigoDivipola;
	}
	
	@XmlElement(name="codigoDivipola")
	public void setCodigoDivipola(String codigoDivipola){
		this.codigoDivipola = codigoDivipola;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.id);        
        hash = 37 * hash + Objects.hashCode(this.primerNombre);
        hash = 37 * hash + Objects.hashCode(this.segundoNombre);
        hash = 37 * hash + Objects.hashCode(this.primerApellido);
        hash = 37 * hash + Objects.hashCode(this.segundoApellido);
        hash = 37 * hash + Objects.hashCode(this.codigoDivipola);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad BeneficiarioDTO que se pasa
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
        final BeneficiarioDTO other = (BeneficiarioDTO) obj;
                
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        
        if (!Objects.equals(this.primerNombre, other.primerNombre)) {
            return false;
        }
        
        if (!Objects.equals(this.segundoNombre, other.segundoNombre)) {
            return false;
        }
        
        if (!Objects.equals(this.primerApellido, other.primerApellido)) {
            return false;
        }
        
        if (!Objects.equals(this.segundoApellido, other.segundoApellido)) {
            return false;
        }
        
        return Objects.equals(this.codigoDivipola, other.codigoDivipola);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

