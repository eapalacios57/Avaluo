package com.segurosbolivar.avaluos.planificador.data;

import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.dto.TecnificacionAgricolaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.TecnificacionAgricola;


@Stateless
public class TecnificacionAgricolaDao extends ManejadorCrud<TecnificacionAgricola, Long> {

	private static final long serialVersionUID = 7747626177696187846L;
	public static final String SELECT = "SELECT a FROM TecnificacionAgricola a ";

	public TecnificacionAgricolaDao() {
		super(TecnificacionAgricola.class);
	}

	public TecnificacionAgricola getTecnificacionAgricola(TecnificacionAgricolaDTO consultar)
			throws SQLException {

		
		 @SuppressWarnings("unchecked")
		List<TecnificacionAgricola> listTecnificacionAgricola = 
				 mp.createQuery("SELECT t from TecnificacionAgricola as t WHERE t.unidadProductiva.idUnidadProductiva = :idUnidadProductiva")
				 .setParameter("idUnidadProductiva", consultar.getIdUnidadProductiva())
				 .getResultList();
		
		 if(listTecnificacionAgricola != null && listTecnificacionAgricola.size()!=0) {
			 
			 TecnificacionAgricola tecnificacionAgricola = listTecnificacionAgricola.get(0);
			           
			 return tecnificacionAgricola;
			 
		 }else{
			 return null;
		 }
		 
		
		
		
	}

	

}
