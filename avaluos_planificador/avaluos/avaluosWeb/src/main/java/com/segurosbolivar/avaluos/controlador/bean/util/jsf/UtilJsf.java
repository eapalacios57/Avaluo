package com.segurosbolivar.avaluos.controlador.bean.util.jsf;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.time.DateUtils;
import org.jfree.util.Log;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.component.selectoneradio.SelectOneRadio;
import org.primefaces.context.RequestContext;
import org.primefaces.model.SortOrder;

import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.modelo.cons.SentidoOrientacion;

/**
 * Esta utilidad provee funcionalidades para realizar operaciones que implican
 * la capa de JSF del proyecto web. Como las reglas de navegación o la creación
 * de una sesion web.
 * 
 * @author stilaguy
 * @version 1.
 * @created 31-ago-2017 10:30:47 a.m.
 */
public final class UtilJsf implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5441076753811170561L;

	private UtilJsf() {

	}

	/**
	 * 
	 * @param mensaje
	 * @return
	 */
	public static void mensajeGlobal(NegocioException error) {
		mostrarMensaje(null, error);
	}

	/**
	 * 
	 * @param formulario
	 * @param mensaje
	 */
	public static void mostrarMensaje(String formulario, NegocioException error) {
		Severity severidad = null;
		switch (error.getTipo()) {
		case FATAL:
			severidad = FacesMessage.SEVERITY_FATAL;
			formulario = null;
			break;
		case ERROR:
			severidad = FacesMessage.SEVERITY_ERROR;
			formulario = null;
			break;
		case ALERTA:
			severidad = FacesMessage.SEVERITY_WARN;
			break;
		default:
			severidad = FacesMessage.SEVERITY_INFO;
			break;
		}
		FacesContext.getCurrentInstance().addMessage(formulario,
				new FacesMessage(severidad, error.getMessage(), error.getDetalle()));

	}

	public static void mostrarMensaje(Exception e) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
	}

	public static void mostrarMensaje(String ubicacionFormulario, String mensaje) {
		FacesContext.getCurrentInstance().addMessage(ubicacionFormulario,
				new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, mensaje));
	}

	public static void mostrarMensaje(String ubicacionFormulario, String mensaje, FacesMessage.Severity severidad,
			String detalleMensaje) {
		FacesContext.getCurrentInstance().addMessage(ubicacionFormulario,
				new FacesMessage(severidad == null ? FacesMessage.SEVERITY_INFO : severidad, mensaje, detalleMensaje));
	}

	public static void mostrarMensajeError(String ubicacionFormulario, String mensaje) {
		FacesContext.getCurrentInstance().addMessage(ubicacionFormulario,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje));
	}

	/***
	 * Permite obtener una instancia de un backing bean determinado a traves del
	 * nombre del mismo.
	 * 
	 * @param nombre
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T obtenerBackingBean(String nombre) {
		FacesContext context = FacesContext.getCurrentInstance();
		return (T) context.getApplication().evaluateExpressionGet(context, expresionEl(nombre), Object.class);
	}

	public static String expresionEl(String texto) {
		if (UtilTexto.estaVacio(texto))
			return "";
		StringBuilder retorno = new StringBuilder("#{");
		retorno.append(texto);
		retorno.append("}");
		return retorno.toString();
	}

	public static void agregarEstilo(String ubicacionFormulario, String estiloAdicionar) {
		UIComponent componente = FacesContext.getCurrentInstance().getViewRoot().findComponent(ubicacionFormulario);
		if (componente == null)
			return;
		if (componente instanceof HtmlInputText) {
			HtmlInputText input = (HtmlInputText) componente;
			if (input.getStyleClass() == null)
				return;
			input.setStyleClass(input.getStyleClass() + " " + estiloAdicionar);
		} else if (componente instanceof HtmlInputTextarea) {
			HtmlInputTextarea input = (HtmlInputTextarea) componente;
			if (input.getStyleClass() == null)
				return;
			input.setStyleClass(input.getStyleClass() + " " + estiloAdicionar);
		} else if (componente instanceof SelectOneMenu) {
			SelectOneMenu input = (SelectOneMenu) componente;
			if (input.getStyleClass() == null)
				return;
			input.setStyleClass(input.getStyleClass() + " " + estiloAdicionar);
		} else if (componente instanceof SelectOneRadio) {
			SelectOneRadio input = (SelectOneRadio) componente;
			if (input.getStyleClass() == null)
				return;
			input.setStyleClass(input.getStyleClass() + " " + estiloAdicionar);
		} else if (componente instanceof SelectBooleanCheckbox) {
			SelectBooleanCheckbox input = (SelectBooleanCheckbox) componente;
			if (input.getStyleClass() == null)
				return;
			input.setStyleClass(input.getStyleClass() + " " + estiloAdicionar);
		}
	}

	public static void quitarEstilo(String ubicacionFormulario, String estiloAdicionar) {
		UIComponent componente = FacesContext.getCurrentInstance().getViewRoot().findComponent(ubicacionFormulario);
		if (componente == null)
			return;
		if (componente instanceof HtmlInputText) {
			HtmlInputText input = (HtmlInputText) componente;
			if (input.getStyleClass() == null)
				return;
			input.setStyleClass(input.getStyleClass().replaceAll(estiloAdicionar, ""));
		} else if (componente instanceof HtmlInputTextarea) {
			HtmlInputTextarea input = (HtmlInputTextarea) componente;
			if (input.getStyleClass() == null)
				return;
			input.setStyleClass(input.getStyleClass().replaceAll(estiloAdicionar, ""));
		} else if (componente instanceof SelectOneMenu) {
			SelectOneMenu input = (SelectOneMenu) componente;
			if (input.getStyleClass() == null)
				return;
			input.setStyleClass(input.getStyleClass().replaceAll(estiloAdicionar, ""));
		} else if (componente instanceof SelectOneRadio) {
			SelectOneRadio input = (SelectOneRadio) componente;
			if (input.getStyleClass() == null)
				return;
			input.setStyleClass(input.getStyleClass().replaceAll(estiloAdicionar, ""));
		} else if (componente instanceof SelectBooleanCheckbox) {
			SelectBooleanCheckbox input = (SelectBooleanCheckbox) componente;
			if (input.getStyleClass() == null)
				return;
			input.setStyleClass(input.getStyleClass().replaceAll(estiloAdicionar, ""));
		}

	}

	/**
	 * Permite aplicar un valor a un parametro existente en un backingBean
	 * 
	 * @param destino
	 *            : ruta del valor al que se aplicara el parametro.
	 * @param parametro
	 *            : valor a aplicar al parametro.
	 */
	public static void aplicarParametro(String destino, Object parametro) {
		FacesContext fc = FacesContext.getCurrentInstance();
		ValueExpression ve = fc.getApplication().getExpressionFactory().createValueExpression(fc.getELContext(),
				expresionEl(destino), Object.class);
		ve.setValue(fc.getELContext(), parametro);
	}

	public static void agregarParametroSesion(String key, Object value) {
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getExternalContext().getSessionMap().put(key, value);
	}

	public static Object obtenerParametroSesion(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
	}

	public static Object borrarParametroSesion(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
	}

	public static Object obtenerParametro(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
	}

	/**
	 * Permite crear una sesion y guardar un parametro dentro de la misma.
	 * 
	 * @param nombre:
	 *            parametro a guardar.
	 * @param valor:
	 *            a guardar asociado a la sesion.
	 * @throws Exception
	 */
	public static void crearSession(String nombre, Serializable valor) {
		Log.info("Entra al metodo de crear sesion");
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute(nombre, valor);
		Log.info("Se crea sesion para el usuario con credencial "+nombre);
	}

	/**
	 * Permite cerrar la sesion.
	 */
	public static void cerrarSession() {
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
	}

	/**
	 * permite navegar dentro del aplicativo haciendo uso de una regla de
	 * navegacion.
	 * 
	 * @param accion
	 */
	public static void navegar(String accion) {
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getApplication().getNavigationHandler().handleNavigation(fc, null, accion);
	}

	/**
	 * Permite navegar a la aplicacion hacia una ruta sin necesidad de encontrarse
	 * dentro de un backing bean.
	 * 
	 * @param accion
	 * @throws IOException
	 */
	public static void navegarFueraContexto(String accion) throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect(((ServletContext) externalContext.getContext()).getContextPath() + accion);
	}

	public static void navegarFueraAplicacion(String pagina) throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect(pagina);
	}

	public static void navegarNuevaPagina(String url) {
		RequestContext.getCurrentInstance().execute("window.open('" + url + "','_blank')");
	}

	/**
	 * Permite obtener la ruta del contexto del aplicativo.
	 * 
	 * @return
	 */
	public static String getContexto() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
	}

	public static String getRealPath(String path) {
		return FacesContext.getCurrentInstance().getExternalContext().getRealPath(path);
	}

	public static synchronized String obtenerDescripcion(List<SelectItem> valores, String valor) {
		if (valores == null || valores.isEmpty() || UtilTexto.estaVacio(valor)) {
			return "";
		}
		for (SelectItem elemento : valores) {
			if (valor.equals((String) elemento.getValue())) {
				return elemento.getLabel();
			}
		}
		return "";
	}

	public static synchronized String obtenerDescripcion(List<SelectItem> valores, Long valor) {
		if (valores == null || valores.isEmpty() || valor == null) {
			return "";
		}
		for (SelectItem elemento : valores) {
			if (valor.equals((Long) elemento.getValue())) {
				return elemento.getLabel();
			}
		}
		return "";
	}

	public static synchronized boolean compararListado(Object value, Object filter, List<SelectItem> valores) {
		String filterText = (filter == null) ? null : filter.toString().trim();
		if (UtilTexto.estaVacio(filterText)) {
			return true;
		}
		if (value == null || filter == null) {
			return false;
		}
		if (filter instanceof Long)
			return UtilJsf.obtenerDescripcion(valores, (Long) filter).equals(value);
		return UtilJsf.obtenerDescripcion(valores, filterText).equals(value);
	}

	public static synchronized boolean contener(Object value, Object filter) {
		String filterText = (filter == null) ? null : filter.toString().trim();
		if (filterText == null || filterText.equals("")) {
			return true;
		}
		if (value == null) {
			return false;
		}
		return value.toString().trim().toUpperCase().contains(filterText.trim().toUpperCase());
	}

	public static synchronized boolean filtrarFecha(Object value, Object filter) {
		if (filter == null || filter.toString().trim().equals("")) {
			return true;
		}
		if (value == null) {
			return false;
		}
		return DateUtils.truncatedEquals((Date) value, (Date) filter, Calendar.DATE);
	}

	public static void mostrarMensajeExitoso(String string) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, string, null));

	}

	public static void actualizar(String componente) {
		if (UtilTexto.estaVacio(componente))
			return;
		RequestContext.getCurrentInstance().update(componente);
	}

	public static void ocultarDialogo(String popup) {
		if (UtilTexto.estaVacio(popup))
			return;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('" + popup.trim() + "').hide();");
	}

	public static void abrirDialogo(String popup) {
		if (UtilTexto.estaVacio(popup))
			return;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('" + popup.trim() + "').show();");
	}

	public static void hacerFoco(String foco) {
		if (UtilTexto.estaVacio(foco))
			return;
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('" + foco.trim() + "').focus();");
	}

	public static void descargarArchivo(String rutaArchivo) throws IOException {
		descargarPdf(new File(rutaArchivo));
	}

	@SuppressWarnings("deprecation")
	public static boolean visualizarPdf(File file) throws IOException {
		boolean resultado = false;
		FacesContext faces = FacesContext.getCurrentInstance();
		Path path = Paths.get(file.getAbsolutePath());
		byte[] bytes = Files.readAllBytes(path);
		if (bytes == null || bytes.length <= 0) {
			return false;
		}
		HttpServletResponse response = (HttpServletResponse) faces.getExternalContext().getResponse();
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		response.setHeader("Window-target:", "_blank");
		response.setHeader("Content-disposition", "inline");
		response.setHeader("Cache-Control", "cache, must-revalidate");
		response.setHeader("Pragma", "public");
		ServletOutputStream out = response.getOutputStream();
		out.write(bytes);
		resultado = true;
		faces.getApplication().getStateManager().saveSerializedView(faces);
		faces.responseComplete();
		return resultado;
	}

	public static SentidoOrientacion resolverOrientacion(SortOrder order) {
		if (order == null)
			return SentidoOrientacion.SIN_ESPECIFICAR;
		switch (order) {
		case ASCENDING:
			return SentidoOrientacion.ASCENDENTE;
		case DESCENDING:
			return SentidoOrientacion.DESCENDENTE;
		default:
			return SentidoOrientacion.SIN_ESPECIFICAR;
		}
	}

	public static void descargarPdf(File archivo) throws IOException {
		byte[] bytes = new byte[1024];
		int read = 0;
		FileInputStream archivoStream = null;
		try {
			archivoStream = new FileInputStream(archivo);
			FacesContext contexto = FacesContext.getCurrentInstance();
			if (contexto.getResponseComplete())
				return;
			String nombreArchivo = archivo.getName();
			HttpServletResponse response = (HttpServletResponse) contexto.getExternalContext().getResponse();
			response.setContentType("application/pdf");
			response.setHeader("Window-target:", "_blank");
			response.setHeader("Content-Disposition", "attachment;filename=\"" + nombreArchivo + "\"");
			response.setHeader("Cache-Control", "cache, must-revalidate");
			response.setHeader("Pragma", "public");
			ServletOutputStream outputStream = response.getOutputStream();
			while ((read = archivoStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.flush();
			outputStream.close();
			contexto.responseComplete();
		} finally {
			if (archivoStream != null)
				archivoStream.close();
		}
	}

	public static void descargarPdf(String nombreArchivo, byte[] bytes) throws IOException {
		FacesContext contexto = FacesContext.getCurrentInstance();
		if (contexto.getResponseComplete())
			return;
		HttpServletResponse response = (HttpServletResponse) contexto.getExternalContext().getResponse();
		response.setContentType("application/pdf");
		response.setHeader("Window-target:", "_blank");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + nombreArchivo + "\"");
		response.setHeader("Cache-Control", "cache, must-revalidate");
		response.setHeader("Pragma", "public");
		ServletOutputStream outputStream = response.getOutputStream();
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		IOUtils.copy(bais, outputStream);
		contexto.responseComplete();
	}

}