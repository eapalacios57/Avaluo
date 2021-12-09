package com.segurosbolivar.avaluos.planificador.modelo.services;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.segurosbolivar.avaluos.planificador.modelo.dto.ProductoRelacionadoDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ProductoRelacionado;

@Local
public interface IProductoRelacionado  extends Serializable {
	
	
	public List<ProductoRelacionado> getProductosRelacionados() throws SQLException;
	
	public ProductoRelacionado getProductosRelacionados(ProductoRelacionadoDTO dto) throws SQLException;
	
}
