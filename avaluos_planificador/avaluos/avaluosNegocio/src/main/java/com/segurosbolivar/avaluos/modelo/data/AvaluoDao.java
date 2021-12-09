package com.segurosbolivar.avaluos.modelo.data;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.cons.EstadoAvaluo;
import com.segurosbolivar.avaluos.modelo.cons.EstadoBoleano;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.cons.TipoAvaluo;
import com.segurosbolivar.avaluos.modelo.cons.TipoInforme;
import com.segurosbolivar.avaluos.modelo.cons.TipoPerfil;
import com.segurosbolivar.avaluos.modelo.dto.ConsultaAvaluoDto;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.LogsGeneraArchivo;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.service.util.UtilJpa;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad avaluo. Se encarga de las operaciones a
 * base de datos de la tabla de PGB_AVALUOS a través del uso del entityManager.
 *
 * @author stilaguy
 *
 */
@Stateless
public class AvaluoDao extends ManejadorCrud<Avaluo, Long> {

    private static final long serialVersionUID = -6077979740577045532L;
    private static final Logger log = Logger.getLogger(AvaluoDao.class);
    public static final String SELECT = "SELECT a FROM Avaluo a ";
    public static final String ORDER_BY = " ORDER BY a.idAvaluo";
    public static final String COUNT = "SELECT COUNT( a.idAvaluo) FROM Avaluo a ";
    public static final String GET_LAST_ENTRY = "SELECT max(a.idAvaluo) FROM Avaluo a WHERE a.estadoAvaluo = 2 AND a.codTipoAvaluo = 3 AND a.transmitido = 0";
    public static final String OBTENER_AVALUOS_ASEGURABILIDAD = "SELECT a FROM Avaluo a WHERE a.asegurabilidad = 'P'";
    public static final String PARAMETRO_CONSECUTIVO_BANCO = "consecutivoBanco";
    private static final Map<String, String> JOINS;

    public AvaluoDao() {
	super(Avaluo.class);
    }

    /**
     * Se cargar los joins a las principales entidades asociadas al avaluo.
     */
    static {
	JOINS = new HashMap<>();
	JOINS.put("informacionInmueble", "b");
	JOINS.put("informacionConstruccion", "c");
	JOINS.put("alertas", "d");
	JOINS.put("informacionBarrio", "e");
	JOINS.put("liquidacionTotal", "f");
	JOINS.put("peritoEmpresa", "p");
	JOINS.put("logsGeneraArchivo", "l");
    }

    /**
     * Permite realizar la consulta de la ventana de informe de avaluos, permite
     * establecer el orden definido por un campo determinado al igual que un limite
     * de registros a consultar para la paginacion.
     * 
     * @param consultar
     *            contiene todos los posibles filtro asociados a la consulta.
     * @param inicio
     *            registro en el que inicia la consulta
     * @param tamanioPagina
     *            cantidad de registros a consultar a partir del registro inicial
     * @param campoOrden
     *            campo de la base de datos por la cual se determinará el orden de
     *            la consulta
     * @param sentido
     *            de orientacion de la consulta.
     * @return listado de avaluos asociados al filtro de la consulta.
     */
    @SuppressWarnings("unchecked")
    public List<Avaluo> consultar(ConsultaAvaluoDto consultar, int inicio, int tamanioPagina, String campoOrden, SentidoOrientacion sentido) {
    	String query = SELECT.concat(agregarJoin(consultar)).concat(generarWhere(consultar))
		.concat(UtilTexto.estaVacio(campoOrden) ? ORDER_BY : UtilJpa.generarOrder("a", campoOrden, sentido, JOINS));
	Query consulta = mp.createQuery(query);
	agregarParametros(consulta, consultar);
	consulta.setMaxResults(tamanioPagina);
	consulta.setFirstResult(inicio);
	return consulta.getResultList();
    }

