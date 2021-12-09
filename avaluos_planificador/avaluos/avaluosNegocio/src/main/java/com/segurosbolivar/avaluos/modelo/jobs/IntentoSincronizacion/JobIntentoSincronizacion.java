package com.segurosbolivar.avaluos.modelo.jobs.IntentoSincronizacion;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.ejb.TransactionRolledbackLocalException;

import org.apache.log4j.Logger;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.jobs.IProgramacionIntentoSincronizacion;
import com.segurosbolivar.avaluos.modelo.jobs.Procedatos.IProgramacionJobProcedatos;
import com.segurosbolivar.avaluos.modelo.jobs.Procedatos.JobProcedatos;
import com.segurosbolivar.avaluos.planificador.modelo.service.impl.IntentoSincronizacion;
import com.segurosbolivar.avaluos.planificador.modelo.services.ISincronizar;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion.SincronizarModel;
import com.segurosbolivar.avaluos.planificador.util.Constantes;
import com.segurosbolivar.avaluos.planificador.util.ProcesosSincronizacion;


@Singleton
@LocalBean
@Startup
public class JobIntentoSincronizacion {
	
	private static Logger log = Logger.getLogger(IntentoSincronizacion.class.getName());
	
	
	
	@EJB
	ISincronizar isincronizar;
	
	@Resource
    TimerService timerService;
	
	
	@EJB
	IProgramacionIntentoSincronizacion programarJob;

	@PostConstruct
	public void init() {
		inicializarJob();
	}

	/**
	 * Realiza la inciacion unica de la busqueda de parametros necesarios para la
	 * cracion del Job
	 * 
	 * @author stilaguy
	 */
	public void inicializarJob() {
		try {
			//programarJob.ProgramarSchedulerJob(JobIntentoSincronizacion.class.getSimpleName());
		} catch (Exception ex) {
			log.error("Error general en " + this.getClass().getSimpleName(), ex);
		}
	}

	
}
