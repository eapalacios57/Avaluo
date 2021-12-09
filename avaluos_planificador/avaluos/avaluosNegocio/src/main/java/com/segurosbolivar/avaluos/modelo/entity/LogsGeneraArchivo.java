package com.segurosbolivar.avaluos.modelo.entity;

import static org.eclipse.persistence.annotations.CacheType.NONE;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the PGB_LOGS_GENERA_ARCHIVOS database table.
 * 
 */
@Entity
@Table(name = "PGB_LOGS_GENERA_ARCHIVOS")
@NamedQuery(name = "LogsGeneraArchivo.findAll", query = "SELECT p FROM LogsGeneraArchivo p")
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class LogsGeneraArchivo implements Serializable {
	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_PK = "idLogArchivos";
	public static final String ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_NOMBRE_ARCHIVO = "nombreArchivo";
	public static final String ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_FECHA_DESDE = "fechaDesde";
	public static final String ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_FECHA_HASTA = "fechaHasta";
	public static final String ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_ESTADO = "estado";
	public static final String ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_ENVIADO = "enviado";
	public static final String ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_LINK_DESCARGA = "linkDescarga";
	public static final String ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_CODIGO_ERROR = "codigoError";
	public static final String ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_DESC_ERROR = "descError";
	public static final String ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_FECHA_INICIO_CREACION = "fechaInicioCreacion";
	public static final String ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_FECHA_FIN_CREACION = "fechaFinCreacion";
	public static final String ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_NOMBRE_PLANO = "nombrePlano";
	public static final String ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_FECHA_TRANSACCION = "fechaTransaccion";
	private static final String[] ATRIBUTOS_ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS = {
			ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_FECHA_DESDE, ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_FECHA_HASTA,
			ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_CODIGO_ERROR, ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_NOMBRE_ARCHIVO,
			ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_FECHA_FIN_CREACION, ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_LINK_DESCARGA,
			ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_USUARIO_CREACION, ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_FECHA_INICIO_CREACION,
			ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_ENVIADO, ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_USUARIO_TRANSACCION,
			ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_PK, ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_FECHA_TRANSACCION,
			ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_DESC_ERROR, ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_ESTADO,
			ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS_NOMBRE_PLANO };

	@Id
	@SequenceGenerator(name = "PGB_LOGS_GENERA_ARCHIVOS_IDLOGARCHIVOS_GENERATOR", sequenceName = "SEQ_PGB_LOGS_GENERA_ARCHIVOS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGB_LOGS_GENERA_ARCHIVOS_IDLOGARCHIVOS_GENERATOR")
	@Column(name = "ID_LOG_ARCHIVOS", unique = true, nullable = false, precision = 22)
	private Long idLogArchivos;

	@Column(name = "CODIGO_ERROR", precision = 22)
	private Long codigoError;

	@Column(name = "DESC_ERROR", length = 2000)
	private String descripcionError;

	@Column(name = "ENVIADO", length = 1)
	private String enviado;

	@Column(name = "ESTADO", length = 1)
	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_DESDE")
	private Date fechaDesde;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_FIN_CREACION")
	private Date fechaFinCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_HASTA")
	private Date fechaHasta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_INICIO_CREACION")
	private Date fechaInicioCreacion;

	@Column(name = "LINK_DESCARGA", length = 500)
	private String linkDescarga;

	@Column(name = "NOMBRE_ARCHIVO", length = 200)
	private String nombreArchivo;

	@Column(name = "USUARIO_CREACION", length = 15)
	private String usuarioCreacion;

	@Column(name = "NOMBRE_PLANO", length = 200)
	private String nombrePlano;

	@Column(name = "USUARIO_TRANSACCION", length = 15)
	private String usuarioTransaccion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_TRANSACCION")
	private Date fechaTransaccion;

	// bi-directional many-to-one association to PgbAvaluo
	@OneToMany(mappedBy = "logsGeneraArchivo", fetch = FetchType.LAZY)
	private List<Avaluo> avaluos;

	@OneToMany(mappedBy = "logsGeneraArchivo", fetch = FetchType.LAZY)
	private List<HistoricoLogsGeneraArch> historico;

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	@Transient
	private String cantReg;

	// protected region atributos adicionales end

	public LogsGeneraArchivo() {
		// protected region procedimientos adicionales de inicialización on
		// begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
	}

	public Long getIdLogArchivos() {
		return this.idLogArchivos;
	}

	public void setIdLogArchivos(Long idLogArchivos) {
		this.idLogArchivos = idLogArchivos;
	}

	public Long getCodigoError() {
		return this.codigoError;
	}

	public void setCodigoError(Long codigoError) {
		this.codigoError = codigoError;
	}

	public String getDescripcionError() {
		return this.descripcionError;
	}

	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
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

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public List<Avaluo> getAvaluos() {
		return avaluos;
	}

	public void setAvaluos(List<Avaluo> avaluos) {
		this.avaluos = avaluos;
	}

	public String getNombrePlano() {
		return nombrePlano;
	}

	public void setNombrePlano(String nombrePlano) {
		this.nombrePlano = nombrePlano;
	}

	public String getUsuarioTransaccion() {
		return this.usuarioTransaccion;
	}

	public void setUsuarioTransaccion(String usuarioTransaccion) {
		this.usuarioTransaccion = usuarioTransaccion;
	}

	public Date getFechaTransaccion() {
		return this.fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public void setCantReg(String cantReg) {
		this.cantReg = cantReg;
	}

	public String getCantReg() {
		return cantReg;
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
		for (final String atr : ATRIBUTOS_ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	public static String[] getAtributosEntidadLogsGeneraArchivo() {
		return ATRIBUTOS_ENTIDAD_PGB_LOGS_GENERA_ARCHIVOS;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idLogArchivos);
		hash = 37 * hash + Objects.hashCode(this.nombreArchivo);
		hash = 37 * hash + Objects.hashCode(this.fechaDesde);
		hash = 37 * hash + Objects.hashCode(this.fechaHasta);
		hash = 37 * hash + Objects.hashCode(this.estado);
		hash = 37 * hash + Objects.hashCode(this.enviado);
		hash = 37 * hash + Objects.hashCode(this.linkDescarga);
		hash = 37 * hash + Objects.hashCode(this.codigoError);
		hash = 37 * hash + Objects.hashCode(this.descripcionError);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaInicioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaFinCreacion);
		hash = 37 * hash + Objects.hashCode(this.nombrePlano);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad LogsGeneraArchivo que se
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
		final LogsGeneraArchivo other = (LogsGeneraArchivo) obj;

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

		if (!Objects.equals(this.descripcionError, other.descripcionError)) {
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

	public List<HistoricoLogsGeneraArch> getHistorico() {
		return historico;
	}

	public void setHistorico(List<HistoricoLogsGeneraArch> historico) {
		this.historico = historico;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}