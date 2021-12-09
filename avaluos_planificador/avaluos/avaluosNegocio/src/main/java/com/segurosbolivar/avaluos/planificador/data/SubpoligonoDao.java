package com.segurosbolivar.avaluos.planificador.data;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Subpoligono;

@Stateless
public class SubpoligonoDao extends ManejadorCrud<Subpoligono, Long> {

	private static final long serialVersionUID = 7931428426390249825L;
	
	public SubpoligonoDao() {
		super(Subpoligono.class);
	}
	
	public void eliminarPorCultivo(BigDecimal idCultivo) throws SQLException {
		StringBuilder dltSubpoligonos = new StringBuilder("")
				.append("DELETE FROM Subpoligono s WHERE s.id.idPoligono IN (SELECT p.idPoligono FROM Poligono p WHERE p.cultivo.idCultivo = :idCultivo) ");
		Query query = mp.createQuery(dltSubpoligonos.toString());
		query.setParameter("idCultivo", idCultivo);
		query.executeUpdate();
	}

}
