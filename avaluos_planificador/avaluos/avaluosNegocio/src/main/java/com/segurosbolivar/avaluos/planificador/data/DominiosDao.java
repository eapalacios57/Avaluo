package com.segurosbolivar.avaluos.planificador.data;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.entity.Dominios;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.dto.DominiosPlanificadorDTO;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.DominiosFullDTOMapper;

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
public class DominiosDao extends ManejadorCrud<Dominios, Long> {

    private static final long serialVersionUID = 7747626177696187846L;

    public DominiosDao() {
	super(Dominios.class);
    }

    public List<Dominios> getDominios() throws SQLException{
	    Query query = mp.createQuery("SELECT d FROM Dominios d WHERE d.rvDomain = :nombreDominio");
		query.setParameter("nombreDominio", "TIPOIDENTIFICACION");
		return query.getResultList();
    }
    
    public List<Dominios> listarDominios() throws SQLException {
    	List<Dominios> consulta = mp.createNamedQuery("Dominios.findAll").getResultList();
    	return consulta;
        }

    public Dominios getDominioPorCodigo(String codigoDominio) throws SQLException{
	    Query query = mp.createQuery("SELECT d FROM Dominios d WHERE d.rvDomain = :nombreDominio AND d.rvLowValue = :codigoDominio");
		query.setParameter("nombreDominio", "TIPOIDENTIFICACION");
		query.setParameter("codigoDominio", codigoDominio);
		return (Dominios) query.getSingleResult();
    }
    
    public void crear(DominiosPlanificadorDTO dominiosDTO) {
    	
    	DominiosFullDTOMapper dominiosFullDTOMapper = Mappers.getMapper( DominiosFullDTOMapper.class );
               
   		Dominios dominios= dominiosFullDTOMapper.dto2entity(dominiosDTO);

   		System.out.println(dominios.getRvDomain());
    	mp.create(dominios);
    }
    
    
    
}
