package com.segurosbolivar.avaluos.planificador.data;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;

import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.dto.CultivoDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Cultivo;

@Stateless
public class CultivoDao extends ManejadorCrud<Cultivo, BigDecimal> {

	private static final long serialVersionUID = 7747626177696187846L;

	public CultivoDao() {
		super(Cultivo.class);
	}
	
	public List<Cultivo> getCultivos(CultivoDTO consultar)
			throws SQLException {
		
		List<Cultivo> listCultivos = mp.createQuery("Select t from Cultivo as t WHERE t.unidadProductiva.idUnidadProductiva = :idUnidadProductiva").setParameter("idUnidadProductiva", consultar.getIdUnidadProductiva()).getResultList();
		
		return listCultivos;
	}

}
