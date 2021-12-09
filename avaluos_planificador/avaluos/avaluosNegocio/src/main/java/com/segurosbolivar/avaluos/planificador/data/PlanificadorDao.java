package com.segurosbolivar.avaluos.planificador.data;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.dto.PlanificadorDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Planificador;
import com.segurosbolivar.avaluos.planificador.modelo.entity.PlanificadorPK;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.PlanificadorFullDTOMapper;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad peritos. Se encarga de las operaciones a
 * base de datos de la tabla de PGB_PERITOS_EMPRESAS a través del uso del
 * entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class PlanificadorDao extends ManejadorCrud<Planificador, PlanificadorPK> {

	private static final long serialVersionUID = 7747626177696187846L;

	public PlanificadorDao() {
		super(Planificador.class);
	}

	public List<Planificador> listarPlanificadores() throws SQLException {

		List<Planificador> consulta = mp.createNamedQuery("Planificador.findAll").getResultList();

		return consulta;
	}
	
	public List<Planificador> listarPlanificadoresPorRegion(BigDecimal idRegion) throws SQLException {
			Query query = mp.createNamedQuery("Planificador.findByRegion");
			query.setParameter("idRegion", idRegion);
			List<Planificador> consulta = query.getResultList();
			return consulta;
	}

	public PlanificadorDTO crear(PlanificadorDTO planificadorDTO) {

		PlanificadorFullDTOMapper planificadorFullDTOMapper = Mappers.getMapper(PlanificadorFullDTOMapper.class);

		System.out.println(planificadorDTO.getId().getNumeroDocumentoPlanificador());

		Planificador planificador = planificadorFullDTOMapper.dto2entity(planificadorDTO);
		planificador.setId(planificadorDTO.getId());
		System.out.println(planificador.getId().getNumeroDocumentoPlanificador());
		mp.create(planificador);

		return planificadorDTO;
		// protected region Modifique el metodo crear end
	}

	public Planificador buscarPlanificador(PlanificadorPK planificadorPk) throws SQLException {

		Query query = mp.createNamedQuery("Planificador.findById");
		query.setParameter("id", planificadorPk);
		Planificador consulta = (Planificador) query.getSingleResult();

		return consulta;
	}

	public Planificador buscarPorNumeroDoc(String numeroDocumentoPlanificador) throws SQLException {
		Planificador consulta = new Planificador();

		try {
			Query query = mp.createNamedQuery("Planificador.findByNumDoc");
			query.setParameter("numeroDocumentoPlanificador", numeroDocumentoPlanificador);
			return (Planificador) query.getSingleResult();
		} catch (NoResultException e) {
			e.getMessage();
			return consulta;
		}

	}

}
