package com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion;

import java.math.BigDecimal;
import java.util.List;

public class UnidadProductivaModel  {

    private String idUnidadProductiva;
    private String codSolicitud;
    private Integer tipoActividad;
    private boolean lugarInversion;
    private String nombreUnidadProductiva;
    private double areaProyecto;
    private Integer unidadAreaProyecto;
    private double areaProyectoHA;
    private String nombreVereda;
    private String municipioDepartamento;
    private String latitud;
    private String longitud;
    private String altitud;
    private String condicionesViaAcceso;
    private String aspectoClimatico;
    private String precipitacionAnual;
    private String humedadRelativa;
    private String topografiaPredominante;
    private String contenidoMateriaOrganica;
    private String nivelDrenaje;
    private String areasProtegidasHA;
    private String fuentesHidricasExistentes;
    private String infraestructuraAlmacenamientoAgua;
    private String coberturaSistemaRiego;
    private String nombreDistritoRiego;
    private boolean caracteristicasUnidad;
    private boolean tecnificacionAgricola;
    private boolean cultivos;
    private boolean actividadPecuaria;
    private List<PredioModel> listPredioModel;
    private List<ValoresPorcentajeModel> listValoresPorcentajeModel;
    private TecnificacionAgricolaModel tecnificacionAgricolaModel;
    private List<CultivoModel> listCultivoModel;
    private ActividadGanaderaModel actividadGanaderaModel;
    private ActividadAvicolaModel actividadAvicolaModel;
    private ActividadPiscicolaModel actividadPiscicolaModel;
    private ActividadPorcicolaModel actividadPorcicolaModel;
    private List<ImagenesSolicitudModel> listImagenesSolicitudModel;
    private double areaUtilizadaHa;


