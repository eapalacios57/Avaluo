package com.segurosbolivar.avaluos.modelo.jobs;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.TransactionRolledbackLocalException;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion.SincronizarModel;

@Local
//@Remote
public interface IProgramacionIntentoSincronizacion extends Serializable {

	/**
	 * Metodo que programa los nuevos reintentos consultando 
	 * la lista de los Json que no han sido sincronizados y
	 * guardados en la carpeta del seridor
	 */

	void consultarSincronizarSolicitudes();

	void intentoSincronizacion(String codigoSolicitud);

	int sincronizarSolicitud(SincronizarModel sincronizarModel)
			throws TransactionRolledbackLocalException, SQLException, NegocioException, ParseException, Exception;

	//void ProgramarSchedulerJob(String nombreJob);

}