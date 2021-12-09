package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * The persistent class for the POLIGONO database table.
 * 
 */
@Entity
@NamedQuery(name = "Poligono.findAll", query = "SELECT p FROM Poligono p")
public class Poligono implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "POLIGONO_IDPOLIGONO_GENERATOR", sequenceName = "SEQ_POLIGONO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POLIGONO_IDPOLIGONO_GENERATOR")
	@Column(name = "ID_POLIGONO")
	private BigDecimal idPoligono;

	@Column(name = "BORDE_COLOR")
	private String bordeColor;

	@Column(name = "BORDE_ESTILO")
	private String bordeEstilo;

	@Column(name = "BORDE_GROSOR")
	private BigDecimal bordeGrosor;

	@Column(name = "BORDE_TIPO")
	private String bordeTipo;

	@Column(name = "RELLENO_COLOR")
	private String rellenoColor;

	@Column(name = "RELLENO_ESTILO")
	private String rellenoEstilo;

	@Column(name = "RELLENO_TIPO")
	private String rellenoTipo;

	@Column(name = "ULTIMO_WKID")
	private BigDecimal ultimoWkid;

	@Column(name = "WKID")
	private BigDecimal wkid;

	// bi-directional many-to-one association to Subpoligono
	@OneToMany(mappedBy = "poligono", cascade = CascadeType.ALL)
	private List<Subpoligono> subpoligonos;

	// bi-directional many-to-one association to Poligono
	@ManyToOne
	@JoinColumn(name = "ID_CULTIVO")
	private Cultivo cultivo;

	public Poligono() {
	}

	public BigDecimal getIdPoligono() {
		return this.idPoligono;
	}

	public void setIdPoligono(BigDecimal idPoligono) {
		this.idPoligono = idPoligono;
	}

	public String getBordeColor() {
		return this.bordeColor;
	}

	public void setBordeColor(String bordeColor) {
		this.bordeColor = bordeColor;
	}

	public String getBordeEstilo() {
		return this.bordeEstilo;
	}

	public void setBordeEstilo(String bordeEstilo) {
		this.bordeEstilo = bordeEstilo;
	}

	public BigDecimal getBordeGrosor() {
		return this.bordeGrosor;
	}

	public void setBordeGrosor(BigDecimal bordeGrosor) {
		this.bordeGrosor = bordeGrosor;
	}

	public String getBordeTipo() {
		return this.bordeTipo;
	}

	public void setBordeTipo(String bordeTipo) {
		this.bordeTipo = bordeTipo;
	}

	public String getRellenoColor() {
		return this.rellenoColor;
	}

	public void setRellenoColor(String rellenoColor) {
		this.rellenoColor = rellenoColor;
	}

	public String getRellenoEstilo() {
		return this.rellenoEstilo;
	}

	public void setRellenoEstilo(String rellenoEstilo) {
		this.rellenoEstilo = rellenoEstilo;
	}

	public String getRellenoTipo() {
		return this.rellenoTipo;
	}

	public void setRellenoTipo(String rellenoTipo) {
		this.rellenoTipo = rellenoTipo;
	}

	public BigDecimal getUltimoWkid() {
		return this.ultimoWkid;
	}

	public void setUltimoWkid(BigDecimal ultimoWkid) {
		this.ultimoWkid = ultimoWkid;
	}

	public BigDecimal getWkid() {
		return this.wkid;
	}

	public void setWkid(BigDecimal wkid) {
		this.wkid = wkid;
	}

	public List<Subpoligono> getSubpoligonos() {
		return this.subpoligonos;
	}

	public void setSubpoligonos(List<Subpoligono> subpoligonos) {
		this.subpoligonos = subpoligonos;
	}

	public Subpoligono addSubpoligono(Subpoligono subpoligono) {
		getSubpoligonos().add(subpoligono);
		subpoligono.setPoligono(this);

		return subpoligono;
	}

	public Subpoligono removeSubpoligono(Subpoligono subpoligono) {
		getSubpoligonos().remove(subpoligono);
		subpoligono.setPoligono(null);

		return subpoligono;
	}

	public Cultivo getCultivo() {
		return cultivo;
	}

	public void setCultivo(Cultivo cultivo) {
		this.cultivo = cultivo;
	}

}