package com.segurosbolivar.avaluos.planificador.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.modelo.dto.PredioDTO;
import com.segurosbolivar.avaluos.planificador.modelo.services.IPredio;

@Path("/Predio")
@Stateless
public class PredioSvc {

	@EJB
	IPredio iPredio;

	@Path("/modificar")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public RespuestaRest modificar(List<PredioDTO> listaPrediosModel) {
		RespuestaRest respuesta;

		try {
			for (PredioDTO predioDto : listaPrediosModel) {
				predioDto = iPredio.actualizaPredio(predioDto);
			}
			respuesta = new RespuestaRest("200", "ok", listaPrediosModel);
		} catch (NegocioException e) {
			respuesta = new RespuestaRest("500", e.getMessage(), null);
		}

		return respuesta;

	}

	@Path("/consultar")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public RespuestaRest consultar() {
		RespuestaRest respuesta;

		try {
			List<PredioDTO> listaPredioDto = iPredio.consultaPredio();
			respuesta = new RespuestaRest("200", "ok", listaPredioDto);
		} catch (NegocioException e) {
			respuesta = new RespuestaRest("500", e.getMessage(), null);
		}

		return respuesta;

	}

}