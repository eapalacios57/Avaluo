package com.segurosbolivar.avaluos.planificador.data;

import java.util.List;

import javax.ejb.Stateless;

import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ProductoRelacionadoDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ProductoRelacionado;

@Stateless
public class ProductoRelacionadoDao extends ManejadorCrud<ProductoRelacionado, Long> {

	private static final long serialVersionUID = 7747626177696187846L;

	public ProductoRelacionadoDao() {
		super(ProductoRelacionado.class);
	}

	public List<ProductoRelacionado> getProductosRelacionados() {

		List<ProductoRelacionado> consulta = mp.createNamedQuery("ProductoRelacionado.findAll").getResultList();

		return consulta;

	}
	
	public ProductoRelacionado getProductoRelacionado(ProductoRelacionadoDTO dto) {

		
		ProductoRelacionado consulta = 
				(ProductoRelacionado) mp.createQuery("SELECT t from ProductoRelacionado AS t WHERE t.codigo = :codigo")
                .setParameter("codigo", dto.getCodigo())
                .getSingleResult();
		
		return consulta;

	}

}
