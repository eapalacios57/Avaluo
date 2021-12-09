package com.segurosbolivar.avaluos.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.segurosbolivar.avaluos.modelo.cons.TipoInforme;

public class ConsultaAvaluoDto implements Serializable {

	private static final long serialVersionUID = -7202803950670551050L;

	private Long idAvaluo;

	private String latitud;

	private String longitud;

	private BigInteger consecutivoPadre;

	private String matricula;

	private Long estrato;

	private Long estadoConstruccion;

	private Long avanceObra;

	private BigDecimal vetustez;

	private Long calificacion;

	private BigDecimal totalAvaluo;

	private String nombrePlano;

	private String nombreArchivo;

	private String tiposAlerta;

	private BigInteger consecutivoBanco;

	private Long idTipoIdentificacion;

	private Long numeroIdentificacion;

	private Date fechaAvaluo;

	private Long idDepartamento;

	private Long idCiudad;

	private String direccionInmueble;

	private String nombreConjuntoEdificio;

	private String nombreConstructora;

	private Long tipoInforme;

	private Long estadoAvaluo;

	private String usuarioCreacion;

	private Date fechaCreacion;

	private Long numLinea;

	private Long codTipoAvaluo;

	private Long codigoProcedencia;

	private Date fechaImpresion;

	private String asegurabilidad;

	private Long tipoVivienda;

	private Long transmitido;

	private Date fechaEliminacion;

	private String nombreSolicitante;

	private Long vetustezDesde;

	private Long vetustezHasta;

	private Long avanceDesde;

	private Long avanceHasta;

	private Date fechaAvaluoDesde;

	private Date fechaAvaluoHasta;

	private Date fechaCargueDesde;

	private Date fecharCargueHasta;

	private Date fechaImpresionDesde;

	private Date fechaImpresionHasta;

	private Long valorDesde;

	private Long valorHasta;

	private String filtroTipoAvaluo;

	private boolean tipoAvaluoDefinitivo;

	private boolean tipoAvaluoProvisional;
	
	private UsuarioDto usuario;
	
	private Long categoria;
	
	private Long claseInmueble;

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end

	

	public ConsultaAvaluoDto() {
	}

	/**
	 * determinar si el avaluo es un proyecto constructor.
	 * 
	 * @return en caso de que el tipo de informe sea proyecto, devuelve
	 *         verdadero.
	 */
	public boolean isConstructor() {
		return tipoInforme != null && TipoInforme.PROYECTO.getValor().compareTo(tipoInforme) == 0;
	}

	/**
	 * Permite que definamos el tipo de informe del proyecto indicando si es un
	 * proyecto constructor o no.
	 * 
	 * @param constructor
	 *            en caso de ser verdadero el proyecto se parametriza como un
	 *            proyecto constructor.
	 */
	public void setConstructor(boolean constructor) {
		this.tipoInforme = constructor ? TipoInforme.PROYECTO.getValor() : null;
	}

	/*
	 * getters y setters
	 */

	public BigInteger getConsecutivoPadre() {
		return consecutivoPadre;
	}

	public void setConsecutivoPadre(BigInteger consecutivoPadre) {
		this.consecutivoPadre = consecutivoPadre;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public Long getCategoria() {
		return categoria;
	}

	public void setCategoria(Long categoria) {
		this.categoria = categoria;
	}
	
	public Long getClaseInmueble() {
		return claseInmueble;
	}

	public void setClaseInmueble(Long claseInmueble) {
		this.claseInmueble = claseInmueble;
	}

	public Long getEstrato() {
		return estrato;
	}

	public void setEstrato(Long estrato) {
		this.estrato = estrato;
	}
	
	public UsuarioDto getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}

	public Long getEstadoConstruccion() {
		return estadoConstruccion;
	}

	public void setEstadoConstruccion(Long estadoConstruccion) {
		this.estadoConstruccion = estadoConstruccion;
	}

	public Long getAvanceObra() {
		return avanceObra;
	}

	public void setAvanceObra(Long avanceObra) {
		this.avanceObra = avanceObra;
	}

	public BigDecimal getVetustez() {
		return vetustez;
	}

	public void setVetustez(BigDecimal vetustez) {
		this.vetustez = vetustez;
	}

