package com.segurosbolivar.avaluos.modelo.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.entity.ComplementosExcel;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.service.util.UtilJpa;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad ComplementosExcel. Se encarga de las
 * operaciones a base de datos de la tabla de PGB_COMPLEMENTOS_EXCEL a través
 * del uso del entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class ComplementosExcelDao extends ManejadorCrud<ComplementosExcel, Long> {

    private static final long serialVersionUID = -3542967427455082070L;
    private static final Logger log = Logger.getLogger(ComplementosExcelDao.class);
    public static final String SELECT = "SELECT a FROM ComplementosExcel a ";
    public static final String COUNT = "SELECT COUNT( a.idComplementoExcel) FROM ComplementosExcel a ";
    public static final String ORDER_BY = " ORDER BY a.idComplementoExcel";

    public ComplementosExcelDao() {
	super(ComplementosExcel.class);
    }

    /**
     * Realiza la consulta dinamica de las complementos con un orden establecido por
     * el usuario.
     * 
     * @param consultar
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
     * @return listado de complementos que cumplen con las condiciones de los
     *         filtros.
     */
    @SuppressWarnings("unchecked")
    public List<ComplementosExcel> consultar(ComplementosExcel consultar, int inicio, int tamanioPagina, String campoOrden, SentidoOrientacion sentido) {
	String query = SELECT.concat(generarWhere(consultar)).concat(UtilTexto.estaVacio(campoOrden) ? ORDER_BY : UtilJpa.generarOrder("a", campoOrden, sentido));
	log.info(query);
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
    protected void agregarParametros(Query consulta, ComplementosExcel complementosExcel) {
	UtilJpa.agregarParametros(consulta, "fechaCreacion", complementosExcel.getFechaCreacion());
	UtilJpa.agregarParametros(consulta, "fechaTransaccion", complementosExcel.getFechaTransaccion());
    }

    /***
     * Permite determinar la cantidad de registros para la consulta a realizarse de
     * complementos, esto permitira el uso del paginador para la consulta.
     * 
     * @param consultar
     *            contiene los valores del filtro que se usaran en la consulta y
     *            para determinar la cantidad de registros.
     * @return el numero de registros que cumplen con los filtros de la consulta
     */
    public int cantidadRegistros(ComplementosExcel consultar) {
	String query = COUNT.concat(generarWhere(consultar));
	log.info(query);
	Query consulta = mp.createQuery(query);
	agregarParametros(consulta, consultar);
	return ((Long) consulta.getSingleResult()).intValue();
    }

    /**
     * genera la cadena WHERE para la consulta dinamica de los complementos
     * 
     * @param consultar
     *            contiene los valores de los filtros a aplicar
     * @return cadena WHERE con las condicones JPA asociadas a los filtros
     *         aplicados.
     */
    private String generarWhere(ComplementosExcel complementosExcel) {
	if (complementosExcel == null)
	    return "";
	StringBuilder sb = new StringBuilder();
	boolean tieneCondiciones = false;
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.usuarioCreacion", complementosExcel.getUsuarioCreacion(), null, null, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.usuarioTransaccion", complementosExcel.getUsuarioTransaccion(), null, null, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.descripcionComplemento", complementosExcel.getDescripcionComplemento(), null, null, tieneCondiciones, true, false,
		false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.textoLibre", complementosExcel.getTextoLibre(), null, null, tieneCondiciones, true, false, false);
	if (complementosExcel.getFechaCreacion() != null)
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "a.fechaCreacion", "fechaCreacion", UtilConstantes.CR_GREATER_EQUAL, tieneCondiciones, true, false, false);
	if (complementosExcel.getFechaTransaccion() != null)
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "a.fechaTransaccion", "fechaTransaccion", UtilConstantes.CR_LESS_EQUAL, tieneCondiciones, true, false, false);
	return tieneCondiciones ? UtilConstantes.SQL_WHERE + sb.toString() : "";
    }

}