    /**
     * Permite agregar a una consulta de JPA los valores que deben enviarse
     * obligatoriamente como parametros del aplicativo.
     * 
     * @param consulta
     *            JPA al que se asociaran los parametros del filtro.
     * @param consultar
     *            contiene los valores del filtro que se aplicaran como parametros a
     *            la consulta de JPA
     */
    private void agregarParametros(Query consulta, ConsultaAvaluoDto consultar) {
	UtilJpa.agregarParametros(consulta, "fechaAvaluoDesde", consultar.getFechaAvaluoDesde());
	UtilJpa.agregarParametros(consulta, "fechaAvaluoHasta", consultar.getFechaAvaluoHasta());
	UtilJpa.agregarParametros(consulta, "fechaImpresionDesde", consultar.getFechaImpresionDesde());
	UtilJpa.agregarParametros(consulta, "fechaImpresionHasta", consultar.getFechaImpresionHasta());
	UtilJpa.agregarParametros(consulta, "fechaCargueDesde", consultar.getFechaCargueDesde());
	UtilJpa.agregarParametros(consulta, "fechaCargueHasta", consultar.getFecharCargueHasta());
	UtilJpa.agregarParametros(consulta, "numeroIdentificacion", consultar.getNumeroIdentificacion());
	UtilJpa.agregarParametros(consulta, PARAMETRO_CONSECUTIVO_BANCO, consultar.getConsecutivoBanco());
    }

    /**
     * Permite realizar la consulta de los avaluos para la alerta de cumulo
     * constructor a partir del avaluo que se examina.
     * 
     * @param avaluo
     *            al que se consultara los avaluos asociados y que cumplen con las
     *            reglas para la alerta de cumulo constructor.
     * @return listado de avlauos relacionados con el avaluo base y que cumplen con
     *         las reglas de la alerta de cumulo constructor.
     */
    @SuppressWarnings("unchecked")
    public List<Avaluo> consultaCumulo(Avaluo avaluo) {
	StringBuilder consultaArea = new StringBuilder();
	consultaArea.append(SELECT);
	consultaArea.append("JOIN a.informacionBarrio b ");
	consultaArea.append("JOIN a.informacionConstruccion d ");
	consultaArea.append("WHERE a.idDepartamento = :idDepartamento ");
	consultaArea.append("AND a.idCiudad = :idCiudad ");
	consultaArea.append("AND d.codigoFinanciadoConstructor = :codigoFinanciadoConstructor ");
	consultaArea.append("AND a.tipoInforme = :tipoInforme ");
	consultaArea.append("AND a.estadoAvaluo = :estadoAvaluo ");
	consultaArea.append("AND a.consecutivoBanco <> :consecutivoBanco ");
	Query consulta = mp.createQuery(consultaArea.toString());
	consulta.setParameter("idDepartamento", avaluo.getIdDepartamento());
	consulta.setParameter("idCiudad", avaluo.getIdCiudad());
	consulta.setParameter("codigoFinanciadoConstructor", avaluo.getInformacionConstruccion().getCodigoFinanciadoConstructor());
	consulta.setParameter("tipoInforme", TipoInforme.CREDITO.getValor());
	consulta.setParameter("estadoAvaluo", EstadoAvaluo.APROBADO.getValor());
	consulta.setParameter(PARAMETRO_CONSECUTIVO_BANCO, avaluo.getConsecutivoBanco());
	return consulta.getResultList();
    }

