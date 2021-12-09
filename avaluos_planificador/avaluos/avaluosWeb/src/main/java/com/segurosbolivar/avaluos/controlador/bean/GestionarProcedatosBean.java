package com.segurosbolivar.avaluos.controlador.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.StreamedContent;

import com.asesoftware.util.cons.TipoComparacion;
import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.controlador.bean.general.PopupAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilValidadorJsf;
import com.segurosbolivar.avaluos.modelo.entity.Archivo;
import com.segurosbolivar.avaluos.modelo.entity.HistoricoLogsGeneraArch;
import com.segurosbolivar.avaluos.modelo.entity.LogsGeneraArchivo;
import com.segurosbolivar.avaluos.modelo.facade.IProcedatoFacade;

/**
 * Controlador para la vista que permite consultar y generar los archivos de
 * procedatos que se trasmiten a la ASOBANCARIA.
 *
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:42 a.m.
 */
@ManagedBean(name = "procedatosBean")
@SessionScoped
public class GestionarProcedatosBean extends PopupAbstractoBean implements Serializable{

	private static final long serialVersionUID = -2954295962361213018L;
	@SuppressWarnings("unused")
	private Archivo archivo;
	private LazyDataModel<LogsGeneraArchivo> listado;
	private List<HistoricoLogsGeneraArch> historico;
	private static final int REGISTRO_PAGINA = 20;
	private List<LogsGeneraArchivo> procedatos;
	private List<LogsGeneraArchivo> procedatosSeleccionados;
	private LogsGeneraArchivo consulta;
	@EJB
	public IProcedatoFacade procedatoFacade;
	private StreamedContent file;
	private int cantidadAvaluos;
	private boolean verConsulta;
	private String descripcionError;
	private String nombreArchivo;

	public static GestionarProcedatosBean getBean() {
		return UtilJsf.obtenerBackingBean("procedatosBean");
	}

