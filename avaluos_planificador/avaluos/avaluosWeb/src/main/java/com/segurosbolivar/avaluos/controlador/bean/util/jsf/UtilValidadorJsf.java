package com.segurosbolivar.avaluos.controlador.bean.util.jsf;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.primefaces.model.UploadedFile;

import com.asesoftware.util.archivo.UtilPropiedades;
import com.asesoftware.util.cons.Constantes;
import com.asesoftware.util.cons.TipoComparacion;
import com.asesoftware.util.exception.IRecuperador;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilTexto;
import com.asesoftware.util.validador.UtilValidador;
import com.segurosbolivar.avaluos.controlador.bean.general.GeneralAbstractoBean;
import com.segurosbolivar.avaluos.exception.RecuperadorWeb;

/**
 * Provee funcionalidad para las operaciones del validación de campo mas
 * comunes, teniendo en cuenta los componentes visuales de JSF.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:48 a.m.
 */
public final class UtilValidadorJsf {

    /**
     * archivo de propiedades con los mensajes de error.
     */
    private static IRecuperador recuperar = RecuperadorWeb.getInstance();
    /**
     * archivo de propiedades con las etiquetas de los campos
     */
    private static ResourceBundle etiquetas = GeneralAbstractoBean.getEtiquetas();

    /**
     * Constructor privado para evitar la creacion de una instacia de la clase
     */
    private UtilValidadorJsf() {

    }

    /**
     * Permite realizar la carga de los mensajes de error hacia jsf de forma
     * estandarizada teniendo en cuenta el identificador de las excepciones
     * generadas por el utilvalidador de la libreria AsesoftwareUtil.
     * 
     * @param id:
     *            identificador del mensaje de error.
     * @param parametros:
     *            para personalizar el mensaje de error si aplica.
     * @param ubicacionFormulario:
     *            localizacion en el formulario donde se quiere mostrar la alerta o
     *            error.
     * @param etiquetaEspecifica:
     *            si se desea personalizar el mensaje enviando la etiqueta del campo
     *            revisado.
     * @return falso para determinar que la validacion realizada fallo.
     */
    private static boolean cargarMensaje(long id, List<String> parametros, String ubicacionFormulario, String etiquetaEspecifica) {
	boolean especifico = false;
	if (parametros == null) {
	    parametros = new ArrayList<>();
	}
	if (!UtilTexto.estaVacio(etiquetaEspecifica)) {
	    parametros.add(0, UtilPropiedades.cargarPropiedad(etiquetas, etiquetaEspecifica, null));
	    especifico = true;
	}
	if (id == UtilValidador.MSJ_OBLIGATORIO)
	    UtilJsf.agregarEstilo(ubicacionFormulario, "avaluos-from-error");
	UtilJsf.mostrarMensaje(ubicacionFormulario, recuperar.obtenerMensaje(id, parametros.toArray(), especifico).getDescripcion());
	return false;
    }

    /**
     * Realiza las validaciones pertinentes de obligatoriedad y longitud maxima para
     * un valor entero tipo Long.
     * 
     * @param valor
     *            a verificar.
     * @param obligatorio
     *            determina si se validara la obligatoriedad.
     * @param longitud
     *            determina la longitud maxima en caracteres para el valor.
     * @param ubicacionFormulario
     *            destino del mensaje de error si se desea especificar la ubicacion.
     * @param etiquetaEspecifica
     *            en caso de que se desee especificar el nombre del campo que fallo.
     * @return verdadero si se cumplen todas las validaciones, de lo contrario se
     *         generar un mensaje de jsf con el error.
     */
    public static boolean validar(Long valor, String ubicacionFormulario, boolean obligatorio, int longitud, String etiquetaEspecifica) {
	UtilJsf.quitarEstilo(ubicacionFormulario, "avaluos-from-error");
	try {
	    return UtilValidador.validar(valor, obligatorio, longitud);
	} catch (NegocioException e) {
	    List<String> parametros = new ArrayList<>();
	    parametros.add(Integer.toString(longitud));
	    return cargarMensaje(e.getId(), parametros, ubicacionFormulario, etiquetaEspecifica);
	}
    }

