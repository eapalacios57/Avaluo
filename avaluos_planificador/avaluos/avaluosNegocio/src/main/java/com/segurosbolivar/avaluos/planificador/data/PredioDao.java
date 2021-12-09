package com.segurosbolivar.avaluos.planificador.data;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Predio;

@Stateless
public class PredioDao extends ManejadorCrud<Predio, BigDecimal> {

	private static final long serialVersionUID = 7747626177696187846L;

	public PredioDao() {
		super(Predio.class);
	}

	public List<Predio> buscarPredios() {		
		List<Predio> consulta = mp.createNamedQuery("Predio.findAll").getResultList();
    	return consulta;
	}
	
	public List<Predio> buscarPrediosPorUnidadProd(Long idUnidadProd) {
		Query query =  mp.createQuery("SELECT p FROM Predio p WHERE p.unidadProductiva.idUnidadProductiva = :idUnidadProd");
		query.setParameter("idUnidadProd", idUnidadProd);
		List<Predio> predios = query.getResultList();
    	return predios;
	}
	
}
