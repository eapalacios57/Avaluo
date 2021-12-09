package com.segurosbolivar.avaluos.modelo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.dto.AnexoFotograficoFullDTO;
import com.segurosbolivar.avaluos.modelo.entity.AnexoFotografico;

@Mapper
public interface AnexoFotograficoFullDTOMapper {
	
	AnexoFotograficoFullDTOMapper MAPPER = Mappers.getMapper(AnexoFotograficoFullDTOMapper.class);
	
	AnexoFotograficoFullDTO entity2dto(AnexoFotografico entity);

}
