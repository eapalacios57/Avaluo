package com.segurosbolivar.avaluos.cons;

/**
 * 
 * @author stilaguy
 *
 */
public final class AvaluosCons {

	private AvaluosCons() {
	}

	public static final String RUTA_PROPERTIES_MENSAJES = "resources.errores_es";
	public static final String RUTA_PROPERTIES_ETIQUETAS = "resources.etiquetas_es";
	public static final String RUTA_PROPERTIES_IMAGENES = "resources.images";
	public static final String MGR_EXCEPCIONES_WEB = "CO_535_";
	public static final String ESTRUCTURA_MENSAJE_GRAL = "MSJ_GRL_";
	public static final String MEN_ESPECIFICIO = "_ESP";
	public static final String CREDENCIAL = "CREDENCIAL";
	public static final String RUTA_PAG_IMPRESION = "/imprimir.faces";
	public static final String PAGINA_INICIO = "inicio";
	public static final String PAGINA_REINICIO = "reinicio";
	public static final String PAGINA_IMPRESION = "imprimir";
	public static final String RUTA_PAG_DESCARGA = "/descargar.faces";
	public static final String PAGINA_AVALUO = "avaluo";
	public static final String PAGINA_CARGUE = "cargueMasivo";
	public static final String PAGINA_INFORME_COMERCIAL = "consultarAvaluo";
	public static final String NO_AUTORIZADO = "noAutorizado";
	public static final String RUTA_NO_AUTORIZADO = "/noautorizado.faces";
	//public static final String RUTA_LOGIN = "/login.faces";
	public static final String RUTA_LOGIN_HTML = "/login.html";

	public static final String FORMATO_MATRICULA = "^[0-9a-zA-Z]{1,4}-[0-9a-zA-Z]{3,10}$";
	public static final String FORMATO_MATRICULA_MEN = "AAAA-AAAAAAAAAA";
	public static final String LABEL_CONSULTA_USUARIOS = "CONSULTA DE USUARIO";
	public static final String URL_CAPACITACION = "https://www.bolnet.com.co/ekp/servlet/ekp/verify?UID=USUARIO_CAP&PWD=PWD_CAP";
	public static final String DOMINIO_USER_CAPACITACION = "USUARIO_CAPACITACION";
	public static final String PARAMETRO_USUARIO_CAPACITACION = "USUARIO_CAP";
	public static final String PARAMETRO_PWD_CAPACITACION = "PWD_CAP";

	public static final String LATITUD_DEFAULT = "4.668131";
	public static final String LONGITUD_DEFAULT = "-74.061815";
	public static final String XMIN_DEFAULT = "-74.45085037";
	public static final String YMIN_DEFAULT = "3.730633099";
	public static final String XMAX_DEFAULT = "-73.986125794";
	public static final String YMAX_DEFAULT = "4.836827395";

    // Parametros de configuracion FCA
    public static final String TIPO_PARAMETRO_WS_FCA = "WS_FCA";
    public static final String NOMBRE_PARAMETRO_URL_LOGOUT = "URL_LOGOUT";

}