package com.segurosbolivar.avaluos.modelo.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.entity.CondicionesSalubridad;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad condiciones de salubridad. Se encarga de
 * las operaciones a base de datos de la tabla de PGB_CONDSALUBRIDAD a través
 * del uso del entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class CondicionesSalubridadDao extends ManejadorCrud<CondicionesSalubridad, Long> {

    private static final long serialVersionUID = 2230861438232388880L;

    public CondicionesSalubridadDao() {
	super(CondicionesSalubridad.class);
    }

    /**
     * Permite consultar las condiciones de salubridad asociados a un avaluo en
     * especifico.
     * 
     * @param idAvaluo
     *            id para el cual consultaremos el registro de condiciones de
     *            salubridad
     * @return si tiene asociado el registro devolvera condiciones de salubridad
     *         para el avaluo. de lo contrario nulo.
     */
    @SuppressWarnings("rawtypes")
    public CondicionesSalubridad consultar(Long idAvaluo) {
	Query consulta = mp.createNamedQuery(CondicionesSalubridad.class.getSimpleName() + ".idAvaluo");
	consulta.setParameter("idAvaluo", idAvaluo);
	List resultado = consulta.getResultList();
	return (CondicionesSalubridad) (resultado == null || resultado.size() != 1 ? null : resultado.get(0));
    }

}
