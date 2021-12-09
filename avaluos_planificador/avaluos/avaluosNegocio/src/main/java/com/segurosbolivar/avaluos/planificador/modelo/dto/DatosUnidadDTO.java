package com.segurosbolivar.avaluos.planificador.modelo.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

/**
 * DAO que contiene la información de la entidad DatosUnidadDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author GeneradorCRUD
 */
@XmlRootElement
public class DatosUnidadDTO implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal idDatosUnidad;

	private BigDecimal idUnidadProductiva;
	
	private BigDecimal idCultivo;
	
	private BigDecimal idParametro;
	
	private String valorParametro;
	
	private BigDecimal porcentaje;
	

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public DatosUnidadDTO(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	
	@XmlElement(name="idDatosUnidad")
	public BigDecimal getIdDatosUnidad(){
		return this.idDatosUnidad;
	}
	
	@XmlElement(name="idDatosUnidad")
	public void setIdDatosUnidad(BigDecimal idDatosUnidad){
		this.idDatosUnidad = idDatosUnidad;
	}
	
	@XmlElement(name="idUnidadProductiva")
	public BigDecimal getIdUnidadProductiva(){
		return this.idUnidadProductiva;
	}
	
	@XmlElement(name="idUnidadProductiva")
	public void setIdUnidadProductiva(BigDecimal idUnidadProductiva){
		this.idUnidadProductiva = idUnidadProductiva;
	}
		
	@XmlElement(name="idCultivo")
	public BigDecimal getIdCultivo(){
		return this.idCultivo;
	}
	
	@XmlElement(name="idCultivo")
	public void setIdCultivo(BigDecimal idCultivo){
		this.idCultivo = idCultivo;
	}
		
	@XmlElement(name="idParametro")
	public BigDecimal getIdParametro(){
		return this.idParametro;
	}
	
	@XmlElement(name="idParametro")
	public void setIdParametro(BigDecimal idParametro){
		this.idParametro = idParametro;
	}
		
	@XmlElement(name="valorParametro")
	public String getValorParametro(){
		return this.valorParametro;
	}
	
	@XmlElement(name="valorParametro")
	public void setValorParametro(String valorParametro){
		this.valorParametro = valorParametro;
	}
		
	@XmlElement(name="porcentaje")
	public BigDecimal getPorcentaje(){
		return this.porcentaje;
	}
	
	@XmlElement(name="porcentaje")
	public void setPorcentaje(BigDecimal porcentaje){
		this.porcentaje = porcentaje;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idDatosUnidad);        
        hash = 37 * hash + Objects.hashCode(this.idUnidadProductiva);
        hash = 37 * hash + Objects.hashCode(this.idCultivo);
        hash = 37 * hash + Objects.hashCode(this.idParametro);
        hash = 37 * hash + Objects.hashCode(this.valorParametro);
        hash = 37 * hash + Objects.hashCode(this.porcentaje);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad DatosUnidadDTO que se pasa
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
        final DatosUnidadDTO other = (DatosUnidadDTO) obj;
                
        if (!Objects.equals(this.idDatosUnidad, other.idDatosUnidad)) {
            return false;
        }
        
        if (!Objects.equals(this.idUnidadProductiva, other.idUnidadProductiva)) {
            return false;
        }
        
        if (!Objects.equals(this.idCultivo, other.idCultivo)) {
            return false;
        }
        
        if (!Objects.equals(this.idParametro, other.idParametro)) {
            return false;
        }
        
        if (!Objects.equals(this.valorParametro, other.valorParametro)) {
            return false;
        }
        
        return Objects.equals(this.porcentaje, other.porcentaje);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

