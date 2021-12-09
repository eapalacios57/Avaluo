package com.segurosbolivar.avaluos.modelo.service.util;

import static com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes.INT_PRIMITIVE;
import static com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes.LONG_PRIMITIVE;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Parameter;
import javax.persistence.Query;

import com.asesoftware.util.exception.NegocioException;

/**
 * Clase con utilidades que hacen uso de reflection.
 * 
 * @author GeneradorCRUD
 */
public class UtilReflection {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UtilReflection.class.getName());

    private UtilReflection(){}

	/**
	 * Devuelve el método get consultado por reflection del atributo que se pasa
	 * como parámetro en la clase del objeto objId
	 * 
	 * @param objId
	 *            Instancia de la clase a analizar
	 * @param nombreAtributo
	 *            Nombre del atributo del cual se quiere consultar el get.
	 * @return El metodo get correspondiente al atributo
	 * @throws NegocioException
	 */
	public static Method consultarMetodoGetAtributo(Object objId, String nombreAtributo) throws NegocioException {

		Method metodo = null;
		try {
			String nombre = "get" + nombreAtributo.substring(0, 1).toUpperCase() + nombreAtributo.substring(1);
			metodo = objId.getClass().getMethod(nombre);
		} catch (NoSuchMethodException | SecurityException ex) {
			logger.error(ex);
			throw new NegocioException("El metodo get consultado para el atributo " + nombreAtributo + " no existe.");
		}

		return metodo;
	}

	/**
	 * Devuelve el atributo etiquetado con @EmbbededId de la clase que define
	 * una entidad que se pasa como parámetro
	 * 
	 * @param claseEntidad
	 *            Clase de la entidad a consultar
	 * @return El atributo etiqueta con @EmbbededId de la entidad
	 * @throws NegocioException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String consultarAtributoPKCompleja(Class claseEntidad) throws NegocioException {
		if (claseEntidad.isAnnotationPresent(Entity.class)) {
			Field[] atributos = claseEntidad.getDeclaredFields();
			for (Field atributo : atributos) {
				if (atributo.isAnnotationPresent(EmbeddedId.class)) {
					return atributo.getName();
				}
			}
		}
		throw new NegocioException("El objeto consultado o no es una entidad o no tiene un atributo embeddedid");
	}

	/**
	 * Identifica si la clase del objeto que se pasa como parámetro es una pk
	 * compuesta, es decir, si está anotada con la etiqueta @Embbeded
	 * 
	 * @param idObj
	 *            Instancia de la clase a analizar
	 * @return Verdadero si el objeto está anotado con @Embbeded
	 */
	public static boolean esPkCompleja(Object idObj) {
		return idObj.getClass().isAnnotationPresent(Embeddable.class);
	}

	/**
	 * Devuelve los atributos de la clase que se pasa como parametro
	 * 
	 * @param clase
	 *            Clase a analizar
	 * @return Lista de atributos no estáticos y privados de la clase
	 */
	@SuppressWarnings({ "rawtypes" })
	public static List<String> consultarAtributosClase(Class clase) {
		List<String> atributosId = new ArrayList<>();
		for (Field atributo : clase.getDeclaredFields()) {
			if (Modifier.isPrivate(atributo.getModifiers()) && !Modifier.isStatic(atributo.getModifiers())) {
				atributosId.add(atributo.getName());
			}
		}
		return atributosId;
	}

	/**
	 * Devuelve el nombre del atributo etiquetado con @Id de la entidad que se
	 * pasa como parámetro.
	 * 
	 * @param claseEntidad
	 *            Clase que define una entidad
	 * @return El nombre del atributo identificador de la entidad
	 * @throws NegocioException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String consultarAtributoId(Class claseEntidad) throws NegocioException {
		if (claseEntidad.isAnnotationPresent(Entity.class)) {
			Field[] atributos = claseEntidad.getDeclaredFields();
			for (Field atributo : atributos) {
				if (atributo.isAnnotationPresent(Id.class)) {
					return atributo.getName();
				}
			}
		}

		throw new NegocioException("El objeto consultado o no es una entidad o no tiene un atributo id");
	}

	/**
	 * Establece el valor del parametro en el query deado
	 *
	 * @param q
	 *            Query jpql
	 * @param p
	 *            Parametro del query jpql a setear
	 * @param value
	 *            El valor a asignar al parámetro
	 * @throws NegocioException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void setParameter(Query q, Parameter p, String value) throws NegocioException {
		Class c = p.getParameterType();
		if (value == null) {
			q.setParameter(p, value);
		} else if (c.equals(String.class)) {
			q.setParameter(p, value);
		} else if (c.equals(Integer.class)) {
			q.setParameter(p, new Integer(value));
		} else if (INT_PRIMITIVE.equals(c.toString())) {
			q.setParameter(p, new Integer(value));
		} else if (c.equals(Boolean.class)) {
			q.setParameter(p, Boolean.parseBoolean(value));
		} else if (c.equals(Long.class)) {
			q.setParameter(p, Long.parseLong(value));
		} else if (LONG_PRIMITIVE.equals(c.toString())) {
			q.setParameter(p, Long.parseLong(value));
		} else if (c.equals(BigInteger.class)) {
			q.setParameter(p, new BigInteger(value));
		} else if (c.equals(BigDecimal.class)) {
			q.setParameter(p, new BigDecimal(value));
		} else if (c.equals(Object.class)) {
			q.setParameter(p, value);
		} else {
			throw new NegocioException(
					"El valor especificado para el parámetro no se reconocio como un tipo java. Valor especificado: "
							+ c.getName());
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static Stack<Method> getMethods(Class type)
	{
		Stack<Method> result = new Stack<Method>();
		try {
			for (Class<?> c = type; c != null; c = c.getSuperclass()) {
				Method[] methods = c.getDeclaredMethods();
				for (Method method : methods) {
					result.add(method);
				}
			}
		} catch (Exception e) {

		}

		return result;
	}
	
	public static Object runGetter(String field, Object o)
	{
		for (Method method : getMethods(o.getClass())) {
			if ((method.getName().startsWith("get")) && (method.getName().length() == (field.length() + 3))) {
				if (method.getName().toLowerCase().endsWith(field.toLowerCase())) {
					try {
						return method.invoke(o);
					} catch (IllegalAccessException e) {
//	                    Logger.fatal("Could not determine method: " + method.getName());
					} catch (InvocationTargetException e) {
//	                    Logger.fatal("Could not determine method: " + method.getName());
					}

				}
			}
		}
		return null;
	}
}
