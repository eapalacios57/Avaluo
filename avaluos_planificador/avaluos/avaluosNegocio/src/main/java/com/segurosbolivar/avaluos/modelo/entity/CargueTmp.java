package com.segurosbolivar.avaluos.modelo.entity;

import static org.eclipse.persistence.annotations.CacheType.NONE;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the PGB_CARGUE_TMP database table.
 * 
 */
@Entity
@Table(name="PGB_CARGUE_TMP")
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
@NamedQueries({
@NamedQuery(name = "CargueTmp.findAll", query = "SELECT p FROM CargueTmp p"),
@NamedQuery(name = "CargueTmp.estadoCargue", query = "SELECT p.estadoCargue FROM CargueTmp p WHERE p.numeroRefCargue = :refCargue AND p.consecutivoTmp = :consecutivo"),
@NamedQuery(name = "CargueTmp.obtenerMsjError", query = "SELECT p.mensajeError FROM CargueTmp p WHERE p.numeroRefCargue = :refCargue AND p.consecutivoTmp = :consecutivo"),
@NamedQuery(name = "CargueTmp.obtenerPorReferencia", query = "SELECT p FROM CargueTmp p WHERE p.numeroRefCargue = :refCargue"),
@NamedQuery(name = "CargueTmp.obtenerPorLinea", query = "SELECT p FROM CargueTmp p WHERE p.numeroRefCargue = :refCargue AND p.lineaPlano = :linea")
})
@NamedNativeQueries({
 @NamedNativeQuery(name = "CargueTmp.ejecutarValidador", query = "BEGIN PRC_CARGA_AVALUO_CONSTRUCTOR(pREFERENCIA_CARGUE=>?,pTIPO_CARGUE=>?,pProyecto_constructor=>?); END;"),
 @NamedNativeQuery(name = "CargueTmp.secuenciaCargue", query = "SELECT SEQ_PGB_REFERENCIA_CARGUE.NEXTVAL FROM DUAL")})
public class CargueTmp implements Serializable {
	private static final long serialVersionUID = 1L;

	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_CARGUE_TMP_PK = "consecutivoTmp";
	public static final String ENTIDAD_PGB_CARGUE_TMP_NUMERO_REF_CARGUE = "numeroRefCargue";
	public static final String ENTIDAD_PGB_CARGUE_TMP_LINEA_PLANO = "lineaPlano";
	public static final String ENTIDAD_PGB_CARGUE_TMP_TIPO_CARGUE = "tipoCargue";
	public static final String ENTIDAD_PGB_CARGUE_TMP_ESTADO_CARGUE = "estadoCargue";
	public static final String ENTIDAD_PGB_CARGUE_TMP_FECHA_TRANSACCION = "fechaTransaccion";
	public static final String ENTIDAD_PGB_CARGUE_TMP_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_CARGUE_TMP_MENSAJE_ERROR = "mensajeError";
	public static final String ENTIDAD_PGB_CARGUE_TMP_CONSECUTIVO_BATCH = "consecutivoBatch";
	public static final String ENTIDAD_PGB_CARGUE_TMP_NOMBRE_ARCHIVO = "nombreArchivo";
	public static final String ENTIDAD_PGB_CARGUE_TMP_CONTENIDO_LINEA_PLANO = "contenidoLineaPlano";
	public static final String ENTIDAD_PGB_CARGUE_TMP_TIPO_PROYECTO = "tipoProyecto";
    private static final String[] ATRIBUTOS_ENTIDAD_PGB_CARGUE_TMP
            = {ENTIDAD_PGB_CARGUE_TMP_ESTADO_CARGUE, ENTIDAD_PGB_CARGUE_TMP_NOMBRE_ARCHIVO, ENTIDAD_PGB_CARGUE_TMP_TIPO_CARGUE, ENTIDAD_PGB_CARGUE_TMP_FECHA_TRANSACCION, ENTIDAD_PGB_CARGUE_TMP_MENSAJE_ERROR, ENTIDAD_PGB_CARGUE_TMP_CONSECUTIVO_BATCH, ENTIDAD_PGB_CARGUE_TMP_PK, ENTIDAD_PGB_CARGUE_TMP_NUMERO_REF_CARGUE, ENTIDAD_PGB_CARGUE_TMP_LINEA_PLANO, ENTIDAD_PGB_CARGUE_TMP_TIPO_PROYECTO, ENTIDAD_PGB_CARGUE_TMP_CONTENIDO_LINEA_PLANO, ENTIDAD_PGB_CARGUE_TMP_USUARIO_TRANSACCION};

	@Id
	@SequenceGenerator(name="PGB_CARGUE_TMP_CONSECUTIVOTMP_GENERATOR", sequenceName="SEQ_PGB_CARGUE_TMP", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PGB_CARGUE_TMP_CONSECUTIVOTMP_GENERATOR")
	@Column(name="CONSECUTIVO_TMP")
	private Long consecutivoTmp;
	
	@Lob
	@Column(name="CONTENIDO_LINEA_PLANO")
	private String contenidoLineaPlano;

	@Column(name="ESTADO_CARGUE")
	private String estadoCargue;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="FECHA_TRANSACCION")
	private Date fechaTransaccion;

