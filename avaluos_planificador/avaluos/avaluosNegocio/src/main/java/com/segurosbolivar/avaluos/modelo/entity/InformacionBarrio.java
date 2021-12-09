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
 * The persistent class for the PGB_INFBARRIO database table.
 * 
 */
@Entity
@Table(name = "PGB_INFBARRIO")
@NamedQuery(name = "InformacionBarrio.idAvaluo", query = "SELECT p FROM InformacionBarrio p WHERE p.idAvaluo=:idAvaluo")
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class InformacionBarrio implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_INFBARRIO_PK = "idInfbarrio";
	public static final String ENTIDAD_PGB_INFBARRIO_ID_AVALUO = "idAvaluo";
	public static final String ENTIDAD_PGB_INFBARRIO_C_ESTRATO = "cEstrato";
	public static final String ENTIDAD_PGB_INFBARRIO_C_LEGALIDAD = "cLegalidad";
	public static final String ENTIDAD_PGB_INFBARRIO_C_TOPOGRAFIA = "cTopografia";
	public static final String ENTIDAD_PGB_INFBARRIO_C_TRANSPORTE = "cTransporte";
	public static final String ENTIDAD_PGB_INFBARRIO_K_ACUEDUCTOSECTOR = "kAcueductosector";
	public static final String ENTIDAD_PGB_INFBARRIO_K_ACUEDUCTOPREDIO = "kAcueductopredio";
	public static final String ENTIDAD_PGB_INFBARRIO_K_ALCANTAPREDIO = "kAlcantapredio";
	public static final String ENTIDAD_PGB_INFBARRIO_K_ALCANTASECTOR = "kAlcantasector";
	public static final String ENTIDAD_PGB_INFBARRIO_K_ELECTRICAPREDIO = "kElectricapredio";
	public static final String ENTIDAD_PGB_INFBARRIO_K_ELECTRICASECTOR = "kElectricasector";
	public static final String ENTIDAD_PGB_INFBARRIO_K_GASSECTOR = "kGassector";
	public static final String ENTIDAD_PGB_INFBARRIO_K_GASPREDIO = "kGaspredio";
	public static final String ENTIDAD_PGB_INFBARRIO_K_TELSECTOR = "kTelsector";
	public static final String ENTIDAD_PGB_INFBARRIO_K_TELPREDIO = "kTelpredio";
	public static final String ENTIDAD_PGB_INFBARRIO_K_INDUSTRIA = "kIndustria";
	public static final String ENTIDAD_PGB_INFBARRIO_K_VIVIENDA = "kVivienda";
	public static final String ENTIDAD_PGB_INFBARRIO_K_COMERCIO = "kComercio";
	public static final String ENTIDAD_PGB_INFBARRIO_K_OTROSUSOS = "kOtrosusos";
	public static final String ENTIDAD_PGB_INFBARRIO_T_OTROSUSOS = "tOtrosusos";
	public static final String ENTIDAD_PGB_INFBARRIO_C_ESTVIAACCESO = "cEstviaacceso";
	public static final String ENTIDAD_PGB_INFBARRIO_C_PAVIMENTADA = "cPavimentada";
	public static final String ENTIDAD_PGB_INFBARRIO_C_SARDENELES = "cSardeneles";
	public static final String ENTIDAD_PGB_INFBARRIO_C_ANDENES = "cAndenes";
	public static final String ENTIDAD_PGB_INFBARRIO_K_PARQUES = "kParques";
	public static final String ENTIDAD_PGB_INFBARRIO_K_ARBORIZACION = "kArborizacion";
	public static final String ENTIDAD_PGB_INFBARRIO_K_PARADERO = "kParadero";
	public static final String ENTIDAD_PGB_INFBARRIO_K_ALAMEDAS = "kAlamedas";
	public static final String ENTIDAD_PGB_INFBARRIO_K_ALUMBRADO = "kAlumbrado";
	public static final String ENTIDAD_PGB_INFBARRIO_K_ZONASVERDES = "kZonasverdes";
	public static final String ENTIDAD_PGB_INFBARRIO_K_CICLORUTAS = "kCiclorutas";
	public static final String ENTIDAD_PGB_INFBARRIO_T_PERSPECTIVAS = "tPerspectivas";
	public static final String ENTIDAD_PGB_INFBARRIO_A_EDIIGUALES = "aEdiiguales";
	public static final String ENTIDAD_PGB_INFBARRIO_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_PGB_INFBARRIO_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_PGB_INFBARRIO_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_INFBARRIO_FECHA_TRANSACCION = "fechaTransaccion";
	private static final String[] ATRIBUTOS_ENTIDAD_PGB_INFBARRIO = { ENTIDAD_PGB_INFBARRIO_K_ALCANTAPREDIO,
			ENTIDAD_PGB_INFBARRIO_C_TOPOGRAFIA, ENTIDAD_PGB_INFBARRIO_K_ELECTRICAPREDIO,
			ENTIDAD_PGB_INFBARRIO_K_ARBORIZACION, ENTIDAD_PGB_INFBARRIO_USUARIO_TRANSACCION,
			ENTIDAD_PGB_INFBARRIO_K_TELSECTOR, ENTIDAD_PGB_INFBARRIO_K_ALCANTASECTOR, ENTIDAD_PGB_INFBARRIO_C_LEGALIDAD,
			ENTIDAD_PGB_INFBARRIO_K_ELECTRICASECTOR, ENTIDAD_PGB_INFBARRIO_K_TELPREDIO,
			ENTIDAD_PGB_INFBARRIO_FECHA_CREACION, ENTIDAD_PGB_INFBARRIO_ID_AVALUO, ENTIDAD_PGB_INFBARRIO_K_INDUSTRIA,
			ENTIDAD_PGB_INFBARRIO_K_GASSECTOR, ENTIDAD_PGB_INFBARRIO_K_COMERCIO,
			ENTIDAD_PGB_INFBARRIO_K_ACUEDUCTOSECTOR, ENTIDAD_PGB_INFBARRIO_K_ZONASVERDES,
			ENTIDAD_PGB_INFBARRIO_K_OTROSUSOS, ENTIDAD_PGB_INFBARRIO_K_ACUEDUCTOPREDIO,
			ENTIDAD_PGB_INFBARRIO_T_PERSPECTIVAS, ENTIDAD_PGB_INFBARRIO_K_ALUMBRADO, ENTIDAD_PGB_INFBARRIO_A_EDIIGUALES,
			ENTIDAD_PGB_INFBARRIO_K_VIVIENDA, ENTIDAD_PGB_INFBARRIO_K_GASPREDIO, ENTIDAD_PGB_INFBARRIO_PK,
			ENTIDAD_PGB_INFBARRIO_C_ESTVIAACCESO, ENTIDAD_PGB_INFBARRIO_C_SARDENELES, ENTIDAD_PGB_INFBARRIO_K_ALAMEDAS,
			ENTIDAD_PGB_INFBARRIO_C_TRANSPORTE, ENTIDAD_PGB_INFBARRIO_C_ESTRATO, ENTIDAD_PGB_INFBARRIO_K_PARQUES,
			ENTIDAD_PGB_INFBARRIO_K_PARADERO, ENTIDAD_PGB_INFBARRIO_USUARIO_CREACION, ENTIDAD_PGB_INFBARRIO_T_OTROSUSOS,
			ENTIDAD_PGB_INFBARRIO_C_PAVIMENTADA, ENTIDAD_PGB_INFBARRIO_K_CICLORUTAS,
			ENTIDAD_PGB_INFBARRIO_FECHA_TRANSACCION, ENTIDAD_PGB_INFBARRIO_C_ANDENES };

	@Id
	@SequenceGenerator(name = "PGB_INFBARRIO_IDINFORMACIONBARRIO_GENERATOR", sequenceName = "SEQ_PGB_INFBARRIO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGB_INFBARRIO_IDINFORMACIONBARRIO_GENERATOR")
	@Column(name = "ID_INFBARRIO", unique = true, nullable = false, precision = 10)
	private Long idInformacionBarrio;

	@Column(name = "A_EDIIGUALES", precision = 5)
	private Long edificacionesIguales;

	@Column(name = "C_ANDENES", precision = 3)
	private Long andenes;

	@Column(name = "C_ESTRATO", precision = 3)
	private Long estrato;

	@Column(name = "C_ESTVIAACCESO", precision = 3)
	private Long estadosViaAcceso;

	@Column(name = "C_LEGALIDAD", precision = 3)
	private Long legalidad;

	@Column(name = "C_PAVIMENTADA", precision = 3)
	private Long pavimentada;

	@Column(name = "C_SARDENELES", precision = 3)
	private Long sardeneles;

	@Column(name = "C_TOPOGRAFIA", precision = 3)
	private Long topografia;

	@Column(name = "C_TRANSPORTE", precision = 3)
	private Long transporte;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_CREACION", nullable = false)
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_TRANSACCION", nullable = false)
	private Date fechaTransaccion;

	@Column(name = "K_ACUEDUCTOPREDIO", precision = 3)
	private Long acueductoPredio;

	@Column(name = "K_ACUEDUCTOSECTOR", precision = 3)
	private Long acueductoSector;

	@Column(name = "K_ALAMEDAS", precision = 3)
	private Long alamedas;

	@Column(name = "K_ALCANTAPREDIO", precision = 3)
	private Long alcantarilladoPredio;

	@Column(name = "K_ALCANTASECTOR", precision = 3)
	private Long alcantarilladoSector;

	@Column(name = "K_ALUMBRADO", precision = 3)
	private Long alumbrado;

	@Column(name = "K_ARBORIZACION", precision = 3)
	private Long arborizacion;

	@Column(name = "K_CICLORUTAS", precision = 3)
	private Long cicloRutas;

	@Column(name = "K_COMERCIO", precision = 3)
	private Long comercio;

	@Column(name = "K_ELECTRICAPREDIO", precision = 3)
	private Long electricidadPredio;

	@Column(name = "K_ELECTRICASECTOR", precision = 3)
	private Long electricidadSector;

	@Column(name = "K_GASPREDIO", precision = 3)
	private Long gasPredio;

	@Column(name = "K_GASSECTOR", precision = 3)
	private Long gasSector;

	@Column(name = "K_INDUSTRIA", precision = 3)
	private Long industria;

	@Column(name = "K_OTROSUSOS", precision = 3)
	private Long otrosUsos;

	@Column(name = "K_PARADERO", precision = 3)
	private Long paradero;

	@Column(name = "K_PARQUES", precision = 3)
	private Long parques;

	@Column(name = "K_TELPREDIO", precision = 3)
	private Long telefonoPredio;

	@Column(name = "K_TELSECTOR", precision = 3)
	private Long telefonoSector;

	@Column(name = "K_VIVIENDA", precision = 3)
	private Long vivienda;

	@Column(name = "K_ZONASVERDES", precision = 3)
	private Long zonasVerdes;

	@Column(name = "T_OTROSUSOS", length = 30)
	private String textoOtrosUsos;

	@Column(name = "T_PERSPECTIVAS", length = 500)
	private String perspectivas;

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
	
	@Transient
	private String transporteDescripcion;
	@Transient
	private String estadosViaAccesoDescripcion;
	@Transient
	private String topografiaDescripcion;
	@Transient
	private String estratoDescripcion;
	@Transient
	private String legalidadDescripcion; // LEGALBARRIO

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end

	public InformacionBarrio() {
		// protected region procedimientos adicionales de inicialización on
		// begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
	}

	@Override
	public InformacionBarrio clone() throws CloneNotSupportedException {
		return (InformacionBarrio) super.clone();
	}

	public Long getIdInformacionBarrio() {
		return this.idInformacionBarrio;
	}

	public void setIdInformacionBarrio(Long idInformacionBarrio) {
		this.idInformacionBarrio = idInformacionBarrio;
	}

	public Long getEdificacionesIguales() {
		return this.edificacionesIguales;
	}

	public void setEdificacionesIguales(Long edificacionesIguales) {
		this.edificacionesIguales = edificacionesIguales;
	}

	public Long getAndenes() {
		return this.andenes;
	}

	public void setAndenes(Long andenes) {
		this.andenes = andenes;
	}

	public Long getEstrato() {
		return this.estrato;
	}

	public void setEstrato(Long estrato) {
		this.estrato = estrato;
	}

	public Long getEstadosViaAcceso() {
		return this.estadosViaAcceso;
	}

	public void setEstadosViaAcceso(Long estadosViaAcceso) {
		this.estadosViaAcceso = estadosViaAcceso;
	}

	public Long getLegalidad() {
		return this.legalidad;
	}

	public void setLegalidad(Long legalidad) {
		this.legalidad = legalidad;
	}

	public Long getPavimentada() {
		return this.pavimentada;
	}

	public void setPavimentada(Long pavimentada) {
		this.pavimentada = pavimentada;
	}

	public Long getSardeneles() {
		return this.sardeneles;
	}

	public void setSardeneles(Long sardeneles) {
		this.sardeneles = sardeneles;
	}

	public Long getTopografia() {
		return this.topografia;
	}

	public void setTopografia(Long topografia) {
		this.topografia = topografia;
	}

	public Long getTransporte() {
		return this.transporte;
	}

	public void setTransporte(Long transporte) {
		this.transporte = transporte;
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

	public Long getAcueductoPredio() {
		return this.acueductoPredio;
	}

	public void setAcueductoPredio(Long acueductoPredio) {
		this.acueductoPredio = acueductoPredio;
	}

	public Long getAcueductoSector() {
		return this.acueductoSector;
	}

	public void setAcueductoSector(Long acueductoSector) {
		this.acueductoSector = acueductoSector;
	}

	public Long getAlamedas() {
		return this.alamedas;
	}

	public void setAlamedas(Long alamedas) {
		this.alamedas = alamedas;
	}

	public Long getAlcantarilladoPredio() {
		return this.alcantarilladoPredio;
	}

	public void setAlcantarilladoPredio(Long alcantarilladoPredio) {
		this.alcantarilladoPredio = alcantarilladoPredio;
	}

	public Long getAlcantarilladoSector() {
		return this.alcantarilladoSector;
	}

	public void setAlcantarilladoSector(Long alcantarilladoSector) {
		this.alcantarilladoSector = alcantarilladoSector;
	}

	public Long getAlumbrado() {
		return this.alumbrado;
	}

	public void setAlumbrado(Long alumbrado) {
		this.alumbrado = alumbrado;
	}

	public Long getArborizacion() {
		return this.arborizacion;
	}

	public void setArborizacion(Long arborizacion) {
		this.arborizacion = arborizacion;
	}

	public Long getCicloRutas() {
		return this.cicloRutas;
	}

	public void setCicloRutas(Long cicloRutas) {
		this.cicloRutas = cicloRutas;
	}

	public Long getComercio() {
		return this.comercio;
	}

	public void setComercio(Long comercio) {
		this.comercio = comercio;
	}

	public Long getElectricidadPredio() {
		return this.electricidadPredio;
	}

	public void setElectricidadPredio(Long electricidadPredio) {
		this.electricidadPredio = electricidadPredio;
	}

	public Long getElectricidadSector() {
		return this.electricidadSector;
	}

	public void setElectricidadSector(Long electricidadSector) {
		this.electricidadSector = electricidadSector;
	}

	public Long getGasPredio() {
		return this.gasPredio;
	}

	public void setGasPredio(Long gasPredio) {
		this.gasPredio = gasPredio;
	}

	public Long getGasSector() {
		return this.gasSector;
	}

	public void setGasSector(Long gasSector) {
		this.gasSector = gasSector;
	}

	public Long getIndustria() {
		return this.industria;
	}

	public void setIndustria(Long industria) {
		this.industria = industria;
	}

	public Long getOtrosUsos() {
		return this.otrosUsos;
	}

	public void setOtrosUsos(Long otrosUsos) {
		this.otrosUsos = otrosUsos;
	}

	public Long getParadero() {
		return this.paradero;
	}

	public void setParadero(Long paradero) {
		this.paradero = paradero;
	}

	public Long getParques() {
		return this.parques;
	}

	public void setParques(Long parques) {
		this.parques = parques;
	}

	public Long getTelefonoPredio() {
		return this.telefonoPredio;
	}

	public void setTelefonoPredio(Long telefonoPredio) {
		this.telefonoPredio = telefonoPredio;
	}

	public Long getTelefonoSector() {
		return this.telefonoSector;
	}

	public void setTelefonoSector(Long telefonoSector) {
		this.telefonoSector = telefonoSector;
	}

	public Long getVivienda() {
		return this.vivienda;
	}

	public void setVivienda(Long vivienda) {
		this.vivienda = vivienda;
	}

	public Long getZonasVerdes() {
		return this.zonasVerdes;
	}

	public void setZonasVerdes(Long zonasVerdes) {
		this.zonasVerdes = zonasVerdes;
	}

	public String getTextoOtrosUsos() {
		return this.textoOtrosUsos;
	}

	public void settextoOtrosUsos(String otrosUsos) {
		this.textoOtrosUsos = otrosUsos;
	}

	public String getPerspectivas() {
		return this.perspectivas;
	}

	public void setPerspectivas(String perspectivas) {
		this.perspectivas = perspectivas;
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

	public Long getIdAvaluo() {
		return idAvaluo;
	}

	public void setIdAvaluo(Long idAvaluo) {
		this.idAvaluo = idAvaluo;
	}

	public void setTextoOtrosUsos(String textoOtrosUsos) {
		this.textoOtrosUsos = textoOtrosUsos;
	}

	@Override
	public String toString() {
		return "InformacionBarrio [idInformacionBarrio=" + idInformacionBarrio + ", edificacionesIguales="
				+ edificacionesIguales + ", andenes=" + andenes + ", estrato=" + estrato + ", estadosViaAcceso="
				+ estadosViaAcceso + ", legalidad=" + legalidad + ", pavimentada=" + pavimentada + ", sardeneles="
				+ sardeneles + ", topografia=" + topografia + ", transporte=" + transporte + ", fechaCreacion="
				+ fechaCreacion + ", fechaTransaccion=" + fechaTransaccion + ", acueductoPredio=" + acueductoPredio
				+ ", acueductoSector=" + acueductoSector + ", alamedas=" + alamedas + ", alcantarilladoPredio="
				+ alcantarilladoPredio + ", alcantarilladoSector=" + alcantarilladoSector + ", alumbrado=" + alumbrado
				+ ", arborizacion=" + arborizacion + ", cicloRutas=" + cicloRutas + ", comercio=" + comercio
				+ ", electricidadPredio=" + electricidadPredio + ", electricidadSector=" + electricidadSector
				+ ", gasPredio=" + gasPredio + ", gasSector=" + gasSector + ", industria=" + industria + ", otrosUsos="
				+ otrosUsos + ", paradero=" + paradero + ", parques=" + parques + ", telefonoPredio=" + telefonoPredio
				+ ", telefonoSector=" + telefonoSector + ", vivienda=" + vivienda + ", zonasVerdes=" + zonasVerdes
				+ ", textoOtrosUsos=" + textoOtrosUsos + ", perspectivas=" + perspectivas + ", usuarioCreacion="
				+ usuarioCreacion + ", usuarioTransaccion=" + usuarioTransaccion + ", avaluo=" + avaluo + "]";
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
		for (final String atr : ATRIBUTOS_ENTIDAD_PGB_INFBARRIO) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	public static String[] getAtributosEntidadInformacionBarrio() {
		return ATRIBUTOS_ENTIDAD_PGB_INFBARRIO;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idInformacionBarrio);
		hash = 37 * hash + Objects.hashCode(this.idAvaluo);
		hash = 37 * hash + Objects.hashCode(this.estrato);
		hash = 37 * hash + Objects.hashCode(this.legalidad);
		hash = 37 * hash + Objects.hashCode(this.topografia);
		hash = 37 * hash + Objects.hashCode(this.transporte);
		hash = 37 * hash + Objects.hashCode(this.acueductoSector);
		hash = 37 * hash + Objects.hashCode(this.acueductoPredio);
		hash = 37 * hash + Objects.hashCode(this.alcantarilladoPredio);
		hash = 37 * hash + Objects.hashCode(this.alcantarilladoSector);
		hash = 37 * hash + Objects.hashCode(this.electricidadPredio);
		hash = 37 * hash + Objects.hashCode(this.electricidadSector);
		hash = 37 * hash + Objects.hashCode(this.gasSector);
		hash = 37 * hash + Objects.hashCode(this.gasPredio);
		hash = 37 * hash + Objects.hashCode(this.telefonoSector);
		hash = 37 * hash + Objects.hashCode(this.telefonoPredio);
		hash = 37 * hash + Objects.hashCode(this.industria);
		hash = 37 * hash + Objects.hashCode(this.vivienda);
		hash = 37 * hash + Objects.hashCode(this.comercio);
		hash = 37 * hash + Objects.hashCode(this.otrosUsos);
		hash = 37 * hash + Objects.hashCode(this.otrosUsos);
		hash = 37 * hash + Objects.hashCode(this.estadosViaAcceso);
		hash = 37 * hash + Objects.hashCode(this.pavimentada);
		hash = 37 * hash + Objects.hashCode(this.sardeneles);
		hash = 37 * hash + Objects.hashCode(this.andenes);
		hash = 37 * hash + Objects.hashCode(this.parques);
		hash = 37 * hash + Objects.hashCode(this.arborizacion);
		hash = 37 * hash + Objects.hashCode(this.paradero);
		hash = 37 * hash + Objects.hashCode(this.alamedas);
		hash = 37 * hash + Objects.hashCode(this.alumbrado);
		hash = 37 * hash + Objects.hashCode(this.zonasVerdes);
		hash = 37 * hash + Objects.hashCode(this.cicloRutas);
		hash = 37 * hash + Objects.hashCode(this.perspectivas);
		hash = 37 * hash + Objects.hashCode(this.edificacionesIguales);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad InformacionBarrio que se
	 * pasa como parámetro comprobando que comparten los mismos valores en cada
	 * uno de sus atributos. Solo se tienen en cuenta los atributos simples, es
	 * decir, se omiten aquellos que definen una relación con otra tabla.
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
		final InformacionBarrio other = (InformacionBarrio) obj;

		if (!Objects.equals(this.idInformacionBarrio, other.idInformacionBarrio)) {
			return false;
		}

		if (!Objects.equals(this.idAvaluo, other.idAvaluo)) {
			return false;
		}

		if (!Objects.equals(this.estrato, other.estrato)) {
			return false;
		}

		if (!Objects.equals(this.legalidad, other.legalidad)) {
			return false;
		}

		if (!Objects.equals(this.topografia, other.topografia)) {
			return false;
		}

		if (!Objects.equals(this.transporte, other.transporte)) {
			return false;
		}

		if (!Objects.equals(this.acueductoSector, other.acueductoSector)) {
			return false;
		}

		if (!Objects.equals(this.acueductoPredio, other.acueductoPredio)) {
			return false;
		}

		if (!Objects.equals(this.alcantarilladoPredio, other.alcantarilladoPredio)) {
			return false;
		}

		if (!Objects.equals(this.alcantarilladoSector, other.alcantarilladoSector)) {
			return false;
		}

		if (!Objects.equals(this.electricidadPredio, other.electricidadPredio)) {
			return false;
		}

		if (!Objects.equals(this.electricidadSector, other.electricidadSector)) {
			return false;
		}

		if (!Objects.equals(this.gasSector, other.gasSector)) {
			return false;
		}

		if (!Objects.equals(this.gasPredio, other.gasPredio)) {
			return false;
		}

		if (!Objects.equals(this.telefonoSector, other.telefonoSector)) {
			return false;
		}

		if (!Objects.equals(this.telefonoPredio, other.telefonoPredio)) {
			return false;
		}

		if (!Objects.equals(this.industria, other.industria)) {
			return false;
		}

		if (!Objects.equals(this.vivienda, other.vivienda)) {
			return false;
		}

		if (!Objects.equals(this.comercio, other.comercio)) {
			return false;
		}

		if (!Objects.equals(this.otrosUsos, other.otrosUsos)) {
			return false;
		}

		if (!Objects.equals(this.otrosUsos, other.otrosUsos)) {
			return false;
		}

		if (!Objects.equals(this.estadosViaAcceso, other.estadosViaAcceso)) {
			return false;
		}

		if (!Objects.equals(this.pavimentada, other.pavimentada)) {
			return false;
		}

		if (!Objects.equals(this.sardeneles, other.sardeneles)) {
			return false;
		}

		if (!Objects.equals(this.andenes, other.andenes)) {
			return false;
		}

		if (!Objects.equals(this.parques, other.parques)) {
			return false;
		}

		if (!Objects.equals(this.arborizacion, other.arborizacion)) {
			return false;
		}

		if (!Objects.equals(this.paradero, other.paradero)) {
			return false;
		}

		if (!Objects.equals(this.alamedas, other.alamedas)) {
			return false;
		}

		if (!Objects.equals(this.alumbrado, other.alumbrado)) {
			return false;
		}

		if (!Objects.equals(this.zonasVerdes, other.zonasVerdes)) {
			return false;
		}

		if (!Objects.equals(this.cicloRutas, other.cicloRutas)) {
			return false;
		}

		if (!Objects.equals(this.perspectivas, other.perspectivas)) {
			return false;
		}

		if (!Objects.equals(this.edificacionesIguales, other.edificacionesIguales)) {
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

	public String getTransporteDescripcion() {
		return transporteDescripcion;
	}

	public void setTransporteDescripcion(String transporteDescripcion) {
		this.transporteDescripcion = transporteDescripcion;
	}

	public String getEstadosViaAccesoDescripcion() {
		return estadosViaAccesoDescripcion;
	}

	public void setEstadosViaAccesoDescripcion(String estadosViaAccesoDescripcion) {
		this.estadosViaAccesoDescripcion = estadosViaAccesoDescripcion;
	}

	public String getTopografiaDescripcion() {
		return topografiaDescripcion;
	}

	public void setTopografiaDescripcion(String topografiaDescripcion) {
		this.topografiaDescripcion = topografiaDescripcion;
	}

	public String getEstratoDescripcion() {
		return estratoDescripcion;
	}

	public void setEstratoDescripcion(String estratoDescripcion) {
		this.estratoDescripcion = estratoDescripcion;
	}

	public String getLegalidadDescripcion() {
		return legalidadDescripcion;
	}

	public void setLegalidadDescripcion(String legalidadDescripcion) {
		this.legalidadDescripcion = legalidadDescripcion;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}