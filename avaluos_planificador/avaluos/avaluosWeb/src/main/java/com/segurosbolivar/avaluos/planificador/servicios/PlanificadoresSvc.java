package com.segurosbolivar.avaluos.planificador.servicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.planificador.modelo.dto.PlanificadorDTO;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.PlanificadorFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.services.IPlanificador;


@Path("/Planificadores")
@Stateless
public class PlanificadoresSvc {

	@EJB
	IPlanificador planificador;
	
	@GET
	@Path("consultarTodos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaRest consultarTodos() {
		RespuestaRest respuesta;
		PlanificadorFullDTOMapper planificadorFullDTOMapper = Mappers.getMapper(PlanificadorFullDTOMapper.class);
		List<PlanificadorDTO> listado = new ArrayList<>();

		
		try {
		
			listado = planificadorFullDTOMapper.entity2dtoList(planificador.listaPlanificador());
			
			respuesta = new RespuestaRest("200","ok",listado);
		} catch (SQLException e) {
			e.printStackTrace();
			respuesta = new RespuestaRest("500",e.getMessage(),null);
		}

		return respuesta;
	}
	
	@GET
	@Path("consultarPorRegion/{idDepartamento}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaRest consultarPorRegion(@PathParam("idDepartamento") Long idDepartamento) {
		RespuestaRest respuesta;
		PlanificadorFullDTOMapper planificadorFullDTOMapper = Mappers.getMapper(PlanificadorFullDTOMapper.class);
		List<PlanificadorDTO> listado = new ArrayList<>();
		
		try {
			listado = planificadorFullDTOMapper.entity2dtoList(planificador.listaPlanificadorPorRegion(idDepartamento));
			respuesta = new RespuestaRest("200", "ok", listado);
			
		} catch (SQLException e) {
			e.printStackTrace();
			respuesta = new RespuestaRest("500",e.getMessage(), null);
		}
		return respuesta;
	}
	
}