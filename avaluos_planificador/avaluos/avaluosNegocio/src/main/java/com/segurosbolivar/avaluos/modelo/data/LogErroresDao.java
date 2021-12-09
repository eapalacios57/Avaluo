package com.segurosbolivar.avaluos.modelo.data;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.entity.LogErrores;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad cargueTmp. Se encarga de las operaciones a
 * base de datos de la tabla de LogErrores a través del uso del
 * entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless	
public class LogErroresDao extends ManejadorCrud<LogErrores, BigDecimal> {
	private static final long serialVersionUID = 1L;
    public static final String SELECT = "SELECT c FROM LogErrores c ";


    public LogErroresDao() {
    super(LogErrores.class);
    }
    
    /**
     * Obtiene el valor de la secuencia con el que se registrara el batch
     *  de la tabla SEQ_LOG_ERRORES
     * 
     * @return numero del batch a generar.
     */
    public BigDecimal obtenerNumeroSeq() {
	Query query = mp.nativeQuery("SELECT SEQ_LOG_ERRORES.NEXTVAL FROM DUAL", null);
	BigDecimal resultado = (BigDecimal) query.getSingleResult();
	if (resultado == null)
	    return null;
	return resultado;
    }
}