	@Column(name="LINEA_PLANO")
	private Long lineaPlano;

	@Column(name="NUMERO_REF_CARGUE")
	private Long numeroRefCargue;

	@Column(name="TIPO_CARGUE")
	private String tipoCargue;

	@Column(name="USUARIO_TRANSACCION")
	private String usuarioTransaccion;

	@Column(name="MENSAJE_ERROR")
	private String mensajeError;

	@Column(name="NOMBRE_ARCHIVO")
	private String nombreArchivo;
	
	@Column(name="CONSECUTIVO_BATCH", precision=5)
	private Long consecutivoBatch;
	
	@Column(name="TIPO_PROYECTO", precision=5)
	private Long tipoProyecto;
	
	// protected region atributos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region atributos adicionales end
	
    public CargueTmp() {
	// protected region procedimientos adicionales de inicializaci�n on begin
		// Escriba en esta secci�n sus modificaciones

		// protected region procedimientos adicionales de inicializaci�n end
    }

	public Long getConsecutivoTmp() {
		return this.consecutivoTmp;
	}

	public void setConsecutivoTmp(Long consecutivoTmp) {
		this.consecutivoTmp = consecutivoTmp;
	}

	public String getContenidoLineaPlano() {
		return this.contenidoLineaPlano;
	}

	public void setContenidoLineaPlano(String contenidoLineaPlano) {
		this.contenidoLineaPlano = contenidoLineaPlano;
	}

	public String getEstadoCargue() {
		return this.estadoCargue;
	}

	public void setEstadoCargue(String estadoCargue) {
		this.estadoCargue = estadoCargue;
	}

	public Date getFechaTransaccion() {
		return this.fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public Long getLineaPlano() {
		return this.lineaPlano;
	}

	public void setLineaPlano(Long lineaPlano) {
		this.lineaPlano = lineaPlano;
	}

	public Long getNumeroRefCargue() {
		return this.numeroRefCargue;
	}

	public void setNumeroRefCargue(Long numeroRefCargue) {
		this.numeroRefCargue = numeroRefCargue;
	}

	public String getTipoCargue() {
		return this.tipoCargue;
	}

	public void setTipoCargue(String tipoCargue) {
		this.tipoCargue = tipoCargue;
	}

	public String getUsuarioTransaccion() {
		return this.usuarioTransaccion;
	}

	public void setUsuarioTransaccion(String usuarioTransaccion) {
		this.usuarioTransaccion = usuarioTransaccion;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public Long getConsecutivoBatch() {
		return consecutivoBatch;
	}

	public void setConsecutivoBatch(Long consecutivoBatch) {
		this.consecutivoBatch = consecutivoBatch;
	}

	public Long getTipoProyecto() {
		return tipoProyecto;
	}

	public void setTipoProyecto(Long tipoProyecto) {
		this.tipoProyecto = tipoProyecto;
	}
	


	/**
     * Verifica si la entidad contiene el atributo que se pasa como par�metro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_PGB_CARGUE_TMP) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
    }
    
    public static String[] getAtributosEntidadPgbCargueTmp() {
		return ATRIBUTOS_ENTIDAD_PGB_CARGUE_TMP;
	}

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.consecutivoTmp);        
        hash = 37 * hash + Objects.hashCode(this.numeroRefCargue);
        hash = 37 * hash + Objects.hashCode(this.lineaPlano);
        hash = 37 * hash + Objects.hashCode(this.tipoCargue);
        hash = 37 * hash + Objects.hashCode(this.estadoCargue);
        hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
        hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
        hash = 37 * hash + Objects.hashCode(this.mensajeError);
        hash = 37 * hash + Objects.hashCode(this.consecutivoBatch);
        hash = 37 * hash + Objects.hashCode(this.nombreArchivo);
        hash = 37 * hash + Objects.hashCode(this.contenidoLineaPlano);
        hash = 37 * hash + Objects.hashCode(this.tipoProyecto);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PgbCargueTmp que se pasa
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
        final CargueTmp other = (CargueTmp) obj;
        
        if (!Objects.equals(this.consecutivoTmp, other.consecutivoTmp)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroRefCargue, other.numeroRefCargue)) {
            return false;
        }
        
        if (!Objects.equals(this.lineaPlano, other.lineaPlano)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoCargue, other.tipoCargue)) {
            return false;
        }
        
        if (!Objects.equals(this.estadoCargue, other.estadoCargue)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaTransaccion, other.fechaTransaccion)) {
            return false;
        }
        
        if (!Objects.equals(this.usuarioTransaccion, other.usuarioTransaccion)) {
            return false;
        }
        
        if (!Objects.equals(this.mensajeError, other.mensajeError)) {
            return false;
        }
        
        if (!Objects.equals(this.consecutivoBatch, other.consecutivoBatch)) {
            return false;
        }
        
        if (!Objects.equals(this.nombreArchivo, other.nombreArchivo)) {
            return false;
        }
        
        if (!Objects.equals(this.contenidoLineaPlano, other.contenidoLineaPlano)) {
            return false;
        }
        
        return Objects.equals(this.tipoProyecto, other.tipoProyecto);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region metodos adicionales end
}