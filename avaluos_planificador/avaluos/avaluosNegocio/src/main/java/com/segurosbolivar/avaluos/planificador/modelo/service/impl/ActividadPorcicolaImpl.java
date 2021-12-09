package com.segurosbolivar.avaluos.planificador.modelo.service.impl;


import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionRolledbackLocalException;

import org.mapstruct.factory.Mappers;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.data.ActividadPorcicolaDao;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadPorcicolaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadPorcicola;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.ActividadPorcicolaFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadPorcicola;

@Stateless
@Local(IActividadPorcicola.class)
public class ActividadPorcicolaImpl implements IActividadPorcicola {

	private static final long serialVersionUID = -2088678805488718651L;
	
	private ActividadPorcicolaFullDTOMapper mapperPorcicola = Mappers.getMapper(ActividadPorcicolaFullDTOMapper.class);

	
	@EJB
	private ActividadPorcicolaDao actividadPorcicolaDao;


	@Override
	public ActividadPorcicola crearActividadPorcicola(ActividadPorcicola actividadPorcicola)
			throws NegocioException,javax.ejb.TransactionRolledbackLocalException {
		actividadPorcicolaDao.crear(actividadPorcicola);
		
		return actividadPorcicola;
	}


	@Override
	public void eliminarActividadPorcicola(ActividadPorcicola actividadPorcicola)
			throws NegocioException, TransactionRolledbackLocalException {
		actividadPorcicolaDao.eliminar(actividadPorcicola);
	}


	@Override
	public void actualizarActividadPorcicola(ActividadPorcicolaDTO porcicolaDTO) throws NegocioException {
		ActividadPorcicola porcicola = mapperPorcicola.dto2entity(porcicolaDTO);
		actividadPorcicolaDao.actualizar(porcicola);
	}


	@Override
	public ActividadPorcicolaDTO buscarActPiscicolaPorId(BigDecimal idPorcicola) throws SQLException {
		ActividadPorcicola porcicola = actividadPorcicolaDao.buscar(idPorcicola);
		return mapperPorcicola.entity2dto(porcicola);
	}
	
	
	
}
