package com.segurosbolivar.avaluos.controlador.bean;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.asesoftware.util.archivo.UtilArchivos;
import com.asesoftware.util.cons.TipoErrorNegocio;
import com.asesoftware.util.lang.UtilTexto;
import com.segurosbolivar.avaluos.controlador.bean.general.GeneralAbstractoBean;
import com.segurosbolivar.avaluos.modelo.cons.TipoArchivo;
import com.segurosbolivar.avaluos.modelo.dto.CargueMasivoDtoBean;
import com.segurosbolivar.avaluos.modelo.facade.ICargueFacade;

/**
 * Controlador para la vista de cargue masivo que permite realizar la operaci�n
 * de importaci�n de registros multiples de aval�os incluso los que provienen de
 * proyectos contructor.
 * 
 * @author stilaguy
 * @version 1.0
 * @created 31-ago-2017 10:30:40 a.m.
 */
@ManagedBean
@SessionScoped
public class CargueMasivoBean extends GeneralAbstractoBean implements Serializable{

	private static final long serialVersionUID = 493612885920429255L;

	@EJB
	public ICargueFacade cargueFacade;
	private List<CargueMasivoDtoBean> listaCargueMasivo;
	private boolean proyectoConstructor;
	private int cantidadArchivos = 0;
	private int cantidadArchivosTxtCargados = 0;
	private int cantidadArchivosZipCargados = 0;
	private static final int TAMANIO_MAXIMO = 50;
	private static final int CANTIDAD_MAXIMA_ARCHIVOS = 10;
	private int tamanioCargado = 0;
	private DataTable table;

	public CargueMasivoBean() {

	}

	@Override
	public void inicio() {
		listaCargueMasivo = new ArrayList<>();
		proyectoConstructor = false;
		cantidadArchivos = CANTIDAD_MAXIMA_ARCHIVOS;
	}

	public void subirArchivos(FileUploadEvent evento) {
		try {
			UploadedFile archivo = evento.getFile();
			boolean EsZip = archivo.getContentType().equalsIgnoreCase(TipoArchivo.ZIP.getValor());
			if (listaCargueMasivo != null) {
				boolean existe = false;
				boolean encontrado = false;
				for (CargueMasivoDtoBean cargue : listaCargueMasivo) {
					encontrado = UtilArchivos.quitarExtension(archivo.getFileName()).equalsIgnoreCase(
							UtilArchivos.quitarExtension(EsZip ? cargue.getNombreTxt() : cargue.getNombreZip()));
					if (UtilArchivos.quitarExtension(archivo.getFileName()).equalsIgnoreCase(
							UtilArchivos.quitarExtension(EsZip ? cargue.getNombreZip() : cargue.getNombreTxt())))
						existe = true;
					if (!encontrado)
						continue;
					cargaArchivo(cargue, archivo);
					return;
				}
				if (existe)
					return;
			}
			CargueMasivoDtoBean cargueNuevo = new CargueMasivoDtoBean();
			cargaArchivo(cargueNuevo, archivo);
			listaCargueMasivo.add(cargueNuevo);
		} catch (Exception e) {
			procesarError(e);
		}
	}
	
	private void cargaArchivo(CargueMasivoDtoBean cargue, UploadedFile archivo) throws Exception {
		if (archivo.getContentType().equalsIgnoreCase(TipoArchivo.TEXT.getValor().toUpperCase())) {
			if(UtilTexto.estaVacio(cargue.getNombreTxt())) {
				cantidadArchivosTxtCargados++;
				tamanioCargado += (archivo.getSize() / (1024 * 1024));
			}
			cargue.setArchivoTxt(archivo.getInputstream());
			cargue.setFechaCargueTxt(Calendar.getInstance().getTime());
			cargue.setNombreTxt(archivo.getFileName().trim());
			return;
		}
		if(UtilTexto.estaVacio(cargue.getNombreZip())) {
			cantidadArchivosZipCargados++;
			tamanioCargado += (archivo.getSize() / (1024 * 1024));
		}
		cargue.setArchivoZip(archivo.getInputstream());
		cargue.setFechaCargueZip(Calendar.getInstance().getTime());
		cargue.setNombreZip(archivo.getFileName().trim());
		
	}

	public void eliminarCargue() {

		try {
			if (listaCargueMasivo.isEmpty())
				return;
			int tamanoArchivo = 0;
			CargueMasivoDtoBean seleccionado = (CargueMasivoDtoBean) table.getRowData();
			try {
				if (seleccionado.getArchivoTxt() != null) {
					tamanoArchivo = seleccionado.getArchivoTxt().available() / (1024 * 1024);
					tamanioCargado -= tamanoArchivo;
					cantidadArchivosTxtCargados--;
				}
				if (seleccionado.getArchivoZip() != null) {
					tamanoArchivo = seleccionado.getArchivoZip().available() / (1024 * 1024);
					tamanioCargado -= tamanoArchivo;
					cantidadArchivosZipCargados--;
				}
				listaCargueMasivo.remove(seleccionado);
				mensajeConfirmacion("Eliminacion del cargue ha sido exitosa");
			} catch (IOException e) {
				throw mgrExc.lanzarExcepcion(103, TipoErrorNegocio.ALERTA);
			}
		} catch (Exception e) {
			procesarError(e);
		}

	}

