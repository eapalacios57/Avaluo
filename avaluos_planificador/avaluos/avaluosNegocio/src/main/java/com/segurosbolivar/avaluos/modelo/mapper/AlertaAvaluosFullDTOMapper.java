package com.segurosbolivar.avaluos.modelo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.dto.AlertaAvaluosFullDTO;
import com.segurosbolivar.avaluos.modelo.entity.AlertaAvaluos;

@Mapper
public interface AlertaAvaluosFullDTOMapper {
	
	AlertaAvaluosFullDTOMapper MAPPER = Mappers.getMapper(AlertaAvaluosFullDTOMapper.class);
	
	AlertaAvaluosFullDTO entity2dto(AlertaAvaluos entity);

}
