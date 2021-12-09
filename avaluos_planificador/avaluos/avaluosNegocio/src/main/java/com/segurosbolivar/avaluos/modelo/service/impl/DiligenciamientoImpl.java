package com.segurosbolivar.avaluos.modelo.service.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.axis.utils.ByteArray;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.asesoftware.util.archivo.UtilArchivos;
import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioAlertaException;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilFecha;
import com.asesoftware.util.lang.UtilFolder;
import com.asesoftware.util.lang.UtilNumero;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.cons.Procedencia;
import com.segurosbolivar.avaluos.modelo.cons.SeccionConstruccion;
import com.segurosbolivar.avaluos.modelo.cons.SiNo;
import com.segurosbolivar.avaluos.modelo.cons.TipoInforme;
import com.segurosbolivar.avaluos.modelo.data.AlertaDao;
import com.segurosbolivar.avaluos.modelo.data.AnexoFotograficoDao;
import com.segurosbolivar.avaluos.modelo.data.ArchivoDao;
import com.segurosbolivar.avaluos.modelo.data.ArchivoTmpDao;
import com.segurosbolivar.avaluos.modelo.data.AvaluoDao;
import com.segurosbolivar.avaluos.modelo.data.ComportamientoOfertaDemandaDao;
import com.segurosbolivar.avaluos.modelo.data.CondicionesSalubridadDao;
import com.segurosbolivar.avaluos.modelo.data.InformacionBarrioDao;
import com.segurosbolivar.avaluos.modelo.data.InformacionConstruccionDao;
import com.segurosbolivar.avaluos.modelo.data.InformacionInmuebleDao;
import com.segurosbolivar.avaluos.modelo.data.LiquidacionAvaluoDao;
import com.segurosbolivar.avaluos.modelo.data.LiquidacionAvaluoTotalDao;
import com.segurosbolivar.avaluos.modelo.data.ListaAnexosPdfDao;
import com.segurosbolivar.avaluos.modelo.data.ObservacionesDao;
import com.segurosbolivar.avaluos.modelo.data.ParametrizacionDao;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.AlertaAvaluos;
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
import com.segurosbolivar.avaluos.modelo.entity.Parametrizacion;
import com.segurosbolivar.avaluos.modelo.entity.PeritosEmpresa;
import com.segurosbolivar.avaluos.modelo.service.IArchivo;
import com.segurosbolivar.avaluos.modelo.service.IDiligenciamiento;
import com.segurosbolivar.avaluos.modelo.service.IParametrizacion;
import com.segurosbolivar.avaluos.modelo.service.IPerito;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;
import com.segurosbolivar.avaluos.modelo.ws.service.correo.ISolicitudesCliente;
import com.segurosbolivar.avaluos.modelo.ws.service.correo.SolicitudesClienteThread;

/**
 * Implementaci�n del servicio de diligenciamiento
 *
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:41 a.m.
 */
@Stateless
public class DiligenciamientoImpl implements IDiligenciamiento {

	private static final long serialVersionUID = 7150528447145538068L;

	private static final Logger log = Logger.getLogger(DiligenciamientoImpl.class);

	/**
	 * Permite realizar el manejo de errores y la recuperacion de los mensajes
	 * respectivos segun la excepcion lanzada.
	 */
	/**
	 * Permite realizar el manejo de errores y la recuperacion de los mensajes
	 * respectivos segun la excepcion lanzada.
	 */
	@EJB
	private ManejadorErroresNegocio mgrExc;

	/**
	 * Dao que permite realizar las operaciones en base de datos para el
	 * comportamiento de oferta y demanda.
	 */
	@EJB
	private ComportamientoOfertaDemandaDao ofertaDao;
	@EJB
	private InformacionBarrioDao barrioDao;
	@EJB
	private IPerito peritoSvc;
	@EJB
	private ObservacionesDao observacionDao;
	@EJB
	private InformacionInmuebleDao inmuebleDao;
	@EJB
	private IParametrizacion parametrizacionSvc;
	@EJB
	private InformacionConstruccionDao construccionDao;
	@EJB
	private CondicionesSalubridadDao salubridadDao;
	@EJB
	private LiquidacionAvaluoDao liquidacionDao;
	@EJB
	private LiquidacionAvaluoTotalDao totalDao;

	@EJB
	private IArchivo svcArchivo;

	@EJB
	private AlertaDao alertaDao;
	@EJB
	private AvaluoDao avaluoDao;
	@EJB
	private ArchivoDao archivoDao;
	@EJB
	private ArchivoTmpDao archivoTmpDao;
	@EJB
	private ListaAnexosPdfDao anexosDao;
	@EJB
	private AnexoFotograficoDao anexoDao;

	@EJB
	private ISolicitudesCliente solicitudesClienteSvc;

	@EJB
	private ParametrizacionDao ParametrizacionDao;

	private byte[] obtenerImagen(String id) throws NegocioException {
		byte[] res = null;
		try {
			res = svcArchivo.obtenerDocumentoCompreso(id);
		} catch (Exception e) {
			System.out.println("Error buscando archivo: " + id);
			return null;
		}
		return res;
	}

