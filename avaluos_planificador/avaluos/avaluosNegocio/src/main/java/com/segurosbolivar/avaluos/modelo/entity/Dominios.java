package com.segurosbolivar.avaluos.modelo.entity;

import static org.eclipse.persistence.annotations.CacheType.SOFT;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the CG_REF_CODES database table.
 * 
 * 
 */
@Entity
@Table(name = "CG_REF_CODES")
@NamedQueries({
		@NamedQuery(name = "Dominios.getDominios", query = "SELECT  d FROM Dominios d WHERE d.rvCreateBy <> \"SEGURIDAD\" order by d.rvDomain"),
		@NamedQuery(name = "Dominios.findbyMeaning", query = "SELECT  d FROM Dominios d WHERE d.rvDomain = :nombreDominio and d.rvMeaning =:valorDominio "),
		@NamedQuery(name = "Dominios.findbyLowValue", query = "SELECT  d FROM Dominios d WHERE d.rvDomain = :nombreDominio and d.rvLowValue =:valorDominio "),
		@NamedQuery(name = "Dominios.cantidadRegistros", query = "SELECT COUNT( DISTINCT d.rvDomain) FROM Dominios d WHERE d.rvCreateBy <> \"SEGURIDAD\""),
		@NamedQuery(name = "Dominios.cantidadValores", query = "SELECT COUNT( d.idCgRefCodes) FROM Dominios d WHERE d.rvDomain = :nombreDominio"),
		@NamedQuery(name = "Dominios.cantidadValoresFiltro", query = "SELECT COUNT( d.idCgRefCodes) FROM Dominios d WHERE d.rvDomain = :nombreDominio AND d.rvMeaning = :valorDominio "),
		@NamedQuery(name = "Dominios.getNombresDominios", query = "SELECT DISTINCT d.rvDomain FROM Dominios d WHERE d.rvCreateBy <> \"SEGURIDAD\" order by d.rvDomain"),
		@NamedQuery(name = "Dominios.getValoresDominio", query = "SELECT d FROM Dominios d WHERE d.rvDomain = :nombreDominio order by d.rvMeaning"),
		@NamedQuery(name = "Dominios.getValoresDominioFiltro", query = "SELECT d FROM Dominios d WHERE d.rvDomain = :nombreDominio AND d.rvMeaning = :valorDominio order by d.rvMeaning"),
		@NamedQuery(name = "Dominios.findAll", query = "SELECT p FROM Dominios p") })
