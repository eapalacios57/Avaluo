package com.segurosbolivar.avaluos.modelo.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.entity.InformacionBarrio;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad informacion barrio. Se encarga de las
 * operaciones a base de datos de la tabla de PGB_INFBARRIO a través del uso del
 * entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class InformacionBarrioDao extends ManejadorCrud<InformacionBarrio, Long> {

    private static final long serialVersionUID = 4601508045162785414L;

    public InformacionBarrioDao() {
	super(InformacionBarrio.class);
    }

    /**
     * Permite consultar la informacion del barrio asociado a un avaluo en
     * especifico.
     * 
     * @param avaluo
     *            id para el cual consultaremos el registro de informacion del
     *            barrio
     * @return si tiene asociado el registro devolvera la informacion del barrio
     *         para el avaluo. de lo contrario nulo.
     */
    @SuppressWarnings("rawtypes")
    public InformacionBarrio consultar(Long idAvaluo) {
	Query consulta = mp.createNamedQuery(InformacionBarrio.class.getSimpleName() + ".idAvaluo");
	consulta.setParameter("idAvaluo", idAvaluo);
	List resultado = consulta.getResultList();
	return (InformacionBarrio) (resultado == null || resultado.size() != 1 ? null : resultado.get(0));
    }

}