	@Override
	public List<ListaAnexosPdf> consultarRegistroFotografico(Avaluo avaluo, boolean obtenerContenido) throws NegocioException {
		log.info("============Inicio metodo consultarRegistroFotografico=============");
		
		if(avaluo.getIndMotor() != null && avaluo.getIndMotor().equals("S") && obtenerContenido) {
			return consultarRegistroFotograficoS3(avaluo);
		}else {
			long tiempoInicial = System.currentTimeMillis();
			List<ListaAnexosPdf> anexosPdf = anexosDao.consultaAnexosPorAvaluo(avaluo);
			AnexoFotografico anxFotografico = anexoDao.consultarLista(avaluo.getIdAvaluo());
	
			if (avaluo.getCodigoProcedencia().equals(Procedencia.CARGUE_MASIVO.getValor())) {
				List<ListaAnexosPdf> anexosFachada = new ArrayList<>();
				ListaAnexosPdf anexoUnico = new ListaAnexosPdf();
				try {
					List<ArchivosTmp> archivosTmp = archivoTmpDao
							.buscarArchivosPorNombre(avaluo.getConsecutivoBanco().toString() + "_fachada.jpg");
					if (archivosTmp == null || archivosTmp.isEmpty()) {
						return Collections.emptyList();
					}
	
					ArchivosTmp archivoTmp = archivosTmp.get(0);
					Archivo archivoFotografico = new Archivo();
	
					byte[] contenidoByte = new byte[] {};			
	//				contenidoByte = svcArchivo.obtenerDocumentoMin(archivoTmp.getIdDocumento());
	//				if (contenidoByte == null) {
					 if(obtenerContenido) {
						 contenidoByte = svcArchivo.obtenerDocumento(archivoTmp.getIdDocumento());
					 }
	//				}				
					archivoFotografico.setContenidoArchivo(contenidoByte);
					archivoFotografico.setNombreArchivo(archivoTmp.getNombreArchivo());
	
					anexoUnico.setAvaluo(avaluo);
					anexoUnico.setArchivo(archivoFotografico);
					anexoUnico.setConsecutivoAnexo(1L);
					anexoUnico.setFechaCreacion(archivoTmp.getFechaCreacion());
					anexoUnico.setFechaTransaccion(archivoTmp.getFechaTransaccion());
					anexoUnico.setUsuarioCreacion(archivoTmp.getUsuarioCreacion());
					anexoUnico.setUsuarioTransaccion(archivoTmp.getUsuarioTransaccion());
					anexoUnico.setPortada(true);
					anexoUnico.setAnexo(contenidoByte);
					anexoUnico.setModificado(true);
	
				} catch (Exception e) {
					throw mgrExc.lanzarExcepcion(32, TipoErrorNegocio.ERROR, e.getMessage(), null);
				}
				anexosFachada.add(anexoUnico);
				return anexosFachada;
			}
	
			if (anexosPdf.isEmpty() && anxFotografico == null) {
				return Collections.emptyList();
			} else if (anxFotografico != null && anexosPdf.isEmpty()) {
				List<ListaAnexosPdf> anexosFachada = new ArrayList<>();
				ListaAnexosPdf anexoUnico = new ListaAnexosPdf();
				Archivo archivoFotografico = null;
				try {
					if (anxFotografico.getIdImgFachada() != null)
						archivoFotografico = archivoDao.buscar(anxFotografico.getIdImgFachada());
					if (archivoFotografico == null)
						return Collections.emptyList();
					byte[] contenidoByte = new byte[] {};	
					
					if(obtenerContenido) {
						 contenidoByte = archivoFotografico.getContenidoArchivo() != null
								? archivoFotografico.getContenidoArchivo()
								: svcArchivo.obtenerDocumento(archivoFotografico.getIdDocumento());
	//							: svcArchivo.obtenerDocumentoMin(archivoFotografico.getIdDocumento());
	//					if(contenidoByte == null) {
	//						contenidoByte = svcArchivo.obtenerDocumento(archivoFotografico.getIdDocumento());
	//					}
					}
					if (contenidoByte == null) {
						return Collections.emptyList();
					}
					anexoUnico.setAvaluo(avaluo);
					anexoUnico.setArchivo(archivoFotografico);
					anexoUnico.setConsecutivoAnexo(1L);
					anexoUnico.setFechaCreacion(anxFotografico.getFechaCreacion());
					anexoUnico.setFechaTransaccion(anxFotografico.getFechaTransaccion());
					anexoUnico.setUsuarioCreacion(anxFotografico.getUsuarioCreacion());
					anexoUnico.setUsuarioTransaccion(anxFotografico.getUsuarioTransaccion());
					if (anxFotografico != null && anxFotografico.getIdImgFachada() != null
							&& (anxFotografico.getIdImgFachada().equals(archivoFotografico.getIdArchivo()))) {
						anexoUnico.setPortada(true);
					}
					anexoUnico.setAnexo(contenidoByte);
					anexoUnico.setModificado(archivoFotografico.getContenidoArchivo() != null);
				} catch (Exception e) {
					throw mgrExc.lanzarExcepcion(32, TipoErrorNegocio.ERROR, e.getMessage(), null);
				}
				anexosFachada.add(anexoUnico);
				return anexosFachada;
			} else {
				try {
					for (ListaAnexosPdf foto : anexosPdf) {//por aqui entra para la consulta
						log.info("identificador del archivo fotografico " + foto.getIdArchivo());
						Archivo archivoFotografico = null;
						if (foto.getIdArchivo() != null)
							archivoFotografico = archivoDao.buscar(foto.getIdArchivo());
						if (archivoFotografico == null)
							continue;
						log.info("Identificador del documento: " + archivoFotografico.getIdDocumento());
						
						byte[] contenidoByte = new byte[] {};				
						if(obtenerContenido) {
							contenidoByte = archivoFotografico.getContenidoArchivo() != null
								? archivoFotografico.getContenidoArchivo()
								: svcArchivo.obtenerDocumento(archivoFotografico.getIdDocumento());
						}
						if (contenidoByte == null) {
							return Collections.emptyList();
						}
						if (anxFotografico != null && anxFotografico.getIdImgFachada() != null
								&& (anxFotografico.getIdImgFachada().equals(archivoFotografico.getIdArchivo()))) {
							foto.setPortada(true);
						}
						foto.setAnexo(contenidoByte);
						foto.setModificado(archivoFotografico.getContenidoArchivo() != null);
					}
				} catch (Exception e) {
					throw mgrExc.lanzarExcepcion(32, TipoErrorNegocio.ERROR, e.getMessage(), null);
				}
				long tiempoFinal = System.currentTimeMillis();
				log.info("============Finaliza metodo consultarRegistroFotografico===========");
				long tiempoTotal = tiempoFinal - tiempoInicial;
		    	log.info("============Tiempo total consultarRegistroFotografico : "+ tiempoTotal+"ms");
	
				return anexosPdf;
			}
		}
	}

	@Override
	public void guardar(InformacionBarrio barrio, UsuarioDto usuario) throws NegocioException {
		if (usuario == null || usuario.getUsuario() == null)
			throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
		if (barrio == null) {
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		}
		barrio.setFechaTransaccion(new Date());
		barrio.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
		// si el registro no existe lo crearemos. de lo contrario solo se
		// actualizaran sus valores.
		if (barrio.getIdInformacionBarrio() == null || barrioDao.buscar(barrio.getIdInformacionBarrio()) == null) {
			barrio.setFechaCreacion(new Date());
			barrio.setUsuarioCreacion(usuario.getUsuario().getCodigo());
			barrioDao.crear(barrio);
		} else
			barrioDao.actualizar(barrio);
	}

	@Override
	public void guardar(InformacionConstruccion construccion, SeccionConstruccion seccion, Avaluo avaluo,
			UsuarioDto usuario) throws NegocioException {
		if (usuario == null || usuario.getUsuario() == null)
			throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
		if (construccion == null)
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		if (avaluo == null)
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		log.info("--se  inicia guardar informacion construccion avaluo::" + avaluo.getConsecutivoBanco());
		verificarPropiedadHorizontal(seccion, construccion);
		construccion.setFechaTransaccion(new Date());
		construccion.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
		// si el registro no existe lo crearemos. de lo contrario solo se
		// actualizaran sus valores.
		if (construccion.getVetustez() != null)
			construccion.setAnoConstruccionBUA(
					UtilFecha.obtenerAnio(avaluo.getFechaAvaluo()) - construccion.getVetustez().longValueExact());
		else {
			construccion.setAnoConstruccionBUA(null);
		}
		if (construccion.getIdInformacionConstruccion() == null
				|| construccionDao.buscar(construccion.getIdInformacionConstruccion()) == null) {
			construccion.setFechaCreacion(new Date());
			construccion.setUsuarioCreacion(usuario.getUsuario().getCodigo());
			construccionDao.crear(construccion);
		} else
			construccionDao.actualizar(construccion);
		log.info("--finaliza  guardar informacion construccion avaluo::" + avaluo.getConsecutivoBanco());
		// buscamos el avaluo para actualizar el tipo de informe a proyecto.en
		// caso de que la vivienda sea nueva y se encuentre terminada.
		Avaluo avaluoCambioTipoInforme = avaluoDao.buscar(avaluo.getIdAvaluo());
		if (avaluoCambioTipoInforme == null)
			return;
		if (construccion.getEstadoConstruccion() == null)
			return;
		if (construccion.getEstadoConstruccion().compareTo(1L) != 0)
			avaluoCambioTipoInforme.setTipoInforme(TipoInforme.CREDITO.getValor());
		else
			avaluoCambioTipoInforme.setTipoInforme(TipoInforme.CREDITO.getValor());
		avaluoDao.actualizar(avaluoCambioTipoInforme);
		log.info("--finaliza  guardar marca constructor avaluo::" + avaluo.getConsecutivoBanco());
	}

	private void verificarPropiedadHorizontal(SeccionConstruccion seccion, InformacionConstruccion construccion)
			throws NegocioException {
		if (!SeccionConstruccion.PROPIEDAD_HORIZONTAL.equals(seccion))
			return;
		Long totalComparar = construccion.getTotalUnidades() == null ? 1 : construccion.getTotalUnidades();
		if (construccion.getPropiedadHorizontal() != null && construccion.getPropiedadHorizontal().compareTo(2L) == 0) {
			if (totalComparar.compareTo(1L) != 0)
				throw mgrExc.lanzarExcepcion(60, TipoErrorNegocio.ERROR);
			return;
		}
	}

