package com.segurosbolivar.avaluos.planificador.modelo.dto;

import javax.xml.bind.annotation.XmlRootElement;


import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;

/**
 * DAO que contiene la información de la entidad ParametroDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author GeneradorCRUD
 */
@XmlRootElement
public class ParametroDTO implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5192700528729325495L;

	private long idParametro;

	private String nombre;
	
	private String usuarioCreacion;
	
	private Date fechaCreacion;
	
	private String usuarioTransaccion;
	
	private Date fechaTransaccion;
	
	private String entidad;
	
	private String campo;
	
	private List<ParametroValorDTO> parametroValores;
	

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public ParametroDTO(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	
	@XmlElement(name="idParametro")
	public long getIdParametro(){
		return this.idParametro;
	}
	
	@XmlElement(name="idParametro")
	public void setIdParametro(long idParametro){
		this.idParametro = idParametro;
	}
	
	@XmlElement(name="nombre")
	public String getNombre(){
		return this.nombre;
	}
	
	@XmlElement(name="nombre")
	public void setNombre(String nombre){
		this.nombre = nombre;
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
		
	@XmlElement(name="entidad")
	public String getEntidad(){
		return this.entidad;
	}
	
	@XmlElement(name="entidad")
	public void setEntidad(String entidad){
		this.entidad = entidad;
	}
		
	@XmlElement(name="campo")
	public String getCampo(){
		return this.campo;
	}
	
	@XmlElement(name="campo")
	public void setCampo(String campo){
		this.campo = campo;
	}
		
	@XmlElement(name="parametroValores")
    public List<ParametroValorDTO> getParametroValores() {
		return parametroValores;
	}

	@XmlElement(name="parametroValores")
	public void setParametroValores(List<ParametroValorDTO> parametroValores) {
		this.parametroValores = parametroValores;
	}



	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idParametro);        
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
        hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
        hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
        hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
        hash = 37 * hash + Objects.hashCode(this.entidad);
        hash = 37 * hash + Objects.hashCode(this.campo);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ParametroDTO que se pasa
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
        final ParametroDTO other = (ParametroDTO) obj;
                
        if (!Objects.equals(this.idParametro, other.idParametro)) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
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
        
        if (!Objects.equals(this.entidad, other.entidad)) {
            return false;
        }
        
        return Objects.equals(this.campo, other.campo);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

