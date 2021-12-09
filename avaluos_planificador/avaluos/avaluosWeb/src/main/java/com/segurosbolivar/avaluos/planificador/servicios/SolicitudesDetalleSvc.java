package com.segurosbolivar.avaluos.planificador.servicios;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionRolledbackLocalException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.mapstruct.factory.Mappers;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.modelo.service.IArchivo;
import com.segurosbolivar.avaluos.modelo.service.impl.ArchivoImpl;
import com.segurosbolivar.avaluos.planificador.filenet.GestionArchivosImpl;
import com.segurosbolivar.avaluos.planificador.modelo.dto.DocumentoDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.SoporteDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Soporte;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.SoporteFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadFinancieraSolicitud;
import com.segurosbolivar.avaluos.planificador.modelo.services.IBeneficiario;
import com.segurosbolivar.avaluos.planificador.modelo.services.ICultivo;
import com.segurosbolivar.avaluos.planificador.modelo.services.IDominios;
import com.segurosbolivar.avaluos.planificador.modelo.services.IGestionArchivos;
import com.segurosbolivar.avaluos.planificador.modelo.services.ISolicitud;
import com.segurosbolivar.avaluos.planificador.modelo.services.ISolicitudBeneficiario;
import com.segurosbolivar.avaluos.planificador.modelo.services.ISoporte;
import com.segurosbolivar.avaluos.planificador.modelo.services.IUnidadProductiva;

@Path("/SolicitudesDet")
@Stateless
public class SolicitudesDetalleSvc {

	@EJB
	IBeneficiario iBeneficiario;
	
	@EJB
	IActividadFinancieraSolicitud iActFinSolicitud;
	
	@EJB
	ISolicitud iSolicitud;
	
	@EJB 
	ISolicitudBeneficiario iSolBeneficiario;
	
	@EJB
	ICultivo iCultivo;
	
	@EJB
	IUnidadProductiva iUnidadProductiva;
	
	@EJB
	IDominios iDominios;
	
	@EJB
	IGestionArchivos gestionArchivos;	
	
	@EJB
	IArchivo archivo;
	
	@EJB
	ISoporte iSoporte;

	@Path("/consultaPorCod/{codSolicitud}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaRest getSolicitudDetalle(@PathParam("codSolicitud") String codSolicitud) {
		
		RespuestaRest respuesta;
		
			
		try {
			
			
			respuesta = new RespuestaRest("200", "",iSolicitud.getSolicitud(codSolicitud));
		} catch (SQLException e) {
			respuesta = new RespuestaRest("500", e.getMessage(), null);
			e.printStackTrace();
		}

//		System.out.println("Res: "+respuesta.getDatosRespuesta().toString());
		return respuesta;
	}
	

	@GET 
	@Path("descargarDocumento/{idDocumento}")
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaRest descargarArchvio(@PathParam("idDocumento") String idDocumento)  {      
		Logger.getLogger(ArchivoImpl.class.getName()).info("Descargar documento por URL, descargarArchvio , inicio " + idDocumento);
		idDocumento = idDocumento.replace('|', '/');
//		byte[] decodedBytes = Base64.getDecoder().decode(idDocumento);
//		String decodedString = new String(decodedBytes);
		
		try {
			 //Borramos los primeros ocho caracteres.
	        String nombreArchivo = idDocumento.substring(16);
			String url = archivo.obtenerDocumentoURL(idDocumento,nombreArchivo);
			Logger.getLogger(ArchivoImpl.class.getName()).info("Descargar documento por URL, descargarArchvio, fin " + idDocumento);	
			return new RespuestaRest("200","Descarga archivo por URL",url);
		} catch (NegocioException e) {
			Logger.getLogger(ArchivoImpl.class.getName()).info("Descargar documento por URL, descargarArchvio, error negocio: " + e.getMessage());
			return new RespuestaRest("500",e.getMessage(),null);		
		} catch (IOException e) {
			Logger.getLogger(ArchivoImpl.class.getName()).info("Descargar documento por URL, descargarArchvio, error IO: " + e.getMessage());
			return new RespuestaRest("500",e.getMessage(),null);
		}
	}
	
	@GET 
	@Path("consultaDocumentosSolicitud/{codigoSolicitud}/{tipoDocumento}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaRest consultaDocumentosSolicitud(@PathParam("codigoSolicitud") String codigoSolicitud, @PathParam("tipoDocumento") String tipoDocumento) {      
		List<DocumentoDTO> listaDoc = new ArrayList<>();
		
			try {
				  List<DocumentoDTO> listaDocumentos=gestionArchivos.consultarDocumentosSolicitud(codigoSolicitud,tipoDocumento);
				  for(DocumentoDTO documentoDto : listaDocumentos) {
					  documentoDto.setSolicitud(null);
					  listaDoc.add(documentoDto);					 
				  }
				  
				return new RespuestaRest("200",null,listaDoc);
			} catch (SQLException e) {
				return new RespuestaRest("500",e.getMessage(),null);
			}
	
	}
	
	@GET 
	@Path("consultaSoportes/{unidadProductiva}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaRest consultaSoportes(@PathParam("unidadProductiva") Long unidadProductiva) {      
			List<SoporteDTO> listaSoporteDto = new ArrayList<>();
			SoporteFullDTOMapper soporteFullDTOMapper = Mappers.getMapper( SoporteFullDTOMapper.class );
			
			
			List<Soporte> listaSoportes = new ArrayList<>();
			try {
				listaSoportes = iSoporte.consultaSoportesPorUndProd(unidadProductiva);
			} catch (TransactionRolledbackLocalException | NegocioException e) {
				e.printStackTrace();
			}
			
			for(Soporte soporte: listaSoportes) {
//				soporte.setCultivo(null);
//				soporte.setUnidadProductiva(null);
				SoporteDTO soporteDto = soporteFullDTOMapper.entity2dto(soporte);
				if(soporteDto.getCultivo()!=null) {				
					soporteDto.setIdCultivo(soporte.getCultivo().getIdCultivo().intValue());					
				}
				soporteDto.setCultivo(null);
				
				soporteDto.setUnidadProductiva(null);
				try {
					soporteDto.setContenidoImagen(gestionArchivos.consultarArchivoS3(soporteDto.getPath()));
				} catch (NegocioException | IOException e) {
					e.printStackTrace();
				}
				listaSoporteDto.add(soporteDto);
			}
			
			return new RespuestaRest("200",null,listaSoporteDto);
					
	}
}
