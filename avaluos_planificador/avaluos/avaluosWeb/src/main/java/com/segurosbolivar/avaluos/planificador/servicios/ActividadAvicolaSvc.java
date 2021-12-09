package com.segurosbolivar.avaluos.planificador.servicios;

import java.math.BigDecimal;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadAvicolaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadAvicola;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadAvicola;

@Path("/ActividadAvicola")
@Stateless
public class ActividadAvicolaSvc {

	@EJB
	IActividadAvicola iActividadAvicola;

	@Path("/{codSolicitud}/{idUnidadProductiva}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaRest getActividadAvicola(@PathParam("codSolicitud") String codSolicitud,
			@PathParam("idUnidadProductiva") String idUnidadProductiva) {

		RespuestaRest respuesta;

		ActividadAvicolaDTO actividadAvicolaDTO = new ActividadAvicolaDTO();
		actividadAvicolaDTO.setIdUnidadProductiva(new BigDecimal(idUnidadProductiva));

		try {
			ActividadAvicolaDTO actAvicolaEncontrada = iActividadAvicola.getActividadAvicola(actividadAvicolaDTO);

			respuesta = new RespuestaRest("200", "", actAvicolaEncontrada);
		} catch (SQLException e) {
			respuesta = new RespuestaRest("500", e.getMessage(), null);
			e.printStackTrace();
		}

		return respuesta;
	}
	
	@Path("/actualizar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaRest actualizarActAvicola(ActividadAvicolaDTO avicolaDTO) {
		try {
			iActividadAvicola.actualizarActAvicola(avicolaDTO);
			return new RespuestaRest("200", "Actividad avicola actualizada", "ok");
		} catch (NegocioException e) {
			e.printStackTrace();
			return new RespuestaRest("500", e.getMessage(), null);
		}
	} 

}
