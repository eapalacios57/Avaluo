package com.segurosbolivar.avaluos.planificador.data;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;

import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ParametroValorDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ParametroValor;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.ParametroValorFullDTOMapper;

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
public class ParametroValorDao extends ManejadorCrud<ParametroValor, Long> {

    private static final long serialVersionUID = 7747626177696187846L;

    public ParametroValorDao() {
	super(ParametroValor.class);
    }

    public List<ParametroValor> listarParametrosValor() throws SQLException {
    	    	
    	List<ParametroValor> consulta = mp.createNamedQuery("ParametroValor.findAll").getResultList();
    	
    	return consulta;
    }
    
    public ParametroValorDTO crear(ParametroValorDTO parametroValorDTO) {
    	
        
        ParametroValorFullDTOMapper parametroValorFullDTOMapper = Mappers.getMapper( ParametroValorFullDTOMapper.class );

        System.out.println(parametroValorDTO.getDescripcion());
        
   		ParametroValor parametroValor= parametroValorFullDTOMapper.dto2entity(parametroValorDTO);
   		parametroValor.setId(parametroValorDTO.getId()); 		
   		System.out.println(parametroValor.getId());
        mp.create(parametroValor);

        return parametroValorDTO;
        // protected region Modifique el metodo crear end
    }
    
  
    
}
