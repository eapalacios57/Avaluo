package com.segurosbolivar.avaluos.modelo.data;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.eclipse.persistence.sessions.DatabaseRecord;

import com.asesoftware.util.exception.NegocioAlertaException;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.entity.Departamento;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.service.util.UtilJpa;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad Departamento. Se encarga de las
 * operaciones a base de datos de la tabla de PGB_DEPARTAMENTOS a través del uso
 * del entityManager.
 *
 * @author stilaguy
 *
 */
@Stateless
public class DepartamentoDao extends ManejadorCrud<Departamento, Long> {

    private static final long serialVersionUID = 5131329541875892438L;
    public static final String SELECT = "SELECT d FROM Departamento d ";
    public static final String COUNT = "SELECT COUNT( d.idDepartamento) FROM Departamento d ";

    public DepartamentoDao() {
	super(Departamento.class);
    }

    /**
     * Realiza la consulta dinamica de los departamentos con un orden establecido
     * por el usuario.
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
     * @return listado de departamentos que cumplen con las condiciones de los
     *         filtros.
     */
    @SuppressWarnings("unchecked")
    public List<Departamento> consultar(Departamento consultar, int inicio, int tamanioPagina, String campoOrden, SentidoOrientacion sentido) {
	Query consulta = mp.createQuery(SELECT.concat(generarWhere(consultar)).concat(UtilJpa.generarOrder("d", campoOrden, sentido)));
	consulta.setMaxResults(tamanioPagina);
	consulta.setFirstResult(inicio);
	return consulta.getResultList();
    }

    /**
     * genera la cadena WHERE para la consulta dinamica de los departamentos
     * 
     * @param consultar
     *            contiene los valores de los filtros a aplicar
     * @return cadena WHERE con las condicones JPA asociadas a los filtros
     *         aplicados.
     */
    private String generarWhere(Departamento consultar) {
	if (consultar == null)
	    return "";
	StringBuilder sb = new StringBuilder();
	boolean tieneCondiciones = false;
	tieneCondiciones |= UtilJpa.agregarCondicionLIKE(sb, "upper(d.departamento)", consultar.getDepartamento() != null ? consultar.getDepartamento().toUpperCase() : null,
		tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "d.codDivpol", consultar.getCodDivpol(), null, tieneCondiciones, true);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "d.codAsobancaria", consultar.getCodAsobancaria(), null, tieneCondiciones, true);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "d.codDane", consultar.getCodDane(), null, tieneCondiciones, true);
	return tieneCondiciones ? UtilConstantes.SQL_WHERE + sb.toString() : "";
    }

    /**
     * Ejecuta el procedimeinto almacenado SP_SINCRONIZA_DANE que realiza la
     * verificacion de los codigos de asobancaria para las icuades y departamentos.
     * 
     * @param usuario
     *            que realiza la ejecucion del procedimiento
     * @throws NegocioException
     *             en caso de presentarte incovenientes con la base de datos.
     */
    public void sincronizacionDane(String usuario) throws NegocioException {
	String mensaje = null;
	try {
	    DatabaseRecord respuesta = mp.ejecutarPrcPLSQL("PKG_GENERAL_AVALUOS.SP_SINCRONIZA_DANE", Collections.singletonList("pusuario"), Collections.singletonList("pmensaje"),
		    Collections.singletonList(usuario));
	    mensaje = (String) respuesta.get("pmensaje");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	if (!UtilTexto.estaVacio(mensaje))
	    throw new NegocioAlertaException(mensaje);
    }

    /***
     * Permite determinar la cantidad de registros para la consulta a realizarse de
     * departamentos, esto permitira el uso del paginador para la consulta.
     * 
     * @param consultar
     *            contiene los valores del filtro que se usaran en la consulta y
     *            para determinar la cantidad de registros.
     * @return el numero de registros que cumplen con los filtros de la consulta
     */
    public int cantidadRegistros(Departamento consultar) {
	String query = COUNT.concat(generarWhere(consultar));
	Query consulta = mp.createQuery(query);
	return ((Long) consulta.getSingleResult()).intValue();
    }

    /**
     * Permite consultar un departamento segun el codigo de asobancaria.
     * 
     * @param codAsobancaria
     *            a consultar
     * @return la ciudad que corresponde al codigo enviado.
     */
    public Departamento buscarPorAsobancaria(long codAsobancaria) {
	Query query = mp.createNamedQuery(Departamento.class.getSimpleName() + ".buscarPorAsobancaria");
	query.setParameter("codAsobancaria", codAsobancaria);
	return (Departamento) query.getSingleResult();
    }
    
    public BigDecimal buscarRegion(Long idDepartamento) {
    	String idRegionStr = "";
    	BigDecimal idRegionFail = new BigDecimal(0);
    	try {
    		Query consulta = mp.createNamedQuery("Departamento.buscarRegion");
        	consulta.setParameter("idDepartamento", idDepartamento);
        	idRegionStr = consulta.getSingleResult().toString();
        	BigDecimal idRegion = new BigDecimal(idRegionStr);
        	
        	return idRegion;
		} catch (Exception e) {
			e.getMessage();
			return idRegionFail;
		}

    }

}
