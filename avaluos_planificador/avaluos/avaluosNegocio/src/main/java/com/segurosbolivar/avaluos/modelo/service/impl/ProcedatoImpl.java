package com.segurosbolivar.avaluos.modelo.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.asesoftware.util.archivo.UtilCompresion;
import com.asesoftware.util.archivo.UtilPropiedades;
import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilFecha;
import com.asesoftware.util.lang.UtilNumero;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.cons.EstadoBoleano;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.data.AvaluoDao;
import com.segurosbolivar.avaluos.modelo.data.HistoricoLogGeneraArchDao;
import com.segurosbolivar.avaluos.modelo.data.ListaAnexosPdfDao;
import com.segurosbolivar.avaluos.modelo.data.LogsGeneraArchivoDao;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.HistoricoLogsGeneraArch;
import com.segurosbolivar.avaluos.modelo.entity.ListaAnexosPdf;
import com.segurosbolivar.avaluos.modelo.entity.LogsGeneraArchivo;
import com.segurosbolivar.avaluos.modelo.service.IParametrizacion;
import com.segurosbolivar.avaluos.modelo.service.IProcedato;
import com.segurosbolivar.avaluos.modelo.service.IProcedatoGeneracion;
import com.segurosbolivar.avaluos.modelo.service.IReporte;
import com.segurosbolivar.avaluos.modelo.service.util.PGPFileProcessor;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;

/**
 * Implementaci�n del servicio de procedato
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:46 a.m.
 */
@Stateless
public class ProcedatoImpl implements IProcedato {

	private static final long serialVersionUID = -5863879066571162668L;
	
	private static final Logger log = Logger.getLogger(ProcedatoImpl.class);
	
	@EJB
	private LogsGeneraArchivoDao logsGeneraArchivoDao;
	@EJB
	private HistoricoLogGeneraArchDao historicoDao;
	@EJB
	private AvaluoDao avaluoDao;
	@EJB
	public IParametrizacion parametrizacionSvc;
	@EJB
	public IProcedatoGeneracion generarSvc;
	@EJB
	public IReporte reporteSvc;
	@EJB
	private ManejadorErroresNegocio mgrExc;
	@EJB
	private ListaAnexosPdfDao anexosDao;
	private static ResourceBundle propiedades = UtilConstantes.RSC_ERRORES;

	@Override
	public List<LogsGeneraArchivo> consultar(LogsGeneraArchivo consultar, int inicio, int registroXPagina,
			String campoOrden, SentidoOrientacion sentido) throws NegocioException {
		return logsGeneraArchivoDao.consultar(consultar, inicio, registroXPagina, campoOrden, sentido);
	}

	@Override
	public List<HistoricoLogsGeneraArch> consultar(HistoricoLogsGeneraArch consultar, int inicio, int registroXPagina,
			String campoOrden, SentidoOrientacion sentido) throws NegocioException {
		return historicoDao.consultar(consultar, inicio, registroXPagina, campoOrden, sentido);
	}

	@Override
	public int cantidadRegistros(LogsGeneraArchivo consulta) throws NegocioException {
		return logsGeneraArchivoDao.cantidadRegistros(consulta);
	}

