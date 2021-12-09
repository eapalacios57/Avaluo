package com.segurosbolivar.avaluos.planificador.servicios;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.apache.log4j.Logger;

import com.segurosbolivar.avaluos.modelo.jobs.IProgramacionIntentoSincronizacion;
import com.segurosbolivar.avaluos.modelo.jobs.ProgramacionIntentoSincronizaciones;
import com.segurosbolivar.avaluos.planificador.modelo.service.impl.SincronizarImpl;
import com.segurosbolivar.avaluos.planificador.modelo.services.ISincronizar;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion.SincronizarModel;
import com.segurosbolivar.avaluos.planificador.util.Constantes;
import com.segurosbolivar.avaluos.planificador.util.ProcesosSincronizacion;

@Stateless
@LocalBean
public class EjbAsincrono implements IEjbAsincrono {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Logger log = Logger.getLogger(EjbAsincrono.class);
	
	@EJB
	ISincronizar iSincronizar;
	
	@EJB
	IProgramacionIntentoSincronizacion intento;
	
	@Override
	@Asynchronous
	public void RespuestaAsincrona(SincronizarModel modelo) {
		//ProgramacionIntentoSincronizaciones intento = new ProgramacionIntentoSincronizaciones();
		ProcesosSincronizacion procesos = new ProcesosSincronizacion();
		int estadoSincronizacion = 0;
		try {
			estadoSincronizacion = iSincronizar.sincronizarSolicitud(modelo);
			if (estadoSincronizacion == Constantes.SINCRONIZAR_RESPUESTA_OK) {
				procesos.eliminarSolicitud(modelo.getSolicitudModel().getCodSolicitud());
			}else {
				log.info("Solicitud no sincronizada :" + modelo.getSolicitudModel().getCodSolicitud());
			}

		} catch (Exception e) {
			log.info("Sincronizar Solicitud ,  sincronizarSolicitud , error " + e.getMessage());
			//e.printStackTrace();
			intento.intentoSincronizacion(modelo.getSolicitudModel().getCodSolicitud());
			
		}

	}

}
