package com.segurosbolivar.avaluos.modelo.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.entity.DaReglaEstDir;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad DaReglaEstDir. Se encarga de
 * las operaciones a base de datos de la tabla de DA_REGLA_ESTDIR a través
 * del uso del entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class DaReglasEstDirDao extends ManejadorCrud<DaReglaEstDir, String> {

    private static final long serialVersionUID = 182207864965789111L;

    public DaReglasEstDirDao() {
	super(DaReglaEstDir.class);
    }

    /**
     * Permite obtener el listado completo de las reglas para establecer
     * direcciones.
     * 
     * @return listado de reglas para el procesamiento y formateo de direcciones.
     */
    @SuppressWarnings("unchecked")
    public List<DaReglaEstDir> obtenerReglasDirecciones() {
	Query consulta = mp.createNamedQuery("DaReglaEstDir.findAll");
	return consulta.getResultList();
    }

}
