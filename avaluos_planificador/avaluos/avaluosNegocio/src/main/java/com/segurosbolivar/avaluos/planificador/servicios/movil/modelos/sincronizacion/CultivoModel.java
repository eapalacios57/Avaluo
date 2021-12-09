package com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion;

import java.util.List;

public class CultivoModel {


    private String idCultivo;
    private String idUnidadProductiva;
    private String codSolicitud;
    private String producto;
    private String variedad;
    private double areaProductiva;
    private int areaProductivaUnidad;
    private double areaProductivaHa;
    private String fechaSiembra;
    private double densidad;
    private String unidadPlantas;
    private String mesesCosecha;
    private Integer ciclosProduccionAnio;
    private String lugarVenta;
    private boolean rotaCultivo;
    private String cultivoAlterna;
    private String comentarios;
    private List<ImagenesSolicitudModel> listImagenesSolicitudModel;
    private String asistenciaTecnica;
    
    public CultivoModel() {
    }

    public String getIdCultivo() {
        return idCultivo;
    }

    public void setIdCultivo(String idCultivo) {
        this.idCultivo = idCultivo;
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

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getVariedad() {
		return variedad;
	}

	public void setVariedad(String variedad) {
		this.variedad = variedad;
	}

	public double getAreaProductiva() {
		return areaProductiva;
	}

	public void setAreaProductiva(double areaProductiva) {
		this.areaProductiva = areaProductiva;
	}

	public int getAreaProductivaUnidad() {
		return areaProductivaUnidad;
	}

	public void setAreaProductivaUnidad(int areaProductivaUnidad) {
		this.areaProductivaUnidad = areaProductivaUnidad;
	}

	public double getAreaProductivaHa() {
		return areaProductivaHa;
	}

	public void setAreaProductivaHa(double areaProductivaHa) {
		this.areaProductivaHa = areaProductivaHa;
	}

	public String getFechaSiembra() {
		return fechaSiembra;
	}

	public void setFechaSiembra(String fechaSiembra) {
		this.fechaSiembra = fechaSiembra;
	}

    public double getDensidad() {
        return densidad;
    }

    public void setDensidad(double densidad) {
        this.densidad = densidad;
    }

    public String getUnidadPlantas() {
        return unidadPlantas;
    }

    public void setUnidadPlantas(String unidadPlantas) {
        this.unidadPlantas = unidadPlantas;
    }

    public String getMesesCosecha() {
        return mesesCosecha;
    }

    public void setMesesCosecha(String mesesCosecha) {
        this.mesesCosecha = mesesCosecha;
    }

    public Integer getCiclosProduccionAnio() {
        return ciclosProduccionAnio;
    }

    public void setCiclosProduccionAnio(Integer ciclosProduccionAnio) {
        this.ciclosProduccionAnio = ciclosProduccionAnio;
    }

    public String getLugarVenta() {
        return lugarVenta;
    }

    public void setLugarVenta(String lugarVenta) {
        this.lugarVenta = lugarVenta;
    }

    public boolean isRotaCultivo() {
        return rotaCultivo;
    }

    public void setRotaCultivo(boolean rotaCultivo) {
        this.rotaCultivo = rotaCultivo;
    }

    public String getCultivoAlterna() {
        return cultivoAlterna;
    }

    public void setCultivoAlterna(String cultivoAlterna) {
        this.cultivoAlterna = cultivoAlterna;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public List<ImagenesSolicitudModel> getListImagenesSolicitudModel() {
        return listImagenesSolicitudModel;
    }

	public void setListImagenesSolicitudModel(List<ImagenesSolicitudModel> listImagenesSolicitudModel) {
		this.listImagenesSolicitudModel = listImagenesSolicitudModel;
	}
	
	public String getAsistenciaTecnica() {
		return asistenciaTecnica;
	}

	public void setAsistenciaTecnica(String asistenciaTecnica) {
		this.asistenciaTecnica = asistenciaTecnica;
	}
    
    
    
}
