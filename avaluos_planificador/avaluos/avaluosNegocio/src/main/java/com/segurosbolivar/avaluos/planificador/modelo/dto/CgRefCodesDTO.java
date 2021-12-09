package com.segurosbolivar.avaluos.planificador.modelo.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Objects;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

/**
 * DAO que contiene la información de la entidad CgRefCodesDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author GeneradorCRUD
 */
@XmlRootElement
public class CgRefCodesDTO implements Serializable{	

	private BigDecimal idCgRefCodes;

	private String rvDomain;
	
	private String rvLowValue;
	
	private String rvHighValue;
	
	private String rvAbbreviation;
	
	private String rvMeaning;
	
	private String rvCreateBy;
	

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public CgRefCodesDTO(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	
	@XmlElement(name="idCgRefCodes")
	public BigDecimal getIdCgRefCodes(){
		return this.idCgRefCodes;
	}
	
	@XmlElement(name="idCgRefCodes")
	public void setIdCgRefCodes(BigDecimal idCgRefCodes){
		this.idCgRefCodes = idCgRefCodes;
	}
	
	@XmlElement(name="rvDomain")
	public String getRvDomain(){
		return this.rvDomain;
	}
	
	@XmlElement(name="rvDomain")
	public void setRvDomain(String rvDomain){
		this.rvDomain = rvDomain;
	}
		
	@XmlElement(name="rvLowValue")
	public String getRvLowValue(){
		return this.rvLowValue;
	}
	
	@XmlElement(name="rvLowValue")
	public void setRvLowValue(String rvLowValue){
		this.rvLowValue = rvLowValue;
	}
		
	@XmlElement(name="rvHighValue")
	public String getRvHighValue(){
		return this.rvHighValue;
	}
	
	@XmlElement(name="rvHighValue")
	public void setRvHighValue(String rvHighValue){
		this.rvHighValue = rvHighValue;
	}
		
	@XmlElement(name="rvAbbreviation")
	public String getRvAbbreviation(){
		return this.rvAbbreviation;
	}
	
	@XmlElement(name="rvAbbreviation")
	public void setRvAbbreviation(String rvAbbreviation){
		this.rvAbbreviation = rvAbbreviation;
	}
		
	@XmlElement(name="rvMeaning")
	public String getRvMeaning(){
		return this.rvMeaning;
	}
	
	@XmlElement(name="rvMeaning")
	public void setRvMeaning(String rvMeaning){
		this.rvMeaning = rvMeaning;
	}
		
	@XmlElement(name="rvCreateBy")
	public String getRvCreateBy(){
		return this.rvCreateBy;
	}
	
	@XmlElement(name="rvCreateBy")
	public void setRvCreateBy(String rvCreateBy){
		this.rvCreateBy = rvCreateBy;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idCgRefCodes);        
        hash = 37 * hash + Objects.hashCode(this.rvDomain);
        hash = 37 * hash + Objects.hashCode(this.rvLowValue);
        hash = 37 * hash + Objects.hashCode(this.rvHighValue);
        hash = 37 * hash + Objects.hashCode(this.rvAbbreviation);
        hash = 37 * hash + Objects.hashCode(this.rvMeaning);
        hash = 37 * hash + Objects.hashCode(this.rvCreateBy);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad CgRefCodesDTO que se pasa
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
        final CgRefCodesDTO other = (CgRefCodesDTO) obj;
                
        if (!Objects.equals(this.idCgRefCodes, other.idCgRefCodes)) {
            return false;
        }
        
        if (!Objects.equals(this.rvDomain, other.rvDomain)) {
            return false;
        }
        
        if (!Objects.equals(this.rvLowValue, other.rvLowValue)) {
            return false;
        }
        
        if (!Objects.equals(this.rvHighValue, other.rvHighValue)) {
            return false;
        }
        
        if (!Objects.equals(this.rvAbbreviation, other.rvAbbreviation)) {
            return false;
        }
        
        if (!Objects.equals(this.rvMeaning, other.rvMeaning)) {
            return false;
        }
        
        return Objects.equals(this.rvCreateBy, other.rvCreateBy);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

