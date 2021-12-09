package com.segurosbolivar.avaluos.modelo.jobs.Procedatos;

import javax.ejb.Local;
import javax.ejb.Remote;
import java.io.Serializable;

@Local
@Remote
public interface IProgramacionJobProcedatos extends Serializable {

	/**
	 * Proceso para inicializar el Job en el sistema
	 * 
	 * @param Nombre
	 *            de la clase del Job
	 * @author stilaguy
	 */
	public void programarJob(String JobName);

	/**
	 * Proceso para parar el Job Programado en el sistema
	 * 
	 * @param schedulerId
	 */
	public void detenerJob(String schedulerId);

}