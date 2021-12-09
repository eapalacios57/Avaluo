package com.segurosbolivar.avaluos.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.dto.HistoricoAvaluoFullDTO;
import com.segurosbolivar.avaluos.modelo.entity.HistoricoAvaluo;

@Mapper( uses = CiudadFullDTOMapper.class )
public interface HistoricoAvaluoFullDTOMapper {
	
	HistoricoAvaluoFullDTOMapper MAPPER = Mappers.getMapper(HistoricoAvaluoFullDTOMapper.class);
	
	HistoricoAvaluoFullDTO entity2dto(HistoricoAvaluo entity);
	
	List<HistoricoAvaluoFullDTO> entity2dtoList(List<HistoricoAvaluo> entities);

}
