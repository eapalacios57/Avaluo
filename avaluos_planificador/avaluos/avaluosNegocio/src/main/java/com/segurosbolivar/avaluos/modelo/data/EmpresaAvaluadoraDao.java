package com.segurosbolivar.avaluos.modelo.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.entity.EmpresasAvaluos;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.service.util.UtilJpa;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad EmpresasAvaluos. Se encarga de las
 * operaciones a base de datos de la tabla de PGB_EMPRESAS_AVALUOS a través del uso
 * del entityManager.
 * 
 * @author GeneradorCRUD
 */
@Stateless
public class EmpresaAvaluadoraDao extends ManejadorCrud<EmpresasAvaluos, Long> {

    private static final long serialVersionUID = -4302635942444663511L;
    public static final String SELECT = "SELECT a FROM EmpresasAvaluos a ";
    public static final String COUNT = "SELECT COUNT( a.idEmpresaAvaluo) FROM EmpresasAvaluos a ";

    public EmpresaAvaluadoraDao() {
	super(EmpresasAvaluos.class);
    }

    /***
     * Permite determinar la cantidad de registros para la consulta a realizarse de
     * empresas, esto permitira el uso del paginador para la consulta.
     * 
     * @param consultar
     *            contiene los valores del filtro que se usaran en la consulta y
     *            para determinar la cantidad de registros.
     * @return el numero de registros que cumplen con los filtros de la consulta
     */
    public int cantidadRegistros(EmpresasAvaluos consultar) {
	Query consulta = mp.createQuery(COUNT.concat(generarWhere(consultar)));
	return ((Long) consulta.getSingleResult()).intValue();
    }

    /**
     * Realiza la consulta dinamica de las empresas avaluadoras con un orden
     * establecido por el usuario.
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
     * @return listado de empresas avaluadoras que cumplen con las condiciones de
     *         los filtros.
     */
    @SuppressWarnings("unchecked")
    public List<EmpresasAvaluos> consultar(EmpresasAvaluos consultar, int inicio, int tamanioPagina, String campoOrden, SentidoOrientacion sentido) {
	Query consulta = mp.createQuery(SELECT.concat(generarWhere(consultar)).concat(UtilJpa.generarOrder("a", campoOrden, sentido)));
	consulta.setMaxResults(tamanioPagina);
	consulta.setFirstResult(inicio);
	return consulta.getResultList();
    }

    /**
     * genera la cadena WHERE para la consulta dinamica de empresas
     * 
     * @param consultar
     *            contiene los valores de los filtros a aplicar
     * @return cadena WHERE con las condicones JPA asociadas a los filtros
     *         aplicados.
     */
    private String generarWhere(EmpresasAvaluos consultar) {
	if (consultar == null)
	    return "";
	StringBuilder sb = new StringBuilder();
	boolean tieneCondiciones = false;
	if (!UtilTexto.estaVacio(consultar.getDescEmpresa()))
	    tieneCondiciones |= UtilJpa.agregarCondicion(sb, "UPPER(a.descEmpresa)", consultar.getDescEmpresa().toUpperCase(), null, UtilConstantes.CR_LIKE, tieneCondiciones, true,
		    false, false);
//	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.numeroDocumento", consultar.getNumeroDocumento(), null, tieneCondiciones, true);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.numeroDocumento", consultar.getNumeroDocumento(), null,UtilConstantes.CR_LIKE ,tieneCondiciones, true,false,false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "a.idEmpresaAvaluo", consultar.getIdEmpresaAvaluo(), null, tieneCondiciones, true);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "UPPER(a.estado)", "A", null, tieneCondiciones, true);
	return tieneCondiciones ? UtilConstantes.SQL_WHERE + sb.toString() : "";
    }

}
