package com.segurosbolivar.avaluos.modelo.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.entity.ArchivosTmp;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad ArchivosTmp. Se encarga de las operaciones
 * a base de datos de la tabla de PGB_ARCHIVOS_TMP a través del uso del
 * entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class ArchivoTmpDao extends ManejadorCrud<ArchivosTmp, Long> {
	
	private static final long serialVersionUID = 2865328586066630349L;
	public static final String SELECT = "SELECT p FROM ArchivosTmp p ";

    public ArchivoTmpDao() {
    	super(ArchivosTmp.class);
    }

    /**
     * Permite realizar la busqueda de archivos que se cargaron como parte del
     * cargue masivo de avaluos por el nombre del archivo con el que se cargaron.
     * 
     * @param nombreArchivo
     *            con el que se realizo el cargue.
     * @return lista los archivos temporales que se cargaron asociados a una
     *         operacion de cargue masivo.
     */
    @SuppressWarnings("unchecked")
    public List<ArchivosTmp> buscarArchivosPorNombre(String nombreArchivo) {
		StringBuilder consulta = new StringBuilder();
		consulta.append(SELECT);
		consulta.append("WHERE UPPER(p.nombreArchivo) LIKE :nombreArchivo");
		Query query = mp.createQuery(consulta.toString());
		query.setParameter("nombreArchivo", "%" + nombreArchivo.toUpperCase() + "%");
		return query.getResultList();
    }

}


