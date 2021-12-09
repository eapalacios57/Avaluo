package com.segurosbolivar.avaluos.planificador.servicios;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jfree.chart.renderer.AreaRendererEndType;
import org.mapstruct.factory.Mappers;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.modelo.entity.Ciudad;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadAvicolaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ActividadPiscicolaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.SoporteDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.TecnificacionAgricolaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.UnidadProductivaDTO;
import com.segurosbolivar.avaluos.planificador.modelo.dto.ValorPorcentajeDTO;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadAvicola;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadGanadera;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadPiscicola;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadPorcicola;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Cultivo;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Predio;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Soporte;
import com.segurosbolivar.avaluos.planificador.modelo.entity.TecnificacionAgricola;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Unidad;
import com.segurosbolivar.avaluos.planificador.modelo.entity.UnidadProductiva;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ValorPorcentaje;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ValorPorcentajePK;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.ActividadAvicolaFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.ActividadGanaderaFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.ActividadPiscicolaFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.ActividadPorcicolaFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.CultivoFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.PredioFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.SoporteFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.TecAgricolaFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.mapper.ValorPorcentajeFullDTOMapper;
import com.segurosbolivar.avaluos.planificador.modelo.services.ICiudad;
import com.segurosbolivar.avaluos.planificador.modelo.services.IUnidadProductiva;

@Path("/UnidadProductiva")
@Stateless
public class UnidadProductivaSvc {

	@EJB
	IUnidadProductiva iUnidadProductiva;

	@EJB
	ICiudad iCiudad;

