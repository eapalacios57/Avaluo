package com.segurosbolivar.avaluos.modelo.service.impl;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;

import org.apache.log4j.Logger;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.davivienda.xml.notificacionaseguradoras.DataBUAReqType;
import com.davivienda.xml.notificacionaseguradoras.DataHeaderReqType;
import com.davivienda.xml.notificacionaseguradoras.EntidadeBUAReqType;
import com.davivienda.xml.notificacionaseguradoras.EventoBUAReqType;
import com.davivienda.xml.notificacionaseguradoras.EventoDatoReqType;
import com.davivienda.xml.notificacionaseguradoras.EventosBUAReqType;
import com.davivienda.xml.notificacionaseguradoras.GestionAvaluosReqType;
import com.davivienda.xml.notificacionaseguradoras.NotificacionAvaluosRespType;
import com.davivienda.xml.notificacionaseguradoras.NotificacionAvaluosRqType;
import com.davivienda.xml.notificacionaseguradoras.PerfecCreditoViviendaMReqType;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.data.ComplementosExcelDao;
import com.segurosbolivar.avaluos.modelo.data.ParametrizacionDao;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.ComplementosExcel;
import com.segurosbolivar.avaluos.modelo.entity.Parametrizacion;
import com.segurosbolivar.avaluos.modelo.facade.IIntegradorFacade;
import com.segurosbolivar.avaluos.modelo.service.IArchivo;
import com.segurosbolivar.avaluos.modelo.service.IComplementos;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;

@Stateless
public class ComplementosImpl implements IComplementos {

    private static final long serialVersionUID = -1196953327883019870L;
    private static final Logger log = Logger.getLogger(ComplementosImpl.class);
    @EJB
    private ManejadorErroresNegocio mgrExc;
    @EJB
    private IArchivo archivoSvc;
    @EJB
    private ComplementosExcelDao complementosDao;
    @EJB
	private IIntegradorFacade integradorSvc;
	@EJB
	private ParametrizacionDao parametrizacionDao;
	
    @Override
    public void guardar(ComplementosExcel complemento, InputStream archivo, UsuarioDto usuario) throws NegocioException {
	try {
	    if (usuario == null || usuario.getUsuario() == null)
		throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
	    if (complemento == null)
		throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
	    if (complemento.getArchivo() == null)
		throw mgrExc.lanzarExcepcion(29, TipoErrorNegocio.ERROR);
	    // guardamos los archivos en caso de que se modificaran.
	    Avaluo persona = new Avaluo();
	    persona.setNumeroIdentificacion(Long.parseLong(usuario.getUsuario().getCodigo()));
	    persona.setNombreSolicitante(usuario.getUsuario().getCodigo());
	    persona.setIdTipoIdentificacion(21L);
	    if (complemento.getArchivo().isModificado()) {
	    	String llaveArchivo = UtilConstantes.RUTA_COMPLEMENTOS + "/" + complemento.getArchivo().getNombreArchivo();
	    	archivoSvc.guardarArchivo(persona, complemento.getArchivo(), usuario, llaveArchivo);
	    	complemento.setIdArchivo(complemento.getArchivo().getIdArchivo());
	    }
	    complemento.setFechaTransaccion(new Date());
	    complemento.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
	    // si el registro no existe lo crearemos. de lo contrario solo se
	    // actualizaran sus valores.
	    if (complemento.getIdComplementoExcel() == null || complementosDao.buscar(complemento.getIdComplementoExcel()) == null) {
		complemento.setFechaCreacion(new Date());
		complemento.setUsuarioCreacion(usuario.getUsuario().getCodigo());
		complementosDao.crear(complemento);
	    } else
		complementosDao.actualizar(complemento);
	} catch (Exception e) {
	    throw mgrExc.lanzarExcepcion(29, TipoErrorNegocio.ERROR, e.getMessage(), null);
	}
    }

    @Override
    public List<ComplementosExcel> consultar(ComplementosExcel consulta, int registroInicio, int cantidadRegistros, String campoOrden, SentidoOrientacion sentido)
	    throws NegocioException {
	return complementosDao.consultar(consulta, registroInicio, cantidadRegistros, campoOrden, sentido);
    }

    @Override
    public int cantidadRegistros(ComplementosExcel consulta) throws NegocioException {
	return complementosDao.cantidadRegistros(consulta);
    }

    @Override
    public void eliminar(ComplementosExcel eliminar, UsuarioDto usuarioDto) throws NegocioException {
	if (eliminar == null)
	    throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
	complementosDao.eliminar(eliminar);
	//	integradorFacade.eliminarArchivo(eliminar.getArchivo().getIdDocumento());
	
    }
    