    public static boolean validar(Integer valor, String ubicacionFormulario, boolean obligatorio, int longitud, String etiquetaEspecifica) {
	UtilJsf.quitarEstilo(ubicacionFormulario, "avaluos-from-error");
	try {
	    return UtilValidador.validar(valor, obligatorio, longitud);
	} catch (NegocioException e) {
	    List<String> parametros = new ArrayList<>();
	    parametros.add(Integer.toString(longitud));
	    return cargarMensaje(e.getId(), parametros, ubicacionFormulario, etiquetaEspecifica);
	}
    }

    public static boolean validar(BigInteger valor, String ubicacionFormulario, boolean obligatorio, int longitud, String etiquetaEspecifica) {
	UtilJsf.quitarEstilo(ubicacionFormulario, "avaluos-from-error");
	try {
	    return UtilValidador.validar(valor, obligatorio, longitud);
	} catch (NegocioException e) {
	    List<String> parametros = new ArrayList<>();
	    parametros.add(Integer.toString(longitud));
	    return cargarMensaje(e.getId(), parametros, ubicacionFormulario, etiquetaEspecifica);
	}
    }

    /***
     * Realiza las operacion de validaciones pertinentes de obligatoriedad y numero
     * de lugares entero y decimales para un valor flotante.
     * 
     * @param valor
     *            a verificar
     * @param ubicacionFormulario
     *            destino del mensaje de error si se desea especificar la ubicacion
     * @param obligatorio
     *            determina si debe validar que el valor es obligatorio.
     * @param enteros
     *            longitud de enteros.
     * @param decimales
     *            cantidad maxima de lugares decimales
     * @param etiquetaEspecifica
     *            en caso que se desee personalidad el nombre del campo en los
     *            mensajes de error.
     * @return verdadero en caso de que se cumplan para el valor flotante todas las
     *         validaciones parametrizadas.
     */
    public static boolean validar(BigDecimal valor, String ubicacionFormulario, boolean obligatorio, int enteros, int decimales, String etiquetaEspecifica) {
	UtilJsf.quitarEstilo(ubicacionFormulario, "avaluos-from-error");
	try {
	    return UtilValidador.validar(valor, obligatorio, enteros, decimales);
	} catch (NegocioException e) {
	    List<String> parametros = new ArrayList<>();
	    parametros.add(Integer.toString(enteros));
	    parametros.add(Integer.toString(decimales));
	    return cargarMensaje(e.getId(), parametros, ubicacionFormulario, etiquetaEspecifica);
	}
    }

    /**
     * Realiza las validaciones pertinentes de obligatorierdad y longitud maxima
     * para un valor de tipo texto.
     * 
     * @param valor:
     *            a verificar.
     * @param obligatorio
     *            determina si se validara la obligatoriedad.
     * @param longitud
     *            determina la longitud maxima en caracteres para el valor.
     * @param ubicacionFormulario
     *            destino del mensaje de error si se desea especificar la ubicacion.
     * @param etiquetaEspecifica
     *            en caso de que se desee especificar el nombre del campo que fallo.
     * @return verdadero si se cumplen todas las validaciones, de lo contrario se
     *         generar un mensaje de jsf con el error.
     */
    public static boolean validar(String valor, String ubicacionFormulario, boolean obligatorio, int longitud, String etiquetaEspecifica) {
	UtilJsf.quitarEstilo(ubicacionFormulario, "avaluos-from-error");
	try {
	    return UtilValidador.validar(valor, obligatorio, longitud);
	} catch (NegocioException e) {
	    List<String> parametros = new ArrayList<>();
	    parametros.add(Integer.toString(longitud));
	    return cargarMensaje(e.getId(), parametros, ubicacionFormulario, etiquetaEspecifica);
	}
    }

