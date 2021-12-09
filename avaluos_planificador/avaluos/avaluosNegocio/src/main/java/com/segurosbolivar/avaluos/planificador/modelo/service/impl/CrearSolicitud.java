package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagementType;
import javax.ejb.TransactionRolledbackLocalException;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.persistence.NoResultException;
import javax.swing.plaf.synth.SynthSpinnerUI;

import org.mapstruct.factory.Mappers;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilFolder;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.ws.service.impl.ClienteFcaWebService;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadFinancieraSolicitudDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.BeneficiarioCrearDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.BeneficiarioDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.DocumentoDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.MedioComunicacionDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudBeneficiarioDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudCreaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadAvicola;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadFinancieraSolicitud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadGanadera;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadPiscicola;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadPorcicola;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Beneficiario;
import com.segurosbolivar.avaluos.planificador.modelo.entity.BeneficiarioPK;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Cultivo;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Documento;
import com.segurosbolivar.avaluos.planificador.modelo.entity.MedioComunicacion;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Planificador;
import com.segurosbolivar.avaluos.planificador.modelo.entity.PlanificadorPK;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Predio;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ProductoRelacionadoSolicitud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Solicitud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.SolicitudBeneficiario;
import com.segurosbolivar.avaluos.planificador.modelo.entity.SolicitudBeneficiarioPK;
import com.segurosbolivar.avaluos.planificador.modelo.entity.SolicitudMovimiento;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Soporte;
import com.segurosbolivar.avaluos.planificador.modelo.entity.TecnificacionAgricola;
import com.segurosbolivar.avaluos.planificador.modelo.entity.UnidadProductiva;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ValorPorcentaje;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.BeneficiarioFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.MedioComunicacionFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.SolicitudBeneficiarioFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadFinanciera;
import com.segurosbolivar.avaluos.planificador.modelo.services.IActividadFinancieraSolicitud;
import com.segurosbolivar.avaluos.planificador.modelo.services.IBeneficiario;
import com.segurosbolivar.avaluos.planificador.modelo.services.ICrearSolicitud;
import com.segurosbolivar.avaluos.planificador.modelo.services.IDocumento;
import com.segurosbolivar.avaluos.planificador.modelo.services.IGestionArchivos;
import com.segurosbolivar.avaluos.planificador.modelo.services.IMedioComunicacion;
import com.segurosbolivar.avaluos.planificador.modelo.services.IPlanificador;
import com.segurosbolivar.avaluos.planificador.modelo.services.IProductoRelacionadoSolicitud;
import com.segurosbolivar.avaluos.planificador.modelo.services.ISolicitud;
import com.segurosbolivar.avaluos.planificador.modelo.services.ISolicitudBeneficiario;
import com.segurosbolivar.avaluos.planificador.modelo.services.ISolicitudMovimiento;
import com.segurosbolivar.avaluos.planificador.notificaciones.Notificador;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(ICrearSolicitud.class)
public class CrearSolicitud implements ICrearSolicitud {
	
	

	@Resource
	private EJBContext context;
	
	@EJB
	IProductoRelacionadoSolicitud iProductoRelacionadoSolcitud;
	
	@EJB
	IPlanificador iPlanificador;

	@EJB
	ISolicitud iSolicitud;

	@EJB
	IBeneficiario iBeneficiario;

	@EJB
	IMedioComunicacion iMedioComunicacion;

	@EJB
	ISolicitudBeneficiario iSolicitudBeneficiario;

	@EJB
	ISolicitudMovimiento iSolicitudMovimiento;

	@EJB
	IActividadFinanciera iActividadFinanciera;

	@EJB
	IActividadFinancieraSolicitud iActividadFinancieraSolicitud;

	@EJB
	IGestionArchivos iGestionArchivos;

