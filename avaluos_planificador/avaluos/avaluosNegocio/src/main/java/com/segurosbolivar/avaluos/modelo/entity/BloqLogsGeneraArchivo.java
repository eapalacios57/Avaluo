package com.segurosbolivar.avaluos.modelo.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the PGB_BLOQ_LOGS_GENERA_ARCHIVOS database table.
 * 
 */
@Entity
@Table(name="PGB_BLOQ_LOGS_GENERA_ARCHIVOS")
@NamedQuery(name = "BloqLogsGeneraArchivo.findAll", query = "SELECT p FROM BloqLogsGeneraArchivo p")
public class BloqLogsGeneraArchivo implements Serializable {
	private static final long serialVersionUID = 1L;

	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_BLOQ_LOGS_GENERA_ARCHIVOS_PK = "idGeneracionPlano";
	public static final String ENTIDAD_PGB_BLOQ_LOGS_GENERA_ARCHIVOS_FECHA_DESDE = "fechaDesde";
	public static final String ENTIDAD_PGB_BLOQ_LOGS_GENERA_ARCHIVOS_FECHA_HASTA = "fechaHasta";
	public static final String ENTIDAD_PGB_BLOQ_LOGS_GENERA_ARCHIVOS_FECHA_INICIO_CREACION = "fechaInicioCreacion";
	public static final String ENTIDAD_PGB_BLOQ_LOGS_GENERA_ARCHIVOS_FECHA_FIN_CREACION = "fechaFinCreacion";
	public static final String ENTIDAD_PGB_BLOQ_LOGS_GENERA_ARCHIVOS_FECHA_TRANSACCION = "fechaTransaccion";
	public static final String ENTIDAD_PGB_BLOQ_LOGS_GENERA_ARCHIVOS_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_PGB_BLOQ_LOGS_GENERA_ARCHIVOS_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_BLOQ_LOGS_GENERA_ARCHIVOS_ESTADO_REGISTRO = "estadoRegistro";
    private static final String[] ATRIBUTOS_ENTIDAD_PGB_BLOQ_LOGS_GENERA_ARCHIVOS
            = {ENTIDAD_PGB_BLOQ_LOGS_GENERA_ARCHIVOS_PK, ENTIDAD_PGB_BLOQ_LOGS_GENERA_ARCHIVOS_FECHA_DESDE, ENTIDAD_PGB_BLOQ_LOGS_GENERA_ARCHIVOS_USUARIO_CREACION, ENTIDAD_PGB_BLOQ_LOGS_GENERA_ARCHIVOS_FECHA_HASTA, ENTIDAD_PGB_BLOQ_LOGS_GENERA_ARCHIVOS_FECHA_TRANSACCION, ENTIDAD_PGB_BLOQ_LOGS_GENERA_ARCHIVOS_USUARIO_TRANSACCION, ENTIDAD_PGB_BLOQ_LOGS_GENERA_ARCHIVOS_ESTADO_REGISTRO, ENTIDAD_PGB_BLOQ_LOGS_GENERA_ARCHIVOS_FECHA_INICIO_CREACION, ENTIDAD_PGB_BLOQ_LOGS_GENERA_ARCHIVOS_FECHA_FIN_CREACION};

	@Id
	@SequenceGenerator(name="PGB_BLOQ_LOGS_GENERA_ARCHIVOS_IDGENERACIONPLANO_GENERATOR", sequenceName="SEQ_BLOQ_LOGS_GENERA_ARCHIVOS", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PGB_BLOQ_LOGS_GENERA_ARCHIVOS_IDGENERACIONPLANO_GENERATOR")
	@Column(name="ID_GENERACION_PLANO")
	private long idGeneracionPlano;

	@Column(name="ESTADO_REGISTRO")
	private String estadoRegistro;

    @Temporal( TemporalType.DATE)
	@Column(name="FECHA_DESDE")
	private Date fechaDesde;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="FECHA_FIN_CREACION")
	private Date fechaFinCreacion;

    @Temporal( TemporalType.DATE)
	@Column(name="FECHA_HASTA")
	private Date fechaHasta;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="FECHA_INICIO_CREACION")
	private Date fechaInicioCreacion;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="FECHA_TRANSACCION")
	private Date fechaTransaccion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name="USUARIO_TRANSACCION")
	private String usuarioTransaccion;


	// protected region atributos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region atributos adicionales end

    public BloqLogsGeneraArchivo() {
	// protected region procedimientos adicionales de inicializaci�n on begin
		// Escriba en esta secci�n sus modificaciones

		// protected region procedimientos adicionales de inicializaci�n end
    }

	public long getIdGeneracionPlano() {
		return this.idGeneracionPlano;
	}

	public void setIdGeneracionPlano(long idGeneracionPlano) {
		this.idGeneracionPlano = idGeneracionPlano;
	}

	public String getEstadoRegistro() {
		return this.estadoRegistro;
	}

	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	public Date getFechaDesde() {
		return this.fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaFinCreacion() {
		return this.fechaFinCreacion;
	}

	public void setFechaFinCreacion(Date fechaFinCreacion) {
		this.fechaFinCreacion = fechaFinCreacion;
	}

	public Date getFechaHasta() {
		return this.fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Date getFechaInicioCreacion() {
		return this.fechaInicioCreacion;
	}

	public void setFechaInicioCreacion(Date fechaInicioCreacion) {
		this.fechaInicioCreacion = fechaInicioCreacion;
	}

	public Date getFechaTransaccion() {
		return this.fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
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
        for (final String atr : ATRIBUTOS_ENTIDAD_PGB_BLOQ_LOGS_GENERA_ARCHIVOS) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
    }
    
    public static String[] getAtributosEntidadPgbBloqLogsGeneraArchivos() {
		return ATRIBUTOS_ENTIDAD_PGB_BLOQ_LOGS_GENERA_ARCHIVOS;
	}

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idGeneracionPlano);        
        hash = 37 * hash + Objects.hashCode(this.fechaDesde);
        hash = 37 * hash + Objects.hashCode(this.fechaHasta);
        hash = 37 * hash + Objects.hashCode(this.fechaInicioCreacion);
        hash = 37 * hash + Objects.hashCode(this.fechaFinCreacion);
        hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
        hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
        hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
        hash = 37 * hash + Objects.hashCode(this.estadoRegistro);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PgbBloqLogsGeneraArchivos que se pasa
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
        final BloqLogsGeneraArchivo other = (BloqLogsGeneraArchivo) obj;
        
        if (!Objects.equals(this.idGeneracionPlano, other.idGeneracionPlano)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaDesde, other.fechaDesde)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaHasta, other.fechaHasta)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaInicioCreacion, other.fechaInicioCreacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaFinCreacion, other.fechaFinCreacion)) {
            return false;
        }
        
        if (!Objects.equals(this.fechaTransaccion, other.fechaTransaccion)) {
            return false;
        }
        
        if (!Objects.equals(this.usuarioCreacion, other.usuarioCreacion)) {
            return false;
        }
        
        if (!Objects.equals(this.usuarioTransaccion, other.usuarioTransaccion)) {
            return false;
        }
        
        return Objects.equals(this.estadoRegistro, other.estadoRegistro);
                
    }
	
	// protected region metodos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region metodos adicionales end



}