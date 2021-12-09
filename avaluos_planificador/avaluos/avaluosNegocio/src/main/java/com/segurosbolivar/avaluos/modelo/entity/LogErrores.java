package com.segurosbolivar.avaluos.modelo.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="LOG_ERRORES")
@NamedQuery(name = "LogErrores.findAll", query = "SELECT p FROM LogErrores p")
public class LogErrores implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_LOG_ERRORES_PK = "ppNumerr";
	public static final String ENTIDAD_LOG_ERRORES_PP_DESERR = "ppDeserr";
	public static final String ENTIDAD_LOG_ERRORES_PP_CODPRG = "ppCodprg";
	public static final String ENTIDAD_LOG_ERRORES_PP_FECHA = "ppFecha";
	public static final String ENTIDAD_LOG_ERRORES_PP_USUARIO = "ppUsuario";
    private static final String[] ATRIBUTOS_ENTIDAD_LOG_ERRORES
            = {ENTIDAD_LOG_ERRORES_PP_DESERR, ENTIDAD_LOG_ERRORES_PP_USUARIO, ENTIDAD_LOG_ERRORES_PK, ENTIDAD_LOG_ERRORES_PP_CODPRG, ENTIDAD_LOG_ERRORES_PP_FECHA};

	@Id
    @Column(name="PP_NUMERR")
	private BigDecimal ppNumerr;

	@Column(name="PP_DESERR")
	private String ppDeserr;
	
	@Column(name="PP_CODPRG")
	private String ppCodprg;
	
	@Column(name="PP_FECHA")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date ppFecha;
	
	@Column(name="PP_USUARIO")
	private String ppUsuario;
	


	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public LogErrores(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	public BigDecimal getPpNumerr(){
		return this.ppNumerr;
	}
	
	public void setPpNumerr(BigDecimal ppNumerr){
	
		this.ppNumerr = ppNumerr;
	}
	
	public String getPpDeserr(){
		return this.ppDeserr;
	}
	
	public void setPpDeserr(String ppDeserr){
	
		this.ppDeserr = ppDeserr;
	}
		
	public String getPpCodprg(){
		return this.ppCodprg;
	}
	
	public void setPpCodprg(String ppCodprg){
	
		this.ppCodprg = ppCodprg;
	}
		
	public Date getPpFecha(){
		return this.ppFecha;
	}
	
	public void setPpFecha(Date ppFecha){
	
		this.ppFecha = ppFecha;
	}
		
	public String getPpUsuario(){
		return this.ppUsuario;
	}
	
	public void setPpUsuario(String ppUsuario){
	
		this.ppUsuario = ppUsuario;
	}
		


	/**
     * Verifica si la entidad contiene el atributo que se pasa como parámetro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_LOG_ERRORES) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
    }
    
    public static String[] getAtributosEntidadLogErrores() {
		return ATRIBUTOS_ENTIDAD_LOG_ERRORES;
	}

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.ppNumerr);        
        hash = 37 * hash + Objects.hashCode(this.ppDeserr);
        hash = 37 * hash + Objects.hashCode(this.ppCodprg);
        hash = 37 * hash + Objects.hashCode(this.ppFecha);
        hash = 37 * hash + Objects.hashCode(this.ppUsuario);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad LogErrores que se pasa
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
        final LogErrores other = (LogErrores) obj;
        
        if (!Objects.equals(this.ppNumerr, other.ppNumerr)) {
            return false;
        }
        
        if (!Objects.equals(this.ppDeserr, other.ppDeserr)) {
            return false;
        }
        
        if (!Objects.equals(this.ppCodprg, other.ppCodprg)) {
            return false;
        }
        
        if (!Objects.equals(this.ppFecha, other.ppFecha)) {
            return false;
        }
        
        return Objects.equals(this.ppUsuario, other.ppUsuario);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

