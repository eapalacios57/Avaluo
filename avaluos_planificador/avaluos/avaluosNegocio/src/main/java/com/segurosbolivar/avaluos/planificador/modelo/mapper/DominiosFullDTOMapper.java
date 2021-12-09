package com.segurosbolivar.avaluos.planificador.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.entity.Dominios;
import com.segurosbolivar.avaluos.planificador.modelo.dto.DominiosPlanificadorDTO;

@Mapper
public interface DominiosFullDTOMapper {
	
	DominiosFullDTOMapper MAPPER = Mappers.getMapper(DominiosFullDTOMapper.class);
	
	DominiosPlanificadorDTO entity2dto(Dominios entity);
	
	Dominios dto2entity(DominiosPlanificadorDTO dto);
		
	List<DominiosPlanificadorDTO> entity2dtoList(List<Dominios> entities);
	
}
