package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ACTIVIDAD_PORCICOLA database table.
 * 
 */
@Entity
@Cacheable(false)
@Table(name="ACTIVIDAD_PORCICOLA")
@NamedQuery(name="ActividadPorcicola.findAll", query="SELECT a FROM ActividadPorcicola a")
public class ActividadPorcicola implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SeqActividadPorcicola", sequenceName="SEQ_ACTIVIDAD_PORCICOLA", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SeqActividadPorcicola")
	@Column(name="ID_ACTIVIDAD_PORCICOLA")
	private BigDecimal idActividadPorcicola;

	@Column(name="CICLOS_PRODUCCCION_ANIO")
	private BigDecimal ciclosProducccionAnio;

	@Column(name="EQUIPOS_DISPONIBLES")
	private String equiposDisponibles;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_TRANSACCION")
	private Date fechaTransaccion;

	private String infraestructura;

	@Column(name="LINEA_GENETICA")
	private String lineaGenetica;

	@Column(name="LUGAR_VENTA")
	private String lugarVenta;

	@Column(name="MANEJO_RESIDUOS")
	private String manejoResiduos;

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
	
	@Column(name="AREA_ACTIVIDAD")
	private BigDecimal areaActividad;
	
	@Column(name="COMENTARIO")
	private String comentarios;
	
	@Column(name="ASISTENCIA_TECNICA")
	private String asistenciaTecnica;
	

	//bi-directional many-to-one association to UnidadProductiva
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="ID_UNIDAD_PRODUCTIVA")
	private UnidadProductiva unidadProductiva;

	public ActividadPorcicola() {
	}

	public BigDecimal getCiclosProducccionAnio() {
		return this.ciclosProducccionAnio;
	}

	public void setCiclosProducccionAnio(BigDecimal ciclosProducccionAnio) {
		this.ciclosProducccionAnio = ciclosProducccionAnio;
	}

	public String getEquiposDisponibles() {
		return this.equiposDisponibles;
	}

	public void setEquiposDisponibles(String equiposDisponibles) {
		this.equiposDisponibles = equiposDisponibles;
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

	public BigDecimal getIdActividadPorcicola() {
		return this.idActividadPorcicola;
	}

	public void setIdActividadPorcicola(BigDecimal idActividadPorcicola) {
		this.idActividadPorcicola = idActividadPorcicola;
	}

	public String getInfraestructura() {
		return this.infraestructura;
	}

	public void setInfraestructura(String infraestructura) {
		this.infraestructura = infraestructura;
	}

	public String getLineaGenetica() {
		return this.lineaGenetica;
	}

	public void setLineaGenetica(String lineaGenetica) {
		this.lineaGenetica = lineaGenetica;
	}

	public String getLugarVenta() {
		return this.lugarVenta;
	}

	public void setLugarVenta(String lugarVenta) {
		this.lugarVenta = lugarVenta;
	}

	public String getManejoResiduos() {
		return this.manejoResiduos;
	}

	public void setManejoResiduos(String manejoResiduos) {
		this.manejoResiduos = manejoResiduos;
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

	public BigDecimal getAreaActividad() {
		return areaActividad;
	}

	public void setAreaActividad(BigDecimal areaActividad) {
		this.areaActividad = areaActividad;
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