package com.segurosbolivar.avaluos.modelo.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.entity.Dominios;
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
 * 
 */
@Stateless
public class DominioDao extends ManejadorCrud<Dominios, Long> {

    private static final long serialVersionUID = -654829067575404354L;
    private static final String SELECT_DOMINIO = "SELECT DISTINCT d.rvDomain FROM Dominios d ";
    private static final String SELECT_VALORES_DOMINIO = "SELECT d FROM Dominios d WHERE d.rvDomain =:nombreDominio ";
    private static final String SELECT_DOMINIO_FILTRO = "SELECT d FROM Dominios d ";
    private static final String COUNT_DOMINIO = "SELECT COUNT( DISTINCT d.rvDomain) FROM Dominios d ";
    private static final String WHERE_DOMINIO = "WHERE d.rvCreateBy <> \"SEGURIDAD\" ";

    public DominioDao() {
	super(Dominios.class);
    }

    /**
     * Realiza la consulta dinamica de los dominios con un orden establecido por el
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
     * @return listado de dominios que cumplen con las condiciones de los filtros.
     */
    @SuppressWarnings("unchecked")
    public List<String> consultarDominios(Dominios consultar, int inicio, int tamanioPagina, String campoOrden, SentidoOrientacion sentido) {
	Query consulta = mp.createQuery(SELECT_DOMINIO.concat(generarWhere(consultar)).concat(UtilJpa.generarOrder("d", campoOrden, sentido)));
	consulta.setMaxResults(tamanioPagina);
	consulta.setFirstResult(inicio);
	return consulta.getResultList();
    }

    /**
     * genera la cadena WHERE para la consulta dinamica de los dominios
     * 
     * @param consultar
     *            contiene los valores de los filtros a aplicar
     * @return cadena WHERE con las condicones JPA asociadas a los filtros
     *         aplicados.
     */
    private String generarWhere(Dominios consultar) {
	StringBuilder sb = new StringBuilder(WHERE_DOMINIO);
	if (consultar == null)
	    return sb.toString();
	boolean tieneCondiciones = true;
	tieneCondiciones |= UtilJpa.agregarCondicionLIKE(sb, "upper(d.rvDomain)", consultar.getRvDomain() != null ? consultar.getRvDomain().toUpperCase() : null, tieneCondiciones,
		true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicionLIKE(sb, "d.rvMeaning", consultar.getRvMeaning() != null ? consultar.getRvMeaning() : null, tieneCondiciones,true, false, false);
	return sb.toString();
    }

    /***
     * Permite determinar la cantidad de registros para la consulta a realizarse de
     * dominios, esto permitira el uso del paginador para la consulta.
     * 
     * @param consultar
     *            contiene los valores del filtro que se usaran en la consulta y
     *            para determinar la cantidad de registros.
     * @return el numero de registros que cumplen con los filtros de la consulta
     */
    public int cantidadRegistros(Dominios consultar) {
	String query = COUNT_DOMINIO.concat(generarWhere(consultar));
	Query consulta = mp.createQuery(query);
	return ((Long) consulta.getSingleResult()).intValue();
    }

    /**
     * Realiza la consulta dinamica de los valores de dominio con un orden
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
     * @return listado de valores de dominio que cumplen con las condiciones de los
     *         filtros.
     */
    @SuppressWarnings("unchecked")
    public List<Dominios> consultarValoresDominiosFiltro(Dominios filtro, int inicio, int tamanioPagina, String campoOrden, SentidoOrientacion sentido) {
	Query consulta = mp.createQuery(SELECT_DOMINIO_FILTRO.concat(generarWhere(filtro)).concat(UtilJpa.generarOrder("d", campoOrden, sentido)));
	consulta.setMaxResults(tamanioPagina);
	consulta.setFirstResult(inicio);
	return consulta.getResultList();
    }
    
    /**
     * Realiza la consulta dinamica de los valores de dominio con un orden
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
     * @return listado de valores de dominio que cumplen con las condiciones de los
     *         filtros.
     */
    @SuppressWarnings("unchecked")
    public List<Dominios> consultarValoresDominios(String dominio, int inicio, int tamanioPagina, String campoOrden, SentidoOrientacion sentido) {
	Query consulta = mp.createQuery(SELECT_VALORES_DOMINIO.concat(UtilJpa.generarOrder("d", campoOrden, sentido)));
	consulta.setParameter("nombreDominio", dominio);
	consulta.setMaxResults(tamanioPagina);
	consulta.setFirstResult(inicio);
	return consulta.getResultList();
    }

    /***
     * Permite determinar la cantidad de registros para la consulta a realizarse de
     * valores de dominio, esto permitira el uso del paginador para la consulta.
     * 
     * @param consultar
     *            contiene los valores del filtro que se usaran en la consulta y
     *            para determinar la cantidad de registros.
     * @return el numero de registros que cumplen con los filtros de la consulta
     */
    public int cantidadRegistrosValores(String dominio) {
	Query consulta = mp.createNamedQuery(Dominios.class.getSimpleName() + ".cantidadValores");
	consulta.setParameter("nombreDominio", dominio);
	return ((Long) consulta.getSingleResult()).intValue();
    }
    
    /***
     * Permite determinar la cantidad de registros para la consulta a realizarse de
     * valores de dominio, esto permitira el uso del paginador para la consulta.
     * 
     * @param consultar
     *            contiene los valores del filtro que se usaran en la consulta y
     *            para determinar la cantidad de registros.
     * @return el numero de registros que cumplen con los filtros de la consulta
     */
    public int cantidadRegistrosValoresFiltro(String dominio, Dominios filtro) {
	Query consulta = mp.createNamedQuery(Dominios.class.getSimpleName() + ".cantidadValores");
	consulta.setParameter("nombreDominio", dominio);
	return ((Long) consulta.getSingleResult()).intValue();
    }
    
    /***
     * Permite consultar el dominio por el valor lowvalue
     * 
     * @param Dominio ,LowValue
     * @return el dominio respecto a los parametros de referencia
     */
	public Dominios getDominioByLowValue(String dominio, String valorDominio) {
		try {
			Query consulta = mp.createNamedQuery("Dominios.findbyLowValue");
			consulta.setParameter("nombreDominio", dominio);
			consulta.setParameter("valorDominio", valorDominio);
			return (Dominios) consulta.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
    /***
     * Permite consultar el dominio por el valor Meaning
     * 
     * @param Dominio , Meaning
     * @return el dominio respecto a los parametros de referencia ingresados
     */
	public Dominios getDominioByMeaning(String dominio, String valorDominio) {
		try {
			Query consulta = mp.createNamedQuery("Dominios.findbyMeaning");
			consulta.setParameter("nombreDominio", dominio);
			consulta.setParameter("valorDominio", valorDominio);
			return (Dominios) consulta.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