    /**
     * Permite realizar la consulta de los avaluos para la alerta de comparativo
     * valor a partir del avaluo que se examina.
     * 
     * @param avaluo
     *            al que se consultara los avaluos asociados y que cumplen con las
     *            reglas para la alerta de valor comparativo
     * @return listado de avlauos relacionados con el avaluo base y que cumplen con
     *         las reglas de la alerta de valor comparativo.
     */
    @SuppressWarnings("unchecked")
    public List<Avaluo> consultaPorArea(Avaluo avaluo) {
	StringBuilder consultaArea = new StringBuilder();
	consultaArea.append("SELECT a FROM Avaluo a ");
	consultaArea.append("JOIN a.informacionBarrio b ");
	consultaArea.append("JOIN a.informacionInmueble c ");
	consultaArea.append("JOIN a.informacionConstruccion d ");
	consultaArea.append("WHERE a.idDepartamento = :idDepartamento ");
	consultaArea.append("AND a.idCiudad = :idCiudad ");
	consultaArea.append("AND b.estrato = :estrato ");
	consultaArea.append("AND c.tipoVivienda = :tipoVivienda ");
	consultaArea.append("AND d.propiedadHorizontal = :propiedadHorizontal ");
	consultaArea.append("AND a.estadoAvaluo = :estadoAvaluo ");
	consultaArea.append("AND a.consecutivoBanco <> :consecutivoBanco ");
	Query consulta = mp.createQuery(consultaArea.toString());
	consulta.setParameter("idDepartamento", avaluo.getIdDepartamento());
	consulta.setParameter("idCiudad", avaluo.getIdCiudad());
	consulta.setParameter("estrato", avaluo.getInformacionBarrio().getEstrato());
	consulta.setParameter("tipoVivienda", avaluo.getInformacionInmueble().getTipoVivienda());
	consulta.setParameter("propiedadHorizontal", avaluo.getInformacionConstruccion().getPropiedadHorizontal());
	consulta.setParameter("estadoAvaluo", 3);
	consulta.setParameter(PARAMETRO_CONSECUTIVO_BANCO, avaluo.getConsecutivoBanco());
	return consulta.getResultList();
    }

    /***
     * Permite determinar la cantidad de registros para la consulta a realizarse de
     * un avaluo, esto permitira el uso del paginador para la consulta.
     * 
     * @param consultar
     *            contiene los valores del filtro que se usaran en la consulta y
     *            para determinar la cantidad de registros.
     * @return el numero de registros que cumplen con los filtros de la consulta
     */
    public int cantidadRegistros(ConsultaAvaluoDto consultar) {
	String query = COUNT.concat(agregarJoin(consultar)).concat(generarWhere(consultar));
	Query consulta = mp.createQuery(query);
	agregarParametros(consulta, consultar);
	return ((Long) consulta.getSingleResult()).intValue();
    }

    /**
     * Permite asociar los diferentes entidades relacionadas al avaluo a la
     * consulta, dependiendo de si el filtro de las mismas implica el uso de estas
     * entidades.
     * 
     * @param consultar
     *            contiene los valores de los filtros usados para la consulta
     * @return el JOIN de JPA requerido para que la consulta se relacione con las
     *         tablas respectivas.
     */
    public String agregarJoin(ConsultaAvaluoDto consultar) {
	if (consultar == null)
	    return "";
	StringBuilder join = new StringBuilder();
	if (consultar.getConsecutivoBanco() != null)
	    join.append("LEFT JOIN a.padre dr ");
	if (consultar.getTiposAlerta() != null)
	    join.append("LEFT JOIN a.alertas d ");
	join.append("LEFT JOIN a.informacionInmueble b ");
	join.append("LEFT JOIN a.informacionConstruccion c ");
	join.append("LEFT JOIN a.informacionBarrio e ");
	join.append("LEFT JOIN a.liquidacionTotal f ");
	join.append("LEFT JOIN a.peritoEmpresa p ");
	join.append("LEFT JOIN a.logsGeneraArchivo l ");
	return join.toString();
    }

