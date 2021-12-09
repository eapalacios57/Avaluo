package com.segurosbolivar.avaluos.planificador.modelo.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Objects;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * DAO que contiene la información de la entidad CiudadDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author GeneradorCRUD
 */
@XmlRootElement
public class CiudadDTO implements Serializable{	

	private BigDecimal idCiudad;

	private BigDecimal idDepartamento;
	
	private String ciudad;
	
	private BigDecimal codDane;
	
	private String usuarioCreacion;
	
	private Date fechaCreacion;
	
	private String usuarioTransaccion;
	
	private Date fechaTransaccion;
	
	private BigDecimal codAsobancaria;
	
	private String logitud;
	
	private String latitud;
	
	private BigDecimal capital;
	

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public CiudadDTO(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	
	@XmlElement(name="idCiudad")
	public BigDecimal getIdCiudad(){
		return this.idCiudad;
	}
	
	@XmlElement(name="idCiudad")
	public void setIdCiudad(BigDecimal idCiudad){
		this.idCiudad = idCiudad;
	}
	
	@XmlElement(name="idDepartamento")
	public BigDecimal getIdDepartamento(){
		return this.idDepartamento;
	}
	
	@XmlElement(name="idDepartamento")
	public void setIdDepartamento(BigDecimal idDepartamento){
		this.idDepartamento = idDepartamento;
	}
		
	@XmlElement(name="ciudad")
	public String getCiudad(){
		return this.ciudad;
	}
	
	@XmlElement(name="ciudad")
	public void setCiudad(String ciudad){
		this.ciudad = ciudad;
	}
		
	@XmlElement(name="codDane")
	public BigDecimal getCodDane(){
		return this.codDane;
	}
	
	@XmlElement(name="codDane")
	public void setCodDane(BigDecimal codDane){
		this.codDane = codDane;
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
		
	@XmlElement(name="codAsobancaria")
	public BigDecimal getCodAsobancaria(){
		return this.codAsobancaria;
	}
	
	@XmlElement(name="codAsobancaria")
	public void setCodAsobancaria(BigDecimal codAsobancaria){
		this.codAsobancaria = codAsobancaria;
	}
		
	@XmlElement(name="logitud")
	public String getLogitud(){
		return this.logitud;
	}
	
	@XmlElement(name="logitud")
	public void setLogitud(String logitud){
		this.logitud = logitud;
	}
		
	@XmlElement(name="latitud")
	public String getLatitud(){
		return this.latitud;
	}
	
	@XmlElement(name="latitud")
	public void setLatitud(String latitud){
		this.latitud = latitud;
	}
		
	@XmlElement(name="capital")
	public BigDecimal getCapital(){
		return this.capital;
	}
	
	@XmlElement(name="capital")
	public void setCapital(BigDecimal capital){
		this.capital = capital;
	}
		
	
    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idCiudad);        
        hash = 37 * hash + Objects.hashCode(this.idDepartamento);
        hash = 37 * hash + Objects.hashCode(this.ciudad);
        hash = 37 * hash + Objects.hashCode(this.codDane);
        hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
        hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
        hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
        hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
        hash = 37 * hash + Objects.hashCode(this.codAsobancaria);
        hash = 37 * hash + Objects.hashCode(this.logitud);
        hash = 37 * hash + Objects.hashCode(this.latitud);
        hash = 37 * hash + Objects.hashCode(this.capital);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad CiudadDTO que se pasa
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
        final CiudadDTO other = (CiudadDTO) obj;
                
        if (!Objects.equals(this.idCiudad, other.idCiudad)) {
            return false;
        }
        
        if (!Objects.equals(this.idDepartamento, other.idDepartamento)) {
            return false;
        }
        
        if (!Objects.equals(this.ciudad, other.ciudad)) {
            return false;
        }
        
        if (!Objects.equals(this.codDane, other.codDane)) {
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
        
        if (!Objects.equals(this.fechaTransaccion, other.fechaTransaccion)) {
            return false;
        }
        
        if (!Objects.equals(this.codAsobancaria, other.codAsobancaria)) {
            return false;
        }
        
        if (!Objects.equals(this.logitud, other.logitud)) {
            return false;
        }
        
        if (!Objects.equals(this.latitud, other.latitud)) {
            return false;
        }
        
        return Objects.equals(this.capital, other.capital);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

