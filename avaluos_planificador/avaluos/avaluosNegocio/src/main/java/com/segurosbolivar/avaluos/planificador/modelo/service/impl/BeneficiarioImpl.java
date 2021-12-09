package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.mapstruct.factory.Mappers;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.data.BeneficiarioDao;
import com.segurosbolivar.avaluos.planificador.modelo.dto.BeneficiarioDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Beneficiario;
import com.segurosbolivar.avaluos.planificador.modelo.entity.BeneficiarioPK;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.BeneficiarioFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.services.IBeneficiario;

/**
 * Implementaci�n del servicio de archivo
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:38 a.m.
 */
@Stateless
@Local(IBeneficiario.class)
public class BeneficiarioImpl implements IBeneficiario {

    private static final long serialVersionUID = -2088678805488718651L;
    
    @EJB
    private BeneficiarioDao beneficiarioDao;


	@Override
	public Beneficiario crearBeneficiario(Beneficiario beneficiario) throws SQLException, NegocioException, Exception {
		
		try {
				
		beneficiarioDao.crear(beneficiario);
		
		return beneficiario;
		
		}catch(Exception e) {
			Logger.getLogger(SincronizarImpl.class.getName()).info("BeneficiarioImpl ,  crearBeneficiario  , Error al crear el Beneficiario  "+ e );
			return null;
			}
		}
	

	@Override
	public List<Beneficiario> listaBeneficiario() throws SQLException {
		List<Beneficiario> lista = new ArrayList<>();
		lista = beneficiarioDao.listarBeneficiarios();
		
		return lista;
	}

	@Override
	public Beneficiario buscaBeneficiarioPorId(BeneficiarioPK beneficiarioId)  throws SQLException, NoResultException {
		
		Beneficiario beneficiario = beneficiarioDao.buscarPorId(beneficiarioId);
		
		return beneficiario;
	}

	@Override
	public  Beneficiario actualizaBeneficiario(Beneficiario beneficiario) throws SQLException  {
		
		beneficiarioDao.actualziar(beneficiario);
		
		return beneficiario;
		
	}

    

  
}