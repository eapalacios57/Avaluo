package com.segurosbolivar.avaluos.planificador.servicios;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.mapstruct.factory.Mappers;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadFinancieraConsDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadFinancieraSolicitudDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadFinanciera;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadFinancieraSolicitud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadFinancieraSolicitudPK;
import com.segurosbolivar.avaluos.planificador.modelo.entity.HistActFinancSolicitud;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.ActividadFinancieraSolicitudFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.HistActFinanSolicitudFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadFinanciera;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadFinancieraSolicitud;
import com.segurosbolivar.avaluos.planificador.modelo.services.IHistoricoActFinSolicitud;

@Path("/ActividadesFinancieras")
@Stateless
public class ActividadesFinancierasRestSvc {

	@EJB
	IActividadFinanciera actividadFinanciera;

	@EJB
	IActividadFinancieraSolicitud actFinancieraSol;
	
	@EJB
	IHistoricoActFinSolicitud histActFinancieraSol;

	@GET
	@Path("consultarTodas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaRest consultarTodas() {
		RespuestaRest respuesta;

		ActividadFinancieraConsDTO actividadFinancieraConDto;
		List<ActividadFinancieraConsDTO> listado = new ArrayList<>();

		try {
			for (ActividadFinanciera actFinan : actividadFinanciera.listaActividadFinancieras()) {
				actividadFinancieraConDto = new ActividadFinancieraConsDTO();
				actividadFinancieraConDto.setCodigoActividad(actFinan.getCodigoActividad());
				actividadFinancieraConDto.setNombre(actFinan.getNombre());
				listado.add(actividadFinancieraConDto);
			}
			respuesta = new RespuestaRest("200", "ok", listado);
		} catch (SQLException e) {
			e.printStackTrace();
			respuesta = new RespuestaRest("500", e.getMessage(), null);
		}

		return respuesta;
	}

	@GET
	@Path("consultarPorSolicitud/{codigoSolicitud}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaRest consultarPorSolicitud(@PathParam("codigoSolicitud") String codigoSolicitud) {
		RespuestaRest respuesta;

		ActividadFinancieraSolicitudDTO actividadFinancieraSolDto;
		List<ActividadFinancieraSolicitudDTO> listado = new ArrayList<>();
		ActividadFinancieraSolicitudPK actFinSolPk;
		ActividadFinanciera actFinanciera;
		
		HistActFinanSolicitudFullDTOMapper histActFinanSolMapper = Mappers.getMapper(HistActFinanSolicitudFullDTOMapper.class);

		try {
			for (ActividadFinancieraSolicitud actFinanSol : actFinancieraSol
					.listaActFinSolicitudPorSol(codigoSolicitud)) {
				actividadFinancieraSolDto = new ActividadFinancieraSolicitudDTO();
				actFinSolPk = new ActividadFinancieraSolicitudPK();
				actFinSolPk.setCodigoActividad(actFinanSol.getCodigoActividad());
				actFinSolPk.setCodigoSolicitud(actFinanSol.getCodigoSolicitud());

				actividadFinancieraSolDto.setId(actFinSolPk);
				actividadFinancieraSolDto.setPk(actFinanSol.getId());
				actFinanciera = new ActividadFinanciera();
				actFinanciera.setCodigoActividad(actFinanSol.getCodigoActividad());
				actFinanciera.setNombre(actFinanSol.getActividadFinanciera().getNombre());
				actFinanciera.setVigencia(actFinanSol.getActividadFinanciera().getVigencia());

				actividadFinancieraSolDto.setActividadFinanciera(actFinanciera);
				actividadFinancieraSolDto.setCantidad(actFinanSol.getCantidad());
				actividadFinancieraSolDto.setFechaFin(actFinanSol.getFechaFin());
				actividadFinancieraSolDto.setFechaInicio(actFinanSol.getFechaInicio());
				actividadFinancieraSolDto.setPeriodoGracia(actFinanSol.getPeriodoGracia());
				actividadFinancieraSolDto.setPlazo(actFinanSol.getPlazo());
				actividadFinancieraSolDto.setPrincipal(actFinanSol.getPrincipal());
				actividadFinancieraSolDto.setRazonInversion(actFinanSol.getRazonInversion());
				actividadFinancieraSolDto.setUnidad(actFinanSol.getUnidad());
				actividadFinancieraSolDto.setValorCredito(actFinanSol.getValorCredito());
				actividadFinancieraSolDto.setValorProyecto(actFinanSol.getValorProyecto());
				actividadFinancieraSolDto.setFechaCreacion(actFinanSol.getFechaCreacion());
				actividadFinancieraSolDto.setUsuarioCreacion(actFinanSol.getUsuarioCreacion());
				actividadFinancieraSolDto.setFechaTransaccion(actFinanSol.getFechaTransaccion());
				actividadFinancieraSolDto.setUsuarioTransaccion(actFinanSol.getUsuarioTransaccion());
				
				
				List<HistActFinancSolicitud> historicos = histActFinancieraSol.buscarPorIdActFinSolicitud(actFinanSol.getId());
				List<ActividadFinancieraSolicitudDTO> historicoDtos = histActFinanSolMapper.entites2dtos(historicos); 
				for (ActividadFinancieraSolicitudDTO dto : historicoDtos) {
					ActividadFinanciera actFinDto = new ActividadFinanciera();
					actFinDto.setCodigoActividad(dto.getActividadFinanciera().getCodigoActividad());
					actFinDto.setNombre(dto.getActividadFinanciera().getNombre());
					dto.setActividadFinanciera(actFinDto);
				}
				actividadFinancieraSolDto.setHistoricos(historicoDtos);

				listado.add(actividadFinancieraSolDto);
			}
			respuesta = new RespuestaRest("200", "ok", listado);
		} catch (SQLException e) {
			e.printStackTrace();
			respuesta = new RespuestaRest("500", e.getMessage(), null);
		}

		return respuesta;
	}
	
