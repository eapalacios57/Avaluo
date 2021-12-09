package com.segurosbolivar.avaluos.planificador.modelo.services;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.modelo.dto.UnidadProductivaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.UnidadProductiva;

@Local
public interface IUnidadProductiva extends Serializable {

	public UnidadProductiva getUnidadProductiva(Long idUnidadProductiva, String codigoSolicitud) throws SQLException;
	
	public List<UnidadProductiva> listarUnidadesProductivas(String codigoSolicitud) throws SQLException;
	
	public UnidadProductiva  crearUnidadProductiva (UnidadProductiva unidadProductiva) throws SQLException,javax.ejb.TransactionRolledbackLocalException;
	
	public void  eliminarUnidadProductiva (UnidadProductiva unidadProductiva) throws SQLException,javax.ejb.TransactionRolledbackLocalException,NegocioException;
	
	public void actualizarUnidadProductiva(UnidadProductivaDTO unidadProductivaDTO) throws NegocioException;
}
