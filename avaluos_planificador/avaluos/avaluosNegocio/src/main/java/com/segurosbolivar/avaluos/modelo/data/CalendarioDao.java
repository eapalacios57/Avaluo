package com.segurosbolivar.avaluos.modelo.data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.eclipse.persistence.sessions.DatabaseRecord;

import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.entity.Calendario;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.service.util.UtilJpa;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad Calendario. Se encarga de las operaciones
 * a base de datos de la tabla de PGB_CALENDARIOS a través del uso del
 * entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class CalendarioDao extends ManejadorCrud<Calendario, BigDecimal> {

    private static final long serialVersionUID = 3400333940016484989L;
    private static final Logger log = Logger.getLogger(CalendarioDao.class);
    public static final String SELECT = "SELECT c FROM Calendario c ";
    public static final String COUNT = "SELECT COUNT( c.idCalendario) FROM Calendario c ";
    public static final String ORDER_BY = " ORDER BY c.anio DESC, c.fechaNoHabil ";

    public CalendarioDao() {
	super(Calendario.class);
    }

    /**
     * Realiza la consulta dinamica de las fechas no habiles en la tabla calendarios
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
     * @return listado de fechas no habiles que cumplen con las condiciones de los
     *         filtros.
     */
    @SuppressWarnings("unchecked")
    public List<Calendario> consultar(Calendario consultar, int inicio, int tamanioPagina, String campoOrden, SentidoOrientacion sentido) {
	String query = SELECT.concat(generarWhere(consultar)).concat(UtilTexto.estaVacio(campoOrden) ? ORDER_BY : UtilJpa.generarOrder("c", campoOrden, sentido));
	Query consulta = mp.createQuery(query);
	agregarParametros(consulta, consultar);
	consulta.setMaxResults(tamanioPagina);
	consulta.setFirstResult(inicio);
	return consulta.getResultList();
    }

    /**
     * Permite agregar los parametros a la consulta JPA de las fechas no habiles
     * 
     * @param consulta
     *            JPA a la cual se agregaran los parametros.
     * @param consultar
     *            contiene los filtros que se aplciaran como parametros.
     */
    protected void agregarParametros(Query consulta, Calendario consultar) {
	UtilJpa.agregarParametros(consulta, "fechaNoHabil", consultar.getFechaNoHabil());
    }

    /**
     * genera la cadena WHERE para la consulta dinamica de las fechas no habiles
     * 
     * @param consultar
     *            contiene los valores de los filtros a aplicar
     * @return cadena WHERE con las condicones JPA asociadas a los filtros
     *         aplicados.
     */
    protected String generarWhere(Calendario consultar) {
	if (consultar == null)
	    return "";
	StringBuilder sb = new StringBuilder();
	boolean tieneCondiciones = false;
	if (consultar.getFechaNoHabil() != null)
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "c.fechaNoHabil", "fechaNoHabil", UtilConstantes.EX_IN, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "c.anio", consultar.getAnio(), null, null, tieneCondiciones, true, false, false);
	return tieneCondiciones ? UtilConstantes.SQL_WHERE + sb.toString() : "";
    }

    /**
     * permite consultar los años en los que existen fechas asociadas al calendario.
     * 
     * @return listado de años a los cuales se registro al menos una fecha no habil.
     */
    @SuppressWarnings("unchecked")
    public List<Long> consultarAnios() {
	Query consulta = mp.createNamedQuery(Calendario.class.getSimpleName() + ".anios");
	return consulta.getResultList();
    }

    /***
     * Permite determinar la cantidad de registros para la consulta a realizarse de
     * fechas no habiles, esto permitira el uso del paginador para la consulta.
     * 
     * @param consultar
     *            contiene los valores del filtro que se usaran en la consulta y
     *            para determinar la cantidad de registros.
     * @return el numero de registros que cumplen con los filtros de la consulta
     */
    public int cantidadRegistros(Calendario consultar) {
	String query = COUNT.concat(generarWhere(consultar));
	log.info(query);
	Query consulta = mp.createQuery(query);
	agregarParametros(consulta, consultar);
	return ((Long) consulta.getSingleResult()).intValue();
    }

    /**
     * Realiza la invocacion de procedimiento almacenado sp_carga_sabado_domingo el
     * cual realiza la carga de las fechas no habiles basicas(fines de semana) para
     * un año determinado
     * 
     * @param anio
     *            al que realizaremos el proceso de carga de las fechas no habiles.
     * @param usuario
     *            que realiza el proceso de carga de las fechas no habiles.
     * @throws NegocioException
     *             en caso de inconveniente con la base de datos y la invocacion del
     *             procedimiento almacenado.
     */
    public void cargarAnio(Integer anio, String usuario) throws NegocioException {
	DatabaseRecord respuesta = mp.ejecutarPrcPLSQL("Pkg_General_avaluos.sp_carga_sabado_domingo", Arrays.asList("panio", "pusuario"), Collections.singletonList("pmensaje"),
		Arrays.asList(anio, usuario));
	String mensaje = (String) respuesta.get("pmensaje");
	if (!UtilTexto.estaVacio(mensaje))
	    throw new NegocioException(mensaje);
    }

}
