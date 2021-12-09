package com.segurosbolivar.avaluos.controlador.bean.diligenciamiento;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.controlador.bean.general.GeneralAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilValidadorJsf;
import com.segurosbolivar.avaluos.dto.InformacionBarrioDto;
import com.segurosbolivar.avaluos.modelo.entity.ArchivoPlano;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.InformacionBarrio;
import com.segurosbolivar.avaluos.modelo.facade.IAvaluoFacade;

/**
 * Controlador para la vista de la secci�n informaci�n del barrio, que hace
 * parte del diligenciamiento del aval�o.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:43 a.m.
 */
@ManagedBean(name = "barrioBean")
@SessionScoped
public class InformacionBarrioBean extends GeneralAbstractoBean implements Serializable{

    private static final long serialVersionUID = 1006523986047719440L;
    private Avaluo avaluo;
    private boolean ocultarAuditoria;
    private InformacionBarrioDto barrio;
    private static final String MENSAJE_ESPECIFICO = "avaluoForm:errorEspecifico";

    @EJB
    public transient IAvaluoFacade avaluoFacade;

    public static InformacionBarrioBean getBean() {
	return UtilJsf.obtenerBackingBean("barrioBean");
    }

    @Override
    public void inicio() {

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
	    ocultarAuditoria = true;
	    InformacionBarrio entidad = avaluoFacade.consultarBarrio(avaluo.getIdAvaluo());
	    if (entidad == null) {
		entidad = new InformacionBarrio();
		entidad.setIdAvaluo(avaluo.getIdAvaluo());
		entidad.setAvaluo(avaluo);
		if (getUsuario() != null && getUsuario().getUsuario() != null) {
		    entidad.setUsuarioCreacion(getUsuario().getUsuario().getCodigo());
		    entidad.setUsuarioTransaccion(getUsuario().getUsuario().getCodigo());
		}
		entidad.setFechaCreacion(new Date());
		entidad.setFechaTransaccion(new Date());
	    }
	    this.barrio = new InformacionBarrioDto(entidad);
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    public void cambiarOtrosUsos() {
	barrio.getBarrio().setTextoOtrosUsos(null);
    }

    public void anterior() {
	try {
	    if (isEditable())
		guardar();
	    AvaluoBean.getBean().setTab(0);
	} catch (Exception e) {
	    procesarError("avaluoForm:errorEspecifico", e);
	}
    }

    public void guardar() throws NegocioException {
	if (!validar())
	    throw mgrExc.lanzarExcepcion(22, TipoErrorNegocio.ALERTA);
	avaluoFacade.guardar(barrio.getBarrio(), getUsuario());
	avaluo.setInformacionBarrio(barrio.getBarrio());
//	mensajeConfirmacion(obtenerEtiqueta("inb_menGuardar"));
	UtilJsf.mostrarMensaje(MENSAJE_ESPECIFICO, obtenerEtiqueta("inb_menGuardar"));
    }

    public boolean validar() {
	boolean validar = true;
	validar &= UtilValidadorJsf.validar(barrio.getBarrio().getTextoOtrosUsos(), "avaluoForm:edicionAvaluosTab:textoOtrosUsos", false, 30, null);
	validar &= UtilValidadorJsf.validar(barrio.getBarrio().getEdificacionesIguales(), "avaluoForm:edicionAvaluosTab:edificacionesIguales", false, 5, null);
	validar &= UtilValidadorJsf.validar(barrio.getBarrio().getEstadosViaAcceso(), "avaluoForm:edicionAvaluosTab:estadosViaAcceso", false, 3, null);
	validar &= UtilValidadorJsf.validar(barrio.getBarrio().getEstrato(), "avaluoForm:edicionAvaluosTab:estrato", false, 3, null);
	validar &= UtilValidadorJsf.validar(barrio.getBarrio().getTopografia(), "avaluoForm:edicionAvaluosTab:topografia", false, 3, null);
	validar &= UtilValidadorJsf.validar(barrio.getBarrio().getLegalidad(), "avaluoForm:edicionAvaluosTab:legalidad", false, 3, null);
	validar &= UtilValidadorJsf.validar(barrio.getBarrio().getTransporte(), "avaluoForm:edicionAvaluosTab:transporte", false, 3, null);
	validar &= UtilValidadorJsf.validar(barrio.getBarrio().getPerspectivas(), "avaluoForm:edicionAvaluosTab:perspectivas", false, 500, null);
	return validar;
    }

    public void obligatoriedad(List<ArchivoPlano> obligatorios) {
	if (obligatorios == null || obligatorios.isEmpty())
	    return;
	UtilValidadorJsf.validar(barrio.getBarrio().getSardeneles(), "avaluoForm:edicionAvaluosTab:sardeneles", AvaluoBean.esObligatorio(obligatorios, 47L), 3, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getEdificacionesIguales(), "avaluoForm:edicionAvaluosTab:edificacionesIguales", AvaluoBean.esObligatorio(obligatorios, 266L), 5,
		null);
	UtilValidadorJsf.validar(barrio.getBarrio().getTransporte(), "avaluoForm:edicionAvaluosTab:transporte", AvaluoBean.esObligatorio(obligatorios, 51L), 3, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getAcueductoPredio(), "avaluoForm:edicionAvaluosTab:acueductoPredio", AvaluoBean.esObligatorio(obligatorios, 59L), 3, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getAcueductoSector(), "avaluoForm:edicionAvaluosTab:acueductoSector", AvaluoBean.esObligatorio(obligatorios, 60L), 3, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getAlamedas(), "avaluoForm:edicionAvaluosTab:alamedas", AvaluoBean.esObligatorio(obligatorios, 62L), 3, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getAlcantarilladoPredio(), "avaluoForm:edicionAvaluosTab:alcantarilladoPredio", AvaluoBean.esObligatorio(obligatorios, 63L), 3,
		null);
	UtilValidadorJsf.validar(barrio.getBarrio().getAlcantarilladoSector(), "avaluoForm:edicionAvaluosTab:alcantarilladoSector", AvaluoBean.esObligatorio(obligatorios, 64L), 3,
		null);
	UtilValidadorJsf.validar(barrio.getBarrio().getAlumbrado(), "avaluoForm:edicionAvaluosTab:alumbrado", AvaluoBean.esObligatorio(obligatorios, 65L), 3, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getArborizacion(), "avaluoForm:edicionAvaluosTab:alumbrado", AvaluoBean.esObligatorio(obligatorios, 69L), 3, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getCicloRutas(), "avaluoForm:edicionAvaluosTab:cicloRutas", AvaluoBean.esObligatorio(obligatorios, 75L), 3, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getComercio(), "avaluoForm:edicionAvaluosTab:comercio", AvaluoBean.esObligatorio(obligatorios, 78L), 3, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getElectricidadPredio(), "avaluoForm:edicionAvaluosTab:electricidadPredio", AvaluoBean.esObligatorio(obligatorios, 79L), 3,
		null);
	UtilValidadorJsf.validar(barrio.getBarrio().getElectricidadSector(), "avaluoForm:edicionAvaluosTab:electricidadSector", AvaluoBean.esObligatorio(obligatorios, 80L), 3,
		null);
	UtilValidadorJsf.validar(barrio.getBarrio().getGasPredio(), "avaluoForm:edicionAvaluosTab:gasPredio", AvaluoBean.esObligatorio(obligatorios, 85L), 3, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getGasSector(), "avaluoForm:edicionAvaluosTab:gasSector", AvaluoBean.esObligatorio(obligatorios, 86L), 3, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getIndustria(), "avaluoForm:edicionAvaluosTab:industria", AvaluoBean.esObligatorio(obligatorios, 90L), 3, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getOtrosUsos(), "avaluoForm:edicionAvaluosTab:otrosUsos", AvaluoBean.esObligatorio(obligatorios, 93L), 30, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getParadero(), "avaluoForm:edicionAvaluosTab:paradero", AvaluoBean.esObligatorio(obligatorios, 94L), 30, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getTextoOtrosUsos(), "avaluoForm:edicionAvaluosTab:textoOtrosUsos", AvaluoBean.esObligatorio(obligatorios, 217L), 30, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getPerspectivas(), "avaluoForm:edicionAvaluosTab:perspectivas", AvaluoBean.esObligatorio(obligatorios, 219L), 500, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getTelefonoPredio(), "avaluoForm:edicionAvaluosTab:telefonoPredio", AvaluoBean.esObligatorio(obligatorios, 107L), 30, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getTelefonoSector(), "avaluoForm:edicionAvaluosTab:telefonoSector", AvaluoBean.esObligatorio(obligatorios, 108L), 30, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getVivienda(), "avaluoForm:edicionAvaluosTab:vivienda", AvaluoBean.esObligatorio(obligatorios, 109L), 30, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getZonasVerdes(), "avaluoForm:edicionAvaluosTab:zonasVerdes", AvaluoBean.esObligatorio(obligatorios, 110L), 30, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getAndenes(), "avaluoForm:edicionAvaluosTab:andenes", AvaluoBean.esObligatorio(obligatorios, 1L), 30, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getEstrato(), "avaluoForm:edicionAvaluosTab:estrato", AvaluoBean.esObligatorio(obligatorios, 31L), 3, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getEstadosViaAcceso(), "avaluoForm:edicionAvaluosTab:estadosViaAcceso", AvaluoBean.esObligatorio(obligatorios, 34L), 3, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getLegalidad(), "avaluoForm:edicionAvaluosTab:legalidad", AvaluoBean.esObligatorio(obligatorios, 42L), 3, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getPavimentada(), "avaluoForm:edicionAvaluosTab:pavimentada", AvaluoBean.esObligatorio(obligatorios, 43L), 3, null);
	UtilValidadorJsf.validar(barrio.getBarrio().getTopografia(), "avaluoForm:edicionAvaluosTab:topografia", AvaluoBean.esObligatorio(obligatorios, 50L), 3, null);
    }

    public void siguiente() {
	try {
	    if (!isEditable()) {
		AvaluoBean.getBean().setTab(2);
	    } else {
		guardar();
		AvaluoBean.getBean().setTab(2);
	    }
	} catch (Exception e) {
	    procesarError("avaluoForm:errorEspecifico", e);
	}
    }

    public void ocultar() {
	ocultarAuditoria = !ocultarAuditoria;
    }

    /*
     * getters y setters
     */

    public Avaluo getAvaluo() {
	return avaluo;
    }

    public void setAvaluo(Avaluo avaluo) {
	this.avaluo = avaluo;
    }

    public boolean isEstadoAprobado() {
	return AvaluoBean.getBean().isEstadoAprobado();
    }

    public InformacionBarrioDto getBarrio() {
	return barrio;
    }

    public void setBarrio(InformacionBarrioDto barrio) {
	this.barrio = barrio;
    }

    public boolean isOcultarAuditoria() {
	return ocultarAuditoria;
    }

    public void setOcultarAuditoria(boolean ocultarAuditoria) {
	this.ocultarAuditoria = ocultarAuditoria;
    }
    
    @Override
    public boolean isEditable() {
    	return super.isEditable() && !AvaluoBean.getBean().isEstadoAprobado();
    }

}