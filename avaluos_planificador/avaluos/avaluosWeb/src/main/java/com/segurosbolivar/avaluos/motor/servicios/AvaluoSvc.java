package com.segurosbolivar.avaluos.motor.servicios;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.primefaces.model.SortOrder;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.modelo.cons.TipoAvaluo;
import com.segurosbolivar.avaluos.modelo.cons.TipoInforme;
import com.segurosbolivar.avaluos.modelo.data.AvaluoDao;
import com.segurosbolivar.avaluos.modelo.dto.ConsultaAvaluoDto;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.ComportamientoOfertaDemanda;
import com.segurosbolivar.avaluos.modelo.entity.CondicionesSalubridad;
import com.segurosbolivar.avaluos.modelo.entity.InformacionBarrio;
import com.segurosbolivar.avaluos.modelo.entity.InformacionConstruccion;
import com.segurosbolivar.avaluos.modelo.entity.InformacionInmueble;
import com.segurosbolivar.avaluos.modelo.entity.LiquidacionAvaluo;
import com.segurosbolivar.avaluos.modelo.entity.LiquidacionAvaluoTotal;
import com.segurosbolivar.avaluos.modelo.entity.ListaAnexosPdf;
import com.segurosbolivar.avaluos.modelo.entity.Observaciones;
import com.segurosbolivar.avaluos.modelo.facade.IAvaluoFacade;
import com.segurosbolivar.avaluos.modelo.service.IParametrizacion;
import com.segurosbolivar.avaluos.modelo.service.ISeguridad;
import com.segurosbolivar.avaluos.modelo.ws.dto.fca.Usuario;
import com.segurosbolivar.avaluos.motor.dto.AvaluoDTO;
import com.segurosbolivar.avaluos.motor.dto.BarrioAvaluoDTO;
import com.segurosbolivar.avaluos.motor.dto.ConstruccionAvaluoDTO;
import com.segurosbolivar.avaluos.motor.dto.InmuebleAvaluoDTO;
import com.segurosbolivar.avaluos.motor.dto.LiquidacionAvaluoDTO;
import com.segurosbolivar.avaluos.motor.dto.ObservacionesAvaluoDTO;
import com.segurosbolivar.avaluos.motor.dto.OfertaDemandaAvaluoDTO;
import com.segurosbolivar.avaluos.motor.dto.RegistroFotograficoDTO;
import com.segurosbolivar.avaluos.motor.dto.ResponseDTO;

import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;

