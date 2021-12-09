package com.segurosbolivar.avaluos.planificador.data;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.mapstruct.factory.Mappers;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.dto.MedioComunicacionDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Beneficiario;
import com.segurosbolivar.avaluos.planificador.modelo.entity.BeneficiarioPK;
import com.segurosbolivar.avaluos.planificador.modelo.entity.MedioComunicacion;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.MedioComunicacionFullDTOMapper;

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
public class MedioComunicacionDao extends ManejadorCrud<MedioComunicacion, Long> {

	private static final long serialVersionUID = 7747626177696187846L;

	public MedioComunicacionDao() {
		super(MedioComunicacion.class);
	}

	public List<MedioComunicacion> listarMedioComunicacion() throws SQLException {
		List<MedioComunicacion> consulta = mp.createNamedQuery("MedioComunicacion.findAll").getResultList();
		return consulta;
	}

	public void crear(MedioComunicacion medioComunicacion) throws NegocioException {
		
		mp.create(medioComunicacion);
	}

	public void actualzarMedioComunicacionBeneficiario(MedioComunicacionDTO comunicacionDTO) throws SQLException, NoResultException {

		// Consultar los medios de comunicacion del beneficiario
		List<MedioComunicacion> listMedioComunicacion = mp.createQuery(
				"SELECT t from MedioComunicacion as t WHERE t.tipoMedioComunicacion = :tipoMedioComunicacion AND  t.beneficiario.id.numeroDocumentoBeneficiario = :numeroDocumentoBeneficiario AND t.beneficiario.id.tipoDocumentoBeneficiario = :tipoDocumentoBeneficiario ")
				.setParameter("numeroDocumentoBeneficiario", comunicacionDTO.getNumeroDocumentoBeneficiario())
				.setParameter("tipoDocumentoBeneficiario", comunicacionDTO.getTipoDocumentoBeneficiario())
				.setParameter("tipoMedioComunicacion", comunicacionDTO.getTipoMedioComunicacion()).getResultList();

		// Actuaizar los medios de cominizacion como no principales

		for (MedioComunicacion comunicacion : listMedioComunicacion) {

			comunicacion.setPrincipal("N");
			comunicacion.setFechaTransaccion(new Date());
			mp.update(comunicacion);
		}

		// Consultar si existe el medio de comunicacion

		MedioComunicacion medioComunicacion = null;
		
		Query query = mp.createQuery("SELECT t from MedioComunicacion as t WHERE t.descripcion = :descripcion AND t.tipoMedioComunicacion = :tipoMedioComunicacion AND  t.beneficiario.id.numeroDocumentoBeneficiario = :numeroDocumentoBeneficiario AND t.beneficiario.id.tipoDocumentoBeneficiario = :tipoDocumentoBeneficiario ");
		
		query.setParameter("numeroDocumentoBeneficiario", comunicacionDTO.getNumeroDocumentoBeneficiario());
		query.setParameter("tipoDocumentoBeneficiario", comunicacionDTO.getTipoDocumentoBeneficiario());
		query.setParameter("tipoMedioComunicacion", comunicacionDTO.getTipoMedioComunicacion());
		query.setParameter("descripcion", comunicacionDTO.getDescripcion());
		
		if(query.getResultList().size()>0) {
    		medioComunicacion = (MedioComunicacion) query.getResultList().get(0);    		
    	}

		if (medioComunicacion != null) {

			medioComunicacion.setPrincipal("S");
			mp.update(medioComunicacion);

		} else {
			
			medioComunicacion = new MedioComunicacion();
			
			BeneficiarioPK beneficiarioPK = new BeneficiarioPK();
			beneficiarioPK .setNumeroDocumentoBeneficiario(comunicacionDTO.getNumeroDocumentoBeneficiario());
			beneficiarioPK .setTipoDocumentoBeneficiario(comunicacionDTO.getTipoDocumentoBeneficiario());
			Beneficiario beneficiario = new Beneficiario();
			beneficiario .setId(beneficiarioPK);
					
			medioComunicacion.setBeneficiario(beneficiario);
			medioComunicacion.setTipoMedioComunicacion(comunicacionDTO.getTipoMedioComunicacion());
			medioComunicacion.setPrincipal(comunicacionDTO.getPrincipal());
			medioComunicacion.setEstado(comunicacionDTO.getEstado());
			medioComunicacion.setValor(comunicacionDTO.getValor());
			medioComunicacion.setDescripcion(comunicacionDTO.getDescripcion());
			medioComunicacion.setUsuarioCreacion(comunicacionDTO.getUsuarioCreacion());
			medioComunicacion.setFechaCreacion(new Date());
			
			mp.create(medioComunicacion);
		}

	}
	
	public MedioComunicacionDTO consultaPorBeneficiario(BeneficiarioPK beneficiarioPK) throws SQLException {
		
		MedioComunicacion medioComunicacion = new MedioComunicacion();
		MedioComunicacionDTO medioComunicacionDTO = new MedioComunicacionDTO();
		MedioComunicacionFullDTOMapper medioComunicacionMapper = Mappers.getMapper(MedioComunicacionFullDTOMapper.class);
				
		Query query =  mp.createNamedQuery("MedioComunicacion.findByBen");
		query.setParameter("tipoDocumentoBeneficairio",beneficiarioPK.getTipoDocumentoBeneficiario());
		query.setParameter("numeroDocumentoBeneficiario", beneficiarioPK.getNumeroDocumentoBeneficiario());
		medioComunicacion = (MedioComunicacion) query.getSingleResult();
		
		medioComunicacionDTO = medioComunicacionMapper.entity2dto(medioComunicacion);
		
		return medioComunicacionDTO;
	}

}
