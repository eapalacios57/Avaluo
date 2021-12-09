package com.segurosbolivar.avaluos.modelo.entity;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the CATEGORIES database table.
 *
 */
@Entity
@Table(name="TABLA_ERRORES")
@NamedQuery(name = "TablaErrores.findAll", query = "SELECT p FROM TablaErrores p")
public class TablaErrores implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_TABLA_ERRORES_PK = "codigo";
	public static final String ENTIDAD_TABLA_ERRORES_DESC_GRAL = "descGral";
	public static final String ENTIDAD_TABLA_ERRORES_NOMBRE_EXCEPCION = "nombreExcepcion";
	public static final String ENTIDAD_TABLA_ERRORES_TIPO_ERROR = "tipoError";
	public static final String ENTIDAD_TABLA_ERRORES_GENERA_RAISE = "generaRaise";
	public static final String ENTIDAD_TABLA_ERRORES_GENERA_LOG = "generaLog";
    private static final String[] ATRIBUTOS_ENTIDAD_TABLA_ERRORES
            = {ENTIDAD_TABLA_ERRORES_TIPO_ERROR, ENTIDAD_TABLA_ERRORES_DESC_GRAL, ENTIDAD_TABLA_ERRORES_PK, ENTIDAD_TABLA_ERRORES_GENERA_RAISE, ENTIDAD_TABLA_ERRORES_NOMBRE_EXCEPCION, ENTIDAD_TABLA_ERRORES_GENERA_LOG};

	@Id
    @Column(name="CODIGO")
	private Long codigo;

	@Column(name="DESC_GRAL")
	private String descGral;
	
	@Column(name="NOMBRE_EXCEPCION")
	private String nombreExcepcion;
	
	@Column(name="TIPO_ERROR")
	private String tipoError;
	
	@Column(name="GENERA_RAISE")
	private String generaRaise;
	
	@Column(name="GENERA_LOG")
	private String generaLog;
	


	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public TablaErrores(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	public Long getCodigo(){
		return this.codigo;
	}
	
	public void setCodigo(Long codigo){
	
		this.codigo = codigo;
	}
	
	public String getDescGral(){
		return this.descGral;
	}
	
	public void setDescGral(String descGral){
	
		this.descGral = descGral;
	}
		
	public String getNombreExcepcion(){
		return this.nombreExcepcion;
	}
	
	public void setNombreExcepcion(String nombreExcepcion){
	
		this.nombreExcepcion = nombreExcepcion;
	}
		
	public String getTipoError(){
		return this.tipoError;
	}
	
	public void setTipoError(String tipoError){
	
		this.tipoError = tipoError;
	}
		
	public String getGeneraRaise(){
		return this.generaRaise;
	}
	
	public void setGeneraRaise(String generaRaise){
	
		this.generaRaise = generaRaise;
	}
		
	public String getGeneraLog(){
		return this.generaLog;
	}
	
	public void setGeneraLog(String generaLog){
	
		this.generaLog = generaLog;
	}
	
	@Override
	public String toString() {
		return "TablaError [descGral=" + descGral + "generaLog=" + generaLog + ", generaRaise=" + generaRaise
				+ ", nombreExcepcion=" + nombreExcepcion + ", tipoError=" + tipoError + ", codigo="
				+ codigo + "]";
	}
		


	/**
     * Verifica si la entidad contiene el atributo que se pasa como parámetro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_TABLA_ERRORES) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
    }
    
    public static String[] getAtributosEntidadTablaErrores() {
		return ATRIBUTOS_ENTIDAD_TABLA_ERRORES;
	}

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.codigo);        
        hash = 37 * hash + Objects.hashCode(this.descGral);
        hash = 37 * hash + Objects.hashCode(this.nombreExcepcion);
        hash = 37 * hash + Objects.hashCode(this.tipoError);
        hash = 37 * hash + Objects.hashCode(this.generaRaise);
        hash = 37 * hash + Objects.hashCode(this.generaLog);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad TablaErrores que se pasa
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
        final TablaErrores other = (TablaErrores) obj;
        
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        
        if (!Objects.equals(this.descGral, other.descGral)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreExcepcion, other.nombreExcepcion)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoError, other.tipoError)) {
            return false;
        }
        
        if (!Objects.equals(this.generaRaise, other.generaRaise)) {
            return false;
        }
        
        return Objects.equals(this.generaLog, other.generaLog);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

