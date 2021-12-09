package com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion;

import java.util.List;

public class ActividadPiscicolaModel {

	private String idUnidadProductiva;
    private String codSolicitud;
    private String especieCultivada;
    private Integer numEstanques;
    private Integer numAnimalesEstanques;
    private double areaProductivaEspejoAguaM2;
    private double profundidadM2;
    private String tipoProduccion;
    private String equipoDisponible;
    private String infraestructura;
    private String lugarVenta;
    private Integer ciclosProduccionAnio;
    private boolean transportePropio;
    private boolean transporteAlquilado;
    private String tipoTransportePropio;
    private String comentarios;
    private List<ImagenesSolicitudModel> listImagenesSolicitudModel;
    private String asistenciaTecnica;

    public ActividadPiscicolaModel() {
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

    public String getEspecieCultivada() {
        return especieCultivada;
    }

    public void setEspecieCultivada(String especieCultivada) {
        this.especieCultivada = especieCultivada;
    }

    public Integer getNumEstanques() {
        return numEstanques;
    }

    public void setNumEstanques(Integer numEstanques) {
        this.numEstanques = numEstanques;
    }

    public Integer getNumAnimalesEstanques() {
        return numAnimalesEstanques;
    }

    public void setNumAnimalesEstanques(Integer numAnimalesEstanques) {
        this.numAnimalesEstanques = numAnimalesEstanques;
    }

    public double getAreaProductivaEspejoAguaM2() {
        return areaProductivaEspejoAguaM2;
    }

    public void setAreaProductivaEspejoAguaM2(double areaProductivaEspejoAguaM2) {
        this.areaProductivaEspejoAguaM2 = areaProductivaEspejoAguaM2;
    }

    public double getProfundidadM2() {
        return profundidadM2;
    }

    public void setProfundidadM2(double profundidadM2) {
        this.profundidadM2 = profundidadM2;
    }

    public String getTipoProduccion() {
        return tipoProduccion;
    }

    public void setTipoProduccion(String tipoProduccion) {
        this.tipoProduccion = tipoProduccion;
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
