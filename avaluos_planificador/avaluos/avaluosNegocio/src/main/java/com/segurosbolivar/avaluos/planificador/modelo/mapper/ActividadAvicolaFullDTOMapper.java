package com.segurosbolivar.avaluos.planificador.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadAvicolaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadAvicola;

@Mapper
public interface ActividadAvicolaFullDTOMapper {
	
	ActividadAvicolaFullDTOMapper MAPPER = Mappers.getMapper(ActividadAvicolaFullDTOMapper.class);
	
	@Mappings({
		@Mapping(source = "unidadProductiva.idUnidadProductiva", target = "idUnidadProductiva")
	})
	ActividadAvicolaDTO entity2dto(ActividadAvicola entity);
	
	@Mappings({
		@Mapping(source = "idUnidadProductiva", target = "unidadProductiva.idUnidadProductiva")
	})
	ActividadAvicola dto2entity(ActividadAvicolaDTO dto);
	
	List<ActividadAvicolaDTO> entity2dtoList(List<ActividadAvicola> entities);
	
	List<ActividadAvicola> dto2entityList(List<ActividadAvicolaDTO> dto);
	
}
