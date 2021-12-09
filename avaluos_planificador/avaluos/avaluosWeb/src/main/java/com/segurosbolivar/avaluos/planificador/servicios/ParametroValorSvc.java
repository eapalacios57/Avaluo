package com.segurosbolivar.avaluos.planificador.servicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.segurosbolivar.avaluos.planificador.modelo.dto.ParametroValorDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ParametroValor;
import com.segurosbolivar.avaluos.planificador.modelo.services.IParametroValor;

@Path("/ParametroValores")
@Stateless
public class ParametroValorSvc {

	@EJB
	IParametroValor parametroValor;

	/**
	 * Consulta los datos de la tabla ParametroValor
	 * @param ParametroValor
	 * @return Lista de ParametroValors
	 */

	@Path("/consulta")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaRest consulta() {
		List<ParametroValor> listParametroValor = new ArrayList<>();
		RespuestaRest respuesta;
		try {
			listParametroValor = parametroValor.listaParametroValor();
			respuesta = new RespuestaRest("200", "ok", listParametroValor);
		} catch (SQLException e) {
			e.printStackTrace();
			respuesta = new RespuestaRest("500", e.getMessage(), listParametroValor);
		}

		for (ParametroValor param : listParametroValor) {
			System.out.println("Datos: " + param.getId().getIdParametroValor() + " " + param.getDescripcion());
		}
		
		return respuesta;
	}
	
	/**
	 * Inserta registro con los datos de ParametroValor
	 * @param ParametroValor
	 * @return 
	 */
	
	@Path("/inserta")	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaRest inserta(ParametroValorDTO parametroValorDto) {
		RespuestaRest respuesta;
				
		try {
			parametroValor.crearParametroValor(parametroValorDto);
			respuesta = new RespuestaRest("200","ok",parametroValorDto);
		}catch (Exception e) {
			respuesta = new RespuestaRest("500",e.getMessage(),parametroValorDto);			
		}
			
		return respuesta;
	}
	
}
