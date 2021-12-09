package com.segurosbolivar.avaluos.planificador.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadAvicola;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadGanadera;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadPiscicola;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadPorcicola;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Cultivo;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Predio;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Soporte;
import com.segurosbolivar.avaluos.planificador.modelo.entity.TecnificacionAgricola;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ValorPorcentaje;

/**
 * DAO que contiene la información de la entidad UnidadProductivaDTO que se transmite
 * por los servicios REST. Solo se transmiten los atributos simples, es decir,
 * se omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author GeneradorCRUD
 */
@XmlRootElement
public class UnidadProductivaDTO implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5555531713213574588L;

	private Long idUnidadProductiva;

	private String codigoSolicitud;
	
	private String tipoActividad;
	
	private String lugarInversion;
	
	private String nombre;
	
	private BigDecimal areaProyecto;
	
	private Long unidadIdUnidad;
	
	private String vereda;
	
	private String codigoDivipola;
	
	private String latitud;
	
	private String longitud;
	
	private String altitud;
	
	private String condicionViaAcceso;
	
	private String aspectosClimaticos;
	
	private String coberturaSistemaRiego;
	
	private String precipitacion;
	
	private String humedadRelativa;
	
	private String topografia;
	
	private String materiaOrganica;
	
	private String nivelDrenaje;
	
	private String areaProtegida;
	
	private String fuenteHidricaExistente;
	
	private String infraAlmacenamientoAgua;
	
	private BigDecimal idCiudad;
	
	private BigDecimal idDepartamento;
	
	private String usuarioCreacion;
	
	private Date fechaCreacion;
	
	private String usuarioTransaccion;
	
	private Date fechaTransaccion;
	
	private String nombreDistritoRiego;
	
	private List<Predio> predios;
	
	private List<Cultivo> cultivos;
	
	private List<ActividadAvicola> actividadAvicolas;

	private List<ActividadGanadera> actividadGanaderas;

	private List<ActividadPiscicola> actividadPiscicolas;

	private List<ActividadPorcicola> actividadPorcicolas; 

	private List<TecnificacionAgricola> tecnificacionAgricolas;
	
	private List<Soporte> soportes;
	
	private List<ValorPorcentaje> valorPorcentajes;

	private BigDecimal areaUtilizadaHA;

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end

	public List<ValorPorcentaje> getValorPorcentajes() {
		return valorPorcentajes;
	}

	public void setValorPorcentajes(List<ValorPorcentaje> valorPorcentajes) {
		this.valorPorcentajes = valorPorcentajes;
	}

	public List<Soporte> getSoportes() {
		return soportes;
	}

	public void setSoportes(List<Soporte> soportes) {
		this.soportes = soportes;
	}

	public List<Cultivo> getCultivos() {
		return cultivos;
	}

	public void setCultivos(List<Cultivo> cultivos) {
		this.cultivos = cultivos;
	}

	public List<ActividadAvicola> getActividadAvicolas() {
		return actividadAvicolas;
	}

	public void setActividadAvicolas(List<ActividadAvicola> actividadAvicolas) {
		this.actividadAvicolas = actividadAvicolas;
	}

	public List<ActividadGanadera> getActividadGanaderas() {
		return actividadGanaderas;
	}

	public void setActividadGanaderas(List<ActividadGanadera> actividadGanaderas) {
		this.actividadGanaderas = actividadGanaderas;
	}

	public List<ActividadPiscicola> getActividadPiscicolas() {
		return actividadPiscicolas;
	}

	public void setActividadPiscicolas(List<ActividadPiscicola> actividadPiscicolas) {
		this.actividadPiscicolas = actividadPiscicolas;
	}



	public List<ActividadPorcicola> getActividadPorcicolas() {
		return actividadPorcicolas;
	}



	public void setActividadPorcicolas(List<ActividadPorcicola> actividadPorcicolas) {
		this.actividadPorcicolas = actividadPorcicolas;
	}



	public List<TecnificacionAgricola> getTecnificacionAgricolas() {
		return tecnificacionAgricolas;
	}



	public void setTecnificacionAgricolas(List<TecnificacionAgricola> tecnificacionAgricolas) {
		this.tecnificacionAgricolas = tecnificacionAgricolas;
	}



	public String getCoberturaSistemaRiego() {
		return coberturaSistemaRiego;
	}



	public void setCoberturaSistemaRiego(String coberturaSistemaRiego) {
		this.coberturaSistemaRiego = coberturaSistemaRiego;
	}



	public List<Predio> getPredios() {
		return predios;
	}



	public void setPredios(List<Predio> predios) {
		this.predios = predios;
	}



	public String getNombreDistritoRiego() {
		return nombreDistritoRiego;
	}



	public void setNombreDistritoRiego(String nombreDistritoRiego) {
		this.nombreDistritoRiego = nombreDistritoRiego;
	}



	public UnidadProductivaDTO(){
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
    }


	
	@XmlElement(name = "areaUtilizadaHA")
	public BigDecimal getAreaUtilizadaHA() {
		return areaUtilizadaHA;
	}
	
	@XmlElement(name = "areaUtilizadaHA")
	public void setAreaUtilizadaHA(BigDecimal areaUtilizadaHA) {
		this.areaUtilizadaHA = areaUtilizadaHA;
	}

	@XmlElement(name = "idUnidadProductiva")
	public Long getIdUnidadProductiva() {
		return this.idUnidadProductiva;
	}
	
	@XmlElement(name="idUnidadProductiva")
	public void setIdUnidadProductiva(Long idUnidadProductiva){
		this.idUnidadProductiva = idUnidadProductiva;
	}
	
	@XmlElement(name="codigoSolicitud")
	public String getCodigoSolicitud(){
		return this.codigoSolicitud;
	}
	
	@XmlElement(name="codigoSolicitud")
	public void setCodigoSolicitud(String codigoSolicitud){
		this.codigoSolicitud = codigoSolicitud;
	}
		
	@XmlElement(name="tipoActividad")
	public String getTipoActividad(){
		return this.tipoActividad;
	}
	
	@XmlElement(name="tipoActividad")
	public void setTipoActividad(String tipoActividad){
		this.tipoActividad = tipoActividad;
	}
		
	@XmlElement(name="lugarInversion")
	public String getLugarInversion(){
		return this.lugarInversion;
	}
	
	@XmlElement(name="lugarInversion")
	public void setLugarInversion(String lugarInversion){
		this.lugarInversion = lugarInversion;
	}
		
	@XmlElement(name="nombre")
	public String getNombre(){
		return this.nombre;
	}
	
	@XmlElement(name="nombre")
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
		
	@XmlElement(name="areaProyecto")
	public BigDecimal getAreaProyecto(){
		return this.areaProyecto;
	}
	
	@XmlElement(name="areaProyecto")
	public void setAreaProyecto(BigDecimal areaProyecto){
		this.areaProyecto = areaProyecto;
	}
		
	@XmlElement(name="unidadIdUnidad")
	public Long getUnidadIdUnidad(){
		return this.unidadIdUnidad;
	}
	
	@XmlElement(name="unidadIdUnidad")
	public void setUnidadIdUnidad(Long unidadIdUnidad){
		this.unidadIdUnidad = unidadIdUnidad;
	}
		
	@XmlElement(name="vereda")
	public String getVereda(){
		return this.vereda;
	}
	
	@XmlElement(name="vereda")
	public void setVereda(String vereda){
		this.vereda = vereda;
	}
		
	@XmlElement(name="codigoDivipola")
	public String getCodigoDivipola(){
		return this.codigoDivipola;
	}
	
	@XmlElement(name="codigoDivipola")
	public void setCodigoDivipola(String codigoDivipola){
		this.codigoDivipola = codigoDivipola;
	}
		
	@XmlElement(name="latitud")
	public String getLatitud(){
		return this.latitud;
	}
	
	@XmlElement(name="latitud")
	public void setLatitud(String latitud){
		this.latitud = latitud;
	}
		
	@XmlElement(name="longitud")
	public String getLongitud(){
		return this.longitud;
	}
	
	@XmlElement(name="longitud")
	public void setLongitud(String longitud){
		this.longitud = longitud;
	}
		
	@XmlElement(name="altitud")
	public String getAltitud(){
		return this.altitud;
	}
	
	@XmlElement(name="altitud")
	public void setAltitud(String altitud){
		this.altitud = altitud;
	}
		
	@XmlElement(name="condicionViaAcceso")
	public String getCondicionViaAcceso(){
		return this.condicionViaAcceso;
	}
	
	@XmlElement(name="condicionViaAcceso")
	public void setCondicionViaAcceso(String condicionViaAcceso){
		this.condicionViaAcceso = condicionViaAcceso;
	}
		
	@XmlElement(name="aspectosClimaticos")
	public String getAspectosClimaticos(){
		return this.aspectosClimaticos;
	}
	
	@XmlElement(name="aspectosClimaticos")
	public void setAspectosClimaticos(String aspectosClimaticos){
		this.aspectosClimaticos = aspectosClimaticos;
	}
		
	@XmlElement(name="precipitacion")
	public String getPrecipitacion(){
		return this.precipitacion;
	}
	
	@XmlElement(name="precipitacion")
	public void setPrecipitacion(String precipitacion){
		this.precipitacion = precipitacion;
	}
		
	@XmlElement(name="humedadRelativa")
	public String getHumedadRelativa(){
		return this.humedadRelativa;
	}
	
	@XmlElement(name="humedadRelativa")
	public void setHumedadRelativa(String humedadRelativa){
		this.humedadRelativa = humedadRelativa;
	}
		
	@XmlElement(name="topografia")
	public String getTopografia(){
		return this.topografia;
	}
	
	@XmlElement(name="topografia")
	public void setTopografia(String topografia){
		this.topografia = topografia;
	}
		
	@XmlElement(name="materiaOrganica")
	public String getMateriaOrganica(){
		return this.materiaOrganica;
	}
	
	@XmlElement(name="materiaOrganica")
	public void setMateriaOrganica(String materiaOrganica){
		this.materiaOrganica = materiaOrganica;
	}
		
	@XmlElement(name="nivelDrenaje")
	public String getNivelDrenaje(){
		return this.nivelDrenaje;
	}
	
	@XmlElement(name="nivelDrenaje")
	public void setNivelDrenaje(String nivelDrenaje){
		this.nivelDrenaje = nivelDrenaje;
	}
		
	@XmlElement(name="areaProtegida")
	public String getAreaProtegida(){
		return this.areaProtegida;
	}
	
	@XmlElement(name="areaProtegida")
	public void setAreaProtegida(String areaProtegida){
		this.areaProtegida = areaProtegida;
	}
		
	@XmlElement(name="fuenteHidricaExistente")
	public String getFuenteHidricaExistente(){
		return this.fuenteHidricaExistente;
	}
	
	@XmlElement(name="fuenteHidricaExistente")
	public void setFuenteHidricaExistente(String fuenteHidricaExistente){
		this.fuenteHidricaExistente = fuenteHidricaExistente;
	}
		
	@XmlElement(name="infraAlmacenamientoAgua")
	public String getInfraAlmacenamientoAgua(){
		return this.infraAlmacenamientoAgua;
	}
	
	@XmlElement(name="infraAlmacenamientoAgua")
	public void setInfraAlmacenamientoAgua(String infraAlmacenamientoAgua){
		this.infraAlmacenamientoAgua = infraAlmacenamientoAgua;
	}
		
	@XmlElement(name="idCiudad")
	public BigDecimal getIdCiudad(){
		return this.idCiudad;
	}
	
	@XmlElement(name="idCiudad")
	public void setIdCiudad(BigDecimal idCiudad){
		this.idCiudad = idCiudad;
	}
		
	@XmlElement(name="idDepartamento")
	public BigDecimal getIdDepartamento(){
		return this.idDepartamento;
	}
	
	@XmlElement(name="idDepartamento")
	public void setIdDepartamento(BigDecimal idDepartamento){
		this.idDepartamento = idDepartamento;
	}
		
	@XmlElement(name="usuarioCreacion")
	public String getUsuarioCreacion(){
		return this.usuarioCreacion;
	}
	
	@XmlElement(name="usuarioCreacion")
	public void setUsuarioCreacion(String usuarioCreacion){
		this.usuarioCreacion = usuarioCreacion;
	}
		
	@XmlElement(name="fechaCreacion")
	public Date getFechaCreacion(){
		return this.fechaCreacion;
	}
	
	@XmlElement(name="fechaCreacion")
	public void setFechaCreacion(Date fechaCreacion){
		this.fechaCreacion = fechaCreacion;
	}
		
	@XmlElement(name="usuarioTransaccion")
	public String getUsuarioTransaccion(){
		return this.usuarioTransaccion;
	}
	
	@XmlElement(name="usuarioTransaccion")
	public void setUsuarioTransaccion(String usuarioTransaccion){
		this.usuarioTransaccion = usuarioTransaccion;
	}
		
	@XmlElement(name="fechaTransaccion")
	public Date getFechaTransaccion(){
		return this.fechaTransaccion;
	}

	@XmlElement(name = "fechaTransaccion")
	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idUnidadProductiva);
		hash = 37 * hash + Objects.hashCode(this.codigoSolicitud);
		hash = 37 * hash + Objects.hashCode(this.tipoActividad);
		hash = 37 * hash + Objects.hashCode(this.lugarInversion);
		hash = 37 * hash + Objects.hashCode(this.nombre);
		hash = 37 * hash + Objects.hashCode(this.areaProyecto);
		hash = 37 * hash + Objects.hashCode(this.unidadIdUnidad);
		hash = 37 * hash + Objects.hashCode(this.vereda);
		hash = 37 * hash + Objects.hashCode(this.codigoDivipola);
		hash = 37 * hash + Objects.hashCode(this.latitud);
		hash = 37 * hash + Objects.hashCode(this.longitud);
		hash = 37 * hash + Objects.hashCode(this.altitud);
		hash = 37 * hash + Objects.hashCode(this.condicionViaAcceso);
		hash = 37 * hash + Objects.hashCode(this.aspectosClimaticos);
		hash = 37 * hash + Objects.hashCode(this.precipitacion);
		hash = 37 * hash + Objects.hashCode(this.humedadRelativa);
		hash = 37 * hash + Objects.hashCode(this.topografia);
		hash = 37 * hash + Objects.hashCode(this.materiaOrganica);
		hash = 37 * hash + Objects.hashCode(this.nivelDrenaje);
		hash = 37 * hash + Objects.hashCode(this.areaProtegida);
		hash = 37 * hash + Objects.hashCode(this.fuenteHidricaExistente);
		hash = 37 * hash + Objects.hashCode(this.infraAlmacenamientoAgua);
		hash = 37 * hash + Objects.hashCode(this.idCiudad);
		hash = 37 * hash + Objects.hashCode(this.idDepartamento);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad UnidadProductivaDTO que se
	 * pasa como parámetro comprobando que comparten los mismos valores en cada uno
	 * de sus atributos. Solo se tienen en cuenta los atributos simples, es decir,
	 * se omiten aquellos que definen una relación con otra tabla.
	 *
	 * @param obj Instancia de la categoría a comprobar iguales.
	 * @return Verdadero si esta instancia y la que se pasan como parámetros son
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final UnidadProductivaDTO other = (UnidadProductivaDTO) obj;

		if (!Objects.equals(this.idUnidadProductiva, other.idUnidadProductiva)) {
			return false;
		}

		if (!Objects.equals(this.codigoSolicitud, other.codigoSolicitud)) {
			return false;
		}

		if (!Objects.equals(this.tipoActividad, other.tipoActividad)) {
			return false;
		}

		if (!Objects.equals(this.lugarInversion, other.lugarInversion)) {
			return false;
		}

		if (!Objects.equals(this.nombre, other.nombre)) {
			return false;
		}

		if (!Objects.equals(this.areaProyecto, other.areaProyecto)) {
			return false;
		}

		if (!Objects.equals(this.unidadIdUnidad, other.unidadIdUnidad)) {
			return false;
		}

		if (!Objects.equals(this.vereda, other.vereda)) {
			return false;
		}

		if (!Objects.equals(this.codigoDivipola, other.codigoDivipola)) {
			return false;
		}

		if (!Objects.equals(this.latitud, other.latitud)) {
			return false;
		}

		if (!Objects.equals(this.longitud, other.longitud)) {
			return false;
		}

		if (!Objects.equals(this.altitud, other.altitud)) {
			return false;
		}

		if (!Objects.equals(this.condicionViaAcceso, other.condicionViaAcceso)) {
			return false;
		}

		if (!Objects.equals(this.aspectosClimaticos, other.aspectosClimaticos)) {
			return false;
		}

		if (!Objects.equals(this.precipitacion, other.precipitacion)) {
			return false;
		}

		if (!Objects.equals(this.humedadRelativa, other.humedadRelativa)) {
			return false;
		}

		if (!Objects.equals(this.topografia, other.topografia)) {
			return false;
		}

		if (!Objects.equals(this.materiaOrganica, other.materiaOrganica)) {
			return false;
		}

		if (!Objects.equals(this.nivelDrenaje, other.nivelDrenaje)) {
			return false;
		}

		if (!Objects.equals(this.areaProtegida, other.areaProtegida)) {
			return false;
		}

		if (!Objects.equals(this.fuenteHidricaExistente, other.fuenteHidricaExistente)) {
			return false;
		}

		if (!Objects.equals(this.infraAlmacenamientoAgua, other.infraAlmacenamientoAgua)) {
			return false;
		}

		if (!Objects.equals(this.idCiudad, other.idCiudad)) {
			return false;
		}

		if (!Objects.equals(this.idDepartamento, other.idDepartamento)) {
			return false;
		}

		if (!Objects.equals(this.usuarioCreacion, other.usuarioCreacion)) {
			return false;
		}

		if (!Objects.equals(this.fechaCreacion, other.fechaCreacion)) {
			return false;
		}

		if (!Objects.equals(this.usuarioTransaccion, other.usuarioTransaccion)) {
			return false;
		}

		return Objects.equals(this.fechaTransaccion, other.fechaTransaccion);

	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

} 

