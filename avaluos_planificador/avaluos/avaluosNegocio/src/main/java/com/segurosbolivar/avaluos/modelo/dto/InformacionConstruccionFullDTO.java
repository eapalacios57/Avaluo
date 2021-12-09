package com.segurosbolivar.avaluos.modelo.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.segurosbolivar.avaluos.modelo.entity.Dominios;

public class InformacionConstruccionFullDTO {
	
	private Long idInformacionConstruccion;

	private Long idAvaluo;

	private Long asentamientos;

	private Long danoPrevio;

	private Long codigoNombreConstructora;

	private String nombreConstructora;

	private Long danoPrevioSF;

	private Long danoReparadoSF;

	private Long ubicacionTanque;

	private Long materialEstructura;

	private Long parapetosCubierta;

	private Long pisoInmueble;

	private Long simetriaAltura;

	private Long simetriaPlanta;

	private Long golpeteo;

	private Long luces;

	private String otraUbicacionTanque;

	private String otroMaterial;

	private Long rangoConstruccionSF;

	private Long sobrePeso;
	
	private Long ubicacionFuentesHidricas;

	private Long ubicacionNivelVia;

	private Long calidadBanio;

	private Long calidadMadera;

	private Long calidMetal;

	private Long calidadCocina;

	private Long calidadMuro;

	private Long calidadPiso;

	private Long calidadTecho;

	private Long conjuntoAgrupacionCerrada;

	private Long cubierta;

	private Long estadoBanios;

	private Long danoPrevioBUA;

	private Long detalleMaterialSFBUA;

	private Long estadoCocina;

	private Long estadoConservacion;

	private Long estadoMadera;

	private Long estadoMetal;

	private Long estadoMuros;

	private Long estadoPisos;

	private Long estructuraReforzadaSF;

	private Long estructura;

	private Long estructuraReforzadaBUA;

	private Long estructuraTechos;

	private Long fachada;

	private Long iluminacion;

	private Long irregularidadAlturaBUA;

	private Long irregularidadPlantaBUA;

	private Long materialConstruccionBUA;

	private List<Dominios> detallemat;

	private Long materialConstruccionSF;

	private Long pisosBodega;

	private Long propiedadHorizontal;

	private Long reparadosBUA;

	private Long tipoFachada;

	private Long ubicacionInmueble;

	private Long ventilacion;

	private Date fechaCreacion;

	private Date fechaTransaccion;

	private Long aireAcondicionado;

	private Long ascensor;

	private Long bicicletero;

	private Long bombaEyectora;

	private Long canchaMultiple;

	private Long citofono;

	private Long clubHouse;

	private Long enObra;

	private Long estadoRemodelacion;

	private Long estadoTerminadaNueva;

	private Long estadoTerminadoUsado;

	private Long gimnasio;

	private Long garajeVisitante;

	private Long golfito;

	private Long juegoNinos;

	private Long piscina;

	private Long planta;

	private Long porteria;

	private Long presion;

	private Long salonComunal;

	private Long shutBasuras;

	private Long sinTerminar;

	private Long squash;

	private Long tanqueAgua;

	private Long zonasVerdes;

	private Long anoConstruccionBUA;

	private Long bahiaComunal;

	private Long balcon;

	private Long banioPrivado;

	private Long banioServicio;

	private Long banioSocial;

	private Long bodega;

	private Long cocina;

	private Long comedor;

	private Long cuartoServicio;

	private Long cubierto;

	private Long deposito;

	private Long descubierto;

	private Long doble;

	private Long estarHabitacion;

	private Long estudio;

	private Long numeroHabitaciones;

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

	private Long totalGarajes;

	private Long totalUnidades;

	private Long unidadPorPiso;

	private Long usoExclusivo;

	private BigDecimal vetustez;

	private Long zonaVerdePrivada;

	private Long estadoConstruccion;

	private Long codigoFinanciadoConstructor;

	private Long simetriaAlturaSF;

	private Long simetriaPlantaSF;

	private Long avanceObra;

	private String textoCubierta;

	private String textoEstructura;

	private String textoFachada;

	private String otrosDotacion;

	private String usuarioCreacion;

	private String usuarioTransaccion;

	private Long altura;

