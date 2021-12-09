package com.segurosbolivar.avaluos.modelo.entity;

import static org.eclipse.persistence.annotations.CacheType.NONE;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import uk.co.jemos.podam.annotations.PodamExclude;

/**
 * The persistent class for the PGB_INFCONSTRUCCION database table.
 * 
 */
@Entity
@Table(name = "PGB_INFCONSTRUCCION")
@NamedQuery(name = "InformacionConstruccion.idAvaluo", query = "SELECT p FROM InformacionConstruccion p WHERE p.idAvaluo=:idAvaluo")
@org.eclipse.persistence.annotations.Cache(type = NONE, alwaysRefresh = true, refreshOnlyIfNewer = true)
public class InformacionConstruccion implements Serializable, Cloneable {
	
	

	private static final long serialVersionUID = 1L;

	// Definicion de atributos de la entidad (Exceptuando relaciones)
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_PK = "idInfconstruccion";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_ID_AVALUO = "idAvaluo";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_PISOS = "nPisos";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_SOTANOS = "nSotanos";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_VETUSTEZ = "nVetustez";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_R_ESTCONS = "rEstcons";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_ESTCONSERVACION = "cEstconservacion";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_ESTTERMINADO = "kEstterminado";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_SINTERMINAR = "kSinterminar";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_ESTREMODELA = "kEstremodela";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_ESTTERMINADA = "kEstterminada";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_ENOBRA = "kEnobra";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_T_AVANCEOBRA = "tAvanceobra";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_ESTRUCTURA = "cEstructura";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_T_ESTRUCTURA = "tEstructura";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_FACHADA = "cFachada";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_T_FACHADA = "tFachada";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_CUBIERTA = "cCubierta";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_T_CUBIERTA = "tCubierta";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_SALA = "nSala";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_BANIOSOCIAL = "nBaniosocial";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_BANIOPRIVADO = "nBanioprivado";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_BANIOSERVICIO = "nBanioservicio";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_JARDIN = "nJardin";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_COMEDOR = "nComedor";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_ESTARHAB = "nEstarhab";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_COCINA = "nCocina";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_PATIOINT = "nPatioint";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_BALCON = "nBalcon";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_ESTUDIO = "nEstudio";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_HABITACIONES = "nHabitaciones";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_CUARTOSERV = "nCuartoserv";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_ZVERDEPRIV = "nZverdepriv";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_TERRAZA = "nTerraza";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_ILUMINACION = "cIluminacion";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_VENTILACION = "cVentilacion";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_TOTALGARAJES = "nTotalgarajes";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_CUBIERTO = "nCubierto";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_USOEXCLUSIVO = "nUsoexclusivo";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_SENCILLO = "nSencillo";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_BAHIACOMUNAL = "nBahiacomunal";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_DEPOSITO = "nDeposito";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_DESCUBIERTO = "nDescubierto";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_PRIVADO = "nPrivado";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_DOBLE = "nDoble";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_SERVIDUMBRE = "nServidumbre";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_LOCAL = "nLocal";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_BODEGA = "nBodega";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_OFICINA = "nOficina";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_ESTPISOS = "cEstpisos";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_ESTMUROS = "cEstmuros";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_ESTMETAL = "cEstmetal";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_ESTCOCINA = "cEstcocina";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_ESTTECHOS = "cEsttechos";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_ESTMADERA = "cEstmadera";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_ESTBANIOS = "cEstbanios";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_TIPOFACHADA = "cTipofachada";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_PISOSBODEGA = "cPisosbodega";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_CALIDPISO = "cCalidpiso";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_CALIDMURO = "cCalidmuro";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_CALIDCMETAL = "cCalidcmetal";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_CALIDCOCINA = "cCalidcocina";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_CALIDTECHO = "cCalidtecho";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_CALIDCMADER = "cCalidcmader";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_CALIDBANIO = "cCalidbanio";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_PROPHORZ = "cProphorz";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_CONJAGRUPCERR = "cConjagrupcerr";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_UBICACIONINM = "cUbicacioninm";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_NUMEDIF = "nNumedif";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_UNDPISO = "nUndpiso";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_TOTALUND = "nTotalund";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_PORTERIA = "kPorteria";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_SALONCOMN = "kSaloncomn";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_AACOND = "kAacond";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_CLUBHOUSE = "kClubhouse";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_JUEGONINOS = "kJuegoninos";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_BICICLETERO = "kBicicletero";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_PRESION = "kPresion";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_ZVERDES = "kZverdes";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_CITOFONO = "kCitofono";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_BOMBA = "kBomba";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_TANQUEAGUA = "kTanqueagua";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_GJVISITA = "kGjvisita";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_CANCHAMULT = "kCanchamult";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_SHUT = "kShut";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_SQUASH = "kSquash";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_GIMNASIO = "kGimnasio";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_GOLFITO = "kGolfito";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_PISCINA = "kPiscina";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_PLANTA = "kPlanta";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_K_ASCENSOR = "kAscensor";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_NUMASCENSORES = "nNumascensores";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_T_OTROSDOTACION = "tOtrosdotacion";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_T_OTROSDOTACION2 = "tOtrosdotacion2";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_A_UBICFUENTESH = "aUbicfuentesh";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_A_UBICNIVELZ = "aUbicnivelz";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_A_LUCES = "aLuces";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_A_EDIPISO = "aEdipiso";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_A_EDISIMALT = "aEdisimalt";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_A_EDISIMPLANTA = "aEdisimplanta";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_A_EDIMAT = "aEdimat";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_A_OTRO_MATERIAL = "aOtroMaterial";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_A_EDIPARAPET = "aEdiparapet";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_A_DCELEVTANQ = "aDcelevtanq";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_A_OTRA_UBIC_TANQUE = "aOtraUbicTanque";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_A_SOBREPESO = "aSobrepeso";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_A_GOLPETEO = "aGolpeteo";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_A_ASENTAMIENTOS = "aAsentamientos";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_A_DANOPREVIO = "aDanoprevio";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_USUARIO_CREACION = "usuarioCreacion";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_FECHA_CREACION = "fechaCreacion";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_USUARIO_TRANSACCION = "usuarioTransaccion";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_FECHA_TRANSACCION = "fechaTransaccion";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_ALTURA = "cAltura";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_REPARADOS = "cReparados";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_IRRALTURA = "cIrraltura";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_IRRPLANTA = "cIrrplanta";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_ESTRUCTURAREFORZADA = "cEstructurareforzada";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_DANOPREVIO = "cDanoprevio";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_N_ANOCONSTRUCCION = "nAnoconstruccion";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_MATERIAL = "cMaterial";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_DETALLEMATERIAL = "cDetallematerial";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_A_RCONS_SF = "aRconsSf";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_A_DANOREPARADO_SF = "aDanoreparadoSf";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_SIMETRIA_ALT_SF = "simetriaAltSf";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_SIMETRIA_PLANT_SF = "simetriaPlantSf";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_A_DANOPREVIO_SF = "aDanoprevioSf";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_ESTREFORZADA_SF = "cEstreforzadaSf";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_C_MATERIAL_SF = "cMaterialSf";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_COD_NOMBRE_CONSTRUCTORA = "codNombreConstructora";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_COD_FINANCIADO_CONSTRUCTOR = "codFinanciadoConstructor";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_TIPO_ADMINISTRADOR = "tipoAdministrador";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_NOMBRE_ADMINISTRADOR = "nombreAdministrador";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_TELEFONO_ADMINISTRADOR = "telefonoAdminstrador";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_TELEFONO_ADMINISTRADOR_2 = "telefonoAdminstrador2";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_TELEFONO_ADMINISTRADOR_3 = "telefonoAdminstrador3";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_CORREO_ADMINISTRADOR = "correoAdministrador";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_CORREO_ADMINISTRADOR_2 = "correoAdministrador2";
	public static final String ENTIDAD_PGB_INFCONSTRUCCION_CORREO_ADMINISTRADOR_3 = "correoAdministrador3";	
	private static final String[] ATRIBUTOS_ENTIDAD_PGB_INFCONSTRUCCION = { ENTIDAD_PGB_INFCONSTRUCCION_N_VETUSTEZ,
			ENTIDAD_PGB_INFCONSTRUCCION_A_ASENTAMIENTOS, ENTIDAD_PGB_INFCONSTRUCCION_C_ESTRUCTURA,
			ENTIDAD_PGB_INFCONSTRUCCION_N_ZVERDEPRIV, ENTIDAD_PGB_INFCONSTRUCCION_C_ALTURA,
			ENTIDAD_PGB_INFCONSTRUCCION_A_EDIMAT, ENTIDAD_PGB_INFCONSTRUCCION_COD_NOMBRE_CONSTRUCTORA,
			ENTIDAD_PGB_INFCONSTRUCCION_C_CONJAGRUPCERR, ENTIDAD_PGB_INFCONSTRUCCION_K_SALONCOMN,
			ENTIDAD_PGB_INFCONSTRUCCION_N_DESCUBIERTO, ENTIDAD_PGB_INFCONSTRUCCION_C_CUBIERTA,
			ENTIDAD_PGB_INFCONSTRUCCION_C_MATERIAL, ENTIDAD_PGB_INFCONSTRUCCION_C_REPARADOS,
			ENTIDAD_PGB_INFCONSTRUCCION_A_OTRA_UBIC_TANQUE, ENTIDAD_PGB_INFCONSTRUCCION_N_CUBIERTO,
			ENTIDAD_PGB_INFCONSTRUCCION_A_DANOREPARADO_SF, ENTIDAD_PGB_INFCONSTRUCCION_A_LUCES,
			ENTIDAD_PGB_INFCONSTRUCCION_K_SQUASH, ENTIDAD_PGB_INFCONSTRUCCION_C_ESTREFORZADA_SF,
			ENTIDAD_PGB_INFCONSTRUCCION_USUARIO_TRANSACCION, ENTIDAD_PGB_INFCONSTRUCCION_C_TIPOFACHADA,
			ENTIDAD_PGB_INFCONSTRUCCION_C_MATERIAL_SF, ENTIDAD_PGB_INFCONSTRUCCION_A_DANOPREVIO_SF,
			ENTIDAD_PGB_INFCONSTRUCCION_N_PISOS, ENTIDAD_PGB_INFCONSTRUCCION_A_DANOPREVIO,
			ENTIDAD_PGB_INFCONSTRUCCION_C_ESTRUCTURAREFORZADA, ENTIDAD_PGB_INFCONSTRUCCION_C_FACHADA,
			ENTIDAD_PGB_INFCONSTRUCCION_C_CALIDCOCINA, ENTIDAD_PGB_INFCONSTRUCCION_SIMETRIA_PLANT_SF,
			ENTIDAD_PGB_INFCONSTRUCCION_T_CUBIERTA, ENTIDAD_PGB_INFCONSTRUCCION_N_BALCON,
			ENTIDAD_PGB_INFCONSTRUCCION_K_TANQUEAGUA, ENTIDAD_PGB_INFCONSTRUCCION_K_SINTERMINAR,
			ENTIDAD_PGB_INFCONSTRUCCION_N_TOTALUND, ENTIDAD_PGB_INFCONSTRUCCION_T_OTROSDOTACION2,
			ENTIDAD_PGB_INFCONSTRUCCION_N_LOCAL, ENTIDAD_PGB_INFCONSTRUCCION_C_DANOPREVIO,
			ENTIDAD_PGB_INFCONSTRUCCION_R_ESTCONS, ENTIDAD_PGB_INFCONSTRUCCION_C_ESTMUROS,
			ENTIDAD_PGB_INFCONSTRUCCION_K_ESTTERMINADA, ENTIDAD_PGB_INFCONSTRUCCION_C_ESTBANIOS,
			ENTIDAD_PGB_INFCONSTRUCCION_C_CALIDTECHO, ENTIDAD_PGB_INFCONSTRUCCION_C_UBICACIONINM,
			ENTIDAD_PGB_INFCONSTRUCCION_K_ZVERDES, ENTIDAD_PGB_INFCONSTRUCCION_K_GJVISITA,
			ENTIDAD_PGB_INFCONSTRUCCION_N_COCINA, ENTIDAD_PGB_INFCONSTRUCCION_N_NUMASCENSORES,
			ENTIDAD_PGB_INFCONSTRUCCION_N_BANIOSERVICIO, ENTIDAD_PGB_INFCONSTRUCCION_K_BICICLETERO,
			ENTIDAD_PGB_INFCONSTRUCCION_K_ESTTERMINADO, ENTIDAD_PGB_INFCONSTRUCCION_C_ESTMETAL,
			ENTIDAD_PGB_INFCONSTRUCCION_C_ESTTECHOS, ENTIDAD_PGB_INFCONSTRUCCION_K_JUEGONINOS,
			ENTIDAD_PGB_INFCONSTRUCCION_N_USOEXCLUSIVO, ENTIDAD_PGB_INFCONSTRUCCION_N_BODEGA,
			ENTIDAD_PGB_INFCONSTRUCCION_A_RCONS_SF, ENTIDAD_PGB_INFCONSTRUCCION_N_SENCILLO,
			ENTIDAD_PGB_INFCONSTRUCCION_C_IRRPLANTA, ENTIDAD_PGB_INFCONSTRUCCION_C_ESTPISOS,
			ENTIDAD_PGB_INFCONSTRUCCION_N_DOBLE, ENTIDAD_PGB_INFCONSTRUCCION_A_EDIPARAPET,
			ENTIDAD_PGB_INFCONSTRUCCION_K_PLANTA, ENTIDAD_PGB_INFCONSTRUCCION_PK, ENTIDAD_PGB_INFCONSTRUCCION_N_COMEDOR,
			ENTIDAD_PGB_INFCONSTRUCCION_N_ANOCONSTRUCCION, ENTIDAD_PGB_INFCONSTRUCCION_N_TERRAZA,
			ENTIDAD_PGB_INFCONSTRUCCION_C_ESTMADERA, ENTIDAD_PGB_INFCONSTRUCCION_K_GOLFITO,
			ENTIDAD_PGB_INFCONSTRUCCION_SIMETRIA_ALT_SF, ENTIDAD_PGB_INFCONSTRUCCION_K_ESTREMODELA,
			ENTIDAD_PGB_INFCONSTRUCCION_K_CLUBHOUSE, ENTIDAD_PGB_INFCONSTRUCCION_C_ILUMINACION,
			ENTIDAD_PGB_INFCONSTRUCCION_C_CALIDPISO, ENTIDAD_PGB_INFCONSTRUCCION_K_ASCENSOR,
			ENTIDAD_PGB_INFCONSTRUCCION_USUARIO_CREACION, ENTIDAD_PGB_INFCONSTRUCCION_N_TOTALGARAJES,
			ENTIDAD_PGB_INFCONSTRUCCION_K_SHUT, ENTIDAD_PGB_INFCONSTRUCCION_FECHA_CREACION,
			ENTIDAD_PGB_INFCONSTRUCCION_COD_FINANCIADO_CONSTRUCTOR, ENTIDAD_PGB_INFCONSTRUCCION_K_GIMNASIO,
			ENTIDAD_PGB_INFCONSTRUCCION_N_PATIOINT, ENTIDAD_PGB_INFCONSTRUCCION_T_ESTRUCTURA,
			ENTIDAD_PGB_INFCONSTRUCCION_C_CALIDCMETAL, ENTIDAD_PGB_INFCONSTRUCCION_C_CALIDCMADER,
			ENTIDAD_PGB_INFCONSTRUCCION_N_BANIOPRIVADO, ENTIDAD_PGB_INFCONSTRUCCION_N_NUMEDIF,
			ENTIDAD_PGB_INFCONSTRUCCION_A_EDISIMALT, ENTIDAD_PGB_INFCONSTRUCCION_T_FACHADA,
			ENTIDAD_PGB_INFCONSTRUCCION_A_DCELEVTANQ, ENTIDAD_PGB_INFCONSTRUCCION_A_UBICNIVELZ,
			ENTIDAD_PGB_INFCONSTRUCCION_N_BANIOSOCIAL, ENTIDAD_PGB_INFCONSTRUCCION_C_CALIDBANIO,
			ENTIDAD_PGB_INFCONSTRUCCION_C_DETALLEMATERIAL, ENTIDAD_PGB_INFCONSTRUCCION_C_ESTCOCINA,
			ENTIDAD_PGB_INFCONSTRUCCION_FECHA_TRANSACCION, ENTIDAD_PGB_INFCONSTRUCCION_A_EDIPISO,
			ENTIDAD_PGB_INFCONSTRUCCION_N_SERVIDUMBRE, ENTIDAD_PGB_INFCONSTRUCCION_N_ESTUDIO,
			ENTIDAD_PGB_INFCONSTRUCCION_C_IRRALTURA, ENTIDAD_PGB_INFCONSTRUCCION_A_SOBREPESO,
			ENTIDAD_PGB_INFCONSTRUCCION_T_AVANCEOBRA, ENTIDAD_PGB_INFCONSTRUCCION_N_UNDPISO,
			ENTIDAD_PGB_INFCONSTRUCCION_K_AACOND, ENTIDAD_PGB_INFCONSTRUCCION_N_SOTANOS,
			ENTIDAD_PGB_INFCONSTRUCCION_C_PISOSBODEGA, ENTIDAD_PGB_INFCONSTRUCCION_C_VENTILACION,
			ENTIDAD_PGB_INFCONSTRUCCION_K_CANCHAMULT, ENTIDAD_PGB_INFCONSTRUCCION_C_ESTCONSERVACION,
			ENTIDAD_PGB_INFCONSTRUCCION_K_PRESION, ENTIDAD_PGB_INFCONSTRUCCION_K_PISCINA,
			ENTIDAD_PGB_INFCONSTRUCCION_N_SALA, ENTIDAD_PGB_INFCONSTRUCCION_N_JARDIN,
			ENTIDAD_PGB_INFCONSTRUCCION_A_UBICFUENTESH, ENTIDAD_PGB_INFCONSTRUCCION_ID_AVALUO,
			ENTIDAD_PGB_INFCONSTRUCCION_K_CITOFONO, ENTIDAD_PGB_INFCONSTRUCCION_K_ENOBRA,
			ENTIDAD_PGB_INFCONSTRUCCION_N_ESTARHAB, ENTIDAD_PGB_INFCONSTRUCCION_A_OTRO_MATERIAL,
			ENTIDAD_PGB_INFCONSTRUCCION_T_OTROSDOTACION, ENTIDAD_PGB_INFCONSTRUCCION_C_CALIDMURO,
			ENTIDAD_PGB_INFCONSTRUCCION_N_DEPOSITO, ENTIDAD_PGB_INFCONSTRUCCION_C_PROPHORZ,
			ENTIDAD_PGB_INFCONSTRUCCION_N_PRIVADO, ENTIDAD_PGB_INFCONSTRUCCION_K_PORTERIA,
			ENTIDAD_PGB_INFCONSTRUCCION_N_BAHIACOMUNAL, ENTIDAD_PGB_INFCONSTRUCCION_A_EDISIMPLANTA,
			ENTIDAD_PGB_INFCONSTRUCCION_K_BOMBA, ENTIDAD_PGB_INFCONSTRUCCION_N_CUARTOSERV,
			ENTIDAD_PGB_INFCONSTRUCCION_N_OFICINA, ENTIDAD_PGB_INFCONSTRUCCION_A_GOLPETEO,
			ENTIDAD_PGB_INFCONSTRUCCION_N_HABITACIONES,ENTIDAD_PGB_INFCONSTRUCCION_TIPO_ADMINISTRADOR,
			ENTIDAD_PGB_INFCONSTRUCCION_NOMBRE_ADMINISTRADOR, ENTIDAD_PGB_INFCONSTRUCCION_TELEFONO_ADMINISTRADOR,
			ENTIDAD_PGB_INFCONSTRUCCION_TELEFONO_ADMINISTRADOR_2, ENTIDAD_PGB_INFCONSTRUCCION_TELEFONO_ADMINISTRADOR_3,
			ENTIDAD_PGB_INFCONSTRUCCION_CORREO_ADMINISTRADOR, ENTIDAD_PGB_INFCONSTRUCCION_CORREO_ADMINISTRADOR_2,
			ENTIDAD_PGB_INFCONSTRUCCION_CORREO_ADMINISTRADOR_3};

