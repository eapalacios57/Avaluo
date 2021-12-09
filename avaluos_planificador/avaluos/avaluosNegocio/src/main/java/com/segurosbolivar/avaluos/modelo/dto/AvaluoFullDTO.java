package com.segurosbolivar.avaluos.modelo.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class AvaluoFullDTO {
	
	private Long numeroSolicitudMovil;
	
	private String tipoAvaluo;

	private Long idAvaluo;

	private Long idAvaluoPadre;

	private Long codigoBanco;

	private Long estadoAvaluo;

	private String nombreBanco;

	private Long celularSolicitante;

	private String correoSolicitante;

	private Long telefonoSolicitante;

	private Long tipoInforme;

	private String ubicacionGps;

	private Long idMetodologia;

	private Long idObjetoAvaluo;

	private Long idTipoIdentificacion;

	private Long sistemaCoordenadasBUA;

	private Date fechaAvaluo;

	private Date fechaCreacion;

	private Date fechaTransaccion;

	private BigInteger consecutivoBanco;

	private Long numeroIdentificacion;

	private String barrio;

	private String direccionInmueble;

	private String justificacion;

	private String latitudSFBUA;

	private String longitudSFBUA;

	private String nombreConjuntoEdificio;

	private String nombreSolicitante;

	private Long sector;

	private String sistemaCoordenadasTxtBUA;

	private String usuario;

	private String usuarioCreacion;

	private String usuarioTransaccion;

	private Long idCiudad;

	private Long idDepartamento;

	private Long numLinea;

	private Long idArchivo;

	private Long codTipoAvaluo;

	private Long transmitido;

	private String direccionComplementaria;

	private Long codigoProcedencia;

	private Date fechaImpresion;

	private String asegurabilidad;

	private Long codigoMotivoEliminacion;

	private Date fechaEliminacion;

	private DepartamentoFullDTO departamento;

	private CiudadFullDTO ciudad;

	private LogsGeneraArchivoFullDTO logsGeneraArchivo;

	private AnexoFotograficoFullDTO anexosFotograficos;

	private ComportamientoOfertaDemandaFullDTO compOfertaDemanda;

	private CondicionesSalubridadFullDTO condicionSalubridad;

	private List<HistoricoAvaluoFullDTO> historicosAvaluo;

	private InformacionBarrioFullDTO informacionBarrio;

	private InformacionConstruccionFullDTO informacionConstruccion;

	private InformacionInmuebleFullDTO informacionInmueble;

	private List<LiquidacionAvaluoFullDTO> liquidacionAvaluos;

	private LiquidacionAvaluoTotalFullDTO liquidacionTotal;

	private ObservacionesFullDTO observacion;

	private List<ListaAnexosPdfFullDTO> listaAnexosPdf;

	private List<AlertaAvaluosFullDTO> alertas;

	public Long getIdAvaluo() {
		return idAvaluo;
	}

	public void setIdAvaluo(Long idAvaluo) {
		this.idAvaluo = idAvaluo;
	}

	public Long getIdAvaluoPadre() {
		return idAvaluoPadre;
	}

	public void setIdAvaluoPadre(Long idAvaluoPadre) {
		this.idAvaluoPadre = idAvaluoPadre;
	}

	public Long getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(Long codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public Long getEstadoAvaluo() {
		return estadoAvaluo;
	}

	public void setEstadoAvaluo(Long estadoAvaluo) {
		this.estadoAvaluo = estadoAvaluo;
	}

	public String getNombreBanco() {
		return nombreBanco;
	}

	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	public Long getCelularSolicitante() {
		return celularSolicitante;
	}

	public void setCelularSolicitante(Long celularSolicitante) {
		this.celularSolicitante = celularSolicitante;
	}

	public String getCorreoSolicitante() {
		return correoSolicitante;
	}

	public void setCorreoSolicitante(String correoSolicitante) {
		this.correoSolicitante = correoSolicitante;
	}

	public Long getTelefonoSolicitante() {
		return telefonoSolicitante;
	}

	public void setTelefonoSolicitante(Long telefonoSolicitante) {
		this.telefonoSolicitante = telefonoSolicitante;
	}

	public Long getTipoInforme() {
		return tipoInforme;
	}

	public void setTipoInforme(Long tipoInforme) {
		this.tipoInforme = tipoInforme;
	}

	public String getUbicacionGps() {
		return ubicacionGps;
	}

	public void setUbicacionGps(String ubicacionGps) {
		this.ubicacionGps = ubicacionGps;
	}

	public Long getIdMetodologia() {
		return idMetodologia;
	}

	public void setIdMetodologia(Long idMetodologia) {
		this.idMetodologia = idMetodologia;
	}

	public Long getIdObjetoAvaluo() {
		return idObjetoAvaluo;
	}

	public void setIdObjetoAvaluo(Long idObjetoAvaluo) {
		this.idObjetoAvaluo = idObjetoAvaluo;
	}

	public Long getIdTipoIdentificacion() {
		return idTipoIdentificacion;
	}

	public void setIdTipoIdentificacion(Long idTipoIdentificacion) {
		this.idTipoIdentificacion = idTipoIdentificacion;
	}

	public Long getSistemaCoordenadasBUA() {
		return sistemaCoordenadasBUA;
	}

	public void setSistemaCoordenadasBUA(Long sistemaCoordenadasBUA) {
		this.sistemaCoordenadasBUA = sistemaCoordenadasBUA;
	}

	public Date getFechaAvaluo() {
		return fechaAvaluo;
	}

	public void setFechaAvaluo(Date fechaAvaluo) {
		this.fechaAvaluo = fechaAvaluo;
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

	public BigInteger getConsecutivoBanco() {
		return consecutivoBanco;
	}

	public void setConsecutivoBanco(BigInteger consecutivoBanco) {
		this.consecutivoBanco = consecutivoBanco;
	}

	public Long getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(Long numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getDireccionInmueble() {
		return direccionInmueble;
	}

	public void setDireccionInmueble(String direccionInmueble) {
		this.direccionInmueble = direccionInmueble;
	}

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	public String getLatitudSFBUA() {
		return latitudSFBUA;
	}

	public void setLatitudSFBUA(String latitudSFBUA) {
		this.latitudSFBUA = latitudSFBUA;
	}

	public String getLongitudSFBUA() {
		return longitudSFBUA;
	}

	public void setLongitudSFBUA(String longitudSFBUA) {
		this.longitudSFBUA = longitudSFBUA;
	}

	public String getNombreConjuntoEdificio() {
		return nombreConjuntoEdificio;
	}

	public void setNombreConjuntoEdificio(String nombreConjuntoEdificio) {
		this.nombreConjuntoEdificio = nombreConjuntoEdificio;
	}

	public String getNombreSolicitante() {
		return nombreSolicitante;
	}

	public void setNombreSolicitante(String nombreSolicitante) {
		this.nombreSolicitante = nombreSolicitante;
	}

	public Long getSector() {
		return sector;
	}

	public void setSector(Long sector) {
		this.sector = sector;
	}

	public String getSistemaCoordenadasTxtBUA() {
		return sistemaCoordenadasTxtBUA;
	}

	public void setSistemaCoordenadasTxtBUA(String sistemaCoordenadasTxtBUA) {
		this.sistemaCoordenadasTxtBUA = sistemaCoordenadasTxtBUA;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

	public Long getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(Long idCiudad) {
		this.idCiudad = idCiudad;
	}

	public Long getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public Long getNumLinea() {
		return numLinea;
	}

	public void setNumLinea(Long numLinea) {
		this.numLinea = numLinea;
	}

	public Long getIdArchivo() {
		return idArchivo;
	}

	public void setIdArchivo(Long idArchivo) {
		this.idArchivo = idArchivo;
	}

	public Long getCodTipoAvaluo() {
		return codTipoAvaluo;
	}

	public void setCodTipoAvaluo(Long codTipoAvaluo) {
		this.codTipoAvaluo = codTipoAvaluo;
	}

	public Long getTransmitido() {
		return transmitido;
	}

	public void setTransmitido(Long transmitido) {
		this.transmitido = transmitido;
	}

	public String getDireccionComplementaria() {
		return direccionComplementaria;
	}

	public void setDireccionComplementaria(String direccionComplementaria) {
		this.direccionComplementaria = direccionComplementaria;
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

	public Long getCodigoMotivoEliminacion() {
		return codigoMotivoEliminacion;
	}

	public void setCodigoMotivoEliminacion(Long codigoMotivoEliminacion) {
		this.codigoMotivoEliminacion = codigoMotivoEliminacion;
	}

	public Date getFechaEliminacion() {
		return fechaEliminacion;
	}

	public void setFechaEliminacion(Date fechaEliminacion) {
		this.fechaEliminacion = fechaEliminacion;
	}

	public DepartamentoFullDTO getDepartamento() {
		return departamento;
	}

	public void setDepartamento(DepartamentoFullDTO departamento) {
		this.departamento = departamento;
	}

	public CiudadFullDTO getCiudad() {
		return ciudad;
	}

	public void setCiudad(CiudadFullDTO ciudad) {
		this.ciudad = ciudad;
	}

	public LogsGeneraArchivoFullDTO getLogsGeneraArchivo() {
		return logsGeneraArchivo;
	}

	public void setLogsGeneraArchivo(LogsGeneraArchivoFullDTO logsGeneraArchivo) {
		this.logsGeneraArchivo = logsGeneraArchivo;
	}

	public AnexoFotograficoFullDTO getAnexosFotograficos() {
		return anexosFotograficos;
	}

	public void setAnexosFotograficos(AnexoFotograficoFullDTO anexosFotograficos) {
		this.anexosFotograficos = anexosFotograficos;
	}

	public ComportamientoOfertaDemandaFullDTO getCompOfertaDemanda() {
		return compOfertaDemanda;
	}

	public void setCompOfertaDemanda(ComportamientoOfertaDemandaFullDTO compOfertaDemanda) {
		this.compOfertaDemanda = compOfertaDemanda;
	}

	public CondicionesSalubridadFullDTO getCondicionSalubridad() {
		return condicionSalubridad;
	}

	public void setCondicionSalubridad(CondicionesSalubridadFullDTO condicionSalubridad) {
		this.condicionSalubridad = condicionSalubridad;
	}

	public List<HistoricoAvaluoFullDTO> getHistoricosAvaluo() {
		return historicosAvaluo;
	}

	public void setHistoricosAvaluo(List<HistoricoAvaluoFullDTO> historicosAvaluo) {
		this.historicosAvaluo = historicosAvaluo;
	}

	public InformacionBarrioFullDTO getInformacionBarrio() {
		return informacionBarrio;
	}

	public void setInformacionBarrio(InformacionBarrioFullDTO informacionBarrio) {
		this.informacionBarrio = informacionBarrio;
	}

	public InformacionConstruccionFullDTO getInformacionConstruccion() {
		return informacionConstruccion;
	}

	public void setInformacionConstruccion(InformacionConstruccionFullDTO informacionConstruccion) {
		this.informacionConstruccion = informacionConstruccion;
	}

	public InformacionInmuebleFullDTO getInformacionInmueble() {
		return informacionInmueble;
	}

	public void setInformacionInmueble(InformacionInmuebleFullDTO informacionInmueble) {
		this.informacionInmueble = informacionInmueble;
	}

	public List<LiquidacionAvaluoFullDTO> getLiquidacionAvaluos() {
		return liquidacionAvaluos;
	}

	public void setLiquidacionAvaluos(List<LiquidacionAvaluoFullDTO> liquidacionAvaluos) {
		this.liquidacionAvaluos = liquidacionAvaluos;
	}

	public LiquidacionAvaluoTotalFullDTO getLiquidacionTotal() {
		return liquidacionTotal;
	}

	public void setLiquidacionTotal(LiquidacionAvaluoTotalFullDTO liquidacionTotal) {
		this.liquidacionTotal = liquidacionTotal;
	}

	public ObservacionesFullDTO getObservacion() {
		return observacion;
	}

	public void setObservacion(ObservacionesFullDTO observacion) {
		this.observacion = observacion;
	}

	public List<ListaAnexosPdfFullDTO> getListaAnexosPdf() {
		return listaAnexosPdf;
	}

	public void setListaAnexosPdf(List<ListaAnexosPdfFullDTO> listaAnexosPdf) {
		this.listaAnexosPdf = listaAnexosPdf;
	}

	public List<AlertaAvaluosFullDTO> getAlertas() {
		return alertas;
	}

	public void setAlertas(List<AlertaAvaluosFullDTO> alertas) {
		this.alertas = alertas;
	}

	public Long getNumeroSolicitudMovil() {
		return numeroSolicitudMovil;
	}

	public void setNumeroSolicitudMovil(Long numeroSolicitudMovil) {
		this.numeroSolicitudMovil = numeroSolicitudMovil;
	}

	
	public String getTipoAvaluo() {
		return tipoAvaluo;
	}

	public void setTipoAvaluo(String tipoAvaluo) {
		this.tipoAvaluo = tipoAvaluo;
	}

}
