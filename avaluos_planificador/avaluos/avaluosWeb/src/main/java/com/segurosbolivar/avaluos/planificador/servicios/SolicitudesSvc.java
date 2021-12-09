package com.segurosbolivar.avaluos.planificador.servicios;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Level;
import org.mapstruct.factory.Mappers;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilFolder;
import com.segurosbolivar.avaluos.cons.AvaluosCons;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.facade.IIntegradorFacade;
import com.segurosbolivar.avaluos.modelo.prueba.ITomaTiempos;
import com.segurosbolivar.avaluos.modelo.service.IArchivo;
import com.segurosbolivar.avaluos.modelo.service.IParametrizacion;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.planificador.filenet.GestionArchivosImpl;
//import com.segurosbolivar.avaluos.modelo.ws.service.impl.ClienteFileNetWebService;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadFinancieraSolicitudDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.BeneficiarioDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.DocumentoDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudCreaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudMovimientoDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.MedioComunicacionDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.PlanificadorConsultaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.PlanificadorDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudBeneficiarioDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudConsultaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudPlanificadorDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadFinanciera;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Beneficiario;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Planificador;
import com.segurosbolivar.avaluos.planificador.modelo.entity.PlanificadorPK;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Solicitud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.SolicitudBeneficiarioPK;
import com.segurosbolivar.avaluos.planificador.modelo.entity.SolicitudMovimiento;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.PlanificadorFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.SolicitudPlanificadorFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.service.impl.SincronizarImpl;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadFinanciera;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadFinancieraSolicitud;
import com.segurosbolivar.avaluos.planificador.modelo.services.IBeneficiario;
import com.segurosbolivar.avaluos.planificador.modelo.services.ICrearSolicitud;
import com.segurosbolivar.avaluos.planificador.modelo.services.IDocumento;
import com.segurosbolivar.avaluos.planificador.modelo.services.IDominios;
import com.segurosbolivar.avaluos.planificador.modelo.services.IGestionArchivos;
import com.segurosbolivar.avaluos.planificador.modelo.services.IMedioComunicacion;
import com.segurosbolivar.avaluos.planificador.modelo.services.IPlanificador;
import com.segurosbolivar.avaluos.planificador.modelo.services.ISolicitud;
import com.segurosbolivar.avaluos.planificador.modelo.services.ISolicitudBeneficiario;
import com.segurosbolivar.avaluos.planificador.modelo.services.ISolicitudMovimiento;


@Path("/Solicitudes")
@Stateless
public class SolicitudesSvc {

	@EJB
	IParametrizacion pi;
	@EJB
	ISolicitud solicitud;

	@EJB
	IBeneficiario beneficiario;

	@EJB
	IActividadFinancieraSolicitud actividadFinancieraSolicitud;

	@EJB
	IMedioComunicacion medioComunicacion;

	@EJB
	IDominios dominios;

	@EJB
	IPlanificador planificador;

	@EJB
	IActividadFinanciera actividadFinanciera;

	@EJB
	ISolicitudBeneficiario solicitudBeneficiario;

	@EJB
	ISolicitudMovimiento solicitudMovimiento;

	@EJB
	IDocumento iDocumento;

	@EJB
	private IIntegradorFacade integradorFacade;

//	@EJB
//	ClienteFileNetWebService clienteFileNetWebService;

	@EJB
	private ITomaTiempos Tiempo;

	@EJB
	IArchivo iArchivo;

	@EJB
	IGestionArchivos iGestionArchivos;
	
	@EJB
	ICrearSolicitud crearSolicitud;
	