	@Id
	@SequenceGenerator(name = "PGB_INFCONST_IDINFCONST_GENERATOR", sequenceName = "SEQ_PGB_INFCONSTRUCCION", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGB_INFCONST_IDINFCONST_GENERATOR")
	@Column(name = "ID_INFCONSTRUCCION", unique = true, nullable = false, precision = 10)
	private Long idInformacionConstruccion;

	@PodamExclude
	@Column(name = "ID_AVALUO")
	private Long idAvaluo;

	@Column(name = "A_ASENTAMIENTOS", precision = 3)
	private Long asentamientos;

	@Column(name = "A_DANOPREVIO", precision = 3)
	private Long danoPrevio;

	@Column(name = "COD_NOMBRE_CONSTRUCTORA")
	private Long codigoNombreConstructora;

	@Column(name = "T_NOMBRE_CONSTRUCTORA")
	private String nombreConstructora;

	@Column(name = "A_DANOPREVIO_SF")
	private Long danoPrevioSF;

	@Column(name = "A_DANOREPARADO_SF")
	private Long danoReparadoSF;

	@Column(name = "A_DCELEVTANQ", precision = 3)
	private Long ubicacionTanque;

	@Column(name = "A_EDIMAT", precision = 22)
	private Long materialEstructura;

	@Column(name = "A_EDIPARAPET", precision = 3)
	private Long parapetosCubierta;

	@Column(name = "A_EDIPISO", precision = 4)
	private Long pisoInmueble;

	@Column(name = "A_EDISIMALT", precision = 3)
	private Long simetriaAltura;

	@Column(name = "A_EDISIMPLANTA", precision = 3)
	private Long simetriaPlanta;

	@Column(name = "A_GOLPETEO", precision = 3)
	private Long golpeteo;

	@Column(name = "A_LUCES", precision = 3)
	private Long luces;

	@Column(name = "A_OTRA_UBIC_TANQUE", length = 30)
	private String otraUbicacionTanque;

	@Column(name = "A_OTRO_MATERIAL", length = 30)
	private String otroMaterial;

	//
	@Column(name = "A_RCONS_SF")
	private Long rangoConstruccionSF;

	@Column(name = "A_SOBREPESO", precision = 3)
	private Long sobrePeso;

	@Column(name = "A_UBICFUENTESH", precision = 3)
	private Long ubicacionFuentesHidricas;

	@Column(name = "A_UBICNIVELZ", precision = 3)
	private Long ubicacionNivelVia;

	@Column(name = "C_CALIDBANIO", precision = 3)
	private Long calidadBanio;

	@Column(name = "C_CALIDCMADER", precision = 3)
	private Long calidadMadera;

	@Column(name = "C_CALIDCMETAL", precision = 3)
	private Long calidMetal;

	@Column(name = "C_CALIDCOCINA", precision = 3)
	private Long calidadCocina;

	@Column(name = "C_CALIDMURO", precision = 3)
	private Long calidadMuro;

	@Column(name = "C_CALIDPISO", precision = 3)
	private Long calidadPiso;

	@Column(name = "C_CALIDTECHO", precision = 3)
	private Long calidadTecho;

	@Column(name = "C_CONJAGRUPCERR", precision = 3)
	private Long conjuntoAgrupacionCerrada;

	@Column(name = "C_CUBIERTA", precision = 3)
	private Long cubierta;

	@Column(name = "C_ESTBANIOS", precision = 3)
	private Long estadoBanios;

	//
	@Column(name = "C_DANOPREVIO")
	private Long danoPrevioBUA;

	//
	@Column(name = "C_DETALLEMATERIAL")
	private Long detalleMaterialSFBUA;

	@Column(name = "C_ESTCOCINA", precision = 3)
	private Long estadoCocina;

	@Column(name = "C_ESTCONSERVACION", precision = 3)
	private Long estadoConservacion;

	@Column(name = "C_ESTMADERA", precision = 3)
	private Long estadoMadera;

	@Column(name = "C_ESTMETAL", precision = 3)
	private Long estadoMetal;

	@Column(name = "C_ESTMUROS", precision = 3)
	private Long estadoMuros;

	@Column(name = "C_ESTPISOS", precision = 3)
	private Long estadoPisos;

	//
	@Column(name = "C_ESTREFORZADA_SF")
	private Long estructuraReforzadaSF;

	@Column(name = "C_ESTRUCTURA", precision = 3)
	private Long estructura;

	//
	@Column(name = "C_ESTRUCTURAREFORZADA")
	private Long estructuraReforzadaBUA;

	@Column(name = "C_ESTTECHOS", precision = 3)
	private Long estructuraTechos;

	@Column(name = "C_FACHADA", precision = 3)
	private Long fachada;

	@Column(name = "C_ILUMINACION", precision = 3)
	private Long iluminacion;

	//
	@Column(name = "C_IRRALTURA")
	private Long irregularidadAlturaBUA;

	//
	@Column(name = "C_IRRPLANTA")
	private Long irregularidadPlantaBUA;

	//
	@Column(name = "C_MATERIAL")
	private Long materialConstruccionBUA;

	@Transient
	private List<Dominios> detallemat;

	//
	@Column(name = "C_MATERIAL_SF")
	private Long materialConstruccionSF;

	@Column(name = "C_PISOSBODEGA", precision = 3)
	private Long pisosBodega;

	@Column(name = "C_PROPHORZ", precision = 3)
	private Long propiedadHorizontal;

	//
	@Column(name = "C_REPARADOS")
	private Long reparadosBUA;

	@Column(name = "C_TIPOFACHADA", precision = 3)
	private Long tipoFachada;

	@Column(name = "C_UBICACIONINM", precision = 3)
	private Long ubicacionInmueble;

	@Column(name = "C_VENTILACION", precision = 3)
	private Long ventilacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CREACION", nullable = false)
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_TRANSACCION", nullable = false)
	private Date fechaTransaccion;

	@Column(name = "K_AACOND", precision = 3)
	private Long aireAcondicionado;

	@Column(name = "K_ASCENSOR", precision = 3)
	private Long ascensor;

	@Column(name = "K_BICICLETERO", precision = 3)
	private Long bicicletero;

	@Column(name = "K_BOMBA", precision = 3)
	private Long bombaEyectora;

	@Column(name = "K_CANCHAMULT", precision = 3)
	private Long canchaMultiple;

	@Column(name = "K_CITOFONO", precision = 3)
	private Long citofono;

	@Column(name = "K_CLUBHOUSE", precision = 3)
	private Long clubHouse;

	@Column(name = "K_ENOBRA", precision = 3)
	private Long enObra;

	@Column(name = "K_ESTREMODELA", precision = 3)
	private Long estadoRemodelacion;

	@Column(name = "K_ESTTERMINADA", precision = 3)
	private Long estadoTerminadaNueva;

	@Column(name = "K_ESTTERMINADO", precision = 3)
	private Long estadoTerminadoUsado;

	@Column(name = "K_GIMNASIO", precision = 3)
	private Long gimnasio;

	@Column(name = "K_GJVISITA", precision = 3)
	private Long garajeVisitante;

	@Column(name = "K_GOLFITO", precision = 3)
	private Long golfito;

	@Column(name = "K_JUEGONINOS", precision = 3)
	private Long juegoNinos;

	@Column(name = "K_PISCINA", precision = 3)
	private Long piscina;

	@Column(name = "K_PLANTA", precision = 3)
	private Long planta;

	@Column(name = "K_PORTERIA", precision = 3)
	private Long porteria;

	@Column(name = "K_PRESION", precision = 3)
	private Long presion;

	@Column(name = "K_SALONCOMN", precision = 3)
	private Long salonComunal;

	@Column(name = "K_SHUT", precision = 3)
	private Long shutBasuras;

	@Column(name = "K_SINTERMINAR", precision = 3)
	private Long sinTerminar;

	@Column(name = "K_SQUASH", precision = 3)
	private Long squash;

	@Column(name = "K_TANQUEAGUA", precision = 3)
	private Long tanqueAgua;

	@Column(name = "K_ZVERDES", precision = 3)
	private Long zonasVerdes;

	@Column(name = "N_ANOCONSTRUCCION", precision = 4)
	private Long anoConstruccionBUA;

	@Column(name = "N_BAHIACOMUNAL", precision = 2)
	private Long bahiaComunal;

	@Column(name = "N_BALCON", precision = 2)
	private Long balcon;

	@Column(name = "N_BANIOPRIVADO", precision = 2)
	private Long banioPrivado;

	@Column(name = "N_BANIOSERVICIO", precision = 2)
	private Long banioServicio;

	@Column(name = "N_BANIOSOCIAL", precision = 2)
	private Long banioSocial;

	@Column(name = "N_BODEGA", precision = 2)
	private Long bodega;

	@Column(name = "N_COCINA", precision = 2)
	private Long cocina;

	@Column(name = "N_COMEDOR", precision = 2)
	private Long comedor;

	@Column(name = "N_CUARTOSERV", precision = 2)
	private Long cuartoServicio;

	@Column(name = "N_CUBIERTO", precision = 2)
	private Long cubierto;

	@Column(name = "N_DEPOSITO", precision = 2)
	private Long deposito;

	@Column(name = "N_DESCUBIERTO", precision = 2)
	private Long descubierto;

	@Column(name = "N_DOBLE", precision = 2)
	private Long doble;

	@Column(name = "N_ESTARHAB", precision = 2)
	private Long estarHabitacion;

	@Column(name = "N_ESTUDIO", precision = 2)
	private Long estudio;

	@Column(name = "N_HABITACIONES", precision = 2)
	private Long numeroHabitaciones;

	@Column(name = "N_JARDIN", precision = 2)
	private Long jardin;

	@Column(name = "N_LOCAL", precision = 2)
	private Long local;

	@Column(name = "N_NUMASCENSORES", precision = 2)
	private Long numeroAscensores;

	@Column(name = "N_NUMEDIF", precision = 2)
	private Long numeroEdificios;

	@Column(name = "N_OFICINA", precision = 2)
	private Long oficina;

	@Column(name = "N_PATIOINT", precision = 2)
	private Long patioInterior;

	@Column(name = "N_PISOS", precision = 2)
	private Long pisos;

	@Column(name = "N_PRIVADO", precision = 2)
	private Long privado;

	@Column(name = "N_SALA", precision = 2)
	private Long sala;

	@Column(name = "N_SENCILLO", precision = 2)
	private Long sencillo;

	@Column(name = "N_SERVIDUMBRE", precision = 2)
	private Long servidumbre;

	@Column(name = "N_SOTANOS", precision = 2)
	private Long sotanos;

	@Column(name = "N_TERRAZA", precision = 2)
	private Long terraza;

	@Column(name = "N_TOTALGARAJES", precision = 2)
	private Long totalGarajes;

	@Column(name = "N_TOTALUND", precision = 8)
	private Long totalUnidades;

	@Column(name = "N_UNDPISO", precision = 2)
	private Long unidadPorPiso;

	@Column(name = "N_USOEXCLUSIVO", precision = 2)
	private Long usoExclusivo;

	@Column(name = "N_VETUSTEZ", precision = 4, scale = 4)
	private BigDecimal vetustez;

	@Column(name = "N_ZVERDEPRIV", precision = 2)
	private Long zonaVerdePrivada;

	@Column(name = "R_ESTCONS", precision = 3)
	private Long estadoConstruccion;

	@Column(name = "COD_FINANCIADO_CONSTRUCTOR")
	private Long codigoFinanciadoConstructor;
	


	public Long getIdAvaluo() {
		return idAvaluo;
	}

	public void setIdAvaluo(Long idAvaluo) {
		this.idAvaluo = idAvaluo;
	}

	public Long getCodigoNombreConstructora() {
		return codigoNombreConstructora;
	}

	public void setCodigoNombreConstructora(Long codigoNombreConstructora) {
		this.codigoNombreConstructora = codigoNombreConstructora;
	}

	public Long getCodigoFinanciadoConstructor() {
		return codigoFinanciadoConstructor;
	}

	public void setCodigoFinanciadoConstructor(Long codigoFinanciadoConstructor) {
		this.codigoFinanciadoConstructor = codigoFinanciadoConstructor;
	}

	@Column(name = "SIMETRIA_ALT_SF")
	private Long simetriaAlturaSF;

	@Column(name = "SIMETRIA_PLANT_SF")
	private Long simetriaPlantaSF;

	@Column(name = "T_AVANCEOBRA", precision = 3)
	private Long avanceObra;

	@Column(name = "T_CUBIERTA", length = 30)
	private String textoCubierta;

	@Column(name = "T_ESTRUCTURA", length = 30)
	private String textoEstructura;

	@Column(name = "T_FACHADA", length = 30)
	private String textoFachada;

	@Column(name = "T_OTROSDOTACION", length = 250)
	private String otrosDotacion;

	@Column(name = "USUARIO_CREACION", nullable = false, length = 15)
	private String usuarioCreacion;

	@Column(name = "USUARIO_TRANSACCION", nullable = false, length = 15)
	private String usuarioTransaccion;

	@Column(name = "C_ALTURA", precision = 3)
	private Long altura;
	
	@Column(name = "TIPO_ADMINISTRADOR", length = 20)
	private String tipoAdministrador;
	
	@Column(name = "NOMBRE_ADMINISTRADOR", length = 50)
	private String nombreAdministrador;
	
	@Column(name = "TELEFONO_ADMINISTRADOR")
	private Long telefonoAdministrador;
	
	@Column(name = "TELEFONO_ADMINISTRADOR_2")
	private Long telefonoAdministrador2;
	
	@Column(name = "TELEFONO_ADMINISTRADOR_3")
	private Long telefonoAdministrador3;
	
	@Column(name = "CORREO_ADMINISTRADOR", length = 50)
	private String correoAdministrador;
	
	@Column(name = "CORREO_ADMINISTRADOR_2", length = 50)
	private String correoAdministrador2;
	
	@Column(name = "CORREO_ADMINISTRADOR_3", length = 50)
	private String correoAdministrador3;

	// bi-directional many-to-one association to Avaluo
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_AVALUO", referencedColumnName = "ID_AVALUO", insertable = false, updatable = false)
	private Avaluo avaluo;

	@Transient
	private String estructuraTechosDescripcion; // INFOCONSTR
	@Transient
	private String detalleMaterialSFBUADescripcion; // C_ESTRUCTURA_SF_BUA
	@Transient
	private String alturaDescripcion; // ALTURA
	@Transient
	private String ubicacionNivelViaDescripcion; // NIVEL
	@Transient
	private String ubicacionTanqueDescripcion; // TANQUES
	@Transient
	private String parapetosCubiertaDescripcion; // AFIRMAR
	@Transient
	private String ubicacionInmuebleDescripcion; // UBICACION
	@Transient
	private String conjuntoAgrupacionCerradaDescripcion; // AFIRMAR
	@Transient
	private String propiedadHorizontalDescripcion; // AFIRMAR
	@Transient
	private String calidadCocinaDescripcion; // COCINA
	@Transient
	private String calidadBanioDescripcion; // CALIDAD
	@Transient
	private String calidadTechosDescripcion; // CALIDAD
	@Transient
	private String calidadMuroDescripcion; // CALIDAD
	@Transient
	private String calidadMetalDescripcion; // CALIDAD
	@Transient
	private String calidadPisoDescripcion; // CALIDAD
	@Transient
	private String calidadMaderaDescripcion; // CALIDAD
	@Transient
	private String estadoCocinaDescripcion; // INFOCONSTR
	@Transient
	private String estadoBaniosDescripcion; // INFOCONSTR
	@Transient
	private String estadoMetalDescripcion; // INFOCONSTR
	@Transient
	private String estadoMaderaDescripcion; // INFOCONSTR
	@Transient
	private String estadoMurosDescripcion; // INFOCONSTR
	@Transient
	private String estadoPisosDescripcion; // INFOCONSTR
	@Transient
	private String iluminacionDescripcion; // MBR
	@Transient
	private String ventilacionDescripcion; // MBR
	@Transient
	private String asentamientosDescripcion; // AFIRMAR
	@Transient
	private String danoPrevioDescripcion; // DANOS
	@Transient
	private String lucesDescripcion; // LUCES
	@Transient
	private String golpeteoDescripcion; // GOLPETEO
	@Transient
	private String sobrePesoDescripcion; // AFIRMAR
	@Transient
	private String ubicacionFuentesHidricasDescripcion; // AFIRMAR
	@Transient
	private String simetriaPlantaDescripcion; // SIMETRIA
	@Transient
	private String simetriaAlturaDescripcion; // SIMETRIA
	@Transient
	private String materialEstructuraDescripcion; // MATEST
	@Transient
	private String cubiertaDescripcion; // CUBIERTA
	@Transient
	private String tipoFachadaDescripcion; // TIPOFACHADA
	@Transient
	private String estructuraDescripcion; // ESTRUCTURA
	@Transient
	private String fachadaDescripcion; // FACHADA
	@Transient
	private String estadoConservacionDescripcion; // ESTADOCONSERVACION
	@Transient
	private String pisosBodegaDescripcion; // PISOSBODEGA
	@Transient
	private String estadoConstruccionDescripcion; // ESTADOCONSTRUCCION
	@Transient
	private String codigoConstructoraDescripcion; // NOMBRECONSTRUCTORA

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end

	public InformacionConstruccion() {
		// protected region procedimientos adicionales de inicialización on
		// begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
	}

	public InformacionConstruccion(Long avaluo) {
		this.idAvaluo = avaluo;
	}

	@Override
	public InformacionConstruccion clone() throws CloneNotSupportedException {
		return (InformacionConstruccion) super.clone();
	}

	public Long getIdInformacionConstruccion() {
		return this.idInformacionConstruccion;
	}

	public void setIdInformacionConstruccion(Long idInformacionConstruccion) {
		this.idInformacionConstruccion = idInformacionConstruccion;
	}

	public Long getAsentamientos() {
		return this.asentamientos;
	}

	public void setAsentamientos(Long asentamientos) {
		this.asentamientos = asentamientos;
	}

	public Long getDanoPrevio() {
		return this.danoPrevio;
	}

	public void setDanoPrevio(Long danoPrevio) {
		this.danoPrevio = danoPrevio;
	}

	public Long getDanoPrevioSF() {
		return danoPrevioSF;
	}

	public void setDanoPrevioSF(Long danoPrevioSF) {
		this.danoPrevioSF = danoPrevioSF;
	}

	public Long getDanoReparadoSF() {
		return danoReparadoSF;
	}

	public void setDanoReparadoSF(Long danoReparadoSF) {
		this.danoReparadoSF = danoReparadoSF;
	}

	public Long getUbicacionTanque() {
		return this.ubicacionTanque;
	}

	public void setUbicacionTanque(Long ubicacionTanque) {
		this.ubicacionTanque = ubicacionTanque;
	}

	public Long getMaterialEstructura() {
		return this.materialEstructura;
	}

	public void setMaterialEstructura(Long materialEstructura) {
		this.materialEstructura = materialEstructura;
	}

	public Long getParapetosCubierta() {
		return this.parapetosCubierta;
	}

	public void setParapetosCubierta(Long parapetosCubierta) {
		this.parapetosCubierta = parapetosCubierta;
	}

	public Long getPisoInmueble() {
		return this.pisoInmueble;
	}

	public void setPisoInmueble(Long pisoInmueble) {
		this.pisoInmueble = pisoInmueble;
	}

	public Long getSimetriaAltura() {
		return this.simetriaAltura;
	}

	public void setSimetriaAltura(Long simetriaAltura) {
		this.simetriaAltura = simetriaAltura;
	}

	public Long getSimetriaPlanta() {
		return this.simetriaPlanta;
	}

	public void setSimetriaPlanta(Long simetriaPlanta) {
		this.simetriaPlanta = simetriaPlanta;
	}

	public Long getGolpeteo() {
		return this.golpeteo;
	}

	public void setGolpeteo(Long golpeteo) {
		this.golpeteo = golpeteo;
	}

	public Long getLuces() {
		return this.luces;
	}

	public void setLuces(Long luces) {
		this.luces = luces;
	}

	public String getOtraUbicacionTanque() {
		return this.otraUbicacionTanque;
	}

	public void setOtraUbicacionTanque(String otraUbicacionTanque) {
		this.otraUbicacionTanque = otraUbicacionTanque;
	}

	public Long getRangoConstruccionSF() {
		return rangoConstruccionSF;
	}

	public void setRangoConstruccionSF(Long rangoConstruccionSF) {
		this.rangoConstruccionSF = rangoConstruccionSF;
	}

	public String getOtroMaterial() {
		return this.otroMaterial;
	}

	public void setOtroMaterial(String otroMaterial) {
		this.otroMaterial = otroMaterial;
	}

	public Long getSobrePeso() {
		return this.sobrePeso;
	}

	public void setSobrePeso(Long sobrePeso) {
		this.sobrePeso = sobrePeso;
	}

	public Long getUbicacionFuentesHidricas() {
		return this.ubicacionFuentesHidricas;
	}

	public void setUbicacionFuentesHidricas(Long ubicacionFuentesHidricas) {
		this.ubicacionFuentesHidricas = ubicacionFuentesHidricas;
	}

	public Long getUbicacionNivelVia() {
		return this.ubicacionNivelVia;
	}

	public void setUbicacionNivelVia(Long ubicacionNivelVia) {
		this.ubicacionNivelVia = ubicacionNivelVia;
	}

	public Long getCalidadBanio() {
		return this.calidadBanio;
	}

	public void setCalidadBanio(Long calidadBanio) {
		this.calidadBanio = calidadBanio;
	}

	public Long getCalidadMadera() {
		return this.calidadMadera;
	}

	public void setCalidadMadera(Long calidadMadera) {
		this.calidadMadera = calidadMadera;
	}

	public Long getCalidMetal() {
		return this.calidMetal;
	}

	public void setCalidMetal(Long calidMetal) {
		this.calidMetal = calidMetal;
	}

	public Long getCalidadCocina() {
		return this.calidadCocina;
	}

	public void setCalidadCocina(Long calidadCocina) {
		this.calidadCocina = calidadCocina;
	}

	public Long getCalidadMuro() {
		return this.calidadMuro;
	}

	public void setCalidadMuro(Long calidadMuro) {
		this.calidadMuro = calidadMuro;
	}

	public Long getCalidadPiso() {
		return this.calidadPiso;
	}

	public void setCalidadPiso(Long calidadPiso) {
		this.calidadPiso = calidadPiso;
	}

	public Long getCalidadTecho() {
		return this.calidadTecho;
	}

	public void setCalidadTecho(Long calidadTecho) {
		this.calidadTecho = calidadTecho;
	}

	public Long getConjuntoAgrupacionCerrada() {
		return this.conjuntoAgrupacionCerrada;
	}

	public void setConjuntoAgrupacionCerrada(Long conjuntoAgrupacionCerrada) {
		this.conjuntoAgrupacionCerrada = conjuntoAgrupacionCerrada;
	}

	public Long getCubierta() {
		return this.cubierta;
	}

	public void setCubierta(Long cubierta) {
		this.cubierta = cubierta;
	}

	public Long getEstadoBanios() {
		return this.estadoBanios;
	}

	public void setEstadoBanios(Long estadoBanios) {
		this.estadoBanios = estadoBanios;
	}

	public Long getDanoPrevioBUA() {
		return danoPrevioBUA;
	}

	public void setDanoPrevioBUA(Long danoPrevioBUA) {
		this.danoPrevioBUA = danoPrevioBUA;
	}

	public Long getDetalleMaterialSFBUA() {
		return detalleMaterialSFBUA;
	}

	public void setDetalleMaterialSFBUA(Long detalleMaterialSFBUA) {
		this.detalleMaterialSFBUA = detalleMaterialSFBUA;
	}

	public Long getEstadoCocina() {
		return this.estadoCocina;
	}

	public void setEstadoCocina(Long estadoCocina) {
		this.estadoCocina = estadoCocina;
	}

	public Long getEstadoConservacion() {
		return this.estadoConservacion;
	}

	public void setEstadoConservacion(Long estadoConservacion) {
		this.estadoConservacion = estadoConservacion;
	}

	public Long getEstadoMadera() {
		return this.estadoMadera;
	}

	public void setEstadoMadera(Long estadoMadera) {
		this.estadoMadera = estadoMadera;
	}

	public Long getEstadoMetal() {
		return this.estadoMetal;
	}

	public void setEstadoMetal(Long estadoMetal) {
		this.estadoMetal = estadoMetal;
	}

	public Long getEstadoMuros() {
		return this.estadoMuros;
	}

	public void setEstadoMuros(Long estadoMuros) {
		this.estadoMuros = estadoMuros;
	}

	public Long getEstadoPisos() {
		return this.estadoPisos;
	}

	public void setEstadoPisos(Long estadoPisos) {
		this.estadoPisos = estadoPisos;
	}

	public void setEstructuraReforzadaSF(Long estructuraReforzadaSF) {
		this.estructuraReforzadaSF = estructuraReforzadaSF;
	}

	public Long getEstructuraReforzadaSF() {
		return estructuraReforzadaSF;
	}

	public Long getEstructura() {
		return this.estructura;
	}

	public void setEstructura(Long estructura) {
		this.estructura = estructura;
	}

	public Long getEstructuraReforzadaBUA() {
		return estructuraReforzadaBUA;
	}

	public void setEstructuraReforzadaBUA(Long estructuraReforzadaBUA) {
		this.estructuraReforzadaBUA = estructuraReforzadaBUA;
	}

	public Long getEstructuraTechos() {
		return this.estructuraTechos;
	}

	public void setEstructuraTechos(Long estructuraTechos) {
		this.estructuraTechos = estructuraTechos;
	}

	public Long getFachada() {
		return this.fachada;
	}

	public void setFachada(Long fachada) {
		this.fachada = fachada;
	}

	public Long getIluminacion() {
		return this.iluminacion;
	}

	public void setIluminacion(Long iluminacion) {
		this.iluminacion = iluminacion;
	}

	public Long getIrregularidadAlturaBUA() {
		return irregularidadAlturaBUA;
	}

	public void setIrregularidadAlturaBUA(Long irregularidadAlturaBUA) {
		this.irregularidadAlturaBUA = irregularidadAlturaBUA;
	}

	public Long getIrregularidadPlantaBUA() {
		return irregularidadPlantaBUA;
	}

	public void setIrregularidadPlantaBUA(Long irregularidadPlantaBUA) {
		this.irregularidadPlantaBUA = irregularidadPlantaBUA;
	}

	public Long getMaterialConstruccionBUA() {
		return materialConstruccionBUA;
	}

	public void setMaterialConstruccionBUA(Long materialConstruccionBUA) {
		this.materialConstruccionBUA = materialConstruccionBUA;
	}

	public List<Dominios> getDetallemat() {
		return detallemat;
	}

	public void setDetallemat(List<Dominios> detallemat) {
		this.detallemat = detallemat;
	}

	public void setMaterialConstruccionSF(Long materialConstruccionSF) {
		this.materialConstruccionSF = materialConstruccionSF;
	}

	public Long getMaterialConstruccionSF() {
		return materialConstruccionSF;
	}

	public Long getPisosBodega() {
		return this.pisosBodega;
	}

	public void setPisosBodega(Long pisosBodega) {
		this.pisosBodega = pisosBodega;
	}

	public Long getPropiedadHorizontal() {
		return this.propiedadHorizontal;
	}

	public void setPropiedadHorizontal(Long propiedadHorizontal) {
		this.propiedadHorizontal = propiedadHorizontal;
	}

	public Long getReparadosBUA() {
		return reparadosBUA;
	}

	public void setReparadosBUA(Long reparadosBUA) {
		this.reparadosBUA = reparadosBUA;
	}

	public Long getTipoFachada() {
		return this.tipoFachada;
	}

	public void setTipoFachada(Long tipoFachada) {
		this.tipoFachada = tipoFachada;
	}

	public Long getUbicacionInmueble() {
		return this.ubicacionInmueble;
	}

	public void setUbicacionInmueble(Long ubicacionInmueble) {
		this.ubicacionInmueble = ubicacionInmueble;
	}

	public Long getVentilacion() {
		return this.ventilacion;
	}

	public void setVentilacion(Long ventilacion) {
		this.ventilacion = ventilacion;
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

	public Long getAireAcondicionado() {
		return this.aireAcondicionado;
	}

	public void setAireAcondicionado(Long aireAcondicionado) {
		this.aireAcondicionado = aireAcondicionado;
	}

	public Long getAscensor() {
		return this.ascensor;
	}

	public void setAscensor(Long ascensor) {
		this.ascensor = ascensor;
	}

	public Long getBicicletero() {
		return this.bicicletero;
	}

	public void setBicicletero(Long bicicletero) {
		this.bicicletero = bicicletero;
	}

	public Long getBombaEyectora() {
		return this.bombaEyectora;
	}

	public void setBombaEyectora(Long bombaEyectora) {
		this.bombaEyectora = bombaEyectora;
	}

	public Long getCanchaMultiple() {
		return this.canchaMultiple;
	}

	public void setCanchaMultiple(Long canchaMultiple) {
		this.canchaMultiple = canchaMultiple;
	}

	public Long getCitofono() {
		return this.citofono;
	}

	public void setCitofono(Long citofono) {
		this.citofono = citofono;
	}

	public Long getClubHouse() {
		return this.clubHouse;
	}

	public void setClubHouse(Long clubHouse) {
		this.clubHouse = clubHouse;
	}

	public Long getEnObra() {
		return this.enObra;
	}

	public void setEnObra(Long enObra) {
		this.enObra = enObra;
	}

	public Long getEstadoRemodelacion() {
		return this.estadoRemodelacion;
	}

	public void setEstadoRemodelacion(Long estadoRemodelacion) {
		this.estadoRemodelacion = estadoRemodelacion;
	}

	public Long getEstadoTerminadaNueva() {
		return this.estadoTerminadaNueva;
	}

	public void setEstadoTerminadaNueva(Long estadoTerminadaNueva) {
		this.estadoTerminadaNueva = estadoTerminadaNueva;
	}

	public Long getEstadoTerminadoUsado() {
		return this.estadoTerminadoUsado;
	}

	public void setEstadoTerminadoUsado(Long estadoTerminadoUsado) {
		this.estadoTerminadoUsado = estadoTerminadoUsado;
	}

	public Long getGimnasio() {
		return this.gimnasio;
	}

	public void setGimnasio(Long gimnasio) {
		this.gimnasio = gimnasio;
	}

	public Long getGarajeVisitante() {
		return this.garajeVisitante;
	}

	public void setGarajeVisitante(Long garajeVisitante) {
		this.garajeVisitante = garajeVisitante;
	}

	public Long getGolfito() {
		return this.golfito;
	}

	public void setGolfito(Long golfito) {
		this.golfito = golfito;
	}

	public Long getJuegoNinos() {
		return this.juegoNinos;
	}

	public void setJuegoNinos(Long juegoNinos) {
		this.juegoNinos = juegoNinos;
	}

	public Long getPiscina() {
		return this.piscina;
	}

	public void setPiscina(Long piscina) {
		this.piscina = piscina;
	}

	public Long getPlanta() {
		return this.planta;
	}

	public void setPlanta(Long planta) {
		this.planta = planta;
	}

	public Long getPorteria() {
		return this.porteria;
	}

	public void setPorteria(Long porteria) {
		this.porteria = porteria;
	}

	public Long getPresion() {
		return this.presion;
	}

	public void setPresion(Long presion) {
		this.presion = presion;
	}

	public Long getSalonComunal() {
		return this.salonComunal;
	}

	public void setSalonComunal(Long salonComunal) {
		this.salonComunal = salonComunal;
	}

	public Long getShutBasuras() {
		return this.shutBasuras;
	}

	public void setShutBasuras(Long shutBasuras) {
		this.shutBasuras = shutBasuras;
	}

	public Long getSinTerminar() {
		return this.sinTerminar;
	}

	public void setSinTerminar(Long sinTerminar) {
		this.sinTerminar = sinTerminar;
	}

	public Long getSquash() {
		return this.squash;
	}

	public void setSquash(Long squash) {
		this.squash = squash;
	}

	public Long getTanqueAgua() {
		return this.tanqueAgua;
	}

	public void setTanqueAgua(Long tanqueAgua) {
		this.tanqueAgua = tanqueAgua;
	}

	public Long getZonasVerdes() {
		return this.zonasVerdes;
	}

	public void setZonasVerdes(Long zonasVerdes) {
		this.zonasVerdes = zonasVerdes;
	}

	public Long getAnoConstruccionBUA() {
		return anoConstruccionBUA;
	}

	public void setAnoConstruccionBUA(Long anoConstruccionBUA) {
		this.anoConstruccionBUA = anoConstruccionBUA;
	}

	public Long getBahiaComunal() {
		return this.bahiaComunal;
	}

	public void setBahiaComunal(Long bahiaComunal) {
		this.bahiaComunal = bahiaComunal;
	}

	public Long getBalcon() {
		return this.balcon;
	}

	public void setBalcon(Long balcon) {
		this.balcon = balcon;
	}

	public Long getBanioPrivado() {
		return this.banioPrivado;
	}

	public void setBanioPrivado(Long banioPrivado) {
		this.banioPrivado = banioPrivado;
	}

	public Long getBanioServicio() {
		return this.banioServicio;
	}

	public void setBanioServicio(Long banioServicio) {
		this.banioServicio = banioServicio;
	}

	public Long getBanioSocial() {
		return this.banioSocial;
	}

	public void setBanioSocial(Long banioSocial) {
		this.banioSocial = banioSocial;
	}

	public Long getBodega() {
		return this.bodega;
	}

	public void setBodega(Long bodega) {
		this.bodega = bodega;
	}

	public Long getCocina() {
		return this.cocina;
	}

	public void setCocina(Long cocina) {
		this.cocina = cocina;
	}

	public Long getComedor() {
		return this.comedor;
	}

	public void setComedor(Long comedor) {
		this.comedor = comedor;
	}

	public Long getCuartoServicio() {
		return this.cuartoServicio;
	}

	public void setCuartoServicio(Long cuartoServicio) {
		this.cuartoServicio = cuartoServicio;
	}

	public Long getCubierto() {
		return this.cubierto;
	}

	public void setCubierto(Long cubierto) {
		this.cubierto = cubierto;
	}

	public Long getDeposito() {
		return this.deposito;
	}

	public void setDeposito(Long deposito) {
		this.deposito = deposito;
	}

	public Long getDescubierto() {
		return this.descubierto;
	}

	public void setDescubierto(Long descubierto) {
		this.descubierto = descubierto;
	}

	public Long getDoble() {
		return this.doble;
	}

	public void setDoble(Long doble) {
		this.doble = doble;
	}

	public Long getEstarHabitacion() {
		return this.estarHabitacion;
	}

	public void setEstarHabitacion(Long estarHabitacion) {
		this.estarHabitacion = estarHabitacion;
	}

	public Long getEstudio() {
		return this.estudio;
	}

	public void setEstudio(Long estudio) {
		this.estudio = estudio;
	}

	public Long getNumeroHabitaciones() {
		return this.numeroHabitaciones;
	}

	public void setNumeroHabitaciones(Long numeroHabitaciones) {
		this.numeroHabitaciones = numeroHabitaciones;
	}

	public Long getJardin() {
		return this.jardin;
	}

	public void setJardin(Long jardin) {
		this.jardin = jardin;
	}

	public Long getLocal() {
		return this.local;
	}

	public void setLocal(Long local) {
		this.local = local;
	}

	public Long getNumeroAscensores() {
		return this.numeroAscensores;
	}

	public void setNumeroAscensores(Long numeroAscensores) {
		this.numeroAscensores = numeroAscensores;
	}

	public Long getNumeroEdificios() {
		return this.numeroEdificios;
	}

	public void setNumeroEdificios(Long numeroEdificios) {
		this.numeroEdificios = numeroEdificios;
	}

	public Long getOficina() {
		return this.oficina;
	}

	public void setOficina(Long oficina) {
		this.oficina = oficina;
	}

	public Long getPatioInterior() {
		return this.patioInterior;
	}

	public void setPatioInterior(Long patioInterior) {
		this.patioInterior = patioInterior;
	}

	public Long getPisos() {
		return this.pisos;
	}

	public void setPisos(Long pisos) {
		this.pisos = pisos;
	}

	public Long getPrivado() {
		return this.privado;
	}

	public void setPrivado(Long privado) {
		this.privado = privado;
	}

	public Long getSala() {
		return this.sala;
	}

	public void setSala(Long sala) {
		this.sala = sala;
	}

	public Long getSencillo() {
		return this.sencillo;
	}

	public void setSencillo(Long sencillo) {
		this.sencillo = sencillo;
	}

	public Long getServidumbre() {
		return this.servidumbre;
	}

	public void setServidumbre(Long servidumbre) {
		this.servidumbre = servidumbre;
	}

	public Long getSotanos() {
		return this.sotanos;
	}

	public void setSotanos(Long sotanos) {
		this.sotanos = sotanos;
	}

	public Long getTerraza() {
		return this.terraza;
	}

	public void setTerraza(Long terraza) {
		this.terraza = terraza;
	}

	public Long getTotalGarajes() {
		return this.totalGarajes;
	}

	public void setTotalGarajes(Long totalGarajes) {
		this.totalGarajes = totalGarajes;
	}

	public Long getTotalUnidades() {
		return this.totalUnidades;
	}

	public void setTotalUnidades(Long totalUnidades) {
		this.totalUnidades = totalUnidades;
	}

	public Long getUnidadPorPiso() {
		return this.unidadPorPiso;
	}

	public void setUnidadPorPiso(Long unidadPorPiso) {
		this.unidadPorPiso = unidadPorPiso;
	}

	public Long getUsoExclusivo() {
		return this.usoExclusivo;
	}

	public void setUsoExclusivo(Long usoExclusivo) {
		this.usoExclusivo = usoExclusivo;
	}

	public BigDecimal getVetustez() {
		return this.vetustez;
	}

	public void setVetustez(BigDecimal vetustez) {
		this.vetustez = vetustez;
	}

	public Long getZonaVerdePrivada() {
		return this.zonaVerdePrivada;
	}

	public void setZonaVerdePrivada(Long zonaVerdePrivada) {
		this.zonaVerdePrivada = zonaVerdePrivada;
	}

	public Long getEstadoConstruccion() {
		return this.estadoConstruccion;
	}

	public void setEstadoConstruccion(Long estadoConstruccion) {
		this.estadoConstruccion = estadoConstruccion;
	}

	public Long getSimetriaAlturaSF() {
		return simetriaAlturaSF;
	}

	public void setSimetriaAlturaSF(Long simetriaAlturaSF) {
		this.simetriaAlturaSF = simetriaAlturaSF;
	}

	public Long getSimetriaPlantaSF() {
		return simetriaPlantaSF;
	}

	public void setSimetriaPlantaSF(Long simetriaPlantaSF) {
		this.simetriaPlantaSF = simetriaPlantaSF;
	}

	public Long getAvanceObra() {
		return this.avanceObra;
	}

	public void setAvanceObra(Long avanceObra) {
		this.avanceObra = avanceObra;
	}

	public String getTextoCubierta() {
		return this.textoCubierta;
	}

	public void setTextoCubierta(String cubierta) {
		this.textoCubierta = cubierta;
	}

	public String getTextoEstructura() {
		return this.textoEstructura;
	}

	public void setTextoEstructura(String estructura) {
		this.textoEstructura = estructura;
	}

	public String getTextoFachada() {
		return this.textoFachada;
	}

	public void setTextoFachada(String fachada) {
		this.textoFachada = fachada;
	}

	public String getOtrosDotacion() {
		return this.otrosDotacion;
	}

	public void setOtrosDotacion(String otrosDotacion) {
		this.otrosDotacion = otrosDotacion;
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

	public Avaluo getAvaluo() {
		return this.avaluo;
	}

	public void setAvaluo(Avaluo avaluo) {
		this.avaluo = avaluo;
	}

	public Long getAltura() {
		return altura;
	}

	public void setAltura(Long altura) {
		this.altura = altura;
	}
	
	public String getTipoAdministrador() {
		return tipoAdministrador;
	}

	public void setTipoAdministrador(String tipoAdministrador) {
		this.tipoAdministrador = tipoAdministrador;
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
		for (final String atr : ATRIBUTOS_ENTIDAD_PGB_INFCONSTRUCCION) {
			if (atr.equals(atributo)) {
				contiene = true;
			}
		}

		return contiene;
	}

	public static String[] getAtributosEntidadInformacionConstruccion() {
		return ATRIBUTOS_ENTIDAD_PGB_INFCONSTRUCCION;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idInformacionConstruccion);
		hash = 37 * hash + Objects.hashCode(this.idAvaluo);
		hash = 37 * hash + Objects.hashCode(this.pisos);
		hash = 37 * hash + Objects.hashCode(this.sotanos);
		hash = 37 * hash + Objects.hashCode(this.vetustez);
		hash = 37 * hash + Objects.hashCode(this.estadoConservacion);
		hash = 37 * hash + Objects.hashCode(this.estadoConservacion);
		hash = 37 * hash + Objects.hashCode(this.estadoTerminadoUsado);
		hash = 37 * hash + Objects.hashCode(this.sinTerminar);
		hash = 37 * hash + Objects.hashCode(this.estadoRemodelacion);
		hash = 37 * hash + Objects.hashCode(this.estadoTerminadaNueva);
		hash = 37 * hash + Objects.hashCode(this.enObra);
		hash = 37 * hash + Objects.hashCode(this.avanceObra);
		hash = 37 * hash + Objects.hashCode(this.estructura);
		hash = 37 * hash + Objects.hashCode(this.estructuraTechos);
		hash = 37 * hash + Objects.hashCode(this.fachada);
		hash = 37 * hash + Objects.hashCode(this.textoFachada);
		hash = 37 * hash + Objects.hashCode(this.cubierta);
		hash = 37 * hash + Objects.hashCode(this.textoCubierta);
		hash = 37 * hash + Objects.hashCode(this.sala);
		hash = 37 * hash + Objects.hashCode(this.banioSocial);
		hash = 37 * hash + Objects.hashCode(this.banioPrivado);
		hash = 37 * hash + Objects.hashCode(this.banioServicio);
		hash = 37 * hash + Objects.hashCode(this.jardin);
		hash = 37 * hash + Objects.hashCode(this.comedor);
		hash = 37 * hash + Objects.hashCode(this.estarHabitacion);
		hash = 37 * hash + Objects.hashCode(this.calidadCocina);
		hash = 37 * hash + Objects.hashCode(this.patioInterior);
		hash = 37 * hash + Objects.hashCode(this.balcon);
		hash = 37 * hash + Objects.hashCode(this.estudio);
		hash = 37 * hash + Objects.hashCode(this.numeroHabitaciones);
		hash = 37 * hash + Objects.hashCode(this.cuartoServicio);
		hash = 37 * hash + Objects.hashCode(this.zonaVerdePrivada);
		hash = 37 * hash + Objects.hashCode(this.terraza);
		hash = 37 * hash + Objects.hashCode(this.iluminacion);
		hash = 37 * hash + Objects.hashCode(this.ventilacion);
		hash = 37 * hash + Objects.hashCode(this.totalGarajes);
		hash = 37 * hash + Objects.hashCode(this.cubierto);
		hash = 37 * hash + Objects.hashCode(this.usoExclusivo);
		hash = 37 * hash + Objects.hashCode(this.sencillo);
		hash = 37 * hash + Objects.hashCode(this.bahiaComunal);
		hash = 37 * hash + Objects.hashCode(this.deposito);
		hash = 37 * hash + Objects.hashCode(this.descubierto);
		hash = 37 * hash + Objects.hashCode(this.privado);
		hash = 37 * hash + Objects.hashCode(this.doble);
		hash = 37 * hash + Objects.hashCode(this.servidumbre);
		hash = 37 * hash + Objects.hashCode(this.local);
		hash = 37 * hash + Objects.hashCode(this.bodega);
		hash = 37 * hash + Objects.hashCode(this.oficina);
		hash = 37 * hash + Objects.hashCode(this.estadoPisos);
		hash = 37 * hash + Objects.hashCode(this.estadoMuros);
		hash = 37 * hash + Objects.hashCode(this.estadoMetal);
		hash = 37 * hash + Objects.hashCode(this.estadoCocina);
		hash = 37 * hash + Objects.hashCode(this.estructuraTechos);
		hash = 37 * hash + Objects.hashCode(this.estadoMadera);
		hash = 37 * hash + Objects.hashCode(this.estadoBanios);
		hash = 37 * hash + Objects.hashCode(this.tipoFachada);
		hash = 37 * hash + Objects.hashCode(this.pisosBodega);
		hash = 37 * hash + Objects.hashCode(this.calidadPiso);
		hash = 37 * hash + Objects.hashCode(this.calidadMuro);
		hash = 37 * hash + Objects.hashCode(this.calidMetal);
		hash = 37 * hash + Objects.hashCode(this.calidadCocina);
		hash = 37 * hash + Objects.hashCode(this.calidadTecho);
		hash = 37 * hash + Objects.hashCode(this.calidadMadera);
		hash = 37 * hash + Objects.hashCode(this.calidadBanio);
		hash = 37 * hash + Objects.hashCode(this.propiedadHorizontal);
		hash = 37 * hash + Objects.hashCode(this.conjuntoAgrupacionCerrada);
		hash = 37 * hash + Objects.hashCode(this.ubicacionInmueble);
		hash = 37 * hash + Objects.hashCode(this.numeroEdificios);
		hash = 37 * hash + Objects.hashCode(this.unidadPorPiso);
		hash = 37 * hash + Objects.hashCode(this.totalUnidades);
		hash = 37 * hash + Objects.hashCode(this.porteria);
		hash = 37 * hash + Objects.hashCode(this.salonComunal);
		hash = 37 * hash + Objects.hashCode(this.aireAcondicionado);
		hash = 37 * hash + Objects.hashCode(this.clubHouse);
		hash = 37 * hash + Objects.hashCode(this.juegoNinos);
		hash = 37 * hash + Objects.hashCode(this.bicicletero);
		hash = 37 * hash + Objects.hashCode(this.presion);
		hash = 37 * hash + Objects.hashCode(this.zonasVerdes);
		hash = 37 * hash + Objects.hashCode(this.citofono);
		hash = 37 * hash + Objects.hashCode(this.bombaEyectora);
		hash = 37 * hash + Objects.hashCode(this.tanqueAgua);
		hash = 37 * hash + Objects.hashCode(this.garajeVisitante);
		hash = 37 * hash + Objects.hashCode(this.canchaMultiple);
		hash = 37 * hash + Objects.hashCode(this.shutBasuras);
		hash = 37 * hash + Objects.hashCode(this.squash);
		hash = 37 * hash + Objects.hashCode(this.gimnasio);
		hash = 37 * hash + Objects.hashCode(this.golfito);
		hash = 37 * hash + Objects.hashCode(this.piscina);
		hash = 37 * hash + Objects.hashCode(this.planta);
		hash = 37 * hash + Objects.hashCode(this.ascensor);
		hash = 37 * hash + Objects.hashCode(this.numeroAscensores);
		hash = 37 * hash + Objects.hashCode(this.otrosDotacion);
		hash = 37 * hash + Objects.hashCode(this.ubicacionFuentesHidricas);
		hash = 37 * hash + Objects.hashCode(this.ubicacionNivelVia);
		hash = 37 * hash + Objects.hashCode(this.luces);
		hash = 37 * hash + Objects.hashCode(this.pisoInmueble);
		hash = 37 * hash + Objects.hashCode(this.simetriaAltura);
		hash = 37 * hash + Objects.hashCode(this.simetriaPlanta);
		hash = 37 * hash + Objects.hashCode(this.materialEstructura);
		hash = 37 * hash + Objects.hashCode(this.otroMaterial);
		hash = 37 * hash + Objects.hashCode(this.parapetosCubierta);
		hash = 37 * hash + Objects.hashCode(this.ubicacionTanque);
		hash = 37 * hash + Objects.hashCode(this.otraUbicacionTanque);
		hash = 37 * hash + Objects.hashCode(this.sobrePeso);
		hash = 37 * hash + Objects.hashCode(this.golpeteo);
		hash = 37 * hash + Objects.hashCode(this.asentamientos);
		hash = 37 * hash + Objects.hashCode(this.danoPrevio);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
		hash = 37 * hash + Objects.hashCode(this.altura);
		hash = 37 * hash + Objects.hashCode(this.reparadosBUA);
		hash = 37 * hash + Objects.hashCode(this.irregularidadAlturaBUA);
		hash = 37 * hash + Objects.hashCode(this.irregularidadPlantaBUA);
		hash = 37 * hash + Objects.hashCode(this.estructuraReforzadaBUA);
		hash = 37 * hash + Objects.hashCode(this.danoPrevio);
		hash = 37 * hash + Objects.hashCode(this.anoConstruccionBUA);
		hash = 37 * hash + Objects.hashCode(this.materialConstruccionBUA);
		hash = 37 * hash + Objects.hashCode(this.detalleMaterialSFBUA);
		hash = 37 * hash + Objects.hashCode(this.rangoConstruccionSF);
		hash = 37 * hash + Objects.hashCode(this.danoReparadoSF);
		hash = 37 * hash + Objects.hashCode(this.simetriaAlturaSF);
		hash = 37 * hash + Objects.hashCode(this.simetriaPlantaSF);
		hash = 37 * hash + Objects.hashCode(this.danoPrevioSF);
		hash = 37 * hash + Objects.hashCode(this.estructuraReforzadaSF);
		hash = 37 * hash + Objects.hashCode(this.materialConstruccionSF);
		hash = 37 * hash + Objects.hashCode(this.codigoNombreConstructora);
		hash = 37 * hash + Objects.hashCode(this.codigoFinanciadoConstructor);
		hash = 37 * hash + Objects.hashCode(this.tipoAdministrador);
		hash = 37 * hash + Objects.hashCode(this.nombreAdministrador);
		hash = 37 * hash + Objects.hashCode(this.telefonoAdministrador);
		hash = 37 * hash + Objects.hashCode(this.telefonoAdministrador2);
		hash = 37 * hash + Objects.hashCode(this.telefonoAdministrador3);
		hash = 37 * hash + Objects.hashCode(this.correoAdministrador);
		hash = 37 * hash + Objects.hashCode(this.correoAdministrador2);
		hash = 37 * hash + Objects.hashCode(this.correoAdministrador3);
		

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad InformacionConstruccion
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
		final InformacionConstruccion other = (InformacionConstruccion) obj;

		if (!Objects.equals(this.idInformacionConstruccion, other.idInformacionConstruccion)) {
			return false;
		}

		if (!Objects.equals(this.idAvaluo, other.idAvaluo)) {
			return false;
		}

		if (!Objects.equals(this.pisos, other.pisos)) {
			return false;
		}

		if (!Objects.equals(this.sotanos, other.sotanos)) {
			return false;
		}

		if (!Objects.equals(this.vetustez, other.vetustez)) {
			return false;
		}

		if (!Objects.equals(this.estadoConstruccion, other.estadoConstruccion)) {
			return false;
		}

		if (!Objects.equals(this.estadoConservacion, other.estadoConservacion)) {
			return false;
		}

		if (!Objects.equals(this.estadoTerminadoUsado, other.estadoTerminadoUsado)) {
			return false;
		}

		if (!Objects.equals(this.sinTerminar, other.sinTerminar)) {
			return false;
		}

		if (!Objects.equals(this.estadoRemodelacion, other.estadoRemodelacion)) {
			return false;
		}

		if (!Objects.equals(this.estadoTerminadaNueva, other.estadoTerminadaNueva)) {
			return false;
		}

		if (!Objects.equals(this.enObra, other.enObra)) {
			return false;
		}

		if (!Objects.equals(this.avanceObra, other.avanceObra)) {
			return false;
		}

		if (!Objects.equals(this.estructura, other.estructura)) {
			return false;
		}

		if (!Objects.equals(this.textoEstructura, other.textoEstructura)) {
			return false;
		}

		if (!Objects.equals(this.fachada, other.fachada)) {
			return false;
		}

		if (!Objects.equals(this.textoFachada, other.textoFachada)) {
			return false;
		}

		if (!Objects.equals(this.cubierta, other.cubierta)) {
			return false;
		}

		if (!Objects.equals(this.textoCubierta, other.textoCubierta)) {
			return false;
		}

		if (!Objects.equals(this.sala, other.sala)) {
			return false;
		}

		if (!Objects.equals(this.banioSocial, other.banioSocial)) {
			return false;
		}

		if (!Objects.equals(this.banioPrivado, other.banioPrivado)) {
			return false;
		}

		if (!Objects.equals(this.banioServicio, other.banioServicio)) {
			return false;
		}

		if (!Objects.equals(this.jardin, other.jardin)) {
			return false;
		}

		if (!Objects.equals(this.comedor, other.comedor)) {
			return false;
		}

		if (!Objects.equals(this.estarHabitacion, other.estarHabitacion)) {
			return false;
		}

		if (!Objects.equals(this.cocina, other.cocina)) {
			return false;
		}

		if (!Objects.equals(this.patioInterior, other.patioInterior)) {
			return false;
		}

		if (!Objects.equals(this.balcon, other.balcon)) {
			return false;
		}

		if (!Objects.equals(this.estudio, other.estudio)) {
			return false;
		}

		if (!Objects.equals(this.numeroHabitaciones, other.numeroHabitaciones)) {
			return false;
		}

		if (!Objects.equals(this.cuartoServicio, other.cuartoServicio)) {
			return false;
		}

		if (!Objects.equals(this.zonaVerdePrivada, other.zonaVerdePrivada)) {
			return false;
		}

		if (!Objects.equals(this.terraza, other.terraza)) {
			return false;
		}

		if (!Objects.equals(this.iluminacion, other.iluminacion)) {
			return false;
		}

		if (!Objects.equals(this.ventilacion, other.ventilacion)) {
			return false;
		}

		if (!Objects.equals(this.totalGarajes, other.totalGarajes)) {
			return false;
		}

		if (!Objects.equals(this.cubierto, other.cubierto)) {
			return false;
		}

		if (!Objects.equals(this.usoExclusivo, other.usoExclusivo)) {
			return false;
		}

		if (!Objects.equals(this.sencillo, other.sencillo)) {
			return false;
		}

		if (!Objects.equals(this.bahiaComunal, other.bahiaComunal)) {
			return false;
		}

		if (!Objects.equals(this.deposito, other.deposito)) {
			return false;
		}

		if (!Objects.equals(this.descubierto, other.descubierto)) {
			return false;
		}

		if (!Objects.equals(this.privado, other.privado)) {
			return false;
		}

		if (!Objects.equals(this.doble, other.doble)) {
			return false;
		}

		if (!Objects.equals(this.servidumbre, other.servidumbre)) {
			return false;
		}

		if (!Objects.equals(this.local, other.local)) {
			return false;
		}

		if (!Objects.equals(this.bodega, other.bodega)) {
			return false;
		}

		if (!Objects.equals(this.oficina, other.oficina)) {
			return false;
		}

		if (!Objects.equals(this.estadoPisos, other.estadoPisos)) {
			return false;
		}

		if (!Objects.equals(this.estadoMuros, other.estadoMuros)) {
			return false;
		}

		if (!Objects.equals(this.estadoMetal, other.estadoMetal)) {
			return false;
		}

		if (!Objects.equals(this.estadoCocina, other.estadoCocina)) {
			return false;
		}

		if (!Objects.equals(this.estructuraTechos, other.estructuraTechos)) {
			return false;
		}

		if (!Objects.equals(this.estadoMadera, other.estadoMadera)) {
			return false;
		}

		if (!Objects.equals(this.estadoBanios, other.estadoBanios)) {
			return false;
		}

		if (!Objects.equals(this.tipoFachada, other.tipoFachada)) {
			return false;
		}

		if (!Objects.equals(this.pisosBodega, other.pisosBodega)) {
			return false;
		}

		if (!Objects.equals(this.calidadPiso, other.calidadPiso)) {
			return false;
		}

		if (!Objects.equals(this.calidadMuro, other.calidadMuro)) {
			return false;
		}

		if (!Objects.equals(this.calidMetal, other.calidMetal)) {
			return false;
		}

		if (!Objects.equals(this.calidadCocina, other.calidadCocina)) {
			return false;
		}

		if (!Objects.equals(this.calidadTecho, other.calidadTecho)) {
			return false;
		}

		if (!Objects.equals(this.calidadMadera, other.calidadMadera)) {
			return false;
		}

		if (!Objects.equals(this.calidadBanio, other.calidadBanio)) {
			return false;
		}

		if (!Objects.equals(this.propiedadHorizontal, other.propiedadHorizontal)) {
			return false;
		}

		if (!Objects.equals(this.conjuntoAgrupacionCerrada, other.conjuntoAgrupacionCerrada)) {
			return false;
		}

		if (!Objects.equals(this.ubicacionInmueble, other.ubicacionInmueble)) {
			return false;
		}

		if (!Objects.equals(this.numeroEdificios, other.numeroEdificios)) {
			return false;
		}

		if (!Objects.equals(this.unidadPorPiso, other.unidadPorPiso)) {
			return false;
		}

		if (!Objects.equals(this.totalUnidades, other.totalUnidades)) {
			return false;
		}

		if (!Objects.equals(this.porteria, other.porteria)) {
			return false;
		}

		if (!Objects.equals(this.salonComunal, other.salonComunal)) {
			return false;
		}

		if (!Objects.equals(this.aireAcondicionado, other.aireAcondicionado)) {
			return false;
		}

		if (!Objects.equals(this.clubHouse, other.clubHouse)) {
			return false;
		}

		if (!Objects.equals(this.juegoNinos, other.juegoNinos)) {
			return false;
		}

		if (!Objects.equals(this.bicicletero, other.bicicletero)) {
			return false;
		}

		if (!Objects.equals(this.presion, other.presion)) {
			return false;
		}

		if (!Objects.equals(this.zonasVerdes, other.zonasVerdes)) {
			return false;
		}

		if (!Objects.equals(this.citofono, other.citofono)) {
			return false;
		}

		if (!Objects.equals(this.bombaEyectora, other.bombaEyectora)) {
			return false;
		}

		if (!Objects.equals(this.tanqueAgua, other.tanqueAgua)) {
			return false;
		}

		if (!Objects.equals(this.garajeVisitante, other.garajeVisitante)) {
			return false;
		}

		if (!Objects.equals(this.canchaMultiple, other.canchaMultiple)) {
			return false;
		}

		if (!Objects.equals(this.shutBasuras, other.shutBasuras)) {
			return false;
		}

		if (!Objects.equals(this.squash, other.squash)) {
			return false;
		}

		if (!Objects.equals(this.gimnasio, other.gimnasio)) {
			return false;
		}

		if (!Objects.equals(this.golfito, other.golfito)) {
			return false;
		}

		if (!Objects.equals(this.piscina, other.piscina)) {
			return false;
		}

		if (!Objects.equals(this.planta, other.planta)) {
			return false;
		}

		if (!Objects.equals(this.ascensor, other.ascensor)) {
			return false;
		}

		if (!Objects.equals(this.numeroAscensores, other.numeroAscensores)) {
			return false;
		}

		if (!Objects.equals(this.otrosDotacion, other.otrosDotacion)) {
			return false;
		}

		if (!Objects.equals(this.ubicacionFuentesHidricas, other.ubicacionFuentesHidricas)) {
			return false;
		}

		if (!Objects.equals(this.ubicacionNivelVia, other.ubicacionNivelVia)) {
			return false;
		}

		if (!Objects.equals(this.luces, other.luces)) {
			return false;
		}

		if (!Objects.equals(this.pisoInmueble, other.pisoInmueble)) {
			return false;
		}

		if (!Objects.equals(this.simetriaAltura, other.simetriaAltura)) {
			return false;
		}

		if (!Objects.equals(this.simetriaPlanta, other.simetriaPlanta)) {
			return false;
		}

		if (!Objects.equals(this.materialEstructura, other.materialEstructura)) {
			return false;
		}

		if (!Objects.equals(this.otroMaterial, other.otroMaterial)) {
			return false;
		}

		if (!Objects.equals(this.parapetosCubierta, other.parapetosCubierta)) {
			return false;
		}

		if (!Objects.equals(this.ubicacionTanque, other.ubicacionTanque)) {
			return false;
		}

		if (!Objects.equals(this.otraUbicacionTanque, other.otraUbicacionTanque)) {
			return false;
		}

		if (!Objects.equals(this.sobrePeso, other.sobrePeso)) {
			return false;
		}

		if (!Objects.equals(this.golpeteo, other.golpeteo)) {
			return false;
		}

		if (!Objects.equals(this.asentamientos, other.asentamientos)) {
			return false;
		}

		if (!Objects.equals(this.danoPrevio, other.danoPrevio)) {
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

		if (!Objects.equals(this.altura, other.altura)) {
			return false;
		}

		if (!Objects.equals(this.reparadosBUA, other.reparadosBUA)) {
			return false;
		}

		if (!Objects.equals(this.irregularidadAlturaBUA, other.irregularidadAlturaBUA)) {
			return false;
		}

		if (!Objects.equals(this.irregularidadPlantaBUA, other.irregularidadPlantaBUA)) {
			return false;
		}

		if (!Objects.equals(this.estructuraReforzadaBUA, other.estructuraReforzadaBUA)) {
			return false;
		}

		if (!Objects.equals(this.danoPrevioBUA, other.danoPrevioBUA)) {
			return false;
		}

		if (!Objects.equals(this.anoConstruccionBUA, other.anoConstruccionBUA)) {
			return false;
		}

		if (!Objects.equals(this.materialConstruccionBUA, other.materialConstruccionBUA)) {
			return false;
		}

		if (!Objects.equals(this.detalleMaterialSFBUA, other.detalleMaterialSFBUA)) {
			return false;
		}

		if (!Objects.equals(this.rangoConstruccionSF, other.rangoConstruccionSF)) {
			return false;
		}

		if (!Objects.equals(this.danoReparadoSF, other.danoReparadoSF)) {
			return false;
		}

		if (!Objects.equals(this.simetriaAlturaSF, other.simetriaAlturaSF)) {
			return false;
		}

		if (!Objects.equals(this.simetriaPlantaSF, other.simetriaPlantaSF)) {
			return false;
		}

		if (!Objects.equals(this.danoPrevioSF, other.danoPrevioSF)) {
			return false;
		}

		if (!Objects.equals(this.estructuraReforzadaSF, other.estructuraReforzadaSF)) {
			return false;
		}

		if (!Objects.equals(this.materialConstruccionSF, other.materialConstruccionSF)) {
			return false;
		}

		if (!Objects.equals(this.codigoNombreConstructora, other.codigoNombreConstructora)) {
			return false;
		}
		

		if (!Objects.equals(this.tipoAdministrador, other.tipoAdministrador)) {
			return false;
		}
		
		if (!Objects.equals(this.nombreAdministrador, other.nombreAdministrador)) {
			return false;
		}
		
		if (!Objects.equals(this.telefonoAdministrador, other.telefonoAdministrador)) {
			return false;
		}
		
		if (!Objects.equals(this.telefonoAdministrador2, other.telefonoAdministrador2)) {
			return false;
		}
		
		if (!Objects.equals(this.telefonoAdministrador3, other.telefonoAdministrador3)) {
			return false;
		}
		
		if (!Objects.equals(this.correoAdministrador, other.correoAdministrador)) {
			return false;
		}
		
		if (!Objects.equals(this.correoAdministrador2, other.correoAdministrador2)) {
			return false;
		}
		
		if (!Objects.equals(this.correoAdministrador3, other.correoAdministrador3)) {
			return false;
		}

		return Objects.equals(this.codigoFinanciadoConstructor, other.codigoFinanciadoConstructor);

	}

	public String getNombreConstructora() {
		return nombreConstructora;
	}

	public void setNombreConstructora(String nombreConstructora) {
		this.nombreConstructora = nombreConstructora;
	}

	public String getEstructuraTechosDescripcion() {
		return estructuraTechosDescripcion;
	}

	public void setEstructuraTechosDescripcion(String estructuraTechosDescripcion) {
		this.estructuraTechosDescripcion = estructuraTechosDescripcion;
	}

	public String getDetalleMaterialSFBUADescripcion() {
		return detalleMaterialSFBUADescripcion;
	}

	public void setDetalleMaterialSFBUADescripcion(String detalleMaterialSFBUADescripcion) {
		this.detalleMaterialSFBUADescripcion = detalleMaterialSFBUADescripcion;
	}

	public String getAlturaDescripcion() {
		return alturaDescripcion;
	}

	public void setAlturaDescripcion(String alturaDescripcion) {
		this.alturaDescripcion = alturaDescripcion;
	}

	public String getUbicacionNivelViaDescripcion() {
		return ubicacionNivelViaDescripcion;
	}

	public void setUbicacionNivelViaDescripcion(String ubicacionNivelViaDescripcion) {
		this.ubicacionNivelViaDescripcion = ubicacionNivelViaDescripcion;
	}

	public String getUbicacionTanqueDescripcion() {
		return ubicacionTanqueDescripcion;
	}

	public void setUbicacionTanqueDescripcion(String ubicacionTanqueDescripcion) {
		this.ubicacionTanqueDescripcion = ubicacionTanqueDescripcion;
	}

	public String getParapetosCubiertaDescripcion() {
		return parapetosCubiertaDescripcion;
	}

	public void setParapetosCubiertaDescripcion(String parapetosCubiertaDescripcion) {
		this.parapetosCubiertaDescripcion = parapetosCubiertaDescripcion;
	}

	public String getUbicacionInmuebleDescripcion() {
		return ubicacionInmuebleDescripcion;
	}

	public void setUbicacionInmuebleDescripcion(String ubicacionInmuebleDescripcion) {
		this.ubicacionInmuebleDescripcion = ubicacionInmuebleDescripcion;
	}

	public String getConjuntoAgrupacionCerradaDescripcion() {
		return conjuntoAgrupacionCerradaDescripcion;
	}

	public void setConjuntoAgrupacionCerradaDescripcion(String conjuntoAgrupacionCerradaDescripcion) {
		this.conjuntoAgrupacionCerradaDescripcion = conjuntoAgrupacionCerradaDescripcion;
	}

	public String getPropiedadHorizontalDescripcion() {
		return propiedadHorizontalDescripcion;
	}

	public void setPropiedadHorizontalDescripcion(String propiedadHorizontalDescripcion) {
		this.propiedadHorizontalDescripcion = propiedadHorizontalDescripcion;
	}

	public String getCalidadCocinaDescripcion() {
		return calidadCocinaDescripcion;
	}

	public void setCalidadCocinaDescripcion(String calidadCocinaDescripcion) {
		this.calidadCocinaDescripcion = calidadCocinaDescripcion;
	}

	public String getCalidadBanioDescripcion() {
		return calidadBanioDescripcion;
	}

	public void setCalidadBanioDescripcion(String calidadBanioDescripcion) {
		this.calidadBanioDescripcion = calidadBanioDescripcion;
	}

	public String getCalidadTechosDescripcion() {
		return calidadTechosDescripcion;
	}

	public void setCalidadTechosDescripcion(String calidadTechosDescripcion) {
		this.calidadTechosDescripcion = calidadTechosDescripcion;
	}

	public String getCalidadMuroDescripcion() {
		return calidadMuroDescripcion;
	}

	public void setCalidadMuroDescripcion(String calidadMuroDescripcion) {
		this.calidadMuroDescripcion = calidadMuroDescripcion;
	}

	public String getCalidadMetalDescripcion() {
		return calidadMetalDescripcion;
	}

	public void setCalidadMetalDescripcion(String calidadMetalDescripcion) {
		this.calidadMetalDescripcion = calidadMetalDescripcion;
	}

	public String getCalidadMaderaDescripcion() {
		return calidadMaderaDescripcion;
	}

	public void setCalidadMaderaDescripcion(String calidadMaderaDescripcion) {
		this.calidadMaderaDescripcion = calidadMaderaDescripcion;
	}

	public String getEstadoCocinaDescripcion() {
		return estadoCocinaDescripcion;
	}

	public void setEstadoCocinaDescripcion(String estadoCocinaDescripcion) {
		this.estadoCocinaDescripcion = estadoCocinaDescripcion;
	}

	public String getEstadoBaniosDescripcion() {
		return estadoBaniosDescripcion;
	}

	public void setEstadoBaniosDescripcion(String estadoBaniosDescripcion) {
		this.estadoBaniosDescripcion = estadoBaniosDescripcion;
	}

	public String getEstadoMetalDescripcion() {
		return estadoMetalDescripcion;
	}

	public void setEstadoMetalDescripcion(String estadoMetalDescripcion) {
		this.estadoMetalDescripcion = estadoMetalDescripcion;
	}

	public String getEstadoMaderaDescripcion() {
		return estadoMaderaDescripcion;
	}

	public void setEstadoMaderaDescripcion(String estadoMaderaDescripcion) {
		this.estadoMaderaDescripcion = estadoMaderaDescripcion;
	}

	public String getEstadoMurosDescripcion() {
		return estadoMurosDescripcion;
	}

	public void setEstadoMurosDescripcion(String estadoMurosDescripcion) {
		this.estadoMurosDescripcion = estadoMurosDescripcion;
	}

	public String getEstadoPisosDescripcion() {
		return estadoPisosDescripcion;
	}

	public void setEstadoPisosDescripcion(String estadoPisosDescripcion) {
		this.estadoPisosDescripcion = estadoPisosDescripcion;
	}

	public String getIluminacionDescripcion() {
		return iluminacionDescripcion;
	}

	public void setIluminacionDescripcion(String iluminacionDescripcion) {
		this.iluminacionDescripcion = iluminacionDescripcion;
	}

	public String getVentilacionDescripcion() {
		return ventilacionDescripcion;
	}

	public void setVentilacionDescripcion(String ventilacionDescripcion) {
		this.ventilacionDescripcion = ventilacionDescripcion;
	}

	public String getAsentamientosDescripcion() {
		return asentamientosDescripcion;
	}

	public void setAsentamientosDescripcion(String asentamientosDescripcion) {
		this.asentamientosDescripcion = asentamientosDescripcion;
	}

	public String getDanoPrevioDescripcion() {
		return danoPrevioDescripcion;
	}

	public void setDanoPrevioDescripcion(String danoPrevioDescripcion) {
		this.danoPrevioDescripcion = danoPrevioDescripcion;
	}

	public String getLucesDescripcion() {
		return lucesDescripcion;
	}

	public void setLucesDescripcion(String lucesDescripcion) {
		this.lucesDescripcion = lucesDescripcion;
	}

	public String getGolpeteoDescripcion() {
		return golpeteoDescripcion;
	}

	public void setGolpeteoDescripcion(String golpeteoDescripcion) {
		this.golpeteoDescripcion = golpeteoDescripcion;
	}

	public String getSobrePesoDescripcion() {
		return sobrePesoDescripcion;
	}

	public void setSobrePesoDescripcion(String sobrePesoDescripcion) {
		this.sobrePesoDescripcion = sobrePesoDescripcion;
	}

	public String getUbicacionFuentesHidricasDescripcion() {
		return ubicacionFuentesHidricasDescripcion;
	}

	public void setUbicacionFuentesHidricasDescripcion(String ubicacionFuentesHidricasDescripcion) {
		this.ubicacionFuentesHidricasDescripcion = ubicacionFuentesHidricasDescripcion;
	}

	public String getSimetriaPlantaDescripcion() {
		return simetriaPlantaDescripcion;
	}

	public void setSimetriaPlantaDescripcion(String simetriaPlantaDescripcion) {
		this.simetriaPlantaDescripcion = simetriaPlantaDescripcion;
	}

	public String getSimetriaAlturaDescripcion() {
		return simetriaAlturaDescripcion;
	}

	public void setSimetriaAlturaDescripcion(String simetriaAlturaDescripcion) {
		this.simetriaAlturaDescripcion = simetriaAlturaDescripcion;
	}

	public String getMaterialEstructuraDescripcion() {
		return materialEstructuraDescripcion;
	}

	public void setMaterialEstructuraDescripcion(String materialEstructuraDescripcion) {
		this.materialEstructuraDescripcion = materialEstructuraDescripcion;
	}

	public String getCubiertaDescripcion() {
		return cubiertaDescripcion;
	}

	public void setCubiertaDescripcion(String cubiertaDescripcion) {
		this.cubiertaDescripcion = cubiertaDescripcion;
	}

	public String getTipoFachadaDescripcion() {
		return tipoFachadaDescripcion;
	}

	public void setTipoFachadaDescripcion(String tipoFachadaDescripcion) {
		this.tipoFachadaDescripcion = tipoFachadaDescripcion;
	}

	public String getEstructuraDescripcion() {
		return estructuraDescripcion;
	}

	public void setEstructuraDescripcion(String estructuraDescripcion) {
		this.estructuraDescripcion = estructuraDescripcion;
	}

	public String getFachadaDescripcion() {
		return fachadaDescripcion;
	}

	public void setFachadaDescripcion(String fachadaDescripcion) {
		this.fachadaDescripcion = fachadaDescripcion;
	}

	public String getEstadoConservacionDescripcion() {
		return estadoConservacionDescripcion;
	}

	public void setEstadoConservacionDescripcion(String estadoConservacionDescripcion) {
		this.estadoConservacionDescripcion = estadoConservacionDescripcion;
	}

	public String getPisosBodegaDescripcion() {
		return pisosBodegaDescripcion;
	}

	public void setPisosBodegaDescripcion(String pisosBodegaDescripcion) {
		this.pisosBodegaDescripcion = pisosBodegaDescripcion;
	}

	public String getEstadoConstruccionDescripcion() {
		return estadoConstruccionDescripcion;
	}

	public void setEstadoConstruccionDescripcion(String estadoConstruccionDescripcion) {
		this.estadoConstruccionDescripcion = estadoConstruccionDescripcion;
	}

	public String getCalidadPisoDescripcion() {
		return calidadPisoDescripcion;
	}

	public void setCalidadPisoDescripcion(String calidadPisoDescripcion) {
		this.calidadPisoDescripcion = calidadPisoDescripcion;
	}

	public String getCodigoConstructoraDescripcion() {
		return codigoConstructoraDescripcion;
	}

	public void setCodigoConstructoraDescripcion(String codigoConstructoraDescripcion) {
		this.codigoConstructoraDescripcion = codigoConstructoraDescripcion;
	}

	public String getNombreAdministrador() {
		return nombreAdministrador;
	}

	public void setNombreAdministrador(String nombreAdministrador) {
		this.nombreAdministrador = nombreAdministrador;
	}

	public Long getTelefonoAdministrador() {
		return telefonoAdministrador;
	}

	public void setTelefonoAdministrador(Long telefonoAdministrador) {
		this.telefonoAdministrador = telefonoAdministrador;
	}

	public Long getTelefonoAdministrador2() {
		return telefonoAdministrador2;
	}

	public void setTelefonoAdministrador2(Long telefonoAdministrador2) {
		this.telefonoAdministrador2 = telefonoAdministrador2;
	}

	public Long getTelefonoAdministrador3() {
		return telefonoAdministrador3;
	}

	public void setTelefonoAdministrador3(Long telefonoAdministrador3) {
		this.telefonoAdministrador3 = telefonoAdministrador3;
	}

	public String getCorreoAdministrador() {
		return correoAdministrador;
	}

	public void setCorreoAdministrador(String correoAdministrador) {
		this.correoAdministrador = correoAdministrador;
	}

	public String getCorreoAdministrador2() {
		return correoAdministrador2;
	}

	public void setCorreoAdministrador2(String correoAdministrador2) {
		this.correoAdministrador2 = correoAdministrador2;
	}

	public String getCorreoAdministrador3() {
		return correoAdministrador3;
	}

	public void setCorreoAdministrador3(String correoAdministrador3) {
		this.correoAdministrador3 = correoAdministrador3;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}