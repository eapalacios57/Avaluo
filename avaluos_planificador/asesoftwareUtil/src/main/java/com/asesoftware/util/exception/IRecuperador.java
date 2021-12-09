package com.asesoftware.util.exception;


/**
 * Permite recuperar los mensajes de error que el manejador usara. De esta forma
 * cada proyecto puede implementar su propio recuperador de mensajes de error.
 * 
 * ALERTA** todas las implentaciones del recuperador deben ser en lo posible
 * clases SINGLETON. Con el fin de evitar que se cree mas de una instancia
 * innecesariamente.
 * 
 * @author stilaguy
 *
 */
public abstract class IRecuperador {

	/***
	 * Permite identificar el recuperador de mensajes en uso. tambien cumple con
	 * una estrctura que permite determinar los mensajes de error propios de
	 * esta instancia. Ej. CO_535_ , ASESOFTWARE_
	 */
	public abstract String getId();

	/**
	 * Permite recuperar la descripcioon del mensaje de error, el mensaje se
	 * puede personalizar con el envio asociado de parametros.
	 * 
	 * @param codigo
	 *            identificador del mensaje que permitira recuperarlo
	 * @param parametros
	 *            permite personalizar el mensaje de error
	 * @return el mensaje recuperado con la personalizacion parametrizada.
	 */
	public abstract MensajeDto obtenerMensaje(Long codigo, Object[] parametros, boolean especifico);
	
}
