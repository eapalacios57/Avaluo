package com.segurosbolivar.avaluos.planificador.data;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;

import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ParametroDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Parametro;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.ParametroFullDTOMapper;

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
public class ParametroDao extends ManejadorCrud<Parametro, Long> {

    private static final long serialVersionUID = 7747626177696187846L;

    public ParametroDao() {
	super(Parametro.class);
    }

    public List<Parametro> listarParametros() throws SQLException {
    	    	
    	List<Parametro> consulta = mp.createNamedQuery("Parametro.findAll").getResultList();
    	
    	return consulta;
    }
    
    public ParametroDTO crear(ParametroDTO parametroDTO) {
    	
        
        ParametroFullDTOMapper parametroFullDTOMapper = Mappers.getMapper( ParametroFullDTOMapper.class );
        
   		Parametro parametro= parametroFullDTOMapper.dto2entity(parametroDTO);
   		parametro.setIdParametro(parametroDTO.getIdParametro());   		
        mp.create(parametro);

        return parametroDTO;
        // protected region Modifique el metodo crear end
    }
    
  
    
}
