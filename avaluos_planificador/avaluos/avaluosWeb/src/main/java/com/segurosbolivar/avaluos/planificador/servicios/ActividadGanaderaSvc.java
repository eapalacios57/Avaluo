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
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadGanaderaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadGanadera;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadGanadera;

@Path("/ActividadGanadera")
@Stateless
public class ActividadGanaderaSvc {

	@EJB
	IActividadGanadera iActividadGanadera;

	@Path("/{codSolicitud}/{idUnidadProductiva}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaRest getActividadGanadera(@PathParam("codSolicitud") String codSolicitud,
			@PathParam("idUnidadProductiva") String idUnidadProductiva) {

		RespuestaRest respuesta;

		ActividadGanaderaDTO busquedaDTO = new ActividadGanaderaDTO();
		busquedaDTO.setIdUnidadProductiva(new BigDecimal(idUnidadProductiva));

		try {
			ActividadGanaderaDTO actividadGanadera = iActividadGanadera.getActividadGanadera(busquedaDTO);

			respuesta = new RespuestaRest("200", "", actividadGanadera);
		} catch (SQLException e) {
			respuesta = new RespuestaRest("500", e.getMessage(), null);
			e.printStackTrace();
		}

		return respuesta;
	}

	@Path("/actualizar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaRest actualizarActividadGanadera(ActividadGanaderaDTO ganaderaDTO) {
		try {
			iActividadGanadera.actualizarActividadGanadera(ganaderaDTO);
			return new RespuestaRest("200", "Actividad ganadera actualizada", "ok");
		} catch (NegocioException e) {
			e.printStackTrace();
			return new RespuestaRest("500", e.getMessage(), null);
		}
	}

}
