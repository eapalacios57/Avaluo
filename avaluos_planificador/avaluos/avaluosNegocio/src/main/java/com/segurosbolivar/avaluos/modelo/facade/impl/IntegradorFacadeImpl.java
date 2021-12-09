package com.segurosbolivar.avaluos.modelo.facade.impl;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.davivienda.esquemas.filenet.documentotipo.v1.DocumentoTipo;
import com.davivienda.filenet.srvscngestorfilenet.v1.MsjRespOpCargarDocumento;
import com.davivienda.filenet.srvscngestorfilenet.v1.MsjSolOpCargarDocumento;
import com.davivienda.filenet.srvscngestorfilenet.v1.SrvScnGestorFileNet;
import com.davivienda.xml.notificacionaseguradoras.DataBUAReqType;
import com.davivienda.xml.notificacionaseguradoras.DataHeaderReqType;
import com.davivienda.xml.notificacionaseguradoras.NotificacionAvaluosRespType;
import com.davivienda.xml.notificacionaseguradoras.NotificacionAvaluosRqType;
import com.segurosbolivar.avaluos.modelo.cons.SiNo;
import com.segurosbolivar.avaluos.modelo.data.ParametrizacionDao;
import com.segurosbolivar.avaluos.modelo.dto.MapaRiesgo;
import com.segurosbolivar.avaluos.modelo.entity.Parametrizacion;
import com.segurosbolivar.avaluos.modelo.facade.IIntegradorFacade;
import com.segurosbolivar.avaluos.modelo.service.impl.ManejadorErroresNegocio;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Modulos;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Perfiles;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Usuario;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Usuarios;
import com.segurosbolivar.avaluos.modelo.ws.service.impl.ClienteFcaWebService;
import com.servicios.aws.ClienteS3Service;
import com.servicios.aws.interfaces.IS3Service;
import com.segurosbolivar.avaluos.modelo.ws.service.impl.ClienteNotificacionesAseguradorasService;
import com.segurosbolivar.avaluos.modelo.ws.service.impl.SrvScnGestorFileNetImple;

/**
 *
 * Clase que implementa las distintas operaciones con los servicios web de
 * terceros
 * 
 * @author John Fredy Rincon
 * @version 1.0
 * @since 19/09/2017
 */
@Stateless
public class IntegradorFacadeImpl implements IIntegradorFacade {

	private static final long serialVersionUID = -6361008413184241264L;

	private String nombreBucketAwsS3; 
	/**
	 * Cliente EJB del servicio FCA
	 */
	@EJB
	private ClienteFcaWebService clienteFcaWebService;

	/**
	 * Cliente de los servicios de AWS S3.
	 */
	private IS3Service amazonS3;

	/**
	 * Cliente EJB del servicio de Davivienda
	 */	
	@EJB
	private SrvScnGestorFileNetImple srvScnGestorFileNetImple;
	
	/**
	 * Cliente EJB del servicio de Notificacion de Davivienda
	 */	
	@EJB
	private ManejadorErroresNegocio mgrExc;
	
	@EJB
	private ClienteNotificacionesAseguradorasService clienteNotificacionesAseguradorasService;
	
	@EJB
	private ParametrizacionDao parametrizacionDao;
	
