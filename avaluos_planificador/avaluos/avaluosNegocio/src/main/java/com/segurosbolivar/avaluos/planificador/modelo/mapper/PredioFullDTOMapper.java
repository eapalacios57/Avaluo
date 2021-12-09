package com.segurosbolivar.avaluos.planificador.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.planificador.modelo.dto.PredioDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Predio;

@Mapper
public interface PredioFullDTOMapper {
	
	PredioFullDTOMapper MAPPER = Mappers.getMapper(PredioFullDTOMapper.class);
	
	PredioDTO entity2dto(Predio entity);
	
	Predio dto2entity(PredioDTO dto);
		
	List<PredioDTO> entity2dtoList(List<Predio> entities);
	
	List<Predio> dto2entityList(List<PredioDTO> dto);
	
}
