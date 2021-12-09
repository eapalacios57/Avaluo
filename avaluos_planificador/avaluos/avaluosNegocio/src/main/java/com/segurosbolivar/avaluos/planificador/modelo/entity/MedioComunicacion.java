package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;


/**
 * The persistent class for the MEDIO_COMUNICACION database table.
 * 
 */
@Entity
@Cacheable(false)
@Table(name="MEDIO_COMUNICACION")
@NamedQueries({
	@NamedQuery(name="MedioComunicacion.findAll", query="SELECT m FROM MedioComunicacion m"),
	@NamedQuery(name="MedioComunicacion.findByBen", query="SELECT m FROM MedioComunicacion m WHERE m.beneficiario.id.tipoDocumentoBeneficiario = :tipoDocumentoBeneficairio AND m.beneficiario.id.numeroDocumentoBeneficiario = :numeroDocumentoBeneficiario AND m.estado = 'A' AND m.principal = 'S' ")
}) 
public class MedioComunicacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SeqMedioComunicacion", sequenceName="SEQ_MEDIO_COMUNICACION", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SeqMedioComunicacion")
	@Column(name="ID_MEDIO_COMUNICACION")
	private long idMedioComunicacion;

	private String descripcion;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_TRANSACCION")
	private Date fechaTransaccion;

	private String principal;

	@Column(name="TIPO_MEDIO_COMUNICACION")
	private String tipoMedioComunicacion;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name="USUARIO_TRANSACCION")
	private String usuarioTransaccion;

	private String valor;

	//bi-directional many-to-one association to Beneficiario
	@JsonIgnore
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="NUMERO_DOCUMENTO_BENEFICIARIO", referencedColumnName="NUMERO_DOCUMENTO_BENEFICIARIO"),
		@JoinColumn(name="TIPO_DOCUMENTO_BENEFICIARIO", referencedColumnName="TIPO_DOCUMENTO_BENEFICIARIO")
		})
	private Beneficiario beneficiario;

	//bi-directional many-to-one association to Planificador
	@JsonIgnore
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="NUMERO_DOCUMENTO_PLANIFICADOR", referencedColumnName="NUMERO_DOCUMENTO_PLANIFICADOR"),
		@JoinColumn(name="TIPO_DOCUMENTO_PLANIFICADOR", referencedColumnName="TIPO_DOCUMENTO_PLANIFICADOR")
		})
	private Planificador planificador;

	public MedioComunicacion() {
	}

	public long getIdMedioComunicacion() {
		return this.idMedioComunicacion;
	}

	public void setIdMedioComunicacion(long idMedioComunicacion) {
		this.idMedioComunicacion = idMedioComunicacion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public String getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getTipoMedioComunicacion() {
		return this.tipoMedioComunicacion;
	}

	public void setTipoMedioComunicacion(String tipoMedioComunicacion) {
		this.tipoMedioComunicacion = tipoMedioComunicacion;
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

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Beneficiario getBeneficiario() {
		return this.beneficiario;
	}

	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}

	public Planificador getPlanificador() {
		return this.planificador;
	}

	public void setPlanificador(Planificador planificador) {
		this.planificador = planificador;
	}

}