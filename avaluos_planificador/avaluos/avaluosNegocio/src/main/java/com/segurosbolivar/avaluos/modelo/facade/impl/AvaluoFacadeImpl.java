package com.segurosbolivar.avaluos.modelo.facade.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.io.FileUtils;

import com.asesoftware.util.archivo.UtilPdf;
import com.asesoftware.util.archivo.UtilPropiedades;
import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioAlertaException;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilFecha;
import com.segurosbolivar.avaluos.modelo.cons.Procedencia;
import com.segurosbolivar.avaluos.modelo.cons.SeccionConstruccion;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;
import com.segurosbolivar.avaluos.modelo.data.CargueTmpDao;
import com.segurosbolivar.avaluos.modelo.dto.ConsultaAvaluoDto;
import com.segurosbolivar.avaluos.modelo.dto.UsuarioDto;
import com.segurosbolivar.avaluos.modelo.entity.AnexoFotografico;
import com.segurosbolivar.avaluos.modelo.entity.ArchivoPlano;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.Ciudad;
import com.segurosbolivar.avaluos.modelo.entity.ComportamientoOfertaDemanda;
import com.segurosbolivar.avaluos.modelo.entity.CondicionesSalubridad;
import com.segurosbolivar.avaluos.modelo.entity.Departamento;
import com.segurosbolivar.avaluos.modelo.entity.Dominios;
import com.segurosbolivar.avaluos.modelo.entity.InformacionBarrio;
import com.segurosbolivar.avaluos.modelo.entity.InformacionConstruccion;
import com.segurosbolivar.avaluos.modelo.entity.InformacionInmueble;
import com.segurosbolivar.avaluos.modelo.entity.LiquidacionAvaluo;
import com.segurosbolivar.avaluos.modelo.entity.LiquidacionAvaluoTotal;
import com.segurosbolivar.avaluos.modelo.entity.ListaAnexosPdf;
import com.segurosbolivar.avaluos.modelo.entity.Observaciones;
import com.segurosbolivar.avaluos.modelo.facade.IAvaluoFacade;
import com.segurosbolivar.avaluos.modelo.facade.IReporteFacade;
import com.segurosbolivar.avaluos.modelo.service.IAvaluo;
import com.segurosbolivar.avaluos.modelo.service.ICopiaAvaluo;
import com.segurosbolivar.avaluos.modelo.service.IDiligenciamiento;
import com.segurosbolivar.avaluos.modelo.service.IDirecciones;
import com.segurosbolivar.avaluos.modelo.service.IParametrizacion;
import com.segurosbolivar.avaluos.modelo.service.IPerito;
import com.segurosbolivar.avaluos.modelo.service.IProcedato;
import com.segurosbolivar.avaluos.modelo.service.IReporte;
import com.segurosbolivar.avaluos.modelo.service.ITronador;
import com.segurosbolivar.avaluos.modelo.service.IValidador;
import com.segurosbolivar.avaluos.modelo.service.impl.ManejadorErroresNegocio;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Implementaciï¿½n de la fachada para gestionar los avalï¿½os.
 *
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:39 a.m.
 */
@Stateless
public class AvaluoFacadeImpl implements IAvaluoFacade {

    private static final long serialVersionUID = -186068583311771603L;
    @EJB
    public IAvaluo avaluoSvc;
    @EJB
    public IParametrizacion parametrizacionSvc;
    @EJB
    public IDiligenciamiento diligenciamientoSvc;
    @EJB
    public IPerito peritoSvc;
    @EJB
    public ITronador tronadorSvc;
    @EJB
    public IReporte reporteSvc;
    @EJB
    public IValidador validadorSvc;
    @EJB
    public IDirecciones direccionSvc;

    @EJB
    public IReporteFacade reporteFacade;
    @EJB
    private ManejadorErroresNegocio mgrExc;
    @EJB
    private IProcedato procedatoSvc;
    @EJB
    private ICopiaAvaluo copiaSvc;

    @EJB
    private CargueTmpDao cargueTmpDao;

    private static ResourceBundle propiedades = UtilConstantes.RSC_ERRORES;

