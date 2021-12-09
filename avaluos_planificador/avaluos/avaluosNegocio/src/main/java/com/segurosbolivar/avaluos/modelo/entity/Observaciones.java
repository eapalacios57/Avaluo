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
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import uk.co.jemos.podam.annotations.PodamExclude;

/**
 * The persistent class for the PGB_OBSERVACIONES database table.
 * 
 */
@Entity
@Table(name = "PGB_OBSERVACIONES")
@NamedQuery(name = "Observaciones.idAvaluo", query = "SELECT p FROM Observaciones p WHERE p.idAvaluo=:idAvaluo")
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class Observaciones implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_OBSERVACIONES_PK = "idObservacion";
	public static final String ENTIDAD_PGB_OBSERVACIONES_ID_AVALUO = "idAvaluo";
	public static final String ENTIDAD_PGB_OBSERVACIONES_T_OBSERVAVALUO = "tObservavaluo";
	public static final String ENTIDAD_PGB_OBSERVACIONES_T_OTRASDIR = "tOtrasdir";
	public static final String ENTIDAD_PGB_OBSERVACIONES_T_DIRANEXOS = "tDiranexos";
	public static final String ENTIDAD_PGB_OBSERVACIONES_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_PGB_OBSERVACIONES_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_PGB_OBSERVACIONES_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_OBSERVACIONES_FECHA_TRANSACCION = "fechaTransaccion";
	private static final String[] ATRIBUTOS_ENTIDAD_PGB_OBSERVACIONES = { ENTIDAD_PGB_OBSERVACIONES_PK,
			ENTIDAD_PGB_OBSERVACIONES_ID_AVALUO, ENTIDAD_PGB_OBSERVACIONES_T_DIRANEXOS,
			ENTIDAD_PGB_OBSERVACIONES_FECHA_TRANSACCION, ENTIDAD_PGB_OBSERVACIONES_T_OBSERVAVALUO,
			ENTIDAD_PGB_OBSERVACIONES_FECHA_CREACION, ENTIDAD_PGB_OBSERVACIONES_T_OTRASDIR,
			ENTIDAD_PGB_OBSERVACIONES_USUARIO_CREACION, ENTIDAD_PGB_OBSERVACIONES_USUARIO_TRANSACCION};

	// Dnino 17 Jul 2016 Se deshabilita validacion para tiempo de respuesta
	// @PrePersist
	// protected void onCreate() throws Exception {
	// BigDecimal max =
	// UtilJpa.getMaxTableValue("PGB_OBSERVACIONES","ID_OBSERVACION");
	// if(idObservacion.compareTo(max)<1)
	// {
	// BigDecimal seqNew =
	// UtilJpa.getAlterSequenceValueMaxTable(max,"SEQ_PGB_OBSERVACIONES");
	// this.setIdObservacion(seqNew);
	// }
	// }

	@Id
	@SequenceGenerator(name = "PGB_OBSERVACIONES_IDOBSERVACION_GENERATOR", sequenceName = "SEQ_PGB_OBSERVACIONES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGB_OBSERVACIONES_IDOBSERVACION_GENERATOR")
	@Column(name = "ID_OBSERVACION", unique = true, nullable = false, precision = 10)
	private Long idObservacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CREACION", nullable = false)
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_TRANSACCION", nullable = false)
	private Date fechaTransaccion;

	@Column(name = "T_DIRANEXOS", length = 100)
	private String direccionAnexos;

	@Column(name = "T_OBSERVAVALUO", length = 2000)
	private String observacionAvaluo;

	@Column(name = "T_OTRASDIR", length = 100)
	private String otrasDirecciones;

	@Column(name = "USUARIO_CREACION", nullable = false, length = 15)
	private String usuarioCreacion;

	@Column(name = "USUARIO_TRANSACCION", nullable = false, length = 15)
	private String usuarioTransaccion;

	@PodamExclude
	@Column(name = "ID_AVALUO")
	private Long idAvaluo;

	// bi-directional many-to-one association to Avaluo
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_AVALUO", referencedColumnName = "ID_AVALUO", insertable = false, updatable = false)
	private Avaluo avaluo;
	
	@PodamExclude
	@Column(name = "ID_ARCHIVO")
	private Long idArchivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ARCHIVO", referencedColumnName = "ID_ARCHIVO", insertable = false, updatable = false)
	private Archivo archivo;
	


	// protected region atributos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region atributos adicionales end

	public Observaciones() {
		// protected region procedimientos adicionales de inicializaci�n on
		// begin
		// Escriba en esta secci�n sus modificaciones

		// protected region procedimientos adicionales de inicializaci�n end
	}
	
	@Override
	public Observaciones clone() throws CloneNotSupportedException {
		return (Observaciones) super.clone();
	}
	
	

	public Long getIdArchivo() {
	    return idArchivo;
	}



	public void setIdArchivo(Long idArchivo) {
	    this.idArchivo = idArchivo;
	}



	public Archivo getArchivo() {
	    return archivo;
	}



	public void setArchivo(Archivo archivo) {
	    this.archivo = archivo;
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

	public String getDireccionAnexos() {
		return this.direccionAnexos;
	}

	public void setDireccionAnexos(String direccionAnexos) {
		this.direccionAnexos = direccionAnexos;
	}

	public String getObservacionAvaluo() {
		return this.observacionAvaluo;
	}

	public void setObservacionAvaluo(String observacionAvaluo) {
		this.observacionAvaluo = observacionAvaluo;
	}

	public String getOtrasDirecciones() {
		return this.otrasDirecciones;
	}

	public void setOtrasDirecciones(String otrasDirecciones) {
		this.otrasDirecciones = otrasDirecciones;
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

	public Avaluo getAvaluo() {
		return this.avaluo;
	}

	public void setAvaluo(Avaluo avaluo) {
		this.avaluo = avaluo;
	}
	

	/**
	 * Verifica si la entidad contiene el atributo que se pasa como par�metro
	 *
	 * @param atributo
	 *            Nombre del atributo a validar
	 * @return Verdadero si la entidad contiene al atributo.
	 */
	public static boolean contieneAtributo(String atributo) {

		boolean contiene = false;
		for (final String atr : ATRIBUTOS_ENTIDAD_PGB_OBSERVACIONES) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	public static String[] getAtributosEntidadObservaciones() {
		return ATRIBUTOS_ENTIDAD_PGB_OBSERVACIONES;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idObservacion);
		hash = 37 * hash + Objects.hashCode(this.idAvaluo);
		hash = 37 * hash + Objects.hashCode(this.observacionAvaluo);
		hash = 37 * hash + Objects.hashCode(this.otrasDirecciones);
		hash = 37 * hash + Objects.hashCode(this.direccionAnexos);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad Observaciones que se
	 * pasa como par�metro comprobando que comparten los mismos valores en cada
	 * uno de sus atributos. Solo se tienen en cuenta los atributos simples, es
	 * decir, se omiten aquellos que definen una relaci�n con otra tabla.
	 *
	 * @param obj
	 *            Instancia de la categor�a a comprobar iguales.
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
		final Observaciones other = (Observaciones) obj;

		if (!Objects.equals(this.idObservacion, other.idObservacion)) {
			return false;
		}

		if (!Objects.equals(this.idAvaluo, other.idAvaluo)) {
			return false;
		}

		if (!Objects.equals(this.observacionAvaluo, other.observacionAvaluo)) {
			return false;
		}

		if (!Objects.equals(this.otrasDirecciones, other.otrasDirecciones)) {
			return false;
		}

		if (!Objects.equals(this.direccionAnexos, other.direccionAnexos)) {
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

	public Long getIdAvaluo() {
		return idAvaluo;
	}

	public void setIdAvaluo(Long idAvaluo) {
		this.idAvaluo = idAvaluo;
	}

	public void setIdObservacion(Long idObservacion) {
		this.idObservacion = idObservacion;
	}

	public Long getIdObservacion() {
		return idObservacion;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region metodos adicionales end
}