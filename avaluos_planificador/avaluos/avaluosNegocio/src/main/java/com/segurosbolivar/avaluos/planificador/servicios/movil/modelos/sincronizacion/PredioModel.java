package com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion;


public class PredioModel {

    private String idPredio;
    private String codSolicitud;
    private String idUnidadProductiva;
    private String nombrePredio;
    private double areaPredio;
    private int idUnidad;
    

    public PredioModel() {
    }

    public String getIdPredio() {
        return idPredio;
    }

    public void setIdPredio(String idPredio) {
        this.idPredio = idPredio;
    }

    public String getIdUnidadProductiva() {
        return idUnidadProductiva;
    }

    public void setIdUnidadProductiva(String idUnidadProductiva) {
        this.idUnidadProductiva = idUnidadProductiva;
    }

    public String getNombrePredio() {
        return nombrePredio;
    }

    public void setNombrePredio(String nombrePredio) {
        this.nombrePredio = nombrePredio;
    }

    public double getAreaPredio() {
        return areaPredio;
    }

    public void setAreaPredio(double areaPredio) {
        this.areaPredio = areaPredio;
    }

    public String getCodSolicitud() {
        return codSolicitud;
    }

    public void setCodSolicitud(String codSolicitud) {
        this.codSolicitud = codSolicitud;
    }

	public int getIdUnidad() {
		return idUnidad;
	}

	public void setIdUnidad(int idUnidad) {
		this.idUnidad = idUnidad;
	}
    
    
}
