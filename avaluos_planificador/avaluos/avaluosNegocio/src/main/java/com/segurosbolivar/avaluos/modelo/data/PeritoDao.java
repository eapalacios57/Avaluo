package com.segurosbolivar.avaluos.modelo.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.entity.PeritosEmpresa;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.service.util.UtilJpa;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad peritos. Se encarga de las operaciones a
 * base de datos de la tabla de PGB_PERITOS_EMPRESAS a través del uso del
 * entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class PeritoDao extends ManejadorCrud<PeritosEmpresa, Long> {

    private static final long serialVersionUID = 7747626177696187846L;
    public static final String SELECT = "SELECT a FROM PeritosEmpresa a ";
    public static final String COUNT = "SELECT COUNT( a.idPeritoEmpresa) FROM PeritosEmpresa a ";

    public PeritoDao() {
	super(PeritosEmpresa.class);
    }

    /**
     * Realiza la consulta dinamica de los peritos con un orden establecido por el
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
     * @return listado de peritos que cumplen con las condiciones de los filtros.
     */
    @SuppressWarnings("unchecked")
    public List<PeritosEmpresa> consultar(PeritosEmpresa consultar, int inicio, int tamanioPagina, String campoOrden, SentidoOrientacion sentido) {
	Query consulta = mp.createQuery(SELECT.concat(generarWhere(consultar)).concat(UtilJpa.generarOrder("a", campoOrden, sentido)));
	consulta.setMaxResults(tamanioPagina);
	consulta.setFirstResult(inicio);
	return consulta.getResultList();
    }

    /***
     * Permite determinar la cantidad de registros para la consulta a realizarse de
     * periitos, esto permitira el uso del paginador para la consulta.
     * 
     * @param consultar
     *            contiene los valores del filtro que se usaran en la consulta y
     *            para determinar la cantidad de registros.
     * @return el numero de registros que cumplen con los filtros de la consulta
     */
    public int cantidadRegistros(PeritosEmpresa consultar) {
	Query consulta = mp.createQuery(COUNT.concat(generarWhere(consultar)));
	return ((Long) consulta.getSingleResult()).intValue();
    }

    /**
     * genera la cadena WHERE para la consulta dinamica de peritos
     * 
     * @param consultar
     *            contiene los valores de los filtros a aplicar
     * @return cadena WHERE con las condicones JPA asociadas a los filtros
     *         aplicados.
     */
    private String generarWhere(PeritosEmpresa consultar) {
	if (consultar == null)
	    return "";
	StringBuilder sb = new StringBuilder();
	boolean tieneCondiciones = false;
	if (!UtilTexto.estaVacio(consultar.getNombrePerito()))
	    tieneCondiciones |= UtilJpa.agregarCondicion(sb, "UPPER(a.nombrePerito)", consultar.getNombrePerito().toUpperCase(), null, UtilConstantes.CR_LIKE, tieneCondiciones,
		    true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.numeroDocumento", consultar.getNumeroDocumento(), null, UtilConstantes.CR_LIKE, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.idEmpresaAvaluo", consultar.getIdEmpresaAvaluo(), null, tieneCondiciones, true);
	if (consultar.getEmpresasAvaluo() != null)
	    tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.idEmpresaAvaluo", consultar.getEmpresasAvaluo().getIdEmpresaAvaluo(), null, tieneCondiciones, true);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.estadoAsociacion", consultar.getEstadoAsociacion(), null, tieneCondiciones, true);
	return tieneCondiciones ? UtilConstantes.SQL_WHERE + sb.toString() : "";
    }
}
