package com.segurosbolivar.avaluos.modelo.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.entity.HorasCorteArchivo;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.modelo.service.util.UtilJpa;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad Lista valores. Se encarga de las
 * operaciones a base de datos de la tabla de CG_REF_CODES a través del uso del
 * entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class HorasCorteDao extends ManejadorCrud<HorasCorteArchivo, Long> {

    private static final long serialVersionUID = 2784020081044152674L;
    public static final String SELECT = "SELECT p FROM HorasCorteArchivo p ";

    /**
     * Constructor que permite crear el manejador especifico para la entidad horas
     * corte.
     */
    public HorasCorteDao() {
	super(HorasCorteArchivo.class);
    }

    /**
     * Permite consultar las horas de corte del sistema aplicando un filtro segun
     * los campos de la tabla horas corte.
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
     * @return lsitado de horas de corte que cumplen con el filtro.
     */
    @SuppressWarnings("unchecked")
    public List<HorasCorteArchivo> consultar(HorasCorteArchivo consultar, int inicio, int tamanioPagina, String campoOrden, SentidoOrientacion sentido) {
	Query consulta = mp.createQuery(SELECT.concat(UtilJpa.generarOrder("p", campoOrden, sentido)));
	consulta.setMaxResults(tamanioPagina);
	consulta.setFirstResult(inicio);
	return consulta.getResultList();
    }

    /***
     * Permite determinar la cantidad de registros para la consulta a realizarse de
     * horas de corte, esto permitira el uso del paginador para la consulta.
     * 
     * @param consultar
     *            contiene los valores del filtro que se usaran en la consulta y
     *            para determinar la cantidad de registros.
     * @return el numero de registros que cumplen con los filtros de la consulta
     */
    public int cantidadRegistros(HorasCorteArchivo consultar) {
	return mp.getCount(HorasCorteArchivo.class);
    }

}
