package com.segurosbolivar.avaluos.modelo.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
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
@Table(name="PGB_ARCHIVOS_CARGUE")
@NamedQuery(name = "ArchivosCargue.findAll", query = "SELECT p FROM ArchivosCargue p")
public class ArchivosCargue implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_ARCHIVOS_CARGUE_PK = "idArchivo";
	public static final String ENTIDAD_PGB_ARCHIVOS_CARGUE_TIPO_ARCHIVO = "tipoArchivo";
	public static final String ENTIDAD_PGB_ARCHIVOS_CARGUE_NOMBRE_ARCHIVO = "nombreArchivo";
	public static final String ENTIDAD_PGB_ARCHIVOS_CARGUE_TAMANIO_ARCHIVO = "tamanioArchivo";
	public static final String ENTIDAD_PGB_ARCHIVOS_CARGUE_CONTENIDO = "contenido";
	public static final String ENTIDAD_PGB_ARCHIVOS_CARGUE_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_PGB_ARCHIVOS_CARGUE_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_PGB_ARCHIVOS_CARGUE_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_ARCHIVOS_CARGUE_FECHA_TRANSACCION = "fechaTransaccion";
    private static final String[] ATRIBUTOS_ENTIDAD_PGB_ARCHIVOS_CARGUE
            = {ENTIDAD_PGB_ARCHIVOS_CARGUE_TAMANIO_ARCHIVO, ENTIDAD_PGB_ARCHIVOS_CARGUE_CONTENIDO, ENTIDAD_PGB_ARCHIVOS_CARGUE_PK, ENTIDAD_PGB_ARCHIVOS_CARGUE_USUARIO_TRANSACCION, ENTIDAD_PGB_ARCHIVOS_CARGUE_USUARIO_CREACION, ENTIDAD_PGB_ARCHIVOS_CARGUE_NOMBRE_ARCHIVO, ENTIDAD_PGB_ARCHIVOS_CARGUE_FECHA_CREACION, ENTIDAD_PGB_ARCHIVOS_CARGUE_TIPO_ARCHIVO, ENTIDAD_PGB_ARCHIVOS_CARGUE_FECHA_TRANSACCION};

	@Id
    @Column(name="ID_ARCHIVO")
	private BigDecimal idArchivo;

	@Column(name="TIPO_ARCHIVO")
	private BigDecimal tipoArchivo;
	
	@Column(name="NOMBRE_ARCHIVO")
	private String nombreArchivo;
	
	@Column(name="TAMANIO_ARCHIVO")
	private BigDecimal tamanioArchivo;
	
	@Column(name="CONTENIDO")
	private Blob contenido;
	
	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;
	
	@Column(name="FECHA_CREACION")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	
	@Column(name="USUARIO_TRANSACCION")
	private String usuarioTransaccion;
	
	@Column(name="FECHA_TRANSACCION")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date fechaTransaccion;
	


	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end
	
    public ArchivosCargue(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	public BigDecimal getIdArchivo(){
		return this.idArchivo;
	}
	
	public void setIdArchivo(BigDecimal idArchivo){
	
		this.idArchivo = idArchivo;
	}
	
	public BigDecimal getTipoArchivo(){
		return this.tipoArchivo;
	}
	
	public void setTipoArchivo(BigDecimal tipoArchivo){
	
		this.tipoArchivo = tipoArchivo;
	}
		
	public String getNombreArchivo(){
		return this.nombreArchivo;
	}
	
	public void setNombreArchivo(String nombreArchivo){
	
		this.nombreArchivo = nombreArchivo;
	}
		
	public BigDecimal getTamanioArchivo(){
		return this.tamanioArchivo;
	}
	
	public void setTamanioArchivo(BigDecimal tamanioArchivo){
	
		this.tamanioArchivo = tamanioArchivo;
	}
		
	public Blob getContenido(){
		return this.contenido;
	}
	
	public void setContenido(Blob contenido){
	
		this.contenido = contenido;
	}
		
	public String getUsuarioCreacion(){
		return this.usuarioCreacion;
	}
	
	public void setUsuarioCreacion(String usuarioCreacion){
	
		this.usuarioCreacion = usuarioCreacion;
	}
		
	public Date getFechaCreacion(){
		return this.fechaCreacion;
	}
	
	public void setFechaCreacion(Date fechaCreacion){
	
		this.fechaCreacion = fechaCreacion;
	}
		
	public String getUsuarioTransaccion(){
		return this.usuarioTransaccion;
	}
	
	public void setUsuarioTransaccion(String usuarioTransaccion){
	
		this.usuarioTransaccion = usuarioTransaccion;
	}
		
	public Date getFechaTransaccion(){
		return this.fechaTransaccion;
	}
	
	public void setFechaTransaccion(Date fechaTransaccion){
	
		this.fechaTransaccion = fechaTransaccion;
	}
		


	/**
     * Verifica si la entidad contiene el atributo que se pasa como parámetro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_PGB_ARCHIVOS_CARGUE) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
    }
    
    public static String[] getAtributosEntidadArchivosCargue() {
		return ATRIBUTOS_ENTIDAD_PGB_ARCHIVOS_CARGUE;
	}

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idArchivo);        
        hash = 37 * hash + Objects.hashCode(this.tipoArchivo);
        hash = 37 * hash + Objects.hashCode(this.nombreArchivo);
        hash = 37 * hash + Objects.hashCode(this.tamanioArchivo);
        hash = 37 * hash + Objects.hashCode(this.contenido);
        hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
        hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
        hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
        hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad ArchivosCargue que se pasa
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
        final ArchivosCargue other = (ArchivosCargue) obj;
        
        if (!Objects.equals(this.idArchivo, other.idArchivo)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoArchivo, other.tipoArchivo)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreArchivo, other.nombreArchivo)) {
            return false;
        }
        
        if (!Objects.equals(this.tamanioArchivo, other.tamanioArchivo)) {
            return false;
        }
        
        if (!Objects.equals(this.contenido, other.contenido)) {
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

