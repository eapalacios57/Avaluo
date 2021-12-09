package com.segurosbolivar.avaluos.controlador.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.controlador.bean.general.PopupAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilValidadorJsf;
import com.segurosbolivar.avaluos.modelo.cons.EstadoAvaluo;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.facade.IAvaluoFacade;

@ManagedBean(name = "eliminarBean")
@SessionScoped
public class PopupEliminacionBean extends PopupAbstractoBean implements Serializable{

	private static final long serialVersionUID = 4863375345645472859L;
	private transient Avaluo avaluo;
	@EJB
	private transient IAvaluoFacade avaluoSvc;

	public static PopupEliminacionBean getBean() {
		return UtilJsf.obtenerBackingBean("eliminarBean");
	}

	@Override
	public String getPopupId() {
		return "dlg_EliminarPopup";
	}

	@Override
	public void inicio() {
		// no se va usar el metodo debido que no es necesario que se inicialicen
		// variables o procesos una vez se inicie la ventana.
	}

	public void ver(Avaluo avaluoEliminar) throws NegocioException {
		try {
			if (avaluoEliminar == null)
				throw mgrExc.lanzarExcepcion(53, TipoErrorNegocio.ALERTA);
//			this.avaluo = avaluoSvc.consultarAvaluo(idAvaluo);
			this.avaluo = avaluoEliminar;
			if (this.avaluo.getEstadoAvaluo() != null
					&& this.avaluo.getEstadoAvaluo().compareTo(EstadoAvaluo.ELIMINADO.getValor()) == 0)
				throw mgrExc.lanzarExcepcion(54, TipoErrorNegocio.ALERTA);
			abrir();
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void eliminar() {
		try {
			if (!validar())
				throw mgrExc.lanzarExcepcion(22, TipoErrorNegocio.ALERTA);
			avaluoSvc.eliminar(avaluo, getUsuario());
			ocultar();
			ConsultarAvaluoBean.getBean().consultarAvaluo();
			mensajeConfirmacion(
					obtenerEtiqueta("con_menEliminar", new String[] { avaluo.getConsecutivoBanco().toString() }));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public boolean validar() {
		boolean validar = true;
		validar &= UtilValidadorJsf.validar(avaluo.getCodigoMotivoEliminacion(), "eliminarForm:codigoMotivoEliminacion",
				true, 3, null);
		return validar;
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

}
