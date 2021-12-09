package com.segurosbolivar.avaluos.modelo.service.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioAlertaException;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.reflection.CopiarPropiedades;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.cons.EstadoAvaluo;
import com.segurosbolivar.avaluos.modelo.cons.Procedencia;
import com.segurosbolivar.avaluos.modelo.cons.SeccionConstruccion;
import com.segurosbolivar.avaluos.modelo.data.AlertaDao;
import com.segurosbolivar.avaluos.modelo.data.AnexoFotograficoDao;
import com.segurosbolivar.avaluos.modelo.data.ArchivoDao;
import com.segurosbolivar.avaluos.modelo.data.ArchivoTmpDao;
import com.segurosbolivar.avaluos.modelo.data.AvaluoDao;
import com.segurosbolivar.avaluos.modelo.data.ListaAnexosPdfDao;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.AnexoFotografico;
import com.segurosbolivar.avaluos.modelo.entity.Archivo;
import com.segurosbolivar.avaluos.modelo.entity.ArchivosTmp;
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
import com.segurosbolivar.avaluos.modelo.service.IArchivo;
import com.segurosbolivar.avaluos.modelo.service.IAvaluo;
import com.segurosbolivar.avaluos.modelo.service.ICopiaAvaluo;
import com.segurosbolivar.avaluos.modelo.service.IDiligenciamiento;
import com.segurosbolivar.avaluos.modelo.service.ITronador;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;

/**
 * Implementación del servicio de avaluo.
 *
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:39 a.m.
 */
@Stateless
public class CopiaAvaluoImpl implements ICopiaAvaluo {

    private static final long serialVersionUID = 6595343318974919524L;

    private static final Logger log = Logger.getLogger(CopiaAvaluoImpl.class);
    
    private static final int NUMERO_FOTOS = 15;

    @EJB
    private ManejadorErroresNegocio mgrExc;
    @EJB
    private IDiligenciamiento diligenciamientoSvc;
    @EJB
    private IArchivo archivoSvc;
    @EJB
    private ITronador tronadorSvc;
    @EJB
    private IAvaluo avaluoSvc;
    @EJB
    private ListaAnexosPdfDao anexosPdfDao;
    @EJB
    private AnexoFotograficoDao anexoFotogragicoDao;
    @EJB
    private ArchivoDao archivoDao;
    @EJB
    private AlertaDao alertaDao;
    @EJB
    private ArchivoTmpDao archivoTmpDao;
    @EJB
    private AvaluoDao avaluoDao;

    @Override
    public void validarCopia(List<Avaluo> copias, boolean replicacion) throws NegocioException {
	NegocioException ex = mgrExc.lanzarExcepcion(114, TipoErrorNegocio.ERROR);
	List<BigInteger> consecutivosAGenerar = new ArrayList<>();
	for (Avaluo original : copias) {
	    try {
		Avaluo copia = original.clonar();
		if (replicacion) {
		    if (copia.getEstadoAvaluo() == null || copia.getEstadoAvaluo().compareTo(EstadoAvaluo.APROBADO.getValor()) != 0)
			throw mgrExc.lanzarExcepcion(51, TipoErrorNegocio.INFO, null, new String[] { copia.getConsecutivoBanco().toString() });
		}
		copia.setFechaAvaluo(new Date());
		copia.setIdAvaluo(null);
		copia.generarConsecutivoBanco();
		avaluoSvc.validarRepeticionConsecutivoBanco(copia);
		if (consecutivosAGenerar.contains(copia.getConsecutivoBanco()))
		    throw mgrExc.lanzarExcepcion(14, TipoErrorNegocio.ERROR, null, new String[] { copia.getConsecutivoBanco().toString() });
		consecutivosAGenerar.add(copia.getConsecutivoBanco());
	    } catch (NegocioException e) {
	    	e.printStackTrace();
	    	ex.agregarError(e);
	    	break;
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	throw e;
	    }
	}
	if (ex.getErrores() != null && !ex.getErrores().isEmpty())
	    throw ex;
    }
    
