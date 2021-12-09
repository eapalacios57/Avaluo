package com.segurosbolivar.avaluos.controlador.bean.diligenciamiento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioAlertaException;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.cons.AvaluosCons;
import com.segurosbolivar.avaluos.controlador.bean.general.GeneralAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.general.ListaBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilValidadorJsf;
import com.segurosbolivar.avaluos.modelo.entity.ArchivoPlano;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.InformacionInmueble;
import com.segurosbolivar.avaluos.modelo.facade.IAvaluoFacade;

/**
 * Controlador para la vista de la secci�n informaci�n del inmueble, que hace
 * parte del diligenciamiento del aval�o.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:44 a.m.
 */
@ManagedBean(name = "inmuebleBean")
@SessionScoped
public class InformacionInmuebleBean extends GeneralAbstractoBean implements Serializable{

    private static final long serialVersionUID = -4039889234214597111L;
    private Avaluo avaluo;
    private boolean ocultarAuditoria;
    private InformacionInmueble inmueble;
    private List<SelectItem> ciudades;
    private boolean verMatriculaInmobiliaria;
    private int verMatriculaGaraje;
    private boolean verMatriculaDeposito;
    private static final String MENSAJE_ESPECIFICO = "avaluoForm:errorEspecifico";

    @EJB
    public transient IAvaluoFacade avaluoFacade;
    private boolean verUbicacion3;

