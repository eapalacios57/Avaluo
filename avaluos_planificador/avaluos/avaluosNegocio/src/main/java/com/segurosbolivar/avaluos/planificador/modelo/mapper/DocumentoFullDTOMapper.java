package com.segurosbolivar.avaluos.planificador.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.planificador.modelo.dto.DocumentoDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Documento;

@Mapper
public interface DocumentoFullDTOMapper {
	
	DocumentoFullDTOMapper MAPPER = Mappers.getMapper(DocumentoFullDTOMapper.class);
	
	DocumentoDTO entity2dto(Documento entity);
	
	Documento dto2entity(DocumentoDTO dto);
		
	List<DocumentoDTO> entity2dtoList(List<Documento> entities);
	
}
