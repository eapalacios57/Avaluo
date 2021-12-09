package com.asesoftware.util.exception;

import java.util.HashMap;
import java.util.Map;

import com.asesoftware.util.cons.TipoErrorNegocio;

/**
 * Permite realizar el manejo de las excepciones de negocio con el fin de
 * procesar los mensajes de error y que estos se ecuentre parametrizados de
 * forma correcta sin llegar a hacer uso de cadenas de caracteres de forma
 * literal.
 * 
 * Para esto se define un listado de manejadores reutilizables, a cada uno se
 * asigna un recuperador de mensajes que implementara la forma en que recupera
 * la descripcion del mensaje y su persolazacion y permite que al lanzar una
 * excepcion se pueda obtener facilmente la descripcion para el error.
 * 
 * @author stilaguy
 *
 */
public class ManejadorExcepcion {

	/**
	 * Listado de instancias del manejador.
	 */
	private static Map<String, ManejadorExcepcion> instancias;
	/**
	 * Archivo que contiene las descripciones de los mensajes de error.
	 */
	private IRecuperador recuperar;

	/**
	 * Constructor privado para el manejador de excepciones, se crea la instancia
	 * con el identificador que permitira su posterior referencia.
	 * 
	 * @param recuperar
	 *            recuperador de mensajes asociado al manejador.
	 */
	private ManejadorExcepcion(IRecuperador recuperar) {
		this.recuperar = recuperar;
	}

	/**
	 * Permite gestionar las instancias necesarias de cada uno de los manejadores y
	 * permite su reutilizacion. si la instancia no existe se creará.
	 * 
	 * @param manejador
	 *            identifiicador de la instancia solicitada.
	 * @param archivo
	 *            archivo que contiene los mensajes de error.
	 * @return instancia asociada al identificador.
	 */
	public static ManejadorExcepcion getInstance(IRecuperador recuperar) {
		ManejadorExcepcion instancia = null;
		if (instancias == null) {
			instancias = new HashMap<>();

		}
		if (instancias.containsKey(recuperar.getId())) {
			return instancias.get(recuperar.getId());
		}
		instancia = new ManejadorExcepcion(recuperar);
		instancia.recuperar = recuperar;
		instancias.put(recuperar.getId(), instancia);
		return instancia;
	}

	/**
	 * Permite recuperar la instancia de los mensajes propios de la libreria de
	 * asesoftware.
	 * 
	 * @return isntancia del manejador por defecto con los mensajes propios de la
	 *         libreria de asesoftware.
	 */
	public static ManejadorExcepcion getDef() {
		return getInstance(RecuperadorGrl.getInstance());
	}

	/**
	 * Permite lanzar una excepcion de negocio unicamente estableciendo el
	 * identificador de la excepcion y de forma opcional el tipo de error. Los
	 * mensajes de error tiene la estructura MSJ_ + identificador del recuperador +
	 * identificador del mensaje.
	 * 
	 * @param id
	 *            identificador del mensaje de la excepcion.
	 * @param tipo
	 *            nivel o clasificacion del error que permitiria determinar el
	 *            tratamiento para el mismo.
	 * @return excepcion con el mensaje especificado.
	 */
	public NegocioException lanzarExcepcion(long id, TipoErrorNegocio tipo) {
		MensajeDto mensaje = recuperar.obtenerMensaje(id, null, false);
		if (TipoErrorNegocio.ALERTA.equals(tipo))
			return new NegocioAlertaException(id, mensaje.getTitulo(), mensaje.getDescripcion(), mensaje.getDetalle());
		return new NegocioException(id, tipo, mensaje.getTitulo(), mensaje.getDescripcion(), mensaje.getDetalle());
	}

	/**
	 * Permite lanzar una excepcion de negocio estableciendo todas sus propiedades,
	 * desde un detalle que complemente el error hasta parametros que permitan la
	 * personalizacion del mensaje de error. Los mensajes de error tiene la
	 * estructura MSJ_ + identificador del manejador + identificador del mensaje.
	 * 
	 * @param id
	 *            identificador del mensaje de la excepcion.
	 * @param tipo
	 *            nivel o clasificacion del error que permitiria determinar el
	 *            tratamiento para el mismo.
	 * @param detalle
	 *            descripcion extendida del error presentado.
	 * @param parametros
	 *            parametros para la personalizacion del mensaje de error si este lo
	 *            permite.
	 * @return
	 */
	public NegocioException lanzarExcepcion(long id, TipoErrorNegocio tipo, String detalle, String[] parametros) {
		MensajeDto mensaje = recuperar.obtenerMensaje(id, parametros, false);
		if (TipoErrorNegocio.ALERTA.equals(tipo))
			return new NegocioAlertaException(id, mensaje.getTitulo(), mensaje.getDescripcion(),
					(mensaje.getDetalle() != null ? mensaje.getDetalle() : "") + " "
							+ (detalle != null ? detalle : ""));
		return new NegocioException(id, tipo, mensaje.getTitulo(), mensaje.getDescripcion(),
				(mensaje.getDetalle() != null ? mensaje.getDetalle() : "") + " " + (detalle != null ? detalle : ""));
	}

}
