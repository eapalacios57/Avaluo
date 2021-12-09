package com.segurosbolivar.avaluos.modelo.service.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Query;

import com.asesoftware.util.lang.UtilFecha;
import com.asesoftware.util.lang.UtilNumero;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;

public class UtilJpa {

    public static final String DATASOURCE = "jdbc/avaluosDS";

    /**
     * Permite agregar condiciones a un where para formar un select dinamico
     * 
     * @param sb
     *            stringBuilder que contiene el select que se esta armando y al que
     *            se agregaran las condiciones del where
     * @param campo
     *            nombre del campo que se validara en la condciones
     * @param valor
     *            con el que se contrastara el campo. Si el valor esta nulo, la
     *            coondicion no se agregara. El valor se intenta formatear segun el
     *            tipo de dato del mismo (Long, Date, String)
     * @param formato
     *            en caso de las fechas es necesario especificar el formato en que
     *            se debe procesar la fecha.
     * @param agregarPrefijo
     *            si se debe agregar antes un AND o OR a la condicion.
     * @param usarAnd
     *            verdadero si el prefijo a agregar es un AND de lo contrario se
     *            agrega la condicion OR
     * @return verdadero si se agrego la condicion al StringBuilder.
     */
    public static boolean agregarCondicion(StringBuilder sb, String campo, Object valor, String formato, boolean agregarPrefijo, boolean usarAnd) {
	if (valor == null) {
	    return false;
	}
	String valorTexto = obtenerValor(valor, formato, true);
	if (UtilTexto.estaVacio(valorTexto))
	    return false;
	if (agregarPrefijo)
	    sb.append(usarAnd ? UtilConstantes.CM_AND : UtilConstantes.CM_OR);
	sb.append(" ").append(campo).append("=").append(valorTexto).append(" ");
	return true;
    }

    private static String obtenerValor(Object valor, String formato, boolean comillas) {
	String valorTexto = null;
	if (valor instanceof Long) {
	    valorTexto = UtilNumero.pasarTexto((Long) valor);
	} else if (valor instanceof BigDecimal) {
	    valorTexto = UtilNumero.obtenerParteEntera((BigDecimal) valor);
	} else if (valor instanceof String && !UtilTexto.estaVacio((String) valor)) {
	    if (formato == null)
		valorTexto = (comillas ? "'" : "") + (String) valor + (comillas ? "'" : "");
	    else
		valorTexto = (String) valor;
	} else if (valor instanceof Date) {
	    valorTexto = UtilFecha.dateToString(formato, (Date) valor);
	    // valorTexto = (comillas ? "'" : "") + valorTexto + (comillas ? "'" : "");
	}
	return valorTexto;
    }

    public static boolean agregarParametros(Query consulta, String parametro, Object valor) {
	if (consulta == null || valor == null)
	    return false;
	consulta.setParameter(parametro, valor);
	return true;
    }

    
    public static boolean agregarParametros(Query consulta, int parametro, Object valor) {
	if (consulta == null || valor == null)
	    return false;
	consulta.setParameter(parametro, valor);
	return true;
    }
   public static boolean agregarCondicion(StringBuilder sb, String campo, Object valor, String formato, String tipoCondicion, boolean agregarPrefijo, boolean usarAnd,
    boolean abrirParentesis, boolean cerrarParantesis) {
	if (valor == null) {
	    return false;
	}
//    public static boolean agregarCondicion(StringBuilder sb, String campo, Object valor, String formato, String tipoCondicion, boolean agregarPrefijo, boolean usarAnd,
//    	    boolean abrirParentesis, boolean cerrarParantesis) {
//        	if (valor == null) {
//        		if (campo.equals("p.idEmpresaAvaluo")) {
//        			sb.append(" AND ").append(campo).append(" is null");
//        			return true;
//        		}
//        		else {
//        			return false;
//        		}
//        	}
	String valorTexto = obtenerValor(valor, formato, true);
		if (UtilTexto.estaVacio(valorTexto))
	    return false;
	if (agregarPrefijo)
	    sb.append(usarAnd ? UtilConstantes.CM_AND : UtilConstantes.CM_OR);
	if (abrirParentesis)
	    sb.append("(");
	if (tipoCondicion == null) {
	    sb.append(" ").append(campo).append("=").append(valorTexto).append(" ");
	    if (cerrarParantesis)
		sb.append(")");
	    return true;
	}
	if (tipoCondicion.equals(UtilConstantes.EX_IN)) {
	    sb.append(" ").append(campo).append(" ").append(UtilConstantes.EX_IN).append(" (").append(valorTexto).append(") ");
	}
	if (tipoCondicion.equals(UtilConstantes.CR_EQUAL)) {
	    sb.append(" ").append(campo).append(" = ").append(valorTexto).append(" ");
	}
	if (tipoCondicion.equals(UtilConstantes.CR_GREATER_EQUAL)) {
	    sb.append(" ").append(campo).append(" >= ").append(valorTexto).append(" ");
	}
	if (tipoCondicion.equals(UtilConstantes.CR_LESS_EQUAL)) {
	    sb.append(" ").append(campo).append(" <= ").append(valorTexto).append(" ");
	}
	if (tipoCondicion.equals(UtilConstantes.CR_NOT_EQUAL)) {
	    sb.append(" ").append(campo).append(" != ").append(valorTexto).append(" ");
	}
	if (tipoCondicion.equals(UtilConstantes.CR_LIKE) && valorTexto != null) {
	    valorTexto = valorTexto.replaceAll("'", "");
	    sb.append(" ").append(campo).append(" ").append(UtilConstantes.CR_LIKE).append("  '%").append(valorTexto).append("%' ");
	}
	if (tipoCondicion.equals(UtilConstantes.EX_IS_NULL)) {
	    sb.append(" ").append(campo).append(" ").append(UtilConstantes.CR_IS_NULL);
	}

	
	
	if (cerrarParantesis)
	    sb.append(")");
	return true;
    }


