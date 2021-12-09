package com.segurosbolivar.avaluos.modelo.jobs;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilNumero;
import com.segurosbolivar.avaluos.modelo.data.ParametrizacionDao;
import com.segurosbolivar.avaluos.modelo.entity.Parametrizacion;
import com.segurosbolivar.avaluos.modelo.jobs.Asegurabilidad.JobAsegurabilidad;
import com.segurosbolivar.avaluos.modelo.service.IAvaluo;

@Stateless(name = "IProgramacionTimerSchedurer", mappedName = "IProgramacionTimerSchedurer")
@TransactionManagement(TransactionManagementType.BEAN)

public class ProgramacionTimerSchedurer implements IProgramacionTimerSchedurer {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(ProgramacionTimerSchedurer.class);

    @Resource
    TimerService timerService;

    @EJB
    private IProcessJobScheduler ProcesosJob;

    @EJB
    private ParametrizacionDao ParametrosJobJpa;

    @EJB
    private IAvaluo avaluoSvc;

    /* Constraint */
    public ProgramacionTimerSchedurer() {
    }

    /**
     * Proceso de ejecucion en el sistema
     * 
     * @param timer
     * @author arincon
     */
    @Timeout
    public void doProcess(Timer timer) {
	System.out.println("---------------------INICIA TAREA PROGRAMADA " + timer.getInfo() + "--------------------");
	try {
	    executeProccessJobScheduler(timer.getInfo().toString());
	} catch (NumberFormatException e) {
	    log.log(Level.ERROR, e.getMessage());
	} catch (NegocioException e) {
	    log.log(Level.ERROR, e.getMessage());
	} catch (SQLException e) {
	    log.log(Level.ERROR, e.getMessage());
	}
    }

    /**
     * {@inheritDoc}
     * 
     * @author arincon
     */
    @Override
    public void ProgramarSchedulerJob(String JobName) {
	ScheduleExpression JobSchedule = new ScheduleExpression();
	// Recorre los parametros de configuracion
	for (Parametrizacion Parametro : getParametrosJob(JobName)) {
	    if (!UtilNumero.esEntero(Parametro.getNombreparametro()))
		continue;
	    switch (Integer.parseInt(Parametro.getNombreparametro())) {
	    case java.util.Calendar.YEAR:
		JobSchedule.year(Parametro.getValorparametro());
		break;
	    case java.util.Calendar.MONTH:
		JobSchedule.month(Parametro.getValorparametro());
		break;
	    case java.util.Calendar.DAY_OF_MONTH:
		JobSchedule.dayOfMonth(Parametro.getValorparametro());
		break;
	    case java.util.Calendar.DAY_OF_WEEK:
		JobSchedule.dayOfWeek(Parametro.getValorparametro());
		break;
	    case java.util.Calendar.HOUR:
		JobSchedule.hour(Parametro.getValorparametro());
		break;
	    case java.util.Calendar.MINUTE:
		JobSchedule.minute(Parametro.getValorparametro());
		break;
	    case java.util.Calendar.SECOND:
		JobSchedule.second(Parametro.getValorparametro());
		break;
	    default:
		JobSchedule.timezone(Parametro.getValorparametro());
		break;
	    }
	}
	stopScheduler(JobName);
	log.info(JobSchedule.toString());
	timerService.createCalendarTimer(JobSchedule, new TimerConfig(JobName, true));
//	log.info("Realiza la programacion de la tarea: " + JobName + " ");
    }

    /**
     * {@inheritDoc}
     * 
     * @author arincon
     */
    @Override
    public void stopScheduler(String schedulerId) {
	for (Object obj : timerService.getTimers()) {
	    Timer t = (Timer) obj;
	    if (t.getInfo().equals(schedulerId) || String.valueOf(t.getInfo()).contains(schedulerId)) {
		try {
		    log.info("Programacion del Job " + t.getInfo() + " Suspendida");
		    t.cancel();
		} catch (Exception e) {
		    log.log(Level.ERROR, e.getMessage());
		}
	    }
	}
    }

    /**
     * Parametros de configuracion en la base de datos de programacion de un Job
     * 
     * @param Nombre
     *            de la clase que contiene el Job
     * @return Parametros de configuracion del la Scheduler
     * @author arincon
     */
    private List<Parametrizacion> getParametrosJob(String JobClassName) {
	// Obtiene los parametros del sistema
	List<Parametrizacion> ParametrosJob = ParametrosJobJpa.getTiposParametro(JobClassName);
	return ParametrosJob;
    }

    /**
     * Realiza la invocacion del proceso Java segun el nombre del Job
     * 
     * @param Nombre
     *            de la clase Job
     * @throws NumberFormatException
     * @throws NegocioException
     * @throws SQLException
     */
    private void executeProccessJobScheduler(String TimerJob) throws NegocioException, SQLException {
//	if (TimerJob.equals(JobEnvioImagenesFileNet.class.getSimpleName())) {
//	    try {
//		ProcesosJob.ejectuarEnvioArchivosFilenet();
//	    } catch (Exception e) {
//		log.error(e);
//	    }
//	} else 
    	if (TimerJob.equals(JobAsegurabilidad.class.getSimpleName())) {
	    try {
		avaluoSvc.ejecutarJobAsegurabilidad();
	    } catch (Exception e) {
		log.error(e);
	    }
	}
}
}