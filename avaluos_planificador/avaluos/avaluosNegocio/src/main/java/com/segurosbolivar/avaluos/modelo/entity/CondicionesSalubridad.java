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
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import uk.co.jemos.podam.annotations.PodamExclude;

/**
 * The persistent class for the PGB_CONDSALUBRIDAD database table.
 * 
 */
@Entity
@Table(name = "PGB_CONDSALUBRIDAD")
@NamedQuery(name = "CondicionesSalubridad.idAvaluo", query = "SELECT p FROM CondicionesSalubridad p WHERE p.idAvaluo =:idAvaluo")
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class CondicionesSalubridad implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_CONDSALUBRIDAD_PK = "idCondsalubridad";
	public static final String ENTIDAD_PGB_CONDSALUBRIDAD_ID_AVALUO = "idAvaluo";
	public static final String ENTIDAD_PGB_CONDSALUBRIDAD_A_REQUIERE_COND_SALUB = "aRequiereCondSalub";
	public static final String ENTIDAD_PGB_CONDSALUBRIDAD_C_SALUBRIDAD = "cSalubridad";
	public static final String ENTIDAD_PGB_CONDSALUBRIDAD_K_AMBARBORIZA = "kAmbarboriza";
	public static final String ENTIDAD_PGB_CONDSALUBRIDAD_K_AMBPARQUES = "kAmbparques";
	public static final String ENTIDAD_PGB_CONDSALUBRIDAD_K_AMBZVERDE = "kAmbzverde";
	public static final String ENTIDAD_PGB_CONDSALUBRIDAD_T_AMBOTRO = "tAmbotro";
	public static final String ENTIDAD_PGB_CONDSALUBRIDAD_K_PORAIRE = "kPoraire";
	public static final String ENTIDAD_PGB_CONDSALUBRIDAD_K_BASURA = "kBasura";
	public static final String ENTIDAD_PGB_CONDSALUBRIDAD_K_INSERGURIDAD = "kInserguridad";
	public static final String ENTIDAD_PGB_CONDSALUBRIDAD_K_RUIDO = "kRuido";
	public static final String ENTIDAD_PGB_CONDSALUBRIDAD_K_AGUASHERV = "kAguasherv";
	public static final String ENTIDAD_PGB_CONDSALUBRIDAD_T_AMBNEGOTRO = "tAmbnegotro";
	public static final String ENTIDAD_PGB_CONDSALUBRIDAD_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_PGB_CONDSALUBRIDAD_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_PGB_CONDSALUBRIDAD_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_CONDSALUBRIDAD_FECHA_TRANSACCION = "fechaTransaccion";
	private static final String[] ATRIBUTOS_ENTIDAD_PGB_CONDSALUBRIDAD = { ENTIDAD_PGB_CONDSALUBRIDAD_ID_AVALUO,
			ENTIDAD_PGB_CONDSALUBRIDAD_K_PORAIRE, ENTIDAD_PGB_CONDSALUBRIDAD_K_RUIDO,
			ENTIDAD_PGB_CONDSALUBRIDAD_T_AMBNEGOTRO, ENTIDAD_PGB_CONDSALUBRIDAD_K_AGUASHERV,
			ENTIDAD_PGB_CONDSALUBRIDAD_K_AMBARBORIZA, ENTIDAD_PGB_CONDSALUBRIDAD_K_AMBPARQUES,
			ENTIDAD_PGB_CONDSALUBRIDAD_FECHA_CREACION, ENTIDAD_PGB_CONDSALUBRIDAD_A_REQUIERE_COND_SALUB,
			ENTIDAD_PGB_CONDSALUBRIDAD_T_AMBOTRO, ENTIDAD_PGB_CONDSALUBRIDAD_K_INSERGURIDAD,
			ENTIDAD_PGB_CONDSALUBRIDAD_K_BASURA, ENTIDAD_PGB_CONDSALUBRIDAD_K_AMBZVERDE,
			ENTIDAD_PGB_CONDSALUBRIDAD_USUARIO_CREACION, ENTIDAD_PGB_CONDSALUBRIDAD_C_SALUBRIDAD,
			ENTIDAD_PGB_CONDSALUBRIDAD_PK, ENTIDAD_PGB_CONDSALUBRIDAD_USUARIO_TRANSACCION,
			ENTIDAD_PGB_CONDSALUBRIDAD_FECHA_TRANSACCION };

	// Dnino 17 Jul 2016 Se deshabilita validacion para tiempo de respuesta
	// @PrePersist
	// protected void onCreate() throws Exception {
	// Long max =
	// UtilJpa.getMaxTableValue("PGB_CONDSALUBRIDAD","ID_CONDSALUBRIDAD");
	// if(idCondicionSalubridad.compareTo(max)<1)
	// {
	// Long seqNew =
	// UtilJpa.getAlterSequenceValueMaxTable(max,"SEQ_PGB_CONDSALUBRIDAD");
	// this.setIdCondicionSalubridad(seqNew);
	// }
	// }

	@Id
	@SequenceGenerator(name = "PGB_CONDSALUBRIDAD_IDCONDICIONSALUBRIDAD_GENERATOR", sequenceName = "SEQ_PGB_CONDSALUBRIDAD", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGB_CONDSALUBRIDAD_IDCONDICIONSALUBRIDAD_GENERATOR")
	@Column(name = "ID_CONDSALUBRIDAD", unique = true, nullable = false, precision = 10)
	private Long idCondicionSalubridad;

	@Column(name = "A_REQUIERE_COND_SALUB", precision = 3)
	private Long requiereCondicionesSalubridad;

	@Column(name = "C_SALUBRIDAD", precision = 3)
	private Long condicionSalubridad;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CREACION", nullable = false)
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_TRANSACCION", nullable = false)
	private Date fechaTransaccion;

	@Column(name = "K_AGUASHERV", precision = 3)
	private Long aguasServidas;

	@Column(name = "K_AMBARBORIZA", precision = 3)
	private Long ambientalArborizacion;

	@Column(name = "K_AMBPARQUES", precision = 3)
	private Long ambientalParques;

	@Column(name = "K_AMBZVERDE", precision = 3)
	private Long ambientalZonaVerde;

	@Column(name = "K_BASURA", precision = 3)
	private Long impactoNegativoBasura;

	@Column(name = "K_INSERGURIDAD", precision = 3)
	private Long impactoNegativoInseguridad;

	@Column(name = "K_PORAIRE", precision = 3)
	private Long impactoNegativoPorAire;

	@Column(name = "K_RUIDO", precision = 3)
	private Long impactoNegativoRuido;

	@Column(name = "T_AMBNEGOTRO", length = 100)
	private String impactoNegativoOtros;

	@Column(name = "T_AMBOTRO", length = 100)
	private String ambientalOtros;

	@Column(name = "USUARIO_CREACION", nullable = false, length = 15)
	private String usuarioCreacion;

	@Column(name = "USUARIO_TRANSACCION", nullable = false, length = 15)
	private String usuarioTransaccion;

	@PodamExclude
	@Column(name = "ID_AVALUO")
	private Long idAvaluo;

	// bi-directional many-to-one association to Avaluo
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_AVALUO", nullable = false, referencedColumnName = "ID_AVALUO", insertable = false, updatable = false)
	private Avaluo avaluo;
	
	@Transient
	private String requiereCondicionesSalubridadDescripcion; // AFIRMAR
	@Transient
	private String impactoNegativoRuidoDescripcion; // AFIRMAR2
	@Transient
	private String impactoNegativoPorAireDescripcion; // AFIRMAR2
	@Transient
	private String impactoNegativoInseguridadDescripcion; // AFIRMAR2
	@Transient
	private String impactoNegativoBasuraDescripcion; // AFIRMAR2
	@Transient
	private String ambientalZonaVerdeDescripcion; // AFIRMAR2
	@Transient
	private String ambientalParquesDescripcion; // AFIRMAR2
	@Transient
	private String ambientalArborizacionDescripcion; // AFIRMAR2
	@Transient
	private String aguasServidasDescripcion; // AFIRMAR2
	@Transient
	private String condicionesSalubridadDescripcion; // MBR

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end

	public CondicionesSalubridad() {
		// protected region procedimientos adicionales de inicialización on
		// begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
	}
	
	@Override
	public CondicionesSalubridad clone() throws CloneNotSupportedException {
		return (CondicionesSalubridad) super.clone();
	}

	public Long getIdCondicionSalubridad() {
		return this.idCondicionSalubridad;
	}

	public void setIdCondicionSalubridad(Long idCondicionSalubridad) {
		this.idCondicionSalubridad = idCondicionSalubridad;
	}

	public Long getRequiereCondicionesSalubridad() {
		return this.requiereCondicionesSalubridad;
	}

	public void setRequiereCondicionesSalubridad(Long requiereCondicionesSalubridad) {
		this.requiereCondicionesSalubridad = requiereCondicionesSalubridad;
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

	public Long getAguasServidas() {
		return this.aguasServidas;
	}

	public void setAguasServidas(Long aguasServidas) {
		this.aguasServidas = aguasServidas;
	}

	public Long getAmbientalArborizacion() {
		return this.ambientalArborizacion;
	}

	public void setAmbientalArborizacion(Long ambientalArborizacion) {
		this.ambientalArborizacion = ambientalArborizacion;
	}

	public Long getAmbientalParques() {
		return this.ambientalParques;
	}

	public void setAmbientalParques(Long ambientalParques) {
		this.ambientalParques = ambientalParques;
	}

	public Long getAmbientalZonaVerde() {
		return this.ambientalZonaVerde;
	}

	public void setAmbientalZonaVerde(Long ambientalZonaVerde) {
		this.ambientalZonaVerde = ambientalZonaVerde;
	}

	public Long getImpactoNegativoBasura() {
		return this.impactoNegativoBasura;
	}

	public void setImpactoNegativoBasura(Long impactoNegativoBasura) {
		this.impactoNegativoBasura = impactoNegativoBasura;
	}

	public Long getImpactoNegativoInseguridad() {
		return this.impactoNegativoInseguridad;
	}

	public void setImpactoNegativoInseguridad(Long impactoNegativoInseguridad) {
		this.impactoNegativoInseguridad = impactoNegativoInseguridad;
	}

	public Long getImpactoNegativoPorAire() {
		return this.impactoNegativoPorAire;
	}

	public void setImpactoNegativoPorAire(Long impactoNegativoPorAire) {
		this.impactoNegativoPorAire = impactoNegativoPorAire;
	}

	public Long getImpactoNegativoRuido() {
		return this.impactoNegativoRuido;
	}

	public void setImpactoNegativoRuido(Long impactoNegativoRuido) {
		this.impactoNegativoRuido = impactoNegativoRuido;
	}

	public String getImpactoNegativoOtros() {
		return this.impactoNegativoOtros;
	}

	public void setImpactoNegativoOtros(String impactoNegativoOtros) {
		this.impactoNegativoOtros = impactoNegativoOtros;
	}

	public String getAmbientalOtros() {
		return this.ambientalOtros;
	}

	public void setAmbientalOtros(String ambientalOtros) {
		this.ambientalOtros = ambientalOtros;
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
	 * Verifica si la entidad contiene el atributo que se pasa como parámetro
	 *
	 * @param atributo
	 *            Nombre del atributo a validar
	 * @return Verdadero si la entidad contiene al atributo.
	 */
	public static boolean contieneAtributo(String atributo) {

		boolean contiene = false;
		for (final String atr : ATRIBUTOS_ENTIDAD_PGB_CONDSALUBRIDAD) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	public static String[] getAtributosEntidadCondicionesSalubridad() {
		return ATRIBUTOS_ENTIDAD_PGB_CONDSALUBRIDAD;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idCondicionSalubridad);
		hash = 37 * hash + Objects.hashCode(this.idAvaluo);
		hash = 37 * hash + Objects.hashCode(this.requiereCondicionesSalubridad);
		hash = 37 * hash + Objects.hashCode(this.condicionSalubridad);
		hash = 37 * hash + Objects.hashCode(this.ambientalArborizacion);
		hash = 37 * hash + Objects.hashCode(this.ambientalParques);
		hash = 37 * hash + Objects.hashCode(this.ambientalZonaVerde);
		hash = 37 * hash + Objects.hashCode(this.ambientalOtros);
		hash = 37 * hash + Objects.hashCode(this.impactoNegativoPorAire);
		hash = 37 * hash + Objects.hashCode(this.impactoNegativoBasura);
		hash = 37 * hash + Objects.hashCode(this.impactoNegativoInseguridad);
		hash = 37 * hash + Objects.hashCode(this.impactoNegativoRuido);
		hash = 37 * hash + Objects.hashCode(this.aguasServidas);
		hash = 37 * hash + Objects.hashCode(this.impactoNegativoOtros);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad CondicionesSalubridad
	 * que se pasa como parámetro comprobando que comparten los mismos valores
	 * en cada uno de sus atributos. Solo se tienen en cuenta los atributos
	 * simples, es decir, se omiten aquellos que definen una relación con otra
	 * tabla.
	 *
	 * @param obj
	 *            Instancia de la categoría a comprobar iguales.
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
		final CondicionesSalubridad other = (CondicionesSalubridad) obj;

		if (!Objects.equals(this.idCondicionSalubridad, other.idCondicionSalubridad)) {
			return false;
		}

		if (!Objects.equals(this.idAvaluo, other.idAvaluo)) {
			return false;
		}

		if (!Objects.equals(this.requiereCondicionesSalubridad, other.requiereCondicionesSalubridad)) {
			return false;
		}

		if (!Objects.equals(this.condicionSalubridad, other.condicionSalubridad)) {
			return false;
		}

		if (!Objects.equals(this.ambientalArborizacion, other.ambientalArborizacion)) {
			return false;
		}

		if (!Objects.equals(this.ambientalParques, other.ambientalParques)) {
			return false;
		}

		if (!Objects.equals(this.ambientalZonaVerde, other.ambientalZonaVerde)) {
			return false;
		}

		if (!Objects.equals(this.ambientalOtros, other.ambientalOtros)) {
			return false;
		}

		if (!Objects.equals(this.impactoNegativoPorAire, other.impactoNegativoPorAire)) {
			return false;
		}

		if (!Objects.equals(this.impactoNegativoBasura, other.impactoNegativoBasura)) {
			return false;
		}

		if (!Objects.equals(this.impactoNegativoInseguridad, other.impactoNegativoInseguridad)) {
			return false;
		}

		if (!Objects.equals(this.impactoNegativoRuido, other.impactoNegativoRuido)) {
			return false;
		}

		if (!Objects.equals(this.aguasServidas, other.aguasServidas)) {
			return false;
		}

		if (!Objects.equals(this.impactoNegativoOtros, other.impactoNegativoOtros)) {
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

	public Long getCondicionSalubridad() {
		return condicionSalubridad;
	}

	public void setCondicionSalubridad(Long condicionSalubridad) {
		this.condicionSalubridad = condicionSalubridad;
	}
	
	public String getRequiereCondicionesSalubridadDescripcion() {
		return requiereCondicionesSalubridadDescripcion;
	}

	public void setRequiereCondicionesSalubridadDescripcion(String requiereCondicionesSalubridadDescripcion) {
		this.requiereCondicionesSalubridadDescripcion = requiereCondicionesSalubridadDescripcion;
	}

	public String getImpactoNegativoRuidoDescripcion() {
		return impactoNegativoRuidoDescripcion;
	}

	public void setImpactoNegativoRuidoDescripcion(String impactoNegativoRuidoDescripcion) {
		this.impactoNegativoRuidoDescripcion = impactoNegativoRuidoDescripcion;
	}

	public String getImpactoNegativoPorAireDescripcion() {
		return impactoNegativoPorAireDescripcion;
	}

	public void setImpactoNegativoPorAireDescripcion(String impactoNegativoPorAireDescripcion) {
		this.impactoNegativoPorAireDescripcion = impactoNegativoPorAireDescripcion;
	}

	public String getImpactoNegativoInseguridadDescripcion() {
		return impactoNegativoInseguridadDescripcion;
	}

	public void setImpactoNegativoInseguridadDescripcion(String impactoNegativoInseguridadDescripcion) {
		this.impactoNegativoInseguridadDescripcion = impactoNegativoInseguridadDescripcion;
	}

	public String getImpactoNegativoBasuraDescripcion() {
		return impactoNegativoBasuraDescripcion;
	}

	public void setImpactoNegativoBasuraDescripcion(String impactoNegativoBasuraDescripcion) {
		this.impactoNegativoBasuraDescripcion = impactoNegativoBasuraDescripcion;
	}

	public String getAmbientalZonaVerdeDescripcion() {
		return ambientalZonaVerdeDescripcion;
	}

	public void setAmbientalZonaVerdeDescripcion(String ambientalZonaVerdeDescripcion) {
		this.ambientalZonaVerdeDescripcion = ambientalZonaVerdeDescripcion;
	}

	public String getAmbientalParquesDescripcion() {
		return ambientalParquesDescripcion;
	}

	public void setAmbientalParquesDescripcion(String ambientalParquesDescripcion) {
		this.ambientalParquesDescripcion = ambientalParquesDescripcion;
	}

	public String getAmbientalArborizacionDescripcion() {
		return ambientalArborizacionDescripcion;
	}

	public void setAmbientalArborizacionDescripcion(String ambientalArborizacionDescripcion) {
		this.ambientalArborizacionDescripcion = ambientalArborizacionDescripcion;
	}

	public String getAguasServidasDescripcion() {
		return aguasServidasDescripcion;
	}

	public void setAguasServidasDescripcion(String aguasServidasDescripcion) {
		this.aguasServidasDescripcion = aguasServidasDescripcion;
	}

	public String getCondicionesSalubridadDescripcion() {
		return condicionesSalubridadDescripcion;
	}

	public void setCondicionesSalubridadDescripcion(String condicionesSalubridadDescripcion) {
		this.condicionesSalubridadDescripcion = condicionesSalubridadDescripcion;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}