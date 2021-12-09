package com.segurosbolivar.avaluos.modelo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.dto.DepartamentoFullDTO;
import com.segurosbolivar.avaluos.modelo.entity.Departamento;

@Mapper
public interface DepartamentoFullDTOMapper {
	
	DepartamentoFullDTOMapper MAPPER = Mappers.getMapper(DepartamentoFullDTOMapper.class);
	
	DepartamentoFullDTO entity2dto(Departamento entity);

}
