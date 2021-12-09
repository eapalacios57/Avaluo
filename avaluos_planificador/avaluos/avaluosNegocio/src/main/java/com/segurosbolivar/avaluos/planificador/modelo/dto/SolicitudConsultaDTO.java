package com.segurosbolivar.avaluos.planificador.modelo.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.segurosbolivar.avaluos.planificador.modelo.entity.BeneficiarioPK;

import java.util.Date;
import java.util.Objects;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * DAO que contiene la información de la entidad SolicitudDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author GeneradorCRUD
 */
@XmlRootElement
public class SolicitudConsultaDTO implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoSolicitud;

	private PlanificadorConsultaDTO planificador;
	
	private BeneficiarioPK id;

	private String primerNombre;
	
	private String segundoNombre;
	
	private String primerApellido;
	
	private String segundoApellido;

	private String municipioDepartamento;

	private Date fechaSolicitud;

	private String fechaSolicitudStr;
	
	private String estado;

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public SolicitudConsultaDTO(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	
	@XmlElement(name="codigoSolicitud")
	public String getCodigoSolicitud(){
		return this.codigoSolicitud;
	}
	
	@XmlElement(name="codigoSolicitud")
	public void setCodigoSolicitud(String codigoSolicitud){
		this.codigoSolicitud = codigoSolicitud;
	}
	
	@XmlElement(name="planificador")
    public PlanificadorConsultaDTO getPlanificador() {
		return planificador;
	}

	@XmlElement(name="planificador")
	public void setPlanificador(PlanificadorConsultaDTO planificador) {
		this.planificador = planificador;
	}


	@XmlElement(name="id")
	public BeneficiarioPK getId() {
		return id;
	}


	@XmlElement(name="id")
	public void setId(BeneficiarioPK id) {
		this.id = id;
	}


	@XmlElement(name="primerNombre")
	public String getPrimerNombre() {
		return primerNombre;
	}


	@XmlElement(name="primerNombre")
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}


	@XmlElement(name="segundoNombre")
	public String getSegundoNombre() {
		return segundoNombre;
	}


	@XmlElement(name="segundoNombre")
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}


	@XmlElement(name="primerApellido")
	public String getPrimerApellido() {
		return primerApellido;
	}


	@XmlElement(name="primerApellido")
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}


	@XmlElement(name="segundoApellido")
	public String getSegundoApellido() {
		return segundoApellido;
	}


	@XmlElement(name="segundoApellido")
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}



	@XmlElement(name="municipioDepartamento")
	public String getMunicipioDepartamento() {
		return municipioDepartamento;
	}


	@XmlElement(name="municipioDepartamento")
	public void setMunicipioDepartamento(String municipioDepartamento) {
		this.municipioDepartamento = municipioDepartamento;
	}

	@XmlElement(name="fechaSolicitud")
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}


	@XmlElement(name="fechaSolicitud")
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	
	public String getFechaSolicitudStr() {
		return fechaSolicitudStr;
	}

	public void setFechaSolicitudStr(String fechaSolicitudStr) {
		this.fechaSolicitudStr = fechaSolicitudStr;
	}
	
	


	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.codigoSolicitud);                
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad SolicitudDTO que se pasa
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
        final SolicitudConsultaDTO other = (SolicitudConsultaDTO) obj;
                
        if (!Objects.equals(this.codigoSolicitud, other.codigoSolicitud)) {
            return false;
        }
        

        
        return Objects.equals(this.codigoSolicitud, other.codigoSolicitud);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

