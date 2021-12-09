package com.segurosbolivar.avaluos.planificador.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.planificador.modelo.dto.BeneficiarioDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Beneficiario;

@Mapper
public interface BeneficiarioFullDTOMapper {
	
	BeneficiarioFullDTOMapper MAPPER = Mappers.getMapper(BeneficiarioFullDTOMapper.class);
	
	BeneficiarioDTO entity2dto(Beneficiario entity);
	
	Beneficiario dto2entity(BeneficiarioDTO dto);
		
	List<BeneficiarioDTO> entity2dtoList(List<Beneficiario> entities);
	
}
