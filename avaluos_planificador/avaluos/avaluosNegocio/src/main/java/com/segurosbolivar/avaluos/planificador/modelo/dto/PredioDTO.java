package com.segurosbolivar.avaluos.planificador.modelo.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.segurosbolivar.avaluos.planificador.modelo.entity.Unidad;
import com.segurosbolivar.avaluos.planificador.modelo.entity.UnidadProductiva;

import java.util.Date;
import java.util.Objects;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * DAO que contiene la información de la entidad PredioDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author GeneradorCRUD
 */
@XmlRootElement
public class PredioDTO implements Serializable{	

	private BigDecimal idPredio;
	
	private String numeroFolio;

	private UnidadProductiva unidadProductiva;
	
	private String nombreMatriculaInmobiliaria;
	
	private String codigoDivipola;
	
	private String vereda;
	
	private String unidad;
	
	private BigDecimal areaTotal;
	
	private BigDecimal areaProdcutiva;
	
	private String fuenteInformacion;
	
	private String tenencia;
	
	private BigDecimal latitud;
	
	private BigDecimal longitud;
	
	private BigDecimal idCiudad;
	
	private BigDecimal idDepartamento;
	
	private String usuarioCreacion;
	
	private Date fechaCreacion;
	
	private String fechaCreacionStr;
	
	private String usuarioTransaccion;
	
	private Date fechaTransaccion;
	
	private String fechaTransaccionStr;
	
	private String formaLlegar;
	
