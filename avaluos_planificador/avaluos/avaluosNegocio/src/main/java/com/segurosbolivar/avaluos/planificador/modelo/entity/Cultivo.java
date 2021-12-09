package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the CULTIVO database table.
 * 
 */
@Entity
@Cacheable(false)
@NamedQuery(name = "Cultivo.findAll", query = "SELECT c FROM Cultivo c")
public class Cultivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SeqCultivo", sequenceName = "SEQ_CULTIVO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqCultivo")
	@Column(name = "ID_CULTIVO")
	private BigDecimal idCultivo;

	@Column(name = "CICLOS_PRODUCCION_ANIO")
	private BigDecimal ciclosProduccionAnio;

	private String comentario;

	private BigDecimal densidad;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_TRANSACCION")
	private Date fechaTransaccion;

	@Column(name = "LUGAR_VENTA")
	private String lugarVenta;
	
	@Column(name = "CULTIVO_ALTERNA")
	private String cultivoAlterna;

	@Column(name = "MESES_COSECHA")
	private String mesesCosecha;

	@Column(name = "PRODUCTO")
	private String producto;

	@Column(name = "VARIEDAD")
	private String variedad;

	@Column(name = "AREA_PRODUCTIVA")
	private BigDecimal areaProductiva;

	@Column(name = "AREA_PRODUCTIVA_HA")
	private BigDecimal areaProductivaHa;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_SIEMBRA")
	private Date fechaSiembra;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ID_UNIDAD_AREA_PRODUCTIVA")
	private Unidad areaProductivaUnidad;

	@Column(name = "ROTA_CULTIVO")
	private String rotaCultivo;

	@Column(name = "USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name = "USUARIO_TRANSACCION")
	private String usuarioTransaccion;
	
	@Column(name = "ASISTENCIA_TECNICA")
	private String asistenciaTecnica;
	
	// bi-directional many-to-one association to Unidad
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ID_UNIDAD")
	private Unidad unidad;

	// bi-directional many-to-one association to UnidadProductiva
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ID_UNIDAD_PRODUCTIVA")
	private UnidadProductiva unidadProductiva;

	// bi-directional many-to-one association to Soporte
	@JsonIgnore
	@OneToMany(mappedBy = "cultivo")
	private List<Soporte> soportes;

	public Cultivo() {
	}

	public BigDecimal getCiclosProduccionAnio() {
		return this.ciclosProduccionAnio;
	}

	public void setCiclosProduccionAnio(BigDecimal ciclosProduccionAnio) {
		this.ciclosProduccionAnio = ciclosProduccionAnio;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public BigDecimal getDensidad() {
		return this.densidad;
	}

	public void setDensidad(BigDecimal densidad) {
		this.densidad = densidad;
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

	public BigDecimal getIdCultivo() {
		return this.idCultivo;
	}

	public void setIdCultivo(BigDecimal idCultivo) {
		this.idCultivo = idCultivo;
	}

	public String getLugarVenta() {
		return this.lugarVenta;
	}

	public void setLugarVenta(String lugarVenta) {
		this.lugarVenta = lugarVenta;
	}

	public String getMesesCosecha() {
		return this.mesesCosecha;
	}

	public void setMesesCosecha(String mesesCosecha) {
		this.mesesCosecha = mesesCosecha;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getVariedad() {
		return variedad;
	}

	public void setVariedad(String variedad) {
		this.variedad = variedad;
	}

	public BigDecimal getAreaProductiva() {
		return areaProductiva;
	}

	public void setAreaProductiva(BigDecimal areaProductiva) {
		this.areaProductiva = areaProductiva;
	}

	public BigDecimal getAreaProductivaHa() {
		return areaProductivaHa;
	}

	public void setAreaProductivaHa(BigDecimal areaProductivaHa) {
		this.areaProductivaHa = areaProductivaHa;
	}

	public Date getFechaSiembra() {
		return fechaSiembra;
	}

	public void setFechaSiembra(Date fechaSiembra) {
		this.fechaSiembra = fechaSiembra;
	}

	public Unidad getAreaProductivaUnidad() {
		return areaProductivaUnidad;
	}

	public void setAreaProductivaUnidad(Unidad areaProductivaUnidad) {
		this.areaProductivaUnidad = areaProductivaUnidad;
	}

	public String getRotaCultivo() {
		return this.rotaCultivo;
	}

	public void setRotaCultivo(String rotaCultivo) {
		this.rotaCultivo = rotaCultivo;
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

	public Unidad getUnidad() {
		return this.unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public UnidadProductiva getUnidadProductiva() {
		return this.unidadProductiva;
	}

	public void setUnidadProductiva(UnidadProductiva unidadProductiva) {
		this.unidadProductiva = unidadProductiva;
	}

	public List<Soporte> getSoportes() {
		return this.soportes;
	}

	public void setSoportes(List<Soporte> soportes) {
		this.soportes = soportes;
	}

	public Soporte addSoporte(Soporte soporte) {
		getSoportes().add(soporte);
		soporte.setCultivo(this);

		return soporte;
	}

	public Soporte removeSoporte(Soporte soporte) {
		getSoportes().remove(soporte);
		soporte.setCultivo(null);

		return soporte;
	}

	public String getCultivoAlterna() {
		return cultivoAlterna;
	}

	public void setCultivoAlterna(String cultivoAlterna) {
		this.cultivoAlterna = cultivoAlterna;
	}

	public String getAsistenciaTecnica() {
		return asistenciaTecnica;
	}

	public void setAsistenciaTecnica(String asistenciaTecnica) {
		this.asistenciaTecnica = asistenciaTecnica;
	}
	
	
	
	

}