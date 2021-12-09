package com.segurosbolivar.avaluos.modelo.entity;

import static org.eclipse.persistence.annotations.CacheType.NONE;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the PGB_MAESTRO_CARGUES database table.
 * 
 */
@Entity
@Table(name = "PGB_MAESTRO_CARGUES")
@NamedQuery(name = "MaestroCargue.findAll", query = "SELECT p FROM MaestroCargue p")
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class MaestroCargue implements Serializable {
	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_MAESTRO_CARGUES_PK_ID_MAESTRO_CARGUE = "MaestroCarguePK.idMaestroCargue";
	public static final String ENTIDAD_PGB_MAESTRO_CARGUES_PK_SECUENCIA_ARCHIVO = "MaestroCarguePK.secuenciaArchivo";
	public static final String ENTIDAD_PGB_MAESTRO_CARGUES_ESTADO = "estado";
	public static final String ENTIDAD_PGB_MAESTRO_CARGUES_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_MAESTRO_CARGUES_FECHA_TRANSACCION = "fechaTransaccion";
	public static final String ENTIDAD_PGB_MAESTRO_CARGUES_NUM_REGISTROS = "numRegistros";
	public static final String ENTIDAD_PGB_MAESTRO_CARGUES_TIENE_ANEXOS = "tieneAnexos";
	public static final String ENTIDAD_PGB_MAESTRO_CARGUES_NOMBRE_ARCHIVO = "nombreArchivo";
	private static final String[] ATRIBUTOS_ENTIDAD_PGB_MAESTRO_CARGUES = { ENTIDAD_PGB_MAESTRO_CARGUES_TIENE_ANEXOS,
			ENTIDAD_PGB_MAESTRO_CARGUES_FECHA_TRANSACCION, ENTIDAD_PGB_MAESTRO_CARGUES_PK_ID_MAESTRO_CARGUE,
			ENTIDAD_PGB_MAESTRO_CARGUES_ESTADO, ENTIDAD_PGB_MAESTRO_CARGUES_NUM_REGISTROS,
			ENTIDAD_PGB_MAESTRO_CARGUES_PK_SECUENCIA_ARCHIVO, ENTIDAD_PGB_MAESTRO_CARGUES_USUARIO_TRANSACCION,
			ENTIDAD_PGB_MAESTRO_CARGUES_NOMBRE_ARCHIVO };

	@EmbeddedId
	private MaestroCarguePK id;

	@Column(name = "ESTADO", nullable = false, length = 1)
	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_TRANSACCION", nullable = false)
	private Date fechaTransaccion;

	@Column(name = "NOMBRE_ARCHIVO", nullable = false, length = 250)
	private String nombreArchivo;

	@Column(name = "NUM_REGISTROS", nullable = false, precision = 6)
	private BigDecimal numRegistros;

	@Column(name = "TIENE_ANEXOS", length = 1)
	private String tieneAnexos;

	@Column(name = "USUARIO_TRANSACCION", nullable = false, length = 15)
	private String usuarioTransaccion;

	// bi-directional many-to-one association to ErroresCargue
	@OneToMany(mappedBy = "maestroCargue")
	private List<ErroresCargue> erroresCargues;

	// protected region atributos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region atributos adicionales end

	public MaestroCargue() {
		// protected region procedimientos adicionales de inicializaci�n on
		// begin
		// Escriba en esta secci�n sus modificaciones

		// protected region procedimientos adicionales de inicializaci�n end
	}

	public MaestroCarguePK getId() {
		return this.id;
	}

	public void setId(MaestroCarguePK id) {
		this.id = id;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaTransaccion() {
		return this.fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public String getNombreArchivo() {
		return this.nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public BigDecimal getNumRegistros() {
		return this.numRegistros;
	}

	public void setNumRegistros(BigDecimal numRegistros) {
		this.numRegistros = numRegistros;
	}

	public String getTieneAnexos() {
		return this.tieneAnexos;
	}

	public void setTieneAnexos(String tieneAnexos) {
		this.tieneAnexos = tieneAnexos;
	}

	public String getUsuarioTransaccion() {
		return this.usuarioTransaccion;
	}

	public void setUsuarioTransaccion(String usuarioTransaccion) {
		this.usuarioTransaccion = usuarioTransaccion;
	}

	public List<ErroresCargue> getErroresCargues() {
		return this.erroresCargues;
	}

	public void setErroresCargues(List<ErroresCargue> erroresCargues) {
		this.erroresCargues = erroresCargues;
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
		for (final String atr : ATRIBUTOS_ENTIDAD_PGB_MAESTRO_CARGUES) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	public static String[] getAtributosEntidadMaestroCargue() {
		return ATRIBUTOS_ENTIDAD_PGB_MAESTRO_CARGUES;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.id);
		hash = 37 * hash + Objects.hashCode(this.estado);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
		hash = 37 * hash + Objects.hashCode(this.numRegistros);
		hash = 37 * hash + Objects.hashCode(this.tieneAnexos);
		hash = 37 * hash + Objects.hashCode(this.nombreArchivo);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad MaestroCargue que se
	 * pasa como par�metro comprobando que comparten los mismos valores en cada
	 * uno de sus atributos. Solo se tienen en cuenta los atributos simples, es
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
		final MaestroCargue other = (MaestroCargue) obj;

		if (!Objects.equals(this.id, other.id)) {
			return false;
		}

		if (!Objects.equals(this.estado, other.estado)) {
			return false;
		}

		if (!Objects.equals(this.usuarioTransaccion, other.usuarioTransaccion)) {
			return false;
		}

		if (!Objects.equals(this.fechaTransaccion, other.fechaTransaccion)) {
			return false;
		}

		if (!Objects.equals(this.numRegistros, other.numRegistros)) {
			return false;
		}

		if (!Objects.equals(this.tieneAnexos, other.tieneAnexos)) {
			return false;
		}

		return Objects.equals(this.nombreArchivo, other.nombreArchivo);

	}

	// protected region metodos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region metodos adicionales end

}