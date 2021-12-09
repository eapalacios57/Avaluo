package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import org.mapstruct.factory.Mappers;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.data.DocumentoDao;
import com.segurosbolivar.avaluos.planificador.modelo.dto.DocumentoDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Documento;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.DocumentoFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.services.IDocumento;

/**
 * Implementaci�n del servicio de archivo
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:38 a.m.
 */
@Stateless
@Local(IDocumento.class)
public class DocumentoImpl implements IDocumento {

    private static final long serialVersionUID = -2088678805488718651L;
    
    @EJB
    private DocumentoDao documentoDao;


	@Override
	public void crearDocumento(DocumentoDTO documentoDto) throws Exception{
		
       try {
		DocumentoFullDTOMapper documentoFullDTOMapper = Mappers.getMapper( DocumentoFullDTOMapper.class );
	        
   		Documento documento= documentoFullDTOMapper.dto2entity(documentoDto);
   		
		documentoDao.crear(documento);
		
       }catch(Exception e) {
    	   Logger.getLogger(SincronizarImpl.class.getName()).info("CrearDocumentoImpl ,  crearDocumento , error ");
    	     e.printStackTrace();
       }
        
		
	}
	
	@Override
	public void eliminarDocumento(Documento documento) throws SQLException {	
   		
		try {
			documentoDao.eliminar(documento);
		} catch (NegocioException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Documento> listaDocumento() throws SQLException {
		List<Documento> lista = new ArrayList<>();
		lista = documentoDao.listarDocumentos();
		
		return lista;
	}

    @Override
    public Documento buscaDocumento(Long idDocumento) {
    	Documento documento = new Documento();
    	
    	try {
			documento = documentoDao.buscarDocumento(idDocumento);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return documento;
    }
  
}