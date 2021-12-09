package com.segurosbolivar.avaluos.planificador.modelo.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.segurosbolivar.avaluos.planificador.modelo.entity.Planificador;

import java.util.Date;
import java.util.Objects;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

/**
 * DAO que contiene la información de la entidad SolicitudDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author GeneradorCRUD
 */
@XmlRootElement
public class SolicitudDTO implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoSolicitud;

	private String tipoDocumentoPlanificador;
	
	private String numeroDocumentoPlanificador;
	
	private BigDecimal avance;
	
	private String fechaSolicitudStr;
	
	private Date fechaSolicitud;
	
	private String fechaVisitaPredioStr;
	
	private Date fechaVisitaPredio;
	
	private String descripcion;
	
	private String comentariosAnexos;
	
	private BigDecimal valorTotalProyecto;
	
	private BigDecimal valorTotalCredito;
	
	private String nombreAsesorComercial;
	
	private String telefonoAsesorComercial;
	
	private String municipioDepartamento;
	
	private String usuarioCreacion;
	
	private String fechaCreacionStr;
	
	private Date fechaCreacion;
	
	private String usuarioTransaccion;
	
	private String fechaTransaccionStr;
	
	private Date fechaTransaccion;
	
	private Planificador planificador;
	

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public SolicitudDTO(){
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
		
	@XmlElement(name="avance")
	public BigDecimal getAvance(){
		return this.avance;
	}
	
	@XmlElement(name="avance")
	public void setAvance(BigDecimal avance){
		this.avance = avance;
	}
		
	@XmlElement(name="fechaSolicitudStr")
	public String getFechaSolicitudStr(){
		return this.fechaSolicitudStr;
	}
	
	@XmlElement(name="fechaSolicitudStr")
	public void setFechaSolicitudStr(String fechaSolicitudStr){
		this.fechaSolicitudStr = fechaSolicitudStr;
	}
		
	@XmlElement(name="fechaVisitaPredioStr")
	public String getFechaVisitaPredioStr(){
		return this.fechaVisitaPredioStr;
	}
	
	@XmlElement(name="fechaVisitaPredioStr")
	public void setFechaVisitaPredioStr(String fechaVisitaPredioStr){
		this.fechaVisitaPredioStr = fechaVisitaPredioStr;
	}
		
	@XmlElement(name="descripcion")
	public String getDescripcion(){
		return this.descripcion;
	}
	
	@XmlElement(name="descripcion")
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
		
	@XmlElement(name="comentariosAnexos")
	public String getComentariosAnexos(){
		return this.comentariosAnexos;
	}
	
	@XmlElement(name="comentariosAnexos")
	public void setComentariosAnexos(String comentariosAnexos){
		this.comentariosAnexos = comentariosAnexos;
	}
		
	@XmlElement(name="valorTotalProyecto")
	public BigDecimal getValorTotalProyecto(){
		return this.valorTotalProyecto;
	}
	
	@XmlElement(name="valorTotalProyecto")
	public void setValorTotalProyecto(BigDecimal valorTotalProyecto){
		this.valorTotalProyecto = valorTotalProyecto;
	}
		
	@XmlElement(name="valorTotalCredito")
	public BigDecimal getValorTotalCredito(){
		return this.valorTotalCredito;
	}
	
	@XmlElement(name="valorTotalCredito")
	public void setValorTotalCredito(BigDecimal valorTotalCredito){
		this.valorTotalCredito = valorTotalCredito;
	}
		
	@XmlElement(name="nombreAsesorComercial")
	public String getNombreAsesorComercial(){
		return this.nombreAsesorComercial;
	}
	
	@XmlElement(name="nombreAsesorComercial")
	public void setNombreAsesorComercial(String nombreAsesorComercial){
		this.nombreAsesorComercial = nombreAsesorComercial;
	}
		
	@XmlElement(name="telefonoAsesorComercial")
	public String getTelefonoAsesorComercial(){
		return this.telefonoAsesorComercial;
	}
	
	@XmlElement(name="telefonoAsesorComercial")
	public void setTelefonoAsesorComercial(String telefonoAsesorComercial){
		this.telefonoAsesorComercial = telefonoAsesorComercial;
	}
		
	@XmlElement(name="municipioDepartamento")
	public String getMunicipioDepartamento(){
		return this.municipioDepartamento;
	}
	
	@XmlElement(name="municipioDepartamento")
	public void setMunicipioDepartamento(String municipioDepartamento){
		this.municipioDepartamento = municipioDepartamento;
	}
		
	@XmlElement(name="usuarioCreacion")
	public String getUsuarioCreacion(){
		return this.usuarioCreacion;
	}
	
	@XmlElement(name="usuarioCreacion")
	public void setUsuarioCreacion(String usuarioCreacion){
		this.usuarioCreacion = usuarioCreacion;
	}
		
	@XmlElement(name="fechaCreacionStr")
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
		
	
	@XmlElement(name="fechaSolicitud")
    public Date getFechaSolicitud() {
		return fechaSolicitud;
	}


	@XmlElement(name="fechaSolicitud")
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}


	@XmlElement(name="fechaVisitaPredio")
	public Date getFechaVisitaPredio() {
		return fechaVisitaPredio;
	}


	@XmlElement(name="fechaVisitaPredio")
	public void setFechaVisitaPredio(Date fechaVisitaPredio) {
		this.fechaVisitaPredio = fechaVisitaPredio;
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
        
        hash = 37 * hash + Objects.hashCode(this.codigoSolicitud);        
        hash = 37 * hash + Objects.hashCode(this.tipoDocumentoPlanificador);
        hash = 37 * hash + Objects.hashCode(this.numeroDocumentoPlanificador);
        hash = 37 * hash + Objects.hashCode(this.avance);
        hash = 37 * hash + Objects.hashCode(this.fechaSolicitud);
        hash = 37 * hash + Objects.hashCode(this.fechaVisitaPredio);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.comentariosAnexos);
        hash = 37 * hash + Objects.hashCode(this.valorTotalProyecto);
        hash = 37 * hash + Objects.hashCode(this.valorTotalCredito);
        hash = 37 * hash + Objects.hashCode(this.nombreAsesorComercial);
        hash = 37 * hash + Objects.hashCode(this.telefonoAsesorComercial);
        hash = 37 * hash + Objects.hashCode(this.municipioDepartamento);
        hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
        hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
        hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
        hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
  
        
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
        final SolicitudDTO other = (SolicitudDTO) obj;
                
        if (!Objects.equals(this.codigoSolicitud, other.codigoSolicitud)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoDocumentoPlanificador, other.tipoDocumentoPlanificador)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroDocumentoPlanificador, other.numeroDocumentoPlanificador)) {
            return false;
        }
        
        if (!Objects.equals(this.avance, other.avance)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaSolicitud, other.fechaSolicitud)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaVisitaPredio, other.fechaVisitaPredio)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.comentariosAnexos, other.comentariosAnexos)) {
            return false;
        }
        
        if (!Objects.equals(this.valorTotalProyecto, other.valorTotalProyecto)) {
            return false;
        }
        
        if (!Objects.equals(this.valorTotalCredito, other.valorTotalCredito)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreAsesorComercial, other.nombreAsesorComercial)) {
            return false;
        }
        
        if (!Objects.equals(this.telefonoAsesorComercial, other.telefonoAsesorComercial)) {
            return false;
        }
        
        if (!Objects.equals(this.municipioDepartamento, other.municipioDepartamento)) {
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

