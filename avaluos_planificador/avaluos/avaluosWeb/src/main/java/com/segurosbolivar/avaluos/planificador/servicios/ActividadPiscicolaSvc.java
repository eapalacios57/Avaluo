package com.segurosbolivar.avaluos.planificador.servicios;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadPiscicolaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadPiscicola;

@Path("/ActividadPiscicola")
@Stateless
public class ActividadPiscicolaSvc {

	@EJB
	IActividadPiscicola iActividadPiscicola;

	@Path("/buscarPorId/{idPiscicola}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaRest buscarActividadPiscicola(@PathParam("idPiscicola") BigDecimal idPiscicola) {
		try {
			ActividadPiscicolaDTO piscicolaDTO = iActividadPiscicola.buscarActividadPiscicola(idPiscicola);
			return new RespuestaRest("200", "Busqueda exitosa", piscicolaDTO);
		} catch (SQLException e) {
			e.printStackTrace();
			return new RespuestaRest("500", e.getMessage(), null);
		}
	}

	@Path("/actualizar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaRest actualizarActividadPiscicola(ActividadPiscicolaDTO piscicolaDTO) {
		try {
			iActividadPiscicola.actualizarActividadPiscicola(piscicolaDTO);
			return new RespuestaRest("200", "Actividad piscicola actualizada", "ok");
		} catch (NegocioException e) {
			e.printStackTrace();
			return new RespuestaRest("500", e.getMessage(), null);
		}
	}

}
