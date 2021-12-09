package com.segurosbolivar.avaluos.planificador.servicios;

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
import com.segurosbolivar.avaluos.planificador.modelo.dto.TecnificacionAgricolaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.services.ITecnificacionAgricola;

@Path("/TecnificacionAgricola")
@Stateless
public class TecnificacionSvc {

	@EJB
	ITecnificacionAgricola iTecnificacionAgricola;

	@Path("/{codSolicitud}/{idUnidadProductiva}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaRest getTecnificacionAgricola(@PathParam("codSolicitud") String codSolicitud,
			@PathParam("idUnidadProductiva") String idUnidadProductiva) {

		RespuestaRest respuesta;

		
		TecnificacionAgricolaDTO tecnificacionAgricolaDTO = new TecnificacionAgricolaDTO();
		tecnificacionAgricolaDTO.setIdUnidadProductiva(new Long(idUnidadProductiva));
		
		try {
			TecnificacionAgricolaDTO tecEncontradaDto = iTecnificacionAgricola.buscarTecnificacionAgricola(tecnificacionAgricolaDTO);
			respuesta = new RespuestaRest("200", "", tecEncontradaDto);
		} catch (SQLException e) {
			respuesta = new RespuestaRest("500", e.getMessage(), null);
			e.printStackTrace();
		}

		return respuesta;
	}
	
	/**
	 * Mtodo para actualizar una tecnificacin agrcola
	 * @param tecnificacionAgricolaDTO
	 * @return
	 */
	@Path("/actualizar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaRest actualizarTecAgricola(TecnificacionAgricolaDTO tecnificacionAgricolaDTO) {
		try {
			iTecnificacionAgricola.actualizarTecAgricola(tecnificacionAgricolaDTO);
		} catch (NegocioException ex) {
			return new RespuestaRest("500", ex.getMessage(), null);
		}
		return new RespuestaRest("200", "ok", "ok");
	}

}
