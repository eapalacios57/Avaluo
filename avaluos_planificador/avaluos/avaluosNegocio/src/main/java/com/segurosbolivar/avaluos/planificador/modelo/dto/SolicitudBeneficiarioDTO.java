package com.segurosbolivar.avaluos.planificador.modelo.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.segurosbolivar.avaluos.planificador.modelo.entity.Beneficiario;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Solicitud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.SolicitudBeneficiarioPK;

import java.util.Date;
import java.util.Objects;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;


/**
 * DAO que contiene la información de la entidad SolicitudBeneficiarioDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author GeneradorCRUD
 */
@XmlRootElement
public class SolicitudBeneficiarioDTO implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SolicitudBeneficiarioPK id;

	private String contacto;
	
	private String usuarioCreacion;
	
	private String fechaCreacionStr;
	
	private Date fechaCreacion;
	
	private String usuarioTransaccion;
	
	private Date fechaTransaccion;
	
	private String fechaTransaccionStr;
	
	private String numeroDocumentoBeneficiario;
	
	private String tipoDocumentoBeneficiario;
	
	private Solicitud solicitud;
	
	private Beneficiario beneficiario;
	
	
	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public SolicitudBeneficiarioDTO(){
		id = new SolicitudBeneficiarioPK();
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	@XmlElement(name="id")
	public SolicitudBeneficiarioPK getId(){
		return this.id;
	}
	
	@XmlElement(name="id")
	public void setId(SolicitudBeneficiarioPK id){
		this.id   = id ;
	}  
	
	@XmlElement(name="contacto")
	public String getContacto(){
		return this.contacto;
	}
	
	@XmlElement(name="contacto")
	public void setContacto(String contacto){
		this.contacto = contacto;
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
		
	
	@XmlElement(name="fechaCreacionStr")
    public String getFechaCreacionStr() {
		return fechaCreacionStr;
	}

	@XmlElement(name="fechaCreacionStr")
	public void setFechaCreacionStr(String fechaCreacionStr) {
		this.fechaCreacionStr = fechaCreacionStr;
	}

	@XmlElement(name="fechaTransaccionStr")
	public String getFechaTransaccionStr() {
		return fechaTransaccionStr;
	}

	@XmlElement(name="fechaTransaccionStr")
	public void setFechaTransaccionStr(String fechaTransaccionStr) {
		this.fechaTransaccionStr = fechaTransaccionStr;
	}

	
	public String getNumeroDocumentoBeneficiario() {
		return numeroDocumentoBeneficiario;
	}


	public void setNumeroDocumentoBeneficiario(String numeroDocumentoBeneficiario) {
		this.numeroDocumentoBeneficiario = numeroDocumentoBeneficiario;
	}


	public String getTipoDocumentoBeneficiario() {
		return tipoDocumentoBeneficiario;
	}


	public void setTipoDocumentoBeneficiario(String tipoDocumentoBeneficiario) {
		this.tipoDocumentoBeneficiario = tipoDocumentoBeneficiario;
	}


	public Solicitud getSolicitud() {
		return solicitud;
	}


	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	
	public Beneficiario getBeneficiario() {
		return beneficiario;
	}


	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
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
        hash = 37 * hash + Objects.hashCode(this.contacto);
        hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
        hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
        hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
        hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad SolicitudBeneficiarioDTO que se pasa
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
        final SolicitudBeneficiarioDTO other = (SolicitudBeneficiarioDTO) obj;
                
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        
        if (!Objects.equals(this.contacto, other.contacto)) {
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

