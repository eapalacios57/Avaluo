package com.segurosbolivar.avaluos.controlador.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;


import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioAlertaException;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.cons.AvaluosCons;
import com.segurosbolivar.avaluos.controlador.bean.general.ListaBean;
import com.segurosbolivar.avaluos.controlador.bean.general.PopupAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilValidadorJsf;
import com.segurosbolivar.avaluos.modelo.cons.EstadoAvaluo;
import com.segurosbolivar.avaluos.modelo.entity.Avaluo;
import com.segurosbolivar.avaluos.modelo.entity.Ciudad;
import com.segurosbolivar.avaluos.modelo.entity.Departamento;
import com.segurosbolivar.avaluos.modelo.facade.IAvaluoFacade;

@ManagedBean(name = "transcribirBean")
@SessionScoped
public class PopupTranscribirBean extends PopupAbstractoBean implements Serializable{

	private static final long serialVersionUID = 4863375345645472859L;
	private Avaluo copia;
	private List<SelectItem> ciudades;
	private Avaluo original;
	
	
	@EJB
	private transient IAvaluoFacade avaluoSvc;

	public static PopupTranscribirBean getBean() {
		return UtilJsf.obtenerBackingBean("transcribirBean");
	}

	@Override
	public String getPopupId() {
		return "dlg_TranscribirPopup";
	}

	@Override
	public void inicio() {
		copia = new Avaluo();
		ciudades = new ArrayList<>();
	}

	public void ver(Avaluo avaluoTranscribir) throws NegocioException {
		if (avaluoTranscribir == null)
			throw mgrExc.lanzarExcepcion(48, TipoErrorNegocio.ALERTA);
		Avaluo avaluo = avaluoTranscribir;
		if (avaluo.getEstadoAvaluo() == null
				|| avaluo.getEstadoAvaluo().compareTo(EstadoAvaluo.APROBADO.getValor()) != 0)
			throw mgrExc.lanzarExcepcion(51, TipoErrorNegocio.ALERTA);
		this.original = avaluo;
		this.original.setMatriculaInmobiliariaPpal1(avaluo.getInformacionInmueble() != null
				? avaluo.getInformacionInmueble().getMatriculaInmobiliariaPpal1()
				: null);
		copia = avaluo.clonar();
		copia.setFechaAvaluo(new Date());
		ciudades = ListaBean.getBean().cargarCiudades(copia.getIdDepartamento());
		abrir();
	}

	public void cambiarDepartamento(ValueChangeEvent evento) {
		if (evento == null || evento.getNewValue() == null)
			return;
		Long valor = (Long) evento.getNewValue();
		if (valor == null) {
			ciudades = new ArrayList<>();
			copia.setCodigoDaneDepartamento(null);
			copia.setIdCiudad(null);
			copia.setCodigoDaneCiudad(null);
			return;
		}
		if (!valor.equals(evento.getOldValue())) {
			copia.setIdCiudad(null);
			copia.setCodigoDaneCiudad(null);
		}
		ciudades = ListaBean.getBean().cargarCiudades(valor);
		Departamento departamento = ListaBean.getBean().obtenerDepartamento(valor, null);
		if (departamento == null)
			return;
		copia.setCodigoDaneDepartamento(departamento.getCodDane());
	}

	public void cambiarCiudad(ValueChangeEvent evento) {
		Long valor = (Long) evento.getNewValue();
		if (valor == null) {
			copia.setCodigoDaneCiudad(null);
			return;
		}
		Ciudad ciudad = ListaBean.getBean().obtenerCiudad(valor, null);
		if (ciudad == null)
			return;
		copia.setCodigoDaneCiudad(ciudad.getCodDane());
	}

	public void cambiarDireccion(ValueChangeEvent event) throws IOException, Exception {
		String valor = (String) event.getNewValue();
		if (UtilTexto.estaVacio(valor))
			return;
		valor = avaluoSvc.transformarDireccion(valor, true);
		if (UtilTexto.estaVacio(valor)) {
			copia.setDireccionInmueble((String) event.getNewValue());
			UtilJsf.mostrarMensaje("transcribirForm:direccionInmueble", obtenerEtiqueta("men_direccionNoValida"));
		} else if (valor.contains("|")) {
			StringTokenizer token = new StringTokenizer(valor, "|");
			copia.setDireccionInmueble(token.nextToken().trim());
			copia.setDireccionComplementaria(token.nextToken().trim());
		} else
			copia.setDireccionInmueble(valor.trim());
		((UIInput) event.getComponent()).setValue(copia.getDireccionInmueble());
	}
	
	public void cambiarDireccion() throws IOException, Exception {
        String valor = copia.getDireccionInmueble();
        if (UtilTexto.estaVacio(valor))
                        return;
        valor = avaluoSvc.transformarDireccion(valor, true);
        if (UtilTexto.estaVacio(valor)) {                                 
                        UtilJsf.mostrarMensaje("transcribirForm:direccionInmueble", obtenerEtiqueta("men_direccionNoValida"));
        } else if (valor.contains("|")) {
                        StringTokenizer token = new StringTokenizer(valor, "|");
                        copia.setDireccionInmueble(token.nextToken().trim());
                        copia.setDireccionComplementaria(token.nextToken().trim());
        } else
                        copia.setDireccionInmueble(valor.trim());                                          
        
}


