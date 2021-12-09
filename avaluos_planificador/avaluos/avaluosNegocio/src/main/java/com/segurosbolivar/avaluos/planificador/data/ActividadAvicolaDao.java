package com.segurosbolivar.avaluos.planificador.data;

import java.util.List;

import javax.ejb.Stateless;

import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadAvicolaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadAvicola;

@Stateless
public class ActividadAvicolaDao extends ManejadorCrud<ActividadAvicola, Long> {

	private static final long serialVersionUID = 7747626177696187846L;

	public ActividadAvicolaDao() {
		super(ActividadAvicola.class);
	}

	public ActividadAvicola getActividadAvicola(ActividadAvicolaDTO actividadAvicolaDTO) {

		@SuppressWarnings("unchecked")
		List<ActividadAvicola> listActividadAvicola = mp.createQuery(
				"SELECT t from ActividadAvicola as t WHERE t.unidadProductiva.idUnidadProductiva = :idUnidadProductiva")
				.setParameter("idUnidadProductiva", actividadAvicolaDTO.getIdUnidadProductiva()).getResultList();

		if (listActividadAvicola != null && listActividadAvicola.size() != 0) {

			ActividadAvicola actividadAvicola = listActividadAvicola.get(0);

			return actividadAvicola;

		} else {
			return null;
		}

	}
	
	

}