    /**
     * Realiza las validaciones pertinentes de obligatorierdad y longitud maxima
     * para un valor de tipo texto que tiene el formato de correo.
     * 
     * @param valor:
     *            a verificar.
     * @param obligatorio:
     *            determina si se validara la obligatoriedad.
     * @param longitud:
     *            determina la longitud maxima en caracteres para el valor.
     * @param ubicacionFormulario
     *            destino del mensaje de error si se desea especificar la ubicacion.
     * @param etiquetaEspecifica
     *            en caso de que se desee especificar el nombre del campo que fallo.
     * @return verdadero si se cumplen todas las validaciones, de lo contrario se
     *         generar un mensaje de jsf con el error.
     */
    public static boolean validarCorreo(String valor, String ubicacionFormulario, boolean obligatorio, int longitud, String etiquetaEspecifica) {
	UtilJsf.quitarEstilo(ubicacionFormulario, "avaluos-from-error");
	try {
	    return UtilValidador.validarCorreo(valor, obligatorio, longitud);
	} catch (NegocioException e) {
	    List<String> parametros = new ArrayList<>();
	    parametros.add(Integer.toString(longitud));
	    return cargarMensaje(e.getId(), parametros, ubicacionFormulario, etiquetaEspecifica);
	}
    }

    public static boolean validarFormato(String valor, String ubicacionFormulario, boolean obligatorio, int longitud, String formato, String etiquetaEspecifica,
	    String formatoMensaje) {
	UtilJsf.quitarEstilo(ubicacionFormulario, "avaluos-from-error");
	try {
	    return UtilValidador.validarFormato(valor, obligatorio, longitud, formato);
	} catch (NegocioException e) {
	    List<String> parametros = new ArrayList<>();
	    parametros.add(Integer.toString(longitud));
	    parametros.add(formatoMensaje);
	    return cargarMensaje(e.getId(), parametros, ubicacionFormulario, etiquetaEspecifica);
	}
    }

    /***
     * Permite realizar las validaciones pertinentes sobre un componente fileupload
     * para la carga de archivos.
     * 
     * @param archivo:
     *            componente a verificar.
     * @param obligatorio:
     *            determinar si es obligatorio.
     * @param formulario:
     *            permite identificar la ubicacion del mensaje de error especifico
     *            en caso de ser necesario.
     * @param etiquetaEspecifica:
     *            determina si es necesario que se identifique el nombre del campo
     *            en el mensaje de error.
     * @param extensiones:
     *            arreglo con las extensiones permitidas para el archivo que se va a
     *            subir. Si esta vacio, permitira cualquier tipo de archivo.
     * @param tamanio:
     *            Define el limite del peso del archivo, se especifica en kilobytes
     *            para mayor facilidad.
     * @return verdadero si el componente cumple con todas la validaciones
     *         asociadas. de contrario retorna falso y genera un mensaje de error en
     *         JSF.
     * @throws IOException
     */
    public static boolean validar(UploadedFile archivo, boolean obligatorio, String ubicacionFormulario, String etiquetaEspecifica, String[] extensiones, Long tamanio)
	    throws IOException {
	try {
	    if (archivo != null)
		return UtilValidador.validar(archivo, archivo.getSize(), archivo.getFileName(), obligatorio, extensiones, tamanio);
	    else if (obligatorio) {
		cargarMensaje(UtilValidador.MSJ_OBLIGATORIO, null, ubicacionFormulario, etiquetaEspecifica);
		return false;
	    } else
		return true;
	} catch (NegocioException e) {
	    List<String> parametros = new ArrayList<>();
	    parametros.add(Long.toString(tamanio * 1000));
	    parametros.add(UtilTexto.unificar(extensiones, ","));
	    return cargarMensaje(e.getId(), parametros, ubicacionFormulario, etiquetaEspecifica);
	}
    }

    public static boolean validar(byte[] archivo, String nombre, boolean obligatorio, String ubicacionFormulario, String etiquetaEspecifica, String[] extensiones, Long tamanio) {
	try {
	    if (archivo != null)
		return UtilValidador.validar(archivo, nombre, obligatorio, extensiones, tamanio);
	    else if (obligatorio) {
		cargarMensaje(UtilValidador.MSJ_OBLIGATORIO, null, ubicacionFormulario, etiquetaEspecifica);
		return false;
	    } else
		return true;
	} catch (NegocioException e) {
	    List<String> parametros = new ArrayList<>();
	    parametros.add(Long.toString(tamanio * 1000));
	    parametros.add(UtilTexto.unificar(extensiones, ","));
	    return cargarMensaje(e.getId(), parametros, ubicacionFormulario, etiquetaEspecifica);
	}
    }

