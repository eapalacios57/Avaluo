package com.segurosbolivar.avaluos.modelo.entity;

import static org.eclipse.persistence.annotations.CacheType.NONE;

import java.io.Serializable;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import uk.co.jemos.podam.annotations.PodamExclude;

/**
 * The persistent class for the PGB_PERITOS_EMPRESAS database table.
 * 
 */
@Entity
@Table(name = "PGB_PERITOS_EMPRESAS")
@NamedQueries({
	@NamedQuery(name = "PeritosEmpresa.cantidadValores", query = "SELECT COUNT( p.idPeritoEmpresa) FROM PeritosEmpresa p WHERE p.empresasAvaluo = :empresa"),
	@NamedQuery(name = "PeritosEmpresa.findAll", query = "SELECT p FROM PeritosEmpresa p WHERE p.empresasAvaluo = :empresa order by p.numeroDocumento"),
	@NamedQuery(name = "PeritosEmpresa.todos", query = "SELECT p FROM PeritosEmpresa p  order by p.numeroDocumento")})
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class PeritosEmpresa implements Serializable, Cloneable{
	private static final long serialVersionUID = 1L;

	//Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_PERITOS_EMPRESAS_PK = "idPeritoEmpresa";
	public static final String ENTIDAD_PGB_PERITOS_EMPRESAS_ID_EMPRESA_AVALUO = "idEmpresaAvaluo";
	public static final String ENTIDAD_PGB_PERITOS_EMPRESAS_TIPO_DOCUMENTO = "tipoDocumento";
	public static final String ENTIDAD_PGB_PERITOS_EMPRESAS_NUMERO_DOCUMENTO = "numeroDocumento";
	public static final String ENTIDAD_PGB_PERITOS_EMPRESAS_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_PGB_PERITOS_EMPRESAS_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_PGB_PERITOS_EMPRESAS_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_PERITOS_EMPRESAS_FECHA_TRANSACCION = "fechaTransaccion";
	public static final String ENTIDAD_PGB_PERITOS_EMPRESAS_ESTADO_ASOCIACION = "estadoAsociacion";
	public static final String ENTIDAD_PGB_PERITOS_EMPRESAS_NOMBRE_PERITO = "nombrePerito";
    private static final String[] ATRIBUTOS_ENTIDAD_PGB_PERITOS_EMPRESAS
            = {ENTIDAD_PGB_PERITOS_EMPRESAS_PK, ENTIDAD_PGB_PERITOS_EMPRESAS_USUARIO_TRANSACCION, ENTIDAD_PGB_PERITOS_EMPRESAS_USUARIO_CREACION, ENTIDAD_PGB_PERITOS_EMPRESAS_ESTADO_ASOCIACION, ENTIDAD_PGB_PERITOS_EMPRESAS_ID_EMPRESA_AVALUO, ENTIDAD_PGB_PERITOS_EMPRESAS_TIPO_DOCUMENTO, ENTIDAD_PGB_PERITOS_EMPRESAS_FECHA_CREACION, ENTIDAD_PGB_PERITOS_EMPRESAS_FECHA_TRANSACCION, ENTIDAD_PGB_PERITOS_EMPRESAS_NUMERO_DOCUMENTO};

	@Id
	@SequenceGenerator(name = "PGB_PERITOS_EMPRESAS_IDPERITOEMPRESA_GENERATOR", sequenceName = "SEQ_PGB_PERITOS_EMPRESAS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGB_PERITOS_EMPRESAS_IDPERITOEMPRESA_GENERATOR")
	@Column(name = "ID_PERITO_EMPRESA")
	private Long idPeritoEmpresa;

	@Column(name = "ESTADO_ASOCIACION")
	private String estadoAsociacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_TRANSACCION")
	private Date fechaTransaccion;

	@Column(name = "NUMERO_DOCUMENTO")
	private Long numeroDocumento;

	@Column(name = "TIPO_DOCUMENTO")
	private String tipoDocumento;

	@Column(name = "USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name = "USUARIO_TRANSACCION")
	private String usuarioTransaccion;
	
	@Column(name = "NOMBRE_PERITO")
	private String nombrePerito;

   @PodamExclude
	@Column(name="ID_EMPRESA_AVALUO")
	private Long idEmpresaAvaluo;

	// bi-directional many-to-one association to EmpresasAvaluos
   @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_EMPRESA_AVALUO", referencedColumnName="ID_EMPRESA_AVALUO", insertable = false, updatable = false)
	private EmpresasAvaluos empresasAvaluo;
	
//	@Transient
//	private String nombre;

	// protected region atributos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region atributos adicionales end
	

	public PeritosEmpresa() {
	// protected region procedimientos adicionales de inicializaci�n on begin
		// Escriba en esta secci�n sus modificaciones

		// protected region procedimientos adicionales de inicializaci�n end
	}

	@Override
	public PeritosEmpresa clone() throws CloneNotSupportedException {
			return (PeritosEmpresa) super.clone();
	}

	public Long getIdPeritoEmpresa() {
		return this.idPeritoEmpresa;
	}

	public void setIdPeritoEmpresa(Long idPeritoEmpresa) {
		this.idPeritoEmpresa = idPeritoEmpresa;
	}

	public String getEstadoAsociacion() {
		return this.estadoAsociacion;
	}

	public void setEstadoAsociacion(String estadoAsociacion) {
		this.estadoAsociacion = estadoAsociacion;
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

	public Long getNumeroDocumento() {
		return this.numeroDocumento;
	}

	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
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

	public EmpresasAvaluos getEmpresasAvaluo() {
		return this.empresasAvaluo;
	}

	public void setEmpresasAvaluo(EmpresasAvaluos empresasAvaluo) {
		this.empresasAvaluo = empresasAvaluo;

	}

	/**
     * Verifica si la entidad contiene el atributo que se pasa como par�metro
     *
     * @param atributo Nombre del atributo a validar
     * @return Verdadero si la entidad contiene al atributo.
     */
    public static boolean contieneAtributo(String atributo) {
		
		boolean contiene = false;
        for (final String atr : ATRIBUTOS_ENTIDAD_PGB_PERITOS_EMPRESAS) {
            if (atr.equals(atributo)) {
                contiene = true;
            }
        }

        return contiene;
    }
    
    public static String[] getAtributosEntidadPgbPeritosEmpresas() {
		return ATRIBUTOS_ENTIDAD_PGB_PERITOS_EMPRESAS;
	}

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 37 * hash + Objects.hashCode(this.idPeritoEmpresa);        
        hash = 37 * hash + Objects.hashCode(this.idEmpresaAvaluo);
        hash = 37 * hash + Objects.hashCode(this.tipoDocumento);
        hash = 37 * hash + Objects.hashCode(this.numeroDocumento);
        hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
        hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
        hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
        hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
        hash = 37 * hash + Objects.hashCode(this.estadoAsociacion);
        
        return hash;
    }

	/**
     * Valida la igualdad de la instancia de la entidad PgbPeritosEmpresas que se pasa
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
        final PeritosEmpresa other = (PeritosEmpresa) obj;
        
        if (!Objects.equals(this.idPeritoEmpresa, other.idPeritoEmpresa)) {
            return false;
        }
        
        if (!Objects.equals(this.idEmpresaAvaluo, other.idEmpresaAvaluo)) {
            return false;
        }
        
        if (!Objects.equals(this.tipoDocumento, other.tipoDocumento)) {
            return false;
        }
        
        if (!Objects.equals(this.numeroDocumento, other.numeroDocumento)) {
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
        
        if (!Objects.equals(this.fechaTransaccion, other.fechaTransaccion)) {
            return false;
        }
        
        return Objects.equals(this.estadoAsociacion, other.estadoAsociacion);
                
    }

	public String getNombrePerito() {
		return nombrePerito;
	}

	public void setNombrePerito(String nombrePerito) {
		this.nombrePerito = nombrePerito;
	}

	public Long getIdEmpresaAvaluo() {
		return idEmpresaAvaluo;
	}

	public void setIdEmpresaAvaluo(Long idEmpresaAvaluo) {
		this.idEmpresaAvaluo = idEmpresaAvaluo;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region metodos adicionales end



}