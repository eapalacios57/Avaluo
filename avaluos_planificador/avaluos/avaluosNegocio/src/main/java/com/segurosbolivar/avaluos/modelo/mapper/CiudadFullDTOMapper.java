package com.segurosbolivar.avaluos.modelo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.dto.CiudadFullDTO;
import com.segurosbolivar.avaluos.modelo.entity.Ciudad;

@Mapper
public interface CiudadFullDTOMapper {
	
	CiudadFullDTOMapper MAPPER = Mappers.getMapper(CiudadFullDTOMapper.class);
	
	CiudadFullDTO entity2dto(Ciudad entity);
	
}