    /**
     * Permite validar la obligatoriedad de una fecha tipo Timestamp
     * 
     * @param valor
     *            a verificar
     * @param obligatorio
     *            en caso de que el valor sea obligatorio.
     * @param ubicacionFormulario
     *            destino del mensaje de error si se desea especificar la ubicacion.
     * @param etiquetaEspecifica
     *            en caso de que se desee especificar el nombre del campo que fallo.
     * @return verdadero si se cumplen todas las validaciones, de lo contrario se
     *         generar un mensaje de jsf con el error.
     */
    public static boolean validar(Timestamp valor, String ubicacionFormulario, boolean obligatorio, String etiquetaEspecifica) {
	UtilJsf.quitarEstilo(ubicacionFormulario, "avaluos-from-error");
	try {
	    return UtilValidador.validar(valor, obligatorio);
	} catch (NegocioException e) {
	    return cargarMensaje(e.getId(), null, ubicacionFormulario, etiquetaEspecifica);
	}
    }

    /**
     * Permite validar la obligatorierdad de una fecha tipo Date.
     * 
     * @param valor
     *            a verificar
     * @param obligatorio
     *            en caso de que el valor sea obligatorio.
     * @param ubicacionFormulario
     *            destino del mensaje de error si se desea especificar la ubicacion.
     * @param etiquetaEspecifica
     *            en caso de que se desee especificar el nombre del campo que fallo.
     * @return verdadero si se cumplen todas las validaciones, de lo contrario se
     *         generar un mensaje de jsf con el error.
     */
    public static boolean validar(Date valor, String ubicacionFormulario, boolean obligatorio, String etiquetaEspecifica) {
	UtilJsf.quitarEstilo(ubicacionFormulario, "avaluos-from-error");
	try {
	    return UtilValidador.validar(valor, obligatorio);
	} catch (NegocioException e) {
	    return cargarMensaje(e.getId(), null, ubicacionFormulario, etiquetaEspecifica);
	}
    }

    /**
     * Verifica si un listado de opciones por lo menos se selecciono al menos un
     * valor.
     * 
     * @param valor
     *            listado a verificar.
     * @param obligatorio
     *            permite verificar su obligatoriedad.
     * @param ubicacionFormulario
     *            destino del mensaje de error si se desea especificar la ubicacion.
     * @param etiquetaEspecifica
     *            en caso de que se desee especificar el nombre del campo que fallo.
     * @return verdadero si se cumplen todas las validaciones, de lo contrario se
     *         generar un mensaje de jsf con el error.
     */
    public static boolean validar(String[] valor, String ubicacionFormulario, boolean obligatorio, String etiquetaEspecifica) {
	try {
	    return UtilValidador.validar(valor, obligatorio);
	} catch (NegocioException e) {
	    return cargarMensaje(e.getId(), null, ubicacionFormulario, etiquetaEspecifica);
	}
    }

    /**
     * Permite realizar la comparacion entre dos fechas tipo Timestamp, las
     * comparaciones permitidas son IGUAL, DIFERENTE, MAYOR_QUE, MENOR_QUE,
     * MAYOR_IGUAL, MENOR_IGUAL
     * 
     * @param fecha:
     *            fecha a verificar
     * @param tipo:
     *            de comparacion a realizar
     * @param fechaComparacion:
     *            fecha con la que se compara la fecha a verificar.
     * @param ubicacionFormulario:
     *            destino del mensaje de error si se desea especificar la ubicacion.
     * @param etiquetaEspecifica:
     *            en caso de que se desee especificar el nombre del campo que fallo.
     * @return verdadero si el campo supera la comparacion, de lo contrario se
     *         generar un mensaje de jsf con el error.
     */
    public static boolean comparar(Timestamp fecha, TipoComparacion tipo, Timestamp fechaComparacion, String ubicacionFormulario, String etiquetaEspecifica) {
	try {
	    return UtilValidador.comparar(fecha, tipo, fechaComparacion);
	} catch (NegocioException e) {
	    SimpleDateFormat sd = new SimpleDateFormat(UtilPropiedades.cargarPropiedad(etiquetas, "formato_fecha", null));
	    List<String> parametros = new ArrayList<>();
	    parametros.add(sd.format(fechaComparacion));
	    return cargarMensaje(e.getId(), parametros, ubicacionFormulario, etiquetaEspecifica);
	}
    }

