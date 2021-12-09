package com.asesoftware.util.exception;

import java.util.ArrayList;

import javax.ejb.ApplicationException;

import com.asesoftware.util.cons.TipoErrorNegocio;

/**
 * Excepcion para el manejo de errores dentro del sistema permite definir la
 * prioridad de los errores y anidarlos uno con otro.
 * 
 * @author stilaguy
 *
 */
@ApplicationException(rollback=false, inherited=false)
public class NegocioAlertaException extends NegocioException {
	
	private static final long serialVersionUID = 1763111320342053312L;

	public NegocioAlertaException(String mensaje) {
		super(TipoErrorNegocio.ALERTA, mensaje);
	}

	/**
	 * Permite crear una excepcion de negocio con el tipo, el mensaje y el
	 * detalle.
	 * 
	 * @param tipo
	 * @param mensaje
	 * @param detalle
	 */
	public NegocioAlertaException( String mensaje, String detalle) {
		super(0, TipoErrorNegocio.ALERTA, null, mensaje, detalle);
	}

	/**
	 * Constructor para una excepcion de negocio a traves del identificador.
	 * Este constructor solo es usado por el manejador de excepciones. Para
	 * recuperar previamente el mensaje asociado al identificador de la
	 * excepcion.
	 * 
	 * @param id
	 *            identificador de la excepcion.
	 * @param tipo
	 *            tipo de excepcion si no se define por defecto se usara INFO.
	 * @param tiitulo
	 *            identificador del mensaje o titulo.
	 * @param mensaje
	 *            mensaje a modo de titulo del error presentado.
	 * @param detalle
	 *            Si es necesario se pueda dar una informacion mas detallada del
	 *            origen del mensaje.
	 */
	protected NegocioAlertaException(long id, String titulo, String mensaje, String detalle) {
		super(mensaje);
		this.tipo = tipo == null ? TipoErrorNegocio.ALERTA: tipo;
		this.id = id;
		this.titulo = titulo;
		this.detalle = detalle;
		errores = new ArrayList<NegocioException>();
	}

}