package com.segurosbolivar.avaluos.modelo.entity;

import static org.eclipse.persistence.annotations.CacheType.NONE;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the PGB_ARCHIVO_PLANO database table.
 * 
 */
@Entity
@Table(name = "PGB_ARCHIVO_PLANO")
@NamedQuery(name = "ArchivoPlano.findAll", query = "SELECT p FROM ArchivoPlano p")
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class ArchivoPlano implements Serializable {
	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_ARCHIVO_PLANO_PK = "idPgbArchivoPlano";
	public static final String ENTIDAD_PGB_ARCHIVO_PLANO_CONSECUTIVO_PLANO = "consecutivoPlano";
	public static final String ENTIDAD_PGB_ARCHIVO_PLANO_TABLA_BD = "tablaBd";
	public static final String ENTIDAD_PGB_ARCHIVO_PLANO_COLUMNA_BD = "columnaBd";
	public static final String ENTIDAD_PGB_ARCHIVO_PLANO_TIPO_DATO = "tipoDato";
	public static final String ENTIDAD_PGB_ARCHIVO_PLANO_FORMATO = "formato";
	public static final String ENTIDAD_PGB_ARCHIVO_PLANO_DOMINIO = "dominio";
	public static final String ENTIDAD_PGB_ARCHIVO_PLANO_OBLIGATORIEDAD_APARTAMENTO = "obligatoriedadApartamento";
	public static final String ENTIDAD_PGB_ARCHIVO_PLANO_OBLIGATORIEDAD_BODEGA = "obligatoriedadBodega";
	public static final String ENTIDAD_PGB_ARCHIVO_PLANO_OBLIGATORIEDAD_CASA = "obligatoriedadCasa";
	public static final String ENTIDAD_PGB_ARCHIVO_PLANO_OBLIGATORIEDAD_CASARURAL = "obligatoriedadCasarural";
	public static final String ENTIDAD_PGB_ARCHIVO_PLANO_OBLIGATORIEDAD_LOCAL = "obligatoriedadLocal";
	public static final String ENTIDAD_PGB_ARCHIVO_PLANO_OBLIGATORIEDAD_LOTE = "obligatoriedadLote";
	public static final String ENTIDAD_PGB_ARCHIVO_PLANO_OBLIGATORIEDAD_LOTEURBANO = "obligatoriedadLoteurbano";
	public static final String ENTIDAD_PGB_ARCHIVO_PLANO_OBLIGATORIEDAD_OFICINA = "obligatoriedadOficina";
	public static final String ENTIDAD_PGB_ARCHIVO_PLANO_ES_ID = "esId";
	public static final String ENTIDAD_PGB_ARCHIVO_PLANO_FUNCION_CONVERSION = "funcionConversion";
	public static final String ENTIDAD_PGB_ARCHIVO_PLANO_ES_ASOBANCARIA = "esAsobancaria";
	public static final String ENTIDAD_PGB_ARCHIVO_PLANO_DESCRIPCION_CAMPO = "descripcionCampo";
	public static final String ENTIDAD_PGB_ARCHIVO_PLANO_LONGITUD_VISUAL_CAMPO = "longitudVisualCampo";
	public static final String ENTIDAD_PGB_ARCHIVO_PLANO_ES_CARGUE_MASIVO = "esCargueMasivo";
	public static final String ENTIDAD_PGB_ARCHIVO_PLANO_ORDEN_MALLA = "ordenMalla";
	public static final String ENTIDAD_PGB_ARCHIVO_PLANO_ESTADO_CAMPO = "estadoCampo";
	public static final String ENTIDAD_PGB_ARCHIVO_PLANO_PESTANA_FORMULARIO = "pestanaFormulario";
	private static final String[] ATRIBUTOS_ENTIDAD_PGB_ARCHIVO_PLANO = { ENTIDAD_PGB_ARCHIVO_PLANO_ESTADO_CAMPO,
			ENTIDAD_PGB_ARCHIVO_PLANO_COLUMNA_BD, ENTIDAD_PGB_ARCHIVO_PLANO_PK, ENTIDAD_PGB_ARCHIVO_PLANO_TABLA_BD,
			ENTIDAD_PGB_ARCHIVO_PLANO_TIPO_DATO, ENTIDAD_PGB_ARCHIVO_PLANO_ES_ASOBANCARIA,
			ENTIDAD_PGB_ARCHIVO_PLANO_OBLIGATORIEDAD_LOTE, ENTIDAD_PGB_ARCHIVO_PLANO_OBLIGATORIEDAD_LOTEURBANO,
			ENTIDAD_PGB_ARCHIVO_PLANO_OBLIGATORIEDAD_OFICINA, ENTIDAD_PGB_ARCHIVO_PLANO_OBLIGATORIEDAD_CASARURAL,
			ENTIDAD_PGB_ARCHIVO_PLANO_ORDEN_MALLA, ENTIDAD_PGB_ARCHIVO_PLANO_DOMINIO,
			ENTIDAD_PGB_ARCHIVO_PLANO_ES_CARGUE_MASIVO, ENTIDAD_PGB_ARCHIVO_PLANO_ES_ID,
			ENTIDAD_PGB_ARCHIVO_PLANO_OBLIGATORIEDAD_BODEGA, ENTIDAD_PGB_ARCHIVO_PLANO_PESTANA_FORMULARIO,
			ENTIDAD_PGB_ARCHIVO_PLANO_LONGITUD_VISUAL_CAMPO, ENTIDAD_PGB_ARCHIVO_PLANO_OBLIGATORIEDAD_LOCAL,
			ENTIDAD_PGB_ARCHIVO_PLANO_DESCRIPCION_CAMPO, ENTIDAD_PGB_ARCHIVO_PLANO_OBLIGATORIEDAD_CASA,
			ENTIDAD_PGB_ARCHIVO_PLANO_CONSECUTIVO_PLANO, ENTIDAD_PGB_ARCHIVO_PLANO_OBLIGATORIEDAD_APARTAMENTO,
			ENTIDAD_PGB_ARCHIVO_PLANO_FUNCION_CONVERSION, ENTIDAD_PGB_ARCHIVO_PLANO_FORMATO };

	@Id
	@Column(name = "ID_PGB_ARCHIVO_PLANO")
	private Long  idPgbArchivoPlano;

	@Column(name = "CONSECUTIVO_PLANO", nullable = false, precision = 3)
	private Long consecutivoPlano;

	@Column(name = "COLUMNA_BD", length = 200)
	private String columnaBd;

	@Column(name="DOMINIO", length=100)
	private String dominio;

	@Column(name = "ES_ID", length = 1)
	private String esId;

	@Column(name = "FORMATO", length = 500)
	private String formato;

	@Column(name = "OBLIGATORIEDAD_APARTAMENTO", length = 300)
	private String obligatoriedadApartamento;

	@Column(name = "OBLIGATORIEDAD_BODEGA", length = 300)
	private String obligatoriedadBodega;

	@Column(name = "OBLIGATORIEDAD_CASA", length = 300)
	private String obligatoriedadCasa;

	@Column(name = "OBLIGATORIEDAD_CASARURAL", length = 300)
	private String obligatoriedadCasarural;

	@Column(name = "OBLIGATORIEDAD_LOCAL", length = 300)
	private String obligatoriedadLocal;

	@Column(name = "OBLIGATORIEDAD_LOTE", length = 300)
	private String obligatoriedadLote;

	@Column(name = "OBLIGATORIEDAD_LOTEURBANO", length = 300)
	private String obligatoriedadLoteurbano;

	@Column(name = "OBLIGATORIEDAD_OFICINA", length = 300)
	private String obligatoriedadOficina;

	@Column(name = "TABLA_BD", length = 100)
	private String tablaBd;

	@Column(name = "TIPO_DATO", length = 100)
	private String tipoDato;

	@Column(name = "FUNCION_CONVERSION", length = 1)
	private String funcionConversion;

	@Column(name = "ES_ASOBANCARIA", length = 1)
	private String esAsobancaria;

	@Column(name = "LONGITUD_VISUAL_CAMPO", length = 1)
	private Long longitudVisualCampo;

	@Column(name = "DESCRIPCION_CAMPO")
	private String descripcionCampo;

	@Column(name = "ES_CARGUE_MASIVO")
	private String esCargueMasivo;

	@Column(name = "ORDEN_MALLA")
	private Long ordenMalla;

	@Column(name = "ESTADO_CAMPO")
	private String estadoCampo;

	@Column(name = "PESTANA_FORMULARIO")
	private String pestanaFormulario;
	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end

	public ArchivoPlano() {
		// protected region procedimientos adicionales de inicialización on
		// begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
	}

	public Long getConsecutivoPlano() {
		return this.consecutivoPlano;
	}

	public void setConsecutivoPlano(Long consecutivoPlano) {
		this.consecutivoPlano = consecutivoPlano;
	}

	public String getColumnaBd() {
		return this.columnaBd;
	}

	public void setColumnaBd(String columnaBd) {
		this.columnaBd = columnaBd;
	}

	public String getDominio() {
		return this.dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getEsId() {
		return this.esId;
	}

	public void setEsId(String esId) {
		this.esId = esId;
	}

	public String getFormato() {
		return this.formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getObligatoriedadApartamento() {
		return this.obligatoriedadApartamento;
	}

	public void setObligatoriedadApartamento(String obligatoriedadApartamento) {
		this.obligatoriedadApartamento = obligatoriedadApartamento;
	}

	public String getObligatoriedadBodega() {
		return this.obligatoriedadBodega;
	}

	public void setObligatoriedadBodega(String obligatoriedadBodega) {
		this.obligatoriedadBodega = obligatoriedadBodega;
	}

	public String getObligatoriedadCasa() {
		return this.obligatoriedadCasa;
	}

	public void setObligatoriedadCasa(String obligatoriedadCasa) {
		this.obligatoriedadCasa = obligatoriedadCasa;
	}

	public String getObligatoriedadCasarural() {
		return this.obligatoriedadCasarural;
	}

	public void setObligatoriedadCasarural(String obligatoriedadCasarural) {
		this.obligatoriedadCasarural = obligatoriedadCasarural;
	}

	public String getObligatoriedadLocal() {
		return this.obligatoriedadLocal;
	}

	public void setObligatoriedadLocal(String obligatoriedadLocal) {
		this.obligatoriedadLocal = obligatoriedadLocal;
	}

	public String getObligatoriedadLote() {
		return this.obligatoriedadLote;
	}

	public void setObligatoriedadLote(String obligatoriedadLote) {
		this.obligatoriedadLote = obligatoriedadLote;
	}

	public String getObligatoriedadLoteurbano() {
		return this.obligatoriedadLoteurbano;
	}

	public void setObligatoriedadLoteurbano(String obligatoriedadLoteurbano) {
		this.obligatoriedadLoteurbano = obligatoriedadLoteurbano;
	}

	public String getObligatoriedadOficina() {
		return this.obligatoriedadOficina;
	}

	public void setObligatoriedadOficina(String obligatoriedadOficina) {
		this.obligatoriedadOficina = obligatoriedadOficina;
	}

	public String getTablaBd() {
		return this.tablaBd;
	}

	public void setTablaBd(String tablaBd) {
		this.tablaBd = tablaBd;
	}

	public String getTipoDato() {
		return this.tipoDato;
	}

	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}

	public String getEsAsobancaria() {
		return esAsobancaria;
	}

	public void setEsAsobancaria(String esAsobancaria) {
		this.esAsobancaria = esAsobancaria;
	}

	public String getFuncionConversion() {
		return funcionConversion;
	}

	public void setFuncionConversion(String funcionConversion) {
		this.funcionConversion = funcionConversion;
	}

	public Long getLongitudVisualCampo() {
		return longitudVisualCampo;
	}

	public void setLongitudVisualCampo(Long longitudVisualCampo) {
		this.longitudVisualCampo = longitudVisualCampo;
	}

	public Long getIdPgbArchivoPlano() {
		return idPgbArchivoPlano;
	}

	public void setIdPgbArchivoPlano(Long idPgbArchivoPlano) {
		this.idPgbArchivoPlano = idPgbArchivoPlano;
	}

	public String getDescripcionCampo() {
		return descripcionCampo;
	}

	public void setDescripcionCampo(String descripcionCampo) {
		this.descripcionCampo = descripcionCampo;
	}

	public String getEsCargueMasivo() {
		return esCargueMasivo;
	}

	public void setEsCargueMasivo(String esCargueMasivo) {
		this.esCargueMasivo = esCargueMasivo;
	}

	public Long getOrdenMalla() {
		return ordenMalla;
	}

	public void setOrdenMalla(Long ordenMalla) {
		this.ordenMalla = ordenMalla;
	}

	public String getEstadoCampo() {
		return estadoCampo;
	}

	public void setEstadoCampo(String estadoCampo) {
		this.estadoCampo = estadoCampo;
	}

	public String getPestanaFormulario() {
		return pestanaFormulario;
	}

	public void setPestanaFormulario(String pestanaFormulario) {
		this.pestanaFormulario = pestanaFormulario;
	}

	/**
	 * Verifica si la entidad contiene el atributo que se pasa como parámetro
	 *
	 * @param atributo
	 *            Nombre del atributo a validar
	 * @return Verdadero si la entidad contiene al atributo.
	 */
	public static boolean contieneAtributo(String atributo) {

		boolean contiene = false;
		for (final String atr : ATRIBUTOS_ENTIDAD_PGB_ARCHIVO_PLANO) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	public static String[] getAtributosEntidadPgbArchivoPlano() {
		return ATRIBUTOS_ENTIDAD_PGB_ARCHIVO_PLANO;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idPgbArchivoPlano);
		hash = 37 * hash + Objects.hashCode(this.consecutivoPlano);
		hash = 37 * hash + Objects.hashCode(this.tablaBd);
		hash = 37 * hash + Objects.hashCode(this.columnaBd);
		hash = 37 * hash + Objects.hashCode(this.tipoDato);
		hash = 37 * hash + Objects.hashCode(this.formato);
		hash = 37 * hash + Objects.hashCode(this.dominio);
		hash = 37 * hash + Objects.hashCode(this.obligatoriedadApartamento);
		hash = 37 * hash + Objects.hashCode(this.obligatoriedadBodega);
		hash = 37 * hash + Objects.hashCode(this.obligatoriedadCasa);
		hash = 37 * hash + Objects.hashCode(this.obligatoriedadCasarural);
		hash = 37 * hash + Objects.hashCode(this.obligatoriedadLocal);
		hash = 37 * hash + Objects.hashCode(this.obligatoriedadLote);
		hash = 37 * hash + Objects.hashCode(this.obligatoriedadLoteurbano);
		hash = 37 * hash + Objects.hashCode(this.obligatoriedadOficina);
		hash = 37 * hash + Objects.hashCode(this.esId);
		hash = 37 * hash + Objects.hashCode(this.funcionConversion);
		hash = 37 * hash + Objects.hashCode(this.esAsobancaria);
		hash = 37 * hash + Objects.hashCode(this.descripcionCampo);
		hash = 37 * hash + Objects.hashCode(this.longitudVisualCampo);
		hash = 37 * hash + Objects.hashCode(this.esCargueMasivo);
		hash = 37 * hash + Objects.hashCode(this.ordenMalla);
		hash = 37 * hash + Objects.hashCode(this.estadoCampo);
		hash = 37 * hash + Objects.hashCode(this.pestanaFormulario);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad PgbArchivoPlano que se
	 * pasa como parámetro comprobando que comparten los mismos valores en cada
	 * uno de sus atributos. Solo se tienen en cuenta los atributos simples, es
	 * decir, se omiten aquellos que definen una relación con otra tabla.
	 *
	 * @param obj
	 *            Instancia de la categoría a comprobar iguales.
	 * @return Verdadero si esta instancia y la que se pasan como parámetros son
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ArchivoPlano other = (ArchivoPlano) obj;

		if (!Objects.equals(this.idPgbArchivoPlano, other.idPgbArchivoPlano)) {
			return false;
		}

		if (!Objects.equals(this.consecutivoPlano, other.consecutivoPlano)) {
			return false;
		}

		if (!Objects.equals(this.tablaBd, other.tablaBd)) {
			return false;
		}

		if (!Objects.equals(this.columnaBd, other.columnaBd)) {
			return false;
		}

		if (!Objects.equals(this.tipoDato, other.tipoDato)) {
			return false;
		}

		if (!Objects.equals(this.formato, other.formato)) {
			return false;
		}

		if (!Objects.equals(this.dominio, other.dominio)) {
			return false;
		}

		if (!Objects.equals(this.obligatoriedadApartamento, other.obligatoriedadApartamento)) {
			return false;
		}

		if (!Objects.equals(this.obligatoriedadBodega, other.obligatoriedadBodega)) {
			return false;
		}

		if (!Objects.equals(this.obligatoriedadCasa, other.obligatoriedadCasa)) {
			return false;
		}

		if (!Objects.equals(this.obligatoriedadCasarural, other.obligatoriedadCasarural)) {
			return false;
		}

		if (!Objects.equals(this.obligatoriedadLocal, other.obligatoriedadLocal)) {
			return false;
		}

		if (!Objects.equals(this.obligatoriedadLote, other.obligatoriedadLote)) {
			return false;
		}

		if (!Objects.equals(this.obligatoriedadLoteurbano, other.obligatoriedadLoteurbano)) {
			return false;
		}

		if (!Objects.equals(this.obligatoriedadOficina, other.obligatoriedadOficina)) {
			return false;
		}

		if (!Objects.equals(this.esId, other.esId)) {
			return false;
		}

		if (!Objects.equals(this.funcionConversion, other.funcionConversion)) {
			return false;
		}

		if (!Objects.equals(this.esAsobancaria, other.esAsobancaria)) {
			return false;
		}

		if (!Objects.equals(this.descripcionCampo, other.descripcionCampo)) {
			return false;
		}

		if (!Objects.equals(this.longitudVisualCampo, other.longitudVisualCampo)) {
			return false;
		}

		if (!Objects.equals(this.esCargueMasivo, other.esCargueMasivo)) {
			return false;
		}

		if (!Objects.equals(this.ordenMalla, other.ordenMalla)) {
			return false;
		}

		if (!Objects.equals(this.estadoCampo, other.estadoCampo)) {
			return false;
		}

		return Objects.equals(this.pestanaFormulario, other.pestanaFormulario);

	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}