@Path("/consulta")
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class AvaluoSvc {

	private ConsultaAvaluoDto consulta;
	private List<Avaluo> listAvaluos;
	private UsuarioDto usuario;

	@EJB
	private transient IAvaluoFacade avaluoSvc;

	@EJB
	private IParametrizacion parametrizacion;

	@EJB
	public transient IAvaluoFacade avaluoFacade;

	@EJB
	private transient ISeguridad seguridadSvc;

	@EJB
	private AvaluoDao avaluoDao;

	@Resource
	private UserTransaction tx;

	@GET
	@Path("/consultarAvaluo/{matricula}/{estado}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseDTO consultaAvaluo(@PathParam("matricula") String matricula, @PathParam("estado") Long estado) {

		ResponseDTO respuesta = new ResponseDTO();
		consulta = new ConsultaAvaluoDto();
		consulta.setMatricula(matricula);
		consulta.setEstadoAvaluo(estado);
		AvaluoDTO avaluoResponse = new AvaluoDTO();

		try {
			listAvaluos = avaluoSvc.consultarAvaluoPorMatricula(consulta, 0, 10, "fechaTransaccion",
					UtilJsf.resolverOrientacion(SortOrder.DESCENDING));
			if (listAvaluos.size() > 0) {
				avaluoResponse.setUsuario(Long.valueOf(listAvaluos.get(0).getUsuarioCreacion()));
				avaluoResponse.setNombreSolicitante(listAvaluos.get(0).getNombreSolicitante());
				avaluoResponse.setDocumentoIdentificacion(parametrizacion.getDominioByLowValue("TIPOIDENTIFICACION",
						listAvaluos.get(0).getIdTipoIdentificacion().toString()));
				avaluoResponse.setNoIdentificacion(listAvaluos.get(0).getNumeroIdentificacion());
				if (listAvaluos.get(0).getNombreBanco() != null)
					avaluoResponse.setEntidad(listAvaluos.get(0).getNombreBanco());
				if (listAvaluos.get(0).getFechaAvaluo() != null)
					avaluoResponse.setFechaAvaluo(listAvaluos.get(0).getFechaAvaluo());
				if (listAvaluos.get(0).getConsecutivoBanco() != null)
					avaluoResponse.setConsecutivoBanco(listAvaluos.get(0).getConsecutivoBanco().longValue());
				avaluoResponse.setObjetivo(parametrizacion.getDominioByLowValue("OBJETOAVALUO",
						listAvaluos.get(0).getIdObjetoAvaluo().toString()));
				avaluoResponse.setDepartamento(parametrizacion
						.consultarDepartamento(listAvaluos.get(0).getIdDepartamento()).getDepartamento());
				avaluoResponse.setCiudad(parametrizacion.consultarCiudad(listAvaluos.get(0).getIdCiudad()).getCiudad());
				avaluoResponse.setBarrioVereda(listAvaluos.get(0).getBarrio());
				avaluoResponse.setDireccionInmueble(listAvaluos.get(0).getDireccionInmueble());
				if (listAvaluos.get(0).getDireccionComplementaria() != null)
					avaluoResponse.setDireccionComplementaria(listAvaluos.get(0).getDireccionComplementaria());
				if (listAvaluos.get(0).getNombreConjuntoEdificio() != null)
					avaluoResponse.setNombreConjuntoEdificio(listAvaluos.get(0).getNombreConjuntoEdificio());
				avaluoResponse.setSector(
						parametrizacion.getDominioByLowValue("SECTOR", listAvaluos.get(0).getSector().toString()));
				// avaluoResponse.setMapa(listAvaluos.get(0).getUbicacionGps());
				avaluoResponse.setLatitud(listAvaluos.get(0).getLatitudSFBUA());
				avaluoResponse.setLongitud(listAvaluos.get(0).getLongitudSFBUA());
				avaluoResponse.setTelefonoFijo(listAvaluos.get(0).getTelefonoSolicitante());
				avaluoResponse.setTelefonoCelular(listAvaluos.get(0).getCelularSolicitante());
				avaluoResponse.setEmail(listAvaluos.get(0).getCorreoSolicitante());
				avaluoResponse.setMetodologia(parametrizacion.getDominioByLowValue("METODOLOGIA",
						listAvaluos.get(0).getIdMetodologia().toString()));
				avaluoResponse.setJustificaconMetodologia(listAvaluos.get(0).getJustificacion());
				avaluoResponse.setProyectoConstructor(listAvaluos.get(0).getTipoInforme() != null
						&& TipoInforme.PROYECTO.getValor().compareTo(listAvaluos.get(0).getTipoInforme()) == 0 ? "SI"
								: "NO");
				avaluoResponse.setProvisional(listAvaluos.get(0).getCodTipoAvaluo() != null
						&& listAvaluos.get(0).getCodTipoAvaluo() == TipoAvaluo.PROVISIONAL.getValor() ? "SI" : "NO");
				avaluoResponse.setProcedencia(parametrizacion.getDominioByLowValue("PROCEDENCIA_AVALUO",
						listAvaluos.get(0).getCodigoProcedencia().toString()));
				avaluoResponse.setEstado(parametrizacion.getDominioByLowValue("ESTADOAVALUO",
						listAvaluos.get(0).getEstadoAvaluo().toString()));
				avaluoResponse.setIdAvaluo(listAvaluos.get(0).getIdAvaluo());

				InformacionBarrio entidadBarrio = avaluoFacade.consultarBarrio(listAvaluos.get(0).getIdAvaluo());
				BarrioAvaluoDTO barrio = new BarrioAvaluoDTO();

				barrio.setAcueductoSector(valorCadenaSiNo(entidadBarrio.getAcueductoSector()));
				barrio.setAlcantarilladoSector(valorCadenaSiNo(entidadBarrio.getAlcantarilladoSector()));
				barrio.setElectricidadSector(valorCadenaSiNo(entidadBarrio.getElectricidadSector()));
				barrio.setGasSector(valorCadenaSiNo(entidadBarrio.getGasSector()));
				barrio.setTelefonoSector(valorCadenaSiNo(entidadBarrio.getTelefonoSector()));
				barrio.setAcueductoPredio(valorCadenaSiNo(entidadBarrio.getAcueductoPredio()));
				barrio.setAlcantarilladoPredio(valorCadenaSiNo(entidadBarrio.getAlcantarilladoPredio()));
				barrio.setElectricidadPredio(valorCadenaSiNo(entidadBarrio.getElectricidadPredio()));
				barrio.setGasSectorPredio(valorCadenaSiNo(entidadBarrio.getGasPredio()));
				barrio.setTelefonoPredio(valorCadenaSiNo(entidadBarrio.getTelefonoPredio()));
				barrio.setEdificacionesSimilares(entidadBarrio.getEdificacionesIguales());
				barrio.setPavimentada(valorCadenaSiNo(entidadBarrio.getPavimentada()));
				barrio.setAndenes(valorCadenaSiNo(entidadBarrio.getAndenes()));
				barrio.setSardineles(valorCadenaSiNo(entidadBarrio.getSardeneles()));
				barrio.setEstado(
						parametrizacion.getDominioByLowValue("MBR", entidadBarrio.getEstadosViaAcceso().toString()));
				barrio.setParques(valorCadenaSiNo(entidadBarrio.getParques()));
				barrio.setParadero(valorCadenaSiNo(entidadBarrio.getParadero()));
				barrio.setAlumbrado(valorCadenaSiNo(entidadBarrio.getAlumbrado()));
				barrio.setZonasVerdes(valorCadenaSiNo(entidadBarrio.getZonasVerdes()));
				barrio.setArborizacion(valorCadenaSiNo(entidadBarrio.getArborizacion()));
				barrio.setAlamedas(valorCadenaSiNo(entidadBarrio.getAlamedas()));
				barrio.setCicloRutas(valorCadenaSiNo(entidadBarrio.getCicloRutas()));
				barrio.setEstrato(
						parametrizacion.getDominioByLowValue("ESTRATO", entidadBarrio.getEstrato().toString()));
				barrio.setLegalidad(
						parametrizacion.getDominioByLowValue("LEGALBARRIO", entidadBarrio.getLegalidad().toString()));
				barrio.setTopografia(
						parametrizacion.getDominioByLowValue("TOPOGRAFIA", entidadBarrio.getTopografia().toString()));
				barrio.setTransporte(
						parametrizacion.getDominioByLowValue("MBR", entidadBarrio.getTransporte().toString()));
				barrio.setPerspectivasValorizacion(entidadBarrio.getPerspectivas());
				if (entidadBarrio.getIndustria() != null && entidadBarrio.getIndustria().compareTo(1L) == 0) {
					barrio.setUsoPredominante("Industria");
				} else if (entidadBarrio.getComercio() != null && entidadBarrio.getComercio().compareTo(1L) == 0) {
					barrio.setUsoPredominante("Comercio");
				} else {
					barrio.setUsoPredominante("Vivienda");
				}

				avaluoResponse.setBarrio(barrio);

				InformacionInmueble entidadInmueble = avaluoFacade.consultarInmueble(listAvaluos.get(0).getIdAvaluo());
				InmuebleAvaluoDTO inmueble = new InmuebleAvaluoDTO();
				inmueble.setUso(parametrizacion.getDominioByLowValue("USOINMUEBLE",
						entidadInmueble.getUsoInmueble().toString()));
				if (entidadInmueble.getOtroUsoInmueble() != null)
					inmueble.setOtroUso(entidadInmueble.getOtroUsoInmueble());
				if (entidadInmueble.getClaseInmueble() != null)
					inmueble.setClase(parametrizacion.getDominioByLowValue("CLASEINMUEBLE",
							entidadInmueble.getClaseInmueble().toString()));
				if (entidadInmueble.getOtroClase() != null)
					inmueble.setOtraClase(entidadInmueble.getOtroClase());
				inmueble.setCategoria(
						parametrizacion.getDominioByLowValue("CATEGORIA", entidadInmueble.getIdCategoria().toString()));
				inmueble.setUbicacion(
						parametrizacion.getDominioByLowValue("UBICACION2", entidadInmueble.getUbicacion2().toString()));
				inmueble.setConstruidoUsoActual(
						parametrizacion.getDominioByLowValue("AFIRMAR", entidadInmueble.getEdiContUso().toString()));
				inmueble.setNotaria(entidadInmueble.getNotaria());
				inmueble.setNumeroEscritura(entidadInmueble.getNumeroEscritura());
				inmueble.setDepartamentoEscritura(parametrizacion
						.consultarDepartamento(entidadInmueble.getDepartamentoEscritura()).getDepartamento());
				inmueble.setCiudadEscritura(
						parametrizacion.consultarCiudad(entidadInmueble.getCiudadEscritura()).getCiudad());
				inmueble.setFechaEscritura(entidadInmueble.getFechaEscritura());
				inmueble.setMatriculaInmobiliaria(entidadInmueble.getMatriculaInmobiliariaPpal1());
				if (entidadInmueble.getMatriculaInmobiliariaGaraje1() != null)
					inmueble.setMatrículaInmobiliariaGaraje(entidadInmueble.getMatriculaInmobiliariaGaraje1());
				if (entidadInmueble.getMatriculaInmobiliariaDeposito1() != null)
					inmueble.setMatrículaInmobiliariaDeposito(entidadInmueble.getMatriculaInmobiliariaDeposito1());
				if (entidadInmueble.getChip() != null)
					inmueble.setChip(entidadInmueble.getChip());
				if (entidadInmueble.getCatastralSF() != null)
					inmueble.setNumeroCatastral(entidadInmueble.getCatastralSF());

				avaluoResponse.setInmueble(inmueble);

				ComportamientoOfertaDemanda entidadOfertaDemanda = avaluoFacade
						.consultarOferta(listAvaluos.get(0).getIdAvaluo());
				OfertaDemandaAvaluoDTO ofertaDemanda = new OfertaDemandaAvaluoDTO();

				ofertaDemanda.setTiempoComercializacion(entidadOfertaDemanda.getTipoComercializacion());
				ofertaDemanda.setActualidadEdificadora(entidadOfertaDemanda.getActualidadEdificadora());
				ofertaDemanda.setComportamiento(entidadOfertaDemanda.getComportamiento());

				avaluoResponse.setOfertaDemanda(ofertaDemanda);

				List<LiquidacionAvaluo> lisLiquidacion = avaluoFacade
						.consultarLiquidacion(listAvaluos.get(0).getIdAvaluo());
				List<LiquidacionAvaluoDTO> liquidacionAvaluo = new ArrayList<LiquidacionAvaluoDTO>();
				LiquidacionAvaluoTotal entidadTotal = avaluoFacade
						.consultarLiquidacionTotal(listAvaluos.get(0).getIdAvaluo());

				for (LiquidacionAvaluo liquidacion : lisLiquidacion) {
					LiquidacionAvaluoDTO liquidacionA = new LiquidacionAvaluoDTO();

					if (liquidacion.getDescripcionLiquidacion() != null) {
						liquidacionA.setDescripcionLiquidacion(parametrizacion.getDominioByLowValue("DESCLIQUIDACION",
								liquidacion.getDescripcionLiquidacion().toString()));
					}
					if (liquidacion.getDescripcionDependencia() != null)
						liquidacionA.setDescripcionDependencia(liquidacion.getDescripcionDependencia());
					liquidacionA.setArea(liquidacion.getAreaLiquidacion());
					liquidacionA.setValorUnitario(liquidacion.getValor());
					liquidacionA.setValorTotal(liquidacion.getValorTotal());
					liquidacionA.setTipoVivienda(parametrizacion.getDominioByLowValue("TIPOVIVIENDA",
							entidadInmueble.getTipoVivienda().toString()));
					liquidacionA.setGarantia(parametrizacion.getDominioByLowValue("CALGARANTIA",
							entidadTotal.getCalificacion().toString()));
					liquidacionA.setMetodologia(parametrizacion.getDominioByLowValue("METODOLOGIA",
							listAvaluos.get(0).getIdMetodologia().toString()));

					liquidacionAvaluo.add(liquidacionA);

				}
				avaluoResponse.setLiquidacionAvaluo(liquidacionAvaluo);

				Observaciones entidadObservaciones = avaluoFacade
						.consultarObservacion(listAvaluos.get(0).getIdAvaluo());
				ObservacionesAvaluoDTO observaciones = new ObservacionesAvaluoDTO();
				if (entidadObservaciones.getDireccionAnexos() != null)
					observaciones.setDireccionAnexos(entidadObservaciones.getDireccionAnexos());
				if (entidadObservaciones.getOtrasDirecciones() != null)
					observaciones.setOtrasDirecciones(entidadObservaciones.getOtrasDirecciones());
				if (entidadObservaciones.getObservacionAvaluo() != null)
					observaciones.setObservacionesAvaluo(entidadObservaciones.getObservacionAvaluo());

				avaluoResponse.setObservaciones(observaciones);

				InformacionConstruccion entidadConstruccion = avaluoFacade
						.consultarConstruccion(listAvaluos.get(0).getIdAvaluo());
				CondicionesSalubridad entidadSalubridad = avaluoFacade
						.consultarSalubridad(listAvaluos.get(0).getIdAvaluo());

				ConstruccionAvaluoDTO construccion = new ConstruccionAvaluoDTO();

				construccion.setNumeroPisos(entidadConstruccion.getPisos());
				construccion.setNumeroSotanos(entidadConstruccion.getSotanos());
				construccion.setPisoInmueble(entidadConstruccion.getPisoInmueble());
				construccion.setCercaFuenteHidrica(parametrizacion.getDominioByLowValue("AFIRMAR",
						entidadConstruccion.getUbicacionFuentesHidricas().toString()));
				construccion.setEdificacionVsvia(parametrizacion.getDominioByLowValue("NIVEL",
						entidadConstruccion.getUbicacionNivelVia().toString()));
				construccion.setFinanciadoConstructor(
						valorCadenaSiNo(entidadConstruccion.getCodigoFinanciadoConstructor()));
				construccion.setConstructora(entidadConstruccion.getCodigoNombreConstructora() != null
						? parametrizacion.getDominioByLowValue("NOMBRECONSTRUCTORA",
								entidadConstruccion.getCodigoNombreConstructora().toString())
						: null);
				construccion.setNombreConstructora(entidadConstruccion.getNombreConstructora());
				construccion.setEstadoConstruccion(parametrizacion.getDominioByLowValue("ESTADOCONSTRUCCION",
						entidadConstruccion.getEstadoConstruccion().toString()));
				construccion.setTerminado(valorCadenaSiNo(entidadConstruccion.getEstadoTerminadaNueva()));
				construccion.setEnObra(valorCadenaSiNo(entidadConstruccion.getEnObra()));
				construccion.setAvanceObra(entidadConstruccion.getAvanceObra());
				construccion.setRemodelado(valorCadenaSiNo(entidadConstruccion.getEstadoRemodelacion()));
				construccion.setSinTerminar(valorCadenaSiNo(entidadConstruccion.getSinTerminar()));
				construccion.setEstadoConservacion(parametrizacion.getDominioByLowValue("ESTADOCONSERVACION",
						entidadConstruccion.getEstadoConservacion().toString()));
				construccion.setParapetosCubierta(parametrizacion.getDominioByLowValue("AFIRMAR",
						entidadConstruccion.getParapetosCubierta().toString()));
				construccion.setEstructuraReforzada(parametrizacion.getDominioByLowValue("ESTRUCTURAREFORZADA_BUA",
						entidadConstruccion.getEstructuraReforzadaBUA().toString()));
				construccion.setEstructura(parametrizacion.getDominioByLowValue("ESTRUCTURA",
						entidadConstruccion.getEstructura().toString()));
				construccion.setMaterialEstructura(parametrizacion.getDominioByLowValue("ESTRUCTURA2_BUA",
						entidadConstruccion.getMaterialConstruccionBUA().toString()));
				construccion.setDetalleMaterial(parametrizacion.getDominioByLowValue("C_ESTRUCTURA_SF_BUA",
						entidadConstruccion.getDetalleMaterialSFBUA().toString()));
				construccion.setIrregularidadPlanta(parametrizacion.getDominioByLowValue("IRREGULARIDAD_BUA",
						entidadConstruccion.getIrregularidadPlantaBUA().toString()));
				construccion.setIrregularidadAltura(parametrizacion.getDominioByLowValue("IRREGULARIDAD_BUA",
						entidadConstruccion.getIrregularidadAlturaBUA().toString()));
				construccion.setFachada(
						parametrizacion.getDominioByLowValue("FACHADA", entidadConstruccion.getFachada().toString()));
				construccion.setTipoFachada(parametrizacion.getDominioByLowValue("TIPOFACHADA",
						entidadConstruccion.getTipoFachada().toString()));
				construccion.setCubierta(
						parametrizacion.getDominioByLowValue("CUBIERTA", entidadConstruccion.getCubierta().toString()));
				construccion.setGolpeteo(
						parametrizacion.getDominioByLowValue("GOLPETEO", entidadConstruccion.getGolpeteo().toString()));
				construccion.setDanoPrevio(parametrizacion.getDominioByLowValue("DANOSPREVIOS_BUA",
						entidadConstruccion.getDanoPrevioBUA().toString()));
				construccion.setReparados(parametrizacion.getDominioByLowValue("REPARADOS_BUA",
						entidadConstruccion.getReparadosBUA().toString()));
				if (entidadConstruccion.getVetustez() != null) {
					construccion.setVetustez(entidadConstruccion.getVetustez().longValue());
				}
				construccion.setSala(entidadConstruccion.getSala());
				construccion.setComedor(entidadConstruccion.getComedor());
				construccion.setEstudio(entidadConstruccion.getEstudio());
				construccion.setBanioSocial(entidadConstruccion.getBanioSocial());
				construccion.setEstar(entidadConstruccion.getEstarHabitacion());
				construccion.setHabitaciones(entidadConstruccion.getNumeroHabitaciones());
				construccion.setBanioPrivado(entidadConstruccion.getBanioPrivado());
				construccion.setCocina(entidadConstruccion.getCocina());
				construccion.setCuartoServicio(entidadConstruccion.getCuartoServicio());
				construccion.setBanioServicio(entidadConstruccion.getBanioServicio());
				construccion.setPatioInterior(entidadConstruccion.getPatioInterior());
				construccion.setTerraza(entidadConstruccion.getTerraza());
				construccion.setJardin(entidadConstruccion.getJardin());
				construccion.setBalcon(entidadConstruccion.getBalcon());
				construccion.setzVerdePrivada(entidadConstruccion.getZonaVerdePrivada());
				construccion.setTotalGarajes(entidadConstruccion.getTotalGarajes());
				construccion.setCubierto(entidadConstruccion.getCubierto());
				construccion.setDescubierto(entidadConstruccion.getDescubierto());
				construccion.setUsoExclusivo(entidadConstruccion.getUsoExclusivo());
				construccion.setPrivado(entidadConstruccion.getPrivado());
				construccion.setBahiaComun(entidadConstruccion.getBahiaComunal());
				construccion.setSencillo(entidadConstruccion.getSencillo());
				construccion.setDoble(entidadConstruccion.getDoble());
				construccion.setServidumbre(entidadConstruccion.getServidumbre());
				construccion.setDeposito(entidadConstruccion.getDeposito());
				construccion.setLocal(entidadConstruccion.getLocal());
				construccion.setBodega(entidadConstruccion.getBodega());
				construccion.setOficina(entidadConstruccion.getOficina());
				construccion.setVentilacion(
						parametrizacion.getDominioByLowValue("MBR", entidadConstruccion.getVentilacion().toString()));
				construccion.setIluminacion(
						parametrizacion.getDominioByLowValue("MBR", entidadConstruccion.getIluminacion().toString()));
				construccion.setRequiereCondicionesSalubridad(
						valorCadenaSiNo(entidadSalubridad.getRequiereCondicionesSalubridad()));
				construccion.setCondicionesSalubridad(parametrizacion.getDominioByLowValue("MBR",
						entidadSalubridad.getCondicionSalubridad().toString()));
				construccion.setArborizacion(valorCadenaSiNo(entidadSalubridad.getAmbientalArborizacion()));
				construccion.setParques(valorCadenaSiNo(entidadSalubridad.getAmbientalParques()));
				construccion.setZonaVerde(valorCadenaSiNo(entidadSalubridad.getAmbientalZonaVerde()));
				construccion.setImpactoNegativoPorAire(valorCadenaSiNo(entidadSalubridad.getImpactoNegativoPorAire()));
				construccion.setImpactoNegativoPorBasura(valorCadenaSiNo(entidadSalubridad.getImpactoNegativoBasura()));
				construccion.setImpactoNegativoPorInseguridad(
						valorCadenaSiNo(entidadSalubridad.getImpactoNegativoInseguridad()));
				construccion.setImpactoNegativoRuido(valorCadenaSiNo(entidadSalubridad.getImpactoNegativoRuido()));
				construccion.setAguasServidas(valorCadenaSiNo(entidadSalubridad.getAguasServidas()));
				if (entidadSalubridad.getImpactoNegativoOtros() != null)
					construccion.setImpactoNegativoOtros(entidadSalubridad.getImpactoNegativoOtros());
				construccion.setEstadoPisos(parametrizacion.getDominioByLowValue("INFOCONSTR",
						entidadConstruccion.getEstadoPisos().toString()));
				construccion.setEstadoMuros(parametrizacion.getDominioByLowValue("INFOCONSTR",
						entidadConstruccion.getEstadoMuros().toString()));
				construccion.setEstadoTechos(parametrizacion.getDominioByLowValue("INFOCONSTR",
						entidadConstruccion.getEstructuraTechos().toString()));
				construccion.setEstadoMadera(parametrizacion.getDominioByLowValue("INFOCONSTR",
						entidadConstruccion.getEstadoMadera().toString()));
				construccion.setEstadoMetal(parametrizacion.getDominioByLowValue("INFOCONSTR",
						entidadConstruccion.getEstadoMetal().toString()));
				construccion.setEstadoBanios(parametrizacion.getDominioByLowValue("INFOCONSTR",
						entidadConstruccion.getEstadoBanios().toString()));
				construccion.setEstadoCocina(parametrizacion.getDominioByLowValue("INFOCONSTR",
						entidadConstruccion.getEstadoCocina().toString()));
				construccion.setCalidadPisos(parametrizacion.getDominioByLowValue("CALIDAD",
						entidadConstruccion.getCalidadPiso().toString()));
				construccion.setCalidadMuros(parametrizacion.getDominioByLowValue("CALIDAD",
						entidadConstruccion.getCalidadMuro().toString()));
				construccion.setCalidadTechos(parametrizacion.getDominioByLowValue("CALIDAD",
						entidadConstruccion.getCalidadTecho().toString()));
				construccion.setCalidadMadera(parametrizacion.getDominioByLowValue("CALIDAD",
						entidadConstruccion.getCalidadMadera().toString()));
				construccion.setCalidadMetal(parametrizacion.getDominioByLowValue("CALIDAD",
						entidadConstruccion.getCalidMetal().toString()));
				construccion.setCalidadBanios(parametrizacion.getDominioByLowValue("CALIDAD",
						entidadConstruccion.getCalidadBanio().toString()));
				construccion.setCalidadCocina(parametrizacion.getDominioByLowValue("COCINA",
						entidadConstruccion.getCalidadCocina().toString()));
				construccion.setSometidoPropiedadHorizontal(parametrizacion.getDominioByLowValue("SOMETIDOPROPIEDAD",
						entidadConstruccion.getPropiedadHorizontal().toString()));
				construccion.setConjuntoAgrupacionCerrada(
						valorCadenaSiNo(entidadConstruccion.getConjuntoAgrupacionCerrada()));
				construccion.setUbicacionInmueble(parametrizacion.getDominioByLowValue("UBICACION",
						entidadConstruccion.getUbicacionInmueble().toString()));
				construccion.setNumeroEdificios(entidadConstruccion.getNumeroEdificios());
				construccion.setUnidadesPorPiso(entidadConstruccion.getUnidadPorPiso());
				construccion.setTotalUnidades(entidadConstruccion.getTotalUnidades());
				construccion.setTipoAdministrador(parametrizacion.getDominioByLowValue("TIPO_ADMINISTRADOR",
						entidadConstruccion.getTipoAdministrador() != null
								? entidadConstruccion.getTipoAdministrador().toString()
								: "1"));
				construccion.setNombreAdministrador(entidadConstruccion.getNombreAdministrador());
				construccion.setCorreoAdministrador(entidadConstruccion.getCorreoAdministrador());
				construccion.setTelefonoAdministrador(entidadConstruccion.getTelefonoAdministrador());
				construccion.setPorteria(valorCadenaSiNo(entidadConstruccion.getPorteria()));
				construccion.setCitofono(valorCadenaSiNo(entidadConstruccion.getCitofono()));
				construccion.setBicicletero(valorCadenaSiNo(entidadConstruccion.getBicicletero()));
				construccion.setSalonComunal(valorCadenaSiNo(entidadConstruccion.getSalonComunal()));
				construccion.setPiscina(valorCadenaSiNo(entidadConstruccion.getPiscina()));
				construccion.setTanqueAgua(valorCadenaSiNo(entidadConstruccion.getTanqueAgua()));
				construccion.setClubHouse(valorCadenaSiNo(entidadConstruccion.getClubHouse()));
				construccion.setPlantaElectrica(valorCadenaSiNo(entidadConstruccion.getPlanta()));
				construccion.setGarajeVisitantes(valorCadenaSiNo(entidadConstruccion.getGarajeVisitante()));
				construccion.setJuegoNinos(valorCadenaSiNo(entidadConstruccion.getJuegoNinos()));
				construccion.setCanchaMultiple(valorCadenaSiNo(entidadConstruccion.getCanchaMultiple()));
				construccion.setShutBasuras(valorCadenaSiNo(entidadConstruccion.getShutBasuras()));
				construccion.setBombaEyectora(valorCadenaSiNo(entidadConstruccion.getBombaEyectora()));
				construccion.setAireAcondicionado(valorCadenaSiNo(entidadConstruccion.getAireAcondicionado()));
				construccion.setSquash(valorCadenaSiNo(entidadConstruccion.getSquash()));
				construccion.setPresion(valorCadenaSiNo(entidadConstruccion.getPresion()));
				construccion.setZonasVerdes(valorCadenaSiNo(entidadConstruccion.getZonasVerdes()));
				construccion.setGimnasio(valorCadenaSiNo(entidadConstruccion.getGimnasio()));
				construccion.setGolfito(valorCadenaSiNo(entidadConstruccion.getGolfito()));
				construccion.setAscensor(valorCadenaSiNo(entidadConstruccion.getAscensor()));
				construccion.setCuantos(entidadConstruccion.getNumeroAscensores());
				construccion.setOtros(entidadConstruccion.getOtrosDotacion());

				avaluoResponse.setConstruccion(construccion);

				// si avaluo de motor el archivo fotográfico se encuentra en s3
				if (listAvaluos.get(0).getIndMotor() != null && listAvaluos.get(0).getIndMotor().equals("S")) {
					List<String> listaarchivos = avaluoFacade.consultarRegistroFotograficoS3(listAvaluos.get(0));
					if (listaarchivos != null && !listaarchivos.isEmpty()) {
						RegistroFotograficoDTO registroObj = new RegistroFotograficoDTO();
						registroObj.setIdFotografias(listaarchivos);
						avaluoResponse.setRegistroFotografico(registroObj);
					}
				} else {
					List<ListaAnexosPdf> lfotografias = avaluoFacade.consultarRegistroFotografico(listAvaluos.get(0),
							false);// ya vienen ordenadas por consecutivo
					if (lfotografias != null && !lfotografias.isEmpty()) {
						List<String> idsFotos = new ArrayList<>();
						for (ListaAnexosPdf anexoFoto : lfotografias) {
							if (anexoFoto.getArchivo() != null && anexoFoto.getArchivo().getIdDocumento() != null) {
								if (anexoFoto.isPortada()) {
									idsFotos.add(0, anexoFoto.getArchivo().getIdDocumento());
								} else {
									idsFotos.add(anexoFoto.getArchivo().getIdDocumento());
								}
							}
						}
						RegistroFotograficoDTO registroObj = new RegistroFotograficoDTO();
						registroObj.setIdFotografias(idsFotos);
						avaluoResponse.setRegistroFotografico(registroObj);
					}
				}

			} else {
				throw new NegocioException("No existe avalúos con matrícula " + matricula);
			}

			respuesta.setCodigoRespuesta("200");
			respuesta.setDatosRespuesta(avaluoResponse);
		} catch (NegocioException e) {
			respuesta.setCodigoRespuesta("500");
			respuesta.setMensajeRespuesta(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			respuesta.setCodigoRespuesta("500");
			respuesta.setMensajeRespuesta("Error en la consulta del Avalúo");
			e.printStackTrace();
		}

		return respuesta;
	}

	@POST
	@Path("/sincronizarAvaluo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseDTO sincronizarAvaluo(AvaluoDTO avaluoRequest) {

		Avaluo avaluoEntity = new Avaluo();
		ResponseDTO respuesta = new ResponseDTO();
		Long idAvaluo;

		consultarUsuario(avaluoRequest.getUsuario().toString());

		avaluoEntity.setUsuario(avaluoRequest.getUsuarioFirmaAvaluadora().toString());
		avaluoEntity.setCodigoBanco(39L);
		avaluoEntity.setFechaCreacion(new Date());
		avaluoEntity.setFechaTransaccion(new Date());
		avaluoEntity.setUsuarioCreacion(avaluoRequest.getUsuario().toString());
		avaluoEntity.setUsuarioTransaccion(avaluoRequest.getUsuario().toString());
		avaluoEntity.setNombreSolicitante(avaluoRequest.getNombreSolicitante());
		avaluoEntity.setIdTipoIdentificacion(Long.valueOf(avaluoRequest.getDocumentoIdentificacion()));
		avaluoEntity.setNumeroIdentificacion(avaluoRequest.getNoIdentificacion());
		if (avaluoRequest.getEntidad() != null)
			avaluoEntity.setNombreBanco(avaluoRequest.getEntidad());
		if (avaluoRequest.getFechaAvaluo() != null)
			avaluoEntity.setFechaAvaluo(avaluoRequest.getFechaAvaluo());
		if (avaluoRequest.getConsecutivoBanco() != null)
			avaluoEntity.setConsecutivoBanco(BigInteger.valueOf(avaluoRequest.getConsecutivoBanco()));
		avaluoEntity.setIdObjetoAvaluo(Long.valueOf(avaluoRequest.getObjetivo()));
		avaluoEntity.setIdDepartamento(Long.valueOf(avaluoRequest.getDepartamento()));
		avaluoEntity.setIdCiudad(Long.valueOf(avaluoRequest.getCiudad()));
		avaluoEntity.setBarrio(avaluoRequest.getBarrioVereda());
		avaluoEntity.setDireccionInmueble(avaluoRequest.getDireccionInmueble());
		if (avaluoRequest.getDireccionComplementaria() != null)
			avaluoEntity.setDireccionComplementaria(avaluoRequest.getDireccionComplementaria());
		if (avaluoRequest.getNombreConjuntoEdificio() != null)
			avaluoEntity.setNombreConjuntoEdificio(avaluoRequest.getNombreConjuntoEdificio());
		avaluoEntity.setSector(Long.valueOf(avaluoRequest.getSector()));
		if (avaluoRequest.getLatitud() != null)
			avaluoEntity.setLatitudSFBUA(avaluoRequest.getLatitud().toString());
		if (avaluoRequest.getLongitud() != null)
			avaluoEntity.setLongitudSFBUA(avaluoRequest.getLongitud().toString());
		avaluoEntity.setTelefonoSolicitante(avaluoRequest.getTelefonoFijo());
		avaluoEntity.setCelularSolicitante(avaluoRequest.getTelefonoCelular());
		avaluoEntity.setCorreoSolicitante(avaluoRequest.getEmail());
		avaluoEntity.setIdMetodologia(Long.valueOf(avaluoRequest.getMetodologia()));
		avaluoEntity.setJustificacion(avaluoRequest.getJustificaconMetodologia());
		avaluoEntity.setTipoInforme(
				avaluoRequest.getProyectoConstructor() != null && avaluoRequest.getProyectoConstructor() == "1"
						? TipoInforme.PROYECTO.getValor()
						: TipoInforme.CREDITO.getValor());
		avaluoEntity.setCodTipoAvaluo(avaluoRequest.getProvisional() != null && avaluoRequest.getProvisional() == "1"
				? TipoAvaluo.PROVISIONAL.getValor()
				: TipoAvaluo.DEFINITIVO.getValor());
		avaluoEntity.setCodigoProcedencia(Long.valueOf(avaluoRequest.getProcedencia()));
		avaluoEntity.setEstadoAvaluo(Long.valueOf(avaluoRequest.getEstado().toString()));
		avaluoEntity.setIdAvaluo(avaluoRequest.getIdAvaluo());
		avaluoEntity.setIndMotor("S");// indicador de motor de avaluo

		try {
			validarEntradas(avaluoRequest);
			if (avaluoRequest.getIdAvaluo() == null) {
				idAvaluo = guardarAvaluoGetId(avaluoEntity, usuario);
				avaluoEntity.setIdAvaluo(idAvaluo);
				avaluoRequest.setIdAvaluo(idAvaluo);
			}

			InformacionBarrio entidadBarrio = null;
			entidadBarrio = avaluoFacade
					.consultarBarrio(avaluoRequest.getIdAvaluo() != null ? avaluoRequest.getIdAvaluo() : null);

			if (entidadBarrio == null) {
				entidadBarrio = new InformacionBarrio();
				entidadBarrio.setIdAvaluo(avaluoRequest.getIdAvaluo());
				entidadBarrio.setAvaluo(avaluoEntity);
				if (usuario != null && usuario.getUsuario() != null) {
					entidadBarrio.setUsuarioCreacion(usuario.getUsuario().getCodigo());
					entidadBarrio.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
				}
				entidadBarrio.setFechaCreacion(new Date());
				entidadBarrio.setFechaTransaccion(new Date());
			}

			entidadBarrio.setAcueductoSector(Long.valueOf(avaluoRequest.getBarrio().getAcueductoSector()));
			entidadBarrio.setAlcantarilladoSector(Long.valueOf(avaluoRequest.getBarrio().getAlcantarilladoSector()));
			entidadBarrio.setElectricidadSector(Long.valueOf(avaluoRequest.getBarrio().getElectricidadSector()));
			entidadBarrio.setGasSector(Long.valueOf(avaluoRequest.getBarrio().getGasSector()));
			entidadBarrio.setTelefonoSector(Long.valueOf(avaluoRequest.getBarrio().getTelefonoSector()));
			entidadBarrio.setAcueductoPredio(Long.valueOf(avaluoRequest.getBarrio().getAcueductoPredio()));
			entidadBarrio.setAlcantarilladoPredio(Long.valueOf(avaluoRequest.getBarrio().getAlcantarilladoPredio()));
			entidadBarrio.setElectricidadPredio(Long.valueOf(avaluoRequest.getBarrio().getElectricidadPredio()));
			entidadBarrio.setGasPredio(Long.valueOf(avaluoRequest.getBarrio().getGasSectorPredio()));
			entidadBarrio.setTelefonoPredio(Long.valueOf(avaluoRequest.getBarrio().getTelefonoPredio()));
			entidadBarrio.setEdificacionesIguales(avaluoRequest.getBarrio().getEdificacionesSimilares());
			entidadBarrio.setPavimentada(Long.valueOf(avaluoRequest.getBarrio().getPavimentada()));
			entidadBarrio.setAndenes(Long.valueOf(avaluoRequest.getBarrio().getAndenes()));
			entidadBarrio.setSardeneles(Long.valueOf(avaluoRequest.getBarrio().getSardineles()));

			entidadBarrio.setEstadosViaAcceso(Long.valueOf(avaluoRequest.getBarrio().getEstado()));

			entidadBarrio.setParques(Long.valueOf(avaluoRequest.getBarrio().getParques()));
			entidadBarrio.setParadero(Long.valueOf(avaluoRequest.getBarrio().getParadero()));
			entidadBarrio.setAlumbrado(Long.valueOf(avaluoRequest.getBarrio().getAlumbrado()));
			entidadBarrio.setZonasVerdes(Long.valueOf(avaluoRequest.getBarrio().getZonasVerdes()));
			entidadBarrio.setArborizacion(Long.valueOf(avaluoRequest.getBarrio().getArborizacion()));
			entidadBarrio.setAlamedas(Long.valueOf(avaluoRequest.getBarrio().getAlamedas()));
			entidadBarrio.setCicloRutas(Long.valueOf(avaluoRequest.getBarrio().getCicloRutas()));
			entidadBarrio.setEstrato(Long.valueOf(avaluoRequest.getBarrio().getEstrato()));
			entidadBarrio.setLegalidad(Long.valueOf(avaluoRequest.getBarrio().getLegalidad()));
			entidadBarrio.setTopografia(Long.valueOf(avaluoRequest.getBarrio().getTopografia()));
			entidadBarrio.setTransporte(Long.valueOf(avaluoRequest.getBarrio().getTransporte()));
			entidadBarrio.setPerspectivas(avaluoRequest.getBarrio().getPerspectivasValorizacion());
			entidadBarrio.setVivienda(Long.valueOf(avaluoRequest.getBarrio().getUsoPredominante()));

			avaluoEntity.setInformacionBarrio(entidadBarrio);

			InformacionInmueble entidadInmueble = avaluoFacade
					.consultarInmueble(avaluoRequest.getIdAvaluo() != null ? avaluoRequest.getIdAvaluo() : null);

			if (entidadInmueble == null) {
				entidadInmueble = new InformacionInmueble();
				entidadInmueble.setIdAvaluo(avaluoRequest.getIdAvaluo());
				entidadInmueble.setAvaluo(avaluoEntity);
				if (usuario != null && usuario.getUsuario() != null) {
					entidadInmueble.setUsuarioCreacion(usuario.getUsuario().getCodigo());
					entidadInmueble.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
				}
				entidadInmueble.setFechaCreacion(new Date());
				entidadInmueble.setFechaTransaccion(new Date());
			}
			// validar campo
			entidadInmueble.setUsoInmueble(Long.valueOf(avaluoRequest.getInmueble().getUso()));

			if (avaluoRequest.getInmueble().getOtroUso() != null) {
				entidadInmueble.setOtroUsoInmueble(avaluoRequest.getInmueble().getOtroUso());
			}
			// validar clase
			entidadInmueble.setClaseInmueble(Long.valueOf(avaluoRequest.getInmueble().getClase()));
			if (avaluoRequest.getInmueble().getOtraClase() != null) {
				entidadInmueble.setOtroClase(avaluoRequest.getInmueble().getOtraClase());
			}
			// validar categoria
			entidadInmueble.setIdCategoria(Long.valueOf(avaluoRequest.getInmueble().getCategoria()));

			entidadInmueble.setUbicacion2(Long.valueOf(avaluoRequest.getInmueble().getUbicacion()));
			entidadInmueble.setEdiContUso(Long.valueOf(avaluoRequest.getInmueble().getConstruidoUsoActual()));
			entidadInmueble.setNotaria(avaluoRequest.getInmueble().getNotaria());
			entidadInmueble.setNumeroEscritura(avaluoRequest.getInmueble().getNumeroEscritura());
			entidadInmueble
					.setDepartamentoEscritura(Long.valueOf(avaluoRequest.getInmueble().getDepartamentoEscritura()));
			entidadInmueble.setCiudadEscritura(Long.valueOf(avaluoRequest.getInmueble().getCiudadEscritura()));
			entidadInmueble.setFechaEscritura(avaluoRequest.getInmueble().getFechaEscritura());
			entidadInmueble.setMatriculaInmobiliariaPpal1(avaluoRequest.getInmueble().getMatriculaInmobiliaria());

			if (avaluoRequest.getInmueble().getMatrículaInmobiliariaGaraje() != null) {
				entidadInmueble
						.setMatriculaInmobiliariaGaraje1(avaluoRequest.getInmueble().getMatrículaInmobiliariaGaraje());
			}
			if (avaluoRequest.getInmueble().getMatrículaInmobiliariaDeposito() != null)
				entidadInmueble.setMatriculaInmobiliariaDeposito1(
						avaluoRequest.getInmueble().getMatrículaInmobiliariaDeposito());
			if (avaluoRequest.getInmueble().getChip() != null) {
				entidadInmueble.setChip(avaluoRequest.getInmueble().getChip());
			}
			if (avaluoRequest.getInmueble().getNumeroCatastral() != null) {
				entidadInmueble.setCatastralSF(avaluoRequest.getInmueble().getNumeroCatastral());
			}
			if (avaluoRequest.getLiquidacionAvaluo().size() > 0) {
				entidadInmueble.setTipoVivienda(avaluoRequest.getLiquidacionAvaluo().get(0).getTipoVivienda() != null
						? Long.valueOf(avaluoRequest.getLiquidacionAvaluo().get(0).getTipoVivienda())
						: null);
			}

			avaluoEntity.setInformacionInmueble(entidadInmueble);

			ComportamientoOfertaDemanda entidadOfertaDemanda = avaluoFacade
					.consultarOferta(avaluoRequest.getIdAvaluo() != null ? avaluoRequest.getIdAvaluo() : null);

			if (entidadOfertaDemanda == null) {
				entidadOfertaDemanda = new ComportamientoOfertaDemanda();
				entidadOfertaDemanda.setIdAvaluo(avaluoRequest.getIdAvaluo());
				entidadOfertaDemanda.setAvaluo(avaluoEntity);
				if (usuario != null && usuario.getUsuario() != null) {
					entidadOfertaDemanda.setUsuarioCreacion(usuario.getUsuario().getCodigo());
					entidadOfertaDemanda.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
				}
				entidadOfertaDemanda.setFechaCreacion(new Date());
				entidadOfertaDemanda.setFechaTransaccion(new Date());
			}
			entidadOfertaDemanda.setTipoComercializacion(avaluoRequest.getOfertaDemanda().getTiempoComercializacion());
			entidadOfertaDemanda.setActualidadEdificadora(avaluoRequest.getOfertaDemanda().getActualidadEdificadora());
			entidadOfertaDemanda.setComportamiento(avaluoRequest.getOfertaDemanda().getComportamiento());

			avaluoEntity.setCompOfertaDemanda(entidadOfertaDemanda);

			List<LiquidacionAvaluo> lisLiquidacion = new ArrayList<LiquidacionAvaluo>();
			LiquidacionAvaluoTotal liquidacionTotal = null;

			liquidacionTotal = avaluoFacade.consultarLiquidacionTotal(
					avaluoRequest.getIdAvaluo() != null ? avaluoRequest.getIdAvaluo() : null);

			if (liquidacionTotal == null) {
				liquidacionTotal = new LiquidacionAvaluoTotal();
				liquidacionTotal.setIdAvaluo(avaluoRequest.getIdAvaluo() != null ? avaluoRequest.getIdAvaluo() : null);
				liquidacionTotal.setAvaluo(avaluoEntity);
				if (usuario != null && usuario.getUsuario() != null) {
					liquidacionTotal.setUsuarioCreacion(usuario.getUsuario().getCodigo());
					liquidacionTotal.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
				}
				liquidacionTotal.setFechaCreacion(new Date());
				liquidacionTotal.setFechaTransaccion(new Date());
			}

			if (avaluoRequest.getLiquidacionAvaluo().size() > 0) {
				liquidacionTotal.setCalificacion(avaluoRequest.getLiquidacionAvaluo().get(0).getGarantia() != null
						? Long.valueOf(avaluoRequest.getLiquidacionAvaluo().get(0).getGarantia())
						: null);
			}
			liquidacionTotal.setValorUvrDia(avaluoFacade.consultaUvr());

			
			long secuencia = 0;

			BigDecimal valorTotal =  new BigDecimal(0);
			for (LiquidacionAvaluoDTO liquidacion : avaluoRequest.getLiquidacionAvaluo()) {
				LiquidacionAvaluo liquidacionA = new LiquidacionAvaluo();

				liquidacionA.setConsecutivo(secuencia++);
				if (liquidacion.getDescripcionLiquidacion() != null)
					liquidacionA.setDescripcionLiquidacion(Long.valueOf(liquidacion.getDescripcionLiquidacion()));
				if (liquidacion.getDescripcionDependencia() != null)
					liquidacionA.setDescripcionDependencia(liquidacion.getDescripcionDependencia());
				liquidacionA.setAreaLiquidacion(liquidacion.getArea());
				liquidacionA.setValor(liquidacion.getValorUnitario());
				liquidacionA.setValorTotal(liquidacion.getValorTotal());
				liquidacionA.setIdAvaluo(avaluoRequest.getIdAvaluo());
				liquidacionA.setUsuarioCreacion(usuario.getUsuario().getCodigo());
				liquidacionA.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
				liquidacionA.setFechaCreacion(new Date());
				liquidacionA.setFechaTransaccion(new Date());
				lisLiquidacion.add(liquidacionA);
				valorTotal = valorTotal.add(liquidacion.getValorTotal());
			}
			if(liquidacionTotal.getIdLiqavaluoTotal() == null ||liquidacionTotal.getIdLiqavaluoTotal() == 0 ) {
				liquidacionTotal.setTotalAvaluo(valorTotal);
				liquidacionTotal.setValorAsegurable(valorTotal);
				liquidacionTotal.setAvaluoUvr(valorTotal.divide(liquidacionTotal.getValorUvrDia(), MathContext.DECIMAL128));
			}
			
			avaluoEntity.setLiquidacionTotal(liquidacionTotal);
			avaluoEntity.setLiquidacionAvaluos(lisLiquidacion);

			Observaciones entidadObservaciones = avaluoFacade.consultarObservacion(avaluoRequest.getIdAvaluo());

			if (entidadObservaciones == null) {
				entidadObservaciones = new Observaciones();
				entidadObservaciones
						.setIdAvaluo(avaluoRequest.getIdAvaluo() != null ? avaluoRequest.getIdAvaluo() : null);
				entidadObservaciones.setAvaluo(avaluoEntity);
				if (usuario != null && usuario.getUsuario() != null) {
					entidadObservaciones.setUsuarioCreacion(usuario.getUsuario().getCodigo());
					entidadObservaciones.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
				}
				entidadObservaciones.setFechaCreacion(new Date());
				entidadObservaciones.setFechaTransaccion(new Date());
			}

			if (avaluoRequest.getObservaciones().getDireccionAnexos() != null)
				entidadObservaciones.setDireccionAnexos(avaluoRequest.getObservaciones().getDireccionAnexos());
			if (avaluoRequest.getObservaciones().getOtrasDirecciones() != null)
				entidadObservaciones.setOtrasDirecciones(avaluoRequest.getObservaciones().getOtrasDirecciones());
			if (avaluoRequest.getObservaciones().getObservacionesAvaluo() != null)
				entidadObservaciones.setObservacionAvaluo(avaluoRequest.getObservaciones().getObservacionesAvaluo());

			avaluoEntity.setObservacion(entidadObservaciones);

			InformacionConstruccion entidadConstruccion = null;
			CondicionesSalubridad entidadSalubridad = null;

			entidadConstruccion = avaluoFacade.consultarConstruccion(avaluoRequest.getIdAvaluo());
			entidadSalubridad = avaluoFacade.consultarSalubridad(avaluoRequest.getIdAvaluo());

			if (entidadConstruccion == null) {
				entidadConstruccion = new InformacionConstruccion();
				entidadConstruccion.setIdAvaluo(avaluoRequest.getIdAvaluo());
				entidadConstruccion.setAvaluo(avaluoEntity);
				if (usuario != null && usuario.getUsuario() != null) {
					entidadConstruccion.setUsuarioCreacion(usuario.getUsuario().getCodigo());
					entidadConstruccion.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
				}
				entidadConstruccion.setFechaCreacion(new Date());
				entidadConstruccion.setFechaTransaccion(new Date());
			}

			if (entidadSalubridad == null) {
				entidadSalubridad = new CondicionesSalubridad();
				entidadSalubridad.setIdAvaluo(avaluoRequest.getIdAvaluo());
				entidadSalubridad.setAvaluo(avaluoEntity);
				if (usuario != null && usuario.getUsuario() != null) {
					entidadSalubridad.setUsuarioCreacion(usuario.getUsuario().getCodigo());
					entidadSalubridad.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
				}
				entidadSalubridad.setFechaCreacion(new Date());
				entidadSalubridad.setFechaTransaccion(new Date());
			}
			if (avaluoRequest.getConstruccion().getVetustez() != null)
				entidadConstruccion.setVetustez(BigDecimal.valueOf(avaluoRequest.getConstruccion().getVetustez()));
			entidadConstruccion.setPisos(avaluoRequest.getConstruccion().getNumeroPisos());
			entidadConstruccion.setSotanos(avaluoRequest.getConstruccion().getNumeroSotanos());
			entidadConstruccion.setPisoInmueble(avaluoRequest.getConstruccion().getPisoInmueble());

			entidadConstruccion
					.setUbicacionFuentesHidricas(Long.valueOf(avaluoRequest.getConstruccion().getCercaFuenteHidrica()));
			entidadConstruccion
					.setParapetosCubierta(Long.valueOf(avaluoRequest.getConstruccion().getParapetosCubierta()));

			entidadConstruccion
					.setUbicacionNivelVia(Long.valueOf(avaluoRequest.getConstruccion().getEdificacionVsvia()));
			entidadConstruccion
					.setCodigoNombreConstructora(Long.valueOf(avaluoRequest.getConstruccion().getConstructora()));
			entidadConstruccion.setNombreConstructora(avaluoRequest.getConstruccion().getNombreConstructora());
			entidadConstruccion
					.setEstadoConstruccion(Long.valueOf(avaluoRequest.getConstruccion().getEstadoConstruccion()));
			

			// valores si y no
			entidadConstruccion.setEstadoRemodelacion(valorSiNo(avaluoRequest.getConstruccion().getRemodelado()));
			entidadConstruccion.setSinTerminar(valorSiNo(avaluoRequest.getConstruccion().getSinTerminar()));
			entidadConstruccion.setEstadoTerminadaNueva(valorSiNo(avaluoRequest.getConstruccion().getTerminado()));
			entidadConstruccion.setEnObra(Long.valueOf(avaluoRequest.getConstruccion().getEnObra()));
			entidadConstruccion.setAvanceObra(avaluoRequest.getConstruccion().getAvanceObra());
			
			// Si el estado de construccion es usada se asume el estado terminado 
			if(Long.valueOf(avaluoRequest.getConstruccion().getEstadoConstruccion()) == 2) {
				entidadConstruccion.setEstadoTerminadoUsado(valorSiNo(avaluoRequest.getConstruccion().getTerminado()));
			}
			
			entidadConstruccion.setCodigoFinanciadoConstructor(
					Long.valueOf(avaluoRequest.getConstruccion().getFinanciadoConstructor()));

			entidadSalubridad.setRequiereCondicionesSalubridad(
					Long.valueOf(avaluoRequest.getConstruccion().getRequiereCondicionesSalubridad()));

			entidadSalubridad.setAmbientalArborizacion(valorSiNo(avaluoRequest.getConstruccion().getArborizacion()));
			entidadSalubridad.setAmbientalParques(valorSiNo(avaluoRequest.getConstruccion().getParques()));
			entidadSalubridad.setAmbientalZonaVerde(valorSiNo(avaluoRequest.getConstruccion().getZonaVerde()));
			entidadSalubridad
					.setImpactoNegativoPorAire(valorSiNo(avaluoRequest.getConstruccion().getImpactoNegativoPorAire()));
			entidadSalubridad
					.setImpactoNegativoBasura(valorSiNo(avaluoRequest.getConstruccion().getImpactoNegativoPorBasura()));
			entidadSalubridad.setImpactoNegativoInseguridad(
					valorSiNo(avaluoRequest.getConstruccion().getImpactoNegativoPorInseguridad()));
			entidadSalubridad
					.setImpactoNegativoRuido(valorSiNo(avaluoRequest.getConstruccion().getImpactoNegativoRuido()));
			entidadSalubridad.setAguasServidas(valorSiNo(avaluoRequest.getConstruccion().getAguasServidas()));

			entidadConstruccion.setPorteria(valorSiNo(avaluoRequest.getConstruccion().getPorteria()));
			entidadConstruccion.setCitofono(valorSiNo(avaluoRequest.getConstruccion().getCitofono()));
			entidadConstruccion.setBicicletero(valorSiNo(avaluoRequest.getConstruccion().getBicicletero()));
			entidadConstruccion.setSalonComunal(valorSiNo(avaluoRequest.getConstruccion().getSalonComunal()));
			entidadConstruccion.setPiscina(valorSiNo(avaluoRequest.getConstruccion().getPiscina()));
			entidadConstruccion.setTanqueAgua(valorSiNo(avaluoRequest.getConstruccion().getTanqueAgua()));
			entidadConstruccion.setClubHouse(valorSiNo(avaluoRequest.getConstruccion().getClubHouse()));
			entidadConstruccion.setPlanta(valorSiNo(avaluoRequest.getConstruccion().getPlantaElectrica()));
			entidadConstruccion.setGarajeVisitante(valorSiNo(avaluoRequest.getConstruccion().getGarajeVisitantes()));
			entidadConstruccion.setJuegoNinos(valorSiNo(avaluoRequest.getConstruccion().getJuegoNinos()));
			entidadConstruccion.setCanchaMultiple(valorSiNo(avaluoRequest.getConstruccion().getCanchaMultiple()));
			entidadConstruccion.setShutBasuras(valorSiNo(avaluoRequest.getConstruccion().getShutBasuras()));
			entidadConstruccion.setBombaEyectora(valorSiNo(avaluoRequest.getConstruccion().getBombaEyectora()));
			entidadConstruccion.setAireAcondicionado(valorSiNo(avaluoRequest.getConstruccion().getAireAcondicionado()));
			entidadConstruccion.setSquash(valorSiNo(avaluoRequest.getConstruccion().getSquash()));
			entidadConstruccion.setPresion(valorSiNo(avaluoRequest.getConstruccion().getPresion()));
			entidadConstruccion.setZonasVerdes(valorSiNo(avaluoRequest.getConstruccion().getZonasVerdes()));
			entidadConstruccion.setGimnasio(valorSiNo(avaluoRequest.getConstruccion().getGimnasio()));
			entidadConstruccion.setGolfito(valorSiNo(avaluoRequest.getConstruccion().getGolfito()));
			entidadConstruccion.setAscensor(valorSiNo(avaluoRequest.getConstruccion().getAscensor()));
			entidadConstruccion.setNumeroAscensores(Long.valueOf(avaluoRequest.getConstruccion().getCuantos()));
			// fin

			entidadConstruccion
					.setEstadoConservacion(Long.valueOf(avaluoRequest.getConstruccion().getEstadoConservacion()));
			entidadConstruccion
					.setEstructuraReforzadaBUA(Long.valueOf(avaluoRequest.getConstruccion().getEstructuraReforzada()));
			entidadConstruccion.setEstructura(Long.valueOf(avaluoRequest.getConstruccion().getEstructura()));
			entidadConstruccion
					.setMaterialConstruccionBUA(Long.valueOf(avaluoRequest.getConstruccion().getMaterialEstructura()));
			entidadConstruccion
					.setDetalleMaterialSFBUA(Long.valueOf(avaluoRequest.getConstruccion().getDetalleMaterial()));
			entidadConstruccion
					.setIrregularidadPlantaBUA(Long.valueOf(avaluoRequest.getConstruccion().getIrregularidadPlanta()));
			entidadConstruccion
					.setIrregularidadAlturaBUA(Long.valueOf(avaluoRequest.getConstruccion().getIrregularidadAltura()));
			entidadConstruccion.setFachada(Long.valueOf(avaluoRequest.getConstruccion().getFachada()));
			entidadConstruccion.setTipoFachada(Long.valueOf(avaluoRequest.getConstruccion().getTipoFachada()));
			entidadConstruccion.setCubierta(Long.valueOf(avaluoRequest.getConstruccion().getCubierta()));
			entidadConstruccion.setGolpeteo(Long.valueOf(avaluoRequest.getConstruccion().getGolpeteo()));
			entidadConstruccion.setDanoPrevioBUA(Long.valueOf(avaluoRequest.getConstruccion().getDanoPrevio()));
			entidadConstruccion.setReparadosBUA(Long.valueOf(avaluoRequest.getConstruccion().getReparados()));
			entidadConstruccion.setSala(avaluoRequest.getConstruccion().getSala());
			entidadConstruccion.setComedor(avaluoRequest.getConstruccion().getComedor());
			entidadConstruccion.setEstudio(avaluoRequest.getConstruccion().getEstudio());
			entidadConstruccion.setBanioSocial(avaluoRequest.getConstruccion().getBanioSocial());
			entidadConstruccion.setEstarHabitacion(avaluoRequest.getConstruccion().getEstar());
			entidadConstruccion.setNumeroHabitaciones(avaluoRequest.getConstruccion().getHabitaciones());
			entidadConstruccion.setBanioPrivado(avaluoRequest.getConstruccion().getBanioPrivado());
			entidadConstruccion.setCocina(avaluoRequest.getConstruccion().getCocina());
			entidadConstruccion.setCuartoServicio(avaluoRequest.getConstruccion().getCuartoServicio());
			entidadConstruccion.setBanioServicio(avaluoRequest.getConstruccion().getBanioServicio());
			entidadConstruccion.setPatioInterior(avaluoRequest.getConstruccion().getPatioInterior());
			entidadConstruccion.setTerraza(avaluoRequest.getConstruccion().getTerraza());
			entidadConstruccion.setJardin(avaluoRequest.getConstruccion().getJardin());
			entidadConstruccion.setBalcon(avaluoRequest.getConstruccion().getBalcon());
			entidadConstruccion.setZonaVerdePrivada(avaluoRequest.getConstruccion().getzVerdePrivada());
			entidadConstruccion.setTotalGarajes(avaluoRequest.getConstruccion().getTotalGarajes());
			entidadConstruccion.setCubierto(avaluoRequest.getConstruccion().getCubierto());
			entidadConstruccion.setDescubierto(avaluoRequest.getConstruccion().getDescubierto());
			entidadConstruccion.setUsoExclusivo(avaluoRequest.getConstruccion().getUsoExclusivo());
			entidadConstruccion.setPrivado(avaluoRequest.getConstruccion().getPrivado());
			entidadConstruccion.setBahiaComunal(avaluoRequest.getConstruccion().getBahiaComun());
			entidadConstruccion.setSencillo(avaluoRequest.getConstruccion().getSencillo());
			entidadConstruccion.setDoble(avaluoRequest.getConstruccion().getDoble());
			entidadConstruccion.setServidumbre(avaluoRequest.getConstruccion().getServidumbre());
			entidadConstruccion.setDeposito(avaluoRequest.getConstruccion().getDeposito());
			entidadConstruccion.setLocal(avaluoRequest.getConstruccion().getLocal());
			entidadConstruccion.setBodega(avaluoRequest.getConstruccion().getBodega());
			entidadConstruccion.setOficina(avaluoRequest.getConstruccion().getOficina());
			entidadConstruccion.setVentilacion(Long.valueOf(avaluoRequest.getConstruccion().getVentilacion()));
			entidadConstruccion.setIluminacion(Long.valueOf(avaluoRequest.getConstruccion().getIluminacion()));
			entidadSalubridad
					.setCondicionSalubridad(Long.valueOf(avaluoRequest.getConstruccion().getCondicionesSalubridad()));

			if (avaluoRequest.getConstruccion().getImpactoNegativoOtros() != null)
				entidadSalubridad.setImpactoNegativoOtros(avaluoRequest.getConstruccion().getImpactoNegativoOtros());
			entidadConstruccion.setEstadoPisos(Long.valueOf(avaluoRequest.getConstruccion().getEstadoPisos()));
			entidadConstruccion.setEstadoMuros(Long.valueOf(avaluoRequest.getConstruccion().getEstadoMuros()));
			entidadConstruccion.setEstructuraTechos(Long.valueOf(avaluoRequest.getConstruccion().getEstadoTechos()));
			entidadConstruccion.setEstadoMadera(Long.valueOf(avaluoRequest.getConstruccion().getEstadoMadera()));
			entidadConstruccion.setEstadoMetal(Long.valueOf(avaluoRequest.getConstruccion().getEstadoMetal()));
			entidadConstruccion.setEstadoBanios(Long.valueOf(avaluoRequest.getConstruccion().getEstadoBanios()));
			entidadConstruccion.setEstadoCocina(Long.valueOf(avaluoRequest.getConstruccion().getEstadoCocina()));
			entidadConstruccion.setCalidadPiso(Long.valueOf(avaluoRequest.getConstruccion().getCalidadPisos()));
			entidadConstruccion.setCalidadMuro(Long.valueOf(avaluoRequest.getConstruccion().getCalidadMuros()));
			entidadConstruccion.setCalidadTecho(Long.valueOf(avaluoRequest.getConstruccion().getCalidadTechos()));
			entidadConstruccion.setCalidadMadera(Long.valueOf(avaluoRequest.getConstruccion().getCalidadMadera()));
			entidadConstruccion.setCalidMetal(Long.valueOf(avaluoRequest.getConstruccion().getCalidadMetal()));
			entidadConstruccion.setCalidadBanio(Long.valueOf(avaluoRequest.getConstruccion().getCalidadBanios()));
			entidadConstruccion.setCalidadCocina(Long.valueOf(avaluoRequest.getConstruccion().getCalidadCocina()));
			entidadConstruccion.setPropiedadHorizontal(
					Long.valueOf(avaluoRequest.getConstruccion().getSometidoPropiedadHorizontal()));
			entidadConstruccion.setConjuntoAgrupacionCerrada(
					Long.valueOf(avaluoRequest.getConstruccion().getConjuntoAgrupacionCerrada()));
			entidadConstruccion
					.setUbicacionInmueble(Long.valueOf(avaluoRequest.getConstruccion().getUbicacionInmueble()));
			entidadConstruccion.setNumeroEdificios(avaluoRequest.getConstruccion().getNumeroEdificios());
			entidadConstruccion.setUnidadPorPiso(avaluoRequest.getConstruccion().getUnidadesPorPiso());
			entidadConstruccion.setTotalUnidades(avaluoRequest.getConstruccion().getTotalUnidades());
			entidadConstruccion.setTipoAdministrador(avaluoRequest.getConstruccion().getTipoAdministrador());
			entidadConstruccion.setNombreAdministrador(avaluoRequest.getConstruccion().getNombreAdministrador());
			entidadConstruccion.setCorreoAdministrador(avaluoRequest.getConstruccion().getCorreoAdministrador());
			entidadConstruccion.setTelefonoAdministrador(avaluoRequest.getConstruccion().getTelefonoAdministrador());
			entidadConstruccion.setOtrosDotacion(avaluoRequest.getConstruccion().getOtros());

			avaluoEntity.setCondicionSalubridad(entidadSalubridad);
			avaluoEntity.setInformacionConstruccion(entidadConstruccion);

			// guarda información del cada uno de las secciones del avaluuo
//			avaluoFacade.guardar(entidadInmueble, avaluoEntity, usuario);
//			avaluoFacade.guardar(entidadOfertaDemanda, usuario);
//			avaluoFacade.guardar(entidadObservaciones, usuario);
//			avaluoFacade.guardar(lisLiquidacion, liquidacionTotal, usuario);
//			avaluoFacade.guardar(entidadSalubridad, usuario);
//			avaluoFacade.guardar(entidadConstruccion, usuario);

			// persistencia de la información del avalúo
			avaluoFacade.guardar(avaluoEntity, usuario);

			respuesta.setCodigoRespuesta("200");
			respuesta.setMensajeRespuesta("Sincronización exitosa");
		} catch (NegocioException e) {
			respuesta.setCodigoRespuesta("500");
			respuesta.setMensajeRespuesta(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			try {
				avaluoEntity.setInformacionBarrio(null);
				avaluoEntity.setInformacionConstruccion(null);
				avaluoEntity.setInformacionInmueble(null);
				avaluoEntity.setCompOfertaDemanda(null);
				avaluoEntity.setLiquidacionAvaluos(null);
				avaluoEntity.setLiquidacionTotal(null);
				avaluoEntity.setObservacion(null);
				avaluoEntity.setCondicionSalubridad(null);
				avaluoFacade.eliminar(avaluoEntity);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			respuesta.setCodigoRespuesta("500");
			respuesta.setMensajeRespuesta("Error en la sincronización de datos del avalúo");
			e.printStackTrace();
		}

		return respuesta;

	}

	/**
	 * retorna la cadena SI o NO en homologación al valor numerico
	 * 
	 * @param valor
	 * @return
	 */
	private String valorCadenaSiNo(Long valor) {
		return valor != null && valor == 1L ? "SI" : "NO";
	}

	/**
	 * retorna la valor numerico en homologación al cadena SI o NO
	 * 
	 * @param valor
	 * @return
	 * @throws NegocioException
	 */
	private Long valorSiNo(String valorstring) throws NegocioException {
		Long valor = Long.valueOf(valorstring);
		if (valor != 1 && valor != 0) {
			throw new NegocioException("Error se esperaba un 1 o 0");
		}
		return valor;
	}

	private void consultarUsuario(String codigoUsuario) {
		try {
			this.usuario = this.seguridadSvc.consultarUsuario(codigoUsuario);
		} catch (NegocioException var6) {
			var6.printStackTrace();
		} finally {
			if (this.usuario == null) {
				this.usuario = new UsuarioDto(new Usuario());
				this.usuario.getUsuario().setCodigo(codigoUsuario);
			}

		}

	}

	// @TransactionAttribute(TransactionAttributeType.REQUIRED)
	private Long guardarAvaluoGetId(Avaluo avaluo, UsuarioDto usuario) throws NegocioException {

		avaluoFacade.guardar(avaluo, usuario);
		try {
			tx.commit();
//			sessionContext.getUserTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error al guardar el avalúo");
			e.printStackTrace();
		}
		ConsultaAvaluoDto consulta = new ConsultaAvaluoDto();
		consulta.setConsecutivoBanco(avaluo.getConsecutivoBanco());
		consulta.setEstadoAvaluo(Long.valueOf(avaluo.getEstadoAvaluo()));
		listAvaluos = avaluoSvc.consultar(consulta, 0, 10, "fechaTransaccion",
				UtilJsf.resolverOrientacion(SortOrder.DESCENDING));
		System.out.println(listAvaluos.size());
		if (listAvaluos.size() > 0) {
			System.out.println("id avaluo guardado" + listAvaluos.get(0).getIdAvaluo());
		} else {
			throw new NegocioException("Error al crear el avaluo");
		}

		return listAvaluos.get(0).getIdAvaluo();
	}
	
	public boolean validarEntradasPorUsadaNueva(
			Long sinTerminar, // JSON sinTerminar
			Long remodelado, //JSON remodelado
			Long terminado, // JSON terminado
			Long enObra,  // JSON enObra
			Long avanceObra, // JSON avanceObra
			Long estadoConstruccion // JSON estadoConstruccion
			) {
		boolean estado = false;
		if(estadoConstruccion == 1) {
			System.out.println("ESTADO NUEVA");
			if(terminado == 1 && ( enObra == null || enObra == 0) ) {
				if((sinTerminar == null || sinTerminar == 0)&&
					(remodelado == null || remodelado == 0) &&
					(avanceObra == null || avanceObra == 0 )) {
						System.out.println("CUMPLIO LOS REQUISITOS PARA SER NUEVA Y TERMINADA ");
						 estado = true;
				}else {
						System.out.println("EXCEPCION POR REGLA DE NEGOCIO 1");
						 estado = false;
				}
			}else if((terminado == 0 || terminado == null) && enObra == 1) {
				if(avanceObra == null) {
					System.out.println("EXCEPCION POR REGLA DE NEGOCIO DEBE TENER UN PORCENTAJE DE OBRA");
					 estado = false;
				}else {
					if((sinTerminar == null || sinTerminar == 0)&&
					 (remodelado == null || remodelado == 0)) {
						System.out.println("CUMPLIO LOS REQUISITOS PARA SER NUEVA Y EN OBRA CON % DE OBRA");
						 estado = true;
					}else {
						System.out.println("EXCEPCION POR REGLA DE NEGOCIO 2");
						 estado = false;
					}
				}
			}
		}else if(estadoConstruccion == 2){
			System.out.println("ESTADO USADA");
			if(terminado == 1 && (remodelado == null || remodelado == 0) && (sinTerminar == null || sinTerminar == 0)) {
				if((enObra == null || enObra == 0) && 
				   (avanceObra == null || avanceObra == 0 )) {
					System.out.println("CUMPLIO LOS REQUISITOS PARA SER USADA Y TERMINADA ");
					estado = true;
				}else {
					System.out.println("EXCEPCION POR REGLA DE NEGOCIO 3");
					estado = false;
				}
			}else if(terminado == 1 && remodelado == 1 && (sinTerminar == null || sinTerminar == 0)) {
				
				if((enObra == null || enObra == 0) && 
					(avanceObra == null || avanceObra == 0 )) {
					System.out.println("CUMPLIO LOS REQUISITOS PARA SER USADA, TERMINADA Y REMODELADA ");
					estado = true;
				}else {
					estado = false;
					System.out.println("EXCEPCION POR REGLA DE NEGOCIO 4");
				}
			}else if(sinTerminar == 1){
				
				if((remodelado == null || remodelado == 0) &&	
					(terminado == null || terminado == 0) &&
					(enObra == null || enObra == 0) && 
					(avanceObra == null || avanceObra == 0 )) {
						estado = true;
					    System.out.println("CUMPLIO LOS REQUISITOS PARA SER USADA Y SIN TERMINAR ");
				}else {
						estado = false;
						System.out.println("EXCEPCION POR REGLA DE NEGOCIO 5");
				}
			}
			
		}
		return estado;
	}

	/**
	 * Valida los campos del json para sincronizar avaluos
	 * 
	 * @param avaluoRequest
	 * @throws NegocioException
	 */
	public void validarEntradas(AvaluoDTO avaluoRequest) throws NegocioException {

		if (Long.valueOf(avaluoRequest.getBarrio().getEstrato()) < 1L
				&& Long.valueOf(avaluoRequest.getBarrio().getEstrato()) > 6L
				&& Long.valueOf(avaluoRequest.getBarrio().getEstrato()) != 80L) {
			throw new NegocioException("Error el estrato debe ser 1,2,3,4,5,6 o 80");

		}
		if (!avaluoRequest.getBarrio().getEstado().equals("1") && !avaluoRequest.getBarrio().getEstado().equals("2")
				&& !avaluoRequest.getBarrio().getEstado().equals("3")) {
			throw new NegocioException("Error el estado del barrio debe ser 1,2 o 3: ");
		}
		if (!avaluoRequest.getDocumentoIdentificacion().equals("21")
				&& !avaluoRequest.getDocumentoIdentificacion().equals("22")
				&& !avaluoRequest.getDocumentoIdentificacion().equals("23")) {
			throw new NegocioException("Error el documentoIdentificacion debe ser 21, 22 o 23: ");
		}
		if (Long.valueOf(avaluoRequest.getEstado().toString()) > 5
				|| Long.valueOf(avaluoRequest.getEstado().toString()) < 2) {
			throw new NegocioException("Error el estado del avaluo debe ser 2,3,4 o 5: ");
		}
		
		Long sinTerminar = valorSiNo(avaluoRequest.getConstruccion().getSinTerminar()); // JSON sinTerminar
		Long remodelado = valorSiNo(avaluoRequest.getConstruccion().getRemodelado()); // JSON remodelado
		Long terminado = valorSiNo(avaluoRequest.getConstruccion().getTerminado()); //JSON terminado
		Long enObra = Long.valueOf(avaluoRequest.getConstruccion().getEnObra()); // JSON enObra
		Long avanceObra = avaluoRequest.getConstruccion().getAvanceObra(); // JSON avanceObra
		Long estadoConstruccion = Long.valueOf(avaluoRequest.getConstruccion().getEstadoConstruccion()); // JSON estadoConstruccion
		
		if(!this.validarEntradasPorUsadaNueva(sinTerminar,remodelado,terminado,enObra,avanceObra, estadoConstruccion)) {
			throw new NegocioException("Los estados para la construccion no concuerdan ");
		}

		validarMetodologia(avaluoRequest.getMetodologia());
		validarObjetivo(avaluoRequest.getObjetivo());


		validarSiNo(avaluoRequest.getConstruccion().getSinTerminar(), "sinTerminar");
		validarSiNo(avaluoRequest.getConstruccion().getTerminado(), "terminado");
		validarSiNo(avaluoRequest.getConstruccion().getEnObra(), "enObra");
		validarSiNo(avaluoRequest.getConstruccion().getFinanciadoConstructor(), "financiadoConstructor");

		validarSiNo(avaluoRequest.getConstruccion().getArborizacion(), "arborizacion");
		validarSiNo(avaluoRequest.getConstruccion().getParques(), "parques");
		validarSiNo(avaluoRequest.getConstruccion().getZonaVerde(), "zonaVerde");
		validarSiNo(avaluoRequest.getConstruccion().getImpactoNegativoPorAire(), "impactoNegativoPorAire");
		validarSiNo(avaluoRequest.getConstruccion().getImpactoNegativoPorBasura(), "impactoNegativoPorBasura");
		validarSiNo(avaluoRequest.getConstruccion().getImpactoNegativoPorInseguridad(),
				"impactoNegativoPorInseguridad");
		validarSiNo(avaluoRequest.getConstruccion().getImpactoNegativoRuido(), "impactoNegativoRuido");
		validarSiNo(avaluoRequest.getConstruccion().getAguasServidas(), "aguasServidas");
		validarSiNo(avaluoRequest.getConstruccion().getPorteria(), "porteria");
		validarSiNo(avaluoRequest.getConstruccion().getCitofono(), "citofono");
		validarSiNo(avaluoRequest.getConstruccion().getBicicletero(), "bicicletero");
		validarSiNo(avaluoRequest.getConstruccion().getSalonComunal(), "salonComunal");
		validarSiNo(avaluoRequest.getConstruccion().getPiscina(), "piscina");
		validarSiNo(avaluoRequest.getConstruccion().getTanqueAgua(), "tanqueAgua");
		validarSiNo(avaluoRequest.getConstruccion().getClubHouse(), "clubHouse");
		validarSiNo(avaluoRequest.getConstruccion().getPlantaElectrica(), "plantaElectrica");
		validarSiNo(avaluoRequest.getConstruccion().getGarajeVisitantes(), "garajeVisitantes");
		validarSiNo(avaluoRequest.getConstruccion().getJuegoNinos(), "juegoNinos");
		validarSiNo(avaluoRequest.getConstruccion().getCanchaMultiple(), "canchaMultiple");
		validarSiNo(avaluoRequest.getConstruccion().getShutBasuras(), "shutBasuras");
		validarSiNo(avaluoRequest.getConstruccion().getBombaEyectora(), "bombaEyectora");
		validarSiNo(avaluoRequest.getConstruccion().getAireAcondicionado(), "aireAcondicionado");
		validarSiNo(avaluoRequest.getConstruccion().getSquash(), "squash");
		validarSiNo(avaluoRequest.getConstruccion().getPresion(), "presion");
		validarSiNo(avaluoRequest.getConstruccion().getZonasVerdes(), "zonasVerdes");
		validarSiNo(avaluoRequest.getConstruccion().getGimnasio(), "gimnasio");
		validarSiNo(avaluoRequest.getConstruccion().getGolfito(), "golfito");
		validarSiNo(avaluoRequest.getConstruccion().getAscensor(), "ascensor");

		// validarUsoInmueble(avaluoRequest.getInmueble().getUso());

		// validarclaseInmueble(avaluoRequest.getInmueble().getClase());

		/*
		 * validarcategoriainmueble(avaluoRequest.getInmueble().getCategoria());
		 * validarcampo01y2(); validarvalorEntre1o6(); validarestructuraReforzada();
		 * validarestadsoconservacion(); validarcamponumerico1y2();
		 * validarcategoriainmueble(); validarclaseInmueble();
		 */
	}

	/**
	 * valida si el valor ingresado es 1 o 0
	 * 
	 * @param valorstring
	 * @param campo
	 * @throws NegocioException
	 */
	public void validarSiNo(String valorstring, String campo) throws NegocioException {
		try {
			Long valor = Long.valueOf(valorstring);
			if (valor != 1 && valor != 0) {
				throw new NegocioException("Error se esperaba un 1 o 0 en el campo: " + campo);
			}

		} catch (Exception e) {
			throw new NegocioException("Error se esperaba un 1 o 0 en el campo: " + campo);
		}

	}

	/**
	 * Valida que el objetivo sea entre 1 y 8
	 * 
	 * @param valorstring
	 * @param campo
	 * @throws NegocioException
	 */
	public void validarObjetivo(String valorstring) throws NegocioException {
		try {
			Long valor = Long.valueOf(valorstring);

			if (valor > 8 || valor < 1) {
				throw new NegocioException("Error se esperaba entre 1 y 8 en el campo: Objetivo");
			}

		} catch (Exception e) {
			throw new NegocioException("Error se esperaba entre 1 y 8 en el campo: Objetivo");
		}
	}

	/**
	 * Valida la metodologia
	 * 
	 * @param valorstring
	 * @throws NegocioException
	 */
	public void validarMetodologia(String valorstring) throws NegocioException {
		Long valor = Long.valueOf(valorstring);

		if (valor != 21L && valor != 22L && valor != 23L && valor != 24L && valor != 25L && valor != 26L && valor != 41L
				&& valor != 42L && valor != 43L && valor != 44L && valor != 45L && valor != 61L && valor != 62L
				&& valor != 63L) {
			throw new NegocioException(
					"Error la metodología debe ser 21, 22, 23, 24, 25,26,41,42,43,44,45,61,62 o 63 ");
		}
	}

	/**
	 * valida la categoria del inmueble
	 * 
	 * @param valorString
	 * @throws NegocioException
	 */
	public void validarcategoriainmueble(String valorString) throws NegocioException {
		try {
			Long valor = Long.valueOf(valorString);
			if (valor != 4L && valor != 5L && valor != 6L && valor != 7L && valor != 2L && valor != 15L && valor != 13L
					&& valor != 12L) {
				throw new NegocioException("Error la categoría del inmuble debe ser 5,5,6,7,2,15,12 o 13");
			}
		} catch (Exception e) {
			throw new NegocioException("Error la categoría del inmuble debe ser 5,5,6,7,2,15,12 o 13");
		}
	}

	/**
	 * Valida la clase del inmueble
	 * 
	 * @param valorString
	 * @throws NegocioException
	 */
	public void validarclaseInmueble(String valorString) throws NegocioException {
		try {
			Long valor = Long.valueOf(valorString);
			if (valor > 8L || valor < 1L) {
				throw new NegocioException("Error la clase del inmuble debe ser entre 1 y 8");
			}
		} catch (Exception e) {
			throw new NegocioException("Error la clase del inmuble debe ser entre 1 y 8");
		}
	}

	/**
	 * Validar la ubicacion del inmueble
	 * 
	 * @param valorString
	 * @throws NegocioException
	 */
	// estructuraReforzada, ubicacioninmueble
	public void validarcamponumerico1y2(String valorString, String campo) throws NegocioException {
		try {
			Long valor = Long.valueOf(valorString);
			if (valor < 1L || valor > 2L) {
				throw new NegocioException("Error el campo debe ser entre 1 o 2: " + campo);
			}
		} catch (Exception e) {
			throw new NegocioException("Error el campo debe ser entre 1 o 2: " + campo);
		}
	}

	/**
	 * Validar campos que deban ser numericos entre 1 y 6
	 * 
	 * @param valorString
	 * @param campo
	 * @throws NegocioException
	 */
	// aplica para estructura, material estructura, detalle material, uso inmueble,
	// estadsoconservacion
	public void validarvalorEntre1o6(String valorString, String campo) throws NegocioException {
		try {
			Long valor = Long.valueOf(valorString);
			if (valor > 6 || valor < 1) {
				throw new NegocioException("Error el campo debe ser entre 1 y 6: " + campo);
			}
		} catch (Exception e) {
			throw new NegocioException("Error el campo debe ser entre 1 y 6: " + campo);
		}
	}

	/**
	 * Valida los campos numericos entre 0 y 2 aplica para irregularidad planta,
	 * irregularidad altura
	 * 
	 * @param valorString
	 * @throws NegocioException
	 */
	public void validarcampo01y2(String valorString, String campo) throws NegocioException {
		try {
			Long valor = Long.valueOf(valorString);
			if (valor > 2 || valor < 0) {
				throw new NegocioException("Error el campo debe ser entre 0 y 2: " + campo);
			}
		} catch (Exception e) {
			throw new NegocioException("Error el campo debe ser entre 0 y 2: " + campo);
		}
	}

}
