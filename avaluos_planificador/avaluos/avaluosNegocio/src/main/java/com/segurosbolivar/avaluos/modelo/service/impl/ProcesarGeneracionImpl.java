package com.segurosbolivar.avaluos.modelo.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

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
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.Ciudad;
import com.segurosbolivar.avaluos.modelo.entity.Departamento;
import com.segurosbolivar.avaluos.modelo.entity.LiquidacionAvaluo;
import com.segurosbolivar.avaluos.modelo.service.IParametrizacion;
import com.segurosbolivar.avaluos.modelo.service.IProcedatoGeneracion;

@Stateless
public class ProcesarGeneracionImpl implements IProcedatoGeneracion {

    private static final long serialVersionUID = -1459098820412835458L;
    Logger logger = Logger.getLogger(ProcesarGeneracionImpl.class);
    @EJB
    private ManejadorErroresNegocio mgrExc;
    @EJB
    private AvaluoDao avaluoDao;
    @EJB
    public IParametrizacion parametrizacionSvc;
    private static final String LISTA_CALIDAD = "CALIDAD";
    private static final String LISTA_AFIRMAR = "AFIRMAR";
    private static final String LISTA_INFOCONSTR = "INFOCONSTR";

    private void procesar(StringBuilder sb, Object valor, String dominio, String separador, String formato) throws NegocioException {
	String valorTexto = "";
	if (valor == null) {
	    if (!UtilTexto.estaVacio(separador))
		sb.append(separador);
	    return;
	}
	if (!UtilTexto.estaVacio(dominio)) {
	    if (!UtilTexto.estaVacio(valor.toString()))
		valorTexto = parametrizacionSvc.consultarDescripcionDominio(dominio, valor.toString());
	} else if (valor instanceof Long && ((Long) valor) != 0) {
	    valorTexto = UtilNumero.pasarTexto((Long) valor);
	} else if (valor instanceof String) {
	    valorTexto = (String) valor;
	} else if (valor instanceof BigDecimal) {
	    valorTexto = ((BigDecimal) valor).toPlainString();
	} else if (valor instanceof BigInteger) {
	    valorTexto = ((BigInteger) valor).toString();
	} else if (valor instanceof Date) {
	    valorTexto = UtilFecha.dateToString(formato, (Date) valor);
	}
	sb.append(valorTexto);
	if (!UtilTexto.estaVacio(separador))
	    sb.append(separador);
    }

    private void procesarCiudad(StringBuilder sb, Long valor, String separador, boolean dane) throws NegocioException {
	if (valor != null && valor != 0) {
	    Ciudad ciudad = parametrizacionSvc.consultarCiudad(valor);
	    if (ciudad != null)
		sb.append(dane ? ciudad.getCodDane() : ciudad.getDepartamento().getDepartamento());
	}
	if (!UtilTexto.estaVacio(separador))
	    sb.append(separador);
    }

