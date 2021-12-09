package com.segurosbolivar.avaluos.planificador.modelo.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.asesoftware.util.exception.NegocioException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.segurosbolivar.avaluos.modelo.dto.ColorDto;
import com.segurosbolivar.avaluos.planificador.data.PoligonoDao;
import com.segurosbolivar.avaluos.planificador.data.SubpoligonoDao;
import com.segurosbolivar.avaluos.planificador.modelo.dto.CrearPoligonosDto;
import com.segurosbolivar.avaluos.planificador.modelo.dto.GeometriaDto;
import com.segurosbolivar.avaluos.planificador.modelo.dto.LineaDto;
import com.segurosbolivar.avaluos.planificador.modelo.dto.PoligonoDto;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ReferenciaEspacialDto;
import com.segurosbolivar.avaluos.planificador.modelo.dto.SimboloDto;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Cultivo;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Poligono;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Subpoligono;
import com.segurosbolivar.avaluos.planificador.modelo.entity.SubpoligonoPK;
import com.segurosbolivar.avaluos.planificador.modelo.services.IPoligono;

@Stateless
@Local(IPoligono.class)
public class PoligonoImpl implements IPoligono {

	private static final long serialVersionUID = -1940900824532091379L;

	@EJB
	private PoligonoDao poligonoDao;

	@EJB
	private SubpoligonoDao subpoligonoDao;

	@Override
	public List<PoligonoDto> buscarPoligonosPorCultivo(BigDecimal idCultivo) {
		List<Subpoligono> poligonos = poligonoDao.buscarPoligonosCultivo(idCultivo);
		return subpoligonosAPoligonoDtos(poligonos);
	}
	
	public List<PoligonoDto> buscarPoligonosPorUnidadProd(Long idUnidadProd) {
		List<Subpoligono> poligonos = poligonoDao.buscarPoligonosUnidadProd(idUnidadProd);
		return  subpoligonosAPoligonoDtos(poligonos);
	}
	
	private List<PoligonoDto> subpoligonosAPoligonoDtos(List<Subpoligono> subpoligonos) {
		Map<BigDecimal, Map<BigDecimal, List<List<BigDecimal>>>> hashPoligonos = getHashPoligonos(subpoligonos);
		List<PoligonoDto> poligonosDto = new ArrayList<PoligonoDto>();

		Map<BigDecimal, Boolean> poligonosProcesados = new HashMap<BigDecimal, Boolean>();
		for (Subpoligono poligono : subpoligonos) {

			if (poligonosProcesados.containsKey(poligono.getId().getIdPoligono())) {
				continue;
			} else {
				GeometriaDto geometria = new GeometriaDto();
				geometria.setHasM(false);
				geometria.setHasZ(false);
				geometria.setType("polygon");
				geometria.setRings(getRings(hashPoligonos, poligono.getId().getIdPoligono()));

				ReferenciaEspacialDto refEspacial = new ReferenciaEspacialDto();
				refEspacial.setLatestWkid(poligono.getPoligono().getUltimoWkid());
				refEspacial.setWkid(poligono.getPoligono().getWkid());
				geometria.setSpatialReference(refEspacial);

				SimboloDto simbolo = new SimboloDto();
				simbolo.setColor(convertirString(poligono.getPoligono().getRellenoColor()));

				LineaDto borde = new LineaDto();
				borde.setColor(convertirString(poligono.getPoligono().getBordeColor()));
				borde.setStyle("solid");
				borde.setType("simple-line");
				borde.setWidth(poligono.getPoligono().getBordeGrosor());
				simbolo.setOutline(borde);
				simbolo.setStyle("solid");
				simbolo.setType("simple-fill");

				PoligonoDto poligonoDto = new PoligonoDto();
				poligonoDto.setGeometry(geometria);
				poligonoDto.setSymbol(simbolo);
				poligonosDto.add(poligonoDto);
				poligonosProcesados.put(poligono.getId().getIdPoligono(), true);
			}
		}
		return poligonosDto;
	}

	private Map<BigDecimal, Map<BigDecimal, List<List<BigDecimal>>>> getHashPoligonos(List<Subpoligono> poligonos) {

		Map<BigDecimal, Map<BigDecimal, List<List<BigDecimal>>>> hashPolygon = new HashMap<BigDecimal, Map<BigDecimal, List<List<BigDecimal>>>>();

		for (Subpoligono poligono : poligonos) {
			BigDecimal idPolygon = poligono.getId().getIdPoligono();
			BigDecimal idSubPolygon = poligono.getId().getIdSubpoligono();

			// Crear nuevo punto
			List<BigDecimal> punto = new ArrayList<BigDecimal>();
			punto.add(poligono.getX());
			punto.add(poligono.getY());

			if (hashPolygon.containsKey(idPolygon)) {
				Map<BigDecimal, List<List<BigDecimal>>> hashSubPolygon = hashPolygon.get(idPolygon);
				if (hashSubPolygon.containsKey(idSubPolygon)) {
					hashSubPolygon.get(idSubPolygon).add(punto);
				} else {
					List<List<BigDecimal>> newSubPolygon = new ArrayList<List<BigDecimal>>();
					newSubPolygon.add(punto);
					hashSubPolygon.put(idSubPolygon, newSubPolygon);
				}
			} else {
				List<List<BigDecimal>> newSubPolygon = new ArrayList<List<BigDecimal>>();
				newSubPolygon.add(punto);
				Map<BigDecimal, List<List<BigDecimal>>> hashSubPolygon = new HashMap<BigDecimal, List<List<BigDecimal>>>();
				hashSubPolygon.put(idSubPolygon, newSubPolygon);
				hashPolygon.put(idPolygon, hashSubPolygon);
			}
		}

		return hashPolygon;
	}

