package com.segurosbolivar.avaluos.modelo.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.Asynchronous;
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
import com.segurosbolivar.avaluos.modelo.cons.SiNo;
import com.segurosbolivar.avaluos.modelo.data.AvaluoDao;
import com.segurosbolivar.avaluos.modelo.data.IndiceAcumuladoDao;
import com.segurosbolivar.avaluos.modelo.data.ParametrizacionDao;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.IndiceAcumulado;
import com.segurosbolivar.avaluos.modelo.entity.LiquidacionAvaluo;
import com.segurosbolivar.avaluos.modelo.entity.LiquidacionAvaluoTotal;
import com.segurosbolivar.avaluos.modelo.entity.Parametrizacion;
import com.segurosbolivar.avaluos.modelo.entity.PeritosEmpresa;
import com.segurosbolivar.avaluos.modelo.facade.IIntegradorFacade;
import com.segurosbolivar.avaluos.modelo.service.IComparar;
import com.segurosbolivar.avaluos.modelo.service.IDiligenciamiento;
import com.segurosbolivar.avaluos.modelo.service.IParametrizacion;
import com.segurosbolivar.avaluos.modelo.service.IPerito;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.ws.service.correo.ISolicitudesCliente;
import com.segurosbolivar.avaluos.modelo.ws.service.correo.SolicitudesCliente;
import com.segurosbolivar.avaluos.modelo.ws.service.correo.SolicitudesClienteThread;

/**
 * Implementación del servicio de avaluo.
 *
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:39 a.m.
 */
@Stateless
public class CompararImpl implements IComparar {

    private static final long serialVersionUID = 6734987786725921900L;

    private static final Logger log = Logger.getLogger(CompararImpl.class);

    @EJB
    private ManejadorErroresNegocio mgrExc;
    @EJB
    private IParametrizacion parametrizacionSvc;
    @EJB
    private IDiligenciamiento diligenciamientoSvc;
    @EJB
    private IIntegradorFacade integradorFacade;
    @EJB
    private IPerito peritoSvc;
    @EJB
    private AvaluoDao avaluoDao;

    @EJB
    private ParametrizacionDao parametrizacionDao;

    @EJB
    private IndiceAcumuladoDao indiceAcumuladoDao;

    @EJB
    private ISolicitudesCliente solicitudesClienteSvc;

    @Override
    @Asynchronous
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void confirmarAsegurabilidad(Avaluo avaluo, UsuarioDto usuario) {
	try {
	    boolean servicioCaido = false;
	    try {
		log.info("--Se inicia verificacion de la asegurabilidad del avaluo: " + avaluo.getConsecutivoBanco());
		avaluo.setAsegurabilidad(integradorFacade.obtenerAsegurabilidad(avaluo.getLatitudSFBUA(), avaluo.getLongitudSFBUA()));
	    } catch (Exception e) {
		log.info("--Se presenta inconveniente con el servicio de asegurabilidad para el avaluo: " + avaluo.getConsecutivoBanco());
		avaluo.setAsegurabilidad(UtilConstantes.CONCEPTO_ASEGURABILIDAD_PENDIENTE);
		servicioCaido = true;
	    }
	    verificarEstadoAsegurabilidad(servicioCaido, false);
	    avaluoDao.actualizar(avaluo);
	    log.info("--Se realiza la confirmacion de la alerta de asegurabilidad para el avaluo: " + avaluo.getConsecutivoBanco());
	    diligenciamientoSvc.confirmarAlerta(avaluo, UtilConstantes.ALT_ZONA_RIESGO_ALTO, usuario, avaluo.getAsegurabilidad().equals(SiNo.NO.getValor()));
	} catch (Exception e) {
	    log.error(e);
	}
    }