	@EJB
	IDocumento iDocumento;	
    
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public String crearSolicitud(SolicitudCreaDTO solicitudCreaDTO) 
			throws SQLException, TransactionRolledbackLocalException, NegocioException, ParseException , Exception {
		Logger.getLogger(SincronizarImpl.class.getName()).info("Crear Solicitud ,  crearSolicitud , inicio");
		
		//Solicitud solicitud = null;      
		Solicitud solicitud = guardarSolicitud(solicitudCreaDTO);
		try {

			//solicitud = guardarSolicitud(solicitudCreaDTO);						
			crearActividadFinancieraSolicitud(solicitudCreaDTO.getListaAFSolicitud(), solicitud);			
			crearActualizarBeneficirario(solicitudCreaDTO, solicitud);		
			crearMovimientoSolicitud(solicitudCreaDTO, solicitud);
			crearDocumentosSolicitud(solicitudCreaDTO, solicitud);
			System.out.println("FCM INICIO  ANTES DE NOTIFICAR>");
			Notificador notificador = new Notificador();
			System.out.println("FCM SE CREO EL OBJETO DE NOTIFICAR>");

			if (solicitud.getPlanificador().getTokenDispositivo() != null) {
				notificador.notificarCrearSolicitud(solicitud.getCodigoSolicitud(), 
						solicitud.getMunicipioDepartamento(),
						solicitud.getPlanificador().getTokenDispositivo());
			}
			else {				
			}
			Logger.getLogger(SincronizarImpl.class.getName()).info("Crear Solicitud ,  crearSolicitud , fin");
			return solicitud.getCodigoSolicitud();

		} catch (Exception e) {            
			Logger.getLogger(SincronizarImpl.class.getName()).info("Crear Solicitud ,  crearSolicitud , Error " + e );
			crearSolicitudRollBack(solicitud.getCodigoSolicitud());
			throw new NegocioException("No fue posible crear la solicitud. Error:   " + e.getMessage());
		}

	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public Planificador buscarPlanificador(String tipoDocumento, String numeroDocumento)
			throws NoResultException, NegocioException, SQLException,  Exception {
		Logger.getLogger(SincronizarImpl.class.getName()).info("Crear Solicitud ,  buscarPlanificador , inicio");
		try {
		PlanificadorPK planificadorPk = new PlanificadorPK();
		planificadorPk.setTipoDocumentoPlanificador(tipoDocumento);
		planificadorPk.setNumeroDocumentoPlanificador(numeroDocumento);
		Logger.getLogger(SincronizarImpl.class.getName()).info("Crear Solicitud ,  buscarPlanificador , fin");
		return iPlanificador.buscaPlanificador(planificadorPk);
		}catch(Exception e) {
			Logger.getLogger(SincronizarImpl.class.getName()).info("Crear Solicitud ,  buscarPlanificador , Error al buscar planificador  "+ e );
			throw new Exception("Error al buscar planificador");
		}

	}
    
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public Solicitud guardarSolicitud(SolicitudCreaDTO solicitudCreaDTO) 
			throws NoResultException, Exception, NegocioException, SQLException {		
		Logger.getLogger(SincronizarImpl.class.getName()).info("Crear Solicitud ,  guardarSolicitud , inicio");
		try {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		Date fechaSolicitud;	
		Date fechaUltimaSolicitud;

		Solicitud solicitud = new Solicitud();
		solicitud.setAvance(new BigDecimal(0));
		solicitud				.setPlanificador(buscarPlanificador(solicitudCreaDTO.getPlanificadorId().getTipoDocumentoPlanificador(),
						solicitudCreaDTO.getPlanificadorId().getNumeroDocumentoPlanificador()));
		solicitud.setFechaSolicitud(new Date());
		solicitud.setFechaCreacion(new Date());
		solicitud.setComentariosAnexos(solicitudCreaDTO.getComentariosAnexos());
		solicitud.setValorTotalProyecto(new BigDecimal(0));
		solicitud.setValorTotalCredito(new BigDecimal(0));
		solicitud.setNombreAsesorComercial(solicitudCreaDTO.getNombreAsesorComercial());
		solicitud.setTelefonoAsesorComercial(solicitudCreaDTO.getTelefonoAsesorComercial());
		solicitud.setMunicipioDepartamento(solicitudCreaDTO.getMunicipioDepartamento());
		solicitud.setUsuarioCreacion(solicitudCreaDTO.getListaAFSolicitud().get(0).getUsuarioCreacion());
		solicitud.setIdCiudad(new BigDecimal(solicitudCreaDTO.getMunicipio()));
		solicitud.setIdDepartamento(new BigDecimal(solicitudCreaDTO.getDepartamento()));
		
		fechaSolicitud = solicitud.getFechaSolicitud();
		fechaUltimaSolicitud = iSolicitud.ultimaSolicitud(solicitudCreaDTO.getListBeneficiarios().get(0).getNumeroDocumentoBeneficiario().toString());
				
		if (fechaUltimaSolicitud == null || (fechaSolicitud.getTime() - fechaUltimaSolicitud.getTime())/86400000 >= 1) {
			
			iSolicitud.crearSolicitud(solicitud);
			
		}else {
			
			throw new NegocioException("No se puede crear más de una solicitud para un mismo beneficiario en el día");
		} 

		
		Logger.getLogger(SincronizarImpl.class.getName()).info("Crear Solicitud ,  guardarSolicitud , fin");
		return solicitud;
		
		}catch(Exception e) {
			Logger.getLogger(SincronizarImpl.class.getName()).info("Crear Solicitud ,  guardarSolicitud , Error al guardar la solicitud  "+ e );
			throw new Exception("Error al guardar solicitud");
		}
		
		

	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void crearActualizarBeneficirario(SolicitudCreaDTO solicitudModel, Solicitud solicitudEnt)
			throws NoResultException,  NegocioException, ParseException, Exception {
		Logger.getLogger(SincronizarImpl.class.getName()).info("Crear Solicitud ,  crearActualizarBeneficirario , inicio");
		try {
		// El beneficiario principal sera el primero de la lista
		boolean isPrincipal = true;
        
		for (BeneficiarioCrearDTO crearDTO : solicitudModel.getListBeneficiarios()) {

			BeneficiarioDTO beneficiarioDto = new BeneficiarioDTO();
			Beneficiario beneficiarioEnt = new Beneficiario();
			MedioComunicacionDTO medioComunicacionDto = new MedioComunicacionDTO();
			SolicitudBeneficiarioDTO solicitudBeneficiarioDto = new SolicitudBeneficiarioDTO();
			SolicitudBeneficiarioPK solicitudBeneficiarioPK = new SolicitudBeneficiarioPK();

			BeneficiarioPK beneficiarioPK = new BeneficiarioPK();
			beneficiarioPK.setNumeroDocumentoBeneficiario(crearDTO.getNumeroDocumentoBeneficiario());
			beneficiarioPK.setTipoDocumentoBeneficiario(crearDTO.getTipoDocumentoBeneficiario());

			beneficiarioDto.setId(beneficiarioPK);
			beneficiarioDto.setPrimerNombre(crearDTO.getPrimerNombre());
			beneficiarioDto.setSegundoNombre(crearDTO.getSegundoNombre());
			beneficiarioDto.setPrimerApellido(crearDTO.getPrimerApellido());
			beneficiarioDto.setSegundoApellido(crearDTO.getSegundoApellido());

			/*
			 * Ojo verificar si ya existe el beneficiario, si es asi actualizarlo
			 */

			beneficiarioEnt = iBeneficiario.buscaBeneficiarioPorId(beneficiarioDto.getId());

			if (beneficiarioEnt == null) {
				beneficiarioEnt = iBeneficiario.crearBeneficiario(
						Mappers.getMapper(BeneficiarioFullDTOMapper.class).dto2entity(beneficiarioDto));
			} else {
//				if (beneficiarioEnt.getPrimerNombre().equals(beneficiarioDto.getPrimerNombre())
//						&& beneficiarioEnt.getPrimerApellido().equals(beneficiarioDto.getPrimerApellido())
//						&& beneficiarioEnt.getSegundoNombre().equals(beneficiarioDto.getSegundoNombre())
//						&& beneficiarioEnt.getSegundoApellido().equals(beneficiarioDto.getSegundoApellido())) {
				iBeneficiario.actualizaBeneficiario(beneficiarioEnt);
//				} else {
//					
//					throw new NegocioException("El beneficiario asociado al número de documento ya existe");
//					}
			}

			/*
			 * Crea Medio de Comunicacion para el Beneficiario
			 */
			medioComunicacionDto.setDescripcion("Teléfono principal de contacto");
			medioComunicacionDto.setTipoMedioComunicacion("2"); // Pendiente definir como se estandarizan
			medioComunicacionDto.setEstado("A");
			medioComunicacionDto.setPrincipal(isPrincipal ? "S" : "N");
			medioComunicacionDto.setValor(crearDTO.getTelefonoBeneficiario());
			medioComunicacionDto.setFechaCreacion(new Date());
			medioComunicacionDto.setBeneficiario(beneficiarioEnt);
			medioComunicacionDto.setTipoDocumentoBeneficiario(beneficiarioEnt.getId().getTipoDocumentoBeneficiario());
			medioComunicacionDto
					.setNumeroDocumentoBeneficiario(beneficiarioEnt.getId().getNumeroDocumentoBeneficiario());
			medioComunicacionDto.setUsuarioCreacion(solicitudModel.getListaAFSolicitud().get(0).getUsuarioCreacion());

			iMedioComunicacion.crearMedioComunicacion(
					Mappers.getMapper(MedioComunicacionFullDTOMapper.class).dto2entity(medioComunicacionDto));

			/*
			 * Validar segundo telefono
			 */
			if (crearDTO.getTelefonoBeneficiario2() != null && !crearDTO.getTelefonoBeneficiario2().equals("")) {

				medioComunicacionDto = new MedioComunicacionDTO();
				medioComunicacionDto.setDescripcion("Teléfono segundario de contacto");
				medioComunicacionDto.setTipoMedioComunicacion("2"); // Pendiente definir como se estandarizan
				medioComunicacionDto.setEstado("A");
				medioComunicacionDto.setPrincipal("N");
				medioComunicacionDto.setValor(crearDTO.getTelefonoBeneficiario2());
				medioComunicacionDto.setFechaCreacion(new Date());
				medioComunicacionDto.setBeneficiario(beneficiarioEnt);
				medioComunicacionDto
						.setTipoDocumentoBeneficiario(beneficiarioEnt.getId().getTipoDocumentoBeneficiario());
				medioComunicacionDto
						.setNumeroDocumentoBeneficiario(beneficiarioEnt.getId().getNumeroDocumentoBeneficiario());
				medioComunicacionDto
						.setUsuarioCreacion(solicitudModel.getListaAFSolicitud().get(0).getUsuarioCreacion());

				iMedioComunicacion.crearMedioComunicacion(
						Mappers.getMapper(MedioComunicacionFullDTOMapper.class).dto2entity(medioComunicacionDto));

			}

			/*
			 * Crea Solicitud Beneficiario
			 */
			solicitudBeneficiarioDto.setSolicitud(solicitudEnt);
			solicitudBeneficiarioDto.setBeneficiario(beneficiarioEnt);
			solicitudBeneficiarioPK.setCodigoSolicitud(solicitudEnt.getCodigoSolicitud());
			solicitudBeneficiarioPK
					.setNumeroDocumentoBeneficiario(beneficiarioDto.getId().getNumeroDocumentoBeneficiario());
			solicitudBeneficiarioPK
					.setTipoDocumentoBeneficiario(beneficiarioDto.getId().getTipoDocumentoBeneficiario());
			solicitudBeneficiarioDto.setId(solicitudBeneficiarioPK);
			solicitudBeneficiarioDto
					.setTipoDocumentoBeneficiario(solicitudBeneficiarioPK.getTipoDocumentoBeneficiario());
			solicitudBeneficiarioDto.setContacto(isPrincipal ? "S" : "N");
			solicitudBeneficiarioDto
					.setUsuarioCreacion(solicitudModel.getListaAFSolicitud().get(0).getUsuarioCreacion());
			solicitudBeneficiarioDto.setFechaCreacion(new Date());
			solicitudBeneficiarioDto
					.setNumeroDocumentoBeneficiario(solicitudBeneficiarioPK.getNumeroDocumentoBeneficiario());

			iSolicitudBeneficiario.crearSolicitudBeneficiario(
					Mappers.getMapper(SolicitudBeneficiarioFullDTOMapper.class).dto2entity(solicitudBeneficiarioDto));

			isPrincipal = false;
		    }
		Logger.getLogger(SincronizarImpl.class.getName()).info("Crear Solicitud ,  crearActualizarBeneficirario , fin");
		}catch(Exception  e) {
			Logger.getLogger(SincronizarImpl.class.getName()).info("Crear Solicitud ,  crearActualizarBeneficirario  , Error al crear o actualizar el Beneficiario  "+ e );
			throw new Exception("Error al crear o actualizar el Beneficiario");			
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void crearMovimientoSolicitud(SolicitudCreaDTO solicitudCreaDTO, Solicitud solicitud)
			throws Exception, NegocioException {
		Logger.getLogger(SincronizarImpl.class.getName()).info("Crear Solicitud ,  crearMovimientoSolicitud , inicio");
		SimpleDateFormat formatoTexto = new SimpleDateFormat("dd-MM-yyyy");
		String[] estadoMovimientos = { "CRE", "APR", "ASG" };
           try {
		for (int i = 0; i < estadoMovimientos.length; i++) {
			String estado = estadoMovimientos[i];

			SolicitudMovimiento solicitudMovimiento = new SolicitudMovimiento();
			solicitudMovimiento.setFechaMovimiento(
					formatoTexto.parse(solicitudCreaDTO.getListaAFSolicitud().get(0).getFechaCreacionStr()));
			solicitudMovimiento.setUsuarioMovimiento(solicitudCreaDTO.getListaAFSolicitud().get(0).getUsuarioCreacion());
			solicitudMovimiento.setSolicitud(solicitud);
			solicitudMovimiento.setEstadoMovimiento(estado);

			iSolicitudMovimiento.crearSolicitudMovimiento(solicitudMovimiento);
		      }
		Logger.getLogger(SincronizarImpl.class.getName()).info("Crear Solicitud ,  crearMovimientoSolicitud , fin");
           }catch(Exception e) {        	   					
        	   Logger.getLogger(SincronizarImpl.class.getName()).info("Crear Solicitud ,  crearMovimientoSolicitud  , Error al crear el estado de la solicitud  "+ e );
        	   				throw new NegocioException("No se puede guardar el estado de la solicitud  ");
           			}
        }
           
           
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void crearActividadFinancieraSolicitud(
			List<ActividadFinancieraSolicitudDTO> listaActividadFinancieraSolicitudDTO, Solicitud solicitud)
			throws Exception {
		Logger.getLogger(SincronizarImpl.class.getName()).info("Crear Solicitud ,  crearActividadFinancieraSolicitud , inicio");
		try {
		SimpleDateFormat formatoTexto = new SimpleDateFormat("dd-MM-yyyy");

		for (ActividadFinancieraSolicitudDTO aFinSolDto : listaActividadFinancieraSolicitudDTO) {
			ActividadFinancieraSolicitud actividadFinancieraSolicitud = new ActividadFinancieraSolicitud();
			actividadFinancieraSolicitud.setCodigoSolicitud(solicitud.getCodigoSolicitud());
			actividadFinancieraSolicitud.setCodigoActividad(aFinSolDto.getId().getCodigoActividad());
			actividadFinancieraSolicitud.setUnidad(aFinSolDto.getUnidad());
			actividadFinancieraSolicitud.setCantidad(aFinSolDto.getCantidad());
			actividadFinancieraSolicitud.setValorProyecto(aFinSolDto.getValorProyecto());
			actividadFinancieraSolicitud.setValorCredito(aFinSolDto.getValorCredito());
			actividadFinancieraSolicitud.setPlazo(aFinSolDto.getPlazo());
			actividadFinancieraSolicitud.setPeriodoGracia(aFinSolDto.getPeriodoGracia());
			actividadFinancieraSolicitud.setPrincipal(aFinSolDto.getPrincipal());
			actividadFinancieraSolicitud.setRazonInversion(aFinSolDto.getRazonInversion());
			actividadFinancieraSolicitud.setFechaInicio(formatoTexto.parse(aFinSolDto.getFechaInicioStr()));
			actividadFinancieraSolicitud.setFechaFin(formatoTexto.parse(aFinSolDto.getFechaFinStr()));
			actividadFinancieraSolicitud.setFechaCreacion(formatoTexto.parse(aFinSolDto.getFechaCreacionStr()));
			actividadFinancieraSolicitud.setFechaTransaccion(formatoTexto.parse(aFinSolDto.getFechaTransaccionStr()));
			actividadFinancieraSolicitud.setUsuarioCreacion(aFinSolDto.getUsuarioCreacion());
			actividadFinancieraSolicitud.setUsuarioTransaccion(aFinSolDto.getUsuarioTransaccion());
			actividadFinancieraSolicitud.setSolicitud(solicitud);
			actividadFinancieraSolicitud.setActividadFinanciera(
					iActividadFinanciera.buscarActividadFinanciera(aFinSolDto.getId().getCodigoActividad()));

			iActividadFinancieraSolicitud.crearActividadFinancieraSolicitud(actividadFinancieraSolicitud);
			Logger.getLogger(SincronizarImpl.class.getName()).info("Crear Solicitud ,  crearActividadFinancieraSolicitud , fin");
           }
		}catch(Exception e) {
			Logger.getLogger(SincronizarImpl.class.getName()).info("Crear Solicitud ,  crearActividadFinancieraSolicitud  , Error al crear la actividad financiera  "+ e );		
			throw new Exception("No se puede guardar los datos de crédito  ");
		   }
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void crearDocumentosSolicitud(SolicitudCreaDTO solicitudCreaDTO, Solicitud solicitud)
			throws ParseException, TransactionRolledbackLocalException, NegocioException, Exception{
		Logger.getLogger(SincronizarImpl.class.getName()).info("Crear Solicitud ,  crearDocumentosSolicitud , inicio");
         try {
		for (Documento doc : solicitudCreaDTO.getDocumento()) {

			DocumentoDTO documentoDto = new DocumentoDTO();

			// Inserta Datos del Documento
			documentoDto.setNombreDocumento(doc.getNombreDocumento());

			Date date = new Date();
			documentoDto.setFecha(date);
			documentoDto.setFechaCreacion(date);
			documentoDto.setSolicitud(solicitud);
			documentoDto.setTipoDocumento("P");
			documentoDto.setUsuarioCreacion(solicitudCreaDTO.getListaAFSolicitud().get(0).getUsuarioCreacion());
			documentoDto.setContenidoArchivo(doc.getContenidoArchivo());

			List<String> ruta = new ArrayList<String>();
			ruta.add(UtilConstantes.RUTA_SOPORTES);
			ruta.add("SOL" + solicitud.getCodigoSolicitud() + '_' + documentoDto.getNombreDocumento());
			documentoDto.setPath(iGestionArchivos.guardarArchivoS3(
						new String(UtilFolder.obtenerRutaDirectorio(ruta)), documentoDto.getContenidoArchivo()));
			
			iDocumento.crearDocumento(documentoDto);
			
			Logger.getLogger(SincronizarImpl.class.getName()).info("Crear Solicitud ,  crearDocumentosSolicitud , fin solicitud " + solicitud.getCodigoSolicitud());

				}
         }catch (Exception e) {        	
        	 Logger.getLogger(SincronizarImpl.class.getName()).info("Crear Solicitud ,  crearDocumentosSolicitud  , Error al crear documento de solicitud  " + solicitud.getCodigoSolicitud() + "   " + e );
        	 throw new Exception ("No se puede cargar documento en S3  ");
         }
	}
	
	
	@Override
	public void crearSolicitudRollBack(String codSoliciutd) throws NegocioException , SQLException, ParseException {
		Logger.getLogger(SincronizarImpl.class.getName()).info("Crear Solicitud ,  crearSolicitudRollBack , inicio");
		try {
             
			Solicitud solicitud = iSolicitud.buscarPorId(codSoliciutd);
			
			//Elimina producto relacionado solicitud
			if (solicitud.getProductoRelacionadoSolicituds() != null) {
				for(ProductoRelacionadoSolicitud productoRelacionado : solicitud.getProductoRelacionadoSolicituds()) {
					iProductoRelacionadoSolcitud.eliminarProductoRelacionadoSolicitud(productoRelacionado);
				}
				
			}
			
			//Elimina Documento
			if(solicitud.getDocumentos() != null) {
				
				for (Documento documento : solicitud.getDocumentos() ) {
					iDocumento.eliminarDocumento(documento);				
					}				
			}			
			
			//Elimina actividad finaciera solicitud
			if (solicitud.getActividadFinancieraSolicituds() != null) {
					for (ActividadFinancieraSolicitud actividadFinancieraSolicitud : solicitud.getActividadFinancieraSolicituds() ) {
						iActividadFinancieraSolicitud.eliminarActividadFinancieraSolicitud(actividadFinancieraSolicitud); 					
						}
			}	
			
			//Elimina solicitud beneficirios 
			if (solicitud.getSolicitudBeneficiarios() != null) {
				
				//Elimina Beneficiarios
				
				for (SolicitudBeneficiario solicitudBeneficiario : solicitud.getSolicitudBeneficiarios()) {
						iSolicitudBeneficiario.eliminarSolicitudBeneficiario(solicitudBeneficiario);
					}
				
				//Elimina Medio de Comunicacion
				
				for(SolicitudBeneficiario solicitudbeneficiario : solicitud.getSolicitudBeneficiarios()) {
					   BeneficiarioPK beneficiario = solicitudbeneficiario.getBeneficiario().getId();
					   Beneficiario beneficiarios = iBeneficiario.buscaBeneficiarioPorId(beneficiario);
					     for(MedioComunicacion medioComunicacion : beneficiarios.getMedioComunicacions()) {
					    	 iMedioComunicacion.eliminarMedioComunicacion(medioComunicacion);
					     }						
					}
			}	
			
						
			//Elimina Movimiento Solicitud
			
			if (solicitud.getSolicitudMovimientos() != null) {
				for (SolicitudMovimiento solicitudMovimientos : solicitud.getSolicitudMovimientos()) {					
						iSolicitudMovimiento.eliminarSolicitudMovimiento(solicitudMovimientos);
				}
			}		
			
			
			// Elimina la solicitud !!!!
			if (solicitud.getCodigoSolicitud() != null) {
				iSolicitud.eliminarSolicitud(solicitud);			
					}
		
			Logger.getLogger(SincronizarImpl.class.getName()).info("Crear Solicitud ,  crearSolicitudRollBack , fin");	
		} catch (SQLException e) {
			
			Logger.getLogger(SincronizarImpl.class.getName()).info("Crear Solicitud ,  crearSolicitudRollBack  , Error al realizar rollback de creacion  "+ e );
			e.printStackTrace();
			throw new SQLException("ERROR AL REALIZAR ROLLBACK DE CREACION");
		}

	}

}
