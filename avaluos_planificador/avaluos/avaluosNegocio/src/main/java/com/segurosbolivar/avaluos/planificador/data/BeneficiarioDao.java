package com.segurosbolivar.avaluos.planificador.data;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.dto.BeneficiarioDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Beneficiario;
import com.segurosbolivar.avaluos.planificador.modelo.entity.BeneficiarioPK;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.BeneficiarioFullDTOMapper;

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
public class BeneficiarioDao extends ManejadorCrud<Beneficiario, Long> {

    private static final long serialVersionUID = 7747626177696187846L;

    public BeneficiarioDao() {
	super(Beneficiario.class);
    }

    public List<Beneficiario> listarBeneficiarios() throws SQLException {
    	    	
    	@SuppressWarnings("unchecked")
		List<Beneficiario> consulta = mp.createNamedQuery("Beneficiario.findAll").getResultList();
    	
    	return consulta;
    }
    
    public Beneficiario crear(BeneficiarioDTO beneficiarioDTO)  throws SQLException {    	
        
        BeneficiarioFullDTOMapper beneficiarioFullDTOMapper = Mappers.getMapper( BeneficiarioFullDTOMapper.class );
        
   		Beneficiario beneficiario = beneficiarioFullDTOMapper.dto2entity(beneficiarioDTO);

        mp.create(beneficiario);
        

        return beneficiario;
        // protected region Modifique el metodo crear end
    }
    
    public Beneficiario buscarPorId(BeneficiarioPK beneficiarioId) throws SQLException, NoResultException  {
    	
    	Beneficiario beneficiario=null;
    	
    	Query query = mp.createNamedQuery("Beneficiario.findById");
    	query.setParameter("tipoDocumentoBeneficiario", beneficiarioId.getTipoDocumentoBeneficiario());
    	query.setParameter("numeroDocumentoBeneficiario", beneficiarioId.getNumeroDocumentoBeneficiario());    	
    	    	
    	if(query.getResultList().size()>0) {
    		beneficiario=(Beneficiario) query.getResultList().get(0);    		
    	}
    
    	return beneficiario;
    }
  
    public Beneficiario actualziar(Beneficiario beneficiario) throws SQLException  {

		mp.update(beneficiario);
		
		return beneficiario;
	}
    
}
