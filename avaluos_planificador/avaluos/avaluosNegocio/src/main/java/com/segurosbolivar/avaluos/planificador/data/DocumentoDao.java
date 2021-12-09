package com.segurosbolivar.avaluos.planificador.data;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;


import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Documento;



/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad peritos. Se encarga de las operaciones a
 * base de datos de la tabla de PGB_PERITOS_EMPRESAS a través del uso del
 * entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class DocumentoDao extends ManejadorCrud<Documento, Long> {

    private static final long serialVersionUID = 7747626177696187846L;

    public DocumentoDao() {
	super(Documento.class);
    }

    public List<Documento> listarDocumentos() throws SQLException {
    	    	
    	List<Documento> consulta = mp.createNamedQuery("Documento.findAll").getResultList();
    	
    	return consulta;
    }
    
    public void crear(Documento documento) {

    	mp.create(documento);

        // protected region Modifique el metodo crear end
    }
    
    public Documento buscarDocumento(Long idDocumento) throws SQLException {
    	
    	Query query = mp.createQuery("SELECT d FROM Documento d WHERE d.idDocumento=:id");
		query.setParameter("id", idDocumento);
    	Documento consulta = (Documento) query.getSingleResult();
    	
    	return consulta;
    }
    
    public List<Documento> buscarDocumentosPorSolicitud(String codigoSolicitud, String tipoDocumento) throws SQLException {
    	
    	Query query = mp.createQuery("SELECT d FROM Documento d WHERE d.solicitud.codigoSolicitud=:solicitud AND d.tipoDocumento = :tipoDocumento ORDER BY d.fecha ASC ");
		query.setParameter("solicitud", codigoSolicitud);
		query.setParameter("tipoDocumento", tipoDocumento);
    	    	
    	return query.getResultList();
    }
  
    
}
