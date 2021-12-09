package com.segurosbolivar.avaluos.planificador.modelo.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Objects;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

/**
 * DAO que contiene la información de la entidad ActividadPiscicolaDTO que se
 * transmite por los servicios REST. Solo se transmiten los atributos simples,
 * es decir, se omiten aquellos atributos que definen relaciones con otras
 * entidades.
 * 
 * @author GeneradorCRUD
 */
@XmlRootElement
public class ActividadPiscicolaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9169953780561561941L;

	private BigDecimal idActividadPiscicola;

	private BigDecimal idUnidadProductiva;

	private String especieCultivada;

	private BigDecimal numeroEstanques;

	private BigDecimal numeroAnimalesEstanque;

	private BigDecimal areaProductivaEspejoAgua;

	private BigDecimal profundidad;

	private String tipoProduccion;

	private String equipoDisponible;

	private String infraestructura;

	private BigDecimal ciclosProducccionAnio;

	private String transportePropio;

	private String transporteAlquilado;

	private String tipoTransportePropio;

	private String usuarioCreacion;

	private Date fechaCreacion;

	private String usuarioTransaccion;

	private Date fechaTransaccion;

	private String lugarVenta;

	private String comentarios;

	private String asistenciaTecnica;

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end

	public ActividadPiscicolaDTO() {
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
	}

	@XmlElement(name = "lugarVenta")
	public String getLugarVenta() {
		return this.lugarVenta;
	}

	@XmlElement(name = "lugarVenta")
	public void setLugarVenta(String lugarVenta) {
		this.lugarVenta = lugarVenta;
	}

	@XmlElement(name = "idActividadPiscicola")
	public BigDecimal getIdActividadPiscicola() {
		return this.idActividadPiscicola;
	}

	@XmlElement(name = "idActividadPiscicola")
	public void setIdActividadPiscicola(BigDecimal idActividadPiscicola) {
		this.idActividadPiscicola = idActividadPiscicola;
	}

	@XmlElement(name = "idUnidadProductiva")
	public BigDecimal getIdUnidadProductiva() {
		return this.idUnidadProductiva;
	}

	@XmlElement(name = "idUnidadProductiva")
	public void setIdUnidadProductiva(BigDecimal idUnidadProductiva) {
		this.idUnidadProductiva = idUnidadProductiva;
	}

	@XmlElement(name = "especieCultivada")
	public String getEspecieCultivada() {
		return this.especieCultivada;
	}

	@XmlElement(name = "especieCultivada")
	public void setEspecieCultivada(String especieCultivada) {
		this.especieCultivada = especieCultivada;
	}

	@XmlElement(name = "numeroEstanques")
	public BigDecimal getNumeroEstanques() {
		return this.numeroEstanques;
	}

	@XmlElement(name = "numeroEstanques")
	public void setNumeroEstanques(BigDecimal numeroEstanques) {
		this.numeroEstanques = numeroEstanques;
	}

	@XmlElement(name = "numeroAnimalesEstanque")
	public BigDecimal getNumeroAnimalesEstanque() {
		return this.numeroAnimalesEstanque;
	}

	@XmlElement(name = "numeroAnimalesEstanque")
	public void setNumeroAnimalesEstanque(BigDecimal numeroAnimalesEstanque) {
		this.numeroAnimalesEstanque = numeroAnimalesEstanque;
	}

	@XmlElement(name = "areaProductivaEspejoAgua")
	public BigDecimal getAreaProductivaEspejoAgua() {
		return this.areaProductivaEspejoAgua;
	}

	@XmlElement(name = "areaProductivaEspejoAgua")
	public void setAreaProductivaEspejoAgua(BigDecimal areaProductivaEspejoAgua) {
		this.areaProductivaEspejoAgua = areaProductivaEspejoAgua;
	}

	@XmlElement(name = "profundidad")
	public BigDecimal getProfundidad() {
		return this.profundidad;
	}

	@XmlElement(name = "profundidad")
	public void setProfundidad(BigDecimal profundidad) {
		this.profundidad = profundidad;
	}

	@XmlElement(name = "tipoProduccion")
	public String getTipoProduccion() {
		return this.tipoProduccion;
	}

	@XmlElement(name = "tipoProduccion")
	public void setTipoProduccion(String tipoProduccion) {
		this.tipoProduccion = tipoProduccion;
	}

	@XmlElement(name = "equipoDisponible")
	public String getEquipoDisponible() {
		return this.equipoDisponible;
	}

	@XmlElement(name = "equipoDisponible")
	public void setEquipoDisponible(String equipoDisponible) {
		this.equipoDisponible = equipoDisponible;
	}

	@XmlElement(name = "infraestructura")
	public String getInfraestructura() {
		return this.infraestructura;
	}

	@XmlElement(name = "infraestructura")
	public void setInfraestructura(String infraestructura) {
		this.infraestructura = infraestructura;
	}

	@XmlElement(name = "ciclosProducccionAnio")
	public BigDecimal getCiclosProducccionAnio() {
		return this.ciclosProducccionAnio;
	}

	@XmlElement(name = "ciclosProducccionAnio")
	public void setCiclosProducccionAnio(BigDecimal ciclosProducccionAnio) {
		this.ciclosProducccionAnio = ciclosProducccionAnio;
	}

	@XmlElement(name = "transportePropio")
	public String getTransportePropio() {
		return this.transportePropio;
	}

	@XmlElement(name = "transportePropio")
	public void setTransportePropio(String transportePropio) {
		this.transportePropio = transportePropio;
	}

	@XmlElement(name = "transporteAlquilado")
	public String getTransporteAlquilado() {
		return this.transporteAlquilado;
	}

	@XmlElement(name = "transporteAlquilado")
	public void setTransporteAlquilado(String transporteAlquilado) {
		this.transporteAlquilado = transporteAlquilado;
	}

	@XmlElement(name = "tipoTransportePropio")
	public String getTipoTransportePropio() {
		return this.tipoTransportePropio;
	}

	@XmlElement(name = "tipoTransportePropio")
	public void setTipoTransportePropio(String tipoTransportePropio) {
		this.tipoTransportePropio = tipoTransportePropio;
	}

	@XmlElement(name = "usuarioCreacion")
	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	@XmlElement(name = "usuarioCreacion")
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	@XmlElement(name = "fechaCreacion")
	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	@XmlElement(name = "fechaCreacion")
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@XmlElement(name = "usuarioTransaccion")
	public String getUsuarioTransaccion() {
		return this.usuarioTransaccion;
	}

	@XmlElement(name = "usuarioTransaccion")
	public void setUsuarioTransaccion(String usuarioTransaccion) {
		this.usuarioTransaccion = usuarioTransaccion;
	}

	@XmlElement(name = "fechaTransaccion")
	public Date getFechaTransaccion() {
		return this.fechaTransaccion;
	}

	@XmlElement(name = "fechaTransaccion")
	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getAsistenciaTecnica() {
		return asistenciaTecnica;
	}

	public void setAsistenciaTecnica(String asistenciaTecnica) {
		this.asistenciaTecnica = asistenciaTecnica;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idActividadPiscicola);
		hash = 37 * hash + Objects.hashCode(this.idUnidadProductiva);
		hash = 37 * hash + Objects.hashCode(this.especieCultivada);
		hash = 37 * hash + Objects.hashCode(this.numeroEstanques);
		hash = 37 * hash + Objects.hashCode(this.numeroAnimalesEstanque);
		hash = 37 * hash + Objects.hashCode(this.areaProductivaEspejoAgua);
		hash = 37 * hash + Objects.hashCode(this.profundidad);
		hash = 37 * hash + Objects.hashCode(this.tipoProduccion);
		hash = 37 * hash + Objects.hashCode(this.equipoDisponible);
		hash = 37 * hash + Objects.hashCode(this.infraestructura);
		hash = 37 * hash + Objects.hashCode(this.ciclosProducccionAnio);
		hash = 37 * hash + Objects.hashCode(this.transportePropio);
		hash = 37 * hash + Objects.hashCode(this.transporteAlquilado);
		hash = 37 * hash + Objects.hashCode(this.tipoTransportePropio);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
		hash = 37 * hash + Objects.hashCode(this.lugarVenta);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad ActividadPiscicolaDTO que se
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
		final ActividadPiscicolaDTO other = (ActividadPiscicolaDTO) obj;

		if (!Objects.equals(this.idActividadPiscicola, other.idActividadPiscicola)) {
			return false;
		}

		if (!Objects.equals(this.idUnidadProductiva, other.idUnidadProductiva)) {
			return false;
		}

		if (!Objects.equals(this.especieCultivada, other.especieCultivada)) {
			return false;
		}

		if (!Objects.equals(this.numeroEstanques, other.numeroEstanques)) {
			return false;
		}

		if (!Objects.equals(this.numeroAnimalesEstanque, other.numeroAnimalesEstanque)) {
			return false;
		}

		if (!Objects.equals(this.areaProductivaEspejoAgua, other.areaProductivaEspejoAgua)) {
			return false;
		}

		if (!Objects.equals(this.profundidad, other.profundidad)) {
			return false;
		}

		if (!Objects.equals(this.tipoProduccion, other.tipoProduccion)) {
			return false;
		}

		if (!Objects.equals(this.equipoDisponible, other.equipoDisponible)) {
			return false;
		}

		if (!Objects.equals(this.infraestructura, other.infraestructura)) {
			return false;
		}

		if (!Objects.equals(this.ciclosProducccionAnio, other.ciclosProducccionAnio)) {
			return false;
		}

		if (!Objects.equals(this.transportePropio, other.transportePropio)) {
			return false;
		}

		if (!Objects.equals(this.transporteAlquilado, other.transporteAlquilado)) {
			return false;
		}

		if (!Objects.equals(this.tipoTransportePropio, other.tipoTransportePropio)) {
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

		if (!Objects.equals(this.lugarVenta, other.lugarVenta)) {
			return false;
		}

		return Objects.equals(this.fechaTransaccion, other.fechaTransaccion);

	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
