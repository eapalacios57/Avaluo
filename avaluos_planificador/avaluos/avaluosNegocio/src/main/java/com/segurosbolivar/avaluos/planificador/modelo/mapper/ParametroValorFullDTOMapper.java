package com.segurosbolivar.avaluos.planificador.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.planificador.modelo.dto.ParametroValorDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ParametroValor;

@Mapper
public interface ParametroValorFullDTOMapper {
	
	ParametroValorFullDTOMapper MAPPER = Mappers.getMapper(ParametroValorFullDTOMapper.class);
	
	ParametroValorDTO entity2dto(ParametroValor entity);
	
	ParametroValor dto2entity(ParametroValorDTO dto);
		
	List<ParametroValorDTO> entity2dtoList(List<ParametroValor> entities);
	
}
