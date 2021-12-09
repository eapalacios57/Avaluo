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
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadPorcicolaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadPorcicola;

@Path("/ActividadPorcicola")
@Stateless
public class ActividadPorcicolaSvc {

	@EJB
	IActividadPorcicola iActividadPorcicola;

	@Path("/{idPorcicola}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaRest buscarActividadGanadera(@PathParam("idPorcicola") BigDecimal idPorcicola) {
		try {
			ActividadPorcicolaDTO porcicolaDTO = iActividadPorcicola.buscarActPiscicolaPorId(idPorcicola);
			return new RespuestaRest("200", "", porcicolaDTO);
		} catch (SQLException e) {
			e.printStackTrace();
			return new RespuestaRest("500", e.getMessage(), null);
		}
	}

	@Path("/actualizar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaRest actualizarActividadPorcicola(ActividadPorcicolaDTO porcicolaDTO) {
		try {
			iActividadPorcicola.actualizarActividadPorcicola(porcicolaDTO);
			return new RespuestaRest("200", "Actividad porcicola actualizada", "ok");
		} catch (NegocioException e) {
			e.printStackTrace();
			return new RespuestaRest("500", e.getMessage(), null);
		}
	}

}
