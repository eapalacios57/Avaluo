package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;
import org.codehaus.jackson.annotate.JsonIgnore;
import java.util.List;


/**
 * The persistent class for the BENEFICIARIO database table.
 * 
 */
@Entity
@Cacheable(false)
@NamedQueries({
	@NamedQuery(name="Beneficiario.findAll", query="SELECT b FROM Beneficiario b"),
	@NamedQuery(name="Beneficiario.findById", query="SELECT b FROM Beneficiario b WHERE b.id.tipoDocumentoBeneficiario = :tipoDocumentoBeneficiario AND b.id.numeroDocumentoBeneficiario = :numeroDocumentoBeneficiario ")
})
public class Beneficiario implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BeneficiarioPK id;

	@Column(name="PRIMER_APELLIDO")
	private String primerApellido;

	@Column(name="PRIMER_NOMBRE")
	private String primerNombre;

	@Column(name="SEGUNDO_APELLIDO")
	private String segundoApellido;

	@Column(name="SEGUNDO_NOMBRE")
	private String segundoNombre;

	//bi-directional many-to-one association to MedioComunicacion
	@OneToMany(mappedBy="beneficiario")
	private List<MedioComunicacion> medioComunicacions;

	//bi-directional many-to-one association to SolicitudBeneficiario
	@JsonIgnore
	@OneToMany(mappedBy="beneficiario")
	private List<SolicitudBeneficiario> solicitudBeneficiarios;

	public Beneficiario() {
	}

	public BeneficiarioPK getId() {
		return this.id;
	}

	public void setId(BeneficiarioPK id) {
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
		medioComunicacion.setBeneficiario(this);

		return medioComunicacion;
	}

	public MedioComunicacion removeMedioComunicacion(MedioComunicacion medioComunicacion) {
		getMedioComunicacions().remove(medioComunicacion);
		medioComunicacion.setBeneficiario(null);

		return medioComunicacion;
	}

	public List<SolicitudBeneficiario> getSolicitudBeneficiarios() {
		return this.solicitudBeneficiarios;
	}

	public void setSolicitudBeneficiarios(List<SolicitudBeneficiario> solicitudBeneficiarios) {
		this.solicitudBeneficiarios = solicitudBeneficiarios;
	}

	public SolicitudBeneficiario addSolicitudBeneficiario(SolicitudBeneficiario solicitudBeneficiario) {
		getSolicitudBeneficiarios().add(solicitudBeneficiario);
		solicitudBeneficiario.setBeneficiario(this);

		return solicitudBeneficiario;
	}

	public SolicitudBeneficiario removeSolicitudBeneficiario(SolicitudBeneficiario solicitudBeneficiario) {
		getSolicitudBeneficiarios().remove(solicitudBeneficiario);
		solicitudBeneficiario.setBeneficiario(null);

		return solicitudBeneficiario;
	}

}