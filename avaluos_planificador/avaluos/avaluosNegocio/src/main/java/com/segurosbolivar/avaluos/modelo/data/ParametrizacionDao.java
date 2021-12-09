package com.segurosbolivar.avaluos.modelo.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.entity.Parametrizacion;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.service.util.UtilJpa;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad Parametrizacion. Se encarga de las
 * operaciones a base de datos de la tabla de PGB_PARAMETRIZACION a través del
 * uso del entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class ParametrizacionDao extends ManejadorCrud<Parametrizacion, Long> {
    
	private static final long serialVersionUID = 1L;
	private static final String SELECT = "SELECT p FROM Parametrizacion p ";
	private static final String COUNT = "SELECT COUNT(p.Idparametrizacion) FROM Parametrizacion p ";

    public ParametrizacionDao() {
	super(Parametrizacion.class);
    }

    /**
     * Obtiene de la base de datos todos los parametros de un tipo especifico
     * 
     * @param Nombre
     *            del tipo Parametro
     * @return Lista de parametros de un tipo especifico
     * @author arincon
     */
    @SuppressWarnings("unchecked")
    public List<Parametrizacion> getTiposParametro(String tipoParametro) {
	Query query = mp.createNamedQuery(Parametrizacion.class.getSimpleName() + ".getTiposParametro");
	query.setParameter("tipoParametro", tipoParametro);
	return query.getResultList();
    }

    /**
     * Obtiene un parametro especifico
     * 
     * @param Nombre
     *            del tipo Parametro
     * @return Parametro buscado
     * @author arincon
     */
    public Parametrizacion getParametro(String tipoParametro, String nombreParametro) {
	if(UtilTexto.estaVacio(tipoParametro) || UtilTexto.estaVacio(nombreParametro))
	    return null;
	Query query = mp.createNamedQuery(Parametrizacion.class.getSimpleName() + ".getParametro");
	query.setParameter("tipoParametro", tipoParametro);
	query.setParameter("nombreParametro", nombreParametro);
	return (Parametrizacion) query.getSingleResult();
    }

	@SuppressWarnings("unchecked")
	public List<Parametrizacion> consultar(Parametrizacion consultar, int first, int pageSize, String campoOrden,SentidoOrientacion sentido) {
		Query consulta = mp.createQuery(SELECT.concat(generaWhere(consultar)).concat(UtilJpa.generarOrder("p", campoOrden, sentido)));
		consulta.setFirstResult(first);
		consulta.setMaxResults(pageSize);
		return consulta.getResultList();
	}
	
	private String generaWhere(Parametrizacion consulta) {
		if(consulta == null) 
			return "";
		StringBuilder condiciones = new StringBuilder();
		boolean tieneCondiciones = false;
		tieneCondiciones |= UtilJpa.agregarCondicion(condiciones, "UPPER(p.Tipoparametro)", consulta.getTipoparametro() != null ? consulta.getTipoparametro().toUpperCase():null, null, tieneCondiciones, true);
		return tieneCondiciones ? UtilConstantes.SQL_WHERE + condiciones.toString() : "";
	}

	public int cantidadRegistros(Parametrizacion consultar) {
		Query consulta = mp.createQuery(COUNT.concat(generaWhere(consultar)));
		return ((Long)consulta.getSingleResult()).intValue();
	}

}