    public static boolean agregarCondicionISNULL(StringBuilder sb, String campo, String tipoCondicion, boolean agregarPrefijo, boolean usarAnd,
    	    boolean abrirParentesis, boolean cerrarParantesis) {
    	if (agregarPrefijo)
    	    sb.append(usarAnd ? UtilConstantes.CM_AND : UtilConstantes.CM_OR);
    	if (abrirParentesis)
    	    sb.append("(");
    	
    	if (tipoCondicion.equals(UtilConstantes.EX_IS_NULL)) {
    	    sb.append(" ").append(campo).append(" ").append(UtilConstantes.CR_IS_NULL);
    	}
    	
    	if (cerrarParantesis)
    	    sb.append(")");
    	return true;
        }

    
    
    public static boolean agregarCondicionLIKE(StringBuilder sb, String campo, Object valor, boolean agregarPrefijo, boolean usarAnd, boolean abrirParentesis,
	    boolean cerrarParantesis) {
	if (valor == null) {
	    return false;
	}
	String valorTexto = obtenerValor(valor, null, false);
	if (UtilTexto.estaVacio(valorTexto))
	    return false;
	if (agregarPrefijo)
	    sb.append(usarAnd ? UtilConstantes.CM_AND : UtilConstantes.CM_OR);
	if (abrirParentesis)
	    sb.append("(");
	sb.append(" ").append(campo).append(" ").append(UtilConstantes.CR_LIKE).append("  '%").append(valorTexto).append("%' ");
	if (cerrarParantesis)
	    sb.append(")");
	return true;
    }

    public static boolean agregarCondicionIN(StringBuilder sb, String campo, Object valor, boolean agregarPrefijo, boolean usarAnd, boolean abrirParentesis,
	    boolean cerrarParantesis) {
	if (valor == null) {
	    return false;
	}
	String valorTexto = obtenerValor(valor, null, false);
	if (UtilTexto.estaVacio(valorTexto))
	    return false;
	if (agregarPrefijo)
	    sb.append(usarAnd ? UtilConstantes.CM_AND : UtilConstantes.CM_OR);
	if (abrirParentesis)
	    sb.append("(");
	sb.append(" ").append(campo).append(" ").append(UtilConstantes.EX_IN).append(" (").append(valorTexto).append(") ");
	if (cerrarParantesis)
	    sb.append(")");
	return true;
    }

    public static boolean agregarCondicionParametro(StringBuilder sb, String campo, String parametro, String tipoCondicion, boolean agregarPrefijo, boolean usarAnd,
	    boolean abrirParentesis, boolean cerrarParantesis) {
	if (UtilTexto.estaVacio(parametro)) {
	    return false;
	}
	if (agregarPrefijo)
	    sb.append(usarAnd ? UtilConstantes.CM_AND : UtilConstantes.CM_OR);
	if (abrirParentesis)
	    sb.append("(");
	if (tipoCondicion == null) {
	    sb.append(" ").append(campo).append(" = :").append(parametro).append(" ");
	    if (cerrarParantesis)
		sb.append(")");
	    return true;
	}
	if (tipoCondicion.equals(UtilConstantes.EX_IN)) {
	    sb.append(" ").append(campo).append(" ").append(UtilConstantes.EX_IN).append(" (:").append(parametro).append(") ");
	}
	if (tipoCondicion.equals(UtilConstantes.CR_GREATER_EQUAL)) {
	    sb.append(" ").append(campo).append(" >= :").append(parametro).append(" ");
	}
	if (tipoCondicion.equals(UtilConstantes.CR_LESS_EQUAL)) {
	    sb.append(" ").append(campo).append(" <= :").append(parametro).append(" ");
	}
	if (tipoCondicion.equals(UtilConstantes.CR_LESS_THAN)) {
	    sb.append(" ").append(campo).append(" < :").append(parametro).append(" ");
	}
	if (tipoCondicion.equals(UtilConstantes.CR_EQUAL)) {
	    sb.append(" ").append(campo).append(" = :").append(parametro).append(" ");
	}
	if (tipoCondicion.equals(UtilConstantes.CR_NOT_EQUAL)) {
	    sb.append(" ").append(campo).append(" != :").append(parametro).append(" ");
	}
	if (tipoCondicion.equals(UtilConstantes.CR_LIKE)) {
	    sb.append(" ").append(campo).append(" ").append(UtilConstantes.CR_LIKE).append("  '%:").append(parametro).append("%' ");
	}
	if (cerrarParantesis)
	    sb.append(")");
	return true;
    }

    public static String generarOrder(String alias, String campoOrden, SentidoOrientacion sentido) {
	if (UtilTexto.estaVacio(campoOrden) || sentido == null)
	    return "";
	return " ORDER BY " + alias + "." + campoOrden + " " + sentido.getValor();
    }

    public static String generarOrder(String alias, String campoOrden, SentidoOrientacion sentido, Map<String, String> joins) {
	if (UtilTexto.estaVacio(campoOrden) || sentido == null)
	    return "";
	StringBuilder sb = new StringBuilder(" ORDER BY ");
	sb.append(alias).append(".").append(campoOrden).append(" ").append(sentido.getValor());
	String retorno = sb.toString();
	if (joins == null || joins.isEmpty())
	    return retorno;
	for (Entry<String, String> join : joins.entrySet()) {
	    retorno = retorno.replaceAll(alias + "." + join.getKey(), join.getValue());
	}
	return retorno;
    }

}