	@GET
	@Path("consultarTodas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaRest consultarTodas() {
		RespuestaRest respuesta;
		SolicitudConsultaDTO solConsultaDto;
		List<SolicitudConsultaDTO> listaSolConsulta = new ArrayList<>();
		PlanificadorDTO planificadorDTO = new PlanificadorDTO();
		Planificador planificadorEnt = new Planificador();
				
		
//		Se comentarean esta lineas mientras se integra con proyecto WEB para prueba 
		
		UsuarioDto dto = (UsuarioDto) UtilJsf.obtenerParametroSesion(AvaluosCons.CREDENCIAL);

		PlanificadorPK planificadorPK = new PlanificadorPK();
		planificadorPK.setNumeroDocumentoPlanificador(dto.getUsuario().getDocumento());
		
		
		//planificadorPK.setNumeroDocumentoPlanificador("75074545");//75074545 | 80192879
		planificadorEnt.setId(planificadorPK);
		
		SolicitudPlanificadorFullDTOMapper solicitudFullDTOMapper = Mappers.getMapper(SolicitudPlanificadorFullDTOMapper.class);
		
		PlanificadorFullDTOMapper planificadorFullDTOMapper = Mappers.getMapper(PlanificadorFullDTOMapper.class);

		List<SolicitudPlanificadorDTO> listado = new ArrayList<>();
		List<Solicitud> listaEnt = new ArrayList<>();

		try {

			planificadorEnt = planificador.buscaPorNumDoc(planificadorEnt.getId().getNumeroDocumentoPlanificador());
			planificadorDTO = planificadorFullDTOMapper.entity2dto(planificadorEnt);
			
			if(planificadorEnt.getId() == null) {
				listado = solicitudFullDTOMapper.entity2dtoList(solicitud.listaSolicitudes());
				listaEnt = solicitud.listaSolicitudes();	
				for(Solicitud solConsulta: solicitud.listaSolicitudes()) {
					solConsultaDto = new SolicitudConsultaDTO();
					solConsultaDto.setCodigoSolicitud(solConsulta.getCodigoSolicitud());
					if (!solConsulta.getSolicitudBeneficiarios().isEmpty()) {
						solConsultaDto.setId(solConsulta.getSolicitudBeneficiarios().get(0).getBeneficiario().getId());
						solConsultaDto.setPrimerNombre(solConsulta.getSolicitudBeneficiarios().get(0).getBeneficiario().getPrimerNombre());
						solConsultaDto.setSegundoNombre(solConsulta.getSolicitudBeneficiarios().get(0).getBeneficiario().getSegundoNombre());
						solConsultaDto.setPrimerApellido(solConsulta.getSolicitudBeneficiarios().get(0).getBeneficiario().getPrimerApellido());
						solConsultaDto.setSegundoApellido(solConsulta.getSolicitudBeneficiarios().get(0).getBeneficiario().getSegundoApellido());
					}
					PlanificadorConsultaDTO planificadorConsDto = new PlanificadorConsultaDTO(solConsulta.getPlanificador().getPrimerApellido(), 
																							solConsulta.getPlanificador().getPrimerNombre(), 
																							solConsulta.getPlanificador().getSegundoApellido(), 
																							solConsulta.getPlanificador().getSegundoNombre());
					solConsultaDto.setPlanificador(planificadorConsDto);
					listaSolConsulta.add(solConsultaDto);
				}
				respuesta = new RespuestaRest("200","ok",listaSolConsulta);
			}else {
				listado = solicitudFullDTOMapper.entity2dtoList(solicitud.listarSolicitudesPlanificador(planificadorDTO.getId().getNumeroDocumentoPlanificador()));
				listaEnt = solicitud.listarSolicitudesPlanificador(planificadorDTO.getId().getNumeroDocumentoPlanificador());	
				for(Solicitud solConsulta: solicitud.listaSolicitudes(planificadorDTO)) {
					solConsultaDto = new SolicitudConsultaDTO();
					solConsultaDto.setCodigoSolicitud(solConsulta.getCodigoSolicitud());
					if (!solConsulta.getSolicitudBeneficiarios().isEmpty()) {
						solConsultaDto.setId(solConsulta.getSolicitudBeneficiarios().get(0).getBeneficiario().getId());
						solConsultaDto.setPrimerNombre(solConsulta.getSolicitudBeneficiarios().get(0).getBeneficiario().getPrimerNombre());
						solConsultaDto.setSegundoNombre(solConsulta.getSolicitudBeneficiarios().get(0).getBeneficiario().getSegundoNombre());
						solConsultaDto.setPrimerApellido(solConsulta.getSolicitudBeneficiarios().get(0).getBeneficiario().getPrimerApellido());
						solConsultaDto.setSegundoApellido(solConsulta.getSolicitudBeneficiarios().get(0).getBeneficiario().getSegundoApellido());
					}
					PlanificadorConsultaDTO planificadorConsDto = new PlanificadorConsultaDTO(solConsulta.getPlanificador().getPrimerApellido(), 
																							solConsulta.getPlanificador().getPrimerNombre(), 
																							solConsulta.getPlanificador().getSegundoApellido(), 
																							solConsulta.getPlanificador().getSegundoNombre());
					solConsultaDto.setPlanificador(planificadorConsDto);
					listaSolConsulta.add(solConsultaDto);
				}
				respuesta = new RespuestaRest("200", "ok", listaSolConsulta);
			}
		} catch (SQLException | NegocioException e) {
			e.printStackTrace();
			respuesta = new RespuestaRest("500", e.getMessage(), null);
		}

		return respuesta;
	}