	private List<List<List<BigDecimal>>> getRings(
			Map<BigDecimal, Map<BigDecimal, List<List<BigDecimal>>>> hashPoligonos, BigDecimal idPoligono) {
		List<List<List<BigDecimal>>> rings = new ArrayList<List<List<BigDecimal>>>();
		if (hashPoligonos.containsKey(idPoligono)) {
			for (List<List<BigDecimal>> subpolygon : hashPoligonos.get(idPoligono).values()) {
				rings.add(subpolygon);
			}
		}
		return rings;
	}

	private ColorDto convertirString(String rgbaString) {
		if (rgbaString == null || "".equals(rgbaString.trim()) || rgbaString.split(",").length < 4) {
			return new ColorDto(0, 0, 0, new BigDecimal(0.5));
		} else {
			ColorDto color = new ColorDto();
			String[] rgba = rgbaString.split(",");
			color.setR(Integer.parseInt(rgba[0]));
			color.setG(Integer.parseInt(rgba[1]));
			color.setB(Integer.parseInt(rgba[2]));
			if (rgba[3] == null) {
				color.setA(new BigDecimal(0));
			} else {
				color.setA(new BigDecimal(rgba[3]));
			}
			return color;
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearActualizarPoligono(String crearPoligonosJson) throws SQLException, NegocioException {
		CrearPoligonosDto dto = castearStringAObjeto(crearPoligonosJson);
		BigDecimal idCultivo = dto.getIdCultivo();

		subpoligonoDao.eliminarPorCultivo(idCultivo);
		poligonoDao.eliminarPorCultivo(idCultivo);

		List<PoligonoDto> poligonosDto = dto.getPoligonos();
		Cultivo cultivo = new Cultivo(); 
		cultivo.setIdCultivo(idCultivo);

		for (PoligonoDto poligonoDto : poligonosDto) {
			Poligono poligono = new Poligono();
			poligono.setCultivo(cultivo);
			poligono.setWkid(poligonoDto.getGeometry().getSpatialReference().getWkid());
			poligono.setUltimoWkid(poligonoDto.getGeometry().getSpatialReference().getLatestWkid());
			poligono.setRellenoColor(convertirAString(poligonoDto.getSymbol().getColor()));
			poligono.setRellenoTipo(poligonoDto.getSymbol().getType());
			poligono.setRellenoEstilo(poligonoDto.getSymbol().getStyle());
			poligono.setBordeColor(convertirAString(poligonoDto.getSymbol().getOutline().getColor()));
			poligono.setBordeEstilo(poligonoDto.getSymbol().getOutline().getStyle());
			poligono.setBordeGrosor(poligonoDto.getSymbol().getOutline().getWidth());
			poligono.setBordeTipo(poligonoDto.getSymbol().getOutline().getType());

			poligonoDao.crear(poligono);

			List<Subpoligono> subpoligonos = convertirListaRings(poligonoDto.getGeometry().getRings());
			for (Subpoligono subpoligono : subpoligonos) {
				subpoligono.getId().setIdPoligono(poligono.getIdPoligono());
				subpoligono.setPoligono(poligono);
				subpoligonoDao.crear(subpoligono);
			}
		}
	}

	private CrearPoligonosDto castearStringAObjeto(String json) throws NegocioException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			CrearPoligonosDto dto = mapper.readValue(json, CrearPoligonosDto.class);
			return dto;
		} catch (IOException ex) {
			throw new NegocioException("No fue posible convertir el objeto JSON. ");
		}
	}

	private List<Subpoligono> convertirListaRings(List<List<List<BigDecimal>>> rings) {
		List<Subpoligono> subpoligonos = new ArrayList<Subpoligono>();
		Integer idSubpoligono = 0;
		Integer contLinea = 0;
		for (List<List<BigDecimal>> ring : rings) {
			idSubpoligono++;
			contLinea = 1;
			for (List<BigDecimal> punto : ring) {
				Subpoligono subpoligono = new Subpoligono();
				SubpoligonoPK pk = new SubpoligonoPK();
				pk.setIdSubpoligono(new BigDecimal(idSubpoligono));
				subpoligono.setId(pk);
				subpoligono.setLinea(contLinea);
				subpoligono.setX(punto.get(0));
				subpoligono.setY(punto.get(1));
				subpoligonos.add(subpoligono);
				contLinea++;
			}
		}
		return subpoligonos;
	}

	private String convertirAString(ColorDto color) {
		return new StringBuilder("").append(color.getR()).append(",").append(color.getG()).append(",")
				.append(color.getB()).append(",").append(color.getA() == null ? new BigDecimal(0) : color.getA())
				.toString();
	}

}
