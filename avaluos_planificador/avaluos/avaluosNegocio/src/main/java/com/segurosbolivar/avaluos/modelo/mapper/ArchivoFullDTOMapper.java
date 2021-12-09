package com.segurosbolivar.avaluos.modelo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.dto.ArchivoFullDTO;
import com.segurosbolivar.avaluos.modelo.entity.Archivo;

@Mapper
public interface ArchivoFullDTOMapper {
	
	ArchivoFullDTOMapper MAPPER = Mappers.getMapper(ArchivoFullDTOMapper.class);
	
	ArchivoFullDTO entity2dto(Archivo entity);
	
}
