package com.segurosbolivar.avaluos.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.dto.AvaluoFullDTO;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;

@Mapper (uses = {DepartamentoFullDTOMapper.class, CiudadFullDTOMapper.class,
//		LogsGeneraArchivoFullDTOMapper.class, 
		AnexoFotograficoFullDTOMapper.class, 
		ComportamientoOfertaDemandaFullDTOMapper.class, CondicionesSalubridadFullDTOMapper.class, HistoricoAvaluoFullDTOMapper.class, InformacionBarrioFullDTOMapper.class, 
		InformacionConstruccionFullDTOMapper.class, InformacionInmuebleFullDTOMapper.class, LiquidacionAvaluoFullDTOMapper.class, LiquidacionAvaluoTotalFullDTOMapper.class, 
		ObservacionesFullDTOMapper.class, ListaAnexosPdfFullDTOMapper.class, AlertaAvaluosFullDTOMapper.class} )
public interface AvaluoFullDTOMapper {
	
	AvaluoFullDTOMapper MAPPER = Mappers.getMapper(AvaluoFullDTOMapper.class);
	
	AvaluoFullDTO entity2dto(Avaluo entity);
	
	List<AvaluoFullDTO> entity2dtoList(List<Avaluo> entities);

}