    @Override
    @Asynchronous
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void comparaAvaluo(Avaluo avaluo, UsuarioDto usuario) {
	try {
	    log.info("--inicia proceso de comparacion avaluo: " + avaluo.getConsecutivoBanco());
	    List<Avaluo> avaluosCercanos = obtenerAvaluosPorArea(avaluo, 500);
	    if (avaluosCercanos == null || avaluosCercanos.isEmpty())
		throw mgrExc.lanzarExcepcion(75, TipoErrorNegocio.ALERTA, null, new String[] { "500", avaluo.getConsecutivoBanco().toString() });
	    LiquidacionAvaluoTotal liqTotal = diligenciamientoSvc.consultarLiquidacionTotal(avaluo.getIdAvaluo());
	    BigDecimal totalValorAvaluo = liqTotal != null ? liqTotal.getTotalAvaluo() : BigDecimal.ZERO;
	    BigDecimal metrosCuadrados = obtieneCantidadMetros(avaluo);
	    BigDecimal costoMetroCuadrado = calcularValorMetroCuadrado(avaluo, false, metrosCuadrados);
	    if (costoMetroCuadrado.equals(new BigDecimal(-1)))
		throw mgrExc.lanzarExcepcion(76, TipoErrorNegocio.ALERTA, null, new String[] { avaluo.getConsecutivoBanco().toString() });
	    BigDecimal totalValorMetroCuadradoAvaluosCercanos = BigDecimal.ZERO;
	    BigDecimal totalValorMetroCuadradoAvaluosCercanosMismoAnio = BigDecimal.ZERO;
	    BigDecimal valorPromedio = BigDecimal.ZERO;
	    List<Avaluo> avaluosMismoAnio = new ArrayList<>();
	    int avaluosCercanosOmitidos = 0;
	    for (Avaluo avaluoCercano : avaluosCercanos) {
		// Se toma cada uno de los avaluos cercanos, se obtiene el valor por metro
		// cuadrado.
		BigDecimal metrosCuadradoAvaluoCercano = obtieneCantidadMetros(avaluo);
		BigDecimal costoMetroCuadradoAvaluoCercano = calcularValorMetroCuadrado(avaluoCercano, true, metrosCuadradoAvaluoCercano);
		if (costoMetroCuadradoAvaluoCercano.compareTo(new BigDecimal(-1)) == 0) {
		    avaluosCercanosOmitidos++;
		    continue;
		}
		// Si los avaluos son del mismo año se obtendra la sumatoria de los valores del
		// metro cuadrado, de lo contrario se sumara de los años anteriores.
		if (avaluosMismoAnio.isEmpty() && UtilFecha.obtenerAnio(avaluoCercano.getFechaAvaluo()).compareTo(UtilFecha.obtenerAnio(avaluo.getFechaAvaluo())) != 0) {
		    totalValorMetroCuadradoAvaluosCercanos = totalValorMetroCuadradoAvaluosCercanos.add(costoMetroCuadradoAvaluoCercano);
		    continue;
		}
		avaluosMismoAnio.add(avaluoCercano);
		totalValorMetroCuadradoAvaluosCercanosMismoAnio = totalValorMetroCuadradoAvaluosCercanosMismoAnio.add(costoMetroCuadradoAvaluoCercano);
	    }
	    // obtenemos el valor promedio diviviendo por la cantidad de avaluos, la
	    // sumatoria del valor del metro cuadrado de los avaluos, ya sean del mismo año
	    // o de los años anteriores.
	    if (!avaluosMismoAnio.isEmpty())
		valorPromedio = totalValorMetroCuadradoAvaluosCercanosMismoAnio.divide(new BigDecimal(avaluosMismoAnio.size()), 2, RoundingMode.HALF_UP);
	    else if (totalValorMetroCuadradoAvaluosCercanos.compareTo(BigDecimal.ZERO) != 0 && (avaluosCercanos.size() - avaluosCercanosOmitidos) != 0)
		valorPromedio = totalValorMetroCuadradoAvaluosCercanos.divide(new BigDecimal(avaluosCercanos.size() - avaluosCercanosOmitidos), 2, RoundingMode.HALF_UP);

	    // Se obtiene la diferencia entre el metro cuadrado del avaluo
	    // contra el valor promedio de los avaluos cercanos
	    double diferenciaCosto = 0.0;
	    if (valorPromedio.compareTo(BigDecimal.ZERO) != 0)
		diferenciaCosto = costoMetroCuadrado.divide(valorPromedio, 2, RoundingMode.HALF_UP).subtract(BigDecimal.ONE).doubleValue();
	    // Genera alerta y correo a la fabrica de credito por excederce del
	    // valor del costo
	    log.info("--verificacion alerta diferencia costo avaluo: " + avaluo.getConsecutivoBanco());
	    diligenciamientoSvc.confirmarAlerta(avaluo, UtilConstantes.ALT_COMPARATIVO_AVALUO, usuario, diferenciaCosto > 0.10);
	    if (diferenciaCosto > 0.10) {
		try {
		    log.info("--confirmacion alerta diferencia costo avaluo: " + avaluo.getConsecutivoBanco());
		    PeritosEmpresa perito = peritoSvc.consultarPeritoDocumento(Long.valueOf(usuario.getUsuario().getCodigo()));
		    String destinatario = diligenciamientoSvc.consultarDestinatariosNotificacion(UtilConstantes.PARAMETRIZACION_NOTIFICACION_DESTINATARIO_VALORCOMPARATIVO);
		    new SolicitudesClienteThread(solicitudesClienteSvc.valorComparativo(destinatario,
			    UtilNumero.pasarTexto(avaluo.getNumeroIdentificacion()),
			    parametrizacionSvc.consultarDescripcionDominio("TIPOIDENTIFICACION", UtilNumero.pasarTexto(avaluo.getIdTipoIdentificacion())),
			    new String[] { UtilConstantes.ALT_COMPARATIVO_AVALUO_ASUNTO,
				    UtilNumero.pasarTexto(avaluosMismoAnio.isEmpty() ? avaluosCercanos.size() - avaluosCercanosOmitidos : avaluosMismoAnio.size()),
				    metrosCuadrados.toPlainString(), UtilNumero.pasarTexto(avaluo.getConsecutivoBanco().longValue()),
				    UtilNumero.pasarTexto(perito.getNumeroDocumento()), perito.getNombrePerito(),
				    construirResumen(valorPromedio, !avaluosMismoAnio.isEmpty(), diferenciaCosto), UtilNumero.formatoMoneda(costoMetroCuadrado),
				    UtilNumero.formatoMoneda(totalValorAvaluo) })).EnviarCorreo();
		} catch (Exception e) {
		    log.error(e);
		}
	    }
	    // Valida que el avaluo no sea constructor, que el tipo de vivienda
	    // sea
	    // nueva y que no sea financiado constructor
	    log.info("--inicia  comparacion avaluo no constructor avaluo: " + avaluo.getConsecutivoBanco());
	    boolean validaConstructor = true;
	    validaConstructor &= avaluo.getInformacionConstruccion().getCodigoFinanciadoConstructor() == 0L;
	    validaConstructor &= !avaluo.isConstructor();
	    validaConstructor &= avaluo.getInformacionConstruccion().getEstadoConstruccion() == 1L;
	    if (!validaConstructor)
		return;
	    // Consulta los avaluos aprobados en un radio de 40 metros
	    List<Avaluo> avalProximos = obtenerAvaluosPorArea(avaluo, 40);
	    if (avalProximos == null || avalProximos.isEmpty()) {
		throw mgrExc.lanzarExcepcion(75, TipoErrorNegocio.ALERTA, null, new String[] { "40", avaluo.getConsecutivoBanco().toString() });
	    }
	    int tamanioProximos = avalProximos.size() + 1;
	    // Si el tamanio de los avaluos cercanos es mayor a 5 incluido
	    // el
	    // avaluo cercano envia alerta y correo
	    // a la fabrica de credito indicando el numero de avaluos
	    // encontrados en el cumulo de credito.
	    log.info("--verificacion  alerta avaluo no constructor avaluo: " + avaluo.getConsecutivoBanco());
	    diligenciamientoSvc.confirmarAlerta(avaluo, UtilConstantes.ALT_CUMULO_CREDITO_CONSTRUCTOR, usuario, tamanioProximos > 5);
	    if (tamanioProximos > 5) {
		try {
		    log.info("--confirmacion  alerta avaluo no constructor avaluo: " + avaluo.getConsecutivoBanco());
		    if (avalProximos == null || avalProximos.isEmpty())
			return;
		    StringBuilder consecutivos = new StringBuilder();
		    for (Avaluo proximo : avalProximos) {
			consecutivos.append(proximo.getConsecutivoBanco());
			consecutivos.append(", ");
		    }
		    String destinatarios = diligenciamientoSvc.consultarDestinatariosNotificacion(UtilConstantes.PARAMETRIZACION_NOTIFICACION_DESTINATARIO_NOCONSTRUCTOR);
		    new SolicitudesClienteThread(solicitudesClienteSvc.aprobadoNoConstructor(destinatarios,
			    UtilNumero.pasarTexto(avaluo.getNumeroIdentificacion()),
			    parametrizacionSvc.consultarDescripcionDominio("TIPOIDENTIFICACION", UtilNumero.pasarTexto(avaluo.getIdTipoIdentificacion())),
			    new String[] { UtilConstantes.ALT_CUMULO_CREDITO_CONSTRUCTOR_ASUNTO, UtilNumero.pasarTexto(tamanioProximos),
				    UtilNumero.pasarTexto(avaluo.getConsecutivoBanco().longValue()), UtilTexto.removerSufijo(consecutivos.toString().trim(), ","),
				    avaluo.obtenerDireccionCompleta() })).EnviarCorreo();
		} catch (Exception e) {
		    log.error(e);
		}
	    }
	} catch (NegocioException e) {
	    log.error(e);
	}
    }

