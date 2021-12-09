package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.ejb.TransactionRolledbackLocalException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.modelo.services.ISincronizar;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion.SincronizarModel;
import com.segurosbolivar.avaluos.planificador.util.Constantes;
import com.segurosbolivar.avaluos.planificador.util.ProcesosSincronizacion;

@Stateless
public class IntentoSincronizacion {
	
	public IntentoSincronizacion() {		
	}
	
	@EJB
	ISincronizar isincronizar;		
	
	public void intentoSincronizacion(String codigoSolicitud ) {	
		Logger.getLogger(IntentoSincronizacion.class.getName()).info("IntentoSincronizacion ,  intentoSincronizacion , inicio " + codigoSolicitud);
		ProcesosSincronizacion procesos = new ProcesosSincronizacion();			
		try {
		//Llama al metodo de consulta del  objeto con el codigoSolicitud		
		SincronizarModel sincronizarModel = procesos.ConsultarSolicitud(codigoSolicitud);	
		//Llama al metodo que sincroniza la solicitud
		int estadoSincronizacion = sincronizarSolicitud(sincronizarModel);
		//Validacion de la respuesta del intento de sincronizacion
		if(estadoSincronizacion == Constantes.SINCRONIZAR_RESPUESTA_OK) {			
		   procesos.eliminarSolicitud(codigoSolicitud);			
		}
//		else {
//			consultarSincronizarSolicitudes();
//		}		
		Logger.getLogger(IntentoSincronizacion.class.getName()).info("IntentoSincronizacion ,  intentoSincronizacion , fin " + codigoSolicitud);
		}catch(Exception e) {
			Logger.getLogger(IntentoSincronizacion.class.getName()).info("IntentoSincronizacion , intentoSincronizacion , error en el intento de sincronizacion");
			e.printStackTrace();
			
		}
	}
	
		
	@SuppressWarnings("static-access")
	public int sincronizarSolicitud( SincronizarModel sincronizarModel ) 
			throws TransactionRolledbackLocalException, SQLException, NegocioException, ParseException, Exception {
		Logger.getLogger(IntentoSincronizacion.class.getName()).info("IntentoSincronizacion ,  sincronizarSolicitud , inicio " + sincronizarModel.getSolicitudModel().getCodSolicitud());
		int contador = 1; 
		int estadoSincronizacion = 0 ;	
		
		while(estadoSincronizacion != Constantes.SINCRONIZAR_RESPUESTA_OK) {
			Logger.getLogger(IntentoSincronizacion.class.getName()).info("Intento de Sincronizacion numero :" + contador);
			try {
				Logger.getLogger(IntentoSincronizacion.class.getName()).info(sincronizarModel.getSolicitudModel().getCodSolicitud());
				//SincronizarImpl sincronizar = new SincronizarImpl();
				//holaMundo.ejbActivate();
				estadoSincronizacion = isincronizar.sincronizarSolicitud(sincronizarModel);		
				}catch(Exception e) {
				Logger.getLogger(IntentoSincronizacion.class.getName()).info("Falló  el intento "+ contador + " de sincronizacion");
					estadoSincronizacion = 0;
					   e.printStackTrace();
						if(contador == 3) {
								break;
								}
						contador ++;
						Thread.currentThread().sleep(30000);			
			
							}	
			
			}
		Logger.getLogger(IntentoSincronizacion.class.getName()).info("IntentoSincronizacion ,  sincronizarSolicitud , fin " + sincronizarModel.getSolicitudModel().getCodSolicitud());
		return estadoSincronizacion;
		
	}		
	
	
}
