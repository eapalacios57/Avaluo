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
import com.segurosbolivar.avaluos.modelo.entity.ArchivoPlano;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.ComportamientoOfertaDemanda;
import com.segurosbolivar.avaluos.modelo.facade.IAvaluoFacade;

/**
 * Controlador para la vista de la secci�n de oferta y demanda del aval�o. Hace
 * parte de la pantalla diligenciamiento.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:45 a.m.
 */
@ManagedBean(name = "ofertaBean")
@SessionScoped
public class OfertaDemandaBean extends GeneralAbstractoBean implements Serializable{

	private static final long serialVersionUID = 438058889572151701L;
	/**
	 * avaluo al que esta asociado la oferta y la demanda.
	 */
	private Avaluo avaluo;
	/**
	 * permite determinar si esa oculta o no la informacion respectiva de lso campos
	 * de auditoria.
	 */
	private boolean ocultarAuditoria;
	/**
	 * Registro de la oferta y la demanda de un avaluo, el cual se editara.
	 */
	private ComportamientoOfertaDemanda ofertaDemanda;
	
	private static final String MENSAJE_ESPECIFICO = "avaluoForm:errorEspecifico";
	/**
	 * implementacion del servicio para avaluos, que permite realizar las
	 * operaciones sobre el diligencimiaento del avaluo y su informacion sobre la
	 * oferta y la demanda.
	 */
	@EJB
	public transient IAvaluoFacade avaluoFacade;

	/**
	 * Permite obtener la instancia del controlador en ejecucion en el cliente.
	 * 
	 * @return instancia del bean actual.
	 */
	public static OfertaDemandaBean getBean() {
		return UtilJsf.obtenerBackingBean("ofertaBean");
	}

	@Override
	public void inicio() {

	}

	@Override
	public String getPermiso() {
		return null;
	}

	/**
	 * Permite inicializar la pestaña de oferta y demanda. Ya se para la edicion o
	 * creacion del registro respectivo.
	 * 
	 * @param oferta
	 *            a editar, en caso de que este vacia el registro se creara.
	 * @param avaluo
	 *            al que estara asociado la informacion de la oferta y la demanda.
	 */
	public void editar(Avaluo avaluo) {
		try {
			if (avaluo == null) {
				throw mgrExc.lanzarExcepcion(12, TipoErrorNegocio.ERROR);
			}
			this.avaluo = avaluo;
			ComportamientoOfertaDemanda entidad = avaluoFacade.consultarOferta(avaluo.getIdAvaluo());
			// en caso de que la informacion llegue vacia se creara el registro
			// apra su posterior almacenamiento. Se asocia el registro al avaluo
			if (entidad == null) {
				entidad = new ComportamientoOfertaDemanda();
				entidad.setIdAvaluo(avaluo.getIdAvaluo());
				entidad.setAvaluo(avaluo);
				if (getUsuario() != null && getUsuario().getUsuario() != null) {
					entidad.setUsuarioCreacion(getUsuario().getUsuario().getCodigo());
					entidad.setUsuarioTransaccion(getUsuario().getUsuario().getCodigo());
				}
				entidad.setFechaCreacion(new Date());
				entidad.setFechaTransaccion(new Date());
			}
			this.ofertaDemanda = entidad;
			// por defecto los campos de auditoria aparecen ocultos.
			ocultarAuditoria = true;
		} catch (Exception e) {
			procesarError(e);
		}
	}

	/**
	 * boton para seguir a la anterior pestaña del formulario de avaluo, se guarda
	 * automaticamente la informacion de la oferta y la demanda.
	 */
	public void anterior() {
		try {
			if (isEditable())
				guardar();
			AvaluoBean.getBean().setTab(3);
		} catch (Exception e) {
			procesarError("avaluoForm:errorEspecifico", e);
		}
	}

	/**
	 * realiza la operacion de guardar la infromacion de oferta y demanda del
	 * avaluo. previamente se valida que la informacion ingresada correspnda.
	 * 
	 * @throws NegocioException
	 *             en caso de que falle la validacion de campos o exista un error al
	 *             guardar la informacion respectiva.
	 */
	public void guardar() throws NegocioException {
		if (!validar())
			throw mgrExc.lanzarExcepcion(10, TipoErrorNegocio.ALERTA);
		avaluoFacade.guardar(ofertaDemanda, getUsuario());
		avaluo.setCompOfertaDemanda(ofertaDemanda);
//		mensajeConfirmacion(obtenerEtiqueta("ofe_menGuardar"));
		UtilJsf.mostrarMensaje(MENSAJE_ESPECIFICO, obtenerEtiqueta("ofe_menGuardar"));
	}

	/**
	 * boton para seguir a la siguiente pestaña del formulario de avaluo, se guarda
	 * automaticamente la informacion de la oferta y la demanda.
	 */
	public void siguiente() {
		try {
			if (isEditable())
				guardar();
			AvaluoBean.getBean().setTab(5);
		} catch (Exception e) {
			procesarError("avaluoForm:errorEspecifico", e);
		}
	}

	/***
	 * esta ventan permite validar los campos de la pantalla de oferta y demanda,
	 * validando su longitud y obligatorierad al igual que otras condiciones. Si las
	 * condiciones no se cumplen se generan los mensajes respectivos en la ventana y
	 * la operacion de guardar no podra realizarse.
	 * 
	 * @return verdadero en caso que todas las validaciones a campos respectivas
	 *         sean superadas.
	 */
	public boolean validar() {
		boolean validar = true;
		validar &= UtilValidadorJsf.validar(ofertaDemanda.getTipoComercializacion(),
				"avaluoForm:edicionAvaluosTab:tipoComercializacion", false, 5, null);
		validar &= UtilValidadorJsf.validar(ofertaDemanda.getActualidadEdificadora(),
				"avaluoForm:edicionAvaluosTab:actualidadEdificadora", false, 500, null);
		validar &= UtilValidadorJsf.validar(ofertaDemanda.getComportamiento(),
				"avaluoForm:edicionAvaluosTab:comportamiento", false, 500, null);
		return validar;
	}

	public void obligatoriedad(List<ArchivoPlano> obligatorios) {
		if (obligatorios == null || obligatorios.isEmpty())
			return;
		UtilValidadorJsf.validar(ofertaDemanda.getTipoComercializacion(),
				"avaluoForm:edicionAvaluosTab:tipoComercializacion", AvaluoBean.esObligatorio(obligatorios, 154L), 5,
				null);
		UtilValidadorJsf.validar(ofertaDemanda.getActualidadEdificadora(),
				"avaluoForm:edicionAvaluosTab:actualidadEdificadora", AvaluoBean.esObligatorio(obligatorios, 186L), 500,
				null);
		UtilValidadorJsf.validar(ofertaDemanda.getComportamiento(), "avaluoForm:edicionAvaluosTab:comportamiento",
				AvaluoBean.esObligatorio(obligatorios, 192L), 500, null);
	}

	/**
	 * Permite ocultar o mostrar la ventana de auditoria con los campos respectivos.
	 */
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

	public ComportamientoOfertaDemanda getOfertaDemanda() {
		return ofertaDemanda;
	}

	public void setOfertaDemanda(ComportamientoOfertaDemanda ofertaDemanda) {
		this.ofertaDemanda = ofertaDemanda;
	}
	
	@Override
	public boolean isEditable() {
		return AvaluoBean.getBean().isEditable();
	}

}