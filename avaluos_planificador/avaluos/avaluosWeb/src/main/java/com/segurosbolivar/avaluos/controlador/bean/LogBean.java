package com.segurosbolivar.avaluos.controlador.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.asesoftware.util.archivo.UtilPropiedades;
import com.segurosbolivar.avaluos.controlador.bean.general.GeneralAbstractoBean;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;

/**
 * Controlador para el módulo log de la aplicación. Permite descarga del archivo
 * log de la aplicación.
 * 
 * @author Luis Contreras
 * @version 1.0
 */
@ManagedBean
@SessionScoped
public class LogBean extends GeneralAbstractoBean implements Serializable{

	private static final long serialVersionUID = 4165640758916876435L;
	private List<String> logs;

	@Override
	public void inicio() {
		try {
			logs = new ArrayList<>();
			logs.add("Log");
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void confirmarDescarga() {
		mensajeConfirmacion(obtenerEtiqueta("com_descargado"));
	}

	@Override
	public String getPermiso() {
		return null;
	}

	public StreamedContent getFile() {
		try {
			String rutaLog = UtilPropiedades.cargarPropiedad(UtilConstantes.RSC_ERRORES, UtilConstantes.RUTA_LOG)
					+ "logArchivo.txt";
			File fileTxt = new File(rutaLog);
			if (!fileTxt.exists()) {
				fileTxt.createNewFile();
			}
			InputStream stream = new FileInputStream(rutaLog);
			return new DefaultStreamedContent(stream, "text/plain", "logAvaluos.txt");
		} catch (FileNotFoundException e) {
			procesarError(e);
		} catch (IOException e) {
			procesarError(e);
		}
		return null;
	}

	public List<String> getLogs() {
		return logs;
	}

	public void setLogs(List<String> logs) {
		this.logs = logs;
	}

}
