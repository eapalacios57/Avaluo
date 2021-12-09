package com.segurosbolivar.avaluos.modelo.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.log4j.Logger;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilFecha;
import com.asesoftware.util.lang.UtilNumero;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.data.AvaluoDao;
import com.segurosbolivar.avaluos.modelo.data.CargueTmpDao;
import com.segurosbolivar.avaluos.modelo.data.CiudadDao;
import com.segurosbolivar.avaluos.modelo.data.DepartamentoDao;
import com.segurosbolivar.avaluos.modelo.dto.DetalleMasivoDto;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.CargueTmp;
import com.segurosbolivar.avaluos.modelo.entity.ComportamientoOfertaDemanda;
import com.segurosbolivar.avaluos.modelo.entity.CondicionesSalubridad;
import com.segurosbolivar.avaluos.modelo.entity.Dominios;
import com.segurosbolivar.avaluos.modelo.entity.InformacionBarrio;
import com.segurosbolivar.avaluos.modelo.entity.InformacionConstruccion;
import com.segurosbolivar.avaluos.modelo.entity.InformacionInmueble;
import com.segurosbolivar.avaluos.modelo.entity.LiquidacionAvaluo;
import com.segurosbolivar.avaluos.modelo.entity.LiquidacionAvaluoTotal;
import com.segurosbolivar.avaluos.modelo.entity.Observaciones;
import com.segurosbolivar.avaluos.modelo.service.IDiligenciamiento;
import com.segurosbolivar.avaluos.modelo.service.IParametrizacion;
import com.segurosbolivar.avaluos.modelo.service.IValidador;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;

@Stateless
public class ValidadorImpl implements IValidador {

    private static final long serialVersionUID = 1797277574156182190L;

    private static final Logger log = Logger.getLogger(ValidadorImpl.class);

    @EJB
    private IDiligenciamiento diligenciamientoSvc;

    @EJB
    private CargueTmpDao cargueTmpDao;

    @EJB
    private IParametrizacion parametrizacionSvc;

    @EJB
    private ManejadorErroresNegocio mgrExc;

    @EJB
    private CiudadDao ciudadDao;
    @EJB
    private DepartamentoDao departamentoDao;
    @EJB
    private AvaluoDao avaluoDao;

    private CargueTmp generaCargueTemporal(Long numeroRefCargue, String linea) throws NegocioException {
	CargueTmp cargueTmp = new CargueTmp();
	cargueTmp.setNumeroRefCargue(numeroRefCargue);
	cargueTmp.setLineaPlano(1L);
	cargueTmp.setContenidoLineaPlano(linea);
	cargueTmp.setTipoCargue(UtilConstantes.TIPO_CARGUE_VALIDAWEB);
	cargueTmp.setEstadoCargue(UtilConstantes.TIPO_CARGUE_CARGADO);
	cargueTmp.setNombreArchivo(UtilConstantes.NOMBRE_ARCHIVO_TEMPORAL);
	cargueTmp.setFechaTransaccion(new Date());
	cargueTmp.setTipoProyecto(1L);
	cargueTmpDao.crear(cargueTmp);
	return cargueTmp;
    }

    private String obtieneTextoSinSaltolinea(String expresion) {
	String regexExpresion = "\n";
	Pattern pattern = Pattern.compile(regexExpresion);
	Matcher matcher = pattern.matcher(expresion);

	if (matcher.find()) {
	    return expresion.replace(regexExpresion, " ");
	}
	return expresion;
    }

    /**
     * Trunca a un máximo de 4 posiciones decimales un número decimal.
     *
     * @param bigDecimal
     *            Número que se desea truncar
     * @return Número BigDecimal truncado
     */
    private BigDecimal round(BigDecimal bigDecimal) {
	BigDecimal res = null;
	String[] partesNumero = bigDecimal.toString().replace(".", "#").split("#");

	if ((partesNumero.length > 1) && (partesNumero[1].length() > 4)) {
	    StringBuilder numero = new StringBuilder();
	    numero.append(partesNumero[0]);
	    numero.append(".");
	    for (int i = 0; i < 4; i++) {
		numero.append(partesNumero[1].toCharArray()[i]);
	    }
	    res = new BigDecimal(numero.toString());
	} else {
	    res = bigDecimal;
	}
	return res;
    }

