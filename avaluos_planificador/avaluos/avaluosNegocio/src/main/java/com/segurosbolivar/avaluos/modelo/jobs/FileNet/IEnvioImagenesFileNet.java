package com.segurosbolivar.avaluos.modelo.jobs.FileNet;

import java.io.Serializable;
import java.sql.SQLException;

import javax.ejb.Local;

import com.asesoftware.util.exception.NegocioException;

/**
 * Interface de implementacion del proceso para el envio de imagenes de la base
 * de datos al S.I FileNet
 * 
 * @author arincon
 *
 */
@Local
public interface IEnvioImagenesFileNet extends Serializable {
	/**
	 * Metodo para ejecutar el proceso de envio de documentos a FileNet
	 * 
	 * @throws NumberFormatException
	 * @throws NegocioException
	 * @throws SQLException
	 * @author arincon
	 */
	public void ejectuarEnvioArchivosFilenet() throws NumberFormatException, NegocioException, SQLException;
}