    @Override
    public InformacionBarrio consultarBarrio(Long idAvaluo) throws NegocioException {
	return diligenciamientoSvc.consultarBarrio(idAvaluo);
    }

    @Override
    public InformacionInmueble consultarInmueble(Long idAvaluo) throws NegocioException {
	return diligenciamientoSvc.consultarInmueble(idAvaluo);
    }

    @Override
    public InformacionConstruccion consultarConstruccion(Long idAvaluo) throws NegocioException {
	return diligenciamientoSvc.consultarConstruccion(idAvaluo);
    }

    @Override
    public CondicionesSalubridad consultarSalubridad(Long idAvaluo) throws NegocioException {
	return diligenciamientoSvc.consultarSalubridad(idAvaluo);
    }

    @Override
    public ComportamientoOfertaDemanda consultarOferta(Long idAvaluo) throws NegocioException {
	return diligenciamientoSvc.consultarOferta(idAvaluo);
    }

    @Override
    public List<LiquidacionAvaluo> consultarLiquidacion(Long idAvaluo) throws NegocioException {
	return diligenciamientoSvc.consultarLiquidacion(idAvaluo);
    }

    @Override
    public LiquidacionAvaluoTotal consultarLiquidacionTotal(Long idAvaluo) throws NegocioException {
	return diligenciamientoSvc.consultarLiquidacionTotal(idAvaluo);
    }

    @Override
    public Observaciones consultarObservacion(Long idAvaluo) throws NegocioException {
	return diligenciamientoSvc.consultarObservacion(idAvaluo);
    }

    @Override
    public AnexoFotografico consultarAnexo(Long idAvaluo) throws NegocioException {
	return diligenciamientoSvc.consultarAnexo(idAvaluo);
    }

    @Override
    public List<ListaAnexosPdf> consultarRegistroFotografico(Avaluo avaluo, boolean devolverContenido) throws NegocioException {
    	return diligenciamientoSvc.consultarRegistroFotografico(avaluo, devolverContenido);
    }
    
    @Override
    public void enviarParaAprobacion(List<Avaluo> avaluos, UsuarioDto usuario) throws NegocioException {
	avaluoSvc.enviarParaAprobacion(avaluos, usuario);
    }

    @Override
    public void validar(Avaluo avaluo, UsuarioDto usuario) throws NegocioException {
	NegocioException lanzar = mgrExc.lanzarExcepcion(72, TipoErrorNegocio.ALERTA);
	validadorSvc.validar(avaluoSvc.consultarAvaluo(avaluo.getIdAvaluo()), lanzar, usuario);
	if (lanzar.getErrores() != null && !lanzar.getErrores().isEmpty())
	    throw lanzar;
    }

    @Override
    public List<Avaluo> consultar(ConsultaAvaluoDto consulta, int inicio, int registroXPagina, String campoOrden, SentidoOrientacion sentido) throws NegocioException {
	return avaluoSvc.consultarAvaluo(consulta, inicio, registroXPagina, campoOrden, sentido);
    }

    @Override
    public int cantidadRegistros(ConsultaAvaluoDto consulta) throws NegocioException {
	return avaluoSvc.cantidadRegistros(consulta);
    }

    @Override
    public Avaluo aprobar(Avaluo aprobar, UsuarioDto usuario) throws Exception {
	return avaluoSvc.aprobar(aprobar, usuario);
    }

    @Override
    public void enviarCorreos(List<Avaluo> avaluos, UsuarioDto usuario) throws NegocioException {
	if (avaluos == null || avaluos.isEmpty())
	    return;
	for (Avaluo avaluo : avaluos) {
	    diligenciamientoSvc.enviarCorreos(avaluo, usuario);
	}
    }

    @Override
    public void enviarCorreos(Avaluo avaluoConsulta, UsuarioDto usuario) throws NegocioException {
	diligenciamientoSvc.enviarCorreos(avaluoConsulta, usuario);
    }

    @Override
    public List<Avaluo> aprobar(List<Avaluo> seleccionados, UsuarioDto usuario) throws NegocioException, Exception {
	return avaluoSvc.aprobar(seleccionados, usuario);
    }

    @Override
    public void transcribir(Avaluo copia, Avaluo original, UsuarioDto usuario) throws Exception {
	avaluoSvc.validarRepeticionConsecutivoBanco(copia);
	copiaSvc.transcribir(copia, original, usuario);
    }

