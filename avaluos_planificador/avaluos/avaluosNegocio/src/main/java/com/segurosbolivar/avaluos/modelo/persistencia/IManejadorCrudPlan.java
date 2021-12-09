package com.segurosbolivar.avaluos.modelo.persistencia;

import java.util.List;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.service.util.SearchExpression;

/**
 * 
 * @author GeneradorCRUD
 * @param <T> Clase de la entidad asociada al manejador
 * @param <U> Clase del pk de la entidad T. Si es un PK compuesto es una clase con la etiqueta @Embbeded, sino una clase "primitiva" como Integer, Long, String, etc.
 */
public interface IManejadorCrudPlan<T,U> {

    /**
     * Busca el objeto de tipo T cuyo identificar es pId
     * 
     * @param pId Identificador el objeto de clase T
     * @return El objeto de la entidad T encontrado en la busqueda
     */
    public T buscar(U pId);   

    /**
     * Crea el objeto de clase T que se pasa como parámetro en la base de datos
     * @param obj Instancia de la entidad T a almacenar en la base de datos
     * @throws NegocioException 
     */
    public void crear(T obj) throws NegocioException;

    /**
     * Crea una instancia de la clase T sin ningún valor seteado
     * @return Instancia de la entidad T sin valores asignados
     */
    public T crear();

    /**
     * Actualiza en base de datos el registro correspondiente al objeto de la entidad
     * T que se pasa como parámetro
     * @param obj Instancia de la entidad T a actualizar en la base de datos
     * @throws NegocioException 
     */
    public void actualizar(T obj) throws NegocioException;
    

    /**
     * Elimina en base de datos el registro correspondiente al objeto de la entidad
     * T que se pasa como parámetro
     * @param obj Instancia de la entidad T a eliminar de la base de datos
     * @throws NegocioException 
     */
    public void eliminar(T obj) throws NegocioException;

    /**
     * Elimina en base de datos el registro correspondiente al objeto de la entidad
     * T que se identifica con el parámetro pId
     * @param pId Instancia del identificador del objeto de la entidad T que se va a eliminar 
     * de la base de datos.
     * @throws NegocioException 
     */
    public void eliminarPorId(U pId) throws NegocioException;

    /**
     * 
     * @param search
     * @return
     */
    public List<T> consultarPorCriteria(SearchExpression search);
    
    public void iniciaTransaccion();

    public void commit();

    public boolean estaActiva();

    public void rollback();
    
}