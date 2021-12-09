package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import org.apache.log4j.Logger;
import org.mapstruct.factory.Mappers;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.data.MedioComunicacionDao;
import com.segurosbolivar.avaluos.planificador.modelo.dto.MedioComunicacionDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.BeneficiarioPK;
import com.segurosbolivar.avaluos.planificador.modelo.entity.MedioComunicacion;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.MedioComunicacionFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.services.IMedioComunicacion;

/**
 * Implementaci�n del servicio de archivo
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:38 a.m.
 */
@Stateless
@Local(IMedioComunicacion.class)
public class MedioComunicacionImpl implements IMedioComunicacion {

    private static final long serialVersionUID = -2088678805488718651L;
    
    @EJB
    private MedioComunicacionDao medioComunicacionDao;

    /**
     * Permite realizar el manejo de errores y la recuperacion de los mensajes
     * respectivos segun la excepcion lanzada.
     */
    private static final Logger log = Logger.getLogger(MedioComunicacionImpl.class);

	@Override
	public List<MedioComunicacion> listaMedioComunicacion() throws SQLException {
		List<MedioComunicacion> lista = new ArrayList<>();
		lista = medioComunicacionDao.listarMedioComunicacion();
		
		return lista;
	}

	@Override
	public void crearMedioComunicacion(MedioComunicacion medioComunicacion) throws NegocioException, ParseException {	
		
		medioComunicacionDao.crear(medioComunicacion);
		
	}
	
	@Override
	public void eliminarMedioComunicacion(MedioComunicacion medioComunicacion) throws NegocioException, ParseException {	
		
		medioComunicacionDao.eliminar(medioComunicacion);
		
	}

	@Override
	public void actualzarMedioComunicacionBeneficiario(MedioComunicacionDTO medioComunicacionDto) throws SQLException  {
		medioComunicacionDao.actualzarMedioComunicacionBeneficiario(medioComunicacionDto);
	}

	@Override
	public MedioComunicacionDTO consultaPorBeneficiario(BeneficiarioPK beneficiarioPk) throws SQLException {
		
		MedioComunicacionDTO medioComunicacionDto = medioComunicacionDao.consultaPorBeneficiario(beneficiarioPk);
		return medioComunicacionDto;
	}

	
	

}