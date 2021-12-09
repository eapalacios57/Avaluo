package com.segurosbolivar.avaluos.modelo.entity;

import static org.eclipse.persistence.annotations.CacheType.NONE;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import uk.co.jemos.podam.annotations.PodamExclude;


/**
 * The persistent class for the PGB_FORMATO_AVALUO database table.
 * 
 */
@Entity
@Table(name="PGB_FORMATO_AVALUO")
@NamedQuery(name = "FormatoAvaluo.findAll", query = "SELECT p FROM FormatoAvaluo p")
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class FormatoAvaluo implements Serializable {
	private static final long serialVersionUID = 1L;

	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_FORMATO_AVALUO_PK = "idFormatoAvaluo";
	public static final String ENTIDAD_PGB_FORMATO_AVALUO_ID_ARCHIVO = "idArchivo";
	public static final String ENTIDAD_PGB_FORMATO_AVALUO_DESCRICPION = "descricpion";
	public static final String ENTIDAD_PGB_FORMATO_AVALUO_FECHA_ACTUALIZACION = "fechaActualizacion";
	public static final String ENTIDAD_PGB_FORMATO_AVALUO_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_FORMATO_AVALUO_FECHA_TRANSACCION = "fechaTransaccion";
    private static final String[] ATRIBUTOS_ENTIDAD_PGB_FORMATO_AVALUO
            = {ENTIDAD_PGB_FORMATO_AVALUO_FECHA_TRANSACCION, ENTIDAD_PGB_FORMATO_AVALUO_DESCRICPION, ENTIDAD_PGB_FORMATO_AVALUO_ID_ARCHIVO, ENTIDAD_PGB_FORMATO_AVALUO_FECHA_ACTUALIZACION, ENTIDAD_PGB_FORMATO_AVALUO_USUARIO_TRANSACCION, ENTIDAD_PGB_FORMATO_AVALUO_PK};

	@Id
	@SequenceGenerator(name="PGB_FORMATO_AVALUO_IDFORMATOAVALUO_GENERATOR", sequenceName="SEQ_PGB_FORMATO_AVALUO", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PGB_FORMATO_AVALUO_IDFORMATOAVALUO_GENERATOR")
	@Column(name="ID_FORMATO_AVALUO", unique=true, nullable=false, precision=5)
	private BigDecimal idFormatoAvaluo;

	@Column(name="DESCRICPION", nullable=false, length=250)
	private String descripcion;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="FECHA_ACTUALIZACION", nullable=false)
	private Date fechaActualizacion;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="FECHA_TRANSACCION", nullable=false)
	private Date fechaTransaccion;

	@Column(name="USUARIO_TRANSACCION", nullable=false, length=15)
	private String usuarioTransaccion;

  @PodamExclude
	@Column(name="ID_ARCHIVO")
	private BigDecimal idArchivo;

	//uni-directional many-to-one association to Archivo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_ARCHIVO", nullable=false, referencedColumnName="ID_ARCHIVO", insertable = false, updatable = false)
	private Archivo formatoExcel;

	// protected region atributos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region atributos adicionales end

    public FormatoAvaluo() {
	// protected region procedimientos adicionales de inicializaci�n on begin
		// Escriba en esta secci�n sus modificaciones

		// protected region procedimientos adicionales de inicializaci�n end
    }

	public BigDecimal getIdFormatoAvaluo() {
		return this.idFormatoAvaluo;
	}

	public void setIdFormatoAvaluo(BigDecimal idFormatoAvaluo) {
		this.idFormatoAvaluo = idFormatoAvaluo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Date getFechaTransaccion() {
		return this.fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public String getUsuarioTransaccion() {
		return this.usuarioTransaccion;
	}

	public void setUsuarioTransaccion(String usuarioTransaccion) {
		this.usuarioTransaccion = usuarioTransaccion;
	}

	public Archivo getFormatoExcel() {
		return this.formatoExcel;
	}

	public void setFormatoExcel(Archivo formatoExcel) {
		this.formatoExcel = formatoExcel;
	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como par�metro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_PGB_FORMATO_AVALUO) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
    }
    
    public static String[] getAtributosEntidadFormatoAvaluo() {
		return ATRIBUTOS_ENTIDAD_PGB_FORMATO_AVALUO;
	}

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idFormatoAvaluo);        
        hash = 37 * hash + Objects.hashCode(this.idArchivo);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + Objects.hashCode(this.fechaActualizacion);
        hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
        hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad FormatoAvaluo que se pasa
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
        final FormatoAvaluo other = (FormatoAvaluo) obj;
        
        if (!Objects.equals(this.idFormatoAvaluo, other.idFormatoAvaluo)) {
            return false;
        }
        
        if (!Objects.equals(this.idArchivo, other.idArchivo)) {
            return false;
        }
        
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaActualizacion, other.fechaActualizacion)) {
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