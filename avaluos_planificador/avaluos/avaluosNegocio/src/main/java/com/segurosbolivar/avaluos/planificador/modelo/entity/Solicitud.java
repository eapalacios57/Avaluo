package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the SOLICITUD database table.
 * 
 */
@Entity
@Cacheable(false)
@NamedQuery(name="Solicitud.findAll", query="SELECT s FROM Solicitud s ORDER BY s.codigoSolicitud ASC ")
public class Solicitud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SeqSolicitud", sequenceName="SEQ_SOLICITUD", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SeqSolicitud")
	@Column(name="CODIGO_SOLICITUD")
	private String codigoSolicitud;

	private BigDecimal avance;

	@Column(name="COMENTARIOS_ANEXOS")
	private String comentariosAnexos;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_SOLICITUD")
	private Date fechaSolicitud;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_TRANSACCION")
	private Date fechaTransaccion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_VISITA_PREDIO")
	private Date fechaVisitaPredio;

	@Column(name="MUNICIPIO_DEPARTAMENTO")
	private String municipioDepartamento;

	@Column(name="NOMBRE_ASESOR_COMERCIAL")
	private String nombreAsesorComercial;

	@Column(name="TELEFONO_ASESOR_COMERCIAL")
	private String telefonoAsesorComercial;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name="USUARIO_TRANSACCION")
	private String usuarioTransaccion;

	@Column(name="VALOR_TOTAL_CREDITO")
	private BigDecimal valorTotalCredito;

	@Column(name="VALOR_TOTAL_PROYECTO")
	private BigDecimal valorTotalProyecto;

	@Column(name="ID_CIUDAD")
	private BigDecimal idCiudad;
	
	@Column(name="ID_DEPARTAMENTO")
	private BigDecimal idDepartamento;

	//bi-directional many-to-one association to ActividadFinancieraSolicitud
	@JsonIgnore
	@OneToMany(mappedBy="solicitud")
	private List<ActividadFinancieraSolicitud> actividadFinancieraSolicituds;

	//bi-directional many-to-one association to Documento
	@OneToMany(mappedBy="solicitud")
	private List<Documento> documentos;

	//bi-directional many-to-one association to ProductoRelacionadoSolicitud
	@JsonIgnore
	@OneToMany(mappedBy="solicitud")
	private List<ProductoRelacionadoSolicitud> productoRelacionadoSolicituds;

	//bi-directional many-to-one association to Planificador
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="NUMERO_DOCUMENTO_PLANIFICADOR", referencedColumnName="NUMERO_DOCUMENTO_PLANIFICADOR"),
		@JoinColumn(name="TIPO_DOCUMENTO_PLANIFICADOR", referencedColumnName="TIPO_DOCUMENTO_PLANIFICADOR")
		})
	private Planificador planificador;

	//bi-directional many-to-one association to SolicitudBeneficiario
	@JsonIgnore
	@OneToMany(mappedBy="solicitud")
	private List<SolicitudBeneficiario> solicitudBeneficiarios;

	//bi-directional many-to-one association to SolicitudMovimiento
	@OneToMany(mappedBy="solicitud")
	private List<SolicitudMovimiento> solicitudMovimientos;

	//bi-directional many-to-one association to UnidadProductiva
	@JsonIgnore
	@OneToMany(mappedBy="solicitud")
	private List<UnidadProductiva> unidadProductivas;
	
	public Solicitud() {
	}

	public String getCodigoSolicitud() {
		return this.codigoSolicitud;
	}

	public void setCodigoSolicitud(String codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public BigDecimal getAvance() {
		return this.avance;
	}

	public void setAvance(BigDecimal avance) {
		this.avance = avance;
	}

	public String getComentariosAnexos() {
		return this.comentariosAnexos;
	}

	public void setComentariosAnexos(String comentariosAnexos) {
		this.comentariosAnexos = comentariosAnexos;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaSolicitud() {
		return this.fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public Date getFechaTransaccion() {
		return this.fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public Date getFechaVisitaPredio() {
		return this.fechaVisitaPredio;
	}

	public void setFechaVisitaPredio(Date fechaVisitaPredio) {
		this.fechaVisitaPredio = fechaVisitaPredio;
	}

	public String getMunicipioDepartamento() {
		return this.municipioDepartamento;
	}

	public void setMunicipioDepartamento(String municipioDepartamento) {
		this.municipioDepartamento = municipioDepartamento;
	}

	public String getNombreAsesorComercial() {
		return this.nombreAsesorComercial;
	}

	public void setNombreAsesorComercial(String nombreAsesorComercial) {
		this.nombreAsesorComercial = nombreAsesorComercial;
	}

	public String getTelefonoAsesorComercial() {
		return this.telefonoAsesorComercial;
	}

	public void setTelefonoAsesorComercial(String telefonoAsesorComercial) {
		this.telefonoAsesorComercial = telefonoAsesorComercial;
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

	public BigDecimal getValorTotalCredito() {
		return this.valorTotalCredito;
	}

	public void setValorTotalCredito(BigDecimal valorTotalCredito) {
		this.valorTotalCredito = valorTotalCredito;
	}

	public BigDecimal getValorTotalProyecto() {
		return this.valorTotalProyecto;
	}

	public void setValorTotalProyecto(BigDecimal valorTotalProyecto) {
		this.valorTotalProyecto = valorTotalProyecto;
	}

	public List<ActividadFinancieraSolicitud> getActividadFinancieraSolicituds() {
		return this.actividadFinancieraSolicituds;
	}

	public void setActividadFinancieraSolicituds(List<ActividadFinancieraSolicitud> actividadFinancieraSolicituds) {
		this.actividadFinancieraSolicituds = actividadFinancieraSolicituds;
	}

	public ActividadFinancieraSolicitud addActividadFinancieraSolicitud(ActividadFinancieraSolicitud actividadFinancieraSolicitud) {
		getActividadFinancieraSolicituds().add(actividadFinancieraSolicitud);
		actividadFinancieraSolicitud.setSolicitud(this);

		return actividadFinancieraSolicitud;
	}

	public ActividadFinancieraSolicitud removeActividadFinancieraSolicitud(ActividadFinancieraSolicitud actividadFinancieraSolicitud) {
		getActividadFinancieraSolicituds().remove(actividadFinancieraSolicitud);
		actividadFinancieraSolicitud.setSolicitud(null);

		return actividadFinancieraSolicitud;
	}

	public List<Documento> getDocumentos() {
		return this.documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public Documento addDocumento(Documento documento) {
		getDocumentos().add(documento);
		documento.setSolicitud(this);

		return documento;
	}

	public Documento removeDocumento(Documento documento) {
		getDocumentos().remove(documento);
		documento.setSolicitud(null);

		return documento;
	}

	public List<ProductoRelacionadoSolicitud> getProductoRelacionadoSolicituds() {
		return this.productoRelacionadoSolicituds;
	}

	public void setProductoRelacionadoSolicituds(List<ProductoRelacionadoSolicitud> productoRelacionadoSolicituds) {
		this.productoRelacionadoSolicituds = productoRelacionadoSolicituds;
	}

	public ProductoRelacionadoSolicitud addProductoRelacionadoSolicitud(ProductoRelacionadoSolicitud productoRelacionadoSolicitud) {
		getProductoRelacionadoSolicituds().add(productoRelacionadoSolicitud);
		productoRelacionadoSolicitud.setSolicitud(this);

		return productoRelacionadoSolicitud;
	}

	public ProductoRelacionadoSolicitud removeProductoRelacionadoSolicitud(ProductoRelacionadoSolicitud productoRelacionadoSolicitud) {
		getProductoRelacionadoSolicituds().remove(productoRelacionadoSolicitud);
		productoRelacionadoSolicitud.setSolicitud(null);

		return productoRelacionadoSolicitud;
	}

	public Planificador getPlanificador() {
		return this.planificador;
	}

	public void setPlanificador(Planificador planificador) {
		this.planificador = planificador;
	}

	public List<SolicitudBeneficiario> getSolicitudBeneficiarios() {
		return this.solicitudBeneficiarios;
	}

	public void setSolicitudBeneficiarios(List<SolicitudBeneficiario> solicitudBeneficiarios) {
		this.solicitudBeneficiarios = solicitudBeneficiarios;
	}

	public SolicitudBeneficiario addSolicitudBeneficiario(SolicitudBeneficiario solicitudBeneficiario) {
		getSolicitudBeneficiarios().add(solicitudBeneficiario);
		solicitudBeneficiario.setSolicitud(this);

		return solicitudBeneficiario;
	}

	public SolicitudBeneficiario removeSolicitudBeneficiario(SolicitudBeneficiario solicitudBeneficiario) {
		getSolicitudBeneficiarios().remove(solicitudBeneficiario);
		solicitudBeneficiario.setSolicitud(null);

		return solicitudBeneficiario;
	}

	public List<SolicitudMovimiento> getSolicitudMovimientos() {
		return this.solicitudMovimientos;
	}

	public void setSolicitudMovimientos(List<SolicitudMovimiento> solicitudMovimientos) {
		this.solicitudMovimientos = solicitudMovimientos;
	}

	public SolicitudMovimiento addSolicitudMovimiento(SolicitudMovimiento solicitudMovimiento) {
		getSolicitudMovimientos().add(solicitudMovimiento);
		solicitudMovimiento.setSolicitud(this);

		return solicitudMovimiento;
	}

	public SolicitudMovimiento removeSolicitudMovimiento(SolicitudMovimiento solicitudMovimiento) {
		getSolicitudMovimientos().remove(solicitudMovimiento);
		solicitudMovimiento.setSolicitud(null);

		return solicitudMovimiento;
	}

	public List<UnidadProductiva> getUnidadProductivas() {
		return this.unidadProductivas;
	}

	public void setUnidadProductivas(List<UnidadProductiva> unidadProductivas) {
		this.unidadProductivas = unidadProductivas;
	}

	public UnidadProductiva addUnidadProductiva(UnidadProductiva unidadProductiva) {
		getUnidadProductivas().add(unidadProductiva);
		unidadProductiva.setSolicitud(this);

		return unidadProductiva;
	}

	public UnidadProductiva removeUnidadProductiva(UnidadProductiva unidadProductiva) {
		getUnidadProductivas().remove(unidadProductiva);
		unidadProductiva.setSolicitud(null);

		return unidadProductiva;
	}

	public BigDecimal getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(BigDecimal idCiudad) {
		this.idCiudad = idCiudad;
	}

	public BigDecimal getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(BigDecimal idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

}