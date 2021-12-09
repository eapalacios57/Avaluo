package com.segurosbolivar.avaluos.modelo.service.impl;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.data.TronadorDao;
import com.segurosbolivar.avaluos.modelo.service.ITronador;

/**
 * Provee los servicios para la gesti�n sobre los peritos.
 * 
 * @author crivera
 * @version 1.0
 * @created 24-oct-2017 10:30:44 a.m.
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TronadorImpl implements ITronador {

	private static final long serialVersionUID = 5896583002924358511L;

	@EJB
	private ManejadorErroresNegocio mgrExc;

	@EJB
	private TronadorDao tronadorDao;

	@Override
	public BigDecimal consultaUvr() throws NegocioException {
		try {
			BigDecimal retorno = new BigDecimal(219.23);//HABILITAR PARA TEST Y LOCAL
//			BigDecimal retorno = tronadorDao.consultaUvr(); //HABILITAR PARA PRODUCCION
//			if (retorno.compareTo(new BigDecimal(-1)) == 0)//HABILITAR PARA PRODUCCION
//				throw mgrExc.lanzarExcepcion(33, TipoErrorNegocio.ALERTA);//HABILITAR PARA PRODUCCION
			return retorno;
		} catch (Exception e) {
			throw mgrExc.lanzarExcepcion(23, TipoErrorNegocio.ERROR, e.getMessage(), null);
		}
	}
	
	
	public String consultaUvrMotor() throws NegocioException {
			return tronadorDao.consultaUvrMotor();
	}

}