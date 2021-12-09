package com.segurosbolivar.avaluos.modelo.jobs.Procedatos;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.log4j.Logger;



@Singleton
@LocalBean
@Startup
public class JobProcedatos {

	private static final Logger log = Logger.getLogger(JobProcedatos.class);

	@EJB
	IProgramacionJobProcedatos programarJob;

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
			programarJob.programarJob(JobProcedatos.class.getSimpleName());
		} catch (Exception ex) {
			log.error("Error general en " + this.getClass().getSimpleName(), ex);
		}
	}

}
