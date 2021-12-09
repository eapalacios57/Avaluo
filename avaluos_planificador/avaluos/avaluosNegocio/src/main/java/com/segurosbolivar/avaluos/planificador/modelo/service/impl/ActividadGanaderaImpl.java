package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionRolledbackLocalException;

import org.mapstruct.factory.Mappers;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.data.ActividadGanaderaDao;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadGanaderaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadGanadera;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.ActividadGanaderaFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadGanadera;

@Stateless
@Local(IActividadGanadera.class)
public class ActividadGanaderaImpl implements IActividadGanadera {

	private static final long serialVersionUID = -2088678805488718651L;

	ActividadGanaderaFullDTOMapper mapper = Mappers.getMapper(ActividadGanaderaFullDTOMapper.class);

	@EJB
	private ActividadGanaderaDao actividadGanaderaDao;

	@Override
	public ActividadGanaderaDTO getActividadGanadera(ActividadGanaderaDTO actividadGanaderaDTO) throws SQLException {
		ActividadGanadera ganadera = actividadGanaderaDao.getActividadGanadera(actividadGanaderaDTO);
		return mapper.entity2dto(ganadera);
	}

	@Override
	public ActividadGanadera crearActividadGanadera(ActividadGanadera actividadGanadera)
			throws NegocioException, javax.ejb.TransactionRolledbackLocalException {

		actividadGanaderaDao.crear(actividadGanadera);

		return actividadGanadera;
	}

	@Override
	public void eliminarActividadGanadera(ActividadGanadera actividadGanadera)
			throws NegocioException, TransactionRolledbackLocalException {
		actividadGanaderaDao.eliminar(actividadGanadera);
	}
	
	@Override
	public void actualizarActividadGanadera(ActividadGanaderaDTO ganaderaDTO) throws NegocioException {
		ActividadGanadera ganadera = mapper.dto2entity(ganaderaDTO);
		actividadGanaderaDao.actualizar(ganadera);
	}

}
