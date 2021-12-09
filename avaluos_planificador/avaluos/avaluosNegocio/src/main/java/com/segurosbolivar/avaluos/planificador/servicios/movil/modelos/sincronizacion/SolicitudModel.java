package com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.sincronizacion;


import java.util.List;

public class SolicitudModel  {

    private String codSolicitud;
    private String productoRelacionado;
    private String comentariosAnexos;
    private String nombreAsesorComercial;
    private String telefonoAsesorComercial;
    private String nombrePlanificador;
    private String tipoDocumentoPlanificador;
    private String numDocumentoPlanificador;
    private String nombreBeneficiario;
    private String tipoDocumentoBeneficiario;
    private String telefonoBeneficiario;
    private String numDocumentoBeneficiario;
    private String municipioDepartamento;
    private List<UnidadProductivaModel> listUnidadProductivaModel;


    public SolicitudModel() {
    }

    public String getCodSolicitud() {
        return codSolicitud;
    }

    public void setCodSolicitud(String codSolicitud) {
        this.codSolicitud = codSolicitud;
    }

    public String getProductoRelacionado() {
        return productoRelacionado;
    }

    public void setProductoRelacionado(String productoRelacionado) {
        this.productoRelacionado = productoRelacionado;
    }

    public String getComentariosAnexos() {
        return comentariosAnexos;
    }

    public void setComentariosAnexos(String comentariosAnexos) {
        this.comentariosAnexos = comentariosAnexos;
    }

    public String getNombreAsesorComercial() {
        return nombreAsesorComercial;
    }

    public void setNombreAsesorComercial(String nombreAsesorComercial) {
        this.nombreAsesorComercial = nombreAsesorComercial;
    }

    public String getTelefonoAsesorComercial() {
        return telefonoAsesorComercial;
    }

    public void setTelefonoAsesorComercial(String telefonoAsesorComercial) {
        this.telefonoAsesorComercial = telefonoAsesorComercial;
    }

    public String getNombrePlanificador() {
        return nombrePlanificador;
    }

    public void setNombrePlanificador(String nombrePlanificador) {
        this.nombrePlanificador = nombrePlanificador;
    }

    public String getTipoDocumentoPlanificador() {
        return tipoDocumentoPlanificador;
    }

    public void setTipoDocumentoPlanificador(String tipoDocumentoPlanificador) {
        this.tipoDocumentoPlanificador = tipoDocumentoPlanificador;
    }

    public String getNumDocumentoPlanificador() {
        return numDocumentoPlanificador;
    }

    public void setNumDocumentoPlanificador(String numDocumentoPlanificador) {
        this.numDocumentoPlanificador = numDocumentoPlanificador;
    }

    public String getNombreBeneficiario() {
        return nombreBeneficiario;
    }

    public void setNombreBeneficiario(String nombreBeneficiario) {
        this.nombreBeneficiario = nombreBeneficiario;
    }

    public String getTipoDocumentoBeneficiario() {
        return tipoDocumentoBeneficiario;
    }

    public void setTipoDocumentoBeneficiario(String tipoDocumentoBeneficiario) {
        this.tipoDocumentoBeneficiario = tipoDocumentoBeneficiario;
    }

    public String getTelefonoBeneficiario() {
        return telefonoBeneficiario;
    }

    public void setTelefonoBeneficiario(String telefonoBeneficiario) {
        this.telefonoBeneficiario = telefonoBeneficiario;
    }

    public String getNumDocumentoBeneficiario() {
        return numDocumentoBeneficiario;
    }

    public void setNumDocumentoBeneficiario(String numDocumentoBeneficiario) {
        this.numDocumentoBeneficiario = numDocumentoBeneficiario;
    }

    public String getMunicipioDepartamento() {
        return municipioDepartamento;
    }

    public void setMunicipioDepartamento(String municipioDepartamento) {
        this.municipioDepartamento = municipioDepartamento;
    }


    //////////////////////////////////////////


    public List<UnidadProductivaModel> getListUnidadProductivaModel() {
        return listUnidadProductivaModel;
    }

    public void setListUnidadProductivaModel(List<UnidadProductivaModel> listUnidadProductivaModel) {
        this.listUnidadProductivaModel = listUnidadProductivaModel;
    }
}
