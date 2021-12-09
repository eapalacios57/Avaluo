package com.segurosbolivar.avaluos.modelo.dto;

import java.math.BigDecimal;
import java.util.Date;

public class IndiceAcumuladoFullDTO {
	
	private Long idIndiceAcumulado;

	private Long idDepartamento;

	private Long idCiudad;

	private BigDecimal valor;

	private Long estrato;

	private Long anio;

	private Date fechaCreacion;

	private Date fechaTransaccion;

	private String usuarioCreacion;

	private String usuarioTransaccion;

	public Long getIdIndiceAcumulado() {
		return idIndiceAcumulado;
	}

	public void setIdIndiceAcumulado(Long idIndiceAcumulado) {
		this.idIndiceAcumulado = idIndiceAcumulado;
	}

	public Long getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public Long getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(Long idCiudad) {
		this.idCiudad = idCiudad;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getEstrato() {
		return estrato;
	}

	public void setEstrato(Long estrato) {
		this.estrato = estrato;
	}

	public Long getAnio() {
		return anio;
	}

	public void setAnio(Long anio) {
		this.anio = anio;
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
	
}
