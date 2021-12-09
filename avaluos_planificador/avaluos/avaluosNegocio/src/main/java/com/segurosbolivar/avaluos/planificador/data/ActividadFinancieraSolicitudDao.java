package com.segurosbolivar.avaluos.planificador.data;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadFinancieraSolicitudDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadFinancieraSolicitud;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.ActividadFinancieraSolicitudFullDTOMapper;

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
public class ActividadFinancieraSolicitudDao extends ManejadorCrud<ActividadFinancieraSolicitud, BigDecimal> {

    private static final long serialVersionUID = 7747626177696187846L;

    public ActividadFinancieraSolicitudDao() {
	super(ActividadFinancieraSolicitud.class);
    }

      
    
    public ActividadFinancieraSolicitudDTO crear(ActividadFinancieraSolicitudDTO actividadFinancieraSolicitudDTO) {
    	SimpleDateFormat formatoTexto = new SimpleDateFormat("dd-MM-yyyy");
        
        ActividadFinancieraSolicitudFullDTOMapper actividadFinancieraSolicitudFullDTOMapper = Mappers.getMapper( ActividadFinancieraSolicitudFullDTOMapper.class );

        System.out.println(actividadFinancieraSolicitudDTO.getId().getCodigoSolicitud());
        
   		try {
			if (actividadFinancieraSolicitudDTO.getFechaInicioStr() != null) {actividadFinancieraSolicitudDTO.setFechaInicio(formatoTexto.parse(actividadFinancieraSolicitudDTO.getFechaInicioStr()));}
			if (actividadFinancieraSolicitudDTO.getFechaFinStr() != null) actividadFinancieraSolicitudDTO.setFechaFin(formatoTexto.parse(actividadFinancieraSolicitudDTO.getFechaFinStr()));
			if (actividadFinancieraSolicitudDTO.getFechaCreacionStr() != null) actividadFinancieraSolicitudDTO.setFechaCreacion(formatoTexto.parse(actividadFinancieraSolicitudDTO.getFechaCreacionStr()));
			if (actividadFinancieraSolicitudDTO.getFechaTransaccionStr() != null) actividadFinancieraSolicitudDTO.setFechaTransaccion(formatoTexto.parse(actividadFinancieraSolicitudDTO.getFechaTransaccionStr()));
   		} catch (ParseException e) {
			e.printStackTrace();
		}
   		   		
   		ActividadFinancieraSolicitud actividadFinancieraSolicitud = actividadFinancieraSolicitudFullDTOMapper.dto2entity(actividadFinancieraSolicitudDTO);
   		
   		mp.create(actividadFinancieraSolicitud);

        return actividadFinancieraSolicitudDTO;

    }
    
    public List<ActividadFinancieraSolicitud> listarActividadFinancieraSolicitud() throws SQLException {
    	
    	@SuppressWarnings("unchecked")
		List<ActividadFinancieraSolicitud> consulta = mp.createNamedQuery("ActividadFinancieraSolicitud.findAll").getResultList();
    	
    	return consulta;
    }
 
    @SuppressWarnings("unchecked")
	public List<ActividadFinancieraSolicitud> listarActFinSolicitudPorSol(String codSolic) throws SQLException {
    	
    	Query consulta = mp.createQuery(
				"SELECT a FROM ActividadFinancieraSolicitud a where a.solicitud.codigoSolicitud=:idSolicitud ORDER BY a.valorProyecto DESC ");
				
		consulta.setParameter("idSolicitud", codSolic);
		return consulta.getResultList();    	

    }
    
    public List<ActividadFinancieraSolicitud> listaActFinSolicitudPorSolYAct(String codSolic, String codActividad) {
    	Query query = mp.createNamedQuery("ActividadFinancieraSolicitud.findBySolAndAct");
    	query.setParameter("codigoSolicitud", codSolic);
    	query.setParameter("codigoActividad", codActividad);
    	return query.getResultList();
    }
}