    private void procesar(StringBuilder sb, Object valor, String separador, String formato) {
	String valorTexto = "";
	if (valor == null) {
	    sb.append(separador);
	    return;
	}
	if (valor instanceof Long) {
	    valorTexto = UtilNumero.pasarTexto((Long) valor);
	} else if (valor instanceof String) {
	    valorTexto = "'" + (String) valor + "'";
	} else if (valor instanceof Date) {
	    valorTexto = UtilFecha.dateToString(formato, (Date) valor);
	}
	sb.append(valorTexto);
	if (separador != null && !separador.isEmpty()) {
	    sb.append(separador);
	}
    }

    private StringBuilder obtenerlineaAvaluo(Avaluo avaluo, boolean asobancaria, String separador) {

	StringBuilder linea = new StringBuilder();
	LiquidacionAvaluoTotal liquidacionAvaluoTotal = avaluo.getLiquidacionTotal() != null ? avaluo.getLiquidacionTotal() : new LiquidacionAvaluoTotal();
	List<LiquidacionAvaluo> liquidacionAvaluo = avaluo.getLiquidacionAvaluos() != null ? avaluo.getLiquidacionAvaluos() : new ArrayList<LiquidacionAvaluo>();
	InformacionInmueble inmueble = avaluo.getInformacionInmueble() != null ? avaluo.getInformacionInmueble() : new InformacionInmueble();
	InformacionBarrio barrio = avaluo.getInformacionBarrio() != null ? avaluo.getInformacionBarrio() : new InformacionBarrio();
	CondicionesSalubridad condicionesSalubridad = avaluo.getCondicionSalubridad() != null ? avaluo.getCondicionSalubridad() : new CondicionesSalubridad();
	ComportamientoOfertaDemanda comportamientoOfertaDemanda = avaluo.getCompOfertaDemanda() != null ? avaluo.getCompOfertaDemanda() : new ComportamientoOfertaDemanda();
	InformacionConstruccion construccion = avaluo.getInformacionConstruccion() != null ? avaluo.getInformacionConstruccion() : new InformacionConstruccion();
	Observaciones observaciones = avaluo.getObservacion() != null ? avaluo.getObservacion() : new Observaciones();

	procesar(linea, barrio.getAndenes(), separador, null);
	procesar(linea, construccion.getCalidadBanio(), separador, null);
	procesar(linea, construccion.getCalidadMadera(), separador, null);
	procesar(linea, construccion.getCalidMetal(), separador, null);
	procesar(linea, construccion.getCalidadCocina(), separador, null);
	procesar(linea, construccion.getCalidadMuro(), separador, null);
	procesar(linea, construccion.getCalidadPiso(), separador, null);
	procesar(linea, construccion.getCalidadTecho(), separador, null);
	procesar(linea, inmueble.getCiudadEscritura(), separador, null);
	procesar(linea, inmueble.getClaseInmueble(), separador, null);
	procesar(linea, construccion.getConjuntoAgrupacionCerrada(), separador, null); // 11
	procesar(linea, construccion.getCubierta(), separador, null);
	procesar(linea, inmueble.getDepartamentoEscritura(), separador, null);
	if (liquidacionAvaluo.size() < 10) {
	    for (int i = liquidacionAvaluo.size(); i < 10; i++) {
		liquidacionAvaluo.add(new LiquidacionAvaluo());
	    }
	}
	liquidacionAvaluo.add(1, liquidacionAvaluo.remove(9));
	for (LiquidacionAvaluo registro : liquidacionAvaluo) {
	    if (registro.getDescripcionLiquidacion() != null) {
		linea.append(registro.getDescripcionLiquidacion().toString());
	    }
	    linea.append(separador);
	}
	procesar(linea, construccion.getEstadoBanios(), separador, null);
	procesar(linea, construccion.getEstadoCocina(), separador, null);
	procesar(linea, construccion.getEstadoConservacion(), separador, null);
	procesar(linea, construccion.getEstadoMadera(), separador, null);
	procesar(linea, construccion.getEstadoMetal(), separador, null);
	procesar(linea, construccion.getEstadoMuros(), separador, null);
	procesar(linea, construccion.getEstadoPisos(), separador, null);
	procesar(linea, barrio.getEstrato(), separador, null);
	procesar(linea, construccion.getEstructura(), separador, null);
	procesar(linea, construccion.getEstructuraTechos(), separador, null);
	procesar(linea, barrio.getEstadosViaAcceso(), separador, null);
	procesar(linea, construccion.getFachada(), separador, null);
	if (avaluo.getCiudad() == null)
	    avaluo.setCiudad(ciudadDao.buscar(avaluo.getIdCiudad()));
	procesar(linea, avaluo.getCiudad().getCodAsobancaria(), separador, null);
	if (avaluo.getDepartamento() == null)
	    avaluo.setDepartamento(departamentoDao.buscar(avaluo.getIdDepartamento()));
	procesar(linea, avaluo.getDepartamento().getCodAsobancaria(), separador, null);
	procesar(linea, avaluo.getIdMetodologia(), separador, null);
	procesar(linea, avaluo.getIdObjetoAvaluo(), separador, null);
	procesar(linea, avaluo.getIdTipoIdentificacion(), separador, null);
	procesar(linea, construccion.getIluminacion(), separador, null);
	procesar(linea, barrio.getLegalidad(), separador, null);
	procesar(linea, barrio.getPavimentada(), separador, null);
	procesar(linea, construccion.getPisosBodega(), separador, null);
	procesar(linea, construccion.getPropiedadHorizontal(), separador, null);
	procesar(linea, condicionesSalubridad.getCondicionSalubridad(), separador, null);
	procesar(linea, barrio.getSardeneles(), separador, null);
	procesar(linea, construccion.getTipoFachada(), separador, null);
	procesar(linea, inmueble.getTipoVivienda(), separador, null);
	procesar(linea, barrio.getTopografia(), separador, null);
	procesar(linea, barrio.getTransporte(), separador, null);
	procesar(linea, construccion.getUbicacionInmueble(), separador, null);
	procesar(linea, inmueble.getUbicacion2(), separador, null);
	procesar(linea, inmueble.getUsoInmueble(), separador, null);
	procesar(linea, construccion.getVentilacion(), separador, null);
	procesar(linea, avaluo.getFechaAvaluo(), separador, "dd-MM-yyyy HH:mm:ss");
	procesar(linea, inmueble.getFechaEscritura(), separador, "dd-MM-yyyy HH:mm:ss");
	procesar(linea, construccion.getAireAcondicionado(), separador, null);
	procesar(linea, barrio.getAcueductoPredio(), separador, null);
	procesar(linea, barrio.getAcueductoSector(), separador, null);
	procesar(linea, condicionesSalubridad.getAguasServidas(), separador, null);
	procesar(linea, barrio.getAlamedas(), separador, null);
	procesar(linea, barrio.getAlcantarilladoPredio(), separador, null);
	procesar(linea, barrio.getAlcantarilladoSector(), separador, null);
	procesar(linea, barrio.getAlumbrado(), separador, null);
	procesar(linea, condicionesSalubridad.getAmbientalArborizacion(), separador, null);
	procesar(linea, condicionesSalubridad.getAmbientalParques(), separador, null);
	procesar(linea, condicionesSalubridad.getAmbientalZonaVerde(), separador, null);
	procesar(linea, barrio.getArborizacion(), separador, null);
	procesar(linea, construccion.getAscensor(), separador, null);
	procesar(linea, condicionesSalubridad.getImpactoNegativoBasura(), separador, null);
	procesar(linea, construccion.getBicicletero(), separador, null);
	procesar(linea, construccion.getBombaEyectora(), separador, null);
	procesar(linea, construccion.getCanchaMultiple(), separador, null);
	procesar(linea, barrio.getCicloRutas(), separador, null);
	procesar(linea, construccion.getCitofono(), separador, null);
	procesar(linea, construccion.getClubHouse(), separador, null);
	procesar(linea, barrio.getComercio(), separador, null);
	procesar(linea, barrio.getElectricidadPredio(), separador, null);
	procesar(linea, barrio.getElectricidadSector(), separador, null);
	procesar(linea, construccion.getEnObra(), separador, null);
	procesar(linea, construccion.getEstadoRemodelacion(), separador, null);
	procesar(linea, construccion.getEstadoTerminadaNueva(), separador, null);
	procesar(linea, construccion.getEstadoTerminadoUsado(), separador, null);
	procesar(linea, barrio.getGasPredio(), separador, null);
	procesar(linea, barrio.getGasSector(), separador, null);
	procesar(linea, construccion.getGimnasio(), separador, null);
	procesar(linea, construccion.getGarajeVisitante(), separador, null);
	procesar(linea, construccion.getGolfito(), separador, null);
	procesar(linea, barrio.getIndustria(), separador, null);
	procesar(linea, condicionesSalubridad.getImpactoNegativoInseguridad(), separador, null);
	procesar(linea, construccion.getJuegoNinos(), separador, null);
	procesar(linea, barrio.getOtrosUsos(), separador, null);
	procesar(linea, barrio.getParadero(), separador, null);
	procesar(linea, barrio.getParques(), separador, null);
	procesar(linea, construccion.getPiscina(), separador, null);
	procesar(linea, construccion.getPlanta(), separador, null);
	procesar(linea, condicionesSalubridad.getImpactoNegativoPorAire(), separador, null);
	procesar(linea, construccion.getPorteria(), separador, null);
	procesar(linea, construccion.getPresion(), separador, null);
	procesar(linea, condicionesSalubridad.getImpactoNegativoRuido(), separador, null);
	procesar(linea, construccion.getSalonComunal(), separador, null);
	procesar(linea, construccion.getShutBasuras(), separador, null);
	procesar(linea, construccion.getSinTerminar(), separador, null);
	procesar(linea, construccion.getSquash(), separador, null);
	procesar(linea, construccion.getTanqueAgua(), separador, null);
	procesar(linea, barrio.getTelefonoPredio(), separador, null);
	procesar(linea, barrio.getTelefonoSector(), separador, null);
	procesar(linea, barrio.getVivienda(), separador, null);
	procesar(linea, barrio.getZonasVerdes(), separador, null);
	procesar(linea, construccion.getZonasVerdes(), separador, null);

	for (LiquidacionAvaluo registro : liquidacionAvaluo) {
	    if (registro.getAreaLiquidacion() != null) {
		if (asobancaria)
		    linea.append(registro.getAreaLiquidacion().toString());
		else
		    linea.append(round(registro.getAreaLiquidacion()).toString());
	    }
	    linea.append(separador);
	}

	if (liquidacionAvaluoTotal != null && liquidacionAvaluoTotal.getAvaluoUvr() != null) {
	    if (asobancaria) {
		linea.append(liquidacionAvaluoTotal.getAvaluoUvr().toString());
	    } else {
		linea.append(round(liquidacionAvaluoTotal.getAvaluoUvr()).toString());
	    }
	} else {
	    linea.append(BigDecimal.ZERO.toString());
	}
	linea.append(separador);

	procesar(linea, construccion.getBahiaComunal(), separador, null);
	procesar(linea, construccion.getBalcon(), separador, null);
	procesar(linea, construccion.getBanioPrivado(), separador, null);
	procesar(linea, construccion.getBanioServicio(), separador, null);
	procesar(linea, construccion.getBanioSocial(), separador, null);
	procesar(linea, construccion.getBodega(), separador, null);
	procesar(linea, construccion.getCocina(), separador, null);
	procesar(linea, construccion.getComedor(), separador, null);
	procesar(linea, avaluo.getConsecutivoBanco().toString(), separador, null);
	procesar(linea, construccion.getCuartoServicio(), separador, null);
	procesar(linea, construccion.getCubierto(), separador, null);
	procesar(linea, construccion.getDeposito(), separador, null);
	procesar(linea, construccion.getDescubierto(), separador, null);
	procesar(linea, construccion.getDoble(), separador, null);
	procesar(linea, construccion.getEstarHabitacion(), separador, null);
	procesar(linea, construccion.getEstudio(), separador, null);
	procesar(linea, construccion.getNumeroHabitaciones(), separador, null);
	procesar(linea, avaluo.getNumeroIdentificacion(), separador, null);
	procesar(linea, construccion.getJardin(), separador, null);
	procesar(linea, construccion.getLocal(), separador, null);
	procesar(linea, construccion.getNumeroAscensores(), separador, null);
	procesar(linea, construccion.getNumeroEdificios(), separador, null);
	procesar(linea, construccion.getOficina(), separador, null);
	procesar(linea, construccion.getPatioInterior(), separador, null);
	procesar(linea, construccion.getPisos(), separador, null);
	procesar(linea, construccion.getPrivado(), separador, null);
	procesar(linea, construccion.getSala(), separador, null);
	procesar(linea, construccion.getSencillo(), separador, null);
	procesar(linea, construccion.getServidumbre(), separador, null);
	procesar(linea, construccion.getSotanos(), separador, null);
	procesar(linea, construccion.getTerraza(), separador, null);
	procesar(linea, comportamientoOfertaDemanda.getTipoComercializacion(), separador, null);

	if (liquidacionAvaluoTotal != null && liquidacionAvaluoTotal.getTotalAvaluo() != null) {
	    if (asobancaria)
		linea.append(liquidacionAvaluoTotal.getTotalAvaluo());
	    else
		linea.append(round(liquidacionAvaluoTotal.getTotalAvaluo()).toString());
	} else {
	    linea.append(BigDecimal.ZERO.toString());
	}
	linea.append(separador);

	procesar(linea, construccion.getTotalGarajes(), separador, null);
	procesar(linea, construccion.getTotalUnidades(), separador, null);
	procesar(linea, construccion.getUnidadPorPiso(), separador, null);
	procesar(linea, construccion.getUsoExclusivo(), separador, null);

	if (liquidacionAvaluoTotal != null && liquidacionAvaluoTotal.getValorAsegurable() != null) {
	    if (asobancaria)
		linea.append(liquidacionAvaluoTotal.getValorAsegurable().toString());
	    else
		linea.append(round(liquidacionAvaluoTotal.getValorAsegurable()).toString());
	}
	linea.append(separador);

	for (LiquidacionAvaluo registro : liquidacionAvaluo) {
	    if (registro.getValorTotal() != null) {
		if (asobancaria)
		    linea.append(registro.getValorTotal().toString());
		else
		    linea.append(round(registro.getValorTotal()).toString());
	    }
	    linea.append(separador);
	}

	if (liquidacionAvaluoTotal != null && liquidacionAvaluoTotal.getValorUvrDia() != null) {
	    if (asobancaria) {
		linea.append(liquidacionAvaluoTotal.getValorUvrDia().toString());
	    } else {
		linea.append(round(liquidacionAvaluoTotal.getValorUvrDia()).toString());
	    }
	} else {
	    linea.append(BigDecimal.ZERO.toString());
	}
	linea.append(separador);

	for (LiquidacionAvaluo registro : liquidacionAvaluo) {
	    if (registro.getValor() != null) {
		if (asobancaria)
		    linea.append(registro.getValor().toString());
		else
		    linea.append(round(registro.getValor()).toString());
	    }
	    linea.append(separador);
	}

	if (construccion != null && construccion.getVetustez() != null) {
	    if (asobancaria)
		linea.append(construccion.getVetustez().toString());
	    else
		linea.append(round(construccion.getVetustez()).toString());
	}
	linea.append(separador);

	procesar(linea, construccion.getZonaVerdePrivada(), separador, null);
	procesar(linea, liquidacionAvaluoTotal.getCalificacion(), separador, null);
	procesar(linea, construccion.getEstadoConstruccion(), separador, null);

	if (comportamientoOfertaDemanda != null && comportamientoOfertaDemanda.getActualidadEdificadora() != null) {
	    linea.append(obtieneTextoSinSaltolinea(comportamientoOfertaDemanda.getActualidadEdificadora()));
	}
	linea.append(separador);
	procesar(linea, condicionesSalubridad.getImpactoNegativoOtros(), separador, null);
	procesar(linea, condicionesSalubridad.getAmbientalOtros(), separador, null);
	procesar(linea, construccion.getAvanceObra(), separador, null);
	procesar(linea, avaluo.getBarrio(), separador, null);
	procesar(linea, inmueble.getChip(), separador, null);
	procesar(linea, UtilTexto.soloAlfaNumerico(comportamientoOfertaDemanda.getComportamiento()), separador, null);
	procesar(linea, construccion.getTextoCubierta(), separador, null);
	procesar(linea, observaciones.getDireccionAnexos(), separador, null);
	procesar(linea, avaluo.getDireccionInmueble(), separador, null);
	procesar(linea, construccion.getTextoEstructura(), separador, null);
	procesar(linea, construccion.getTextoFachada(), separador, null);
	procesar(linea, avaluo.getJustificacion(), separador, null);
	procesar(linea, inmueble.getMatriculaInmobiliariaPpal1(), separador, null);
	procesar(linea, inmueble.getMatriculaInmobiliariaPpal2(), separador, null);
	procesar(linea, inmueble.getMatriculaInmobiliariaDeposito1(), separador, null);
	procesar(linea, inmueble.getMatriculaInmobiliariaDeposito2(), separador, null);
	procesar(linea, inmueble.getMatriculaInmobiliariaGaraje1(), separador, null);
	procesar(linea, inmueble.getMatriculaInmobiliariaGaraje2(), separador, null);
	procesar(linea, inmueble.getMatriculaInmobiliariaGaraje3(), separador, null);
	procesar(linea, inmueble.getMatriculaInmobiliariaGaraje4(), separador, null);
	procesar(linea, inmueble.getMatriculaInmobiliariaGaraje5(), separador, null);
	procesar(linea, avaluo.getNombreConjuntoEdificio(), separador, null);

	if (avaluo != null && avaluo.getNombreSolicitante() != null) {
	    if (avaluo.getNombreSolicitante().length() > 30) {
		linea.append(avaluo.getNombreSolicitante().substring(0, 30));
	    } else {
		linea.append(avaluo.getNombreSolicitante());
	    }
	}
	linea.append(separador);

	procesar(linea, inmueble.getNotaria(), separador, null);
	procesar(linea, inmueble.getNumeroEscritura(), separador, null);
	procesar(linea, UtilTexto.soloAlfaNumerico(observaciones.getObservacionAvaluo()), separador, null);
	procesar(linea, observaciones.getOtrasDirecciones(), separador, null);
	procesar(linea, inmueble.getOtroClase(), separador, null);
	procesar(linea, construccion.getOtrosDotacion(), separador, null);
	linea.append(separador);
	procesar(linea, barrio.getTextoOtrosUsos(), separador, null);
	procesar(linea, inmueble.getOtroUsoInmueble(), separador, null);
	procesar(linea, UtilTexto.soloAlfaNumerico(barrio.getPerspectivas()), separador, null);
	linea.append(separador);
	procesar(linea, inmueble.getUbicacion3(), separador, null);
	boolean generaCargue = asobancaria;
	procesar(linea, avaluo.getSistemaCoordenadasTxtBUA(), separador, null);
	procesar(linea, construccion.getReparadosBUA(), separador, null);
	procesar(linea, construccion.getIrregularidadAlturaBUA(), separador, null);
	procesar(linea, construccion.getIrregularidadPlantaBUA(), separador, null);
	procesar(linea, construccion.getEstructuraReforzadaBUA(), separador, null);
	procesar(linea, avaluo.getSistemaCoordenadasBUA(), separador, null);
	procesar(linea, construccion.getDanoPrevioBUA(), separador, null);// 228

	if (avaluo != null && avaluo.getLatitudSFBUA() != null) {
	    if (asobancaria)
		linea.append(avaluo.getLatitudSFBUA());
	    else {
		Object val = new BigDecimal((Math.round(Double.parseDouble(avaluo.getLatitudSFBUA()) * 10000000000l) / 10000000000l));
		linea.append(val.toString());
	    }
	}
	linea.append(separador);

	procesar(linea, construccion.getAnoConstruccionBUA(), separador, null);
	procesar(linea, construccion.getMaterialConstruccionBUA(), separador, null);

	if (avaluo != null && avaluo.getLongitudSFBUA() != null) {
	    if (asobancaria)
		linea.append(avaluo.getLongitudSFBUA());
	    else {
		Object val = new BigDecimal((Math.round(Double.parseDouble(avaluo.getLongitudSFBUA()) * 10000000000l) / 10000000000l));
		linea.append(val.toString());
	    }
	}
	linea.append(separador);

	if (construccion != null && construccion.getDetalleMaterialSFBUA() != null) {
	    if (construccion.getDetalleMaterialSFBUA() == 20)
		linea.append(21);
	    else
		linea.append(construccion.getDetalleMaterialSFBUA().toString());
	}
	linea.append(separador);

	procesar(linea, inmueble.getIdCategoria(), separador, null);
	procesar(linea, avaluo.getUsuario(), separador, null);

	if (asobancaria) {

	    procesar(linea, construccion.getSimetriaPlanta(), separador, null);
	    procesar(linea, construccion.getMaterialEstructura(), separador, null);
	    procesar(linea, construccion.getOtroMaterial(), separador, null);
	    procesar(linea, construccion.getParapetosCubierta(), separador, null);
	    procesar(linea, construccion.getUbicacionTanque(), separador, null);
	    procesar(linea, construccion.getOtraUbicacionTanque(), separador, null);
	    procesar(linea, construccion.getSobrePeso(), separador, null);
	    procesar(linea, construccion.getGolpeteo(), separador, null);
	    procesar(linea, construccion.getAsentamientos(), separador, null);
	    procesar(linea, construccion.getDanoPrevio(), separador, null); // 245

	    for (LiquidacionAvaluo registro : liquidacionAvaluo) {
		if (registro.getDescripcionDependencia() != null) {
		    linea.append(registro.getDescripcionDependencia());
		}
		linea.append(separador);
	    }

	    procesar(linea, condicionesSalubridad.getRequiereCondicionesSalubridad(), separador, null);
	}

	if (asobancaria) {
	    procesar(linea, construccion.getPisos(), separador, null);
	    procesar(linea, construccion.getSimetriaAltura(), separador, null);
	    procesar(linea, avaluo.getSector(), separador, null);
	    procesar(linea, avaluo.getTelefonoSolicitante(), separador, null);
	    procesar(linea, avaluo.getCelularSolicitante(), separador, null);
	    procesar(linea, avaluo.getCorreoSolicitante(), separador, null);
	    procesar(linea, avaluo.getUbicacionGps(), separador, null);
	    procesar(linea, construccion.getUbicacionFuentesHidricas(), separador, null);
	    procesar(linea, construccion.getUbicacionNivelVia(), separador, null);
	    procesar(linea, barrio.getEdificacionesIguales(), separador, null);
	    procesar(linea, construccion.getLuces(), separador, null);
	    procesar(linea, inmueble.getEdiContUso(), separador, null);
	}

	if (generaCargue && asobancaria) {
	    procesar(linea, construccion.getRangoConstruccionSF(), separador, null);
	    procesar(linea, inmueble.getCatastralSF(), separador, null);
	    procesar(linea, construccion.getDanoReparadoSF(), separador, null);
	    procesar(linea, construccion.getSimetriaAlturaSF(), separador, null);
	    procesar(linea, construccion.getSimetriaPlantaSF(), separador, null);
	    procesar(linea, construccion.getDanoPrevioSF(), separador, null); // 274
	    procesar(linea, construccion.getEstructuraReforzadaSF(), separador, null);
	    procesar(linea, construccion.getMaterialConstruccionSF(), separador, null);
	    procesar(linea, inmueble.getUsoInmuebleBUA(), separador, null);
	    procesar(linea, inmueble.getUsoInmuebleSF(), separador, null);
	}
	String nombreConstructora = "";
	try {
	    if (construccion.getCodigoConstructoraDescripcion().equals("7")) {
		nombreConstructora = construccion.getNombreConstructora() != null ? construccion.getNombreConstructora() : "";
	    } else {
		Dominios dominio = parametrizacionSvc.consultarDominio("NOMBRECONSTRUCTORA", construccion.getCodigoNombreConstructora().toString());
		if (dominio != null) {
		    nombreConstructora = dominio.getRvMeaning();
		}
	    }
	} catch (Exception e) {
	    nombreConstructora = "";
	}
	// procesar(linea, construccion.getCodigoNombreConstructora(),
	// separador, null);
	procesar(linea, nombreConstructora, separador, null);
	procesar(linea, construccion.getCodigoFinanciadoConstructor(), null, null);
	liquidacionAvaluo.clear();
	return linea;
    }

