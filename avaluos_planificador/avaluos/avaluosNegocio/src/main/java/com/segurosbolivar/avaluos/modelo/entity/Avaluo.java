package com.segurosbolivar.avaluos.modelo.entity;

import static org.eclipse.persistence.annotations.CacheType.NONE;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.asesoftware.util.lang.UtilFecha;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.cons.EstadoAvaluo;
import com.segurosbolivar.avaluos.modelo.cons.EstadoBoleano;
import com.segurosbolivar.avaluos.modelo.cons.TipoAvaluo;
import com.segurosbolivar.avaluos.modelo.cons.TipoInforme;
import com.segurosbolivar.avaluos.modelo.service.util.ClonUtil;

/**
 * The persistent class for the PGB_AVALUOS database table.
 *
 */
@Entity
@Table(name = "PGB_AVALUOS")
@NamedQueries({ @NamedQuery(name = "buscaId", query = "Select a from Avaluo a where a.idAvaluo = :id"),
	@NamedQuery(name = "Avaluo.consecutivoBanco", query = "Select a from Avaluo a where a.consecutivoBanco = :consecutivo"),
	@NamedQuery(name = "buscaPorEstado", query = "select a from Avaluo a where a.estadoAvaluo = :estado"),
	@NamedQuery(name = "Avaluo.findAll", query = "SELECT p FROM Avaluo p ") })
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class Avaluo implements Serializable, Cloneable, IPersona {
    private static final long serialVersionUID = 1L;
    public static final String ENTIDAD_PGB_AVALUOS_FK_ID_PERITO_EMPRESA = "peritoEmpresa";
    // Definicion de atributos de la entidad (Exceptuando relaciones)
    public static final String ENTIDAD_PGB_AVALUOS_PK = "idAvaluo";
    public static final String ENTIDAD_PGB_AVALUOS_N_CONSECUTIVOBANCO = "nConsecutivobanco";
    public static final String ENTIDAD_PGB_AVALUOS_C_IDTIPOIDENTIFICACION = "cIdtipoidentificacion";
    public static final String ENTIDAD_PGB_AVALUOS_N_IDENTIFICACION = "nIdentificacion";
    public static final String ENTIDAD_PGB_AVALUOS_A_TIPODOCUMENTO = "aTipodocumento";
    public static final String ENTIDAD_PGB_AVALUOS_T_NOMBRESOLICITANTE = "tNombresolicitante";
    public static final String ENTIDAD_PGB_AVALUOS_A_SOLTEL = "aSoltel";
    public static final String ENTIDAD_PGB_AVALUOS_A_SOLCEL = "aSolcel";
    public static final String ENTIDAD_PGB_AVALUOS_A_SOLCORREO = "aSolcorreo";
    public static final String ENTIDAD_PGB_AVALUOS_F_FECHAAVALUO = "fechaAvaluo";
    public static final String ENTIDAD_PGB_AVALUOS_T_SECTOR = "tSector";
    public static final String ENTIDAD_PGB_AVALUOS_C_IDDEPARTAMENTO = "cIddepartamento";
    public static final String ENTIDAD_PGB_AVALUOS_C_IDCIUDAD = "cIdciudad";
    public static final String ENTIDAD_PGB_AVALUOS_A_CODDANE_DEPTO = "aCoddaneDepto";
    public static final String ENTIDAD_PGB_AVALUOS_A_CODDANE_CIUDAD = "aCoddaneCiudad";
    public static final String ENTIDAD_PGB_AVALUOS_T_DIRINMUEBLE = "tDirinmueble";
    public static final String ENTIDAD_PGB_AVALUOS_T_NOMBCONJEDIF = "tNombconjedif";
    public static final String ENTIDAD_PGB_AVALUOS_T_BARRIO = "tBarrio";
    public static final String ENTIDAD_PGB_AVALUOS_A_NOMBANCO = "aNombanco";
    public static final String ENTIDAD_PGB_AVALUOS_A_CODBANCO = "aCodbanco";
    public static final String ENTIDAD_PGB_AVALUOS_C_IDMETODOLOGIA = "cIdmetodologia";
    public static final String ENTIDAD_PGB_AVALUOS_C_IDOBJETOAVALUO = "cIdobjetoavaluo";
    public static final String ENTIDAD_PGB_AVALUOS_T_JUSTIFICACION = "tJustificacion";
    public static final String ENTIDAD_PGB_AVALUOS_A_UBICGPS = "aUbicgps";
    public static final String ENTIDAD_PGB_AVALUOS_A_TIPO_INFORME = "aTipoInforme";
    public static final String ENTIDAD_PGB_AVALUOS_A_ESTADOAVALUO = "estadoAvaluo";
    public static final String ENTIDAD_PGB_AVALUOS_T_USUARIO = "tUsuario";
    public static final String ENTIDAD_PGB_AVALUOS_USUARIO_CREACION = "usuarioCreacion";
    public static final String ENTIDAD_PGB_AVALUOS_FECHA_CREACION = "fechaCreacion";
    public static final String ENTIDAD_PGB_AVALUOS_USUARIO_TRANSACCION = "usuarioTransaccion";
    public static final String ENTIDAD_PGB_AVALUOS_FECHA_TRANSACCION = "fechaTransaccion";
    public static final String ENTIDAD_PGB_AVALUOS_A_IDARCHIVO = "aIdarchivo";
    public static final String ENTIDAD_PGB_AVALUOS_A_NUMLINEA = "aNumlinea";
    public static final String ENTIDAD_PGB_AVALUOS_T_SISTEMA = "tSistema";
    public static final String ENTIDAD_PGB_AVALUOS_C_SISTEMA = "cSistema";
    public static final String ENTIDAD_PGB_AVALUOS_T_LATITUD = "tLatitud";
    public static final String ENTIDAD_PGB_AVALUOS_T_LONGITUD = "tLongitud";
    public static final String ENTIDAD_PGB_AVALUOS_COD_TIPO_AVALUO = "codTipoAvaluo";
    public static final String ENTIDAD_PGB_AVALUOS_DES_DIRECCION_COMPLEMENTARIA = "desDireccionComplementaria";
    public static final String ENTIDAD_PGB_AVALUOS_COD_PROCEDENCIA = "codigoProcedencia";
    public static final String ENTIDAD_PGB_AVALUOS_FECHA_IMPRESION = "fechaImpresion";
    public static final String ENTIDAD_PGB_AVALUOS_ASEGURABILIDAD = "asegurabilidad";
    public static final String ENTIDAD_PGB_AVALUOS_COD_MOTIVO_ELIMINACION = "codMotivoEliminacion";
    public static final String ENTIDAD_PGB_AVALUOS_FECHA_ELIMINACION = "fechaEliminacion";
    public static final String ENTIDAD_PGB_AVALUOS_TIPOAVALUO = "tipoAvaluo";
    public static final String ENTIDAD_PGB_AVALUOS_NUMERO_SOLICITUD_MOVIL = "numeroSolicitudMovil";
    public static final String ENTIDAD_PGB_AVALUOS_IND_MOTOR = "indMotor";
    
    private static final String[] ATRIBUTOS_ENTIDAD_PGB_AVALUOS = {ENTIDAD_PGB_AVALUOS_NUMERO_SOLICITUD_MOVIL,ENTIDAD_PGB_AVALUOS_TIPOAVALUO, ENTIDAD_PGB_AVALUOS_A_CODBANCO, ENTIDAD_PGB_AVALUOS_N_IDENTIFICACION, ENTIDAD_PGB_AVALUOS_C_IDDEPARTAMENTO,
	    ENTIDAD_PGB_AVALUOS_USUARIO_CREACION, ENTIDAD_PGB_AVALUOS_PK, ENTIDAD_PGB_AVALUOS_C_IDCIUDAD, ENTIDAD_PGB_AVALUOS_A_ESTADOAVALUO, ENTIDAD_PGB_AVALUOS_T_SISTEMA,
	    ENTIDAD_PGB_AVALUOS_FECHA_CREACION, ENTIDAD_PGB_AVALUOS_C_IDTIPOIDENTIFICACION, ENTIDAD_PGB_AVALUOS_T_NOMBRESOLICITANTE, ENTIDAD_PGB_AVALUOS_A_CODDANE_DEPTO,
	    ENTIDAD_PGB_AVALUOS_A_CODDANE_CIUDAD, ENTIDAD_PGB_AVALUOS_COD_TIPO_AVALUO, ENTIDAD_PGB_AVALUOS_A_TIPO_INFORME, ENTIDAD_PGB_AVALUOS_T_LATITUD,
	    ENTIDAD_PGB_AVALUOS_T_LONGITUD, ENTIDAD_PGB_AVALUOS_T_SECTOR, ENTIDAD_PGB_AVALUOS_C_IDOBJETOAVALUO, ENTIDAD_PGB_AVALUOS_DES_DIRECCION_COMPLEMENTARIA,
	    ENTIDAD_PGB_AVALUOS_FECHA_IMPRESION, ENTIDAD_PGB_AVALUOS_COD_MOTIVO_ELIMINACION, ENTIDAD_PGB_AVALUOS_A_TIPODOCUMENTO, ENTIDAD_PGB_AVALUOS_A_SOLTEL,
	    ENTIDAD_PGB_AVALUOS_T_NOMBCONJEDIF, ENTIDAD_PGB_AVALUOS_FECHA_TRANSACCION, ENTIDAD_PGB_AVALUOS_A_IDARCHIVO, ENTIDAD_PGB_AVALUOS_A_NUMLINEA,
	    ENTIDAD_PGB_AVALUOS_A_NOMBANCO, ENTIDAD_PGB_AVALUOS_T_BARRIO, ENTIDAD_PGB_AVALUOS_ASEGURABILIDAD, ENTIDAD_PGB_AVALUOS_A_SOLCORREO, ENTIDAD_PGB_AVALUOS_T_DIRINMUEBLE,
	    ENTIDAD_PGB_AVALUOS_T_USUARIO, ENTIDAD_PGB_AVALUOS_N_CONSECUTIVOBANCO, ENTIDAD_PGB_AVALUOS_A_SOLCEL, ENTIDAD_PGB_AVALUOS_USUARIO_TRANSACCION,
	    ENTIDAD_PGB_AVALUOS_FECHA_ELIMINACION, ENTIDAD_PGB_AVALUOS_A_UBICGPS, ENTIDAD_PGB_AVALUOS_C_SISTEMA, ENTIDAD_PGB_AVALUOS_T_JUSTIFICACION,
	    ENTIDAD_PGB_AVALUOS_COD_PROCEDENCIA, ENTIDAD_PGB_AVALUOS_C_IDMETODOLOGIA, ENTIDAD_PGB_AVALUOS_F_FECHAAVALUO,
	    ENTIDAD_PGB_AVALUOS_IND_MOTOR
	    };

    @Id
    @SequenceGenerator(name = "PGB_AVALUOS_IDAVALUO_GENERATOR", sequenceName = "SEQ_PGB_AVALUOS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGB_AVALUOS_IDAVALUO_GENERATOR")
    @Column(name = "ID_AVALUO", unique = true, nullable = false, precision = 22)
    private Long idAvaluo;

    @Column(name = "NUMERO_SOLICITUD_MOVIL")
    private Long numeroSolicitudMovil;
    
    @Column(name = "TIPOAVALUO")
    private Long tipoAvaluo;
    
    @Column(name = "ID_AVALUO_PADRE", nullable = true, precision = 22)
    private Long idAvaluoPadre;

    @Column(name = "A_CODBANCO", nullable = false, precision = 6)
    private Long codigoBanco;

    @Column(name = "A_ESTADOAVALUO", nullable = false, precision = 2)
    private Long estadoAvaluo;

    @Column(name = "A_NOMBANCO", nullable = false, length = 30)
    private String nombreBanco;

    @Column(name = "A_SOLCEL", precision = 15)
    private Long celularSolicitante;

    @Column(name = "A_SOLCORREO", length = 60)
    private String correoSolicitante;

    @Column(name = "A_SOLTEL", precision = 15)
    private Long telefonoSolicitante;

    @Column(name = "A_TIPO_INFORME", nullable = false, precision = 2)
    private Long tipoInforme;

    @Column(name = "A_UBICGPS", length = 30)
    private String ubicacionGps;

    @Column(name = "C_IDMETODOLOGIA", nullable = true, precision = 22)
    private Long idMetodologia;

    @Column(name = "C_IDOBJETOAVALUO", nullable = false, precision = 22)
    private Long idObjetoAvaluo;

    @Column(name = "C_IDTIPOIDENTIFICACION", nullable = false, precision = 22)
    private Long idTipoIdentificacion;

    //
    @Column(name = "C_SISTEMA")
    private Long sistemaCoordenadasBUA;

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
    private BigInteger consecutivoBanco;

    @Column(name = "N_IDENTIFICACION", nullable = true, length = 10)
    private Long numeroIdentificacion;

    @Column(name = "T_BARRIO", nullable = false, length = 30)
    private String barrio;

    @Column(name = "T_DIRINMUEBLE", nullable = true, length = 100)
    private String direccionInmueble;

    @Column(name = "T_JUSTIFICACION", nullable = true, length = 500)
    private String justificacion;

    @Column(name = "T_LATITUD", nullable = false, length = 13)
    private String latitudSFBUA;

    @Column(name = "T_LONGITUD", nullable = false, length = 13)
    private String longitudSFBUA;

    @Column(name = "T_NOMBCONJEDIF", length = 30)
    private String nombreConjuntoEdificio;

    @Column(name = "T_NOMBRESOLICITANTE", nullable = false, length = 60)
    private String nombreSolicitante;

    @Column(name = "T_SECTOR", nullable = false, precision = 3)
    private Long sector;

    @Column(name = "T_SISTEMA")
    private String sistemaCoordenadasTxtBUA;

    @Column(name = "T_USUARIO", nullable = false, length = 30)
    private String usuario;

    @Column(name = "USUARIO_CREACION", nullable = false, length = 5)
    private String usuarioCreacion;

    @Column(name = "USUARIO_TRANSACCION", nullable = false, length = 15)
    private String usuarioTransaccion;

    @Column(name = "C_IDCIUDAD", nullable = false, precision = 13)
    private Long idCiudad;

    @Column(name = "C_IDDEPARTAMENTO", nullable = false, precision = 13)
    private Long idDepartamento;

    @Column(name = "A_NUMLINEA", precision = 22)
    private Long numLinea;

    @Column(name = "A_IDARCHIVO")
    private Long idArchivo;

    @Column(name = "COD_TIPO_AVALUO", nullable = false)
    private Long codTipoAvaluo;

    @Column(name = "K_TRANSMITIDO")
    private Long transmitido;

    @Column(name = "DES_DIRECCION_COMPLEMENTARIA")
    private String direccionComplementaria;

    @Column(name = "COD_PROCEDENCIA")
    private Long codigoProcedencia;

    @Column(name = "FECHA_IMPRESION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaImpresion;

    @Column(name = "ASEGURABILIDAD")
    private String asegurabilidad;

    @Column(name = "COD_MOTIVO_ELIMINACION")
    private Long codigoMotivoEliminacion;

    @Column(name = "FECHA_ELIMINACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEliminacion;

    @Column(name = "ID_PERITO_EMPRESA")
    private Long idPeritoEmpresa;
    
    @Column(name = "I_INDMOTOR")
    private String indMotor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERITO_EMPRESA", referencedColumnName = "ID_PERITO_EMPRESA", insertable = false, updatable = false)
    private PeritosEmpresa peritoEmpresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AVALUO_PADRE", referencedColumnName = "ID_AVALUO", insertable = false, updatable = false)
    private Avaluo padre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "C_IDDEPARTAMENTO", referencedColumnName = "ID_DEPARTAMENTO", insertable = false, updatable = false)
    private Departamento departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "C_IDCIUDAD", referencedColumnName = "ID_CIUDAD", insertable = false, updatable = false)
    private Ciudad ciudad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "A_IDARCHIVO", referencedColumnName = "ID_LOG_ARCHIVOS", insertable = false, updatable = false)
    private LogsGeneraArchivo logsGeneraArchivo;

    @OneToOne(mappedBy = "avaluo", fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AVALUO", referencedColumnName = "ID_AVALUO", insertable = false, updatable = false)
    private AnexoFotografico anexosFotograficos;

    @OneToOne(mappedBy = "avaluo", fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AVALUO", referencedColumnName = "ID_AVALUO", insertable = false, updatable = false)
    private ComportamientoOfertaDemanda compOfertaDemanda;

    @OneToOne(mappedBy = "avaluo", fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AVALUO", referencedColumnName = "ID_AVALUO", insertable = false, updatable = false)
    private CondicionesSalubridad condicionSalubridad;

    @OneToMany(mappedBy = "avaluo", fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AVALUO", referencedColumnName = "ID_AVALUO", insertable = false, updatable = false)
    private List<HistoricoAvaluo> historicosAvaluo;

    @OneToOne(mappedBy = "avaluo", fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AVALUO", referencedColumnName = "ID_AVALUO", insertable = false, updatable = false)
    private InformacionBarrio informacionBarrio;

    @OneToOne(mappedBy = "avaluo", fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AVALUO", referencedColumnName = "ID_AVALUO", insertable = false, updatable = false)
    private InformacionConstruccion informacionConstruccion;

    @OneToOne(mappedBy = "avaluo", fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AVALUO", referencedColumnName = "ID_AVALUO", insertable = false, updatable = false)
    private InformacionInmueble informacionInmueble;

    @OneToMany(mappedBy = "avaluo", fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AVALUO", referencedColumnName = "ID_AVALUO", insertable = false, updatable = false)
    private List<LiquidacionAvaluo> liquidacionAvaluos;

    @OneToOne(mappedBy = "avaluo", fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AVALUO", referencedColumnName = "ID_AVALUO", insertable = false, updatable = false)
    private LiquidacionAvaluoTotal liquidacionTotal;

    @OneToOne(mappedBy = "avaluo", fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AVALUO", referencedColumnName = "ID_AVALUO", insertable = false, updatable = false)
    private Observaciones observacion;

    @OneToMany(mappedBy = "avaluo", fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AVALUO", referencedColumnName = "ID_AVALUO", insertable = false, updatable = false)
    private List<ListaAnexosPdf> listaAnexosPdf;

    @OneToMany(mappedBy = "avaluo", fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AVALUO", referencedColumnName = "ID_AVALUO", insertable = false, updatable = false)
    private List<AlertaAvaluos> alertas;

    @Transient
    private boolean codTipoAvaluobool1;
    @Transient
    private boolean codTipoAvaluobool2;
    @Transient
    private boolean impreso;
    @Transient
    private Date fechaImpresionAnterior;
    @Transient
    private String filtrocodTipoAvaluo;
    // creado por Carlos Andres Rivera, variable filtros para consulta sobre
    // informacionInmueble
    @Transient
    private String matriculaInmobiliariaPpal1;
    @Transient
    private String matriculaInmobiliariaPpal2;
    @Transient
    private String matriculaInmobiliariaDeposito1;
    @Transient
    private String matriculaInmobiliariaGaraje1;
    @Transient
    private String matriculaInmobiliariaGaraje2;
    @Transient
    private Long codigoNombreConstructora;
    @Transient
    private String nombreConstructora;
    @Transient
    private Long codigoDaneCiudad;
    @Transient
    private Long codigoDaneDepartamento;
    @Transient
    private String registroPrivado;
    @Transient
    private String registroSic;
    @Transient
    private String sectorDescripcion;
    @Transient
    private String objetoDescripcion;
    @Transient
    private String tipoDocumento;
    @Transient
    private String tipoAvaluoNombre;
    @Transient
    private String tipoViviendaNombre;
    @Transient
    private String estadoConstruccionDescripcion;
    @Transient
    private String estadoAvaluoDescripcion;
    @Transient
    private String estrato;
    @Transient
    private String asegurabilidadDescripcion;
    @Transient
    private String archivoZip;
    @Transient
    private String calificacion;
    @Transient
    private Long avance;
    @Transient
    private String metodologiaDescripcion;
    @Transient
    private BigDecimal valorTotal;
    @Transient
    private BigDecimal vetustez;
    @Transient
    private String descEmpresa; // es el nombre de la empresa de usuario.

    @Transient
    private ByteArrayInputStream imagenLogoEmpresa;
    @Transient
    private ByteArrayInputStream imagenFirmaEmpresa;

    // protected region atributos adicionales on begin
    // Escriba en esta sección sus modificaciones

    // protected region atributos adicionales end

    public Avaluo() {
	// protected region procedimientos adicionales de inicialización on
	// begin
	// Escriba en esta sección sus modificaciones
	alertas = new ArrayList<>();
	listaAnexosPdf = new ArrayList<>();
	historicosAvaluo = new ArrayList<>();
	// protected region procedimientos adicionales de inicialización end
    }

    @Override
    public String getNombre() {
	return getNombreSolicitante();
    }

    /***
     * Clonador para aprobacion debido a inconveniente presentado con la interfaz
     * Cloneable.
     */
    public Avaluo clone() {
	Avaluo clon = null;
	try {
	    clon = (Avaluo) ClonUtil.getClon(this);
	    List<LiquidacionAvaluo> liquidacionClon = new ArrayList<LiquidacionAvaluo>(this.getLiquidacionAvaluos());
	    List<HistoricoAvaluo> historicosAvaluoClon = new ArrayList<HistoricoAvaluo>(this.getHistoricosAvaluo());
	    List<ListaAnexosPdf> listaAnexosPdfClon = new ArrayList<ListaAnexosPdf>(this.getListaAnexosPdf());
	    List<AlertaAvaluos> alertasClon = new ArrayList<AlertaAvaluos>(this.getAlertas());

	    clon.setHistoricosAvaluo(historicosAvaluoClon);
	    clon.setListaAnexosPdf(listaAnexosPdfClon);
	    clon.setAlertas(alertasClon);
	    clon.setLiquidacionAvaluos(liquidacionClon);
	} catch (Exception e) {

	}
	return clon;
    }

    /**
     * se crea seegundo metodo de clonacion por inconveniente presentado con JPA al
     * realizar la transcripcion, replica y multiple constructor.
     * 
     * @return
     */
    public Avaluo clonar() {
	Avaluo clon = null;
	try {
	    clon = (Avaluo) super.clone();
	} catch (Exception e) {

	}
	return clon;
    }

    /**
     * Permite determinar si un avaluo se puede eliminar en caso de cumplir con las
     * siguientes condiciones:<br>
     * - si el avaluo se encuentra en estado APROBADO.<br>
     * -si el avaluo se encuentra en estado NUEVO.
     * 
     * @return verdadero en caso de cumplir con las condiciones para la eliminacion
     */
    public boolean isPuedeEliminar() {
	return estadoAvaluo != null && (EstadoAvaluo.APROBADO.getValor().compareTo(estadoAvaluo) == 0 || EstadoAvaluo.NUEVO.getValor().compareTo(estadoAvaluo) == 0
		|| EstadoAvaluo.EN_APROBACION.getValor().compareTo(estadoAvaluo) == 0);
    }

    public boolean isPuedeAprobar() {
	return estadoAvaluo != null && (EstadoAvaluo.NUEVO.getValor().compareTo(estadoAvaluo) == 0 || EstadoAvaluo.EN_APROBACION.getValor().compareTo(estadoAvaluo) == 0);
    }

    /**
     * Permite determinar si un avaluo se puede reversar en caso de cumplir con las
     * siguientes condiciones: <br>
     * - Si el avaluo esta APROBADO y no sea tranmitido a asobancaria ni es de tipo
     * provisional. <br>
     * - si esta en estado ELIMINADO y la fecha de eliminacion no es mayor a 2 meses
     * de la fecha actual. <br>
     * - si se encuentra en estado EN APROBACION.
     * 
     * @return verdadero en caso de cumplir con las condiciones para la reversion.
     */
    public boolean isPuedeReversar() {
	return estadoAvaluo != null && ((EstadoAvaluo.APROBADO.getValor().compareTo(estadoAvaluo) == 0 && !isTransmitido() && !isProvisional())
		|| (EstadoAvaluo.ELIMINADO.getValor().compareTo(estadoAvaluo) == 0 && fechaEliminacion != null && UtilFecha.diferenciaEnMeses(fechaEliminacion, new Date()) > -2)
		|| EstadoAvaluo.EN_APROBACION.getValor().compareTo(estadoAvaluo) == 0);
    }

    /**
     * ¨permite determinar si el avaluo se encuentrada en estado APROBADO
     * 
     * @return verdadero si el estado del avaluo es APROBADO
     */
    public boolean isAprobado() {
	return estadoAvaluo != null && EstadoAvaluo.APROBADO.getValor().compareTo(estadoAvaluo) == 0;
    }

    public boolean isEliminado() {
	return estadoAvaluo != null && EstadoAvaluo.ELIMINADO.getValor().compareTo(estadoAvaluo) == 0;
    }

    /**
     * determinar si el avaluo es un proyecto constructor.
     * 
     * @return en caso de que el tipo de informe sea proyecto, devuelve verdadero.
     */
    public boolean isConstructor() {
	return tipoInforme != null && TipoInforme.PROYECTO.getValor().compareTo(tipoInforme) == 0;
    }

    /**
     * determina si el avaluo sea transmitido a la asobancaria.
     * 
     * @return verdardero si el avaluo ya fuen transmitido.
     */
    public boolean isTransmitido() {
	return transmitido != null && EstadoBoleano.TRUE.getValor().compareTo(transmitido) == 0;
    }

    /**
     * Permite que definamos el tipo de informe del proyecto indicando si es un
     * proyecto constructor o no.
     * 
     * @param constructor
     *            en caso de ser verdadero el proyecto se parametriza como un
     *            proyecto constructor si no como uno de credito.
     */
    public void setConstructor(boolean constructor) {
	this.tipoInforme = constructor ? TipoInforme.PROYECTO.getValor() : TipoInforme.CREDITO.getValor();
    }

    /**
     * Permite identificar si el avaluo es provisional.
     * 
     * @return verdadero en caso de que el tipo de avaluo sea provisional.
     */
    public boolean isProvisional() {
	return codTipoAvaluo != null && codTipoAvaluo == TipoAvaluo.PROVISIONAL.getValor();
    }

    /**
     * // * permite definir el tipo de avaluo, para esto indicamos si el avaluo es
     * PROVISIONAL o no.
     * 
     * @param provisional
     *            en caso de ser verdadero el avaluo se registra como PROVISIONAL de
     *            lo contrario sera considerado como DEFINITIVO.
     */
    public void setProvisional(boolean provisional) {
	this.codTipoAvaluo = provisional ? TipoAvaluo.PROVISIONAL.getValor() : TipoAvaluo.DEFINITIVO.getValor();
    }
    public boolean isTradicional() {
    	return tipoAvaluo != null && tipoAvaluo == TipoAvaluo.TRADICIONAL.getValor(); 
    }
    
    public void setTradicional(boolean tradicional) {
    	this.tipoAvaluo = tradicional ? TipoAvaluo.TRADICIONAL.getValor() : TipoAvaluo.MOVIL.getValor();
    }
    
    
    public boolean isMovil() {
    	return tipoAvaluo != null && tipoAvaluo == TipoAvaluo.MOVIL.getValor(); 
    }
    
    public void setMovil(boolean movil) {
    	this.tipoAvaluo = movil ? TipoAvaluo.MOVIL.getValor() : TipoAvaluo.TRADICIONAL.getValor();
    }
    

    public String obtenerDireccionCompleta() {
	return (!UtilTexto.estaVacio(this.direccionInmueble) ? this.direccionInmueble : "")
		+ (!UtilTexto.estaVacio(this.direccionComplementaria) ? " " + this.direccionComplementaria : "");
    }

    public void generarConsecutivoBanco() {
	if (fechaAvaluo != null && numeroIdentificacion != null)
	    this.setConsecutivoBanco(new BigInteger(UtilFecha.fechaAFormatoddMMyyyySinSeparadores(fechaAvaluo) + numeroIdentificacion));
    }

    public InformacionInmueble obtenerInmuebleMatriculas() {
	InformacionInmueble retorno = new InformacionInmueble();
	retorno.setMatriculaInmobiliariaDeposito1(matriculaInmobiliariaDeposito1);
	retorno.setMatriculaInmobiliariaGaraje1(matriculaInmobiliariaDeposito1);
	retorno.setMatriculaInmobiliariaGaraje2(matriculaInmobiliariaGaraje2);
	retorno.setMatriculaInmobiliariaPpal1(matriculaInmobiliariaPpal1);
	retorno.setMatriculaInmobiliariaPpal2(matriculaInmobiliariaPpal2);
	retorno.setNumeroIdentificacion(numeroIdentificacion);
	return retorno;
    }

    public List<InformacionBarrio> getObtenerBarrio() {
	return Collections.singletonList(informacionBarrio);
    }

    public List<InformacionInmueble> getObtenerInmueble() {
	return Collections.singletonList(informacionInmueble);
    }

    public List<InformacionConstruccion> getObtenerConstruccion() {
	return Collections.singletonList(informacionConstruccion);
    }

    public List<CondicionesSalubridad> getObtenerSalubridad() {
	return Collections.singletonList(condicionSalubridad);
    }

    public List<LiquidacionAvaluoTotal> getObtenerTotal() {
	return Collections.singletonList(liquidacionTotal);
    }

    public List<Observaciones> getObtenerObservacion() {
	return Collections.singletonList(observacion);
    }

    public List<ComportamientoOfertaDemanda> getObtenerOferta() {
	return Collections.singletonList(compOfertaDemanda);
    }

    /*
     * getters y setters
     * 
    */
    
    public Long getNumeroSolicitudMovil() {
		return numeroSolicitudMovil;
	}

	public void setNumeroSolicitudMovil(Long numeroSolicitudMovil) {
		this.numeroSolicitudMovil = numeroSolicitudMovil;
	}

	public Long getTipoAvaluo() {
		return tipoAvaluo;
	}

	public void setTipoAvaluo(Long tipoAvaluo) {
		this.tipoAvaluo = tipoAvaluo;
	}
	
    public Long getIdAvaluo() {
	return this.idAvaluo;
    }

    public void setIdAvaluo(Long idAvaluo) {
	this.idAvaluo = idAvaluo;
    }

    public Long getCodigoBanco() {
	return this.codigoBanco;
    }

    public void setCodigoBanco(Long codigoBanco) {
	this.codigoBanco = codigoBanco;
    }

    public Long getEstadoAvaluo() {
	return this.estadoAvaluo;
    }

    public void setEstadoAvaluo(Long estadoAvaluo) {
	this.estadoAvaluo = estadoAvaluo;
    }

    public String getNombreBanco() {
	return this.nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
	this.nombreBanco = nombreBanco;
    }

    public Long getCelularSolicitante() {
	return this.celularSolicitante;
    }

    public void setCelularSolicitante(Long celularSolicitante) {
	this.celularSolicitante = celularSolicitante;
    }

    public String getCorreoSolicitante() {
	return this.correoSolicitante;
    }

    public void setCorreoSolicitante(String correoSolicitante) {
	this.correoSolicitante = correoSolicitante;
    }

    public Long getTelefonoSolicitante() {
	return this.telefonoSolicitante;
    }

    public void setTelefonoSolicitante(Long telefonoSolicitante) {
	this.telefonoSolicitante = telefonoSolicitante;
    }

    public Long getTipoInforme() {
	return this.tipoInforme;
    }

    public void setTipoInforme(Long tipoInforme) {
	this.tipoInforme = tipoInforme;
    }

    public String getUbicacionGps() {
	return this.ubicacionGps;
    }

    public void setUbicacionGps(String ubicacionGps) {
	this.ubicacionGps = ubicacionGps;
    }

    public Long getIdMetodologia() {
	return this.idMetodologia;
    }

    public void setIdMetodologia(Long idMetodologia) {
	this.idMetodologia = idMetodologia;
    }

    public Long getIdObjetoAvaluo() {
	return this.idObjetoAvaluo;
    }

    public void setIdObjetoAvaluo(Long idObjetoAvaluo) {
	this.idObjetoAvaluo = idObjetoAvaluo;
    }

    public Long getIdTipoIdentificacion() {
	return this.idTipoIdentificacion;
    }

    public void setIdTipoIdentificacion(Long idTipoIdentificacion) {
	this.idTipoIdentificacion = idTipoIdentificacion;
    }

    public Long getSistemaCoordenadasBUA() {
	return sistemaCoordenadasBUA;
    }

    public void setSistemaCoordenadasBUA(Long sistemaCoordenadasBUA) {
	this.sistemaCoordenadasBUA = sistemaCoordenadasBUA;
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

    public BigInteger getConsecutivoBanco() {
	return this.consecutivoBanco;
    }

    public void setConsecutivoBanco(BigInteger consecutivoBanco) {
	this.consecutivoBanco = consecutivoBanco;
    }

    public Long getNumeroIdentificacion() {
	return this.numeroIdentificacion;
    }

    public void setNumeroIdentificacion(Long numeroIdentificacion) {
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

    public String getLatitudSFBUA() {
	return latitudSFBUA;
    }

    public void setLatitudSFBUA(String latitudSFBUA) {
	this.latitudSFBUA = latitudSFBUA;
    }

    public String getLongitudSFBUA() {
	return longitudSFBUA;
    }

    public void setLongitudSFBUA(String longitudSFBUA) {
	this.longitudSFBUA = longitudSFBUA;
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

    public Long getSector() {
	return this.sector;
    }

    public void setSector(Long sector) {
	this.sector = sector;
    }

    public String getSistemaCoordenadasTxtBUA() {
	return sistemaCoordenadasTxtBUA;
    }

    public void setSistemaCoordenadasTxtBUA(String sistemaCoordenadasTxtBUA) {
	this.sistemaCoordenadasTxtBUA = sistemaCoordenadasTxtBUA;
    }

    public String getUsuario() {
	return this.usuario;
    }

    public void setUsuario(String usuario) {
	this.usuario = usuario;
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

    public List<HistoricoAvaluo> getHistoricosAvaluo() {
	return this.historicosAvaluo;
    }

    public void setHistoricosAvaluo(List<HistoricoAvaluo> historicosAvaluo) {
	this.historicosAvaluo = historicosAvaluo;
    }

    public List<LiquidacionAvaluo> getLiquidacionAvaluos() {
	return this.liquidacionAvaluos;
    }

    public void setLiquidacionAvaluos(List<LiquidacionAvaluo> liquidacionAvaluos) {
	this.liquidacionAvaluos = liquidacionAvaluos;
    }

    public LogsGeneraArchivo getLogsGeneraArchivo() {
	return logsGeneraArchivo;
    }

    public void setLogsGeneraArchivo(LogsGeneraArchivo logsGeneraArchivo) {
	this.logsGeneraArchivo = logsGeneraArchivo;
    }

    public Long getIdCiudad() {
	return idCiudad;
    }

    public void setIdCiudad(Long idCiudad) {
	this.idCiudad = idCiudad;
    }

    public Long getIdDepartamento() {
	return idDepartamento;
    }

    public void setIdDepartamento(Long iddepartamento) {
	this.idDepartamento = iddepartamento;
    }

    public Long getNumLinea() {
	return numLinea;
    }

    public void setNumLinea(Long numLinea) {
	this.numLinea = numLinea;
    }

    public Departamento getDepartamento() {
	return departamento;
    }

    public void setDepartamento(Departamento departamento) {
	this.departamento = departamento;
    }

    public Ciudad getCiudad() {
	return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
	this.ciudad = ciudad;
    }

    public Long getIdArchivo() {
	return idArchivo;
    }

    public void setIdArchivo(Long idArchivo) {
	this.idArchivo = idArchivo;
    }

    public Long getCodTipoAvaluo() {
	return codTipoAvaluo;
    }

    public void setCodTipoAvaluo(Long codTipoAvaluo) {
	this.codTipoAvaluo = codTipoAvaluo;
    }

    public String getDireccionComplementaria() {
	return direccionComplementaria;
    }

    public void setDireccionComplementaria(String direccionComplementaria) {
	this.direccionComplementaria = direccionComplementaria;
    }

    public Long getCodigoProcedencia() {
	return codigoProcedencia;
    }

    public void setCodigoProcedencia(Long codigoProcedencia) {
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

    public Long getCodigoMotivoEliminacion() {
	return codigoMotivoEliminacion;
    }

    public void setCodigoMotivoEliminacion(Long codigoMotivoEliminacion) {
	this.codigoMotivoEliminacion = codigoMotivoEliminacion;
    }

    public Date getFechaEliminacion() {
	return fechaEliminacion;
    }

    public void setFechaEliminacion(Date fechaEliminacion) {
	this.fechaEliminacion = fechaEliminacion;
    }

    public List<ListaAnexosPdf> getListaAnexosPdf() {
	return listaAnexosPdf;
    }

    public void setListaAnexosPdf(List<ListaAnexosPdf> listaAnexosPdf) {
	this.listaAnexosPdf = listaAnexosPdf;
    }

    public List<AlertaAvaluos> getAlertas() {
	return alertas;
    }

    public void setAlertas(List<AlertaAvaluos> alertas) {
	this.alertas = alertas;
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
	for (final String atr : ATRIBUTOS_ENTIDAD_PGB_AVALUOS) {
	    if (atr.equals(atributo)) {
		contiene = true;
	    }
	}

	return contiene;
    }

    public static String[] getAtributosEntidadPgbAvaluos() {
	return ATRIBUTOS_ENTIDAD_PGB_AVALUOS;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int hashCode() {
	int hash = 3;

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
	hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
	hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
	hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
	hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
	hash = 37 * hash + Objects.hashCode(this.idArchivo);
	hash = 37 * hash + Objects.hashCode(this.numLinea);
	hash = 37 * hash + Objects.hashCode(this.sistemaCoordenadasTxtBUA);
	hash = 37 * hash + Objects.hashCode(this.sistemaCoordenadasBUA);
	hash = 37 * hash + Objects.hashCode(this.latitudSFBUA);
	hash = 37 * hash + Objects.hashCode(this.longitudSFBUA);
	hash = 37 * hash + Objects.hashCode(this.codTipoAvaluo);
	hash = 37 * hash + Objects.hashCode(this.direccionComplementaria);
	hash = 37 * hash + Objects.hashCode(this.codigoProcedencia);
	hash = 37 * hash + Objects.hashCode(this.fechaImpresion);
	hash = 37 * hash + Objects.hashCode(this.asegurabilidad);
	hash = 37 * hash + Objects.hashCode(this.codigoMotivoEliminacion);
	hash = 37 * hash + Objects.hashCode(this.fechaEliminacion);
	return hash;
    }

    /**
     * Valida la igualdad de la instancia de la entidad PgbAvaluos que se pasa como
     * parámetro comprobando que comparten los mismos valores en cada uno de sus
     * atributos. Solo se tienen en cuenta los atributos simples, es decir, se
     * omiten aquellos que definen una relación con otra tabla.
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
	final Avaluo other = (Avaluo) obj;
	if (!Objects.equals(this.idAvaluo, other.idAvaluo)) {
	    return false;
	}
	if (!Objects.equals(this.consecutivoBanco, other.consecutivoBanco)) {
	    return false;
	}
	if (!Objects.equals(this.idTipoIdentificacion, other.idTipoIdentificacion)) {
	    return false;
	}
	if (!Objects.equals(this.numeroIdentificacion, other.numeroIdentificacion)) {
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
	if (!Objects.equals(this.usuarioCreacion, other.usuarioCreacion)) {
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
	if (!Objects.equals(this.idArchivo, other.idArchivo)) {
	    return false;
	}
	if (!Objects.equals(this.numLinea, other.numLinea)) {
	    return false;
	}
	if (!Objects.equals(this.sistemaCoordenadasTxtBUA, other.sistemaCoordenadasTxtBUA)) {
	    return false;
	}
	if (!Objects.equals(this.sistemaCoordenadasBUA, other.sistemaCoordenadasBUA)) {
	    return false;
	}
	if (!Objects.equals(this.latitudSFBUA, other.latitudSFBUA)) {
	    return false;
	}
	if (!Objects.equals(this.longitudSFBUA, other.longitudSFBUA)) {
	    return false;
	}
	if (!Objects.equals(this.codTipoAvaluo, other.codTipoAvaluo)) {
	    return false;
	}
	if (!Objects.equals(this.direccionComplementaria, other.direccionComplementaria)) {
	    return false;
	}
	if (!Objects.equals(this.codigoProcedencia, other.codigoProcedencia)) {
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

    // convertir booleanos a numeros
    public boolean isCodTipoAvaluobool1() {
	return codTipoAvaluobool1;
    }

    public void setCodTipoAvaluobool1(boolean codTipoAvaluobool1) {
	this.codTipoAvaluobool1 = codTipoAvaluobool1;
    }

    public boolean isCodTipoAvaluobool2() {
	return codTipoAvaluobool2;
    }

    public void setCodTipoAvaluobool2(boolean codTipoAvaluobool2) {
	this.codTipoAvaluobool2 = codTipoAvaluobool2;
    }

    public String getFiltrocodTipoAvaluo() {
	return filtrocodTipoAvaluo;
    }

    public void setFiltrocodTipoAvaluo(String filtrocodTipoAvaluo) {
	this.filtrocodTipoAvaluo = filtrocodTipoAvaluo;
    }

    public String getMatriculaInmobiliariaPpal1() {
	return matriculaInmobiliariaPpal1;
    }

    public void setMatriculaInmobiliariaPpal1(String matriculaInmobiliariaPpal1) {
	this.matriculaInmobiliariaPpal1 = matriculaInmobiliariaPpal1;
    }

    public Long getCodigoDaneCiudad() {
	return codigoDaneCiudad;
    }

    public void setCodigoDaneCiudad(Long codigoDaneCiudad) {
	this.codigoDaneCiudad = codigoDaneCiudad;
    }

    public Long getCodigoDaneDepartamento() {
	return codigoDaneDepartamento;
    }

    public void setCodigoDaneDepartamento(Long codigoDaneDepartamento) {
	this.codigoDaneDepartamento = codigoDaneDepartamento;
    }

    public Long getIdAvaluoPadre() {
	return idAvaluoPadre;
    }

    public void setIdAvaluoPadre(Long idAvaluoPadre) {
	this.idAvaluoPadre = idAvaluoPadre;
    }

    public Avaluo getPadre() {
	return padre;
    }

    public void setPadre(Avaluo padtre) {
	this.padre = padtre;
    }

    public Long getTransmitido() {
	return transmitido;
    }

    public void setTransmitido(Long transmitido) {
	this.transmitido = transmitido;
    }

    public ComportamientoOfertaDemanda getCompOfertaDemanda() {
	return compOfertaDemanda;
    }

    public void setCompOfertaDemanda(ComportamientoOfertaDemanda compOfertaDemanda) {
	this.compOfertaDemanda = compOfertaDemanda;
    }

    public CondicionesSalubridad getCondicionSalubridad() {
	return condicionSalubridad;
    }

    public void setCondicionSalubridad(CondicionesSalubridad condicionSalubridad) {
	this.condicionSalubridad = condicionSalubridad;
    }

    public InformacionBarrio getInformacionBarrio() {
	return informacionBarrio;
    }

    public void setInformacionBarrio(InformacionBarrio informacionBarrio) {
	this.informacionBarrio = informacionBarrio;
    }

    public InformacionConstruccion getInformacionConstruccion() {
	return informacionConstruccion;
    }

    public void setInformacionConstruccion(InformacionConstruccion informacionConstruccion) {
	this.informacionConstruccion = informacionConstruccion;
    }

    public InformacionInmueble getInformacionInmueble() {
	return informacionInmueble;
    }

    public void setInformacionInmueble(InformacionInmueble informacionInmueble) {
	this.informacionInmueble = informacionInmueble;
    }

    public LiquidacionAvaluoTotal getLiquidacionTotal() {
	return liquidacionTotal;
    }

    public void setLiquidacionTotal(LiquidacionAvaluoTotal liquidacionTotal) {
	this.liquidacionTotal = liquidacionTotal;
    }

    public Observaciones getObservacion() {
	return observacion;
    }

    public void setObservacion(Observaciones observacion) {
	this.observacion = observacion;
    }

    public AnexoFotografico getAnexosFotograficos() {
	return anexosFotograficos;
    }

    public void setAnexosFotograficos(AnexoFotografico anexosFotograficos) {
	this.anexosFotograficos = anexosFotograficos;
    }

    public String getMatriculaInmobiliariaPpal2() {
	return matriculaInmobiliariaPpal2;
    }

    public void setMatriculaInmobiliariaPpal2(String matriculaInmobiliariaPpal2) {
	this.matriculaInmobiliariaPpal2 = matriculaInmobiliariaPpal2;
    }

    public String getMatriculaInmobiliariaDeposito1() {
	return matriculaInmobiliariaDeposito1;
    }

    public void setMatriculaInmobiliariaDeposito1(String matriculaInmobiliariaDeposito1) {
	this.matriculaInmobiliariaDeposito1 = matriculaInmobiliariaDeposito1;
    }

    public String getMatriculaInmobiliariaGaraje1() {
	return matriculaInmobiliariaGaraje1;
    }

    public void setMatriculaInmobiliariaGaraje1(String matriculaInmobiliariaGaraje1) {
	this.matriculaInmobiliariaGaraje1 = matriculaInmobiliariaGaraje1;
    }

    public String getMatriculaInmobiliariaGaraje2() {
	return matriculaInmobiliariaGaraje2;
    }

    public void setMatriculaInmobiliariaGaraje2(String matriculaInmobiliariaGaraje2) {
	this.matriculaInmobiliariaGaraje2 = matriculaInmobiliariaGaraje2;
    }

    public String getRegistroPrivado() {
	return registroPrivado;
    }

    public void setRegistroPrivado(String registroPrivado) {
	this.registroPrivado = registroPrivado;
    }

    public String getRegistroSic() {
	return registroSic;
    }

    public void setRegistroSic(String registroSic) {
	this.registroSic = registroSic;
    }

    public String getSectorDescripcion() {
	return sectorDescripcion;
    }

    public void setSectorDescripcion(String sectorDescripcion) {
	this.sectorDescripcion = sectorDescripcion;
    }

    public String getObjetoDescripcion() {
	return objetoDescripcion;
    }

    public void setObjetoDescripcion(String objetoDescripcion) {
	this.objetoDescripcion = objetoDescripcion;
    }

    public ByteArrayInputStream getImagenLogoEmpresa() {
	return imagenLogoEmpresa;
    }

    public void setImagenLogoEmpresa(ByteArrayInputStream imagenLogoEmpresa) {
	this.imagenLogoEmpresa = imagenLogoEmpresa;
    }

    public String getMetodologiaDescripcion() {
	return metodologiaDescripcion;
    }

    public void setMetodologiaDescripcion(String metodologiaDescripcion) {
	this.metodologiaDescripcion = metodologiaDescripcion;
    }

    public String getDescEmpresa() {
	return descEmpresa;
    }

    public void setDescEmpresa(String descEmpresa) {
	this.descEmpresa = descEmpresa;
    }

    public String getTipoDocumento() {
	return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
	this.tipoDocumento = tipoDocumento;
    }

    public boolean isImpreso() {
	return impreso;
    }

    public void setImpreso(boolean impreso) {
	this.impreso = impreso;
    }

    public Date getFechaImpresionAnterior() {
	return fechaImpresionAnterior;
    }

    public void setFechaImpresionAnterior(Date fechaImpresionAnterior) {
	this.fechaImpresionAnterior = fechaImpresionAnterior;
    }

    public ByteArrayInputStream getImagenFirmaEmpresa() {
	return imagenFirmaEmpresa;
    }

    public void setImagenFirmaEmpresa(ByteArrayInputStream imagenFirmaEmpresa) {
	this.imagenFirmaEmpresa = imagenFirmaEmpresa;
    }

    public Long getCodigoNombreConstructora() {
	return codigoNombreConstructora;
    }

    public void setCodigoNombreConstructora(Long codigoNombreConstructora) {
	this.codigoNombreConstructora = codigoNombreConstructora;
    }

    public String getNombreConstructora() {
	return nombreConstructora;
    }

    public void setNombreConstructora(String nombreConstructora) {
	this.nombreConstructora = nombreConstructora;
    }

    public Long getIdPeritoEmpresa() {
	return idPeritoEmpresa;
    }

    public void setIdPeritoEmpresa(Long idPeritoEmpresa) {
	this.idPeritoEmpresa = idPeritoEmpresa;
    }

    public PeritosEmpresa getPeritoEmpresa() {
	return peritoEmpresa;
    }

    public void setPeritoEmpresa(PeritosEmpresa peritoEmpresa) {
	this.peritoEmpresa = peritoEmpresa;
    }

    public String getTipoAvaluoNombre() {
	return tipoAvaluoNombre;
    }

    public void setTipoAvaluoNombre(String tipoAvaluoNombre) {
	this.tipoAvaluoNombre = tipoAvaluoNombre;
    }

    public String getTipoViviendaNombre() {
	return tipoViviendaNombre;
    }

    public void setTipoViviendaNombre(String tipoViviendaNombre) {
	this.tipoViviendaNombre = tipoViviendaNombre;
    }

    public String getEstadoConstruccionDescripcion() {
	return estadoConstruccionDescripcion;
    }

    public void setEstadoConstruccionDescripcion(String estadoConstruccionDescripcion) {
	this.estadoConstruccionDescripcion = estadoConstruccionDescripcion;
    }

    public String getEstadoAvaluoDescripcion() {
	return estadoAvaluoDescripcion;
    }

    public void setEstadoAvaluoDescripcion(String estadoAvaluoDescripcion) {
	this.estadoAvaluoDescripcion = estadoAvaluoDescripcion;
    }

    public String getEstrato() {
	return estrato;
    }

    public void setEstrato(String estrato) {
	this.estrato = estrato;
    }

    public String getAsegurabilidadDescripcion() {
	return asegurabilidadDescripcion;
    }

    public void setAsegurabilidadDescripcion(String asegurabilidadDescripcion) {
	this.asegurabilidadDescripcion = asegurabilidadDescripcion;
    }

    public String getArchivoZip() {
	return archivoZip;
    }

    public void setArchivoZip(String archivoZip) {
	this.archivoZip = archivoZip;
    }

    public String getCalificacion() {
	return calificacion;
    }

    public void setCalificacion(String calificacion) {
	this.calificacion = calificacion;
    }

    public Long getAvance() {
	return avance;
    }

    public void setAvance(Long avance) {
	this.avance = avance;
    }

    public BigDecimal getValorTotal() {
	return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
	this.valorTotal = valorTotal;
    }

    public BigDecimal getVetustez() {
	return vetustez;
    }

    public void setVetustez(BigDecimal vetustez) {
	this.vetustez = vetustez;
    }
    
    
	public String getIndMotor() {
		return indMotor;
	}

	public void setIndMotor(String indMotor) {
		this.indMotor = indMotor;
	}

	@Override
	public String getConsecutivo() {
		// TODO Auto-generated method stub
		return null;
	}

    // protected region metodos adicionales on begin
    // Escriba en esta sección sus modificaciones

    // protected region metodos adicionales end

}