    @Override
    public void enviarNotificacion(Avaluo avaluo , UsuarioDto usuario) throws NegocioException {
    	InetAddress address;
    	
        String NOMBRE_OPERACION = null;
        Short NOTIF_ID_CANAL = null;
        Short TOTAL = null;
        Short NOTIF_JORNADA = null;
        String VERSION_SERVICIO = null;
        Short MODO_OPERACION = null;
        String NOTIF_USUARIO = null;//FIXME quedo pendiente jordy dara un usr fijo para SB
        Short PERFIL = null;
        String NOTIF_PAIS = null;
        String NOTIF_IDIOMA = null;
        String NOTIF_DOMAIN = null;//FIXME quedo pendiente jordy dara un valor fijo para SB 
        String NOTIF_NOMBRE_EVENTO = null;
        String NOTIF_IDRESULTADO_AVALUO_APROBADO = null;
        String NOTIF_IDRESULTADO_AVALUO_NO_APROBADO = null;
    	
        List<Parametrizacion> ParametrosReqDavivienda = parametrizacionDao.getTiposParametro(UtilConstantes.TIPO_PARAMETRO_REQUEST_WS_NOTIF_DAVIVIENDA);
		for (Parametrizacion parametro : ParametrosReqDavivienda) {
			if(parametro.getNombreparametro().equals("NOMBRE_OPERACION")) {
				NOMBRE_OPERACION = parametro.getValorparametro();
			}else if(parametro.getNombreparametro().equals("NOTIF_ID_CANAL")) {
				NOTIF_ID_CANAL = Short.valueOf(parametro.getValorparametro());
			}else if(parametro.getNombreparametro().equals("TOTAL")) {
				TOTAL = Short.valueOf(parametro.getValorparametro());
			}else if(parametro.getNombreparametro().equals("NOTIF_JORNADA")) {
				NOTIF_JORNADA = Short.valueOf(parametro.getValorparametro());
			}else if(parametro.getNombreparametro().equals("VERSION_SERVICIO")) {
				VERSION_SERVICIO = parametro.getValorparametro();
			}else if(parametro.getNombreparametro().equals("MODO_OPERACION")) {
				MODO_OPERACION = Short.valueOf(parametro.getValorparametro());
			}else if(parametro.getNombreparametro().equals("NOTIF_USUARIO")) {
				NOTIF_USUARIO = parametro.getValorparametro();
			}else if(parametro.getNombreparametro().equals("PERFIL")) {
				PERFIL = Short.valueOf(parametro.getValorparametro());
			}else if(parametro.getNombreparametro().equals("NOTIF_PAIS")) {
				NOTIF_PAIS = parametro.getValorparametro();
			}else if(parametro.getNombreparametro().equals("NOTIF_IDIOMA")) {
				NOTIF_IDIOMA = parametro.getValorparametro();
			}else if(parametro.getNombreparametro().equals("NOTIF_DOMAIN")) {
				NOTIF_DOMAIN = parametro.getValorparametro();
			}else if(parametro.getNombreparametro().equals("NOTIF_NOMBRE_EVENTO")) {
				NOTIF_NOMBRE_EVENTO = parametro.getValorparametro();
			}else if(parametro.getNombreparametro().equals("NOTIF_IDRESULTADO_AVALUO_APROBADO")) {
				NOTIF_IDRESULTADO_AVALUO_APROBADO = parametro.getValorparametro();
			}else if(parametro.getNombreparametro().equals("NOTIF_IDRESULTADO_AVALUO_NO_APROBADO")) {
				NOTIF_IDRESULTADO_AVALUO_NO_APROBADO = parametro.getValorparametro();
			}
		}
    	
    	String direccionIp = "SegBolivar";
		try {
			address = InetAddress.getLocalHost();
			direccionIp = address.getHostAddress();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
    	NotificacionAvaluosRqType request = new NotificacionAvaluosRqType();
    	DataHeaderReqType dataHeaderReqType = new DataHeaderReqType();
		DataBUAReqType dataBuaReqType = new DataBUAReqType();
		log.info("Se va notificar a Davivienda");
		dataBuaReqType.setIdSesion(usuario.getUsuario().getCodigo()+new Date().getTime());
		dataBuaReqType.setCodIdioma(NOTIF_IDIOMA);
		dataBuaReqType.setValOrigen(direccionIp);
		dataBuaReqType.setCodPais(NOTIF_PAIS);
		dataBuaReqType.setValDomain(NOTIF_DOMAIN);
		
		/*
		dataBuaReqType.setNumCodigoAvaluo(avaluo.getConsecutivoBanco().toString());
		dataBuaReqType.setNumIdentificacionPerito(Long.parseLong(usuario.getUsuario().getCodigo()));
		dataBuaReqType.setValNombrePerito(usuario.getUsuario().getNombres());
		dataBuaReqType.setNumSolicitudMovil(avaluo.getNumeroSolicitudMovil());
		dataBuaReqType.setValEstadoAvaluo(avaluo.getEstadoAvaluoDescripcion());
		 */
		
		EventosBUAReqType eventos = new EventosBUAReqType();
		
		EventoDatoReqType evtoDato = new EventoDatoReqType();
		evtoDato.setNumRadNumber(String.valueOf(avaluo.getNumeroSolicitudMovil()));
		evtoDato.setValEventName(NOTIF_NOMBRE_EVENTO);
		
		GestionAvaluosReqType gestionAvaluos = new GestionAvaluosReqType();
		GregorianCalendar gcal = new GregorianCalendar();
		gcal.setTime(new Date());
		try {
			gestionAvaluos.setFechaNotificacion(DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal.get(Calendar.YEAR), gcal.get(Calendar.MONTH)+1, gcal.get(Calendar.DAY_OF_MONTH), gcal.get(Calendar.HOUR_OF_DAY), gcal.get(Calendar.MINUTE), gcal.get(Calendar.SECOND), DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED));
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		gestionAvaluos.setValUsuarioBUA(usuario.getUsuario().getCodigo());
		gestionAvaluos.setValIdResultadoAvaluo(avaluo.getEstadoAvaluo().equals(3L) ? NOTIF_IDRESULTADO_AVALUO_APROBADO : NOTIF_IDRESULTADO_AVALUO_NO_APROBADO);
		gestionAvaluos.setValNumeroAvaluo(String.valueOf(avaluo.getConsecutivoBanco()));
		
		EntidadeBUAReqType entidades = new EntidadeBUAReqType();
		PerfecCreditoViviendaMReqType perfecCreditoViviendaM = new PerfecCreditoViviendaMReqType();
		perfecCreditoViviendaM.setGestionAvaluos(gestionAvaluos);
		entidades.setPerfecCreditoViviendaM(perfecCreditoViviendaM);
		
		EventoBUAReqType evto = new EventoBUAReqType();
		evto.setEventoDato(evtoDato);
		evto.setEntidades(entidades);
		eventos.getEvento().add(evto);
		
		dataBuaReqType.setEventos(eventos);

		
		String nroIdCliente = String.valueOf(avaluo.getNumeroIdentificacion());
		String idTransaccion = nroIdCliente.substring(nroIdCliente.length()-3) + "" + gcal.get(Calendar.DAY_OF_MONTH) + "" + (gcal.get(Calendar.MONTH)+1) + "" + gcal.get(Calendar.HOUR_OF_DAY);
		
		dataHeaderReqType.setNombreOperacion(NOMBRE_OPERACION);
		dataHeaderReqType.setTotal(TOTAL);
		dataHeaderReqType.setJornada(NOTIF_JORNADA);
		dataHeaderReqType.setCanal(NOTIF_ID_CANAL);
		dataHeaderReqType.setModoDeOperacion(MODO_OPERACION);
		dataHeaderReqType.setUsuario(NOTIF_USUARIO);//usuario.getUsuario().getCodigo());
		dataHeaderReqType.setPerfil(PERFIL);
		dataHeaderReqType.setVersionServicio(VERSION_SERVICIO);		
		dataHeaderReqType.setIdTransaccion(Integer.parseInt(idTransaccion));
		
		request.setData(dataBuaReqType);
		request.setDataHeader(dataHeaderReqType);
		NotificacionAvaluosRespType respuesta = integradorSvc.enviarNotificacion(request);	


		if(respuesta.getDataHeader().getCodMsgRespuesta() != 0 || respuesta.getDataHeader().getMsgRespuesta() != null) {
			log.error("Transaccion "+ idTransaccion +"WS Notificaciones respondio con error: "+respuesta.getDataHeader().getCodMsgRespuesta()+" - "+respuesta.getDataHeader().getMsgRespuesta());
		}else {
			log.info("Transaccion "+ idTransaccion +" Caracter Aceptacion recibido del WS Notificaciones: "+respuesta.getDataHeader().getCaracterAceptacion());
		}
		

		
		if (!respuesta.getDataHeader().getCaracterAceptacion().equals("B") )
		    throw mgrExc.lanzarExcepcion(129, TipoErrorNegocio.ERROR, avaluo.getConsecutivoBanco().toString(), null);
		
    }

}
