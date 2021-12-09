package com.segurosbolivar.avaluos.planificador.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.planificador.modelo.dto.CultivoDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Cultivo;

@Mapper
public interface CultivoFullDTOMapper {
	
	CultivoFullDTOMapper MAPPER = Mappers.getMapper(CultivoFullDTOMapper.class);
	
	@Mappings({
		@Mapping(source = "unidadProductiva.idUnidadProductiva", target = "idUnidadProductiva"),
		@Mapping(source = "unidad.idUnidad", target = "idUnidad"),
		@Mapping(source = "areaProductivaUnidad.idUnidad", target = "idAreaProductivaUnidad")
	})
	CultivoDTO entity2dto(Cultivo entity);
	
	@Mappings({
		@Mapping(source = "idUnidadProductiva", target = "unidadProductiva.idUnidadProductiva"),
		@Mapping(source = "idUnidad", target = "unidad.idUnidad"),
		@Mapping(source = "idAreaProductivaUnidad", target = "areaProductivaUnidad.idUnidad"),
		@Mapping(target = "soportes", ignore = true)
	})
	Cultivo dto2entity(CultivoDTO dto);
		
	List<CultivoDTO> entity2dtoList(List<Cultivo> entities);
	
	List<Cultivo> dto2entityList(List<CultivoDTO> dto);
	
}
