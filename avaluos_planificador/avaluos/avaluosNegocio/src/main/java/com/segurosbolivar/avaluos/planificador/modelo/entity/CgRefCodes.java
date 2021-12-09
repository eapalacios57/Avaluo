package com.segurosbolivar.avaluos.planificador.modelo.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="CG_REF_CODES")
@NamedQuery(name = "CgRefCodes.findAll", query = "SELECT p FROM CgRefCodes p")
public class CgRefCodes implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_CG_REF_CODES_PK = "idCgRefCodes";
	public static final String ENTIDAD_CG_REF_CODES_RV_DOMAIN = "rvDomain";
	public static final String ENTIDAD_CG_REF_CODES_RV_LOW_VALUE = "rvLowValue";
	public static final String ENTIDAD_CG_REF_CODES_RV_HIGH_VALUE = "rvHighValue";
	public static final String ENTIDAD_CG_REF_CODES_RV_ABBREVIATION = "rvAbbreviation";
	public static final String ENTIDAD_CG_REF_CODES_RV_MEANING = "rvMeaning";
	public static final String ENTIDAD_CG_REF_CODES_RV_CREATE_BY = "rvCreateBy";
    private static final String[] ATRIBUTOS_ENTIDAD_CG_REF_CODES
            = {ENTIDAD_CG_REF_CODES_RV_HIGH_VALUE, ENTIDAD_CG_REF_CODES_RV_DOMAIN, ENTIDAD_CG_REF_CODES_RV_MEANING, ENTIDAD_CG_REF_CODES_RV_ABBREVIATION, ENTIDAD_CG_REF_CODES_RV_CREATE_BY, ENTIDAD_CG_REF_CODES_PK, ENTIDAD_CG_REF_CODES_RV_LOW_VALUE};

	@Id
    @Column(name="ID_CG_REF_CODES")
	private BigDecimal idCgRefCodes;

	@Column(name="RV_DOMAIN")
	@Size(min=0, max= 100)
	private String rvDomain;
	
	@Column(name="RV_LOW_VALUE")
	@Size(min=0, max= 240)
	private String rvLowValue;
	
	@Column(name="RV_HIGH_VALUE")
	@Size(min=0, max= 240)
	private String rvHighValue;
	
	@Column(name="RV_ABBREVIATION")
	@Size(min=0, max= 240)
	private String rvAbbreviation;
	
	@Column(name="RV_MEANING")
	@Size(min=0, max= 240)
	private String rvMeaning;
	
	@Column(name="RV_CREATE_BY")
	@Size(min=0, max= 25)
	private String rvCreateBy;
	


	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public CgRefCodes(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	public BigDecimal getIdCgRefCodes(){
		return this.idCgRefCodes;
	}
	
	public void setIdCgRefCodes(BigDecimal idCgRefCodes){
	
		this.idCgRefCodes = idCgRefCodes;
	}
	
	public String getRvDomain(){
		return this.rvDomain;
	}
	
	public void setRvDomain(String rvDomain){
	
		this.rvDomain = rvDomain;
	}
		
	public String getRvLowValue(){
		return this.rvLowValue;
	}
	
	public void setRvLowValue(String rvLowValue){
	
		this.rvLowValue = rvLowValue;
	}
		
	public String getRvHighValue(){
		return this.rvHighValue;
	}
	
	public void setRvHighValue(String rvHighValue){
	
		this.rvHighValue = rvHighValue;
	}
		
	public String getRvAbbreviation(){
		return this.rvAbbreviation;
	}
	
	public void setRvAbbreviation(String rvAbbreviation){
	
		this.rvAbbreviation = rvAbbreviation;
	}
		
	public String getRvMeaning(){
		return this.rvMeaning;
	}
	
	public void setRvMeaning(String rvMeaning){
	
		this.rvMeaning = rvMeaning;
	}
		
	public String getRvCreateBy(){
		return this.rvCreateBy;
	}
	
	public void setRvCreateBy(String rvCreateBy){
	
		this.rvCreateBy = rvCreateBy;
	}
		


	/**
     * Verifica si la entidad contiene el atributo que se pasa como parámetro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_CG_REF_CODES) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
    }
    
    public static String[] getAtributosEntidadCgRefCodes() {
		return ATRIBUTOS_ENTIDAD_CG_REF_CODES;
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
     * Valida la igualdad de la instancia de la entidad CgRefCodes que se pasa
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
        final CgRefCodes other = (CgRefCodes) obj;
        
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

