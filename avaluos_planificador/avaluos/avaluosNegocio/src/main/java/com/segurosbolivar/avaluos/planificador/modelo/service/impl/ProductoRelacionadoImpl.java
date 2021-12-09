package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import com.segurosbolivar.avaluos.planificador.data.ProductoRelacionadoDao;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ProductoRelacionadoDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ProductoRelacionado;
import com.segurosbolivar.avaluos.planificador.modelo.services.IProductoRelacionado;

@Stateless
@Local(IProductoRelacionado.class)
public class ProductoRelacionadoImpl implements IProductoRelacionado {

	private static final long serialVersionUID = -2088678805488718651L;

	@EJB
	private ProductoRelacionadoDao productoRelacionadoDao;

	@Override
	public List<ProductoRelacionado> getProductosRelacionados() throws SQLException {
		return productoRelacionadoDao.getProductosRelacionados();
	}

	@Override
	public ProductoRelacionado getProductosRelacionados(ProductoRelacionadoDTO dto) throws SQLException {
		return productoRelacionadoDao.getProductoRelacionado(dto);
	}

}