    private void procesarDepartamento(StringBuilder sb, Long valor, String separador, boolean dane) throws NegocioException {
	if (valor != null && valor != 0) {
	    Departamento departamento = parametrizacionSvc.consultarDepartamento(valor);
	    if (departamento != null)
		sb.append(dane ? departamento.getCodDane() : departamento.getDepartamento());
	}
	if (!UtilTexto.estaVacio(separador))
	    sb.append(separador);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String generarTexto(Avaluo avaluo, Long linea) throws NegocioException {
	try {
	    StringBuilder registroAvaluo = new StringBuilder();
	    final String separador = "|";
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getAndenes(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getCalidadBanio(), LISTA_CALIDAD, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getCalidadMadera(), LISTA_CALIDAD, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getCalidMetal(), LISTA_CALIDAD, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getCalidadCocina(), "COCINA", separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getCalidadMuro(), LISTA_CALIDAD, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getCalidadPiso(), LISTA_CALIDAD, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getCalidadTecho(), LISTA_CALIDAD, separador, null);
	    procesarCiudad(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getCiudadEscritura(), separador, false);
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getClaseInmueble(), "CLASEINMUEBLE", separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getConjuntoAgrupacionCerrada(), LISTA_AFIRMAR,
		    separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getCubierta(), "CUBIERTA", separador, null);
	    procesarDepartamento(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getDepartamentoEscritura(), separador, false);
	    if (avaluo.getLiquidacionAvaluos() != null)
		for (LiquidacionAvaluo liquidacion : avaluo.getLiquidacionAvaluos()) {
		    procesar(registroAvaluo, liquidacion.getDescripcionLiquidacion(), null, separador, null);
		}
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getEstadoBanios(), LISTA_INFOCONSTR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getEstadoCocina(), LISTA_INFOCONSTR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getEstadoConservacion(), "ESTADOCONSERVACION",
		    separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getEstadoMadera(), LISTA_INFOCONSTR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getEstadoMuros(), LISTA_INFOCONSTR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getEstadoPisos(), LISTA_INFOCONSTR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getEstadoPisos(), LISTA_INFOCONSTR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getEstrato(), "ESTRATO", separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getEstructura(), "ESTRUCTURA", separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getEstructuraTechos(), LISTA_INFOCONSTR, separador,
		    null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getEstadosViaAcceso(), "MBR", separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getFachada(), "FACHADA", separador, null);
	    procesarCiudad(registroAvaluo, avaluo.getCiudad() == null ? null : avaluo.getCiudad().getIdCiudad(), separador, true);
	    procesar(registroAvaluo, avaluo.getCiudad() == null ? null : avaluo.getCiudad().getIdCiudad(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getDepartamento() == null ? null : avaluo.getDepartamento().getIdDepartamento(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getIdMetodologia(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getIdObjetoAvaluo(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getIdTipoIdentificacion(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getIluminacion(), "MBR", separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getLegalidad(), "LEGALBARRIO", separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getPavimentada(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getPisosBodega(), "PISOSBODEGA", separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getPropiedadHorizontal(), "SOMETIDOPROPIEDAD",
		    separador, null);
	    procesar(registroAvaluo, avaluo.getCondicionSalubridad() == null ? null : avaluo.getCondicionSalubridad().getCondicionSalubridad(), "MBR", separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getSardeneles(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getTipoFachada(), "TIPOFACHADA", separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getTipoVivienda(), "TIPOVIVIENDA", separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getTopografia(), "TOPOGRAFIA", separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getTransporte(), "MBR", separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getUbicacionInmueble(), "UBICACION", separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getUbicacion2(), "UBICACION2", separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getUsoInmueble(), "USOINMUEBLE", separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getVentilacion(), "MBR", separador, null);
	    procesar(registroAvaluo, avaluo.getFechaAvaluo(), null, separador, "dd/MM/yyy");
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getFechaEscritura(), null, separador, "dd/MM/yyy");
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getAireAcondicionado(), LISTA_AFIRMAR, separador,
		    null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getAcueductoPredio(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getAcueductoSector(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getCondicionSalubridad() == null ? null : avaluo.getCondicionSalubridad().getAguasServidas(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getAlamedas(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getAlcantarilladoPredio(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getAlcantarilladoSector(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getAlumbrado(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getCondicionSalubridad() == null ? null : avaluo.getCondicionSalubridad().getAmbientalArborizacion(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getCondicionSalubridad() == null ? null : avaluo.getCondicionSalubridad().getAmbientalZonaVerde(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getArborizacion(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getAscensor(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getCondicionSalubridad() == null ? null : avaluo.getCondicionSalubridad().getImpactoNegativoBasura(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getBicicletero(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getBombaEyectora(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getCanchaMultiple(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getCicloRutas(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getCitofono(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getClubHouse(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getComercio(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getElectricidadPredio(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getElectricidadSector(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getEnObra(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getEstadoRemodelacion(), LISTA_AFIRMAR, separador,
		    null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getEstadoTerminadaNueva(), LISTA_AFIRMAR, separador,
		    null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getEstadoTerminadoUsado(), LISTA_AFIRMAR, separador,
		    null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getGasSector(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getGimnasio(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getGarajeVisitante(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getGolfito(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getIndustria(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getCondicionSalubridad() == null ? null : avaluo.getCondicionSalubridad().getImpactoNegativoInseguridad(), LISTA_AFIRMAR, separador,
		    null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getJuegoNinos(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getOtrosUsos(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getParadero(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getParques(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getPiscina(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getPlanta(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getCondicionSalubridad() == null ? null : avaluo.getCondicionSalubridad().getImpactoNegativoPorAire(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getPorteria(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getPresion(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getCondicionSalubridad() == null ? null : avaluo.getCondicionSalubridad().getImpactoNegativoRuido(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getSalonComunal(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getShutBasuras(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getSinTerminar(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getSquash(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getTanqueAgua(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getTelefonoPredio(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getTelefonoSector(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getVivienda(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getZonasVerdes(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getZonasVerdes(), LISTA_AFIRMAR, separador, null);
	    if (avaluo.getLiquidacionAvaluos() != null)
		for (LiquidacionAvaluo liquidacion : avaluo.getLiquidacionAvaluos()) {
		    procesar(registroAvaluo, liquidacion.getAreaLiquidacion(), null, separador, null);
		}
	    procesar(registroAvaluo, avaluo.getLiquidacionTotal() == null ? null : avaluo.getLiquidacionTotal().getAvaluoUvr(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getZonasVerdes(), LISTA_AFIRMAR, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getBahiaComunal(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getBalcon(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getBanioPrivado(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getBanioServicio(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getBanioSocial(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getBodega(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getCocina(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getComedor(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getConsecutivoBanco(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getCuartoServicio(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getCubierto(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getDeposito(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getDescubierto(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getDoble(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getEstarHabitacion(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getEstudio(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getNumeroHabitaciones(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getNumeroIdentificacion(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getJardin(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getLocal(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getNumeroAscensores(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getNumeroEdificios(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getNumeroAscensores(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getOficina(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getPatioInterior(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getPisos(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getPrivado(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getSala(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getSencillo(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getServidumbre(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getSotanos(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getTerraza(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getCompOfertaDemanda() == null ? null : avaluo.getCompOfertaDemanda().getTipoComercializacion(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getLiquidacionTotal() == null ? null : avaluo.getLiquidacionTotal().getTotalAvaluo(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getTotalGarajes(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getTotalUnidades(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getUnidadPorPiso(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getUsoExclusivo(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getLiquidacionTotal() == null ? null : avaluo.getLiquidacionTotal().getValorAsegurable(), null, separador, null);
	    if (avaluo.getLiquidacionAvaluos() != null)
		for (LiquidacionAvaluo liquidacion : avaluo.getLiquidacionAvaluos()) {
		    procesar(registroAvaluo, liquidacion.getValorTotal(), null, separador, null);
		}
	    procesar(registroAvaluo, avaluo.getLiquidacionTotal() == null ? null : avaluo.getLiquidacionTotal().getValorUvrDia(), null, separador, null);
	    if (avaluo.getLiquidacionAvaluos() != null)
		for (LiquidacionAvaluo liquidacion : avaluo.getLiquidacionAvaluos()) {
		    procesar(registroAvaluo, liquidacion.getValor(), null, separador, null);
		}
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getVetustez(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getLiquidacionTotal() == null ? null : avaluo.getLiquidacionTotal().getCalificacion(), "CALGARANTIA", separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getZonaVerdePrivada(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getEstadoConstruccion(), "ESTADOCONSTRUCCION",
		    separador, null);
	    procesar(registroAvaluo, avaluo.getCompOfertaDemanda() == null ? null : avaluo.getCompOfertaDemanda().getActualidadEdificadora(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getCondicionSalubridad() == null ? null : avaluo.getCondicionSalubridad().getImpactoNegativoOtros(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getCondicionSalubridad() == null ? null : avaluo.getCondicionSalubridad().getAmbientalOtros(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getAvanceObra(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getBarrio(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getChip(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getCompOfertaDemanda() == null ? null : avaluo.getCompOfertaDemanda().getComportamiento(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getCubierta(), "CUBIERTA", separador, null);
	    procesar(registroAvaluo, avaluo.getObservacion() == null ? null : avaluo.getObservacion().getDireccionAnexos(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getObservacion() == null ? null : avaluo.getObservacion().getDireccionAnexos(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getDireccionInmueble() + (UtilTexto.estaVacio(avaluo.getDireccionComplementaria()) ? "" : avaluo.getDireccionComplementaria()), null,
		    separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getEstructura(), "ESTRUCTURA", separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getFachada(), "FACHADA", separador, null);
	    procesar(registroAvaluo, avaluo.getJustificacion(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getMatriculaInmobiliariaPpal1(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getMatriculaInmobiliariaPpal2(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getMatriculaInmobiliariaDeposito1(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getMatriculaInmobiliariaDeposito2(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getMatriculaInmobiliariaGaraje1(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getMatriculaInmobiliariaGaraje2(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getMatriculaInmobiliariaGaraje3(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getMatriculaInmobiliariaGaraje4(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getMatriculaInmobiliariaGaraje5(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getNombreConjuntoEdificio(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getNombreSolicitante(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getNotaria(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getNumeroEscritura(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getObservacion() == null ? null : avaluo.getObservacion().getOtrasDirecciones(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getOtroClase(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getOtrosDotacion(), null, separador, null);
	    procesar(registroAvaluo, "", null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getOtrosUsos(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getOtroUsoInmueble(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getPerspectivas(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getAltura(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getUbicacion3(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getLatitudSFBUA() + "," + avaluo.getLongitudSFBUA(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getReparadosBUA(), "REPARADOS_BUA", separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getIrregularidadAlturaBUA(), "IRREGULARIDAD_BUA",
		    separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getIrregularidadPlantaBUA(), "IRREGULARIDAD_BUA",
		    separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getEstructuraReforzadaBUA(),
		    "ESTRUCTURAREFORZADA_BUA", separador, null);
	    procesar(registroAvaluo, avaluo.getSistemaCoordenadasBUA(), "SISTEMACOORDENADAS_BUA", separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getDanoPrevio(), "DANOSPREVIOS_BUA", separador, null);
	    procesar(registroAvaluo, avaluo.getLatitudSFBUA(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getAnoConstruccionBUA(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getMaterialConstruccionBUA(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getLongitudSFBUA(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getDetalleMaterialSFBUA(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getIdCategoria(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getUsuario(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getSimetriaPlanta(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getMaterialEstructura(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getOtroMaterial(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getParapetosCubierta(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getUbicacionTanque(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getOtraUbicacionTanque(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getSobrePeso(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getGolpeteo(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getAsentamientos(), null, separador, null);
	    if (avaluo.getLiquidacionAvaluos() != null)
		for (LiquidacionAvaluo liquidacion : avaluo.getLiquidacionAvaluos()) {
		    procesar(registroAvaluo, liquidacion.getDescripcionDependencia(), null, separador, null);
		}
	    procesar(registroAvaluo, avaluo.getCondicionSalubridad() == null ? null : avaluo.getCondicionSalubridad().getRequiereCondicionesSalubridad(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getPisoInmueble(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getSimetriaAltura(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getSector(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getTelefonoSolicitante(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getCelularSolicitante(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getCorreoSolicitante(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getUbicacionGps(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getUbicacionFuentesHidricas(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getUbicacionNivelVia(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionBarrio() == null ? null : avaluo.getInformacionBarrio().getEdificacionesIguales(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getLuces(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getEdiContUso(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getRangoConstruccionSF(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getCatastralSF(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getDanoReparadoSF(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getSimetriaAlturaSF(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getSimetriaPlantaSF(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getDanoPrevioSF(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getEstructuraReforzadaSF(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getMaterialConstruccionSF(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getUsoInmuebleBUA(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionInmueble() == null ? null : avaluo.getInformacionInmueble().getUsoInmuebleSF(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getNombreConstructora(), null, separador, null);
	    procesar(registroAvaluo, avaluo.getInformacionConstruccion() == null ? null : avaluo.getInformacionConstruccion().getCodigoFinanciadoConstructor(), null, separador,
		    null);
	    registroAvaluo.append("\r\n");
	    avaluo.setNumLinea(linea);
	    avaluoDao.actualizar(avaluo);
	    return registroAvaluo.toString();
	} catch (Exception e) {
	    e.printStackTrace();
	    throw mgrExc.lanzarExcepcion(97, TipoErrorNegocio.ERROR, e.getMessage(), null);
	}
    }

}