	@GET
	@Path("/consultarPorCriterio/{perfil}/{usuario}/{criterioConsulta}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaRest consultarPorCriterio(@PathParam("criterioConsulta") String criterioConsulta,
			@PathParam("perfil") String perfil,
			@PathParam("usuario") String usuario) {
		
		RespuestaRest respuesta;
		SolicitudConsultaDTO solConsultaDto;
		List<SolicitudConsultaDTO> listaSolConsulta = new ArrayList<>();

		try {

			for (Solicitud solConsulta : solicitud.listaSolicitudes(perfil, usuario, criterioConsulta.toUpperCase())) {
				solConsultaDto = new SolicitudConsultaDTO();
				solConsultaDto.setCodigoSolicitud(solConsulta.getCodigoSolicitud());
				if (!solConsulta.getSolicitudBeneficiarios().isEmpty()) {
					solConsultaDto.setId(solConsulta.getSolicitudBeneficiarios().get(0).getBeneficiario().getId());
					solConsultaDto.setPrimerNombre(solConsulta.getSolicitudBeneficiarios().get(0).getBeneficiario().getPrimerNombre());
					solConsultaDto.setSegundoNombre(solConsulta.getSolicitudBeneficiarios().get(0).getBeneficiario().getSegundoNombre());
					solConsultaDto.setPrimerApellido(solConsulta.getSolicitudBeneficiarios().get(0).getBeneficiario().getPrimerApellido());
					solConsultaDto.setSegundoApellido(solConsulta.getSolicitudBeneficiarios().get(0).getBeneficiario().getSegundoApellido());
				}
				PlanificadorConsultaDTO planificadorConsDto = new PlanificadorConsultaDTO(solConsulta.getPlanificador().getPrimerApellido(), 
																						solConsulta.getPlanificador().getPrimerNombre(), 
																						solConsulta.getPlanificador().getSegundoApellido(), 
																						solConsulta.getPlanificador().getSegundoNombre());
				solConsultaDto.setPlanificador(planificadorConsDto);
				solConsultaDto.setMunicipioDepartamento(solConsulta.getMunicipioDepartamento());
				solConsultaDto.setFechaSolicitud(solConsulta.getFechaSolicitud());
				solConsultaDto.setFechaSolicitudStr(new SimpleDateFormat("dd-MM-yyyy").format(solConsulta.getFechaSolicitud()));
				solConsultaDto.setEstado(solConsulta.getSolicitudMovimientos().get(0).getEstadoMovimiento());
				long ultimoMovimiento = solConsulta.getSolicitudMovimientos().get(0).getIdSolicitudMovimiento();
				
				for(SolicitudMovimiento sm : solConsulta.getSolicitudMovimientos()) {
				
					if(sm.getIdSolicitudMovimiento() > ultimoMovimiento) {
						solConsultaDto.setEstado(sm.getEstadoMovimiento());
						ultimoMovimiento = sm.getIdSolicitudMovimiento() ;
					}
					
				}
				
				
				listaSolConsulta.add(solConsultaDto);
			}
			respuesta = new RespuestaRest("200", "ok", listaSolConsulta);
		} catch (SQLException e) {
			e.printStackTrace();
			respuesta = new RespuestaRest("500", e.getMessage(), null);
		}

		return respuesta;
	}

