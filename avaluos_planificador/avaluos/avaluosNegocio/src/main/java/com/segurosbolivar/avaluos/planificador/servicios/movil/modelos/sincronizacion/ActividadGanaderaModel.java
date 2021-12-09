package com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion;

import java.util.List;

public class ActividadGanaderaModel {

	 private String idUnidadProductiva;
	    private String codSolicitud;
	    private String razaCruce;
	    private Integer cantidadPotreros;
	    private double areaPraderaMejorada;
	    private int areaPraderaMejoradaUnidad;
	    private double areaPraderaMejoradaHa;
	    private double areaActividad;
	    private int areaActividadUnidad;
	    private double areaActividadHa;
	    private String tipoPradera;
	    private String equiposDisponibles;
	    private String infraestructura;
	    private String lugarVenta;
	    private Integer ciclosProduccionAnio;
	    private boolean transportePropio;
	    private boolean transporteAlquilado;
	    private String tipoTransportePropio;
	    private String comentarios;
	    private List<ImagenesSolicitudModel> listImagenesSolicitudModel;
	    private String asistenciaTecnica;


    public ActividadGanaderaModel() {
    }

	public String getIdUnidadProductiva() {
		return idUnidadProductiva;
	}

	public void setIdUnidadProductiva(String idUnidadProductiva) {
		this.idUnidadProductiva = idUnidadProductiva;
	}

	public String getCodSolicitud() {
		return codSolicitud;
	}

	public void setCodSolicitud(String codSolicitud) {
		this.codSolicitud = codSolicitud;
	}

	public String getRazaCruce() {
		return razaCruce;
	}

	public void setRazaCruce(String razaCruce) {
		this.razaCruce = razaCruce;
	}

	public Integer getCantidadPotreros() {
		return cantidadPotreros;
	}

	public void setCantidadPotreros(Integer cantidadPotreros) {
		this.cantidadPotreros = cantidadPotreros;
	}

	public double getAreaPraderaMejorada() {
		return areaPraderaMejorada;
	}

	public void setAreaPraderaMejorada(double areaPraderaMejorada) {
		this.areaPraderaMejorada = areaPraderaMejorada;
	}

	public int getAreaPraderaMejoradaUnidad() {
		return areaPraderaMejoradaUnidad;
	}

	public void setAreaPraderaMejoradaUnidad(int areaPraderaMejoradaUnidad) {
		this.areaPraderaMejoradaUnidad = areaPraderaMejoradaUnidad;
	}

	public double getAreaPraderaMejoradaHa() {
		return areaPraderaMejoradaHa;
	}

	public void setAreaPraderaMejoradaHa(double areaPraderaMejoradaHa) {
		this.areaPraderaMejoradaHa = areaPraderaMejoradaHa;
	}

	public double getAreaActividad() {
		return areaActividad;
	}

	public void setAreaActividad(double areaActividad) {
		this.areaActividad = areaActividad;
	}

	public int getAreaActividadUnidad() {
		return areaActividadUnidad;
	}

	public void setAreaActividadUnidad(int areaActividadUnidad) {
		this.areaActividadUnidad = areaActividadUnidad;
	}

	public double getAreaActividadHa() {
		return areaActividadHa;
	}

	public void setAreaActividadHa(double areaActividadHa) {
		this.areaActividadHa = areaActividadHa;
	}

	public String getTipoPradera() {
		return tipoPradera;
	}

	public void setTipoPradera(String tipoPradera) {
		this.tipoPradera = tipoPradera;
	}

	public String getEquiposDisponibles() {
		return equiposDisponibles;
	}

	public void setEquiposDisponibles(String equiposDisponibles) {
		this.equiposDisponibles = equiposDisponibles;
	}

	public String getInfraestructura() {
		return infraestructura;
	}

	public void setInfraestructura(String infraestructura) {
		this.infraestructura = infraestructura;
	}

	public String getLugarVenta() {
		return lugarVenta;
	}

	public void setLugarVenta(String lugarVenta) {
		this.lugarVenta = lugarVenta;
	}

	public Integer getCiclosProduccionAnio() {
		return ciclosProduccionAnio;
	}

	public void setCiclosProduccionAnio(Integer ciclosProduccionAnio) {
		this.ciclosProduccionAnio = ciclosProduccionAnio;
	}

	public boolean isTransportePropio() {
		return transportePropio;
	}

	public void setTransportePropio(boolean transportePropio) {
		this.transportePropio = transportePropio;
	}

	public boolean isTransporteAlquilado() {
		return transporteAlquilado;
	}

	public void setTransporteAlquilado(boolean transporteAlquilado) {
		this.transporteAlquilado = transporteAlquilado;
	}

	public String getTipoTransportePropio() {
		return tipoTransportePropio;
	}

	public void setTipoTransportePropio(String tipoTransportePropio) {
		this.tipoTransportePropio = tipoTransportePropio;
	}

	public List<ImagenesSolicitudModel> getListImagenesSolicitudModel() {
		return listImagenesSolicitudModel;
	}

	public void setListImagenesSolicitudModel(List<ImagenesSolicitudModel> listImagenesSolicitudModel) {
		this.listImagenesSolicitudModel = listImagenesSolicitudModel;
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
