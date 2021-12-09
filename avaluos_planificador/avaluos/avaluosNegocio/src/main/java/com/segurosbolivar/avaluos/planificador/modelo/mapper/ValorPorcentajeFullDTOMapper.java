package com.segurosbolivar.avaluos.planificador.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.planificador.modelo.dto.ValorPorcentajeDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ValorPorcentaje;

@Mapper
public interface ValorPorcentajeFullDTOMapper {
	
	ValorPorcentajeFullDTOMapper MAPPER = Mappers.getMapper(ValorPorcentajeFullDTOMapper.class);
	
	@Mappings({
		@Mapping(source = "id", target = "valorPorcentajePK")
	})
	ValorPorcentajeDTO entity2dto(ValorPorcentaje entity);
	
	@Mappings({
		@Mapping(source = "valorPorcentajePK", target = "id")
	})
	ValorPorcentaje dto2entity(ValorPorcentajeDTO dto);
		
	List<ValorPorcentajeDTO> entity2dtoList(List<ValorPorcentaje> entities);
	
	List<ValorPorcentaje> dto2entityList(List<ValorPorcentajeDTO> dto);
	
}
