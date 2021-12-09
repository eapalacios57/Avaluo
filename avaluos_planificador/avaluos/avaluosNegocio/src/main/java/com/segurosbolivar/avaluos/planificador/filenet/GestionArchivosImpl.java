package com.segurosbolivar.avaluos.planificador.filenet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import javax.ejb.Stateless;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.entity.ListaAnexosPdf;
import com.segurosbolivar.avaluos.modelo.service.IArchivo;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Usuario;
import com.segurosbolivar.avaluos.planificador.data.DocumentoDao;
import com.segurosbolivar.avaluos.planificador.modelo.dto.DocumentoDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Documento;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.DocumentoFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.service.impl.SincronizarImpl;
import com.segurosbolivar.avaluos.planificador.modelo.services.IGestionArchivos;

@Stateless
public class GestionArchivosImpl implements IGestionArchivos {

	/**
	 * 
	 */
	private static final long serialVersionUID = 575371528587472227L;

	@EJB
	private DocumentoDao documentoDao;

	@EJB
	IArchivo iArchivo;

	@Override
	public List<DocumentoDTO> consultarDocumentosSolicitud(String codigoSolicitud, String tipoDocumento)
			throws SQLException {

		List<Documento> documentos = documentoDao.buscarDocumentosPorSolicitud(codigoSolicitud, tipoDocumento);

		List<DocumentoDTO> listaDocumentosDTO = DocumentoFullDTOMapper.MAPPER.entity2dtoList(documentos);

		return listaDocumentosDTO;
	}

	@Override
	public String guardarArchivoS3(String nombreArchivo, byte[] archivo)
			throws NegocioException, IOException, Exception {
		Logger.getLogger(GestionArchivosImpl.class.getName()).info("GestionArchivo , guardarArchivoS3 , inicio " );
		String idDocumento = "-1";

		try {
			iArchivo.guardarArchivo(nombreArchivo, archivo);
			idDocumento = nombreArchivo;
			
		} catch (Exception e) {
			Logger.getLogger(GestionArchivosImpl.class.getName()).log(Level.ERROR,
					"GestionArchivo , guardarArchivoS3 , Falla al guardar , " + nombreArchivo ,  e);
						e.printStackTrace();
			throw new Exception("Error al guardar archivo ", e);
		}
		
		Logger.getLogger(IArchivo.class.getName()).info("GestionArchivo ,  guardarArchivoS3 , fin " + nombreArchivo);
		return idDocumento;
	}

	@Override
	public byte[] consultarArchivoS3(String id) throws NegocioException, IOException {
		return iArchivo.obtenerDocumento(id);
	}
	
	@Override
	public void consultarArchivoS3URL(String idDocumento, String nombreArchivo) throws NegocioException, IOException {
		Logger.getLogger(GestionArchivosImpl.class.getName()).info("GestionArchivo , consultarArchivoS3URL , inicio " );
		iArchivo.obtenerDocumentoURL(idDocumento, nombreArchivo);
		Logger.getLogger(GestionArchivosImpl.class.getName()).info("GestionArchivo , consultarArchivoS3URL , fin " );
	}

}
