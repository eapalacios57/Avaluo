package com.asesoftware.util.exception;

import java.util.ResourceBundle;

import com.asesoftware.util.archivo.UtilPropiedades;
import com.asesoftware.util.cons.Constantes;

/**
 * Implementacion del recuperador estandar de los mensajes basicos de la
 * libreria, en especial para el validador y los diferentes utilities del
 * proyecto.
 * 
 * @author stilaguy
 *
 */
public final class RecuperadorGrl extends IRecuperador {

	/**
	 * Instancia para la implementacion del patron SINGLETON, como lo sugiere la
	 * interfaz IRecuperador para cada una de sus implementaciones.
	 */
	private static RecuperadorGrl instancia;

	/**
	 * Contiene los mensajes de error generales de la libreria.
	 */
	private ResourceBundle archivo = ResourceBundle.getBundle(Constantes.RUTA__PROPERTIES_MENSAJES);

	/**
	 * Constructor privado para el manejador generar con el fin de realizar la
	 * correcta implementacion del patro SINGLETON
	 * 
	 */
	private RecuperadorGrl() {
	}

	/***
	 * Permite recuperar la instancia unica del manejador general con el fin de
	 * cumplir conm la exigencia del patro SINGLETON para todas las instancias
	 * de IRecuperador
	 * 
	 * @return
	 */
	public static RecuperadorGrl getInstance() {
		if (instancia == null)
			instancia = new RecuperadorGrl();
		return instancia;
	}

	@Override
	public String getId() {
		return Constantes.MGR_X_DEF;
	}

	@Override
	public MensajeDto obtenerMensaje(Long llave, Object[] parametros, boolean especifico) {
		return new MensajeDto(UtilPropiedades.cargarPropiedad(archivo,
				Constantes.ESTRUCTURA_MENSAJE + this.getId() + llave, parametros));
	}


}
