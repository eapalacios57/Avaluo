package com.segurosbolivar.avaluos.modelo.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.entity.AnexoFotografico;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;

/**
 * Manejador que define las operaciones CRUD y de negocio a realizar sobre la
 * tabla correspondiente a la entidad anexo fotografico. Se encarga de las
 * operaciones a base de datos de la tabla de PGB_ANEXO_FOTOGRAFICO a través del
 * uso del entityManager.
 * 
 * @author stilaguy
 * 
 */
@Stateless
public class AnexoFotograficoDao extends ManejadorCrud<AnexoFotografico, Long> {

    private static final long serialVersionUID = -6652237827504144076L;

    public AnexoFotograficoDao() {
	super(AnexoFotografico.class);
    }

    /**
     * Permite consultar el anexo fotografico asociado a un avaluo determinado.
     * 
     * @param idAvaluo
     *            identificador del avaluo al cual consultaremos el anexo asociado.
     * @return el anexo asociado al avaluo, si no existe retornarna nulo.
     */
    @SuppressWarnings("rawtypes")
    public AnexoFotografico consultar(Long idAvaluo) {
	Query consulta = mp.createNamedQuery(AnexoFotografico.class.getSimpleName() + ".idAvaluo");
	consulta.setParameter("idAvaluo", idAvaluo);
	List resultado = consulta.getResultList();
	return (AnexoFotografico) (resultado == null || resultado.size() != 1 ? null : resultado.get(0));
    }

	
	@SuppressWarnings("rawtypes")
	 public AnexoFotografico consultarAnexoImprimir(Long idAvaluo) {
		Query consulta = mp.createNamedQuery(AnexoFotografico.class.getSimpleName() + ".idAvaluo");
		consulta.setParameter("idAvaluo", idAvaluo);
		List resultado = consulta.getResultList();
		return (AnexoFotografico) (resultado != null && resultado.size() == 1 ? resultado.get(0) :null);
	}

    @SuppressWarnings("rawtypes")
    public AnexoFotografico consultarLista(Long idAvaluo) {
	Query consulta = mp.createNamedQuery(AnexoFotografico.class.getSimpleName() + ".idAvaluo");
	consulta.setParameter("idAvaluo", idAvaluo);
	List resultado = consulta.getResultList();
	for(int i=0 ;i<resultado.size();i++) {
		AnexoFotografico a;
		a= (AnexoFotografico) resultado.get(i);
		if(a.getIdImgFachada() != null) {
			return a;
		}
	}
	return (AnexoFotografico) (resultado == null || resultado.size() != 1 ? null : resultado.get(0));
    }

}