	@Override
	public String consultarDestinatariosNotificacion(String tipoDestinatario) throws NegocioException {
//	Parametrizacion valor = ParametrizacionDao.getParametro(UtilConstantes.PARAMETRIZACION_TIPO_NOTIFICACION, UtilConstantes.PARAMETRIZACION_NOTIFICACION_DESTINATARIO);
		Parametrizacion valor = ParametrizacionDao.getParametro(UtilConstantes.PARAMETRIZACION_TIPO_NOTIFICACION,
				tipoDestinatario);
		if (valor == null)
			return null;
		return valor.getValorparametro();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void enviarCorreos(Avaluo avaluo, UsuarioDto usuario) throws NegocioException {
		enviarCorreosAvaluo(avaluo, usuario);
	}

	@Override
	@Asynchronous
	public void enviarCorreosAvaluo(Avaluo avaluo, UsuarioDto usuario) {
		try {
			log.info("--inicia envio correos alertas avaluo:" + avaluo.getConsecutivoBanco());
			AlertaAvaluos consultarAlerta = new AlertaAvaluos();
			consultarAlerta.setIdAvaluo(avaluo.getIdAvaluo());
			consultarAlerta.setAvaluo(avaluo);
			List<AlertaAvaluos> alertas = alertaDao.consultar(consultarAlerta);
			if (alertas == null || alertas.isEmpty())
				return;
//	    String destinatario = consultarDestinatariosNotificacion();
			String destinatario;
			InformacionInmueble inmueble = consultarInmueble(avaluo.getIdAvaluo());
			PeritosEmpresa perito = peritoSvc.consultarPeritoDocumento(Long.valueOf(usuario.getUsuario().getCodigo()));
			log.info("--se encontraron  alertas para el avaluo:" + avaluo.getConsecutivoBanco());
			for (AlertaAvaluos alerta : alertas) {
				switch (alerta.getCodTipoAlerta().intValue()) {
				case UtilConstantes.ALT_NUM_PISO_MAMPO:
					destinatario = consultarDestinatariosNotificacion(
							UtilConstantes.PARAMETRIZACION_NOTIFICACION_DESTINATARIO_MAMPOSTERIASUPERIOR);
					new SolicitudesClienteThread(solicitudesClienteSvc.aprobadoMamposteria8Niveles(destinatario,
							UtilNumero.pasarTexto(avaluo.getNumeroIdentificacion()),
							parametrizacionSvc.consultarDescripcionDominio("TIPOIDENTIFICACION",
									UtilNumero.pasarTexto(avaluo.getIdTipoIdentificacion())),
							new String[] { UtilConstantes.ALT_NUM_PISO_MAMPO_ASUNTO,
									UtilNumero.pasarTexto(avaluo.getConsecutivoBanco().longValue()),
									UtilNumero.pasarTexto(perito.getNumeroDocumento()), perito.getNombrePerito() }))
											.EnviarCorreo();
					break;
				case UtilConstantes.ALT_NUM_PISO_ADOBE:
					destinatario = consultarDestinatariosNotificacion(
							UtilConstantes.PARAMETRIZACION_NOTIFICACION_DESTINATARIO_ADOBESUPERIOR);
					new SolicitudesClienteThread(solicitudesClienteSvc.adobeSuperior3Niveles(destinatario,
							UtilNumero.pasarTexto(avaluo.getNumeroIdentificacion()),
							parametrizacionSvc.consultarDescripcionDominio("TIPOIDENTIFICACION",
									UtilNumero.pasarTexto(avaluo.getIdTipoIdentificacion())),
							new String[] { UtilConstantes.ALT_NUM_PISO_ADOBE_ASUNTO,
									UtilNumero.pasarTexto(avaluo.getConsecutivoBanco().longValue()),
									UtilNumero.pasarTexto(perito.getNumeroDocumento()), perito.getNombrePerito() }))
											.EnviarCorreo();
					break;
				case UtilConstantes.ALT_VETUSTEZ_ADOBE:
					destinatario = consultarDestinatariosNotificacion(
							UtilConstantes.PARAMETRIZACION_NOTIFICACION_DESTINATARIO_VETUSTEZMENOR50);
					new SolicitudesClienteThread(solicitudesClienteSvc.vetustezMenor50Anios(destinatario,
							UtilNumero.pasarTexto(avaluo.getNumeroIdentificacion()),
							parametrizacionSvc.consultarDescripcionDominio("TIPOIDENTIFICACION",
									UtilNumero.pasarTexto(avaluo.getIdTipoIdentificacion())),
							new String[] { UtilConstantes.ALT_VETUSTEZ_ADOBE_ASUNTO,
									UtilNumero.pasarTexto(avaluo.getConsecutivoBanco().longValue()),
									UtilNumero.pasarTexto(perito.getNumeroDocumento()), perito.getNombrePerito() }))
											.EnviarCorreo();
					break;
				case UtilConstantes.ALT_NUM_PISO_ELEV:
					destinatario = consultarDestinatariosNotificacion(
							UtilConstantes.PARAMETRIZACION_NOTIFICACION_DESTINATARIO_PISOSSUPERIOR60);
					new SolicitudesClienteThread(solicitudesClienteSvc.aprobadoPisosSuperior60Niveles(destinatario,
							UtilNumero.pasarTexto(avaluo.getNumeroIdentificacion()),
							parametrizacionSvc.consultarDescripcionDominio("TIPOIDENTIFICACION",
									UtilNumero.pasarTexto(avaluo.getIdTipoIdentificacion())),
							new String[] { UtilConstantes.ALT_NUM_PISO_ELEV_ASUNTO,
									UtilNumero.pasarTexto(avaluo.getConsecutivoBanco().longValue()),
									UtilNumero.pasarTexto(perito.getNumeroDocumento()), perito.getNombrePerito() }))
											.EnviarCorreo();
					break;
				case UtilConstantes.ALT_NUM_PISO_CONCRE:
					destinatario = consultarDestinatariosNotificacion(
							UtilConstantes.PARAMETRIZACION_NOTIFICACION_DESTINATARIO_MENOR8PISOS);
					new SolicitudesClienteThread(solicitudesClienteSvc.dualMenor8Pisos(destinatario,
							UtilNumero.pasarTexto(avaluo.getNumeroIdentificacion()),
							parametrizacionSvc.consultarDescripcionDominio("TIPOIDENTIFICACION",
									UtilNumero.pasarTexto(avaluo.getIdTipoIdentificacion())),
							new String[] { UtilConstantes.ALT_NUM_PISO_ELEV_ASUNTO,
									UtilNumero.pasarTexto(avaluo.getConsecutivoBanco().longValue()),
									UtilNumero.pasarTexto(perito.getNumeroDocumento()), perito.getNombrePerito() }))
											.EnviarCorreo();
					break;
				case UtilConstantes.ALT_ZONA_RIESGO_ALTO:
					destinatario = consultarDestinatariosNotificacion(
							UtilConstantes.PARAMETRIZACION_NOTIFICACION_DESTINATARIO_ZONANOASEGURABLE);
					new SolicitudesClienteThread(solicitudesClienteSvc.aprobadoZonaNoAsegurable(destinatario,
							UtilNumero.pasarTexto(avaluo.getNumeroIdentificacion()),
							parametrizacionSvc.consultarDescripcionDominio("TIPOIDENTIFICACION",
									UtilNumero.pasarTexto(avaluo.getIdTipoIdentificacion())),
							new String[] { UtilConstantes.ALT_ZONA_RIESGO_ALTO_ASUNTO,
									UtilNumero.pasarTexto(avaluo.getConsecutivoBanco().longValue()),
									UtilNumero.pasarTexto(perito.getNumeroDocumento()), perito.getNombrePerito() }))
											.EnviarCorreo();
					break;
				case UtilConstantes.ALT_MATRICULA_REPETIDA:
					inmueble.setNumeroIdentificacion(avaluo.getNumeroIdentificacion());
					List<InformacionInmueble> matriculasRepetidas = inmuebleDao.consultarMatriculas(inmueble);
					if (matriculasRepetidas == null || matriculasRepetidas.isEmpty())
						break;
					log.info("--generacion alerta matricula repetida para avaluo:" + avaluo.getConsecutivoBanco());
					StringBuilder matriculas = new StringBuilder();
					InformacionInmueble primerRepitente = null;
					for (InformacionInmueble repetido : matriculasRepetidas) {
						if (repetido.getIdInfinmueble().compareTo(inmueble.getIdInfinmueble()) == 0)
							continue;
						primerRepitente = repetido;
						verificarMatriculaRepetida(matriculas, repetido.getMatriculaInmobiliariaDeposito1(),
								inmueble.getMatriculaInmobiliariaDeposito1());
						verificarMatriculaRepetida(matriculas, repetido.getMatriculaInmobiliariaDeposito2(),
								inmueble.getMatriculaInmobiliariaDeposito2());
						verificarMatriculaRepetida(matriculas, repetido.getMatriculaInmobiliariaGaraje1(),
								inmueble.getMatriculaInmobiliariaGaraje1());
						verificarMatriculaRepetida(matriculas, repetido.getMatriculaInmobiliariaGaraje2(),
								inmueble.getMatriculaInmobiliariaGaraje2());
						verificarMatriculaRepetida(matriculas, repetido.getMatriculaInmobiliariaGaraje3(),
								inmueble.getMatriculaInmobiliariaGaraje3());
						verificarMatriculaRepetida(matriculas, repetido.getMatriculaInmobiliariaGaraje4(),
								inmueble.getMatriculaInmobiliariaGaraje4());
						verificarMatriculaRepetida(matriculas, repetido.getMatriculaInmobiliariaGaraje5(),
								inmueble.getMatriculaInmobiliariaGaraje5());
						verificarMatriculaRepetida(matriculas, repetido.getMatriculaInmobiliariaPpal1(),
								inmueble.getMatriculaInmobiliariaPpal1());
						verificarMatriculaRepetida(matriculas, repetido.getMatriculaInmobiliariaPpal1(),
								inmueble.getMatriculaInmobiliariaPpal1());
						break;
					}
					if (primerRepitente == null || primerRepitente.getAvaluo() == null)
						break;
					destinatario = consultarDestinatariosNotificacion(
							UtilConstantes.PARAMETRIZACION_NOTIFICACION_DESTINATARIO_MATRICULAREPETIDA);
					new SolicitudesClienteThread(solicitudesClienteSvc.aprobadoMatriculaRepetida(destinatario,
							UtilNumero.pasarTexto(avaluo.getNumeroIdentificacion()),
							parametrizacionSvc.consultarDescripcionDominio("TIPOIDENTIFICACION",
									UtilNumero.pasarTexto(avaluo.getIdTipoIdentificacion())),
							new String[] { UtilConstantes.ALT_MATRICULA_REPETIDA_ASUNTO,
									UtilNumero.pasarTexto(
											UtilFecha.obtenerAnio(primerRepitente.getAvaluo().getFechaAvaluo())),
									UtilNumero.pasarTexto(avaluo.getConsecutivoBanco().longValue()),
									UtilNumero
											.pasarTexto(primerRepitente.getAvaluo().getConsecutivoBanco().longValue()),
									UtilNumero.pasarTexto(primerRepitente.getAvaluo().getNumeroIdentificacion()),
									UtilNumero.pasarTexto(perito.getNumeroDocumento()),
									primerRepitente.getAvaluo().getNombreSolicitante(), perito.getNombrePerito(),
									UtilTexto.removerSufijo(matriculas.toString(), ",") })).EnviarCorreo();
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
	}

	private void verificarMatriculaRepetida(StringBuilder matriculas, String matriculaVerificar,
			String matriculaOriginal) {
		if (UtilTexto.estaVacio(matriculaOriginal) || UtilTexto.estaVacio(matriculaVerificar)
				|| matriculas.indexOf(matriculaOriginal) >= 0)
			return;
		if (matriculaVerificar.equals(matriculaOriginal))
			matriculas.append(matriculaOriginal + ",");
	}

	@Override
	public NegocioAlertaException generarAlertasAvaluo(Avaluo avaluo, UsuarioDto usuario) throws NegocioException {
		NegocioAlertaException agruparAlertas = (NegocioAlertaException) mgrExc.lanzarExcepcion(107L,
				TipoErrorNegocio.ALERTA, null, new String[] { avaluo.getConsecutivoBanco() + "" });
		log.info("inicia verificacion alerta avaluo:" + avaluo.getConsecutivoBanco());
		NegocioAlertaException alertaConstruccion = generarAlertasInformacionConstruccion(avaluo,
				consultarConstruccion(avaluo.getIdAvaluo()), usuario);
		if (alertaConstruccion != null)
			agruparAlertas.getErrores().addAll(alertaConstruccion.getErrores());
		NegocioAlertaException alertaInmueble = generarAlertasInformacionInmueble(avaluo,
				consultarInmueble(avaluo.getIdAvaluo()), usuario);
		if (alertaInmueble != null)
			agruparAlertas.getErrores().addAll(alertaInmueble.getErrores());
		if (agruparAlertas.getErrores() != null && !agruparAlertas.getErrores().isEmpty())
			return agruparAlertas;
		return null;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public NegocioAlertaException generarAlertaPrevioAprobacion(Avaluo avaluo, UsuarioDto usuario)
			throws NegocioException {
		return generarAlertasAvaluo(avaluo, usuario);
	}

	@Override
	public NegocioAlertaException consultarAlertas(Avaluo preAprobar) throws NegocioException {
		AlertaAvaluos consultarAlerta = new AlertaAvaluos();
		consultarAlerta.setIdAvaluo(preAprobar.getIdAvaluo());
		consultarAlerta.setAvaluo(preAprobar);
		List<AlertaAvaluos> alertas = alertaDao.consultar(consultarAlerta);
		if (alertas == null)
			return null;
		NegocioAlertaException agruparAlertas = (NegocioAlertaException) mgrExc.lanzarExcepcion(107L,
				TipoErrorNegocio.ALERTA, null, new String[] { preAprobar.getConsecutivoBanco() + "" });
		for (AlertaAvaluos alertaAvaluos : alertas) {
			if (alertaAvaluos.getCodTipoAlerta().compareTo(new Long(UtilConstantes.ALT_CUMULO_CREDITO_CONSTRUCTOR)) == 0
					|| alertaAvaluos.getCodTipoAlerta().compareTo(new Long(UtilConstantes.ALT_COMPARATIVO_AVALUO)) == 0
					|| alertaAvaluos.getCodTipoAlerta().compareTo(new Long(UtilConstantes.ALT_ZONA_RIESGO_ALTO)) == 0)
				continue;
			agruparAlertas
					.agregarError(mgrExc.lanzarExcepcion(alertaAvaluos.getCodTipoAlerta(), TipoErrorNegocio.ALERTA));
		}
		if (agruparAlertas.getErrores() == null || agruparAlertas.getErrores().isEmpty())
			return null;
		return agruparAlertas;
	}

	@Override
	public NegocioAlertaException generarAlertasInformacionConstruccion(Avaluo avaluo,
			InformacionConstruccion construccion, UsuarioDto usuario) throws NegocioException {
		if (construccion == null)
			return null;
		// consultamos previamente las alertas del avaluo
		log.info("---generando alertas para la seccion de la construccion:" + avaluo.getConsecutivoBanco());
		AlertaAvaluos consultarAlerta = new AlertaAvaluos();
		consultarAlerta.setIdAvaluo(avaluo.getIdAvaluo());
		consultarAlerta.setAvaluo(avaluo);
		List<AlertaAvaluos> alertas = alertaDao.consultar(consultarAlerta);
		// creamos la excepcion que agrupara las alertas que se generen.
		NegocioAlertaException lanzar = (NegocioAlertaException) mgrExc.lanzarExcepcion(57, TipoErrorNegocio.ALERTA);
		// se confirma si el avlauo tiene cada una de las alertas asociadas.
		confirmarAlerta(lanzar, avaluo, UtilConstantes.ALT_NUM_PISO_MAMPO, usuario, alertas,
				construccion.getPisos() != null && construccion.getMaterialConstruccionBUA() != null
						&& construccion.getPisos().compareTo(8L) > 0
						&& construccion.getMaterialConstruccionBUA().compareTo(28L) == 0);
		confirmarAlerta(lanzar, avaluo, UtilConstantes.ALT_NUM_PISO_CONCRE, usuario, alertas,
				construccion.getPisos() != null && construccion.getDetalleMaterialSFBUA() != null
						&& construccion.getPisos().compareTo(8L) < 0
						&& construccion.getDetalleMaterialSFBUA().compareTo(3L) == 0);
		confirmarAlerta(lanzar, avaluo, UtilConstantes.ALT_NUM_PISO_ADOBE, usuario, alertas,
				construccion.getPisos() != null && construccion.getMaterialConstruccionBUA() != null
						&& construccion.getPisos().compareTo(3L) > 0
						&& construccion.getMaterialConstruccionBUA().compareTo(31L) == 0);
		confirmarAlerta(lanzar, avaluo, UtilConstantes.ALT_NUM_PISO_ELEV, usuario, alertas,
				construccion.getPisos() != null && construccion.getPisos().compareTo(60L) >= 0);
		confirmarAlerta(lanzar, avaluo, UtilConstantes.ALT_VETUSTEZ_ADOBE, usuario, alertas,
				construccion.getVetustez() != null && construccion.getMaterialConstruccionBUA() != null
						&& construccion.getVetustez().compareTo(new BigDecimal(50)) < 0
						&& construccion.getMaterialConstruccionBUA().compareTo(31L) == 0);
		// si se asociaron alertas se lanzara la excepcion que las agrupo.
		if (lanzar.getErrores() != null && !lanzar.getErrores().isEmpty())
			return lanzar;
		return null;
	}

	/**
	 * Permite defminir el proceso al confirmar si un avaluo aplica para una alerta.
	 * 
	 * @param asociarAlerta excepcion a la que se asociaran las alerta si estas
	 *                      existe y aun no se han notificado al usuario.
	 * @param avaluo        al que se asociaran las alertas si estas aun no se han
	 *                      creado.
	 * @param codigoAlerta  que se debe crear y verificar su existencia previa.
	 * @param usuario       que realiza la operacion.
	 * @param alertas       existentes asociadas al avaluo y con las cuales se
	 *                      verificar si ya existe la alerta previamente.
	 * @param estaEnAlerta  determina si el avaluo cumplio con la regla de la
	 *                      alerta.
	 * @throws NegocioException en caso de que se presente algun error.
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void confirmarAlerta(Avaluo avaluo, int codigoAlerta, UsuarioDto usuario, boolean estaEnAlerta)
			throws NegocioException {
		AlertaAvaluos consultarAlerta = new AlertaAvaluos();
		consultarAlerta.setIdAvaluo(avaluo.getIdAvaluo());
		consultarAlerta.setAvaluo(avaluo);
		List<AlertaAvaluos> alertas = alertaDao.consultar(consultarAlerta);
		confirmarAlerta(new NegocioAlertaException(""), avaluo, codigoAlerta, usuario, alertas, estaEnAlerta);
	}

	public void confirmarAlerta(NegocioAlertaException asociarAlerta, Avaluo avaluo, int codigoAlerta,
			UsuarioDto usuario, List<AlertaAvaluos> alertas, boolean estaEnAlerta) throws NegocioException {
		log.info("---verificando alerta :" + codigoAlerta);
		AlertaAvaluos alertaExistente = verificarAlertaExistente(alertas, codigoAlerta);
		if (alertaExistente != null || !estaEnAlerta) {
			if (alertaExistente != null && !estaEnAlerta)
				eliminarAlerta(alertaExistente);
			return;
		}
		crearAlerta(avaluo, codigoAlerta, usuario);
		asociarAlerta.agregarError(mgrExc.lanzarExcepcion(codigoAlerta, TipoErrorNegocio.ALERTA));
	}

	/**
	 * Determina si un avaluo ya posee un tipo de alerta en especifico. si no tiene
	 * alertas asociadas o el codigo especifico de alerta no existe devolvera falso.
	 * 
	 * @param alertas listado de las alertas del avaluo
	 * @param alerta  codigo del tipo de alerta del avaluo.
	 * @return verdadero en caso de que el codigo del tipo de alerta exista o este
	 *         vacio.
	 */
	private AlertaAvaluos verificarAlertaExistente(List<AlertaAvaluos> alertas, int alerta) {
		if (alertas == null || alertas.isEmpty())
			return null;
		for (AlertaAvaluos alertaAvaluos : alertas) {
			if (alertaAvaluos.getCodTipoAlerta().compareTo(new Long(alerta)) == 0)
				return alertaAvaluos;
		}
		return null;
	}

	@Override
	public void guardar(InformacionInmueble inmueble, Avaluo avaluo, UsuarioDto usuario) throws NegocioException {
		if (usuario == null || usuario.getUsuario() == null)
			throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
		if (inmueble == null)
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		log.info("--inicia guardar informacion del inmueble avaluo::" + avaluo.getConsecutivoBanco());
		inmueble.setFechaTransaccion(new Date());
		inmueble.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
		// si el registro no existe lo crearemos. de lo contrario solo se
		// actualizaran sus valores.
		if (inmueble.getIdInfinmueble() == null || inmuebleDao.buscar(inmueble.getIdInfinmueble()) == null) {
			inmueble.setFechaCreacion(new Date());
			inmueble.setUsuarioCreacion(usuario.getUsuario().getCodigo());
			inmuebleDao.crear(inmueble);
		} else
			inmuebleDao.actualizar(inmueble);
		log.info("--se  guardo informacion del inmueble avaluo::" + avaluo.getConsecutivoBanco());
		// En caso de que la categoria sea Lote se debe actualizar la
		// informacion del construccion del avaluo con el numero de pisos igual
		// a 0.
		if (inmueble.getIdCategoria() == null || inmueble.getIdCategoria().compareTo(12L) != 0) {
			return;
		}
		log.info("--se  inicia actualizacion numero de pisos lote avaluo::" + avaluo.getConsecutivoBanco());
		InformacionConstruccion construccion = construccionDao.consultar(avaluo.getIdAvaluo());
		if (construccion == null) {
			construccion = new InformacionConstruccion(avaluo.getIdAvaluo());
		}
		construccion.setPisos(0L);
		guardar(construccion, SeccionConstruccion.ADICIONAL, avaluo, usuario);
		log.info("--se  finaliza actualizacion numero de pisos lote avaluo::" + avaluo.getConsecutivoBanco());
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public NegocioAlertaException generarAlertasInformacionInmueble(Avaluo avaluo, InformacionInmueble inmueble,
			UsuarioDto usuario) throws NegocioException {
		if (inmueble == null)
			return null;
		// consultamos previamente las alertas del avaluo
		log.info("---generando alertas para la seccion del inmueble:" + avaluo.getConsecutivoBanco());
		AlertaAvaluos consultarAlerta = new AlertaAvaluos();
		consultarAlerta.setIdAvaluo(avaluo.getIdAvaluo());
		consultarAlerta.setAvaluo(avaluo);
		List<AlertaAvaluos> alertas = alertaDao.consultar(consultarAlerta);
		// creamos la excepcion que agrupara las alertas que se generen.
		NegocioAlertaException lanzar = (NegocioAlertaException) mgrExc.lanzarExcepcion(106L, TipoErrorNegocio.ALERTA);
		inmueble.setNumeroIdentificacion(avaluo.getNumeroIdentificacion());
		confirmarAlerta(lanzar, avaluo, UtilConstantes.ALT_MATRICULA_REPETIDA, usuario, alertas,
				verificarInmuebleRepetido(inmueble));
		if (lanzar.getErrores() != null && !lanzar.getErrores().isEmpty())
			return lanzar;
		return null;
	}

	@Override
	public boolean verificarInmuebleRepetido(InformacionInmueble inmueble) {
		List<InformacionInmueble> repetidos = inmuebleDao.consultarMatriculas(inmueble);
		if (repetidos != null && !repetidos.isEmpty()) {
			if (inmueble.getIdInfinmueble() == null)
				return true;
			for (InformacionInmueble repetido : repetidos) {
				if (repetido.getIdInfinmueble().compareTo(inmueble.getIdInfinmueble()) != 0)
					return true;
			}
		}
		return false;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearAlerta(Avaluo avaluo, int codigoAlerta, UsuarioDto usuario) throws NegocioException {
		if (usuario == null || usuario.getUsuario() == null)
			throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
		if (avaluo == null) {
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		}
		AlertaAvaluos alerta = new AlertaAvaluos();
		alerta.setIdAvaluo(avaluo.getIdAvaluo());
		alerta.setCodTipoAlerta(new Long(codigoAlerta));
		// alerta.setAvaluo(avaluo);
		alerta.setEstActiva(SiNo.SI.getValor());
		alerta.setFechaTransaccion(new Date());
		alerta.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
		alerta.setFechaCreacion(new Date());
		alerta.setUsuarioCreacion(usuario.getUsuario().getCodigo());
		try {
			alertaDao.crear(alerta);
			log.info("---se creo  alerta: " + alerta + " para:" + avaluo.getConsecutivoBanco());
		} catch (Exception e) {
			throw mgrExc.lanzarExcepcion(69, TipoErrorNegocio.ERROR, e.getMessage(), null);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private void eliminarAlerta(AlertaAvaluos alerta) throws NegocioException {
		if (alerta == null) {
			return;
		}
		try {
			alertaDao.eliminarPorId(alerta.getIdAlerta());
		} catch (Exception e) {
			throw mgrExc.lanzarExcepcion(69, TipoErrorNegocio.ERROR, e.getMessage(), null);
		}
	}

	@Override
	public void guardar(Observaciones observacion, UsuarioDto usuario) throws NegocioException {
		if (usuario == null || usuario.getUsuario() == null) {
			throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
		}
		if (observacion == null) {
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		}
		guardar(observacion.getArchivo(), observacion, observacion.getAvaluo(), usuario);
		observacion.setFechaTransaccion(new Date());
		observacion.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
		// si el registro no existe lo crearemos. de lo contrario solo se
		// actualizaran sus valores.
		if (observacion.getIdObservacion() == null || observacionDao.buscar(observacion.getIdObservacion()) == null) {
			observacion.setFechaCreacion(new Date());
			observacion.setUsuarioCreacion(usuario.getUsuario().getCodigo());
			observacionDao.crear(observacion);
		} else
			observacionDao.actualizar(observacion);
		guardar(observacion.getArchivo(), observacion, observacion.getAvaluo(), usuario);
	}

	private void guardar(Archivo archivo, Observaciones observacion, Avaluo avaluo, UsuarioDto usuario)
			throws NegocioException {
		if (usuario == null || usuario.getUsuario() == null) {
			throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
		}
		try {
			// En caso de que no se envie el archivoo este se encuentre vacio lo eliminamos
			if (archivo == null || (archivo.getAnexo() == null && UtilTexto.estaVacio(archivo.getNombreArchivo()))) {
				if (observacion.getIdArchivo() != null)
					svcArchivo.borrarDocumento(archivoDao.buscar(observacion.getIdArchivo()),null);
				observacion.setIdArchivo(null);
				observacion.setArchivo(null);
				return;
			}

			// guardamos el archivo y recuperamos la referencia.
			String llave = UtilConstantes.RUTA_SOPORTES + '/' + avaluo.getIdAvaluo() + '_' + archivo.getNombreArchivo();
			svcArchivo.guardarArchivo(avaluo, archivo, usuario, llave);
			
			// Por ultimo guardamos la referencia del archivo a la observacion.
			observacion.setIdArchivo(archivo.getIdArchivo());
			

		} catch (Exception e) {
			throw new NegocioException(TipoErrorNegocio.ERROR, e.getMessage());
		}

	}

	@Override
	public void guardar(ComportamientoOfertaDemanda oferta, UsuarioDto usuario) throws NegocioException {
		if (usuario == null || usuario.getUsuario() == null)
			throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
		if (oferta == null)
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		oferta.setFechaTransaccion(new Date());
		oferta.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
		// si el registro no existe lo crearemos. de lo contrario solo se
		// actualizaran sus valores.
		if (oferta.getIdComportamientoOfertaDemanda() == null
				|| ofertaDao.buscar(oferta.getIdComportamientoOfertaDemanda()) == null) {
			oferta.setFechaCreacion(new Date());
			oferta.setUsuarioCreacion(usuario.getUsuario().getCodigo());
			ofertaDao.crear(oferta);
		} else
			ofertaDao.actualizar(oferta);
	}
	
	@Override
	public void eliminar(ComportamientoOfertaDemanda oferta) throws NegocioException {

		if (oferta == null)
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);

		ofertaDao.eliminar(oferta);
	}
	
	@Override
	public void eliminarLiq(Long idAvaluo) throws NegocioException {
		// TODO Auto-generated method stub
		List<LiquidacionAvaluo> liquidacionesAnteriores = liquidacionDao.consultar(idAvaluo);
		for (LiquidacionAvaluo liquidacionAvaluo : liquidacionesAnteriores) {
			liquidacionDao.eliminar(liquidacionAvaluo);
		}
	}

	private boolean buscarLiquidacion(List<LiquidacionAvaluo> liquidacionesAnteriores,Long idLiqavaluo ) {
		for (LiquidacionAvaluo liquidacionAvaluo : liquidacionesAnteriores) {
			//si lo encuentra de
			if(liquidacionAvaluo.getIdLiqavaluo().equals(idLiqavaluo)) {
				return true;
			}
		}
		return false;
	}
	
	private void guardar(List<LiquidacionAvaluo> liquidaciones, Long avaluo, UsuarioDto usuario)
			throws NegocioException {

		int consecutivosOcupados[] = new int[99];

		if (usuario == null || usuario.getUsuario() == null)
			throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
		List<LiquidacionAvaluo> liquidacionesAnteriores = liquidacionDao.consultar(avaluo);
		if (liquidaciones == null || liquidaciones.isEmpty())
			return;
		// Recorre las liquidaciones para almacenarlas en la base de datos
		
		try {
			Long con = 1L;
			for (LiquidacionAvaluo liquidacionAvaluo : liquidaciones) {
				if (liquidacionAvaluo.isVacio())
					continue;
				
				liquidacionAvaluo.setConsecutivo(con);
				liquidacionAvaluo.setFechaTransaccion(new Date());
				liquidacionAvaluo.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
				if(buscarLiquidacion(liquidacionesAnteriores, liquidacionAvaluo.getIdLiqavaluo())) {
					//actualizar
					liquidacionDao.actualizar(liquidacionAvaluo);
				}else {
					//crear
					liquidacionAvaluo.setIdAvaluo(avaluo);
					liquidacionAvaluo.setFechaCreacion(new Date());
					liquidacionAvaluo.setUsuarioCreacion(usuario.getUsuario().getCodigo());
					liquidacionAvaluo.setIdLiqavaluo(null);
					liquidacionDao.crear(liquidacionAvaluo);
				}
				
				con++;
			}
		}catch(Exception e) {
			throw new NegocioException(TipoErrorNegocio.ERROR, e.getMessage());
		}	
	}
	/*private void guardar(List<LiquidacionAvaluo> liquidaciones, Long avaluo, UsuarioDto usuario)
			throws NegocioException {

		int consecutivosOcupados[] = new int[99];

		if (usuario == null || usuario.getUsuario() == null)
			throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
		List<LiquidacionAvaluo> liquidacionesAnteriores = liquidacionDao.consultar(avaluo);
		if (liquidaciones == null || liquidaciones.isEmpty())
			return;
		// Se recorren las liquidaciones anteriores para eliminarlas
		try {
			for (LiquidacionAvaluo liquidacionAvaluo : liquidacionesAnteriores) {
				if (liquidacionAvaluo.isVacio())
					continue;
				consecutivosOcupados[liquidacionAvaluo.getConsecutivo().intValue()] = 0; // marcar ocupado
				liquidacionDao.eliminar(liquidacionAvaluo);
				
			}
		}catch(Exception e) {
			throw new NegocioException(TipoErrorNegocio.ERROR, e.getMessage());
		}
		// Recorre las liquidaciones para almacenarlas en la base de datos
		try {
			for (LiquidacionAvaluo liquidacionAvaluo : liquidaciones) {
				if (liquidacionAvaluo.isVacio())
					continue;
				liquidacionAvaluo.setConsecutivo(new Long(siguienteLibre(consecutivosOcupados)));
				liquidacionAvaluo.setIdAvaluo(avaluo);
				liquidacionAvaluo.setIdLiqavaluo(null);
				liquidacionAvaluo.setFechaCreacion(new Date());
				liquidacionAvaluo.setUsuarioCreacion(usuario.getUsuario().getCodigo());
				liquidacionAvaluo.setFechaTransaccion(new Date());
				liquidacionAvaluo.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
				liquidacionDao.crear(liquidacionAvaluo);
			}
		}catch(Exception e) {
			throw new NegocioException(TipoErrorNegocio.ERROR, e.getMessage());
		}	
	}*/

	private int siguienteLibre(int consecutivosOcupados[]) {
		int i = 1; // no utilizar cero como consecutivo
		while (consecutivosOcupados[i] == 1 && i < 99) {
			i++;
		}
		consecutivosOcupados[i] = 1; // marcar ocupado
		return i;
	}

	@Override
	public void guardar(List<LiquidacionAvaluo> listado, LiquidacionAvaluoTotal total, UsuarioDto usuario)
			throws NegocioException {
		if (usuario == null || usuario.getUsuario() == null)
			throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
		if (total == null) {
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		}
			
		guardar(listado, total.getIdAvaluo(), usuario);
		total.setFechaTransaccion(new Date());
		total.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
		// si el registro no existe lo crearemos. de lo contrario solo se
		// actualizaran sus valores.
		try {
			if (total.getIdLiqavaluoTotal() == null || totalDao.buscar(total.getIdLiqavaluoTotal()) == null) {
				total.setFechaCreacion(new Date());
				total.setUsuarioCreacion(usuario.getUsuario().getCodigo());
				totalDao.crear(total);
			} else {
				totalDao.actualizar(total);
			}
		}catch(Exception e) {
			throw new NegocioException(TipoErrorNegocio.ERROR, e.getMessage());
		}
	}

//    private void guardar(List<LiquidacionAvaluo> liquidaciones, Long avaluo, UsuarioDto usuario) throws NegocioException {
//	if (usuario == null || usuario.getUsuario() == null)
//	    throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
//	List<LiquidacionAvaluo> liquidacionesAnteriores = liquidacionDao.consultar(avaluo);
//	if (liquidaciones == null || liquidaciones.isEmpty())
//	    return;
//	for (LiquidacionAvaluo liquidacionAvaluo : liquidaciones) {
//	    if (liquidacionAvaluo.isVacio())
//		continue;
//	    liquidacionAvaluo.setIdAvaluo(avaluo);
//	    // si el registro no existe lo crearemos. de lo contrario solo se
//	    // actualizaran sus valores.
//	    LiquidacionAvaluo liquidacionAnterior = null;
//	    if (liquidacionesAnteriores != null && !liquidacionesAnteriores.isEmpty()) {
//		for (LiquidacionAvaluo revisar : liquidacionesAnteriores) {
//		    if (revisar.getConsecutivo().compareTo(liquidacionAvaluo.getConsecutivo()) == 0) {
//			liquidacionAnterior = revisar;
//			break;
//		    }
//		}
//	    }
//	    if (liquidacionAnterior == null) {
//		liquidacionAvaluo.setIdLiqavaluo(null);
//		liquidacionAvaluo.setFechaCreacion(new Date());
//		liquidacionAvaluo.setUsuarioCreacion(usuario.getUsuario().getCodigo());
//		liquidacionAvaluo.setFechaTransaccion(new Date());
//		liquidacionAvaluo.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
//		liquidacionDao.crear(liquidacionAvaluo);
//	    } else {
//		liquidacionesAnteriores.remove(liquidacionAnterior);
//		liquidacionAnterior.setDescripcionDependencia(liquidacionAvaluo.getDescripcionDependencia());
//		liquidacionAnterior.setDescripcionLiquidacion(liquidacionAvaluo.getDescripcionLiquidacion());
//		liquidacionAnterior.setValor(liquidacionAvaluo.getValor());
//		liquidacionAnterior.setValorTotal(liquidacionAvaluo.getValorTotal());
//		liquidacionAnterior.setAreaLiquidacion(liquidacionAvaluo.getAreaLiquidacion());
//		liquidacionAnterior.setFechaTransaccion(new Date());
//		liquidacionAnterior.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
//		liquidacionDao.actualizar(liquidacionAnterior);
//	    }
//	}
//	if (liquidacionesAnteriores == null || liquidacionesAnteriores.isEmpty())
//	    return;
//	for (LiquidacionAvaluo anterior : liquidacionesAnteriores) {
//		liquidacionDao.eliminar(anterior);
//	}
//    }

	@Override
	public void guardar(CondicionesSalubridad salubridad, UsuarioDto usuario) throws NegocioException {
		if (usuario == null || usuario.getUsuario() == null)
			throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
		if (salubridad == null) {
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		}
		salubridad.setFechaTransaccion(new Date());
		salubridad.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
		// si el registro no existe lo crearemos. de lo contrario solo se
		// actualizaran sus valores.
		if (salubridad.getIdCondicionSalubridad() == null
				|| salubridadDao.buscar(salubridad.getIdCondicionSalubridad()) == null) {
			salubridad.setFechaCreacion(new Date());
			salubridad.setUsuarioCreacion(usuario.getUsuario().getCodigo());
			salubridadDao.crear(salubridad);
		} else
			salubridadDao.actualizar(salubridad);
	}

	@Override
	public byte[] obtenerDocumento(String idDocumento) throws NegocioException, IOException {
		return svcArchivo.obtenerDocumento(idDocumento);
	}

	@Override
	public String obtenerDocumentoURL(String idDocumento, String nombreArchivo) throws NegocioException, IOException {
		return svcArchivo.obtenerDocumentoURL(idDocumento, nombreArchivo);
	}

	@Override
	public void guardar(AnexoFotografico registroFotografico, List<ListaAnexosPdf> fotografias, Avaluo avaluo,
			UsuarioDto usuario) throws NegocioException {
		log.info("=============== Ingresa a metodo para guardar registro fotografico ==================");
		log.info("Consecutivo del avaluo: " + avaluo.getConsecutivoBanco());
//    	log.info(System.getProperty("aws.accessKeyId"));
		if (usuario == null || usuario.getUsuario() == null)
			throw mgrExc.lanzarExcepcion(11, TipoErrorNegocio.ERROR);
		if (registroFotografico == null) {
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		}

		registroFotografico.setIdAvaluo(avaluo.getIdAvaluo());
		registroFotografico.setFechaTransaccion(new Date());
		registroFotografico.setUsuarioTransaccion(usuario.getUsuario().getCodigo());
		List<ListaAnexosPdf> existentes = anexosDao.consultaAnexosPorAvaluo(avaluo);
		// Crea listado de archivos a enviar o actualizar
		List<ListaAnexosPdf> fotografiasParaEnviar = new ArrayList<>();
		List<String> codigoEliminar = new ArrayList<String>();
		if (fotografias != null && !fotografias.isEmpty()) {
			log.info("Preparando lista de fotografias para enviar a S3 .....");
			try {
				for (ListaAnexosPdf anexo : fotografias) {
					anexo.setAvaluo(avaluo);
					anexo.setIdAvaluo(avaluo.getIdAvaluo());
					log.info("Id de registro fotografico " + anexo.getIdListaAnexosPdf());
					if (anexo.getIdListaAnexosPdf() != null && existentes != null)
						existentes.remove(anexo);
					if (anexo.isModificado())
						fotografiasParaEnviar.add(anexo);
				}
			} catch (Exception e) {
				throw mgrExc.lanzarExcepcion(26, TipoErrorNegocio.ERROR, e.getMessage(), null);
			}
			try {
				if (fotografiasParaEnviar != null || !fotografiasParaEnviar.isEmpty())
					svcArchivo.guardarFotografiaMultiples(fotografiasParaEnviar, null, usuario, true);
			} catch (Exception e) {
				throw mgrExc.lanzarExcepcion(26, TipoErrorNegocio.ERROR, e.getMessage(), null);
			}
			Long idArchivoFachada = fotografias.get(0).getIdArchivo();
			log.info("Id archivo de imagen de fachada que se obtuvo " + idArchivoFachada);
			registroFotografico.setIdImgFachada(idArchivoFachada);
			registroFotografico.setIdDocAnexos(idArchivoFachada);
		}
		if (existentes != null && !existentes.isEmpty()) {
			log.info(" Existen registros fotograficos para borrar");
			for (ListaAnexosPdf eliminar : existentes) {
				log.info("Eliminando el registro fotografico No " + eliminar.getIdListaAnexosPdf());
				if (eliminar.getIdArchivo() == null)
					continue;
				Archivo archivoEliminar = archivoDao.buscar(eliminar.getIdArchivo());

				if (archivoEliminar != null && archivoEliminar.getIdDocumento() != null)
					codigoEliminar.add(archivoEliminar.getIdDocumento());
				archivoDao.eliminarPorId(eliminar.getIdArchivo());
				anexosDao.eliminarPorId(eliminar.getIdListaAnexosPdf());

				if (registroFotografico.getIdImgFachada() != null
						&& registroFotografico.getIdImgFachada().compareTo(eliminar.getIdArchivo()) == 0) {
					registroFotografico.setIdImgFachada(null);
					log.info("Borrando la imagen de la fachada ");
				}

				if (registroFotografico.getIdDocAnexos() != null
						&& registroFotografico.getIdDocAnexos().compareTo(eliminar.getIdArchivo()) == 0) {
					registroFotografico.setIdDocAnexos(null);
					log.info("Borrando el documento anexo ");
				}

			}
		}

		if (registroFotografico.getIdImgFachada() != null) {
			// si el registro no existe lo crearemos. de lo contrario solo se
			// actualizaran sus valores.
			if (registroFotografico.getIdAnexoFotografico() == null
					|| anexoDao.buscar(registroFotografico.getIdAnexoFotografico()) == null) {
				registroFotografico.setFechaCreacion(new Date());
				registroFotografico.setUsuarioCreacion(usuario.getUsuario().getCodigo());
				anexoDao.crear(registroFotografico);
				log.info("Fachada del registro fotografico Creado");
			} else {
				anexoDao.actualizar(registroFotografico);
				log.info("Fachada dell registro fotografico actualizado");
			}
		} else {
			throw mgrExc.lanzarExcepcion(26, TipoErrorNegocio.ERROR);
		}

		// Se ha finalizado con exito las operaciones de actualizacion y borrado en la
		// base de datos de Avaluos

		// Se deja al final la eliminacion en S3 una vez las operaciones en la BD han
		// terminado
		for (String eliminar : codigoEliminar) {
			svcArchivo.borrarDoc(eliminar);
		}
		log.info("=============== Finaliza metodo para guardar registro fotografico ==================");
	}

	@Override
	public void eliminarRegistrosFotograficos(AnexoFotografico registroFotografico, Avaluo avaluo)
			throws NegocioException {
		List<ListaAnexosPdf> existentes = anexosDao.consultaAnexosPorAvaluo(avaluo);
		List<String> codigoEliminar = new ArrayList<String>();
		if (existentes == null || existentes.isEmpty()) {
			return;
		}
		try {
			log.info(" Existen registros fotograficos para borrar");
			for (ListaAnexosPdf eliminar : existentes) {
				log.info("Eliminando el registro fotografico No " + eliminar.getIdListaAnexosPdf());
				if (eliminar.getIdArchivo() == null)
					continue;
//				svcArchivo.borrarDocumento(eliminar.getIdArchivo());
				Archivo archivoEliminar = archivoDao.buscar(eliminar.getIdArchivo());
				if (archivoEliminar != null && archivoEliminar.getIdDocumento() != null)
					codigoEliminar.add(archivoEliminar.getIdDocumento());
				archivoDao.eliminarPorId(eliminar.getIdArchivo());
				anexosDao.eliminarPorId(eliminar.getIdListaAnexosPdf());
				if (registroFotografico != null && registroFotografico.getIdAnexoFotografico() != null) {
					anexoDao.eliminarPorId(registroFotografico.getIdAnexoFotografico());
					registroFotografico = new AnexoFotografico();
				}
			}
		} catch (NegocioException e) {
			throw mgrExc.lanzarExcepcion(26, TipoErrorNegocio.ERROR);
		}
		for (String eliminar : codigoEliminar) {
			svcArchivo.borrarDoc(eliminar);
		}
	}

	@Override
	public InformacionBarrio consultarBarrio(Long idAvaluo) throws NegocioException {
		return barrioDao.consultar(idAvaluo);
	}

	@Override
	public InformacionInmueble consultarInmueble(Long idAvaluo) throws NegocioException {
		return inmuebleDao.consultar(idAvaluo);
	}

	@Override
	public InformacionConstruccion consultarConstruccion(Long idAvaluo) throws NegocioException {
		if (idAvaluo == null) {
			throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
		}
		return construccionDao.consultar(idAvaluo);
	}

	@Override
	public CondicionesSalubridad consultarSalubridad(Long idAvaluo) throws NegocioException {
		return salubridadDao.consultar(idAvaluo);
	}

	@Override
	public ComportamientoOfertaDemanda consultarOferta(Long idAvaluo) throws NegocioException {
		return ofertaDao.consultar(idAvaluo);
	}

	@Override
	public List<LiquidacionAvaluo> consultarLiquidacion(Long idAvaluo) throws NegocioException {
		return liquidacionDao.consultar(idAvaluo);
	}

	@Override
	public LiquidacionAvaluoTotal consultarLiquidacionTotal(Long idAvaluo) throws NegocioException {
		return totalDao.consultar(idAvaluo);
	}

	@Override
	public Observaciones consultarObservacion(Long idAvaluo) throws NegocioException {
		return observacionDao.consultar(idAvaluo);
	}

	@Override
	public AnexoFotografico consultarAnexo(Long idAvaluo) throws NegocioException {
		return anexoDao.consultar(idAvaluo);
	}

	@Override
	public List<ListaAnexosPdf> consultarRegistroFotograficoS3(Avaluo avaluo) throws NegocioException {
		Long contador = 1L;
		List<ListaAnexosPdf> anexosPdf = new  ArrayList();
		try{
			log.info("=== Consecutivo banco cuando ya va a buscar documentos====" + avaluo.getConsecutivoBanco().toString());
			List<String> listaNombres = null;
			listaNombres = svcArchivo.obtenerDocumentosporconsecutivo(avaluo.getConsecutivoBanco().toString());
			if(listaNombres == null) {
				return Collections.emptyList();
			}
			for (String nombre : listaNombres) {
				
				byte[] contenidoByte = new byte[] {};			
				contenidoByte = svcArchivo.obtenerDocumentoAvaluoMotor(nombre);
		
				ListaAnexosPdf anexo = new ListaAnexosPdf();
				Archivo archivoFotografico = new Archivo();
				
				archivoFotografico.setAnexo(contenidoByte);
				archivoFotografico.setNombreArchivo(nombre);
				
				anexo.setAnexo(contenidoByte);
				anexo.setEditarNombre(false);
				anexo.setAvaluo(avaluo);
				if(contador == 1L) {
					anexo.setPortada(true);
				}
				anexo.setConsecutivoAnexo(contador);
				anexo.setNombreFotografia(nombre);
				anexo.setArchivo(archivoFotografico);
				anexosPdf.add(anexo);
				
				contador = contador + 1;
				
			}
		}catch(Exception e) {
			
			throw mgrExc.lanzarExcepcion(32, TipoErrorNegocio.ERROR, e.getMessage(), null);		
			
		}
		return anexosPdf;
	}

	@Override
	public List<String> obtenerArchivofotograficos3(Avaluo avaluo) {
		List<String> listaNombres = null;
		try{
			listaNombres = svcArchivo.obtenerDocumentosporconsecutivo(avaluo.getConsecutivoBanco().toString());
		}catch(Exception e) {
			//throw mgrExc.lanzarExcepcion(32, TipoErrorNegocio.ERROR, e.getMessage(), null);		
			return null;
		}
		return listaNombres;
	}
}