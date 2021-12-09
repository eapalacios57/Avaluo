package com.segurosbolivar.avaluos.modelo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.dto.LiquidacionAvaluoTotalFullDTO;
import com.segurosbolivar.avaluos.modelo.entity.LiquidacionAvaluoTotal;

@Mapper(uses = {AvaluoFullDTOMapper.class})
public interface LiquidacionAvaluoTotalFullDTOMapper {
	
	LiquidacionAvaluoTotalFullDTOMapper MAPPER = Mappers.getMapper(LiquidacionAvaluoTotalFullDTOMapper.class);
	
	LiquidacionAvaluoTotalFullDTO entity2dto(LiquidacionAvaluoTotal entity);

}
