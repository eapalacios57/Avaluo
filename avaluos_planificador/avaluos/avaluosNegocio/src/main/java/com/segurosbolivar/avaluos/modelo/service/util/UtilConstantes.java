
package com.segurosbolivar.avaluos.modelo.service.util;

import java.util.ResourceBundle;

/**
 * Clase con constantes consultadas por diferentes clases de la aplicación
 *
 * @author GeneradorCRUD
 */
public class UtilConstantes {

    private UtilConstantes() {
    }

    public static final String CACHE_AVALUOS = "AVALUOS";
    public static final String CACHE_CIUDADES = "CIUDADES";
    public static final String CACHE_DEPARTAMENTOS = "DEPARTAMENTOS";
    public static final String SUFIJO_DOMINIOS = "_DOM";
    public static final String SUFIJO_SERVICIOS_WEB = "_SERV";
    public static final String CACHE_USUARIOS = "USUARIOS";
    public static final String CACHE_REGION_USUARIOS = "REG_USUARIOS";

    // Endpoint client service AWS Amazon
   public static final String AWS_BUCKET_MIN = "test-seguros-bolivar-s3";
    public static final String MINIATURA = "fotos";
    
    public static final int AWS_TIME_URL_M = 2;
    
    // Configuracion Rutas AWS Amazon
    public static final String RUTA_COMPLEMENTOS = "complementos";
    public static final String RUTA_SOPORTES = "soportes";
    public static final String RUTA_LOGOS = "logo";
    public static final String RUTA_FIRMA = "firma";
    public static final String RUTA_FOTOS = "fotos";
    
    public static final String WSDL_EVENTOS = "wsdl.eventos";

    public static final String RUTA_PROPERTIES = "config";
    public static final String CACHE_MENSAJES_ERROR = "AVALUO_ERRORES";
    public static final ResourceBundle RSC_ERRORES = ResourceBundle.getBundle(RUTA_PROPERTIES);
    public static final String SEPARADOR_HTTP_ORDER_BY = "$";
    public static final String CARACTER_DE_ESCAPE = "\\";
    public static final String SEPARADOR_PARAMETROS_CONSULTA = "&";

    // ManejadorCrud
    public static final String NULL_VALUE = "NULL";
    public static final String NOT_NULL_VALUE = "NOT NULL";

    // UtilReflection
    public static final String LONG_PRIMITIVE = "long";
    public static final String INT_PRIMITIVE = "int";

    // CriteriaSearch

    public static final String TYPESAFE_QUERY = "TypesafeQuery";

    public static final String SQL_WHERE = "WHERE";

    public static final String TQ_CONDITIONS = "Conditions";
    public static final String TQ_ORDERING = "Ordering";
    public static final String TQ_GROUPING = "Grouping";

    public static final String EXPRESSION_METHODS = "ExpressionMethods";
    public static final String CRITERIA_METHODS = "CriteriaMethods";
    public static final String COMPOUND_METHODS = "CompoundMethods";

    public static final String EX_IS_NULL = "isNull";
    public static final String EX_IS_NOT_NULL = "isNotNull";
    public static final String EX_IN = "in";

    public static final String CR_EQUAL = "equal";
    public static final String CR_NOT_EQUAL = "notEqual";
    public static final String CR_GREATER_THAN = "gt";
    public static final String CR_GREATER_EQUAL = "ge";
    public static final String CR_LESS_THAN = "lt";
    public static final String CR_LESS_EQUAL = "le";
    public static final String CR_BETWEEN = "between";
    public static final String CR_LIKE = "like";
    public static final String CR_IS_NULL = " IS NULL ";

    public static final String CM_AND = "AND";
    public static final String CM_OR = "OR";
    public static final String CM_NOT = "not";

    public static final String OR_ASC = "asc";
    public static final String OR_DESC = "desc";
    public static final String ERROR_NO_ENCONTRADO = "NO SE ENCUENTRA PARAMETRIZADO EL MENSAJE";

    public static final String MGR_EXCEPCIONES_NEGOCIO = "CO_535_N_";

    public static final String ENDPOINT_ASEGURABILIDAD = "app.endpoint.asegurabilidad";
    public static final String CONSULTA_LATITUD = "&latitud=";
    public static final String CONSULTA_LONGITUD = "&longitud=";
    public static final String METHOD_POST = "POST";
    public static final String METHOD_GET = "GET";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String APPLICATION_PDF = "application/pdf";

