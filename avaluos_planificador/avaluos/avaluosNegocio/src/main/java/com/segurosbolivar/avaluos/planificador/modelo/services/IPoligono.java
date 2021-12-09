package com.segurosbolivar.avaluos.planificador.modelo.services;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.modelo.dto.PoligonoDto;

@Local
public interface IPoligono extends Serializable {
	
	public List<PoligonoDto> buscarPoligonosPorCultivo(BigDecimal idCultivo) throws SQLException;
	
	public List<PoligonoDto> buscarPoligonosPorUnidadProd(Long idUnidadProd) throws SQLException;;
			
	public void crearActualizarPoligono(String crearPoligonosJson) throws SQLException, NegocioException;

}
