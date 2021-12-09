package com.segurosbolivar.avaluos.modelo.dto;

import java.util.Date;
import java.util.List;


public class DepartamentoFullDTO {
	
	private Long idDepartamento;

	private Long codDane;

	private Long codDivpol;

	private String departamento;

	private Date fechaCreacion;

	private Date fechaTransaccion;

	private String usuarioCreacion;

	private String usuarioTransaccion;

	private Long codAsobancaria;
	
	private List<CiudadFullDTO> ciudadesDepto;

	private List<IndiceAcumuladoFullDTO> indices;

	private String longitud;

	private String latitud;

	public Long getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public Long getCodDane() {
		return codDane;
	}

	public void setCodDane(Long codDane) {
		this.codDane = codDane;
	}

	public Long getCodDivpol() {
		return codDivpol;
	}

	public void setCodDivpol(Long codDivpol) {
		this.codDivpol = codDivpol;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
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

	public Long getCodAsobancaria() {
		return codAsobancaria;
	}

	public void setCodAsobancaria(Long codAsobancaria) {
		this.codAsobancaria = codAsobancaria;
	}

	public List<CiudadFullDTO> getCiudadesDepto() {
		return ciudadesDepto;
	}

	public void setCiudadesDepto(List<CiudadFullDTO> ciudadesDepto) {
		this.ciudadesDepto = ciudadesDepto;
	}

	public List<IndiceAcumuladoFullDTO> getIndices() {
		return indices;
	}

	public void setIndices(List<IndiceAcumuladoFullDTO> indices) {
		this.indices = indices;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

}
