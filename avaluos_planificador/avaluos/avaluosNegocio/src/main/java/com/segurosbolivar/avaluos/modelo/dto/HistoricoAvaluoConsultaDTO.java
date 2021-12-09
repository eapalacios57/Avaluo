package com.segurosbolivar.avaluos.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class HistoricoAvaluoConsultaDTO implements Serializable{
	
	private static final long serialVersionUID = -8604053508333088729L;

	private BigDecimal idTipoIdentificacion;
	
	private String numeroIdentificacion;
	
	private String nombreSolicitante;
	
	private Date fechaAvaluoDesde;
	
	private Date fechaAvaluoHasta;
	
	private BigDecimal idDepartamento;

	private BigDecimal idCiudad;
	
	private String direccionInmueble;
	
	private String matriculaInmobiliaria;
	
	private BigDecimal estadoAvaluo;
	
	private String usuarioAprueba;
	
	private Date fechaCreacionDesde;
	
	private Date fechaCreacionHasta;
	
	private String usuarioTransaccion; 
	
	private Date fechaTransaccionDesde;
	
	private Date fechaTransaccionHasta;

	public BigDecimal getIdTipoIdentificacion() {
		return idTipoIdentificacion;
	}

	public void setIdTipoIdentificacion(BigDecimal idTipoIdentificacion) {
		this.idTipoIdentificacion = idTipoIdentificacion;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getNombreSolicitante() {
		return nombreSolicitante;
	}

	public void setNombreSolicitante(String nombreSolicitante) {
		this.nombreSolicitante = nombreSolicitante;
	}

	public Date getFechaAvaluoDesde() {
		return fechaAvaluoDesde;
	}

	public void setFechaAvaluoDesde(Date fechaAvaluoDesde) {
		this.fechaAvaluoDesde = fechaAvaluoDesde;
	}

	public Date getFechaAvaluoHasta() {
		return fechaAvaluoHasta;
	}

	public void setFechaAvaluoHasta(Date fechaAvaluoHasta) {
		this.fechaAvaluoHasta = fechaAvaluoHasta;
	}

	public BigDecimal getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(BigDecimal idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public BigDecimal getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(BigDecimal idCiudad) {
		this.idCiudad = idCiudad;
	}

	public String getMatriculaInmobiliaria() {
		return matriculaInmobiliaria;
	}

	public void setMatriculaInmobiliaria(String matriculaInmobiliaria) {
		this.matriculaInmobiliaria = matriculaInmobiliaria;
	}

	public BigDecimal getEstadoAvaluo() {
		return estadoAvaluo;
	}

	public void setEstadoAvaluo(BigDecimal estadoAvaluo) {
		this.estadoAvaluo = estadoAvaluo;
	}

	public String getUsuarioAprueba() {
		return usuarioAprueba;
	}

	public void setUsuarioAprueba(String usuarioAprueba) {
		this.usuarioAprueba = usuarioAprueba;
	}

	public Date getFechaCreacionDesde() {
		return fechaCreacionDesde;
	}

	public void setFechaCreacionDesde(Date fechaCreacionDesde) {
		this.fechaCreacionDesde = fechaCreacionDesde;
	}

	public Date getFechaCreacionHasta() {
		return fechaCreacionHasta;
	}

	public void setFechaCreacionHasta(Date fechaCreacionHasta) {
		this.fechaCreacionHasta = fechaCreacionHasta;
	}

	public String getUsuarioTransaccion() {
		return usuarioTransaccion;
	}

	public void setUsuarioTransaccion(String usuarioTransaccion) {
		this.usuarioTransaccion = usuarioTransaccion;
	}

	public Date getFechaTransaccionDesde() {
		return fechaTransaccionDesde;
	}

	public void setFechaTransaccionDesde(Date fechaTransaccionDesde) {
		this.fechaTransaccionDesde = fechaTransaccionDesde;
	}

	public Date getFechaTransaccionHasta() {
		return fechaTransaccionHasta;
	}

	public void setFechaTransaccionHasta(Date fechaTransaccionHasta) {
		this.fechaTransaccionHasta = fechaTransaccionHasta;
	}

	public String getDireccionInmueble() {
		return direccionInmueble;
	}

	public void setDireccionInmueble(String direccionInmueble) {
		this.direccionInmueble = direccionInmueble;
	}
	
}