    /** Eatdo aprobado del avaluo Abrobado, */
    public static final Long ESTADO_AVALUOS_APROBADO = 3L;
    public static final Long ESTADO_AVALUOS_NUEVOS = 2L;

    public static final Long NO_TRANSMITIDO = 0L;
    /** Mensaje de estado de transacción fallida */
    public static final String ERROR = "app.avaluos.msj.errorGeneral";
    public static final String SEPARADOR_CAMPOS = "|";
    public static final Integer NUMERO_DIEZ = 10;
    /** Tipos de cargue en la tabla pgb_cargue_temporal: validaweb */
    public static final String TIPO_CARGUE_VALIDAWEB = "VALIDAWEB";
    public static final String TIPO_CARGUE_CARGADO = "CARGADO";
    public static final String TIPO_CARGUE_MASIVO = "CARGUE";
    public static final String NOMBRE_ARCHIVO_TEMPORAL = "Temporal";
    /** Estados del cargue en la tabla pgb_cargue_temporal: aplicado */
    public static final String ESTADO_CARGUE_APLICADO_DESCARGA = "APLICADO";
    public static final String MSJ_APROBADO = "APROBADO";

    public static final String NOMBRE_ENTIDAD_DAVIVIENDA = "DAVIVIENDA";
    public static final Long CODIGO_ENTIDAD_DAVIVIENDA = 39L;

    // Configuracion de Filenet
    public static final String BUSINESS_USER = "AVALUOSWEB";
    public static final String ENDPOINT_FICTICIO_NOTIFICACION = "http://localhost/wsdlNotificacionesDav";//La url real queda en el wsdl dentro del jar del cliente WS 
    public static final String ENDPOINT_FICTICIO_ADJUNTOS = "http://localhost/wsdlFilenetDav";//La url real queda en el wsdl dentro del jar del cliente WS

    
    // usuario Contexto
    public static final String CONTEXTO_USUARIO = "CONTEXTO_USUARIO";

    // Configuracion de Reportes
    public static final String RUTA_REPOSITORIO = "app.reportes.repositorio";
    public static final String RUTA_JASPER = "app.reportes.rutaprincipal";
    public static final String NOMBRE_REPORTE_USUARIOS = "reporteusuarios.jrxml";
    public static final String NOMBRE_REPORTE_AVALUOS = "reporteListadoAvaluos.jasper";
    public static final String NOMBRE_INFORME_AVALUOS = "rep_informe_avaluo.jasper";
    public static final String NOMBRE_INFORME_REGISTRO_FOTOGRAFICO = "rep_avaluos_anexos.jasper";
    public static final String RUTA_INFORME_AVALUOS = "informe/";
    public static final String DATASOURCE_USUARIOS = "usuariosDataSource";
    public static final String DATASOURCE_AVALUOS = "avaluosDataSource";

    // Configuracion pgp
    public static final String RUTA_PROYECTO = "app.pgp.rutaproyecto";
    public static final String NOMBRE_CARPETA_TXT = "app.pgp.nombrecarpetatxt";
    public static final String NOMBRE_CARPETA_ZIP = "app.pgp.nombrecarpetazip";
    public static final String NOMBRE_CARPETA_PGP = "app.pgp.nombrecarpetapgp";
    public static final String NOMBRE_LLAVE_ASOBANCARIA = "app.pgp.nombrellaveasobancaria";

    // Configuracion log aplicacion
    public static final String RUTA_LOG = "app.log.repositorio";

    
    public static final String TIPO_PARAMETRO_REPORTES = "REPORTES";
    public static final String NOMBRE_PARAMETRO_DS_USUARIOS = "DATASOURCE_USUARIOS";
    
    
    
    // Direcciones

    public static String ASTERISCO = "*";
    public static String ASTERISCO_HEX = "\\x2A";
    public static String BACKSLASH = "\\";
    public static String BACKSLASH_HEX = "\\x5C";
    public static final String PICO = "�";

