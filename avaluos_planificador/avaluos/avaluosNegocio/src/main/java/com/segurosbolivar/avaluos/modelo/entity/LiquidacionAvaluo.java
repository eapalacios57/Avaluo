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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.asesoftware.util.lang.UtilTexto;

/**
 * The persistent class for the PGB_LIQAVALUO database table.
 * 
 */
@Entity
@Table(name = "PGB_LIQAVALUO")
@NamedQueries({
@NamedQuery(name = "LiquidacionAvaluo.idAvaluo", query = "SELECT p FROM LiquidacionAvaluo p WHERE p.idAvaluo=:idAvaluo ORDER BY p.consecutivo"),
@NamedQuery(name = "LiquidacionAvaluo.consecutivoAvaluo", query = "SELECT p FROM LiquidacionAvaluo p WHERE p.idAvaluo=:idAvaluo and  p.consecutivo=:consecutivo")
})
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class LiquidacionAvaluo implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_LIQAVALUO_PK = "idLiqavaluo";
	public static final String ENTIDAD_PGB_LIQAVALUO_ID_AVALUO = "idAvaluo";
	public static final String ENTIDAD_PGB_LIQAVALUO_C_DESCRIPLIQ = "cDescripliq";
	public static final String ENTIDAD_PGB_LIQAVALUO_A_DESCDEPENDENCIA = "aDescdependencia";
	public static final String ENTIDAD_PGB_LIQAVALUO_N_AREALIQ = "nArealiq";
	public static final String ENTIDAD_PGB_LIQAVALUO_N_VAL = "nVal";
	public static final String ENTIDAD_PGB_LIQAVALUO_N_VALTOT = "nValtot";
	public static final String ENTIDAD_PGB_LIQAVALUO_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_PGB_LIQAVALUO_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_PGB_LIQAVALUO_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_LIQAVALUO_FECHA_TRANSACCION = "fechaTransaccion";
	public static final String ENTIDAD_PGB_LIQAVALUO_CONSECUTIVO = "consecutivo";
	private static final String[] ATRIBUTOS_ENTIDAD_PGB_LIQAVALUO = { ENTIDAD_PGB_LIQAVALUO_CONSECUTIVO,
			ENTIDAD_PGB_LIQAVALUO_FECHA_CREACION, ENTIDAD_PGB_LIQAVALUO_PK, ENTIDAD_PGB_LIQAVALUO_USUARIO_CREACION,
			ENTIDAD_PGB_LIQAVALUO_USUARIO_TRANSACCION, ENTIDAD_PGB_LIQAVALUO_C_DESCRIPLIQ,
			ENTIDAD_PGB_LIQAVALUO_N_AREALIQ, ENTIDAD_PGB_LIQAVALUO_A_DESCDEPENDENCIA, ENTIDAD_PGB_LIQAVALUO_N_VALTOT,
			ENTIDAD_PGB_LIQAVALUO_ID_AVALUO, ENTIDAD_PGB_LIQAVALUO_N_VAL, ENTIDAD_PGB_LIQAVALUO_FECHA_TRANSACCION };

	@Id
	@SequenceGenerator(name = "PGB_LIQAVALUO_IDLIQAVALUO_GENERATOR", sequenceName = "SEQ_PGB_LIQAVALUO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGB_LIQAVALUO_IDLIQAVALUO_GENERATOR")
	@Column(name = "ID_LIQAVALUO", unique = true, nullable = false, precision = 22)
	private Long idLiqavaluo;

	@Column(name = "A_DESCDEPENDENCIA", length = 30)
	private String descripcionDependencia;

	@Column(name = "C_DESCRIPLIQ", precision = 3)
	private Long descripcionLiquidacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CREACION", nullable = false)
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_TRANSACCION", nullable = false)
	private Date fechaTransaccion;

	@Column(name = "N_AREALIQ", precision = 18, scale = 4)
	private BigDecimal areaLiquidacion;

	@Column(name = "N_VAL", precision = 18, scale = 4)
	private BigDecimal valor;

	@Column(name = "N_VALTOT", precision = 18, scale = 4)
	private BigDecimal valorTotal;

	@Column(name = "USUARIO_CREACION", nullable = false, length = 15)
	private String usuarioCreacion;

	@Column(name = "USUARIO_TRANSACCION", nullable = false, length = 15)
	private String usuarioTransaccion;

	@Column(name = "CONSECUTIVO", length = 2)
	private Long consecutivo;

	@Column(name = "ID_AVALUO")
	private Long idAvaluo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_AVALUO", referencedColumnName = "ID_AVALUO", insertable = false, updatable = false)
	private Avaluo avaluo;
	
	@Transient
	private String descripcionLiquidacionDescripcion; //DESCLIQUIDACION

	// protected region atributos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region atributos adicionales end

	public LiquidacionAvaluo() {

	}

	public LiquidacionAvaluo(Long idAvaluo) {
		this.idAvaluo = idAvaluo;
		// protected region procedimientos adicionales de inicializaci�n on
		// begin
		// Escriba en esta secci�n sus modificaciones

		// protected region procedimientos adicionales de inicializaci�n end
	}

	@Override
	public LiquidacionAvaluo clone() throws CloneNotSupportedException {
		return (LiquidacionAvaluo) super.clone();
	}

	public Long getIdLiqavaluo() {
		return this.idLiqavaluo;
	}

	public void setIdLiqavaluo(Long idLiqavaluo) {
		this.idLiqavaluo = idLiqavaluo;
	}

	public String getDescripcionDependencia() {
		return this.descripcionDependencia;
	}

	public void setDescripcionDependencia(String descripcionDependencia) {
		this.descripcionDependencia = descripcionDependencia;
	}

	public Long getDescripcionLiquidacion() {
		return this.descripcionLiquidacion;
	}

	public void setDescripcionLiquidacion(Long descripcionLiquidacion) {
		this.descripcionLiquidacion = descripcionLiquidacion;
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

	public BigDecimal getAreaLiquidacion() {
		return this.areaLiquidacion;
	}

	public void setAreaLiquidacion(BigDecimal areaLiquidacion) {
		this.areaLiquidacion = areaLiquidacion;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
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

	public Long getConsecutivo() {
		return consecutivo;
	}

	public Long getIdAvaluo() {
		return idAvaluo;
	}

	public void setIdAvaluo(Long idAvaluo) {
		this.idAvaluo = idAvaluo;
	}

	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}

	@Override
	public String toString() {
		return "LiquidacionAvaluo [idLiqavaluo=" + idLiqavaluo + ", descripcionDependencia=" + descripcionDependencia
				+ ", descripcionLiquidacion=" + descripcionLiquidacion + ", fechaCreacion=" + fechaCreacion
				+ ", fechaTransaccion=" + fechaTransaccion + ", areaLiquidacion=" + areaLiquidacion + ", valor=" + valor
				+ ", valorTotal=" + valorTotal + ", usuarioCreacion=" + usuarioCreacion + ", usuarioTransaccion="
				+ usuarioTransaccion + ", consecutivo=" + consecutivo + ", avaluo=" + avaluo + "]";
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
		for (final String atr : ATRIBUTOS_ENTIDAD_PGB_LIQAVALUO) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	public static String[] getAtributosEntidadLiquidacionAvaluo() {
		return ATRIBUTOS_ENTIDAD_PGB_LIQAVALUO;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idLiqavaluo);
		hash = 37 * hash + Objects.hashCode(this.idAvaluo);
		hash = 37 * hash + Objects.hashCode(this.descripcionDependencia);
		hash = 37 * hash + Objects.hashCode(this.descripcionDependencia);
		hash = 37 * hash + Objects.hashCode(this.areaLiquidacion);
		hash = 37 * hash + Objects.hashCode(this.valor);
		hash = 37 * hash + Objects.hashCode(this.valorTotal);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
		hash = 37 * hash + Objects.hashCode(this.consecutivo);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad LiquidacionAvaluo que se
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
		final LiquidacionAvaluo other = (LiquidacionAvaluo) obj;

		if (!Objects.equals(this.idLiqavaluo, other.idLiqavaluo)) {
			return false;
		}

		if (!Objects.equals(this.idAvaluo, other.idAvaluo)) {
			return false;
		}

		if (!Objects.equals(this.descripcionLiquidacion, other.descripcionLiquidacion)) {
			return false;
		}

		if (!Objects.equals(this.descripcionDependencia, other.descripcionDependencia)) {
			return false;
		}

		if (!Objects.equals(this.areaLiquidacion, other.areaLiquidacion)) {
			return false;
		}

		if (!Objects.equals(this.valor, other.valor)) {
			return false;
		}

		if (!Objects.equals(this.valorTotal, other.valorTotal)) {
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

		if (!Objects.equals(this.consecutivo, other.consecutivo)) {
			return false;
		}
		return Objects.equals(this.fechaTransaccion, other.fechaTransaccion);

	}

	public boolean isVacio() {
		boolean vacio = true;
		vacio &= this.getConsecutivo() == null;
		vacio &= UtilTexto.estaVacio(this.getDescripcionDependencia());
		vacio &= this.getAreaLiquidacion() == null;
		vacio &= this.getValor() == null;
		vacio &= this.getValorTotal() == null;
		return vacio;
	}
	
	public boolean esVacioSinConsecutivo() {
		boolean vacio = true;
		vacio &= this.getDescripcionLiquidacion() == null;
		vacio &= UtilTexto.estaVacio(this.getDescripcionDependencia());
		vacio &= this.getAreaLiquidacion() == null;
		vacio &= this.getValor() == null;
		vacio &= this.getValorTotal() == null;
		return vacio;
	}

	public String getDescripcionLiquidacionDescripcion() {
		return descripcionLiquidacionDescripcion;
	}

	public void setDescripcionLiquidacionDescripcion(String descripcionLiquidacionDescripcion) {
		this.descripcionLiquidacionDescripcion = descripcionLiquidacionDescripcion;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region metodos adicionales end

}