    private String construirResumen(BigDecimal valorPromedio, boolean comparadoMismoAnio, double diferenciaCosto) {
	StringBuilder s = new StringBuilder();
	s.append("El valor promedio del metro cuadrado para los avaluos del comparativo es de: ");
	s.append(UtilNumero.formatoMoneda(valorPromedio));
	s.append(" Con una diferencia de costo entre el valor del metro cuadrado del avalúo y el valor promedio del metro cuadrado del comparativo igual a: ");
	s.append(diferenciaCosto * 100d);
	s.append(" %. La diferencia se confronto frente al promedio del valor de metro cuadrado del  ");
	s.append(comparadoMismoAnio ? "mismo año del avalúo. " : "histórico de avalúos de años anteriores.");
	return s.toString();
    }

    /**
     * Obtiene los avaluos mas cercanos al inmueble seleccionado
     * 
     * @param longitud
     * @param latitud
     * @param radio
     * @return
     */
    private List<Avaluo> obtenerAvaluosPorArea(Avaluo avaluo, int radio) {
	log.info("--inicia consulta avaluos cercanos avaluo: " + avaluo.getConsecutivoBanco());
	List<Avaluo> listaAvaluo = radio == 40 ? avaluoDao.consultaCumulo(avaluo) : avaluoDao.consultaPorArea(avaluo);
	log.info("--finaliza  consulta avaluos cercanos avaluo: " + avaluo.getConsecutivoBanco());
	List<Avaluo> avaluosSeleccionado = new ArrayList<>();
	double distancia = 0;
	if (listaAvaluo == null || listaAvaluo.isEmpty()) {
	    return Collections.emptyList();
	}

	for (Avaluo avaluoPotencial : listaAvaluo) {
	    boolean validaCoordenadas = true;
	    validaCoordenadas &= !UtilTexto.estaVacio(avaluo.getLatitudSFBUA());
	    validaCoordenadas &= !UtilTexto.estaVacio(avaluoPotencial.getLatitudSFBUA());
	    validaCoordenadas &= !UtilTexto.estaVacio(avaluo.getLongitudSFBUA());
	    validaCoordenadas &= !UtilTexto.estaVacio(avaluoPotencial.getLongitudSFBUA());
	    if (!validaCoordenadas) {
		distancia = -1;
	    } else {
		distancia = calculaDistancia(Double.parseDouble(avaluo.getLatitudSFBUA()), Double.parseDouble(avaluoPotencial.getLatitudSFBUA()),
			Double.parseDouble(avaluo.getLongitudSFBUA()), Double.parseDouble(avaluoPotencial.getLongitudSFBUA()));
//		log.info(avaluoPotencial.getConsecutivoBanco() + ":" + avaluo.getLatitudSFBUA() + "," + avaluo.getLongitudSFBUA() + ":" + radio + ":" + distancia + ":"
//			+ avaluoPotencial.getLatitudSFBUA() + "," + avaluoPotencial.getLongitudSFBUA());
	    }
	    if (distancia <= radio && distancia != -1) {
		avaluosSeleccionado.add(avaluoPotencial);
	    }

	}
	return avaluosSeleccionado;
    }

