package com.segurosbolivar.avaluos.planificador.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadPiscicolaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadPiscicola;

@Mapper
public interface ActividadPiscicolaFullDTOMapper {
	
	ActividadPiscicolaFullDTOMapper MAPPER = Mappers.getMapper(ActividadPiscicolaFullDTOMapper.class);
	
	@Mappings({
		@Mapping(source = "unidadProductiva.idUnidadProductiva", target = "idUnidadProductiva")
	})
	ActividadPiscicolaDTO entity2dto(ActividadPiscicola entity);
	
	@Mappings({
		@Mapping(source = "idUnidadProductiva", target = "unidadProductiva.idUnidadProductiva")
	})
	ActividadPiscicola dto2entity(ActividadPiscicolaDTO dto);
		
	List<ActividadPiscicolaDTO> entity2dtoList(List<ActividadPiscicola> entities);
	
	List<ActividadPiscicola> dto2entityList(List<ActividadPiscicolaDTO> dto);
}
