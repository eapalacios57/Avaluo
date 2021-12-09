package com.segurosbolivar.avaluos.modelo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the PGB_HIST_LOGS_GENERA_ARCH database table.
 * 
 */
@Entity
@Table(name = "PGB_HIST_LOGS_GENERA_ARCH")
@NamedQuery(name = "HistoricoLogsGeneraArch.findAll", query = "SELECT p FROM HistoricoLogsGeneraArch p")
public class HistoricoLogsGeneraArch implements Serializable {
	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_PK = "idHistLogsGeneraArch";
	public static final String ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_ID_LOG_ARCHIVOS = "idLogArchivos";
	public static final String ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_NOMBRE_ARCHIVO = "nombreArchivo";
	public static final String ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_FECHA_DESDE = "fechaDesde";
	public static final String ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_FECHA_HASTA = "fechaHasta";
	public static final String ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_ESTADO = "estado";
	public static final String ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_ENVIADO = "enviado";
	public static final String ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_LINK_DESCARGA = "linkDescarga";
	public static final String ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_CODIGO_ERROR = "codigoError";
	public static final String ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_DESC_ERROR = "descError";
	public static final String ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_FECHA_INICIO_CREACION = "fechaInicioCreacion";
	public static final String ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_FECHA_FIN_CREACION = "fechaFinCreacion";
	public static final String ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_NOMBRE_PLANO = "nombrePlano";
	public static final String ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_FECHA_TRANSACCION = "fechaTransaccion";
	private static final String[] ATRIBUTOS_ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH = {
			ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_FECHA_HASTA, ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_FECHA_DESDE,
			ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_NOMBRE_PLANO, ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_LINK_DESCARGA,
			ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_FECHA_INICIO_CREACION, ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_ENVIADO,
			ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_DESC_ERROR, ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_USUARIO_CREACION,
			ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_PK, ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_CODIGO_ERROR,
			ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_FECHA_TRANSACCION, ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_ESTADO,
			ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_ID_LOG_ARCHIVOS, ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_NOMBRE_ARCHIVO,
			ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_FECHA_FIN_CREACION,
			ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH_USUARIO_TRANSACCION };

	@Id
	@SequenceGenerator(name = "PGB_HIST_LOGS_GENERA_ARCH_IDHISTLOGSGENERAARCH_GENERATOR", sequenceName = "SEQ_PGB_HIST_LOGS_GENERA_ARCH")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGB_HIST_LOGS_GENERA_ARCH_IDHISTLOGSGENERAARCH_GENERATOR")
	@Column(name = "ID_HIST_LOGS_GENERA_ARCH")
	private Long idHistLogsGeneraArch;

	@Column(name = "CODIGO_ERROR")
	private BigDecimal codigoError;

	@Column(name = "DESC_ERROR")
	private String descError;

	@Column(name = "ENVIADO")
	private String enviado;

	@Column(name = "ESTADO")
	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_DESDE")
	private Date fechaDesde;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_FIN_CREACION")
	private Date fechaFinCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_HASTA")
	private Date fechaHasta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_INICIO_CREACION")
	private Date fechaInicioCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_TRANSACCION")
	private Date fechaTransaccion;

	@Column(name = "ID_LOG_ARCHIVOS")
	private Long idLogArchivos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_LOG_ARCHIVOS", referencedColumnName = "ID_LOG_ARCHIVOS", insertable = false, updatable = false)
	private LogsGeneraArchivo logsGeneraArchivo;

	@Column(name = "LINK_DESCARGA")
	private String linkDescarga;

	@Column(name = "NOMBRE_ARCHIVO")
	private String nombreArchivo;

	@Column(name = "NOMBRE_PLANO")
	private String nombrePlano;

