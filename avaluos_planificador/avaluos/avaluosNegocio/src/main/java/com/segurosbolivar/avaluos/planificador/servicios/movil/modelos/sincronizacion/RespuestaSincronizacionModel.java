package com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion;

public class RespuestaSincronizacionModel {

    private String mensaje ;

    private Integer estatus ;

    public RespuestaSincronizacionModel() {
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }
}
