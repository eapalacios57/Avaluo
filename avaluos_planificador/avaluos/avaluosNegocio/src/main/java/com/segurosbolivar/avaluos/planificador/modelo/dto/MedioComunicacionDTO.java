package com.segurosbolivar.avaluos.planificador.modelo.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.segurosbolivar.avaluos.planificador.modelo.entity.Beneficiario;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Planificador;

import java.util.Date;
import java.util.Objects;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

/**
 * DAO que contiene la información de la entidad MedioComunicacionDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author GeneradorCRUD
 */
@XmlRootElement
public class MedioComunicacionDTO implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal idMedioComunicacion;

	private String tipoMedioComunicacion;
	
	private String estado;
	
	private String principal;
	
	private String valor;
	
	private String descripcion;
	
	private String tipoDocumentoPlanificador;
	
	private String numeroDocumentoPlanificador;
	
	private String tipoDocumentoBeneficiario;
	
	private String numeroDocumentoBeneficiario;
	
	private String usuarioCreacion;
	
	private String fechaCreacionStr;
	private Date fechaCreacion;
	
	private String usuarioTransaccion;
	
	private String fechaTransaccionStr;
	private Date fechaTransaccion;
	
	private Beneficiario beneficiario;
	
	private Planificador planificador;

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public MedioComunicacionDTO(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	
	@XmlElement(name="idMedioComunicacion")
	public BigDecimal getIdMedioComunicacion(){
		return this.idMedioComunicacion;
	}
	
	@XmlElement(name="idMedioComunicacion")
	public void setIdMedioComunicacion(BigDecimal idMedioComunicacion){
		this.idMedioComunicacion = idMedioComunicacion;
	}
	
	@XmlElement(name="tipoMedioComunicacion")
	public String getTipoMedioComunicacion(){
		return this.tipoMedioComunicacion;
	}
	
	@XmlElement(name="tipoMedioComunicacion")
	public void setTipoMedioComunicacion(String tipoMedioComunicacion){
		this.tipoMedioComunicacion = tipoMedioComunicacion;
	}
		
	@XmlElement(name="estado")
	public String getEstado(){
		return this.estado;
	}
	
	@XmlElement(name="estado")
	public void setEstado(String estado){
		this.estado = estado;
	}
		
	@XmlElement(name="principal")
	public String getPrincipal(){
		return this.principal;
	}
	
	@XmlElement(name="principal")
	public void setPrincipal(String principal){
		this.principal = principal;
	}
		
	@XmlElement(name="valor")
	public String getValor(){
		return this.valor;
	}
	
	@XmlElement(name="valor")
	public void setValor(String valor){
		this.valor = valor;
	}
		
	@XmlElement(name="descripcion")
	public String getDescripcion(){
		return this.descripcion;
	}
	
	@XmlElement(name="descripcion")
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
		
	@XmlElement(name="tipoDocumentoPlanificador")
	public String getTipoDocumentoPlanificador(){
		return this.tipoDocumentoPlanificador;
	}
	
	@XmlElement(name="tipoDocumentoPlanificador")
	public void setTipoDocumentoPlanificador(String tipoDocumentoPlanificador){
		this.tipoDocumentoPlanificador = tipoDocumentoPlanificador;
	}
		
	@XmlElement(name="numeroDocumentoPlanificador")
	public String getNumeroDocumentoPlanificador(){
		return this.numeroDocumentoPlanificador;
	}
	
	@XmlElement(name="numeroDocumentoPlanificador")
	public void setNumeroDocumentoPlanificador(String numeroDocumentoPlanificador){
		this.numeroDocumentoPlanificador = numeroDocumentoPlanificador;
	}
		
	@XmlElement(name="tipoDocumentoBeneficiario")
	public String getTipoDocumentoBeneficiario(){
		return this.tipoDocumentoBeneficiario;
	}
	
	@XmlElement(name="tipoDocumentoBeneficiario")
	public void setTipoDocumentoBeneficiario(String tipoDocumentoBeneficiario){
		this.tipoDocumentoBeneficiario = tipoDocumentoBeneficiario;
	}
		
	@XmlElement(name="numeroDocumentoBeneficiario")
	public String getNumeroDocumentoBeneficiario(){
		return this.numeroDocumentoBeneficiario;
	}
	
	@XmlElement(name="numeroDocumentoBeneficiario")
	public void setNumeroDocumentoBeneficiario(String numeroDocumentoBeneficiario){
		this.numeroDocumentoBeneficiario = numeroDocumentoBeneficiario;
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
	public String getFechaCreacionStr(){
		return this.fechaCreacionStr;
	}
	
	@XmlElement(name="fechaCreacionStr")
	public void setFechaCreacionStr(String fechaCreacionStr){
		this.fechaCreacionStr = fechaCreacionStr;
	}
		
	@XmlElement(name="usuarioTransaccion")
	public String getUsuarioTransaccion(){
		return this.usuarioTransaccion;
	}
	
	@XmlElement(name="usuarioTransaccion")
	public void setUsuarioTransaccion(String usuarioTransaccion){
		this.usuarioTransaccion = usuarioTransaccion;
	}
		
	@XmlElement(name="fechaTransaccionStr")
	public String getFechaTransaccionStr(){
		return this.fechaTransaccionStr;
	}
	
	@XmlElement(name="fechaTransaccionStr")
	public void setFechaTransaccionStr(String fechaTransaccionStr){
		this.fechaTransaccionStr = fechaTransaccionStr;
	}
	
	
	@XmlElement(name="fechaCreacion")
    public Date getFechaCreacion() {
		return fechaCreacion;
	}


	@XmlElement(name="fechaCreacion")
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	@XmlElement(name="fechaTransaccion")
	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}


	@XmlElement(name="fechaTransaccion")
	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	
	@XmlElement(name="beneficiario")
	public Beneficiario getBeneficiario() {
		return beneficiario;
	}


	@XmlElement(name="beneficiario")
	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}


	@XmlElement(name="planificador")
	public Planificador getPlanificador() {
		return planificador;
	}


	@XmlElement(name="planificador")
	public void setPlanificador(Planificador planificador) {
		this.planificador = planificador;
	}



	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idMedioComunicacion);        
        hash = 37 * hash + Objects.hashCode(this.tipoMedioComunicacion);
        hash = 37 * hash + Objects.hashCode(this.estado);
        hash = 37 * hash + Objects.hashCode(this.principal);
        hash = 37 * hash + Objects.hashCode(this.valor);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.tipoDocumentoPlanificador);
        hash = 37 * hash + Objects.hashCode(this.numeroDocumentoPlanificador);
        hash = 37 * hash + Objects.hashCode(this.tipoDocumentoBeneficiario);
        hash = 37 * hash + Objects.hashCode(this.numeroDocumentoBeneficiario);
        hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
        hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
        hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
        hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad MedioComunicacionDTO que se pasa
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
        final MedioComunicacionDTO other = (MedioComunicacionDTO) obj;
                
        if (!Objects.equals(this.idMedioComunicacion, other.idMedioComunicacion)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoMedioComunicacion, other.tipoMedioComunicacion)) {
            return false;
        }
        
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        
        if (!Objects.equals(this.principal, other.principal)) {
            return false;
        }
        
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoDocumentoPlanificador, other.tipoDocumentoPlanificador)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroDocumentoPlanificador, other.numeroDocumentoPlanificador)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoDocumentoBeneficiario, other.tipoDocumentoBeneficiario)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroDocumentoBeneficiario, other.numeroDocumentoBeneficiario)) {
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

