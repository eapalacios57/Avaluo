package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionRolledbackLocalException;

import org.mapstruct.factory.Mappers;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.data.CultivoDao;
import com.segurosbolivar.avaluos.planificador.modelo.dto.CultivoDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Cultivo;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.CultivoFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.services.ICultivo;

@Stateless
@Local(ICultivo.class)
public class CultivoImpl implements ICultivo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1549035962596442478L;
	
	CultivoFullDTOMapper mapper = Mappers.getMapper(CultivoFullDTOMapper.class);

	@EJB
	private CultivoDao cultivoDao;

	@Override
	public List<Cultivo> getCultivos(CultivoDTO cultivoDTO) throws SQLException {
		return cultivoDao.getCultivos(cultivoDTO);
	}

	@Override
	public Cultivo crearCultivo(Cultivo cultivo)
			throws NegocioException, javax.ejb.TransactionRolledbackLocalException {
		cultivoDao.crear(cultivo);

		return cultivo;
	}

	@Override
	public void eliminarCultivo(Cultivo cultivo) throws NegocioException, TransactionRolledbackLocalException {
		cultivoDao.eliminar(cultivo);
	}

	@Override
	public Cultivo getCultivo(BigDecimal idCultivo) {
		return cultivoDao.buscar(idCultivo);
	}
	
	@Override
	public void actualizarCultivo(CultivoDTO cultivoDTO) throws NegocioException {
		CultivoFullDTOMapper mapper = Mappers.getMapper(CultivoFullDTOMapper.class);
		Cultivo cultivo = mapper.dto2entity(cultivoDTO);
		cultivoDao.actualizar(cultivo);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void actualizarListaCultivos(List<CultivoDTO> cultivosDTO) throws NegocioException {
		for (CultivoDTO cultivoDTO : cultivosDTO) {
			Cultivo cultivo = mapper.dto2entity(cultivoDTO);
			cultivoDao.actualizar(cultivo);
		}
	}

}
