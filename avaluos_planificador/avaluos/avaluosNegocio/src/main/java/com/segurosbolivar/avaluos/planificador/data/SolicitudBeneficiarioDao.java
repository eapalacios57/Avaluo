package com.segurosbolivar.avaluos.planificador.data;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudBeneficiarioDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.SolicitudBeneficiario;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.SolicitudBeneficiarioFullDTOMapper;

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
public class SolicitudBeneficiarioDao extends ManejadorCrud<SolicitudBeneficiario, Long> {

    private static final long serialVersionUID = 7747626177696187846L;

    public SolicitudBeneficiarioDao() {
	super(SolicitudBeneficiario.class);
    }

    public SolicitudBeneficiario getSolicitudBeneficiario(String codSolicitud) {
		
    	Query consulta = mp.createQuery(
				"SELECT s FROM SolicitudBeneficiario s where s.id.codigoSolicitud =:idSolicitud AND  s.contacto = :contacto ");
		consulta.setParameter("idSolicitud", codSolicitud);
		consulta.setParameter("contacto", "S");
		return (SolicitudBeneficiario) consulta.getSingleResult();    	
    	
    }
    
    
    public List<SolicitudBeneficiario> listarSolicitudBeneficiarios() throws SQLException {
    	    	
    	@SuppressWarnings("unchecked")
		List<SolicitudBeneficiario> consulta = mp.createNamedQuery("SolicitudBeneficiario.findAll").getResultList();
    	
    	return consulta;
    }
    
    public SolicitudBeneficiarioDTO crear(SolicitudBeneficiarioDTO solicitudBeneficiarioDTO) {
    	SimpleDateFormat formatoTexto = new SimpleDateFormat("dd-MM-yyyy");
        
    	try {
			if (solicitudBeneficiarioDTO.getFechaCreacionStr() != null) {solicitudBeneficiarioDTO.setFechaCreacion(formatoTexto.parse(solicitudBeneficiarioDTO.getFechaCreacionStr()));}
    	} catch (ParseException e) {
			e.printStackTrace();			
		}
    	
        SolicitudBeneficiarioFullDTOMapper solicitudBeneficiarioFullDTOMapper = Mappers.getMapper( SolicitudBeneficiarioFullDTOMapper.class );
        
   		SolicitudBeneficiario solicitudBeneficiario= solicitudBeneficiarioFullDTOMapper.dto2entity(solicitudBeneficiarioDTO);

        mp.create(solicitudBeneficiario);

        return solicitudBeneficiarioDTO;
        // protected region Modifique el metodo crear end
    }
    
  
    
}
