package com.segurosbolivar.avaluos.planificador.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadFinancieraSolicitudDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.HistActFinancSolicitud;

@Mapper(uses = ActividadFinancieraFullDTOMapper.class)
public interface HistActFinanSolicitudFullDTOMapper {
	
	HistActFinanSolicitudFullDTOMapper mapper = Mappers.getMapper(HistActFinanSolicitudFullDTOMapper.class);
	
	@Mappings({
		@Mapping(source = "codigoActividad", target = "id.codigoActividad"),
		@Mapping(source = "codigoSolicitud", target = "id.codigoSolicitud"),
		@Mapping(source = "idActFinSol", target = "pk"),
		@Mapping(target = "fechaInicioStr", ignore = true),
		@Mapping(target = "fechaFinStr", ignore = true),
		@Mapping(target = "principal", ignore = true),
		@Mapping(target = "solicitud", ignore = true),
		@Mapping(target = "historicos", ignore = true),
		@Mapping(target = "fechaCreacionStr", ignore = true),
		@Mapping(target = "usuarioTransaccion", ignore = true),
		@Mapping(target = "fechaTransaccionStr", ignore = true),
		@Mapping(target = "fechaTransaccion", ignore = true)
	})
	ActividadFinancieraSolicitudDTO entity2dto(HistActFinancSolicitud entity);
	
	List<ActividadFinancieraSolicitudDTO> entites2dtos(List<HistActFinancSolicitud> entities);

}
