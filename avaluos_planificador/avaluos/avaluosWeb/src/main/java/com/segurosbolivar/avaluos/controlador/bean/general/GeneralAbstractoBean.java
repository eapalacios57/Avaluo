package com.segurosbolivar.avaluos.controlador.bean.general;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import org.apache.log4j.Logger;

import com.asesoftware.util.archivo.UtilPropiedades;
import com.asesoftware.util.cons.Constantes;
import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.ManejadorExcepcion;
import com.asesoftware.util.exception.NegocioAlertaException;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.cons.AvaluosCons;
import com.segurosbolivar.avaluos.controlador.bean.seguridad.SesionBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.modelo.data.LogErroresDao;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.LogErrores;
import com.segurosbolivar.avaluos.modelo.service.impl.ManejadorErroresNegocio;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;

/**
 * Esta clase provee a todos los controladores de las vistas del sistema, de una
 * funcionalidad común para las principales tareas, como es la navegación entre
 * paginas, el manejo de errores y excepciones, la consulta de listas de valores
 * de dominio.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:42 a.m.
 */
@SuppressWarnings("serial")
public abstract class GeneralAbstractoBean implements Serializable {
	@EJB
    private LogErroresDao LogDao;
	/**
	 * Se define la implementacion de log para registrar los errores en el
	 * aplicativo.
	 */
	protected static final Logger log = Logger.getLogger(GeneralAbstractoBean.class);

	/**
	 * Contiene los valores de las etiquetas usadas en las pantallas Xhtml. de
	 * Primefaces.
	 */
	private static transient ResourceBundle etiquetas = ResourceBundle.getBundle(AvaluosCons.RUTA_PROPERTIES_ETIQUETAS);

	/**
	 * Manejador de las excepciones de la capa de presentacion que permite generar
	 * errores de negocio.
	 */
	@EJB
	protected transient ManejadorErroresNegocio mgrExc;

	/***
	 * Definimos como paso previo para todo controlador la comprobacion de permisos
	 * de acceso a la pantalla. Si la comprobacion es exitosa el controlador ejecuta
	 * la secuencia de inicio especifica de cada controlador.
	 */
	@PostConstruct
	public void postConstruct() {
		if (comprobarPermiso()) {
			inicio();
		}
	}

	/**
	 * Secuencia inicial de proceso de cada controlador especifico. Define los pasos
	 * iniciales una vez se inicie la pantalla por primera vez.
	 */
	public abstract void inicio();

	/**
	 * Define un valor concreto para un permiso asociado que se comprobara por parte
	 * del control de seguridad para verificar que el usuario cuenta con acceso a la
	 * pantalla determinada. Si este valor se define como NULL implicara que la
	 * opcion no necesita permisos especificos por usuario para ingresar a la misma.
	 * No necesariamente que la opcion sea publica y se pueda ingresar sin
	 * autenticacion.
	 * 
	 * @return nombre del permiso asociado a la pantalla del backingbean
	 */
	public abstract String getPermiso();
	
	

	/**
	 * Permite recuperar un valor de las etiquetas asociadas a las pantallas.
	 * 
	 * @param etiqueta:
	 *            llave de la etiqueta a recuperar.
	 * @return valor de la etiqueta.
	 */
	public static String obtenerEtiqueta(String etiqueta) {
		if (!UtilTexto.estaVacio(etiqueta)) {
			return UtilPropiedades.cargarPropiedad(etiquetas, etiqueta, null);
		}
		return "";
	}

	/**
	 * Permite recuoerar el valor de una etiqueta applicando para la misma
	 * parametros para la personalizacion del mensaje.
	 * 
	 * @param etiqueta:
	 *            llave de la etiqueta a recuperar
	 * @param parametros:
	 *            valores con el que se personaliza el mensaje asociado a la
	 *            etiqueta.
	 * @return valor de la etiqueta con los parametros de personalizacion
	 *         determinados.
	 */
	public static String obtenerEtiqueta(String etiqueta, String[] parametros) {
		if (!UtilTexto.estaVacio(etiqueta)) {
			return UtilPropiedades.cargarPropiedad(etiquetas, etiqueta, parametros);
		}
		return "";
	}

	/***
	 * Comprueba si el usuario actual tiene permisos sobre la opcion o pantalla que
	 * controla el backingBean concreto. En caso de que este posea permisos le
	 * asociara el usuario al backingbean. Este metodo es usado por el metodo
	 * postConstruct que se ejecuta una vez se inicializa el backingBean que
	 * necesite un control de permisos. para esto se compara el permiso asociado a
	 * la implementacion del metodo getPermiso..
	 * 
	 * @return verdadero si el usuario tiene permisos sobre la opcion o si para el
	 *         controlador especifico no se determino un permiso.
	 */
	public boolean comprobarPermiso() {
		if (getPermiso() == null)
			return true;
		return SesionBean.getBean().comprobarOpcion(getPermiso());
	}

	/**
	 * Permite obtener el usuario que se ha cargado en la sesion.
	 * 
	 * @return el usuario de la sesion.
	 */
	public UsuarioDto getUsuario() {
		return SesionBean.getBean().getUsuario();
	}

