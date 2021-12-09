package com.segurosbolivar.avaluos.modelo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.dto.LiquidacionAvaluoFullDTO;
import com.segurosbolivar.avaluos.modelo.entity.LiquidacionAvaluo;

@Mapper(uses = {AvaluoFullDTOMapper.class})
public interface LiquidacionAvaluoFullDTOMapper {
	
	LiquidacionAvaluoFullDTOMapper MAPPER = Mappers.getMapper(LiquidacionAvaluoFullDTOMapper.class);
	
	LiquidacionAvaluoFullDTO entity2dto(LiquidacionAvaluo entity);

}
