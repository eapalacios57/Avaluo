package com.segurosbolivar.avaluos.modelo.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.entity.LiquidacionAvaluoTotal;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad liquidacion del avalo total. Se encarga de
 * las operaciones a base de datos de la tabla de PGB_LIQAVALUO_TOTAL a través
 * del uso del entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class LiquidacionAvaluoTotalDao extends ManejadorCrud<LiquidacionAvaluoTotal, Long> {

    private static final long serialVersionUID = -6135001388627718139L;

    public LiquidacionAvaluoTotalDao() {
	super(LiquidacionAvaluoTotal.class);
    }

    /**
     * Permite consultar el registro de liquidacion total asociado a un avaluo en
     * especifico.
     * 
     * @param avaluo
     *            id para el cual consultaremos el registro de liquidacion total
     * @return si tiene asociado el registro devolvera la liquidacion total para el
     *         avaluo. de lo contrario nulo.
     */
    @SuppressWarnings("rawtypes")
    public LiquidacionAvaluoTotal consultar(Long idAvaluo) {
	Query consulta = mp.createNamedQuery(LiquidacionAvaluoTotal.class.getSimpleName() + ".idAvaluo");
	consulta.setParameter("idAvaluo", idAvaluo);
	List resultado = consulta.getResultList();
	return (LiquidacionAvaluoTotal) (resultado == null || resultado.size() != 1 ? null : resultado.get(0));
    }

}
