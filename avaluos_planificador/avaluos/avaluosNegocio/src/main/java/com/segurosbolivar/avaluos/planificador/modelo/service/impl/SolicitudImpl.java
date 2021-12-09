package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.transaction.UserTransaction;

import org.apache.log4j.Logger;
import org.apache.poi.util.IOUtils;
import org.mapstruct.factory.Mappers;

import com.asesoftware.util.archivo.UtilArchivos;
import com.asesoftware.util.archivo.UtilPropiedades;
import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.cons.EstadoRegistro;
import com.segurosbolivar.avaluos.modelo.data.AnexoFotograficoDao;
import com.segurosbolivar.avaluos.modelo.data.ArchivoDao;
import com.segurosbolivar.avaluos.modelo.data.ListaAnexosPdfDao;
import com.segurosbolivar.avaluos.modelo.data.ParametrizacionDao;
import com.segurosbolivar.avaluos.modelo.dto.ArchivosFileNetDto;
//import com.segurosbolivar.avaluos.modelo.dto.ConsultaFileNetDto;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.Archivo;
import com.segurosbolivar.avaluos.modelo.entity.IPersona;
import com.segurosbolivar.avaluos.modelo.entity.ListaAnexosPdf;
import com.segurosbolivar.avaluos.modelo.facade.IIntegradorFacade;
import com.segurosbolivar.avaluos.modelo.service.IArchivo;
import com.segurosbolivar.avaluos.modelo.service.util.InputStreamDataSource;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.consultar.ConsultarDocumentoResponse;
import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.consultar.Propiedad;
import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.consultaringresar.DocumentoResponse;
import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.consultaringresar.IngresaConsultaDocumentoResponse;
import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.ingresar.Documento;
import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.ingresar.IngresarDocumentoResponse;
import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.ingresar.Propiedades;
import com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.obtener.ObtenerDocumentoResponse;
import com.segurosbolivar.avaluos.planificador.data.BeneficiarioDao;
import com.segurosbolivar.avaluos.planificador.data.PlanificadorDao;
import com.segurosbolivar.avaluos.planificador.data.SolicitudDao;
import com.segurosbolivar.avaluos.planificador.modelo.dto.PlanificadorDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudDetalleDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadFinancieraSolicitud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Beneficiario;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Cultivo;
import com.segurosbolivar.avaluos.planificador.modelo.entity.MedioComunicacion;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Planificador;
import com.segurosbolivar.avaluos.planificador.modelo.entity.PlanificadorPK;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ProductoRelacionado;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ProductoRelacionadoSolicitud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Solicitud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.SolicitudBeneficiario;
import com.segurosbolivar.avaluos.planificador.modelo.entity.SolicitudMovimiento;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Unidad;
import com.segurosbolivar.avaluos.planificador.modelo.entity.UnidadProductiva;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.SolicitudPlanificadorFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.services.ISolicitud;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.solicitud.ActividadFinancieraModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.solicitud.DocumentosModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.solicitud.PlanificadorModel;
import com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.solicitud.SolicitudAsignadaModel;

/**
 * Implementaci�n del servicio de archivo
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:38 a.m.
 */
@Stateless
@Local(ISolicitud.class)
public class SolicitudImpl implements ISolicitud {

	private static final long serialVersionUID = -2088678805488718651L;

	@EJB
	private SolicitudDao solicitudDao;

	@EJB
	private PlanificadorDao planificadorDao;

	SolicitudPlanificadorFullDTOMapper solicitudFullDTOMapper = Mappers
			.getMapper(SolicitudPlanificadorFullDTOMapper.class);

	@Override
	public List<Solicitud> listaSolicitudes() throws SQLException {
		List<Solicitud> lista = new ArrayList<>();
		lista = solicitudDao.listarSolicitudes();

		return lista;
	}
	
	@Override
	public void eliminarSolicitud(Solicitud solicitud) throws ParseException, NegocioException, SQLException  {
		solicitudDao.eliminar(solicitud);
	}
	

	@Override
	public void crearSolicitud(Solicitud solicitud) throws ParseException, NegocioException, SQLException  {
		solicitudDao.crear(solicitud);
	}

	@Override
	public List<Solicitud> listaSolicitudes(PlanificadorDTO planificadorDTO) throws SQLException {
		return solicitudDao.listarSolicitudes(planificadorDTO);
	}

	@Override
	public List<Solicitud> listaSolicitudes(String perfil, String usuario, String criterioSolicitud)
			throws SQLException {
		criterioSolicitud = validarCriterio(criterioSolicitud);
		return solicitudDao.listarSolicitudes(perfil, usuario, criterioSolicitud);
	}