	public Long getIdInformacionConstruccion() {
		return idInformacionConstruccion;
	}

	public void setIdInformacionConstruccion(Long idInformacionConstruccion) {
		this.idInformacionConstruccion = idInformacionConstruccion;
	}

	public Long getIdAvaluo() {
		return idAvaluo;
	}

	public void setIdAvaluo(Long idAvaluo) {
		this.idAvaluo = idAvaluo;
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

	public Long getCodigoNombreConstructora() {
		return codigoNombreConstructora;
	}

	public void setCodigoNombreConstructora(Long codigoNombreConstructora) {
		this.codigoNombreConstructora = codigoNombreConstructora;
	}

	public String getNombreConstructora() {
		return nombreConstructora;
	}

	public void setNombreConstructora(String nombreConstructora) {
		this.nombreConstructora = nombreConstructora;
	}

	public Long getDanoPrevioSF() {
		return danoPrevioSF;
	}

	public void setDanoPrevioSF(Long danoPrevioSF) {
		this.danoPrevioSF = danoPrevioSF;
	}

	public Long getDanoReparadoSF() {
		return danoReparadoSF;
	}

	public void setDanoReparadoSF(Long danoReparadoSF) {
		this.danoReparadoSF = danoReparadoSF;
	}

	public Long getUbicacionTanque() {
		return ubicacionTanque;
	}

	public void setUbicacionTanque(Long ubicacionTanque) {
		this.ubicacionTanque = ubicacionTanque;
	}

	public Long getMaterialEstructura() {
		return materialEstructura;
	}

	public void setMaterialEstructura(Long materialEstructura) {
		this.materialEstructura = materialEstructura;
	}

	public Long getParapetosCubierta() {
		return parapetosCubierta;
	}

	public void setParapetosCubierta(Long parapetosCubierta) {
		this.parapetosCubierta = parapetosCubierta;
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

	public Long getSimetriaPlanta() {
		return simetriaPlanta;
	}

	public void setSimetriaPlanta(Long simetriaPlanta) {
		this.simetriaPlanta = simetriaPlanta;
	}

	public Long getGolpeteo() {
		return golpeteo;
	}

	public void setGolpeteo(Long golpeteo) {
		this.golpeteo = golpeteo;
	}

	public Long getLuces() {
		return luces;
	}

	public void setLuces(Long luces) {
		this.luces = luces;
	}

	public String getOtraUbicacionTanque() {
		return otraUbicacionTanque;
	}

	public void setOtraUbicacionTanque(String otraUbicacionTanque) {
		this.otraUbicacionTanque = otraUbicacionTanque;
	}

	public String getOtroMaterial() {
		return otroMaterial;
	}

	public void setOtroMaterial(String otroMaterial) {
		this.otroMaterial = otroMaterial;
	}

	public Long getRangoConstruccionSF() {
		return rangoConstruccionSF;
	}

	public void setRangoConstruccionSF(Long rangoConstruccionSF) {
		this.rangoConstruccionSF = rangoConstruccionSF;
	}

	public Long getSobrePeso() {
		return sobrePeso;
	}

	public void setSobrePeso(Long sobrePeso) {
		this.sobrePeso = sobrePeso;
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

	public Long getEstadoBanios() {
		return estadoBanios;
	}

	public void setEstadoBanios(Long estadoBanios) {
		this.estadoBanios = estadoBanios;
	}

	public Long getDanoPrevioBUA() {
		return danoPrevioBUA;
	}

	public void setDanoPrevioBUA(Long danoPrevioBUA) {
		this.danoPrevioBUA = danoPrevioBUA;
	}

	public Long getDetalleMaterialSFBUA() {
		return detalleMaterialSFBUA;
	}

	public void setDetalleMaterialSFBUA(Long detalleMaterialSFBUA) {
		this.detalleMaterialSFBUA = detalleMaterialSFBUA;
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

	public Long getEstructuraReforzadaSF() {
		return estructuraReforzadaSF;
	}

	public void setEstructuraReforzadaSF(Long estructuraReforzadaSF) {
		this.estructuraReforzadaSF = estructuraReforzadaSF;
	}

	public Long getEstructura() {
		return estructura;
	}

	public void setEstructura(Long estructura) {
		this.estructura = estructura;
	}

	public Long getEstructuraReforzadaBUA() {
		return estructuraReforzadaBUA;
	}

	public void setEstructuraReforzadaBUA(Long estructuraReforzadaBUA) {
		this.estructuraReforzadaBUA = estructuraReforzadaBUA;
	}

	public Long getEstructuraTechos() {
		return estructuraTechos;
	}

	public void setEstructuraTechos(Long estructuraTechos) {
		this.estructuraTechos = estructuraTechos;
	}

	public Long getFachada() {
		return fachada;
	}

	public void setFachada(Long fachada) {
		this.fachada = fachada;
	}

	public Long getIluminacion() {
		return iluminacion;
	}

	public void setIluminacion(Long iluminacion) {
		this.iluminacion = iluminacion;
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

	public Long getMaterialConstruccionBUA() {
		return materialConstruccionBUA;
	}

	public void setMaterialConstruccionBUA(Long materialConstruccionBUA) {
		this.materialConstruccionBUA = materialConstruccionBUA;
	}

	public List<Dominios> getDetallemat() {
		return detallemat;
	}

	public void setDetallemat(List<Dominios> detallemat) {
		this.detallemat = detallemat;
	}

	public Long getMaterialConstruccionSF() {
		return materialConstruccionSF;
	}

	public void setMaterialConstruccionSF(Long materialConstruccionSF) {
		this.materialConstruccionSF = materialConstruccionSF;
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

	public Long getReparadosBUA() {
		return reparadosBUA;
	}

	public void setReparadosBUA(Long reparadosBUA) {
		this.reparadosBUA = reparadosBUA;
	}

	public Long getTipoFachada() {
		return tipoFachada;
	}

	public void setTipoFachada(Long tipoFachada) {
		this.tipoFachada = tipoFachada;
	}

	public Long getUbicacionInmueble() {
		return ubicacionInmueble;
	}

	public void setUbicacionInmueble(Long ubicacionInmueble) {
		this.ubicacionInmueble = ubicacionInmueble;
	}

	public Long getVentilacion() {
		return ventilacion;
	}

	public void setVentilacion(Long ventilacion) {
		this.ventilacion = ventilacion;
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

	public Long getAireAcondicionado() {
		return aireAcondicionado;
	}

	public void setAireAcondicionado(Long aireAcondicionado) {
		this.aireAcondicionado = aireAcondicionado;
	}

	public Long getAscensor() {
		return ascensor;
	}

	public void setAscensor(Long ascensor) {
		this.ascensor = ascensor;
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

	public Long getJuegoNinos() {
		return juegoNinos;
	}

	public void setJuegoNinos(Long juegoNinos) {
		this.juegoNinos = juegoNinos;
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

	public Long getZonasVerdes() {
		return zonasVerdes;
	}

	public void setZonasVerdes(Long zonasVerdes) {
		this.zonasVerdes = zonasVerdes;
	}

	public Long getAnoConstruccionBUA() {
		return anoConstruccionBUA;
	}

	public void setAnoConstruccionBUA(Long anoConstruccionBUA) {
		this.anoConstruccionBUA = anoConstruccionBUA;
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

	public Long getEstadoConstruccion() {
		return estadoConstruccion;
	}

	public void setEstadoConstruccion(Long estadoConstruccion) {
		this.estadoConstruccion = estadoConstruccion;
	}

	public Long getCodigoFinanciadoConstructor() {
		return codigoFinanciadoConstructor;
	}

	public void setCodigoFinanciadoConstructor(Long codigoFinanciadoConstructor) {
		this.codigoFinanciadoConstructor = codigoFinanciadoConstructor;
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

	public Long getAvanceObra() {
		return avanceObra;
	}

	public void setAvanceObra(Long avanceObra) {
		this.avanceObra = avanceObra;
	}

	public String getTextoCubierta() {
		return textoCubierta;
	}

	public void setTextoCubierta(String textoCubierta) {
		this.textoCubierta = textoCubierta;
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

	public String getOtrosDotacion() {
		return otrosDotacion;
	}

	public void setOtrosDotacion(String otrosDotacion) {
		this.otrosDotacion = otrosDotacion;
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

	public Long getAltura() {
		return altura;
	}

	public void setAltura(Long altura) {
		this.altura = altura;
	}

}
