package com.segurosbolivar.avaluos.modelo.service.impl;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.transaction.UserTransaction;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import com.asesoftware.util.archivo.Scalr;
import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilTexto;
import com.davivienda.esquemas.filenet.documentotipo.v1.DocumentoTipo;
import com.davivienda.esquemas.filenet.otraspropiedadtipo.v1.OtrasPropiedadTipo;
import com.davivienda.esquemas.filenet.propiedadtipo.v1.PropiedadTipo;
import com.davivienda.esquemas.framework.aplicaciontipo.v1.AplicacionTipo;
import com.davivienda.esquemas.framework.canaltipo.v1.CanalTipo;
import com.davivienda.esquemas.framework.consumidortipo.v1.ConsumidorTipo;
import com.davivienda.esquemas.framework.contextosolicitudtipo.v1.ContextoSolicitudTipo;
import com.davivienda.esquemas.framework.operacioncanaltipo.v1.OperacionCanalTipo;
import com.davivienda.esquemas.framework.serviciotipo.v1.ServicioTipo;
import com.davivienda.esquemas.framework.terminaltipo.v1.TerminalTipo;
import com.davivienda.filenet.srvscngestorfilenet.v1.MsjRespOpCargarDocumento;
import com.davivienda.filenet.srvscngestorfilenet.v1.MsjSolOpCargarDocumento;
import com.segurosbolivar.avaluos.modelo.cons.TipoIdentificacion;
import com.segurosbolivar.avaluos.modelo.data.AnexoFotograficoDao;
import com.segurosbolivar.avaluos.modelo.data.ArchivoDao;
import com.segurosbolivar.avaluos.modelo.data.ListaAnexosPdfDao;
import com.segurosbolivar.avaluos.modelo.data.ParametrizacionDao;
//import com.segurosbolivar.avaluos.modelo.dto.ConsultaFileNetDto;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.Archivo;
import com.segurosbolivar.avaluos.modelo.entity.IPersona;
import com.segurosbolivar.avaluos.modelo.entity.ListaAnexosPdf;
import com.segurosbolivar.avaluos.modelo.entity.Parametrizacion;
import com.segurosbolivar.avaluos.modelo.facade.IIntegradorFacade;
import com.segurosbolivar.avaluos.modelo.service.IArchivo;
import com.segurosbolivar.avaluos.modelo.service.util.InputStreamDataSource;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;

/**
 * Implementacion del servicio de archivo
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:38 a.m.
 */
@Stateless
public class ArchivoImpl implements IArchivo {

    private static final long serialVersionUID = -2088678805488718651L;

    /**
     * Permite realizar el manejo de errores y la recuperacion de los mensajes
     * respectivos segun la excepcion lanzada.
     */
    private static final Logger log = Logger.getLogger(ArchivoImpl.class);

    @EJB
    private ManejadorErroresNegocio mgrExc;

    @EJB
    private IIntegradorFacade integradorFacade;

    @EJB
    private AnexoFotograficoDao anexoFotograficoDao;

    @EJB
    private ListaAnexosPdfDao listaAnexosPdfDao;
    
    @EJB
    private ArchivoDao archivoDao;
    
    @EJB
	private ParametrizacionDao parametrizacionDao;
	
//    @EJB
//    private ParametrizacionDao ParametrosConsultaImagenesFileNet;

    @Resource
    UserTransaction transaccion;

//    @Override
//    @Deprecated
//    public void guardarFotografia(ListaAnexosPdf anexo, UsuarioDto usuario) throws Exception {
//	try {
//	    String resultado = guardarEnFilenet(new ByteArrayInputStream(anexo.getAnexo()), anexo.getArchivo().getNombreArchivo(), usuario, anexo.getAvaluo());
//	    if (UtilTexto.estaVacio(resultado)) {
//		throw mgrExc.lanzarExcepcion(27, TipoErrorNegocio.ERROR);
//	    }
//	    anexo.getArchivo().setIdDocumento(resultado);
//	    anexo.getArchivo().setContenidoArchivo(null);
//	    anexo.setFechaTransaccion(new Date());
//	    anexo.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
//	    anexo.getArchivo().setFechaTransaccion(new Date());
//	    anexo.getArchivo().setUsuarioTransaccion(usuario.getUsuario().getCodigo());
//	    if (anexo.getIdListaAnexosPdf() == null || listaAnexosPdfDao.buscar(anexo.getIdListaAnexosPdf()) == null) {
//		anexo.getArchivo().setFechaCreacion(new Date());
//		anexo.getArchivo().setUsuarioCreacion(usuario.getUsuario().getCodigo());
//		anexo.setFechaCreacion(new Date());
//		anexo.setUsuarioCreacion(usuario.getUsuario().getCodigo());
//		archivoDao.crear(anexo.getArchivo());
//		anexo.setIdArchivo(anexo.getArchivo().getIdArchivo());
//		listaAnexosPdfDao.crear(anexo);
//	    } else {
//		archivoDao.actualizar(anexo.getArchivo());
//		anexo.setIdArchivo(anexo.getArchivo().getIdArchivo());
//		listaAnexosPdfDao.actualizar(anexo);
//		log.info("Se actualizo el documento: " + resultado + ". para el  ID archivo: " + anexo.getArchivo().getIdArchivo());
//	    }
//	} catch (Exception e) {
//	    throw new NegocioException(TipoErrorNegocio.ERROR, e.getMessage());
//	}
//    }

