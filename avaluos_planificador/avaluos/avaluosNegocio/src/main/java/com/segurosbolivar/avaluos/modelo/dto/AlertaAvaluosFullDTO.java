package com.segurosbolivar.avaluos.modelo.dto;

import java.util.Date;

public class AlertaAvaluosFullDTO {
	
	private Long idAlerta;

	private Long idAvaluo;

	private Long codTipoAlerta;

	private String estActiva;

	private Date fechaCreacion;

	private String usuarioCreacion;

	private Date fechaTransaccion;

	private String usuarioTransaccion;

	public Long getIdAlerta() {
		return idAlerta;
	}

	public void setIdAlerta(Long idAlerta) {
		this.idAlerta = idAlerta;
	}

	public Long getIdAvaluo() {
		return idAvaluo;
	}

	public void setIdAvaluo(Long idAvaluo) {
		this.idAvaluo = idAvaluo;
	}

	public Long getCodTipoAlerta() {
		return codTipoAlerta;
	}

	public void setCodTipoAlerta(Long codTipoAlerta) {
		this.codTipoAlerta = codTipoAlerta;
	}

	public String getEstActiva() {
		return estActiva;
	}

	public void setEstActiva(String estActiva) {
		this.estActiva = estActiva;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public String getUsuarioTransaccion() {
		return usuarioTransaccion;
	}

	public void setUsuarioTransaccion(String usuarioTransaccion) {
		this.usuarioTransaccion = usuarioTransaccion;
	}
	
}
