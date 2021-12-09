package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the PLANIFICADOR database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Planificador.findAll", query="SELECT p FROM Planificador p"),
	@NamedQuery(name="Planificador.findById", query="SELECT p FROM Planificador p WHERE p.id = :id "),
	@NamedQuery(name="Planificador.findByNumDoc", query="SELECT p FROM Planificador p WHERE p.id.numeroDocumentoPlanificador = :numeroDocumentoPlanificador "),
	@NamedQuery(name="Planificador.findByRegion", query="SELECT p FROM Planificador p WHERE p.idRegion = :idRegion or p.idRegion = 7")
})
@Cacheable(false)

public class Planificador implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PlanificadorPK id;

	@Column(name="PRIMER_APELLIDO")
	private String primerApellido;

	@Column(name="PRIMER_NOMBRE")
	private String primerNombre;

	@Column(name="SEGUNDO_APELLIDO")
	private String segundoApellido;

	@Column(name="SEGUNDO_NOMBRE")
	private String segundoNombre;
	
	@Column(name="DISPOSITIVO")
	private String dispositivo;
	
	@Column(name="TOKEN_DISPOSITIVO")
	private String tokenDispositivo;
	

	//bi-directional many-to-one association to MedioComunicacion
	@OneToMany(mappedBy="planificador")
	private List<MedioComunicacion> medioComunicacions;

	//bi-directional many-to-one association to Solicitud
	@JsonIgnore
	@OneToMany(mappedBy="planificador")
	private List<Solicitud> solicituds;
	
	@Column(name="ID_DEPARTAMENTO")
	private BigDecimal idDepartamento;
	
	@Column(name="ID_REGION")
	private BigDecimal idRegion;

	public Planificador() {
	}

	public PlanificadorPK getId() {
		return this.id;
	}

	public void setId(PlanificadorPK id) {
		this.id = id;
	}

	public String getPrimerApellido() {
		return this.primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getPrimerNombre() {
		return this.primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoApellido() {
		return this.segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getSegundoNombre() {
		return this.segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public List<MedioComunicacion> getMedioComunicacions() {
		return this.medioComunicacions;
	}

	public void setMedioComunicacions(List<MedioComunicacion> medioComunicacions) {
		this.medioComunicacions = medioComunicacions;
	}

	public MedioComunicacion addMedioComunicacion(MedioComunicacion medioComunicacion) {
		getMedioComunicacions().add(medioComunicacion);
		medioComunicacion.setPlanificador(this);

		return medioComunicacion;
	}

	public MedioComunicacion removeMedioComunicacion(MedioComunicacion medioComunicacion) {
		getMedioComunicacions().remove(medioComunicacion);
		medioComunicacion.setPlanificador(null);

		return medioComunicacion;
	}

	public List<Solicitud> getSolicituds() {
		return this.solicituds;
	}

	public void setSolicituds(List<Solicitud> solicituds) {
		this.solicituds = solicituds;
	}

	public Solicitud addSolicitud(Solicitud solicitud) {
		getSolicituds().add(solicitud);
		solicitud.setPlanificador(this);

		return solicitud;
	}

	public Solicitud removeSolicitud(Solicitud solicitud) {
		getSolicituds().remove(solicitud);
		solicitud.setPlanificador(null);

		return solicitud;
	}

	public String getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}

	public String getTokenDispositivo() {
		return tokenDispositivo;
	}

	public void setTokenDispositivo(String tokenDispositivo) {
		this.tokenDispositivo = tokenDispositivo;
	}

	public BigDecimal getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(BigDecimal idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public BigDecimal getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(BigDecimal idRegion) {
		this.idRegion = idRegion;
	}
	
	

}