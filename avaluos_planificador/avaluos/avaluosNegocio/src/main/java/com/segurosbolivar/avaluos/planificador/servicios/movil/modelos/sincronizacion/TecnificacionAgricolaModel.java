package com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion;


import java.util.List;

public class TecnificacionAgricolaModel  {

    private String idUnidadProductiva;
    private String codSolicitud;
    private String maquinariaDisponible;
    private String actividadMecanizadas;
    private String infraestructuras;
    private boolean transportePropio;
    private boolean transporteAlquilado;
    private String tipoTransportePropio;
    private List<ImagenesSolicitudModel> listImagenesSolicitudModel;

    public TecnificacionAgricolaModel() {
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

    public String getMaquinariaDisponible() {
        return maquinariaDisponible;
    }

    public void setMaquinariaDisponible(String maquinariaDisponible) {
        this.maquinariaDisponible = maquinariaDisponible;
    }

    public String getActividadMecanizadas() {
        return actividadMecanizadas;
    }

    public void setActividadMecanizadas(String actividadMecanizadas) {
        this.actividadMecanizadas = actividadMecanizadas;
    }

    public String getInfraestructuras() {
        return infraestructuras;
    }

    public void setInfraestructuras(String infraestructuras) {
        this.infraestructuras = infraestructuras;
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
}
