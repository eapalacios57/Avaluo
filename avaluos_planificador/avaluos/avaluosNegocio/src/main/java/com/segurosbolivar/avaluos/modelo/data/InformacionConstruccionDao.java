package com.segurosbolivar.avaluos.modelo.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.entity.InformacionConstruccion;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad informacion de la construccion. Se encarga
 * de las operaciones a base de datos de la tabla de PGB_INFCONSTRUCCION a
 * través del uso del entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class InformacionConstruccionDao extends ManejadorCrud<InformacionConstruccion, Long> {

    private static final long serialVersionUID = -5882064414611206125L;

    public InformacionConstruccionDao() {
	super(InformacionConstruccion.class);
    }

    /**
     * Permite consultar la informacion de la construccion asociado a un avaluo en
     * especifico.
     * 
     * @param avaluo
     *            id para el cual consultaremos el registro de informacion de la
     *            construccion
     * @return si tiene asociado el registro devolvera la informacion de la
     *         construccion para el avaluo. de lo contrario nulo.
     */
    @SuppressWarnings("rawtypes")
    public InformacionConstruccion consultar(Long idAvaluo) {
	Query consulta = mp.createNamedQuery(InformacionConstruccion.class.getSimpleName() + ".idAvaluo");
	consulta.setParameter("idAvaluo", idAvaluo);
	List resultado = consulta.getResultList();
	return (InformacionConstruccion) (resultado == null || resultado.size() != 1 ? null : resultado.get(0));
    }

}
