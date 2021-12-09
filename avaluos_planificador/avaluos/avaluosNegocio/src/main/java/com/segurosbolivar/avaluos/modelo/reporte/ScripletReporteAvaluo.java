package com.segurosbolivar.avaluos.modelo.reporte;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.entity.LiquidacionAvaluo;
import com.segurosbolivar.avaluos.modelo.entity.ListaAnexosPdf;

import net.sf.jasperreports.engine.JRDefaultScriptlet;

/**
 * Clase Scriptlet para retornar dominios y campos de ubicacion geografica
 * requeridos en el reporte de Avaluos. Esta clase no es llamada directamente
 * por el aplicativo sino por la libreria Jasper Reports al momento de generar
 * la salida del reporte es llamada en el reportem mediante el siguiente nombre
 * $P{REPORT_SCRIPTLET}.nombreMetodo
 */
public class ScripletReporteAvaluo extends JRDefaultScriptlet {

	/**
	 * recibe el listado de LiquidacionAvaluo perteneciente al Entity Avaluo y
	 * si su tamaño es inferior a 10 Retorna esta lista con las 10 liquidaciones
	 * del avaluo esto es requerido porque para la visualizacion del reporte
	 * simpre se despliegan los 10 registros aunque no todas tengan valores
	 * 
	 * @param liquidaciones
	 *            lista de LiquidacionAvaluo a procesar.
	 * @return lista procesada.
	 */
	public List<LiquidacionAvaluo> llenarLiquidaciones(List<LiquidacionAvaluo> liquidaciones) {
		List<LiquidacionAvaluo> retorno = new ArrayList<>();
		if (liquidaciones != null)
			retorno.addAll(liquidaciones);
		if (retorno.size() >= 10)
			return retorno;
		for (int i = retorno.size(); i < 10; i++) {
			retorno.add(new LiquidacionAvaluo());
		}
		return retorno;
	}

	/**
	 * Permite obtener el archivo para la imagen de un anexo.
	 * 
	 * @param imagen
	 *            objeto a verificar si es un anexo y al que se inentara extraer
	 *            el archivo de la imagen anexada.
	 * @return archivo de la imagen anexada si la imagene es un objeto del tipo
	 *         ListaAnexosPdf
	 */
	public ByteArrayInputStream obtenerImagen(Object imagen) {
		if (imagen == null || !(imagen instanceof ListaAnexosPdf))
			return null;
		ListaAnexosPdf lista = (ListaAnexosPdf) imagen;
		if (lista.getAnexo() == null)
			return null;
		return new ByteArrayInputStream(lista.getAnexo());
	}

	/**
	 * Permite obtener el nombre para la imagen de un anexo.
	 * 
	 * @param imagen
	 *            objeto a verificar si es un anexo y al que se inentara extraer
	 *            el nombre de la imagen anexada.
	 * @return nombre de la imagen anexada si la imagene s un objeto del tipo
	 *         ListaAnexosPdf
	 */
	public String obtenerNombre(Object imagen) {
		if (imagen == null || !(imagen instanceof ListaAnexosPdf))
			return "";
		ListaAnexosPdf lista = (ListaAnexosPdf) imagen;
		if (lista.getArchivo() == null || UtilTexto.estaVacio(lista.getArchivo().getNombreArchivo()))
			return "";
		return lista.getArchivo().getNombreArchivo();
	}

}