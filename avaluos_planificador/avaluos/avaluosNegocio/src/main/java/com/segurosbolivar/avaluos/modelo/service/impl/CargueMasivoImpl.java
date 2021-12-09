package com.segurosbolivar.avaluos.modelo.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.log4j.Logger;

import com.asesoftware.util.archivo.UtilPropiedades;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilFecha;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.data.ArchivoDao;
import com.segurosbolivar.avaluos.modelo.data.ArchivoTmpDao;
import com.segurosbolivar.avaluos.modelo.data.AvaluoDao;
import com.segurosbolivar.avaluos.modelo.data.CargueTmpDao;
import com.segurosbolivar.avaluos.modelo.data.CiudadDao;
import com.segurosbolivar.avaluos.modelo.data.DepartamentoDao;
import com.segurosbolivar.avaluos.modelo.dto.CargueMasivoDtoBean;
import com.segurosbolivar.avaluos.modelo.dto.ConsultaCargueMasivoDto;
import com.segurosbolivar.avaluos.modelo.dto.DetalleMasivoDto;
import com.segurosbolivar.avaluos.modelo.dto.ResultadoCargueMasivoDto;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.ArchivosTmp;
import com.segurosbolivar.avaluos.modelo.entity.CargueTmp;
import com.segurosbolivar.avaluos.modelo.entity.VResumenCargue;
import com.segurosbolivar.avaluos.modelo.facade.IIntegradorFacade;
import com.segurosbolivar.avaluos.modelo.service.IArchivo;
import com.segurosbolivar.avaluos.modelo.service.IAvaluo;
import com.segurosbolivar.avaluos.modelo.service.ICargueMasivo;
import com.segurosbolivar.avaluos.modelo.service.IDiligenciamiento;
import com.segurosbolivar.avaluos.modelo.service.IProcesarAvaluo;
import com.segurosbolivar.avaluos.modelo.service.IValidador;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;

/**
 * Implementaci�n del servicio de cargue masivo.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:40 a.m.
 */

@Stateless
public class CargueMasivoImpl implements ICargueMasivo {

    private static final long serialVersionUID = -5872653959899667800L;

    private static final int BUFFER_MAXIMO = 1024;
    @EJB
    private CargueTmpDao cargueDao;
    @EJB
    private AvaluoDao avaluoDao;
    @EJB
    private IArchivo archivoSvc;
    @EJB
    private ArchivoDao archivoDao;
    @EJB
    private ArchivoTmpDao archivoTmpDao;
    @EJB
    private IValidador validadorSvc;
    @EJB
    private IIntegradorFacade integradorFacade;
    @EJB
    private IDiligenciamiento diligenciamientoSvc;
    @EJB
    private IAvaluo avaluoSvc;
    @EJB
    private CiudadDao ciudadDao;
    @EJB
    private DepartamentoDao departamentoDao;
    @EJB
    private IProcesarAvaluo procesarSvc;

    Logger logger = Logger.getLogger(CargueMasivoImpl.class);

    @Override
    public void cargarArchivos() {

    }

