package com.segurosbolivar.avaluos.modelo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.dto.IndiceAcumuladoFullDTO;
import com.segurosbolivar.avaluos.modelo.entity.IndiceAcumulado;

@Mapper(uses = { DepartamentoFullDTOMapper.class, CiudadFullDTOMapper.class })
public interface IndiceAcumuladoFullDTOMapper {
	
	IndiceAcumuladoFullDTOMapper MAPPER = Mappers.getMapper(IndiceAcumuladoFullDTOMapper.class);
	
	IndiceAcumuladoFullDTO entity2dto(IndiceAcumulado entity);

}
