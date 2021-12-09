package com.segurosbolivar.avaluos.planificador.servicios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.commons.io.IOUtils;

import com.asesoftware.util.exception.NegocioException;
//import com.segurosbolivar.avaluos.modelo.dto.ConsultaFileNetDto;
import com.segurosbolivar.avaluos.modelo.entity.IPersona;
import com.segurosbolivar.avaluos.modelo.entity.ListaAnexosPdf;
import com.segurosbolivar.avaluos.modelo.facade.IIntegradorFacade;
import com.segurosbolivar.avaluos.modelo.prueba.ITomaTiempos;
import com.segurosbolivar.avaluos.modelo.service.IArchivo;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Usuario;
//import com.segurosbolivar.avaluos.modelo.ws.service.impl.ClienteFileNetWebService;
import com.segurosbolivar.avaluos.planificador.modelo.dto.DocumentoDTO;





@Path("/Archivos")
@Stateless
public class ArchivosSvc {
	
	

	@EJB
    private IIntegradorFacade integradorFacade;
	
//	@EJB
//	ClienteFileNetWebService clienteFileNetWebService;
	
	@EJB
	private ITomaTiempos Tiempo;
	
	@EJB
	IArchivo iArchivo;
	
	
	@POST 
	@Path("guardarDocumento")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaRest guardarDocumento(DocumentoDTO documentoModel) {      
	       
	       List<ListaAnexosPdf> listaAnexos=new ArrayList<>();
	       
	       FileInputStream entrada = null;
	       try {
	             entrada = new FileInputStream("C:\\tmp\\4 - Presenta.pdf");
	       } catch (FileNotFoundException e1) {
	             e1.printStackTrace();
	       }
	       
	       ListaAnexosPdf listaAnexosPdf=new ListaAnexosPdf();
	       try {
	             listaAnexosPdf.setAnexo(IOUtils.toByteArray(entrada));
	       } catch (IOException e1) {
	             e1.printStackTrace();
	       }
	       
	       listaAnexos.add(listaAnexosPdf);
	       
	       IPersona iPersona = null;
	       Usuario usuario = new Usuario();
	       
	       usuario.setCodigo("80192879");
	       usuario.setNombres("Pedro Perez");
	       
	       List<Map<String, String>> params = new ArrayList<>();
	       for (ListaAnexosPdf anexo : listaAnexos) {
//	           ConsultaFileNetDto filenetDto = new ConsultaFileNetDto(anexo.obtenerNombreUnico(), usuario, iPersona);
//	           filenetDto.crearMetadata();
	          
//	           params.add(filenetDto.getParametros());
	       }
	       
	       
//	       try {
//	             System.out.println(iArchivo.ingresarDocumentosMultiplesFilenet(listaAnexos, params));
//	       } catch (NegocioException | IOException e) {
//	             e.printStackTrace();
//	       }
	       
	       return null;
	}

	
}