    public static final String GUION = "-";
    public static final String GUION_HEX = "\\x2D";
    public static final String NO_PUNTO = "NO.";
    public static final String NO_PUNTO_HEX = "NO\\x2E";
    public static final String N_INTERROGANTE_DER = "N?";
    public static final String N_INTERROGANTE_DER_HEX = "N\\x3F";
    public static final String PARENTESIS_DER = ")";
    public static final String PARENTESIS_DER_HEX = "\\)";
    public static final String PARENTESIS_IZQ = "(";
    public static final String PARENTESIS_IZQ_HEX = "\\(";
    public static final String PESOS = "$";
    public static final String PESOS_HEX = "\\x24";
    public static final String PIPE = "|";
    public static final String PIPE_HEX = "\\x7C";
    public static final String PIPE_KR = "|KR";
    public static final String PIPE_KR_HEX = "\\|KR";
    public static final String PUNTO = ".";
    public static final String PUNTO_HEX = "\\x2E";
    public static final String INTERROGANTE_DER = "?";
    public static final String INTERROGANTE_DER_HEX = "\\x3F";

    public static final Character LETRA_MAYUS_A = 'A';
    public static final Character LETRA_MINUS_A = 'a';
    public static final Character LETRA_MAYUS_Ñ = 'Ñ';
    public static final Character LETRA_MAYUS_N = 'N';
    public static final Character LETRA_MAYUS_B = 'B';
    public static final Character LETRA_MINUS_Ñ = 'ñ';
    public static final Character LETRA_MAYUS_Z = 'Z';
    public static final Character LETRA_MINUS_Z = 'z';
    public static final Character DIGIT_NUEVE = '9';
    public static final Character DIGIT_CERO = '0';
    public static final String LETRA_I = "I";
    public static final String LETRA_N = "N";
    public static final String LETRA_A = "A";
    public static final Character TRES_CEROS = '\000';
    public static final String EXP_ESPACIOS = "[\\s]{2,}";
    public static final String EXP_PATRON = "[a-zñA-ZÑ]+[0-9]+";

    public static final String CONSULTA_BD = "app.consulta.nomenclaturas";

    public static final Character DIGIT_NUMERAL = '#';
    public static final Character DIGIT_CORCHETE_DER = '{';
    public static final Character DIGIT_CORCHETE_IZQ = '}';
    public static final Character DIGIT_TILDE = '`';
    public static final Character DIGIT_GRADOS = '�';
    public static final Character DIGIT_INTERROGACION = '�';
    public static final Character DIGIT_PUNTO_COMA = ';';
    public static final Character DIGIT_MAS = '+';
    public static final Character DIGIT_PUNTO = '.';
    public static final Character DIGIT_COMILLA = '"';
    public static final Character DIGIT_BACKSPACE = '\'';
    public static final Character DIGIT_AMPERSAN = '&';
    public static final Character DIGIT_ADMIRACION = '!';
    public static final Character DIGIT_MENOR_QUE = '<';
    public static final Character DIGIT_MAYOR_QUE = '>';
    public static final Character DIGIT_PESO = '�';

    public static final String CAMPO_DESCRIPCION = "DESCRIPCION";
    public static final String CAMPO_CONVERSION = "CONVERSION";
    public static final String CAMPO_TIPO_CONV = "TIPO_CONV";

    // Permisos especificos del usuario
    public static final String PERM_ADMINISTRADOR = "ADMINISTRADOR";
    public static final String PERM_APROBADOR = "APROBADOR";
    public static final String PERM_APROBADOR_HIJO = "APROBADOR_HIJO";
    public static final String PERM_IMPRESOR = "IMPRESOR";
    public static final String PERM_COSTO_TRANSACCION = "TRANSACCIONA";
    public static final String PERM_ALERTA_ASEGURABILIDAD = "ALERTAASEGURA";
    public static final String PERM_ADMINISTRA_COMPLEMENTOS = "ADMINISTRACOMPLE";
    public static final String PERM_CONSULTOR = "CONSULTOR";
	public static final String PERM_DESARROLLADOR = "DESARROLLADOR";
	public static final String PERM_PROCEDATOS = "PROCEDATOS";
	public static final String PERM_BLOQUEO_NUEVOS = "CONSULTANUEVO";

