package com.segurosbolivar.avaluos.modelo.data;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.asesoftware.util.lang.UtilFecha;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.dto.ConsultaCargueMasivoDto;
import com.segurosbolivar.avaluos.modelo.dto.DetalleMasivoDto;
import com.segurosbolivar.avaluos.modelo.entity.CargueTmp;
import com.segurosbolivar.avaluos.modelo.entity.VResumenCargue;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.service.util.UtilJpa;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad cargueTmp. Se encarga de las operaciones a
 * base de datos de la tabla de PGB_CARGUE_TMP a través del uso del
 * entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class CargueTmpDao extends ManejadorCrud<CargueTmp, BigDecimal> {

    private static final long serialVersionUID = 1L;
    public static final String SELECT = "SELECT c FROM VResumenCargue c ";
    public static final String SELECT_CARGUE_TMP = "SELECT p FROM CargueTmp p ";
    public static final String COUNT = "SELECT COUNT(0) FROM VResumenCargue c ";
    public static final String COUNT_CARGUE_TMP = "SELECT COUNT(p.consecutivoTmp) FROM CargueTmp p ";
    public static final String PARAMETRO_REF_CARGUE = "refCargue";

    public CargueTmpDao() {
	super(CargueTmp.class);
    }

    /**
     * Realiza la consulta dinamica de los cargues temporales 
     * 
     * @param consultar
     *            contiene los valores de los filtros a aplicar en la consulta
     * @param inicio
     *            determinar el registro incial para el paginador desde donde se
     *            inicia la consulta
     * @param tamanioPagina
     *            determina la cantidad de registros a consultar
     * @param campoOrden
     *            establece el campo por el cual se ordenara la consulta.
     * @param sentido
     *            determinar el sentido de ordenamiento para el campo por el cual se
     *            establece la consulta.
     * @return listado de cargues temporales que cumplen con las condiciones de los
     *         filtros.
     */
    @SuppressWarnings("unchecked")
    public List<VResumenCargue> consultar(ConsultaCargueMasivoDto consulta, int inicio, int registroxPagina, String campoOrden, SentidoOrientacion sentido) {
	String cadena = SELECT.concat(generaWhere(consulta))
		.concat(UtilTexto.estaVacio(campoOrden) ? "ORDER BY c.numeroRefCargue DESC" : UtilJpa.generarOrder("c", campoOrden, sentido));
	Query query = mp.createQuery(cadena);
	agregarParametros(query, consulta);
	query.setMaxResults(registroxPagina);
	query.setFirstResult(inicio);
	return (List<VResumenCargue>) query.getResultList();
    }

    /***
     * Permite determinar la cantidad de registros para la consulta a realizarse de
     * cargues temporales, esto permitira el uso del paginador para la consulta.
     * 
     * @param consultar
     *            contiene los valores del filtro que se usaran en la consulta y
     *            para determinar la cantidad de registros.
     * @return el numero de registros que cumplen con los filtros de la consulta
     */
    public int cantidadRegistros(ConsultaCargueMasivoDto consultar) {
	String query = COUNT.concat(generaWhere(consultar));
	Query consulta = mp.createQuery(query);
	agregarParametros(consulta, consultar);
	return ((Long) consulta.getSingleResult()).intValue();
    }

    /**
     * Permite agregar a una consulta de JPA los valores que deben enviarse
     * obligatoriamente como parametros del aplicativo.
     * 
     * @param consulta
     *            JPA al que se asociaran los parametros del filtro.
     * @param consultar
     *            contiene los valores del filtro que se aplicaran como parametros a
     *            la consulta de JPA
     */
    private void agregarParametros(Query consulta, ConsultaCargueMasivoDto consultar) {
	UtilJpa.agregarParametros(consulta, "fechaTransaccionDesde", consultar.getFechaCargueDesde());
	UtilJpa.agregarParametros(consulta, "fechaTransaccionHasta", UtilFecha.sumaryRestarDias(consultar.getFechaCargueHasta(), 1));
    }

    /**
     * genera la cadena WHERE para la consulta dinamica de los cargues temporales
     * 
     * @param consultar
     *            contiene los valores de los filtros a aplicar
     * @return cadena WHERE con las condicones JPA asociadas a los filtros
     *         aplicados.
     */
    private String generaWhere(ConsultaCargueMasivoDto consulta) {
	if (consulta == null)
	    return "";
	StringBuilder sb = new StringBuilder();
	boolean tieneCondiciones = false;

	if (!UtilTexto.estaVacio(consulta.getNumeroBatch())) {
	    tieneCondiciones |= UtilJpa.agregarCondicionLIKE(sb, "c.consecutivoBatch", consulta.getNumeroBatch(), tieneCondiciones, true, false, false);

	}
	if (!UtilTexto.estaVacio(consulta.getNumeroReferencia())) {
	    tieneCondiciones |= UtilJpa.agregarCondicionLIKE(sb, "c.numeroRefCargue", consulta.getNumeroReferencia(), tieneCondiciones, true, false, false);
	}
	if (!UtilTexto.estaVacio(consulta.getNombreTxt())) {
	    tieneCondiciones |= UtilJpa.agregarCondicionLIKE(sb, "UPPER(c.nombreArchivo)", consulta.getNombreTxt().toUpperCase(), tieneCondiciones, true, false, false);
	}
	if (consulta.getFechaCargueDesde() != null) {
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "c.fechaTransaccion", "fechaTransaccionDesde", UtilConstantes.CR_GREATER_EQUAL, tieneCondiciones, true, false,
		    false);
	}
	if (consulta.getFechaCargueHasta() != null) {
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "c.fechaTransaccion", "fechaTransaccionHasta", UtilConstantes.CR_LESS_EQUAL, tieneCondiciones, true, false,
		    false);
	}
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "c.usuarioTransaccion", consulta.getNumeroIdPerito(), null, null, tieneCondiciones, true, false, false);
	String condicion = UtilConstantes.SQL_WHERE + " c.tipoCargue = 'CARGUE' ";
	return tieneCondiciones ? condicion + "AND " + sb.toString() : condicion;
    }

    /**
     * Realiza el llamado del procedimiento almacenado encargado de realizar el
     * cargue masivo de avaluos.
     * 
     * @param proyectoConstructor
     *            determina si el cargue a realizar es de avaluos de proyecto
     *            constructor
     * @param cargueTmp
     *            contiene el numero de referencia del cargue a ejecutar.
     * @param tipoCargue
     *            especifica la tarea a realizar. ya sea cargue masivo(CARGUE) o
     *            validacion (VALIDAWEB).
     */
    public void ejecutaCargueConstructor(Boolean proyectoConstructor, CargueTmp cargueTmp, String tipoCargue) {
	Query consulta = mp.nativeQuery("BEGIN PRC_CARGA_AVALUO_CONSTRUCTOR(pREFERENCIA_CARGUE=>?,pTIPO_CARGUE=>?,pProyecto_constructor=>?); END;", null);
	consulta.setParameter(1, cargueTmp.getNumeroRefCargue().toString());
	consulta.setParameter(2, tipoCargue);
	consulta.setParameter(3, proyectoConstructor.toString());
	consulta.executeUpdate();
    }

    /**
     * Obtiene el numero de la secuencia para la referencia del cargue temporal.
     * 
     * @return numero de referencia para la secuencia de cargue a generar. Nulo en
     *         caso de que no se pueda determinar el valor.
     */
    public Long obtenerNumeroSecuencia() {
	Query query = mp.nativeQuery("SELECT SEQ_PGB_REFERENCIA_CARGUE.NEXTVAL FROM DUAL", null);
	BigDecimal resultado = (BigDecimal) query.getSingleResult();
	if (resultado == null)
	    return null;
	return (Long) resultado.longValue();
    }

    /**
     * Obtiene el valor de la secuencia con el que se registrara el batch del cargue
     * masivo de avaluos.
     * 
     * @return numero del batch a generar.
     */
    public Long obtenerNumeroBatch() {
	Query query = mp.nativeQuery("SELECT SEQ_PGB_CONSECUTIVO_BATCH.NEXTVAL FROM DUAL", null);
	BigDecimal resultado = (BigDecimal) query.getSingleResult();
	if (resultado == null)
	    return null;
	return (Long) resultado.longValue();
    }

    /**
     * Determina el estado de un cargue temporal de avaluos que se realizo.
     * 
     * @param cargueTmp
     *            contiene el numero de referencia y el consecutivo del cargue para
     *            identificar el estado del mismo.
     * @return verdadero en caso de que el estado del cargue sea APLICADO.
     */
    public boolean obtieneEstadoCargue(CargueTmp cargueTmp) {
	long consecutivoCargue = cargueTmp.getConsecutivoTmp();
	Query consultaEstado = mp.createNamedQuery(CargueTmp.class.getSimpleName() + ".estadoCargue");
	consultaEstado.setParameter(PARAMETRO_REF_CARGUE, cargueTmp.getNumeroRefCargue());
	consultaEstado.setParameter("consecutivo", consecutivoCargue);
	String estado = (String) consultaEstado.getSingleResult();
	return estado.equals(UtilConstantes.ESTADO_CARGUE_APLICADO_DESCARGA);
    }

    /**
     * Obtiene el mensaje de error que se genero para el procesamiento de una linea
     * especifica en un cargue temporal determinado
     * 
     * @param cargueTmp
     *            contiene los filtros para consultar el mensaje de error en este
     *            caso en este caso debe contener la referencia y el consecutivo
     *            temporal.
     * @return mensaje de error para la linea especificada del cargue.
     */
    public String obtenerMensajeError(CargueTmp cargueTmp) {
	Query query = mp.createNamedQuery(CargueTmp.class.getSimpleName() + ".obtenerMsjError");
	query.setParameter(PARAMETRO_REF_CARGUE, cargueTmp.getNumeroRefCargue().intValue());
	query.setParameter("consecutivo", cargueTmp.getConsecutivoTmp());
	return (String) query.getSingleResult();
    }

    /**
     * Permite realizar la consulta paginada de los cargues temporales asociados a
     * una referencia especifica del cargue.
     * 
     * @param referencia
     *            contiene los valores de los filtros a aplicar en la consulta
     * @param inicio
     *            determinar el registro incial para el paginador desde donde se
     *            inicia la consulta
     * @param tamanioPagina
     *            determina la cantidad de registros a consultar
     * @param campoOrden
     *            establece el campo por el cual se ordenara la consulta.
     * @param sentido
     *            determinar el sentido de ordenamiento para el campo por el cual se
     *            establece la consulta.
     * @return listado de lineas procesadas en el cargue que cumplen con las
     *         condiciones de los filtros.
     */
    @SuppressWarnings("unchecked")
    public List<CargueTmp> obtenerCargueTmpPorRef(DetalleMasivoDto referencia, int inicio, int registroxPagina, String campoOrden, SentidoOrientacion sentido) {
	String cadena = SELECT_CARGUE_TMP.concat(generaWhere(referencia).concat(UtilJpa.generarOrder("p", campoOrden, sentido)));
	Query query = mp.createQuery(cadena);
	query.setMaxResults(registroxPagina);
	query.setFirstResult(inicio);
	return (List<CargueTmp>) query.getResultList();
    }

    /***
     * Permite determinar la cantidad de registros para la consulta a realizarse de
     * cargues temporales, esto permitira el uso del paginador para la consulta.
     * 
     * @param consultar
     *            contiene los valores del filtro que se usaran en la consulta y
     *            para determinar la cantidad de registros.
     * @return el numero de registros que cumplen con los filtros de la consulta
     */
    public Long cantidadRegistros(DetalleMasivoDto consultar) {
	String query = COUNT_CARGUE_TMP.concat(generaWhere(consultar));
	Query consulta = mp.createQuery(query);
	return (Long) consulta.getSingleResult();
    }

    /**
     * genera la cadena WHERE para la consulta dinamica de los cargues temporales.
     * 
     * @param consultar
     *            contiene los valores de los filtros a aplicar
     * @return cadena WHERE con las condicones JPA asociadas a los filtros
     *         aplicados.
     */
    private String generaWhere(DetalleMasivoDto consulta) {
	if (consulta == null)
	    return "";
	StringBuilder sb = new StringBuilder();
	boolean tieneCondiciones = false;
	if (consulta.getNumeroReferencia() != null)
	    tieneCondiciones |= UtilJpa.agregarCondicion(sb, "p.numeroRefCargue", consulta.getNumeroReferencia(), null, null, tieneCondiciones, true, false, false);
	if (consulta.getLineaPlano() != null)
	    tieneCondiciones |= UtilJpa.agregarCondicion(sb, "p.lineaPlano", consulta.getLineaPlano(), null, null, tieneCondiciones, true, false, false);
	if (!UtilTexto.estaVacio(consulta.getEstadoCargue()))
	    tieneCondiciones |= UtilJpa.agregarCondicion(sb, "p.estadoCargue", consulta.getEstadoCargue(), null, null, tieneCondiciones, true, false, false);
	return tieneCondiciones ? UtilConstantes.SQL_WHERE + sb.toString() : "";
    }

    /**
     * Permite obtener una linea especifica cargada dentro de un archivo de cargue.
     * 
     * @param referencia
     *            del cargue realizado.
     * @param linea
     *            linea especifica a consultar del cargue
     * @return cargue temporal asociado a la referencia de cargue y la linea
     *         asociada.
     */
    public CargueTmp obtenerCargueTmpPorLinea(Long referencia, Long linea) {
	Query query = mp.createNamedQuery(CargueTmp.class.getSimpleName() + ".obtenerPorLinea");
	query.setParameter(PARAMETRO_REF_CARGUE, referencia.intValue());
	query.setParameter("linea", linea);
	CargueTmp cargueTmp = (CargueTmp) query.getSingleResult();
	return cargueTmp;
    }

}
