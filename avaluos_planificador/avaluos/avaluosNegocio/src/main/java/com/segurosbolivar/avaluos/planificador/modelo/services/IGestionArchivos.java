package com.segurosbolivar.avaluos.planificador.modelo.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.modelo.dto.DocumentoDTO;
import java.io.Serializable;

public interface IGestionArchivos extends Serializable{

	public List<DocumentoDTO> consultarDocumentosSolicitud(String codigoSolicitud,String tipoDocumento) throws SQLException;
	
	public String guardarArchivoS3(String nombreArchivo, byte[] archivo) throws NegocioException, IOException, Exception;
	
	public byte[] consultarArchivoS3(String id) throws NegocioException, IOException;
	
	public void consultarArchivoS3URL(String idDocumento, String nombreArchivo) throws NegocioException, IOException;
		
}
