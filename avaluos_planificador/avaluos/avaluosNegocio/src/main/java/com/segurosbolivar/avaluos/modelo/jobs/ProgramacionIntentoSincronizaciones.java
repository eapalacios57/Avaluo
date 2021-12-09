package com.segurosbolivar.avaluos.modelo.jobs;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.ejb.Timer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.ejb.TransactionRolledbackLocalException;

import org.apache.log4j.Logger;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.planificador.modelo.service.impl.IntentoSincronizacion;
import com.segurosbolivar.avaluos.planificador.modelo.services.ISincronizar;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion.SincronizarModel;
import com.segurosbolivar.avaluos.planificador.util.Constantes;
import com.segurosbolivar.avaluos.planificador.util.ProcesosSincronizacion;


//@Stateless(name = "IProgramacionIntentoSincronizacion", mappedName = "IProgramacionIntentoSincronizacion")
@TransactionManagement(TransactionManagementType.BEAN)
@Stateless
@LocalBean
public class ProgramacionIntentoSincronizaciones implements IProgramacionIntentoSincronizacion {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(IntentoSincronizacion.class.getName());
	
	@EJB
	ISincronizar isincronizar;
	
	@Resource
    TimerService timerService;
	
	
	
	public ProgramacionIntentoSincronizaciones() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Timeout
    public void doProcess(Timer timer) {
		log.info("JobIntentoSincronizaciones ,  execute , inicio " );
        System.out.println("Timer Service : " + timer.getInfo());
        System.out.println("Execution Time : " + new Date());
        System.out.println("____________________________________________");
        //consultarSincronizarSolicitudes();
        log.info("JobIntentoSincronizaciones ,  execute , fin " );
    }
	
//	@Override
//	public void ProgramarSchedulerJob(String nombreJob) {
////		TimerConfig timerConfig = new TimerConfig();
////		timerConfig.setInfo(ProgramacionIntentoSincronizaciones.class.getSimpleName());
//		ScheduleExpression schedule = new ScheduleExpression();
//        schedule.hour("*").minute("*/60").second("13");
//        timerService.createCalendarTimer(schedule, new TimerConfig(nombreJob, true));
//        
//	}
//	
//	
	
	/**
	 * Metodo que programa los nuevos reintentos consultando 
	 * la lista de los Json que no han sido sincronizados y
	 * guardados en la carpeta del seridor
	 */
	
	@Override
	public void consultarSincronizarSolicitudes() {		
		log.info("IntentoSincronizacion ,  consultarSincronizarSolicitudes , inicio " );
		try {		
		ProcesosSincronizacion procesos = new ProcesosSincronizacion();			
		List<String> listaSolicitudes  = procesos.consultarSolicitudes();
		log.info("Json que trae:" + listaSolicitudes);
		if(listaSolicitudes != null || listaSolicitudes.size()>0) {
			for(String solicitud : listaSolicitudes) {			
					intentoSincronizacion(solicitud);							
							}
		}else {
			log.info("No hay archivos para consultar");
		}
	    log.info("IntentoSincronizacion ,  consultarSincronizarSolicitudes , fin " );
		}catch(Exception e) {	
			log.info("IntentoSincronizacion ,  consultarSincronizarSolicitudes , Error " );
			e.printStackTrace();			
		}
		
	}
	
	@Override
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
	@Override
	public int sincronizarSolicitud( SincronizarModel sincronizarModel ) 
			throws TransactionRolledbackLocalException, SQLException, NegocioException, ParseException, Exception {
		Logger.getLogger(IntentoSincronizacion.class.getName()).info("IntentoSincronizacion ,  sincronizarSolicitud , inicio " + sincronizarModel.getSolicitudModel().getCodSolicitud());
		
		int contador = 1; 
		int estadoSincronizacion = 0;	
		
		while(estadoSincronizacion != Constantes.SINCRONIZAR_RESPUESTA_OK) {
			Logger.getLogger(IntentoSincronizacion.class.getName()).info("Intento de Sincronizacion numero :" + contador);
			try {
				Logger.getLogger(IntentoSincronizacion.class.getName()).info(sincronizarModel.getSolicitudModel().getCodSolicitud());
				 estadoSincronizacion = isincronizar.sincronizarSolicitud(sincronizarModel);		
				}catch(Exception e) {
				Logger.getLogger(IntentoSincronizacion.class.getName()).info("Falló  el intento "+ contador + " de sincronizacion");
					//estadoSincronizacion = 0;
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
