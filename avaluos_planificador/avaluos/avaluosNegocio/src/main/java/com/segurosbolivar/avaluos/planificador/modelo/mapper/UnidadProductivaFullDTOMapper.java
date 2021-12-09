package com.segurosbolivar.avaluos.planificador.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.planificador.modelo.dto.UnidadProductivaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.UnidadProductiva;

@Mapper
public interface UnidadProductivaFullDTOMapper {
	
	UnidadProductivaFullDTOMapper MAPPER = Mappers.getMapper(UnidadProductivaFullDTOMapper.class);
	
	UnidadProductivaDTO entity2dto(UnidadProductiva entity);
	
	UnidadProductiva dto2entity(UnidadProductivaDTO dto);
	
	@Mappings({
		@Mapping(target = "actividadAvicolas", ignore = true),
		@Mapping(target = "actividadGanaderas", ignore = true),
		@Mapping(target = "actividadPiscicolas", ignore = true),
		@Mapping(target = "actividadPorcicolas", ignore = true),
		@Mapping(target = "cultivos", ignore = true),
		@Mapping(target = "predios", ignore = true),
		@Mapping(target = "soportes", ignore = true),
		@Mapping(target = "tecnificacionAgricolas", ignore = true),
		@Mapping(target = "solicitud", ignore = true),
		@Mapping(target = "unidad", ignore = true),
		@Mapping(target = "valorPorcentajes", ignore = true),
		@Mapping(source = "infraAlmacenamientoAgua", target = "infraestAlmacenamientoAgua")
	})
	UnidadProductiva dto2entitySinRelaciones(UnidadProductivaDTO dto);
		
	List<UnidadProductivaDTO> entity2dtoList(List<UnidadProductiva> entities);
	
}