    /**
     * Calcula la distancia geografica entre un avaluo inicial y un avaluo destino
     * 
     * @param latitudA
     * @param latitudB
     * @param longitudA
     * @param longitudB
     * @return
     */
    private double calculaDistancia(double latitudA, double latitudB, double longitudA, double longitudB) {
	double mulSen = Math.sin(Math.toRadians(latitudA)) * Math.sin(Math.toRadians(latitudB));
	double mulCos = Math.cos(Math.toRadians(latitudA)) * Math.cos(Math.toRadians(latitudB));
	double difCosLong = Math.cos(Math.toRadians(longitudA) - Math.toRadians(longitudB));
	return Math.acos(mulSen + mulCos * difCosLong) * 6371000;
    }

    private BigDecimal obtieneCantidadMetros(Avaluo avaluo) throws NegocioException {
	BigDecimal totalMetrosCuadrados = BigDecimal.ZERO;
	List<LiquidacionAvaluo> liquidaciones = diligenciamientoSvc.consultarLiquidacion(avaluo.getIdAvaluo());
	if (liquidaciones == null || liquidaciones.isEmpty())
	    return totalMetrosCuadrados;
	for (LiquidacionAvaluo liquidacion : liquidaciones) {
	    if (liquidacion.getAreaLiquidacion() != null)
		totalMetrosCuadrados = totalMetrosCuadrados.add(liquidacion.getAreaLiquidacion());
	}
	return totalMetrosCuadrados;
    }