    public static InformacionInmuebleBean getBean() {
	return UtilJsf.obtenerBackingBean("inmuebleBean");
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
	    InformacionInmueble entidad = avaluoFacade.consultarInmueble(avaluo.getIdAvaluo());
	    if (entidad == null) {
		entidad = new InformacionInmueble();
		entidad.setIdAvaluo(avaluo.getIdAvaluo());
		entidad.setAvaluo(avaluo);
		if (getUsuario() != null && getUsuario().getUsuario() != null) {
		    entidad.setUsuarioCreacion(getUsuario().getUsuario().getCodigo());
		    entidad.setUsuarioTransaccion(getUsuario().getUsuario().getCodigo());
		}
		entidad.setFechaCreacion(new Date());
		entidad.setFechaTransaccion(new Date());
	    }
	    this.inmueble = entidad;
	    ocultarAuditoria = true;
	    verUbicacion3 = inmueble.getIdCategoria() != null && inmueble.getIdCategoria().compareTo(6L) == 0;
	    ciudades = new ArrayList<>();
	    if (this.inmueble.getDepartamentoEscritura() != null)
		ciudades = ListaBean.getBean().cargarCiudades(this.inmueble.getDepartamentoEscritura());
	    verMatriculaDeposito = !UtilTexto.estaVacio(this.inmueble.getMatriculaInmobiliariaDeposito2());
	    verMatriculaInmobiliaria = !UtilTexto.estaVacio(this.inmueble.getMatriculaInmobiliariaPpal2());
	    if (!UtilTexto.estaVacio(this.inmueble.getMatriculaInmobiliariaGaraje5())) {
		verMatriculaGaraje = 4;
	    } else if (!UtilTexto.estaVacio(this.inmueble.getMatriculaInmobiliariaGaraje4())) {
		verMatriculaGaraje = 3;
	    } else if (!UtilTexto.estaVacio(this.inmueble.getMatriculaInmobiliariaGaraje3())) {
		verMatriculaGaraje = 2;
	    } else if (!UtilTexto.estaVacio(this.inmueble.getMatriculaInmobiliariaGaraje2())) {
		verMatriculaGaraje = 1;
	    } else {
		verMatriculaGaraje = 0;
	    }
	} catch (Exception e) {
	    procesarError(e);
	}
    }

    public void verDeposito() {
	verMatriculaDeposito = !verMatriculaDeposito;
	this.inmueble.setMatriculaInmobiliariaDeposito2(null);
    }

    public void verMatricula() {
	verMatriculaInmobiliaria = !verMatriculaInmobiliaria;
	this.inmueble.setMatriculaInmobiliariaPpal2(null);
    }

    public void verGaraje() {
	if (verMatriculaGaraje < 4) {
	    verMatriculaGaraje++;
	}
	if (verMatriculaGaraje == 4) {
	    this.inmueble.setMatriculaInmobiliariaGaraje5(null);
	} else if (verMatriculaGaraje == 3) {
	    this.inmueble.setMatriculaInmobiliariaGaraje4(null);
	} else if (verMatriculaGaraje == 2) {
	    this.inmueble.setMatriculaInmobiliariaGaraje3(null);
	} else if (verMatriculaGaraje == 1) {
	    this.inmueble.setMatriculaInmobiliariaGaraje2(null);
	}
    }

    public void quitarGaraje() {
	if (verMatriculaGaraje > 0)
	    verMatriculaGaraje--;
	if (verMatriculaGaraje == 3) {
	    this.inmueble.setMatriculaInmobiliariaGaraje5(null);
	} else if (verMatriculaGaraje == 2) {
	    this.inmueble.setMatriculaInmobiliariaGaraje4(null);
	} else if (verMatriculaGaraje == 1) {
	    this.inmueble.setMatriculaInmobiliariaGaraje3(null);
	} else if (verMatriculaGaraje == 0) {
	    this.inmueble.setMatriculaInmobiliariaGaraje2(null);
	}
    }

    public void cambiarDepartamento(ValueChangeEvent evento) {
	if (evento == null || evento.getNewValue() == null)
	    return;
	Long valor = (Long) evento.getNewValue();
	if (valor == null) {
	    ciudades = new ArrayList<>();
	    return;
	}
	ciudades = ListaBean.getBean().cargarCiudades(valor);
    }

    public void anterior() {
	try {
	    if (isEditable())
		guardar();
	    AvaluoBean.getBean().setTab(1);
	} catch (NegocioAlertaException e) {
	    if (e.getId().compareTo(22L) != 0)
		AvaluoBean.getBean().setTab(1);
	    procesarError("avaluoForm:errorEspecifico", e);
	} catch (Exception e) {
	    procesarError("avaluoForm:errorEspecifico", e);
	}
    }

    public void cambiarCategoria(ValueChangeEvent evento) {
	if (evento == null || evento.getNewValue() == null) {
	    inmueble.setUbicacion3(null);
	    verUbicacion3 = false;
	    return;
	}
	inmueble.setUbicacion3(null);
	Long valor = (Long) evento.getNewValue();
	verUbicacion3 = valor.compareTo(6L) == 0;
    }

    public void guardar() throws NegocioException {
	if (!validar())
	    throw mgrExc.lanzarExcepcion(22, TipoErrorNegocio.ALERTA);
	log.info("---Se inicia  la accion de guardar sec inmueble");
	avaluoFacade.guardar(inmueble, avaluo, getUsuario());
	log.info("---Se finaliza  la accion de guardar sec inmueble");
	avaluo.setInformacionInmueble(inmueble);
	InformacionConstruccionBean.getBean().editar(avaluo);
//	mensajeConfirmacion(obtenerEtiqueta("inm_menGuardar"));
	UtilJsf.mostrarMensaje(MENSAJE_ESPECIFICO, obtenerEtiqueta("inm_menGuardar"));
	log.info("---Se inicia  la accion de generar las alertas sec inmueble");
	NegocioAlertaException alerta = avaluoFacade.generarAlertasInformacionInmueble(inmueble, avaluo, getUsuario());
	log.info("---Se finaliza  la accion de generar las alertas sec inmueble");
	if (alerta != null)
	    throw alerta;
    }

    public void siguiente() {
	try {
	    if (!isEditable()) {
		AvaluoBean.getBean().setTab(3);
	    } else {
				guardar();
		AvaluoBean.getBean().setTab(3);
	    }
	} catch (NegocioAlertaException e) {
	    if (e.getId().compareTo(22L) != 0)
		AvaluoBean.getBean().setTab(3);
	    procesarError("avaluoForm:errorEspecifico", e);
	} catch (Exception e) {
	    procesarError("avaluoForm:errorEspecifico", e);
	}
    }

    public boolean validar() {
	boolean validar = true;
	validar &= UtilValidadorJsf.validar(inmueble.getTipoVivienda(), "avaluoForm:edicionAvaluosTab:tipoVivienda", false, 3, null);
	validar &= UtilValidadorJsf.validar(inmueble.getUsoInmueble(), "avaluoForm:edicionAvaluosTab:usoInmueble", true, 3, null);
	validar &= UtilValidadorJsf.validar(inmueble.getOtroUsoInmueble(), "avaluoForm:edicionAvaluosTab:otroUsoInmueble", false, 30, null);
	validar &= UtilValidadorJsf.validar(inmueble.getClaseInmueble(), "avaluoForm:edicionAvaluosTab:claseInmueble", false, 3, null);
	validar &= UtilValidadorJsf.validar(inmueble.getOtroClase(), "avaluoForm:edicionAvaluosTab:otroClase", false, 30, null);
	validar &= UtilValidadorJsf.validar(inmueble.getIdCategoria(), "avaluoForm:edicionAvaluosTab:idCategoria", true, 3, null);
	validar &= UtilValidadorJsf.validar(inmueble.getUbicacion2(), "avaluoForm:edicionAvaluosTab:ubicacion2", false, 3, null);
	validar &= UtilValidadorJsf.validar(inmueble.getUbicacion3(), "avaluoForm:edicionAvaluosTab:ubicacion3", false, 3, null);
	validar &= UtilValidadorJsf.validar(inmueble.getEdiContUso(), "avaluoForm:edicionAvaluosTab:ediContUso", false, 3, null);
	validar &= UtilValidadorJsf.validar(inmueble.getNotaria(), "avaluoForm:edicionAvaluosTab:notaria", false, 5, null);
	validar &= UtilValidadorJsf.validar(inmueble.getNumeroEscritura(), "avaluoForm:edicionAvaluosTab:numeroEscritura", false, 5, null);
	validar &= UtilValidadorJsf.validar(inmueble.getDepartamentoEscritura(), "avaluoForm:edicionAvaluosTab:departamentoEscritura", false, 13, null);
	validar &= UtilValidadorJsf.validar(inmueble.getCiudadEscritura(), "avaluoForm:edicionAvaluosTab:ciudadEscritura", false, 13, null);
	validar &= UtilValidadorJsf.validar(inmueble.getFechaEscritura(), "avaluoForm:edicionAvaluosTab:fechaEscritura", false, null);
	validar &= UtilValidadorJsf.validarFormato(inmueble.getMatriculaInmobiliariaPpal1(), "avaluoForm:edicionAvaluosTab:matriculaInmobiliariaPpal1", true, 20,
		AvaluosCons.FORMATO_MATRICULA, null, AvaluosCons.FORMATO_MATRICULA_MEN);
	validar &= UtilValidadorJsf.validarFormato(inmueble.getMatriculaInmobiliariaPpal2(), "avaluoForm:edicionAvaluosTab:matriculaInmobiliariaPpal2", false, 20,
		AvaluosCons.FORMATO_MATRICULA, null, AvaluosCons.FORMATO_MATRICULA_MEN);
	validar &= UtilValidadorJsf.validarFormato(inmueble.getMatriculaInmobiliariaGaraje1(), "avaluoForm:edicionAvaluosTab:matriculaInmobiliariaGaraje1", false, 20,
		AvaluosCons.FORMATO_MATRICULA, null, AvaluosCons.FORMATO_MATRICULA_MEN);
	validar &= UtilValidadorJsf.validarFormato(inmueble.getMatriculaInmobiliariaGaraje2(), "avaluoForm:edicionAvaluosTab:matriculaInmobiliariaGaraje2", false, 20,
		AvaluosCons.FORMATO_MATRICULA, null, AvaluosCons.FORMATO_MATRICULA_MEN);
	validar &= UtilValidadorJsf.validarFormato(inmueble.getMatriculaInmobiliariaGaraje3(), "avaluoForm:edicionAvaluosTab:matriculaInmobiliariaGaraje3", false, 20,
		AvaluosCons.FORMATO_MATRICULA, null, AvaluosCons.FORMATO_MATRICULA_MEN);
	validar &= UtilValidadorJsf.validarFormato(inmueble.getMatriculaInmobiliariaGaraje4(), "avaluoForm:edicionAvaluosTab:matriculaInmobiliariaGaraje4", false, 20,
		AvaluosCons.FORMATO_MATRICULA, null, AvaluosCons.FORMATO_MATRICULA_MEN);
	validar &= UtilValidadorJsf.validarFormato(inmueble.getMatriculaInmobiliariaGaraje5(), "avaluoForm:edicionAvaluosTab:matriculaInmobiliariaGaraje5", false, 20,
		AvaluosCons.FORMATO_MATRICULA, null, AvaluosCons.FORMATO_MATRICULA_MEN);
	validar &= UtilValidadorJsf.validarFormato(inmueble.getMatriculaInmobiliariaDeposito1(), "avaluoForm:edicionAvaluosTab:matriculaInmobiliariaDeposito1", false, 20,
		AvaluosCons.FORMATO_MATRICULA, null, AvaluosCons.FORMATO_MATRICULA_MEN);
	validar &= UtilValidadorJsf.validarFormato(inmueble.getMatriculaInmobiliariaDeposito2(), "avaluoForm:edicionAvaluosTab:matriculaInmobiliariaDeposito2", false, 20,
		AvaluosCons.FORMATO_MATRICULA, null, AvaluosCons.FORMATO_MATRICULA_MEN);
	validar &= UtilValidadorJsf.validar(inmueble.getChip(), "avaluoForm:edicionAvaluosTab:chip", isCiudadBogota(), 20, null);
	validar &= UtilValidadorJsf.validar(inmueble.getCatastralSF(), "avaluoForm:edicionAvaluosTab:catastralSF", false, 30, null);
	return validar;
    }

    public void obligatoriedad(List<ArchivoPlano> obligatorios) {
	if (obligatorios == null || obligatorios.isEmpty())
	    return;
	UtilValidadorJsf.validar(inmueble.getTipoVivienda(), "avaluoForm:edicionAvaluosTab:tipoVivienda", AvaluoBean.esObligatorio(obligatorios, 49L), 3, null);
	UtilValidadorJsf.validar(inmueble.getUsoInmueble(), "avaluoForm:edicionAvaluosTab:usoInmueble", AvaluoBean.esObligatorio(obligatorios, 54L), 3, null);
	UtilValidadorJsf.validar(inmueble.getOtroUsoInmueble(), "avaluoForm:edicionAvaluosTab:otroUsoInmueble", AvaluoBean.esObligatorio(obligatorios, 218L), 30, null);
	UtilValidadorJsf.validar(inmueble.getClaseInmueble(), "avaluoForm:edicionAvaluosTab:claseInmueble", AvaluoBean.esObligatorio(obligatorios, 10L), 3, null);
	UtilValidadorJsf.validar(inmueble.getOtroClase(), "avaluoForm:edicionAvaluosTab:otroClase", AvaluoBean.esObligatorio(obligatorios, 214L), 30, null);
	UtilValidadorJsf.validar(inmueble.getIdCategoria(), "avaluoForm:edicionAvaluosTab:idCategoria", AvaluoBean.esObligatorio(obligatorios, 234L), 3, null);
	UtilValidadorJsf.validar(inmueble.getUbicacion2(), "avaluoForm:edicionAvaluosTab:ubicacion2", AvaluoBean.esObligatorio(obligatorios, 53L), 3, null);
	UtilValidadorJsf.validar(inmueble.getUbicacion3(), "avaluoForm:edicionAvaluosTab:ubicacion3", AvaluoBean.esObligatorio(obligatorios, 221L), 3, null);
	UtilValidadorJsf.validar(inmueble.getEdiContUso(), "avaluoForm:edicionAvaluosTab:ediContUso", AvaluoBean.esObligatorio(obligatorios, 268L), 3, null);
	UtilValidadorJsf.validar(inmueble.getNotaria(), "avaluoForm:edicionAvaluosTab:notaria", AvaluoBean.esObligatorio(obligatorios, 210L), 5, null);
	UtilValidadorJsf.validar(inmueble.getNumeroEscritura(), "avaluoForm:edicionAvaluosTab:numeroEscritura", AvaluoBean.esObligatorio(obligatorios, 211L), 5, null);
	UtilValidadorJsf.validar(inmueble.getDepartamentoEscritura(), "avaluoForm:edicionAvaluosTab:departamentoEscritura", AvaluoBean.esObligatorio(obligatorios, 13L), 13, null);
	UtilValidadorJsf.validar(inmueble.getCiudadEscritura(), "avaluoForm:edicionAvaluosTab:ciudadEscritura", AvaluoBean.esObligatorio(obligatorios, 9L), 13, null);
	UtilValidadorJsf.validar(inmueble.getFechaEscritura(), "avaluoForm:edicionAvaluosTab:fechaEscritura", AvaluoBean.esObligatorio(obligatorios, 57L), null);
	UtilValidadorJsf.validarFormato(inmueble.getMatriculaInmobiliariaPpal1(), "avaluoForm:edicionAvaluosTab:matriculaInmobiliariaPpal1",
		AvaluoBean.esObligatorio(obligatorios, 199L), 20, AvaluosCons.FORMATO_MATRICULA, null, AvaluosCons.FORMATO_MATRICULA_MEN);
	UtilValidadorJsf.validarFormato(inmueble.getMatriculaInmobiliariaPpal2(), "avaluoForm:edicionAvaluosTab:matriculaInmobiliariaPpal2",
		AvaluoBean.esObligatorio(obligatorios, 200L), 20, AvaluosCons.FORMATO_MATRICULA, null, AvaluosCons.FORMATO_MATRICULA_MEN);
	UtilValidadorJsf.validarFormato(inmueble.getMatriculaInmobiliariaGaraje1(), "avaluoForm:edicionAvaluosTab:matriculaInmobiliariaGaraje1",
		AvaluoBean.esObligatorio(obligatorios, 203L), 20, AvaluosCons.FORMATO_MATRICULA, null, AvaluosCons.FORMATO_MATRICULA_MEN);
	UtilValidadorJsf.validarFormato(inmueble.getMatriculaInmobiliariaGaraje2(), "avaluoForm:edicionAvaluosTab:matriculaInmobiliariaGaraje2",
		AvaluoBean.esObligatorio(obligatorios, 204L), 20, AvaluosCons.FORMATO_MATRICULA, null, AvaluosCons.FORMATO_MATRICULA_MEN);
	UtilValidadorJsf.validarFormato(inmueble.getMatriculaInmobiliariaGaraje3(), "avaluoForm:edicionAvaluosTab:matriculaInmobiliariaGaraje3",
		AvaluoBean.esObligatorio(obligatorios, 205L), 20, AvaluosCons.FORMATO_MATRICULA, null, AvaluosCons.FORMATO_MATRICULA_MEN);
	UtilValidadorJsf.validarFormato(inmueble.getMatriculaInmobiliariaGaraje4(), "avaluoForm:edicionAvaluosTab:matriculaInmobiliariaGaraje4",
		AvaluoBean.esObligatorio(obligatorios, 206L), 20, AvaluosCons.FORMATO_MATRICULA, null, AvaluosCons.FORMATO_MATRICULA_MEN);
	UtilValidadorJsf.validarFormato(inmueble.getMatriculaInmobiliariaGaraje5(), "avaluoForm:edicionAvaluosTab:matriculaInmobiliariaGaraje5",
		AvaluoBean.esObligatorio(obligatorios, 207L), 20, AvaluosCons.FORMATO_MATRICULA, null, AvaluosCons.FORMATO_MATRICULA_MEN);
	UtilValidadorJsf.validarFormato(inmueble.getMatriculaInmobiliariaDeposito1(), "avaluoForm:edicionAvaluosTab:matriculaInmobiliariaDeposito1",
		AvaluoBean.esObligatorio(obligatorios, 201L), 20, AvaluosCons.FORMATO_MATRICULA, null, AvaluosCons.FORMATO_MATRICULA_MEN);
	UtilValidadorJsf.validarFormato(inmueble.getMatriculaInmobiliariaDeposito2(), "avaluoForm:edicionAvaluosTab:matriculaInmobiliariaDeposito2",
		AvaluoBean.esObligatorio(obligatorios, 202L), 20, AvaluosCons.FORMATO_MATRICULA, null, AvaluosCons.FORMATO_MATRICULA_MEN);
	UtilValidadorJsf.validar(inmueble.getChip(), "avaluoForm:edicionAvaluosTab:chip", AvaluoBean.esObligatorio(obligatorios, 191L), 20, null);
	UtilValidadorJsf.validar(inmueble.getCatastralSF(), "avaluoForm:edicionAvaluosTab:catastralSF", AvaluoBean.esObligatorio(obligatorios, 270L), 30, null);
    }

    public Long obtenerCategoria() {
	return inmueble != null && inmueble.getIdCategoria() != null ? inmueble.getIdCategoria() : 0L;
    }

    private boolean isCiudadBogota() {
    	return inmueble != null && inmueble.getCiudadEscritura() != null && avaluo.getIdCiudad() != null && avaluo.getIdCiudad().compareTo(2695L) == 0;
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

    public boolean isEstadoAprobado() {
	return AvaluoBean.getBean().isEstadoAprobado();
    }

    public void setAvaluo(Avaluo avaluo) {
	this.avaluo = avaluo;
    }

    public boolean isOcultarAuditoria() {
	return ocultarAuditoria;
    }

    public void setOcultarAuditoria(boolean ocultarAuditoria) {
	this.ocultarAuditoria = ocultarAuditoria;
    }

    public InformacionInmueble getInmueble() {
	return inmueble;
    }

    public void setInmueble(InformacionInmueble inmueble) {
	this.inmueble = inmueble;
    }

    public List<SelectItem> getCiudades() {
	return ciudades;
    }

    public void setCiudades(List<SelectItem> ciudades) {
	this.ciudades = ciudades;
    }

    public boolean isVerMatriculaInmobiliaria() {
	return verMatriculaInmobiliaria;
    }

    public int getVerMatriculaGaraje() {
	return verMatriculaGaraje;
    }

    public boolean isVerMatriculaDeposito() {
	return verMatriculaDeposito;
    }

    public boolean isVerUbicacion3() {
	return verUbicacion3;
    }
    
    @Override
    public boolean isEditable() {
    	return super.isEditable() && !AvaluoBean.getBean().isEstadoAprobado();
    }

}