package com.segurosbolivar.avaluos.planificador.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadGanaderaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadGanadera;

@Mapper
public interface ActividadGanaderaFullDTOMapper {
	
	ActividadGanaderaFullDTOMapper MAPPER = Mappers.getMapper(ActividadGanaderaFullDTOMapper.class);
	
	@Mappings({
		@Mapping(source = "unidadProductiva.idUnidadProductiva", target = "idUnidadProductiva"),
		@Mapping(target = "areaPraderaMejoradaUnidad.cultivos", ignore = true),
		@Mapping(target = "areaPraderaMejoradaUnidad.unidadProductivas", ignore = true),
		@Mapping(target = "areaActividadUnidad.cultivos", ignore = true),
		@Mapping(target = "areaActividadUnidad.unidadProductivas", ignore = true)
	})
	ActividadGanaderaDTO entity2dto(ActividadGanadera entity);
	
	@Mappings({
		@Mapping(source = "idUnidadProductiva", target = "unidadProductiva.idUnidadProductiva")
	})
	ActividadGanadera dto2entity(ActividadGanaderaDTO dto);
		
	List<ActividadGanaderaDTO> entity2dtoList(List<ActividadGanadera> entities);
	
	List<ActividadGanadera> dto2entityList(List<ActividadGanaderaDTO> dto);
	
}
