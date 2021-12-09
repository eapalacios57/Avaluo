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
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import uk.co.jemos.podam.annotations.PodamExclude;

/**
 * The persistent class for the PGB_LIQAVALUO_TOTAL database table.
 * 
 */
@Entity
@Table(name = "PGB_LIQAVALUO_TOTAL")
@NamedQuery(name = "LiquidacionAvaluoTotal.idAvaluo", query = "SELECT p FROM LiquidacionAvaluoTotal p WHERE p.idAvaluo=:idAvaluo")
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class LiquidacionAvaluoTotal implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_LIQAVALUO_TOTAL_PK = "idLiqavaluoTotal";
	public static final String ENTIDAD_PGB_LIQAVALUO_TOTAL_ID_AVALUO = "idAvaluo";
	public static final String ENTIDAD_PGB_LIQAVALUO_TOTAL_N_VALUVRDIA = "nValuvrdia";
	public static final String ENTIDAD_PGB_LIQAVALUO_TOTAL_N_TOTALAVALUO = "nTotalavaluo";
	public static final String ENTIDAD_PGB_LIQAVALUO_TOTAL_N_AVALUOUVR = "nAvaluouvr";
	public static final String ENTIDAD_PGB_LIQAVALUO_TOTAL_N_VALORASEGURABLE = "nValorasegurable";
	public static final String ENTIDAD_PGB_LIQAVALUO_TOTAL_R_CALIFICACION = "rCalificacion";
	public static final String ENTIDAD_PGB_LIQAVALUO_TOTAL_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_PGB_LIQAVALUO_TOTAL_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_PGB_LIQAVALUO_TOTAL_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_LIQAVALUO_TOTAL_FECHA_TRANSACCION = "fechaTransaccion";
	public static final String ENTIDAD_PGB_LIQAVALUO_TOTAL_NUM_COSTO_TRANSACCION = "numCostoTransaccion";
	private static final String[] ATRIBUTOS_ENTIDAD_PGB_LIQAVALUO_TOTAL = { ENTIDAD_PGB_LIQAVALUO_TOTAL_N_TOTALAVALUO,
			ENTIDAD_PGB_LIQAVALUO_TOTAL_USUARIO_CREACION, ENTIDAD_PGB_LIQAVALUO_TOTAL_N_VALUVRDIA,
			ENTIDAD_PGB_LIQAVALUO_TOTAL_N_VALORASEGURABLE, ENTIDAD_PGB_LIQAVALUO_TOTAL_USUARIO_TRANSACCION,
			ENTIDAD_PGB_LIQAVALUO_TOTAL_ID_AVALUO, ENTIDAD_PGB_LIQAVALUO_TOTAL_R_CALIFICACION,
			ENTIDAD_PGB_LIQAVALUO_TOTAL_FECHA_CREACION, ENTIDAD_PGB_LIQAVALUO_TOTAL_FECHA_TRANSACCION,
			ENTIDAD_PGB_LIQAVALUO_TOTAL_NUM_COSTO_TRANSACCION, ENTIDAD_PGB_LIQAVALUO_TOTAL_PK,
			ENTIDAD_PGB_LIQAVALUO_TOTAL_N_AVALUOUVR };

	@Id
	@SequenceGenerator(name = "PGB_LIQAVALUO_TOTAL_IDLIQAVALUOTOTAL_GENERATOR", sequenceName = "SEQ_PGB_LIQAVALUO_TOTAL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGB_LIQAVALUO_TOTAL_IDLIQAVALUOTOTAL_GENERATOR")
	@Column(name = "ID_LIQAVALUO_TOTAL", unique = true, nullable = false, precision = 10)
	private Long idLiqavaluoTotal;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CREACION", nullable = false)
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_TRANSACCION", nullable = false)
	private Date fechaTransaccion;

	@Column(name = "N_AVALUOUVR", precision = 18, scale = 5)
	private BigDecimal avaluoUvr;

	@Column(name = "N_TOTALAVALUO", precision = 18, scale = 4)
	private BigDecimal totalAvaluo;

	@Column(name = "N_VALORASEGURABLE", precision = 18, scale = 4)
	private BigDecimal valorAsegurable;

	@Column(name = "N_VALUVRDIA", precision = 18, scale = 4)
	private BigDecimal valorUvrDia;

	@Column(name = "R_CALIFICACION", precision = 3)
	private Long calificacion;

	@Column(name = "NUM_COSTO_TRANSACCION", precision = 18, scale = 4)
	private BigDecimal numCostoTransaccion;

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
	private String calificacionDescripcion; //CALGARANTIA

	// protected region atributos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region atributos adicionales end
	public LiquidacionAvaluoTotal() {
		// protected region procedimientos adicionales de inicializaci�n on
		// begin
		// Escriba en esta secci�n sus modificaciones

		// protected region procedimientos adicionales de inicializaci�n end
	}

	@Override
	public LiquidacionAvaluoTotal clone() throws CloneNotSupportedException {
		return (LiquidacionAvaluoTotal) super.clone();
	}

	public Long getIdLiqavaluoTotal() {
		return this.idLiqavaluoTotal;
	}

	public void setIdLiqavaluoTotal(Long idLiqavaluoTotal) {
		this.idLiqavaluoTotal = idLiqavaluoTotal;
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

	public BigDecimal getAvaluoUvr() {
		return this.avaluoUvr;
	}

	public void setAvaluoUvr(BigDecimal avaluoUvr) {
		this.avaluoUvr = avaluoUvr;
	}

	public BigDecimal getTotalAvaluo() {
		return this.totalAvaluo;
	}

	public void setTotalAvaluo(BigDecimal totalAvaluo) {
		this.totalAvaluo = totalAvaluo;
	}

	public BigDecimal getValorAsegurable() {
		return this.valorAsegurable;
	}

	public void setValorAsegurable(BigDecimal valorAsegurable) {
		this.valorAsegurable = valorAsegurable;
	}

	public BigDecimal getValorUvrDia() {
		return this.valorUvrDia;
	}

	public void setValorUvrDia(BigDecimal valorUvrDia) {
		this.valorUvrDia = valorUvrDia;
	}

	public Long getCalificacion() {
		return this.calificacion;
	}

	public void setCalificacion(Long calificacion) {
		this.calificacion = calificacion;
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

	/**
	 * Verifica si la entidad contiene el atributo que se pasa como par�metro
	 *
	 * @param atributo
	 *            Nombre del atributo a validar
	 * @return Verdadero si la entidad contiene al atributo.
	 */
	public static boolean contieneAtributo(String atributo) {

		boolean contiene = false;
		for (final String atr : ATRIBUTOS_ENTIDAD_PGB_LIQAVALUO_TOTAL) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	public static String[] getAtributosEntidadLiquidacionAvaluoTotal() {
		return ATRIBUTOS_ENTIDAD_PGB_LIQAVALUO_TOTAL;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idLiqavaluoTotal);
		hash = 37 * hash + Objects.hashCode(this.idAvaluo);
		hash = 37 * hash + Objects.hashCode(this.valorUvrDia);
		hash = 37 * hash + Objects.hashCode(this.totalAvaluo);
		hash = 37 * hash + Objects.hashCode(this.avaluoUvr);
		hash = 37 * hash + Objects.hashCode(this.valorAsegurable);
		hash = 37 * hash + Objects.hashCode(this.calificacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
		hash = 37 * hash + Objects.hashCode(this.numCostoTransaccion);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad LiquidacionAvaluoTotal
	 * que se pasa como par�metro comprobando que comparten los mismos valores
	 * en cada uno de sus atributos. Solo se tienen en cuenta los atributos
	 * simples, es decir, se omiten aquellos que definen una relaci�n con otra
	 * tabla.
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
		final LiquidacionAvaluoTotal other = (LiquidacionAvaluoTotal) obj;

		if (!Objects.equals(this.idLiqavaluoTotal, other.idLiqavaluoTotal)) {
			return false;
		}

		if (!Objects.equals(this.idAvaluo, other.idAvaluo)) {
			return false;
		}

		if (!Objects.equals(this.valorUvrDia, other.valorUvrDia)) {
			return false;
		}

		if (!Objects.equals(this.totalAvaluo, other.totalAvaluo)) {
			return false;
		}

		if (!Objects.equals(this.avaluoUvr, other.avaluoUvr)) {
			return false;
		}

		if (!Objects.equals(this.valorAsegurable, other.valorAsegurable)) {
			return false;
		}

		if (!Objects.equals(this.calificacion, other.calificacion)) {
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

	public String getCalificacionDescripcion() {
		return calificacionDescripcion;
	}

	public void setCalificacionDescripcion(String calificacionDescripcion) {
		this.calificacionDescripcion = calificacionDescripcion;
	}
	
	public BigDecimal getNumCostoTransaccion() {
		return numCostoTransaccion;
	}

	public void setNumCostoTransaccion(BigDecimal numCostoTransaccion) {
		this.numCostoTransaccion = numCostoTransaccion;
	}


	// protected region metodos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region metodos adicionales end

}