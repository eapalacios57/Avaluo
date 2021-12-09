package com.segurosbolivar.avaluos.planificador.data;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;


import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.SolicitudMovimiento;

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
public class SolicitudMovimientoDao extends ManejadorCrud<SolicitudMovimiento, Long> {

	private static final long serialVersionUID = 7747626177696187846L;

	public SolicitudMovimientoDao() {
		super(SolicitudMovimiento.class);
	}

	public List<SolicitudMovimiento> listarSolicitudMovimientos() throws SQLException {

		List<SolicitudMovimiento> consulta = mp.createNamedQuery("SolicitudMovimiento.findAll").getResultList();

		return consulta;
	}

	public void crearSolMov(SolicitudMovimiento solicitudMovimiento) {

		mp.create(solicitudMovimiento);

	}
	
	public SolicitudMovimiento consultarSolicitudMovimiento(String codSolicitud, String estado) {
		
		
		List<SolicitudMovimiento> consulta = mp.createQuery("SELECT b FROM SolicitudMovimiento b WHERE b.estadoMovimiento = :estado AND b.solicitud.codigoSolicitud = :codSolicitud")
				.setParameter("estado", estado)
				.setParameter("codSolicitud", codSolicitud)
				.getResultList();
		
		return consulta.size() == 0 ? null :  consulta.get(0);
		
	}

}
