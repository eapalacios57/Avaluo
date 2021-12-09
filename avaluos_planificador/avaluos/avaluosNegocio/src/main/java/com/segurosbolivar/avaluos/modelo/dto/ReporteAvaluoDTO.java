package com.segurosbolivar.avaluos.modelo.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class ReporteAvaluoDTO {
	
	private int id;
	private BigInteger consecutivo;
	private Long idTipoIdentificacion;
	private String tipoIdentificacion;
	private Long numeroIdentificacion;
	private String nombreSolicitante;
	private String departamento;
	private String ciudad;
	private Long tipoAvaluo;
	private String tipoAvaluoNombre;
	private Date fechaAvaluo;
	private String perito;
	private Date fechaCargue;
	private String nombreEdificio;
	private String direccion;
	private String matriculaInmobiliaria;
	private Long tipoVivienda;
	private String tipoViviendaNombre;
	private Long estadoConstruccion;
	private String estadoConstruccionNombre;
	private Long estrato;
	private Long estado;
	private String estadoNombre;
	private String nombreTxt;
	private Date fechaDeImpresion;
	private BigDecimal valorTotal;
	private Long calificacion;
	private Long avance;
	private BigDecimal vetustez;
	private Long linea;
	private String asegurable;
	private String archivoZip;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigInteger getConsecutivo() {
		return consecutivo;
	}
	public void setConsecutivo(BigInteger consecutivo) {
		this.consecutivo = consecutivo;
	}
	public Long getIdTipoIdentificacion() {
		return idTipoIdentificacion;
	}
	public void setIdTipoIdentificacion(Long idTipoIdentificacion) {
		this.idTipoIdentificacion = idTipoIdentificacion;
	}
	public Long getNumeroIdentificacion() {
		return numeroIdentificacion;
	}
	public void setNumeroIdentificacion(Long numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}
	public String getNombreSolicitante() {
		return nombreSolicitante;
	}
	public void setNombreSolicitante(String nombreSolicitante) {
		this.nombreSolicitante = nombreSolicitante;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public Long getTipoAvaluo() {
		return tipoAvaluo;
	}
	public void setTipoAvaluo(Long tipoAvaluo) {
		this.tipoAvaluo = tipoAvaluo;
	}
	public Date getFechaAvaluo() {
		return fechaAvaluo;
	}
	public void setFechaAvaluo(Date fechaAvaluo) {
		this.fechaAvaluo = fechaAvaluo;
	}
	public String getPerito() {
		return perito;
	}
	public void setPerito(String perito) {
		this.perito = perito;
	}
	public Date getFechaCargue() {
		return fechaCargue;
	}
	public void setFechaCargue(Date fechaCargue) {
		this.fechaCargue = fechaCargue;
	}
	public String getNombreEdificio() {
		return nombreEdificio;
	}
	public void setNombreEdificio(String nombreEdificio) {
		this.nombreEdificio = nombreEdificio;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getMatriculaInmobiliaria() {
		return matriculaInmobiliaria;
	}
	public void setMatriculaInmobiliaria(String matriculaInmobiliaria) {
		this.matriculaInmobiliaria = matriculaInmobiliaria;
	}
	public Long getTipoVivienda() {
		return tipoVivienda;
	}
	public void setTipoVivienda(Long tipoVivienda) {
		this.tipoVivienda = tipoVivienda;
	}
	public Long getEstadoConstruccion() {
		return estadoConstruccion;
	}
	public void setEstadoConstruccion(Long estadoConstruccion) {
		this.estadoConstruccion = estadoConstruccion;
	}
	public Long getEstrato() {
		return estrato;
	}
	public void setEstrato(Long estrato) {
		this.estrato = estrato;
	}
	public Long getEstado() {
		return estado;
	}
	public void setEstado(Long estado) {
		this.estado = estado;
	}
	public String getNombreTxt() {
		return nombreTxt;
	}
	public void setNombreTxt(String nombreTxt) {
		this.nombreTxt = nombreTxt;
	}
	public Date getFechaDeImpresion() {
		return fechaDeImpresion;
	}
	public void setFechaDeImpresion(Date fechaDeImpresion) {
		this.fechaDeImpresion = fechaDeImpresion;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Long getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(Long calificacion) {
		this.calificacion = calificacion;
	}
	public Long getAvance() {
		return avance;
	}
	public void setAvance(Long avance) {
		this.avance = avance;
	}
	public BigDecimal getVetustez() {
		return vetustez;
	}
	public void setVetustez(BigDecimal vetustez) {
		this.vetustez = vetustez;
	}
	public Long getLinea() {
		return linea;
	}
	public void setLinea(Long linea) {
		this.linea = linea;
	}
	public String getAsegurable() {
		return asegurable;
	}
	public void setAsegurable(String asegurable) {
		this.asegurable = asegurable;
	}
	public String getArchivoZip() {
		return archivoZip;
	}
	public void setArchivoZip(String archivoZip) {
		this.archivoZip = archivoZip;
	}
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	public String getTipoAvaluoNombre() {
		return tipoAvaluoNombre;
	}
	public void setTipoAvaluoNombre(String tipoAvaluoNombre) {
		this.tipoAvaluoNombre = tipoAvaluoNombre;
	}
	public String getTipoViviendaNombre() {
		return tipoViviendaNombre;
	}
	public void setTipoViviendaNombre(String tipoViviendaNombre) {
		this.tipoViviendaNombre = tipoViviendaNombre;
	}
	public String getEstadoConstruccionNombre() {
		return estadoConstruccionNombre;
	}
	public void setEstadoConstruccionNombre(String estadoConstruccionNombre) {
		this.estadoConstruccionNombre = estadoConstruccionNombre;
	}
	public String getEstadoNombre() {
		return estadoNombre;
	}
	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
	}
	
}
