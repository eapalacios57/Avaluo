package com.asesoftware.util.cons;

public abstract class Constantes {
	 /**
	  * Constantes para el manejo de los errores generales del sistema.
	  */
	public static final String MGR_X_DEF= "ASESOFTWARE_";
	public static final String RUTA__PROPERTIES_MENSAJES="resources.mensajes_es";
	public static final String ESTRUCTURA_MENSAJE = "MSJ_";
	//Codigos correspondientes a los mensajes de error generalizados.
	public static final int ID_EXCEPCION_APLICACION_NO_CONTROLADA = 100;
	public static final int ID_MSG_ERROR_ARC_RUTA_NO_EXISTE = 11;
	public static final int ID_MSG_ERROR_ARC_NO_EXISTE = 13;
	public static final int ID_MSG_ERROR_ARC_NO_PUEDE_LEER = 14;
	
	public static final String RUTA_ARCHIVO = "aplicacion.ruta";

	//Constantes de configuracion del Logger
	public static final String LOGGER_TYPE = "data.base.logger.type.config";
	public static final String LOGGER_TYPE_XML = "XML";
	public static final String LOGGER_FILE = "data.base.logger.file.config";
	public static final String LOGGER_TYPE_PROPERTIES = "PROP";
	public static final String LOGGER_PRINCIPAL = "aplicacion";

	//Constantes de configuracion de comandos
	public static final String RUTA_EJECUCION_SH = "cargue.archivo.masivo";
	//Constante de configuracion de directorio del usuario del sistema operativo
	public static final String USER_DIRECTORY = "user.dir";
	public static final String FILE_SEPARATOR = "file.separator";
	//Constante de configuracion de nombre del usuario del sistema operativo
	public static final String USER_NAME = "user.name";
	public static final String FILE_NAME_SH = "cargue.archivo.name";
	public static final String FORMATO_FECHA = "YYYYMMDD";
	public static final String FORMATO_FECHA_GUION = "dd/MM/YYYY";
	
	
}
