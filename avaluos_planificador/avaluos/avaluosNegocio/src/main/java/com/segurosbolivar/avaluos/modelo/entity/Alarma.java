package com.segurosbolivar.avaluos.modelo.entity;

import static org.eclipse.persistence.annotations.CacheType.NONE;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the PGB_ALARMAS database table.
 * 
 */
@javax.persistence.Entity
@Table(name="PGB_ALARMAS")
@NamedQueries({@NamedQuery(name = "getAlarmas", query = "select a from Alarma a"),@NamedQuery(name = "getDescAlarma", query = "select a from Alarma a where a.fechaCreacion = (select MAX(b.fechaCreacion) from Alarma b)"),
@NamedQuery(name = "Alarma.findAll", query = "SELECT p FROM Alarma p")})
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class Alarma implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_ALARMAS_PK = "idAlarma";
	public static final String ENTIDAD_PGB_ALARMAS_DESC_ALERTA = "descAlerta";
	public static final String ENTIDAD_PGB_ALARMAS_NUM_DIAS = "numDias";
	public static final String ENTIDAD_PGB_ALARMAS_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_PGB_ALARMAS_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_PGB_ALARMAS_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_ALARMAS_FECHA_TRANSACCION = "fechaTransaccion";
    private static final String[] ATRIBUTOS_ENTIDAD_PGB_ALARMAS
            = {ENTIDAD_PGB_ALARMAS_USUARIO_CREACION, ENTIDAD_PGB_ALARMAS_USUARIO_TRANSACCION, ENTIDAD_PGB_ALARMAS_PK, ENTIDAD_PGB_ALARMAS_FECHA_TRANSACCION, ENTIDAD_PGB_ALARMAS_DESC_ALERTA, ENTIDAD_PGB_ALARMAS_FECHA_CREACION, ENTIDAD_PGB_ALARMAS_NUM_DIAS};

	@Id
	@SequenceGenerator(name="PGB_ALARMAS_IDALARMA_GENERATOR", sequenceName="SEQ_PGB_ALARMAS" , allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PGB_ALARMAS_IDALARMA_GENERATOR")
	@Column(name="ID_ALARMA", unique=true, nullable=false, precision=3)
	private BigDecimal idAlarma;

	@Column(name="DESC_ALERTA", nullable=false, length=250)
	private String descripcionAlerta;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="FECHA_CREACION", nullable=false)
	private Date fechaCreacion;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="FECHA_TRANSACCION", nullable=false)
	private Date fechaTransaccion;

	@Column(name="NUM_DIAS", nullable=false, precision=2)
	private BigDecimal numDias;

	@Column(name="USUARIO_CREACION", nullable=false, length=15)
	private String usuarioCreacion;

	@Column(name="USUARIO_TRANSACCION", nullable=false, length=15)
	private String usuarioTransaccion;

    public Alarma() {
    }

	public BigDecimal getIdAlarma() {
		return this.idAlarma;
	}

	public void setIdAlarma(BigDecimal idAlarma) {
		this.idAlarma = idAlarma;
	}

	public String getDescripcionAlerta() {
		return this.descripcionAlerta;
	}

	public void setDescripcionAlerta(String descripcionAlerta) {
		this.descripcionAlerta = descripcionAlerta;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaTransaccion() {
		return this.fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public BigDecimal getNumDias() {
		return this.numDias;
	}

	public void setNumDias(BigDecimal numDias) {
		this.numDias = numDias;
	}

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioTransaccion() {
		return this.usuarioTransaccion;
	}

	public void setUsuarioTransaccion(String usuarioTransaccion) {
		this.usuarioTransaccion = usuarioTransaccion;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como par�metro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_PGB_ALARMAS) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
    }
    
    public static String[] getAtributosEntidadPgbAlarmas() {
		return ATRIBUTOS_ENTIDAD_PGB_ALARMAS;
	}

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idAlarma);        
        hash = 37 * hash + Objects.hashCode(this.descripcionAlerta);
        hash = 37 * hash + Objects.hashCode(this.numDias);
        hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
        hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
        hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
        hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PgbAlarmas que se pasa
     * como par�metro comprobando que comparten los mismos valores en cada uno
     * de sus atributos. Solo se tienen en cuenta los atributos simples, es
     * decir, se omiten aquellos que definen una relaci�n con otra tabla.
     *
     * @param obj Instancia de la categor�a a comprobar
     * iguales.
     * @return Verdadero si esta instancia y la que se pasan como par�metros son
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Alarma other = (Alarma) obj;
        
        if (!Objects.equals(this.idAlarma, other.idAlarma)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcionAlerta, other.descripcionAlerta)) {
            return false;
        }
        
        if (!Objects.equals(this.numDias, other.numDias)) {
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
	// Escriba en esta secci�n sus modificaciones

	// protected region metodos adicionales end


}