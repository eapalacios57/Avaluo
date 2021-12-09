package com.segurosbolivar.avaluos.controlador.bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.segurosbolivar.avaluos.controlador.bean.general.GeneralAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.modelo.dto.HistoricoAvaluoConsultaDTO;
import com.segurosbolivar.avaluos.modelo.entity.HistoricoAvaluo;
import com.segurosbolivar.avaluos.modelo.facade.IHistoricoAvaluoFacade;

@ManagedBean(name = "historicoBean")
@SessionScoped
public class HistoricoAvaluoBean extends GeneralAbstractoBean implements Serializable{

	private static final long serialVersionUID = 620295998957077322L;
	private HistoricoAvaluoConsultaDTO consulta;
	private LazyDataModel<HistoricoAvaluo> listado;
	private boolean verFiltros;
	private boolean mostrarTabla;
	/**
	 * Define la cantidad de registros por pagina
	 */
	private static final int REGISTRO_PAGINA = 50;
	@EJB
	private IHistoricoAvaluoFacade avaluoSvc;

	{
		listado = new LazyDataModel<HistoricoAvaluo>() {

			private static final long serialVersionUID = 5422828369445369303L;

			@Override
			public List<HistoricoAvaluo> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				try {
					return avaluoSvc.consultar(consulta, first, pageSize, sortField,
							UtilJsf.resolverOrientacion(sortOrder));
				} catch (Exception e) {
					procesarError(e);
					return Collections.emptyList();
				}
			}

			@Override
			@SuppressWarnings("unchecked")
			public HistoricoAvaluo getRowData(String rowKey) {
				List<HistoricoAvaluo> avaluos = (List<HistoricoAvaluo>) getWrappedData();
				for (HistoricoAvaluo avaluo : avaluos) {
					if (avaluo.getIdAvaluo().toString().equals(rowKey))
						return avaluo;
				}
				return null;
			}

			@Override
			public Object getRowKey(HistoricoAvaluo object) {
				return object.getIdAvaluo();
			}

		};
	}

	@Override
	public void inicio() {
		consulta = new HistoricoAvaluoConsultaDTO();
		verFiltros = true;
		listado.setPageSize(REGISTRO_PAGINA);
	}

	public static HistoricoAvaluoBean getBean() {
		return UtilJsf.obtenerBackingBean("historicoAvaluoBean");
	}

	@Override
	public String getPermiso() {
		return null;
	}

	public void consultarAvaluo() {
		try {
			verFiltros = false;
			mostrarTabla = true;
			listado.setRowCount(avaluoSvc.cantidadRegistros(consulta));
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void mostrarFiltro() {
		verFiltros = !verFiltros;
	}

	/*
	 * getters y setters
	 */

	public HistoricoAvaluoConsultaDTO getConsulta() {
		return consulta;
	}

	public void setConsulta(HistoricoAvaluoConsultaDTO consulta) {
		this.consulta = consulta;
	}

	public LazyDataModel<HistoricoAvaluo> getListado() {
		return listado;
	}

	public void setListado(LazyDataModel<HistoricoAvaluo> listado) {
		this.listado = listado;
	}

	public int getRegistroPagina() {
		return REGISTRO_PAGINA;
	}

	public boolean isVerFiltros() {
		return verFiltros;
	}

	public boolean isMostrarTabla() {
		return mostrarTabla;
	}

}
