package com.segurosbolivar.avaluos.motor.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
 * DTO que contiene la información del avaluo requeria para el motor de 
 * atomatización de avaluos. Servicios Rest de consulta y sincronización de información
 */

public class AvaluoDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long    usuarioFirmaAvaluadora;
	private Long 	usuario;
	private String	nombreSolicitante;
	private String	documentoIdentificacion;
	private Long	noIdentificacion;
	private String	entidad;
	private Date	fechaAvaluo;
	private Long	consecutivoBanco;
	private String	objetivo;
	private String	departamento;
	private String	ciudad;
	private String	barrioVereda;
	private String	direccionInmueble;
	private String	direccionComplementaria;
	private String	nombreConjuntoEdificio;
	private String	sector;
	private String	mapa;
	private String	latitud;
	private String	longitud;
	private Long	telefonoFijo;
	private Long	telefonoCelular;
	private String	email;
	private String	metodologia;
	private String	justificaconMetodologia;
	private String	proyectoConstructor;
	private String	provisional;
	private String	procedencia;
	private String	estado;
	private Long 	secuencial;
	private Long 	idAvaluo;
	private BarrioAvaluoDTO barrio;
	private InmuebleAvaluoDTO inmueble;
	private OfertaDemandaAvaluoDTO OfertaDemanda;
	private List<LiquidacionAvaluoDTO> liquidacionAvaluo;
	private ObservacionesAvaluoDTO Observaciones;
	private RegistroFotograficoDTO RegistroFotografico;
	private ConstruccionAvaluoDTO Construccion;
	
	public AvaluoDTO() {
		
	}
	
	public Long getUsuarioFirmaAvaluadora() {
		return usuarioFirmaAvaluadora;
	}

	public void setUsuarioFirmaAvaluadora(Long usuarioFirmaAvaluadora) {
		this.usuarioFirmaAvaluadora = usuarioFirmaAvaluadora;
	}

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

	public ConstruccionAvaluoDTO getConstruccion() {
		return Construccion;
	}

	public void setConstruccion(ConstruccionAvaluoDTO construccion) {
		Construccion = construccion;
	}

	public String getNombreSolicitante() {
		return nombreSolicitante;
	}
	public void setNombreSolicitante(String nombreSolicitante) {
		this.nombreSolicitante = nombreSolicitante;
	}
	public String getDocumentoIdentificacion() {
		return documentoIdentificacion;
	}
	public void setDocumentoIdentificacion(String documentoIdentificacion) {
		this.documentoIdentificacion = documentoIdentificacion;
	}
	public  Long getNoIdentificacion() {
		return noIdentificacion;
	}
	public void setNoIdentificacion(Long noIdentificacion) {
		this.noIdentificacion = noIdentificacion;
	}
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	public Date getFechaAvaluo() {
		return fechaAvaluo;
	}
	public void setFechaAvaluo(Date fechaAvaluo) {
		this.fechaAvaluo = fechaAvaluo;
	}
	public Long getConsecutivoBanco() {
		return consecutivoBanco;
	}
	public void setConsecutivoBanco(Long consecutivoBanco) {
		this.consecutivoBanco = consecutivoBanco;
	}
	public String getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
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
	public String getBarrioVereda() {
		return barrioVereda;
	}
	public void setBarrioVereda(String barrioVereda) {
		this.barrioVereda = barrioVereda;
	}
	public String getDireccionInmueble() {
		return direccionInmueble;
	}
	public void setDireccionInmueble(String direccionInmueble) {
		this.direccionInmueble = direccionInmueble;
	}
	public String getDireccionComplementaria() {
		return direccionComplementaria;
	}
	public void setDireccionComplementaria(String direccionComplementaria) {
		this.direccionComplementaria = direccionComplementaria;
	}
	public String getNombreConjuntoEdificio() {
		return nombreConjuntoEdificio;
	}
	public void setNombreConjuntoEdificio(String nombreConjuntoEdificio) {
		this.nombreConjuntoEdificio = nombreConjuntoEdificio;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getMapa() {
		return mapa;
	}
	public void setMapa(String mapa) {
		this.mapa = mapa;
	}
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	public Long getTelefonoFijo() {
		return telefonoFijo;
	}
	public void setTelefonoFijo(Long telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}
	public Long getTelefonoCelular() {
		return telefonoCelular;
	}
	public void setTelefonoCelular(Long telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMetodologia() {
		return metodologia;
	}
	public void setMetodologia(String metodologia) {
		this.metodologia = metodologia;
	}
	public String getJustificaconMetodologia() {
		return justificaconMetodologia;
	}
	public void setJustificaconMetodologia(String justificaconMetodologia) {
		this.justificaconMetodologia = justificaconMetodologia;
	}
	public String getProyectoConstructor() {
		return proyectoConstructor;
	}
	public void setProyectoConstructor(String proyectoConstructor) {
		this.proyectoConstructor = proyectoConstructor;
	}
	public String getProvisional() {
		return provisional;
	}
	public void setProvisional(String provisional) {
		this.provisional = provisional;
	}
	public String getProcedencia() {
		return procedencia;
	}
	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Long getSecuencial() {
		return secuencial;
	}
	public void setSecuencial(Long secuencial) {
		this.secuencial = secuencial;
	}
	public Long getIdAvaluo() {
		return idAvaluo;
	}
	public void setIdAvaluo(Long idAvaluo) {
		this.idAvaluo = idAvaluo;
	}
	public BarrioAvaluoDTO getBarrio() {
		return barrio;
	}
	public void setBarrio(BarrioAvaluoDTO barrio) {
		this.barrio = barrio;
	}
	public InmuebleAvaluoDTO getInmueble() {
		return inmueble;
	}
	public void setInmueble(InmuebleAvaluoDTO inmueble) {
		this.inmueble = inmueble;
	}
	public OfertaDemandaAvaluoDTO getOfertaDemanda() {
		return OfertaDemanda;
	}
	public void setOfertaDemanda(OfertaDemandaAvaluoDTO ofertaDemanda) {
		OfertaDemanda = ofertaDemanda;
	}
	public List<LiquidacionAvaluoDTO> getLiquidacionAvaluo() {
		return liquidacionAvaluo;
	}
	public void setLiquidacionAvaluo(List<LiquidacionAvaluoDTO> liquidacionAvaluo) {
		this.liquidacionAvaluo = liquidacionAvaluo;
	}
	public ObservacionesAvaluoDTO getObservaciones() {
		return Observaciones;
	}
	public void setObservaciones(ObservacionesAvaluoDTO observaciones) {
		Observaciones = observaciones;
	}
	public RegistroFotograficoDTO getRegistroFotografico() {
		return RegistroFotografico;
	}
	public void setRegistroFotografico(RegistroFotograficoDTO registroFotografico) {
		RegistroFotografico = registroFotografico;
	}

	
	
}
