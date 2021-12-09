package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;


import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.data.SolicitudBeneficiarioDao;
import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudBeneficiarioDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.SolicitudBeneficiario;
import com.segurosbolivar.avaluos.planificador.modelo.services.ISolicitudBeneficiario;

/**
 * Implementaci�n del servicio de archivo
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:38 a.m.
 */
@Stateless
@Local(ISolicitudBeneficiario.class)
public class SolicitudBeneficiarioImpl implements ISolicitudBeneficiario {

    private static final long serialVersionUID = -2088678805488718651L;
    
    @EJB
    private SolicitudBeneficiarioDao solicitudBeneficiarioDao;

   
	@Override
	public void crearSolicitudBeneficiario(SolicitudBeneficiario solicitudBeneficiario) throws NegocioException {
		solicitudBeneficiarioDao.crear(solicitudBeneficiario);
		
	}
	
	@Override
	public void eliminarSolicitudBeneficiario(SolicitudBeneficiario solicitudBeneficiario) throws NegocioException {
		solicitudBeneficiarioDao.eliminar(solicitudBeneficiario);
		
	}
	

	
	@Override
	public List<SolicitudBeneficiario> listaSolicitudBeneficiario() throws SQLException {
		List<SolicitudBeneficiario> lista = new ArrayList<>();
		lista = solicitudBeneficiarioDao.listarSolicitudBeneficiarios();
		
		return lista;
	}


	@Override
	public SolicitudBeneficiario getSolicitudBeneficiario(String codSolicitud) {
		return solicitudBeneficiarioDao.getSolicitudBeneficiario(codSolicitud);
	}



    

  
}