	/***
	 * Permite mostrar un mensaje de error en un lugar especifico de la pantalla.
	 * 
	 * @param form:
	 *            ubicacion en el formulario donde se mostrara el error, corresponde
	 *            al id del componente al que esta asociado el message. se debe
	 *            definir para el mismo la ruta completa.
	 * @param error:
	 *            Contiene la infromacion del error a mostrar: tipo, mensaje, codigo
	 *            de la excepcion.
	 */
	public static void procesarMensajeEspecifico(String form, NegocioException error) {
		UtilJsf.mostrarMensaje(form, error);
	}

	/**
	 * Permite realizar el procesamiento de una excepcion, la cual se manejara de
	 * manera global.
	 * 
	 * @param error:
	 *            excepcion a procesar.
	 */
	public void procesarError(Exception error) {
		procesarError(null, error);
	}

	public static void mensajeConfirmacion(String titulo) {
		PopupErrorBean.getBean().exito(titulo);
	}

	public static void actualizar(String componente) {
		UtilJsf.actualizar(componente);
	}
	public static void foco(String componente) {
		UtilJsf.hacerFoco(componente);
	}

	public static void validador(NegocioException validar) {
		PopupErrorBean.getBean().validar(validar);
	}

	/**
	 * Permite procesar las excepciones que se generan en los controladores, permite
	 * que el mensaje de error se muestre ya sea en un lugar determinado o de forma
	 * global. En caso de que la excepcion tenga errores anidados estos tambien
	 * seran procesados.
	 * 
	 * @param form:
	 *            Determina la ubicacion especifica donde se quiere mostrar el
	 *            mensaje de error. si esta vacio el error se mostrara en el
	 *            contexto global.
	 * @param error:
	 *            excepcion a procesar e identificar.
	 */
	public void procesarError(String form, Exception error) {
		NegocioException negocio = null;
		if (!(error instanceof NegocioException) && !(error instanceof NegocioAlertaException)) {
			error.printStackTrace();
			log.error(error, error);
			StackTraceElement[] mensajeError = error.getStackTrace();
			String mensajeErrorDatos = mensajeError[0].toString() + mensajeError[1]+mensajeError[2]+mensajeError[3];
			LogErrores LogErrores = new LogErrores();
			BigDecimal valorSeq=LogDao.obtenerNumeroSeq();
			String usuario = getUsuario().getUsuario().getCodigo();
			LogErrores.setPpNumerr(valorSeq);
			LogErrores.setPpDeserr(mensajeErrorDatos);
			LogErrores.setPpCodprg("10019042021");
			LogErrores.setPpFecha(Calendar.getInstance().getTime());
			LogErrores.setPpUsuario(usuario);
			try {
				LogDao.crear(LogErrores);
			} catch (NegocioException e) {
				e.printStackTrace();
			}
			//error.getMessage()
			negocio = ManejadorExcepcion.getDef().lanzarExcepcion(Constantes.ID_EXCEPCION_APLICACION_NO_CONTROLADA,
					TipoErrorNegocio.FATAL, "Error "+valorSeq.toString(), null);
		} else {
			negocio = (NegocioException) error;
		}
		switch (negocio.getTipo()) {
		case FATAL:
		case ERROR:
			PopupErrorBean.getBean().ver(negocio);
			log.error(negocio.getId(), error);
			break;
		case ALERTA:
			PopupErrorBean.getBean().ver(negocio);
			break;
		default:
			PopupErrorBean.getBean().ver(negocio);
			break;
		}
	}

	/**
	 * Permite recuperar la zona horaria por defecto.
	 * 
	 * @return zona horaria.
	 */
	public TimeZone getTimeZone() {
		return TimeZone.getDefault();
	}

	/*
	 * getters y setters
	 */

	public static ResourceBundle getEtiquetas() {
		return etiquetas;
	}
	
	public boolean isEditable() {
		boolean resultado = true;
		if(SesionBean.getBean().getPermisosEspecificos().containsKey(UtilConstantes.PERM_CONSULTOR))
			resultado &= !(SesionBean.getBean().getPermisosEspecificos().get(UtilConstantes.PERM_CONSULTOR).compareTo(Boolean.TRUE)==0);
		if(SesionBean.getBean().getPermisosEspecificos().containsKey(UtilConstantes.PERM_ADMINISTRADOR))
			resultado &= !(SesionBean.getBean().getPermisosEspecificos().get(UtilConstantes.PERM_ADMINISTRADOR).compareTo(Boolean.TRUE)==0);
		if(SesionBean.getBean().getPermisosEspecificos().containsKey(UtilConstantes.PERM_DESARROLLADOR))
			resultado &= !(SesionBean.getBean().getPermisosEspecificos().get(UtilConstantes.PERM_DESARROLLADOR).compareTo(Boolean.TRUE)==0);
		if(SesionBean.getBean().getPermisosEspecificos().containsKey(UtilConstantes.PERM_PROCEDATOS))
			resultado &= !(SesionBean.getBean().getPermisosEspecificos().get(UtilConstantes.PERM_PROCEDATOS).compareTo(Boolean.TRUE)==0);
		return resultado;
	}

}