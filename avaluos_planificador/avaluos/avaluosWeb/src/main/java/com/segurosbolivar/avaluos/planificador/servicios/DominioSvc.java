package com.segurosbolivar.avaluos.planificador.servicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.segurosbolivar.avaluos.modelo.entity.Dominios;
import com.segurosbolivar.avaluos.planificador.modelo.dto.DominiosPlanificadorDTO;
import com.segurosbolivar.avaluos.planificador.modelo.services.IDominios;

@Path("/Dominios")
@Stateless
public class DominioSvc {
	
	@EJB
	IDominios dominios;
	
	@GET
	@Path("/consultarTipDoc")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaRest consultarTipDoc() {
		RespuestaRest respuesta;

		DominiosPlanificadorDTO dominiosDto;
		List<DominiosPlanificadorDTO> listado = new ArrayList<>();

		try {
			for (Dominios dominio:dominios.getDominios() ) {
				dominiosDto = new DominiosPlanificadorDTO();
				dominiosDto.setRvMeaning(dominio.getRvMeaning());				
				dominiosDto.setRvLowValue(dominio.getRvLowValue());
				listado.add(dominiosDto);
			}
			respuesta = new RespuestaRest("200","ok",listado);
		} catch (SQLException e) {
			e.printStackTrace();
			respuesta = new RespuestaRest("500",e.getMessage(),null);
		}

		return respuesta;
	}
	
}