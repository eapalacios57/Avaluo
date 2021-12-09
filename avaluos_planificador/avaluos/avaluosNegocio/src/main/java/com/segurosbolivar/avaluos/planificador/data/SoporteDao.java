package com.segurosbolivar.avaluos.planificador.data;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Soporte;

@Stateless
public class SoporteDao extends ManejadorCrud<Soporte, Long> {

	private static final long serialVersionUID = 7747626177696187846L;
	public static final String SELECT = "SELECT a FROM Soporte a ";

	public SoporteDao() {
		super(Soporte.class);
	}

	public List<Soporte> consultarSoportePorUndProd(Long idUnidadProductiva) {

		Query query = mp.createNamedQuery("Soporte.findByUndProd");
		query.setParameter("idUnidadProductiva", idUnidadProductiva);
		@SuppressWarnings("unchecked")
		List<Soporte> consulta = query.getResultList();		
		
		return consulta;
	}

	public List<Soporte> consultarSoportePorCultivo(BigDecimal idCultivo) {
		Query query = mp.createNamedQuery("Soporte.findByCultivo");
		query.setParameter("idCultivo", idCultivo);
		List<Soporte> consulta = query.getResultList();
		return consulta;
	}

}
