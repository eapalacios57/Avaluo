package com.segurosbolivar.avaluos.planificador.servicios;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.mapstruct.factory.Mappers;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.entity.Ciudad;
import com.segurosbolivar.avaluos.planificador.modelo.dto.CultivoDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.PoligonoCultivoDto;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ProductoRelacionadoDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Cultivo;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ProductoRelacionado;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Soporte;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.CultivoFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.ProductoRelacionadoFullDtoMapper;
import com.segurosbolivar.avaluos.planificador.modelo.services.ICiudad;
import com.segurosbolivar.avaluos.planificador.modelo.services.ICultivo;
import com.segurosbolivar.avaluos.planificador.modelo.services.IPoligono;
import com.segurosbolivar.avaluos.planificador.modelo.services.IProductoRelacionado;
import com.segurosbolivar.avaluos.planificador.modelo.services.ISoporte;

@Path("/Cultivos")
@Stateless
public class CultivoSvc {

	@EJB
	ICultivo iCultivo;
	
	@EJB
	ICiudad iCiudad;
	
	@EJB
	ISoporte iSoporte;
	
	@EJB
	IPoligono iPoligono;
	
	@EJB
	IProductoRelacionado iProductoRelacionado;

	@Path("/{codSolicitud}/{idUnidadProductiva}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaRest getTecnificacionAgricola(@PathParam("codSolicitud") String codSolicitud,
			@PathParam("idUnidadProductiva") String idUnidadProductiva) {

		RespuestaRest respuesta;

		CultivoDTO cultivoDTO = new CultivoDTO();
		cultivoDTO.setIdUnidadProductiva(new BigDecimal(idUnidadProductiva));

		try {
			List<Cultivo> listCultivo = iCultivo.getCultivos(cultivoDTO);

			respuesta = new RespuestaRest("200", "", listCultivo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			respuesta = new RespuestaRest("500", e.getMessage(), null);
			e.printStackTrace();
		}

		return respuesta;
	}

	@Path("/{idCultivo}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaRest getCultivo(@PathParam("idCultivo") String idCultivo) {
		try {
			Cultivo cultivo = iCultivo.getCultivo(new BigDecimal(idCultivo));
			Ciudad ciudad = iCiudad.getCiudad(cultivo.getUnidadProductiva().getIdCiudad());			
			CultivoFullDTOMapper mapper = Mappers.getMapper(CultivoFullDTOMapper.class);
			CultivoDTO cultivoDTO = mapper.entity2dto(cultivo);
			
			Map<String, Object> dto = new HashMap<String, Object>();
			dto.put("divipola", null);
			if (ciudad != null) {
				dto.put("divipola", ciudad.getCiudad() + " - " + ciudad.getDepartamento().getDepartamento());
			}
			dto.put("cultivo", cultivoDTO);
			
			return new RespuestaRest("200", "ok", dto);
		} catch (Exception ex) {
			return new RespuestaRest("500", ex.getMessage(), null);
		}
	}
	
	@Path("/consultar-poligonos/{idCultivo}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public RespuestaRest consultarPoligonos(@PathParam("idCultivo") BigDecimal idCultivo) {
		try {
			PoligonoCultivoDto dto = new PoligonoCultivoDto();
			dto.setPoligonos(iPoligono.buscarPoligonosPorCultivo(idCultivo));
			
			List<Map<String, BigDecimal>> ubicaciones = new ArrayList<Map<String,BigDecimal>>();
			List<Soporte> soportes = iSoporte.consultaSoportesPorCultivo(idCultivo);
			for (Soporte soporte : soportes) {
				Map<String, BigDecimal> ubicacion = new HashMap<String, BigDecimal>();
				ubicacion.put("longitud", new BigDecimal(soporte.getLongitud()));
				ubicacion.put("latitud", new BigDecimal(soporte.getLatitud()));
				ubicaciones.add(ubicacion);
			}
			
			dto.setUbicacionFotos(ubicaciones);
			
			return new RespuestaRest("200", "ok", dto);
		} catch (NumberFormatException ex) {
			return new RespuestaRest("500", "Latitud y/o longitud del soporte del cultivo es un valor inválido", null);
		} catch (SQLException ex) {
			return new RespuestaRest("500", ex.getMessage(), null);
		} catch (NegocioException ex) {
			return new RespuestaRest("500", ex.getMessage(), null);
		}
	}
	
	@Path("/consultar-poligonos-unidad/{idUnidadProd}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public RespuestaRest consultarPoligonosUnidadProd(@PathParam("idUnidadProd") Long idUnidadProd) {
		try {
			PoligonoCultivoDto dto = new PoligonoCultivoDto();
			dto.setPoligonos(iPoligono.buscarPoligonosPorUnidadProd(idUnidadProd));
			
			return new RespuestaRest("200", "ok", dto);
		} catch (NumberFormatException ex) {
			return new RespuestaRest("500", "Latitud y/o longitud del soporte del cultivo es un valor inválido", null);
		} catch (SQLException ex) {
			return new RespuestaRest("500", ex.getMessage(), null);
		}
	}

	@Path("/guardar-poligonos")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	public RespuestaRest crearActualizarPoligonos(String json) {
		try {
			iPoligono.crearActualizarPoligono(json);
			return new RespuestaRest("200", "ok", "ok");
		} catch (NegocioException ex) {
			return new RespuestaRest("500", ex.getMessage(), null);
		} catch (SQLException ex) {
			return new RespuestaRest("500", ex.getMessage(), null);
		}
	}
	
	@Path("actualizar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaRest actualizarCultivo(CultivoDTO cultivoDTO) {
		try {
			iCultivo.actualizarCultivo(cultivoDTO);
			return new RespuestaRest("200", "Cultivo actualizado exitosamente", "ok");
		} catch (NegocioException e) {
			e.printStackTrace();
			return new RespuestaRest("500", e.getMessage(), null);
		}
	}
	
	@Path("actualizarListaCultivos")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaRest actualizarListaCultivos(List<CultivoDTO> cultivosDTO) {
		try {
			iCultivo.actualizarListaCultivos(cultivosDTO);
			return new RespuestaRest("200", "Cultivos actualizados exitosamente", "ok");
		} catch (NegocioException e) {
			e.printStackTrace();
			return new RespuestaRest("500", e.getMessage(), null);
		}
	}
	
	@Path("productosRelacionados")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaRest consultarProductosRelacionados() {
		ProductoRelacionadoFullDtoMapper mapper = Mappers.getMapper(ProductoRelacionadoFullDtoMapper.class);
		List<ProductoRelacionado> entities;
		try {
			entities = iProductoRelacionado.getProductosRelacionados();
			List<ProductoRelacionadoDTO> dtos = mapper.entities2dtos(entities);
			return new RespuestaRest("200", "Operacion exitosa", dtos);
		} catch (SQLException e) {
			e.printStackTrace();
			return new RespuestaRest("500", e.getMessage(), null);
		}
		
	}

}