    @Override
    public void replicar(List<Avaluo> avaluoSeleccionado, UsuarioDto usuario) throws Exception {
	copiaSvc.validarCopia(avaluoSeleccionado, true);
	copiaSvc.replicar(avaluoSeleccionado, usuario);
    }

    @Override
    public void guardarMultipleConstructor(List<Avaluo> copias, Avaluo original, Long codigoNombreConstructora, String nombreConstructora, UsuarioDto usuarioDto) throws NegocioException {
        	try {
        		copiaSvc.validarCopiaMultiple(copias);
				copiaSvc.multipleConstructor(copias, original, codigoNombreConstructora, nombreConstructora, usuarioDto);
			}catch (NegocioException e) {
				throw e;
			}
        	catch (Exception e) {
				e.printStackTrace();
				throw mgrExc.lanzarExcepcion(36, TipoErrorNegocio.ERROR, e.getMessage(), null);
			}
    }

    @Override
    public File crearCopiaSeguridad(List<Avaluo> seleccionados) throws NegocioException {
	return procedatoSvc.copiaSeguridadAvaluos(seleccionados);
    }

    @Override
    public void eliminar(Avaluo avaluo, UsuarioDto usuario) throws NegocioException {
	if (avaluo == null)
	    return;
	List<Avaluo> avaluos = new ArrayList<>();
	avaluos.add(avaluo);
	avaluoSvc.eliminar(avaluos, usuario);
    }
    
    @Override
    public void eliminar(Avaluo avaluo) throws NegocioException {
	avaluoSvc.eliminar(avaluo);
    }
    
    @Override
    public void eliminar(ComportamientoOfertaDemanda ofertademanda) throws NegocioException {
	diligenciamientoSvc.eliminar(ofertademanda);
    }
    

    @Override
    public void reversar(Avaluo avaluo, UsuarioDto usuario) throws NegocioException {
	if (avaluo == null)
	    return;
	List<Avaluo> avaluos = new ArrayList<>();
	avaluos.add(avaluo);
	avaluoSvc.reversar(avaluos, usuario);
    }

    @Override
    public void guardar(Avaluo avaluo, UsuarioDto usuario) throws NegocioException {
	avaluoSvc.guardar(avaluo, usuario, Procedencia.INDIVIDUAL);
    }

    @Override
    public void guardar(InformacionBarrio barrio, UsuarioDto usuario) throws NegocioException {
	diligenciamientoSvc.guardar(barrio, usuario);
    }

    @Override
    public void guardar(ComportamientoOfertaDemanda ofertademanda, UsuarioDto usuario) throws NegocioException {
	diligenciamientoSvc.guardar(ofertademanda, usuario);
    }

