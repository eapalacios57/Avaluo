package com.segurosbolivar.avaluos.modelo.facade;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Local;


import com.asesoftware.util.exception.NegocioException;
import com.davivienda.esquemas.filenet.documentotipo.v1.DocumentoTipo;
import com.davivienda.filenet.srvscngestorfilenet.v1.MsjRespOpCargarDocumento;
import com.davivienda.filenet.srvscngestorfilenet.v1.MsjSolOpCargarDocumento;
import com.davivienda.xml.notificacionaseguradoras.NotificacionAvaluosRespType;
import com.davivienda.xml.notificacionaseguradoras.NotificacionAvaluosRqType;
import com.segurosbolivar.avaluos.modelo.dto.MapaRiesgo;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Modulos;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Perfiles;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Usuario;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Usuarios;

/**
 *
 * Abstrae los servicios que seran utilizados para la interaccion con
 * aplicaciones de terceros y transferiran informacion hacia la aplicacion de
 * avaluos
 * 
 * @author John Fredy Rincon
 * @version 1.0
 * @since 19/09/2017
 */
@Local
public interface IIntegradorFacade extends Serializable {

	Modulos obtenerInformacionAplicativo(int idAplicativo) throws NegocioException;

	Modulos obtenerMenuPerfilAplicacion(String perfil, int idAplicativo) throws NegocioException;

	Usuarios obtenerUsuariosPorAplicativo(int idAplicativo) throws NegocioException;

	Modulos obtenerMenuUsuarioAplicacion(String numeroIdentificacion, int idAplicativo, String pais)
			throws RemoteException, NegocioException;

	Perfiles obtenerPerfilesPorAplicacion(int idAplicativo) throws NegocioException;

	Usuario obtenerUsuarioPorAplicativo(String numIdentificacion, int aplicativo)
			throws RemoteException, NegocioException;

	MapaRiesgo obtenerParametrosRiesgo(String coordenadaX, String coordenadaY) throws NegocioException;

	String obtenerAsegurabilidad(String coordenadaX, String coordenadaY) throws NegocioException;

	void crearMultiplesArchivos(List<File> archivos, String rutaDirectorio) throws NegocioException;

	void crearDirectorio(String rutaDirectorio, String nombre) throws NegocioException;

	void obtenerDirectorio(String rutaArchivoAWS, File rutaDirectorioDdestino) throws  NegocioException;

	void eliminarArchivo(String archivo) throws NegocioException;
	
	void eliminarArchivosPorSufijo(String nombreDirectorio, List<String> sufijos) throws NegocioException;
	
	void eliminarDirectorio(String nombreDirectorio) throws NegocioException;

	void crearArchivo(String llave, InputStream archivo);

	byte[] obtenerArchivo(String nombreArchivo) throws NegocioException;
		
	List<String> obtenerArchivosporconsecutivo(String consecutivo) throws NegocioException;
	
	URL generarUrlDescarga(String idDocumento, String nombreArchivo) throws NegocioException;
	
	MsjRespOpCargarDocumento crearDocumentoDav(MsjSolOpCargarDocumento documento) throws NegocioException;
	byte[] obtenerArchivoMin(String nombreArchivo) throws NegocioException, Exception;
	NotificacionAvaluosRespType enviarNotificacion(NotificacionAvaluosRqType Request) throws NegocioException;
}
