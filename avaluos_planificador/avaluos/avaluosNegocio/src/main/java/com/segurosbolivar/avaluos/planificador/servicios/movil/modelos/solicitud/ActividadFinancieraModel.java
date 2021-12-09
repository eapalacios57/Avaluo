package com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.solicitud;


public class ActividadFinancieraModel {


    private String codActividadFinanciera;

    private String nombreActividadFinanciera;
    

    public ActividadFinancieraModel() {
    }

    public ActividadFinancieraModel(String codActividadFinanciera, String nombreActividadFinanciera) {
        this.codActividadFinanciera = codActividadFinanciera;
        this.nombreActividadFinanciera = nombreActividadFinanciera;
    }

    public String getCodActividadFinanciera() {
        return codActividadFinanciera;
    }

    public void setCodActividadFinanciera(String codActividadFinanciera) {
        this.codActividadFinanciera = codActividadFinanciera;
    }

    public String getNombreActividadFinanciera() {
        return nombreActividadFinanciera;
    }

    public void setNombreActividadFinanciera(String nombreActividadFinanciera) {
        this.nombreActividadFinanciera = nombreActividadFinanciera;
    }
}
