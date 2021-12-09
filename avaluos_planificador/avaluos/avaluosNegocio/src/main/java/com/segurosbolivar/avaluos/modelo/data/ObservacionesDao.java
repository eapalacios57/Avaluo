package com.segurosbolivar.avaluos.modelo.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.entity.Observaciones;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad observacines. Se encarga de las
 * operaciones a base de datos de la tabla de PGB_OBSERVACIONES a través del uso
 * del entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class ObservacionesDao extends ManejadorCrud<Observaciones, Long> {

    private static final long serialVersionUID = -5374893279997916388L;

    public ObservacionesDao() {
	super(Observaciones.class);
    }

    /**
     * Permite consultar el registro de observaciones asociado a un avaluo en
     * especifico.
     * 
     * @param avaluo
     *            id para el cual consultaremos el registro de observaciones
     * @return si tiene asociado el registro devolvera la observacion para el
     *         avaluo. de lo contrario nulo.
     */
    @SuppressWarnings("rawtypes")
    public Observaciones consultar(Long idAvaluo) {
	Query consulta = mp.createNamedQuery(Observaciones.class.getSimpleName() + ".idAvaluo");
	consulta.setParameter("idAvaluo", idAvaluo);
	List resultado = consulta.getResultList();
	return (Observaciones) (resultado == null || resultado.size() != 1 ? null : resultado.get(0));
    }

}
