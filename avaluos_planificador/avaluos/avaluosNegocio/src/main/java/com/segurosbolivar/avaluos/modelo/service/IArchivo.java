package com.segurosbolivar.avaluos.modelo.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.xml.datatype.DatatypeConfigurationException;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.Archivo;
import com.segurosbolivar.avaluos.modelo.entity.IPersona;
import com.segurosbolivar.avaluos.modelo.entity.ListaAnexosPdf;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Usuario;

/**
 * Se encarga de las operaciones referentes a los archivos del sistema. Desde la
 * creaci�n de la copia de seguridad con los aval�os, a la consulta de los
 * complementos y manuales del sistema.
 * 
 * Tambi�n es usado por los servicios de cargue masivo y procedato, para los
 * archivos comprimidos que deben procesarse en cada uno.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:42 a.m.
 */
@Local
public interface IArchivo extends Serializable {

//	@Deprecated
//	void guardarFotografia(ListaAnexosPdf anexos, UsuarioDto usuario) throws Exception;

	void guardarArchivo(IPersona persona, Archivo archivo, UsuarioDto usuario, String llaveArchivo) throws Exception;
	
	public void guardarArchivo(String llaveArchivo, byte[] archivo) throws Exception ;

//	Documento consultarDocumento(List<Propiedad> propiedades) throws NegocioException, IOException;

	byte[] obtenerDocumento(String idDocumento) throws NegocioException, IOException;
	
	byte[] obtenerDocumentoAvaluoMotor(String idDocumento) throws NegocioException, IOException;
	
	List<String> obtenerDocumentosporconsecutivo(String consecutivo) throws NegocioException, IOException;
	
	String obtenerDocumentoURL(String idDocumento, String nombreArchivo) throws NegocioException, IOException;

	byte[] obtenerDocumentoCompreso(String idDocumento) throws NegocioException, IOException;

	File obtenerDirectorio(String nombre) throws NegocioException;

	void borrarDocumento(Archivo archivo, String consecutivoAvaluo) throws NegocioException;

	void borrarDocumento(Long idArchivo) throws NegocioException;

	void borrarArchivo(String nombreDocumento);

	void guardarFotografiaMultiples(List<ListaAnexosPdf> anexos, List<ListaAnexosPdf> listaFotos, UsuarioDto usuario,
			boolean guardarEnS3) throws NegocioException;

//	public void consultaArchivosNoEnviados() throws NegocioException, SQLException;

	public void borrarDoc(String idDocS3) throws NegocioException;		

	void guardarEnBus(InputStream archivo, String nombre, UsuarioDto usuario, IPersona persona)
			throws NegocioException, IOException;

	String ingresarDocumentoDav(InputStream documentoDeIngreso, String nombreArchivo, UsuarioDto usuario,
			IPersona avaluo) throws NegocioException;

	byte[] obtenerDocumentoMin(String idDocumento) throws Exception, NegocioException, IOException;

	
	
}