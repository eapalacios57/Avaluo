package com.segurosbolivar.avaluos.planificador.modelo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the UNIDAD_PRODUCTIVA database table.
 * 
 */
@Entity
@Cacheable(false)
@Table(name="UNIDAD_PRODUCTIVA")
@NamedQueries({
	@NamedQuery(name="UnidadProductiva.findAll", query="SELECT u FROM UnidadProductiva u")
	//@NamedQuery(name="UnidadProductiva.findBySolicitud", query="SELECT u FROM UnidadProductiva u WHERE u.solicitud = :solicitud")
})
public class UnidadProductiva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SeqUnidadProductiva", sequenceName="SEQ_UNIDAD_PRODUCTIVA", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SeqUnidadProductiva")

	@Column(name="ID_UNIDAD_PRODUCTIVA")
	private long idUnidadProductiva;

	private String altitud;

	@Column(name="AREA_PROTEGIDA")
	private String areaProtegida;

	@Column(name="AREA_PROYECTO")
	private BigDecimal areaProyecto;

	@Column(name="ASPECTOS_CLIMATICOS")
	private String aspectosClimaticos;

	@Column(name="CONDICION_VIA_ACCESO")
	private String condicionViaAcceso;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_TRANSACCION")
	private Date fechaTransaccion;

	@Column(name="FUENTE_HIDRICA_EXISTENTE")
	private String fuenteHidricaExistente;

	@Column(name="HUMEDAD_RELATIVA")
	private String humedadRelativa;

	@Column(name="ID_CIUDAD")
	private BigDecimal idCiudad;

	@Column(name="ID_DEPARTAMENTO")
	private BigDecimal idDepartamento;

	@Column(name="INFRAEST_ALMACENAMIENTO_AGUA")
	private String infraestAlmacenamientoAgua;

	@Column(name="LATITUD")
	private String latitud;

	@Column(name="LONGITUD")
	private String longitud;

	@Column(name="LUGAR_INVERSION")
	private String lugarInversion;

	@Column(name="MATERIA_ORGANICA")
	private String materiaOrganica;

	@Column(name="NIVEL_DRENAJE")
	private String nivelDrenaje;

	@Column(name="NOMBRE")
	private String nombre;

	@Column(name="PRECIPITACION")
	private String precipitacion;

	@Column(name="TIPO_ACTIVIDAD")
	private String tipoActividad;

	@Column(name="TOPOGRAFIA")
	private String topografia;

	@Column(name="USUARIO_CREACION")
	private String usuarioCreacion;

	@Column(name="USUARIO_TRANSACCION")
	private String usuarioTransaccion;

	@Column(name="VEREDA")
	private String vereda;

	@Column(name="COBERTURA_SISTEMA_RIEGO")
	private String coberturaSistemaRiego;
	
	@Column(name="NOMBRE_DISTRITO_RIEGO")
	private String nombreDistritoRiego;
	
	
	@Column(name="AREA_UTILIZADA_HA")
	private BigDecimal areaUtilizadaHA;
	
	//bi-directional many-to-one association to ActividadAvicola
	@OneToMany(mappedBy="unidadProductiva")
	private List<ActividadAvicola> actividadAvicolas;

	//bi-directional many-to-one association to ActividadGanadera
	@OneToMany(mappedBy="unidadProductiva")
	private List<ActividadGanadera> actividadGanaderas;

	//bi-directional many-to-one association to ActividadPiscicola
	@OneToMany(mappedBy="unidadProductiva")
	private List<ActividadPiscicola> actividadPiscicolas;

	//bi-directional many-to-one association to ActividadPorcicola
	@OneToMany(mappedBy="unidadProductiva")
	private List<ActividadPorcicola> actividadPorcicolas;

	//bi-directional many-to-one association to Cultivo
	@OneToMany(mappedBy="unidadProductiva")
	private List<Cultivo> cultivos;

	//bi-directional many-to-one association to Predio
	@OneToMany(mappedBy="unidadProductiva")
	private List<Predio> predios;

	//bi-directional many-to-one association to Soporte
	
	@OneToMany(mappedBy="unidadProductiva")
	private List<Soporte> soportes;

	//bi-directional many-to-one association to TecnificacionAgricola
	@OneToMany(mappedBy="unidadProductiva")
	private List<TecnificacionAgricola> tecnificacionAgricolas;

	//bi-directional many-to-one association to Solicitud
	
	@ManyToOne
	@JoinColumn(name="CODIGO_SOLICITUD", updatable = false)
	private Solicitud solicitud;

	//bi-directional many-to-one association to Unidad
	
	@ManyToOne
	@JoinColumn(name="ID_UNIDAD", updatable = false)
	private Unidad unidad;

	//bi-directional many-to-one association to ValorPorcentaje
	@OneToMany(mappedBy="unidadProductiva")
	private List<ValorPorcentaje> valorPorcentajes;

	public UnidadProductiva() {
	}

	public long getIdUnidadProductiva() {
		return this.idUnidadProductiva;
	}

	public void setIdUnidadProductiva(long idUnidadProductiva) {
		this.idUnidadProductiva = idUnidadProductiva;
	}

	public String getAltitud() {
		return this.altitud;
	}

	public void setAltitud(String altitud) {
		this.altitud = altitud;
	}

	public String getAreaProtegida() {
		return this.areaProtegida;
	}

	public void setAreaProtegida(String areaProtegida) {
		this.areaProtegida = areaProtegida;
	}

	public BigDecimal getAreaProyecto() {
		return this.areaProyecto;
	}

	public void setAreaProyecto(BigDecimal areaProyecto) {
		this.areaProyecto = areaProyecto;
	}

	public String getAspectosClimaticos() {
		return this.aspectosClimaticos;
	}

	public void setAspectosClimaticos(String aspectosClimaticos) {
		this.aspectosClimaticos = aspectosClimaticos;
	}

	public String getCondicionViaAcceso() {
		return this.condicionViaAcceso;
	}

	public void setCondicionViaAcceso(String condicionViaAcceso) {
		this.condicionViaAcceso = condicionViaAcceso;
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

	public String getFuenteHidricaExistente() {
		return this.fuenteHidricaExistente;
	}

	public void setFuenteHidricaExistente(String fuenteHidricaExistente) {
		this.fuenteHidricaExistente = fuenteHidricaExistente;
	}

	public String getHumedadRelativa() {
		return this.humedadRelativa;
	}

	public void setHumedadRelativa(String humedadRelativa) {
		this.humedadRelativa = humedadRelativa;
	}

	public BigDecimal getIdCiudad() {
		return this.idCiudad;
	}

	public void setIdCiudad(BigDecimal idCiudad) {
		this.idCiudad = idCiudad;
	}

	public BigDecimal getIdDepartamento() {
		return this.idDepartamento;
	}

	public void setIdDepartamento(BigDecimal idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getInfraestAlmacenamientoAgua() {
		return this.infraestAlmacenamientoAgua;
	}

	public void setInfraestAlmacenamientoAgua(String infraestAlmacenamientoAgua) {
		this.infraestAlmacenamientoAgua = infraestAlmacenamientoAgua;
	}

	public String getLatitud() {
		return this.latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return this.longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getLugarInversion() {
		return this.lugarInversion;
	}

	public void setLugarInversion(String lugarInversion) {
		this.lugarInversion = lugarInversion;
	}

	public String getMateriaOrganica() {
		return this.materiaOrganica;
	}

	public void setMateriaOrganica(String materiaOrganica) {
		this.materiaOrganica = materiaOrganica;
	}

	public String getNivelDrenaje() {
		return this.nivelDrenaje;
	}

	public void setNivelDrenaje(String nivelDrenaje) {
		this.nivelDrenaje = nivelDrenaje;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrecipitacion() {
		return this.precipitacion;
	}

	public void setPrecipitacion(String precipitacion) {
		this.precipitacion = precipitacion;
	}

	public String getTipoActividad() {
		return this.tipoActividad;
	}

	public void setTipoActividad(String tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	public String getTopografia() {
		return this.topografia;
	}

	public void setTopografia(String topografia) {
		this.topografia = topografia;
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

	public String getVereda() {
		return this.vereda;
	}

	public void setVereda(String vereda) {
		this.vereda = vereda;
	}

	public List<ActividadAvicola> getActividadAvicolas() {
		return this.actividadAvicolas;
	}

	public void setActividadAvicolas(List<ActividadAvicola> actividadAvicolas) {
		this.actividadAvicolas = actividadAvicolas;
	}

	public ActividadAvicola addActividadAvicola(ActividadAvicola actividadAvicola) {
		getActividadAvicolas().add(actividadAvicola);
		actividadAvicola.setUnidadProductiva(this);

		return actividadAvicola;
	}

	public ActividadAvicola removeActividadAvicola(ActividadAvicola actividadAvicola) {
		getActividadAvicolas().remove(actividadAvicola);
		actividadAvicola.setUnidadProductiva(null);

		return actividadAvicola;
	}

	public List<ActividadGanadera> getActividadGanaderas() {
		return this.actividadGanaderas;
	}

	public void setActividadGanaderas(List<ActividadGanadera> actividadGanaderas) {
		this.actividadGanaderas = actividadGanaderas;
	}

	public ActividadGanadera addActividadGanadera(ActividadGanadera actividadGanadera) {
		getActividadGanaderas().add(actividadGanadera);
		actividadGanadera.setUnidadProductiva(this);

		return actividadGanadera;
	}

	public ActividadGanadera removeActividadGanadera(ActividadGanadera actividadGanadera) {
		getActividadGanaderas().remove(actividadGanadera);
		actividadGanadera.setUnidadProductiva(null);

		return actividadGanadera;
	}

	public List<ActividadPiscicola> getActividadPiscicolas() {
		return this.actividadPiscicolas;
	}

	public void setActividadPiscicolas(List<ActividadPiscicola> actividadPiscicolas) {
		this.actividadPiscicolas = actividadPiscicolas;
	}

	public ActividadPiscicola addActividadPiscicola(ActividadPiscicola actividadPiscicola) {
		getActividadPiscicolas().add(actividadPiscicola);
		actividadPiscicola.setUnidadProductiva(this);

		return actividadPiscicola;
	}

	public ActividadPiscicola removeActividadPiscicola(ActividadPiscicola actividadPiscicola) {
		getActividadPiscicolas().remove(actividadPiscicola);
		actividadPiscicola.setUnidadProductiva(null);

		return actividadPiscicola;
	}

	public List<ActividadPorcicola> getActividadPorcicolas() {
		return this.actividadPorcicolas;
	}

	public void setActividadPorcicolas(List<ActividadPorcicola> actividadPorcicolas) {
		this.actividadPorcicolas = actividadPorcicolas;
	}

	public ActividadPorcicola addActividadPorcicola(ActividadPorcicola actividadPorcicola) {
		getActividadPorcicolas().add(actividadPorcicola);
		actividadPorcicola.setUnidadProductiva(this);

		return actividadPorcicola;
	}

	public ActividadPorcicola removeActividadPorcicola(ActividadPorcicola actividadPorcicola) {
		getActividadPorcicolas().remove(actividadPorcicola);
		actividadPorcicola.setUnidadProductiva(null);

		return actividadPorcicola;
	}

	public List<Cultivo> getCultivos() {
		return this.cultivos;
	}

	public void setCultivos(List<Cultivo> cultivos) {
		this.cultivos = cultivos;
	}

	public Cultivo addCultivo(Cultivo cultivo) {
		getCultivos().add(cultivo);
		cultivo.setUnidadProductiva(this);

		return cultivo;
	}

	public Cultivo removeCultivo(Cultivo cultivo) {
		getCultivos().remove(cultivo);
		cultivo.setUnidadProductiva(null);

		return cultivo;
	}

	public List<Predio> getPredios() {
		return this.predios;
	}

	public void setPredios(List<Predio> predios) {
		this.predios = predios;
	}

	public Predio addPredio(Predio predio) {
		getPredios().add(predio);
		predio.setUnidadProductiva(this);

		return predio;
	}

	public Predio removePredio(Predio predio) {
		getPredios().remove(predio);
		predio.setUnidadProductiva(null);

		return predio;
	}

	public List<Soporte> getSoportes() {
		return this.soportes;
	}

	public void setSoportes(List<Soporte> soportes) {
		this.soportes = soportes;
	}

	public Soporte addSoporte(Soporte soporte) {
		getSoportes().add(soporte);
		soporte.setUnidadProductiva(this);

		return soporte;
	}

	public Soporte removeSoporte(Soporte soporte) {
		getSoportes().remove(soporte);
		soporte.setUnidadProductiva(null);

		return soporte;
	}

	public List<TecnificacionAgricola> getTecnificacionAgricolas() {
		return this.tecnificacionAgricolas;
	}

	public void setTecnificacionAgricolas(List<TecnificacionAgricola> tecnificacionAgricolas) {
		this.tecnificacionAgricolas = tecnificacionAgricolas;
	}

	public TecnificacionAgricola addTecnificacionAgricola(TecnificacionAgricola tecnificacionAgricola) {
		getTecnificacionAgricolas().add(tecnificacionAgricola);
		tecnificacionAgricola.setUnidadProductiva(this);

		return tecnificacionAgricola;
	}

	public TecnificacionAgricola removeTecnificacionAgricola(TecnificacionAgricola tecnificacionAgricola) {
		getTecnificacionAgricolas().remove(tecnificacionAgricola);
		tecnificacionAgricola.setUnidadProductiva(null);

		return tecnificacionAgricola;
	}

	public Solicitud getSolicitud() {
		return this.solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public Unidad getUnidad() {
		return this.unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public List<ValorPorcentaje> getValorPorcentajes() {
		return this.valorPorcentajes;
	}

	public void setValorPorcentajes(List<ValorPorcentaje> valorPorcentajes) {
		this.valorPorcentajes = valorPorcentajes;
	}

	public ValorPorcentaje addValorPorcentaje(ValorPorcentaje valorPorcentaje) {
		getValorPorcentajes().add(valorPorcentaje);
		valorPorcentaje.setUnidadProductiva(this);

		return valorPorcentaje;
	}

	public ValorPorcentaje removeValorPorcentaje(ValorPorcentaje valorPorcentaje) {
		getValorPorcentajes().remove(valorPorcentaje);
		valorPorcentaje.setUnidadProductiva(null);

		return valorPorcentaje;
	}

	public String getCoberturaSistemaRiego() {
		return coberturaSistemaRiego;
	}

	public void setCoberturaSistemaRiego(String coberturaSistemaRiego) {
		this.coberturaSistemaRiego = coberturaSistemaRiego;
	}

	public String getNombreDistritoRiego() {
		return nombreDistritoRiego;
	}

	public void setNombreDistritoRiego(String nombreDistritoRiego) {
		this.nombreDistritoRiego = nombreDistritoRiego;
	}

	public BigDecimal getAreaUtilizadaHA() {
		return areaUtilizadaHA;
	}

	public void setAreaUtilizadaHA(BigDecimal areaUtilizadaHA) {
		this.areaUtilizadaHA = areaUtilizadaHA;
	}

	
	
	
	
	

}