    public static final int ALT_MATRICULA_REPETIDA = 13;
    public static final int ALT_NUM_PISO_MAMPO = 15;
    public static final int ALT_NUM_PISO_ADOBE = 16;
    public static final int ALT_VETUSTEZ_ADOBE = 17;
    public static final int ALT_NUM_PISO_CONCRE = 18;
    public static final int ALT_NUM_PISO_ELEV = 19;
    public static final int ALT_COMPARATIVO_AVALUO = 108;
    public static final int ALT_CUMULO_CREDITO_CONSTRUCTOR = 109;
    public static final int ALT_ZONA_RIESGO_ALTO = 110;
    public static final String ALT_MATRICULA_REPETIDA_ASUNTO = "Matricula inmobiliaria repetida";
    public static final String ALT_NUM_PISO_MAMPO_ASUNTO = "Mampostería mayor a 8 niveles";
    public static final String ALT_NUM_PISO_ADOBE_ASUNTO = "Adobe mayor a 3 niveles";
    public static final String ALT_VETUSTEZ_ADOBE_ASUNTO = "Adobe menor a 50 años";
    public static final String ALT_NUM_PISO_CONCRE_ASUNTO = "Dual de concreto menor a 8 pisos";
    public static final String ALT_NUM_PISO_ELEV_ASUNTO = "Numero de pisos superior a 60";
    public static final String ALT_COMPARATIVO_AVALUO_ASUNTO = "Comparativo de valor comercial avalúo";
    public static final String ALT_CUMULO_CREDITO_CONSTRUCTOR_ASUNTO = "Cumulo de proyecto No Constructor";
    public static final String ALT_ZONA_RIESGO_ALTO_ASUNTO = "Inmueble en zona de riesgo alto";

    // Parametros de configuracion aws s3
    public static final String TIPO_PARAMETRO_AWS_S3 = "AWS_S3";
    public static final String NOMBRE_PARAMETRO_KEY_ID = "KEY_ID";
    public static final String NOMBRE_PARAMETRO_SECRET_KEY = "SECRET_KEY";
    public static final String NOMBRE_PARAMETRO_BUCKET = "BUCKET";
    public static final String NOMBRE_PARAMETRO_MODE_ACCELERATE = "HABILITAR_MODE_ACCELERATE";
    
    // Parametros de configuracion Davivienda
    public static final String TIPO_PARAMETRO_WS_DAVIVIENDA = "WS_DAVIVIENDA";
    public static final String NOMBRE_PARAMETRO_PATH_KEYSTORE = "PATH_KEYSTORE_WEBLOGIC";
    public static final String NOMBRE_PARAMETRO_PASSWORD_KEYSTORE = "PASSWORD_KEYSTORE_WEBLOGIC";
    public static final String NOMBRE_PARAMETRO_ALIAS_LLAVERO = "ALIAS_LLAVERO_DAVIVIENDA";
    public static final String NOMBRE_PARAMETRO_PASSWORD_LLAVERO = "PASSWORD_LLAVERO_DAVIVIENDA";
    public static final String TIPO_PARAMETRO_REQUEST_WS_NOTIF_DAVIVIENDA = "REQUEST_WS_NOTIFICACION_DAV";
    public static final String TIPO_PARAMETRO_REQUEST_WS_FILENET_DAVIVIENDA = "REQUEST_WS_FILENET_DAV";
    
    // Parametros de configuracion FCA
    public static final String TIPO_PARAMETRO_WS_FCA = "WS_FCA";
    public static final String NOMBRE_PARAMETRO_COD_APP_AVALUOS = "CODIGO_APLICACION_AVALUOS";
    public static final String NOMBRE_PARAMETRO_WSDL_FCA = "ENDPOINT_FCA";
    public static final String NOMBRE_PARAMETRO_WSDL_ASEGURABILIDAD = "ENDPOINT_ASEGURABILIDAD";
    
    // Parametros de configuracion de carpetas weblogic
    public static final String TIPO_PARAMETRO_RUTAS_SERVIDOR = "RUTAS_SERVIDOR";
    public static final String NOMBRE_PARAMETRO_RUTA_PPAL = "RUTA_PRINCIPAL";
    public static final String NOMBRE_PARAMETRO_CARPETA_TXT = "NOMBRE_CARPETA_TXT";
    public static final String NOMBRE_PARAMETRO_CARPETA_ZIP = "NOMBRE_CARPETA_ZIP";
    public static final String NOMBRE_PARAMETRO_CARPETA_PGP = "NOMBRE_CARPETA_PGP";
    