	private Unidad idUnidadPredio;
	

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public PredioDTO(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	
	@XmlElement(name="numeroFolio")
	public String getNumeroFolio(){
		return this.numeroFolio;
	}
	
	@XmlElement(name="numeroFolio")
	public void setNumeroFolio(String numeroFolio){
		this.numeroFolio = numeroFolio;
	}
	
	@XmlElement(name="unidadProductiva")
	public UnidadProductiva getUnidadProductiva(){
		return this.unidadProductiva;
	}
	
	@XmlElement(name="unidadProductiva")
	public void setUnidadProductiva(UnidadProductiva unidadProductiva){
		this.unidadProductiva = unidadProductiva;
	}
		
	@XmlElement(name="nombreMatriculaInmobiliaria")
	public String getNombreMatriculaInmobiliaria(){
		return this.nombreMatriculaInmobiliaria;
	}
	
	@XmlElement(name="nombreMatriculaInmobiliaria")
	public void setNombreMatriculaInmobiliaria(String nombreMatriculaInmobiliaria){
		this.nombreMatriculaInmobiliaria = nombreMatriculaInmobiliaria;
	}
		
	@XmlElement(name="codigoDivipola")
	public String getCodigoDivipola(){
		return this.codigoDivipola;
	}
	
	@XmlElement(name="codigoDivipola")
	public void setCodigoDivipola(String codigoDivipola){
		this.codigoDivipola = codigoDivipola;
	}
		
	@XmlElement(name="vereda")
	public String getVereda(){
		return this.vereda;
	}
	
	@XmlElement(name="vereda")
	public void setVereda(String vereda){
		this.vereda = vereda;
	}
		
	@XmlElement(name="unidad")
	public String getUnidad(){
		return this.unidad;
	}
	
	@XmlElement(name="unidad")
	public void setUnidad(String unidad){
		this.unidad = unidad;
	}
		
	@XmlElement(name="areaTotal")
	public BigDecimal getAreaTotal(){
		return this.areaTotal;
	}
	
	@XmlElement(name="areaTotal")
	public void setAreaTotal(BigDecimal areaTotal){
		this.areaTotal = areaTotal;
	}
		
	@XmlElement(name="areaProdcutiva")
	public BigDecimal getAreaProdcutiva(){
		return this.areaProdcutiva;
	}
	
	@XmlElement(name="areaProdcutiva")
	public void setAreaProdcutiva(BigDecimal areaProdcutiva){
		this.areaProdcutiva = areaProdcutiva;
	}
		
	@XmlElement(name="fuenteInformacion")
	public String getFuenteInformacion(){
		return this.fuenteInformacion;
	}
	
	@XmlElement(name="fuenteInformacion")
	public void setFuenteInformacion(String fuenteInformacion){
		this.fuenteInformacion = fuenteInformacion;
	}
		
	@XmlElement(name="tenencia")
	public String getTenencia(){
		return this.tenencia;
	}
	
	@XmlElement(name="tenencia")
	public void setTenencia(String tenencia){
		this.tenencia = tenencia;
	}
		
	@XmlElement(name="latitud")
	public BigDecimal getLatitud(){
		return this.latitud;
	}
	
	@XmlElement(name="latitud")
	public void setLatitud(BigDecimal latitud){
		this.latitud = latitud;
	}
		
	@XmlElement(name="longitud")
	public BigDecimal getLongitud(){
		return this.longitud;
	}
	
	@XmlElement(name="longitud")
	public void setLongitud(BigDecimal longitud){
		this.longitud = longitud;
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
	
	@XmlElement(name="idPredio")
    public BigDecimal getIdPredio() {
		return idPredio;
	}

	@XmlElement(name="idPredio")
	public void setIdPredio(BigDecimal idPredio) {
		this.idPredio = idPredio;
	}

	@XmlElement(name="formaLlegar")
	public String getFormaLlegar() {
		return formaLlegar;
	}

	@XmlElement(name="formaLlegar")
	public void setFormaLlegar(String formaLlegar) {
		this.formaLlegar = formaLlegar;
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
	
	@XmlElement(name="idUnidadPredio")
	public Unidad getIdUnidadPredio() {
		return idUnidadPredio;
	}


	@XmlElement(name="idUnidadPredio")
	public void setIdUnidadPredio(Unidad idUnidadPredio) {
		this.idUnidadPredio = idUnidadPredio;
	}



	/**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.numeroFolio);        
        hash = 37 * hash + Objects.hashCode(this.unidadProductiva);
        hash = 37 * hash + Objects.hashCode(this.nombreMatriculaInmobiliaria);
        hash = 37 * hash + Objects.hashCode(this.codigoDivipola);
        hash = 37 * hash + Objects.hashCode(this.vereda);
        hash = 37 * hash + Objects.hashCode(this.unidad);
        hash = 37 * hash + Objects.hashCode(this.areaTotal);
        hash = 37 * hash + Objects.hashCode(this.areaProdcutiva);
        hash = 37 * hash + Objects.hashCode(this.fuenteInformacion);
        hash = 37 * hash + Objects.hashCode(this.tenencia);
        hash = 37 * hash + Objects.hashCode(this.latitud);
        hash = 37 * hash + Objects.hashCode(this.longitud);
        hash = 37 * hash + Objects.hashCode(this.idCiudad);
        hash = 37 * hash + Objects.hashCode(this.idDepartamento);
        hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
        hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
        hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
        hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
  
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PredioDTO que se pasa
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
        final PredioDTO other = (PredioDTO) obj;
                
        if (!Objects.equals(this.numeroFolio, other.numeroFolio)) {
            return false;
        }
        
        if (!Objects.equals(this.unidadProductiva, other.unidadProductiva)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreMatriculaInmobiliaria, other.nombreMatriculaInmobiliaria)) {
            return false;
        }
        
        if (!Objects.equals(this.codigoDivipola, other.codigoDivipola)) {
            return false;
        }
        
        if (!Objects.equals(this.vereda, other.vereda)) {
            return false;
        }
        
        if (!Objects.equals(this.unidad, other.unidad)) {
            return false;
        }
        
        if (!Objects.equals(this.areaTotal, other.areaTotal)) {
            return false;
        }
        
        if (!Objects.equals(this.areaProdcutiva, other.areaProdcutiva)) {
            return false;
        }
        
        if (!Objects.equals(this.fuenteInformacion, other.fuenteInformacion)) {
            return false;
        }
        
        if (!Objects.equals(this.tenencia, other.tenencia)) {
            return false;
        }
        
        if (!Objects.equals(this.latitud, other.latitud)) {
            return false;
        }
        
        if (!Objects.equals(this.longitud, other.longitud)) {
            return false;
        }
        
        if (!Objects.equals(this.idCiudad, other.idCiudad)) {
            return false;
        }
        
        if (!Objects.equals(this.idDepartamento, other.idDepartamento)) {
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
        
        if (!Objects.equals(this.idUnidadPredio, other.idUnidadPredio)) {
            return false;
        }
        
        return Objects.equals(this.fechaTransaccion, other.fechaTransaccion);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

