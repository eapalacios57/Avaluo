package com.segurosbolivar.avaluos.planificador.data;

import java.util.List;

import javax.ejb.Stateless;

import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadGanaderaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadGanadera;

@Stateless
public class ActividadGanaderaDao extends ManejadorCrud<ActividadGanadera, Long> {

	private static final long serialVersionUID = 7747626177696187846L;

	public ActividadGanaderaDao() {
		super(ActividadGanadera.class);
	}

	public ActividadGanadera getActividadGanadera(ActividadGanaderaDTO actividadGanaderaDTO) {

		@SuppressWarnings("unchecked")
		List<ActividadGanadera> listActividadGanadera = mp.createQuery(
				"SELECT t from ActividadGanadera as t WHERE t.unidadProductiva.idUnidadProductiva = :idUnidadProductiva")
				.setParameter("idUnidadProductiva", actividadGanaderaDTO.getIdUnidadProductiva()).getResultList();

		if (listActividadGanadera != null && listActividadGanadera.size() != 0) {

			ActividadGanadera actividadGanadera = listActividadGanadera.get(0);

			return actividadGanadera;

		} else {
			return null;
		}

	}

}
