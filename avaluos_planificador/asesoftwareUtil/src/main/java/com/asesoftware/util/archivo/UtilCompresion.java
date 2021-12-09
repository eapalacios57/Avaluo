package com.asesoftware.util.archivo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Clase para la compresion de archivos en formato ZIP
 * @author Seguros Bolivar
 * @version 1.0
 * @created 31-ago-2017 10:30:47 a.m.
 */
public class UtilCompresion {

	private static final int BUFFER = 4096;

	/**
	 * Comprime en formato gzip un archivo.
	 *
	 * @param from
	 *            Ruta del archivo que se va a comprimir
	 * @param to
	 *            ruta donde se va a dejar el comprimido
	 * @throws IOException
	 */
	public static void gzipFile(String from, String to) throws IOException {
		FileInputStream in = new FileInputStream(from);
		GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(to));
		byte[] buffer = new byte[BUFFER];
		int bytesRead;
		while ((bytesRead = in.read(buffer)) != -1)
			out.write(buffer, 0, bytesRead);
		in.close();
		out.close();
	}

	/**
	 * Comprime en formato zip los contenidos de un directorio
	 *
	 * @param dir
	 *            ruta del directorio cuyo contenido se quiere comprimir.
	 * @param zipfile
	 *            Define la ruta y el nombre del archivo que se va a comprimir
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	public static void zipDirectory(String dir, String zipfile) throws IOException, IllegalArgumentException {
		// Verifica que el directorio es un directorio y recupera sus contenidos
		File d = new File(dir);
		if (!d.isDirectory())
			throw new IllegalArgumentException("No es un directorio:  " + dir);
		String[] entries = d.list();
		byte[] buffer = new byte[BUFFER]; // Crea un buffer para copiar
		int bytesRead;

		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));

		for (int i = 0; i < entries.length; i++) {
			File f = new File(d, entries[i]);
			if (f.isDirectory())
				continue;// ignora el directorio
			FileInputStream in = new FileInputStream(f); // Stream para lectura
															// del archivo
			ZipEntry entry = new ZipEntry(f.getName()); // Hace una entrada zip
			out.putNextEntry(entry); // Almacena la entrada
			boolean bandera = false;

			while ((bytesRead = in.read(buffer, 0, buffer.length)) > 0) {
				bandera = true;
				out.write(buffer, 0, bytesRead);
			}

			if (!bandera) {
				in.close();
				out.close();
				throw new IOException("Error. Se encontro un contenido del .zip con 0 Bytes: " + entry.getName());
			}
			in.close();
		}
		out.close();
	}
}
