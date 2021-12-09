package com.segurosbolivar.avaluos.modelo.jobs.FileNet;

import java.sql.SQLException;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.service.IArchivo;

/**
 * Objeto de Negocio para el envio de imagenes de la base de datos al S.I
 * FileNet
 * 
 * @author arincon
 */
@Stateless
public class EnvioImagenesFileNet implements IEnvioImagenesFileNet {
	private static final long serialVersionUID = -3281685068064194409L;
	@EJB
	private IArchivo ArchivosPendientes;

	/**
	 * {@inheritDoc}
	 * 
	 * @author arincon
	 */
	@Override
	public void ejectuarEnvioArchivosFilenet() throws NumberFormatException, NegocioException, SQLException {
		System.out.println("Ejecuta el proceso a las " + new Date());
//		ArchivosPendientes.consultaArchivosNoEnviados();
	}
}
