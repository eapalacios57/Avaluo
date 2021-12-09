package com.segurosbolivar.avaluos.modelo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.dto.InformacionConstruccionFullDTO;
import com.segurosbolivar.avaluos.modelo.entity.InformacionConstruccion;

@Mapper(uses = AvaluoFullDTOMapper.class)
public interface InformacionConstruccionFullDTOMapper {

	InformacionConstruccionFullDTOMapper MAPPER = Mappers.getMapper(InformacionConstruccionFullDTOMapper.class);
	
	InformacionConstruccionFullDTO entity2dto(InformacionConstruccion entity);
	
}
