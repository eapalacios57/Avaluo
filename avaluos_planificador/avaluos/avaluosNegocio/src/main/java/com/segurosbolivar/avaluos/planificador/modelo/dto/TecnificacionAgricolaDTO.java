package com.segurosbolivar.avaluos.planificador.modelo.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Objects;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;

/**
 * DAO que contiene la información de la entidad TecnificacionAgricolaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author GeneradorCRUD
 */
@XmlRootElement
public class TecnificacionAgricolaDTO implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idTecnificacionAgricola;

	private Long idUnidadProductiva;
	
	private String maquinariaDisponible;
	
	private String actividadMecanizadas;
	
	private String infraestructura;
	
	private String transportePropio;
	
	private String transporteAlquilado;
	
	private String tipoTransportePropio;
	
	private String usuarioCreacion;
	
	private Date fechaCreacion;
	
	private String usuarioTransaccion;
	
	private Date fechaTransaccion;
	

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public TecnificacionAgricolaDTO(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	
	@XmlElement(name="idTecnificacionAgricola")
	public Long getIdTecnificacionAgricola(){
		return this.idTecnificacionAgricola;
	}
	
	@XmlElement(name="idTecnificacionAgricola")
	public void setIdTecnificacionAgricola(Long idTecnificacionAgricola){
		this.idTecnificacionAgricola = idTecnificacionAgricola;
	}
	
	@XmlElement(name="idUnidadProductiva")
	public Long getIdUnidadProductiva(){
		return this.idUnidadProductiva;
	}
	
	@XmlElement(name="idUnidadProductiva")
	public void setIdUnidadProductiva(Long idUnidadProductiva){
		this.idUnidadProductiva = idUnidadProductiva;
	}
		
	@XmlElement(name="maquinariaDisponible")
	public String getMaquinariaDisponible(){
		return this.maquinariaDisponible;
	}
	
	@XmlElement(name="maquinariaDisponible")
	public void setMaquinariaDisponible(String maquinariaDisponible){
		this.maquinariaDisponible = maquinariaDisponible;
	}
		
	@XmlElement(name="actividadMecanizadas")
	public String getActividadMecanizadas(){
		return this.actividadMecanizadas;
	}
	
	@XmlElement(name="actividadMecanizadas")
	public void setActividadMecanizadas(String actividadMecanizadas){
		this.actividadMecanizadas = actividadMecanizadas;
	}
		
	@XmlElement(name="infraestructura")
	public String getInfraestructura(){
		return this.infraestructura;
	}
	
	@XmlElement(name="infraestructura")
	public void setInfraestructura(String infraestructura){
		this.infraestructura = infraestructura;
	}
		
	@XmlElement(name="transportePropio")
	public String getTransportePropio(){
		return this.transportePropio;
	}
	
	@XmlElement(name="transportePropio")
	public void setTransportePropio(String transportePropio){
		this.transportePropio = transportePropio;
	}
		
	@XmlElement(name="transporteAlquilado")
	public String getTransporteAlquilado(){
		return this.transporteAlquilado;
	}
	
	@XmlElement(name="transporteAlquilado")
	public void setTransporteAlquilado(String transporteAlquilado){
		this.transporteAlquilado = transporteAlquilado;
	}
		
	@XmlElement(name="tipoTransportePropio")
	public String getTipoTransportePropio(){
		return this.tipoTransportePropio;
	}
	
	@XmlElement(name="tipoTransportePropio")
	public void setTipoTransportePropio(String tipoTransportePropio){
		this.tipoTransportePropio = tipoTransportePropio;
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
        
        hash = 37 * hash + Objects.hashCode(this.idTecnificacionAgricola);        
        hash = 37 * hash + Objects.hashCode(this.idUnidadProductiva);
        hash = 37 * hash + Objects.hashCode(this.maquinariaDisponible);
        hash = 37 * hash + Objects.hashCode(this.actividadMecanizadas);
        hash = 37 * hash + Objects.hashCode(this.infraestructura);
        hash = 37 * hash + Objects.hashCode(this.transportePropio);
        hash = 37 * hash + Objects.hashCode(this.transporteAlquilado);
        hash = 37 * hash + Objects.hashCode(this.tipoTransportePropio);
        hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
        hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
        hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
        hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad TecnificacionAgricolaDTO que se pasa
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
        final TecnificacionAgricolaDTO other = (TecnificacionAgricolaDTO) obj;
                
        if (!Objects.equals(this.idTecnificacionAgricola, other.idTecnificacionAgricola)) {
            return false;
        }
        
        if (!Objects.equals(this.idUnidadProductiva, other.idUnidadProductiva)) {
            return false;
        }
        
        if (!Objects.equals(this.maquinariaDisponible, other.maquinariaDisponible)) {
            return false;
        }
        
        if (!Objects.equals(this.actividadMecanizadas, other.actividadMecanizadas)) {
            return false;
        }
        
        if (!Objects.equals(this.infraestructura, other.infraestructura)) {
            return false;
        }
        
        if (!Objects.equals(this.transportePropio, other.transportePropio)) {
            return false;
        }
        
        if (!Objects.equals(this.transporteAlquilado, other.transporteAlquilado)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoTransportePropio, other.tipoTransportePropio)) {
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

