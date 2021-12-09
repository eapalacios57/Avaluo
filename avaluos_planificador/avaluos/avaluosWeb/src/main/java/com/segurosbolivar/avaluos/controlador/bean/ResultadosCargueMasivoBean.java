package com.segurosbolivar.avaluos.controlador.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.asesoftware.util.cons.TipoComparacion;
import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.exception.NegocioException;
import com.asesoftware.util.lang.UtilNumero;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.controlador.bean.general.GeneralAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilValidadorJsf;
import com.segurosbolivar.avaluos.modelo.dto.ConsultaCargueMasivoDto;
import com.segurosbolivar.avaluos.modelo.dto.DetalleMasivoDto;
import com.segurosbolivar.avaluos.modelo.dto.ResultadoCargueMasivoDto;
import com.segurosbolivar.avaluos.modelo.facade.ICargueFacade;
import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;

/**
 * Controlador para la pantalla que permite consultar los resultados de los
 * cargues masivos que se reallizaron en la plataforma. De modo que se pueda
 * realizar el seguimiento respectivo.
 *
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:46 a.m.
 */
@ManagedBean
@SessionScoped
public class ResultadosCargueMasivoBean extends GeneralAbstractoBean implements Serializable{

	private static final long serialVersionUID = 3008554279092666853L;
	private LazyDataModel<DetalleMasivoDto> detalle;
	private ConsultaCargueMasivoDto consulta;
	private DetalleMasivoDto consultaDetalle;
	private boolean verFiltros;
	private LazyDataModel<ResultadoCargueMasivoDto> listado;
	private static final int REGISTRO_PAGINA = 20;
	private ResultadoCargueMasivoDto seleccionado;
	private List<String> estados;
	private List<DetalleMasivoDto> detallesFiltrados;

	@EJB
	public ICargueFacade cargueFacade;
	private Long totalDetalle;

