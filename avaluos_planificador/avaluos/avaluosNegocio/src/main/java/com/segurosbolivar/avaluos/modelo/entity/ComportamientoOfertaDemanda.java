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

import uk.co.jemos.podam.annotations.PodamExclude;

/**
 * The persistent class for the PGB_COMP_OFERTA_DEMANDA database table.
 * 
 */
@Entity
@Table(name = "PGB_COMP_OFERTA_DEMANDA")
@NamedQuery(name = "ComportamientoOfertaDemanda.idAvaluo", query = "SELECT p FROM ComportamientoOfertaDemanda p WHERE p.idAvaluo=:idAvaluo")
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class ComportamientoOfertaDemanda implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_COMP_OFERTA_DEMANDA_PK = "idComportamientoOfertaDemanda";
	public static final String ENTIDAD_PGB_COMP_OFERTA_DEMANDA_ID_AVALUO = "idAvaluo";
	public static final String ENTIDAD_PGB_COMP_OFERTA_DEMANDA_T_ACTEDIFICADORA = "tActedificadora";
	public static final String ENTIDAD_PGB_COMP_OFERTA_DEMANDA_T_COMPORTAOD = "tComportaod";
	public static final String ENTIDAD_PGB_COMP_OFERTA_DEMANDA_N_TIPOCOMERCIALIZA = "nTipocomercializa";
	public static final String ENTIDAD_PGB_COMP_OFERTA_DEMANDA_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_PGB_COMP_OFERTA_DEMANDA_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_PGB_COMP_OFERTA_DEMANDA_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_COMP_OFERTA_DEMANDA_FECHA_TRANSACCION = "fechaTransaccion";
	private static final String[] ATRIBUTOS_ENTIDAD_PGB_COMP_OFERTA_DEMANDA = {
			ENTIDAD_PGB_COMP_OFERTA_DEMANDA_T_COMPORTAOD, ENTIDAD_PGB_COMP_OFERTA_DEMANDA_FECHA_CREACION,
			ENTIDAD_PGB_COMP_OFERTA_DEMANDA_USUARIO_TRANSACCION, ENTIDAD_PGB_COMP_OFERTA_DEMANDA_T_ACTEDIFICADORA,
			ENTIDAD_PGB_COMP_OFERTA_DEMANDA_N_TIPOCOMERCIALIZA, ENTIDAD_PGB_COMP_OFERTA_DEMANDA_USUARIO_CREACION,
			ENTIDAD_PGB_COMP_OFERTA_DEMANDA_ID_AVALUO, ENTIDAD_PGB_COMP_OFERTA_DEMANDA_PK,
			ENTIDAD_PGB_COMP_OFERTA_DEMANDA_FECHA_TRANSACCION };

	// Dnino 17 Jul 2016 Se deshabilita validacion para tiempo de respuesta
	// @PrePersist
	// protected void onCreate() throws Exception {
	// BigDecimal max =
	// UtilJpa.getMaxTableValue("PGB_COMP_OFERTA_DEMANDA","ID_COMPOFERTADEMANDA");
	// if(idComportamientoOfertaDemanda.compareTo(max)<1)
	// {
	// BigDecimal seqNew =
	// UtilJpa.getAlterSequenceValueMaxTable(max,"SEQ_PGB_COMP_OFERTA_DEMANDA");
	// this.setIdComportamientoOfertaDemanda(seqNew);
	// }
	// }

	@Id
	@SequenceGenerator(name = "PGB_COMP_OFERTA_DEMANDA_IDCOMPORTAMIENTOOFERTADEMANDA_GENERATOR", sequenceName = "SEQ_PGB_COMP_OFERTA_DEMANDA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGB_COMP_OFERTA_DEMANDA_IDCOMPORTAMIENTOOFERTADEMANDA_GENERATOR")
	@Column(name = "ID_COMPOFERTADEMANDA", unique = true, nullable = false, precision = 10)
	private Long idComportamientoOfertaDemanda;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CREACION", nullable = false)
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_TRANSACCION", nullable = false)
	private Date fechaTransaccion;

	@Column(name = "N_TIPOCOMERCIALIZA", precision = 22)
	private Long tipoComercializacion;

	@Column(name = "T_ACTEDIFICADORA", length = 500)
	private String actualidadEdificadora;

	@Column(name = "T_COMPORTAOD", length = 500)
	private String comportamiento;

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

	// protected region atributos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region atributos adicionales end

	public ComportamientoOfertaDemanda() {
		// protected region procedimientos adicionales de inicializaci�n on
		// begin
		// Escriba en esta secci�n sus modificaciones

		// protected region procedimientos adicionales de inicializaci�n end
	}

	@Override
	public ComportamientoOfertaDemanda clone() throws CloneNotSupportedException {
		return (ComportamientoOfertaDemanda) super.clone();
	}

	public Long getIdComportamientoOfertaDemanda() {
		return this.idComportamientoOfertaDemanda;
	}

	public void setIdComportamientoOfertaDemanda(Long idComportamientoOfertaDemanda) {
		this.idComportamientoOfertaDemanda = idComportamientoOfertaDemanda;
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

	public Long getTipoComercializacion() {
		return this.tipoComercializacion;
	}

	public void setTipoComercializacion(Long tipoComercializacion) {
		this.tipoComercializacion = tipoComercializacion;
	}

	public String getActualidadEdificadora() {
		return this.actualidadEdificadora;
	}

	public void setActualidadEdificadora(String actualidadEdificadora) {
		this.actualidadEdificadora = actualidadEdificadora;
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
		for (final String atr : ATRIBUTOS_ENTIDAD_PGB_COMP_OFERTA_DEMANDA) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	public static String[] getAtributosEntidadComportamientoOfertaDemanda() {
		return ATRIBUTOS_ENTIDAD_PGB_COMP_OFERTA_DEMANDA;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idComportamientoOfertaDemanda);
		hash = 37 * hash + Objects.hashCode(this.idAvaluo);
		hash = 37 * hash + Objects.hashCode(this.actualidadEdificadora);
		hash = 37 * hash + Objects.hashCode(this.comportamiento);
		hash = 37 * hash + Objects.hashCode(this.tipoComercializacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad
	 * ComportamientoOfertaDemanda que se pasa como par�metro comprobando que
	 * comparten los mismos valores en cada uno de sus atributos. Solo se tienen
	 * en cuenta los atributos simples, es decir, se omiten aquellos que definen
	 * una relaci�n con otra tabla.
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
		final ComportamientoOfertaDemanda other = (ComportamientoOfertaDemanda) obj;

		if (!Objects.equals(this.idComportamientoOfertaDemanda, other.idComportamientoOfertaDemanda)) {
			return false;
		}

		if (!Objects.equals(this.idAvaluo, other.idAvaluo)) {
			return false;
		}

		if (!Objects.equals(this.actualidadEdificadora, other.actualidadEdificadora)) {
			return false;
		}

		if (!Objects.equals(this.comportamiento, other.comportamiento)) {
			return false;
		}

		if (!Objects.equals(this.tipoComercializacion, other.tipoComercializacion)) {
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

	public String getComportamiento() {
		return comportamiento;
	}

	public void setComportamiento(String comportamiento) {
		this.comportamiento = comportamiento;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region metodos adicionales end

}