    @Override
    public void guardar(InformacionInmueble inmueble, Avaluo avaluo, UsuarioDto usuario) throws NegocioException {
	diligenciamientoSvc.guardar(inmueble, avaluo, usuario);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public NegocioAlertaException generarAlertasInformacionInmueble(InformacionInmueble inmueble, Avaluo avaluo, UsuarioDto usuario) throws NegocioException {
	return diligenciamientoSvc.generarAlertasInformacionInmueble(avaluo, inmueble, usuario);
    }

    @Override
    public void guardar(List<LiquidacionAvaluo> listado, LiquidacionAvaluoTotal total, UsuarioDto usuario) throws NegocioException {
	diligenciamientoSvc.guardar(listado, total, usuario);
    }
    
    @Override
    public void eliminarLiq(Long idAvaluo) throws NegocioException {
	diligenciamientoSvc.eliminarLiq( idAvaluo);
    }

    @Override
    public void guardar(Observaciones observacion, UsuarioDto usuario) throws NegocioException {
	diligenciamientoSvc.guardar(observacion, usuario);
    }

    @Override
    public void guardar(InformacionConstruccion construccion, SeccionConstruccion seccion, Avaluo avaluo, UsuarioDto usuario) throws NegocioException {
	diligenciamientoSvc.guardar(construccion, seccion, avaluo, usuario);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public NegocioAlertaException generarAlertasInformacionConstruccion(InformacionConstruccion construccion, SeccionConstruccion seccion, Avaluo avaluo, UsuarioDto usuario)
	    throws NegocioException {
	if (SeccionConstruccion.ADICIONAL.equals(seccion))
	    return diligenciamientoSvc.generarAlertasInformacionConstruccion(avaluo, construccion, usuario);
	return null;
    }

    public NegocioAlertaException preAprobar(List<Avaluo> avaluos, UsuarioDto usuario) throws NegocioException {
	NegocioAlertaException agruparAlertas = (NegocioAlertaException) mgrExc.lanzarExcepcion(111L, TipoErrorNegocio.ALERTA);
	for (Avaluo avaluo : avaluos) {
	    try {
	    	if(avaluo.isProvisional()) {
	    		agruparAlertas.agregarError((NegocioAlertaException)mgrExc.lanzarExcepcion(73L, TipoErrorNegocio.ALERTA, null, new String[] {avaluo.getConsecutivoBanco().toString()}));
			}
		NegocioAlertaException alerta = preAprobar(avaluo, usuario);
		if (alerta != null)
		    throw alerta;
	    } catch (NegocioAlertaException e) {
		agruparAlertas.agregarError(e);
	    }
	}
	if (agruparAlertas.getErrores() != null && !agruparAlertas.getErrores().isEmpty())
	    return agruparAlertas;
	return null;
    }

    @Override
    public NegocioAlertaException preAprobar(Avaluo preAprobar, UsuarioDto usuario) throws NegocioException {
	if (!preAprobar.isPuedeAprobar())
	    return (NegocioAlertaException) mgrExc.lanzarExcepcion(56, TipoErrorNegocio.ALERTA, null, new String[] { preAprobar.getConsecutivoBanco() + "" });
	if (preAprobar.isMovil() && preAprobar.getObservacion().getIdArchivo() == null )
	   return (NegocioAlertaException) mgrExc.lanzarExcepcion(126, TipoErrorNegocio.ALERTA, null, new String[] { preAprobar.getConsecutivoBanco() + "" });
	NegocioException lanzar = mgrExc.lanzarExcepcion(68, TipoErrorNegocio.ERROR);
	Avaluo avaluoConsulta = consultarAvaluo(preAprobar.getIdAvaluo());
	if (!validadorSvc.validar(avaluoConsulta, lanzar, usuario))
	    throw lanzar;
	diligenciamientoSvc.generarAlertaPrevioAprobacion(preAprobar, usuario);
	return diligenciamientoSvc.consultarAlertas(preAprobar);
    }

    @Override
    public NegocioAlertaException consultarAlertasCopia(Avaluo avaluoOrigional, List<Avaluo> copias, UsuarioDto usuario) throws NegocioException {
	return copiaSvc.consultarAlertasCopia(avaluoOrigional, copias);
    }

    @Override
    public NegocioAlertaException generarAlertaPrevioAprobacion(Avaluo preAprobar, UsuarioDto usuario) throws NegocioException {
	return diligenciamientoSvc.generarAlertaPrevioAprobacion(preAprobar, usuario);
    }

    @Override
    public void guardar(CondicionesSalubridad salubridad, UsuarioDto usuario) throws NegocioException {
	diligenciamientoSvc.guardar(salubridad, usuario);
    }

    @Override
    public BigDecimal consultaUvr() throws NegocioException {
	return tronadorSvc.consultaUvr();
    }
    
    @Override
    public String consultaUvrMotor() throws NegocioException {
    	return tronadorSvc.consultaUvrMotor();
    }

    @Override
    public byte[] obtenerDocumento(String idDocumento) throws NegocioException, IOException {
	return diligenciamientoSvc.obtenerDocumento(idDocumento);
    }
    
    @Override
	public String obtenerDocumentoURL(String idDocumento, String nombreArchivo) throws NegocioException, IOException {
    	return diligenciamientoSvc.obtenerDocumentoURL(idDocumento, nombreArchivo);
	}

    @Override
    public void guardar(AnexoFotografico registroFotografico, List<ListaAnexosPdf> fotografias, Avaluo avaluo, UsuarioDto usuario) throws NegocioException {
	diligenciamientoSvc.guardar(registroFotografico, fotografias, avaluo, usuario);
    }

    @Override
    public File imprimir(List<Avaluo> avaluos, boolean registroFotografico, UsuarioDto usuario) throws NegocioException {
	return reporteSvc.imprimir(avaluos, registroFotografico, usuario);
    }

    @Override
    public Avaluo consultarAvaluo(Long idAvaluo) throws NegocioException {
	return avaluoSvc.consultarAvaluo(idAvaluo);
    }

    @Override
    public String transformarDireccion(String direccion, boolean validateComplemento) {
	return direccionSvc.transformarDireccion(direccion, validateComplemento);
    }

    @Override
    public String transformarDireccionComplementaria(String direccion) {
	return direccionSvc.transformarDireccionComplementaria(direccion);
    }

    @Override
    @SuppressWarnings("unchecked")
    public File generarReporteAvaluos(List<Avaluo> avaluos, boolean verAsegurabilidad) throws NegocioException {
	if (avaluos == null || avaluos.isEmpty())
	    return null;
	File retorno = new File(UtilPropiedades.cargarPropiedad(UtilConstantes.RSC_ERRORES, UtilConstantes.RUTA_REPOSITORIO)
		+ UtilConstantes.NOMBRE_REPORTE_AVALUOS.replace(".jasper", "") + UtilFecha.fechaActualFormatoAAAAMMDDHH24MISS() + ".pdf");
	try {
	    List<Dominios> identificacionLista = (List<Dominios>) parametrizacionSvc.obtenerListadoCache("TIPOIDENTIFICACION" + UtilConstantes.SUFIJO_DOMINIOS);
	    List<Dominios> tipoAvaluoLista = (List<Dominios>) parametrizacionSvc.obtenerListadoCache("TIPOAVALUO" + UtilConstantes.SUFIJO_DOMINIOS);
	    List<Dominios> tipoViviendaLista = (List<Dominios>) parametrizacionSvc.obtenerListadoCache("TIPOVIVIENDA" + UtilConstantes.SUFIJO_DOMINIOS);
	    List<Dominios> estadoConstruccionLista = (List<Dominios>) parametrizacionSvc.obtenerListadoCache("ESTADOCONSTRUCCION" + UtilConstantes.SUFIJO_DOMINIOS);
	    List<Dominios> estadoAvaluoLista = (List<Dominios>) parametrizacionSvc.obtenerListadoCache("ESTADOAVALUO" + UtilConstantes.SUFIJO_DOMINIOS);
	    List<Dominios> listaSiNo = (List<Dominios>) parametrizacionSvc.obtenerListadoCache("SI_NO" + UtilConstantes.SUFIJO_DOMINIOS);
	    List<Dominios> listaCalificacion = (List<Dominios>) parametrizacionSvc.obtenerListadoCache("CALGARANTIA" + UtilConstantes.SUFIJO_DOMINIOS);
	    List<Dominios> listaEstato = (List<Dominios>) parametrizacionSvc.obtenerListadoCache("ESTRATO" + UtilConstantes.SUFIJO_DOMINIOS);
	    for (Avaluo avaluo : avaluos) {
		avaluo.setAsegurabilidadDescripcion(parametrizacionSvc.consultarDescripcionDominio(listaSiNo, avaluo.getAsegurabilidad()));
		if (avaluo.getIdTipoIdentificacion() != null)
		    avaluo.setTipoDocumento(parametrizacionSvc.consultarDescripcionDominio(identificacionLista, avaluo.getIdTipoIdentificacion().toString()));
		if (avaluo.getEstadoAvaluo() != null)
		    avaluo.setEstadoAvaluoDescripcion(parametrizacionSvc.consultarDescripcionDominio(estadoAvaluoLista, avaluo.getEstadoAvaluo().toString()));
		if (avaluo.getCodTipoAvaluo() != null)
		    avaluo.setTipoAvaluoNombre(parametrizacionSvc.consultarDescripcionDominio(tipoAvaluoLista, avaluo.getCodTipoAvaluo().toString()));
		if (avaluo.getInformacionInmueble() != null && avaluo.getInformacionInmueble().getTipoVivienda() != null)
		    avaluo.setTipoViviendaNombre(parametrizacionSvc.consultarDescripcionDominio(tipoViviendaLista, avaluo.getInformacionInmueble().getTipoVivienda().toString()));
		if (avaluo.getInformacionConstruccion() != null && avaluo.getInformacionConstruccion().getEstadoConstruccion() != null)
		    avaluo.setEstadoConstruccionDescripcion(
			    parametrizacionSvc.consultarDescripcionDominio(estadoConstruccionLista, avaluo.getInformacionConstruccion().getEstadoConstruccion().toString()));
		if (avaluo.getLiquidacionTotal() != null && avaluo.getLiquidacionTotal().getCalificacion() != null)
		    avaluo.setCalificacion(parametrizacionSvc.consultarDescripcionDominio(listaCalificacion, avaluo.getLiquidacionTotal().getCalificacion().toString()));
		if (avaluo.getInformacionBarrio() != null && avaluo.getInformacionBarrio().getEstrato() != null)
		    avaluo.setEstrato(parametrizacionSvc.consultarDescripcionDominio(listaEstato, avaluo.getInformacionBarrio().getEstrato().toString()));
	    }
	    Map<String, Object> parametros = new HashMap<>();
	    parametros.put("verAsegurabilidad", verAsegurabilidad);
	    String rutaReporte = UtilPropiedades.cargarPropiedad(propiedades, UtilConstantes.RUTA_JASPER) + UtilConstantes.NOMBRE_REPORTE_AVALUOS;
	    FileUtils.writeByteArrayToFile(retorno,
		    UtilPdf.generarReportJasperPdf(this.getClass().getClassLoader().getResourceAsStream(rutaReporte), parametros, new JRBeanCollectionDataSource(avaluos)));
	} catch (Exception e) {
	    throw mgrExc.lanzarExcepcion(83L, TipoErrorNegocio.ERROR, e.getMessage(), null);
	}
	return retorno;
    }

    @Override
    public Dominios consultarDominioPorAbreviacion(String llave, String abreviacion) throws NegocioException {
	return parametrizacionSvc.consultarDominioPorAbreviacion(llave, abreviacion);
    }

    @Override
    public void impresionErronea(Avaluo avaluo, UsuarioDto usuario) throws NegocioException {
	if (avaluo == null)
	    return;
	List<Avaluo> avaluos = new ArrayList<>();
	avaluos.add(avaluo);
	avaluoSvc.impresionErronea(avaluos, usuario);
    }

    @Override
    public Ciudad consultarCiudad(long idCiudad) throws NegocioException {
	return parametrizacionSvc.consultarCiudad(idCiudad);
    }

    @Override
    public Departamento consultarDepartamento(long idDepartamento) throws NegocioException {
	return parametrizacionSvc.consultarDepartamento(idDepartamento);
    }

    @Override
    public ArrayList<String> obtenerExtentCentroide(String departamentoDivipola, String ciudadDivipola, String direccion, String barrio) throws Exception {
	return avaluoSvc.obtenerExtentCentroide(departamentoDivipola, ciudadDivipola, direccion, barrio);
    }

    public List<ArchivoPlano> consultarCamposObligatoriosCategoria(Long categoria, String tabla) throws NegocioException {
	return avaluoSvc.consultarCamposObligatoriosCategoria(categoria, tabla);
    }

	@Override
	public void eliminarRegistroFotografico(AnexoFotografico registroFotografico, Avaluo avaluo) throws NegocioException {
		diligenciamientoSvc.eliminarRegistrosFotograficos(registroFotografico, avaluo);
	}

	@Override
	public List<String> consultarRegistroFotograficoS3(Avaluo avaluo) throws NegocioException {
		// TODO Auto-generated method stub
		return diligenciamientoSvc.obtenerArchivofotograficos3(avaluo);
	}

	@Override
	public List<Avaluo> consultarAvaluoPorMatricula(ConsultaAvaluoDto consulta, int inicio, int registroXPagina,
			String campoOrden, SentidoOrientacion sentido) throws NegocioException {
		return avaluoSvc.consultarAvaluoPorMatriculaInmobiliaria(consulta, inicio, registroXPagina, campoOrden, sentido);
	}

}