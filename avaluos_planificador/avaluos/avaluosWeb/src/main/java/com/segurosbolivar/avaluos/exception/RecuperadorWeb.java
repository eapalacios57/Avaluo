package com.segurosbolivar.avaluos.exception;

import java.util.ResourceBundle;

import com.asesoftware.util.archivo.UtilPropiedades;
import com.asesoftware.util.exception.IRecuperador;
import com.asesoftware.util.exception.MensajeDto;
import com.segurosbolivar.avaluos.cons.AvaluosCons;

/**
 * Implementacion del recuperador estandar de los mensajes basicos de la
 * libreria, en especial para el validador y los diferentes utilities del
 * proyecto.
 * 
 * @author stilaguy
 *
 */
public final class RecuperadorWeb extends IRecuperador {

	/**
	 * Instancia para la implementacion del patron SINGLETON, como lo sugiere la
	 * interfaz IRecuperador para cada una de sus implementaciones.
	 */
	private static RecuperadorWeb instancia;

	/**
	 * Contiene los mensajes de error generales de la libreria.
	 */
	private ResourceBundle archivo = ResourceBundle.getBundle(AvaluosCons.RUTA_PROPERTIES_MENSAJES);

	/**
	 * Constructor privado para el manejador generar con el fin de realizar la
	 * correcta implementacion del patro SINGLETON
	 * 
	 */
	private RecuperadorWeb() {
	}

	/***
	 * Permite recuperar la instancia unica del manejador general con el fin de
	 * cumplir conm la exigencia del patro SINGLETON para todas las instancias
	 * de IRecuperador
	 * 
	 * @return
	 */
	public static RecuperadorWeb getInstance() {
		if (instancia == null)
			instancia = new RecuperadorWeb();
		return instancia;
	}

	@Override
	public String getId() {
		return AvaluosCons.MGR_EXCEPCIONES_WEB;
	}

	@Override
	public MensajeDto obtenerMensaje(Long llave, Object[] parametros, boolean especifico) {
		return new MensajeDto(UtilPropiedades.cargarPropiedad(archivo,
				AvaluosCons.ESTRUCTURA_MENSAJE_GRAL + llave + (especifico ? AvaluosCons.MEN_ESPECIFICIO : ""),
				parametros));
	}

}
