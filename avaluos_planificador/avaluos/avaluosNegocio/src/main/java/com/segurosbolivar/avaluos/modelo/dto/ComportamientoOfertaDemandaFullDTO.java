package com.segurosbolivar.avaluos.modelo.dto;

import java.util.Date;

public class ComportamientoOfertaDemandaFullDTO {
	
	private Long idComportamientoOfertaDemanda;

	private Date fechaCreacion;

	private Date fechaTransaccion;

	private Long tipoComercializacion;

	private String actualidadEdificadora;

	private String comportamiento;

	private String usuarioCreacion;

	private String usuarioTransaccion;

	private Long idAvaluo;

	public Long getIdComportamientoOfertaDemanda() {
		return idComportamientoOfertaDemanda;
	}

	public void setIdComportamientoOfertaDemanda(Long idComportamientoOfertaDemanda) {
		this.idComportamientoOfertaDemanda = idComportamientoOfertaDemanda;
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

	public Long getTipoComercializacion() {
		return tipoComercializacion;
	}

	public void setTipoComercializacion(Long tipoComercializacion) {
		this.tipoComercializacion = tipoComercializacion;
	}

	public String getActualidadEdificadora() {
		return actualidadEdificadora;
	}

	public void setActualidadEdificadora(String actualidadEdificadora) {
		this.actualidadEdificadora = actualidadEdificadora;
	}

	public String getComportamiento() {
		return comportamiento;
	}

	public void setComportamiento(String comportamiento) {
		this.comportamiento = comportamiento;
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

	public Long getIdAvaluo() {
		return idAvaluo;
	}

	public void setIdAvaluo(Long idAvaluo) {
		this.idAvaluo = idAvaluo;
	}

}