    @Override
    public List<ResultadoCargueMasivoDto> consultar(ConsultaCargueMasivoDto consulta, int inicio, int registroxPagina, String campoOrden, SentidoOrientacion sentido) {
	List<VResumenCargue> resultados = cargueDao.consultar(consulta, inicio, registroxPagina, campoOrden, sentido);
	List<ResultadoCargueMasivoDto> cargues = new ArrayList<>();
	for (VResumenCargue resultado : resultados) {
	    ResultadoCargueMasivoDto cargue = new ResultadoCargueMasivoDto();
	    cargue.setNumeroRefCargue(resultado.getNumeroRefCargue().toString());
	    cargue.setConsecutivoBatch(resultado.getConsecutivoBatch() != null ? resultado.getConsecutivoBatch().toString() : "");
	    cargue.setNombreArchivo(resultado.getNombreArchivo() != null ? resultado.getNombreArchivo() : "");
	    cargue.setTipoCargue(resultado.getTipoCargue() != null ? resultado.getTipoCargue() : "");
	    cargue.setUsuarioTransaccion(resultado.getUsuarioTransaccion() != null ? resultado.getUsuarioTransaccion() : "");
	    cargue.setEmpresaAvaluos(resultado.getEmpresaAvaluos() != null ? resultado.getEmpresaAvaluos() : "");
	    cargue.setTotal(resultado.getTotal() != null ? resultado.getTotal().intValue() : 0);
	    cargue.setRechazados(resultado.getRechazados() != null ? resultado.getRechazados().intValue() : 0);
	    cargue.setAplicados(resultado.getAplicados() != null ? resultado.getAplicados().intValue() : 0);
	    cargue.setSinEstado(resultado.getSinEstado() != null ? resultado.getSinEstado().intValue() : 0);
	    cargue.setFechaTransaccion(resultado.getFechaTransaccion() != null ? UtilFecha.dateToString("dd/MM/yy", resultado.getFechaTransaccion()) : "");
	    cargue.setHoraTransaccion(resultado.getFechaTransaccion() != null ? UtilFecha.dateToString("hh:mm:ss", resultado.getFechaTransaccion()) : "");
	    cargues.add(cargue);
	}
	return cargues;
    }

    @Override
    public List<DetalleMasivoDto> consultarDetalle(DetalleMasivoDto referenciaCargue, int inicio, int registroxPagina, String campoOrden, SentidoOrientacion sentido) {
	List<CargueTmp> cargues = cargueDao.obtenerCargueTmpPorRef(referenciaCargue, inicio, registroxPagina, campoOrden, sentido);
	List<DetalleMasivoDto> listaDetalle = new ArrayList<>();
	if (cargues.isEmpty()) {
	    return listaDetalle;
	}
	for (CargueTmp cargue : cargues) {
	    DetalleMasivoDto detalle = new DetalleMasivoDto();
	    detalle.setLineaPlano(cargue.getLineaPlano());
	    detalle.setContenidoLineaPlano(cargue.getContenidoLineaPlano());
	    detalle.setEstadoCargue(cargue.getEstadoCargue());
	    detalle.setMensajeError(cargue.getMensajeError());
	    if (cargue.getFechaTransaccion() != null)
		detalle.setFechaTransaccion(UtilFecha.dateToString("dd/MM/yy", cargue.getFechaTransaccion()));
	    listaDetalle.add(detalle);
	}
	return listaDetalle;
    }
    
    private String obtenerConsecutivo(String linea) {

	StringBuilder contenido = new StringBuilder(linea);
	int i = 0;
	int tamanio = contenido.length();
	while (i < tamanio) {
	    if (contenido.charAt(i) == '|' && contenido.charAt(i + 1) == '|') {
		contenido.insert(i + 1, 0);
	    }
	    i++;
	}
	StringTokenizer tokens = new StringTokenizer(contenido.toString(), "|");
	String consecutivoBanco = "";
	int contador = 0;
	while (tokens.hasMoreTokens()) {
	    String campo = tokens.nextToken();
	    if (contador == 130) {
		consecutivoBanco = campo;
		break;
	    }
	    contador++;
	}

	return consecutivoBanco;
    }

