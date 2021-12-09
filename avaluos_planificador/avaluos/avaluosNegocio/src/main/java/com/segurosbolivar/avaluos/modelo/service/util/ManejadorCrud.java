package com.segurosbolivar.avaluos.modelo.service.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.persistence.Embeddable;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import com.asesoftware.util.archivo.UtilPropiedades;
import com.asesoftware.util.exception.NegocioException;

public abstract class ManejadorCrud<T, U> implements IManejadorCrud<T, U>, Serializable {

	private static ResourceBundle propiedades = UtilConstantes.RSC_ERRORES;
	private static final long serialVersionUID = 8109274458954097982L;
	private static final String logArchivo = UtilPropiedades.cargarPropiedad(propiedades, UtilConstantes.RUTA_LOG) + "logArchivo.txt";

	/**
	 * a imprimir logs...
	 */
	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ManejadorCrud.class.getName());

	public static final Integer IGNORAR_PARAMETRO_CONSULTA = -1;

	@EJB
	protected ManejadorPersistencia<T> mp;

	private Class<T> claseEntidad;

	private CriteriaBuilder cb;
	private CriteriaQuery<T> cq;
	private Root<T> root;

	public ManejadorCrud() {
	}
	
	public static void write(String archivo, String mensaje) throws NegocioException {
		Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat ("yyyy.MM.dd hh:mm:ss ");
        String currentTime = dateFormat.format(currentDate);
       
        try {
        	FileWriter aWriter = new FileWriter(archivo, true);
			aWriter.write(currentTime + " " + mensaje + "\r"+"\n"+"\r"+"\n");
	        aWriter.flush();
	        aWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	/**
	 * @param claseEntidad
	 *            Clase de la entidad T
	 */
	public ManejadorCrud(Class<T> claseEntidad) {
		this.claseEntidad = claseEntidad;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param pId
	 *            {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public T buscar(U pId) {
		return mp.find(getClaseEntidad(), pId);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public T crear() {
		T instancia = null;
		try {
			instancia = getClaseEntidad().newInstance();
		} catch (InstantiationException | IllegalAccessException ex) {
			logger.error(ex);
		}
		return instancia;

	}

	/**
	 * Metodo que se ejecuta antes de crear un registro en la base de datos y en
	 * el que la clase que extienda esta clase puede anadir funcionalidad
	 * personalizada.
	 */
	public void preCrear() {

	}

	/**
	 * Metodo que se ejecuta despues de crear un registro en la base de datos y
	 * en el que la clase que extienda esta clase puede anadir funcionalidad
	 * personalizada.
	 */
	public void postCrear() {

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param pData
	 *            {@inheritDoc}
	 * @throws NegocioException 
	 */
	@Override
	public void crear(T pData) throws NegocioException {
		
		Exception error = null;
//		String mensajeExitoso = "[EXITOSO] Registro almacenado satisfactoriamente." + pData.getClass().getName();
		
		try {
			mp.create(pData);
		} catch (Exception e) {
			error = e;
		}
		
		if (error != null) {
			write(logArchivo, "[ERROR]" + error.getMessage());
		} 
//		else {
//			write(logArchivo, mensajeExitoso);
//		}
		
	}

	/**
	 * Metodo que se ejecuta antes de actualizar un registro en la base de datos
	 * y en el que la clase que extienda esta clase puede anadir funcionalidad
	 * personalizada.
	 */
	public void preActualizar() {

	}

	/**
	 * Metodo que se ejecuta despues de crear un registro en la base de datos y
	 * en el que la clase que extienda esta clase puede anadir funcionalidad
	 * personalizada.
	 */
	public void postActualizar() {

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param pData
	 *            {@inheritDoc}
	 * @throws NegocioException 
	 */
	@Override
	public void actualizar(T pData) throws NegocioException {
		
		Exception error = null;
//		String mensajeExitoso = "[EXITOSO] Registro actualizado satisfactoriamente." + pData.getClass().getName();
		
		try {
			mp.update(pData);
		} catch (Exception e) {
			error = e;
		}
		
		if (error != null) {
			write(logArchivo, "[ERROR]" + error.getMessage());
		} 
//		else {
//			write(logArchivo, mensajeExitoso);
//		}
		
		
	}

	/**
	 * Metodo que se ejecuta antes de eliminar un registro en la base de datos y
	 * en el que la clase que extienda esta clase puede anadir funcionalidad
	 * personalizada.
	 */
	public void preEliminar() {
		
	}

	/**
	 * Metodo que se ejecuta despues de eliminar un registro en la base de datos
	 * y en el que la clase que extienda esta clase puede anadir funcionalidad
	 * personalizada.
	 */
	public void postEliminar() {

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param pData
	 *            {@inheritDoc}
	 * @throws NegocioException 
	 */
	@Override
	public void eliminar(T pData) throws NegocioException {

		Exception error = null;
//		String mensajeExitoso = "[EXITOSO] Registro eliminado satisfactoriamente." + pData.getClass().getName();
		
		try {
			mp.delete(pData);
		} catch (Exception e) {
			error = e;
		}
		
		if (error != null) {
			write(logArchivo, "[ERROR]" + error.getMessage());
		} 
//		else {
//			write(logArchivo, mensajeExitoso);
//		}

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param pId
	 *            {@inheritDoc}
	 * @throws NegocioException
	 */
	@Override
	public void eliminarPorId(U pId) throws NegocioException {
		mp.delete(getClaseEntidad(), pId);
	}

	/**
	 * Metodo que se ejecuta antes de crear o actualizar un registro en la base
	 * de datos y en el que la clase que extienda esta clase puede anadir
	 * funcionalidad personalizada.
	 */
	public void preGuardar() {
	}

	/**
	 * Metodo que se ejecuta despues de crear o actualizar un registro en la
	 * base de datos y en el que la clase que extienda esta clase puede anadir
	 * funcionalidad personalizada.
	 */
	public void postGuardar() {
	}

	/**
	 * @return the claseEntidad
	 */
	public Class<T> getClaseEntidad() {
		return claseEntidad;
	}

	/**
	 * @param claseEntidad
	 *            the claseEntidad to set
	 */
	public void setClaseEntidad(Class<T> claseEntidad) {
		this.claseEntidad = claseEntidad;
	}

	/**
	 *
	 */
	public List<T> consultarPorCriteria(SearchExpression search) {

		this.initCriteriaBuilder();
		this.buildCriteriaExpression(search);
		TypedQuery<T> tq = mp.getTypedQuery(cq);
		return  tq.getResultList();
	}

	/**
	 *
	 */
	private void initCriteriaBuilder() {
		this.cb = mp.getCriteriaBuilder();
		this.cq = cb.createQuery(this.claseEntidad);
		this.root = cq.from(this.claseEntidad);
	}

	/**
	 *
	 * @param search
	 */
	private void buildCriteriaExpression(SearchExpression search) {

		List<Predicate> predicates = this.buildConditions(search);
		List<Order> orders = this.buildOrdering(search);

		cq.select(root);
		cq.where(predicates.toArray(new Predicate[] {}));
		cq.orderBy(orders.toArray(new Order[] {}));
	}

	/**
	 *
	 * @param search
	 * @return
	 */
	private List<Predicate> buildConditions(SearchExpression search) {

		List<Predicate> predicates = new ArrayList<>();

		Object entry = search.getValues().get(UtilConstantes.TQ_CONDITIONS);
		if (entry != null) {
			@SuppressWarnings("unchecked")
			List<SearchExpressionCriteria> list = (List<SearchExpressionCriteria>) entry;
			Iterator<SearchExpressionCriteria> it = list.iterator();
			while (it.hasNext()) {
				SearchExpressionCriteria se =  it.next();
				switch (se.getCompound()) {
				case UtilConstantes.CM_AND:
					predicates.add(cb.and(this.getCriteriaMethod(se.getField(), se)));
					break;
				case UtilConstantes.CM_OR:
					predicates.add(cb.or(this.getCriteriaMethod(se.getField(), se)));
					break;
				case UtilConstantes.CM_NOT:
					predicates.add(cb.not(this.getCriteriaMethod(se.getField(), se)));
					break;
				default:
					predicates.add(cb.and(this.getCriteriaMethod(se.getField(), se)));
				}
			}
		}

		return predicates;
	}

	/**
	 *
	 * @param key
	 * @param se
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Predicate getCriteriaMethod(String key, SearchExpressionCriteria se) {
		Predicate pd;
		SingularAttribute sa = root.getModel().getId(root.getModel().getIdType().getJavaType());
		Path p = root;
		if (sa.getType().getJavaType().isAnnotationPresent(Embeddable.class))
			p = root.<Object>get(sa.getName());
		switch (se.getMethod()) {
		case UtilConstantes.CR_EQUAL:
			pd = cb.equal(p.<Object>get(key), se.getValues().get(0));
			break;
		case UtilConstantes.CR_NOT_EQUAL:
			pd = cb.notEqual(p.<String>get(key), se.getValues().get(0));
			break;
		case UtilConstantes.CR_GREATER_THAN:
			pd = cb.greaterThan(p.<String>get(key), se.getValues().get(0));
			break;
		case UtilConstantes.CR_GREATER_EQUAL:
			pd = cb.greaterThanOrEqualTo(p.<String>get(key), se.getValues().get(0));
			break;
		case UtilConstantes.CR_LESS_THAN:
			pd = cb.lessThan(p.<String>get(key), se.getValues().get(0));
			break;
		case UtilConstantes.CR_LESS_EQUAL:
			pd = cb.lessThanOrEqualTo(p.<String>get(key), se.getValues().get(0));
			break;
		case UtilConstantes.CR_BETWEEN:
			pd = cb.between(p.<Integer>get(key), Integer.parseInt(se.getValues().get(0)),
					Integer.parseInt(se.getValues().get(1)));
			break;
		case UtilConstantes.CR_LIKE:
			pd = cb.like(p.<String>get(key), "%" + se.getValues().get(0) + "%");
			break;
		case UtilConstantes.EX_IS_NULL:
			pd = p.<String>get(key).isNull();
			break;
		case UtilConstantes.EX_IS_NOT_NULL:
			pd = p.<String>get(key).isNotNull();
			break;
		case UtilConstantes.EX_IN:
			pd = p.<String>get(key).in(se.getValues());
			break;
		default:
			pd = p.<String>get(key).isNotNull();
		}
		return pd;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Order> buildOrdering(SearchExpression search) {

		List<Order> orders = new ArrayList<>();
		// Inicio Daniel
		SingularAttribute sa = root.getModel().getId(root.getModel().getIdType().getJavaType());
		Path p = root;
		if (sa.getType().getJavaType().isAnnotationPresent(Embeddable.class))
			p = root.<Object>get(sa.getName());
		// Fin Daniel
		Object entry = search.getValues().get(UtilConstantes.TQ_ORDERING);
		if (entry != null) {
			List<SearchExpressionOrder> list = (List<SearchExpressionOrder>) entry;
			Iterator<SearchExpressionOrder> it = list.iterator();
			while (it.hasNext()) {
				SearchExpressionOrder se =  it.next();
				switch (se.getOrder()) {
				case UtilConstantes.OR_ASC:
					orders.add(cb.asc(p.<String>get(se.getField())));
					break;
				case UtilConstantes.OR_DESC:
					orders.add(cb.desc(p.<String>get(se.getField())));
					break;
				default:
					orders.add(cb.asc(p.<String>get(se.getField())));

				}
			}
		}
		return orders;
	}

	@Override
	public boolean estaActiva() {
		return mp.getTransaction().isActive();
	}

	@Override
	public void iniciaTransaccion() {
		mp.getTransaction().begin();
	}

	@Override
	public void rollback() {
		mp.getTransaction().rollback();
	}

	@Override
	public void commit() {
		mp.getTransaction().commit();
	}

}