	public void cambiarDireccionComplementaria(ValueChangeEvent event) {
		String valor = (String) event.getNewValue();
		if (UtilTexto.estaVacio(valor))
			return;
		valor = avaluoSvc.transformarDireccionComplementaria(valor);
		if (UtilTexto.estaVacio(valor)) {
			copia.setDireccionComplementaria((String) event.getNewValue());
			UtilJsf.mostrarMensaje("transcribirForm:direccionComplementaria", obtenerEtiqueta("men_direccionNoValida"));
			return;
		} else if (valor.contains("|")) {
			StringTokenizer token = new StringTokenizer(valor, "|");
			token.nextToken();
			copia.setDireccionComplementaria(token.nextToken().trim());
		} else
			copia.setDireccionComplementaria(valor.trim());
		((UIInput) event.getComponent()).setValue(copia.getDireccionComplementaria());
	}

	public void cambiarConstructor() {
		try {
			if (copia.isConstructor())
				UtilJsf.mostrarMensaje("transcribirForm:errorEspecifico",
						obtenerEtiqueta("ina_menProyectoConstructor"));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void guardar() {
		try {
			if (!validar())
				throw mgrExc.lanzarExcepcion(22, TipoErrorNegocio.ALERTA);
			if (copia.getFechaAvaluo() == null)
			    copia.setFechaAvaluo(new Date());
			copia.generarConsecutivoBanco();
			
			if(copia.getCodigoDaneDepartamento()==null) {
				Departamento departamento = ListaBean.getBean().obtenerDepartamento(copia.getIdDepartamento(), null);
				copia.setCodigoDaneDepartamento(departamento.getCodDane());
			}
			
			if(copia.getCodigoDaneCiudad()==null) {
				Ciudad ciudad = ListaBean.getBean().obtenerCiudad(copia.getIdCiudad(), null);
				copia.setCodigoDaneCiudad(ciudad.getCodDane());
			}
			
			avaluoSvc.transcribir(copia, original, getUsuario());
			ocultar();
			ConsultarAvaluoBean.getBean().consultarAvaluo();
			mensajeConfirmacion(
					obtenerEtiqueta("con_menTranscribir", new String[] { copia.getConsecutivoBanco().toString() }));
			NegocioAlertaException alerta = avaluoSvc.consultarAlertasCopia(original, Collections.singletonList(copia),
					getUsuario());
			if (alerta != null)
				throw alerta;
		} catch (Exception e) {
			procesarError(e);
		}
	}


	public boolean validar() {
		boolean validar = true;
		validar &= UtilValidadorJsf.validar(copia.getNombreSolicitante(), "transcribirForm:nombreSolicitante", true, 60,
				null);
		validar &= UtilValidadorJsf.validar(copia.getIdTipoIdentificacion(), "transcribirForm:idTipoIdentificacion",
				true, 13, null);
		validar &= UtilValidadorJsf.validar(copia.getNumeroIdentificacion(), "transcribirForm:numeroIdentificacion",
				true, 10, null);
		validar &= UtilValidadorJsf.validar(copia.getFechaAvaluo(), "transcribirForm:fechaAvaluo", true, null);
		validar &= UtilValidadorJsf.validar(copia.getIdDepartamento(), "transcribirForm:idDepartamento", true, 13,
				null);
		validar &= UtilValidadorJsf.validar(copia.getIdCiudad(), "transcribirForm:idCiudad", true, 13, null);
		validar &= UtilValidadorJsf.validar(copia.getBarrio(), "transcribirForm:barrio", true, 30, null);
		validar &= UtilValidadorJsf.validar(copia.getConsecutivoBanco(), "transcribirForm:consecutivoBanco", true, 22,
				null);
		validar &= UtilValidadorJsf.validar(copia.getIdObjetoAvaluo(), "transcribirForm:idObjetoAvaluo", true, 22,
				null);
		validar &= UtilValidadorJsf.validarFormato(copia.getDireccionInmueble(), "transcribirForm:direccionInmueble", !UtilTexto.estaVacio(copia.getDireccionComplementaria()) ? false : true, 100, "^[a-zA-Z0-9 ]*$", null, " Ej:CR 13 34 99");
		validar &= UtilValidadorJsf.validar(copia.getDireccionComplementaria(),
				"transcribirForm:direccionComplementaria", !UtilTexto.estaVacio(copia.getDireccionInmueble()) ? false : true, 100, null);
		
		validar &= UtilValidadorJsf.validarFormato(copia.getMatriculaInmobiliariaPpal1(), "transcribirForm:matricula",
				true, 20, AvaluosCons.FORMATO_MATRICULA, null, AvaluosCons.FORMATO_MATRICULA_MEN);
		return validar;
	}

	/*
	 * getters y setters
	 */

	public Avaluo getCopia() {
		return copia;
	}

	public void setCopia(Avaluo copia) {
		this.copia = copia;
	}

	public List<SelectItem> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<SelectItem> ciudades) {
		this.ciudades = ciudades;
	}

}
