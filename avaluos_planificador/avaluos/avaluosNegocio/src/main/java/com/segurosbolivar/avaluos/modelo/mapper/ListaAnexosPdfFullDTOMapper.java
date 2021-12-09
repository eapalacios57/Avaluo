package com.segurosbolivar.avaluos.modelo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.dto.ListaAnexosPdfFullDTO;
import com.segurosbolivar.avaluos.modelo.entity.ListaAnexosPdf;

@Mapper(uses = {ArchivoFullDTOMapper.class, AvaluoFullDTOMapper.class})
public interface ListaAnexosPdfFullDTOMapper {
	
	ListaAnexosPdfFullDTOMapper MAPPER = Mappers.getMapper(ListaAnexosPdfFullDTOMapper.class);
	
	ListaAnexosPdfFullDTO entity2dto(ListaAnexosPdf entity);

}
