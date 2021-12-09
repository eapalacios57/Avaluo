package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ACTIVIDAD_GANADERA database table.
 * 
 */
@Entity
@Cacheable(false)
@Table(name="ACTIVIDAD_GANADERA")
@NamedQuery(name="ActividadGanadera.findAll", query="SELECT a FROM ActividadGanadera a")
public class ActividadGanadera implements Serializable {
	private static final long serialVersionUID = 1L;

//SEQ_ACTIVIDAD_GANADERA
	@Id
	@SequenceGenerator(name="SeqActividadGanadera", sequenceName="SEQ_ACTIVIDAD_GANADERA", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SeqActividadGanadera")
	@Column(name="ID_ACTIVIDAD_GANADERA")
	private BigDecimal idActividadGanadera;

	@Column(name="AREA_PRADERA_MEJORADA")
	private BigDecimal areaPraderaMejorada;
	
	@Column(name="AREA_PRADERA_MEJORADA_HA")
	private BigDecimal areaPraderaMejoradaHa;
	
	@Column(name="AREA_ACTIVIDAD")
	private BigDecimal areaActividad;
	
	@Column(name="AREA_ACTIVIDAD_HA")
	private BigDecimal areaActividadHa;
	
	@Column(name="CANTIDAD_POTREROS")
	private BigDecimal cantidadPotreros;

	@Column(name="CICLOS_PRODUCCCION_ANIO")
	private BigDecimal ciclosProducccionAnio;

	@Column(name="EQUIPOS_DISPONIBLES")
	private String equiposDisponibles;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_TRANSACCION")
	private Date fechaTransaccion;

	private String infraestructura;

	@Column(name="LUGAR_VENTA")
	private String lugarVenta;

	@Column(name="RAZA_CRUCE")
	private String razaCruce;
	
	@Column(name="TIPO_PRADERA")
	private String tipoPradera;

	@Column(name="TIPO_TRANSPORTE_PROPIO")
	private String tipoTransportePropio;

	@Column(name="TRANSPORTE_ALQUILADO")
	private String transporteAlquilado;

	@Column(name="TRANSPORTE_PROPIO")
	private String transportePropio;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name="USUARIO_TRANSACCION")
	private String usuarioTransaccion;
	
	@Column(name="ASISTENCIA_TECNICA")
	private String asistenciaTecnica;
	

	@Column(name="COMENTARIO")
	private String comentarios;
	
	//bi-directional many-to-one association to UnidadProductiva
	
	@ManyToOne
	@JoinColumn(name="ID_UNIDAD_PRODUCTIVA")
	private UnidadProductiva unidadProductiva;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ID_UNI_AREA_PRADERA_MEJORADA")
	private Unidad areaPraderaMejoradaUnidad;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ID_UNIDAD_AREA_ACTIVIDAD")
	private Unidad areaActividadUnidad;

	public ActividadGanadera() {
	}

	public BigDecimal getCantidadPotreros() {
		return this.cantidadPotreros;
	}

	public void setCantidadPotreros(BigDecimal cantidadPotreros) {
		this.cantidadPotreros = cantidadPotreros;
	}

	public BigDecimal getCiclosProducccionAnio() {
		return this.ciclosProducccionAnio;
	}

	public void setCiclosProducccionAnio(BigDecimal ciclosProducccionAnio) {
		this.ciclosProducccionAnio = ciclosProducccionAnio;
	}

	public String getEquiposDisponibles() {
		return this.equiposDisponibles;
	}

	public void setEquiposDisponibles(String equiposDisponibles) {
		this.equiposDisponibles = equiposDisponibles;
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

	public BigDecimal getIdActividadGanadera() {
		return this.idActividadGanadera;
	}

	public void setIdActividadGanadera(BigDecimal idActividadGanadera) {
		this.idActividadGanadera = idActividadGanadera;
	}

	public String getInfraestructura() {
		return this.infraestructura;
	}

	public void setInfraestructura(String infraestructura) {
		this.infraestructura = infraestructura;
	}

	public String getLugarVenta() {
		return this.lugarVenta;
	}

	public void setLugarVenta(String lugarVenta) {
		this.lugarVenta = lugarVenta;
	}

	public String getRazaCruce() {
		return this.razaCruce;
	}

	public void setRazaCruce(String razaCruce) {
		this.razaCruce = razaCruce;
	}

	public String getTipoPradera() {
		return this.tipoPradera;
	}

	public void setTipoPradera(String tipoPradera) {
		this.tipoPradera = tipoPradera;
	}

	public String getTipoTransportePropio() {
		return this.tipoTransportePropio;
	}

	public void setTipoTransportePropio(String tipoTransportePropio) {
		this.tipoTransportePropio = tipoTransportePropio;
	}

	public String getTransporteAlquilado() {
		return this.transporteAlquilado;
	}

	public void setTransporteAlquilado(String transporteAlquilado) {
		this.transporteAlquilado = transporteAlquilado;
	}

	public String getTransportePropio() {
		return this.transportePropio;
	}

	public void setTransportePropio(String transportePropio) {
		this.transportePropio = transportePropio;
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

	public UnidadProductiva getUnidadProductiva() {
		return this.unidadProductiva;
	}

	public void setUnidadProductiva(UnidadProductiva unidadProductiva) {
		this.unidadProductiva = unidadProductiva;
	}

	public BigDecimal getAreaPraderaMejorada() {
		return areaPraderaMejorada;
	}

	public void setAreaPraderaMejorada(BigDecimal areaPraderaMejorada) {
		this.areaPraderaMejorada = areaPraderaMejorada;
	}

	public BigDecimal getAreaPraderaMejoradaHa() {
		return areaPraderaMejoradaHa;
	}

	public void setAreaPraderaMejoradaHa(BigDecimal areaPraderaMejoradaHa) {
		this.areaPraderaMejoradaHa = areaPraderaMejoradaHa;
	}

	public BigDecimal getAreaActividad() {
		return areaActividad;
	}

	public void setAreaActividad(BigDecimal areaActividad) {
		this.areaActividad = areaActividad;
	}

	public BigDecimal getAreaActividadHa() {
		return areaActividadHa;
	}

	public void setAreaActividadHa(BigDecimal areaActividadHa) {
		this.areaActividadHa = areaActividadHa;
	}

	public Unidad getAreaPraderaMejoradaUnidad() {
		return areaPraderaMejoradaUnidad;
	}

	public void setAreaPraderaMejoradaUnidad(Unidad areaPraderaMejoradaUnidad) {
		this.areaPraderaMejoradaUnidad = areaPraderaMejoradaUnidad;
	}

	public Unidad getAreaActividadUnidad() {
		return areaActividadUnidad;
	}

	public void setAreaActividadUnidad(Unidad areaActividadUnidad) {
		this.areaActividadUnidad = areaActividadUnidad;
	}
	
	public String getAsistenciaTecnica() {
		return asistenciaTecnica;
	}

	public void setAsistenciaTecnica(String asistenciaTecnica) {
		this.asistenciaTecnica = asistenciaTecnica;
	}
	
	

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
}