	@Path("/crear")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public RespuestaRest crear(SolicitudCreaDTO solicitudModel) throws Exception , NegocioException, SQLException {

		RespuestaRest respuesta = null;
		
		try {
			String codigoSolicitud= crearSolicitud.crearSolicitud(solicitudModel);
			respuesta=new RespuestaRest("200", null, codigoSolicitud);
		} catch (SQLException | ParseException | NegocioException | javax.ejb.TransactionRolledbackLocalException  e) {
			
			respuesta=new RespuestaRest("500", e.getMessage(), null);			
		}
		
		
		return respuesta;

	}

	@Path("/addArchivo")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public RespuestaRest addArchivo(DocumentoDTO documentoModel) throws NegocioException, IOException, Exception {

		Logger.getLogger(GestionArchivosImpl.class.getName()).info("GestionArchivo , addArchivo , Inicio ");

		RespuestaRest respuesta;
		Solicitud solicitudEnt = new Solicitud();
		DocumentoDTO documentoDto = new DocumentoDTO();

		/*
		 * Crear Documentos asociados a la Solicitud
		 */
		// Inserta Datos del Documento
		documentoDto.setNombreDocumento(documentoModel.getNombreDocumento());
		// documentoDto.setFechaCreacionStr(documentoModel.getFechaCreacionStr());
		// documentoDto.setFechaStr(documentoModel.getFechaStr());
		Date date = new Date();
		documentoDto.setFecha(date);
		documentoDto.setFechaCreacion(date);
		try {
			solicitudEnt = solicitud.buscarPorId(documentoModel.getSolicitud().getCodigoSolicitud());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		documentoDto.setSolicitud(solicitudEnt);
		documentoDto.setTipoDocumento("E");
		documentoDto.setUsuarioCreacion(documentoModel.getUsuarioCreacion());
		documentoDto.setContenidoArchivo(documentoModel.getContenidoArchivo());

		List<String> ruta = new ArrayList<String>();
		ruta.add(UtilConstantes.RUTA_SOPORTES);
		ruta.add("SOL" + documentoDto.getSolicitud().getCodigoSolicitud() +'_' + documentoDto.getNombreDocumento());
		documentoDto.setPath(iGestionArchivos.guardarArchivoS3(new String(UtilFolder.obtenerRutaDirectorio(ruta)),
				documentoDto.getContenidoArchivo()));
	
		try {
			iDocumento.crearDocumento(documentoDto);
			respuesta = new RespuestaRest("200", "ok", null);
		} catch (Exception e) {
			respuesta = new RespuestaRest("500", e.getMessage(), null);
			Logger.getLogger(GestionArchivosImpl.class.getName()).info("Adjuntar archivo ,  addArchivo , Error al adjuntar documento " + documentoDto.getSolicitud().getCodigoSolicitud());		
		}
		Logger.getLogger(GestionArchivosImpl.class.getName()).info("Adjuntar archivo ,  addArchivo , fin " + documentoDto.getSolicitud().getCodigoSolicitud());		
		return respuesta;
	}

	@Path("/completarSolicitud")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public RespuestaRest completarSolicitud(SolicitudDTO solicitudModel) {
		RespuestaRest respuesta;
		Solicitud solicitudEnt = new Solicitud();
		SolicitudMovimientoDTO solicitudMovimientoDto = new SolicitudMovimientoDTO();

		/*
		 * Cambiar estado de la Solicitud a Finalizar
		 */
		try {
			solicitudEnt = solicitud.buscarPorId(solicitudModel.getCodigoSolicitud());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		solicitudMovimientoDto.setEstadoMovimiento("FIN");
		solicitudMovimientoDto.setFechaMovimientoStr(solicitudModel.getFechaCreacionStr());
		solicitudMovimientoDto.setUsuarioMovimiento(solicitudModel.getUsuarioCreacion());
		solicitudMovimientoDto.setSolicitud(solicitudEnt);
		try {
			solicitudMovimiento.crearSolicitudMovimiento(solicitudMovimientoDto);
			respuesta = new RespuestaRest("200", "ok", null);
		} catch (Exception e) {
			respuesta = new RespuestaRest("500", e.getMessage(), null);
		}

		return respuesta;

	}

}