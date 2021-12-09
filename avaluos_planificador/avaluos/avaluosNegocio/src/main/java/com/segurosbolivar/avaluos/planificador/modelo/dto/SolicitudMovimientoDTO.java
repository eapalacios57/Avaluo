package com.segurosbolivar.avaluos.planificador.modelo.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.segurosbolivar.avaluos.planificador.modelo.entity.Solicitud;

import java.util.Date;
import java.util.Objects;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;

/**
 * DAO que contiene la información de la entidad SolicitudMovimientoDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author GeneradorCRUD
 */
@XmlRootElement
public class SolicitudMovimientoDTO implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long	 idSolicitudMovimiento;

	private Date fechaMovimiento;
	
	private String fechaMovimientoStr;
	
	private String estadoMovimiento;
	
	private Solicitud solicitud;
	
	private String usuarioMovimiento;
	

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public SolicitudMovimientoDTO(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	
	@XmlElement(name="idSolicitudMovimiento")
	public long getIdSolicitudMovimiento(){
		return this.idSolicitudMovimiento;
	}
	
	@XmlElement(name="idSolicitudMovimiento")
	public void setIdSolicitudMovimiento(long idSolicitudMovimiento){
		this.idSolicitudMovimiento = idSolicitudMovimiento;
	}
	
	@XmlElement(name="fechaMovimiento")
	public Date getFechaMovimiento(){
		return this.fechaMovimiento;
	}
	
	@XmlElement(name="fechaMovimiento")
	public void setFechaMovimiento(Date fechaMovimiento){
		this.fechaMovimiento = fechaMovimiento;
	}
		
	@XmlElement(name="estadoMovimiento")
	public String getEstadoMovimiento(){
		return this.estadoMovimiento;
	}
	
	@XmlElement(name="estadoMovimiento")
	public void setEstadoMovimiento(String estadoMovimiento){
		this.estadoMovimiento = estadoMovimiento;
	}
		
	@XmlElement(name="solicitud")
	public Solicitud getSolicitud(){
		return this.solicitud;
	}
	
	@XmlElement(name="solicitud")
	public void setSolicitud(Solicitud solicitud){
		this.solicitud = solicitud;
	}
		
	@XmlElement(name="usuarioMovimiento")
	public String getUsuarioMovimiento(){
		return this.usuarioMovimiento;
	}
	
	@XmlElement(name="usuarioMovimiento")
	public void setUsuarioMovimiento(String usuarioMovimiento){
		this.usuarioMovimiento = usuarioMovimiento;
	}
		
	@XmlElement(name="fechaMovimientoStr")
    public String getFechaMovimientoStr() {
		return fechaMovimientoStr;
	}


	@XmlElement(name="fechaMovimientoStr")
	public void setFechaMovimientoStr(String fechaMovimientoStr) {
		this.fechaMovimientoStr = fechaMovimientoStr;
	}

	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idSolicitudMovimiento);        
        hash = 37 * hash + Objects.hashCode(this.fechaMovimiento);
        hash = 37 * hash + Objects.hashCode(this.estadoMovimiento);
        hash = 37 * hash + Objects.hashCode(this.solicitud);
        hash = 37 * hash + Objects.hashCode(this.usuarioMovimiento);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad SolicitudMovimientoDTO que se pasa
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
        final SolicitudMovimientoDTO other = (SolicitudMovimientoDTO) obj;
                
        if (!Objects.equals(this.idSolicitudMovimiento, other.idSolicitudMovimiento)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaMovimiento, other.fechaMovimiento)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoMovimiento, other.estadoMovimiento)) {
            return false;
        }
        
        if (!Objects.equals(this.solicitud, other.solicitud)) {
            return false;
        }
        
        return Objects.equals(this.usuarioMovimiento, other.usuarioMovimiento);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

