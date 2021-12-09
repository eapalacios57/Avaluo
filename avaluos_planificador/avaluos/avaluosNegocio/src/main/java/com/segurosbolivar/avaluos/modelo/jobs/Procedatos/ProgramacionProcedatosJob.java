package com.segurosbolivar.avaluos.modelo.jobs.Procedatos;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.TimedObject;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;

import com.segurosbolivar.avaluos.modelo.cons.EstadoRegistro;
import com.segurosbolivar.avaluos.modelo.data.CalendarioDao;
import com.segurosbolivar.avaluos.modelo.data.HorasCorteDao;
import com.segurosbolivar.avaluos.modelo.dto.AvaluoFechaEstadoDTO;
import com.segurosbolivar.avaluos.modelo.entity.Calendario;
import com.segurosbolivar.avaluos.modelo.entity.HorasCorteArchivo;
import com.segurosbolivar.avaluos.modelo.service.IAvaluo;

@Stateless(name = "IProgramacionJobProcedatos", mappedName = "IProgramacionJobProcedatos")
@TransactionManagement(TransactionManagementType.BEAN)

public class ProgramacionProcedatosJob implements IProgramacionJobProcedatos, TimedObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(ProgramacionProcedatosJob.class);

	@Resource
	TimerService timerService;

	@EJB
	private HorasCorteDao horaDao;
	@EJB
	private CalendarioDao calendarioDao;

	@EJB
	private IAvaluo avaluoSvc;

	/**
	 * Proceso de ejecucion en el sistema
	 * 
	 * @param timer
	 * @author arincon
	 */
//	@Timeout
//	public void doProcess(Timer timer) {
//		log.info("---------------------INICIA TAREA PROGRAMADA " + timer.getInfo() + "--------------------");
//		try {
//			Calendario consultarFecha = new Calendario();
//			consultarFecha.setFechaNoHabil(new Date());
//			List<Calendario> fechas = calendarioDao.consultar(consultarFecha, 0, 2, null, null);
//			if (fechas != null && !fechas.isEmpty())
//				return;
//			log.info(timer.getInfo() + ": La fecha es habil");
//			AvaluoFechaEstadoDTO consulta = new AvaluoFechaEstadoDTO();
//			consulta.setEstadoAvaluo(2L);
//			consulta.setTipoAvaluo(3L);
//			consulta.setTransmitido(0L);
//			consulta.setFechaActual(new Date());
//			avaluoSvc.ejecutarJobAvaluo(consulta);
//		} catch (Exception e) {
//			log.error(JobEnvioImagenesFileNet.class.getSimpleName(), e);
//		}
//	}

	/**
	 * {@inheritDoc}
	 * 
	 * @author arincon
	 */
	@Override
	public void programarJob(String nombreTareaProgramada) {
		detenerJob(nombreTareaProgramada);
		List<HorasCorteArchivo> horas = horaDao.consultar(new HorasCorteArchivo(), 0, Integer.MAX_VALUE, null, null);
		if (horas == null || horas.isEmpty())
			return;
		for (HorasCorteArchivo hora : horas) {
			if (!EstadoRegistro.ACTIVO.getValor().equals(hora.getEstado()))
				continue;
			ScheduleExpression jobSchedule = new ScheduleExpression();
			jobSchedule.hour(hora.getHoraCorte().intValue());
			jobSchedule.minute(hora.getMinutosCorte().intValue());
			timerService.createCalendarTimer(jobSchedule, new TimerConfig(nombreTareaProgramada, true));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @author arincon
	 */
	@Override
	public void detenerJob(String schedulerId) {
		for (Object obj : timerService.getTimers()) {
			Timer t = (Timer) obj;
			if (t.getInfo().equals(schedulerId) || String.valueOf(t.getInfo()).contains(schedulerId)) {
				try {
					log.info("Programacion del Job " + t.getInfo() + " Suspendida");
					t.cancel();
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		}
	}

	@Override
	public void ejbTimeout(Timer timer) {
		log.info("---------------------INICIA TAREA PROGRAMADA " + timer.getInfo() + "--------------------");
		try {
			Calendario consultarFecha = new Calendario();
			consultarFecha.setFechaNoHabil(new Date());
			List<Calendario> fechas = calendarioDao.consultar(consultarFecha, 0, 2, null, null);
			if (fechas != null && !fechas.isEmpty())
				return;
			log.info(timer.getInfo() + ": La fecha es habil");
			AvaluoFechaEstadoDTO consulta = new AvaluoFechaEstadoDTO();
			consulta.setEstadoAvaluo(2L);
			consulta.setTipoAvaluo(3L);
			consulta.setTransmitido(0L);
			consulta.setFechaActual(new Date());
			avaluoSvc.ejecutarJobAvaluo(consulta);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}


}