	@PostConstruct
	public void init(){
		try {
			if (amazonS3 == null || nombreBucketAwsS3 == null) {
				String keyId = null;
				String secretKey = null;
				String modeAccelerate = null;
				
				List<Parametrizacion> ParametrosAwsS3 = parametrizacionDao.getTiposParametro(UtilConstantes.TIPO_PARAMETRO_AWS_S3);
				for (Parametrizacion parametro : ParametrosAwsS3) {
					if(parametro.getNombreparametro().equals(UtilConstantes.NOMBRE_PARAMETRO_BUCKET)) {
						nombreBucketAwsS3 = parametro.getValorparametro();
					}else if(parametro.getNombreparametro().equals(UtilConstantes.NOMBRE_PARAMETRO_KEY_ID)) {
						keyId = parametro.getValorparametro();
					}else if(parametro.getNombreparametro().equals(UtilConstantes.NOMBRE_PARAMETRO_SECRET_KEY)) {
						secretKey = parametro.getValorparametro();
					}else if(parametro.getNombreparametro().equals(UtilConstantes.NOMBRE_PARAMETRO_MODE_ACCELERATE)) {
						modeAccelerate = parametro.getValorparametro();
					}
				}
				
				amazonS3 = new ClienteS3Service(keyId, secretKey, modeAccelerate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtiene todos los modulos y transacciones que tiene un determinado aplicativo
	 * registrado dentro del servicio Web FCA.
	 * 
	 * @param idAplicativo Identificador del Aplicativo
	 * @return Modulos Conjunto de modulos y transacciones disponibles
	 */
	@Override
	public Modulos obtenerInformacionAplicativo(int idAplicativo) throws NegocioException {
		return clienteFcaWebService.obtenerInformacionAplicativo(idAplicativo);
	}

	/**
	 * Obtiene el menu de permisos a los que tiene acceso un determinado perfil de
	 * usuario dentro de FCA.
	 * 
	 * @param idAplicativo Identificador del Aplicativo
	 * @param perfil       Nombre del perfil
	 * @return Modulos Objeto que contiene la lista de modulos y transacciones
	 */
	@Override
	public Modulos obtenerMenuPerfilAplicacion(String perfil, int idAplicativo) throws NegocioException {
		return this.clienteFcaWebService.obtenerMenuPerfilAplicacion(perfil, idAplicativo);
	}

	/**
	 * Obtiene la lista de los usuarios que tiene acceso a un aplicativo determinado
	 * 
	 * @param idAplicativo Identificador del Aplicativo
	 * @return Usuarios Objeto con la lista de usuarios recuperados.
	 */
	@Override
	public Usuarios obtenerUsuariosPorAplicativo(int idAplicativo) throws NegocioException {
		return this.clienteFcaWebService.obtenerUsuariosPorAplicativo(idAplicativo);
	}

	/**
	 * Obtiene el arbol de menu y sus permisos al que un usuario puede acceder
	 * 
	 * @param numeroIdentificacion Numero de identificacion de un usuario de la
	 *                             aplicacion
	 * @param idAplicativo         Identificador del Aplicativo
	 * @param pais                 Ubicacion desde donde se realiza la peticion
	 * @return Modulos Objeto que contiene la lista de modulos y transacciones
	 */
	@Override
	public Modulos obtenerMenuUsuarioAplicacion(String numeroIdentificacion, int idAplicativo, String pais)
			throws NegocioException {
		return this.clienteFcaWebService.obtenerMenuUsuarioAplicacion(numeroIdentificacion, idAplicativo, pais);
	}

	/**
	 * Obtiene la lista de perfiles que tiene disponible una determinada aplicacion
	 * 
	 * @param idAplicativo Identificador del Aplicativo
	 * @return Perfiles Objeto que contiene la lista de perfiles de la aplicacion
	 */
	@Override
	public Perfiles obtenerPerfilesPorAplicacion(int idAplicativo) throws NegocioException {
		return this.clienteFcaWebService.obtenerPerfilesPorAplicacion(idAplicativo);
	}

	/**
	 * Obtiene la informacion de registro de un determinado usuario
	 * 
	 * @param numIdentificacion Numero de identificacion
	 * @param aplicativo
	 */
	@Override
	public Usuario obtenerUsuarioPorAplicativo(String numIdentificacion, int aplicativo)
			throws NegocioException, RemoteException {
		return this.clienteFcaWebService.obtenerUsuarioPorAplicativo(numIdentificacion, aplicativo);
	}

	/**
	 * Obtiene los parametros de asegurabilidad de un avaluo hipotecario por medio
	 * de consulta al servicio web de Mapas de Riesgo de Seguros Bolivar.
	 * 
	 * @param coordenadaX Coordenada que representa una latitud geografica.
	 * @param coordenadaY Coordenada que representa una longitud geografica.
	 * @return MapaRiesgo Obgeto que contiene los parametros y atributos de
	 *         asegurabilidad.
	 */
	@Override
	public MapaRiesgo obtenerParametrosRiesgo(String coordenadaX, String coordenadaY) throws NegocioException {
		return this.clienteFcaWebService.obtenerParametrosAsegurabilidad(coordenadaX, coordenadaY);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String obtenerAsegurabilidad(String coordenadaX, String coordenadaY) throws NegocioException {
		MapaRiesgo mapaRiesgo = null;
		try {
			mapaRiesgo = obtenerParametrosRiesgo(coordenadaX, coordenadaY);
			if (mapaRiesgo != null && mapaRiesgo.getAdditionalProperties() != null
					&& mapaRiesgo.getAdditionalProperties().containsKey("error"))
				throw new Exception();
		} catch (Exception e) {
			throw new NegocioException("Servicio Caido" + e.getMessage());
		}
		if (mapaRiesgo == null)
			return SiNo.NO.getValor();
		try {
			return mapaRiesgo.getResults().get(0).getValue().getFeatures().get(0).getAttributes().getASEGURABIL();
		} catch (Exception e) {
			return SiNo.NO.getValor();
		}
	}

	/**
	 * Almacena una lista de archivos dentro del servicio de Amazon S3.
	 * 
	 * @param archivos       Colleccion que contiene la lista de archivos que se
	 *                       almacenaran.
	 * @param rutaDirectorio Ruta del directorio donde se encuentran almacenados los
	 *                       archivos.
	 */
	@Override
	public void crearMultiplesArchivos(List<File> archivos, String rutaDirectorio) throws NegocioException {
		try {
			amazonS3.registrarMultiplesArchivos(nombreBucketAwsS3, archivos, rutaDirectorio);
		} catch (Exception e) {
			e.printStackTrace();
			throw mgrExc.lanzarExcepcion(125, TipoErrorNegocio.ERROR);
		}
	}

	/**
	 * Almacena un directorio de archivos dentro del servicio de Amazon S3.
	 * 
	 * @param nombre         Llave de identificacion del directorio en Amazon S3.
	 * @param rutaDirectorio Ruta del directorio donde se encuentran almacenados los
	 *                       archivos.
	 */
	@Override
	public void crearDirectorio(String rutaDirectorio, String nombre) throws NegocioException {
		try {
			amazonS3.registrarDirectorio(nombreBucketAwsS3, rutaDirectorio, nombre);
		} catch (Exception e) {
			e.printStackTrace();
			throw mgrExc.lanzarExcepcion(124, TipoErrorNegocio.ERROR);
		}
	}

	/**
	 * Obtiene un directorio de archivos del servicio de Amazon S3.
	 * 
	 * @param rutaArchivoAWS         Llave de identificacion del directorio en
	 *                               Amazon S3.
	 * @param rutaDirectorioDdestino Ruta del directorio local donde seran
	 *                               almacenados los archivos.
	 */
	@Override
	public void obtenerDirectorio(String rutaArchivoAWS, File rutaDirectorioDdestino){
		try {
			if(amazonS3 != null)
				amazonS3.descargarDirectorio(nombreBucketAwsS3, rutaArchivoAWS, rutaDirectorioDdestino);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MsjRespOpCargarDocumento crearDocumentoDav(MsjSolOpCargarDocumento documento) throws NegocioException {
		MsjRespOpCargarDocumento response = srvScnGestorFileNetImple.enviarDocumento(documento);
		return response;
	}

	/**
	 * Elimina un archivo que existe dentro del servicio de Amazon S3.
	 * 
	 * @param archivo Llave de identificacion del archivo en Amazon S3.
	 */
	@Override
	public void eliminarArchivo(String archivo) throws NegocioException {
		try {
			if(amazonS3 != null)
				amazonS3.borrarArchivos(nombreBucketAwsS3, archivo);
		} catch (Exception e) {
			e.printStackTrace();
			throw mgrExc.lanzarExcepcion(119, TipoErrorNegocio.ERROR);
		}
	}

	/**
	 * Almacena un archivo dentro del servicio de Amazon S3.
	 * 
	 * @param llave   Llave de identificacion del directorio en Amazon S3.
	 * @param archivo InputStream del archivo que sera almacenado.
	 */
	@Override
	public void crearArchivo(String llave, InputStream archivo) {
		if(amazonS3 != null)
			amazonS3.registrarArchivo(nombreBucketAwsS3, llave, archivo);		
	}

	/**
	 * Obtiene un archivo del servicio de Amazon S3.
	 * 
	 * @param nombreArchivo Llave de identificacion del archivo en Amazon S3.
	 */
	@Override
	public byte[] obtenerArchivo(String nombreArchivo) throws NegocioException {
		try {
			if(amazonS3 != null)
				return amazonS3.obtenerArchivo(nombreBucketAwsS3, nombreArchivo);
			else 
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw mgrExc.lanzarExcepcion(121, TipoErrorNegocio.ERROR);
		}
	}
	/**
	 * Obtiene archivos que empiezan por un consecutivo dado
	 * @param consecutivo Consecutivo
	 */
	@Override
	public List<String> obtenerArchivosporconsecutivo(String consecutivo) throws NegocioException {
		try {
			String nombrebucketconsecutivos= nombreBucketAwsS3; 
			if(amazonS3 != null)
				return amazonS3.listadoObjetosPorBucketandPrefix(nombrebucketconsecutivos, "fotos/"+consecutivo);
			else 
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw mgrExc.lanzarExcepcion(121, TipoErrorNegocio.ERROR);
		} 
	}
	
	/**
	 * Obtiene un archivo miniatura del servicio de Amazon S3.
	 * 
	 * @param nombreArchivo Llave de identificacion del archivo en Amazon S3.
	 */
	@Override
	public byte[] obtenerArchivoMin(String nombreArchivo) throws  Exception, NegocioException {
		try {
			if(amazonS3 != null)
				return amazonS3.obtenerArchivo(nombreBucketAwsS3, nombreArchivo);
			else 
				return null;
		} catch (Exception e) {			
			throw e;
		}
	}

	/**
	 * Elimina un directorio de archivos dentro del servicio de Amazon S3.
	 * 
	 * @param nombreDirectorio Llave de identificacion del directorio en Amazon S3.
	 */
	@Override
	public void eliminarDirectorio(String nombreDirectorio) throws NegocioException {
		try {
			if(amazonS3 != null)
				amazonS3.eliminarDirectorio(nombreBucketAwsS3, nombreDirectorio);
		} catch (Exception e) {
			e.printStackTrace();
			throw mgrExc.lanzarExcepcion(119, TipoErrorNegocio.ERROR);
		}
	}

	/**
	 * Elimina una lista de archivos que contienen en su nombre alguna coincidencia
	 * con la lista de sufijos que se parametrizan.
	 * 
	 * @param nombreDirectorio Llave de identificacion del directorio en Amazon S3.
	 * @param sufijos          Lista de sufijos.
	 */
	@Override
	public void eliminarArchivosPorSufijo(String nombreDirectorio, List<String> sufijos) throws NegocioException {
		try {
			if(amazonS3 != null)
				amazonS3.eliminarListaArchivos(nombreBucketAwsS3, nombreDirectorio, sufijos);
		} catch (Exception e) {
			e.printStackTrace();
			throw mgrExc.lanzarExcepcion(119, TipoErrorNegocio.ERROR);
		}
	}
	
	/**
	 * Crea URL para descarga de archivos en Amazon S3.
	 * 
	 * @param idDocumento 		Llave de identificacion del documento en Amazon S3.
	 * @param nombreArchivo		Nombre de archivo que descarga.
	 * @throws NegocioException 
	 */
	@Override
	public URL generarUrlDescarga(String idDocumento, String nombreArchivo) throws NegocioException {
		try {
			return amazonS3.generarURLDescarga(nombreBucketAwsS3, idDocumento, UtilConstantes.AWS_TIME_URL_M, nombreArchivo);
		} catch (Exception e) {
			e.printStackTrace();
			throw mgrExc.lanzarExcepcion(125, TipoErrorNegocio.ERROR);
		}
	}
	
	@Override
	public NotificacionAvaluosRespType enviarNotificacion(NotificacionAvaluosRqType Request)
			throws NegocioException {		
		return this.clienteNotificacionesAseguradorasService.enviarNotificacion(Request);
		
	}

}

