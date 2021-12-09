package com.segurosbolivar.avaluos.planificador.servicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.swing.SortOrder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Usuario;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ParametroDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Parametro;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Unidad;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.ParametroFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.ParametroValorFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.services.IParametro;
import com.segurosbolivar.avaluos.planificador.modelo.services.ISincronizar;
import com.segurosbolivar.avaluos.planificador.modelo.services.IUnidad;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.parametros.MunicipioDepartamentoModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.parametros.UnidadModel;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;


@Path("/Parametros")
@Stateless
public class ParametroSvc {

	@EJB
	IParametro parametro;
	
	@EJB
	ISincronizar iSincronizar;
	
	@EJB
	IUnidad iUnidad;
	
	/**
	 * Consulta los datos de la tabla parametro
	 * @param parametro
	 * @return Lista de Parametros
	 * @throws SQLException 
	 */ 

	@Path("/consulta")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaRest consulta() throws SQLException {
		List<Parametro> listParametro = new ArrayList<>();

		SortOrder sortOrder;		
		ParametroFullDTOMapper parametroMapper = Mappers.getMapper(ParametroFullDTOMapper.class);
		
		//parametro.setIdParametro(6L);
		
		listParametro = parametro.listaParametro();
		
		List<ParametroDTO> listaParametrosDto = new ArrayList<ParametroDTO>();
		
		for(Parametro parametro : listParametro  ) {
			
			ParametroDTO dto =  new ParametroDTO();
			
			dto = ParametroFullDTOMapper.MAPPER.entity2dto(parametro);
			
			dto.setParametroValores(ParametroValorFullDTOMapper.MAPPER.entity2dtoList(parametro.getParametroValors()));
			
			listaParametrosDto.add(dto);
		}	
		
		return new RespuestaRest("200", "ok", listaParametrosDto);
	}
	
	/**
	 * Inserta registro con los datos de Parametro
	 * @param parametro
	 * @return 
	 */
	
	@Path("/inserta")	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaRest inserta(ParametroDTO param){
		Usuario usu = new Usuario();
		UsuarioDto usuario = new UsuarioDto(usu);
		RespuestaRest respuesta;
		
		usu.setCodigo("lramirez");
		param.setUsuarioTransaccion("leonardo");
		
		try {
			parametro.crearParametro(param);
			respuesta = new RespuestaRest("200", "ok", param);
		}catch (Exception e) {
			respuesta = new RespuestaRest("500", e.getMessage(), param);
		}
			
		return respuesta;
	}
	
	@Path("/divipola")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaRest getDivipola() {
		
		List<MunicipioDepartamentoModel> list = new ArrayList<>();
		try {
			list = iSincronizar.consultarMunicipioDepartamento();
			return new RespuestaRest("200", "ok", list);
		} catch (SQLException e) {
			
			e.printStackTrace();
			return new RespuestaRest("500", e.getMessage(), null);
		}
		
	}
	
	@Path("/unidad")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaRest getUnidad() {
		
		List<UnidadModel> list = new ArrayList<>();
		try {
			
			for(Unidad unidad :iUnidad.getUnidades()) {
			
				UnidadModel model = new UnidadModel();
				
				model.setId((int)unidad.getIdUnidad());
				model.setNombre(unidad.getNombre());
				model.setFactorHa(unidad.getFactorHa().doubleValue());
				list.add(model);
			}
			
			return new RespuestaRest("200","ok",list);
		} catch (SQLException e) {
			e.printStackTrace();
			return new RespuestaRest("500",e.getMessage(),null);
			
		}
		
	}

}
