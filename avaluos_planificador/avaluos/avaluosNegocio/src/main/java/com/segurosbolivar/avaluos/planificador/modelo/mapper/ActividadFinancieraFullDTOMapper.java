package com.segurosbolivar.avaluos.planificador.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadFinancieraDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadFinanciera;

@Mapper
public interface ActividadFinancieraFullDTOMapper {
	
	ActividadFinancieraFullDTOMapper MAPPER = Mappers.getMapper(ActividadFinancieraFullDTOMapper.class);
	
	ActividadFinancieraDTO entity2dto(ActividadFinanciera entity);
	
	@Mapping(target = "actividadFinancieraSolicituds", ignore = true)
	ActividadFinanciera dto2entity(ActividadFinancieraDTO dto);
		
	List<ActividadFinancieraDTO> entity2dtoList(List<ActividadFinanciera> entities);
	
}
