package com.segurosbolivar.avaluos.modelo.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.dto.ArchivosFileNetDto;
import com.segurosbolivar.avaluos.modelo.entity.Archivo;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.ListaAnexosPdf;
import com.segurosbolivar.avaluos.modelo.entity.PeritosEmpresa;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad ListaAnexosPdf. Se encarga de las
 * operaciones a base de datos de la tabla de PGB_LST_ANEXOS_PDF a través del
 * uso del entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class ListaAnexosPdfDao extends ManejadorCrud<ListaAnexosPdf, Long> {

    private static final long serialVersionUID = -5316491789156131837L;

    public ListaAnexosPdfDao() {
	super(ListaAnexosPdf.class);
    }

    /**
     * Permite consultar los anexos fotograficos asociados a un avaluo determinado.
     * 
     * @param avaluo
     *            contiene el identificador del avaluo al que vamos a consultar los
     *            anexos.
     * @return lsitado de anexos fotograficos asociados al avaluo.
     */
    @SuppressWarnings("unchecked")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<ListaAnexosPdf> consultaAnexosPorAvaluo(Avaluo avaluo) {
	Query query = mp.createNamedQuery(ListaAnexosPdf.class.getSimpleName() + ".obtenerPorAvaluo");
	query.setParameter("idAvaluo", avaluo.getIdAvaluo());
	return query.getResultList();
    }

    /**
     * Consulta los archivos pendientes que no se han enviado a FileNet con estado
     * nuevo o completos segun las condiciones del control de cambios
     * 
     * @param maxResultList
     *            cantidad limite de registros a consultar.
     * @return Registros que estan pendientes por envio a FileNet con el objeto
     *         ArchivosFileNetDto
     * @throws SQLException
     * @throws NegocioException
     * @author arincon
     */
    public List<ArchivosFileNetDto> consultaArchivosNoEnviados(int maxResultList, int mesesAnteriores) throws SQLException, NegocioException {
	List<ArchivosFileNetDto> archivosEnvio = new ArrayList<>();
	CriteriaBuilder cb = mp.getCriteriaBuilder();
	CriteriaQuery<Tuple> cq = cb.createTupleQuery();
	Root<ListaAnexosPdf> listaAnexosPdfRoot = cq.from(ListaAnexosPdf.class);
	Join<ListaAnexosPdf, Archivo> joinListaAnexosArchivo = listaAnexosPdfRoot.join(ListaAnexosPdf.ENTIDAD_PGB_LST_ANEXOS_PDF_FK_ID_ARCHIVO, JoinType.INNER);
	Join<ListaAnexosPdf, Avaluo> joinListaAnexosAvaluo = listaAnexosPdfRoot.join(ListaAnexosPdf.ENTIDAD_PGB_LST_ANEXOS_PDF_FK_ID_AVALUO, JoinType.INNER);
	Join<Avaluo, PeritosEmpresa> joinAvaluoUsuario = joinListaAnexosAvaluo.join(Avaluo.ENTIDAD_PGB_AVALUOS_FK_ID_PERITO_EMPRESA, JoinType.INNER);
	//
	cq.multiselect(listaAnexosPdfRoot.alias(ListaAnexosPdf.class.getSimpleName()),
		listaAnexosPdfRoot.get(ListaAnexosPdf.ENTIDAD_PGB_LST_ANEXOS_PDF_FK_ID_ARCHIVO).alias(Archivo.class.getSimpleName()),
		listaAnexosPdfRoot.get(ListaAnexosPdf.ENTIDAD_PGB_LST_ANEXOS_PDF_FK_ID_AVALUO).alias(Avaluo.class.getSimpleName()),
		joinAvaluoUsuario.get(PeritosEmpresa.ENTIDAD_PGB_PERITOS_EMPRESAS_NUMERO_DOCUMENTO).alias(PeritosEmpresa.ENTIDAD_PGB_PERITOS_EMPRESAS_NUMERO_DOCUMENTO),
		joinAvaluoUsuario.get(PeritosEmpresa.ENTIDAD_PGB_PERITOS_EMPRESAS_NOMBRE_PERITO).alias(PeritosEmpresa.ENTIDAD_PGB_PERITOS_EMPRESAS_NOMBRE_PERITO));
	// Lista de condiciones
	List<Predicate> predicates = new ArrayList<>();
	// Condiciones de segmentacion para los archivos
	Predicate filtroIdDocumentoDesconocido = cb.equal(joinListaAnexosArchivo.get(Archivo.ENTIDAD_ARCHIVOS_ID_DOCUEMNTO), "DESCONOCIDO");
	Predicate filtroIdDocumentoVacio = cb.isNull(joinListaAnexosArchivo.get(Archivo.ENTIDAD_ARCHIVOS_ID_DOCUEMNTO));
	predicates.add(cb.or(filtroIdDocumentoDesconocido, filtroIdDocumentoVacio));
	// Condicion para verificar que tengan contenido
	Predicate filtrocontenido = cb.isNotNull(joinListaAnexosArchivo.get(Archivo.ENTIDAD_ARCHIVOS_CONTENIDO));
	predicates.add(cb.and(filtrocontenido));
	// Condiciones de segmentacion para los avaluos nuevos
	Predicate estadoAvaluoNuevo = cb.equal(joinListaAnexosAvaluo.get(Avaluo.ENTIDAD_PGB_AVALUOS_A_ESTADOAVALUO), UtilConstantes.ESTADO_AVALUOS_NUEVOS);
	ParameterExpression<Date> fechaParametro = cb.parameter(Date.class);
	Predicate fechaAvaluoParametro = cb.greaterThanOrEqualTo(joinListaAnexosAvaluo.get(Avaluo.ENTIDAD_PGB_AVALUOS_F_FECHAAVALUO).as(java.sql.Date.class), fechaParametro);
	Predicate filtroAvaluosNuevos = cb.and(estadoAvaluoNuevo, fechaAvaluoParametro);
	Predicate filtroAvaluosAprobados = cb.equal(joinListaAnexosAvaluo.get(Avaluo.ENTIDAD_PGB_AVALUOS_A_ESTADOAVALUO), UtilConstantes.ESTADO_AVALUOS_APROBADO);
	// Concatena los filtros de los estados para la consulta JPA
	predicates.add(cb.or(filtroAvaluosNuevos, filtroAvaluosAprobados));
	// Obtiene la fecha actual dos meses atras
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.MONTH, mesesAnteriores * -1);
	Date fechaCalculada = cal.getTime();
	// Realiza la consulta
	cq.where(predicates.toArray(new Predicate[] {}));
	TypedQuery<Tuple> tq = mp.createQuery(cq).setParameter(fechaParametro, fechaCalculada, TemporalType.DATE);
	for (Tuple t : tq.setMaxResults(maxResultList).getResultList()) {
	    // Ingresa la informacion del archivo segun los resultados del MuliSelect
	    ListaAnexosPdf regAnexo = (ListaAnexosPdf) t.get(ListaAnexosPdf.class.getSimpleName());
	    regAnexo.setAnexo(regAnexo.getArchivo().getContenidoArchivo());
	    regAnexo.setAvaluo((Avaluo) t.get(Avaluo.class.getSimpleName()));
	    archivosEnvio.add(new ArchivosFileNetDto(regAnexo, String.valueOf(t.get(PeritosEmpresa.ENTIDAD_PGB_PERITOS_EMPRESAS_NUMERO_DOCUMENTO)),
		    String.valueOf(t.get(PeritosEmpresa.ENTIDAD_PGB_PERITOS_EMPRESAS_NOMBRE_PERITO))));
	}
	return archivosEnvio;
    }

}
