package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionRolledbackLocalException;

import org.mapstruct.factory.Mappers;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.data.PredioDao;
import com.segurosbolivar.avaluos.planificador.modelo.dto.PredioDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Predio;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Unidad;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.PredioFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.services.IPredio;

@Stateless
@Local(IPredio.class)
public class PredioImpl implements IPredio {

	private static final long serialVersionUID = -2088678805488718651L;

	
	@EJB
	private PredioDao predioDao;


	@Override
	public Predio crearPredio(Predio predio) throws NegocioException,javax.ejb.TransactionRolledbackLocalException {
		
		///Predio predio=predioFullDTOMapper.dto2entity(predioDTO);
		predioDao.crear(predio);
		
		return predio;
	}

	@Override
	public PredioDTO actualizaPredio(PredioDTO predioDto) throws NegocioException {
		SimpleDateFormat formatoTexto = new SimpleDateFormat("dd-MM-yyyy");
		Predio predio;
		
		
		try {
			if (predioDto.getFechaCreacionStr() != null && !predioDto.getFechaCreacionStr().equals(" ")) {predioDto.setFechaCreacion(formatoTexto.parse(predioDto.getFechaCreacionStr()));}
			if (predioDto.getFechaTransaccionStr() != null && !predioDto.getFechaTransaccionStr().equals(" ")) {predioDto.setFechaTransaccion(formatoTexto.parse(predioDto.getFechaTransaccionStr()));}
   		} catch (ParseException e) {
			e.printStackTrace();			
		}
		predio = predioDao.buscar(predioDto.getIdPredio());
		
		predio.setAreaProdcutiva(predioDto.getAreaProdcutiva());
		predio.setAreaTotal(predioDto.getAreaTotal());
		predio.setFormaLlegar(predioDto.getFormaLlegar());
		predioDto.setFuenteInformacion(predioDto.getFuenteInformacion());
		predio.setIdCiudad(predioDto.getIdCiudad());
		predio.setIdDepartamento(predioDto.getIdDepartamento());
		predio.setIdPredio(predioDto.getIdPredio());
		predio.setLatitud(predioDto.getLatitud());
		predio.setLongitud(predioDto.getLongitud());
		predio.setNombreMatriculaInmobiliaria(predioDto.getNombreMatriculaInmobiliaria());
		predio.setNumeroFolio(predioDto.getNumeroFolio());					
		predio.setTenencia(predioDto.getTenencia());
		predio.setUnidad(predioDto.getUnidad());
		
		Unidad unidadPredio = new Unidad();
		unidadPredio.setIdUnidad(Integer.parseInt(predioDto.getUnidad()));
		predio.setIdUnidadPredio(unidadPredio);
		
		predio.setVereda(predioDto.getVereda());
		predio.setUsuarioTransaccion(predioDto.getUsuarioTransaccion());
		predio.setFechaTransaccion(new Date());
		predio.setFuenteInformacion(predioDto.getFuenteInformacion());
		
		predioDao.actualizar(predio);
		predioDto = PredioFullDTOMapper.MAPPER.entity2dto(predio);
		return predioDto;
	}


	@Override
	public List<PredioDTO> consultaPredio() throws NegocioException {
		
		List<Predio> listaPredio = predioDao.buscarPredios();
		List<PredioDTO> listaPredioDto = PredioFullDTOMapper.MAPPER.entity2dtoList(listaPredio);
		
		return listaPredioDto;
	}

	@Override
	public PredioDTO buscaPredioPorId(BigDecimal codigoPredio) throws NegocioException {
				
		Predio predio = predioDao.buscar(codigoPredio);
		PredioDTO predioDto = PredioFullDTOMapper.MAPPER.entity2dto(predio);
		
		return predioDto;
	}

	public void eliminarPredio(Predio predio) throws NegocioException, TransactionRolledbackLocalException {
        // TODO Auto-generated method stub
        predioDao.eliminar(predio);
  }

	
	
}
