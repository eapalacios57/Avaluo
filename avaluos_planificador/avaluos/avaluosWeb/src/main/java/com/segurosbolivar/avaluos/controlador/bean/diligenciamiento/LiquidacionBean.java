package com.segurosbolivar.avaluos.controlador.bean.diligenciamiento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.controlador.bean.general.GeneralAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.general.ListaBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilValidadorJsf;
import com.segurosbolivar.avaluos.modelo.entity.ArchivoPlano;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.LiquidacionAvaluo;
import com.segurosbolivar.avaluos.modelo.entity.LiquidacionAvaluoTotal;
import com.segurosbolivar.avaluos.modelo.facade.IAvaluoFacade;

/**
 * Controlador para la vista de la secci�n que permite realizar el calculo de la
 * liquidaci�n del valor total del aval�o. Hace parte del diligenciamiento.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:44 a.m.
 */
@ManagedBean
@SessionScoped
public class LiquidacionBean extends GeneralAbstractoBean implements Serializable{

    private static final long serialVersionUID = -4708088484446398240L;
    private static final long HORIZONTAL_DEFECTO = 1L;
    private static final long NO_HORIZONTAL_DEFECTO = 21L;
    private static final long NUMERO_FILAS = 10L;
    private static final int MAX_FILAS_INICIAL = 2;
    private Avaluo avaluo;
    private boolean ocultarAuditoria;
    private List<LiquidacionAvaluo> listado;
    private LiquidacionAvaluoTotal total;
    private List<SelectItem> listaDescLiq;
    private static final String MENSAJE_ESPECIFICO = "avaluoForm:errorEspecifico";

    @EJB
    public transient IAvaluoFacade avaluoFacade;

    public static LiquidacionBean getBean() {
	return UtilJsf.obtenerBackingBean("liquidacionBean");
    }

    @Override
    public void inicio() {
	listado = new ArrayList<>();

    }

    @Override
    public String getPermiso() {
	return null;
    }

    public void editar(Avaluo avaluo) {
	try {
	    if (avaluo == null) {
		throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
	    }
	    this.avaluo = avaluo;
	    listado = avaluoFacade.consultarLiquidacion(avaluo.getIdAvaluo());
	    LiquidacionAvaluoTotal entidad = avaluoFacade.consultarLiquidacionTotal(avaluo.getIdAvaluo());
	    if (entidad == null) {
		entidad = new LiquidacionAvaluoTotal();
		entidad.setIdAvaluo(avaluo.getIdAvaluo());
		entidad.setAvaluo(avaluo);
		if (getUsuario() != null && getUsuario().getUsuario() != null) {
		    entidad.setUsuarioCreacion(getUsuario().getUsuario().getCodigo());
		    entidad.setUsuarioTransaccion(getUsuario().getUsuario().getCodigo());
		}
		entidad.setFechaCreacion(new Date());
		entidad.setFechaTransaccion(new Date());
	    }
	    this.total = entidad;
	    if (listado == null || listado.isEmpty()) {
		listado = new ArrayList<>();
		agregar();
		agregar();
		agregar();

	    } else {
		List<LiquidacionAvaluo> listaLiquidacion = new ArrayList<>();
		for (LiquidacionAvaluo liquidacion : listado) {
		    if (liquidacion.esVacioSinConsecutivo() && listado.indexOf(liquidacion) > MAX_FILAS_INICIAL) {
			listaLiquidacion.add(liquidacion);
		    }
		}
		listado.removeAll(listaLiquidacion);
		// int numeroFilas = listado.size();
		// while(numeroFilas <= MAX_FILAS_INICIAL ) {
		// agregar();
		// numeroFilas++;
		// }
	    }
	    ocultarAuditoria = true;
	    Long propiedadHorizontal = InformacionConstruccionBean.getBean().getConstruccion() != null
		    && InformacionConstruccionBean.getBean().getConstruccion().getConstruccion().getPropiedadHorizontal() != null
			    ? InformacionConstruccionBean.getBean().getConstruccion().getConstruccion().getPropiedadHorizontal()
			    : 0;
	    cambiarPropiedadHorizontal(propiedadHorizontal);
	    listado.get(0).setDescripcionLiquidacion(propiedadHorizontal == 1 ? HORIZONTAL_DEFECTO : NO_HORIZONTAL_DEFECTO);

	    boolean avaluoAprobado = avaluo.isAprobado();

	    if (!avaluoAprobado) {
		BigDecimal uvr = avaluoFacade.consultaUvr();
		this.total.setValorUvrDia(uvr);
	    }

	} catch (Exception e) {
	    procesarError(e);
	}
    }

