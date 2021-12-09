package com.segurosbolivar.avaluos.planificador.data;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadFinancieraDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadFinanciera;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.ActividadFinancieraFullDTOMapper;

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
public class ActividadFinancieraDao extends ManejadorCrud<ActividadFinanciera, Long> {

    private static final long serialVersionUID = 7747626177696187846L;

    public ActividadFinancieraDao() {
	super(ActividadFinanciera.class);
    }

    public List<ActividadFinanciera> listarActividadFinancieras() throws SQLException {
    	    	
    	@SuppressWarnings("unchecked")
    	List<ActividadFinanciera> consulta = mp.createNamedQuery("ActividadFinanciera.findAll").getResultList();
    	
    	return consulta;
    }
    
    public ActividadFinancieraDTO crear(ActividadFinancieraDTO actividadFinancieraDTO) {
    	
        
        ActividadFinancieraFullDTOMapper actividadFinancieraFullDTOMapper = Mappers.getMapper( ActividadFinancieraFullDTOMapper.class );

   		ActividadFinanciera actividadFinanciera= actividadFinancieraFullDTOMapper.dto2entity(actividadFinancieraDTO);
        mp.create(actividadFinanciera);

        return actividadFinancieraDTO;
        // protected region Modifique el metodo crear end
    }
    
    public ActividadFinanciera buscarActividadFinanciera(String codigoActividad) throws SQLException {	
    	
    	Query query = mp.createNamedQuery("ActividadFinanciera.findById");
		query.setParameter("codigoActividad", codigoActividad);
    	ActividadFinanciera consulta = (ActividadFinanciera) query.getSingleResult();
    	
    	return consulta;
    }
    
}