    /**
     * Metodo que obtiene el valor del metro cuadrado de un avaluo hipotecario.
     * 
     * @param avaluo
     * @return
     * @throws NegocioException
     */
    private BigDecimal calcularValorMetroCuadrado(Avaluo avaluo, boolean aplicarIndice, BigDecimal totalMetrosCuadradoAvaluo) throws NegocioException {
	if (totalMetrosCuadradoAvaluo.compareTo(BigDecimal.ZERO) == 0)
	    return new BigDecimal(-1);
	LiquidacionAvaluoTotal valorLiqTotal = diligenciamientoSvc.consultarLiquidacionTotal(avaluo.getIdAvaluo());
	if(valorLiqTotal == null)
		return new BigDecimal(-1);
	BigDecimal valorMetroCuadradoAvaluo = valorLiqTotal.getTotalAvaluo().divide(totalMetrosCuadradoAvaluo, 2 , RoundingMode.HALF_UP);
	if (!aplicarIndice)
	    return valorMetroCuadradoAvaluo;
	if (avaluo.getCiudad() == null)
	    return new BigDecimal(-1);
	IndiceAcumulado indice = new IndiceAcumulado();
	// Si la ciudad es bogota se debe aplicar tambien el estrato
	if (avaluo.getCiudad().getCodDane() == 11001L)
	    indice.setEstrato(avaluo.getInformacionBarrio().getEstrato());
	indice.setCiudad(avaluo.getCiudad());
	indice.setIdCiudad(avaluo.getIdCiudad());
	indice.setDepartamento(avaluo.getDepartamento());
	indice.setIdDepartamento(avaluo.getIdDepartamento());
	indice.setAnio(UtilFecha.obtenerAnio(avaluo.getFechaAvaluo()).longValue());
	log.info(indice);
	List<IndiceAcumulado> resultado = indiceAcumuladoDao.consultar(indice, 0, Integer.MAX_VALUE, null, null);
	if (resultado == null || resultado.isEmpty())
	    return new BigDecimal(-1);
	return resultado.get(0).getValor().multiply(valorMetroCuadradoAvaluo);
    }

