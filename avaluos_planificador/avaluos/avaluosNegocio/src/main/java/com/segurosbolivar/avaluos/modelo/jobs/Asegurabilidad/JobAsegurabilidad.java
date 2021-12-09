package com.segurosbolivar.avaluos.modelo.jobs.Asegurabilidad;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.log4j.Logger;

import com.segurosbolivar.avaluos.modelo.jobs.IProgramacionTimerSchedurer;

@Singleton
@LocalBean
@Startup
public class JobAsegurabilidad {

	private static final Logger log = Logger.getLogger(JobAsegurabilidad.class);

	@EJB
	IProgramacionTimerSchedurer ProgramacionJob;

	@PostConstruct
	public void init() {
		InicializarTarea();
	}

	/**
	 * Realiza la inciacion unica de la busqueda de parametros necesarios para la
	 * cracion del Job
	 * 
	 * @author arincon
	 */
	public void InicializarTarea() {
		try {
			log.info("INICIA TAREA DE ASEGURABILIDAD");
			ProgramacionJob.ProgramarSchedulerJob(JobAsegurabilidad.class.getSimpleName());
		} catch (Exception ex) {
			log.error("Error general en " + this.getClass().getSimpleName(), ex);
		}
	}

}
