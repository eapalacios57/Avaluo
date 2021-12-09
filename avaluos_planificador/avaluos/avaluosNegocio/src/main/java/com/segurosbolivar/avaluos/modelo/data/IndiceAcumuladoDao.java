package com.segurosbolivar.avaluos.modelo.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.entity.IndiceAcumulado;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.service.util.UtilJpa;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad IndiceAcumulado. Se encarga de las
 * operaciones a base de datos de la tabla de PGB_INDICE_ACUMULADO a través del
 * uso del entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class IndiceAcumuladoDao extends ManejadorCrud<IndiceAcumulado, Long> {

    private static final long serialVersionUID = -2036641354843464248L;
    public static final String SELECT = "SELECT c FROM IndiceAcumulado c ";

    public IndiceAcumuladoDao() {
	super(IndiceAcumulado.class);
    }

    /**
     * Realiza la consulta dinamica de los indices acumulados con un orden
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
     * @return listado de indices acumulados que cumplen con las condiciones de los
     *         filtros.
     */
    @SuppressWarnings("unchecked")
    public List<IndiceAcumulado> consultar(IndiceAcumulado indiceAcumulado, int inicio, int tamanioPagina, String campoOrden, SentidoOrientacion sentido) {
	Query consulta = mp.createQuery(SELECT.concat(generarWhere(indiceAcumulado)).concat(UtilJpa.generarOrder("c", campoOrden, sentido)));
	consulta.setMaxResults(tamanioPagina);
	consulta.setFirstResult(inicio);
	return consulta.getResultList();
    }

    /**
     * genera la cadena WHERE para la consulta dinamica de los indices acumulados
     * 
     * @param consultar
     *            contiene los valores de los filtros a aplicar
     * @return cadena WHERE con las condicones JPA asociadas a los filtros
     *         aplicados.
     */
    private String generarWhere(IndiceAcumulado consultar) {
	if (consultar == null)
	    return "";
	StringBuilder sb = new StringBuilder();
	boolean tieneCondiciones = false;
//	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "c.idDepartamento", consultar.getIdDepartamento() != null ? consultar.getIdDepartamento() : null, null, tieneCondiciones,true);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "c.idDepartamento", consultar.getIdDepartamento() != null ? consultar.getIdDepartamento() : null, null, UtilConstantes.CR_LIKE,tieneCondiciones,true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "c.idCiudad", consultar.getIdCiudad() != null ? consultar.getIdCiudad() : null, null, tieneCondiciones, true);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "c.estrato", consultar.getEstrato() != null ? consultar.getEstrato() : null, null, tieneCondiciones, true);
//	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "c.anio", consultar.getAnio() != null ? consultar.getAnio() : null, null, tieneCondiciones, true);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "c.anio", consultar.getAnio() != null ? consultar.getAnio() : null, null, UtilConstantes.CR_LIKE,tieneCondiciones, true, false, false);
	if(consultar.getCiudad() != null && !UtilTexto.estaVacio(consultar.getCiudad().getCiudad())) {
		tieneCondiciones |= UtilJpa.agregarCondicion(sb, "UPPER(c.ciudad.ciudad)", consultar.getCiudad().getCiudad().toUpperCase(), null, UtilConstantes.CR_LIKE, tieneCondiciones, true, false, false);
	}
	if(consultar.getDepartamento() != null && !UtilTexto.estaVacio(consultar.getDepartamento().getDepartamento())) {
		tieneCondiciones |= UtilJpa.agregarCondicion(sb, "UPPER(c.departamento.departamento)", consultar.getDepartamento().getDepartamento().toUpperCase(), null, UtilConstantes.CR_LIKE, tieneCondiciones, true, false, false);
	}
	return tieneCondiciones ? UtilConstantes.SQL_WHERE + sb.toString() : "";
    }

    /***
     * Permite determinar la cantidad de registros para la consulta a realizarse de
     * indices acumulados, esto permitira el uso del paginador para la consulta.
     * 
     * @param consultar
     *            contiene los valores del filtro que se usaran en la consulta y
     *            para determinar la cantidad de registros.
     * @return el numero de registros que cumplen con los filtros de la consulta
     */
    public int cantidadRegistros(IndiceAcumulado consultar) {
	Query consulta = mp.createNamedQuery(IndiceAcumulado.class.getSimpleName() + ".cantidadValores");
	return ((Long) consulta.getSingleResult()).intValue();
    }

}