@org.eclipse.persistence.annotations.Cache(alwaysRefresh = false, refreshOnlyIfNewer = true, type = SOFT)
public class Dominios implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_CG_REF_CODES_PK = "idCgRefCodes";
	public static final String ENTIDAD_CG_REF_CODES_RV_DOMAIN = "rvDomain";
	public static final String ENTIDAD_CG_REF_CODES_RV_LOW_VALUE = "rvLowValue";
	public static final String ENTIDAD_CG_REF_CODES_RV_HIGH_VALUE = "rvHighValue";
	public static final String ENTIDAD_CG_REF_CODES_RV_ABBREVIATION = "rvAbbreviation";
	public static final String ENTIDAD_CG_REF_CODES_RV_MEANING = "rvMeaning";
	public static final String ENTIDAD_CG_REF_CODES_RV_CREATE_BY = "rvCreateBy";
	private static final String[] ATRIBUTOS_ENTIDAD_CG_REF_CODES = { ENTIDAD_CG_REF_CODES_RV_HIGH_VALUE,
			ENTIDAD_CG_REF_CODES_RV_DOMAIN, ENTIDAD_CG_REF_CODES_RV_MEANING, ENTIDAD_CG_REF_CODES_RV_ABBREVIATION,
			ENTIDAD_CG_REF_CODES_RV_CREATE_BY, ENTIDAD_CG_REF_CODES_PK, ENTIDAD_CG_REF_CODES_RV_LOW_VALUE };

	@Id
	@SequenceGenerator(name = "CG_REF_CODES_IDCGREFCODES_GENERATOR", sequenceName = "SEQ_CG_REF_CODES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CG_REF_CODES_IDCGREFCODES_GENERATOR")
	@Column(name = "ID_CG_REF_CODES")
	private Long idCgRefCodes;

	@Column(name = "RV_ABBREVIATION")
	private String rvAbbreviation;

	@Column(name = "RV_CREATE_BY")
	private String rvCreateBy;

	@Column(name = "RV_DOMAIN")
	private String rvDomain;

	@Column(name = "RV_HIGH_VALUE")
	private String rvHighValue;

	@Column(name = "RV_LOW_VALUE")
	private String rvLowValue;

	@Column(name = "RV_MEANING")
	private String rvMeaning;

	// protected region atributos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region atributos adicionales end

	public Dominios() {
		// protected region procedimientos adicionales de inicializaci�n on
		// begin
		// Escriba en esta secci�n sus modificaciones

		// protected region procedimientos adicionales de inicializaci�n end
	}

	public Dominios(String rvDomain, String rvLowValue, String rvMeaning) {
		this.rvDomain = rvDomain;
		this.rvLowValue = rvLowValue;
		this.rvMeaning = rvMeaning;
	}

	@Override
	public Dominios clone() throws CloneNotSupportedException {
			return (Dominios) super.clone();
	}

	public Long getIdCgRefCodes() {
		return this.idCgRefCodes;
	}

	public void setIdCgRefCodes(Long idCgRefCodes) {
		this.idCgRefCodes = idCgRefCodes;
	}

	public String getRvAbbreviation() {
		return this.rvAbbreviation;
	}

	public void setRvAbbreviation(String rvAbbreviation) {
		this.rvAbbreviation = rvAbbreviation;
	}

	public String getRvCreateBy() {
		return this.rvCreateBy;
	}

	public void setRvCreateBy(String rvCreateBy) {
		this.rvCreateBy = rvCreateBy;
	}

	public String getRvDomain() {
		return this.rvDomain;
	}

	public void setRvDomain(String rvDomain) {
		this.rvDomain = rvDomain;
	}

	public String getRvHighValue() {
		return this.rvHighValue;
	}

	public void setRvHighValue(String rvHighValue) {
		this.rvHighValue = rvHighValue;
	}

	public String getRvLowValue() {
		return this.rvLowValue;
	}

	public void setRvLowValue(String rvLowValue) {
		this.rvLowValue = rvLowValue;
	}

	public String getRvMeaning() {
		return this.rvMeaning;
	}

	public void setRvMeaning(String rvMeaning) {
		this.rvMeaning = rvMeaning;
	}

	/**
	 * Verifica si la entidad contiene el atributo que se pasa como par�metro
	 *
	 * @param atributo
	 *            Nombre del atributo a validar
	 * @return Verdadero si la entidad contiene al atributo.
	 */
	public static boolean contieneAtributo(String atributo) {

		boolean contiene = false;
		for (final String atr : ATRIBUTOS_ENTIDAD_CG_REF_CODES) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	public static String[] getAtributosEntidadCgRefCodes() {
		return ATRIBUTOS_ENTIDAD_CG_REF_CODES;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idCgRefCodes);
		hash = 37 * hash + Objects.hashCode(this.rvDomain);
		hash = 37 * hash + Objects.hashCode(this.rvLowValue);
		hash = 37 * hash + Objects.hashCode(this.rvHighValue);
		hash = 37 * hash + Objects.hashCode(this.rvAbbreviation);
		hash = 37 * hash + Objects.hashCode(this.rvMeaning);
		hash = 37 * hash + Objects.hashCode(this.rvCreateBy);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad CgRefCodes que se pasa
	 * como par�metro comprobando que comparten los mismos valores en cada uno
	 * de sus atributos. Solo se tienen en cuenta los atributos simples, es
	 * decir, se omiten aquellos que definen una relaci�n con otra tabla.
	 *
	 * @param obj
	 *            Instancia de la categor�a a comprobar iguales.
	 * @return Verdadero si esta instancia y la que se pasan como par�metros son
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Dominios other = (Dominios) obj;

		if (!Objects.equals(this.idCgRefCodes, other.idCgRefCodes)) {
			return false;
		}

		if (!Objects.equals(this.rvDomain, other.rvDomain)) {
			return false;
		}

		if (!Objects.equals(this.rvLowValue, other.rvLowValue)) {
			return false;
		}

		if (!Objects.equals(this.rvHighValue, other.rvHighValue)) {
			return false;
		}

		if (!Objects.equals(this.rvAbbreviation, other.rvAbbreviation)) {
			return false;
		}

		if (!Objects.equals(this.rvMeaning, other.rvMeaning)) {
			return false;
		}

		return Objects.equals(this.rvCreateBy, other.rvCreateBy);

	}

	// protected region metodos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region metodos adicionales end

}