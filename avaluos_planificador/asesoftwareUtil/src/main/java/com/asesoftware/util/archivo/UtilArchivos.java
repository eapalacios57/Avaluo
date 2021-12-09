package com.asesoftware.util.archivo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import com.asesoftware.util.cons.Constantes;
import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.ManejadorExcepcion;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilTexto;

/**
 * Esta utilidad permite realizar las operaciones para la manipulación de
 * archivos.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:47 a.m.
 */
public class UtilArchivos {

	public UtilArchivos() {

	}

	private static final int BUFFER = 1024;
	public static String SEPARADOR = System.getProperty("file.separator");
	private static final String AB = "01234564789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static SecureRandom random = new SecureRandom();
	private static ManejadorExcepcion mgrExc = ManejadorExcepcion.getDef();

	

	public static File tranformar(ByteArrayOutputStream bo, String ruta) throws Exception {
		if (bo == null)
			return null;
		File retorno = new File(ruta);
		FileOutputStream archivo = null;
		try {
			archivo = new FileOutputStream(ruta);
			archivo.write(bo.toByteArray());
			archivo.close();
		} catch (Exception e) {
			try {
				if (archivo != null)
					archivo.close();
			} catch (Exception er) {
				throw er;
			}
		}
		return retorno;
	}

	public static void guardarArchivo(String ruta, InputStream inputStream) {

		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			in = new BufferedInputStream(inputStream);
			out = new BufferedOutputStream(new FileOutputStream(ruta));
			byte buffer[] = new byte[BUFFER];
			int leidos;
			while ((leidos = in.read(buffer, 0, BUFFER)) != -1) {
				out.write(buffer, 0, leidos);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * Permite obtener la extension del nombre de un archivo, la cual permite
	 * identificar el tipo de archivo.
	 * 
	 * @param nombreArchivo
	 *            nombre del archivo al que obtendremos la extension.
	 * @return extension del archivo, si el nombre esta vacio se retornara "" al
	 *         igual si no posee extension
	 */
	public static synchronized String obtenerExtension(String nombreArchivo) {
		if (UtilTexto.estaVacio(nombreArchivo)) {
			return "";
		}
		int i = nombreArchivo.lastIndexOf('.');
		if (i > 0) {
			return nombreArchivo.substring(i + 1);
		}
		return "";
	}

	/**
	 * Permite quitar la extension al nombre de un archivo.
	 * 
	 * @param nombreArchivo
	 *            nombre del archivo que debemos procesar
	 * @return el nombre del archivo sin la extension, si el nombre esta vacio
	 *         retornara "" en caso de que no exista la extension se devolvera
	 *         el nombre completo.
	 */
	public static synchronized String quitarExtension(String nombreArchivo) {
		if (UtilTexto.estaVacio(nombreArchivo)) {
			return "";
		}
		int i = nombreArchivo.lastIndexOf('.');
		if (i >= 0) {
			return nombreArchivo.substring(0, i);
		}
		return nombreArchivo;
	}

	public void eliminarArchivo(String ruta) {
		File archivo = new File(ruta);
		archivo.delete();
	}

	/**
	 * Elimina el contenido de un directorio
	 *
	 * @param path
	 *            ruta del directorio que se quiere limpiar
	 * @return true si se pudo borrar todo el contenido del directorio
	 */
	public static boolean eliminaDirectorio(File path) {
		boolean deleted = false;
		if (path.exists()) {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if(files[i].isDirectory())
					eliminaDirectorio(files[i]);
				else
					deleted = files[i].delete();
			}
			deleted = path.delete();
		}
		return deleted;
	}

	/**
	 * Formatea una cadena con espacios a la derecha
	 *
	 * @param s
	 *            Cadena a formatear
	 * @param n
	 *            Espacios del formato
	 */
	public static String padRight(String cadena, int numeroEspacios) {
		return String.format("%1$-" + numeroEspacios + "s", cadena);
	}

	/**
	 * Formatea una cadena con espacios a la izquierda
	 *
	 * @param s
	 *            Cadena a formatear
	 * @param n
	 *            Espacios del formato
	 */
	public static String padLeft(String cadena, int numeroEspacios) {
		return String.format("%1$#" + numeroEspacios + "s", cadena);
	}

	/**
	 * Elimina los archivos mas antiguos que un determinados numero de dias
	 *
	 * @param daysBack
	 *            Numero de dias
	 * @param dirWay
	 *            Ruta del directorio
	 */
	public static void deleteFilesOlderThanNdays(int daysBack, String dirWay) {
		File directory = new File(dirWay);
		if (directory.exists()) {
			File[] listFiles = directory.listFiles();
			long purgeTime = System.currentTimeMillis() - ((long) daysBack * 24 * 60 * 60 * 1000);
			for (File listFile : listFiles) {
				if (listFile.lastModified() < purgeTime) {
					if (listFile.isFile()) {
						listFile.delete();
					} else {
						deleteFilesOlderThanNdays(daysBack, listFile.getAbsolutePath());
					}
				}
			}
		}
	}

	/**
	 * Copia un arhivo de una ubicación a otra. Devuelve true si y solo si se
	 * completó la operación correctamente. de otro modo devuelve false
	 *
	 * @param origen
	 * @param destino
	 * @return boolean
	 * @throws NegocioException
	 */
	public static void copiarArchivo(String origen, String destino, String nombreArchivo) throws NegocioException {
		File archivoOrigen;
		File archivoDestino;
		FileInputStream in = null;
		FileOutputStream out = null;
		Integer mensajeError = null;
		boolean validacion;
		try {
			archivoOrigen = new File(origen);
			archivoDestino = new File(destino);
			validacion = archivoOrigen.exists();
			if (!validacion) {
				mensajeError = Constantes.ID_MSG_ERROR_ARC_NO_EXISTE;
				throw new NegocioException("El documento a publicar no existe");
			}
			validacion = archivoOrigen.canRead();
			if (!validacion) {
				mensajeError = Constantes.ID_MSG_ERROR_ARC_NO_PUEDE_LEER;
				throw new NegocioException("El documento a publicar no existe");
			}
			in = new FileInputStream(archivoOrigen);
			out = new FileOutputStream(
					archivoDestino + SEPARADOR + (nombreArchivo == null ? archivoOrigen.getName() : nombreArchivo));
			/**
			 * Mientras se lee de un lado por otro lado se escribe
			 */
			int c;
			while ((c = in.read()) != -1) {
				out.write(c);
			}
		} catch (Exception ex) {
			if (mensajeError == null) {
				throw mgrExc.lanzarExcepcion(Constantes.ID_MSG_ERROR_ARC_RUTA_NO_EXISTE, TipoErrorNegocio.ERROR);
			}
			throw mgrExc.lanzarExcepcion(mensajeError, TipoErrorNegocio.ERROR);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException ex) {
				throw mgrExc.lanzarExcepcion(Constantes.ID_EXCEPCION_APLICACION_NO_CONTROLADA, TipoErrorNegocio.ERROR);
			}
		}
	}

