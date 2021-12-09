package com.segurosbolivar.avaluos.planificador.modelo.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.segurosbolivar.avaluos.planificador.modelo.entity.ValorPorcentajePK;

import java.util.Date;
import java.util.Objects;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;

import java.math.BigDecimal;

/**
 * DAO que contiene la información de la entidad ValorPorcentajeDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author GeneradorCRUD
 */
@XmlRootElement
public class ValorPorcentajeDTO implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ValorPorcentajePK valorPorcentajePK;

	private BigDecimal porcentaje;
	
	private String usuarioCreacion;
	
	private Date fechaCreacion;
	
	private String usuarioTransaccion;
	
	private Date fechaTransaccion;
	

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public ValorPorcentajeDTO(){
		valorPorcentajePK = new ValorPorcentajePK();
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	@XmlElement(name="valorPorcentajePK")
	public ValorPorcentajePK getValorPorcentajePK(){
		return this.valorPorcentajePK;
	}
	
	@XmlElement(name="valorPorcentajePK")
	public void setValorPorcentajePK(ValorPorcentajePK valorPorcentajePK){
		this.valorPorcentajePK   = valorPorcentajePK ;
	}  
	
	@XmlElement(name="porcentaje")
	public BigDecimal getPorcentaje(){
		return this.porcentaje;
	}
	
	@XmlElement(name="porcentaje")
	public void setPorcentaje(BigDecimal porcentaje){
		this.porcentaje = porcentaje;
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
        
        hash = 37 * hash + Objects.hashCode(this.valorPorcentajePK);        
        hash = 37 * hash + Objects.hashCode(this.porcentaje);
        hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
        hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
        hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
        hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ValorPorcentajeDTO que se pasa
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
        final ValorPorcentajeDTO other = (ValorPorcentajeDTO) obj;
                
        if (!Objects.equals(this.valorPorcentajePK, other.valorPorcentajePK)) {
            return false;
        }
        
        if (!Objects.equals(this.porcentaje, other.porcentaje)) {
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