 // Parametros de configuracion de PGP
    public static final String TIPO_PARAMETRO_PGP = "PGP";
    public static final String NOMBRE_PARAMETRO_LLAVE_ASOBANCARIA = "NOMBRE_LLAVE_ASOBANCARIA";
    
    // Parametros de configuracion EVENTOS
    public static final String TIPO_PARAMETRO_WS_EVENTOS = "WS_EVENTOS";
    public static final String NOMBRE_PARAMETRO_WSDL_EVENTOS = "ENDPOINT_EVENTOS";

    // Parametros de configuracion planificador
    public static final String TIPO_PARAMETRO_PLANIFICADOR = "PLANIFICADOR";
    public static final String NOMBRE_PARAMETRO_URL = "URL";
    public static final String NOMBRE_PARAMETRO_SEGURIDAD_PLANIFICADOR = "SEGURIDAD";
    
    // Parametros Generales de configuracion 
    public static final String TIPO_PARAMETRO_GENERALES = "GENERALES";
    public static final String NOMBRE_PARAMETRO_AMBIENTE_EJEC = "AMBIENTE_EJEC";
    
    // Parametros Generales de configuracion 
    public static final String TIPO_PARAMETRO_WS_REST_ARCGIS  = "WS_ARCGIS";
    public static final String NOMBRE_PARAMETRO_WSDL_ARCGIS = "ENDPOINT_ARCGIS";
//  public static final String URL_API_REST_ARCGIS = "http://ap80sbcc01:6080/arcgis/rest/services/Servicios_SB/gpGeocodificacionAvaluos/GPServer/gpGeocodificacionAvaluos/execute?";
//  public static final String URL_API_REST_ARCGIS = "https://www.segurosbolivar.com/arcgis/rest/services/geocodificadores/gpGeocodificacionAvaluos_versionMejorada/GPServer/gpGeocodificacionAvaluos/execute?";
    public static final String NOMBRE_PARAMETRO_ARCGIS_DIVIPOLA = "PARAMETRO_DIVIPOLA";
    public static final String NOMBRE_PARAMETRO_ARCGIS_DIRECCION = "PARAMETRO_DIRECCION";
    public static final String NOMBRE_PARAMETRO_ARCGIS_BARRIO = "PARAMETRO_BARRIO";
    public static final String NOMBRE_PARAMETRO_ARCGIS_FIN = "PARAMETRO_FINAL";
//    public static final String URL_API_REST_ARCGIS_FIN = "&env%3AoutSR=&env%3AprocessSR=&returnZ=false&returnM=false&returnTrueCurves=false&returnFeatureCollection=false&context=&f=html";

    
    // Parametros de notificaciones electronicas
    public static final String PARAMETRIZACION_TIPO_NOTIFICACION = "notificacion";
    public static final String PARAMETRIZACION_NOTIFICACION_DESTINATARIO = "destinatario";
    public static final String PARAMETRIZACION_NOTIFICACION_DESTINATARIO_VETUSTEZMENOR50 = "VetustezMenor50Anios";
    public static final String PARAMETRIZACION_NOTIFICACION_DESTINATARIO_ADOBESUPERIOR = "AdobeSuperior3Niveles";
    public static final String PARAMETRIZACION_NOTIFICACION_DESTINATARIO_ZONANOASEGURABLE = "ZonaNoAsegurable";
    public static final String PARAMETRIZACION_NOTIFICACION_DESTINATARIO_NOCONSTRUCTOR = "NoConstructor";
    public static final String PARAMETRIZACION_NOTIFICACION_DESTINATARIO_MENOR8PISOS = "Menor8Pisos";
    public static final String PARAMETRIZACION_NOTIFICACION_DESTINATARIO_MAMPOSTERIASUPERIOR = "Mamposteria8Niveles";
    public static final String PARAMETRIZACION_NOTIFICACION_DESTINATARIO_MATRICULAREPETIDA = "MatriculaRepetida";
    public static final String PARAMETRIZACION_NOTIFICACION_DESTINATARIO_PISOSSUPERIOR60 = "PisosSuperior60Niveles";
    public static final String PARAMETRIZACION_NOTIFICACION_DESTINATARIO_GEOREFERENCIACIONFUNCIONANDO = "GeorreferenciacionFuncionando";
    public static final String PARAMETRIZACION_NOTIFICACION_DESTINATARIO_GEOREFERENCIACIONCAIDA = "GeorreferenciacionCaido";
    public static final String PARAMETRIZACION_NOTIFICACION_DESTINATARIO_ASEGURABILIDADFUNCIONANDO = "AsegurabilidadFuncionando";
    public static final String PARAMETRIZACION_NOTIFICACION_DESTINATARIO_ASEGURABILIDADCAIDO = "AsegurabilidadCaido";
    public static final String PARAMETRIZACION_NOTIFICACION_DESTINATARIO_ASEGURABILIDADFINALIZADA = "TareaAsegurabilidadFinalizada";
    public static final String PARAMETRIZACION_NOTIFICACION_DESTINATARIO_PISOSINFERIORCONCRETO = "DualMenor8Pisos";
    public static final String PARAMETRIZACION_NOTIFICACION_DESTINATARIO_VALORCOMPARATIVO = "ValorComparativo";