    private void obtenerMsjErrorValidacion(Long numeroRefCargue, NegocioException lanzar) {
	DetalleMasivoDto filtrar = new DetalleMasivoDto();
	filtrar.setNumeroReferencia(numeroRefCargue);
	List<CargueTmp> cargueTmp = cargueTmpDao.obtenerCargueTmpPorRef(filtrar, 0, Integer.MAX_VALUE, null, null);
	if (cargueTmp.isEmpty()) {
	    lanzar.agregarError(mgrExc.lanzarExcepcion(74, TipoErrorNegocio.ALERTA));
	    return;
	}
	String msjMallaValidadora = cargueTmpDao.obtenerMensajeError(cargueTmp.get(0));
	String[] listaErrores = msjMallaValidadora.split("\n");
	List<String> errores = new ArrayList<String>(Arrays.asList(listaErrores));
	HashSet<String> setErrores = new HashSet<>(errores);
	errores.clear();
	errores.addAll(setErrores);
	for (String tokenActual : errores) {
	    if (!tokenActual.isEmpty()) {
		lanzar.agregarError(mgrExc.lanzarExcepcion(58, TipoErrorNegocio.ALERTA, null, new String[] { tokenActual }));
	    }
	}

    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean validar(Avaluo avaluo, NegocioException lanzar, UsuarioDto usuario) throws NegocioException {
	log.info("--Se inicia validacion del avaluo: " + avaluo.getConsecutivoBanco());
	Long numeroRefCargue = cargueTmpDao.obtenerNumeroSecuencia();
	// Construye linea el avaluo
	log.info("--Se construye linea para validacion avaluo: " + avaluo.getConsecutivoBanco());
	StringBuilder linea = obtenerlineaAvaluo(avaluo, true, UtilConstantes.SEPARADOR_CAMPOS);
	String lineaLimpia = linea.toString().replaceAll("'", "");
	CargueTmp cargueTmp = generaCargueTemporal(numeroRefCargue, lineaLimpia);
	log.info("--Se crea cargue temporal avaluo: " + avaluo.getConsecutivoBanco());
	cargueTmpDao.ejecutaCargueConstructor(Boolean.FALSE, cargueTmp, UtilConstantes.TIPO_CARGUE_VALIDAWEB);
	log.info("--Se ejecuta la validacion avaluo: " + avaluo.getConsecutivoBanco());
	boolean estadoCargue = cargueTmpDao.obtieneEstadoCargue(cargueTmp);
	log.info("--resultado validacion avaluo: " + avaluo.getConsecutivoBanco() + " estado:" + estadoCargue);
	if (estadoCargue)
	    return estadoCargue;
	obtenerMsjErrorValidacion(numeroRefCargue, lanzar);
	if (!avaluo.isProvisional() && (avaluo.getListaAnexosPdf() == null || avaluo.getListaAnexosPdf().isEmpty())) {
	    return false;
	}
	return estadoCargue;
    }

}
