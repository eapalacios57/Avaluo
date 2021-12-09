package com.segurosbolivar.avaluos.modelo.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.entity.TablaErrores;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad TablaErrores. Se encarga de las
 * operaciones a base de datos de la tabla de TABLA_ERRORES a través del uso del
 * entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class TablaErroresDao extends ManejadorCrud<TablaErrores, Long> {

    private static final long serialVersionUID = 2133095101223599002L;

    public TablaErroresDao() {
	super(TablaErrores.class);
    }

    /**
     * Permite consultar el listado completo de errores parametrizados en el sistema
     * 
     * @param consultar
     *            se usara como filtro en caso de implementarse la consulta
     *            dinamica.
     * @return listado de mensajes de error del aplicativo.
     */
    @SuppressWarnings("unchecked")
    public List<TablaErrores> consultar(TablaErrores consultar) {
	Query consulta = mp.createNamedQuery(TablaErrores.class.getSimpleName() + ".findAll");
	return consulta.getResultList();
    }

}
