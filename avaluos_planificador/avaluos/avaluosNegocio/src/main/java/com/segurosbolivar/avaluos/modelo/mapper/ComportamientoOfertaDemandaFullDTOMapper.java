package com.segurosbolivar.avaluos.modelo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.dto.ComportamientoOfertaDemandaFullDTO;
import com.segurosbolivar.avaluos.modelo.entity.ComportamientoOfertaDemanda;

@Mapper
public interface ComportamientoOfertaDemandaFullDTOMapper {
	
	ComportamientoOfertaDemandaFullDTOMapper MAPPER = Mappers.getMapper(ComportamientoOfertaDemandaFullDTOMapper.class);
	
	ComportamientoOfertaDemandaFullDTO entity2dto(ComportamientoOfertaDemanda entity);

}