	@Path("crearActFinanSolicitud")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaRest crearActFinanSolicitud(ActividadFinancieraSolicitudDTO dto) {
		ActividadFinancieraSolicitudFullDTOMapper mapper = Mappers.getMapper(ActividadFinancieraSolicitudFullDTOMapper.class);
		try {
			actFinancieraSol.crearActividadFinancieraSolicitud(mapper.dto2entity(dto));
		} catch (NegocioException e) {
			if (TipoErrorNegocio.INFO.equals(e.getTipo())) {
				return new RespuestaRest("409", e.getMessage(), null);
			}
			return new RespuestaRest("500", e.getMessage(), null);
		} catch (ParseException e) {
			return new RespuestaRest("500", e.getMessage(), null);
		}
		return new RespuestaRest("200", "ok", "ok");
	}
	
	@Path("actualizarActFinanSolicitud")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaRest actualizarActFinanSolicitud(ActividadFinancieraSolicitudDTO dto) {
		ActividadFinancieraSolicitudFullDTOMapper mapper = Mappers.getMapper(ActividadFinancieraSolicitudFullDTOMapper.class);
		try {
			actFinancieraSol.actualizarActividadFinancieraSolicitud(mapper.dto2entity(dto));
		} catch (NegocioException e) {
			if (TipoErrorNegocio.INFO.equals(e.getTipo())) {
				return new RespuestaRest("409", e.getMessage(), null);
			}
			return new RespuestaRest("500", e.getMessage(), null);
		}		
		return new RespuestaRest("200", "ok", "ok");
	}
	
	@Path("eliminarActFinanSolicitud/{codigoSolicitud}/{idActFinSol}")
	@DELETE
	public RespuestaRest eliminarActFinanSolicitud(@PathParam("codigoSolicitud") String codigoSolicitud, @PathParam("idActFinSol") BigDecimal idActFinSol) {
		try {
			actFinancieraSol.eliminarActividadFinancieraSolicitud(codigoSolicitud, idActFinSol);
		} catch (NegocioException e) {
			if (TipoErrorNegocio.INFO.equals(e.getTipo())) {
				return new RespuestaRest("409", e.getMessage(), null);
			}
			return new RespuestaRest("500", e.getMessage(), null);
		} catch (SQLException e) {
			return new RespuestaRest("500", e.getMessage(), null);
		}		
		return new RespuestaRest("200", "ok", "ok");
	}
	
	
}