    public UnidadProductivaModel() {
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

    public Integer getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(Integer tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public boolean isLugarInversion() {
        return lugarInversion;
    }

    public void setLugarInversion(boolean lugarInversion) {
        this.lugarInversion = lugarInversion;
    }

    public String getNombreUnidadProductiva() {
        return nombreUnidadProductiva;
    }

    public void setNombreUnidadProductiva(String nombreUnidadProductiva) {
        this.nombreUnidadProductiva = nombreUnidadProductiva;
    }

    public double getAreaProyecto() {
        return areaProyecto;
    }

    public void setAreaProyecto(double areaProyecto) {
        this.areaProyecto = areaProyecto;
    }

    public Integer getUnidadAreaProyecto() {
        return unidadAreaProyecto;
    }

    public void setUnidadAreaProyecto(Integer unidadAreaProyecto) {
        this.unidadAreaProyecto = unidadAreaProyecto;
    }

    public double getAreaProyectoHA() {
        return areaProyectoHA;
    }

    public void setAreaProyectoHA(double areaProyectoHA) {
        this.areaProyectoHA = areaProyectoHA;
    }

    public String getNombreVereda() {
        return nombreVereda;
    }

    public void setNombreVereda(String nombreVereda) {
        this.nombreVereda = nombreVereda;
    }

    public String getMunicipioDepartamento() {
        return municipioDepartamento;
    }

    public void setMunicipioDepartamento(String municipioDepartamento) {
        this.municipioDepartamento = municipioDepartamento;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getAltitud() {
        return altitud;
    }

    public void setAltitud(String altitud) {
        this.altitud = altitud;
    }

    public String getCondicionesViaAcceso() {
        return condicionesViaAcceso;
    }

    public void setCondicionesViaAcceso(String condicionesViaAcceso) {
        this.condicionesViaAcceso = condicionesViaAcceso;
    }

    public String getAspectoClimatico() {
        return aspectoClimatico;
    }

    public void setAspectoClimatico(String aspectoClimatico) {
        this.aspectoClimatico = aspectoClimatico;
    }

    public String getPrecipitacionAnual() {
        return precipitacionAnual;
    }

    public void setPrecipitacionAnual(String precipitacionAnual) {
        this.precipitacionAnual = precipitacionAnual;
    }

    public String getHumedadRelativa() {
        return humedadRelativa;
    }

    public void setHumedadRelativa(String humedadRelativa) {
        this.humedadRelativa = humedadRelativa;
    }

    public String getTopografiaPredominante() {
        return topografiaPredominante;
    }

    public void setTopografiaPredominante(String topografiaPredominante) {
        this.topografiaPredominante = topografiaPredominante;
    }

    public String getContenidoMateriaOrganica() {
        return contenidoMateriaOrganica;
    }

    public void setContenidoMateriaOrganica(String contenidoMateriaOrganica) {
        this.contenidoMateriaOrganica = contenidoMateriaOrganica;
    }

    public String getNivelDrenaje() {
        return nivelDrenaje;
    }

    public void setNivelDrenaje(String nivelDrenaje) {
        this.nivelDrenaje = nivelDrenaje;
    }

    public String getAreasProtegidasHA() {
        return areasProtegidasHA;
    }

    public void setAreasProtegidasHA(String areasProtegidasHA) {
        this.areasProtegidasHA = areasProtegidasHA;
    }

    public String getFuentesHidricasExistentes() {
        return fuentesHidricasExistentes;
    }

    public void setFuentesHidricasExistentes(String fuentesHidricasExistentes) {
        this.fuentesHidricasExistentes = fuentesHidricasExistentes;
    }

    public String getInfraestructuraAlmacenamientoAgua() {
        return infraestructuraAlmacenamientoAgua;
    }

    public void setInfraestructuraAlmacenamientoAgua(String infraestructuraAlmacenamientoAgua) {
        this.infraestructuraAlmacenamientoAgua = infraestructuraAlmacenamientoAgua;
    }

    public String getCoberturaSistemaRiego() {
        return coberturaSistemaRiego;
    }

    public void setCoberturaSistemaRiego(String coberturaSistemaRiego) {
        this.coberturaSistemaRiego = coberturaSistemaRiego;
    }

    public String getNombreDistritoRiego() {
        return nombreDistritoRiego;
    }

    public void setNombreDistritoRiego(String nombreDistritoRiego) {
        this.nombreDistritoRiego = nombreDistritoRiego;
    }

    public boolean isCaracteristicasUnidad() {
        return caracteristicasUnidad;
    }

    public void setCaracteristicasUnidad(boolean caracteristicasUnidad) {
        this.caracteristicasUnidad = caracteristicasUnidad;
    }

    public boolean isTecnificacionAgricola() {
        return tecnificacionAgricola;
    }

    public void setTecnificacionAgricola(boolean tecnificacionAgricola) {
        this.tecnificacionAgricola = tecnificacionAgricola;
    }

    public boolean isCultivos() {
        return cultivos;
    }

    public void setCultivos(boolean cultivos) {
        this.cultivos = cultivos;
    }

    public boolean isActividadPecuaria() {
        return actividadPecuaria;
    }

    public void setActividadPecuaria(boolean actividadPecuaria) {
        this.actividadPecuaria = actividadPecuaria;
    }

    ////////////////////////////////////////////////////////////////////////////////


    public List<PredioModel> getListPredioModel() {
        return listPredioModel;
    }

    public void setListPredioModel(List<PredioModel> listPredioModel) {
        this.listPredioModel = listPredioModel;
    }

    public List<ValoresPorcentajeModel> getListValoresPorcentajeModel() {
        return listValoresPorcentajeModel;
    }

    public void setListValoresPorcentajeModel(List<ValoresPorcentajeModel> listValoresPorcentajeModel) {
        this.listValoresPorcentajeModel = listValoresPorcentajeModel;
    }

    public TecnificacionAgricolaModel getTecnificacionAgricolaModel() {
        return tecnificacionAgricolaModel;
    }

    public void setTecnificacionAgricolaModel(TecnificacionAgricolaModel tecnificacionAgricolaModel) {
        this.tecnificacionAgricolaModel = tecnificacionAgricolaModel;
    }

    public List<CultivoModel> getListCultivoModel() {
        return listCultivoModel;
    }

    public void setListCultivoModel(List<CultivoModel> listCultivoModel) {
        this.listCultivoModel = listCultivoModel;
    }

    public ActividadGanaderaModel getActividadGanaderaModel() {
        return actividadGanaderaModel;
    }

    public void setActividadGanaderaModel(ActividadGanaderaModel actividadGanaderaModel) {
        this.actividadGanaderaModel = actividadGanaderaModel;
    }

    public ActividadAvicolaModel getActividadAvicolaModel() {
        return actividadAvicolaModel;
    }

    public void setActividadAvicolaModel(ActividadAvicolaModel actividadAvicolaModel) {
        this.actividadAvicolaModel = actividadAvicolaModel;
    }

    public ActividadPiscicolaModel getActividadPiscicolaModel() {
        return actividadPiscicolaModel;
    }

    public void setActividadPiscicolaModel(ActividadPiscicolaModel actividadPiscicolaModel) {
        this.actividadPiscicolaModel = actividadPiscicolaModel;
    }

    public ActividadPorcicolaModel getActividadPorcicolaModel() {
        return actividadPorcicolaModel;
    }

    public void setActividadPorcicolaModel(ActividadPorcicolaModel actividadPorcicolaModel) {
        this.actividadPorcicolaModel = actividadPorcicolaModel;
    }

    public List<ImagenesSolicitudModel> getListImagenesSolicitudModel() {
        return listImagenesSolicitudModel;
    }

    public void setListImagenesSolicitudModel(List<ImagenesSolicitudModel> listImagenesSolicitudModel) {
        this.listImagenesSolicitudModel = listImagenesSolicitudModel;
    }

	public double getAreaUtilizadaHa() {
		return areaUtilizadaHa;
	}

	public void setAreaUtilizadaHa(double areaUtilizadaHa) {
		this.areaUtilizadaHa = areaUtilizadaHa;
	}


    
    
}
