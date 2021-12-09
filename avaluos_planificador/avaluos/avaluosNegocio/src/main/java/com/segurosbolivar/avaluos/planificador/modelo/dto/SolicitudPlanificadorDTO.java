package com.segurosbolivar.avaluos.planificador.modelo.dto;

import java.math.BigDecimal;
import java.util.Date;



public class SolicitudPlanificadorDTO {

	
	
	private String codigoSolicitud;

	private BigDecimal avance;

	private String codigo;

	private String comentariosAnexos;

	private String descripcion;

	private Date fechaCreacion;

	private Date fechaSolicitud;

	private Date fechaTransaccion;

	private Date fechaVisitaPredio;

	private String municipioDepartamento;

	private String nombreAsesorComercial;

	private String numeroDocumentoPlanificador;

	private String telefonoAsesorComercial;

	private String tipoDocumentoPlanificador;

	private String usuarioCreacion;

	private String usuarioTransaccion;

	private BigDecimal valorTotalCredito;

	private BigDecimal valorTotalProyecto;

	
	public String getCodigoSolicitud() {
		return this.codigoSolicitud;
	}

	public void setCodigoSolicitud(String codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public BigDecimal getAvance() {
		return this.avance;
	}

	public void setAvance(BigDecimal avance) {
		this.avance = avance;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getComentariosAnexos() {
		return this.comentariosAnexos;
	}

	public void setComentariosAnexos(String comentariosAnexos) {
		this.comentariosAnexos = comentariosAnexos;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaSolicitud() {
		return this.fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public Date getFechaTransaccion() {
		return this.fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public Date getFechaVisitaPredio() {
		return this.fechaVisitaPredio;
	}

	public void setFechaVisitaPredio(Date fechaVisitaPredio) {
		this.fechaVisitaPredio = fechaVisitaPredio;
	}

	public String getMunicipioDepartamento() {
		return this.municipioDepartamento;
	}

	public void setMunicipioDepartamento(String municipioDepartamento) {
		this.municipioDepartamento = municipioDepartamento;
	}

	public String getNombreAsesorComercial() {
		return this.nombreAsesorComercial;
	}

	public void setNombreAsesorComercial(String nombreAsesorComercial) {
		this.nombreAsesorComercial = nombreAsesorComercial;
	}

	public String getNumeroDocumentoPlanificador() {
		return this.numeroDocumentoPlanificador;
	}

	public void setNumeroDocumentoPlanificador(String numeroDocumentoPlanificador) {
		this.numeroDocumentoPlanificador = numeroDocumentoPlanificador;
	}

	public String getTelefonoAsesorComercial() {
		return this.telefonoAsesorComercial;
	}

	public void setTelefonoAsesorComercial(String telefonoAsesorComercial) {
		this.telefonoAsesorComercial = telefonoAsesorComercial;
	}

	public String getTipoDocumentoPlanificador() {
		return this.tipoDocumentoPlanificador;
	}

	public void setTipoDocumentoPlanificador(String tipoDocumentoPlanificador) {
		this.tipoDocumentoPlanificador = tipoDocumentoPlanificador;
	}

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioTransaccion() {
		return this.usuarioTransaccion;
	}

	public void setUsuarioTransaccion(String usuarioTransaccion) {
		this.usuarioTransaccion = usuarioTransaccion;
	}

	public BigDecimal getValorTotalCredito() {
		return this.valorTotalCredito;
	}

	public void setValorTotalCredito(BigDecimal valorTotalCredito) {
		this.valorTotalCredito = valorTotalCredito;
	}

	public BigDecimal getValorTotalProyecto() {
		return this.valorTotalProyecto;
	}

	public void setValorTotalProyecto(BigDecimal valorTotalProyecto) {
		this.valorTotalProyecto = valorTotalProyecto;
	}

	
	
}
