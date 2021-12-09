package com.segurosbolivar.avaluos.modelo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.dto.InformacionInmuebleFullDTO;
import com.segurosbolivar.avaluos.modelo.entity.InformacionInmueble;

@Mapper( uses = AvaluoFullDTOMapper.class )
public interface InformacionInmuebleFullDTOMapper {
	
	InformacionInmuebleFullDTOMapper MAPPER = Mappers.getMapper(InformacionInmuebleFullDTOMapper.class);
	
	InformacionInmuebleFullDTO entity2dto(InformacionInmueble entity);

}
