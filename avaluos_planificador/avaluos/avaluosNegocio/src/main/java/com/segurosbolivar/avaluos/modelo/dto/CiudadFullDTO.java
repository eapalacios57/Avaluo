package com.segurosbolivar.avaluos.modelo.dto;

import java.util.Date;

public class CiudadFullDTO {
	
	private Long idCiudad;

    private String ciudad;

    private Long codAsobancaria;

    private Long codDane;

    private Date fechaCreacion;

    private Date fechaTransaccion;

    private String usuarioCreacion;

    private String usuarioTransaccion;

    private Long idDepartamento;

    private String longitud;

    private String latitud;

    private Long capital;

	public Long getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(Long idCiudad) {
		this.idCiudad = idCiudad;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Long getCodAsobancaria() {
		return codAsobancaria;
	}

	public void setCodAsobancaria(Long codAsobancaria) {
		this.codAsobancaria = codAsobancaria;
	}

	public Long getCodDane() {
		return codDane;
	}

	public void setCodDane(Long codDane) {
		this.codDane = codDane;
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

	public Long getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
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

	public Long getCapital() {
		return capital;
	}

	public void setCapital(Long capital) {
		this.capital = capital;
	}

}