    /**
     * Permite comparar un par de fechas tipo Date
     * 
     * @param fecha:
     *            fecha a verificar
     * @param tipo:
     *            de comparacion a realizar
     * @param fechaComparacion:
     *            fecha con la que se compara la fecha a verificar.
     * @param ubicacionFormulario:
     *            destino del mensaje de error si se desea especificar la ubicacion.
     * @param etiquetaEspecifica:
     *            en caso de que se desee especificar el nombre del campo que fallo.
     * @return verdadero si el campo supera la comparacion, de lo contrario se
     *         generar un mensaje de jsf con el error.
     */
    public static boolean comparar(Date fecha, TipoComparacion tipo, Date fechaComparacion, String ubicacionFormulario, String etiquetaEspecifica) {
	try {
	    return UtilValidador.comparar(fecha, tipo, fechaComparacion);
	} catch (NegocioException e) {

	    SimpleDateFormat sd = new SimpleDateFormat(Constantes.FORMATO_FECHA_GUION);
	    List<String> parametros = new ArrayList<>();
	    parametros.add(sd.format(fechaComparacion));
	    return cargarMensaje(e.getId(), parametros, ubicacionFormulario, etiquetaEspecifica);
	}
    }

    public static boolean compararDiferenciaMeses(Date fecha, long diferenciaMeses, Date fechaComparacion, String ubicacionFormulario, String etiquetaEspecifica) {
	try {
	    return UtilValidador.compararDiferenciaMeses(fecha, fechaComparacion, diferenciaMeses);
	} catch (NegocioException e) {
	    SimpleDateFormat sd = new SimpleDateFormat(Constantes.FORMATO_FECHA_GUION);
	    List<String> parametros = new ArrayList<>();
	    parametros.add(sd.format(fecha));
	    parametros.add(sd.format(fechaComparacion));
	    parametros.add(Long.toString(diferenciaMeses));
	    return cargarMensaje(e.getId(), parametros, ubicacionFormulario, etiquetaEspecifica);
	}
    }

    /**
     * Permite realizar la comparacion entre dos valores enteros tipo Long, las
     * comparaciones permitidas son IGUAL, DIFERENTE, MAYOR_QUE, MENOR_QUE,
     * MAYOR_IGUAL, MENOR_IGUAL
     * 
     * @param valor
     *            a verificar
     * @param tipo
     *            de comparacion a realizar
     * @param comparacion
     *            valor con el que se compara
     * @param ubicacionFormulario
     *            destino del mensaje de error si se desea especificar la ubicacion.
     * @param etiquetaEspecifica
     *            en caso de que se desee especificar el nombre del campo que fallo.
     * @return verdadero si el campo supera la comparacion, de lo contrario se
     *         generar un mensaje de jsf con el error.
     */
    public static boolean comparar(Long valor, TipoComparacion tipo, Long comparacion, String ubicacionFormulario, String etiquetaEspecifica) {
	try {
	    return UtilValidador.comparar(valor, tipo, comparacion);
	} catch (NegocioException e) {
	    List<String> parametros = new ArrayList<>();
	    parametros.add(comparacion.toString());
	    return cargarMensaje(e.getId(), parametros, ubicacionFormulario, etiquetaEspecifica);
	}
    }

    /**
     * Permite realizar la comparacion entre dos textos, las comparaciones
     * permitidas son IGUAL, DEFERENTE.
     * 
     * @param valor
     *            a verificar
     * @param tipo
     *            de comparacion a realizar
     * @param comparacion
     *            valor con el que se compara
     * @param ubicacionFormulario
     *            destino del mensaje de error si se desea especificar la ubicacion.
     * @param etiquetaEspecifica
     *            en caso de que se desee especificar el nombre del campo que fallo.
     * @return verdadero si el campo supera la comparacion, de lo contrario se
     *         generar un mensaje de jsf con el error.
     */
    public static boolean comparar(String valor, TipoComparacion tipo, String comparacion, String ubicacionFormulario, String etiquetaEspecifica) {
	try {
	    return UtilValidador.comparar(valor, tipo, comparacion);
	} catch (NegocioException e) {
	    List<String> parametros = new ArrayList<>();
	    parametros.add(comparacion.toString());
	    return cargarMensaje(e.getId(), parametros, ubicacionFormulario, etiquetaEspecifica);
	}
    }

}
