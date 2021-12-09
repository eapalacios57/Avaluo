package com.segurosbolivar.avaluos.planificador.data;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ValorPorcentaje;

@Stateless
public class ValorPorcentajeDao extends ManejadorCrud<ValorPorcentaje, Long> {

	private static final long serialVersionUID = 7747626177696187846L;

	public ValorPorcentajeDao() {
		super(ValorPorcentaje.class);
	}
	
	public void eliminarValoresPorcentajePorUnidad(Long idUnidad) {
		Query query = mp.createQuery("DELETE FROM ValorPorcentaje v WHERE v.unidadProductiva.idUnidadProductiva = :idUnidad");
		query.setParameter("idUnidad", idUnidad);
		query.executeUpdate();
	}

	
}
