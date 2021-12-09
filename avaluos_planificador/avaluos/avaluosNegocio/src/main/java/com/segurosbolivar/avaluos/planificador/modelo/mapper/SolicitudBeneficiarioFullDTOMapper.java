package com.segurosbolivar.avaluos.planificador.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudBeneficiarioDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.SolicitudBeneficiario;

@Mapper
public interface SolicitudBeneficiarioFullDTOMapper {
	
	SolicitudBeneficiarioFullDTOMapper MAPPER = Mappers.getMapper(SolicitudBeneficiarioFullDTOMapper.class);
	
	SolicitudBeneficiarioDTO entity2dto(SolicitudBeneficiario entity);
	
	SolicitudBeneficiario dto2entity(SolicitudBeneficiarioDTO dto);
		
	List<SolicitudBeneficiarioDTO> entity2dtoList(List<SolicitudBeneficiario> entities);
	
}
