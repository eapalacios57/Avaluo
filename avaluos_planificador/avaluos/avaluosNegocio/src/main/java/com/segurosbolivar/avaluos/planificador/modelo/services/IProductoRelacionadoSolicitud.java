package com.segurosbolivar.avaluos.planificador.modelo.services;

import java.sql.SQLException;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ProductoRelacionadoSolicitud;
import java.io.Serializable;

@Local
public interface IProductoRelacionadoSolicitud extends Serializable{

	public ProductoRelacionadoSolicitud crearProductoRelacionadoSolicitud (ProductoRelacionadoSolicitud productoRelacionadoSolicitud) throws SQLException,javax.ejb.TransactionRolledbackLocalException;
	
	public void eliminarProductoRelacionadoSolicitud (ProductoRelacionadoSolicitud productoRelacionadoSolicitud) throws SQLException,javax.ejb.TransactionRolledbackLocalException,NegocioException;
}