    // Cargue masivo de avaluos
    public static final String FORMATO_CODIFICACION_ISO_8859 = "ISO-8859-1";
    public static final String ESTADO_CARGUE_APLICADO = "APLICADO";
    public static final String ESTADO_CARGUE_RECHAZADO = "RECHAZADO";
    public static final String ESTADO_CARGUE_CARGADO = "CARGADO";
    public static final String ESTADO_CARGUE_SINESTADO = "SINESTADO";
    public static final String CONCEPTO_ASEGURABILIDAD_PENDIENTE = "P";
    public static final String RUTA_CARGUE_MASIVO = "nueva.ruta.cargue.masivo";
    public static final int DIAS_BORRADO_CARGUE = 7;
//    public static final String PARAMETROS_MIGRACION_FILENET = "ParametrosMigracion";
//    public static final String PARAMETROS_JOB_FILENET = "JobEnvioImagenesFileNet";
//    public static final String PARAMETRO_RESULTADOS_JOB_FILENET = "job.result.limit";
//    public static final String PARAMETRO_MESES_ANTERIORES_JOB_FILENET = "Rango_Meses_Anteriores";
    public static final String ESTADO_PARAMETRIZACION = "status";

    // Parametro para verificar estado del servicio Asegurabilidad
    public static final String ASEGURABILIDAD_ACTIVA = "ACTIVA";
    public static final String ASEGURABILIDAD_INACTIVA = "INACTIVA";

    // Parametro para verificar estado del servicio API REST del ArcGIS
    public static final String SERVICIO_ARCGIS_ACTIVO = "ACTIVO";
    public static final String SERVICIO_ARCGIS_INACTIVO = "INACTIVO";

    // URL APIRest ArcGIS
//    public static final String URL_API_REST_ARCGIS = "http://ap80sbcc01:6080/arcgis/rest/services/Servicios_SB/gpGeocodificacionAvaluos/GPServer/gpGeocodificacionAvaluos/execute?";
    public static final String URL_API_REST_ARCGIS = "https://www.segurosbolivar.com/arcgis/rest/services/Servicios_SB/gpGeocodificacionAvaluos/GPServer/gpGeocodificacionAvaluos/execute?";
//    public static final String URL_API_REST_ARCGIS = "https://www.segurosbolivar.com/arcgis/rest/services/geocodificadores/gpGeocodificacionAvaluos_versionMejorada/GPServer/gpGeocodificacionAvaluos/execute?";
    public static final String URL_API_REST_ARCGIS_DIVIPOLA = "DIVIPOLA=";
    public static final String URL_API_REST_ARCGIS_DIRECCION = "&DIRECCION=";
    public static final String URL_API_REST_ARCGIS_BARRIO = "&BARRIO=";
    public static final String URL_API_REST_ARCGIS_FIN = "&env%3AoutSR=&env%3AprocessSR=&returnZ=false&returnM=false&f=html";
//    public static final String URL_API_REST_ARCGIS_FIN = "&env%3AoutSR=&env%3AprocessSR=&returnZ=false&returnM=false&returnTrueCurves=false&returnFeatureCollection=false&context=&f=html";
    public static final int URL_STATUS_OK = 200;

	

}