	private void verificarEstadoAsegurabilidad(boolean servicioCaido, boolean reenviar) throws Exception {
		//	String email = diligenciamientoSvc.consultarDestinatariosNotificacion();
		String email;
		String numDoc = "123456789"; // Numero de documento.
		String tipDoc = "CC"; // Tipo de documento.
		Date currentDate = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		String fechaActual = dateFormat.format(currentDate);
		String horaActual = timeFormat.format(currentDate);
		SolicitudesCliente solicitud = null;

		// Consultar parametros del estado de asegurabilidad.

		String tipoParametro = "consultarAsegurabilidad";
		String nombreParametro = "status";
		Parametrizacion parametroAsegurabilidad = parametrizacionDao.getParametro(tipoParametro, nombreParametro);
		String estadoParametroActual = parametroAsegurabilidad.getValorparametro();
		if (servicioCaido && (reenviar || UtilTexto.estaVacio(estadoParametroActual)
				|| estadoParametroActual.equalsIgnoreCase(UtilConstantes.ASEGURABILIDAD_ACTIVA))) {
			email = diligenciamientoSvc.consultarDestinatariosNotificacion(UtilConstantes.PARAMETRIZACION_NOTIFICACION_DESTINATARIO_ASEGURABILIDADCAIDO);
			solicitud = solicitudesClienteSvc.asegurabilidadCaido(email, numDoc, tipDoc,
					new String[] { "Servicio de asegurabilidad caido", fechaActual, horaActual });
			parametroAsegurabilidad.setValorparametro(UtilConstantes.ASEGURABILIDAD_INACTIVA);
			try {
				parametrizacionDao.actualizar(parametroAsegurabilidad);
			} catch (NegocioException e) {
				log.error(e);
			}
		} else if (!servicioCaido && estadoParametroActual != null
				&& estadoParametroActual.equalsIgnoreCase(UtilConstantes.ASEGURABILIDAD_INACTIVA)) {
			email = diligenciamientoSvc.consultarDestinatariosNotificacion(UtilConstantes.PARAMETRIZACION_NOTIFICACION_DESTINATARIO_ASEGURABILIDADFUNCIONANDO);
			solicitud = solicitudesClienteSvc.asegurabilidadPwaFuncionando(email, numDoc, tipDoc,
					new String[] { "Servicio de asegurabilidad activo", fechaActual, horaActual });
			parametroAsegurabilidad.setValorparametro(UtilConstantes.ASEGURABILIDAD_ACTIVA);
			try {
				parametrizacionDao.actualizar(parametroAsegurabilidad);
			} catch (NegocioException e) {
				log.error(e);
			}
		}
		if (solicitud != null) {
			SolicitudesClienteThread sct = new SolicitudesClienteThread(solicitud);
			sct.EnviarCorreo();
		}
	}
}