    @Override
	public void validarCopiaMultiple(List<Avaluo> copias) throws NegocioException {
		NegocioException ex = mgrExc.lanzarExcepcion(114, TipoErrorNegocio.ERROR);
		List<BigInteger> consecutivosAGenerar = new ArrayList<>();
		for (Avaluo original : copias) {
			try {
				Avaluo copia = original.clonar();
				copia.setFechaAvaluo(new Date());
				copia.setIdAvaluo(null);
				copia.generarConsecutivoBanco();
				List<Avaluo> repetidos = avaluoDao.consultarConsecutivoBanco(copia);
				if (repetidos != null && !repetidos.isEmpty()) {
				  ex.agregarError(mgrExc.lanzarExcepcion(115, TipoErrorNegocio.ALERTA, null,new String[] { copia.getNumeroIdentificacion().toString(), copia.getConsecutivoBanco().toString() }));
				}
				if (consecutivosAGenerar.contains(copia.getConsecutivoBanco()))
				  ex.agregarError(mgrExc.lanzarExcepcion(116, TipoErrorNegocio.ALERTA, null,new String[] { copia.getNumeroIdentificacion().toString()}));
				consecutivosAGenerar.add(copia.getConsecutivoBanco());
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		if (ex.getErrores() != null && !ex.getErrores().isEmpty())
			throw ex;
	}

   @Override
	public void transcribir(Avaluo copia, Avaluo original, UsuarioDto usuario) throws NegocioException {
		try {
			if (usuario == null || usuario.getUsuario() == null
					|| UtilTexto.estaVacio(usuario.getUsuario().getCodigo()))
				throw mgrExc.lanzarExcepcion(11L, TipoErrorNegocio.ERROR);
			if (copia == null)
				throw mgrExc.lanzarExcepcion(34, TipoErrorNegocio.ERROR);
			List<AnexoFotografico> anexosGuardar = new ArrayList<>();
			List<ListaAnexosPdf> anexosPdfGuardar = new ArrayList<>();
			List<ArchivosTmp> archivosTmp = null;
			AnexoFotografico copiaAnexo = new AnexoFotografico();
			Archivo archivoOriginal = null;
			byte[] contenido = null;
			if (original.getCodigoProcedencia().equals(Procedencia.CARGUE_MASIVO.getValor())) {
				archivosTmp = archivoTmpDao
						.buscarArchivosPorNombre(original.getConsecutivoBanco().toString() + "_fachada.jpg");
			} else {
				if(!original.isProvisional()) {
					CopiarPropiedades.copiarPropiedades(copiaAnexo, original.getAnexosFotograficos());
					if (copiaAnexo.getIdImgFachada() == null)
						return;
					archivoOriginal = archivoDao.buscar(copiaAnexo.getIdImgFachada());
					if (copiaAnexo.getIdImgFachada().compareTo(copiaAnexo.getIdDocAnexos()) != 0)
						// Si esta el BLOB lo guarda en el byte esta es la fachada
						contenido = archivoOriginal.getContenidoArchivo();
				}	
			}
			actualizarCoordenadas(copia);
			copia.setEstadoAvaluo(EstadoAvaluo.NUEVO.getValor());
			copiarAvaluo(copia, original, Procedencia.TRANSCRIPCION, usuario);
			prepararAnexosGuardarMultiple(anexosGuardar, anexosPdfGuardar,
					anexosPdfDao.consultaAnexosPorAvaluo(original), original, copia, usuario, archivosTmp, copiaAnexo,
					archivoOriginal, contenido);
			copiaRegistroFotograficoMultiplesAvaluos(anexosGuardar, anexosPdfGuardar, usuario);
		} catch (Exception e) {
			e.printStackTrace();
			throw mgrExc.lanzarExcepcion(47, TipoErrorNegocio.ERROR, e.getMessage(), null);
		}
	}
    
    private void actualizarCoordenadas(Avaluo copia) throws IOException, Exception {
    	String latitudOriginal = copia.getLatitudSFBUA();
    	String longitudOriginal = copia.getLongitudSFBUA();
    	if (copia.getCodigoDaneDepartamento() != null && copia.getCodigoDaneCiudad() != null) {
    		ArrayList<String> respuesta = null;
			if(copia.getDireccionInmueble().isEmpty() && !copia.getDireccionComplementaria().isEmpty()) {
				respuesta = avaluoSvc.obtenerExtentCentroide((copia.getCodigoDaneDepartamento() < 10 ? "0" : "") + copia.getCodigoDaneDepartamento(),(copia.getCodigoDaneDepartamento() < 10 ? "0" : "") + copia.getCodigoDaneCiudad(),copia.getDireccionComplementaria(), copia.getBarrio());
			}else if(!copia.getDireccionInmueble().isEmpty()){
				respuesta= avaluoSvc.obtenerExtentCentroide((copia.getCodigoDaneDepartamento() < 10 ? "0" : "") + copia.getCodigoDaneDepartamento(),(copia.getCodigoDaneDepartamento() < 10 ? "0" : "") + copia.getCodigoDaneCiudad(),copia.getDireccionInmueble(), copia.getBarrio());
			}
			
			boolean respuestaFallida = respuesta == null || respuesta.isEmpty() || respuesta.get(0).equalsIgnoreCase("None") || respuesta.get(0).equalsIgnoreCase("Error");
			if (!respuestaFallida && respuesta.size() > 4) {
				copia.setLongitudSFBUA(respuesta.get(4));
				copia.setLatitudSFBUA(respuesta.get(5));
				copia.setUbicacionGps(copia.getLatitudSFBUA() + "," + copia.getLongitudSFBUA());
			}

			if (copia.getLongitudSFBUA() == null && copia.getLatitudSFBUA() == null) {
				copia.setLatitudSFBUA(latitudOriginal);
				copia.setLongitudSFBUA(longitudOriginal);
				copia.setUbicacionGps(copia.getLatitudSFBUA() + "," + copia.getLongitudSFBUA());
			}
		}
    }

//	@Asynchronous
	public void replicar(List<Avaluo> seleccionados, UsuarioDto usuario) throws NegocioException {
		try {
			if ((usuario == null) || (usuario.getUsuario() == null)) {
				throw mgrExc.lanzarExcepcion(11L, TipoErrorNegocio.ERROR);
			}
			if (seleccionados == null || seleccionados.isEmpty())
				throw mgrExc.lanzarExcepcion(34, TipoErrorNegocio.ERROR);
			long inicial = System.currentTimeMillis();
			List<AnexoFotografico> anexosGuardar = new ArrayList<>();
			List<ListaAnexosPdf> anexosPdfGuardar = new ArrayList<>();
			for (Avaluo original : seleccionados) {
				List<ArchivosTmp> archivosTmp = null;
				AnexoFotografico copiaAnexo =	new AnexoFotografico();
			    Archivo archivoOriginal = null;
			    byte[] contenido = null;
			    if (original.getCodigoProcedencia().equals(Procedencia.CARGUE_MASIVO.getValor())) {
			    	archivosTmp = archivoTmpDao.buscarArchivosPorNombre(original.getConsecutivoBanco().toString() + "_fachada.jpg");
			    }else {
			    	if(!original.isProvisional()) {
			    		CopiarPropiedades.copiarPropiedades(copiaAnexo,original.getAnexosFotograficos()); 
						if(copiaAnexo.getIdImgFachada() == null)
							return;
						archivoOriginal = archivoDao.buscar(copiaAnexo.getIdImgFachada());
						if(copiaAnexo.getIdImgFachada().compareTo(copiaAnexo.getIdDocAnexos())!=0)
							//Si esta el BLOB lo guarda en el byte esta es la fachada
							contenido = archivoOriginal.getContenidoArchivo();
			    	}
			    }
				Avaluo replica = original.clonar();
				replica.setFechaAvaluo(new Date());
				replica.setEstadoAvaluo(EstadoAvaluo.NUEVO.getValor());
				List<ListaAnexosPdf> anexosOriginales = anexosPdfDao.consultaAnexosPorAvaluo(original);
				copiarAvaluo(replica, original, Procedencia.REPLICA, usuario);
				prepararAnexosGuardarMultiple(anexosGuardar, anexosPdfGuardar, anexosOriginales, original, replica, usuario,archivosTmp,copiaAnexo,archivoOriginal,contenido);
			}
			copiaRegistroFotograficoMultiplesAvaluos(anexosGuardar, anexosPdfGuardar, usuario);
			log.info("se genero la replica de " + seleccionados.size() + " avaluos");
			log.info("Tiempo para la replica: " + (System.currentTimeMillis() - inicial) + " ms");
		} catch (Exception e) {
			e.printStackTrace();
			throw mgrExc.lanzarExcepcion(36, TipoErrorNegocio.ERROR, e.getMessage(), null);
		}
	}

    @Override
    public void multipleConstructor(List<Avaluo> copias, Avaluo original, Long codigoNombreConstructora, String nombreConstructora, UsuarioDto usuario) throws Exception {
		try {
		    if ((usuario == null) || (usuario.getUsuario() == null)) {
			throw mgrExc.lanzarExcepcion(11L, TipoErrorNegocio.ERROR);
		    }
		    if (copias == null || copias.isEmpty())
			throw mgrExc.lanzarExcepcion(34, TipoErrorNegocio.ERROR);
		    long inicial = System.currentTimeMillis();
		    List<ListaAnexosPdf> anexosOriginales = anexosPdfDao.consultaAnexosPorAvaluo(original);
		    List<AnexoFotografico> anexosGuardar = new ArrayList<>();
		    List<ListaAnexosPdf> anexosPdfGuardar = new ArrayList<>();
		    List<ArchivosTmp> archivosTmp = null;
		    AnexoFotografico copiaAnexo =	new AnexoFotografico();
		    Archivo archivoOriginal = null;
		    byte[] contenido = null;
		    if (original.getCodigoProcedencia().equals(Procedencia.CARGUE_MASIVO.getValor())) {
		    	archivosTmp = archivoTmpDao.buscarArchivosPorNombre(original.getConsecutivoBanco().toString() + "_fachada.jpg");
		    }else {
		    	if(!original.isProvisional()) {
		    	CopiarPropiedades.copiarPropiedades(copiaAnexo,original.getAnexosFotograficos());
					archivoOriginal = archivoDao.buscar(copiaAnexo.getIdImgFachada());
					if(copiaAnexo.getIdImgFachada().compareTo(copiaAnexo.getIdDocAnexos())!=0)
						//Si esta el BLOB lo guarda en el byte esta es la fachada
						contenido = archivoOriginal.getContenidoArchivo();
		    	}
		    }
		     
			for (Avaluo copia : copias) {
		    	generaCopiaMultiple(copia,original,codigoNombreConstructora,nombreConstructora,usuario,anexosGuardar,anexosOriginales,anexosPdfGuardar,archivosTmp,copiaAnexo,archivoOriginal,contenido);
			}
		    copiaRegistroFotograficoMultiplesAvaluos(anexosGuardar, anexosPdfGuardar, usuario);
		    log.info("se genero la replica de " + copias.size() + " avaluos");
		    log.info("Tiempo para la replica: " + (System.currentTimeMillis() - inicial) + " ms");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
    }
    
    
    private void generaCopiaMultiple(Avaluo copia, Avaluo original, Long codigoNombreConstructora, String nombreConstructora, UsuarioDto usuario, List<AnexoFotografico> anexosGuardar, List<ListaAnexosPdf> anexosOriginales, List<ListaAnexosPdf> anexosPdfGuardar, List<ArchivosTmp> archivosTmp, AnexoFotografico guardar, Archivo archivoOriginal, byte[] contenido) throws Exception  {
		Avaluo replica = original.clonar();
		replica.setFechaAvaluo(new Date());
		replica.setNombreSolicitante(copia.getNombreSolicitante());
		replica.setIdTipoIdentificacion(copia.getIdTipoIdentificacion());
		replica.setNumeroIdentificacion(copia.getNumeroIdentificacion());
		replica.setDireccionInmueble(copia.getDireccionInmueble());
		replica.setDireccionComplementaria(copia.getDireccionComplementaria());
		replica.setTelefonoSolicitante(copia.getTelefonoSolicitante());
		replica.setCelularSolicitante(copia.getCelularSolicitante());
		replica.setCorreoSolicitante(copia.getCorreoSolicitante());
		replica.setMatriculaInmobiliariaPpal1(copia.getMatriculaInmobiliariaPpal1());
		replica.setMatriculaInmobiliariaPpal2(copia.getMatriculaInmobiliariaPpal2());
		replica.setMatriculaInmobiliariaGaraje1(copia.getMatriculaInmobiliariaGaraje1());
		replica.setMatriculaInmobiliariaGaraje2(copia.getMatriculaInmobiliariaGaraje2());
		replica.setMatriculaInmobiliariaDeposito1(copia.getMatriculaInmobiliariaDeposito1());
		replica.setCodigoNombreConstructora(codigoNombreConstructora);
		replica.setNombreConstructora(nombreConstructora);		
		replica.setEstadoAvaluo(EstadoAvaluo.NUEVO.getValor());
		replica.setLongitudSFBUA(copia.getLongitudSFBUA());
		replica.setLatitudSFBUA(copia.getLatitudSFBUA());
		replica.setUbicacionGps(copia.getUbicacionGps());
		copiarAvaluo(replica, original, Procedencia.COPIA_MULTIPLE, usuario);
		diligenciamientoSvc.generarAlertasAvaluo(replica, usuario);
		prepararAnexosGuardarMultiple(anexosGuardar, anexosPdfGuardar, anexosOriginales, original, replica, usuario, archivosTmp,  guardar, archivoOriginal, contenido);
		diligenciamientoSvc.enviarCorreosAvaluo(replica, usuario);   
    }

 

    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void copiarAvaluo(Avaluo replica, Avaluo original, Procedencia procedencia, UsuarioDto usuario) throws Exception {
	replica.setIdAvaluo(null);
	replica.setProvisional(false);
	replica.generarConsecutivoBanco();
	replica.setIdAvaluoPadre(original.getIdAvaluo());
	replica.setIdArchivo(null);
	replica.setNumLinea(null);
	avaluoSvc.guardar(replica, usuario, procedencia);
	copiarSeccionBarrio(original, replica, usuario);
	copiarSeccionInmueble(original, replica, usuario);
	copiarSeccionConstruccion(original, replica, usuario);
	copiarSeccionSalubridad(original, replica, usuario);
	copiarSeccionOferta(original, replica, usuario);
	copiarSeccionLiquidacion(original, replica, usuario);
	copiarSeccionObservaciones(original, replica, usuario);
    }

    private void copiarSeccionBarrio(Avaluo original, Avaluo copia, UsuarioDto usuario) throws NegocioException {
	try {
	    InformacionBarrio guardar = original.getInformacionBarrio() != null ? original.getInformacionBarrio().clone() : null;
	    if (guardar == null)
		return;
	    guardar.setIdAvaluo(copia.getIdAvaluo());
	    guardar.setIdInformacionBarrio(null);
	    guardar.setAvaluo(copia);
	    diligenciamientoSvc.guardar(guardar, usuario);
	} catch (NegocioException e) {
	    throw e;
	} catch (Exception e) {
	    throw mgrExc.lanzarExcepcion(37, TipoErrorNegocio.ERROR, e.getMessage(), null);
	}
    }

    private void copiarSeccionInmueble(Avaluo original, Avaluo copia, UsuarioDto usuario) throws NegocioException {
	try {
	    InformacionInmueble guardar = original.getInformacionInmueble() != null ? original.getInformacionInmueble().clone() : null;
	    if (guardar == null && UtilTexto.estaVacio(copia.getMatriculaInmobiliariaPpal1()))
		return;
	    else if (guardar == null) {
		guardar = new InformacionInmueble();
	    }
	    if (!UtilTexto.estaVacio(copia.getMatriculaInmobiliariaPpal1())) {
		guardar.setMatriculaInmobiliariaPpal1(copia.getMatriculaInmobiliariaPpal1());
		guardar.setMatriculaInmobiliariaPpal2(copia.getMatriculaInmobiliariaPpal2());
		guardar.setMatriculaInmobiliariaGaraje1(copia.getMatriculaInmobiliariaGaraje1());
		guardar.setMatriculaInmobiliariaGaraje2(copia.getMatriculaInmobiliariaGaraje2());
		guardar.setMatriculaInmobiliariaGaraje3(null);
		guardar.setMatriculaInmobiliariaGaraje4(null);
		guardar.setMatriculaInmobiliariaGaraje5(null);
		guardar.setMatriculaInmobiliariaDeposito1(copia.getMatriculaInmobiliariaDeposito1());
		guardar.setMatriculaInmobiliariaDeposito2(null);
	    }
	    guardar.setIdAvaluo(copia.getIdAvaluo());
	    guardar.setIdInfinmueble(null);
	    guardar.setAvaluo(copia);
	    diligenciamientoSvc.guardar(guardar, copia, usuario);
	} catch (NegocioException e) {
	    throw e;
	} catch (Exception e) {
	    throw mgrExc.lanzarExcepcion(38, TipoErrorNegocio.ERROR, e.getMessage(), null);
	}
    }

    private void copiarSeccionConstruccion(Avaluo original, Avaluo copia, UsuarioDto usuario) throws NegocioException {
	try {
	    InformacionConstruccion guardar = original.getInformacionConstruccion() != null ? original.getInformacionConstruccion().clone() : null;
	    if (guardar == null)
		return;
	    guardar.setIdAvaluo(copia.getIdAvaluo());
	    guardar.setIdInformacionConstruccion(null);
	    guardar.setCodigoNombreConstructora(copia.getCodigoNombreConstructora());
	    guardar.setNombreConstructora(copia.getNombreConstructora());
	    guardar.setAvaluo(copia);
	    diligenciamientoSvc.guardar(guardar, SeccionConstruccion.NINGUNA, copia, usuario);
	} catch (NegocioException e) {
	    throw e;
	} catch (Exception e) {
	    throw mgrExc.lanzarExcepcion(39, TipoErrorNegocio.ERROR, e.getMessage(), null);
	}
    }

    private void copiarSeccionSalubridad(Avaluo original, Avaluo copia, UsuarioDto usuario) throws NegocioException {
	try {
	    CondicionesSalubridad guardar = original.getCondicionSalubridad() != null ? original.getCondicionSalubridad().clone() : null;
	    if (guardar == null)
		return;
	    guardar.setIdAvaluo(copia.getIdAvaluo());
	    guardar.setIdCondicionSalubridad(null);
	    guardar.setAvaluo(copia);
	    diligenciamientoSvc.guardar(guardar, usuario);
	} catch (NegocioException e) {
	    throw e;
	} catch (Exception e) {
	    throw mgrExc.lanzarExcepcion(40, TipoErrorNegocio.ERROR, e.getMessage(), null);
	}
    }

    private void copiarSeccionOferta(Avaluo original, Avaluo copia, UsuarioDto usuario) throws NegocioException {
	try {
	    ComportamientoOfertaDemanda guardar = original.getCompOfertaDemanda() != null ? original.getCompOfertaDemanda().clone() : null;
	    if (guardar == null)
		return;
	    guardar.setIdAvaluo(copia.getIdAvaluo());
	    guardar.setIdComportamientoOfertaDemanda(null);
	    guardar.setAvaluo(copia);
	    diligenciamientoSvc.guardar(guardar, usuario);
	} catch (NegocioException e) {
	    throw e;
	} catch (Exception e) {
	    throw mgrExc.lanzarExcepcion(41, TipoErrorNegocio.ERROR, e.getMessage(), null);
	}
    }

    private void copiarSeccionLiquidacion(Avaluo original, Avaluo copia, UsuarioDto usuario) throws NegocioException {
	try {
	    LiquidacionAvaluoTotal guardar = original.getLiquidacionTotal() != null ? original.getLiquidacionTotal().clone() : null;
	    if (guardar == null)
		return;
	    guardar.setIdAvaluo(copia.getIdAvaluo());
	    guardar.setIdLiqavaluoTotal(null);
	    guardar.setAvaluo(copia);
	    guardar.setValorUvrDia(tronadorSvc.consultaUvr());
	    if (guardar.getTotalAvaluo() != null && guardar.getValorUvrDia() != null)
		guardar.setAvaluoUvr(guardar.getTotalAvaluo().divide(guardar.getValorUvrDia(), 4, RoundingMode.HALF_DOWN));
	    List<LiquidacionAvaluo> liquidacionesCopia = null;
	    if (original.getLiquidacionAvaluos() != null && !original.getLiquidacionAvaluos().isEmpty()) {
		liquidacionesCopia = new ArrayList<>();
		for (LiquidacionAvaluo liquidacionOriginal : original.getLiquidacionAvaluos()) {
		    LiquidacionAvaluo copiaLiquidacion = liquidacionOriginal.clone();
		    copiaLiquidacion.setIdAvaluo(copia.getIdAvaluo());
		    copiaLiquidacion.setIdLiqavaluo(null);
		    copiaLiquidacion.setAvaluo(copia);
		    liquidacionesCopia.add(copiaLiquidacion);
		}
	    }
	    diligenciamientoSvc.guardar(liquidacionesCopia, guardar, usuario);
	} catch (NegocioException e) {
	    throw e;
	} catch (Exception e) {
	    throw mgrExc.lanzarExcepcion(42, TipoErrorNegocio.ERROR, e.getMessage(), null);
	}
    }

    private void copiarSeccionObservaciones(Avaluo original, Avaluo copia, UsuarioDto usuario) throws NegocioException {
	try {
	    Observaciones guardar = original.getObservacion() != null ? original.getObservacion().clone() : null;
	    if (guardar == null)
		return;
	    guardar.setIdAvaluo(copia.getIdAvaluo());
	    guardar.setIdObservacion(null);
	    guardar.setAvaluo(copia);
	    guardar.setArchivo(original.getObservacion().getArchivo() != null ? original.getObservacion().getArchivo().clonar() : null);
	    guardar.setIdArchivo(null);
	    if (guardar.getArchivo() != null)
		guardar.getArchivo().setModificado(false);
	    diligenciamientoSvc.guardar(guardar, usuario);
	} catch (NegocioException e) {
	    throw e;
	} catch (Exception e) {
	    throw mgrExc.lanzarExcepcion(43, TipoErrorNegocio.ERROR, e.getMessage(), null);
	}
    }

	private void prepararAnexosGuardarMultiple(List<AnexoFotografico> anexosGuardar,
			List<ListaAnexosPdf> anexosPdfGuardar, List<ListaAnexosPdf> anexosOriginales, Avaluo original,
			Avaluo replica, UsuarioDto usuario, List<ArchivosTmp> archivosTmp, AnexoFotografico guardar, Archivo archivoOriginal, byte[] contenido) throws Exception {
		if(original.isProvisional()) {
			return;
		}
		if (original.getCodigoProcedencia().equals(Procedencia.CARGUE_MASIVO.getValor())) {
			preparaAnexoCargueMasivo(replica,archivosTmp,anexosGuardar,anexosPdfGuardar,anexosOriginales);
		} else {
			Avaluo avaluoReplica = null;
			int numIntentos = 0;
			while(numIntentos < 2) {
				avaluoReplica = avaluoSvc.consultarAvaluo(replica.getIdAvaluo());
				if(avaluoReplica != null)
					break;
				numIntentos++;
			}
			if(avaluoReplica == null) {
				throw mgrExc.lanzarExcepcion(1, TipoErrorNegocio.ERROR,"ERROR["+replica.getIdAvaluo()+"]",null);
			}
			
			guardar.setIdAvaluo(avaluoReplica.getIdAvaluo());
			guardar.setAvaluo(avaluoReplica);
			guardar.setIdAnexoFotografico(null);
			guardar.setIdImgFachada(null);
			guardar.setIdDocAnexos(null);
			guardar.setArchivoFoto(null);
			guardar.setArchivoPdf(null);
		
			if (original.getAnexosFotograficos().getIdDocAnexos().compareTo(original.getAnexosFotograficos().getIdImgFachada()) != 0) {
				guardar.setIdDocAnexos(original.getAnexosFotograficos().getIdImgFachada());
				guardar.setIdImgFachada(original.getAnexosFotograficos().getIdImgFachada());
			}
			preparaAnexoMultiple(guardar, avaluoReplica, anexosOriginales, anexosGuardar,anexosPdfGuardar, original, archivoOriginal, contenido);
		}
	}
	
	private void preparaAnexoCargueMasivo(Avaluo replica, List<ArchivosTmp> archivosTmp, List<AnexoFotografico> anexosGuardar,List<ListaAnexosPdf> anexosPdfGuardar, List<ListaAnexosPdf> anexosOriginales) {
		if (archivosTmp == null) {
			return;
		}
		ArchivosTmp archivoTmp = archivosTmp.get(0);
		AnexoFotografico guardarCargue = new AnexoFotografico();

		guardarCargue.setIdAvaluo(replica.getIdAvaluo());
		guardarCargue.setAvaluo(replica);
		guardarCargue.setIdAnexoFotografico(null);

		guardarCargue.setIdImgFachada(null);
		guardarCargue.setIdDocAnexos(null);

		guardarCargue.setArchivoFoto(null);
		guardarCargue.setArchivoPdf(null);
		anexosGuardar.add(guardarCargue);

		if (anexosOriginales.isEmpty()) {
			ListaAnexosPdf copiaAnexoPdf = new ListaAnexosPdf();
			copiaAnexoPdf.setIdListaAnexosPdf(null);
			copiaAnexoPdf.setIdAvaluo(replica.getIdAvaluo());
			copiaAnexoPdf.setAvaluo(replica);
			copiaAnexoPdf.setArchivo(new Archivo());
			copiaAnexoPdf.setAnexo(archivoTmp.getContenido());
			copiaAnexoPdf.setPortada(true);
			copiaAnexoPdf.setConsecutivoAnexo(1l);
			copiaAnexoPdf.getArchivo().setNombreArchivo(archivoTmp.getNombreArchivo());
			copiaAnexoPdf.getArchivo().setTamanioArchivo(archivoTmp.getTamanioArchivo().longValue());
			copiaAnexoPdf.setModificado(true);
			anexosPdfGuardar.add(copiaAnexoPdf);
		}//termina copia de cargue masivo
	}
	
	private void preparaAnexoMultiple(AnexoFotografico guardar, Avaluo replica, List<ListaAnexosPdf> anexosOriginales, List<AnexoFotografico> anexosGuardar,List<ListaAnexosPdf> anexosPdfGuardar, Avaluo original, Archivo archivoOriginal, byte[] contenido) throws CloneNotSupportedException, NegocioException, IOException {
		AnexoFotografico fachada = new AnexoFotografico();
		//Agrega la fachada al listado de anexos
		if (anexosOriginales.isEmpty()) {
			ListaAnexosPdf copiaAnexoAntiguo = new ListaAnexosPdf();
			copiaAnexoAntiguo.setIdListaAnexosPdf(null);
			copiaAnexoAntiguo.setIdAvaluo(replica.getIdAvaluo());
			copiaAnexoAntiguo.setAvaluo(replica);
			copiaAnexoAntiguo.setArchivo(archivoOriginal.clonar());
			copiaAnexoAntiguo.setAnexo(contenido);
			copiaAnexoAntiguo.setIdArchivo(null);
			copiaAnexoAntiguo.setPortada(true);
			copiaAnexoAntiguo.setConsecutivoAnexo(1l);
			copiaAnexoAntiguo.setModificado(true);
			anexosPdfGuardar.add(copiaAnexoAntiguo);//adiciona el anexo creado al listado de anexos
			guardar.setFachada(copiaAnexoAntiguo);//agrega el archivo al anexo fotografico (fachada)
		}else {
			for (ListaAnexosPdf anexoOriginal : anexosOriginales) {
				ListaAnexosPdf copiaAnexo = anexoOriginal.clone();
				if(copiaAnexo.getArchivo().getContenidoArchivo() != null) {
//					contenido = archivoSvc.obtenerDocumento(copiaAnexo.getArchivo().getIdDocumento());
					contenido = copiaAnexo.getArchivo().getContenidoArchivo();
				}
//				else {
//					contenido = archivoSvc.obtenerDocumento(copiaAnexo.getArchivo().getIdDocumento());
//				}
				
				copiaAnexo.setIdAvaluo(replica.getIdAvaluo());
				copiaAnexo.setAvaluo(replica);
				copiaAnexo.setArchivo(copiaAnexo.getArchivo().clonar());
				copiaAnexo.setAnexo(contenido);
				copiaAnexo.setIdArchivo(null);
				copiaAnexo.setIdListaAnexosPdf(null);
				copiaAnexo.setModificado(true);
				if (original.getAnexosFotograficos().getIdImgFachada().compareTo(anexoOriginal.getIdArchivo()) == 0) {
					copiaAnexo.setPortada(true);
					guardar.setFachada(copiaAnexo);
				}
				anexosPdfGuardar.add(copiaAnexo);
			}
		}
		fachada.setAnexo(guardar.getAnexo());
		fachada.setArchivoFoto(guardar.getArchivoFoto());
		fachada.setArchivoPdf(guardar.getArchivoPdf());
		fachada.setAvaluo(guardar.getAvaluo());
		fachada.setFachada(guardar.getFachada());
		fachada.setFechaCreacion(guardar.getFechaCreacion());
		fachada.setFechaTransaccion(guardar.getFechaTransaccion());
		fachada.setIdAnexoFotografico(guardar.getIdAnexoFotografico());
		fachada.setIdAvaluo(guardar.getIdAvaluo());
		fachada.setIdDocAnexos(guardar.getIdDocAnexos());
		fachada.setIdImgFachada(guardar.getIdImgFachada());
		fachada.setUsuarioCreacion(guardar.getUsuarioCreacion());
		fachada.setUsuarioTransaccion(guardar.getUsuarioTransaccion());
		anexosGuardar.add(fachada);
	}

    private void copiaRegistroFotograficoMultiplesAvaluos(List<AnexoFotografico> registroFotografico, List<ListaAnexosPdf> fotografias, UsuarioDto usuario)
	    throws NegocioException {
	if (usuario == null || usuario.getUsuario() == null)
	    throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
	if (registroFotografico == null) {
	    return;
	}
	List<ListaAnexosPdf> listaFotos = new ArrayList<ListaAnexosPdf>();
	for(int inicio = 0; inicio < fotografias.size(); inicio += NUMERO_FOTOS) {
		int fin = Math.min(inicio + NUMERO_FOTOS, fotografias.size());
		archivoSvc.guardarFotografiaMultiples(fotografias.subList(inicio, fin), listaFotos , usuario, false);
	}
	for (AnexoFotografico anexo : registroFotografico) {
		if (anexo.getFachada() != null) {
			for(ListaAnexosPdf foto: listaFotos) {
				if(anexo.getIdAvaluo().equals(foto.getIdAvaluo()) && foto.getConsecutivoAnexo() == 1) {
					anexo.setIdImgFachada(foto.getIdArchivo());
					anexo.setIdDocAnexos(foto.getIdArchivo());    	
			    	anexo.setArchivoFoto(null);
			    	anexo.setArchivoPdf(null);
				}
			}
		}
	    anexo.setFechaTransaccion(new Date());
	    anexo.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
	    anexo.setFechaCreacion(new Date());
	    anexo.setUsuarioCreacion(usuario.getUsuario().getCodigo());
	    anexoFotogragicoDao.crear(anexo);
	}
    }

    @Override
    public NegocioAlertaException consultarAlertasCopia(Avaluo avaluoOriginal, List<Avaluo> copias) throws NegocioException {
	NegocioAlertaException ex = (NegocioAlertaException) mgrExc.lanzarExcepcion(71, TipoErrorNegocio.ALERTA);
	NegocioException heredado = diligenciamientoSvc.consultarAlertas(avaluoOriginal);
	if (heredado != null && heredado.getErrores() != null) {
	    heredado.removerError(Long.valueOf(UtilConstantes.ALT_MATRICULA_REPETIDA));
	    ex.getErrores().addAll(heredado.getErrores());
	}
	if (copias != null && !copias.isEmpty())
	    for (Avaluo copia : copias) {
		if (diligenciamientoSvc.verificarInmuebleRepetido(copia.obtenerInmuebleMatriculas())) {
		    if (copia.getFechaAvaluo() == null)
			copia.setFechaAvaluo(new Date());
		    copia.generarConsecutivoBanco();
		    ex.agregarError(mgrExc.lanzarExcepcion(UtilConstantes.ALT_MATRICULA_REPETIDA, TipoErrorNegocio.ALERTA, " Consecutivo: " + copia.getConsecutivoBanco(), null));
		}
	    }
	return ex.getErrores().isEmpty() ? null : ex;
    }

}
