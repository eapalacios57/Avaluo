package com.segurosbolivar.avaluos.planificador.modelo.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

/**
 * DAO que contiene la información de la entidad ActividadPecuariaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author GeneradorCRUD
 */
@XmlRootElement
public class ActividadPecuariaDTO implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal actividadPecuariaId;


	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public ActividadPecuariaDTO(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	
	@XmlElement(name="actividadPecuariaId")
	public BigDecimal getActividadPecuariaId(){
		return this.actividadPecuariaId;
	}
	
	@XmlElement(name="actividadPecuariaId")
	public void setActividadPecuariaId(BigDecimal actividadPecuariaId){
		this.actividadPecuariaId = actividadPecuariaId;
	}
	
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.actividadPecuariaId);        
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ActividadPecuariaDTO que se pasa
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
        final ActividadPecuariaDTO other = (ActividadPecuariaDTO) obj;
                
        
        return Objects.equals(this.actividadPecuariaId, other.actividadPecuariaId);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