	public void cambiaProyecto() {
		limpiaCargue();
	}

	private void limpiaCargue() {
		listaCargueMasivo = new ArrayList<>();
		proyectoConstructor = false;
		cantidadArchivos = CANTIDAD_MAXIMA_ARCHIVOS;
		cantidadArchivosTxtCargados = 0;
		cantidadArchivosZipCargados = 0;
		tamanioCargado = 0;
	}

	private boolean validaCargue() {
		boolean valido = true;
		for (CargueMasivoDtoBean cargue : listaCargueMasivo) {
			if (cargue.getArchivoTxt() == null || cargue.getArchivoZip() == null) {
				valido = false;
				break;
			}
		}
		return valido;
	}

	public boolean isVerCargueTxt() {
		return cantidadArchivosTxtCargados < cantidadArchivos;
	}

	public boolean isVerCargueFoto() {
		return cantidadArchivosZipCargados < cantidadArchivos;
	}

	public void cambiaProyectoConstructor() {
		if (proyectoConstructor)
			this.setCantidadArchivos(1);
		else
			this.setCantidadArchivos(CANTIDAD_MAXIMA_ARCHIVOS);
	}

	public void cargarArchivos() {
		try {
			if (!validaCargue())
				throw mgrExc.lanzarExcepcion(101, TipoErrorNegocio.ALERTA);
			if (tamanioCargado > TAMANIO_MAXIMO)
				throw mgrExc.lanzarExcepcion(102, TipoErrorNegocio.ALERTA);
			if (!validaNombres())
				throw mgrExc.lanzarExcepcion(130, TipoErrorNegocio.ALERTA);
			mantenerZip();
			cargueFacade.procesarCargue(listaCargueMasivo, getUsuario(), proyectoConstructor);
			mensajeConfirmacion("Se ha procesado el cargue correctamente. Ver seccion Resultados del cargue masivo");
			limpiaCargue();
		} catch (Exception e) {
			procesarError(e);
		}
	}
	
	
	private void mantenerZip() {
		for (CargueMasivoDtoBean cargue : listaCargueMasivo) {
			if ( cargue.getArchivoZip() != null) {
				try {
					cargue.getArchivoZip().reset();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}
	}
	
	
	private boolean validaNombres() {
		boolean valido = true;
		for (CargueMasivoDtoBean cargue : listaCargueMasivo) {
			if (cargue.getArchivoZip() != null) {
				InputStream archivoZip= cargue.getArchivoZip();
				try (ZipInputStream zipInputStream = new ZipInputStream(archivoZip);) {
				    ZipEntry zipEntrante = zipInputStream.getNextEntry();
				    
					    while (zipEntrante != null) {
							String nombreZip = zipEntrante.getName();
							Pattern p = Pattern.compile("^\\d{13,19}_(?:fotos.pdf|fachada.jpg)$");
							Matcher m = p.matcher(nombreZip);  
							if(!m.matches()) {
								valido = false;
								break;
							}
							zipEntrante = zipInputStream.getNextEntry();
					    }
					    archivoZip.reset();
				    } catch (IOException e) {
						e.printStackTrace();
					}
			}
		}
		return valido;
	}

	public void validarEstructuraArchivos() {

	}

	@Override
	public String getPermiso() {
		return null;
	}

	public List<CargueMasivoDtoBean> getListaCargueMasivo() {
		return listaCargueMasivo;
	}

	public void setListaCargueMasivo(List<CargueMasivoDtoBean> listaCargueMasivo) {
		this.listaCargueMasivo = listaCargueMasivo;
	}

	public boolean isProyectoConstructor() {
		return proyectoConstructor;
	}

	public void setProyectoConstructor(boolean proyectoConstructor) {
		this.proyectoConstructor = proyectoConstructor;
	}

	public int getCantidadArchivos() {
		return cantidadArchivos;
	}

	public void setCantidadArchivos(int cantidadArchivos) {
		this.cantidadArchivos = cantidadArchivos;
	}

	public int getTamanioCargado() {
		return tamanioCargado;
	}

	public void setTamanioCargado(int tamanioCargado) {
		this.tamanioCargado = tamanioCargado;
	}

	public static int getTamanioMaximo() {
		return TAMANIO_MAXIMO;
	}

	public DataTable getTable() {
		return table;
	}

	public void setTable(DataTable table) {
		this.table = table;
	}

}