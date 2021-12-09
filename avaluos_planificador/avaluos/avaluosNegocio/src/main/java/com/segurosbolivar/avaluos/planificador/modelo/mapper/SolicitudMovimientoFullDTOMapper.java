package com.segurosbolivar.avaluos.planificador.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudMovimientoDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.SolicitudMovimiento;

@Mapper
public interface SolicitudMovimientoFullDTOMapper {
	
	SolicitudMovimientoFullDTOMapper MAPPER = Mappers.getMapper(SolicitudMovimientoFullDTOMapper.class);
	
	SolicitudMovimientoDTO entity2dto(SolicitudMovimiento entity);
	
	SolicitudMovimiento dto2entity(SolicitudMovimientoDTO dto);
		
	List<SolicitudMovimientoDTO> entity2dtoList(List<SolicitudMovimiento> entities);
	
}