	@Override
	public void generar(Date fecha, UsuarioDto usuario,boolean job) throws NegocioException {
		if (fecha == null)
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		if (logsGeneraArchivoDao.procesoGeneracionActivo())
		    if(!job)
			throw mgrExc.lanzarExcepcion(100, TipoErrorNegocio.ERROR,
					"Existe un proceso de generación activo... Intente mas tarde", null);
		    else {
			log.info("Existe un proceso de generación activo... Intente mas tarde");
			return;
		    }
		String limiteTexto = parametrizacionSvc.consultarDescripcionDominio("LOTE_ENVIO_ASOBANCARIA", "1");
		if (UtilTexto.estaVacio(limiteTexto) || !UtilNumero.esEntero(limiteTexto))
		    if(!job)
			throw mgrExc.lanzarExcepcion(100, TipoErrorNegocio.ERROR,
					"No se encuentra parametrizado el numero de envios por lote para asobancaria, ver dominio LOTE_ENVIO_ASOBANCARIA",
					null);
		    else {
			log.error("No se encuentra parametrizado el numero de envios por lote para asobancaria, ver dominio LOTE_ENVIO_ASOBANCARIA");
			return;
		    }
		List<Avaluo> avaluosAprobados = avaluoDao.consultarAvaluosProcedatos(fecha,
				UtilNumero.pasarEntero(limiteTexto).intValue());
		// Se mapea la lista de entidades a DTO's
		if (avaluosAprobados == null || avaluosAprobados.isEmpty()) {
		    if(!job)
			throw mgrExc.lanzarExcepcion(100, TipoErrorNegocio.INFO,
					"Ya se han procesado todos los avalúos hasta la fecha.", null);
		    else {
			log.info("Ya se han procesado todos los avalúos hasta la fecha.");
			return;
		    }
		}
		// Rutas de cada archivo
		String rutaProyecto = UtilPropiedades.cargarPropiedad(propiedades, UtilConstantes.RUTA_PROYECTO);
		String rutaCarpetaArchivoTxt = rutaProyecto
				+ UtilPropiedades.cargarPropiedad(propiedades, UtilConstantes.NOMBRE_CARPETA_TXT);
		String rutaCarpetaArchivoZip = rutaProyecto
				+ UtilPropiedades.cargarPropiedad(propiedades, UtilConstantes.NOMBRE_CARPETA_ZIP);
		String rutaCarpetaArchivoPgp = rutaProyecto
				+ UtilPropiedades.cargarPropiedad(propiedades, UtilConstantes.NOMBRE_CARPETA_PGP);

		// Definir la creacion de carpeta de archivo plano TXT
		String fechaTexto = UtilFecha.fechaAFormatoddMMyyyySinSeparadores(new Date());
		String nombreCarpetaTxt = "CBUA001039" + fechaTexto + "_" + avaluosAprobados.size();
		String crearCarpetaTxt = rutaCarpetaArchivoTxt + nombreCarpetaTxt;
		// Definir la creacion del archivo plano TXT
		String nombreArchivoTxt = "INMUEBLE_" + fechaTexto + "_" + avaluosAprobados.size() + ".txt";
		String crearArchivoTxt = rutaCarpetaArchivoTxt + nombreCarpetaTxt + "/" + nombreArchivoTxt;
		// Definir el nombre del archivo comprimido ZIP
		String nombreArchivoZip = nombreCarpetaTxt + ".zip";
		// Definir la carpera a comprimir
		String comprimirCarpeta = rutaCarpetaArchivoTxt + nombreCarpetaTxt + ".zip";
		// Definir el nombre del archivo encriptado PGP
		String nombreArchivoPgp = nombreArchivoZip + ".pgp";

		// Definir la ruta de la llave
		String nombreLlave = rutaProyecto
				+ UtilPropiedades.cargarPropiedad(propiedades, UtilConstantes.NOMBRE_LLAVE_ASOBANCARIA);
		String passphrase = "avaluosn";
		try {
			// crear carpera donde se almacenaran todos los archivos TXT
			File newFolderTXT = new File(rutaCarpetaArchivoTxt);
			if (!newFolderTXT.exists())
				newFolderTXT.mkdir();
			// crear carpeta donde se almacenaran todos los archivos ZIP
			File newFolderZIP = new File(rutaCarpetaArchivoZip);
			if (!newFolderZIP.exists())
				newFolderZIP.mkdir();
			// crear carpeta donde se almacenaran todos los archivos encriptados
			// PGP
			File newFolderPGP = new File(rutaCarpetaArchivoPgp);
			if (!newFolderPGP.exists())
				newFolderPGP.mkdir();
			// crear carpeta donde se almacenara el archivo TXT por cada lote de
			// avaluos
			File newFolder = new File(crearCarpetaTxt);
			if (newFolder.exists())
				newFolder.delete();
			newFolder.mkdirs();
			// crear archivo txt
			escribirArchivo(crearArchivoTxt, avaluosAprobados);
			avaluosAprobados = generarArchivos(crearArchivoTxt, avaluosAprobados, nombreCarpetaTxt, rutaProyecto);
			// enviar los avaluos aprobados para guardar cada uno de sus pdfs
			// comprimir carpeta con el archivo txt
//			if(avaluosAprobados.isEmpty())
//				throw mgrExc.lanzarExcepcion(97, TipoErrorNegocio.ERROR);
			UtilCompresion.zipDirectory(crearCarpetaTxt, comprimirCarpeta);
			// Mover archivo zip a su ruta especifica para tener orden de los
			// archivos
			String rutaAnteriorZip = rutaCarpetaArchivoTxt + nombreArchivoZip;
			String rutaNuevaZip = rutaCarpetaArchivoZip + nombreArchivoZip;
			File rutaAnterior = new File(rutaAnteriorZip);
			File rutaNueva = new File(rutaNuevaZip);
			rutaAnterior.renameTo(rutaNueva);
			// Encriptar archivo zip
			String archivoPGP = rutaCarpetaArchivoPgp + nombreArchivoPgp;
			encriptarArchivo(rutaNuevaZip, archivoPGP, passphrase, nombreLlave);
			// guardar datos en la tabla logsGeneraArchivos.
			int cantidadAvaluos = avaluosAprobados.size();
			String linkDescarga = rutaCarpetaArchivoPgp + nombreArchivoPgp;
			guardarLog(nombreArchivoPgp, linkDescarga, nombreArchivoTxt, cantidadAvaluos, avaluosAprobados);
		} catch (NegocioException e) {
			throw mgrExc.lanzarExcepcion(97, TipoErrorNegocio.ERROR, e.getMessage(), null, e);
		}catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			throw mgrExc.lanzarExcepcion(97, TipoErrorNegocio.ERROR, e.getMessage(), null);
		}
	}

	@Override
	public File copiaSeguridadAvaluos(List<Avaluo> avaluosSeleccionados) throws NegocioException {
		// Se obtiene la fecha actual y se aplica formato dd-MM-yyyy
		String fechaTexto = UtilFecha.fechaAFormatoddMMyyyySinSeparadores(new Date());
		// Se mapea la lista de entidades a DTO's
		// Rutas de cada archivo
		String rutaProyecto = UtilPropiedades.cargarPropiedad(propiedades, UtilConstantes.RUTA_PROYECTO);
		// Definir la creacion de carpeta de archivo plano TXT
		String nombreCarpetaRespaldo = fechaTexto + "_" + avaluosSeleccionados.size();
		String crearCarpetaRespaldo = rutaProyecto + nombreCarpetaRespaldo;
		// Definir la creacion del archivo plano TXT
		String nombreArchivoTxt = "INMUEBLE_" + fechaTexto + "_" + avaluosSeleccionados.size() + ".txt";
		String crearArchivoTxt = rutaProyecto + nombreCarpetaRespaldo + "/" + nombreArchivoTxt;
		// Definir la carpera a comprimir
		String comprimirCarpeta = rutaProyecto + nombreCarpetaRespaldo + ".zip";
		File archivoComprimido = null;
		// generar el arreglo de string a setear en el archivo plano
		try {
			// crear carpera donde se almacenaran todos los archivos.
			File newBackupFolder = new File(crearCarpetaRespaldo);
			if (!newBackupFolder.exists())
				newBackupFolder.mkdir();
			// crear archivo txt
			escribirArchivoSeguridad(crearArchivoTxt, avaluosSeleccionados);
			// enviar los avaluos aprobados para guardar cada uno de sus pdfs
			String tipo = "copiaSeguridad";
			obtenerPdfs(avaluosSeleccionados, nombreCarpetaRespaldo, tipo, rutaProyecto);
			// comprimir carpeta con el archivo txt
			UtilCompresion.zipDirectory(crearCarpetaRespaldo, comprimirCarpeta);
			archivoComprimido = new File(comprimirCarpeta);
			return archivoComprimido;
		} catch (Exception e) {
			throw mgrExc.lanzarExcepcion(97, TipoErrorNegocio.ERROR, e.getMessage(), null);
		}
	}

	protected List<Avaluo> generarArchivos(String rutaArchivo, List<Avaluo> avaluos, String nombreCarpeta, String rutaProyecto) throws IOException, NegocioException {
		boolean imprimio;
		List<Avaluo> listaFinal = new ArrayList<Avaluo>();
		FileWriter fw = null;
		Map<Avaluo,byte[]> mapaAvaluos = new HashMap<Avaluo, byte[]>();
		try {
			File archivo = new File(rutaArchivo);
			if (archivo.exists())
				archivo.delete();
			if (!archivo.createNewFile())
				return null;
			fw = new FileWriter(archivo);
			StringBuilder contenido = new StringBuilder();
			
			StringBuilder contenidoLog = new StringBuilder();
			
			Long contadorAvaluos = 0L;
			contenidoLog.append("----------------------------------------------------\n");
			contenidoLog.append("INFO: Se procesan "+avaluos.size()
			+" avaluos aprobados \n");
			
			for(Avaluo avaluo : avaluos) {
				try {
					byte[] pdfObtenido = reporteSvc.generarReporteFotografico(avaluo);
					if(pdfObtenido != null)
						mapaAvaluos.put(avaluo, pdfObtenido);
				} catch (Exception e) {
					System.out.println("ingreso a la excepcion");
				}
			}
			
			if(mapaAvaluos.isEmpty()) {
				return Collections.emptyList();
			}
			
			for(Map.Entry<Avaluo, byte[]> entry: mapaAvaluos.entrySet()) {
				Avaluo avaluo = entry.getKey();
				byte[] pdfObtenido = entry.getValue();
				contenidoLog.append("INFO: Inicia proceso de avaluo : "+avaluo.getConsecutivoBanco()+"\n");
				imprimio=false;
					File crearPdf = null;
					// Nombre del pdf a crear
					String nombrePdf = "registroFotograficoAvaluo" + avaluo.getIdAvaluo().toString() + ".pdf";
					// Ruta donde almacenar el pdf

					crearPdf = new File(rutaProyecto + "/"
							+ UtilPropiedades.cargarPropiedad(propiedades, UtilConstantes.NOMBRE_CARPETA_TXT)
							+ nombreCarpeta + "/" + nombrePdf);

					try {
						// PDF obtenido del repositorio en bytes
						// iniciar la conversion de bytes a File para crear el archivo
						// PDF y guardarlo en disco, para su posterior compresion a zip.
							FileOutputStream fileOuputStream = new FileOutputStream(crearPdf);
							fileOuputStream.write(pdfObtenido);
							fileOuputStream.close();
							imprimio=true;
							contenidoLog.append("INFO: Genero el pdf: "+avaluo.getIdAvaluo()+"\n");

					} catch (Exception e) {
						contenidoLog.append("ERROR: No se pudo generar el PDF para el avaluo de id: "+avaluo.getConsecutivoBanco()
						+" error no identificado\n");
						log.error(e.getMessage() + " " + avaluo.getConsecutivoBanco() + " ");
						throw mgrExc.lanzarExcepcion(112, TipoErrorNegocio.ERROR,
								e.getMessage() + " " + avaluo.getConsecutivoBanco() + " ", null);
					}				
				if(imprimio) {
					listaFinal.add(avaluo);
					contadorAvaluos += 1L;
					contenido.append(generarSvc.generarTexto(avaluo, contadorAvaluos));
					contenidoLog.append("INFO: Imprimio en el txt: "+avaluo.getConsecutivoBanco()+"\n");
					try {
						avaluoDao.actualizar(avaluo);
						contenidoLog.append("Se cambio el estado a transmitido al avaluo "+avaluo.getConsecutivoBanco()+"\n");
					}catch (Exception e) {
						contenidoLog.append("No se pudo cambiar el estado a transmitido al avaluo "+avaluo.getConsecutivoBanco()+"\n");
					}
					
				}

			}
			contenidoLog.append("--------------------------Fin de proceso--------------------------\n");
			contenidoLog.append("INFO: Se procesan "+listaFinal.size()	+" avaluos exitosamente \n");
			contenidoLog.append("INFO:  "+ (avaluos.size() - listaFinal.size())	+" avaluos presentaron errores \n");
			contenidoLog.append("------------------------------------------------------------------\n");
			//escribe el archivo final
			fw.write(contenido.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw mgrExc.lanzarExcepcion(97, TipoErrorNegocio.ERROR, e.getMessage(), null);
		} finally {
			if (fw != null)
				fw.close();			
		}
	return listaFinal;
	}
	
	protected void escribirArchivo(String rutaArchivo, List<Avaluo> avaluos) throws IOException, NegocioException {
		FileWriter fw = null;
		try {
			File archivo = new File(rutaArchivo);
			if (archivo.exists())
				archivo.delete();
			if (!archivo.createNewFile())
				return;
			fw = new FileWriter(archivo);
			StringBuilder contenido = new StringBuilder();

			//			Long contadorAvaluos = 0L;
//			for (Avaluo avaluo : avaluos) {
//				contadorAvaluos += 1L;
//				contenido.append(generarSvc.generarTexto(avaluo, contadorAvaluos));
//			}
			fw.write(contenido.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw mgrExc.lanzarExcepcion(97, TipoErrorNegocio.ERROR, e.getMessage(), null);
		} finally {
			if (fw != null)
				fw.close();
		}
	}
	
	protected void escribirArchivoSeguridad(String rutaArchivo, List<Avaluo> avaluos) throws IOException, NegocioException {
		FileWriter fw = null;
		try {
			File archivo = new File(rutaArchivo);
			if (archivo.exists())
				archivo.delete();
			if (!archivo.createNewFile())
				return;
			fw = new FileWriter(archivo);
			StringBuilder contenido = new StringBuilder();

			Long contadorAvaluos = 0L;
			for (Avaluo avaluo : avaluos) {
				contadorAvaluos += 1L;
				contenido.append(generarSvc.generarTexto(avaluo, contadorAvaluos));
			}
			fw.write(contenido.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw mgrExc.lanzarExcepcion(97, TipoErrorNegocio.ERROR, e.getMessage(), null);
		} finally {
			if (fw != null)
				fw.close();
		}
	}

	@Override
	public void reversar(List<LogsGeneraArchivo> procedatosSeleccionados) throws NegocioException {
		List<Avaluo> reversarAvaluos = new ArrayList<>();
		for (LogsGeneraArchivo procedato : procedatosSeleccionados) {
			procedato.setEstado("V");
			logsGeneraArchivoDao.actualizar(procedato);
			reversarAvaluos.addAll(procedato.getAvaluos());
		}
		reversarAvaluos(reversarAvaluos);
	}

	public void reversarAvaluos(List<Avaluo> reversarAvaluos) throws NegocioException {
		for (Avaluo avaluo : reversarAvaluos) {
			avaluo.setEstadoAvaluo(2L);
			avaluo.setIdArchivo(null);
			avaluo.setNumLinea(null);
			avaluo.setTransmitido(0L);
			avaluoDao.actualizar(avaluo);
		}

	}

	public void encriptarArchivo(String archivoZip, String archivoPGP, String passphrase, String nombreLlave) {

		PGPFileProcessor processor = new PGPFileProcessor();
		processor.setInputFileName(archivoZip);
		processor.setOutputFileName(archivoPGP);
		processor.setPassphrase(passphrase);
		processor.setPublicKeyFileName(nombreLlave);
		try {
			System.out.println(processor.encrypt());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void guardarLog(String nombreArchivoPgp, String linkDescarga, String nombreArchivoTxt, int cantidadAvaluos,
			List<Avaluo> avaluosAprobados) throws NegocioException {
		
		LogsGeneraArchivo entity = new LogsGeneraArchivo();
		entity.setNombreArchivo(nombreArchivoPgp);
		entity.setEstado("G");
		entity.setLinkDescarga(linkDescarga);
		entity.setDescripcionError("Proceso realizado satisfactoriamente." + cantidadAvaluos + " registros generados");
		entity.setUsuarioCreacion("AVALUOS");
		entity.setFechaInicioCreacion(new Date());
		entity.setFechaFinCreacion(new Date());
		entity.setNombrePlano(nombreArchivoTxt);
		entity.setAvaluos(avaluosAprobados);
		logsGeneraArchivoDao.crear(entity);
		for (Avaluo avaluo : avaluosAprobados) {
			avaluo.setTransmitido(EstadoBoleano.TRUE.getValor());
			avaluo.setIdArchivo(entity.getIdLogArchivos());
			avaluoDao.actualizar(avaluo);
			
		}
	}

	public void obtenerPdfs(List<Avaluo> avaluos, String nombreCarpeta, String tipo, String rutaProyecto)
			throws NegocioException {
		for (Avaluo avaluo : avaluos) {
			List<ListaAnexosPdf> anexosPdf = anexosDao.consultaAnexosPorAvaluo(avaluo);
			if (anexosPdf == null || anexosPdf.isEmpty())
				continue;
			File crearPdf = null;
			// Nombre del pdf a crear
			String nombrePdf = "registroFotograficoAvaluo" + avaluo.getIdAvaluo().toString() + ".pdf";
			// Ruta donde almacenar el pdf
			if (tipo.equalsIgnoreCase("procedato")) {
				crearPdf = new File(rutaProyecto + "/"
						+ UtilPropiedades.cargarPropiedad(propiedades, UtilConstantes.NOMBRE_CARPETA_TXT)
						+ nombreCarpeta + "/" + nombrePdf);
			} else if (tipo.equalsIgnoreCase("copiaSeguridad")) {
				crearPdf = new File(rutaProyecto + "/" + nombreCarpeta + "/" + nombrePdf);
			}
			try {
				// PDF obtenido del repositorio en bytes
				byte[] pdfObtenido = reporteSvc.generarReporteFotografico(avaluo);
				// iniciar la conversion de bytes a File para crear el archivo
				// PDF y guardarlo en disco, para su posterior compresion a zip.
				if(pdfObtenido==null) {
					
				}else {
					FileOutputStream fileOuputStream = new FileOutputStream(crearPdf);
					fileOuputStream.write(pdfObtenido);
					fileOuputStream.close();
				}

			} catch (NegocioException e) {
				throw mgrExc.lanzarExcepcion(112, TipoErrorNegocio.ERROR,
						e.getMessage() + " " + avaluo.getConsecutivoBanco() + " ", null, e);
			} catch (Exception e) {
				log.error(e.getMessage() + " " + avaluo.getConsecutivoBanco() + " ");
				throw mgrExc.lanzarExcepcion(112, TipoErrorNegocio.ERROR,
						e.getMessage() + " " + avaluo.getConsecutivoBanco() + " ", null);
			}

		}

	}

	@Override
	public void cambiarEstadoProcedato(List<LogsGeneraArchivo> procedatosSeleccionados) throws NegocioException {
		for (LogsGeneraArchivo procedato : procedatosSeleccionados) {
			LogsGeneraArchivo entity = logsGeneraArchivoDao.buscar(procedato.getIdLogArchivos());
			entity.setEstado("E");
			logsGeneraArchivoDao.actualizar(entity);
			marcarAvaluoTrasmitido(procedato.getAvaluos());
		}

	}

	public void marcarAvaluoTrasmitido(List<Avaluo> avaluos) throws NegocioException {
		for (Avaluo avaluo : avaluos) {
			Avaluo avaluoToUpdate = avaluoDao.buscar(avaluo.getIdAvaluo());
			avaluoToUpdate.setTransmitido(1L);
			avaluoDao.actualizar(avaluoToUpdate);
		}
	}

	@Override
	public void cambiarTransmitido(Long idLogsGeneraArchivo) throws NegocioException {
		LogsGeneraArchivo result = logsGeneraArchivoDao.buscar(idLogsGeneraArchivo);
		for (Avaluo avaluo : result.getAvaluos()) {
			avaluo.setTransmitido(1L);
			avaluoDao.actualizar(avaluo);
		}

	}

}
