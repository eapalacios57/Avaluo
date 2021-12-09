package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionRolledbackLocalException;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.data.ProductoRelacionadoSolicitudDao;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ProductoRelacionadoSolicitud;
import com.segurosbolivar.avaluos.planificador.modelo.services.IProductoRelacionadoSolicitud;

@Stateless
@Local(IProductoRelacionadoSolicitud.class)
public class ProductoRelacionadoSolicitudImpl implements IProductoRelacionadoSolicitud {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5779206609986086047L;
	@EJB
	ProductoRelacionadoSolicitudDao productoRelacionadoSolicitudDao;

	@Override
	public ProductoRelacionadoSolicitud crearProductoRelacionadoSolicitud(ProductoRelacionadoSolicitud productoRelacionadoSolicitud) throws SQLException,javax.ejb.TransactionRolledbackLocalException {
		
		return productoRelacionadoSolicitudDao.crearProductoRelacionadoSolicitud(productoRelacionadoSolicitud);
	}

	@Override
	public void eliminarProductoRelacionadoSolicitud(ProductoRelacionadoSolicitud productoRelacionadoSolicitud)
			throws SQLException, TransactionRolledbackLocalException, NegocioException {
		productoRelacionadoSolicitudDao.eliminar(productoRelacionadoSolicitud);
	}
	
	
}
