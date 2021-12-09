package com.segurosbolivar.avaluos.planificador.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.planificador.modelo.dto.SoporteDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Soporte;

@Mapper
public interface SoporteFullDTOMapper {
	
	SoporteFullDTOMapper MAPPER = Mappers.getMapper(SoporteFullDTOMapper.class);
	
	SoporteDTO entity2dto(Soporte entity);
	
	Soporte dto2entity(SoporteDTO dto);
		
	List<SoporteDTO> entity2dtoList(List<Soporte> entities);
	
	List<Soporte> dto2entityList(List<SoporteDTO> dto);
	
}