	@Path("/{codSolicitud}/{idUnidadProductiva}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaRest getUnidadProductiva(@PathParam("codSolicitud") String codSolicitud,
			@PathParam("idUnidadProductiva") String idUnidadProductiva) {

		RespuestaRest respuesta;
		UnidadProductiva unidadProductiva = new UnidadProductiva();

		UnidadProductivaDTO unidadProductivaDto = new UnidadProductivaDTO();

		List<UnidadProductiva> listaUnidadProd = new ArrayList<UnidadProductiva>();
		Predio predioDto = new Predio();
		List<Predio> listaPrediosDto = new ArrayList<Predio>();
		List<UnidadProductivaDTO> listaUnidadProdDto = new ArrayList<UnidadProductivaDTO>();
		Cultivo cultivoDto;
		List<Cultivo> listaCultivos = new ArrayList<>();
		SoporteDTO soporteDto;
		List<SoporteDTO> listaSoporteDto = new ArrayList<>();
		TecnificacionAgricolaDTO tecnificacionAgricolaDto;
		List<TecnificacionAgricolaDTO> listaTecnAgricolaDto = new ArrayList<>();
		ValorPorcentajeDTO valorPorcentajeDto;
		List<ValorPorcentaje> listaValorPorcentajeDto = new ArrayList<>();
		ActividadAvicolaDTO actAvicolaDto;
		List<ActividadAvicolaDTO> listaActAvicolaDto = new ArrayList<>();
		ActividadGanadera actGanaderaDto;
		List<ActividadGanadera> listaActGanaderaDto = new ArrayList<>();
		ActividadPiscicolaDTO actPiscicolaDto;
		List<ActividadPiscicolaDTO> listaActPiscicolaDto = new ArrayList<>();
		ActividadPorcicola actPorcicolaDto;
		List<ActividadPorcicola> listaActPorcicolaDto = new ArrayList<>();

		PredioFullDTOMapper predioMapper = Mappers.getMapper(PredioFullDTOMapper.class);
		CultivoFullDTOMapper cultivoMapper = Mappers.getMapper(CultivoFullDTOMapper.class);
		SoporteFullDTOMapper soporteMapper = Mappers.getMapper(SoporteFullDTOMapper.class);
		TecAgricolaFullDTOMapper tecAgricolaMapper = Mappers.getMapper(TecAgricolaFullDTOMapper.class);
		ValorPorcentajeFullDTOMapper valorPorcentajeMapper = Mappers.getMapper(ValorPorcentajeFullDTOMapper.class);
		ActividadAvicolaFullDTOMapper actAvicolaMapper = Mappers.getMapper(ActividadAvicolaFullDTOMapper.class);
		ActividadGanaderaFullDTOMapper actGanaderaMapper = Mappers.getMapper(ActividadGanaderaFullDTOMapper.class);
		ActividadPiscicolaFullDTOMapper actPiscicolaMapper = Mappers.getMapper(ActividadPiscicolaFullDTOMapper.class);
		ActividadPorcicolaFullDTOMapper actPorcicolaMapper = Mappers.getMapper(ActividadPorcicolaFullDTOMapper.class);

		try {

			unidadProductiva = iUnidadProductiva.getUnidadProductiva(Long.valueOf(idUnidadProductiva), codSolicitud);

			System.out.println("----------------" + unidadProductiva.getSolicitud().getCodigoSolicitud());

//			for(UnidadProductiva undProduct: listaUnidadProd) {
//				listaPrediosDto.removeAll(listaPrediosDto);

			unidadProductivaDto = new UnidadProductivaDTO();

			unidadProductivaDto.setAltitud(unidadProductiva.getAltitud());
			unidadProductivaDto.setCodigoSolicitud(unidadProductiva.getSolicitud().getCodigoSolicitud());
			unidadProductivaDto.setAreaProtegida(unidadProductiva.getAreaProtegida());
			unidadProductivaDto.setAreaProyecto(unidadProductiva.getAreaProyecto());
			unidadProductivaDto.setAspectosClimaticos(unidadProductiva.getAspectosClimaticos());
			unidadProductivaDto.setCondicionViaAcceso(unidadProductiva.getCondicionViaAcceso());
			unidadProductivaDto.setCoberturaSistemaRiego(unidadProductiva.getCoberturaSistemaRiego());
			unidadProductivaDto.setCondicionViaAcceso(unidadProductiva.getCondicionViaAcceso());
			unidadProductivaDto.setFuenteHidricaExistente(unidadProductiva.getFuenteHidricaExistente());
			unidadProductivaDto.setHumedadRelativa(unidadProductiva.getHumedadRelativa());
			unidadProductivaDto.setIdCiudad(unidadProductiva.getIdCiudad());
			unidadProductivaDto.setIdDepartamento(unidadProductiva.getIdDepartamento());
			unidadProductivaDto.setIdUnidadProductiva(unidadProductiva.getIdUnidadProductiva());
			unidadProductivaDto.setInfraAlmacenamientoAgua(unidadProductiva.getInfraestAlmacenamientoAgua());
			unidadProductivaDto.setLatitud(unidadProductiva.getLatitud());
			unidadProductivaDto.setLongitud(unidadProductiva.getLongitud());
			unidadProductivaDto.setLugarInversion(unidadProductiva.getLugarInversion());
			unidadProductivaDto.setMateriaOrganica(unidadProductiva.getMateriaOrganica());
			unidadProductivaDto.setNivelDrenaje(unidadProductiva.getNivelDrenaje());
			unidadProductivaDto.setNombre(unidadProductiva.getNombre());
			unidadProductivaDto.setNombreDistritoRiego(unidadProductiva.getNombreDistritoRiego());
			unidadProductivaDto.setPrecipitacion(unidadProductiva.getPrecipitacion());
			unidadProductivaDto.setTipoActividad(unidadProductiva.getTipoActividad());
			unidadProductivaDto.setTopografia(unidadProductiva.getTopografia());
			unidadProductivaDto.setUnidadIdUnidad(unidadProductiva.getUnidad().getIdUnidad());
			unidadProductivaDto.setVereda(unidadProductiva.getVereda());
			unidadProductivaDto.setAreaUtilizadaHA(unidadProductiva.getAreaUtilizadaHA());
			unidadProductivaDto.setUsuarioCreacion(unidadProductiva.getUsuarioCreacion());
			unidadProductivaDto.setFechaCreacion(unidadProductiva.getFechaCreacion());
			unidadProductivaDto.setUsuarioTransaccion(unidadProductiva.getUsuarioTransaccion());
			unidadProductivaDto.setFechaTransaccion(unidadProductiva.getFechaTransaccion());

			Ciudad ciudad = iCiudad.getCiudad(unidadProductiva.getIdCiudad());

			if (ciudad != null)
				unidadProductivaDto
						.setCodigoDivipola(ciudad.getCiudad() + " - " + ciudad.getDepartamento().getDepartamento());

			/*
			 * Cultivos
			 */

			// TODO AGREGAR CAMPOS CULTIVO
			for (Cultivo cultivo : unidadProductiva.getCultivos()) {
				cultivoDto = new Cultivo();
				cultivoDto.setComentario(cultivo.getComentario());
				cultivoDto.setDensidad(cultivo.getDensidad());
				cultivoDto.setIdCultivo(cultivo.getIdCultivo());
				cultivoDto.setLugarVenta(cultivo.getLugarVenta());
				cultivoDto.setMesesCosecha(cultivo.getMesesCosecha());
				cultivoDto.setCiclosProduccionAnio(cultivo.getCiclosProduccionAnio());
				cultivoDto.setProducto(cultivo.getProducto());
				cultivoDto.setVariedad(cultivo.getVariedad());
				cultivoDto.setCultivoAlterna(cultivo.getCultivoAlterna());

				cultivoDto.setAreaProductiva(cultivo.getAreaProductiva());
				cultivoDto.setAreaProductivaHa(cultivo.getAreaProductivaHa());

				Unidad areaProductivaUnidad = new Unidad();

				if (cultivo.getAreaProductivaUnidad() != null) {
					areaProductivaUnidad.setNombre(cultivo.getAreaProductivaUnidad().getNombre());
					areaProductivaUnidad.setIdUnidad(cultivo.getAreaProductivaUnidad().getIdUnidad());
				} else {
					areaProductivaUnidad.setNombre("Hectárea");
					areaProductivaUnidad.setIdUnidad(1);
				}

				cultivoDto.setAreaProductivaUnidad(areaProductivaUnidad);
				cultivoDto.setRotaCultivo(cultivo.getRotaCultivo());
				cultivoDto.setFechaSiembra(cultivo.getFechaSiembra());
				cultivoDto.setComentario(cultivo.getComentario());
				cultivoDto.setAsistenciaTecnica(cultivo.getAsistenciaTecnica());
				cultivoDto.setFechaCreacion(cultivo.getFechaCreacion());
				cultivoDto.setUsuarioCreacion(cultivo.getUsuarioCreacion());

				Unidad unidad = new Unidad();

				if (cultivo.getUnidad() != null) {
					unidad.setNombre(cultivo.getUnidad().getNombre());
					unidad.setIdUnidad(cultivo.getUnidad().getIdUnidad());
					unidad.setFactorHa(cultivo.getUnidad().getFactorHa());
				} else {
					unidad.setNombre("Hectárea");
					unidad.setIdUnidad(cultivo.getUnidad().getIdUnidad());
					unidad.setFactorHa(new BigDecimal(1));
				}

				cultivoDto.setUnidad(unidad);
				listaCultivos.add(cultivoDto);
			}
			unidadProductivaDto.setCultivos(listaCultivos);
//				/*
//				 * Predios
//				 */
			for (Predio predio : unidadProductiva.getPredios()) {
				predioDto = new Predio();
				predioDto.setAreaProdcutiva(predio.getAreaProdcutiva());
				predioDto.setAreaTotal(predio.getAreaTotal());
				predioDto.setFormaLlegar(predio.getFormaLlegar());
				predioDto.setFuenteInformacion(predio.getFuenteInformacion());

				predioDto.setIdDepartamento(predio.getIdDepartamento());
				predioDto.setIdCiudad(predio.getIdCiudad());
				predioDto.setIdPredio(predio.getIdPredio());
				if (predio.getLatitud() != null) {
					predioDto.setLatitud(predio.getLatitud());
				} else {
					predioDto.setLatitud(new BigDecimal(unidadProductivaDto.getLatitud()));
				}

				if (predio.getLongitud() != null) {
					predioDto.setLongitud(predio.getLongitud());
				} else {
					predioDto.setLongitud(new BigDecimal(unidadProductivaDto.getLongitud()));
				}

				predioDto.setNombreMatriculaInmobiliaria(predio.getNombreMatriculaInmobiliaria());
				predioDto.setNumeroFolio(predio.getNumeroFolio());
				predioDto.setTenencia(predio.getTenencia());
				predioDto.setUnidad(predio.getUnidad());
				predioDto.setVereda(predio.getVereda());
				predioDto.setFechaCreacion(predio.getFechaCreacion());
				predioDto.setUsuarioCreacion(predio.getUsuarioCreacion());

				Unidad unidad = new Unidad();
				unidad.setUsuarioCreacion(predio.getIdUnidadPredio().getUsuarioCreacion());
				unidad.setFechaCreacion(predio.getIdUnidadPredio().getFechaCreacion());

				if (predio.getIdUnidadPredio() != null) {
					unidad.setIdUnidad(predio.getIdUnidadPredio().getIdUnidad());
					unidad.setNombre(predio.getIdUnidadPredio().getNombre());
					unidad.setFactorHa(predio.getIdUnidadPredio().getFactorHa());
				} else {
					unidad.setIdUnidad(1);
					unidad.setNombre("Hectárea");
					unidad.setFactorHa(new BigDecimal(1));
				}

				predioDto.setIdUnidadPredio(unidad);

				listaPrediosDto.add(predioDto);

			}
			unidadProductivaDto.setPredios(listaPrediosDto);
//				/*
//				 * Soportes
//				 */
			for (Soporte soporte : unidadProductiva.getSoportes()) {
				soporteDto = new SoporteDTO();
				soporteDto.setCodImagen(soporte.getCodImagen());
				// soporteDto.setCultivo(soporte.getCultivo());
				soporteDto.setFecha(soporte.getFecha());
				soporteDto.setIdSoporte(soporte.getIdSoporte());
				soporteDto.setLatitud(soporte.getLatitud());
				soporteDto.setLongitud(soporte.getLongitud());
				soporteDto.setPath(soporte.getPath());
				soporteDto.setTipoSoporte(soporte.getTipoSoporte());
				soporteDto.setFechaCreacion(soporte.getFechaCreacion());
				soporteDto.setUsuarioCreacion(soporte.getUsuarioCreacion());
//					soporteDto.setUnidadProductiva(soporte.getUnidadProductiva());
				listaSoporteDto.add(soporteDto);
			}
			unidadProductivaDto.setSoportes(soporteMapper.dto2entityList(listaSoporteDto));
//				/*
//				 * Tecnificaci�n Agricola
//				 */
			for (TecnificacionAgricola tecAgricola : unidadProductiva.getTecnificacionAgricolas()) {
				tecnificacionAgricolaDto = new TecnificacionAgricolaDTO();
				tecnificacionAgricolaDto.setActividadMecanizadas(tecAgricola.getActividadMecanizadas());
				tecnificacionAgricolaDto.setIdTecnificacionAgricola(tecAgricola.getIdTecnificacionAgricola());
				tecnificacionAgricolaDto.setInfraestructura(tecAgricola.getInfraestructura());
				tecnificacionAgricolaDto.setMaquinariaDisponible(tecAgricola.getMaquinariaDisponible());
				tecnificacionAgricolaDto.setTipoTransportePropio(tecAgricola.getTipoTransportePropio());
				tecnificacionAgricolaDto.setTransporteAlquilado(tecAgricola.getTransporteAlquilado());
				tecnificacionAgricolaDto.setTransportePropio(tecAgricola.getTransportePropio());
				tecnificacionAgricolaDto.setFechaCreacion(tecAgricola.getFechaCreacion());
				tecnificacionAgricolaDto.setUsuarioCreacion(tecAgricola.getUsuarioCreacion());
				listaTecnAgricolaDto.add(tecnificacionAgricolaDto);
			}
			unidadProductivaDto.setTecnificacionAgricolas(tecAgricolaMapper.dto2entityList(listaTecnAgricolaDto));
////				
//				/*
//				 * Valores Porcentaje
//				 */
			for (ValorPorcentaje valPorcentaje : unidadProductiva.getValorPorcentajes()) {
				valorPorcentajeDto = new ValorPorcentajeDTO();
				ValorPorcentajePK valorPorcentajePk = new ValorPorcentajePK();
				valorPorcentajePk.setConcepto(valPorcentaje.getId().getConcepto());
				valorPorcentajePk.setIdUnidadProductiva(valPorcentaje.getId().getIdUnidadProductiva());
				valorPorcentajeDto.setPorcentaje(valPorcentaje.getPorcentaje());
				valorPorcentajeDto.setFechaCreacion(valPorcentaje.getFechaCreacion());
				valorPorcentajeDto.setUsuarioCreacion(valPorcentaje.getUsuarioCreacion());

				ValorPorcentaje valorPorcentaje = valorPorcentajeMapper.dto2entity(valorPorcentajeDto);
				valorPorcentaje.setId(valorPorcentajePk);

				listaValorPorcentajeDto.add(valorPorcentaje);
			}

			unidadProductivaDto.setValorPorcentajes(listaValorPorcentajeDto);
			/*
			 * // * Actividad Avicola //
			 */
			for (ActividadAvicola actAvicola : unidadProductiva.getActividadAvicolas()) {
				actAvicolaDto = new ActividadAvicolaDTO();

				actAvicolaDto.setLineaGenetica(actAvicola.getLineaGenetica());
				actAvicolaDto.setAreaGalponesEngorde(actAvicola.getAreaGalponesEngorde());
				actAvicolaDto.setAreaGalponesLevante(actAvicola.getAreaGalponesLevante());
				actAvicolaDto.setCiclosProducccionAnio(actAvicola.getCiclosProducccionAnio());
				actAvicolaDto.setDistancia(actAvicola.getDistancia());
				actAvicolaDto.setEquipoDisponible(actAvicola.getEquipoDisponible());
				actAvicolaDto.setIdActividadAvicola(actAvicola.getIdActividadAvicola());
//					actAvicolaDto.setIdUnidadProductiva(actAvicola.getidUnidadProductiva);
				actAvicolaDto.setInfraestructura(actAvicola.getInfraestructura());
				actAvicolaDto.setIntegradoConQuien(actAvicola.getIntegradoConQuien());
				actAvicolaDto.setNumeroAves(actAvicola.getNumeroAves());
				actAvicolaDto.setNumeroGalponesEngorde(actAvicola.getNumeroGalponesEngorde());
				actAvicolaDto.setNumeroGalponesLevante(actAvicola.getNumeroGalponesLevante());
				actAvicolaDto.setProductorIntegrado(actAvicola.getProductorIntegrado());
				actAvicolaDto.setProveedor(actAvicola.getProveedor());
				actAvicolaDto.setTipoTransportePropio(actAvicola.getTipoTransportePropio());
				actAvicolaDto.setTransporteAlquilado(actAvicola.getTransporteAlquilado());
				actAvicolaDto.setTransportePropio(actAvicola.getTransportePropio());
				actAvicolaDto.setAreaTotalGalpones(actAvicola.getAreaTotalGalpones());
				actAvicolaDto.setComentarios(actAvicola.getComentarios());
				actAvicolaDto.setLugarVenta(actAvicola.getLugarVenta());
				actAvicolaDto.setAreaGalponesPostura(actAvicola.getAreaGalponesPostura());
				actAvicolaDto.setNumGalponesPostura(actAvicola.getNumGalponesPostura());
				actAvicolaDto.setAsistenciaTecnica(actAvicola.getAsistenciaTecnica());
				actAvicolaDto.setFechaCreacion(actAvicola.getFechaCreacion());
				actAvicolaDto.setUsuarioCreacion(actAvicola.getUsuarioCreacion());

				listaActAvicolaDto.add(actAvicolaDto);

			}
			unidadProductivaDto.setActividadAvicolas(actAvicolaMapper.dto2entityList(listaActAvicolaDto));

//				/*
//				 * Actividad Ganadera
//				 */

			// TODO AGREGAR CAMPOS

			for (ActividadGanadera actGanadera : unidadProductiva.getActividadGanaderas()) {
				actGanaderaDto = new ActividadGanadera();

				actGanaderaDto.setCantidadPotreros(actGanadera.getCantidadPotreros());
				actGanaderaDto.setCiclosProducccionAnio(actGanadera.getCiclosProducccionAnio());
				actGanaderaDto.setEquiposDisponibles(actGanadera.getEquiposDisponibles());
				actGanaderaDto.setIdActividadGanadera(actGanadera.getIdActividadGanadera());
				actGanaderaDto.setInfraestructura(actGanadera.getInfraestructura());
				actGanaderaDto.setLugarVenta(actGanadera.getLugarVenta());
				actGanaderaDto.setRazaCruce(actGanadera.getRazaCruce());
				actGanaderaDto.setTipoPradera(actGanadera.getTipoPradera());
				actGanaderaDto.setTipoTransportePropio(actGanadera.getTipoTransportePropio());
				actGanaderaDto.setTransporteAlquilado(actGanadera.getTransporteAlquilado());
				actGanaderaDto.setTransportePropio(actGanadera.getTransportePropio());

				actGanaderaDto.setAreaPraderaMejorada(actGanadera.getAreaPraderaMejorada());
				actGanaderaDto.setAreaPraderaMejoradaHa(actGanadera.getAreaPraderaMejoradaHa());

				Unidad areaPraderaMejoradaUnidad = new Unidad();
				
				if (actGanadera.getAreaPraderaMejoradaUnidad() != null) {
					areaPraderaMejoradaUnidad.setNombre(actGanadera.getAreaPraderaMejoradaUnidad().getNombre());
					areaPraderaMejoradaUnidad.setIdUnidad(actGanadera.getAreaPraderaMejoradaUnidad().getIdUnidad());
					areaPraderaMejoradaUnidad.setFactorHa(actGanadera.getAreaPraderaMejoradaUnidad().getFactorHa());
				}
				else {
					areaPraderaMejoradaUnidad.setNombre("Hectárea");
					areaPraderaMejoradaUnidad.setIdUnidad(1);
					areaPraderaMejoradaUnidad.setFactorHa(new BigDecimal(1));
				}
				actGanaderaDto.setAreaPraderaMejoradaUnidad(areaPraderaMejoradaUnidad);

				actGanaderaDto.setAreaActividad(actGanadera.getAreaActividad());
				actGanaderaDto.setAreaActividadHa(actGanadera.getAreaActividadHa());

				Unidad areaActividadUnidad = new Unidad();

				if (actGanadera.getAreaActividadUnidad() != null) {
					areaActividadUnidad.setNombre(actGanadera.getAreaActividadUnidad().getNombre());
					areaActividadUnidad.setIdUnidad(actGanadera.getAreaActividadUnidad().getIdUnidad());
					areaActividadUnidad.setFactorHa(actGanadera.getAreaActividadUnidad().getFactorHa());
				} else {
					areaActividadUnidad.setNombre("Hectárea");
					areaActividadUnidad.setIdUnidad(1);
					areaActividadUnidad.setFactorHa(new BigDecimal(1));
				}
				actGanaderaDto.setAreaActividadUnidad(areaActividadUnidad);
				actGanaderaDto.setComentarios(actGanadera.getComentarios());

				actGanaderaDto.setAsistenciaTecnica(actGanadera.getAsistenciaTecnica());
				actGanaderaDto.setFechaCreacion(actGanadera.getFechaCreacion());
				actGanaderaDto.setUsuarioCreacion(actGanadera.getUsuarioCreacion());

				listaActGanaderaDto.add(actGanaderaDto);
			}
			unidadProductivaDto.setActividadGanaderas(listaActGanaderaDto);

//				/*
//				 * Actividad Porcicola
//				 */
			for (ActividadPorcicola actPorcicola : unidadProductiva.getActividadPorcicolas()) {
				actPorcicolaDto = new ActividadPorcicola();
				actPorcicolaDto.setCiclosProducccionAnio(actPorcicola.getCiclosProducccionAnio());
				actPorcicolaDto.setEquiposDisponibles(actPorcicola.getEquiposDisponibles());
				actPorcicolaDto.setIdActividadPorcicola(actPorcicola.getIdActividadPorcicola());
//					actPorcicolaDto.setIdUnidadProductiva(actPorcicola.getidUnidadProductiva);
				actPorcicolaDto.setInfraestructura(actPorcicola.getInfraestructura());
				actPorcicolaDto.setLineaGenetica(actPorcicola.getLineaGenetica());
				actPorcicolaDto.setLugarVenta(actPorcicola.getLugarVenta());
				actPorcicolaDto.setManejoResiduos(actPorcicola.getManejoResiduos());
				actPorcicolaDto.setTipoTransportePropio(actPorcicola.getTipoTransportePropio());
				actPorcicolaDto.setTransporteAlquilado(actPorcicola.getTransporteAlquilado());
				actPorcicolaDto.setTransportePropio(actPorcicola.getTransportePropio());
				actPorcicolaDto.setAreaActividad(actPorcicola.getAreaActividad());
				actPorcicolaDto.setComentarios(actPorcicola.getComentarios());
				actPorcicolaDto.setAsistenciaTecnica(actPorcicola.getAsistenciaTecnica());
				actPorcicolaDto.setFechaCreacion(actPorcicola.getFechaCreacion());
				actPorcicolaDto.setUsuarioCreacion(actPorcicola.getUsuarioCreacion());

				listaActPorcicolaDto.add(actPorcicolaDto);
			}
			unidadProductivaDto.setActividadPorcicolas(listaActPorcicolaDto);

//				/*
//				 * Actividades Piscicolas
//				 */
			for (ActividadPiscicola actPiscicola : unidadProductiva.getActividadPiscicolas()) {
				actPiscicolaDto = new ActividadPiscicolaDTO();

				actPiscicolaDto.setAreaProductivaEspejoAgua(actPiscicola.getAreaProductivaEspejoAgua());
				actPiscicolaDto.setCiclosProducccionAnio(actPiscicola.getCiclosProducccionAnio());
				actPiscicolaDto.setEquipoDisponible(actPiscicola.getEquipoDisponible());
				actPiscicolaDto.setEspecieCultivada(actPiscicola.getEspecieCultivada());
				actPiscicolaDto.setIdActividadPiscicola(actPiscicola.getIdActividadPiscicola());
//					actPiscicolaDto.setIdUnidadProductiva(actPiscicola.getidUnidadProductiva);
				actPiscicolaDto.setInfraestructura(actPiscicola.getInfraestructura());
				actPiscicolaDto.setNumeroAnimalesEstanque(actPiscicola.getNumeroAnimalesEstanque());
				actPiscicolaDto.setNumeroEstanques(actPiscicola.getNumeroEstanques());
				actPiscicolaDto.setProfundidad(actPiscicola.getProfundidad());
				actPiscicolaDto.setTipoProduccion(actPiscicola.getTipoProduccion());
				actPiscicolaDto.setTipoTransportePropio(actPiscicola.getTipoTransportePropio());
				actPiscicolaDto.setTransporteAlquilado(actPiscicola.getTransporteAlquilado());
				actPiscicolaDto.setTransportePropio(actPiscicola.getTransportePropio());

				actPiscicolaDto.setLugarVenta(actPiscicola.getLugarVenta());
				actPiscicolaDto.setComentarios(actPiscicola.getComentarios());
				actPiscicolaDto.setAsistenciaTecnica(actPiscicola.getAsistenciaTecnica());
				actPiscicolaDto.setFechaCreacion(actPiscicola.getFechaCreacion());
				actPiscicolaDto.setUsuarioCreacion(actPiscicola.getUsuarioCreacion());

				listaActPiscicolaDto.add(actPiscicolaDto);
			}
			unidadProductivaDto.setActividadPiscicolas(actPiscicolaMapper.dto2entityList(listaActPiscicolaDto));

			System.out.println("Unidad productiva encontrada " + unidadProductivaDto.getCodigoSolicitud());

			return new RespuestaRest("200", "", unidadProductivaDto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new RespuestaRest("500", e.getMessage(), null);

		}

	}

	@Path("consultaPorSol/{codSolicitud}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaRest consultaPorSol(@PathParam("codSolicitud") String codSolicitud) {

		RespuestaRest respuesta;

		List<UnidadProductiva> listaUnidadProd = new ArrayList<>();
		List<UnidadProductivaDTO> listaUnidadProdDto = new ArrayList<>();
		Predio predioDto = new Predio();

		PredioFullDTOMapper predioMapper = Mappers.getMapper(PredioFullDTOMapper.class);

		try {
			listaUnidadProd = iUnidadProductiva.listarUnidadesProductivas(codSolicitud);
			for (UnidadProductiva undProduct : listaUnidadProd) {

				UnidadProductivaDTO unidadProductivaDto = new UnidadProductivaDTO();
				List<Predio> listaPrediosDto = new ArrayList<>();

				listaPrediosDto.removeAll(listaPrediosDto);
				unidadProductivaDto = new UnidadProductivaDTO();
				unidadProductivaDto.setAltitud(undProduct.getAltitud());
				unidadProductivaDto.setAreaProtegida(undProduct.getAreaProtegida());
				unidadProductivaDto.setAreaProyecto(undProduct.getAreaProyecto());
				unidadProductivaDto.setAspectosClimaticos(undProduct.getAspectosClimaticos());
				unidadProductivaDto.setCondicionViaAcceso(undProduct.getCondicionViaAcceso());

				unidadProductivaDto.setFuenteHidricaExistente(undProduct.getFuenteHidricaExistente());
				unidadProductivaDto.setHumedadRelativa(undProduct.getHumedadRelativa());
				unidadProductivaDto.setIdCiudad(undProduct.getIdCiudad());
				unidadProductivaDto.setIdDepartamento(undProduct.getIdDepartamento());
				unidadProductivaDto.setIdUnidadProductiva(undProduct.getIdUnidadProductiva());
				unidadProductivaDto.setInfraAlmacenamientoAgua(undProduct.getInfraestAlmacenamientoAgua());
				unidadProductivaDto.setLatitud(undProduct.getLatitud());
				unidadProductivaDto.setLongitud(undProduct.getLongitud());
				unidadProductivaDto.setLugarInversion(undProduct.getLugarInversion());
				unidadProductivaDto.setMateriaOrganica(undProduct.getMateriaOrganica());
				unidadProductivaDto.setNivelDrenaje(undProduct.getNivelDrenaje());
				unidadProductivaDto.setNombre(undProduct.getNombre());
				unidadProductivaDto.setNombreDistritoRiego(undProduct.getNombreDistritoRiego());

				for (Predio predio : undProduct.getPredios()) {
					predioDto = new Predio();
					predioDto.setAreaProdcutiva(predio.getAreaProdcutiva());
					predioDto.setAreaTotal(predio.getAreaTotal());
					predioDto.setFormaLlegar(predio.getFormaLlegar());
					predioDto.setFuenteInformacion(predio.getFuenteInformacion());
					predioDto.setIdCiudad(predio.getIdCiudad());
					predioDto.setIdDepartamento(predio.getIdDepartamento());
					predioDto.setIdPredio(predio.getIdPredio());
					predioDto.setNumeroFolio(predio.getNumeroFolio());
					predioDto.setNombreMatriculaInmobiliaria(predio.getNombreMatriculaInmobiliaria());
					predioDto.setTenencia(predio.getTenencia());
					predioDto.setUnidad(predio.getUnidad());
					predioDto.setVereda(predio.getVereda());

					Unidad unidad = new Unidad();

					if (predio.getIdUnidadPredio() != null) {

						unidad.setNombre(predio.getIdUnidadPredio().getNombre());
						unidad.setIdUnidad(predio.getIdUnidadPredio().getIdUnidad());

					} else {
						unidad.setNombre("Hectárea");
						unidad.setIdUnidad(1);

					}

					predioDto.setIdUnidadPredio(unidad);

					listaPrediosDto.add(predioDto);
				}
				unidadProductivaDto.setPredios(listaPrediosDto);
				listaUnidadProdDto.add(unidadProductivaDto);
			}

			respuesta = new RespuestaRest("200", "", listaUnidadProdDto);
		} catch (SQLException e) {
			respuesta = new RespuestaRest("500", e.getMessage(), null);
			e.printStackTrace();
		}

		return respuesta;
	}

	@Path("actualizarCaracteristicasUnidad")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaRest actualizar(UnidadProductivaDTO unidadProductivaDTO) {
		try {
			iUnidadProductiva.actualizarUnidadProductiva(unidadProductivaDTO);
			return new RespuestaRest("200",
					"Unidad productiva " + unidadProductivaDTO.getIdUnidadProductiva() + " actualizada. ", "ok");
		} catch (NegocioException e) {
			return new RespuestaRest("500", e.getMessage(), null);
		}
	}
}
