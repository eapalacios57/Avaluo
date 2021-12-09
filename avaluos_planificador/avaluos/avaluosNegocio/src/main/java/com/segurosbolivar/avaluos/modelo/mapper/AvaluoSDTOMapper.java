package com.segurosbolivar.avaluos.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.dto.AvaluoSDTO;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;

import org.mapstruct.Mapping;

@Mapper
public interface AvaluoSDTOMapper {
	
	AvaluoSDTOMapper MAPPER = Mappers.getMapper(AvaluoSDTOMapper.class);
	
	@Mapping(target = "tipoAvaluo", source = "codTipoAvaluo")
	AvaluoSDTO entity2dto(Avaluo entity);
	
	List<AvaluoSDTO> entity2dtoList(List<Avaluo> entities);

}
