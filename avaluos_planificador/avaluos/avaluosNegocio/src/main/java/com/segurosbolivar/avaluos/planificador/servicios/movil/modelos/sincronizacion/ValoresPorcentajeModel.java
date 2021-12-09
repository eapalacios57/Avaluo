package com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion;


public class ValoresPorcentajeModel {

    private String idUnidadProductiva;
    private String codSolicitud;
    private String concepto;
    private String porcentage;


    public ValoresPorcentajeModel() {
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

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getPorcentage() {
        return porcentage;
    }

    public void setPorcentage(String porcentage) {
        this.porcentage = porcentage;
    }
}