	public String validarCriterio(String criterioConsulta) {
		final String patternASG = "(?i)^(as).[g].*";
		final String patternSIN = "(?i)^(si).[c].*";
		final String patternFIN = "(?i)^(fi).(al).*";

		if (criterioConsulta.matches(patternASG)) {
			criterioConsulta = "ASG";
		} else if (criterioConsulta.matches(patternSIN)) {
			criterioConsulta = "SIN";
		} else if (criterioConsulta.matches(patternFIN)) {
			criterioConsulta = "FIN";
		}

//		Validaciones para formato de fecha
		final String patternDay = "\\d{2}-\\d*";
		final String patternMonth = "\\d{2}-\\d{2}-\\d*";
		final String patternDate = "\\d{2}-\\d{2}-\\d{2}";

		if (criterioConsulta.matches(patternDate)) {
			criterioConsulta = criterioConsulta.substring(0, 2) + '/' + criterioConsulta.substring(3, 5) + '/'
					+ criterioConsulta.substring(6);
		}
		if (criterioConsulta.matches(patternMonth)) {
			criterioConsulta = criterioConsulta.substring(0, 2) + '/' + criterioConsulta.substring(3, 5) + '/'
					+ criterioConsulta.substring(6);
		}
		if (criterioConsulta.matches(patternDay)) {
			criterioConsulta = criterioConsulta.substring(0, 2) + '/' + criterioConsulta.substring(3);
		}

		return criterioConsulta;
	}

	@Override
	public Solicitud getSolicitud(String codSolicitud) throws SQLException {

		Solicitud solicitud = solicitudDao.getSolicitudById(codSolicitud);

		Solicitud solicitudDto = new Solicitud();

		solicitudDto.setComentariosAnexos(solicitud.getComentariosAnexos());
		solicitudDto.setPlanificador(solicitud.getPlanificador());
		solicitudDto.getPlanificador().setSolicituds(null);
		solicitudDto.getPlanificador().setMedioComunicacions(null);

		List<ProductoRelacionadoSolicitud> listProductoRelacionadoSolicitud = new ArrayList<>();

		if (solicitud.getProductoRelacionadoSolicituds() != null
				&& solicitud.getProductoRelacionadoSolicituds().size() > 0) {

			ProductoRelacionado productoRelacionado = new ProductoRelacionado();

			if (solicitud.getProductoRelacionadoSolicituds().get(0) != null) {

				productoRelacionado.setNombre(
						solicitud.getProductoRelacionadoSolicituds().get(0).getProductoRelacionado().getNombre());
				productoRelacionado.setCodigo(
						solicitud.getProductoRelacionadoSolicituds().get(0).getProductoRelacionado().getCodigo());

				ProductoRelacionadoSolicitud productoRelacionadoSolicitud = new ProductoRelacionadoSolicitud();
				productoRelacionadoSolicitud.setProductoRelacionado(productoRelacionado);

				listProductoRelacionadoSolicitud.add(productoRelacionadoSolicitud);
			}
		}
		solicitudDto.setProductoRelacionadoSolicituds(listProductoRelacionadoSolicitud);

		solicitudDto.setUnidadProductivas(new ArrayList<UnidadProductiva>());

		for (UnidadProductiva unidadProductiva : solicitud.getUnidadProductivas()) {

			UnidadProductiva unidadProductivaTem = new UnidadProductiva();

			unidadProductivaTem.setCultivos(new ArrayList<Cultivo>());

			for (Cultivo cultivo : unidadProductiva.getCultivos()) {

				Cultivo cultivoTem = new Cultivo();

				cultivoTem.setComentario(cultivo.getComentario());

				unidadProductivaTem.getCultivos().add(cultivoTem);
			}

			solicitudDto.getUnidadProductivas().add(unidadProductivaTem);

		}

		// SolicitudDetalleDTO solicitudDetDTO=new SolicitudDetalleDTO();

		// solicitudDetDTO.setSolicitud(solicitudDto);
		// solicitudDetDTO.setSolBeneficiario(solicitud.getSolicitudBeneficiarios().get(0));
		// solicitudDetDTO.setActFinSolicitud(solicitud.getActividadFinancieraSolicituds());
		// solicitudDetDTO.setActFinSolicitud(solicitud.getActividadFinancieraSolicituds());
		// solicitudDetDTO.setUnidadProductiva(solicitud.getUnidadProductivas());

		return solicitudDto;
	}

	@Override
	public Solicitud actualziarSolicitud(SolicitudDTO solicitudDto) throws SQLException {

		SolicitudPlanificadorFullDTOMapper solicitudFullDTOMapper = Mappers
				.getMapper(SolicitudPlanificadorFullDTOMapper.class);
		Solicitud solicitud = solicitudFullDTOMapper.dto2entity(solicitudDto);
		solicitud = solicitudDao.actualziar(solicitud);

		return solicitud;
	}

	@Override
	public List<Solicitud> listarSolicitudesPlanificador(String numeroDocumentoPlanificador) throws SQLException {

		return solicitudDao.listarSolicitudesPlanificador(numeroDocumentoPlanificador);

	}

	@Override
	public Solicitud buscarPorId(String codigoSolicitud) throws SQLException {

		return solicitudDao.buscar(codigoSolicitud);

	}

