package com.segurosbolivar.avaluos.modelo.entity;

import static org.eclipse.persistence.annotations.CacheType.NONE;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the PGB_HORAS_CORTE_ARCHIVO database table.
 * 
 */
@Entity
@Table(name = "PGB_HORAS_CORTE_ARCHIVO")
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
@NamedQueries({ @NamedQuery(name = "HorasCorteArchivo.findAll", query = "SELECT p FROM HorasCorteArchivo p"),
		@NamedQuery(name = "HorasCorteArchivo.cantidadRegistros", query = "SELECT count(p.idHorasCorteArchivo) FROM HorasCorteArchivo p") })
public class HorasCorteArchivo implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_HORAS_CORTE_ARCHIVO_PK = "idHorasCorteArchivo";
	public static final String ENTIDAD_PGB_HORAS_CORTE_ARCHIVO_HORA_CORTE = "horaCorte";
	public static final String ENTIDAD_PGB_HORAS_CORTE_ARCHIVO_MINUTOS_CORTE = "minutosCorte";
	public static final String ENTIDAD_PGB_HORAS_CORTE_ARCHIVO_ESTADO = "estado";
	public static final String ENTIDAD_PGB_HORAS_CORTE_ARCHIVO_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_PGB_HORAS_CORTE_ARCHIVO_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_PGB_HORAS_CORTE_ARCHIVO_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_HORAS_CORTE_ARCHIVO_FECHA_TRANSACCION = "fechaTransaccion";
	private static final String[] ATRIBUTOS_ENTIDAD_PGB_HORAS_CORTE_ARCHIVO = {
			ENTIDAD_PGB_HORAS_CORTE_ARCHIVO_MINUTOS_CORTE, ENTIDAD_PGB_HORAS_CORTE_ARCHIVO_FECHA_CREACION,
			ENTIDAD_PGB_HORAS_CORTE_ARCHIVO_HORA_CORTE, ENTIDAD_PGB_HORAS_CORTE_ARCHIVO_USUARIO_TRANSACCION,
			ENTIDAD_PGB_HORAS_CORTE_ARCHIVO_ESTADO, ENTIDAD_PGB_HORAS_CORTE_ARCHIVO_FECHA_TRANSACCION,
			ENTIDAD_PGB_HORAS_CORTE_ARCHIVO_PK, ENTIDAD_PGB_HORAS_CORTE_ARCHIVO_USUARIO_CREACION };

	@Id
	@SequenceGenerator(name = "PGB_HORAS_CORTE_ARCHIVO_IDHORASCORTEARCHIVO_GENERATOR", sequenceName = "SEQ_PGB_HORAS_CORTE_ARCHIVO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGB_HORAS_CORTE_ARCHIVO_IDHORASCORTEARCHIVO_GENERATOR")
	@Column(name = "ID_HORAS_CORTE_ARCHIVO")
	private Long idHorasCorteArchivo;

	@Column(name = "ESTADO")
	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_TRANSACCION")
	private Date fechaTransaccion;

	@Column(name = "HORA_CORTE")
	private Long horaCorte;

	@Column(name = "MINUTOS_CORTE")
	private Long minutosCorte;

	@Column(name = "USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name = "USUARIO_TRANSACCION")
	private String usuarioTransaccion;

	@Transient
	private boolean editar;
	@Transient
	private Long pocision;

	// protected region atributos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region atributos adicionales end

	public HorasCorteArchivo() {
		// protected region procedimientos adicionales de inicializaci�n on
		// begin
		// Escriba en esta secci�n sus modificaciones

		// protected region procedimientos adicionales de inicializaci�n end
	}

	public HorasCorteArchivo(String estado, Long horaCorte, Long minutosCorte) {
		super();
		this.estado = estado;
		this.horaCorte = horaCorte;
		this.minutosCorte = minutosCorte;
	}

	@Override
	public HorasCorteArchivo clone() throws CloneNotSupportedException {
		return (HorasCorteArchivo) super.clone();
	}

	public Long getIdHorasCorteArchivo() {
		return this.idHorasCorteArchivo;
	}

	public void setIdHorasCorteArchivo(Long idHorasCorteArchivo) {
		this.idHorasCorteArchivo = idHorasCorteArchivo;
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

	public Long getHoraCorte() {
		return this.horaCorte;
	}

	public void setHoraCorte(Long horaCorte) {
		this.horaCorte = horaCorte;
	}

	public Long getMinutosCorte() {
		return this.minutosCorte;
	}

	public void setMinutosCorte(Long minutosCorte) {
		this.minutosCorte = minutosCorte;
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

	/**
	 * Verifica si la entidad contiene el atributo que se pasa como par�metro
	 *
	 * @param atributo
	 *            Nombre del atributo a validar
	 * @return Verdadero si la entidad contiene al atributo.
	 */
	public static boolean contieneAtributo(String atributo) {

		boolean contiene = false;
		for (final String atr : ATRIBUTOS_ENTIDAD_PGB_HORAS_CORTE_ARCHIVO) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	public static String[] getAtributosEntidadPgbHorasCorteArchivo() {
		return ATRIBUTOS_ENTIDAD_PGB_HORAS_CORTE_ARCHIVO;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idHorasCorteArchivo);
		hash = 37 * hash + Objects.hashCode(this.horaCorte);
		hash = 37 * hash + Objects.hashCode(this.minutosCorte);
		hash = 37 * hash + Objects.hashCode(this.estado);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad PgbHorasCorteArchivo que se
	 * pasa como par�metro comprobando que comparten los mismos valores en cada uno
	 * de sus atributos. Solo se tienen en cuenta los atributos simples, es decir,
	 * se omiten aquellos que definen una relaci�n con otra tabla.
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
		final HorasCorteArchivo other = (HorasCorteArchivo) obj;

		if (!Objects.equals(this.idHorasCorteArchivo, other.idHorasCorteArchivo)) {
			return false;
		}

		if (!Objects.equals(this.horaCorte, other.horaCorte)) {
			return false;
		}

		if (!Objects.equals(this.minutosCorte, other.minutosCorte)) {
			return false;
		}

		if (!Objects.equals(this.estado, other.estado)) {
			return false;
		}

		if (!Objects.equals(this.usuarioCreacion, other.usuarioCreacion)) {
			return false;
		}

		if (!Objects.equals(this.fechaCreacion, other.fechaCreacion)) {
			return false;
		}

		if (!Objects.equals(this.usuarioTransaccion, other.usuarioTransaccion)) {
			return false;
		}

		return Objects.equals(this.fechaTransaccion, other.fechaTransaccion);

	}

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	public Long getPocision() {
		return pocision;
	}

	public void setPocision(Long pocision) {
		this.pocision = pocision;
	}

	@Override
	public String toString() {
		return " HORA_CORTE [Hora=" + horaCorte + "minuto=" + minutosCorte + ", Id=" + idHorasCorteArchivo + "]";
	}

	// protected region metodos adicionales on begin
	// Escriba en esta secci�n sus modificaciones

	// protected region metodos adicionales end

}