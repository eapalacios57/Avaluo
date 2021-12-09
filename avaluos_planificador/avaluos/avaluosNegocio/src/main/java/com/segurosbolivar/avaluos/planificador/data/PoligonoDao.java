package com.segurosbolivar.avaluos.planificador.data;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.segurosbolivar.avaluos.modelo.service.util.ManejadorCrud;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Poligono;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Subpoligono;
import com.segurosbolivar.avaluos.planificador.modelo.entity.SubpoligonoPK;

@Stateless
public class PoligonoDao extends ManejadorCrud<Poligono, Long> {

	private static final long serialVersionUID = 1985873070718474271L;

	// private static final String SELECT_POLIGONO = "SELECT p.idPoligono,
	// p.bordeColor, p.bordeEstilo, p.bordeGrosor, p.bordeTipo, p.rellenoColor,
	// p.rellenoEstilo, p.rellenoTipo, p.ultimoWkid, p.wkid, sp.id.idSubpoligono,
	// sp.linea, sp.x, sp.y FROM Subpoligono sp JOIN Poligono p ON
	// sp.poligono.idPoligono = p.idPoligono";
	private static final String SELECT_POLIGONO = "SELECT p.id_poligono, p.borde_color, p.borde_estilo, p.borde_grosor, p.borde_tipo, p.relleno_color, p.relleno_estilo, p.relleno_tipo, p.ultimo_wkid, p.wkid, sp.id_subpoligono, sp.linea, sp.x, sp.y FROM Subpoligono sp JOIN poligono p ON sp.id_poligono = p.id_poligono";

	public PoligonoDao() {
		super(Poligono.class);
	}

	public Poligono buscarPoligono(BigDecimal idCultivo, BigDecimal wkid) {
		StringBuilder consulta = new StringBuilder("SELECT p FROM Poligono p ")
				.append("WHERE p.cultivo.idCultivo = :idCultivo AND p.wkid = :wkid");

		Query query = mp.createQuery(consulta.toString());
		query.setParameter("idCultivo", idCultivo);
		query.setParameter("wkid", wkid);
		return (Poligono) query.getSingleResult();
	}

	public List<Subpoligono> buscarPoligonosCultivo(BigDecimal idCultivo) {
		StringBuilder consulta = new StringBuilder(SELECT_POLIGONO).append(" WHERE p.id_cultivo = ? ")
				.append("ORDER BY p.id_poligono, sp.id_subpoligono, sp.linea ");
		Query query = mp.nativeQuery(consulta.toString());
		query.setParameter("1", idCultivo);

		List<Object[]> resultado = query.getResultList();
		return castearResultList(resultado);
	}

	public List<Subpoligono> buscarPoligonosUnidadProd(Long idUnidadProd) {
		StringBuilder consulta = new StringBuilder(SELECT_POLIGONO).append(
				" JOIN cultivo c ON c.id_cultivo = p.id_cultivo WHERE c.id_unidad_productiva = ? ORDER BY p.id_poligono, sp.id_subpoligono, sp.linea");
		Query query = mp.nativeQuery(consulta.toString());
		query.setParameter("1", idUnidadProd);

		List<Object[]> resultado = query.getResultList();
		return castearResultList(resultado);
	}

	private List<Subpoligono> castearResultList(List<Object[]> resultadosConsulta) {
		List<Subpoligono> poligonos = new ArrayList<Subpoligono>();

		for (Object[] columna : resultadosConsulta) {
			Poligono poligono = new Poligono();
			poligono.setIdPoligono((BigDecimal) columna[0]);
			poligono.setBordeColor((String) columna[1]);
			poligono.setBordeEstilo((String) columna[2]);
			poligono.setBordeGrosor((BigDecimal) columna[3]);
			poligono.setBordeTipo((String) columna[4]);
			poligono.setRellenoColor((String) columna[5]);
			poligono.setRellenoEstilo((String) columna[6]);
			poligono.setRellenoTipo((String) columna[7]);
			poligono.setWkid((BigDecimal) columna[8]);
			poligono.setUltimoWkid((BigDecimal) columna[9]);

			Subpoligono subpoligono = new Subpoligono();
			SubpoligonoPK pk = new SubpoligonoPK();
			pk.setIdPoligono(poligono.getIdPoligono());
			pk.setIdSubpoligono((BigDecimal) columna[10]);
			subpoligono.setId(pk);
			subpoligono.setLinea(((BigDecimal) columna[11]).intValue());
			subpoligono.setX((BigDecimal) columna[12]);
			subpoligono.setY((BigDecimal) columna[13]);
			subpoligono.setPoligono(poligono);

			poligonos.add(subpoligono);
		}

		return poligonos;
	}

	public void eliminarPorCultivo(BigDecimal idCultivo) throws SQLException {
		StringBuilder dltPoligonos = new StringBuilder("")
				.append("DELETE FROM Poligono p WHERE p.cultivo.idCultivo = :idCultivo ");
		Query query = mp.createQuery(dltPoligonos.toString());
		query.setParameter("idCultivo", idCultivo);
		query.executeUpdate();
	}

}
