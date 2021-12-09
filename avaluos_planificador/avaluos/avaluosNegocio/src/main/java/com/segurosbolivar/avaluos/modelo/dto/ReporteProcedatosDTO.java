package com.segurosbolivar.avaluos.modelo.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class ReporteProcedatosDTO {
	
	private Long idAvaluo;
	private Long andenes;
	private Long calidadBanio;
	private Long calidadMadera;
	private Long calidMetal;
	private Long calidadCocina;
	private Long calidadMuro;
	private Long calidadPiso;
	private Long calidadTecho;
	private Long ciudadEscritura;
	private Long claseInmueble;
	private Long conjuntoAgrupacionCerrada;
	private Long cubierta;
	private Long departamentoEscritura;
	private Long estadoBanios;
	private Long estadoCocina;
	private Long estadoConservacion;
	private Long estadoMadera;
	private Long estadoMetal;
	private Long estadoMuros;
	private Long estadoPisos;
	private Long estrato;
	private Long estructura;
	private Long estructuraTechos;
	private Long estadosViaAcceso;
	private Long fachada;
	private String matriculaInmobiliariaPpal1;
	private String matriculaInmobiliariaPpal2;
	private String matriculaInmobiliariaDeposito1;
	private String matriculaInmobiliariaDeposito2;
	private String matriculaInmobiliariaGaraje1;
	private String matriculaInmobiliariaGaraje2;
	private String matriculaInmobiliariaGaraje3;
	private String matriculaInmobiliariaGaraje4;
	private String matriculaInmobiliariaGaraje5;
	private String notaria;
	private String numeroEscritura;
	private Long idCiudad;
	private Long idDepartamento;
	private Long codigoDaneCiudad;
	private Long idMetodologia;
	private Long idObjetoAvaluo;
	private Long idTipoIdentificacion;
	//private Long tipoDocumento; pendiente
	private Long iluminacion;
	private Long legalidad;
	private Long pavimentada;
	private Long pisosBodega;
	private Long propiedadHorizontal;
	private Long condicionSalubridad;
	private Long sardeneles;
	private Long tipoFachada;
	private Long tipoVivienda;
	private Long topografia;
	private Long transporte;
	private Long ubicacionInmueble;
	private Long ubicacion2;
	private Long usoInmueble;
	private Long ventilacion;
	private Date fechaAvaluo;
	private Date fechaEscritura;
	private Long aireAcondicionado;
	private Long acueductoPredio;
	private Long acueductoSector;
	private Long aguasServidas;
	private Long alamedas;
	private Long alcantarilladoPredio;
	private Long alcantarilladoSector;
	private Long alumbrado;
	private Long ambientalArborizacion;
	private Long ambientalParques;
	private Long ambientalZonaVerde;
	private Long arborizacion;
	private Long ascensor;
	private Long impactoNegativoBasura;
	private Long bicicletero;
	private Long bombaEyectora;
	private Long canchaMultiple;
	private Long cicloRutas;
	private Long citofono;
	private Long clubHouse;
	private Long comercio;
	private Long electricidadPredio;
	private Long electricidadSector;
	private Long enObra;
	private Long estadoRemodelacion;
	private Long estadoTerminadaNueva;
	private Long estadoTerminadoUsado;
	private Long gasPredio;
	private Long gasSector;
	private Long gimnasio;
	private Long garajeVisitante;
	private Long golfito;
	private Long industria;
	private Long impactoNegativoInseguridad;
	private Long juegoNinos;
	private Long otrosUsos;
	private Long paradero;
	private Long parques;
	private Long piscina;
	private Long planta;
	private Long impactoNegativoPorAire;
	private Long porteria;
	private Long presion;
	private Long impactoNegativoRuido;
	private Long salonComunal;
	private Long shutBasuras;
	private Long sinTerminar;
	private Long squash;
	private Long tanqueAgua;
	private Long telefonoPredio;
	private Long telefonoSector;
	private Long vivienda;
	private Long zonasVerdes;
	private Long zonasVerdesInfoConstruccion;
	private BigDecimal avaluoUvr;
	private Long bahiaComunal;
	private Long balcon;
	private Long banioPrivado;
	private Long banioServicio;
	private Long banioSocial;
	private Long bodega;
	private Long cocina;
	private Long comedor;
	private BigInteger consecutivoBanco;
	private Long cuartoServicio;
	private Long cubierto;
	private Long deposito;
	private Long descubierto;
	private Long doble;
	private Long estarHabitacion;
	private Long estudio;
	private Long numeroHabitaciones;
	private Long numeroIdentificacion;
	private Long jardin;
	private Long local;
	private Long numeroAscensores;
	private Long numeroEdificios;
	private Long oficina;
	private Long patioInterior;
	private Long pisos;
	private Long privado;
	private Long sala;
	private Long sencillo;
	private Long servidumbre;
	private Long sotanos;
	private Long terraza;
	private Long tipoComercializacion;
	private BigDecimal totalAvaluo;
	private Long totalGarajes;
	private Long totalUnidades;
	private Long unidadPorPiso;
	private Long usoExclusivo;
	private BigDecimal valorAsegurable;
	private BigDecimal valorUvrDia;
	private BigDecimal vetustez;
	private Long zonaVerdePrivada;
	private Long calificacion;
	private Long estadoConstruccion;
	private String actualidadEdificadora;
	private String impactoNegativoOtros;
	private String ambientalOtros;
	private Long avanceObra;
	private String barrio;
	private String chip;
	private String comportamiento;
	private String textoCubierta;
	private String direccionAnexos;
	private String direccionInmueble;
	private String textoEstructura;
	private String textoFachada;
	private List<HistoricoAvaluoFullDTO> historicosAvaluo;
	private List<LiquidacionAvaluoFullDTO> liquidacionAvaluos;
	private List<ListaAnexosPdfFullDTO> listaAnexosPdf;
	private List<AlertaAvaluosFullDTO> alertas;
	private String otrasDirecciones;
	private String otroClase;
	private String otrosDotacion;
	private Long otrosUsosBarrio;
	private String otroUsoInmueble;
	private String perspectivas;
	private Long altura;
	private Long ubicacion3;
	private Long reparadosBUA;
	private Long irregularidadAlturaBUA;
	private Long irregularidadPlantaBUA;
	private Long estructuraReforzadaBUA;
	private Long danoPrevioBUA;
	private Long anoConstruccionBUA;
	private Long materialConstruccionBUA;
	private Long detalleMaterialSFBUA;
	private Long idCategoria;
	private Long simetriaPlanta;
	private Long materialEstructura;
	private String otroMaterial;
	private Long parapetosCubierta;
	private Long ubicacionTanque;
	private String otraUbicacionTanque;
	private Long sobrePeso;
	private Long golpeteo;
	private Long asentamientos;
	private Long danoPrevio;
	private Long requiereCondicionesSalubridad;
	private Long pisoInmueble;
	private Long simetriaAltura;
	private Long ubicacionFuentesHidricas;
	private Long ubicacionNivelVia;
	private Long edificacionesIguales;
	private Long luces;
	private Long ediContUso;
	private Long rangoConstruccionSF;
	private String catastralSF;
	private Long danoReparadoSF;
	private Long simetriaAlturaSF;
	private Long simetriaPlantaSF;
	private Long danoPrevioSF;
	private Long estructuraReforzadaSF;
	private Long materialConstruccionSF;
	private Long usoInmuebleBUA;
	private Long usoInmuebleSF;
	private String nombreConstructora;
	private Long codigoFinanciadoConstructor;
	private String ubicacionGps;
	private String correoSolicitante;
	private Long celularSolicitante;
	private Long telefonoSolicitante;
	private Long sector;
	private String usuario;
	private String justificacion;
	private String nombreConjuntoEdificio;
	private String nombreSolicitante;
	private String sistemaCoordenadasTxtBUA;
	private Long sistemaCoordenadasBUA;
	private String latitudSFBUA;
	private String longitudSFBUA;
	
	
	public Long getSistemaCoordenadasBUA() {
		return sistemaCoordenadasBUA;
	}
	public void setSistemaCoordenadasBUA(Long sistemaCoordenadasBUA) {
		this.sistemaCoordenadasBUA = sistemaCoordenadasBUA;
	}
	public String getNombreSolicitante() {
		return nombreSolicitante;
	}
	public void setNombreSolicitante(String nombreSolicitante) {
		this.nombreSolicitante = nombreSolicitante;
	}
	public String getNombreConjuntoEdificio() {
		return nombreConjuntoEdificio;
	}
	public void setNombreConjuntoEdificio(String nombreConjuntoEdificio) {
		this.nombreConjuntoEdificio = nombreConjuntoEdificio;
	}
	public String getJustificacion() {
		return justificacion;
	}
	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Long getSector() {
		return sector;
	}
	public void setSector(Long sector) {
		this.sector = sector;
	}
	public Long getTelefonoSolicitante() {
		return telefonoSolicitante;
	}
	public void setTelefonoSolicitante(Long telefonoSolicitante) {
		this.telefonoSolicitante = telefonoSolicitante;
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
	public String getUbicacionGps() {
		return ubicacionGps;
	}
	public void setUbicacionGps(String ubicacionGps) {
		this.ubicacionGps = ubicacionGps;
	}
	public Long getIdAvaluo() {
		return idAvaluo;
	}
	public void setIdAvaluo(Long idAvaluo) {
		this.idAvaluo = idAvaluo;
	}
	public Long getAndenes() {
		return andenes;
	}
	public void setAndenes(Long andenes) {
		this.andenes = andenes;
	}
	public Long getCalidadBanio() {
		return calidadBanio;
	}
	public void setCalidadBanio(Long calidadBanio) {
		this.calidadBanio = calidadBanio;
	}
	public Long getCalidadMadera() {
		return calidadMadera;
	}
	public void setCalidadMadera(Long calidadMadera) {
		this.calidadMadera = calidadMadera;
	}
	public Long getCalidMetal() {
		return calidMetal;
	}
	public void setCalidMetal(Long calidMetal) {
		this.calidMetal = calidMetal;
	}
	public Long getCalidadCocina() {
		return calidadCocina;
	}
	public void setCalidadCocina(Long calidadCocina) {
		this.calidadCocina = calidadCocina;
	}
	public Long getCalidadMuro() {
		return calidadMuro;
	}
	public void setCalidadMuro(Long calidadMuro) {
		this.calidadMuro = calidadMuro;
	}
	public Long getCalidadPiso() {
		return calidadPiso;
	}
	public void setCalidadPiso(Long calidadPiso) {
		this.calidadPiso = calidadPiso;
	}
	public Long getCalidadTecho() {
		return calidadTecho;
	}
	public void setCalidadTecho(Long calidadTecho) {
		this.calidadTecho = calidadTecho;
	}
	public Long getCiudadEscritura() {
		return ciudadEscritura;
	}
	public void setCiudadEscritura(Long ciudadEscritura) {
		this.ciudadEscritura = ciudadEscritura;
	}
	public Long getClaseInmueble() {
		return claseInmueble;
	}
	public void setClaseInmueble(Long claseInmueble) {
		this.claseInmueble = claseInmueble;
	}
	public Long getConjuntoAgrupacionCerrada() {
		return conjuntoAgrupacionCerrada;
	}
	public void setConjuntoAgrupacionCerrada(Long conjuntoAgrupacionCerrada) {
		this.conjuntoAgrupacionCerrada = conjuntoAgrupacionCerrada;
	}
	public Long getCubierta() {
		return cubierta;
	}
	public void setCubierta(Long cubierta) {
		this.cubierta = cubierta;
	}
	public Long getDepartamentoEscritura() {
		return departamentoEscritura;
	}
	public void setDepartamentoEscritura(Long departamentoEscritura) {
		this.departamentoEscritura = departamentoEscritura;
	}
	public Long getEstadoBanios() {
		return estadoBanios;
	}
	public void setEstadoBanios(Long estadoBanios) {
		this.estadoBanios = estadoBanios;
	}
	public Long getEstadoCocina() {
		return estadoCocina;
	}
	public void setEstadoCocina(Long estadoCocina) {
		this.estadoCocina = estadoCocina;
	}
	public Long getEstadoConservacion() {
		return estadoConservacion;
	}
	public void setEstadoConservacion(Long estadoConservacion) {
		this.estadoConservacion = estadoConservacion;
	}
	public Long getEstadoMadera() {
		return estadoMadera;
	}
	public void setEstadoMadera(Long estadoMadera) {
		this.estadoMadera = estadoMadera;
	}
	public Long getEstadoMetal() {
		return estadoMetal;
	}
	public void setEstadoMetal(Long estadoMetal) {
		this.estadoMetal = estadoMetal;
	}
	public Long getEstadoMuros() {
		return estadoMuros;
	}
	public void setEstadoMuros(Long estadoMuros) {
		this.estadoMuros = estadoMuros;
	}
	public Long getEstadoPisos() {
		return estadoPisos;
	}
	public void setEstadoPisos(Long estadoPisos) {
		this.estadoPisos = estadoPisos;
	}
	public Long getEstrato() {
		return estrato;
	}
	public void setEstrato(Long estrato) {
		this.estrato = estrato;
	}
	public Long getEstructura() {
		return estructura;
	}
	public void setEstructura(Long estructura) {
		this.estructura = estructura;
	}
	public Long getEstructuraTechos() {
		return estructuraTechos;
	}
	public void setEstructuraTechos(Long estructuraTechos) {
		this.estructuraTechos = estructuraTechos;
	}
	public Long getEstadosViaAcceso() {
		return estadosViaAcceso;
	}
	public void setEstadosViaAcceso(Long estadosViaAcceso) {
		this.estadosViaAcceso = estadosViaAcceso;
	}
	public Long getFachada() {
		return fachada;
	}
	public void setFachada(Long fachada) {
		this.fachada = fachada;
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
	public Long getCodigoDaneCiudad() {
		return codigoDaneCiudad;
	}
	public void setCodigoDaneCiudad(Long codigoDaneCiudad) {
		this.codigoDaneCiudad = codigoDaneCiudad;
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
	public Long getIluminacion() {
		return iluminacion;
	}
	public void setIluminacion(Long iluminacion) {
		this.iluminacion = iluminacion;
	}
	public Long getLegalidad() {
		return legalidad;
	}
	public void setLegalidad(Long legalidad) {
		this.legalidad = legalidad;
	}
	public Long getPavimentada() {
		return pavimentada;
	}
	public void setPavimentada(Long pavimentada) {
		this.pavimentada = pavimentada;
	}
	public Long getPisosBodega() {
		return pisosBodega;
	}
	public void setPisosBodega(Long pisosBodega) {
		this.pisosBodega = pisosBodega;
	}
	public Long getPropiedadHorizontal() {
		return propiedadHorizontal;
	}
	public void setPropiedadHorizontal(Long propiedadHorizontal) {
		this.propiedadHorizontal = propiedadHorizontal;
	}
	public Long getCondicionSalubridad() {
		return condicionSalubridad;
	}
	public void setCondicionSalubridad(Long condicionSalubridad) {
		this.condicionSalubridad = condicionSalubridad;
	}
	public Long getSardeneles() {
		return sardeneles;
	}
	public void setSardeneles(Long sardeneles) {
		this.sardeneles = sardeneles;
	}
	public Long getTipoFachada() {
		return tipoFachada;
	}
	public void setTipoFachada(Long tipoFachada) {
		this.tipoFachada = tipoFachada;
	}
	public Long getTipoVivienda() {
		return tipoVivienda;
	}
	public void setTipoVivienda(Long tipoVivienda) {
		this.tipoVivienda = tipoVivienda;
	}
	public Long getTopografia() {
		return topografia;
	}
	public void setTopografia(Long topografia) {
		this.topografia = topografia;
	}
	public Long getTransporte() {
		return transporte;
	}
	public void setTransporte(Long transporte) {
		this.transporte = transporte;
	}
	public Long getUbicacionInmueble() {
		return ubicacionInmueble;
	}
	public void setUbicacionInmueble(Long ubicacionInmueble) {
		this.ubicacionInmueble = ubicacionInmueble;
	}
	public Long getUbicacion2() {
		return ubicacion2;
	}
	public void setUbicacion2(Long ubicacion2) {
		this.ubicacion2 = ubicacion2;
	}
	public Long getUsoInmueble() {
		return usoInmueble;
	}
	public void setUsoInmueble(Long usoInmueble) {
		this.usoInmueble = usoInmueble;
	}
	public Long getVentilacion() {
		return ventilacion;
	}
	public void setVentilacion(Long ventilacion) {
		this.ventilacion = ventilacion;
	}
	public Date getFechaAvaluo() {
		return fechaAvaluo;
	}
	public void setFechaAvaluo(Date fechaAvaluo) {
		this.fechaAvaluo = fechaAvaluo;
	}
	public Date getFechaEscritura() {
		return fechaEscritura;
	}
	public void setFechaEscritura(Date fechaEscritura) {
		this.fechaEscritura = fechaEscritura;
	}
	public Long getAireAcondicionado() {
		return aireAcondicionado;
	}
	public void setAireAcondicionado(Long aireAcondicionado) {
		this.aireAcondicionado = aireAcondicionado;
	}
	public Long getAcueductoPredio() {
		return acueductoPredio;
	}
	public void setAcueductoPredio(Long acueductoPredio) {
		this.acueductoPredio = acueductoPredio;
	}
	public Long getAcueductoSector() {
		return acueductoSector;
	}
	public void setAcueductoSector(Long acueductoSector) {
		this.acueductoSector = acueductoSector;
	}
	public Long getAguasServidas() {
		return aguasServidas;
	}
	public void setAguasServidas(Long aguasServidas) {
		this.aguasServidas = aguasServidas;
	}
	public Long getAlamedas() {
		return alamedas;
	}
	public void setAlamedas(Long alamedas) {
		this.alamedas = alamedas;
	}
	public Long getAlcantarilladoPredio() {
		return alcantarilladoPredio;
	}
	public void setAlcantarilladoPredio(Long alcantarilladoPredio) {
		this.alcantarilladoPredio = alcantarilladoPredio;
	}
	public Long getAlcantarilladoSector() {
		return alcantarilladoSector;
	}
	public void setAlcantarilladoSector(Long alcantarilladoSector) {
		this.alcantarilladoSector = alcantarilladoSector;
	}
	public Long getAlumbrado() {
		return alumbrado;
	}
	public void setAlumbrado(Long alumbrado) {
		this.alumbrado = alumbrado;
	}
	public Long getAmbientalArborizacion() {
		return ambientalArborizacion;
	}
	public void setAmbientalArborizacion(Long ambientalArborizacion) {
		this.ambientalArborizacion = ambientalArborizacion;
	}
	public Long getAmbientalParques() {
		return ambientalParques;
	}
	public void setAmbientalParques(Long ambientalParques) {
		this.ambientalParques = ambientalParques;
	}
	public Long getAmbientalZonaVerde() {
		return ambientalZonaVerde;
	}
	public void setAmbientalZonaVerde(Long ambientalZonaVerde) {
		this.ambientalZonaVerde = ambientalZonaVerde;
	}
	public Long getArborizacion() {
		return arborizacion;
	}
	public void setArborizacion(Long arborizacion) {
		this.arborizacion = arborizacion;
	}
	public Long getAscensor() {
		return ascensor;
	}
	public void setAscensor(Long ascensor) {
		this.ascensor = ascensor;
	}
	public Long getImpactoNegativoBasura() {
		return impactoNegativoBasura;
	}
	public void setImpactoNegativoBasura(Long impactoNegativoBasura) {
		this.impactoNegativoBasura = impactoNegativoBasura;
	}
	public Long getBicicletero() {
		return bicicletero;
	}
	public void setBicicletero(Long bicicletero) {
		this.bicicletero = bicicletero;
	}
	public Long getBombaEyectora() {
		return bombaEyectora;
	}
	public void setBombaEyectora(Long bombaEyectora) {
		this.bombaEyectora = bombaEyectora;
	}
	public Long getCanchaMultiple() {
		return canchaMultiple;
	}
	public void setCanchaMultiple(Long canchaMultiple) {
		this.canchaMultiple = canchaMultiple;
	}
	public Long getCicloRutas() {
		return cicloRutas;
	}
	public void setCicloRutas(Long cicloRutas) {
		this.cicloRutas = cicloRutas;
	}
	public Long getCitofono() {
		return citofono;
	}
	public void setCitofono(Long citofono) {
		this.citofono = citofono;
	}
	public Long getClubHouse() {
		return clubHouse;
	}
	public void setClubHouse(Long clubHouse) {
		this.clubHouse = clubHouse;
	}
	public Long getComercio() {
		return comercio;
	}
	public void setComercio(Long comercio) {
		this.comercio = comercio;
	}
	public Long getElectricidadPredio() {
		return electricidadPredio;
	}
	public void setElectricidadPredio(Long electricidadPredio) {
		this.electricidadPredio = electricidadPredio;
	}
	public Long getElectricidadSector() {
		return electricidadSector;
	}
	public void setElectricidadSector(Long electricidadSector) {
		this.electricidadSector = electricidadSector;
	}
	public Long getEnObra() {
		return enObra;
	}
	public void setEnObra(Long enObra) {
		this.enObra = enObra;
	}
	public Long getEstadoRemodelacion() {
		return estadoRemodelacion;
	}
	public void setEstadoRemodelacion(Long estadoRemodelacion) {
		this.estadoRemodelacion = estadoRemodelacion;
	}
	public Long getEstadoTerminadaNueva() {
		return estadoTerminadaNueva;
	}
	public void setEstadoTerminadaNueva(Long estadoTerminadaNueva) {
		this.estadoTerminadaNueva = estadoTerminadaNueva;
	}
	public Long getEstadoTerminadoUsado() {
		return estadoTerminadoUsado;
	}
	public void setEstadoTerminadoUsado(Long estadoTerminadoUsado) {
		this.estadoTerminadoUsado = estadoTerminadoUsado;
	}
	public Long getGasPredio() {
		return gasPredio;
	}
	public void setGasPredio(Long gasPredio) {
		this.gasPredio = gasPredio;
	}
	public Long getGasSector() {
		return gasSector;
	}
	public void setGasSector(Long gasSector) {
		this.gasSector = gasSector;
	}
	public Long getGimnasio() {
		return gimnasio;
	}
	public void setGimnasio(Long gimnasio) {
		this.gimnasio = gimnasio;
	}
	public Long getGarajeVisitante() {
		return garajeVisitante;
	}
	public void setGarajeVisitante(Long garajeVisitante) {
		this.garajeVisitante = garajeVisitante;
	}
	public Long getGolfito() {
		return golfito;
	}
	public void setGolfito(Long golfito) {
		this.golfito = golfito;
	}
	public Long getIndustria() {
		return industria;
	}
	public void setIndustria(Long industria) {
		this.industria = industria;
	}
	public Long getImpactoNegativoInseguridad() {
		return impactoNegativoInseguridad;
	}
	public void setImpactoNegativoInseguridad(Long impactoNegativoInseguridad) {
		this.impactoNegativoInseguridad = impactoNegativoInseguridad;
	}
	public Long getJuegoNinos() {
		return juegoNinos;
	}
	public void setJuegoNinos(Long juegoNinos) {
		this.juegoNinos = juegoNinos;
	}
	public Long getOtrosUsos() {
		return otrosUsos;
	}
	public void setOtrosUsos(Long otrosUsos) {
		this.otrosUsos = otrosUsos;
	}
	public Long getParadero() {
		return paradero;
	}
	public void setParadero(Long paradero) {
		this.paradero = paradero;
	}
	public Long getParques() {
		return parques;
	}
	public void setParques(Long parques) {
		this.parques = parques;
	}
	public Long getPiscina() {
		return piscina;
	}
	public void setPiscina(Long piscina) {
		this.piscina = piscina;
	}
	public Long getPlanta() {
		return planta;
	}
	public void setPlanta(Long planta) {
		this.planta = planta;
	}
	public Long getImpactoNegativoPorAire() {
		return impactoNegativoPorAire;
	}
	public void setImpactoNegativoPorAire(Long impactoNegativoPorAire) {
		this.impactoNegativoPorAire = impactoNegativoPorAire;
	}
	public Long getPorteria() {
		return porteria;
	}
	public void setPorteria(Long porteria) {
		this.porteria = porteria;
	}
	public Long getPresion() {
		return presion;
	}
	public void setPresion(Long presion) {
		this.presion = presion;
	}
	public Long getImpactoNegativoRuido() {
		return impactoNegativoRuido;
	}
	public void setImpactoNegativoRuido(Long impactoNegativoRuido) {
		this.impactoNegativoRuido = impactoNegativoRuido;
	}
	public Long getSalonComunal() {
		return salonComunal;
	}
	public void setSalonComunal(Long salonComunal) {
		this.salonComunal = salonComunal;
	}
	public Long getShutBasuras() {
		return shutBasuras;
	}
	public void setShutBasuras(Long shutBasuras) {
		this.shutBasuras = shutBasuras;
	}
	public Long getSinTerminar() {
		return sinTerminar;
	}
	public void setSinTerminar(Long sinTerminar) {
		this.sinTerminar = sinTerminar;
	}
	public Long getSquash() {
		return squash;
	}
	public void setSquash(Long squash) {
		this.squash = squash;
	}
	public Long getTanqueAgua() {
		return tanqueAgua;
	}
	public void setTanqueAgua(Long tanqueAgua) {
		this.tanqueAgua = tanqueAgua;
	}
	public Long getTelefonoPredio() {
		return telefonoPredio;
	}
	public void setTelefonoPredio(Long telefonoPredio) {
		this.telefonoPredio = telefonoPredio;
	}
	public Long getTelefonoSector() {
		return telefonoSector;
	}
	public void setTelefonoSector(Long telefonoSector) {
		this.telefonoSector = telefonoSector;
	}
	public Long getVivienda() {
		return vivienda;
	}
	public void setVivienda(Long vivienda) {
		this.vivienda = vivienda;
	}
	public Long getZonasVerdes() {
		return zonasVerdes;
	}
	public void setZonasVerdes(Long zonasVerdes) {
		this.zonasVerdes = zonasVerdes;
	}
	public Long getZonasVerdesInfoConstruccion() {
		return zonasVerdesInfoConstruccion;
	}
	public void setZonasVerdesInfoConstruccion(Long zonasVerdesInfoConstruccion) {
		this.zonasVerdesInfoConstruccion = zonasVerdesInfoConstruccion;
	}
	public BigDecimal getAvaluoUvr() {
		return avaluoUvr;
	}
	public void setAvaluoUvr(BigDecimal avaluoUvr) {
		this.avaluoUvr = avaluoUvr;
	}
	public Long getBahiaComunal() {
		return bahiaComunal;
	}
	public void setBahiaComunal(Long bahiaComunal) {
		this.bahiaComunal = bahiaComunal;
	}
	public Long getBalcon() {
		return balcon;
	}
	public void setBalcon(Long balcon) {
		this.balcon = balcon;
	}
	public Long getBanioPrivado() {
		return banioPrivado;
	}
	public void setBanioPrivado(Long banioPrivado) {
		this.banioPrivado = banioPrivado;
	}
	public Long getBanioServicio() {
		return banioServicio;
	}
	public void setBanioServicio(Long banioServicio) {
		this.banioServicio = banioServicio;
	}
	public Long getBanioSocial() {
		return banioSocial;
	}
	public void setBanioSocial(Long banioSocial) {
		this.banioSocial = banioSocial;
	}
	public Long getBodega() {
		return bodega;
	}
	public void setBodega(Long bodega) {
		this.bodega = bodega;
	}
	public Long getCocina() {
		return cocina;
	}
	public void setCocina(Long cocina) {
		this.cocina = cocina;
	}
	public Long getComedor() {
		return comedor;
	}
	public void setComedor(Long comedor) {
		this.comedor = comedor;
	}
	public BigInteger getConsecutivoBanco() {
		return consecutivoBanco;
	}
	public void setConsecutivoBanco(BigInteger consecutivoBanco) {
		this.consecutivoBanco = consecutivoBanco;
	}
	public Long getCuartoServicio() {
		return cuartoServicio;
	}
	public void setCuartoServicio(Long cuartoServicio) {
		this.cuartoServicio = cuartoServicio;
	}
	public Long getCubierto() {
		return cubierto;
	}
	public void setCubierto(Long cubierto) {
		this.cubierto = cubierto;
	}
	public Long getDeposito() {
		return deposito;
	}
	public void setDeposito(Long deposito) {
		this.deposito = deposito;
	}
	public Long getDescubierto() {
		return descubierto;
	}
	public void setDescubierto(Long descubierto) {
		this.descubierto = descubierto;
	}
	public Long getDoble() {
		return doble;
	}
	public void setDoble(Long doble) {
		this.doble = doble;
	}
	public Long getEstarHabitacion() {
		return estarHabitacion;
	}
	public void setEstarHabitacion(Long estarHabitacion) {
		this.estarHabitacion = estarHabitacion;
	}
	public Long getEstudio() {
		return estudio;
	}
	public void setEstudio(Long estudio) {
		this.estudio = estudio;
	}
	public Long getNumeroHabitaciones() {
		return numeroHabitaciones;
	}
	public void setNumeroHabitaciones(Long numeroHabitaciones) {
		this.numeroHabitaciones = numeroHabitaciones;
	}
	public Long getNumeroIdentificacion() {
		return numeroIdentificacion;
	}
	public void setNumeroIdentificacion(Long numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}
	public Long getJardin() {
		return jardin;
	}
	public void setJardin(Long jardin) {
		this.jardin = jardin;
	}
	public Long getLocal() {
		return local;
	}
	public void setLocal(Long local) {
		this.local = local;
	}
	public Long getNumeroAscensores() {
		return numeroAscensores;
	}
	public void setNumeroAscensores(Long numeroAscensores) {
		this.numeroAscensores = numeroAscensores;
	}
	public Long getNumeroEdificios() {
		return numeroEdificios;
	}
	public void setNumeroEdificios(Long numeroEdificios) {
		this.numeroEdificios = numeroEdificios;
	}
	public Long getOficina() {
		return oficina;
	}
	public void setOficina(Long oficina) {
		this.oficina = oficina;
	}
	public Long getPatioInterior() {
		return patioInterior;
	}
	public void setPatioInterior(Long patioInterior) {
		this.patioInterior = patioInterior;
	}
	public Long getPisos() {
		return pisos;
	}
	public void setPisos(Long pisos) {
		this.pisos = pisos;
	}
	public Long getPrivado() {
		return privado;
	}
	public void setPrivado(Long privado) {
		this.privado = privado;
	}
	public Long getSala() {
		return sala;
	}
	public void setSala(Long sala) {
		this.sala = sala;
	}
	public Long getSencillo() {
		return sencillo;
	}
	public void setSencillo(Long sencillo) {
		this.sencillo = sencillo;
	}
	public Long getServidumbre() {
		return servidumbre;
	}
	public void setServidumbre(Long servidumbre) {
		this.servidumbre = servidumbre;
	}
	public Long getSotanos() {
		return sotanos;
	}
	public void setSotanos(Long sotanos) {
		this.sotanos = sotanos;
	}
	public Long getTerraza() {
		return terraza;
	}
	public void setTerraza(Long terraza) {
		this.terraza = terraza;
	}
	public Long getTipoComercializacion() {
		return tipoComercializacion;
	}
	public void setTipoComercializacion(Long tipoComercializacion) {
		this.tipoComercializacion = tipoComercializacion;
	}
	public BigDecimal getTotalAvaluo() {
		return totalAvaluo;
	}
	public void setTotalAvaluo(BigDecimal totalAvaluo) {
		this.totalAvaluo = totalAvaluo;
	}
	public Long getTotalGarajes() {
		return totalGarajes;
	}
	public void setTotalGarajes(Long totalGarajes) {
		this.totalGarajes = totalGarajes;
	}
	public Long getTotalUnidades() {
		return totalUnidades;
	}
	public void setTotalUnidades(Long totalUnidades) {
		this.totalUnidades = totalUnidades;
	}
	public Long getUnidadPorPiso() {
		return unidadPorPiso;
	}
	public void setUnidadPorPiso(Long unidadPorPiso) {
		this.unidadPorPiso = unidadPorPiso;
	}
	public Long getUsoExclusivo() {
		return usoExclusivo;
	}
	public void setUsoExclusivo(Long usoExclusivo) {
		this.usoExclusivo = usoExclusivo;
	}
	public BigDecimal getValorAsegurable() {
		return valorAsegurable;
	}
	public void setValorAsegurable(BigDecimal valorAsegurable) {
		this.valorAsegurable = valorAsegurable;
	}
	public BigDecimal getValorUvrDia() {
		return valorUvrDia;
	}
	public void setValorUvrDia(BigDecimal valorUvrDia) {
		this.valorUvrDia = valorUvrDia;
	}
	public BigDecimal getVetustez() {
		return vetustez;
	}
	public void setVetustez(BigDecimal vetustez) {
		this.vetustez = vetustez;
	}
	public Long getZonaVerdePrivada() {
		return zonaVerdePrivada;
	}
	public void setZonaVerdePrivada(Long zonaVerdePrivada) {
		this.zonaVerdePrivada = zonaVerdePrivada;
	}
	public Long getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(Long calificacion) {
		this.calificacion = calificacion;
	}
	public Long getEstadoConstruccion() {
		return estadoConstruccion;
	}
	public void setEstadoConstruccion(Long estadoConstruccion) {
		this.estadoConstruccion = estadoConstruccion;
	}
	public String getActualidadEdificadora() {
		return actualidadEdificadora;
	}
	public void setActualidadEdificadora(String actualidadEdificadora) {
		this.actualidadEdificadora = actualidadEdificadora;
	}
	public String getImpactoNegativoOtros() {
		return impactoNegativoOtros;
	}
	public void setImpactoNegativoOtros(String impactoNegativoOtros) {
		this.impactoNegativoOtros = impactoNegativoOtros;
	}
	public String getAmbientalOtros() {
		return ambientalOtros;
	}
	public void setAmbientalOtros(String ambientalOtros) {
		this.ambientalOtros = ambientalOtros;
	}
	public Long getAvanceObra() {
		return avanceObra;
	}
	public void setAvanceObra(Long avanceObra) {
		this.avanceObra = avanceObra;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	public String getChip() {
		return chip;
	}
	public void setChip(String chip) {
		this.chip = chip;
	}
	public String getComportamiento() {
		return comportamiento;
	}
	public void setComportamiento(String comportamiento) {
		this.comportamiento = comportamiento;
	}
	public String getTextoCubierta() {
		return textoCubierta;
	}
	public void setTextoCubierta(String textoCubierta) {
		this.textoCubierta = textoCubierta;
	}
	public String getDireccionAnexos() {
		return direccionAnexos;
	}
	public void setDireccionAnexos(String direccionAnexos) {
		this.direccionAnexos = direccionAnexos;
	}
	public String getDireccionInmueble() {
		return direccionInmueble;
	}
	public void setDireccionInmueble(String direccionInmueble) {
		this.direccionInmueble = direccionInmueble;
	}
	public String getTextoEstructura() {
		return textoEstructura;
	}
	public void setTextoEstructura(String textoEstructura) {
		this.textoEstructura = textoEstructura;
	}
	public String getTextoFachada() {
		return textoFachada;
	}
	public void setTextoFachada(String textoFachada) {
		this.textoFachada = textoFachada;
	}
	public List<HistoricoAvaluoFullDTO> getHistoricosAvaluo() {
		return historicosAvaluo;
	}
	public void setHistoricosAvaluo(List<HistoricoAvaluoFullDTO> historicosAvaluo) {
		this.historicosAvaluo = historicosAvaluo;
	}
	public List<LiquidacionAvaluoFullDTO> getLiquidacionAvaluos() {
		return liquidacionAvaluos;
	}
	public void setLiquidacionAvaluos(List<LiquidacionAvaluoFullDTO> liquidacionAvaluos) {
		this.liquidacionAvaluos = liquidacionAvaluos;
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
	public String getMatriculaInmobiliariaPpal1() {
		return matriculaInmobiliariaPpal1;
	}
	public void setMatriculaInmobiliariaPpal1(String matriculaInmobiliariaPpal1) {
		this.matriculaInmobiliariaPpal1 = matriculaInmobiliariaPpal1;
	}
	public String getMatriculaInmobiliariaPpal2() {
		return matriculaInmobiliariaPpal2;
	}
	public void setMatriculaInmobiliariaPpal2(String matriculaInmobiliariaPpal2) {
		this.matriculaInmobiliariaPpal2 = matriculaInmobiliariaPpal2;
	}
	public String getMatriculaInmobiliariaDeposito1() {
		return matriculaInmobiliariaDeposito1;
	}
	public void setMatriculaInmobiliariaDeposito1(String matriculaInmobiliariaDeposito1) {
		this.matriculaInmobiliariaDeposito1 = matriculaInmobiliariaDeposito1;
	}
	public String getMatriculaInmobiliariaDeposito2() {
		return matriculaInmobiliariaDeposito2;
	}
	public void setMatriculaInmobiliariaDeposito2(String matriculaInmobiliariaDeposito2) {
		this.matriculaInmobiliariaDeposito2 = matriculaInmobiliariaDeposito2;
	}
	public String getMatriculaInmobiliariaGaraje1() {
		return matriculaInmobiliariaGaraje1;
	}
	public void setMatriculaInmobiliariaGaraje1(String matriculaInmobiliariaGaraje1) {
		this.matriculaInmobiliariaGaraje1 = matriculaInmobiliariaGaraje1;
	}
	public String getMatriculaInmobiliariaGaraje2() {
		return matriculaInmobiliariaGaraje2;
	}
	public void setMatriculaInmobiliariaGaraje2(String matriculaInmobiliariaGaraje2) {
		this.matriculaInmobiliariaGaraje2 = matriculaInmobiliariaGaraje2;
	}
	public String getMatriculaInmobiliariaGaraje3() {
		return matriculaInmobiliariaGaraje3;
	}
	public void setMatriculaInmobiliariaGaraje3(String matriculaInmobiliariaGaraje3) {
		this.matriculaInmobiliariaGaraje3 = matriculaInmobiliariaGaraje3;
	}
	public String getMatriculaInmobiliariaGaraje4() {
		return matriculaInmobiliariaGaraje4;
	}
	public void setMatriculaInmobiliariaGaraje4(String matriculaInmobiliariaGaraje4) {
		this.matriculaInmobiliariaGaraje4 = matriculaInmobiliariaGaraje4;
	}
	public String getMatriculaInmobiliariaGaraje5() {
		return matriculaInmobiliariaGaraje5;
	}
	public void setMatriculaInmobiliariaGaraje5(String matriculaInmobiliariaGaraje5) {
		this.matriculaInmobiliariaGaraje5 = matriculaInmobiliariaGaraje5;
	}
	public String getNotaria() {
		return notaria;
	}
	public void setNotaria(String notaria) {
		this.notaria = notaria;
	}
	public String getNumeroEscritura() {
		return numeroEscritura;
	}
	public void setNumeroEscritura(String numeroEscritura) {
		this.numeroEscritura = numeroEscritura;
	}
	public String getOtrasDirecciones() {
		return otrasDirecciones;
	}
	public void setOtrasDirecciones(String otrasDirecciones) {
		this.otrasDirecciones = otrasDirecciones;
	}
	public String getOtroClase() {
		return otroClase;
	}
	public void setOtroClase(String otroClase) {
		this.otroClase = otroClase;
	}
	public String getOtrosDotacion() {
		return otrosDotacion;
	}
	public void setOtrosDotacion(String otrosDotacion) {
		this.otrosDotacion = otrosDotacion;
	}
	public Long getOtrosUsosBarrio() {
		return otrosUsosBarrio;
	}
	public void setOtrosUsosBarrio(Long otrosUsosBarrio) {
		this.otrosUsosBarrio = otrosUsosBarrio;
	}
	public String getOtroUsoInmueble() {
		return otroUsoInmueble;
	}
	public void setOtroUsoInmueble(String otroUsoInmueble) {
		this.otroUsoInmueble = otroUsoInmueble;
	}
	public String getPerspectivas() {
		return perspectivas;
	}
	public void setPerspectivas(String perspectivas) {
		this.perspectivas = perspectivas;
	}
	public Long getAltura() {
		return altura;
	}
	public void setAltura(Long altura) {
		this.altura = altura;
	}
	public Long getUbicacion3() {
		return ubicacion3;
	}
	public void setUbicacion3(Long ubicacion3) {
		this.ubicacion3 = ubicacion3;
	}
	public Long getReparadosBUA() {
		return reparadosBUA;
	}
	public void setReparadosBUA(Long reparadosBUA) {
		this.reparadosBUA = reparadosBUA;
	}
	public Long getIrregularidadAlturaBUA() {
		return irregularidadAlturaBUA;
	}
	public void setIrregularidadAlturaBUA(Long irregularidadAlturaBUA) {
		this.irregularidadAlturaBUA = irregularidadAlturaBUA;
	}
	public Long getIrregularidadPlantaBUA() {
		return irregularidadPlantaBUA;
	}
	public void setIrregularidadPlantaBUA(Long irregularidadPlantaBUA) {
		this.irregularidadPlantaBUA = irregularidadPlantaBUA;
	}
	public Long getEstructuraReforzadaBUA() {
		return estructuraReforzadaBUA;
	}
	public void setEstructuraReforzadaBUA(Long estructuraReforzadaBUA) {
		this.estructuraReforzadaBUA = estructuraReforzadaBUA;
	}
	public Long getDanoPrevioBUA() {
		return danoPrevioBUA;
	}
	public void setDanoPrevioBUA(Long danoPrevioBUA) {
		this.danoPrevioBUA = danoPrevioBUA;
	}
	public Long getAnoConstruccionBUA() {
		return anoConstruccionBUA;
	}
	public void setAnoConstruccionBUA(Long anoConstruccionBUA) {
		this.anoConstruccionBUA = anoConstruccionBUA;
	}
	public Long getMaterialConstruccionBUA() {
		return materialConstruccionBUA;
	}
	public void setMaterialConstruccionBUA(Long materialConstruccionBUA) {
		this.materialConstruccionBUA = materialConstruccionBUA;
	}
	public Long getDetalleMaterialSFBUA() {
		return detalleMaterialSFBUA;
	}
	public void setDetalleMaterialSFBUA(Long detalleMaterialSFBUA) {
		this.detalleMaterialSFBUA = detalleMaterialSFBUA;
	}
	public Long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}
	public Long getSimetriaPlanta() {
		return simetriaPlanta;
	}
	public void setSimetriaPlanta(Long simetriaPlanta) {
		this.simetriaPlanta = simetriaPlanta;
	}
	public Long getMaterialEstructura() {
		return materialEstructura;
	}
	public void setMaterialEstructura(Long materialEstructura) {
		this.materialEstructura = materialEstructura;
	}
	public String getOtroMaterial() {
		return otroMaterial;
	}
	public void setOtroMaterial(String otroMaterial) {
		this.otroMaterial = otroMaterial;
	}
	public Long getParapetosCubierta() {
		return parapetosCubierta;
	}
	public void setParapetosCubierta(Long parapetosCubierta) {
		this.parapetosCubierta = parapetosCubierta;
	}
	public Long getUbicacionTanque() {
		return ubicacionTanque;
	}
	public void setUbicacionTanque(Long ubicacionTanque) {
		this.ubicacionTanque = ubicacionTanque;
	}
	public String getOtraUbicacionTanque() {
		return otraUbicacionTanque;
	}
	public void setOtraUbicacionTanque(String otraUbicacionTanque) {
		this.otraUbicacionTanque = otraUbicacionTanque;
	}
	public Long getSobrePeso() {
		return sobrePeso;
	}
	public void setSobrePeso(Long sobrePeso) {
		this.sobrePeso = sobrePeso;
	}
	public Long getGolpeteo() {
		return golpeteo;
	}
	public void setGolpeteo(Long golpeteo) {
		this.golpeteo = golpeteo;
	}
	public Long getAsentamientos() {
		return asentamientos;
	}
	public void setAsentamientos(Long asentamientos) {
		this.asentamientos = asentamientos;
	}
	public Long getDanoPrevio() {
		return danoPrevio;
	}
	public void setDanoPrevio(Long danoPrevio) {
		this.danoPrevio = danoPrevio;
	}
	public Long getRequiereCondicionesSalubridad() {
		return requiereCondicionesSalubridad;
	}
	public void setRequiereCondicionesSalubridad(Long requiereCondicionesSalubridad) {
		this.requiereCondicionesSalubridad = requiereCondicionesSalubridad;
	}
	public Long getPisoInmueble() {
		return pisoInmueble;
	}
	public void setPisoInmueble(Long pisoInmueble) {
		this.pisoInmueble = pisoInmueble;
	}
	public Long getSimetriaAltura() {
		return simetriaAltura;
	}
	public void setSimetriaAltura(Long simetriaAltura) {
		this.simetriaAltura = simetriaAltura;
	}
	public Long getUbicacionFuentesHidricas() {
		return ubicacionFuentesHidricas;
	}
	public void setUbicacionFuentesHidricas(Long ubicacionFuentesHidricas) {
		this.ubicacionFuentesHidricas = ubicacionFuentesHidricas;
	}
	public Long getUbicacionNivelVia() {
		return ubicacionNivelVia;
	}
	public void setUbicacionNivelVia(Long ubicacionNivelVia) {
		this.ubicacionNivelVia = ubicacionNivelVia;
	}
	public Long getEdificacionesIguales() {
		return edificacionesIguales;
	}
	public void setEdificacionesIguales(Long edificacionesIguales) {
		this.edificacionesIguales = edificacionesIguales;
	}
	public Long getLuces() {
		return luces;
	}
	public void setLuces(Long luces) {
		this.luces = luces;
	}
	public Long getEdiContUso() {
		return ediContUso;
	}
	public void setEdiContUso(Long ediContUso) {
		this.ediContUso = ediContUso;
	}
	public Long getRangoConstruccionSF() {
		return rangoConstruccionSF;
	}
	public void setRangoConstruccionSF(Long rangoConstruccionSF) {
		this.rangoConstruccionSF = rangoConstruccionSF;
	}
	public String getCatastralSF() {
		return catastralSF;
	}
	public void setCatastralSF(String catastralSF) {
		this.catastralSF = catastralSF;
	}
	public Long getDanoReparadoSF() {
		return danoReparadoSF;
	}
	public void setDanoReparadoSF(Long danoReparadoSF) {
		this.danoReparadoSF = danoReparadoSF;
	}
	public Long getSimetriaAlturaSF() {
		return simetriaAlturaSF;
	}
	public void setSimetriaAlturaSF(Long simetriaAlturaSF) {
		this.simetriaAlturaSF = simetriaAlturaSF;
	}
	public Long getSimetriaPlantaSF() {
		return simetriaPlantaSF;
	}
	public void setSimetriaPlantaSF(Long simetriaPlantaSF) {
		this.simetriaPlantaSF = simetriaPlantaSF;
	}
	public Long getDanoPrevioSF() {
		return danoPrevioSF;
	}
	public void setDanoPrevioSF(Long danoPrevioSF) {
		this.danoPrevioSF = danoPrevioSF;
	}
	public Long getEstructuraReforzadaSF() {
		return estructuraReforzadaSF;
	}
	public void setEstructuraReforzadaSF(Long estructuraReforzadaSF) {
		this.estructuraReforzadaSF = estructuraReforzadaSF;
	}
	public Long getMaterialConstruccionSF() {
		return materialConstruccionSF;
	}
	public void setMaterialConstruccionSF(Long materialConstruccionSF) {
		this.materialConstruccionSF = materialConstruccionSF;
	}
	public Long getUsoInmuebleBUA() {
		return usoInmuebleBUA;
	}
	public void setUsoInmuebleBUA(Long usoInmuebleBUA) {
		this.usoInmuebleBUA = usoInmuebleBUA;
	}
	public Long getUsoInmuebleSF() {
		return usoInmuebleSF;
	}
	public void setUsoInmuebleSF(Long usoInmuebleSF) {
		this.usoInmuebleSF = usoInmuebleSF;
	}
	public String getNombreConstructora() {
		return nombreConstructora;
	}
	public void setNombreConstructora(String nombreConstructora) {
		this.nombreConstructora = nombreConstructora;
	}
	public Long getCodigoFinanciadoConstructor() {
		return codigoFinanciadoConstructor;
	}
	public void setCodigoFinanciadoConstructor(Long codigoFinanciadoConstructor) {
		this.codigoFinanciadoConstructor = codigoFinanciadoConstructor;
	}
	public String getSistemaCoordenadasTxtBUA() {
		return sistemaCoordenadasTxtBUA;
	}
	public void setSistemaCoordenadasTxtBUA(String sistemaCoordenadasTxtBUA) {
		this.sistemaCoordenadasTxtBUA = sistemaCoordenadasTxtBUA;
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
	
	
	
}
