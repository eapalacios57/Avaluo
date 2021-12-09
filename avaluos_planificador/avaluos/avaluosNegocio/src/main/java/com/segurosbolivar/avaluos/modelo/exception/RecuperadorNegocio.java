package com.segurosbolivar.avaluos.modelo.exception;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.asesoftware.util.cache.UtilCache;
import com.asesoftware.util.exception.IRecuperador;
import com.asesoftware.util.exception.MensajeDto;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.data.TablaErroresDao;
import com.segurosbolivar.avaluos.modelo.entity.TablaErrores;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;

/**
 * Implementacion del recuperador estandar de los mensajes basicos de la
 * libreria, en especial para el validador y los diferentes utilities del
 * proyecto.
 * 
 * @author stilaguy
 *
 */
@Stateless
public class RecuperadorNegocio extends IRecuperador implements Serializable {

	private static final long serialVersionUID = 135144683539016155L;

	/**
	 * Dao que permite la operacion de consulta en la tabla de errores.
	 */
	@EJB
	private TablaErroresDao erroresDao;

	/**
	 * permite realizar las operaciones de log sobre esta tabla.
	 */
	private static final Logger log = Logger.getLogger(RecuperadorNegocio.class);

	/**
	 * Como constante esta parametrizado el mensaje de no encontrado en caso de
	 * que el mensaje a cargar no se ecuentre en la tabla.
	 */
	private static MensajeDto MENSAJE_NO_ENCONTRADO = new MensajeDto(UtilConstantes.ERROR_NO_ENCONTRADO);



	/**
	 * prefijo para la llave de los errores para la cache
	 */
	private static final String PREFIJO = "ERR_";



	/**
	 * Se consultan los mensajes de error en la base de datos y se crea la cache
	 * respectiva que mantendra los mensajes de error en memoria.
	 */
	public void iniciar() {
	    log.info("Se inicio la consulta de los mensajes de error del aplicativo");
		List<TablaErrores> errores = erroresDao.consultar(new TablaErrores());
		UtilCache.getInstance().agregarRegion(UtilConstantes.CACHE_MENSAJES_ERROR, String.class, TablaErrores.class,
				1000);
		if (errores == null || errores.isEmpty())
			return;
		for (TablaErrores elemento : errores) {
			UtilCache.getInstance().agregarObjeto(UtilConstantes.CACHE_MENSAJES_ERROR, PREFIJO + elemento.getCodigo(),
					elemento);
		}
	}

	@Override
	public String getId() {
		return UtilConstantes.MGR_EXCEPCIONES_NEGOCIO;
	}

	@Override
	public MensajeDto obtenerMensaje(Long llave, Object[] parametros, boolean especifico) {
		// si no se ha cargado los mensajes de error en la cache la consultamos.
		if (!UtilCache.getInstance().contieneRegion(UtilConstantes.CACHE_MENSAJES_ERROR))
			iniciar();
		// una vez iniciada la cache consultamos el mensaje de error.
		TablaErrores error = (TablaErrores) UtilCache.getInstance().obtenerObjeto(UtilConstantes.CACHE_MENSAJES_ERROR,
				PREFIJO + llave);
		log.info(error==null?MENSAJE_NO_ENCONTRADO:error);
		// si el mensaje no existe retronamos el mensaje por defecto para este
		// caso.
		if (error == null)
			return MENSAJE_NO_ENCONTRADO;
		// si el msanej contiene opciones de personalizacion se las aplciamos.
		String mensaje = MessageFormat.format(error.getDescGral(), parametros);
		MensajeDto retornar = new MensajeDto(error.getNombreExcepcion(), mensaje, null);
		// si se ocnfiguro el mensaje para que levante el log generamos el
		// mismo.
		if (!UtilTexto.estaVacio(error.getGeneraLog()) && "S".equals(error.getGeneraLog()))
			log.error(mensaje);
		return retornar;
	}


}