    public void cambiarPropiedadHorizontal(Long propiedadHorizontal) {
	if (propiedadHorizontal == null) {
	    listaDescLiq = ListaBean.getBean().cargarLista("DESCLIQUIDACION");
	    return;
	}
	listaDescLiq = ListaBean.getBean().cargarDependiente("DESCLIQUIDACION", propiedadHorizontal.toString());
    }

    public void agregar() {
	try {
	    LiquidacionAvaluo agregar = new LiquidacionAvaluo();
	    agregar.setAvaluo(avaluo);
	    agregar.setIdAvaluo(avaluo.getIdAvaluo());
	    if (listado.size() > NUMERO_FILAS) {
		throw mgrExc.lanzarExcepcion(0, TipoErrorNegocio.ALERTA);
	    } else {
		listado.add(agregar);
		sumaTotalAvaluo();
	    }
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    public boolean isPuedeAgregar() {
	return listado != null && listado.size() < NUMERO_FILAS;
    }

    public boolean isPuedeEliminar() {
	return listado != null && listado.size() > 1;
    }

    public boolean isEstadoAprobado() {
	return AvaluoBean.getBean().isEstadoAprobado();
    }

    public void eliminarFila(LiquidacionAvaluo filaLiquidacion) {
	if (listado == null || listado.size() <= 1)
	    return;
	listado.remove(filaLiquidacion);
	sumaTotalAvaluo();
	mensajeConfirmacion("Se elimino el registro");
    }

    public void anterior() {
	try {
	    AvaluoBean.getBean().setTab(4);
	    if (!isEditable())
		return;
	    guardar();
	} catch (Exception e) {
	    procesarError("avaluoForm:errorEspecifico", e);
	}
    }

    public void guardar() throws NegocioException {
	if (!validar())
	    throw mgrExc.lanzarExcepcion(22, TipoErrorNegocio.ALERTA);
	List<LiquidacionAvaluo> eliminar = new ArrayList<>();
	Long consecutivo = 0L;
	for (LiquidacionAvaluo liquidacionAvaluo : listado) {
	    if (liquidacionAvaluo.esVacioSinConsecutivo()) {
			  eliminar.add(liquidacionAvaluo);
	    }
	    
	    liquidacionAvaluo.setConsecutivo(consecutivo++);
	}
	listado.removeAll(eliminar);
	//llamar a eliminar primero
	avaluoFacade.eliminarLiq(total.getIdAvaluo());
	avaluoFacade.guardar(listado, total, getUsuario() );
	avaluo.setLiquidacionAvaluos(listado);
	avaluo.setLiquidacionTotal(total);
//	mensajeConfirmacion(obtenerEtiqueta("liq_menGuardar"));
	UtilJsf.mostrarMensaje(MENSAJE_ESPECIFICO, obtenerEtiqueta("liq_menGuardar"));
    }

    public void siguiente() {
	try {
	    AvaluoBean.getBean().setTab(6);
	    if (!isEditable())
		return;
	    guardar();
	} catch (Exception e) {
	    procesarError("avaluoForm:errorEspecifico", e);
	}
    }

    public boolean validar() {
	boolean validar = true;
	LiquidacionAvaluo liquidacion = listado != null && !listado.isEmpty() ? listado.get(0) : null;
	if (liquidacion != null) {
	    validar &= UtilValidadorJsf.validar(liquidacion.getDescripcionLiquidacion(),
		    "avaluoForm:edicionAvaluosTab:ResultData:" + listado.indexOf(liquidacion) + ":descripcionLiquidacion", true, 2, null);
	    validar &= UtilValidadorJsf.validar(liquidacion.getAreaLiquidacion(), "avaluoForm:edicionAvaluosTab:ResultData:" + listado.indexOf(liquidacion) + ":areaLiquidacion",
		    true, 7, 2, null);
	    validar &= UtilValidadorJsf.validar(liquidacion.getValor(), "avaluoForm:edicionAvaluosTab:ResultData:" + listado.indexOf(liquidacion) + ":valorLiquidacion", true, 10,
		    2, null);
	}
	if (!validar) {
	    AvaluoBean.getBean().setTab(5);
	}
	return validar;
    }

    public void obligatoriedad(List<ArchivoPlano> obligatorios) {
	if (obligatorios == null || obligatorios.isEmpty())
	    return;
	UtilValidadorJsf.validar(total.getValorUvrDia(), "avaluoForm:edicionAvaluosTab:valorUvrDia:", AvaluoBean.esObligatorio(obligatorios, 171L), 10, 2, null);
	UtilValidadorJsf.validar(total.getAvaluoUvr(), "avaluoForm:edicionAvaluosTab:avaluoUvr", AvaluoBean.esObligatorio(obligatorios, 122L), 10, 2, null);
	UtilValidadorJsf.validar(total.getCalificacion(), "avaluoForm:edicionAvaluosTab:calificacion", AvaluoBean.esObligatorio(obligatorios, 184L), 3, null);
	UtilValidadorJsf.validar(total.getValorAsegurable(), "avaluoForm:edicionAvaluosTab:valorAsegurable", AvaluoBean.esObligatorio(obligatorios, 160L), 10, 2, null);
	UtilValidadorJsf.validar(total.getTotalAvaluo(), "avaluoForm:edicionAvaluosTab:totalAvaluo", AvaluoBean.esObligatorio(obligatorios, 155L), 10, 2, null);
	if (listado == null || listado.isEmpty())
	    return;
	for (LiquidacionAvaluo liquidacion : listado) {
	    UtilValidadorJsf.validar(liquidacion.getDescripcionLiquidacion(), "avaluoForm:edicionAvaluosTab:ResultData:" + listado.indexOf(liquidacion) + ":descripcionLiquidacion",
		    AvaluoBean.esObligatorio(obligatorios, 14L), 2, null);
	    UtilValidadorJsf.validar(liquidacion.getDescripcionDependencia(), "avaluoForm:edicionAvaluosTab:ResultData:" + listado.indexOf(liquidacion) + ":descripcionLiquidacion",
		    AvaluoBean.esObligatorio(obligatorios, 246L), 30, null);
	    UtilValidadorJsf.validar(liquidacion.getAreaLiquidacion(), "avaluoForm:edicionAvaluosTab:ResultData:" + listado.indexOf(liquidacion) + ":areaLiquidacion",
		    AvaluoBean.esObligatorio(obligatorios, 112L), 7, 2, null);
	    UtilValidadorJsf.validar(liquidacion.getValor(), "avaluoForm:edicionAvaluosTab:ResultData:" + listado.indexOf(liquidacion) + ":valorLiquidacion",
		    AvaluoBean.esObligatorio(obligatorios, 172L), 10, 2, null);
	    UtilValidadorJsf.validar(liquidacion.getValorTotal(), "avaluoForm:edicionAvaluosTab:ResultData:" + listado.indexOf(liquidacion) + ":valorTotal",
		    AvaluoBean.esObligatorio(obligatorios, 161L), 10, 2, null);
	}
    }

    // calcular valor total
    public void calcularTotal(LiquidacionAvaluo liquidacion) {
	if (liquidacion.getAreaLiquidacion() == null || liquidacion.getValor() == null)
	    liquidacion.setValorTotal(BigDecimal.ZERO);
	else
	    liquidacion.setValorTotal(liquidacion.getAreaLiquidacion().multiply(liquidacion.getValor()));
	sumaTotalAvaluo();
    }

    private void sumaTotalAvaluo() {
	this.total.setTotalAvaluo(BigDecimal.ZERO);
	this.total.setAvaluoUvr(BigDecimal.ZERO);
	this.total.setValorAsegurable(BigDecimal.ZERO);
	if (listado == null || listado.isEmpty())
	    return;
	for (LiquidacionAvaluo liq : listado) {
	    if (liq.getValorTotal() == null)
		continue;
	    this.total.setTotalAvaluo(total.getTotalAvaluo().add(liq.getValorTotal()));
	    if (total.getValorUvrDia() != null && total.getValorUvrDia().compareTo(BigDecimal.ZERO) != 0)
		this.total.setAvaluoUvr(total.getTotalAvaluo().divide(total.getValorUvrDia(), 2, RoundingMode.HALF_UP));
	    this.total.setValorAsegurable(total.getTotalAvaluo());
	}
    }

    public boolean validarConcepto() {
	return false;
    }

    public void ocultar() {
	ocultarAuditoria = !ocultarAuditoria;
    }

    /*
     * getters y setters
     */

    public boolean isOcultarAuditoria() {
	return ocultarAuditoria;
    }

    public void setOcultarAuditoria(boolean ocultarAuditoria) {
	this.ocultarAuditoria = ocultarAuditoria;
    }

    public List<LiquidacionAvaluo> getListado() {
	return listado;
    }

    public void setListado(List<LiquidacionAvaluo> listado) {
	this.listado = listado;
    }

    public LiquidacionAvaluoTotal getTotal() {
	return total;
    }

    public void setTotal(LiquidacionAvaluoTotal total) {
	this.total = total;
    }

    public List<SelectItem> getListaDescLiq() {
	return listaDescLiq;
    }

    public void setListaDescLiq(List<SelectItem> listaDescLiq) {
	this.listaDescLiq = listaDescLiq;
    }
    
    @Override
    public boolean isEditable() {
    	return super.isEditable() && !AvaluoBean.getBean().isEstadoAprobado();
    }

}