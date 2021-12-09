package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionRolledbackLocalException;

import org.mapstruct.factory.Mappers;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.data.TecnificacionAgricolaDao;
import com.segurosbolivar.avaluos.planificador.modelo.dto.TecnificacionAgricolaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.TecnificacionAgricola;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.TecAgricolaFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.services.ITecnificacionAgricola;

@Stateless
@Local(ITecnificacionAgricola.class)
public class TecnificacionAgricolaImpl  implements ITecnificacionAgricola {

	private static final long serialVersionUID = -2088678805488718651L;
	
	@EJB
    private TecnificacionAgricolaDao tecnificacionAgricolaDao;
	
	
	@Override
	public TecnificacionAgricolaDTO buscarTecnificacionAgricola(TecnificacionAgricolaDTO tecnificacionAgricolaDTO)
			throws SQLException {
		TecnificacionAgricola tecAgricola = tecnificacionAgricolaDao.getTecnificacionAgricola(tecnificacionAgricolaDTO);
		TecAgricolaFullDTOMapper mapper = Mappers.getMapper(TecAgricolaFullDTOMapper.class);
		return mapper.entity2dto(tecAgricola);
	}


	@Override
	public TecnificacionAgricola crearTecnificacionAgricola(TecnificacionAgricola tecnificacionAgricola) throws NegocioException,javax.ejb.TransactionRolledbackLocalException {
		tecnificacionAgricolaDao.crear(tecnificacionAgricola);		
		return tecnificacionAgricola;
	}
	
	@Override
	public void actualizarTecAgricola(TecnificacionAgricolaDTO tecnificacionAgricolaDTO) throws NegocioException {
		TecAgricolaFullDTOMapper mapper = Mappers.getMapper(TecAgricolaFullDTOMapper.class);
		TecnificacionAgricola tecnificacionAgricola = mapper.dto2entity(tecnificacionAgricolaDTO);
		tecnificacionAgricolaDao.actualizar(tecnificacionAgricola);
	}


	@Override
	public void eliminarTecnificacionAgricola(TecnificacionAgricola tecnificacionAgricola)
			throws NegocioException, TransactionRolledbackLocalException {
		tecnificacionAgricolaDao.eliminar(tecnificacionAgricola);
		
	}

	
	
	
	
	

}
