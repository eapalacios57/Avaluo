package com.segurosbolivar.avaluos.modelo.data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.entity.LogsGeneraArchivo;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.service.util.UtilJpa;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad LogsGeneraArchivo. Se encarga de las
 * operaciones a base de datos de la tabla de PGB_LOGS_GENERA_ARCHIVOS a través
 * del uso del entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class LogsGeneraArchivoDao extends ManejadorCrud<LogsGeneraArchivo, Long> {

    private static final long serialVersionUID = 2935532024177699190L;
    public static final String SELECT = "SELECT a FROM LogsGeneraArchivo a ";
    public static final String COUNT = "SELECT COUNT( a.idLogArchivos) FROM LogsGeneraArchivo a ";
    public static final String COUNT_PROCESO_ACTIVO = " SELECT COUNT(a.id_generacion_plano) FROM pgb_bloq_logs_genera_archivos a "
	    + " WHERE a.fecha_fin_creacion IS NULL AND a.estado_registro = 'A' AND TRUNC(a.fecha_inicio_creacion) > TRUNC(SYSDATE)-1";

    public LogsGeneraArchivoDao() {
	super(LogsGeneraArchivo.class);
    }

    /**
     * Realiza la consulta dinamica del log de archivos generados por el cargue
     * masivo con un orden establecido por el usuario.
     * 
     * @param logsGeneraArchivoDTO
     *            contiene los valores de los filtros a aplicar en la consulta
     * @param inicio
     *            determinar el registro incial para el paginador desde donde se
     *            inicia la consulta
     * @param tamanioPagina
     *            determina la cantidad de registros a consultar
     * @param campoOrden
     *            establece el campo por el cual se ordenara la consulta.
     * @param sentido
     *            determinar el sentido de ordenamiento para el campo por el cual se
     *            establece la consulta.
     * @return listado de ciudades que cumplen con las condiciones de los filtros.
     */
    @SuppressWarnings("unchecked")
    public List<LogsGeneraArchivo> consultar(LogsGeneraArchivo logsGeneraArchivoDTO, int inicio, int tamanioPagina, String campoOrden, SentidoOrientacion sentido) {
	String query = SELECT.concat(generarWhere(logsGeneraArchivoDTO))
		.concat(UtilTexto.estaVacio(campoOrden) ? "ORDER BY a.idLogArchivos" : UtilJpa.generarOrder("a", campoOrden, sentido));
	Query consulta = mp.createQuery(query);
	agregarParametros(consulta, logsGeneraArchivoDTO);
	consulta.setMaxResults(tamanioPagina);
	consulta.setFirstResult(inicio);
	List<LogsGeneraArchivo> res=consulta.getResultList();
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
    private void agregarParametros(Query consulta, LogsGeneraArchivo logsGeneraArchivoDTO) {
	UtilJpa.agregarParametros(consulta, "fechaDesde", logsGeneraArchivoDTO.getFechaDesde());
	UtilJpa.agregarParametros(consulta, "fechaHasta", logsGeneraArchivoDTO.getFechaHasta());
	UtilJpa.agregarParametros(consulta, "fechaInicioCreacion", logsGeneraArchivoDTO.getFechaInicioCreacion());
	UtilJpa.agregarParametros(consulta, "fechaFinCreacion", logsGeneraArchivoDTO.getFechaFinCreacion());
	UtilJpa.agregarParametros(consulta, "fechaTransaccion", logsGeneraArchivoDTO.getFechaTransaccion());
    }

    /***
     * Permite determinar la cantidad de registros para la consulta a realizarse,,
     * esto permitira el uso del paginador para la consulta.
     * 
     * @param consultar
     *            contiene los valores del filtro que se usaran en la consulta y
     *            para determinar la cantidad de registros.
     * @return el numero de registros que cumplen con los filtros de la consulta
     */
    public int cantidadRegistros(LogsGeneraArchivo logsGeneraArchivoDTO) {
	String query = COUNT.concat(generarWhere(logsGeneraArchivoDTO));
	Query consulta = mp.createQuery(query);
	agregarParametros(consulta, logsGeneraArchivoDTO);
	return ((Long) consulta.getSingleResult()).intValue();
    }

    /**
     * genera la cadena WHERE para la consulta dinamica
     * 
     * @param consultar
     *            contiene los valores de los filtros a aplicar
     * @return cadena WHERE con las condicones JPA asociadas a los filtros
     *         aplicados.
     */
    private String generarWhere(LogsGeneraArchivo consultar) {
	if (consultar == null)
	    return "";
	StringBuilder sb = new StringBuilder();
	boolean tieneCondiciones = false;
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.codigoError", consultar.getCodigoError(), null, null, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.descripcionError", consultar.getDescripcionError(), null, null, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.enviado", consultar.getEnviado(), null, null, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.estado", consultar.getEstado(), null, null, tieneCondiciones, true, false, false);
	if (consultar.getFechaDesde() != null) {
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "a.fechaDesde", "fechaDesde", UtilConstantes.CR_GREATER_EQUAL, tieneCondiciones, true, false, false);
	}
	if (consultar.getFechaHasta() != null) {
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "a.fechaHasta", "fechaHasta", UtilConstantes.CR_LESS_EQUAL, tieneCondiciones, true, false, false);
	}
	if (consultar.getFechaInicioCreacion() != null) {
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "a.fechaInicioCreacion", "fechaInicioCreacion", UtilConstantes.CR_GREATER_EQUAL, tieneCondiciones, true,
		    false, false);
	}
	if (consultar.getFechaFinCreacion() != null) {
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "a.fechaFinCreacion", "fechaFinCreacion", UtilConstantes.CR_LESS_EQUAL, tieneCondiciones, true, false, false);
	}
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.linkDescarga", consultar.getLinkDescarga(), null, null, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.nombreArchivo", consultar.getNombreArchivo(), null, null, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.usuarioCreacion", consultar.getUsuarioCreacion(), null, null, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.nombrePlano", consultar.getNombrePlano(), null, null, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.usuarioTransaccion", consultar.getUsuarioTransaccion(), null, null, tieneCondiciones, true, false, false);
	if (consultar.getFechaTransaccion() != null) {
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "a.fechaTransaccion", "fechaTransaccion", UtilConstantes.CR_LESS_EQUAL, tieneCondiciones, true, false, false);
	}
	return tieneCondiciones ? UtilConstantes.SQL_WHERE + sb.toString() : "";
    }

    /***
     * Permite consultar e log de generacion de archivo del proceso de cargue
     * masivio desde una fecha determinada.
     * 
     * @param fechaActual
     *            a verificar
     * @return listado de logs que cumplen con ser menores o iguales que la fecha
     *         dada
     */
    @SuppressWarnings("unchecked")
    public List<LogsGeneraArchivo> consultarPorRangoFechas(Date fechaActual) {
	String query = SELECT.concat(generarWhereFecha()).concat("ORDER BY a.idLogArchivos");
	Query consulta = mp.createQuery(query);
	UtilJpa.agregarParametros(consulta, "fechaActual", fechaActual);
	return consulta.getResultList();
    }

    /**
     * Genera la cadena WHERE para la consulta por rango de fechas de los logs de
     * archivos cargados para el proceso de cargue masivo.
     * 
     * @return cadena WHERE para la consulta por rango de fechas de los logs de
     *         archivos cargados para el proceso de cargue masivo.
     */
    private String generarWhereFecha() {
	StringBuilder sb = new StringBuilder();
	boolean tieneCondiciones = false;
	tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "a.fechaTransaccion", "fechaActual", UtilConstantes.CR_LESS_EQUAL, tieneCondiciones, true, false, false);
	return tieneCondiciones ? UtilConstantes.SQL_WHERE + sb.toString() : "";
    }

    /**
     * determinar si existen procesos de generacion de cargues masivos activos en el
     * momento.
     * 
     * @return verdadero si existen procesos activos.
     */
    public boolean procesoGeneracionActivo() {
	Query consulta = mp.nativeQuery(COUNT_PROCESO_ACTIVO);
	BigDecimal cantidadProcesos = (BigDecimal) consulta.getSingleResult();
	return cantidadProcesos.compareTo(BigDecimal.ZERO) > 0;
    }

}
