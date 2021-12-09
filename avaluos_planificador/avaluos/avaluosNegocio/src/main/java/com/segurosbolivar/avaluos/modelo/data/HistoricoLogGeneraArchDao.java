package com.segurosbolivar.avaluos.modelo.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.entity.HistoricoLogsGeneraArch;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.service.util.UtilJpa;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad HistoricoLogsGeneraArch. Se encarga de las
 * operaciones a base de datos de la tabla de PGB_HIST_LOGS_GENERA_ARCH a través
 * del uso del entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class HistoricoLogGeneraArchDao extends ManejadorCrud<HistoricoLogsGeneraArch, Long> {

    private static final long serialVersionUID = -2036641354843464248L;
    public static final String SELECT = "SELECT c FROM HistoricoLogsGeneraArch c ";

    public HistoricoLogGeneraArchDao() {
	super(HistoricoLogsGeneraArch.class);
    }

    /**
     * Realiza la consulta dinamica del historico del log de archivos generados con
     * un orden establecido por el usuario.
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
     * @return listado del historico del log de archivos generados que cumplen con
     *         las condiciones de los filtros.
     */
    @SuppressWarnings("unchecked")
    public List<HistoricoLogsGeneraArch> consultar(HistoricoLogsGeneraArch historicoLogsGeneraArch, int inicio, int tamanioPagina, String campoOrden, SentidoOrientacion sentido) {
	Query consulta = mp.createQuery(SELECT.concat(generarWhere(historicoLogsGeneraArch)).concat(UtilJpa.generarOrder("c", campoOrden, sentido)));
	consulta.setMaxResults(tamanioPagina);
	consulta.setFirstResult(inicio);
	return consulta.getResultList();
    }

    /**
     * genera la cadena WHERE para la consulta dinamica del historico del log de
     * archivos generados
     * 
     * @param consultar
     *            contiene los valores de los filtros a aplicar
     * @return cadena WHERE con las condicones JPA asociadas a los filtros
     *         aplicados.
     */
    private String generarWhere(HistoricoLogsGeneraArch consultar) {
	if (consultar == null)
	    return "";
	StringBuilder sb = new StringBuilder();
	boolean tieneCondiciones = false;
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "c.idLogArchivos", consultar.getIdLogArchivos(), null, tieneCondiciones, true);
	return tieneCondiciones ? UtilConstantes.SQL_WHERE + sb.toString() : "";
    }

    /***
     * Permite determinar la cantidad de registros para la consulta a realizarse del
     * historico del log de archivos generados, esto permitira el uso del paginador
     * para la consulta.
     * 
     * @param consultar
     *            contiene los valores del filtro que se usaran en la consulta y
     *            para determinar la cantidad de registros.
     * @return el numero de registros que cumplen con los filtros de la consulta
     */
    public int cantidadRegistros(HistoricoLogsGeneraArch consultar) {
	Query consulta = mp.createNamedQuery(HistoricoLogsGeneraArch.class.getSimpleName() + ".cantidadValores");
	return ((Long) consulta.getSingleResult()).intValue();
    }

}
