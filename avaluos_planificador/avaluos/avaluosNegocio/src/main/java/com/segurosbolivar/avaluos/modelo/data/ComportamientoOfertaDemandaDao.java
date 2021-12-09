package com.segurosbolivar.avaluos.modelo.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.entity.ComportamientoOfertaDemanda;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad comportamiento oferta demanda. Se encarga
 * de las operaciones a base de datos de la tabla de PGB_COMP_OFERTA_DEMANDA a
 * través del uso del entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class ComportamientoOfertaDemandaDao extends ManejadorCrud<ComportamientoOfertaDemanda, Long> {

    private static final long serialVersionUID = -9191707169677087100L;

    public ComportamientoOfertaDemandaDao() {
	super(ComportamientoOfertaDemanda.class);
    }

    /**
     * Permite consultar el coportamiento de oferta y demanda asociado a un avaluo
     * en especifico.
     * 
     * @param avaluo
     *            id para el cual consultaremos el registro de comportamiento y
     *            demanda.
     * @return si tiene asociado el registro devolvera el comportamiento para el
     *         avaluo. de lo contrario nulo.
     */
    @SuppressWarnings("rawtypes")
    public ComportamientoOfertaDemanda consultar(Long idAvaluo) {
	Query consulta = mp.createNamedQuery(ComportamientoOfertaDemanda.class.getSimpleName() + ".idAvaluo");
	consulta.setParameter("idAvaluo", idAvaluo);
	List resultado = consulta.getResultList();
	return (ComportamientoOfertaDemanda) (resultado == null || resultado.size() != 1 ? null : resultado.get(0));
    }

}