	@Override
	public List<SolicitudAsignadaModel> consultarSolicitud(PlanificadorModel planificadorModel)
			throws SQLException, NegocioException {

		List<SolicitudAsignadaModel> listSolicitudModel = new ArrayList();

		PlanificadorDTO planificadorDTO = new PlanificadorDTO();

		PlanificadorPK pk = new PlanificadorPK();
		pk.setNumeroDocumentoPlanificador(planificadorModel.getNumDocIdentificacion());
		pk.setTipoDocumentoPlanificador(planificadorModel.getTipoDocIdentificacion());
		planificadorDTO.setId(pk);

		Planificador planificador = planificadorDao.buscarPorNumeroDoc(planificadorModel.getNumDocIdentificacion());
		planificador.setDispositivo(planificadorModel.getDispositivo());
		planificador.setTokenDispositivo(planificadorModel.getTokenDispositivo());

		planificadorDao.actualizar(planificador);

		List<Solicitud> listSolicitud = listaSolicitudes(planificadorDTO);

		// validar si la solicitud ya esta finalizada
		for (Solicitud solicitud : listSolicitud) {

			boolean sincronizada = false;

			for (SolicitudMovimiento solicitudMovimiento : solicitud.getSolicitudMovimientos()) {

				if (solicitudMovimiento.getEstadoMovimiento().equals("SIN")) {
					sincronizada = true;
					break;
				}

			}

			if (!sincronizada) {

				SolicitudAsignadaModel solicitudModel = new SolicitudAsignadaModel();
				solicitudModel.setCodigoSolicitud(solicitud.getCodigoSolicitud());

				solicitudModel.setComentariosAnexos(solicitud.getComentariosAnexos());

				for (SolicitudBeneficiario soBeneficiario : solicitud.getSolicitudBeneficiarios()) {

					// Consultar el beneficiario de contacto
					if (soBeneficiario.getContacto().equals("S")) {

						solicitudModel.setTipoDocumentoBeneficiario(
								soBeneficiario.getBeneficiario().getId().getTipoDocumentoBeneficiario());
						solicitudModel.setNumDocumentoBeneficiario(
								soBeneficiario.getBeneficiario().getId().getNumeroDocumentoBeneficiario());
						solicitudModel.setNombreBeneficiario(soBeneficiario.getBeneficiario().getPrimerNombre() + " "
								+ soBeneficiario.getBeneficiario().getPrimerApellido());

						// consultar el medio de comunicacion del beneficiario
						for (MedioComunicacion comunicacion : soBeneficiario.getBeneficiario()
								.getMedioComunicacions()) {

							if (comunicacion.getTipoMedioComunicacion().equals("2")
									&& comunicacion.getPrincipal().equals("S")) {
								solicitudModel.setTelefonoBeneficiario(comunicacion.getValor());
							}

						}

						break;
					}

				}

				if (solicitud.getFechaSolicitud() != null) {

					solicitudModel.setFechaSolicitud(
							new SimpleDateFormat("dd-MM-yyyy").format(solicitud.getFechaSolicitud()));
				}

				solicitudModel.setMunicipioDepartamento(solicitud.getMunicipioDepartamento());
				solicitudModel.setNombreAsesorComercial(solicitud.getNombreAsesorComercial());
				solicitudModel.setTelefonoAsesorComercial(solicitud.getTelefonoAsesorComercial());

				List<ActividadFinancieraModel> listActividadFinancieraModel = new ArrayList<>();

				for (ActividadFinancieraSolicitud actividadFinancieraSolicitud : solicitud
						.getActividadFinancieraSolicituds()) {

					ActividadFinancieraModel actividadFinancieraModel = new ActividadFinancieraModel();

					actividadFinancieraModel.setCodActividadFinanciera(
							actividadFinancieraSolicitud.getActividadFinanciera().getCodigoActividad());
					actividadFinancieraModel.setNombreActividadFinanciera(
							actividadFinancieraSolicitud.getActividadFinanciera().getNombre());

					solicitudModel.setValorTotalProyecto(solicitudModel.getValorTotalProyecto()
							+ actividadFinancieraSolicitud.getValorProyecto().doubleValue());
					solicitudModel.setValorTotalCredito(solicitudModel.getValorTotalCredito()
							+ actividadFinancieraSolicitud.getValorCredito().doubleValue());

					listActividadFinancieraModel.add(actividadFinancieraModel);

				}

				solicitudModel.setListActividadFinancieraModel(listActividadFinancieraModel);

				List<DocumentosModel> listDocumentosModel = new ArrayList<>();

				for (com.segurosbolivar.avaluos.planificador.modelo.entity.Documento doc : solicitud.getDocumentos()) {

					DocumentosModel docModel = new DocumentosModel();

					docModel.setIdDocumento((int) doc.getIdDocumento());
					docModel.setNombreDocumento(doc.getNombreDocumento());
					docModel.setPathFileNet(doc.getPath());

					listDocumentosModel.add(docModel);

				}

				solicitudModel.setListDocumentosModel(listDocumentosModel);

				listSolicitudModel.add(solicitudModel);
			}
		}

		return listSolicitudModel;

	}

	@Override
	public Date ultimaSolicitud(String numeroDocumentoBeneficiario) throws NegocioException {
		return solicitudDao.ultimaSolicitud(numeroDocumentoBeneficiario);
	}

}