package com.segurosbolivar.avaluos.planificador.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.planificador.modelo.dto.ParametroDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Parametro;

@Mapper
public interface ParametroFullDTOMapper {
	
	ParametroFullDTOMapper MAPPER = Mappers.getMapper(ParametroFullDTOMapper.class);
	
	ParametroDTO entity2dto(Parametro entity);
	
	Parametro dto2entity(ParametroDTO dto);
		
	List<ParametroDTO> entity2dtoList(List<Parametro> entities);
	
	List<Parametro> dto2entityList(List<ParametroDTO> dto);
	
}
