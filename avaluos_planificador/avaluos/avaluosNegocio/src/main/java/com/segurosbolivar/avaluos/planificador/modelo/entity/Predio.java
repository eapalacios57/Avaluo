package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the PREDIO database table.
 * 
 */
@Entity
@Cacheable(false)
@NamedQuery(name = "Predio.findAll", query = "SELECT p FROM Predio p")
public class Predio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "SeqPredio", sequenceName = "SEQ_PREDIO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqPredio")
	@Column(name = "ID_PREDIO")
	private BigDecimal idPredio;
	
	@Column(name="NUMERO_FOLIO")
	private String numeroFolio;

	@Column(name="AREA_PRODCUTIVA")
	private BigDecimal areaProdcutiva;

	@Column(name="AREA_TOTAL")
	private BigDecimal areaTotal;

	@Column(name="ID_CIUDAD")
	private BigDecimal idCiudad;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_TRANSACCION")
	private Date fechaTransaccion;

	@Column(name="FUENTE_INFORMACION")
	private String fuenteInformacion;

	@Column(name="ID_DEPARTAMENTO")
	private BigDecimal idDepartamento;

	private BigDecimal latitud;

	private BigDecimal longitud;

	@Column(name="NOMBRE_MATRICULA_INMOBILIARIA")
	private String nombreMatriculaInmobiliaria;

	private String tenencia;

	private String unidad;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name="USUARIO_TRANSACCION")
	private String usuarioTransaccion;

	private String vereda;
	
	@Column(name="FORMA_LLEGAR")
	private String formaLlegar;

	//bi-directional many-to-one association to UnidadProductiva
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="ID_UNIDAD_PRODUCTIVA")
	private UnidadProductiva unidadProductiva;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="ID_UNIDAD")
	private Unidad idUnidadPredio;
	
	public Predio() {
	}

	public String getNumeroFolio() {
		return this.numeroFolio;
	}

	public void setNumeroFolio(String numeroFolio) {
		this.numeroFolio = numeroFolio;
	}

	public BigDecimal getAreaProdcutiva() {
		return this.areaProdcutiva;
	}

	public void setAreaProdcutiva(BigDecimal areaProdcutiva) {
		this.areaProdcutiva = areaProdcutiva;
	}

	public BigDecimal getAreaTotal() {
		return this.areaTotal;
	}

	public void setAreaTotal(BigDecimal areaTotal) {
		this.areaTotal = areaTotal;
	}

	public BigDecimal getIdCiudad() {
		return this.idCiudad;
	}

	public void setIdCiudad(BigDecimal idCiudad) {
		this.idCiudad = idCiudad;
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

	public String getFuenteInformacion() {
		return this.fuenteInformacion;
	}

	public void setFuenteInformacion(String fuenteInformacion) {
		this.fuenteInformacion = fuenteInformacion;
	}

	public BigDecimal getIdDepartamento() {
		return this.idDepartamento;
	}

	public void setIdDepartamento(BigDecimal idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public BigDecimal getLatitud() {
		return this.latitud;
	}

	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}

	public BigDecimal getLongitud() {
		return this.longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	public String getNombreMatriculaInmobiliaria() {
		return this.nombreMatriculaInmobiliaria;
	}

	public void setNombreMatriculaInmobiliaria(String nombreMatriculaInmobiliaria) {
		this.nombreMatriculaInmobiliaria = nombreMatriculaInmobiliaria;
	}

	public String getTenencia() {
		return this.tenencia;
	}

	public void setTenencia(String tenencia) {
		this.tenencia = tenencia;
	}

	public String getUnidad() {
		return this.unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
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

	public String getVereda() {
		return this.vereda;
	}

	public void setVereda(String vereda) {
		this.vereda = vereda;
	}

	public UnidadProductiva getUnidadProductiva() {
		return this.unidadProductiva;
	}

	public void setUnidadProductiva(UnidadProductiva unidadProductiva) {
		this.unidadProductiva = unidadProductiva;
	}

	public BigDecimal getIdPredio() {
		return idPredio;
	}

	public void setIdPredio(BigDecimal idPredio) {
		this.idPredio = idPredio;
	}

	public String getFormaLlegar() {
		return formaLlegar;
	}

	public void setFormaLlegar(String formaLlegar) {
		this.formaLlegar = formaLlegar;
	}

	public Unidad getIdUnidadPredio() {
		return idUnidadPredio;
	}

	public void setIdUnidadPredio(Unidad idUnidadPredio) {
		this.idUnidadPredio = idUnidadPredio;
	}

	

}