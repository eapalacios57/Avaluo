package com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion;

import java.util.List;

public class ActividadPorcicolaModel  {

	private String idUnidadProductiva;
    private String codSolicitud;
    private String lineaGenetica;
    private double areaActividadM;
    private String manejoResiduos;
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

    public ActividadPorcicolaModel() {
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

    public String getLineaGenetica() {
        return lineaGenetica;
    }

    public void setLineaGenetica(String lineaGenetica) {
        this.lineaGenetica = lineaGenetica;
    }

    public String getManejoResiduos() {
        return manejoResiduos;
    }

    public void setManejoResiduos(String manejoResiduos) {
        this.manejoResiduos = manejoResiduos;
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

	public double getAreaActividadM() {
		return areaActividadM;
	}

	public void setAreaActividadM(double areaActividadM) {
		this.areaActividadM = areaActividadM;
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
