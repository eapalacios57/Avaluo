package com.segurosbolivar.avaluos.planificador.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.planificador.modelo.dto.TecnificacionAgricolaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.TecnificacionAgricola;

@Mapper
public interface TecAgricolaFullDTOMapper {
	
	TecAgricolaFullDTOMapper MAPPER = Mappers.getMapper(TecAgricolaFullDTOMapper.class);
	
	@Mapping(source = "unidadProductiva.idUnidadProductiva", target = "idUnidadProductiva")
	TecnificacionAgricolaDTO entity2dto(TecnificacionAgricola entity);
	
	@Mapping(source = "idUnidadProductiva", target = "unidadProductiva.idUnidadProductiva")
	TecnificacionAgricola dto2entity(TecnificacionAgricolaDTO dto);
		
	List<TecnificacionAgricolaDTO> entity2dtoList(List<TecnificacionAgricola> entities);
	
	List<TecnificacionAgricola> dto2entityList(List<TecnificacionAgricolaDTO> dto);
	
}
