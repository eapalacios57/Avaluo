package com.segurosbolivar.avaluos.modelo.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.entity.Ciudad;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.service.util.UtilJpa;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad Ciudades. Se encarga de las operaciones a
 * base de datos de la tabla de PGB_CIUDADES a través del uso del entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class CiudadDao extends ManejadorCrud<Ciudad, Long> {

    private static final long serialVersionUID = 2735238725089781986L;
    public static final String SELECT = "SELECT c FROM Ciudad c ";
    public static final String COUNT = "SELECT COUNT( c.idCiudad) FROM Ciudad c ";

    public CiudadDao() {
	super(Ciudad.class);
    }

    /**
     * Realiza la consulta dinamica de las ciuades con un orden establecido por el
     * usuario.
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
     * @return listado de ciudades que cumplen con las condiciones de los filtros.
     */
    @SuppressWarnings("unchecked")
    public List<Ciudad> consultar(Ciudad ciudad, int inicio, int tamanioPagina, String campoOrden, SentidoOrientacion sentido) {
	Query consulta = mp.createQuery(SELECT.concat(generarWhere(ciudad)).concat(UtilJpa.generarOrder("c", campoOrden, sentido)));
	consulta.setMaxResults(tamanioPagina);
	consulta.setFirstResult(inicio);
	return consulta.getResultList();
    }

    /**
     * Realiza la consulta dinamica de las ciuades. con un orden establecido por El
     * departamento, la capital y luego el nombre de la ciudad, esta consulta se usa
     * especificamente para la cargua de listado de ciudades como un dominio
     * seleccionable
     * 
     * @param consultar
     *            contiene los valores de los filtros a aplicar en la consulta
     * @param inicio
     *            determinar el registro incial para el paginador desde donde se
     *            inicia la consulta
     * @param tamanioPagina
     *            determina la cantidad de registros a consultar
     * @return listado de ciudades que cumplen con las condiciones de los filtros.
     */
    @SuppressWarnings("unchecked")
    public List<Ciudad> consultar(Ciudad ciudad, int inicio, int tamanioPagina) {
	Query consulta = mp.createQuery(SELECT.concat(generarWhere(ciudad)).concat("ORDER BY c.idDepartamento, c.capital, c.ciudad"));
	consulta.setMaxResults(tamanioPagina);
	consulta.setFirstResult(inicio);
	return consulta.getResultList();
    }

    /***
     * Permite determinar la cantidad de registros para la consulta a realizarse de
     * ciudades, esto permitira el uso del paginador para la consulta.
     * 
     * @param consultar
     *            contiene los valores del filtro que se usaran en la consulta y
     *            para determinar la cantidad de registros.
     * @return el numero de registros que cumplen con los filtros de la consulta
     */
    public int cantidadRegistros(Ciudad consultar) {
	Query consulta = mp.createQuery(COUNT.concat(generarWhere(consultar)));
	return ((Long) consulta.getSingleResult()).intValue();
    }

    /**
     * genera la cadena WHERE para la consulta dinamica de los ciudades
     * 
     * @param consultar
     *            contiene los valores de los filtros a aplicar
     * @return cadena WHERE con las condicones JPA asociadas a los filtros
     *         aplicados.
     */
    private String generarWhere(Ciudad consultar) {
	if (consultar == null)
	    return "";
	StringBuilder sb = new StringBuilder();
	boolean tieneCondiciones = false;
	if (!UtilTexto.estaVacio(consultar.getCiudad()))
	    tieneCondiciones |= UtilJpa.agregarCondicion(sb, "UPPER(c.ciudad)", consultar.getCiudad().toUpperCase(), null, UtilConstantes.CR_LIKE, tieneCondiciones,true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "c.idDepartamento", consultar.getDepartamento() != null ? consultar.getDepartamento().getIdDepartamento() : null, null,tieneCondiciones, true);
	
	return tieneCondiciones ? UtilConstantes.SQL_WHERE + sb.toString() : "";
    }

    /**
     * Permite consultar una ciudad segun el codigo de asobancaria.
     * 
     * @param codAsobancaria
     *            a consultar
     * @return la ciudad que corresponde al codigo enviado.
     */
    public Ciudad buscarPorAsobancaria(long codAsobancaria) {
	Query query = mp.createNamedQuery(Ciudad.class.getSimpleName() + ".buscarPorAsobancaria");
	query.setParameter("codAsobancaria", codAsobancaria);
	return (Ciudad) query.getSingleResult();
    }

}
