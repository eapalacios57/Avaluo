package com.segurosbolivar.avaluos.planificador.data;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.entity.Ciudad;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;

@Stateless
public class CiudadPlanificadorDao extends ManejadorCrud<Ciudad, Long> {

	private static final long serialVersionUID = 7747626177696187846L;

	public CiudadPlanificadorDao() {
		super(Ciudad.class);
	}

	public List<Ciudad> getCiudades() {

		@SuppressWarnings("unchecked")
		List<Ciudad> consulta = mp.createNamedQuery("Ciudad.findAll").getResultList();

		return consulta;
	}

	public Ciudad getCiudad(String strCiudad, String strDepartamento) throws NoResultException {

		Ciudad ciudad = null;

		Query query = mp.createQuery("SELECT t from Ciudad AS t WHERE t.ciudad = :ciudad AND t.departamento.departamento = :departamento ");
		query.setParameter("ciudad", strCiudad);
		query.setParameter("departamento", strDepartamento);
		
		if(query.getResultList().size()>0) {
    		ciudad=(Ciudad) query.getResultList().get(0);    		
    	}

		if(ciudad == null) {
			
			query = mp.createQuery("SELECT t from Ciudad AS t WHERE t.ciudad = :ciudad AND t.departamento.departamento = :departamento ");
			query.setParameter("ciudad", "BOGOTA");
			query.setParameter("departamento", "BOGOTA");
			
			if(query.getResultList().size()>0) {
	    		ciudad=(Ciudad) query.getResultList().get(0);    		
	    	}
		}
		
		return ciudad;
	}

	
	public Ciudad getCiudad(BigDecimal idCiudad) throws NoResultException {

		Ciudad ciudad = null;

		Query query = mp.createQuery("SELECT t from Ciudad AS t WHERE t.idCiudad = :idCiudad ");
		query.setParameter("idCiudad", idCiudad);
	
		
		if(query.getResultList().size()>0) {
    		ciudad=(Ciudad) query.getResultList().get(0);    		
    	}
		
		return ciudad;
	}
	
	
}
