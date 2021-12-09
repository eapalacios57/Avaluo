package com.segurosbolivar.avaluos.planificador.data;

import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.UnidadProductiva;

@Stateless
public class UnidadProductivaDao extends ManejadorCrud<UnidadProductiva, Long> {

	private static final long serialVersionUID = 7747626177696187846L;
	public static final String SELECT = "SELECT a FROM UnidadProductiva a ";

	public UnidadProductivaDao() {
		super(UnidadProductiva.class);
	}

	public UnidadProductiva getUnidadProductiva(Long idUnidadProductiva, String codigoSolicitud) throws SQLException {

		Query consulta = mp.createQuery(
				"SELECT a FROM UnidadProductiva a where a.idUnidadProductiva=:idUnidadProductiva and a.solicitud.codigoSolicitud=:idSolicitud");
		consulta.setParameter("idUnidadProductiva", idUnidadProductiva);
		consulta.setParameter("idSolicitud", codigoSolicitud);
		return (UnidadProductiva) consulta.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<UnidadProductiva> listarUnidadesProductivas(String codigoSolicitud) throws SQLException {

		Query consulta = mp.createQuery(
				"SELECT a FROM UnidadProductiva a where a.solicitud.codigoSolicitud=:idSolicitud");
				
		consulta.setParameter("idSolicitud", codigoSolicitud);
		return consulta.getResultList();
	}
	
	public UnidadProductiva crearUnidadProductiva(UnidadProductiva unidadProductiva) throws SQLException,javax.ejb.TransactionRolledbackLocalException {

		mp.create(unidadProductiva);
		return unidadProductiva;
	}
	
}
