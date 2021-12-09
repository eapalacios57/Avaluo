package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionRolledbackLocalException;

import org.mapstruct.factory.Mappers;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.data.ActividadAvicolaDao;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadAvicolaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadAvicola;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.ActividadAvicolaFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadAvicola;

@Stateless
@Local(IActividadAvicola.class)
public class ActividadAvicolaImpl implements IActividadAvicola {

	private static final long serialVersionUID = -2088678805488718651L;

	
	@EJB
	private ActividadAvicolaDao actividadAvicolaDao;

	@Override
	public ActividadAvicolaDTO getActividadAvicola(ActividadAvicolaDTO actividadAvicolaDTO) throws SQLException {
		ActividadAvicola avicola = actividadAvicolaDao.getActividadAvicola(actividadAvicolaDTO);
		ActividadAvicolaFullDTOMapper mapper = Mappers.getMapper(ActividadAvicolaFullDTOMapper.class);
		return mapper.entity2dto(avicola);
	}

	@Override
	public ActividadAvicola crearActividadAvicola(ActividadAvicola actividadAvicola) throws NegocioException,javax.ejb.TransactionRolledbackLocalException {
		
		actividadAvicolaDao.crear(actividadAvicola);
		return actividadAvicola;
	}

	@Override
	public void EliminarActividadAvicola(ActividadAvicola actividadAvicola)
			throws NegocioException, TransactionRolledbackLocalException {
		actividadAvicolaDao.eliminar(actividadAvicola);
	}
	
	public void actualizarActAvicola(ActividadAvicolaDTO avicolaDTO) throws NegocioException {
		ActividadAvicolaFullDTOMapper mapper = Mappers.getMapper(ActividadAvicolaFullDTOMapper.class);
		ActividadAvicola avicola = mapper.dto2entity(avicolaDTO);
		actividadAvicolaDao.actualizar(avicola);
	}

}
