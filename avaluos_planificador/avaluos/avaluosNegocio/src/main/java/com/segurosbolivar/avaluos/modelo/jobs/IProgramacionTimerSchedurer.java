package com.segurosbolivar.avaluos.modelo.jobs;

import java.io.Serializable;

import javax.ejb.Local;
import javax.ejb.Remote;

@Local
@Remote
public interface IProgramacionTimerSchedurer extends Serializable {

	/**
	 * Proceso para inicializar el Job en el sistema
	 * 
	 * @param Nombre
	 *            de la clase del Job
	 * @author arincon
	 */
	public void ProgramarSchedulerJob(String JobName);

	/**
	 * Proceso para parar el Job Programado en el sistema
	 * 
	 * @param schedulerId
	 */
	public void stopScheduler(String schedulerId);

}