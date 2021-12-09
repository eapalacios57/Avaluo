package com.segurosbolivar.avaluos.modelo.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.entity.AlertaAvaluos;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad alertas Avaluos. Se encarga de las
 * operaciones a base de datos de la tabla de PGB_ALERTA_AVALUOS a través del
 * uso del entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class AlertaDao extends ManejadorCrud<AlertaAvaluos, Long> {

    private static final long serialVersionUID = -9187902744058131290L;

    public AlertaDao() {
	super(AlertaAvaluos.class);
    }

    /**
     * Permite realizar la consulta de las alertas asociadas a un avaluo en
     * especifico.
     * 
     * @param consultar
     *            alerta que contiene el identificador del avaluo asociado.
     * @return el listado de alertas asociadas al avaluo.
     */
    @SuppressWarnings("unchecked")
    public List<AlertaAvaluos> consultar(AlertaAvaluos consultar) {
	Query consulta = mp.createNamedQuery(AlertaAvaluos.class.getSimpleName() + ".findAll");
	consulta.setParameter("idAvaluo", consultar.getIdAvaluo());
	return consulta.getResultList();
    }

}
