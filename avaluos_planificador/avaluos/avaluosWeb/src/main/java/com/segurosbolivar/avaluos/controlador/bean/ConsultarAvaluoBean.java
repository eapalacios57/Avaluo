package com.segurosbolivar.avaluos.controlador.bean;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.StreamedContent;

import com.asesoftware.util.cons.TipoComparacion;
import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioAlertaException;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilNumero;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.cons.AvaluosCons;
import com.segurosbolivar.avaluos.controlador.bean.diligenciamiento.AvaluoBean;
import com.segurosbolivar.avaluos.controlador.bean.diligenciamiento.RegistroFotograficoBean;
import com.segurosbolivar.avaluos.controlador.bean.general.GeneralAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.general.ImpresionBean;
import com.segurosbolivar.avaluos.controlador.bean.general.PopupConfirmacionBean;
import com.segurosbolivar.avaluos.controlador.bean.seguridad.SesionBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilValidadorJsf;
import com.segurosbolivar.avaluos.modelo.cons.EstadoAvaluo;
import com.segurosbolivar.avaluos.modelo.dto.ConsultaAvaluoDto;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.Ciudad;
import com.segurosbolivar.avaluos.modelo.entity.Departamento;
import com.segurosbolivar.avaluos.modelo.facade.IAvaluoFacade;
import com.segurosbolivar.avaluos.modelo.facade.IReporteFacade;
import com.segurosbolivar.avaluos.modelo.service.IArchivo;
import com.segurosbolivar.avaluos.modelo.service.IComplementos;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;

@ManagedBean
@SessionScoped
public class ConsultarAvaluoBean extends GeneralAbstractoBean implements Serializable{

    private static final long serialVersionUID = 620295998957077322L;
    private ConsultaAvaluoDto consulta;
    private LazyDataModel<Avaluo> listado;
    private List<Avaluo> paginaActual;
    private boolean verFiltros;
    private boolean verTabla;
    private boolean verMapa;
    private boolean consultaExitosa;
    private List<Avaluo> seleccionados;
    private List<Avaluo> impresos;
    private boolean imprimiendo;
    private Integer cantidadRegistros;
    private Avaluo avaluo;
    /**
     * Define la cantidad de registros por pagina
     */
    private static final int REGISTRO_PAGINA = 100;
    @EJB
    private transient IReporteFacade reporteFacade;
    @EJB
    private transient IAvaluoFacade avaluoSvc;
    @EJB
	public transient IArchivo archivoImpl;
    @EJB
	public transient IComplementos complementosImpl;