	/**
	 * Metodo que genera el nombre de un archivo de reportes de forma aleatoria
	 *
	 * @author hfabra
	 * @since 15/03/2017
	 * @return el nombre del reporte generado aleatoriamente
	 */
	public static String generarNombreRamdom() {
		StringBuilder sb = new StringBuilder(8);
		for (int i = 0; i < 8; i++) {
			sb.append(AB.charAt(random.nextInt(AB.length())));
		}
		return "Reporte" + sb.toString();
	}

	public static SecureRandom getRandom() {
		return random;
	}

	public static void setRandom(SecureRandom random) {
		UtilArchivos.random = random;
	}
	
	public static List<File> filtrarPorNombre(File directorio, final String nombre){
		if(!directorio.isDirectory() || UtilTexto.estaVacio(nombre))
			return Collections.emptyList();
		File[] archivosFiltrados = directorio.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(nombre);
			}
		});
		return Arrays.asList(archivosFiltrados);
	}
	

	public static File convierteInputStreamAFile(InputStream inputStream, String rutaArchivo) {
		FileOutputStream outputStream = null;
		File archivoSalida = null;
		int read = 0;
		byte[] bytes = new byte[1024];
		try {
			archivoSalida = new File(rutaArchivo);
			outputStream = new FileOutputStream(archivoSalida);
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return archivoSalida;
	}

}