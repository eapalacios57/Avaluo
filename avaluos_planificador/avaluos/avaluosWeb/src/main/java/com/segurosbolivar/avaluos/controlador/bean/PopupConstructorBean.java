package com.segurosbolivar.avaluos.controlador.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.component.datatable.DataTable;

import com.asesoftware.util.cache.UtilCache;
import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioAlertaException;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilNumero;
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
import com.segurosbolivar.avaluos.modelo.entity.Dominios;
import com.segurosbolivar.avaluos.modelo.facade.IAvaluoFacade;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;

@ManagedBean(name = "constructorBean")
@SessionScoped
public class PopupConstructorBean extends PopupAbstractoBean implements Serializable{

	private static final long serialVersionUID = -2973158897168601553L;
	private static final String FORMULARIO = "constructorForm:tablaConstructor:";
	private Avaluo original;
	private List<Avaluo> copias;
	@EJB
	private transient IAvaluoFacade avaluoSvc;
	private transient DataTable tabla;
	private List<Dominios> listadoTipoIdentificacion;
	private Long codigoNombreConstructora;
	private String nombreConstructora;
	private boolean verNombreConstructora;
	private boolean verConstructora;
	private Map<String,ArrayList<String>> diccionarioDirecciones = new HashMap<String, ArrayList<String>>();

	/**
	 * Define la cantidad de registros por pagina
	 */
	private static final int REGISTRO_PAGINA = 20;

	public static PopupConstructorBean getBean() {
		return UtilJsf.obtenerBackingBean("constructorBean");
	}

	@Override
	public String getPopupId() {
		return "dlg_ConstructorPopup";
	}