	@Column(name = "USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name = "USUARIO_TRANSACCION")
	private String usuarioTransaccion;

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end

	public HistoricoLogsGeneraArch() {
		// protected region procedimientos adicionales de inicialización on
		// begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
	}

	public BigDecimal getCodigoError() {
		return this.codigoError;
	}

	public void setCodigoError(BigDecimal codigoError) {
		this.codigoError = codigoError;
	}

	public String getDescError() {
		return this.descError;
	}

	public void setDescError(String descError) {
		this.descError = descError;
	}

	public String getEnviado() {
		return this.enviado;
	}

	public void setEnviado(String enviado) {
		this.enviado = enviado;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaDesde() {
		return this.fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaFinCreacion() {
		return this.fechaFinCreacion;
	}

	public void setFechaFinCreacion(Date fechaFinCreacion) {
		this.fechaFinCreacion = fechaFinCreacion;
	}

	public Date getFechaHasta() {
		return this.fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Date getFechaInicioCreacion() {
		return this.fechaInicioCreacion;
	}

	public void setFechaInicioCreacion(Date fechaInicioCreacion) {
		this.fechaInicioCreacion = fechaInicioCreacion;
	}

	public Date getFechaTransaccion() {
		return this.fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public Long getIdHistLogsGeneraArch() {
		return this.idHistLogsGeneraArch;
	}

	public void setIdHistLogsGeneraArch(Long idHistLogsGeneraArch) {
		this.idHistLogsGeneraArch = idHistLogsGeneraArch;
	}

	public Long getIdLogArchivos() {
		return this.idLogArchivos;
	}

	public void setIdLogArchivos(Long idLogArchivos) {
		this.idLogArchivos = idLogArchivos;
	}

	public String getLinkDescarga() {
		return this.linkDescarga;
	}

	public void setLinkDescarga(String linkDescarga) {
		this.linkDescarga = linkDescarga;
	}

	public String getNombreArchivo() {
		return this.nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getNombrePlano() {
		return this.nombrePlano;
	}

	public void setNombrePlano(String nombrePlano) {
		this.nombrePlano = nombrePlano;
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
	 * Verifica si la entidad contiene el atributo que se pasa como parámetro
	 *
	 * @param atributo
	 *            Nombre del atributo a validar
	 * @return Verdadero si la entidad contiene al atributo.
	 */
	public static boolean contieneAtributo(String atributo) {

		boolean contiene = false;
		for (final String atr : ATRIBUTOS_ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	public static String[] getAtributosEntidadHistoricoLogsGeneraArch() {
		return ATRIBUTOS_ENTIDAD_PGB_HIST_LOGS_GENERA_ARCH;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idHistLogsGeneraArch);
		hash = 37 * hash + Objects.hashCode(this.idLogArchivos);
		hash = 37 * hash + Objects.hashCode(this.nombreArchivo);
		hash = 37 * hash + Objects.hashCode(this.fechaDesde);
		hash = 37 * hash + Objects.hashCode(this.fechaHasta);
		hash = 37 * hash + Objects.hashCode(this.estado);
		hash = 37 * hash + Objects.hashCode(this.enviado);
		hash = 37 * hash + Objects.hashCode(this.linkDescarga);
		hash = 37 * hash + Objects.hashCode(this.codigoError);
		hash = 37 * hash + Objects.hashCode(this.descError);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaInicioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaFinCreacion);
		hash = 37 * hash + Objects.hashCode(this.nombrePlano);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad HistoricoLogsGeneraArch
	 * que se pasa como parámetro comprobando que comparten los mismos valores
	 * en cada uno de sus atributos. Solo se tienen en cuenta los atributos
	 * simples, es decir, se omiten aquellos que definen una relación con otra
	 * tabla.
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
		final HistoricoLogsGeneraArch other = (HistoricoLogsGeneraArch) obj;

		if (!Objects.equals(this.idHistLogsGeneraArch, other.idHistLogsGeneraArch)) {
			return false;
		}

		if (!Objects.equals(this.idLogArchivos, other.idLogArchivos)) {
			return false;
		}

		if (!Objects.equals(this.nombreArchivo, other.nombreArchivo)) {
			return false;
		}

		if (!Objects.equals(this.fechaDesde, other.fechaDesde)) {
			return false;
		}

		if (!Objects.equals(this.fechaHasta, other.fechaHasta)) {
			return false;
		}

		if (!Objects.equals(this.estado, other.estado)) {
			return false;
		}

		if (!Objects.equals(this.enviado, other.enviado)) {
			return false;
		}

		if (!Objects.equals(this.linkDescarga, other.linkDescarga)) {
			return false;
		}

		if (!Objects.equals(this.codigoError, other.codigoError)) {
			return false;
		}

		if (!Objects.equals(this.descError, other.descError)) {
			return false;
		}

		if (!Objects.equals(this.usuarioCreacion, other.usuarioCreacion)) {
			return false;
		}

		if (!Objects.equals(this.fechaInicioCreacion, other.fechaInicioCreacion)) {
			return false;
		}

		if (!Objects.equals(this.fechaFinCreacion, other.fechaFinCreacion)) {
			return false;
		}

		if (!Objects.equals(this.nombrePlano, other.nombrePlano)) {
			return false;
		}

		if (!Objects.equals(this.usuarioTransaccion, other.usuarioTransaccion)) {
			return false;
		}

		return Objects.equals(this.fechaTransaccion, other.fechaTransaccion);

	}

	public LogsGeneraArchivo getLogsGeneraArchivo() {
		return logsGeneraArchivo;
	}

	public void setLogsGeneraArchivo(LogsGeneraArchivo logsGeneraArchivo) {
		this.logsGeneraArchivo = logsGeneraArchivo;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}