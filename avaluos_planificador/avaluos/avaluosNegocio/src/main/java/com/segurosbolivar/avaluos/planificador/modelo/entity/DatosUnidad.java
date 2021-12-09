package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DATOS_UNIDAD database table.
 * 
 */
@Entity
@Cacheable(false)
@Table(name="DATOS_UNIDAD")
@NamedQuery(name="DatosUnidad.findAll", query="SELECT d FROM DatosUnidad d")
public class DatosUnidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_DATOS_UNIDAD")
	private long idDatosUnidad;

	@Column(name="ID_CULTIVO")
	private BigDecimal idCultivo;

	private BigDecimal porcentaje;

	@Column(name="VALOR_PARAMETRO")
	private String valorParametro;

	//bi-directional many-to-one association to Parametro
	@ManyToOne
	@JoinColumn(name="ID_PARAMETRO")
	private Parametro parametro;

	//bi-directional many-to-one association to UnidadProductiva
	@ManyToOne
	@JoinColumn(name="ID_UNIDAD_PRODUCTIVA")
	private UnidadProductiva unidadProductiva;

	public DatosUnidad() {
	}

	public long getIdDatosUnidad() {
		return this.idDatosUnidad;
	}

	public void setIdDatosUnidad(long idDatosUnidad) {
		this.idDatosUnidad = idDatosUnidad;
	}

	public BigDecimal getIdCultivo() {
		return this.idCultivo;
	}

	public void setIdCultivo(BigDecimal idCultivo) {
		this.idCultivo = idCultivo;
	}

	public BigDecimal getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getValorParametro() {
		return this.valorParametro;
	}

	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}

	public Parametro getParametro() {
		return this.parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	public UnidadProductiva getUnidadProductiva() {
		return this.unidadProductiva;
	}

	public void setUnidadProductiva(UnidadProductiva unidadProductiva) {
		this.unidadProductiva = unidadProductiva;
	}

}