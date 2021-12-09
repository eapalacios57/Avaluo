package com.segurosbolivar.avaluos.planificador.data;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.mapstruct.factory.Mappers;

import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.dto.PlanificadorDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.SolicitudDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Solicitud;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.SolicitudPlanificadorFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.util.Constantes;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad peritos. Se encarga de las operaciones a
 * base de datos de la tabla de PGB_PERITOS_EMPRESAS a través del uso del
 * entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class SolicitudDao extends ManejadorCrud<Solicitud, String> {

	private static final long serialVersionUID = 7747626177696187846L;

	public SolicitudDao() {
		super(Solicitud.class);
	}

	public List<Solicitud> listarSolicitudes() throws SQLException {
		List<Solicitud> consulta = mp.createNamedQuery("Solicitud.findAll").getResultList();
		return consulta;
	}

	public List<Solicitud> listarSolicitudes(PlanificadorDTO planificadorDTO) throws SQLException {

		List<Solicitud> consulta = mp.createQuery(
				"SELECT t FROM Solicitud AS t WHERE t.planificador.id.numeroDocumentoPlanificador = :numeroDocumentoPlanificador AND t.planificador.id.tipoDocumentoPlanificador = :tipoDocumentoPlanificador ORDER BY t.fechaSolicitud ASC ")
				.setParameter("numeroDocumentoPlanificador", planificadorDTO.getId().getNumeroDocumentoPlanificador())
				.setParameter("tipoDocumentoPlanificador", planificadorDTO.getId().getTipoDocumentoPlanificador())
				.getResultList();

		return consulta;

	}

	public List<Solicitud> listarSolicitudes(String perfil, String usuario, String criterioConsulta)
			throws SQLException {
		List<Solicitud> lista = new ArrayList<>();
		String strQuery = "";

		if (perfil.equals(Constantes.PERFIL_PAGACTUALIZ01)) {

			strQuery = "select s FROM Solicitud s INNER JOIN s.solicitudMovimientos AS sm LEFT JOIN s.solicitudBeneficiarios AS sb LEFT JOIN sb.beneficiario AS b "
					+ "WHERE  sb.contacto = :contacto AND s.planificador.id.numeroDocumentoPlanificador = :cnUsuario "
					+ "AND sm.idSolicitudMovimiento = (select MAX(sm2.idSolicitudMovimiento) FROM s.solicitudMovimientos as sm2 WHERE sm.solicitud = sm2.solicitud)";

			if (criterioConsulta != null && !criterioConsulta.equals("_")) {

				strQuery = strQuery + " AND ( s.codigoSolicitud LIKE :codigoSolicitud "
						+ "OR b.id.numeroDocumentoBeneficiario LIKE :numeroDocumentoBeneficiario "
						+ "OR UPPER(CONCAT(b.primerNombre, ' ',b.primerApellido)) 	LIKE :nombreCompletoBeneficiario "
						+ "OR UPPER(CONCAT(b.primerNombre, ' ',b.segundoApellido)) 	LIKE :nombreCompletoBeneficiario "
						+ "OR UPPER(CONCAT(b.segundoNombre,' ',b.primerApellido)) 	LIKE :nombreCompletoBeneficiario "
						+ "OR UPPER(CONCAT(b.segundoNombre,' ',b.segundoApellido)) 	LIKE :nombreCompletoBeneficiario "
						+ "OR UPPER(CONCAT(b.primerNombre, ' ',b.primerApellido, ' ', b.segundoApellido)) LIKE :nombreCompletoBeneficiario "
						+ "OR UPPER(CONCAT(b.primerNombre, ' ',b.segundoNombre,  ' ', b.primerApellido, ' ', b.segundoApellido)) LIKE :nombreCompletoBeneficiario "
						+ "OR UPPER(sm.estadoMovimiento) 		LIKE :estadoSolicitud "
						+ "OR UPPER(s.fechaSolicitud) 			LIKE :fechaSolicitud "
						+ "OR UPPER(s.municipioDepartamento) 	LIKE :municipioDepartamento "
						+ "OR UPPER(CONCAT(s.planificador.primerNombre, ' ', s.planificador.primerApellido)) 	LIKE :nombreCompletoPlanificador "
						+ "OR UPPER(CONCAT(s.planificador.primerNombre, ' ', s.planificador.segundoApellido)) 	LIKE :nombreCompletoPlanificador "
						+ "OR UPPER(CONCAT(s.planificador.segundoNombre,' ', s.planificador.primerApellido)) 	LIKE :nombreCompletoPlanificador "
						+ "OR UPPER(CONCAT(s.planificador.segundoNombre,' ', s.planificador.segundoApellido)) 	LIKE :nombreCompletoPlanificador "
						+ "OR UPPER(CONCAT(s.planificador.primerNombre, ' ', s.planificador.primerApellido, ' ', s.planificador.segundoApellido)) LIKE :nombreCompletoPlanificador "
						+ "OR UPPER(CONCAT(s.planificador.primerNombre, ' ', s.planificador.segundoNombre, ' ', s.planificador.primerApellido, ' ', s.planificador.segundoApellido)) LIKE :nombreCompletoPlanificador) ";

			}

			strQuery = strQuery + "ORDER BY s.fechaSolicitud DESC, s.codigoSolicitud DESC ";

		} else if (perfil.equals(Constantes.PERFIL_PAGCAPTURA01)) {

			strQuery = "select s FROM Solicitud s INNER JOIN s.solicitudMovimientos AS sm LEFT JOIN s.solicitudBeneficiarios AS sb LEFT JOIN sb.beneficiario AS b "
					+ "WHERE sb.contacto = :contacto AND s.usuarioCreacion = :cnUsuario "
					+ "AND sm.idSolicitudMovimiento = (select MAX(sm2.idSolicitudMovimiento) FROM s.solicitudMovimientos as sm2 WHERE sm.solicitud = sm2.solicitud)";

			if (criterioConsulta != null && !criterioConsulta.equals("_")) {

				strQuery = strQuery + " AND ( s.codigoSolicitud LIKE :codigoSolicitud "
						+ "OR b.id.numeroDocumentoBeneficiario LIKE :numeroDocumentoBeneficiario "
						+ "OR UPPER(CONCAT(b.primerNombre, ' ',b.primerApellido)) 	LIKE :nombreCompletoBeneficiario "
						+ "OR UPPER(CONCAT(b.primerNombre, ' ',b.segundoApellido)) 	LIKE :nombreCompletoBeneficiario "
						+ "OR UPPER(CONCAT(b.segundoNombre,' ',b.primerApellido)) 	LIKE :nombreCompletoBeneficiario "
						+ "OR UPPER(CONCAT(b.segundoNombre,' ',b.segundoApellido)) 	LIKE :nombreCompletoBeneficiario "
						+ "OR UPPER(CONCAT(b.primerNombre, ' ',b.primerApellido, ' ', b.segundoApellido)) LIKE :nombreCompletoBeneficiario "
						+ "OR UPPER(CONCAT(b.primerNombre, ' ',b.segundoNombre,  ' ', b.primerApellido, ' ', b.segundoApellido)) LIKE :nombreCompletoBeneficiario "
						+ "OR UPPER(sm.estadoMovimiento) 		LIKE :estadoSolicitud "
						+ "OR UPPER(s.fechaSolicitud) 			LIKE :fechaSolicitud "
						+ "OR UPPER(s.municipioDepartamento) 	LIKE :municipioDepartamento "
						+ "OR UPPER(CONCAT(s.planificador.primerNombre, ' ', s.planificador.primerApellido)) 	LIKE :nombreCompletoPlanificador "
						+ "OR UPPER(CONCAT(s.planificador.primerNombre, ' ', s.planificador.segundoApellido)) 	LIKE :nombreCompletoPlanificador "
						+ "OR UPPER(CONCAT(s.planificador.segundoNombre,' ', s.planificador.primerApellido)) 	LIKE :nombreCompletoPlanificador "
						+ "OR UPPER(CONCAT(s.planificador.segundoNombre,' ', s.planificador.segundoApellido)) 	LIKE :nombreCompletoPlanificador "
						+ "OR UPPER(CONCAT(s.planificador.primerNombre, ' ', s.planificador.primerApellido, ' ', s.planificador.segundoApellido)) LIKE :nombreCompletoPlanificador "
						+ "OR UPPER(CONCAT(s.planificador.primerNombre, ' ', s.planificador.segundoNombre, ' ', s.planificador.primerApellido, ' ', s.planificador.segundoApellido)) LIKE :nombreCompletoPlanificador) ";

			}
			strQuery = strQuery + "ORDER BY s.fechaSolicitud DESC, s.codigoSolicitud DESC ";
		} else if (perfil.equals(Constantes.PERFIL_PAGGESTION01)) {

			strQuery = "select s FROM Solicitud s INNER JOIN s.solicitudMovimientos AS sm LEFT JOIN s.solicitudBeneficiarios AS sb LEFT JOIN sb.beneficiario AS b"
					+ " WHERE sb.contacto = :contacto "
					+ "AND sm.idSolicitudMovimiento = (select MAX(sm2.idSolicitudMovimiento) FROM s.solicitudMovimientos as sm2 WHERE sm.solicitud = sm2.solicitud)";

			if (criterioConsulta != null && !criterioConsulta.equals("_")) {

				strQuery = strQuery + " " + "AND (b.id.numeroDocumentoBeneficiario LIKE :numeroDocumentoBeneficiario "
						+ "OR s.codigoSolicitud 		LIKE :codigoSolicitud "
						+ "OR UPPER(CONCAT(b.primerNombre, ' ',b.primerApellido)) 	LIKE :nombreCompletoBeneficiario "
						+ "OR UPPER(CONCAT(b.primerNombre, ' ',b.segundoApellido)) 	LIKE :nombreCompletoBeneficiario "
						+ "OR UPPER(CONCAT(b.segundoNombre,' ',b.primerApellido)) 	LIKE :nombreCompletoBeneficiario "
						+ "OR UPPER(CONCAT(b.segundoNombre,' ',b.segundoApellido)) 	LIKE :nombreCompletoBeneficiario "
						+ "OR UPPER(CONCAT(b.primerNombre, ' ',b.primerApellido, ' ', b.segundoApellido)) LIKE :nombreCompletoBeneficiario "
						+ "OR UPPER(CONCAT(b.primerNombre, ' ',b.segundoNombre,  ' ', b.primerApellido, ' ', b.segundoApellido)) LIKE :nombreCompletoBeneficiario "
						+ "OR UPPER(sm.estadoMovimiento) 		LIKE :estadoSolicitud "
						+ "OR UPPER(s.fechaSolicitud) 			LIKE :fechaSolicitud "
						+ "OR UPPER(s.municipioDepartamento) 	LIKE :municipioDepartamento "
						+ "OR UPPER(CONCAT(s.planificador.primerNombre, ' ', s.planificador.primerApellido)) 	LIKE :nombreCompletoPlanificador "
						+ "OR UPPER(CONCAT(s.planificador.primerNombre, ' ', s.planificador.segundoApellido)) 	LIKE :nombreCompletoPlanificador "
						+ "OR UPPER(CONCAT(s.planificador.segundoNombre,' ', s.planificador.primerApellido)) 	LIKE :nombreCompletoPlanificador "
						+ "OR UPPER(CONCAT(s.planificador.segundoNombre,' ', s.planificador.segundoApellido)) 	LIKE :nombreCompletoPlanificador "
						+ "OR UPPER(CONCAT(s.planificador.primerNombre, ' ', s.planificador.primerApellido, ' ', s.planificador.segundoApellido)) LIKE :nombreCompletoPlanificador "
						+ "OR UPPER(CONCAT(s.planificador.primerNombre, ' ', s.planificador.segundoNombre, ' ', s.planificador.primerApellido, ' ', s.planificador.segundoApellido)) LIKE :nombreCompletoPlanificador) ";

			}

			strQuery = strQuery + "ORDER BY s.fechaSolicitud DESC, s.codigoSolicitud DESC ";
		}

		Query query = mp.createQuery(strQuery);

		if (criterioConsulta != null && !criterioConsulta.equals("_")) {

			criterioConsulta = "%" + criterioConsulta.trim() + "%";

			query.setParameter("codigoSolicitud", criterioConsulta);
			query.setParameter("numeroDocumentoBeneficiario", criterioConsulta);
			query.setParameter("nombreCompletoBeneficiario", criterioConsulta);
			query.setParameter("estadoSolicitud", criterioConsulta);
			query.setParameter("fechaSolicitud", criterioConsulta);
			query.setParameter("municipioDepartamento", criterioConsulta);
			query.setParameter("nombreCompletoPlanificador", criterioConsulta);
		}

		if (perfil.equals(Constantes.PERFIL_PAGACTUALIZ01) || perfil.equals(Constantes.PERFIL_PAGCAPTURA01)) {
			query.setParameter("cnUsuario", usuario);
		}

		query.setParameter("contacto", "S");
		System.out.println("Consulta -------------->");
//		System.out.println("strQuery -------------->" + strQuery);
		System.out.println("Parametro: " + criterioConsulta);

		lista = query.getResultList();

		return lista;
	}

	public List<Solicitud> listarSolicitudesPlanificador(String numeroDocumentoPlanificador) throws SQLException {

		Query query = mp.createQuery(
				"SELECT t FROM Solicitud AS t WHERE t.planificador.id.numeroDocumentoPlanificador = :numeroDocumentoPlanificador ORDER BY t.fechaSolicitud ASC ");
		query.setParameter("numeroDocumentoPlanificador", numeroDocumentoPlanificador);

		List<Solicitud> consulta = query.getResultList();

		return consulta;

	}

	public Solicitud crear(SolicitudDTO solicitudDTO) {
		SimpleDateFormat formatoTexto = new SimpleDateFormat("dd-MM-yyyy");

		try {
			if (solicitudDTO.getFechaCreacionStr() != null) {
				solicitudDTO.setFechaCreacion(formatoTexto.parse(solicitudDTO.getFechaCreacionStr()));
			}
			if (solicitudDTO.getFechaTransaccionStr() != null)
				solicitudDTO.setFechaTransaccion(formatoTexto.parse(solicitudDTO.getFechaTransaccionStr()));
			if (solicitudDTO.getFechaSolicitudStr() != null) {
				solicitudDTO.setFechaSolicitud(formatoTexto.parse(solicitudDTO.getFechaSolicitudStr()));
			}
			if (solicitudDTO.getFechaVisitaPredioStr() != null)
				solicitudDTO.setFechaVisitaPredio(formatoTexto.parse(solicitudDTO.getFechaVisitaPredioStr()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		SolicitudPlanificadorFullDTOMapper solicitudFullDTOMapper = Mappers
				.getMapper(SolicitudPlanificadorFullDTOMapper.class);
		Solicitud solicitud = solicitudFullDTOMapper.dto2entity(solicitudDTO);

		mp.create(solicitud);
		solicitudDTO = solicitudFullDTOMapper.entity2dto(solicitud);

		return solicitud;
	}

	public Solicitud getSolicitudById(String codSolicitud) {

		Solicitud solicitud = (Solicitud) mp.createQuery(
				"SELECT t FROM Solicitud AS t WHERE t.codigoSolicitud =:codigoSolicitud ORDER BY t.fechaSolicitud ASC ")
				.setParameter("codigoSolicitud", codSolicitud).getSingleResult();

		return solicitud;
	}

	public Solicitud actualziar(Solicitud solicitud) {

		mp.update(solicitud);

		return solicitud;
	}

	public java.util.Date ultimaSolicitud(String numeroDocumentoBeneficiario) {
		java.util.Date solicitud = (java.util.Date) mp.createQuery(
				"SELECT max(s.fechaCreacion) FROM Solicitud s INNER JOIN s.solicitudBeneficiarios AS sb  where sb.beneficiario.id.numeroDocumentoBeneficiario = :NUMERO_DOCUMENTO_BENEFICIARIO ")
				.setParameter("NUMERO_DOCUMENTO_BENEFICIARIO", numeroDocumentoBeneficiario).getSingleResult();
		return solicitud;
	}

}
