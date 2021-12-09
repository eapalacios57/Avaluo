package com.segurosbolivar.avaluos.modelo.entity;

import static org.eclipse.persistence.annotations.CacheType.NONE;

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
import javax.persistence.Transient;

import uk.co.jemos.podam.annotations.PodamExclude;

/**
 * The persistent class for the PGB_HIST_AVALUOS database table.
 * 
 */
@Entity
@Table(name = "PGB_HIST_AVALUOS")
@NamedQuery(name = "HistoricoAvaluo.findAll", query = "SELECT p FROM HistoricoAvaluo p")
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class HistoricoAvaluo implements Serializable {
	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_HIST_AVALUOS_PK = "secuenciaHistorico";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_ID_AVALUO = "idAvaluo";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_N_CONSECUTIVOBANCO = "nConsecutivobanco";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_C_IDTIPOIDENTIFICACION = "cIdtipoidentificacion";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_N_IDENTIFICACION = "nIdentificacion";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_T_NOMBRESOLICITANTE = "tNombresolicitante";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_A_SOLTEL = "aSoltel";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_A_SOLCEL = "aSolcel";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_A_SOLCORREO = "aSolcorreo";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_F_FECHAAVALUO = "fFechaavaluo";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_T_SECTOR = "tSector";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_C_IDDEPARTAMENTO = "cIddepartamento";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_C_IDCIUDAD = "cIdciudad";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_A_CODDANE_DEPTO = "aCoddaneDepto";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_A_CODDANE_CIUDAD = "aCoddaneCiudad";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_T_DIRINMUEBLE = "tDirinmueble";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_T_NOMBCONJEDIF = "tNombconjedif";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_T_BARRIO = "tBarrio";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_A_NOMBANCO = "aNombanco";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_A_CODBANCO = "aCodbanco";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_C_IDMETODOLOGIA = "cIdmetodologia";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_C_IDOBJETOAVALUO = "cIdobjetoavaluo";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_T_JUSTIFICACION = "tJustificacion";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_A_UBICGPS = "aUbicgps";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_A_TIPO_INFORME = "aTipoInforme";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_A_ESTADOAVALUO = "aEstadoavaluo";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_T_USUARIO = "tUsuario";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_FECHA_TRANSACCION = "fechaTransaccion";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_T_MINMBPPAL1 = "tMinmbppal1";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_COD_TIPO_AVALUO = "codTipoAvaluo";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_DES_DIRECCION_COMPLEMENTARIA = "desDireccionComplementaria";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_COD_PROCEDENCIA = "codProcedencia";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_FECHA_IMPRESION = "fechaImpresion";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_ASEGURABILIDAD = "asegurabilidad";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_COD_MOTIVO_ELIMINACION = "codMotivoEliminacion";
	public static final String ENTIDAD_PGB_HIST_AVALUOS_FECHA_ELIMINACION = "fechaEliminacion";
	private static final String[] ATRIBUTOS_ENTIDAD_PGB_HIST_AVALUOS = { ENTIDAD_PGB_HIST_AVALUOS_COD_PROCEDENCIA,
			ENTIDAD_PGB_HIST_AVALUOS_T_JUSTIFICACION, ENTIDAD_PGB_HIST_AVALUOS_FECHA_ELIMINACION,
			ENTIDAD_PGB_HIST_AVALUOS_A_SOLTEL, ENTIDAD_PGB_HIST_AVALUOS_T_SECTOR,
			ENTIDAD_PGB_HIST_AVALUOS_FECHA_CREACION, ENTIDAD_PGB_HIST_AVALUOS_T_MINMBPPAL1,
			ENTIDAD_PGB_HIST_AVALUOS_N_IDENTIFICACION, ENTIDAD_PGB_HIST_AVALUOS_C_IDCIUDAD,
			ENTIDAD_PGB_HIST_AVALUOS_T_USUARIO, ENTIDAD_PGB_HIST_AVALUOS_C_IDDEPARTAMENTO, ENTIDAD_PGB_HIST_AVALUOS_PK,
			ENTIDAD_PGB_HIST_AVALUOS_T_BARRIO, ENTIDAD_PGB_HIST_AVALUOS_N_CONSECUTIVOBANCO,
			ENTIDAD_PGB_HIST_AVALUOS_FECHA_TRANSACCION, ENTIDAD_PGB_HIST_AVALUOS_F_FECHAAVALUO,
			ENTIDAD_PGB_HIST_AVALUOS_T_DIRINMUEBLE, ENTIDAD_PGB_HIST_AVALUOS_COD_MOTIVO_ELIMINACION,
			ENTIDAD_PGB_HIST_AVALUOS_A_CODDANE_DEPTO, ENTIDAD_PGB_HIST_AVALUOS_A_ESTADOAVALUO,
			ENTIDAD_PGB_HIST_AVALUOS_USUARIO_TRANSACCION, ENTIDAD_PGB_HIST_AVALUOS_DES_DIRECCION_COMPLEMENTARIA,
			ENTIDAD_PGB_HIST_AVALUOS_COD_TIPO_AVALUO, ENTIDAD_PGB_HIST_AVALUOS_A_UBICGPS,
			ENTIDAD_PGB_HIST_AVALUOS_ASEGURABILIDAD, ENTIDAD_PGB_HIST_AVALUOS_FECHA_IMPRESION,
			ENTIDAD_PGB_HIST_AVALUOS_T_NOMBCONJEDIF, ENTIDAD_PGB_HIST_AVALUOS_C_IDMETODOLOGIA,
			ENTIDAD_PGB_HIST_AVALUOS_A_TIPO_INFORME, ENTIDAD_PGB_HIST_AVALUOS_C_IDTIPOIDENTIFICACION,
			ENTIDAD_PGB_HIST_AVALUOS_A_NOMBANCO, ENTIDAD_PGB_HIST_AVALUOS_A_SOLCORREO,
			ENTIDAD_PGB_HIST_AVALUOS_A_SOLCEL, ENTIDAD_PGB_HIST_AVALUOS_C_IDOBJETOAVALUO,
			ENTIDAD_PGB_HIST_AVALUOS_ID_AVALUO, ENTIDAD_PGB_HIST_AVALUOS_T_NOMBRESOLICITANTE,
			ENTIDAD_PGB_HIST_AVALUOS_A_CODBANCO, ENTIDAD_PGB_HIST_AVALUOS_A_CODDANE_CIUDAD};
	@Id
	@SequenceGenerator(name = "PGB_HIST_AVALUOS_SECUENCIAHISTORICO_GENERATOR", sequenceName = "SEQ_PGB_HIST_AVALUOS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGB_HIST_AVALUOS_SECUENCIAHISTORICO_GENERATOR")
	@Column(name = "SECUENCIA_HISTORICO", unique = true, nullable = false, precision = 10)
	private BigDecimal secuenciaHistorico;

	@Column(name = "A_CODBANCO", nullable = false, precision = 6)
	private BigDecimal codigoBanco;

	@Column(name = "A_CODDANE_CIUDAD", precision = 13)
	private BigDecimal codigoDaneCiudad;

	@Column(name = "A_CODDANE_DEPTO", precision = 13)
	private BigDecimal codigoDaneDepto;

	@Column(name = "A_ESTADOAVALUO", nullable = false, precision = 2)
	private BigDecimal estadoAvaluo;

	@Column(name = "A_NOMBANCO", nullable = false, length = 30)
	private String nombreBanco;

	@Column(name = "A_SOLCEL", precision = 15)
	private BigDecimal celularSolicitante;

	@Column(name = "A_SOLCORREO", length = 30)
	private String correoSolicitante;

	@Column(name = "A_SOLTEL", precision = 15)
	private BigDecimal telefonoSolicitante;

	@Column(name = "A_TIPO_INFORME", precision = 22)
	private BigDecimal tipoInforme;

	@Column(name = "A_UBICGPS", precision = 20, scale = 4)
	private String ubicacionGps;

	@Column(name = "C_IDCIUDAD", nullable = false, precision = 13)
	private BigDecimal idCiudad;

	@Column(name = "C_IDDEPARTAMENTO", nullable = false, precision = 13)
	private BigDecimal idDepartamento;

	@Column(name = "C_IDMETODOLOGIA", nullable = false, precision = 22)
	private BigDecimal idMetodologia;

	@Column(name = "C_IDOBJETOAVALUO", nullable = false, precision = 22)
	private BigDecimal idObjetoAvaluo;

	@Column(name = "C_IDTIPOIDENTIFICACION", nullable = false, precision = 22)
	private BigDecimal idTipoIdentificacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "F_FECHAAVALUO", nullable = false)
	private Date fechaAvaluo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CREACION", nullable = false)
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_TRANSACCION", nullable = false)
	private Date fechaTransaccion;

	@Column(name = "N_CONSECUTIVOBANCO", nullable = false, precision = 22)
	private BigDecimal consecutivoBanco;

	@Column(name = "N_IDENTIFICACION")
	private String numeroIdentificacion;

	@Column(name = "T_BARRIO", nullable = false, length = 30)
	private String barrio;

	@Column(name = "T_DIRINMUEBLE", nullable = false, length = 100)
	private String direccionInmueble;

	@Column(name = "T_JUSTIFICACION", nullable = false, length = 500)
	private String justificacion;

	@Column(name = "T_NOMBCONJEDIF", length = 30)
	private String nombreConjuntoEdificio;

	@Column(name = "T_NOMBRESOLICITANTE", nullable = false, length = 30)
	private String nombreSolicitante;

	@Column(name = "T_SECTOR", nullable = false, precision = 3)
	private BigDecimal sector;

	@Column(name = "T_USUARIO", nullable = false, length = 30)
	private String usuario;

	@Column(name = "USUARIO_TRANSACCION", nullable = false, length = 15)
	private String usuarioTransaccion;

	@Column(name = "T_MINMBPPAL1", nullable = false, length = 20)
	private String matriculaInmobiliaria;

	@Transient
	private Ciudad objCiudad;

	@Column(name = "COD_TIPO_AVALUO")
	private BigDecimal codigoTipoAvaluo;

	@Column(name = "DES_DIRECCION_COMPLEMENTARIA")
	private String direccionComplementaria;

	@Column(name = "COD_PROCEDENCIA")
	private BigDecimal codigoProcedencia;

	@Column(name = "FECHA_IMPRESION")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date fechaImpresion;

	@Column(name = "ASEGURABILIDAD")
	private String asegurabilidad;

	@Column(name = "COD_MOTIVO_ELIMINACION")
	private BigDecimal codigoMotivoEliminacion;

	@Column(name = "FECHA_ELIMINACION")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date fechaEliminacion;

	@PodamExclude
	@Column(name = "ID_AVALUO")
	private BigDecimal idAvaluo;

	// bi-directional many-to-one association to Avaluo
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_AVALUO", referencedColumnName = "ID_AVALUO", insertable = false, updatable = false)
	@PodamExclude
	private Avaluo avaluo;

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end

	public HistoricoAvaluo() {
		// protected region procedimientos adicionales de inicialización on
		// begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
	}

	public BigDecimal getSecuenciaHistorico() {
		return this.secuenciaHistorico;
	}

	public void setSecuenciaHistorico(BigDecimal secuenciaHistorico) {
		this.secuenciaHistorico = secuenciaHistorico;
	}

	public BigDecimal getCodigoBanco() {
		return this.codigoBanco;
	}

	public void setCodigoBanco(BigDecimal codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public BigDecimal getCodigoDaneCiudad() {
		return this.codigoDaneCiudad;
	}

	public void setCodigoDaneCiudad(BigDecimal codigoDaneCiudad) {
		this.codigoDaneCiudad = codigoDaneCiudad;
	}

	public BigDecimal getCodigoDaneDepto() {
		return this.codigoDaneDepto;
	}

	public void setCodigoDaneDepto(BigDecimal codigoDaneDepto) {
		this.codigoDaneDepto = codigoDaneDepto;
	}

	public BigDecimal getEstadoAvaluo() {
		return this.estadoAvaluo;
	}

	public void setEstadoAvaluo(BigDecimal estadoAvaluo) {
		this.estadoAvaluo = estadoAvaluo;
	}

	public String getNombreBanco() {
		return this.nombreBanco;
	}

	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	public BigDecimal getCelularSolicitante() {
		return this.celularSolicitante;
	}

	public void setCelularSolicitante(BigDecimal celularSolicitante) {
		this.celularSolicitante = celularSolicitante;
	}

	public String getCorreoSolicitante() {
		return this.correoSolicitante;
	}

	public void setCorreoSolicitante(String correoSolicitante) {
		this.correoSolicitante = correoSolicitante;
	}

	public BigDecimal getTelefonoSolicitante() {
		return this.telefonoSolicitante;
	}

	public void setTelefonoSolicitante(BigDecimal telefonoSolicitante) {
		this.telefonoSolicitante = telefonoSolicitante;
	}

	public BigDecimal getTipoInforme() {
		return this.tipoInforme;
	}

	public void setTipoInforme(BigDecimal tipoInforme) {
		this.tipoInforme = tipoInforme;
	}

	public String getUbicacionGps() {
		return this.ubicacionGps;
	}

	public void setUbicacionGps(String ubicacionGps) {
		this.ubicacionGps = ubicacionGps;
	}

	public BigDecimal getIdCiudad() {
		return this.idCiudad;
	}

	public void setIdCiudad(BigDecimal idCiudad) {
		this.idCiudad = idCiudad;
	}

	public BigDecimal getIdDepartamento() {
		return this.idDepartamento;
	}

	public void setIdDepartamento(BigDecimal idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public BigDecimal getIdMetodologia() {
		return this.idMetodologia;
	}

	public void setIdMetodologia(BigDecimal idMetodologia) {
		this.idMetodologia = idMetodologia;
	}

	public BigDecimal getIdObjetoAvaluo() {
		return this.idObjetoAvaluo;
	}

	public void setIdObjetoAvaluo(BigDecimal idObjetoAvaluo) {
		this.idObjetoAvaluo = idObjetoAvaluo;
	}

	public BigDecimal getIdTipoIdentificacion() {
		return this.idTipoIdentificacion;
	}

	public void setIdTipoIdentificacion(BigDecimal idTipoIdentificacion) {
		this.idTipoIdentificacion = idTipoIdentificacion;
	}

	public Date getFechaAvaluo() {
		return this.fechaAvaluo;
	}

	public void setFechaAvaluo(Date fechaAvaluo) {
		this.fechaAvaluo = fechaAvaluo;
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

	public BigDecimal getConsecutivoBanco() {
		return this.consecutivoBanco;
	}

	public void setConsecutivoBanco(BigDecimal consecutivoBanco) {
		this.consecutivoBanco = consecutivoBanco;
	}

	public String getNumeroIdentificacion() {
		return this.numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getBarrio() {
		return this.barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getDireccionInmueble() {
		return this.direccionInmueble;
	}

	public void setDireccionInmueble(String direccionInmueble) {
		this.direccionInmueble = direccionInmueble;
	}

	public String getJustificacion() {
		return this.justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	public String getNombreConjuntoEdificio() {
		return this.nombreConjuntoEdificio;
	}

	public void setNombreConjuntoEdificio(String nombreConjuntoEdificio) {
		this.nombreConjuntoEdificio = nombreConjuntoEdificio;
	}

	public String getNombreSolicitante() {
		return this.nombreSolicitante;
	}

	public void setNombreSolicitante(String nombreSolicitante) {
		this.nombreSolicitante = nombreSolicitante;
	}

	public BigDecimal getSector() {
		return this.sector;
	}

	public void setSector(BigDecimal sector) {
		this.sector = sector;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getUsuarioTransaccion() {
		return this.usuarioTransaccion;
	}

	public void setUsuarioTransaccion(String usuarioTransaccion) {
		this.usuarioTransaccion = usuarioTransaccion;
	}

	public Avaluo getAvaluo() {
		return this.avaluo;
	}

	public void setAvaluo(Avaluo avaluo) {
		this.avaluo = avaluo;
	}

	public String getMatriculaInmobiliaria() {
		return matriculaInmobiliaria;
	}

	public void setMatriculaInmobiliaria(String matriculaInmobiliaria) {
		this.matriculaInmobiliaria = matriculaInmobiliaria;
	}

	public Ciudad getObjCiudad() {
		return objCiudad;
	}

	public void setObjCiudad(Ciudad objCiudad) {
		this.objCiudad = objCiudad;
	}

	public BigDecimal getIdAvaluo() {
		return idAvaluo;
	}

	public void setIdAvaluo(BigDecimal idAvaluo) {
		this.idAvaluo = idAvaluo;
	}

	public BigDecimal getCodigoTipoAvaluo() {
		return codigoTipoAvaluo;
	}

	public void setCodigoTipoAvaluo(BigDecimal codigoTipoAvaluo) {
		this.codigoTipoAvaluo = codigoTipoAvaluo;
	}

	public String getDireccionComplementaria() {
		return direccionComplementaria;
	}

	public void setDireccionComplementaria(String direccionComplementaria) {
		this.direccionComplementaria = direccionComplementaria;
	}

	public BigDecimal getCodigoProcedencia() {
		return codigoProcedencia;
	}

	public void setCodigoProcedencia(BigDecimal codigoProcedencia) {
		this.codigoProcedencia = codigoProcedencia;
	}

	public Date getFechaImpresion() {
		return fechaImpresion;
	}

	public void setFechaImpresion(Date fechaImpresion) {
		this.fechaImpresion = fechaImpresion;
	}

	public String getAsegurabilidad() {
		return asegurabilidad;
	}

	public void setAsegurabilidad(String asegurabilidad) {
		this.asegurabilidad = asegurabilidad;
	}

	public BigDecimal getCodigoMotivoEliminacion() {
		return codigoMotivoEliminacion;
	}

	public void setCodigoMotivoEliminacion(BigDecimal codigoMotivoEliminacion) {
		this.codigoMotivoEliminacion = codigoMotivoEliminacion;
	}

	public Date getFechaEliminacion() {
		return fechaEliminacion;
	}

	public void setFechaEliminacion(Date fechaEliminacion) {
		this.fechaEliminacion = fechaEliminacion;
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
		for (final String atr : ATRIBUTOS_ENTIDAD_PGB_HIST_AVALUOS) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	public static String[] getAtributosEntidadHistoricoAvaluo() {
		return ATRIBUTOS_ENTIDAD_PGB_HIST_AVALUOS;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.secuenciaHistorico);
		hash = 37 * hash + Objects.hashCode(this.idAvaluo);
		hash = 37 * hash + Objects.hashCode(this.consecutivoBanco);
		hash = 37 * hash + Objects.hashCode(this.idTipoIdentificacion);
		hash = 37 * hash + Objects.hashCode(this.numeroIdentificacion);
		hash = 37 * hash + Objects.hashCode(this.nombreSolicitante);
		hash = 37 * hash + Objects.hashCode(this.telefonoSolicitante);
		hash = 37 * hash + Objects.hashCode(this.celularSolicitante);
		hash = 37 * hash + Objects.hashCode(this.correoSolicitante);
		hash = 37 * hash + Objects.hashCode(this.fechaAvaluo);
		hash = 37 * hash + Objects.hashCode(this.sector);
		hash = 37 * hash + Objects.hashCode(this.idDepartamento);
		hash = 37 * hash + Objects.hashCode(this.idCiudad);
		hash = 37 * hash + Objects.hashCode(this.codigoDaneDepto);
		hash = 37 * hash + Objects.hashCode(this.codigoDaneCiudad);
		hash = 37 * hash + Objects.hashCode(this.direccionInmueble);
		hash = 37 * hash + Objects.hashCode(this.nombreConjuntoEdificio);
		hash = 37 * hash + Objects.hashCode(this.barrio);
		hash = 37 * hash + Objects.hashCode(this.nombreBanco);
		hash = 37 * hash + Objects.hashCode(this.codigoBanco);
		hash = 37 * hash + Objects.hashCode(this.idMetodologia);
		hash = 37 * hash + Objects.hashCode(this.idObjetoAvaluo);
		hash = 37 * hash + Objects.hashCode(this.justificacion);
		hash = 37 * hash + Objects.hashCode(this.ubicacionGps);
		hash = 37 * hash + Objects.hashCode(this.tipoInforme);
		hash = 37 * hash + Objects.hashCode(this.estadoAvaluo);
		hash = 37 * hash + Objects.hashCode(this.usuario);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
		hash = 37 * hash + Objects.hashCode(this.matriculaInmobiliaria);
		hash = 37 * hash + Objects.hashCode(this.codigoTipoAvaluo);
		hash = 37 * hash + Objects.hashCode(this.direccionComplementaria);
		hash = 37 * hash + Objects.hashCode(this.codigoProcedencia);
		hash = 37 * hash + Objects.hashCode(this.fechaImpresion);
		hash = 37 * hash + Objects.hashCode(this.asegurabilidad);
		hash = 37 * hash + Objects.hashCode(this.codigoMotivoEliminacion);
		hash = 37 * hash + Objects.hashCode(this.fechaEliminacion);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad HistoricoAvaluo que se
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
		final HistoricoAvaluo other = (HistoricoAvaluo) obj;

		if (!Objects.equals(this.secuenciaHistorico, other.secuenciaHistorico)) {
			return false;
		}

		if (!Objects.equals(this.idAvaluo, other.idAvaluo)) {
			return false;
		}

		if (!Objects.equals(this.consecutivoBanco, other.consecutivoBanco)) {
			return false;
		}

		if (!Objects.equals(this.idTipoIdentificacion, other.idTipoIdentificacion)) {
			return false;
		}

		if (!Objects.equals(this.idTipoIdentificacion, other.idTipoIdentificacion)) {
			return false;
		}

		if (!Objects.equals(this.nombreSolicitante, other.nombreSolicitante)) {
			return false;
		}

		if (!Objects.equals(this.telefonoSolicitante, other.telefonoSolicitante)) {
			return false;
		}

		if (!Objects.equals(this.celularSolicitante, other.celularSolicitante)) {
			return false;
		}

		if (!Objects.equals(this.correoSolicitante, other.correoSolicitante)) {
			return false;
		}

		if (!Objects.equals(this.fechaAvaluo, other.fechaAvaluo)) {
			return false;
		}

		if (!Objects.equals(this.sector, other.sector)) {
			return false;
		}

		if (!Objects.equals(this.idDepartamento, other.idDepartamento)) {
			return false;
		}

		if (!Objects.equals(this.idCiudad, other.idCiudad)) {
			return false;
		}

		if (!Objects.equals(this.codigoDaneDepto, other.codigoDaneDepto)) {
			return false;
		}

		if (!Objects.equals(this.codigoDaneCiudad, other.codigoDaneCiudad)) {
			return false;
		}

		if (!Objects.equals(this.direccionInmueble, other.direccionInmueble)) {
			return false;
		}

		if (!Objects.equals(this.nombreConjuntoEdificio, other.nombreConjuntoEdificio)) {
			return false;
		}

		if (!Objects.equals(this.barrio, other.barrio)) {
			return false;
		}

		if (!Objects.equals(this.nombreBanco, other.nombreBanco)) {
			return false;
		}

		if (!Objects.equals(this.codigoBanco, other.codigoBanco)) {
			return false;
		}

		if (!Objects.equals(this.idMetodologia, other.idMetodologia)) {
			return false;
		}

		if (!Objects.equals(this.idObjetoAvaluo, other.idObjetoAvaluo)) {
			return false;
		}

		if (!Objects.equals(this.justificacion, other.justificacion)) {
			return false;
		}

		if (!Objects.equals(this.ubicacionGps, other.ubicacionGps)) {
			return false;
		}

		if (!Objects.equals(this.tipoInforme, other.tipoInforme)) {
			return false;
		}

		if (!Objects.equals(this.estadoAvaluo, other.estadoAvaluo)) {
			return false;
		}

		if (!Objects.equals(this.usuario, other.usuario)) {
			return false;
		}

		if (!Objects.equals(this.fechaCreacion, other.fechaCreacion)) {
			return false;
		}

		if (!Objects.equals(this.usuarioTransaccion, other.usuarioTransaccion)) {
			return false;
		}

		if (!Objects.equals(this.fechaTransaccion, other.fechaTransaccion)) {
			return false;
		}

		if (!Objects.equals(this.matriculaInmobiliaria, other.matriculaInmobiliaria)) {
			return false;
		}

		if (!Objects.equals(this.codigoTipoAvaluo, other.codigoTipoAvaluo)) {
			return false;
		}

		if (!Objects.equals(this.direccionComplementaria, other.direccionComplementaria)) {
			return false;
		}

		if (!Objects.equals(this.codigoProcedencia, other.codigoProcedencia)) {
			return false;
		}

		if (!Objects.equals(this.fechaImpresion, other.fechaImpresion)) {
			return false;
		}

		if (!Objects.equals(this.asegurabilidad, other.asegurabilidad)) {
			return false;
		}

		if (!Objects.equals(this.codigoMotivoEliminacion, other.codigoMotivoEliminacion)) {
			return false;
		}

		return Objects.equals(this.fechaEliminacion, other.fechaEliminacion);

	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}