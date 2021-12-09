package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionRolledbackLocalException;

import org.apache.log4j.Logger;
import org.eclipse.persistence.exceptions.DatabaseException;
import org.mapstruct.factory.Mappers;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.data.SolicitudMovimientoDao;
import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudMovimientoDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.SolicitudMovimiento;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.SolicitudMovimientoFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.services.ISolicitudMovimiento;

/**
 * Implementaci�n del servicio de archivo
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:38 a.m.
 */
@Stateless
@Local(ISolicitudMovimiento.class)
public class SolicitudMovimientoImpl implements ISolicitudMovimiento {

	private static final long serialVersionUID = -2088678805488718651L;

	@EJB
	private SolicitudMovimientoDao solicitudMovimientoDao;

	/**
	 * Permite realizar el manejo de errores y la recuperacion de los mensajes
	 * respectivos segun la excepcion lanzada.
	 */
	private static final Logger log = Logger.getLogger(SolicitudMovimientoImpl.class);

	@Override
	public void crearSolicitudMovimiento(SolicitudMovimientoDTO solicitudMovimientoDto) {
		SimpleDateFormat formatoTexto = new SimpleDateFormat("dd-MM-yyyy");

		try {
			if (solicitudMovimientoDto.getFechaMovimientoStr() != null) {
				solicitudMovimientoDto
						.setFechaMovimiento(formatoTexto.parse(solicitudMovimientoDto.getFechaMovimientoStr()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		SolicitudMovimientoFullDTOMapper solicitudMovimientoFullDTOMapper = Mappers
				.getMapper(SolicitudMovimientoFullDTOMapper.class);

		SolicitudMovimiento solicitudMovimiento = solicitudMovimientoFullDTOMapper.dto2entity(solicitudMovimientoDto);

		solicitudMovimientoDao.crearSolMov(solicitudMovimiento);

	}

	@Override
	public List<SolicitudMovimiento> listaSolicitudMovimiento() throws SQLException {
		List<SolicitudMovimiento> lista = new ArrayList<>();
		lista = solicitudMovimientoDao.listarSolicitudMovimientos();

		return lista;
	}

	@Override
	public void crearSolicitudMovimiento(SolicitudMovimiento solicitudMovimiento)
			throws NegocioException, javax.ejb.TransactionRolledbackLocalException, SQLException {
		// TODO Auto-generated method stub
		solicitudMovimientoDao.crear(solicitudMovimiento);
	}

	@Override
	public void eliminarSolicitudMovimiento(SolicitudMovimiento solicitudMovimiento)
			throws NegocioException, TransactionRolledbackLocalException {
		// TODO Auto-generated method stub
		solicitudMovimientoDao.eliminar(solicitudMovimiento);
	}

	@Override
	public SolicitudMovimiento consultarSolicitudMovimiento(String codSolicitud, String estado) throws SQLException {
		// TODO Auto-generated method stub
		
		return solicitudMovimientoDao.consultarSolicitudMovimiento(codSolicitud, estado);
	}

}