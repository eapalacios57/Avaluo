package com.segurosbolivar.avaluos.planificador.data;


import javax.ejb.Stateless;

import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ProductoRelacionadoSolicitud;

@Stateless
public class ProductoRelacionadoSolicitudDao extends ManejadorCrud<ProductoRelacionadoSolicitud, Long> {

	private static final long serialVersionUID = 7747626177696187846L;

	public ProductoRelacionadoSolicitudDao() {
		super(ProductoRelacionadoSolicitud.class);
	}

	public ProductoRelacionadoSolicitud crearProductoRelacionadoSolicitud(
			ProductoRelacionadoSolicitud productoRelacionadoSolicitud) {

			mp.create(productoRelacionadoSolicitud);
			
			System.out.println("666666666"+productoRelacionadoSolicitud.getSolicitud().getCodigoSolicitud());
			System.out.println("7777777777"+productoRelacionadoSolicitud.getPrincipal());
			System.out.println("888888888"+productoRelacionadoSolicitud.getProductoRelacionado().getCodigo());
			System.out.println("9999999999"+productoRelacionadoSolicitud.getUsuarioCreacion());
			System.out.println("10100101010110"+productoRelacionadoSolicitud.getFechaCreacion());
			
			
			return productoRelacionadoSolicitud;
		

	}

	public ProductoRelacionadoSolicitud consultarProductoRelacionado(
			ProductoRelacionadoSolicitud productoRelacionadoSolicitud) {

		ProductoRelacionadoSolicitud productoRelacionadoSolicitudResult = null;

		try {
			productoRelacionadoSolicitudResult = (ProductoRelacionadoSolicitud) mp.createQuery("SELECT t from ProductoRelacionadoSolicitud AS t WHERE t.id.codigoSolicitud = :codigoSolicitud AND t.id.productoRelacionadoCodigo = :codigo")
					.setParameter("codigoSolicitud", productoRelacionadoSolicitud.getSolicitud().getCodigoSolicitud())
					.setParameter("codigo", productoRelacionadoSolicitud.getProductoRelacionado().getCodigo())
					.getSingleResult();
		} catch (Exception e) {
			productoRelacionadoSolicitudResult = null;

		}
		return productoRelacionadoSolicitudResult;
	}

}