    @Override
    public void procesarCargue(CargueMasivoDtoBean cargue, UsuarioDto usuario, boolean esConstructor) {
		Long consecutivoBatch = cargueDao.obtenerNumeroBatch();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream txt = cargue.getArchivoTxt();
		ArrayList<String> consecutivos = new ArrayList<String>();

		byte[] buffer = new byte[1024];
		int len;
		try {
			while ((len = txt.read(buffer)) > -1 ) {
			    baos.write(buffer, 0, len);
			}
			baos.flush();
			cargue.getArchivoTxt().reset();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		InputStream consecutivosTxt = new ByteArrayInputStream(baos.toByteArray()); 
			
		if(esConstructor) {			
			try {
				consecutivos = generarConsecutivos(consecutivosTxt);
			} catch (NegocioException e) {
			    e.printStackTrace();
			}
			procesarZip(cargue.getArchivoZip(), usuario, cargue.getNombreZip(), consecutivoBatch,consecutivos,true);
		}else {
			procesarZip(cargue.getArchivoZip(), usuario, cargue.getNombreZip(), consecutivoBatch,null,false);
		}
		try {
		    procesarTxt(cargue.getArchivoTxt(), cargue.getNombreTxt(), consecutivoBatch, usuario, esConstructor);
		} catch (NegocioException e) {
		    e.printStackTrace();
		}

		
    }

    private ArrayList<String> generarConsecutivos(InputStream archivo) throws NegocioException {
    	String linea = null;
    	Long numeroLinea = 0L;
    	ArrayList<String> listadoConsecutivos = new ArrayList<String>();
    	try (BufferedReader reader = new BufferedReader(new InputStreamReader(archivo, UtilConstantes.FORMATO_CODIFICACION_ISO_8859));) {
    		listadoConsecutivos= new ArrayList<String>();
    	    while ((linea = reader.readLine()) != null) {
    		numeroLinea++;
    		listadoConsecutivos.add(obtenerConsecutivo(linea));
    	    }
    	} catch (UnsupportedEncodingException e) {
    	    e.printStackTrace();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
		return listadoConsecutivos;
	}

	@Override
    public void validarEstructuraArchivos() {

    }

    private void procesarTxt(InputStream archivo, String nombreArchivo, Long consecutivoBatch, UsuarioDto usuario, boolean esConstructor) throws NegocioException {
	String linea = null;
	Long numeroLinea = 0L;
	try (BufferedReader reader = new BufferedReader(new InputStreamReader(archivo, UtilConstantes.FORMATO_CODIFICACION_ISO_8859));) {
	    Long referenciaCargue = cargueDao.obtenerNumeroSecuencia();
	    while ((linea = reader.readLine()) != null) {
		numeroLinea++;
		Future<String> resultado = procesarSvc.procesarLineaCargue(referenciaCargue, nombreArchivo, linea, numeroLinea, consecutivoBatch, usuario, esConstructor);
		if (resultado.isDone()) {
		    System.out.println("Termino el proceso");
		    System.out.println(resultado.get());
		} else {
		    System.out.println("procesando .....");
		}
	    }
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	} catch (ExecutionException e) {
	    e.printStackTrace();
	}
    }

    private void procesarZip(InputStream archivoZip, UsuarioDto usuario, String nombreArchivo, Long consecutivoBatch, ArrayList<String> consecutivos, boolean esConstructor) {
	int n = 0;
	byte[] buffer = new byte[BUFFER_MAXIMO];
	String nomArchivoZip = nombreArchivo.substring(0, nombreArchivo.length() - 4);
	String rutaDirectorioCargue = UtilPropiedades.cargarPropiedad(UtilConstantes.RSC_ERRORES, UtilConstantes.RUTA_CARGUE_MASIVO) + File.separator + nomArchivoZip
		+ File.separator;
	File directorioCargue = new File(rutaDirectorioCargue);

	try (ZipInputStream zipInputStream = new ZipInputStream(archivoZip);) {
	    ZipEntry zipEntrante = zipInputStream.getNextEntry();
	    while (zipEntrante != null) {
		FileOutputStream fileoutputstream = null;
		FileInputStream fileInputStream = null;
		String nombreZip = zipEntrante.getName();
		File archivoZipEntrante = new File(nombreZip);
		String directorioZip = archivoZipEntrante.getParent();

		if (directorioZip == null) {
		    if (archivoZipEntrante.isDirectory())
			break;
		}

		if (!directorioCargue.exists()) {
		    directorioCargue.mkdirs();
		}

		File archivoZipEntrada = new File(rutaDirectorioCargue + nombreZip);
		if (!archivoZipEntrada.exists()) {
		    archivoZipEntrada.createNewFile();
		}
		fileoutputstream = new FileOutputStream(archivoZipEntrada);

		buffer = new byte[(int) zipEntrante.getSize()];

		while ((n = zipInputStream.read(buffer, 0, BUFFER_MAXIMO)) > -1) {
		    fileoutputstream.write(buffer, 0, n);
		}
		fileoutputstream.close();

		byte[] bytesArchivo = new byte[(int) archivoZipEntrada.length()];
		fileInputStream = new FileInputStream(archivoZipEntrada);
		fileInputStream.read(bytesArchivo);
		fileInputStream.close();

		cargaArchivos(usuario, archivoZipEntrada, consecutivoBatch, bytesArchivo,consecutivos,esConstructor);

		zipInputStream.closeEntry();

		zipEntrante = zipInputStream.getNextEntry();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void cargaArchivos(UsuarioDto usuario, File direct, Long consecutivoBatch, byte[] bytesArchivo, ArrayList<String> consecutivos, boolean esConstructor) throws NegocioException {
		
    	eliminarArchivosDuplicados(direct.getName().trim());
    	
    	if(esConstructor) {
    		for(String consecutivo:consecutivos) {
    			ArchivosTmp archivoTmp = new ArchivosTmp();
    			archivoTmp.setNombreArchivo(consecutivo+"_"+direct.getName());
    			archivoTmp.setTamanioArchivo(BigDecimal.valueOf(direct.length()));
    			archivoTmp.setContenido(bytesArchivo);
    			archivoTmp.setConsecutivoBatch(consecutivoBatch);
    			archivoTmp.setUsuarioCreacion(usuario.getUsuario().getCodigo());
    			archivoTmp.setFechaCreacion(Calendar.getInstance().getTime());
    			archivoTmp.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
    			archivoTmp.setFechaTransaccion(Calendar.getInstance().getTime());
    			archivoTmpDao.crear(archivoTmp);
    		}
    	}else {
    		ArchivosTmp archivoTmp = new ArchivosTmp();
    		archivoTmp.setNombreArchivo(direct.getName());
    		archivoTmp.setTamanioArchivo(BigDecimal.valueOf(direct.length()));
    		archivoTmp.setContenido(bytesArchivo);
    		archivoTmp.setConsecutivoBatch(consecutivoBatch);
    		archivoTmp.setUsuarioCreacion(usuario.getUsuario().getCodigo());
    		archivoTmp.setFechaCreacion(Calendar.getInstance().getTime());
    		archivoTmp.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
    		archivoTmp.setFechaTransaccion(Calendar.getInstance().getTime());
    		archivoTmpDao.crear(archivoTmp);
    	}

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void eliminarArchivosDuplicados(String nombre) {
	List<ArchivosTmp> archivosFotograficos = archivoTmpDao.buscarArchivosPorNombre(nombre);
	if (!archivosFotograficos.isEmpty()) {
	    for (ArchivosTmp archivo : archivosFotograficos) {
		try {
		    archivoTmpDao.eliminar(archivo);
		} catch (NegocioException e) {
		    logger.error(e.getMessage());
		}
	    }
	}
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void cambiaEstadoCargue(String estado, String mensaje, CargueTmp cargueTmp) throws NegocioException {
	cargueTmp.setContenidoLineaPlano(cargueTmp.getContenidoLineaPlano());
	cargueTmp.setEstadoCargue(estado);
	cargueTmp.setMensajeError(mensaje);
	cargueDao.actualizar(cargueTmp);
    }

    @Override
    public int cantidadRegistros(ConsultaCargueMasivoDto consulta) throws NegocioException {
	return cargueDao.cantidadRegistros(consulta);
    }

    @Override
    public Long cantidadRegistros(DetalleMasivoDto consulta) throws NegocioException {
	return cargueDao.cantidadRegistros(consulta);
    }
}