package com.segurosbolivar.avaluos.planificador.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.planificador.modelo.dto.PlanificadorDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Planificador;

@Mapper
public interface PlanificadorFullDTOMapper {
	
	PlanificadorFullDTOMapper MAPPER = Mappers.getMapper(PlanificadorFullDTOMapper.class);
	
	PlanificadorDTO entity2dto(Planificador entity);
	
	Planificador dto2entity(PlanificadorDTO dto);
		
	List<PlanificadorDTO> entity2dtoList(List<Planificador> entities);
	
}
