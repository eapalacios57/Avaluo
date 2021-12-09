package com.segurosbolivar.avaluos.modelo.service.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Contiene información contextual para ser propagada desde el nivel de presentación hacia niveles subsiguientes.
 *
 * @version 1.0.0.
 * @date 27 Oct 2017.
 * @author Asesoftware SAS / Leonardo Valbuena
 */
public class PortadorInfoContextual {

    /**
     * Estructura que guarda una variable asociada a un Thread que carga la información contextual.
     */
    private static final ThreadLocal<Map<String, Object>> PORTADOR_INFO_CONTEXTUAL =
        new ThreadLocal<Map<String, Object>>();

    /**
     * Constructora por omisión; privada para evitar instanciaciones.
     */
    private PortadorInfoContextual() {
    }

    /**
     * Agrega objetos de información contextual al portador.
     *
     * @param clave Nombre del objeto.
     * @param valor Información contextual.
     */
    public static void adicionarInfoContextual(String clave, Object valor) {
        if (PORTADOR_INFO_CONTEXTUAL.get() == null)
            PORTADOR_INFO_CONTEXTUAL.set(new HashMap<String, Object>());

        PORTADOR_INFO_CONTEXTUAL.get().put(clave, valor);
    }

    /**
     * Devuelve el objeto de información contextual correspondiente a la clave.
     *
     * @param clave Clave que corresponde al objeto a buscar.
     * @return Información contextual correspondiente a la clave.
     */
    public static Object obtenerInfoContextualPorClave(String clave) {
        if (PORTADOR_INFO_CONTEXTUAL.get() == null)
            return null;
        else
            return PORTADOR_INFO_CONTEXTUAL.get().get(clave);
    }

    /**
     * Limpia el contenido de la estructura.
     */
    public static void limpiarPortadorInfoContextual() {
        PORTADOR_INFO_CONTEXTUAL.remove();
    }
    
    /**
     * Anula la estructura.
     */
    public static void anularPortadorInfoContextual() {
        PORTADOR_INFO_CONTEXTUAL.set(null);
    }

}

