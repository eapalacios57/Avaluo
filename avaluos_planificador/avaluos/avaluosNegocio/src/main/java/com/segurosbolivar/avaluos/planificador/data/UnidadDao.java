package com.segurosbolivar.avaluos.planificador.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Unidad;

@Stateless
public class UnidadDao extends ManejadorCrud<Unidad, Long> {

	private static final long serialVersionUID = 7747626177696187846L;
	public static final String SELECT = "SELECT a FROM Unidad as a ";

	public UnidadDao() {
		super(Unidad.class);
	}

	public List<Unidad> getUnidades() {

		@SuppressWarnings("unchecked")
		List<Unidad> listUnidad = mp.createQuery(SELECT).setHint(QueryHints.REFRESH, HintValues.TRUE).getResultList();

		return listUnidad;
	}

	public Unidad getUnidad(String strUnidad) {

		Unidad unidad = null;
		try {
			unidad = (Unidad) mp.createQuery("SELECT t FROM Unidad AS t WHERE t.nombre = :nombre")
					.setParameter("nombre", strUnidad).getSingleResult();
		} catch (NoResultException e) {
			unidad = (Unidad) mp.createQuery("SELECT t FROM Unidad AS t").getResultList().get(0);
		}

		return unidad;
	}

}
