package com.segurosbolivar.avaluos.modelo.service.util;

import static com.segurosbolivar.avaluos.modelo.service.util.UtilReflection.consultarAtributoId;
import static com.segurosbolivar.avaluos.modelo.service.util.UtilReflection.consultarAtributoPKCompleja;
import static com.segurosbolivar.avaluos.modelo.service.util.UtilReflection.consultarAtributosClase;
import static com.segurosbolivar.avaluos.modelo.service.util.UtilReflection.consultarMetodoGetAtributo;
import static com.segurosbolivar.avaluos.modelo.service.util.UtilReflection.esPkCompleja;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.Metamodel;

import org.eclipse.persistence.jpa.JpaHelper;
import org.eclipse.persistence.queries.DataReadQuery;
import org.eclipse.persistence.queries.StoredProcedureCall;
import org.eclipse.persistence.sessions.DatabaseRecord;
import org.eclipse.persistence.sessions.Session;

import com.asesoftware.util.exception.NegocioException;

/**
 * Administrador de persistencia para todas las tareas que incluyan acceso a la
 * base de datos.
 *
 * @param <T>
 *            Clase de la entidad asociada al manejador
 */
@Stateless
public class ManejadorPersistencia<T> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2524119490745938001L;

	// Atributos
	@PersistenceContext
	private transient EntityManager em;

	/**
	 * Variable estatica para imprimir logs...
	 */
	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ManejadorPersistencia.class.getName());

	/**
	 * Persiste un objeto en la base de datos.
	 *
	 * @param obj
	 *            Instancia de la entidad T a crear en la base de datos
	 */
	public void create(Object obj) {
		em.persist(obj);
	}

	/**
	 * Elimina un objeto de la obase de datos identificado por idObj.
	 *
	 * @param claseEntidad
	 *            Clase de la entidad T
	 * @param idObj
	 *            Puede ser una instancia de una pk compuesto de una entidad o un id
	 *            simple (Ej. Integer, String, Long, etc)
	 * @throws NegocioException
	 */
	public void delete(Class<?> claseEntidad, Object idObj) throws NegocioException {

		Query q = construirQueryEliminacion(claseEntidad, idObj);
		q.executeUpdate();

	}

	/**
	 * Devuelve un query delete del objeto identificado por idObj
	 *
	 * @param claseEntidad
	 *            Clase de la entidad T
	 * @param idObj
	 *            : Puede ser una instancia de una pk compuesto de una entidad o un
	 *            id simple (Ej. Integer, String, Long, etc)
	 * @return Query
	 * @throws NegocioException
	 */
	public Query construirQueryEliminacion(Class<?> claseEntidad, Object idObj) throws NegocioException {

		// Construcci??n de la condici??n
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM " + claseEntidad.getSimpleName());
		sql.append(" p WHERE");
		if (esPkCompleja(idObj)) {
			List<String> atributosId = consultarAtributosClase(idObj.getClass());
			String atributoPKEntity = consultarAtributoPKCompleja(claseEntidad);
			for (int i = 0; i < atributosId.size(); i++) {
				String referenciaAtributo = atributoPKEntity + "." + atributosId.get(i);
				if (i > 0) {
					sql.append(" AND");
				}
				sql.append(" p." + referenciaAtributo + " = :" + atributosId.get(i));
			}
		} else {
			String atributo = consultarAtributoId(claseEntidad);
			sql.append(" p." + atributo + " = :" + atributo);
		}

		Query q = em.createQuery(sql.toString());
		if (esPkCompleja(idObj)) {
			List<String> atributosId = consultarAtributosClase(idObj.getClass());
			for (String atributo : atributosId) {
				try {
					q.setParameter(atributo, consultarMetodoGetAtributo(idObj, atributo).invoke(idObj));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
					Logger.getLogger(ManejadorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		} else {
			q.setParameter(consultarAtributoId(claseEntidad), idObj);
		}

		return q;
	}

	/**
	 * Elimina los objetos en cascada deacuerdo a su mapeo
	 *
	 * @param o
	 *            Objeto a eliminar
	 */
	public void deleteCascade(Object o) {
		em.remove(em.merge(o));
	}

	/**
	 * Limpia toda la informaci??????n en cache para la sesi??????n actual
	 */
	public void clear() {
		em.clear();
	}

	/**
	 * Elimina un objeto de la base de datos.
	 *
	 * @param obj
	 *            Instancia de la entidad T a eliminar de la base de datos
	 */
	public void delete(Object obj) {
		em.remove(em.merge(obj));
	}

	/**
	 * Actualiza un objeto en la base de datos.
	 *
	 * @param obj
	 *            Instancia de la entidad T a actualizar en la base de datos
	 */
	public void update(Object obj) {
		em.merge(obj);
	}

	/**
	 * Actualiza un objeto desde la base de datos.
	 *
	 * @param obj
	 *            Instancia de la entidad T a actualizar desde la base de datos
	 */
	public void refresh(Object obj) {
		em.refresh(obj);
	}

	/**
	 * Busca un objeto en la base de datos.
	 *
	 * @param <T>
	 *            Identificador de la clase de una entidad
	 * @param cl
	 *            Clase de la entidad T
	 * @param pk
	 *            Llave primaria de la instancia de la entidad T a buscar
	 * @return El objeto de la clase T identificado con el pk especificado
	 */
	@SuppressWarnings("hiding")
	public <T> T find(Class<T> cl, Object pk) {
		return em.find(cl, pk);
	}

	/**
	 * Obtiene una lista con todos los resultados de una entidad.
	 *
	 * @param obj
	 *            Instancia de una entidad (No importan los valores)
	 * @return List Lista de todas las instancias encontradas para la entidad del
	 *         objeto obj
	 */
	public List<?> listData(Object obj) {
		return em.createNamedQuery(obj.getClass().getSimpleName() + ".findAll").getResultList();
	}

	/**
	 * Obtiene una lista con todos los resultados de una entidad.
	 *
	 * @param clazz
	 *            Clase de la entidad a consultar
	 * @return List Lista de todas las instancias encontradas para la entidad de la
	 *         clase clazz
	 */
	@SuppressWarnings({ "unchecked" })
	public List<T> listData(Class<?> clazz) {
		return em.createNamedQuery(clazz.getSimpleName() + ".findAll").getResultList();
	}

	/**
	 * Obtiene una lista con todos los resultados de un query.
	 *
	 * @param q
	 *            Query jpql a ejecutar
	 * @return List Lista con los resultados de la ejecuci??n del query
	 */
	public List<?> listData(Query q) {
		return q.getResultList();
	}

	/**
	 * Crea un nuevo query JPQL
	 *
	 * @param sql
	 *            El query en formato JPQL
	 * @return El query JPQL
	 */
	public Query createQuery(String sql) {
		return em.createQuery(sql);
	}

	/**
	 * Crea un nuevo query a partir del nombre dado
	 *
	 * @param sql
	 *            El query nombrado en formato jpql
	 * @return El query JPQL
	 */
	public Query createNamedQuery(String sql) {
		return em.createNamedQuery(sql);
	}

	/**
	 * Obtiene un referencia a una instancia de la clase dada
	 *
	 * @param <R>
	 *            Identificador de una clase
	 * @param clazz
	 *            Clase de la cual se quiere obtener la referencia
	 * @param id
	 *            Identificar del objeto del cual se quiere obtener la referencia
	 * @return El objeto identificado con el id especificado
	 */
	public <R> R getReference(Class<R> clazz, Object id) {
		return em.getReference(clazz, id);
	}

	/**
	 * Ejecuta el conteo de todos los registros de la tabla corrspondiente a la
	 * clase c
	 *
	 * @param c
	 *            La clase de la entidad sobre la cual se va a realizar la consulta
	 * @return El n??mero de registro en la tabla de la entidad c
	 */
	public int getCount(Class<?> c) {
		return ((Long) em.createQuery("select count(c) from " + c.getSimpleName() + " c").getSingleResult()).intValue();
	}

	/**
	 * Ejecuta el "Callable" que llega como parametro en el contexto de persistencia
	 *
	 * @param <V>
	 *            El tipo de resultado generado por el "Callable"
	 * @param c
	 *            El "Callable"
	 * @return El resultado de la ejecuci??n
	 */
	public <V> V executeInPersistenceContext(Callable<V> c) {
		V resultado = null;
		try {
			resultado = c.call();
		} catch (Exception ex) {
			logger.error(ex);
		}
		return resultado;
	}

	/**
	 * Ejecuta el query nativo sel que se pasa como par??metro
	 *
	 * @param querystr
	 *            Query nativo en formato sql
	 * @return La lista con los resultados de la ejecuci??n del query
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> doNativeQuery(String querystr) {

		return em.createNativeQuery(querystr).getResultList();

	}

	public Query nativeQuery(String quer) {
		return em.createNativeQuery(quer);
	}

	public Query nativeQuery(String query, Class<T> clazz) {
		if (clazz == null) {
			return em.createNativeQuery(query);
		}
		return em.createNativeQuery(query, clazz);
	}

	/**
	 * Obtiene un ??nico objeto resultado de la ejecuci??n del query
	 *
	 * @param q
	 *            Query jpql a ejecutar
	 * @return El resultado de la consulta
	 */
	public Object getData(Query q) {
		Object objeto;
		try {
			objeto = q.getSingleResult();
		} catch (NoResultException e) {
			logger.error(e);
			objeto = null;
		}

		return objeto;
	}

	/**
	 *
	 */
	public CriteriaBuilder getCriteriaBuilder() {
		return em.getCriteriaBuilder();
	}

	/**
	 *
	 */
	public TypedQuery<T> getTypedQuery(CriteriaQuery<T> cq) {
		TypedQuery<T> tq = em.createQuery(cq);
		return tq;
	}

	public DatabaseRecord ejecutarPrcPLSQL(String nomProcedimiento, List<String> nomParEntrada,
			List<String> nomParSalida, List<?> valorParEntrada) {
		Session session = JpaHelper.getEntityManager(em).getActiveSession();
		StoredProcedureCall spc = new StoredProcedureCall();
		spc.setProcedureName(nomProcedimiento);
		if (nomParEntrada != null && !nomParEntrada.isEmpty())
			for (String parEntrada : nomParEntrada) {
				spc.addNamedArgument(parEntrada);
			}
		if (nomParSalida != null && !nomParSalida.isEmpty())
			for (String parSalida : nomParSalida) {
				spc.addNamedOutputArgument(parSalida);
			}
		DataReadQuery query = new DataReadQuery();
		query.setCall(spc);
		if (nomParEntrada != null && !nomParEntrada.isEmpty())
			for (String parEntrada : nomParEntrada) {
				query.addArgument(parEntrada);
			}
		List<?> results = (List<?>) session.executeQuery(query, valorParEntrada);
		return (DatabaseRecord) results.get(0);

	}

	/**
	 *
	 */
	public Metamodel getMetamodel() {
		return em.getMetamodel();
	}

	public EntityTransaction getTransaction() {
		return em.getTransaction();
	}

	/**
	 * Metodo para ejecutar los MultiSelect y crear las tuplas de agregacion
	 * 
	 * @param tupla
	 * @return Consulta JPQL TypeQuery
	 * @author arincon
	 */
	public TypedQuery<Tuple> createQuery(CriteriaQuery<Tuple> tupla) {
		return em.createQuery(tupla);
	}

}