package com.segurosbolivar.avaluos.planificador.modelo.dto;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;
import java.util.Objects;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

/**
 * DAO que contiene la información de la entidad CultivoDTO que se transmite por
 * los servicios REST. Solo se transmiten los atributos simples, es decir, se
 * omiten aquellos atributos que definen relaciones con otras entidades.
 * 
 * @author GeneradorCRUD
 */
@XmlRootElement
public class CultivoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4699013607949855200L;

	private BigDecimal idCultivo;

	private BigDecimal idUnidadProductiva;

	private String producto;

	private String variedad;

	private Date fechaSiembra;

	private BigDecimal areaProductiva;

	private BigDecimal areaProductivaHa;

	private BigDecimal idAreaProductivaUnidad;

	private BigDecimal densidad;

	private BigDecimal idUnidad;

	private String mesesCosecha;

	private BigDecimal ciclosProduccionAnio;

	private String lugarVenta;

	private String rotaCultivo;

	private String comentario;
	
	private String asistenciaTecnica;

	private String usuarioCreacion;

	private Date fechaCreacion;

	private String usuarioTransaccion;

	private Date fechaTransaccion;
	
	private String cultivoAlterna;

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end

	public CultivoDTO() {
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
	}

	@XmlElement(name = "idCultivo")
	public BigDecimal getIdCultivo() {
		return this.idCultivo;
	}

	@XmlElement(name = "idCultivo")
	public void setIdCultivo(BigDecimal idCultivo) {
		this.idCultivo = idCultivo;
	}

	@XmlElement(name = "idUnidadProductiva")
	public BigDecimal getIdUnidadProductiva() {
		return this.idUnidadProductiva;
	}

	@XmlElement(name = "idUnidadProductiva")
	public void setIdUnidadProductiva(BigDecimal idUnidadProductiva) {
		this.idUnidadProductiva = idUnidadProductiva;
	}

	@XmlElement(name = "densidad")
	public BigDecimal getDensidad() {
		return this.densidad;
	}

	@XmlElement(name = "densidad")
	public void setDensidad(BigDecimal densidad) {
		this.densidad = densidad;
	}

	@XmlElement(name = "idUnidad")
	public BigDecimal getIdUnidad() {
		return this.idUnidad;
	}

	@XmlElement(name = "idUnidad")
	public void setIdUnidad(BigDecimal idUnidad) {
		this.idUnidad = idUnidad;
	}

	@XmlElement(name = "mesesCosecha")
	public String getMesesCosecha() {
		return this.mesesCosecha;
	}

	@XmlElement(name = "mesesCosecha")
	public void setMesesCosecha(String mesesCosecha) {
		this.mesesCosecha = mesesCosecha;
	}

	@XmlElement(name = "ciclosProduccionAnio")
	public BigDecimal getCiclosProduccionAnio() {
		return this.ciclosProduccionAnio;
	}

	@XmlElement(name = "ciclosProduccionAnio")
	public void setCiclosProduccionAnio(BigDecimal ciclosProduccionAnio) {
		this.ciclosProduccionAnio = ciclosProduccionAnio;
	}

	@XmlElement(name = "lugarVenta")
	public String getLugarVenta() {
		return this.lugarVenta;
	}

	@XmlElement(name = "lugarVenta")
	public void setLugarVenta(String lugarVenta) {
		this.lugarVenta = lugarVenta;
	}

	@XmlElement(name = "rotaCultivo")
	public String getRotaCultivo() {
		return this.rotaCultivo;
	}

	@XmlElement(name = "rotaCultivo")
	public void setRotaCultivo(String rotaCultivo) {
		this.rotaCultivo = rotaCultivo;
	}

	@XmlElement(name = "comentario")
	public String getComentario() {
		return this.comentario;
	}

	@XmlElement(name = "comentario")
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@XmlElement(name = "usuarioCreacion")
	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	@XmlElement(name = "asistenciaTecnica")
	public String getAsistenciaTecnica() {
		return asistenciaTecnica;
	}

	@XmlElement(name = "asistenciaTecnica")
	public void setAsistenciaTecnica(String asistenciaTecnica) {
		this.asistenciaTecnica = asistenciaTecnica;
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

	///////////////

	@XmlElement(name = "producto")
	public String getProducto() {
		return producto;
	}

	@XmlElement(name = "producto")
	public void setProducto(String producto) {
		this.producto = producto;
	}

	@XmlElement(name = "variedad")
	public String getVariedad() {
		return variedad;
	}

	@XmlElement(name = "variedad")
	public void setVariedad(String variedad) {
		this.variedad = variedad;
	}

	@XmlElement(name = "fechaSiembra")
	public Date getFechaSiembra() {
		return fechaSiembra;
	}

	@XmlElement(name = "fechaSiembra")
	public void setFechaSiembra(Date fechaSiembra) {
		this.fechaSiembra = fechaSiembra;
	}

	@XmlElement(name = "areaProductiva")
	public BigDecimal getAreaProductiva() {
		return areaProductiva;
	}

	@XmlElement(name = "areaProductiva")
	public void setAreaProductiva(BigDecimal areaProductiva) {
		this.areaProductiva = areaProductiva;
	}

	@XmlElement(name = "areaProductivaHa")
	public BigDecimal getAreaProductivaHa() {
		return areaProductivaHa;
	}

	@XmlElement(name = "areaProductivaHa")
	public void setAreaProductivaHa(BigDecimal areaProductivaHa) {
		this.areaProductivaHa = areaProductivaHa;
	}

	@XmlElement(name = "idAreaProductivaUnidad")
	public BigDecimal getIdAreaProductivaUnidad() {
		return idAreaProductivaUnidad;
	}

	@XmlElement(name = "idAreaProductivaUnidad")
	public void setIdAreaProductivaUnidad(BigDecimal idAreaProductivaUnidad) {
		this.idAreaProductivaUnidad = idAreaProductivaUnidad;
	}

	@XmlElement(name = "cultivoAlterna")
	public String getCultivoAlterna() {
		return cultivoAlterna;
	}

	@XmlElement(name = "cultivoAlterna")
	public void setCultivoAlterna(String cultivoAlterna) {
		this.cultivoAlterna = cultivoAlterna;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.idCultivo);
		hash = 37 * hash + Objects.hashCode(this.idUnidadProductiva);
		hash = 37 * hash + Objects.hashCode(this.producto);
		hash = 37 * hash + Objects.hashCode(this.variedad);
		hash = 37 * hash + Objects.hashCode(this.fechaSiembra);
		hash = 37 * hash + Objects.hashCode(this.areaProductiva);
		hash = 37 * hash + Objects.hashCode(this.areaProductivaHa);
		hash = 37 * hash + Objects.hashCode(this.idAreaProductivaUnidad);
		hash = 37 * hash + Objects.hashCode(this.densidad);
		hash = 37 * hash + Objects.hashCode(this.idUnidad);
		hash = 37 * hash + Objects.hashCode(this.mesesCosecha);
		hash = 37 * hash + Objects.hashCode(this.ciclosProduccionAnio);
		hash = 37 * hash + Objects.hashCode(this.lugarVenta);
		hash = 37 * hash + Objects.hashCode(this.rotaCultivo);
		hash = 37 * hash + Objects.hashCode(this.comentario);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);

		return hash;
	}

	/**
	 * Valida la igualdad de la instancia de la entidad CultivoDTO que se pasa como
	 * parámetro comprobando que comparten los mismos valores en cada uno de sus
	 * atributos. Solo se tienen en cuenta los atributos simples, es decir, se
	 * omiten aquellos que definen una relación con otra tabla.
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
		final CultivoDTO other = (CultivoDTO) obj;

		if (!Objects.equals(this.idCultivo, other.idCultivo)) {
			return false;
		}

		if (!Objects.equals(this.idUnidadProductiva, other.idUnidadProductiva)) {
			return false;
		}

		if (!Objects.equals(this.densidad, other.densidad)) {
			return false;
		}

		if (!Objects.equals(this.idUnidad, other.idUnidad)) {
			return false;
		}

		if (!Objects.equals(this.mesesCosecha, other.mesesCosecha)) {
			return false;
		}

		if (!Objects.equals(this.ciclosProduccionAnio, other.ciclosProduccionAnio)) {
			return false;
		}

		if (!Objects.equals(this.lugarVenta, other.lugarVenta)) {
			return false;
		}

		if (!Objects.equals(this.rotaCultivo, other.rotaCultivo)) {
			return false;
		}

		if (!Objects.equals(this.comentario, other.comentario)) {
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

		if (!Objects.equals(this.producto, other.producto)) {
			return false;
		}

		if (!Objects.equals(this.variedad, other.variedad)) {
			return false;
		}

		if (!Objects.equals(this.fechaSiembra, other.fechaSiembra)) {
			return false;
		}

		if (!Objects.equals(this.areaProductivaHa, other.areaProductivaHa)) {
			return false;
		}

		if (!Objects.equals(this.idAreaProductivaUnidad, other.idAreaProductivaUnidad)) {
			return false;
		}

		return Objects.equals(this.fechaTransaccion, other.fechaTransaccion);

	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
