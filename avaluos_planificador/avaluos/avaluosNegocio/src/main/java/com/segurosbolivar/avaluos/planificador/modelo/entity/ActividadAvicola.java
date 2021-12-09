package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the ACTIVIDAD_AVICOLA database table.
 * 
 */
@Entity
@Cacheable(false)
@Table(name = "ACTIVIDAD_AVICOLA")
@NamedQuery(name = "ActividadAvicola.findAll", query = "SELECT a FROM ActividadAvicola a")
public class ActividadAvicola implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SeqActividadAvicola", sequenceName = "SEQ_ACTIVIDAD_AVICOLA", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqActividadAvicola")
	@Column(name = "ID_ACTIVIDAD_AVICOLA")
	private BigDecimal idActividadAvicola;

	@Column(name="LINEA_GENETICA")
	private String lineaGenetica;
	
	@Column(name="AREA_GALPONES_ENGORDE")
	private BigDecimal areaGalponesEngorde;

	@Column(name="AREA_GALPONES_LEVANTE")
	private BigDecimal areaGalponesLevante;

	@Column(name="CICLOS_PRODUCCCION_ANIO")
	private String ciclosProducccionAnio;

	private BigDecimal distancia;

	@Column(name="EQUIPO_DISPONIBLE")
	private String equipoDisponible;
	
	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	
	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_TRANSACCION")
	private Date fechaTransaccion;

	private String infraestructura;

	@Column(name="INTEGRADO_CON_QUIEN")
	private String integradoConQuien;

	@Column(name="NUMERO_AVES")
	private BigDecimal numeroAves;

	@Column(name="NUMERO_GALPONES_ENGORDE")
	private BigDecimal numeroGalponesEngorde;

	@Column(name="NUMERO_GALPONES_LEVANTE")
	private BigDecimal numeroGalponesLevante;

	@Column(name = "AREA_TOTAL_GALPONES")
	private BigDecimal areaTotalGalpones;

	@Column(name = "PRODUCTOR_INTEGRADO")
	private String productorIntegrado;

	private String proveedor;

	@Column(name="TIPO_TRANSPORTE_PROPIO")
	private String tipoTransportePropio;

	@Column(name="TRANSPORTE_ALQUILADO")
	private String transporteAlquilado;

	@Column(name="TRANSPORTE_PROPIO")
	private String transportePropio;

	@Column(name="LUGAR_VENTA")
	private String lugarVenta;
	
	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	
	@Column(name="USUARIO_TRANSACCION")
	private String usuarioTransaccion;

	@Column(name = "COMENTARIO")
	private String comentarios;

	@Column(name = "NUMERO_GALPONES_POSTURA")
	private BigDecimal numGalponesPostura;

	@Column(name = "AREA_GALPONES_POSTURA")
	private BigDecimal areaGalponesPostura;

	// bi-directional many-to-one association to UnidadProductiva

	
	@Column(name="ASISTENCIA_TECNICA")
	private String asistenciaTecnica;
	
	//bi-directional many-to-one association to UnidadProductiva
	
	@ManyToOne
	@JoinColumn(name = "ID_UNIDAD_PRODUCTIVA")
	private UnidadProductiva unidadProductiva;

	public ActividadAvicola() {
	}

	public BigDecimal getAreaGalponesEngorde() {
		return this.areaGalponesEngorde;
	}

	public void setAreaGalponesEngorde(BigDecimal areaGalponesEngorde) {
		this.areaGalponesEngorde = areaGalponesEngorde;
	}

	public BigDecimal getAreaGalponesLevante() {
		return this.areaGalponesLevante;
	}

	public void setAreaGalponesLevante(BigDecimal areaGalponesLevante) {
		this.areaGalponesLevante = areaGalponesLevante;
	}

	public String getCiclosProducccionAnio() {
		return this.ciclosProducccionAnio;
	}

	public void setCiclosProducccionAnio(String ciclosProducccionAnio) {
		this.ciclosProducccionAnio = ciclosProducccionAnio;
	}

	public BigDecimal getDistancia() {
		return this.distancia;
	}

	public void setDistancia(BigDecimal distancia) {
		this.distancia = distancia;
	}

	public String getEquipoDisponible() {
		return this.equipoDisponible;
	}

	public void setEquipoDisponible(String equipoDisponible) {
		this.equipoDisponible = equipoDisponible;
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

	public BigDecimal getIdActividadAvicola() {
		return this.idActividadAvicola;
	}

	public void setIdActividadAvicola(BigDecimal idActividadAvicola) {
		this.idActividadAvicola = idActividadAvicola;
	}

	public String getInfraestructura() {
		return this.infraestructura;
	}

	public void setInfraestructura(String infraestructura) {
		this.infraestructura = infraestructura;
	}

	public String getIntegradoConQuien() {
		return this.integradoConQuien;
	}

	public void setIntegradoConQuien(String integradoConQuien) {
		this.integradoConQuien = integradoConQuien;
	}

	public BigDecimal getNumeroAves() {
		return this.numeroAves;
	}

	public void setNumeroAves(BigDecimal numeroAves) {
		this.numeroAves = numeroAves;
	}

	public BigDecimal getNumeroGalponesEngorde() {
		return this.numeroGalponesEngorde;
	}

	public void setNumeroGalponesEngorde(BigDecimal numeroGalponesEngorde) {
		this.numeroGalponesEngorde = numeroGalponesEngorde;
	}

	public BigDecimal getNumeroGalponesLevante() {
		return this.numeroGalponesLevante;
	}

	public void setNumeroGalponesLevante(BigDecimal numeroGalponesLevante) {
		this.numeroGalponesLevante = numeroGalponesLevante;
	}

	public String getProductorIntegrado() {
		return this.productorIntegrado;
	}

	public void setProductorIntegrado(String productorIntegrado) {
		this.productorIntegrado = productorIntegrado;
	}

	public String getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
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

	public String getLineaGenetica() {
		return lineaGenetica;
	}

	public void setLineaGenetica(String lineaGenetica) {
		this.lineaGenetica = lineaGenetica;
	}

	public String getLugarVenta() {
		return lugarVenta;
	}

	public void setLugarVenta(String lugarVenta) {
		this.lugarVenta = lugarVenta;
	}

	public BigDecimal getAreaTotalGalpones() {
		return areaTotalGalpones;
	}

	public void setAreaTotalGalpones(BigDecimal areaTotalGalpones) {
		this.areaTotalGalpones = areaTotalGalpones;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public BigDecimal getNumGalponesPostura() {
		return numGalponesPostura;
	}

	public void setNumGalponesPostura(BigDecimal numGalponesPostura) {
		this.numGalponesPostura = numGalponesPostura;
	}

	public BigDecimal getAreaGalponesPostura() {
		return areaGalponesPostura;
	}

	public void setAreaGalponesPostura(BigDecimal areaGalponesPostura) {
		this.areaGalponesPostura = areaGalponesPostura;
	}

	public String getAsistenciaTecnica() {
		return asistenciaTecnica;
	}

	public void setAsistenciaTecnica(String asistenciaTecnica) {
		this.asistenciaTecnica = asistenciaTecnica;
	}
	
	
	
}