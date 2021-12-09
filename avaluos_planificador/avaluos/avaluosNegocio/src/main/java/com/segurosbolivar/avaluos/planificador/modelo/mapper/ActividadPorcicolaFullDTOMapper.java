package com.segurosbolivar.avaluos.planificador.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadPorcicolaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadPorcicola;

@Mapper
public interface ActividadPorcicolaFullDTOMapper {
	
	ActividadPorcicolaFullDTOMapper MAPPER = Mappers.getMapper(ActividadPorcicolaFullDTOMapper.class);
	
	@Mappings({
		@Mapping(source = "unidadProductiva.idUnidadProductiva", target = "idUnidadProductiva")
	})
	ActividadPorcicolaDTO entity2dto(ActividadPorcicola entity);
	
	@Mappings({
		@Mapping(source = "idUnidadProductiva", target = "unidadProductiva.idUnidadProductiva")
	})
	ActividadPorcicola dto2entity(ActividadPorcicolaDTO dto);
		
	List<ActividadPorcicolaDTO> entity2dtoList(List<ActividadPorcicola> entities);
	
	List<ActividadPorcicola> dto2entityList(List<ActividadPorcicolaDTO> dto);
	
}