    /**
     * Genera la cadena de caracteres que contiene las condiciones asociadas a la
     * consulta segun los valores del filtro de la consulta.
     * 
     * @param consultar
     *            contiene los valores de los filtros
     * @return cadena de caracteres con las condicones implicadas por los filtros
     *         asociados a la consulta a generar.
     */
    private String generarWhere(ConsultaAvaluoDto consultar) {
	if (consultar == null)
	    return "";
	StringBuilder sb = new StringBuilder();
	String codigoPerfil="";
	if (consultar.getUsuario() != null)
	codigoPerfil = consultar.getUsuario().getUsuario().getCodigoPerfil();
	boolean tieneCondiciones = false;
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.idTipoIdentificacion", consultar.getIdTipoIdentificacion(), null, null, tieneCondiciones, true, false, false);
	if (consultar.getNumeroIdentificacion() != null)
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "a.numeroIdentificacion", "numeroIdentificacion", null, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.idCiudad", consultar.getIdCiudad(), null, null, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.idDepartamento", consultar.getIdDepartamento(), null, null, tieneCondiciones, true, false, false);
	if (!UtilTexto.estaVacio(consultar.getDireccionInmueble())) {
	    tieneCondiciones |= UtilJpa.agregarCondicionLIKE(sb, "UPPER(a.direccionInmueble)", consultar.getDireccionInmueble().toUpperCase(), tieneCondiciones, true, true, false);
	    tieneCondiciones |= UtilJpa.agregarCondicionLIKE(sb, "UPPER(a.direccionComplementaria)", consultar.getDireccionInmueble().toUpperCase(), tieneCondiciones, false, false,
		    true);
	}
	
	// Solo ilustrar los avaluos que no han sido eliminados
	tieneCondiciones |= UtilJpa.agregarCondicionISNULL(sb, "a.codigoMotivoEliminacion", UtilConstantes.EX_IS_NULL, tieneCondiciones, true, false, false);	
	
	
	// consultar perito