	public Long getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Long calificacion) {
		this.calificacion = calificacion;
	}

	public BigDecimal getTotalAvaluo() {
		return totalAvaluo;
	}

	public void setTotalAvaluo(BigDecimal totalAvaluo) {
		this.totalAvaluo = totalAvaluo;
	}

	public Long getVetustezDesde() {
		return vetustezDesde;
	}

	public void setVetustezDesde(Long vetustezDesde) {
		this.vetustezDesde = vetustezDesde;
	}

	public Long getVetustezHasta() {
		return vetustezHasta;
	}

	public void setVetustezHasta(Long vetustezHasta) {
		this.vetustezHasta = vetustezHasta;
	}

	public Long getAvanceDesde() {
		return avanceDesde;
	}

	public void setAvanceDesde(Long avanceDesde) {
		this.avanceDesde = avanceDesde;
	}

	public Long getAvanceHasta() {
		return avanceHasta;
	}

	public void setAvanceHasta(Long avanceHasta) {
		this.avanceHasta = avanceHasta;
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

	public Date getFechaCargueDesde() {
		return fechaCargueDesde;
	}

	public void setFechaCargueDesde(Date fechaCargueDesde) {
		this.fechaCargueDesde = fechaCargueDesde;
	}

	public Date getFecharCargueHasta() {
		return fecharCargueHasta;
	}

	public void setFecharCargueHasta(Date fecharCargueHasta) {
		this.fecharCargueHasta = fecharCargueHasta;
	}

	public Date getFechaImpresionDesde() {
		return fechaImpresionDesde;
	}

	public void setFechaImpresionDesde(Date fechaImpresionDesde) {
		this.fechaImpresionDesde = fechaImpresionDesde;
	}

	public Date getFechaImpresionHasta() {
		return fechaImpresionHasta;
	}

	public void setFechaImpresionHasta(Date fechaImpresionHasta) {
		this.fechaImpresionHasta = fechaImpresionHasta;
	}

	public String getNombrePlano() {
		return nombrePlano;
	}

	public void setNombrePlano(String nombrePlano) {
		this.nombrePlano = nombrePlano;
	}

	public String getTiposAlerta() {
		return tiposAlerta;
	}

	public void setTiposAlerta(String tiposAlerta) {
		this.tiposAlerta = tiposAlerta;
	}

	public BigInteger getConsecutivoBanco() {
		return consecutivoBanco;
	}

	public void setConsecutivoBanco(BigInteger consecutivoBanco) {
		this.consecutivoBanco = consecutivoBanco;
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

	public Date getFechaAvaluo() {
		return fechaAvaluo;
	}

	public void setFechaAvaluo(Date fechaAvaluo) {
		this.fechaAvaluo = fechaAvaluo;
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

	public String getDireccionInmueble() {
		return direccionInmueble;
	}

	public void setDireccionInmueble(String direccionInmueble) {
		this.direccionInmueble = direccionInmueble;
	}

	public String getNombreConjuntoEdificio() {
		return nombreConjuntoEdificio;
	}

	public void setNombreConjuntoEdificio(String nombreConjuntoEdificio) {
		this.nombreConjuntoEdificio = nombreConjuntoEdificio;
	}

	public Long getTipoInforme() {
		return tipoInforme;
	}

	public void setTipoInforme(Long tipoInforme) {
		this.tipoInforme = tipoInforme;
	}

	public Long getEstadoAvaluo() {
		return estadoAvaluo;
	}

	public void setEstadoAvaluo(Long estadoAvaluo) {
		this.estadoAvaluo = estadoAvaluo;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Long getNumLinea() {
		return numLinea;
	}

	public void setNumLinea(Long numLinea) {
		this.numLinea = numLinea;
	}

	public Long getCodTipoAvaluo() {
		return codTipoAvaluo;
	}

	public void setCodTipoAvaluo(Long codTipoAvaluo) {
		this.codTipoAvaluo = codTipoAvaluo;
	}

	public Long getCodigoProcedencia() {
		return codigoProcedencia;
	}

	public void setCodigoProcedencia(Long codigoProcedencia) {
		this.codigoProcedencia = codigoProcedencia;
	}

	public Date getFechaImpresion() {
		return fechaImpresion;
	}

	public void setFechaImpresion(Date fechaImpresion) {
		this.fechaImpresion = fechaImpresion;
	}

	public String getAsegurabilidad() {
		return asegurabilidad;
	}

	public void setAsegurabilidad(String asegurabilidad) {
		this.asegurabilidad = asegurabilidad;
	}

	public Long getValorDesde() {
		return valorDesde;
	}

	public void setValorDesde(Long valorDesde) {
		this.valorDesde = valorDesde;
	}

	public Long getValorHasta() {
		return valorHasta;
	}

	public void setValorHasta(Long valorHasta) {
		this.valorHasta = valorHasta;
	}

	public Long getTipoVivienda() {
		return tipoVivienda;
	}

	public void setTipoVivienda(Long tipoVivienda) {
		this.tipoVivienda = tipoVivienda;
	}

	public String getFiltroTipoAvaluo() {
		return filtroTipoAvaluo;
	}

	public void setFiltroTipoAvaluo(String filtroTipoAvaluo) {
		this.filtroTipoAvaluo = filtroTipoAvaluo;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public Long getIdAvaluo() {
		return idAvaluo;
	}

	public void setIdAvaluo(Long idAvaluo) {
		this.idAvaluo = idAvaluo;
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

	public Long getTransmitido() {
		return transmitido;
	}

	public void setTransmitido(Long transmitido) {
		this.transmitido = transmitido;
	}

	public Date getFechaEliminacion() {
		return fechaEliminacion;
	}

	public void setFechaEliminacion(Date fechaEliminacion) {
		this.fechaEliminacion = fechaEliminacion;
	}

	public String getNombreSolicitante() {
		return nombreSolicitante;
	}

	public void setNombreSolicitante(String nombreSolicitante) {
		this.nombreSolicitante = nombreSolicitante;
	}

	public boolean isTipoAvaluoProvisional() {
		return tipoAvaluoProvisional;
	}

	public void setTipoAvaluoProvisional(boolean tipoAvaluoProvisional) {
		this.tipoAvaluoProvisional = tipoAvaluoProvisional;
	}

	public boolean isTipoAvaluoDefinitivo() {
		return tipoAvaluoDefinitivo;
	}

	public void setTipoAvaluoDefinitivo(boolean tipoAvaluoDefinitivo) {
		this.tipoAvaluoDefinitivo = tipoAvaluoDefinitivo;
	}

	public String getNombreConstructora() {
		return nombreConstructora;
	}

	public void setNombreConstructora(String nombreConstructora) {
		this.nombreConstructora = nombreConstructora;
	}

}