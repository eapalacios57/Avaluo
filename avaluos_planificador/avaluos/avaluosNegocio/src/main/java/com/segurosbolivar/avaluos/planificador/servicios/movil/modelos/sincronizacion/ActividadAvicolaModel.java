package com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion;


import java.util.List;

public class ActividadAvicolaModel {

	private String idUnidadProductiva;
    private String codSolicitud;
    private String lineaGenetica;
    private boolean productorIntegrado;
    private String integradoConQuien;
    private String proveedor;
    private double distanciaKM;
    private Integer numGalponesEngorde;
    private double areaGalponesEngorde;
    private Integer numGalponesLevante;
    private double areaGalponesLevante;
    private Integer numAvesM2;
    private String equipoDisponible;
    private String infraestructura;
    private String lugarVenta;
    private Integer ciclosProduccionAnio;
    private boolean transportePropio;
    private boolean transporteAlquilado;
    private String tipoTransportePropio;
    private String comentarios;
    private List<ImagenesSolicitudModel> listImagenesSolicitudModel;
    private double areaTotalGalpones;
    private String asistenciaTecnica;
    private Integer numGalponesPostura;
    private double areaGalponesPostura;
    
    public ActividadAvicolaModel() {
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

    public boolean isProductorIntegrado() {
        return productorIntegrado;
    }

    public void setProductorIntegrado(boolean productorIntegrado) {
        this.productorIntegrado = productorIntegrado;
    }

    public String getIntegradoConQuien() {
        return integradoConQuien;
    }

    public void setIntegradoConQuien(String integradoConQuien) {
        this.integradoConQuien = integradoConQuien;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public double getDistanciaKM() {
        return distanciaKM;
    }

    public void setDistanciaKM(double distanciaKM) {
        this.distanciaKM = distanciaKM;
    }

    public Integer getNumGalponesEngorde() {
        return numGalponesEngorde;
    }

    public void setNumGalponesEngorde(Integer numGalponesEngorde) {
        this.numGalponesEngorde = numGalponesEngorde;
    }

    public double getAreaGalponesEngorde() {
        return areaGalponesEngorde;
    }

    public void setAreaGalponesEngorde(double areaGalponesEngorde) {
        this.areaGalponesEngorde = areaGalponesEngorde;
    }

    public Integer getNumGalponesLevante() {
        return numGalponesLevante;
    }

    public void setNumGalponesLevante(Integer numGalponesLevante) {
        this.numGalponesLevante = numGalponesLevante;
    }

    public double getAreaGalponesLevante() {
        return areaGalponesLevante;
    }

    public void setAreaGalponesLevante(double areaGalponesLevante) {
        this.areaGalponesLevante = areaGalponesLevante;
    }

    public Integer getNumAvesM2() {
        return numAvesM2;
    }

    public void setNumAvesM2(Integer numAvesM2) {
        this.numAvesM2 = numAvesM2;
    }

    public String getEquipoDisponible() {
        return equipoDisponible;
    }

    public void setEquipoDisponible(String equipoDisponible) {
        this.equipoDisponible = equipoDisponible;
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

    public String getLineaGenetica() {
        return lineaGenetica;
    }

    public void setLineaGenetica(String lineaGenetica) {
        this.lineaGenetica = lineaGenetica;
    }

    public List<ImagenesSolicitudModel> getListImagenesSolicitudModel() {
        return listImagenesSolicitudModel;
    }

    public void setListImagenesSolicitudModel(List<ImagenesSolicitudModel> listImagenesSolicitudModel) {
        this.listImagenesSolicitudModel = listImagenesSolicitudModel;
    }

	public double getAreaTotalGalpones() {
		return areaTotalGalpones;
	}

	public void setAreaTotalGalpones(double areaTotalGalpones) {
		this.areaTotalGalpones = areaTotalGalpones;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
    public Integer getNumGalponesPostura() {
        return numGalponesPostura;
    }

    public void setNumGalponesPostura(Integer numGalponesPostura) {
        this.numGalponesPostura = numGalponesPostura;
    }

    public double getAreaGalponesPostura() {
        return areaGalponesPostura;
    }

    public void setAreaGalponesPostura(double areaGalponesPostura) {
        this.areaGalponesPostura = areaGalponesPostura;
    }
	public String getAsistenciaTecnica() {
		return asistenciaTecnica;
	}

	public void setAsistenciaTecnica(String asistenciaTecnica) {
		this.asistenciaTecnica = asistenciaTecnica;
	}
	
	
    
    
}