	if (consultar.getConsecutivoBanco() != null) {
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "a.consecutivoBanco", PARAMETRO_CONSECUTIVO_BANCO, null, tieneCondiciones, true, true, false);
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "dr.consecutivoBanco", PARAMETRO_CONSECUTIVO_BANCO, null, tieneCondiciones, false, false, true);
	}
	
	if(consultar.getEstadoAvaluo() != null) {
		tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.estadoAvaluo",consultar.getEstadoAvaluo(), null, null, tieneCondiciones,true, false, false);
	}
	
	if (consultar.getFechaAvaluoDesde() != null)
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "a.fechaAvaluo", "fechaAvaluoDesde", UtilConstantes.CR_GREATER_EQUAL, tieneCondiciones, true, false, false);
	if (consultar.getFechaAvaluoHasta() != null)
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "a.fechaAvaluo", "fechaAvaluoHasta", UtilConstantes.CR_LESS_EQUAL, tieneCondiciones, true, false, false);
	if (!UtilTexto.estaVacio(consultar.getNombreConjuntoEdificio()))
	    tieneCondiciones |= UtilJpa.agregarCondicionLIKE(sb, "UPPER(a.nombreConjuntoEdificio)", consultar.getNombreConjuntoEdificio().toUpperCase(), tieneCondiciones, true,
		    false, false);
	tieneCondiciones |= UtilJpa.agregarCondicionIN(sb, "a.codTipoAvaluo", consultar.getFiltroTipoAvaluo(), tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.asegurabilidad", consultar.getAsegurabilidad(), null, null, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.numLinea", consultar.getNumLinea(), null, null, tieneCondiciones, true, false, false);
	if (consultar.getFechaImpresionDesde() != null)
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "a.fechaImpresion", "fechaImpresionDesde", UtilConstantes.CR_GREATER_EQUAL, tieneCondiciones, true, false,
		    false);
	if (consultar.getFechaImpresionHasta() != null)
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "a.fechaImpresion", "fechaImpresionHasta", UtilConstantes.CR_LESS_EQUAL, tieneCondiciones, true, false,
		    false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.tipoInforme", consultar.getTipoInforme(), null, null, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.logsGeneraArchivo.nombrePlano", consultar.getNombrePlano(), null, null, tieneCondiciones, true, false, false);
	if (consultar.getFechaCargueDesde() != null)
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "a.fechaCreacion", "fechaCargueDesde", UtilConstantes.CR_GREATER_EQUAL, tieneCondiciones, true, false, false);
	if (consultar.getFecharCargueHasta() != null)
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "a.fechaCreacion", "fechaCargueHasta", UtilConstantes.CR_LESS_EQUAL, tieneCondiciones, true, false, false);
	if (!UtilTexto.estaVacio(consultar.getMatricula()))
	    tieneCondiciones |= UtilJpa.agregarCondicionLIKE(sb, "UPPER(b.matriculaInmobiliariaPpal1)", consultar.getMatricula().toUpperCase(), tieneCondiciones, true, false,
		    false);
	if (!UtilTexto.estaVacio(consultar.getNombreConstructora())) {
	    tieneCondiciones |= UtilJpa.agregarCondicionLIKE(sb, "UPPER(c.nombreConstructora)", consultar.getMatricula().toUpperCase(), tieneCondiciones, true, false, false);
	}
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "b.tipoVivienda", consultar.getTipoVivienda(), null, null, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "c.estadoConstruccion", consultar.getEstadoConstruccion(), null, null, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "c.avanceObra", consultar.getAvanceDesde(), null, UtilConstantes.CR_GREATER_EQUAL, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "c.avanceObra", consultar.getAvanceHasta(), null, UtilConstantes.CR_LESS_EQUAL, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "c.vetustez", consultar.getVetustezDesde(), null, UtilConstantes.CR_GREATER_EQUAL, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "c.vetustez", consultar.getVetustezHasta(), null, UtilConstantes.CR_LESS_EQUAL, tieneCondiciones, true, false, false);
	if (!UtilTexto.estaVacio(consultar.getTiposAlerta()))
	    tieneCondiciones |= UtilJpa.agregarCondicionIN(sb, "d.codTipoAlerta", consultar.getTiposAlerta(), tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "e.estrato", consultar.getEstrato(), null, null, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "f.calificacion", consultar.getCalificacion(), null, null, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "f.totalAvaluo", consultar.getValorDesde(), null, UtilConstantes.CR_GREATER_EQUAL, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.totalAvaluo", consultar.getValorHasta(), null, UtilConstantes.CR_LESS_EQUAL, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.codigoProcedencia", consultar.getCodigoProcedencia(), null, null, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "b.idCategoria", consultar.getCategoria(), null, null, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "b.claseInmueble", consultar.getClaseInmueble(), null, null, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "p.estadoAsociacion", "A", null, null, tieneCondiciones, true, false, false);
	if (TipoPerfil.PERITO_PADRE.getValor().equals(codigoPerfil) || TipoPerfil.PERITO_HIJO.getValor().equals(codigoPerfil)
		|| TipoPerfil.PERITO_COORDINADOR.getValor().equals(codigoPerfil)) {
	    Long idEmpresaAvaluo = obtenerEmpresaAvaluadora(consultar);
	    tieneCondiciones |= UtilJpa.agregarCondicion(sb, "p.idEmpresaAvaluo", idEmpresaAvaluo, null, null, tieneCondiciones, true, false, false);
	} else if (TipoPerfil.PERITO_HIJO_SEGUNDO.getValor().equals(codigoPerfil)) {
	    consultar.setUsuarioCreacion(consultar.getUsuario().getUsuario().getCodigo());
	}
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.usuarioCreacion", consultar.getUsuarioCreacion(), null, null, tieneCondiciones, true, false, false);
	return tieneCondiciones ? UtilConstantes.SQL_WHERE + sb.toString() : "";
    }

    /**
     * Permite consultar los avaluos por el consecutivo de los mismos
     * 
     * @param consultar
     *            contiene el valor del consecutivo por el cual se realizara la
     *            consulta
     * @return listado de avaluos que corresponde al consecutivo asociado.
     */
    @SuppressWarnings("unchecked")
    public List<Avaluo> consultarConsecutivoBanco(Avaluo consultar) {
	Query consulta = mp.createNamedQuery(Avaluo.class.getSimpleName() + ".consecutivoBanco");
	consulta.setParameter("consecutivo", consultar.getConsecutivoBanco());
	return consulta.getResultList();
    }

    /**
     * ermite consultar un avaluo por el consecutivo asociado al mismo.
     * 
     * @param consecutivoBanco
     *            asociado al avaluo y por el cual se intentara consultar el avlauo
     * @return avaluo asociado al consecutivo si no existe retornara nulo.
     */
    public Avaluo consultarConsecutivoBanco(BigInteger consecutivoBanco) {
	Query consulta = mp.createNamedQuery(Avaluo.class.getSimpleName() + ".consecutivoBanco");
	consulta.setParameter("consecutivo", consecutivoBanco);
	try {
	    return (Avaluo) consulta.getSingleResult();
	} catch (Exception e) {
	    return null;
	}
    }

    /**
     * Consulta los avaluos de una fecha de avaluo limite menor o igual que cumplen
     * con las condiciones para procedatos. La consulta tiene un limite de registros
     * definido.
     * 
     * @param fechaHasta
     *            fecha limite menor o igual a la fecha del avaluos
     * @param cantidadRegistros
     *            limite de registros a consultar
     * @return listado de avaluos con fecha menor a la fecha solicitada y que
     *         cumplen con las condiciones de procedatos.
     */
    @SuppressWarnings("unchecked")
    public List<Avaluo> consultarAvaluosProcedatos(Date fechaHasta, int cantidadRegistros) {
	StringBuilder query = new StringBuilder("SELECT a. * FROM pgb_avaluos a");
	query.append(" INNER JOIN pgb_infconstruccion c ON a.id_avaluo=c.id_avaluo");
	//query.append(" INNER JOIN pgb_anexo_fotografico r  on r.id_avaluo=a.id_avaluo ");
	query.append(" WHERE a.a_estadoavaluo = ? AND a.a_idarchivo IS NULL ");
	query.append(" AND a.cod_tipo_avaluo <> ? ");
	query.append(" AND (c.k_enobra <> ? OR c.k_enobra IS NULL)");
	query.append(" AND a.k_transmitido <> ? ");
	if (fechaHasta != null)
	    query.append(" AND TRUNC(a.f_fechaavaluo) <= TRUNC(?) ");
	Query consulta = mp.nativeQuery(query.toString(), Avaluo.class);
	UtilJpa.agregarParametros(consulta, 1, EstadoAvaluo.APROBADO.getValor());
	UtilJpa.agregarParametros(consulta, 2, TipoAvaluo.PROVISIONAL.getValor());
	UtilJpa.agregarParametros(consulta, 3, EstadoBoleano.TRUE.getValor());
	UtilJpa.agregarParametros(consulta, 4, EstadoBoleano.TRUE.getValor());
	UtilJpa.agregarParametros(consulta, 5, fechaHasta);
	consulta.setMaxResults(cantidadRegistros);
	consulta.setFirstResult(0);
	return consulta.getResultList();
    }

    /**
     * Permite consultar la empresa avaluadora a la que esta reliacionado un avaluo.
     * 
     * @param consulta
     *            contiene la identificfacion del perito por el cual intenetaremos
     *            determinar la empresa asociada al avaluo
     * @return identificador de la empresa asociada al avaluo.
     */
    public Long obtenerEmpresaAvaluadora(ConsultaAvaluoDto consulta) {
    	StringBuilder consultar = new StringBuilder();
    	consultar.append("SELECT p.idEmpresaAvaluo FROM PeritosEmpresa p ");
    	consultar.append("WHERE p.estadoAsociacion = 'A' ");
    	consultar.append("AND p.numeroDocumento = :usuarioCreacion ");
    	Query consultaEmpresa = mp.createQuery(consultar.toString());
    	consultaEmpresa.setParameter("usuarioCreacion", Long.parseLong(consulta.getUsuario().getUsuario().getCodigo()));
    	List<?> resultadoEmpresa = consultaEmpresa.getResultList();
    	if (resultadoEmpresa != null && !resultadoEmpresa.isEmpty())
    	    return (Long) consultaEmpresa.getResultList().get(0);
    	return null;
        }

    /**
     * Permite determinar el id del ultimo avaluo que se registro como nuevo y no ha
     * sido transmitido.
     * 
     * @return identificador del ultimo avaluo registrado que se registro como nuevo
     *         y no ha sido transmitido.
     * @throws Exception
     */
    public Long obtenerIdUltimoAvaluoRegistrado() throws Exception {
	try {
	    String query = GET_LAST_ENTRY.concat(" AND a.numLinea IS NULL").concat(" ORDER BY a.idAvaluo DESC");
	    Query consultar = mp.createQuery(query);
	    return ((Long) consultar.getSingleResult());
	} catch (Exception e) {
	    log.error(e);
	    return null;
	}
    }

    /**
     * Permite obtener los avaluos segun el id del log generado para un archivo
     * 
     * @param procedatoSeleccionado
     *            contiene el identificado de log de generacion de archivo.
     * @return retorna los avaluos asociados al archivo log.
     */
    @SuppressWarnings("unchecked")
    public List<Avaluo> obtenerAvaluosPorArchivos(LogsGeneraArchivo procedatoSeleccionado) {
	String query = SELECT.concat(generarWhereArchivo(procedatoSeleccionado)).concat(ORDER_BY);
	Query consulta = mp.createQuery(query);
	return consulta.getResultList();
    }

    /***
     * genera la cadena WHERE con las condiciones asociada a la consulta de avaluos
     * asociados a un archivo log.
     * 
     * @param procedatoSeleccionado
     *            contiene el identificado de log de generacion de archivo.
     * @return cadena Where con las condiones para la consulta JPA de avaluos
     *         asociados a un log.
     */
    private String generarWhereArchivo(LogsGeneraArchivo procedatoSeleccionado) {
	StringBuilder sb = new StringBuilder();
	boolean tieneCondiciones = false;
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.idArchivo", procedatoSeleccionado.getIdLogArchivos(), null, null, tieneCondiciones, true, false, false);
	return tieneCondiciones ? UtilConstantes.SQL_WHERE + sb.toString() : "";
    }

    /**
     * permite consultar los avlauos que estan pendientes por aplicar la marca de
     * asegurabilidad.
     * 
     * @return listado de avaluos que aun esta pendientes por el servicio de
     *         asegurabilidad.
     */
    @SuppressWarnings("unchecked")
    public List<Avaluo> obtenerAvaluosPendienteAsegurabilidad() {
	Query consultaAsegurabilidad = mp.createQuery(OBTENER_AVALUOS_ASEGURABILIDAD);
	return consultaAsegurabilidad.getResultList();

    }
    
    private String generarWhereServicioGET(ConsultaAvaluoDto consultar) {
    	if (consultar == null)
    	    return "";
    	StringBuilder sb = new StringBuilder();
    	String codigoPerfil="";
    	boolean tieneCondiciones = false;

    	if(consultar.getEstadoAvaluo() != null) {
    		tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.estadoAvaluo",consultar.getEstadoAvaluo(), null, null, tieneCondiciones,true, false, false);
    	}
    	if (!UtilTexto.estaVacio(consultar.getMatricula()))
    	    tieneCondiciones |= UtilJpa.agregarCondicionLIKE(sb, "UPPER(b.matriculaInmobiliariaPpal1)", consultar.getMatricula().toUpperCase(), tieneCondiciones, true, false,
    		    false);
    	return tieneCondiciones ? UtilConstantes.SQL_WHERE + sb.toString() : "";
    }
    
    @SuppressWarnings("unchecked")
    public List<Avaluo> consultarAvaluo(ConsultaAvaluoDto consultar, int inicio, int tamanioPagina, String campoOrden, SentidoOrientacion sentido) {
    	String query = SELECT.concat(agregarJoin(consultar)).concat(generarWhereServicioGET(consultar))
		.concat(UtilTexto.estaVacio(campoOrden) ? ORDER_BY : UtilJpa.generarOrder("a", campoOrden, sentido, JOINS));
	Query consulta = mp.createQuery(query);
	agregarParametros(consulta, consultar);
	consulta.setMaxResults(tamanioPagina);
	consulta.setFirstResult(inicio);
	return consulta.getResultList();
    } 

}
