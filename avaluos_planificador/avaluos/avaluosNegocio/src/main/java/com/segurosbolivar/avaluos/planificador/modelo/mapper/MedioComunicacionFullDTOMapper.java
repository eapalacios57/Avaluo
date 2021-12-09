package com.segurosbolivar.avaluos.planificador.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.planificador.modelo.dto.MedioComunicacionDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.MedioComunicacion;

@Mapper
public interface MedioComunicacionFullDTOMapper {
	
	MedioComunicacionFullDTOMapper MAPPER = Mappers.getMapper(MedioComunicacionFullDTOMapper.class);
	
	MedioComunicacionDTO entity2dto(MedioComunicacion entity);
	
	MedioComunicacion dto2entity(MedioComunicacionDTO dto);
	
	List<MedioComunicacionDTO> entity2dtoList(List<MedioComunicacion> entities);
	
}
