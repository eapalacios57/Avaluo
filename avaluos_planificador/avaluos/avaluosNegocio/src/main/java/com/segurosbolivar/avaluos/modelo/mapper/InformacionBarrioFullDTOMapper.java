package com.segurosbolivar.avaluos.modelo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.dto.InformacionBarrioFullDTO;
import com.segurosbolivar.avaluos.modelo.entity.InformacionBarrio;

@Mapper(uses = {AvaluoFullDTOMapper.class} )
public interface InformacionBarrioFullDTOMapper {
	
	InformacionBarrioFullDTOMapper MAPPER = Mappers.getMapper(InformacionBarrioFullDTOMapper.class);
	
	InformacionBarrioFullDTO entity2dto(InformacionBarrio entity);

}
