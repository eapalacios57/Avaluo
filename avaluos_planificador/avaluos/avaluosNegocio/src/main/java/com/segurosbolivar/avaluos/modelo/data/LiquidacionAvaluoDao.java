package com.segurosbolivar.avaluos.modelo.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.entity.LiquidacionAvaluo;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad informacion inmueble. Se encarga de las
 * operaciones a base de datos de la tabla de PGB_INFINMUEBLE a través del uso
 * del entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class LiquidacionAvaluoDao extends ManejadorCrud<LiquidacionAvaluo, Long> {

    private static final long serialVersionUID = -2806261562887652767L;

    public LiquidacionAvaluoDao() {
	super(LiquidacionAvaluo.class);
    }

    /**
     * Permite consultar los registros de liquidacion asociados a un avaluo en
     * especifico.
     * 
     * @param avaluo
     *            id para el cual consultaremos los registros de liquidacion.
     * @return si el avaluo consultado tiene asociado registros de liquidacion
     *         devolvera los mismos.
     */
    @SuppressWarnings("unchecked")
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<LiquidacionAvaluo> consultar(Long idAvaluo) {
	Query consulta = mp.createNamedQuery(LiquidacionAvaluo.class.getSimpleName() + ".idAvaluo");
	consulta.setParameter("idAvaluo", idAvaluo);
	return consulta.getResultList();
    }

    /**
     * Permite consultar un registro de liquidacion especifico a traves del avlauo y
     * su consecutivo.
     * 
     * @param idAvaluo
     *            a consultar
     * @param consecutivo
     *            del registro de liquidacion a consultar.
     * @return registro de de liquidacion correspondiente.
     */
    public LiquidacionAvaluo buscar(Long idAvaluo, Long consecutivo) {
	Query consulta = mp.createNamedQuery(LiquidacionAvaluo.class.getSimpleName() + ".consecutivoAvaluo");
	consulta.setParameter("idAvaluo", idAvaluo);
	consulta.setParameter("consecutivo", consecutivo);
	return (LiquidacionAvaluo) consulta.getSingleResult();
    }

}