    {
	listado = new LazyDataModel<Avaluo>() {

	    private static final long serialVersionUID = 607798256095530157L;

	    @Override
	    public List<Avaluo> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		try {
			if(SesionBean.getBean().getPermisosEspecificos().containsKey(UtilConstantes.PERM_BLOQUEO_NUEVOS))
				consulta.setEstadoAvaluo(EstadoAvaluo.APROBADO.getValor());
		    paginaActual = avaluoSvc.consultar(consulta, first, pageSize, sortField, UtilJsf.resolverOrientacion(sortOrder));
		    if (paginaActual == null)
			return Collections.emptyList();
		    StringBuilder sb = new StringBuilder("[");
		    boolean imprimioAlguno = false;
		    for (int i = 0; i < paginaActual.size(); i++) {
			Avaluo avaluoActual = paginaActual.get(i);
			int indiceContiene = impresos == null ? -1 : impresos.indexOf(avaluoActual);
			if (impresos != null && indiceContiene >= 0 && imprimiendo) {
			    avaluoActual.setImpreso(impresos.get(indiceContiene).isImpreso());
			    imprimioAlguno = true;
			}
			String calificacion = "";
			if (avaluoActual.getLiquidacionTotal() != null && avaluoActual.getLiquidacionTotal().getCalificacion() != null) {
			    calificacion = avaluoActual.getLiquidacionTotal().getCalificacion().toString();
			}
			sb.append("[" + avaluoActual.getLongitudSFBUA() + "," + avaluoActual.getLatitudSFBUA() + ",'" + calificacion + "']"
				+ (i == paginaActual.size() - 1 ? "" : ","));
		    }
		    imprimiendo = imprimioAlguno;
		    sb.append("]");
		    reLocalizarMapa(sb.toString());
		    return paginaActual;
		} catch (Exception e) {
		    procesarError(e);
		    return Collections.emptyList();
		}
	    }

	    /**
	     * Metodo encargado de relocalizar el mapa de acuerdo al departamento y/o ciudad
	     * seleccionado por el usuario
	     * 
	     * @throws NegocioException
	     */
	    public void reLocalizarMapa(String points) throws NegocioException {
		String latitud = null;
		String longitud = null;
		Long zoom = 5L;
		Ciudad ciudad = null;
		Departamento departamento = null;
		if (consulta.getIdCiudad() != null)
		    ciudad = avaluoSvc.consultarCiudad(consulta.getIdCiudad());
		else if (consulta.getIdDepartamento() != null)
		    departamento = avaluoSvc.consultarDepartamento(consulta.getIdDepartamento());
		if (ciudad != null) {
		    latitud = ciudad.getLatitud();
		    longitud = ciudad.getLongitud();
		    zoom = 12L;
		} else if (departamento != null) {
		    latitud = departamento.getLatitud();
		    longitud = departamento.getLongitud();
		    zoom = 8L;
		} else {
		    latitud = AvaluosCons.LATITUD_DEFAULT;
		    longitud = AvaluosCons.LONGITUD_DEFAULT;
		}
		String funcionJavaScript = "reLocalizarMapa(" + UtilNumero.pasarDouble(longitud) + "," + UtilNumero.pasarDouble(latitud) + "," + zoom + "," + points + ");";
		RequestContext.getCurrentInstance().execute(funcionJavaScript);
	    }

	    @Override
	    @SuppressWarnings("unchecked")
	    public Avaluo getRowData(String rowKey) {
		List<Avaluo> avaluos = (List<Avaluo>) getWrappedData();
		for (Avaluo avaluo : avaluos) {
		    if (avaluo.getIdAvaluo().toString().equals(rowKey))
			return avaluo;
		}
		return null;
	    }

	    @Override
	    public Object getRowKey(Avaluo object) {
		return object.getIdAvaluo();
	    }

	};
    }

    @Override
    public void inicio() {
	consulta = new ConsultaAvaluoDto();
	consulta.setIdDepartamento(null);
	consulta.setUsuario(getUsuario());
	PopupPeritoBean.getBean().ocultar();
	verFiltros = true;
	listado.setPageSize(REGISTRO_PAGINA);
    }

    public static ConsultarAvaluoBean getBean() {
	return UtilJsf.obtenerBackingBean("consultarAvaluoBean");
    }

    @Override
    public String getPermiso() {
	return null;
    }

    public void consultarAvaluo() {
	try {
	    if (PopupPeritoBean.getBean().isVer())
		return;
	    if (!validar())
		return;
	    if (consulta.isTipoAvaluoProvisional())
		consulta.setFiltroTipoAvaluo("1");
	    if (consulta.isTipoAvaluoDefinitivo())
		consulta.setFiltroTipoAvaluo("2");
	    if (consulta.isTipoAvaluoProvisional() && consulta.isTipoAvaluoDefinitivo())
		consulta.setFiltroTipoAvaluo("1,2");
	    consulta.setUsuario(getUsuario());
	    cantidadRegistros = avaluoSvc.cantidadRegistros(consulta);
	    listado.setRowCount(cantidadRegistros);
	    verFiltros = true;
	    verMapa = true;
	    verTabla = true;
	    imprimiendo = false;
	    consultaExitosa = true;
	    actualizar("consultarForm");
	} catch (Exception e) {
	    consultaExitosa = false;
	    procesarError(e);
	}
    }
    

    public void ocultarMapa() {
	verMapa = !verMapa;
    }

    public void consultarPeritos() {
	try {
	    PopupPeritoBean.getBean().ver("consultarAvaluoBean.perito");
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    public void setPerito(String numeroDocumento) {
	consulta.setUsuarioCreacion(numeroDocumento);
	actualizar("consultarForm:perito");
    }

    public void limpiarPerito() {
	if (consulta != null)
	    consulta.setUsuarioCreacion(null);
	actualizar("consultarForm:perito");
    }

    private boolean validar() throws NegocioException {
	boolean validar = true;
	if (consulta.getIdCiudad() == null && consulta.getIdDepartamento() == null && consulta.getNumeroIdentificacion() == null && consulta.getFechaCargueDesde() == null
		&& consulta.getFecharCargueHasta() == null && UtilTexto.estaVacio(consulta.getMatricula()) ) {
	    UtilValidadorJsf.validar("", "consultarForm:numeroIdentificacion", true, 10, null);
	    UtilValidadorJsf.validar("", "consultarForm:departamento", true, 10, null);
	    UtilValidadorJsf.validar("", "consultarForm:ciudad", true, 10, null);
	    UtilValidadorJsf.validar("", "consultarForm:fechaCargue", true, 10, null);

	    throw mgrExc.lanzarExcepcion(22, TipoErrorNegocio.ALERTA, obtenerEtiqueta("men_consultarMinimo"), null);
	}
	validar &= UtilValidadorJsf.validar(consulta.getIdCiudad(), "consultarForm:ciudad", false, 4, null);
	validar &= UtilValidadorJsf.validar(consulta.getIdCiudad(), "consultarForm:departamento", false, 4, null);
	validar &= UtilValidadorJsf.validar(consulta.getNumeroIdentificacion(), "consultarForm:numeroIdentificacion", false, 10, null);
	validar &= UtilValidadorJsf.comparar(consulta.getNumeroIdentificacion(), TipoComparacion.MENOR_IGUAL, 9999999999L, "consultarForm:numeroIdentificacion", null);

	if (consulta.getFechaCargueDesde() != null && consulta.getFecharCargueHasta() != null) {
	    validar &= UtilValidadorJsf.comparar(consulta.getFechaCargueDesde(), TipoComparacion.MENOR_IGUAL, consulta.getFecharCargueHasta(), "consultarForm:fechaCargue", null);
	}
	validar &= UtilValidadorJsf.validar(consulta.getIdTipoIdentificacion(), "consultarForm:tipoDocumento", false, 13, null);
	validar &= UtilValidadorJsf.validar(consulta.getDireccionInmueble(), "consultarForm:direccion", false, 100, null);
	validar &= UtilValidadorJsf.validarFormato(consulta.getMatricula(), "consultarForm:matriculaInmobiliaria", false, 20, AvaluosCons.FORMATO_MATRICULA, null,
		AvaluosCons.FORMATO_MATRICULA_MEN);

	// si existen los dos rangos de fechasAvaluo
	if (consulta.getFechaAvaluoDesde() != null && consulta.getFechaAvaluoHasta() != null) {
	    validar &= UtilValidadorJsf.comparar(consulta.getFechaAvaluoDesde(), TipoComparacion.MENOR_IGUAL, consulta.getFechaAvaluoHasta(), "consultarForm:fechaAvaluo", null);
	}
	if (consulta.getFechaImpresionDesde() != null && consulta.getFechaImpresionHasta() != null) {
	    validar &= UtilValidadorJsf.comparar(consulta.getFechaImpresionDesde(), TipoComparacion.MENOR_IGUAL, consulta.getFechaImpresionHasta(), "consultarForm:fechaImpresion",
		    null);
	}
	validar &= UtilValidadorJsf.comparar(consulta.getValorDesde(), TipoComparacion.MENOR_IGUAL, consulta.getValorHasta(), "consultarForm:valorTotal", null);
	final String tagAvance = "consultarForm:avance";
	validar &= UtilValidadorJsf.comparar(consulta.getAvanceDesde(), TipoComparacion.MAYOR_IGUAL, 0L, tagAvance + "Desde", null);
	validar &= UtilValidadorJsf.comparar(consulta.getAvanceHasta(), TipoComparacion.MAYOR_IGUAL, 0L, tagAvance + "Hasta", null);
	validar &= UtilValidadorJsf.comparar(consulta.getAvanceDesde(), TipoComparacion.MENOR_IGUAL, 100L, tagAvance + "Desde", null);
	validar &= UtilValidadorJsf.comparar(consulta.getAvanceHasta(), TipoComparacion.MENOR_IGUAL, 100L, tagAvance + "Hasta", null);
	if (validar)
	    validar &= UtilValidadorJsf.comparar(consulta.getAvanceDesde(), TipoComparacion.MENOR_IGUAL, consulta.getAvanceHasta(), tagAvance, "ca_avance_desde");
	validar &= UtilValidadorJsf.comparar(consulta.getVetustezDesde(), TipoComparacion.MENOR_IGUAL, consulta.getVetustezHasta(), "consultarForm:vetustez", null);
	return validar;
    }

    public void mostrarFiltro() {
	verFiltros = !verFiltros;
	consulta = new ConsultaAvaluoDto();
	consulta.setIdDepartamento(null);
	consulta.setUsuario(getUsuario());
    }
    
//    private void limpiarFiltros() {
//    	consulta = new ConsultaAvaluoDto();
//    	consulta.setIdDepartamento(null);
//    	consulta.setUsuario(getUsuario());
//    }

    public void aprobar() {
	try {
	    if (seleccionados == null || seleccionados.isEmpty())
		throw mgrExc.lanzarExcepcion(34, TipoErrorNegocio.INFO);
	    NegocioAlertaException alerta = avaluoSvc.preAprobar(seleccionados, getUsuario());
	    if (alerta != null)
		throw alerta;
	    avaluoSvc.aprobar(seleccionados, getUsuario());
	    mensajeConfirmacion(obtenerEtiqueta("con_menAprobar"));
	    cantidadRegistros = avaluoSvc.cantidadRegistros(consulta);
	    listado.setRowCount(cantidadRegistros);
	    avaluoSvc.enviarCorreos(seleccionados, getUsuario());
//	    log.info("--Empieza el envio de adjuntos a bus Davivienda");
//		enviarAdjuntos();
//		log.info("--Empieza el envio de notificacion a bus Davivienda");
//		enviarNotificacion();
	} catch (NegocioAlertaException e) {
	    PopupConfirmacionBean.getBean().confirmar("consultarAvaluoBean.confirmarAprobacion", obtenerEtiqueta("titulo_confirmar_pre_aprobacion"), e.obtenerTexto(), null,
		    "consultarForm,errorForm");
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    public void setConfirmarAprobacion(String value) {
	try {
	    avaluoSvc.aprobar(seleccionados, getUsuario());
	    mensajeConfirmacion(obtenerEtiqueta("con_menAprobar"));
	    cantidadRegistros = avaluoSvc.cantidadRegistros(consulta);
	    listado.setRowCount(cantidadRegistros);
	    avaluoSvc.enviarCorreos(seleccionados, getUsuario());
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    public void replicar() {
	try {
	    if (seleccionados == null || seleccionados.isEmpty())
		throw mgrExc.lanzarExcepcion(34, TipoErrorNegocio.INFO);
	    avaluoSvc.replicar(seleccionados, getUsuario());
	    consultarAvaluo();
	    mensajeConfirmacion(obtenerEtiqueta("con_menReplicar"));
	    generarAlertasReplica(seleccionados);
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    private void generarAlertasReplica(List<Avaluo> replicados) throws NegocioException {
	NegocioException ex = mgrExc.lanzarExcepcion(71, TipoErrorNegocio.ALERTA);
	for (Avaluo avaluo : replicados) {
	    try {
		NegocioAlertaException alerta = avaluoSvc.consultarAlertasCopia(avaluo, null, getUsuario());
		if (alerta != null)
		    throw alerta;
	    } catch (NegocioException e) {
		ex.agregarError(e);
	    }
	}
	if (ex.getErrores() != null && !ex.getErrores().isEmpty()) {
	    throw ex;
	}
    }

    public void imprimir() {
	try {
	    if (seleccionados == null || seleccionados.isEmpty())
		throw mgrExc.lanzarExcepcion(34, TipoErrorNegocio.INFO);
	    ImpresionBean.getBean().imprimir(avaluoSvc.imprimir(seleccionados, true, getUsuario()));
	    impresos = new ArrayList<>();
	    for (Avaluo avaluo : seleccionados) {
		avaluo.setImpreso(true);
		impresos.add(avaluo);
	    }
	    imprimiendo = true;
	    mensajeConfirmacion(obtenerEtiqueta("con_menImprimir"));
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    public StreamedContent getCopiaSeguridad() throws NegocioException {
	try {
	    if (seleccionados == null || seleccionados.isEmpty())
		throw mgrExc.lanzarExcepcion(34, TipoErrorNegocio.INFO);
	    InputStream stream = null;
	    File archivo = avaluoSvc.crearCopiaSeguridad(seleccionados);
	    stream = new FileInputStream(archivo);
	    return new DefaultStreamedContent(stream, "application/zip", "copiaSeguridad_" + "registros_" + seleccionados.size() + ".zip");
	} catch (Exception e) {
	    procesarError(e);
	    return null;
	}
    }

    public void confirmarDescarga() {
	mensajeConfirmacion("Copia de seguridad creada, cantidad de registros " + seleccionados.size());
    }

    public void editar() {
	try {
	    AvaluoBean.getBean().editar(listado.getRowData());
	    try {
	    	RegistroFotograficoBean.getBean().getFotografias().clear();
	    }catch(Exception e) {
	    		    }
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    public void transcribir() {
	try {
	    PopupTranscribirBean.getBean().ver(listado.getRowData());
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    public void eliminar() {
	try {
	    PopupEliminacionBean.getBean().ver(listado.getRowData());
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    public void constructor() {
	try {
	    PopupConstructorBean.getBean().ver(listado.getRowData());
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    public void reversar() {
	try {
	    Avaluo avaluoReversar = listado.getRowData();
	    avaluoSvc.reversar(avaluoReversar, getUsuario());
	    consultarAvaluo();
	    mensajeConfirmacion(obtenerEtiqueta("con_menReversar", new String[] { avaluoReversar.getConsecutivoBanco().toString() }));
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    public void impresionErronea() {
	try {
	    Avaluo avaluoReversar = listado.getRowData();
	    avaluoSvc.impresionErronea(avaluoReversar, getUsuario());
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    public void generarReporteAvaluos() {
	try {
	    File reporte = avaluoSvc.generarReporteAvaluos(paginaActual,
		    SesionBean.getBean().getPermisosEspecificos() != null && SesionBean.getBean().getPermisosEspecificos().containsKey("ALERTAASEGURA"));
	    ImpresionBean.getBean().imprimir(reporte);
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    /*
     * getters y setters
     */

    public ConsultaAvaluoDto getConsulta() {
	return consulta;
    }

    public void setConsulta(ConsultaAvaluoDto consulta) {
	this.consulta = consulta;
    }

    public LazyDataModel<Avaluo> getListado() {
	return listado;
    }

    public void setListado(LazyDataModel<Avaluo> listado) {
	this.listado = listado;
    }

    public int getRegistroPagina() {
	return REGISTRO_PAGINA;
    }

    public boolean isVerFiltros() {
	return verFiltros;
    }

    public List<Avaluo> getSeleccionados() {
	return seleccionados;
    }

    public void setSeleccionados(List<Avaluo> seleccionados) {
	this.seleccionados = seleccionados;
    }

    public boolean isImprimiendo() {
	return imprimiendo;
    }

    public void setImprimiendo(boolean imprimiendo) {
	this.imprimiendo = imprimiendo;
    }

    public boolean isVerMapa() {
	return verMapa;
    }

    public boolean isVerTabla() {
	return verTabla;
    }

    public boolean isConsultaExitosa() {
	return consultaExitosa;
    }

    public Integer getCantidadRegistros() {
	return cantidadRegistros;
    }

    public void setCantidadRegistros(Integer cantidadRegistros) {
	this.cantidadRegistros = cantidadRegistros;
    }

}
