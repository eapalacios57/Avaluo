package com.segurosbolivar.avaluos.modelo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.dto.ObservacionesFullDTO;
import com.segurosbolivar.avaluos.modelo.entity.Observaciones;

@Mapper(uses = {ArchivoFullDTOMapper.class, AvaluoFullDTOMapper.class} )
public interface ObservacionesFullDTOMapper {
	
	ObservacionesFullDTOMapper MAPPER = Mappers.getMapper(ObservacionesFullDTOMapper.class);
	
	ObservacionesFullDTO entity2dto(Observaciones entity);

}