    @Override
	public void guardarFotografiaMultiples(List<ListaAnexosPdf> anexos,List<ListaAnexosPdf> listaFotos, UsuarioDto usuario, boolean guardarEnS3)
			throws NegocioException {
    	log.info("Ingresando al metodo que guarda fotografias multiples");
		try {
			// Guardamos los archivos todos en una sola peticion
//			List<ConsultaFileNetDto> documentos = null;
			if (guardarEnS3 && !anexos.isEmpty()) {
				log.info("Procesando registros fotograficos en AWS .....");
//				documentos = procesarResponseIngresarConsultar(guardarMultiplesFilenetConsulta(anexos, usuario, anexos.get(0).getAvaluo()));
//				InputStream inputStream = new ByteArrayInputStream(anexos.get(0).getAnexo());
				
			}

			// los recorremos y obtenemos
			for (ListaAnexosPdf anexo : anexos) {
				if (guardarEnS3) {
					anexo.getArchivo().setContenidoArchivo(null);
				}
				anexo.setFechaTransaccion(new Date());
				anexo.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
				anexo.getArchivo().setFechaTransaccion(new Date());
				anexo.getArchivo().setUsuarioTransaccion(usuario.getUsuario().getCodigo());
				if (anexo.getIdListaAnexosPdf() == null
						|| listaAnexosPdfDao.buscar(anexo.getIdListaAnexosPdf()) == null) {
					anexo.getArchivo().setFechaCreacion(new Date());
					anexo.getArchivo().setUsuarioCreacion(usuario.getUsuario().getCodigo());
					anexo.setFechaCreacion(new Date());
					anexo.setUsuarioCreacion(usuario.getUsuario().getCodigo());
					archivoDao.crear(anexo.getArchivo());//crea el archivo (la foto) en TBL archivos
					anexo.setIdArchivo(anexo.getArchivo().getIdArchivo());
					listaAnexosPdfDao.crear(anexo);
					if (guardarEnS3) {
						anexo.getArchivo()
								.setIdDocumento(UtilConstantes.RUTA_FOTOS + "/" + anexo.obtenerNombreFotoUnico());//Calcula el id unico para guardar en s3
						integradorFacade.crearArchivo(anexo.getArchivo().getIdDocumento(),
								new ByteArrayInputStream(anexo.getAnexo())); //guarda el archivo (foto) en s3 con el id documento anteriormente almacenado
						archivoDao.actualizar(anexo.getArchivo());//actualiza la tabla archivos para que quede almacenado el id documento con q quedo guardado en s3
					}
				} else {
					integradorFacade.crearArchivo(anexo.getArchivo().getIdDocumento(),
							new ByteArrayInputStream(anexo.getAnexo())); //actualiza el archivo (foto) en s3 con el id documento anteriormente almacenado
					archivoDao.actualizar(anexo.getArchivo());
					anexo.setIdArchivo(anexo.getArchivo().getIdArchivo());
					listaAnexosPdfDao.actualizar(anexo);
				}
				if(listaFotos != null) {
					listaFotos.add(anexo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(TipoErrorNegocio.ERROR, e.getMessage());
		}
	}

//    @Deprecated
//    private List<ConsultaFileNetDto> procesarResponseIngresarConsultar(List<DocumentoResponse> response) {
//	if (response == null || response.isEmpty())
//	    return Collections.emptyList();
//	List<ConsultaFileNetDto> retorno = new ArrayList<>();
//	for (DocumentoResponse documentoResponse : response) {
//	    List<com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.consultaringresar.Propiedad> propiedades = documentoResponse.getPropiedades().getPropiedad();
//	    if (propiedades == null || propiedades.isEmpty()) {
//	    	continue;
//	    }
//	    ConsultaFileNetDto documentoAgregar = new ConsultaFileNetDto();
//	    for (com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.consultaringresar.Propiedad propiedad : propiedades) {
//		if (ConsultaFileNetDto.TITULO_DOCUMENTO.equalsIgnoreCase(propiedad.getClave()))
//		    documentoAgregar.setTituloDocumento(propiedad.getValor());
//		else if (ConsultaFileNetDto.ID_DOCUMENTO_INGRESAR.equalsIgnoreCase(propiedad.getClave()))
//		    documentoAgregar.setIdentificador(propiedad.getValor());
//	    }
//	    if (UtilTexto.estaVacio(documentoAgregar.getIdentificador()) || UtilTexto.estaVacio(documentoAgregar.getTituloDocumento()))
//	    {
//	    	continue;
//	    }
//	    retorno.add(documentoAgregar);
//	}
//	return retorno;
//    }

//    private String buscarId(List<ConsultaFileNetDto> documentos, ListaAnexosPdf anexo) {
//	if (documentos == null || documentos.isEmpty())
//	    return null;
//	for (ConsultaFileNetDto buscar : documentos) {
//	    if (buscar.getTituloDocumento() == null || !buscar.getTituloDocumento().equals(anexo.obtenerNombreUnico()))
//	    {
//	    	continue;
//	    }	
//	    return buscar.getIdentificador();
//	}
//	return null;
//    }

//    private String obtenerIdentificadorFilenet(DocumentoResponse resultado) throws NegocioException {
//	List<com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.consultaringresar.Propiedad> propiedades = resultado.getPropiedades().getPropiedad();
//	if (propiedades == null || propiedades.isEmpty())
//	    throw mgrExc.lanzarExcepcion(27, TipoErrorNegocio.ERROR);
//	ConsultaFileNetDto documentoAgregar = new ConsultaFileNetDto();
//	for (com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.consultaringresar.Propiedad propiedad : propiedades) {
//	    if (ConsultaFileNetDto.ID_DOCUMENTO_INGRESAR.equalsIgnoreCase(propiedad.getClave())) {
//		documentoAgregar.setIdentificador(propiedad.getValor());
//		break;
//	    }
//	}
//	if (UtilTexto.estaVacio(documentoAgregar.getIdentificador()))
//	    throw mgrExc.lanzarExcepcion(27, TipoErrorNegocio.ERROR);
//	return documentoAgregar.getIdentificador();
//    }

    @Override
    public void guardarArchivo(IPersona persona, Archivo archivo, UsuarioDto usuario, String llaveArchivo) throws Exception {
	try {
		if (archivo.getAnexo() != null && archivo.isModificado()) {
		integradorFacade.crearArchivo(llaveArchivo, new ByteArrayInputStream(archivo.getAnexo()));
		archivo.setIdDocumento(llaveArchivo);
		archivo.setContenidoArchivo(null);
	    } else if (UtilTexto.estaVacio(archivo.getNombreArchivo())) {
	    	archivo.setNombreArchivo("VACIO");
	    }
	    archivo.setFechaTransaccion(new Date());
	    archivo.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
	    if (archivo.getIdArchivo() == null || archivoDao.buscar(archivo.getIdArchivo()) == null) {
		archivo.setFechaCreacion(new Date());
		archivo.setUsuarioCreacion(usuario.getUsuario().getCodigo());
		archivoDao.crear(archivo);
	    } else {
		archivoDao.actualizar(archivo);
	    }
	    archivo.setModificado(false);
	} catch (Exception e) {
	    throw new NegocioException(TipoErrorNegocio.ERROR, e.getMessage());
	}
    }
    
    @Override
    public void guardarArchivo(String llaveArchivo, byte[] archivo) throws Exception {
    	try {
    		integradorFacade.crearArchivo(llaveArchivo, new ByteArrayInputStream(archivo));
    	} catch (Exception e) {
    		throw new NegocioException(TipoErrorNegocio.ERROR, e.getMessage());
    	}
    }

//    @Override
//    public int ingresarDocumento(InputStream documentoDeIngreso, String nombreArchivo, Map<String, String> metadata) throws NegocioException {
//	Documento documento = new Documento();
//	documento.setTaxonomia(UtilConstantes.ID_TAXONOMIA);
//	DataSource dataSource = new InputStreamDataSource(documentoDeIngreso, nombreArchivo);
//	documento.setMimeType(dataSource.getContentType());
//	DataHandler manejadior = new DataHandler(dataSource);
//	documento.setArchivo(manejadior);
//	Propiedades propiedades = new Propiedades();
//	for (Map.Entry<String, String> entry : metadata.entrySet()) {
//	    com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.ingresar.Propiedad propiedad = new com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.ingresar.Propiedad();
//	    propiedad.setClave(entry.getKey());
//	    propiedad.setValor(entry.getValue());
//	    propiedades.getPropiedad().add(propiedad);
//	}
//	documento.setPropiedades(propiedades);
//	List<Documento> documentos = new ArrayList<>();
//	documentos.add(documento);
//	IngresarDocumentoResponse respuesta = integradorFacade.crearDocumentoFilenet(documentos);
//	return respuesta.getResponseCode();
//    }

//    @Override
//    public com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.consultar.Documento consultarDocumento(List<Propiedad> listaPropiedades) throws NegocioException, IOException {
//	ConsultarDocumentoResponse respuesta = integradorFacade.consultarDocumentoFilenet(listaPropiedades);
//	if (respuesta.getResponseCode() != 0) {
//	    throw new NegocioException(TipoErrorNegocio.ERROR, respuesta.getDtls().get(0).getErrorDescription());
//	}
//	List<com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.consultar.Documento> documento = respuesta.getDocumentos() != null ? respuesta.getDocumentos().getDocumento()
//		: null;
//	if (documento == null || documento.isEmpty())
//	    throw new NegocioException(TipoErrorNegocio.ERROR, "No se pudo consultar el documento");
//	return documento.get(documento.size() - 1);
//    }

//    public List<com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.consultar.Documento> consultarDocumentosMultiples(List<Propiedad> listaPropiedades)
//	    throws NegocioException, IOException {
//	ConsultarDocumentoResponse respuesta = integradorFacade.consultarDocumentoFilenet(listaPropiedades);
//	if (respuesta.getResponseCode() != 0) {
//	    throw new NegocioException(TipoErrorNegocio.ERROR, respuesta.getDtls().get(0).getErrorDescription());
//	}
//	if (respuesta.getDocumentos() == null || respuesta.getDocumentos().getDocumento().isEmpty())
//	    throw new NegocioException(TipoErrorNegocio.ERROR, "No se pudo consultar los documentos");
//	return respuesta.getDocumentos().getDocumento();
//    }

    @Override
    public byte[] obtenerDocumento(String idDocumento) throws NegocioException, IOException {
    	log.info("Inicio obtener documento s3");
		if (UtilTexto.estaVacio(idDocumento))
		    throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		long startTime = System.currentTimeMillis();
		byte[] foto = integradorFacade.obtenerArchivo(idDocumento);
		long endTime = System.currentTimeMillis() - startTime; // tiempo (ms) en que se demora obteniendo el documento
		log.info("Finaliza obtener documento s3 (ms): "+endTime);
		System.out.println("**************AVALUOS*************** Finaliza obtener documento s3 (ms): "+endTime);
		return foto;
    }
    
    @Override
    public byte[] obtenerDocumentoAvaluoMotor(String idDocumento) throws NegocioException, IOException {
    	log.info("Inicio obtener documento s3");
		if (UtilTexto.estaVacio(idDocumento))
		    throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		long startTime = System.currentTimeMillis();
		byte[] foto = integradorFacade.obtenerArchivo(idDocumento);
		long endTime = System.currentTimeMillis() - startTime; // tiempo (ms) en que se demora obteniendo el documento
		log.info("Finaliza obtener documento s3 (ms): "+endTime);
		System.out.println("**************AVALUOS*************** Finaliza obtener documento s3 (ms): "+endTime);
		return foto;
    }
    @Override
	public List<String> obtenerDocumentosporconsecutivo(String consecutivo) throws NegocioException, IOException {
		// TODO Auto-generated method stub
		log.info("Inicio obtener documento s3");
		if (UtilTexto.estaVacio(consecutivo)) {
			log.info("Inicio obtener documento s3 consecutivo vacio");
		    throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		}
		log.info("Inicio obtener documento s3 consecutivo"+ consecutivo);	
		long startTime = System.currentTimeMillis();
		List<String> fotos = integradorFacade.obtenerArchivosporconsecutivo(consecutivo);
		long endTime = System.currentTimeMillis() - startTime; // tiempo (ms) en que se demora obteniendo el documento
		log.info("Finaliza obtener documento s3 (ms): "+endTime);
		System.out.println("**************AVALUOS*************** Finaliza obtener documento s3 (ms): "+endTime);
		
		return fotos;
	}	
	
    @Override
    public byte[] obtenerDocumentoMin(String idDocumento) throws Exception, NegocioException, IOException {
    	log.info("Inicio obtener documento s3 miniatura");
		if (UtilTexto.estaVacio(idDocumento))
		    throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		String[] idDocumentoPath = idDocumento.split("/");
		String idDocumentoMin = UtilConstantes.MINIATURA + "/" + "fotos" + "/" +  idDocumentoPath[1];
		log.info(idDocumentoMin);
		long startTime = System.currentTimeMillis();
		byte[] foto = integradorFacade.obtenerArchivoMin(idDocumentoMin);	
		long endTime = System.currentTimeMillis() - startTime; // tiempo (ms) en que se demora obteniendo el documento
		log.info("Finaliza obtener documento s3 min (ms): "+endTime);
		System.out.println("**************AVALUOS*************** Finaliza obtener documento s3 (ms): "+endTime);
		return foto;
    }
    
   
	public byte[] dimensionarFoto(byte[] foto) throws NegocioException, IOException {
		InputStream in = new ByteArrayInputStream(foto);
		BufferedImage originalImage = ImageIO.read(in);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int ancho = originalImage.getWidth();
		int alto = originalImage.getHeight();
		AffineTransform at = AffineTransform.getRotateInstance(90);
		if (ancho < alto) {
			ImageIO.write(toBufferedImage(originalImage), "jpg", baos);
			baos.flush();
			return baos.toByteArray();
		}
		ImageIO.write(originalImage, "jpg", baos);
		baos.flush();
		return baos.toByteArray();
	}
       
	public static BufferedImage toBufferedImage(BufferedImage img) {
		int ancho = img.getWidth();
		int alto = img.getHeight();
		int escalaAncho = (int) (ancho * 1.2);
		int escalaAlto = (int) (alto * 0.8);
		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(escalaAncho, escalaAlto, img.getType());
		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		bGr.drawImage(img, 0, 0, escalaAncho, escalaAlto, 0, 0, ancho, alto, null);
		bGr.dispose();
		// Return the buffered image
		return bimage;

	}
	
    @Override
    public String obtenerDocumentoURL(String idDocumento, String nombreArchivo) throws NegocioException, IOException {
		if (UtilTexto.estaVacio(idDocumento))
		    throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		return integradorFacade.generarUrlDescarga(idDocumento, nombreArchivo).toString();
    }
    
    @Override
	public byte[] obtenerDocumentoCompreso(String idDocumento) throws NegocioException, IOException {
    	byte[] raw = obtenerDocumento(idDocumento);
		// convert byte array to BufferedImage
		InputStream in = new ByteArrayInputStream(raw);
		// Compress image
		BufferedImage originalImage = ImageIO.read(in);
		Double width = originalImage.getWidth() * 0.8;
		BufferedImage compressImage = Scalr.resize(originalImage, Scalr.Mode.BEST_FIT_BOTH,width.intValue());
		// Convert BufferedImage to byte[]
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(compressImage, "jpg", baos);
		baos.flush();
		return baos.toByteArray();
	}
  
    public Double mejorPorcentaje (int width){
        int valor = width-225;
        Double porcentaje;
        if(valor < 1){
         porcentaje = 0.8;
        }
        else{
            porcentaje = width/(double)valor;
        }
        return porcentaje;
    }


    
	@Override
	public void borrarDocumento(Archivo archivo, String llave) throws NegocioException {
		if (!archivo.getIdDocumento().isEmpty()) {
			borrarArchivo(archivo.getIdDocumento());
		}
		archivoDao.eliminar(archivo);
	}

    @Override
    public void borrarDocumento(Long idArchivo) throws NegocioException {
    	archivoDao.eliminarPorId(idArchivo);
    }
  
    
    @Override
    public void borrarDoc(String idDocumento) throws NegocioException {
        	if(!idDocumento.isEmpty() && archivoDao.obtenerCantidadFotos(idDocumento) < 1) {//Si hay más registros con el mismo idDocumento
        		borrarDocumento(idDocumento);
    	}
    }
    
    @Asynchronous
    public void borrarDocumento(String idDocumento) {
    			borrarArchivo(idDocumento);
    }  

   
    @Override
	public void guardarEnBus(InputStream archivo, String nombre, UsuarioDto usuario, IPersona persona)
			throws NegocioException, IOException {
		if (archivo == null || nombre == null) {
			throw mgrExc.lanzarExcepcion(28, TipoErrorNegocio.ERROR);
		}
		log.info("!!!!!!NOMBRE ARCHIVO A ENVIAR:: " + nombre);
		String respuesta = ingresarDocumentoDav(archivo, nombre, usuario, persona);
		log.info("Respuesta del bus de Davivienda :" + respuesta);
		if (!respuesta.equals("B")) {
			throw mgrExc.lanzarExcepcion(29, TipoErrorNegocio.ERROR);
		}

	}
    
    
	@Override
	public String ingresarDocumentoDav(InputStream documentoDeIngreso, String nombreArchivo, UsuarioDto usuario,
			IPersona avaluo) throws NegocioException {
		MsjSolOpCargarDocumento solCargar = new MsjSolOpCargarDocumento();
		int pocisionLista = 0;
		String idTransaccion = "";
		InetAddress address;
		
		String ID_SERVICIO = null;
		String JORNADA = null;
		String PAIS = null;
		String IDIOMA = null;
		String MONEDA = null;
		String CONSUMIDOR = null;
		String ID_APLICACION = null;
		Short ID_CANAL = null;
		String ID_HOST = null;//FIXME pendiente confirmar con Jhordy
		Integer ID_TERMINAL = null;//FIXME pendiente confirmar con Jhordy
		String COD_USUARIO = null;
		String ID_PERFIL = null;
		String CLASE_DOCUMENTO = null;
		String MIMETYPE = null;
		String CAMPO_TIPO_DOCUMENTO = null;
		String TIPO_DOCUMENTO = null;
		String CAMPO_TIPO_IDENTIFICACION = null; 
		String CAMPO_NRO_IDENTIFICACION = null;
		String CAMPO_TITULO_DOCUMENTO = null; 
		
		List<Parametrizacion> ParametrosReqFilenetDavivienda = parametrizacionDao.getTiposParametro(UtilConstantes.TIPO_PARAMETRO_REQUEST_WS_FILENET_DAVIVIENDA);
		for (Parametrizacion parametro : ParametrosReqFilenetDavivienda) {
			if(parametro.getNombreparametro().equals("ID_SERVICIO")) {
				ID_SERVICIO = parametro.getValorparametro();
			}else if(parametro.getNombreparametro().equals("JORNADA")) {
				JORNADA = parametro.getValorparametro();
			}else if(parametro.getNombreparametro().equals("PAIS")) {
				PAIS = parametro.getValorparametro();
			}else if(parametro.getNombreparametro().equals("IDIOMA")) {
				IDIOMA = parametro.getValorparametro();
			}else if(parametro.getNombreparametro().equals("MONEDA")) {
				MONEDA = parametro.getValorparametro();
			}else if(parametro.getNombreparametro().equals("CONSUMIDOR")) {
				CONSUMIDOR = parametro.getValorparametro();
			}else if(parametro.getNombreparametro().equals("ID_APLICACION")) {
				ID_APLICACION = parametro.getValorparametro();
			}else if(parametro.getNombreparametro().equals("ID_CANAL")) {
				ID_CANAL = Short.valueOf(parametro.getValorparametro());
			}else if(parametro.getNombreparametro().equals("ID_HOST")) {
				ID_HOST = parametro.getValorparametro();
			}else if(parametro.getNombreparametro().equals("ID_TERMINAL")) {
				ID_TERMINAL = Integer.valueOf(parametro.getValorparametro());
			}else if(parametro.getNombreparametro().equals("COD_USUARIO")) {
				COD_USUARIO = parametro.getValorparametro();
			}else if(parametro.getNombreparametro().equals("ID_PERFIL")) {
				ID_PERFIL = parametro.getValorparametro();
			}else if(parametro.getNombreparametro().equals("CLASE_DOCUMENTO")) {
				CLASE_DOCUMENTO = parametro.getValorparametro();
			}else if(parametro.getNombreparametro().equals("MIMETYPE")) {
				MIMETYPE = parametro.getValorparametro();
			}else if(parametro.getNombreparametro().equals("CAMPO_TIPO_DOCUMENTO")) {
				CAMPO_TIPO_DOCUMENTO = parametro.getValorparametro();
			}else if(parametro.getNombreparametro().equals("TIPO_DOCUMENTO")) {
				TIPO_DOCUMENTO = parametro.getValorparametro();
			}else if(parametro.getNombreparametro().equals("CAMPO_TIPO_IDENTIFICACION")) {
				CAMPO_TIPO_IDENTIFICACION = parametro.getValorparametro();
			}else if(parametro.getNombreparametro().equals("CAMPO_NRO_IDENTIFICACION")) {
				CAMPO_NRO_IDENTIFICACION = parametro.getValorparametro();
			}else if(parametro.getNombreparametro().equals("CAMPO_TITULO_DOCUMENTO")) {
				CAMPO_TITULO_DOCUMENTO = parametro.getValorparametro();
			}
		}
		
		
    	String direccionIp = "SegBolivar";
		try {
			address = InetAddress.getLocalHost();
			direccionIp = address.getHostAddress();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		
		try {
			Calendar fechaExpedicionDoc = Calendar.getInstance();
			//String fechaExpedicion = UtilFecha.dateToString("dd-MM-yyyy HH:mm", fechaExpedicionDoc.getTime());
			GregorianCalendar gcal = new GregorianCalendar();
			gcal.setTime(fechaExpedicionDoc.getTime());
			XMLGregorianCalendar fec = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);			
			// CONSUMIDOR
			AplicacionTipo aplicacionTipo = new AplicacionTipo();
			aplicacionTipo.setIdAplicacion(ID_APLICACION);
			ConsumidorTipo consumidorTipo = new ConsumidorTipo();
			consumidorTipo.setAplicacion(aplicacionTipo);
			consumidorTipo.setIdConsumidor(CONSUMIDOR);
			CanalTipo canalTipo = new CanalTipo();
			canalTipo.setIdCanal(ID_CANAL);
			canalTipo.setIdHost(ID_HOST);
			consumidorTipo.setCanal(canalTipo);
			// TERMINAL TIPO
			TerminalTipo terminalTipo = new TerminalTipo();
			terminalTipo.setCodUsuario(COD_USUARIO);
			terminalTipo.setIdTerminal(ID_TERMINAL);
			terminalTipo.setValOrigenPeticion(direccionIp);
			terminalTipo.setValPerfil(ID_PERFIL);
			consumidorTipo.setTerminal(terminalTipo);
			// OPERACION CANAL
			
			String nroIdCliente = String.valueOf(avaluo.getNumeroIdentificacion());
			idTransaccion = nroIdCliente.substring(nroIdCliente.length()-3) + "" + gcal.get(Calendar.DAY_OF_MONTH) + "" + (gcal.get(Calendar.MONTH)+1) + "" + gcal.get(Calendar.HOUR_OF_DAY);
			OperacionCanalTipo operacionCanal = new OperacionCanalTipo();
			operacionCanal.setCodIdioma(IDIOMA);
			operacionCanal.setCodMoneda(MONEDA);
			operacionCanal.setCodPais(PAIS);
			operacionCanal.setFecOperacion(DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal.get(Calendar.YEAR), gcal.get(Calendar.MONTH)+1, gcal.get(Calendar.DAY_OF_MONTH), gcal.get(Calendar.HOUR_OF_DAY), gcal.get(Calendar.MINUTE), gcal.get(Calendar.SECOND), DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED));
			operacionCanal.setIdSesion(usuario.getUsuario().getCodigo()+new Date().getTime());
			operacionCanal.setIdTransaccion(idTransaccion);
			operacionCanal.setValJornada(JORNADA);
			// SERVICIO
			ServicioTipo servicioTipo = new ServicioTipo();
			servicioTipo.setIdServicio(ID_SERVICIO);
			// CONTEXTO SOLICITUD
			ContextoSolicitudTipo contextoSolicitud = new ContextoSolicitudTipo();
			contextoSolicitud.setOperacionCanal(operacionCanal);
			contextoSolicitud.setConsumidor(consumidorTipo);
			contextoSolicitud.setServicio(servicioTipo);
			// DOCUMENTO TIPO
			DocumentoTipo documento = new DocumentoTipo();
			documento.setValClaseDocumento(CLASE_DOCUMENTO);
			DataSource dataSource = new InputStreamDataSource(documentoDeIngreso, nombreArchivo);
			//System.out.println("********MIME TYPE INTERNO: "+dataSource.getContentType());//application/octet-stream			
			documento.setValMimeType(MIMETYPE);
			DataHandler manejadior = new DataHandler(dataSource);
			documento.setValContenido(manejadior);
			
			
			OtrasPropiedadTipo propiedades = new OtrasPropiedadTipo();
			Map<String, String> parametros;
			parametros = new HashMap<>();
			parametros.put(CAMPO_TIPO_IDENTIFICACION, convertidorTiposDocumentos(avaluo.getIdTipoIdentificacion().intValue()));
			parametros.put(CAMPO_NRO_IDENTIFICACION, avaluo.getNumeroIdentificacion().toString());
			parametros.put(CAMPO_TIPO_DOCUMENTO, TIPO_DOCUMENTO);
			parametros.put(CAMPO_TITULO_DOCUMENTO, avaluo.getNumeroIdentificacion().toString()+"_AVALUO");
//			parametros.put("fechaExpedicion", fechaExpedicion);
			List<Map<String, String>> metadatos = new ArrayList<Map<String, String>>();
			metadatos.add(parametros);
			Map<String, String> metadata = metadatos.get(pocisionLista++);
			String extension = "";
			int i = 0;
			for (Map.Entry<String, String> entry : metadata.entrySet()) {
				PropiedadTipo propiedad = new PropiedadTipo();
				if(entry.getKey().equals("DocumentTitle")) {
					// Segun la extension que venga el documento se añade al final para enviar a davivienda
					i = nombreArchivo.lastIndexOf('.');
					if (i > 0) {
						extension = "."+nombreArchivo.substring(i+1);
					}
				}
				propiedad.setValLlave(entry.getKey());
				propiedad.setValValor(entry.getValue() + extension);
				extension="";
				propiedades.getPropiedad().add(propiedad);
			}
			documento.setOtrasPropiedades(propiedades);
			solCargar.setDocumento(documento);
			solCargar.setContextoSolicitud(contextoSolicitud);
			log.info("Se va a enviar documento al bus de Davivienda");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		MsjRespOpCargarDocumento respuesta = integradorFacade.crearDocumentoDav(solCargar);
		if(respuesta.getContextoRespuesta().getError() != null) {
			log.error("Transaccion "+ idTransaccion +"WS Filenet respondio con error: "+respuesta.getContextoRespuesta().getError().getCodMensajeRespuesta()+" - "+respuesta.getContextoRespuesta().getError().getCodMensajeRespuestaBackend()+" - "+respuesta.getContextoRespuesta().getError().getValMensajeRespuesta()+" - "+respuesta.getContextoRespuesta().getError().getValMensajeRespuestaBackend());
		}else {
			log.info("Transaccion "+ idTransaccion +" Id Documento recibido del WS Filenet: "+respuesta.getValIdDocumento());
		}
		
		
		return respuesta.getContextoRespuesta().getResultadoTransaccion().getValCaracterAceptacion();
	}
    
	public String convertidorTiposDocumentos(int tipoDocumento) {
		switch (tipoDocumento) {
		case 21:
			return TipoIdentificacion.CEDULA_CIUDADANIA.getValor();//FIXME jordy indico qeu 01, 02 y 03, pero sale error, validar si es sin el 0 a la izquierda
		case 22:
			return TipoIdentificacion.CEDULA_EXTRANJERIA.getValor();
		default:
			return TipoIdentificacion.NIT.getValor();
		}
	}
    /**
     * {@inheritDoc}
     * 
     * @author arincon
     */
//    public void consultaArchivosNoEnviados() throws NumberFormatException, NegocioException, SQLException {
//	log.info(this.getClass().getName() + ":inicia el job de envio de imagenes a filenet ");
//	try {
//	    String estadoJob = ParametrosConsultaImagenesFileNet.getParametro(UtilConstantes.PARAMETROS_JOB_FILENET, UtilConstantes.ESTADO_PARAMETRIZACION).getValorparametro();
//	    if (estadoJob == null || !EstadoRegistro.ACTIVO.getValor().equals(estadoJob)) {
//		log.info(this.getClass().getName() + ":El job se encuentra desactivado.");
//		return;
//	    }
//	} catch (Exception e) {
//	    log.info(this.getClass().getName() + ":No existe o no se puede consultar el parametro con el estado del job. Se cancela ejecución.");
//	    return;
//	}
//	// Obtiene la cantidad de registros maximos a consultar
//	int maxArchivos = Integer
//		.parseInt(UtilPropiedades.cargarPropiedad(UtilPropiedades.leerProperties(UtilConstantes.RUTA_PROPERTIES), UtilConstantes.PARAMETRO_RESULTADOS_JOB_FILENET));
//	// Obtiene la cantidad de meses anteriores a tomar
//	int mesesAnteriores = Integer.parseInt(ParametrosConsultaImagenesFileNet
//		.getParametro(UtilConstantes.PARAMETROS_MIGRACION_FILENET, UtilConstantes.PARAMETRO_MESES_ANTERIORES_JOB_FILENET).getValorparametro());
//	log.info(this.getClass().getName() + ":cantidad de avaluos a procesar:" + maxArchivos);
//	// Obtiene los resultados a partir de la consulta JPA
//	List<ArchivosFileNetDto> archivosEnvio = listaAnexosPdfDao.consultaArchivosNoEnviados(maxArchivos, mesesAnteriores);
//	log.info("cantidad de archivos consultados a procesar: " + (archivosEnvio == null ? 0 : archivosEnvio.size()) + " archivos. ");
//	if (archivosEnvio == null || archivosEnvio.isEmpty())
//	    return;
//	for (ArchivosFileNetDto archivosFileNetDto : archivosEnvio) {
//	    try {
//		guardarEnFilenet(archivosFileNetDto);
//	    } catch (Exception e) {
//		log.error(e);
//	    }
//	}
//	log.info(this.getClass().getName() + ":Finaliza el job de envio de imagenes a filenet ");
//    }

	@Override
	public File obtenerDirectorio(String nombre) throws NegocioException {
		Parametrizacion parametroRutaWLS = parametrizacionDao.getParametro(UtilConstantes.TIPO_PARAMETRO_RUTAS_SERVIDOR,  UtilConstantes.NOMBRE_PARAMETRO_RUTA_PPAL);
		
		String rutaDescarga = parametroRutaWLS.getValorparametro();
		File directorioDestino = new File(rutaDescarga);
		if(!directorioDestino.exists())
			directorioDestino.mkdir();
		integradorFacade.obtenerDirectorio(nombre, directorioDestino);
		return new File(rutaDescarga + nombre);
	}

	@Override
	public void borrarArchivo(String nombreDocumento) {
		log.info("Eliminar archivo S3, borrarArchivo, inicio, ID " + nombreDocumento);
		try {
			integradorFacade.eliminarArchivo(nombreDocumento);
			log.info("Eliminar archivo S3, borrarArchivo, fin, ID " + nombreDocumento);
		} catch (NegocioException e) {
			log.info("Eliminar archivo S3, borrarArchivo, error, ID " + nombreDocumento + ". ERROR: " + e.getMessage());
			e.printStackTrace();
		}
	}

	
}