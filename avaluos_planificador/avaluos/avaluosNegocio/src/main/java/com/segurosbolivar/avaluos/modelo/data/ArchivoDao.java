
package com.segurosbolivar.avaluos.modelo.data;


import javax.ejb.Stateless;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.entity.Archivo;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad archivo. Se encarga de las operaciones a
 * base de datos de la tabla de ARCHIVOS a través del uso del entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class ArchivoDao extends ManejadorCrud<Archivo, Long> {

	private static final long serialVersionUID = 1162651132707679187L;

	public ArchivoDao() {
		super(Archivo.class);
	}

	public int obtenerCantidadFotos(String idDocumento) {
		Query consulta = mp.createNamedQuery(Archivo.class.getSimpleName() + ".getDocumnentoId");
		consulta.setParameter("idFilenet", idDocumento);
		return consulta.getResultList().size();
	}
	
	public Archivo obtenerArchivo(Long idArchivo) {
		Query consulta = mp.createNamedQuery(Archivo.class.getSimpleName() + ".getArchivoId");
		consulta.setParameter("idArchivo", idArchivo);
		return (Archivo) consulta.getSingleResult();
	}
}
