package com.segurosbolivar.avaluos.planificador.modelo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.segurosbolivar.avaluos.planificador.modelo.dto.ProductoRelacionadoDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ProductoRelacionado;

@Mapper
public interface ProductoRelacionadoFullDtoMapper {
	
	ProductoRelacionadoDTO entity2dto(ProductoRelacionado entity);
	
	List<ProductoRelacionadoDTO> entities2dtos(List<ProductoRelacionado> entity);

}
