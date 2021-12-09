package com.segurosbolivar.avaluos.modelo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.dto.CondicionesSalubridadFullDTO;
import com.segurosbolivar.avaluos.modelo.entity.CondicionesSalubridad;

@Mapper
public interface CondicionesSalubridadFullDTOMapper {
	
	CondicionesSalubridadFullDTOMapper MAPPER = Mappers.getMapper(CondicionesSalubridadFullDTOMapper.class);
	
	CondicionesSalubridadFullDTO entity2dto(CondicionesSalubridad entity);

}
