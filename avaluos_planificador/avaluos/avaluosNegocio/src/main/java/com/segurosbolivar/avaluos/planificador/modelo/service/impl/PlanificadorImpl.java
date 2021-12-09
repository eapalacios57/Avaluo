package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.solicitud.PlanificadorModel;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.data.DepartamentoDao;
import com.segurosbolivar.avaluos.planificador.data.PlanificadorDao;
import com.segurosbolivar.avaluos.planificador.modelo.dto.PlanificadorDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Planificador;
import com.segurosbolivar.avaluos.planificador.modelo.entity.PlanificadorPK;
import com.segurosbolivar.avaluos.planificador.modelo.services.IPlanificador;

/**
 * Implementaci�n del servicio de archivo
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:38 a.m.
 */
@Stateless
@Local(IPlanificador.class)
public class PlanificadorImpl implements IPlanificador {

    private static final long serialVersionUID = -2088678805488718651L;
    
    @EJB
    private PlanificadorDao planificadorDao;

    @EJB
    private DepartamentoDao departamentoDao;

	@Override
	public void crearPlanificador(PlanificadorDTO planificador) {
		planificadorDao.crear(planificador);
		
	}

	@Override
	public List<Planificador> listaPlanificador() throws SQLException {
		List<Planificador> lista = new ArrayList<>();
		lista = planificadorDao.listarPlanificadores();
		
		return lista;
	}

    @Override
    public Planificador buscaPlanificador(PlanificadorPK planificadorPk) {
    	Planificador planificador = new Planificador();
    	
    	try {
			planificador = planificadorDao.buscarPlanificador(planificadorPk);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return planificador;
    }

	@Override
	public Planificador buscaPorNumDoc(String numeroDocumentoPlanificador) throws SQLException, NegocioException {
		Planificador planificador = new Planificador();
		
		planificador = planificadorDao.buscarPorNumeroDoc(numeroDocumentoPlanificador);
		return planificador;
	}

	@Override
	public Planificador actualziarTokenDispositivo(PlanificadorModel model) throws SQLException, NegocioException {
		// TODO Auto-generated method stub
		
		Planificador planificador = buscaPorNumDoc(model.getNumDocIdentificacion());
		planificador.setTokenDispositivo(model.getTokenDispositivo());
		planificador.setDispositivo(model.getDispositivo());
		
		planificadorDao.actualizar(planificador);
		
		return planificador;
		
	}
    
	@Override
	public List<Planificador> listaPlanificadorPorRegion(Long idDepartamento) throws SQLException {
		List<Planificador> lista = new ArrayList<>();
		BigDecimal idRegion = departamentoDao.buscarRegion(idDepartamento);
		lista = planificadorDao.listarPlanificadoresPorRegion(idRegion);
		
		return lista;
	}
  
}