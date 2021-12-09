package com.segurosbolivar.avaluos.controlador.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.asesoftware.util.exception.NegocioException;
import com.segurosbolivar.avaluos.controlador.bean.general.PopupAbstractoBean;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilJsf;
import com.segurosbolivar.avaluos.controlador.bean.util.jsf.UtilValidadorJsf;
import com.segurosbolivar.avaluos.modelo.entity.PeritosEmpresa;
import com.segurosbolivar.avaluos.modelo.facade.IPeritoFacade;

@ManagedBean(name = "peritoPopup")
@SessionScoped
public class PopupPeritoBean extends PopupAbstractoBean implements Serializable{

	private static final long serialVersionUID = -2877748542300070358L;
	private  LazyDataModel<PeritosEmpresa> listado;
	private  PeritosEmpresa consulta;
	/**
	 * Define la cantidad de registros por pagina
	 */
	private static final int REGISTRO_PAGINA = 10;
	@EJB
	private IPeritoFacade peritoSvc;

	public static PopupPeritoBean getBean() {
		return UtilJsf.obtenerBackingBean("peritoPopup");
	}

	@Override
	public String getPopupId() {
		return "dlg_PeritoPopup";
	}

	@Override
	public void inicio() {
		consulta = new PeritosEmpresa();
		listado = new LazyDataModel<PeritosEmpresa>() {

			private static final long serialVersionUID = 8035547717481338058L;

			@Override
			public List<PeritosEmpresa> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				return peritoSvc.consultar(consulta, first, pageSize, sortField,
						UtilJsf.resolverOrientacion(sortOrder));
			}

		};
		listado.setPageSize(REGISTRO_PAGINA);
	}

	public void ver(String destino) throws NegocioException {
		setDestinoConfirmacion(destino);
		consulta = new PeritosEmpresa();
		consultar();
		abrir();
	}

	public void consultar() {
		try {
			listado.setRowCount(peritoSvc.cantidadRegistros(consulta));
			listado.load(0, REGISTRO_PAGINA, null, null, null);
		} catch (Exception e) {
			procesarError(e);
		}
	}

	public void seleccionar() {
		cerrar(listado.getRowData().getNumeroDocumento().toString());
	}

	public boolean validar() {
		boolean validar = true;
		validar &= UtilValidadorJsf.validar(consulta.getNumeroDocumento(), "peritoConsultaForm:numeroDocumento", false,
				13, null);
		validar &= UtilValidadorJsf.validar(consulta.getNombrePerito(), "peritoConsultaForm:nombrePerito", false, 60,
				null);
		return validar;
	}

	/*
	 * getters y setters
	 */

	public LazyDataModel<PeritosEmpresa> getListado() {
		return listado;
	}

	public void setListado(LazyDataModel<PeritosEmpresa> listado) {
		this.listado = listado;
	}

	public PeritosEmpresa getConsulta() {
		return consulta;
	}

	public void setConsulta(PeritosEmpresa consulta) {
		this.consulta = consulta;
	}

	public int getRegistroPagina() {
		return REGISTRO_PAGINA;
	}

}