	public void consultar() {
		try {
			if (PopupPeritoBean.getBean().isVer())
				return;
			if (!validarConsulta())
				throw mgrExc.lanzarExcepcion(22, TipoErrorNegocio.ALERTA);
			verFiltros = false;
			listado.setRowCount(cargueFacade.cantidadRegistros(consulta));
			detalle.setRowCount(0);
			consultaDetalle = new DetalleMasivoDto();
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void mostrarFiltro() {
		verFiltros = true;
		limpiarDatos();
	}

	private void limpiarDatos() {
		consulta = new ConsultaCargueMasivoDto();
		consultaDetalle = new DetalleMasivoDto();
	}

	public void consultarPerito() {
		try {
			PopupPeritoBean.getBean().ver("resultadosCargueMasivoBean.perito");
		} catch (Exception e) {
			procesarError(e);
		}
	}

	private boolean validarConsulta() {
		boolean validar = true;
		validar &= UtilValidadorJsf.validarFormato(consulta.getNumeroBatch(), "resultadoCargueMasivoForm:numeroBatch",
				false, 21, "^[0-9 ]*$", null, "El campo solo permite numeros");
		validar &= UtilValidadorJsf.validarFormato(consulta.getNumeroReferencia(),
				"resultadoCargueMasivoForm:numeroReferencia", false, 21, "^[0-9 ]*$", null,
				"El campo solo permite numeros");
		if (consulta.getFechaCargueDesde() != null && consulta.getFechaCargueHasta() != null) {
			validar &= UtilValidadorJsf.comparar(consulta.getFechaCargueDesde(), TipoComparacion.MENOR_IGUAL,
					consulta.getFechaCargueHasta(), "resultadoCargueMasivoForm:fechaCargue", null);
		}
		return validar;
	}

	public void consultarDetalle() {
		try {
			consultaDetalle = new DetalleMasivoDto();
			consultaDetalle.setNumeroReferencia(UtilNumero.pasarEntero(seleccionado.getNumeroRefCargue()));
			totalDetalle = cargueFacade.cantidadRegistros(consultaDetalle);
			detalle.setRowCount(totalDetalle.intValue());
		} catch (NegocioException e) {
			procesarError(e);
		}
	}

	public void filtrarDetalle() {
		try {
			if (consultaDetalle == null || !validarDetalle())
				return;
			totalDetalle = cargueFacade.cantidadRegistros(consultaDetalle);
			detalle.setRowCount(totalDetalle.intValue());
		} catch (NegocioException e) {
			procesarError(e);
		}
	}

	private boolean validarDetalle() {
		boolean validar = true;
		if (consultaDetalle == null)
			return false;
		if (UtilTexto.estaVacio(consultaDetalle.getEstadoCargue()) && consultaDetalle.getLineaPlano() == null)
			return false;
		validar &= UtilValidadorJsf.validar(consultaDetalle.getEstadoCargue(),
				"cargueForm:tab:filtroEstadoCargueDetalle", false, 20, null);
		validar &= UtilValidadorJsf.validar(consultaDetalle.getLineaPlano(), "cargueForm:tab:filtroLineaDetalle:",
				false, 20, null);
		return validar;
	}

	public void setPerito(String numeroDocumento) {
		consulta.setNumeroIdPerito(numeroDocumento);
		actualizar("cargueForm:tab:peritoResultado");
	}

	public void limpiarPerito() {
		if (consulta != null)
			consulta.setNumeroIdPerito(null);
	}

	@Override
	public void inicio() {
		consulta = new ConsultaCargueMasivoDto();
		estados = new ArrayList<>();
		estados.add(UtilConstantes.ESTADO_CARGUE_APLICADO);
		estados.add(UtilConstantes.ESTADO_CARGUE_RECHAZADO);
		estados.add(UtilConstantes.ESTADO_CARGUE_CARGADO);
		PopupPeritoBean.getBean().ocultar();
		verFiltros = true;
		listado = new LazyDataModel<ResultadoCargueMasivoDto>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -2976938113632172208L;

			@Override
			public List<ResultadoCargueMasivoDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				try {
					List<ResultadoCargueMasivoDto> cargues = cargueFacade.consultar(consulta, first, pageSize,
							sortField, UtilJsf.resolverOrientacion(sortOrder));
					if (cargues == null) {
						return null;
					}

					return cargues;
				} catch (Exception e) {
					procesarError(e);
					return null;
				}
			}

			@Override
			@SuppressWarnings("unchecked")
			public ResultadoCargueMasivoDto getRowData(String rowKey) {
				List<ResultadoCargueMasivoDto> cargues = (List<ResultadoCargueMasivoDto>) getWrappedData();
				for (ResultadoCargueMasivoDto cargue : cargues) {
					if (cargue.getNumeroRefCargue().equals(rowKey)) {
						return cargue;
					}
				}
				return null;
			}

			@Override
			public Object getRowKey(ResultadoCargueMasivoDto object) {
				return object.getNumeroRefCargue();
			}
		};

		detalle = new LazyDataModel<DetalleMasivoDto>() {
			private static final long serialVersionUID = 6461836417766899712L;

			@Override
			public List<DetalleMasivoDto> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				try {
					return cargueFacade.consultarDetalle(consultaDetalle, first, pageSize, sortField,
							UtilJsf.resolverOrientacion(sortOrder));
				} catch (Exception e) {
					procesarError(e);
					return null;
				}
			}

		};
		listado.setPageSize(REGISTRO_PAGINA);
		detalle.setPageSize(REGISTRO_PAGINA);
	}

	@Override
	public String getPermiso() {
		return null;
	}

	public ConsultaCargueMasivoDto getConsulta() {
		return consulta;
	}

	public void setConsulta(ConsultaCargueMasivoDto consulta) {
		this.consulta = consulta;
	}

	public LazyDataModel<ResultadoCargueMasivoDto> getListado() {
		return listado;
	}

	public void setListado(LazyDataModel<ResultadoCargueMasivoDto> listado) {
		this.listado = listado;
	}

	public int getRegistroPagina() {
		return REGISTRO_PAGINA;
	}

	public ResultadoCargueMasivoDto getSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(ResultadoCargueMasivoDto seleccionado) {
		this.seleccionado = seleccionado;
	}

	public List<String> getEstados() {
		return estados;
	}

	public void setEstados(List<String> estados) {
		this.estados = estados;
	}

	public boolean isVerFiltros() {
		return verFiltros;
	}

	public List<DetalleMasivoDto> getDetallesFiltrados() {
		return detallesFiltrados;
	}

	public void setDetallesFiltrados(List<DetalleMasivoDto> detallesFiltrados) {
		this.detallesFiltrados = detallesFiltrados;
	}

	public void setVerFiltros(boolean verFiltros) {
		this.verFiltros = verFiltros;
	}

	public Long getTotalDetalle() {
		return totalDetalle;
	}

	public void setTotalDetalle(Long totalDetalle) {
		this.totalDetalle = totalDetalle;
	}

	public LazyDataModel<DetalleMasivoDto> getDetalle() {
		return detalle;
	}

	public void setDetalle(LazyDataModel<DetalleMasivoDto> detalle) {
		this.detalle = detalle;
	}

	public DetalleMasivoDto getConsultaDetalle() {
		return consultaDetalle;
	}

	public void setConsultaDetalle(DetalleMasivoDto consultaDetalle) {
		this.consultaDetalle = consultaDetalle;
	}

}