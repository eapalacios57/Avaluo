package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ACTIVIDAD_PISCICOLA database table.
 * 
 */
@Entity
@Cacheable(false)
@Table(name="ACTIVIDAD_PISCICOLA")
@NamedQuery(name="ActividadPiscicola.findAll", query="SELECT a FROM ActividadPiscicola a")
public class ActividadPiscicola implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SeqActividadPiscicola", sequenceName="SEQ_ACTIVIDAD_PISCICOLA", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SeqActividadPiscicola")
	@Column(name="ID_ACTIVIDAD_PISCICOLA")
	private BigDecimal idActividadPiscicola;

	@Column(name="AREA_PRODUCTIVA_ESPEJO_AGUA")
	private BigDecimal areaProductivaEspejoAgua;

	@Column(name="CICLOS_PRODUCCCION_ANIO")
	private BigDecimal ciclosProducccionAnio;

	@Column(name="LUGAR_VENTA")
	private String lugarVenta;
	
	@Column(name="EQUIPO_DISPONIBLE")
	private String equipoDisponible;

	@Column(name="ESPECIE_CULTIVADA")
	private String especieCultivada;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_TRANSACCION")
	private Date fechaTransaccion;

	private String infraestructura;

	@Column(name="NUMERO_ANIMALES_ESTANQUE")
	private BigDecimal numeroAnimalesEstanque;

	@Column(name="NUMERO_ESTANQUES")
	private BigDecimal numeroEstanques;

	private BigDecimal profundidad;

	@Column(name="TIPO_PRODUCCION")
	private String tipoProduccion;

	@Column(name="TIPO_TRANSPORTE_PROPIO")
	private String tipoTransportePropio;

	@Column(name="TRANSPORTE_ALQUILADO")
	private String transporteAlquilado;

	@Column(name="TRANSPORTE_PROPIO")
	private String transportePropio;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name="USUARIO_TRANSACCION")
	private String usuarioTransaccion;
	
	@Column(name="COMENTARIO")
	private String comentarios;
	
	@Column(name="ASISTENCIA_TECNICA")
	private String asistenciaTecnica;
	

	//bi-directional many-to-one association to UnidadProductiva
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="ID_UNIDAD_PRODUCTIVA")
	private UnidadProductiva unidadProductiva;

	public ActividadPiscicola() {
	}

	public BigDecimal getAreaProductivaEspejoAgua() {
		return this.areaProductivaEspejoAgua;
	}

	public void setAreaProductivaEspejoAgua(BigDecimal areaProductivaEspejoAgua) {
		this.areaProductivaEspejoAgua = areaProductivaEspejoAgua;
	}

	public BigDecimal getCiclosProducccionAnio() {
		return this.ciclosProducccionAnio;
	}

	public void setCiclosProducccionAnio(BigDecimal ciclosProducccionAnio) {
		this.ciclosProducccionAnio = ciclosProducccionAnio;
	}

	public String getEquipoDisponible() {
		return this.equipoDisponible;
	}

	public void setEquipoDisponible(String equipoDisponible) {
		this.equipoDisponible = equipoDisponible;
	}

	public String getEspecieCultivada() {
		return this.especieCultivada;
	}

	public void setEspecieCultivada(String especieCultivada) {
		this.especieCultivada = especieCultivada;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaTransaccion() {
		return this.fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public BigDecimal getIdActividadPiscicola() {
		return this.idActividadPiscicola;
	}

	public void setIdActividadPiscicola(BigDecimal idActividadPiscicola) {
		this.idActividadPiscicola = idActividadPiscicola;
	}

	public String getInfraestructura() {
		return this.infraestructura;
	}

	public void setInfraestructura(String infraestructura) {
		this.infraestructura = infraestructura;
	}

	public BigDecimal getNumeroAnimalesEstanque() {
		return this.numeroAnimalesEstanque;
	}

	public void setNumeroAnimalesEstanque(BigDecimal numeroAnimalesEstanque) {
		this.numeroAnimalesEstanque = numeroAnimalesEstanque;
	}

	public BigDecimal getNumeroEstanques() {
		return this.numeroEstanques;
	}

	public void setNumeroEstanques(BigDecimal numeroEstanques) {
		this.numeroEstanques = numeroEstanques;
	}

	public BigDecimal getProfundidad() {
		return this.profundidad;
	}

	public void setProfundidad(BigDecimal profundidad) {
		this.profundidad = profundidad;
	}

	public String getTipoProduccion() {
		return this.tipoProduccion;
	}

	public void setTipoProduccion(String tipoProduccion) {
		this.tipoProduccion = tipoProduccion;
	}

	public String getTipoTransportePropio() {
		return this.tipoTransportePropio;
	}

	public void setTipoTransportePropio(String tipoTransportePropio) {
		this.tipoTransportePropio = tipoTransportePropio;
	}

	public String getTransporteAlquilado() {
		return this.transporteAlquilado;
	}

	public void setTransporteAlquilado(String transporteAlquilado) {
		this.transporteAlquilado = transporteAlquilado;
	}

	public String getTransportePropio() {
		return this.transportePropio;
	}

	public void setTransportePropio(String transportePropio) {
		this.transportePropio = transportePropio;
	}

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioTransaccion() {
		return this.usuarioTransaccion;
	}

	public void setUsuarioTransaccion(String usuarioTransaccion) {
		this.usuarioTransaccion = usuarioTransaccion;
	}

	public UnidadProductiva getUnidadProductiva() {
		return this.unidadProductiva;
	}

	public void setUnidadProductiva(UnidadProductiva unidadProductiva) {
		this.unidadProductiva = unidadProductiva;
	}

	public String getLugarVenta() {
		return lugarVenta;
	}

	public void setLugarVenta(String lugarVenta) {
		this.lugarVenta = lugarVenta;
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
	
	
	
	

}