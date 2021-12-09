package com.segurosbolivar.avaluos.planificador.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudPlanificadorDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Solicitud;

@Mapper
public interface SolicitudPlanificadorFullDTOMapper {
	
	SolicitudPlanificadorFullDTOMapper MAPPER = Mappers.getMapper(SolicitudPlanificadorFullDTOMapper.class);
	
	SolicitudDTO entity2dto(Solicitud entity);
	
	Solicitud dto2entity(SolicitudDTO dto);
	
	List<SolicitudPlanificadorDTO> entity2dtoList(List<Solicitud> entities);
	
}
