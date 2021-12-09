package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionRolledbackLocalException;

import org.mapstruct.factory.Mappers;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.data.ActividadPiscicolaDao;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadPiscicolaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadPiscicola;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.ActividadPiscicolaFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadPiscicola;

@Stateless
@Local(IActividadPiscicola.class)
public class ActividadPiscicolaImpl implements IActividadPiscicola {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2465968806846891499L;

	ActividadPiscicolaFullDTOMapper mapperPiscicola = Mappers.getMapper(ActividadPiscicolaFullDTOMapper.class);

	@EJB
	private ActividadPiscicolaDao actividadPiscicolaDao;

	@Override
	public ActividadPiscicola crearActividadPiscicola(ActividadPiscicola actividadPiscicola)
			throws NegocioException, javax.ejb.TransactionRolledbackLocalException {

		actividadPiscicolaDao.crear(actividadPiscicola);

		return actividadPiscicola;
	}

	@Override
	public void eliminarActividadPiscicola(ActividadPiscicola actividadPiscicola)
			throws NegocioException, TransactionRolledbackLocalException {
		actividadPiscicolaDao.eliminar(actividadPiscicola);
	}

	@Override
	public ActividadPiscicolaDTO buscarActividadPiscicola(BigDecimal idActPiscicola) throws SQLException {
		ActividadPiscicola piscicola = actividadPiscicolaDao.buscar(idActPiscicola);
		return mapperPiscicola.entity2dto(piscicola);
	}

	@Override
	public void actualizarActividadPiscicola(ActividadPiscicolaDTO piscicolaDTO) throws NegocioException {
		ActividadPiscicola piscicola = mapperPiscicola.dto2entity(piscicolaDTO);
		actividadPiscicolaDao.actualizar(piscicola);
	}

}