	@Override
	@SuppressWarnings("unchecked")
	public void inicio() {
		try {
			listadoTipoIdentificacion = UtilCache.getInstance().obtenerLista(UtilConstantes.CACHE_AVALUOS,
					"TIPOIDENTIFICACION" + UtilConstantes.SUFIJO_DOMINIOS);
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void ver(Avaluo avaluoConstructor) throws NegocioException {
		copias = new ArrayList<>();
		if (avaluoConstructor == null)
			throw mgrExc.lanzarExcepcion(48, TipoErrorNegocio.ALERTA);
		if (avaluoConstructor.getEstadoAvaluo() == null
				|| avaluoConstructor.getEstadoAvaluo().compareTo(EstadoAvaluo.APROBADO.getValor()) != 0)
			throw mgrExc.lanzarExcepcion(51, TipoErrorNegocio.ALERTA);
		this.original = avaluoConstructor;
		verConstructora = original.getInformacionConstruccion() != null
				&& original.getInformacionConstruccion().getCodigoNombreConstructora() == null;
		verNombreConstructora = false;
		if (verConstructora) {
			codigoNombreConstructora = null;
			nombreConstructora = null;
		} else {
			codigoNombreConstructora = original.getInformacionConstruccion().getCodigoNombreConstructora();
			nombreConstructora = original.getInformacionConstruccion().getNombreConstructora();
		}
		abrir();
	}

	public void cambiarConstructora(ValueChangeEvent evento) {
		if (evento == null || evento.getNewValue() == null)
			return;
		Long valor = (Long) evento.getNewValue();
		verNombreConstructora = valor.compareTo(7L) == 0;
	}

	public void aceptarConstructora() {
		try {
			boolean validar = true;
			validar &= UtilValidadorJsf.validar(codigoNombreConstructora, "constructorForm:codigoNombreConstructora",
					true, 15, null);
			validar &= UtilValidadorJsf.validar(nombreConstructora, "constructorForm:nombreConstructora",
					codigoNombreConstructora != null && codigoNombreConstructora.compareTo(7L) == 0, 100, null);
			if (!validar)
				return;
			verNombreConstructora = false;
			verConstructora = false;
		} catch (Exception e) {
			procesarError(e);
		}

	}

	public void cambiarDireccion(ValueChangeEvent event) {
		String valor = (String) event.getNewValue();
		if (UtilTexto.estaVacio(valor))
			return;
		Avaluo avaluo = (Avaluo) tabla.getRowData();
		procesarDireccion(valor, avaluo, tabla.getRowIndex());
		((UIInput) event.getComponent()).setValue(avaluo.getDireccionInmueble());
	}

	public void cambiarDireccionComplementaria(ValueChangeEvent event) {
		String valor = (String) event.getNewValue();
		if (UtilTexto.estaVacio(valor))
			return;
		valor = avaluoSvc.transformarDireccionComplementaria(valor);
		Avaluo avaluo = (Avaluo) tabla.getRowData();
		avaluo.setDireccionComplementaria(valor);
		((UIInput) event.getComponent()).setValue(avaluo.getDireccionComplementaria());
	}

	public void procesarDireccion(String valor, Avaluo avaluo, int fila) {
		if (UtilTexto.estaVacio(valor)) {
			return;
		}
		String repuestaValor = avaluoSvc.transformarDireccion(valor, true);
		if (UtilTexto.estaVacio(repuestaValor)) {
			avaluo.setDireccionComplementaria(valor);
			UtilJsf.mostrarMensaje(FORMULARIO + fila + ":direccionInmueble", obtenerEtiqueta("men_direccionNoValida"));
			return;
		} else if (repuestaValor.contains("|")) {
			StringTokenizer token = new StringTokenizer(repuestaValor, "|");
			avaluo.setDireccionInmueble(token.nextToken());
			avaluo.setDireccionComplementaria(token.nextToken());
		} else
			avaluo.setDireccionInmueble(repuestaValor);
	}
	
	private void actualizarCoordenadas(Avaluo original, Avaluo copia) throws IOException, Exception {
		String latitudOriginal = original.getLatitudSFBUA();
    	String longitudOriginal = original.getLongitudSFBUA();
    	if (copia.getCodigoDaneDepartamento() == null) {
			Departamento departamento = ListaBean.getBean().obtenerDepartamento(original.getIdDepartamento(), null);
			copia.setCodigoDaneDepartamento(departamento.getCodDane());
		}

		if (copia.getCodigoDaneCiudad() == null) {
			Ciudad ciudad = ListaBean.getBean().obtenerCiudad(original.getIdCiudad(), null);
			copia.setCodigoDaneCiudad(ciudad.getCodDane());
		}

		if (copia.getCodigoDaneDepartamento() != null && copia.getCodigoDaneCiudad() != null) {
			ArrayList<String> respuesta = null;
			
			if (copia.getDireccionInmueble().isEmpty() && !copia.getDireccionComplementaria().isEmpty()) {
				respuesta = this.diccionarioDirecciones.get(copia.getDireccionComplementaria());
				if(respuesta == null) {
				respuesta = avaluoSvc.obtenerExtentCentroide(
						(copia.getCodigoDaneDepartamento() < 10 ? "0" : "") + copia.getCodigoDaneDepartamento(),
						(copia.getCodigoDaneDepartamento() < 10 ? "0" : "") + copia.getCodigoDaneCiudad(),
						copia.getDireccionComplementaria(), null);
				if(respuesta != null)
					this.diccionarioDirecciones.put(copia.getDireccionComplementaria(), respuesta);
				}
			} else if (!copia.getDireccionInmueble().isEmpty()) {
				respuesta = this.diccionarioDirecciones.get(copia.getDireccionInmueble());
				if(respuesta == null) {
				respuesta = avaluoSvc.obtenerExtentCentroide(
						(copia.getCodigoDaneDepartamento() < 10 ? "0" : "") + copia.getCodigoDaneDepartamento(),
						(copia.getCodigoDaneDepartamento() < 10 ? "0" : "") + copia.getCodigoDaneCiudad(),
						copia.getDireccionInmueble(), null);
				if(respuesta != null)
					this.diccionarioDirecciones.put(copia.getDireccionInmueble(), respuesta);
				}
			}
			boolean respuestaFallida = respuesta == null || respuesta.isEmpty()
					|| respuesta.get(0).equalsIgnoreCase("None") || respuesta.get(0).equalsIgnoreCase("Error");
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
	

	public void guardar() {
		try {
			if (!validar())
				throw mgrExc.lanzarExcepcion(22, TipoErrorNegocio.ALERTA);
			List<Avaluo> copiasAvaluos = new ArrayList<>();
			for(Avaluo copia:copias) {
				actualizarCoordenadas(original,copia);
				copiasAvaluos.add(copia);
			}
			avaluoSvc.guardarMultipleConstructor(copiasAvaluos, original, codigoNombreConstructora, nombreConstructora,getUsuario());
			ocultar();
			NegocioAlertaException alerta = avaluoSvc.consultarAlertasCopia(original, copiasAvaluos, getUsuario());
			if (alerta != null)
				throw alerta;
//			ConsultarAvaluoBean.getBean().consultarAvaluo();
			actualizar("consultarForm:seccionInformacion:tablaAvaluos");
			mensajeConfirmacion(obtenerEtiqueta("con_menConstructor", new String[] { original.getConsecutivoBanco().toString(), Integer.toString(copiasAvaluos.size()) }));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public boolean validar() throws NegocioException {
		boolean validar = true;
		int fila = 0;
		if (copias == null || copias.isEmpty())
			throw mgrExc.lanzarExcepcion(113, TipoErrorNegocio.ALERTA);
		for (Avaluo copia : copias) {
			validar &= validar(copia, fila++);
		}
		return validar;
	}

	public boolean validar(Avaluo copia, int fila) {
		boolean validar = true;
		validar &= UtilValidadorJsf.validar(copia.getNombreSolicitante(), FORMULARIO + fila + ":nombreSolicitante",
				true, 60, null);
		validar &= UtilValidadorJsf.validar(copia.getIdTipoIdentificacion(),
				FORMULARIO + fila + ":idTipoIdentificacion", true, 13, null);
		validar &= UtilValidadorJsf.validar(copia.getNumeroIdentificacion(),
				FORMULARIO + fila + ":numeroIdentificacion", true, 10, null);
		validar &= UtilValidadorJsf.validarFormato(copia.getDireccionInmueble(),
				FORMULARIO + fila + ":direccionInmueble", !UtilTexto.estaVacio(copia.getDireccionComplementaria()) ? false: true, 100, "^[a-zA-Z0-9 ]*$", null, " Ej:CR 13 34 99");
		validar &= UtilValidadorJsf.validar(copia.getDireccionComplementaria(),
				FORMULARIO + fila + ":direccionComplementaria", !UtilTexto.estaVacio(copia.getDireccionInmueble()) ? false: true, 100, null);
		
		validar &= UtilValidadorJsf.validar(copia.getTelefonoSolicitante(), FORMULARIO + fila + ":telefonoSolicitante",
				false, 7, null);
		validar &= UtilValidadorJsf.validar(copia.getCelularSolicitante(), FORMULARIO + fila + ":celularSolicitante",
				false, 10, null);
		validar &= UtilValidadorJsf.validarCorreo(copia.getCorreoSolicitante(),
				FORMULARIO + fila + ":correoSolicitante", false, 60, null);
		validar &= UtilValidadorJsf.validarFormato(copia.getMatriculaInmobiliariaPpal1(),
				FORMULARIO + fila + ":matriculaInmobiliariaPpal1", true, 20, AvaluosCons.FORMATO_MATRICULA, null,
				AvaluosCons.FORMATO_MATRICULA_MEN);
		validar &= UtilValidadorJsf.validarFormato(copia.getMatriculaInmobiliariaPpal2(),
				FORMULARIO + fila + ":matriculaInmobiliariaPpal2", false, 20, AvaluosCons.FORMATO_MATRICULA, null,
				AvaluosCons.FORMATO_MATRICULA_MEN);
		validar &= UtilValidadorJsf.validarFormato(copia.getMatriculaInmobiliariaGaraje1(),
				FORMULARIO + fila + ":matriculaInmobiliariaGaraje1", false, 20, AvaluosCons.FORMATO_MATRICULA, null,
				AvaluosCons.FORMATO_MATRICULA_MEN);
		validar &= UtilValidadorJsf.validarFormato(copia.getMatriculaInmobiliariaGaraje2(),
				FORMULARIO + fila + ":matriculaInmobiliariaGaraje2", false, 20, AvaluosCons.FORMATO_MATRICULA, null,
				AvaluosCons.FORMATO_MATRICULA_MEN);
		validar &= UtilValidadorJsf.validarFormato(copia.getMatriculaInmobiliariaDeposito1(),
				FORMULARIO + fila + ":matriculaInmobiliariaDeposito1", false, 20, AvaluosCons.FORMATO_MATRICULA, null,
				AvaluosCons.FORMATO_MATRICULA_MEN);
		return validar;
	}

	public void eliminarFila() {
		copias.remove(tabla.getRowData());
	}

	public void agregar() {
		copias.add(new Avaluo());
	}

	public void actualizarTabla() {
		Map<String, String> paramValues = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String valor = paramValues.get("data");
		StringTokenizer filas = new StringTokenizer(valor, "\n");
		int numeroFilas = 0;
		List<String> filasNoAgregadas = new ArrayList<>();
		while (filas.hasMoreTokens()) {
			String fila = filas.nextToken();
			String[] columnas = fila.split("\t");
			if (columnas.length > 12 || columnas.length < 8) {
				filasNoAgregadas.add(Integer.toString(++numeroFilas));
				continue;
			}
			Avaluo agregar = new Avaluo();
			copias.add(agregar);
			agregar.setNombreSolicitante(columnas[0].trim());
			agregar.setNumeroIdentificacion(UtilNumero.pasarEntero(columnas[2].trim()));
			agregar.setTelefonoSolicitante(UtilNumero.pasarEntero(columnas[4].trim()));
			agregar.setCelularSolicitante(UtilNumero.pasarEntero(columnas[5].trim()));
			agregar.setCorreoSolicitante(columnas[6].trim());
			agregar.setMatriculaInmobiliariaPpal1(columnas[7].trim());
			agregar.setMatriculaInmobiliariaPpal2(columnas.length > 8 ? columnas[8].trim() : "");
			agregar.setMatriculaInmobiliariaGaraje1(columnas.length > 9 ? columnas[9].trim() : "");
			agregar.setMatriculaInmobiliariaGaraje2(columnas.length > 10 ? columnas[10].trim() : "");
			agregar.setMatriculaInmobiliariaDeposito1(columnas.length > 11 ? columnas[11].trim() : "");
			int pocisionFilaTabla = copias.indexOf(agregar);
			try {
				Dominios tipoIdentificacion = obtenerTipoIdentificacion(columnas[1].trim());
				if (tipoIdentificacion != null)
					agregar.setIdTipoIdentificacion(UtilNumero.pasarEntero(tipoIdentificacion.getRvLowValue()));
				else
					UtilJsf.mostrarMensaje(FORMULARIO + pocisionFilaTabla + ":idTipoIdentificacion",
							obtenerEtiqueta("men_tipoIdentificaiconNoAbreviada"));
			} catch (Exception e) {
				UtilJsf.mostrarMensaje(FORMULARIO + pocisionFilaTabla + ":idTipoIdentificacion", e.getMessage());
			}
			procesarDireccion(columnas[3].trim(), agregar, pocisionFilaTabla);
			validar(agregar, pocisionFilaTabla);
			numeroFilas++;
		}
		if (!filasNoAgregadas.isEmpty())
			UtilJsf.mostrarMensaje(null, obtenerEtiqueta("men_filaNoProcesada",
					new String[] { UtilTexto.unificar(filasNoAgregadas, ", ") }));
	}

	private Dominios obtenerTipoIdentificacion(String abreviacion) {
		if (listadoTipoIdentificacion == null || listadoTipoIdentificacion.isEmpty())
			inicio();
		if (listadoTipoIdentificacion == null || listadoTipoIdentificacion.isEmpty())
			return null;
		for (Dominios comparar : listadoTipoIdentificacion) {
			if (comparar.getRvAbbreviation() != null && comparar.getRvAbbreviation().equalsIgnoreCase(abreviacion))
				return comparar;
		}
		return null;
	}

	/*
	 * getters y setters
	 */

	public DataTable getTabla() {
		return tabla;
	}

	public void setTabla(DataTable tabla) {
		this.tabla = tabla;
	}

	public Avaluo getOriginal() {
		return original;
	}

	public void setOriginal(Avaluo original) {
		this.original = original;
	}

	public List<Avaluo> getCopias() {
		return copias;
	}

	public void setCopias(List<Avaluo> copias) {
		this.copias = copias;
	}

	public int getRegistroPagina() {
		return REGISTRO_PAGINA;
	}

	public Long getCodigoNombreConstructora() {
		return codigoNombreConstructora;
	}

	public void setCodigoNombreConstructora(Long codigoNombreConstructora) {
		this.codigoNombreConstructora = codigoNombreConstructora;
	}

	public String getNombreConstructora() {
		return nombreConstructora;
	}

	public void setNombreConstructora(String nombreConstructora) {
		this.nombreConstructora = nombreConstructora;
	}

	public boolean isVerNombreConstructora() {
		return verNombreConstructora;
	}

	public boolean isVerConstructora() {
		return verConstructora;
	}

}