	@Override
	public void inicio() {
		verConsulta = false;
		consulta = new LogsGeneraArchivo();
		listado = new LazyDataModel<LogsGeneraArchivo>() {

			private static final long serialVersionUID = 5364221760252143339L;

			@Override
			public List<LogsGeneraArchivo> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				try {
					return procedatoFacade.consultar(consulta, first, pageSize, sortField,
							UtilJsf.resolverOrientacion(sortOrder));
				} catch (Exception e) {
					procesarError(e);
					return Collections.emptyList();
				}
			}

			@Override
			@SuppressWarnings("unchecked")
			public LogsGeneraArchivo getRowData(String rowKey) {
				List<LogsGeneraArchivo> logsGeneraArchivoList = (List<LogsGeneraArchivo>) getWrappedData();
				for (LogsGeneraArchivo registro : logsGeneraArchivoList) {
					if (registro.getIdLogArchivos().toString().equals(rowKey))
						return registro;
				}
				return null;
			}

			@Override
			public Object getRowKey(LogsGeneraArchivo object) {
				return object.getIdLogArchivos();
			}

		};
		listado.setPageSize(REGISTRO_PAGINA);

	}

	@Override
	public String getPermiso() {
		return null;
	}

	@Override
	public String getPopupId() {
		return "dlg_HistoricoProcedatosPopup";
	}

	public void consultar() {
		try {
			if (!validar())
				return;
			verConsulta = true;
			listado.setRowCount(procedatoFacade.cantidadRegistros(consulta));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	private boolean validar() {
		boolean validar = true;
		validar &= UtilValidadorJsf.validar(consulta.getFechaInicioCreacion(), "procedatosForm:fechaArchivoDesde",
				false, null);
		validar &= UtilValidadorJsf.validar(consulta.getFechaFinCreacion(), "procedatosForm:fechaArchivoHasta", false,
				null);
		validar &= UtilValidadorJsf.validar(consulta.getEstado(), "procedatosForm:consultaEstado", true, 3, null);
		// si existen los dos rangos de fechasAvaluo
		if (consulta.getFechaInicioCreacion() != null && consulta.getFechaFinCreacion() != null) {
			validar &= UtilValidadorJsf.comparar(consulta.getFechaInicioCreacion(), TipoComparacion.MENOR_IGUAL,
					consulta.getFechaFinCreacion(), "procedatosForm:fechaArchivoDesde", null);
		}
		return validar;
	}

	public void verLog() {
		LogsGeneraArchivo log = listado.getRowData();
		descripcionError = log.getDescripcionError();
		nombreArchivo = log.getNombreArchivo();
		if (UtilTexto.estaVacio(descripcionError)) {
			mensajeConfirmacion("No existe log de errores.");
			return;
		}
		UtilJsf.abrirDialogo("logModal");
	}

	public void verHistorico() {
		if (procedatosSeleccionados == null || procedatosSeleccionados.size() != 1) {
			mensajeConfirmacion("Seleccione unicamente un  registro");
			return;
		}
		try {
			HistoricoLogsGeneraArch filtro = new HistoricoLogsGeneraArch();
			filtro.setIdLogArchivos(procedatosSeleccionados.get(0).getIdLogArchivos());
			historico = procedatoFacade.consultar(filtro, 0, Integer.MAX_VALUE, null, null);
			if (historico == null || historico.isEmpty()) {
				mensajeConfirmacion("No posee Historico.");
				return;
			}
			abrir();
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void cambiarEstado() {
		try {
			if (procedatosSeleccionados == null || procedatosSeleccionados.isEmpty())
				throw mgrExc.lanzarExcepcion(34, TipoErrorNegocio.INFO);
			procedatoFacade.cambiarEstadoProcedato(procedatosSeleccionados);
			listado.setRowCount(procedatoFacade.cantidadRegistros(consulta));
			mensajeConfirmacion("Procedatos actualizados satisfactoriamente.");
			consultar();
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void generar() throws NegocioException {
		try {
			if (!validarGenerar())
				return;
			procedatoFacade.generar(consulta.getFechaHasta(), getUsuario());
			mensajeConfirmacion("Archivo de procedatos creado satisfactoriamente.");
			consulta.setEstado("G");
			consulta.setFechaHasta(null);
			consultar();
		} catch (NegocioException e) {
			procesarError(e);
		}
	}

	private boolean validarGenerar() {
		boolean validar = true;
		validar &= UtilValidadorJsf.validar(consulta.getFechaHasta(), "procedatosForm:fechaHastaLimite", true, null);
		// si existen los dos rangos de fechasAvaluo
		if (consulta.getFechaHasta() != null) {
			validar &= UtilValidadorJsf.comparar(consulta.getFechaHasta(), TipoComparacion.MENOR_IGUAL, new Date(),
					"procedatosForm:fechaHastaLimite", null);
			// validar &=
			// UtilValidadorJsf.compararDiferenciaMeses(consulta.getFechaHasta(),
			// 2, new Date(),
			// "procedatosForm:fechaHastaLimite", null);
		}
		return validar;
	}

	public void descargarArchivo(String rutaArchivo, String nombreArchivo, Long idLogsGeneraArchivo)
			throws NegocioException {
		procedatoFacade.cambiarTransmitido(idLogsGeneraArchivo);
		File archivo = new File(rutaArchivo);
		try {
			file = new DefaultStreamedContent(new FileInputStream(archivo), "text/plain", nombreArchivo);
			mensajeConfirmacion("Documento generado y descargado satisfactoriamente.");
		} catch (FileNotFoundException e) {
			procesarError(e);
		}
	}

	public void reversar() throws NegocioException {
		try {
			if (procedatosSeleccionados == null || procedatosSeleccionados.isEmpty())
				throw mgrExc.lanzarExcepcion(34, TipoErrorNegocio.INFO);
			procedatoFacade.reversarProcedatos(procedatosSeleccionados);
			listado.setRowCount(procedatoFacade.cantidadRegistros(consulta));
			mensajeConfirmacion("Avaluos reversados satisfactoriamente.");
			consultar();
		} catch (Exception e) {
			procesarError(e);
		}
	}

	// Getters y Setters

	public int getRegistroPagina() {
		return REGISTRO_PAGINA;
	}

	public List<LogsGeneraArchivo> getProcedatosSeleccionados() {
		return procedatosSeleccionados;
	}

	public void setProcedatosSeleccionados(List<LogsGeneraArchivo> procedatosSeleccionados) {
		this.procedatosSeleccionados = procedatosSeleccionados;
	}

	public List<LogsGeneraArchivo> getProcedatos() {
		return procedatos;
	}

	public void setProcedatos(List<LogsGeneraArchivo> procedatos) {
		this.procedatos = procedatos;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public int getCantidadAvaluos() {
		return cantidadAvaluos;
	}

	public void setCantidadAvaluos(int cantidadAvaluos) {
		this.cantidadAvaluos = cantidadAvaluos;
	}

	public LazyDataModel<LogsGeneraArchivo> getListado() {
		return listado;
	}

	public void setListado(LazyDataModel<LogsGeneraArchivo> listado) {
		this.listado = listado;
	}

	public LogsGeneraArchivo getConsulta() {
		return consulta;
	}

	public void setConsulta(LogsGeneraArchivo consulta) {
		this.consulta = consulta;
	}

	public boolean isVerConsulta() {
		return verConsulta;
	}

	public void setVerConsulta(boolean verConsulta) {
		this.verConsulta = verConsulta;
	}

	public List<HistoricoLogsGeneraArch> getHistorico() {
		return historico;
	}

	public void setHistorico(List<HistoricoLogsGeneraArch> historico) {
		this.historico = historico;
	}

	public String getDescripcionError() {
		return descripcionError;
	}

	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

}