package com.segurosbolivar.avaluos.modelo.data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.dto.HistoricoAvaluoConsultaDTO;
import com.segurosbolivar.avaluos.modelo.entity.HistoricoAvaluo;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.service.util.UtilJpa;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad PGB_HIST_AVALUOS. Se encarga de las
 * operaciones a base de datos de la tabla de EmpresaAvaluadora a través del uso
 * del entityManager.
 * 
 * @author GeneradorCRUD
 */
@Stateless
public class HistoricoAvaluoDao extends ManejadorCrud<HistoricoAvaluo, Long> {

    private static final long serialVersionUID = -2582116391810114701L;

    public HistoricoAvaluoDao() {
	super(HistoricoAvaluo.class);
    }

    public static final String SELECT = "SELECT hist FROM HistoricoAvaluo hist ";
    public static final String COUNT = "SELECT COUNT( hist.secuenciaHistorico) FROM HistoricoAvaluo hist ";

    /**
     * Permite consultar un registro de historico de avaluo por su Id.
     * 
     * @param idHistAvaluo
     *            id de registro a consultar.
     * @return historico que coincide con el id.
     */
    public HistoricoAvaluo buscarPorId(BigDecimal idHistAvaluo) {
	StringBuilder sb = new StringBuilder();
	boolean tieneCondiciones = false;
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "hist.secuenciaHistorico", idHistAvaluo, null, null, tieneCondiciones, true, false, false);
	String where = tieneCondiciones ? UtilConstantes.SQL_WHERE + sb.toString() : "";
	String query = SELECT.concat(where);
	Query consulta = mp.createQuery(query);
	return (HistoricoAvaluo) consulta.getResultList().get(0);
    }

    /**
     * Realiza la consulta dinamica del historico de avaluos con un orden
     * establecido por el usuario.
     * 
     * @param historicoAvaluoDTO
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
     * @return listado de historico de avaluos que cumplen con las condiciones de
     *         los filtros.
     */
    @SuppressWarnings("unchecked")
    public List<HistoricoAvaluo> getHistoricoAvaluoList(HistoricoAvaluoConsultaDTO historicoAvaluoDTO, int inicio, int tamanioPagina, String campoOrden,
	    SentidoOrientacion sentido) {
	String query = SELECT.concat(generarWhere(historicoAvaluoDTO))
		.concat(UtilTexto.estaVacio(campoOrden) ? "ORDER BY hist.secuenciaHistorico" : UtilJpa.generarOrder("hist", campoOrden, sentido));
	Query consulta = mp.createQuery(query);
	agregarParametros(consulta, historicoAvaluoDTO);
	consulta.setMaxResults(tamanioPagina);
	consulta.setFirstResult(inicio);
	return consulta.getResultList();
    }

    /**
     * Permite agregar los parametros a la consulta JPA de los historicos de
     * avaluos.
     * 
     * @param historicoAvaluoDTO
     *            JPA a la cual se agregaran los parametros.
     * @param consultar
     *            contiene los filtros que se aplciaran como parametros.
     */
    private void agregarParametros(Query consulta, HistoricoAvaluoConsultaDTO historicoAvaluoDTO) {
	UtilJpa.agregarParametros(consulta, "fechaAvaluoDesde", historicoAvaluoDTO.getFechaAvaluoDesde());
	UtilJpa.agregarParametros(consulta, "fechaAvaluoHasta", historicoAvaluoDTO.getFechaAvaluoHasta());
	UtilJpa.agregarParametros(consulta, "fechaCreacionDesde", historicoAvaluoDTO.getFechaCreacionDesde());
	UtilJpa.agregarParametros(consulta, "fechaCreacionHasta", historicoAvaluoDTO.getFechaCreacionHasta());
	UtilJpa.agregarParametros(consulta, "fechaTransaccionDesde", historicoAvaluoDTO.getFechaTransaccionDesde());
	UtilJpa.agregarParametros(consulta, "fechaTransaccionHasta", historicoAvaluoDTO.getFechaTransaccionHasta());
    }

    /***
     * Permite determinar la cantidad de registros para la consulta a realizarse de
     * historicos de avaluos, esto permitira el uso del paginador para la consulta.
     * 
     * @param historicoAvaluoDTO
     *            contiene los valores del filtro que se usaran en la consulta y
     *            para determinar la cantidad de registros.
     * @return el numero de registros que cumplen con los filtros de la consulta
     */
    public int cantidadRegistros(HistoricoAvaluoConsultaDTO historicoAvaluoDTO) {
	String query = COUNT.concat(generarWhere(historicoAvaluoDTO));
	Query consulta = mp.createQuery(query);
	agregarParametros(consulta, historicoAvaluoDTO);
	return ((Long) consulta.getSingleResult()).intValue();
    }

    /**
     * genera la cadena WHERE para la consulta dinamica de los historicos de avaluo
     * 
     * @param historicoAvaluoDTO
     *            contiene los valores de los filtros a aplicar
     * @return cadena WHERE con las condicones JPA asociadas a los filtros
     *         aplicados.
     */
    private String generarWhere(HistoricoAvaluoConsultaDTO historicoAvaluoDTO) {
	if (historicoAvaluoDTO == null)
	    return "";
	StringBuilder sb = new StringBuilder();
	boolean tieneCondiciones = false;
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "hist.idTipoIdentificacion", historicoAvaluoDTO.getIdTipoIdentificacion(), null, null, tieneCondiciones, true, false,
		false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "hist.numeroIdentificacion", historicoAvaluoDTO.getNumeroIdentificacion(), null, null, tieneCondiciones, true, false,
		false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "hist.nombreSolicitante", historicoAvaluoDTO.getNombreSolicitante(), null, null, tieneCondiciones, true, false, false);
	if (historicoAvaluoDTO.getFechaAvaluoDesde() != null) {
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "hist.fechaAvaluo", "fechaAvaluoDesde", UtilConstantes.CR_GREATER_EQUAL, tieneCondiciones, true, false,
		    false);
	}
	if (historicoAvaluoDTO.getFechaAvaluoHasta() != null) {
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "hist.fechaAvaluo", "fechaAvaluoHasta", UtilConstantes.CR_LESS_EQUAL, tieneCondiciones, true, false, false);
	}
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "hist.idDepartamento", historicoAvaluoDTO.getIdDepartamento(), null, null, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "hist.idCiudad", historicoAvaluoDTO.getIdCiudad(), null, null, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "hist.direccionInmueble", historicoAvaluoDTO.getDireccionInmueble(), null, null, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "hist.matriculaInmobiliaria", historicoAvaluoDTO.getMatriculaInmobiliaria(), null, null, tieneCondiciones, true, false,
		false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "hist.estadoAvaluo", historicoAvaluoDTO.getEstadoAvaluo(), null, null, tieneCondiciones, true, false, false);
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "hist.usuario", historicoAvaluoDTO.getUsuarioAprueba(), null, null, tieneCondiciones, true, false, false);
	if (historicoAvaluoDTO.getFechaCreacionDesde() != null) {
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "hist.fechaCreacion", "fechaCreacionDesde", UtilConstantes.CR_GREATER_EQUAL, tieneCondiciones, true, false,
		    false);
	}
	if (historicoAvaluoDTO.getFechaCreacionHasta() != null) {
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "hist.fechaCreacion", "fechaCreacionHasta", UtilConstantes.CR_LESS_EQUAL, tieneCondiciones, true, false,
		    false);
	}
	tieneCondiciones |= UtilJpa.agregarCondicion(sb, "hist.usuarioTransaccion", historicoAvaluoDTO.getUsuarioTransaccion(), null, null, tieneCondiciones, true, false, false);
	if (historicoAvaluoDTO.getFechaTransaccionDesde() != null) {
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "hist.fechaTransaccion", "fechaTransaccionDesde", UtilConstantes.CR_GREATER_EQUAL, tieneCondiciones, true,
		    false, false);
	}
	if (historicoAvaluoDTO.getFechaTransaccionHasta() != null) {
	    tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "hist.fechaTransaccion", "fechaTransaccionHasta", UtilConstantes.CR_LESS_EQUAL, tieneCondiciones, true, false,
		    false);
	}
	return tieneCondiciones ? UtilConstantes.SQL_WHERE + sb.toString() : "";
    }

    /* Consulta por fecha */
    @SuppressWarnings("unchecked")
    @Deprecated
    public List<HistoricoAvaluo> historicoAvaluoPorFechaAvaluo(Date fechaAvaluo) {
	String query = SELECT.concat(generarWhereFecha()).concat("ORDER BY hist.secuenciaHistorico");
	Query consulta = mp.createQuery(query);
	agregarParametroFecha(consulta, fechaAvaluo);
	return consulta.getResultList();
    }

    @Deprecated
    private String generarWhereFecha() {
	StringBuilder sb = new StringBuilder();
	boolean tieneCondiciones = false;
	tieneCondiciones |= UtilJpa.agregarCondicionParametro(sb, "hist.fechaAvaluo", "fechaAvaluo", UtilConstantes.CR_LESS_EQUAL, tieneCondiciones, true, false, false);
	return tieneCondiciones ? UtilConstantes.SQL_WHERE + sb.toString() : "";
    }

    @Deprecated
    private void agregarParametroFecha(Query consulta, Date fechaAvaluo) {
	UtilJpa.agregarParametros(consulta, "fechaAvaluo", fechaAvaluo);
    }

}
