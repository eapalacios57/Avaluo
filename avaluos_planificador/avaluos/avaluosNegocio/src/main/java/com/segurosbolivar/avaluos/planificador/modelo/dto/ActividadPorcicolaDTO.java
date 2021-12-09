package com.segurosbolivar.avaluos.planificador.modelo.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Objects;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

/**
 * DAO que contiene la información de la entidad ActividadPorcicolaDTO que se
 * transmite por los servicios REST. Solo se transmiten los atributos simples,
 * es decir, se omiten aquellos atributos que definen relaciones con otras
 * entidades.
 * 
 * @author GeneradorCRUD
 */
@XmlRootElement
public class ActividadPorcicolaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9081893284297000065L;

	private BigDecimal idActividadPorcicola;

	private BigDecimal idUnidadProductiva;

	private String lineaGenetica;

	private String manejoResiduos;

	private String equiposDisponibles;

	private String infraestructura;

	private String lugarVenta;

	private BigDecimal ciclosProducccionAnio;

	private String transportePropio;

	private String transporteAlquilado;

	private String tipoTransportePropio;

	private String usuarioCreacion;

	private Date fechaCreacion;

	private String usuarioTransaccion;

	private Date fechaTransaccion;

	private String areaActividad;

	private String asistenciaTecnica;
	
	private String comentarios;

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end

	public ActividadPorcicolaDTO() {
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
	}

	@XmlElement(name = "idActividadPorcicola")
	public BigDecimal getIdActividadPorcicola() {
		return this.idActividadPorcicola;
	}

	@XmlElement(name = "idActividadPorcicola")
	public void setIdActividadPorcicola(BigDecimal idActividadPorcicola) {
		this.idActividadPorcicola = idActividadPorcicola;
	}

	@XmlElement(name = "idUnidadProductiva")
	public BigDecimal getIdUnidadProductiva() {
		return this.idUnidadProductiva;
	}

	@XmlElement(name = "idUnidadProductiva")
	public void setIdUnidadProductiva(BigDecimal idUnidadProductiva) {
		this.idUnidadProductiva = idUnidadProductiva;
	}

	@XmlElement(name = "lineaGenetica")
	public String getLineaGenetica() {
		return this.lineaGenetica;
	}

	@XmlElement(name = "lineaGenetica")
	public void setLineaGenetica(String lineaGenetica) {
		this.lineaGenetica = lineaGenetica;
	}

	@XmlElement(name = "manejoResiduos")
	public String getManejoResiduos() {
		return this.manejoResiduos;
	}

	@XmlElement(name = "manejoResiduos")
	public void setManejoResiduos(String manejoResiduos) {
		this.manejoResiduos = manejoResiduos;
	}

	@XmlElement(name = "equiposDisponibles")
	public String getEquiposDisponibles() {
		return this.equiposDisponibles;
	}

	@XmlElement(name = "equiposDisponibles")
	public void setEquiposDisponibles(String equiposDisponibles) {
		this.equiposDisponibles = equiposDisponibles;
	}

	@XmlElement(name = "infraestructura")
	public String getInfraestructura() {
		return this.infraestructura;
	}

	@XmlElement(name = "infraestructura")
	public void setInfraestructura(String infraestructura) {
		this.infraestructura = infraestructura;
	}

	@XmlElement(name = "lugarVenta")
	public String getLugarVenta() {
		return this.lugarVenta;
	}

	@XmlElement(name = "lugarVenta")
	public void setLugarVenta(String lugarVenta) {
		this.lugarVenta = lugarVenta;
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

	@XmlElement(name = "areaActividad")
	public String getAreaActividad() {
		return areaActividad;
	}

	@XmlElement(name = "areaActividad")
	public void setAreaActividad(String areaActividad) {
		this.areaActividad = areaActividad;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idActividadPorcicola);
		hash = 37 * hash + Objects.hashCode(this.idUnidadProductiva);
		hash = 37 * hash + Objects.hashCode(this.lineaGenetica);
		hash = 37 * hash + Objects.hashCode(this.manejoResiduos);
		hash = 37 * hash + Objects.hashCode(this.equiposDisponibles);
		hash = 37 * hash + Objects.hashCode(this.infraestructura);
		hash = 37 * hash + Objects.hashCode(this.lugarVenta);
		hash = 37 * hash + Objects.hashCode(this.ciclosProducccionAnio);
		hash = 37 * hash + Objects.hashCode(this.transportePropio);
		hash = 37 * hash + Objects.hashCode(this.transporteAlquilado);
		hash = 37 * hash + Objects.hashCode(this.tipoTransportePropio);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
		hash = 37 * hash + Objects.hashCode(this.areaActividad);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad ActividadPorcicolaDTO que se
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
		final ActividadPorcicolaDTO other = (ActividadPorcicolaDTO) obj;

		if (!Objects.equals(this.idActividadPorcicola, other.idActividadPorcicola)) {
			return false;
		}

		if (!Objects.equals(this.idUnidadProductiva, other.idUnidadProductiva)) {
			return false;
		}

		if (!Objects.equals(this.lineaGenetica, other.lineaGenetica)) {
			return false;
		}

		if (!Objects.equals(this.manejoResiduos, other.manejoResiduos)) {
			return false;
		}

		if (!Objects.equals(this.equiposDisponibles, other.equiposDisponibles)) {
			return false;
		}

		if (!Objects.equals(this.infraestructura, other.infraestructura)) {
			return false;
		}

		if (!Objects.equals(this.lugarVenta, other.lugarVenta)) {
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

		if (!Objects.equals(this.areaActividad, other.areaActividad)) {
			return false;
		}

		return Objects.equals(this.fechaTransaccion, other.fechaTransaccion);

	}

	public String getAsistenciaTecnica() {
		return asistenciaTecnica;
	}

	public void setAsistenciaTecnica(String asistenciaTecnica) {
		this.asistenciaTecnica = asistenciaTecnica;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
