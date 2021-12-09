package com.segurosbolivar.avaluos.planificador.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadFinancieraSolicitudDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadFinancieraSolicitud;

@Mapper(uses = { HistActFinanSolicitudFullDTOMapper.class, SolicitudFullDTOMapper.class })
public interface ActividadFinancieraSolicitudFullDTOMapper {

	ActividadFinancieraSolicitudFullDTOMapper MAPPER = Mappers
			.getMapper(ActividadFinancieraSolicitudFullDTOMapper.class);

	@Mappings({ @Mapping(source = "id", target = "pk"),
			@Mapping(source = "codigoSolicitud", target = "id.codigoSolicitud"),
			@Mapping(source = "codigoActividad", target = "id.codigoActividad"),
			@Mapping(target = "fechaInicioStr", ignore = true), @Mapping(target = "fechaFinStr", ignore = true),
			@Mapping(target = "fechaCreacionStr", ignore = true),
			@Mapping(target = "fechaTransaccionStr", ignore = true),
			@Mapping(target = "historicos", ignore = true)})
	ActividadFinancieraSolicitudDTO entity2dto(ActividadFinancieraSolicitud entity);

	@Mappings({ @Mapping(source = "pk", target = "id"),
			@Mapping(source = "id.codigoSolicitud", target = "codigoSolicitud"),
			@Mapping(source = "id.codigoActividad", target = "codigoActividad"),
			@Mapping(target = "actividadFinanciera", ignore = true),
			@Mapping(target = "solicitud", ignore = true)})
	ActividadFinancieraSolicitud dto2entity(ActividadFinancieraSolicitudDTO dto);

	List<ActividadFinancieraSolicitudDTO> entity2dtoList(List<ActividadFinancieraSolicitud> entities);

}
