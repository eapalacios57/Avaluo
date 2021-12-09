package com.segurosbolivar.avaluos.modelo.dto;

import java.util.Date;

public class ListaAnexosPdfFullDTO {
	
	private Long idListaAnexosPdf;

	private Long consecutivoAnexo;

	private Date fechaCreacion;

	private Date fechaTransaccion;

	private String usuarioCreacion;

	private String usuarioTransaccion;

	private Long idArchivo;

	private ArchivoFullDTO archivo;

	private Long idAvaluo;

	public Long getIdListaAnexosPdf() {
		return idListaAnexosPdf;
	}

	public void setIdListaAnexosPdf(Long idListaAnexosPdf) {
		this.idListaAnexosPdf = idListaAnexosPdf;
	}

	public Long getConsecutivoAnexo() {
		return consecutivoAnexo;
	}

	public void setConsecutivoAnexo(Long consecutivoAnexo) {
		this.consecutivoAnexo = consecutivoAnexo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioTransaccion() {
		return usuarioTransaccion;
	}

	public void setUsuarioTransaccion(String usuarioTransaccion) {
		this.usuarioTransaccion = usuarioTransaccion;
	}

	public Long getIdArchivo() {
		return idArchivo;
	}

	public void setIdArchivo(Long idArchivo) {
		this.idArchivo = idArchivo;
	}

	public ArchivoFullDTO getArchivo() {
		return archivo;
	}

	public void setArchivo(ArchivoFullDTO archivo) {
		this.archivo = archivo;
	}

	public Long getIdAvaluo() {
		return idAvaluo;
	}

	public void setIdAvaluo(Long idAvaluo) {
		this.idAvaluo = idAvaluo;
	}

}
