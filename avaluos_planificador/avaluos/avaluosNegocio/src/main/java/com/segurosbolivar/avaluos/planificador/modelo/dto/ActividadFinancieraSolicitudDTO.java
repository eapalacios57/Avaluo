package com.segurosbolivar.avaluos.planificador.modelo.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadFinanciera;
import com.segurosbolivar.avaluos.planificador.modelo.entity.ActividadFinancieraSolicitudPK;
import com.segurosbolivar.avaluos.planificador.modelo.entity.Solicitud;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;

/**
 * DAO que contiene la información de la entidad BeneficiarioDTO que se
 * transmite por los servicios REST. Solo se transmiten los atributos simples,
 * es decir, se omiten aquellos atributos que definen relaciones con otras
 * entidades.
 * 
 * @author GeneradorCRUD
 */
@XmlRootElement
public class ActividadFinancieraSolicitudDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal pk;

	private ActividadFinancieraSolicitudPK id;

	private String unidad;

	private BigDecimal cantidad;

	private BigDecimal valorProyecto;

	private BigDecimal valorCredito;

	private BigDecimal plazo;

	private BigDecimal periodoGracia;

	private String fechaInicioStr;

	private Date fechaInicio;

	private String fechaFinStr;

	private Date fechaFin;

	private String principal;

	private String usuarioCreacion;

	private String fechaCreacionStr;

	private Date fechaCreacion;

	private String usuarioTransaccion;

	private String fechaTransaccionStr;

	private Date fechaTransaccion;

	private String razonInversion;

	private Solicitud solicitud;

	private ActividadFinanciera actividadFinanciera;

	private List<ActividadFinancieraSolicitudDTO> historicos;

	// protected region atributos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region atributos adicionales end

	public ActividadFinancieraSolicitudDTO() {
		id = new ActividadFinancieraSolicitudPK();
		// protected region procedimientos adicionales de inicialización on begin
		// Escriba en esta sección sus modificaciones

		// protected region procedimientos adicionales de inicialización end
	}
	
	@XmlElement(name = "pk")
	public BigDecimal getPk() {
		return pk;
	}

	@XmlElement(name = "pk")
	public void setPk(BigDecimal pk) {
		this.pk = pk;
	}

	@XmlElement(name = "id")
	public ActividadFinancieraSolicitudPK getId() {
		return this.id;
	}

	@XmlElement(name = "id")
	public void setId(ActividadFinancieraSolicitudPK id) {
		this.id = id;
	}

	@XmlElement(name = "unidad")
	public String getUnidad() {
		return unidad;
	}

	@XmlElement(name = "unidad")
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	@XmlElement(name = "cantidad")
	public BigDecimal getCantidad() {
		return cantidad;
	}

	@XmlElement(name = "cantidad")
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	@XmlElement(name = "valorProyecto")
	public BigDecimal getValorProyecto() {
		return valorProyecto;
	}

	@XmlElement(name = "valorProyecto")
	public void setValorProyecto(BigDecimal valorProyecto) {
		this.valorProyecto = valorProyecto;
	}

	@XmlElement(name = "valorCredito")
	public BigDecimal getValorCredito() {
		return valorCredito;
	}

	@XmlElement(name = "valorCredito")
	public void setValorCredito(BigDecimal valorCredito) {
		this.valorCredito = valorCredito;
	}

	@XmlElement(name = "plazo")
	public BigDecimal getPlazo() {
		return plazo;
	}

	@XmlElement(name = "plazo")
	public void setPlazo(BigDecimal plazo) {
		this.plazo = plazo;
	}

	@XmlElement(name = "periodoGracia")
	public BigDecimal getPeriodoGracia() {
		return periodoGracia;
	}

	@XmlElement(name = "periodoGracia")
	public void setPeriodoGracia(BigDecimal periodoGracia) {
		this.periodoGracia = periodoGracia;
	}

	@XmlElement(name = "fechaInicioStr")
	public String getFechaInicioStr() {
		return fechaInicioStr;
	}

	@XmlElement(name = "fechaInicioStr")
	public void setFechaInicioStr(String fechaInicioStr) {
		this.fechaInicioStr = fechaInicioStr;
	}

	@XmlElement(name = "fechaFinStr")
	public String getFechaFinStr() {
		return fechaFinStr;
	}

	@XmlElement(name = "fechaFinStr")
	public void setFechaFinStr(String fechaFinStr) {
		this.fechaFinStr = fechaFinStr;
	}

	@XmlElement(name = "principal")
	public String getPrincipal() {
		return principal;
	}

	@XmlElement(name = "principal")
	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	@XmlElement(name = "usuarioCreacion")
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	@XmlElement(name = "usuarioCreacion")
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	@XmlElement(name = "fechaCreacionStr")
	public String getFechaCreacionStr() {
		return fechaCreacionStr;
	}

	@XmlElement(name = "fechaCreacionStr")
	public void setFechaCreacionStr(String fechaCreacionStr) {
		this.fechaCreacionStr = fechaCreacionStr;
	}

	@XmlElement(name = "usuarioTransaccion")
	public String getUsuarioTransaccion() {
		return usuarioTransaccion;
	}

	@XmlElement(name = "usuarioTransaccion")
	public void setUsuarioTransaccion(String usuarioTransaccion) {
		this.usuarioTransaccion = usuarioTransaccion;
	}

	@XmlElement(name = "fechaTransaccionStr")
	public String getFechaTransaccionStr() {
		return fechaTransaccionStr;
	}

	@XmlElement(name = "fechaTransaccionStr")
	public void setFechaTransaccionStr(String fechaTransaccionStr) {
		this.fechaTransaccionStr = fechaTransaccionStr;
	}

	@XmlElement(name = "fechaInicio")
	public Date getFechaInicio() {
		return fechaInicio;
	}

	@XmlElement(name = "fechaInicio")
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@XmlElement(name = "fechaFin")
	public Date getFechaFin() {
		return fechaFin;
	}

	@XmlElement(name = "fechaFin")
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@XmlElement(name = "fechaCreacion")
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	@XmlElement(name = "fechaCreacion")
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@XmlElement(name = "fechaTransaccion")
	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}

	@XmlElement(name = "fechaTransaccion")
	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	@XmlElement(name = "razonInversion")
	public String getRazonInversion() {
		return razonInversion;
	}

	@XmlElement(name = "razonInversion")
	public void setRazonInversion(String razonInversion) {
		this.razonInversion = razonInversion;
	}

	@XmlElement(name = "solicitud")
	public Solicitud getSolicitud() {
		return solicitud;
	}

	@XmlElement(name = "solicitud")
	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	@XmlElement(name = "actividadFinanciera")
	public ActividadFinanciera getActividadFinanciera() {
		return actividadFinanciera;
	}

	@XmlElement(name = "actividadFinanciera")
	public void setActividadFinanciera(ActividadFinanciera actividadFinanciera) {
		this.actividadFinanciera = actividadFinanciera;
	}

	@XmlElement(name = "historicos")
	public List<ActividadFinancieraSolicitudDTO> getHistoricos() {
		return historicos;
	}

	@XmlElement(name = "historicos")
	public void setHistoricos(List<ActividadFinancieraSolicitudDTO> historicos) {
		this.historicos = historicos;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 3;

		hash = 37 * hash + Objects.hashCode(this.id);
		hash = 37 * hash + Objects.hashCode(this.cantidad);
		hash = 37 * hash + Objects.hashCode(this.fechaCreacion);
		hash = 37 * hash + Objects.hashCode(this.fechaFin);
		hash = 37 * hash + Objects.hashCode(this.fechaInicio);
		hash = 37 * hash + Objects.hashCode(this.fechaTransaccion);
		hash = 37 * hash + Objects.hashCode(this.periodoGracia);
		hash = 37 * hash + Objects.hashCode(this.plazo);
		hash = 37 * hash + Objects.hashCode(this.principal);
		hash = 37 * hash + Objects.hashCode(this.unidad);
		hash = 37 * hash + Objects.hashCode(this.usuarioCreacion);
		hash = 37 * hash + Objects.hashCode(this.usuarioTransaccion);
		hash = 37 * hash + Objects.hashCode(this.valorCredito);
		hash = 37 * hash + Objects.hashCode(this.valorProyecto);

		return hash;

	}

	/**
	 * Valida la igualdad de la instancia de la entidad BeneficiarioDTO que se pasa
	 * como parámetro comprobando que comparten los mismos valores en cada uno de
	 * sus atributos. Solo se tienen en cuenta los atributos simples, es decir, se
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
		final ActividadFinancieraSolicitudDTO other = (ActividadFinancieraSolicitudDTO) obj;

		if (!Objects.equals(this.id, other.id)) {
			return false;
		}

		if (!Objects.equals(this.cantidad, other.cantidad)) {
			return false;
		}

		if (!Objects.equals(this.fechaCreacion, other.fechaCreacion)) {
			return false;
		}

		if (!Objects.equals(this.fechaFin, other.fechaFin)) {
			return false;
		}

		if (!Objects.equals(this.fechaInicio, other.fechaInicio)) {
			return false;
		}

		if (!Objects.equals(this.fechaTransaccion, other.fechaTransaccion)) {
			return false;
		}

		if (!Objects.equals(this.periodoGracia, other.periodoGracia)) {
			return false;
		}

		if (!Objects.equals(this.plazo, other.plazo)) {
			return false;
		}

		if (!Objects.equals(this.principal, other.principal)) {
			return false;
		}

		if (!Objects.equals(this.unidad, other.unidad)) {
			return false;
		}

		if (!Objects.equals(this.usuarioCreacion, other.usuarioCreacion)) {
			return false;
		}

		if (!Objects.equals(this.usuarioTransaccion, other.usuarioTransaccion)) {
			return false;
		}

		if (!Objects.equals(this.valorCredito, other.valorCredito)) {
			return false;
		}

		return Objects.equals(this.valorProyecto, other.valorProyecto);

	}

	// protected region metodos adicionales on begin
	// Escriba en esta sección sus modificaciones

	// protected region metodos adicionales end

}
