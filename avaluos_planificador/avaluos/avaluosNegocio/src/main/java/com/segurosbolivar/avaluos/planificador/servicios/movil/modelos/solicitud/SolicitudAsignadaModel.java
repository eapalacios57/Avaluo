package com.segurosbolivar.avaluos.planificador.servicios.movil.modelos.solicitud;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SolicitudAsignadaModel {

    private String codigoSolicitud;

    private double valorTotalCredito;
    
    private double valorTotalProyecto;

    private String comentariosAnexos;

    private String codigoProductoRelacionado;

    private String nombreProductoRelacionado;

    private String nombreAsesorComercial;

    private String telefonoAsesorComercial;

    private String tipoDocumentoBeneficiario;

    private String numDocumentoBeneficiario;

    private String nombreBeneficiario;

    private String municipioDepartamento;

    private String telefonoBeneficiario;

    private List<ActividadFinancieraModel> listActividadFinancieraModel;

    private List<DocumentosModel> listDocumentosModel;
    
    private String fechaSolicitud;

    public SolicitudAsignadaModel() {
    }

    public String getComentariosAnexos() {
        return comentariosAnexos;
    }

    public void setComentariosAnexos(String comentariosAnexos) {
        this.comentariosAnexos = comentariosAnexos;
    }

    public String getCodigoSolicitud() {
        return codigoSolicitud;
    }

    public void setCodigoSolicitud(String codigoSolicitud) {
        this.codigoSolicitud = codigoSolicitud;
    }

    public double getValorTotalCredito() {
        return valorTotalCredito;
    }

    public void setValorTotalCredito(double valorTotalCredito) {
        this.valorTotalCredito = valorTotalCredito;
    }

    public double getValorTotalProyecto() {
        return valorTotalProyecto;
    }

    public void setValorTotalProyecto(double valorTotalProyecto) {
        this.valorTotalProyecto = valorTotalProyecto;
    }

  

    public String getCodigoProductoRelacionado() {
        return codigoProductoRelacionado;
    }

    public void setCodigoProductoRelacionado(String codigoProductoRelacionado) {
        this.codigoProductoRelacionado = codigoProductoRelacionado;
    }

    public String getNombreProductoRelacionado() {
        return nombreProductoRelacionado;
    }

    public void setNombreProductoRelacionado(String nombreProductoRelacionado) {
        this.nombreProductoRelacionado = nombreProductoRelacionado;
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

   

    public List<ActividadFinancieraModel> getListActividadFinancieraModel() {
		return listActividadFinancieraModel;
	}

	public void setListActividadFinancieraModel(List<ActividadFinancieraModel> listActividadFinancieraModel) {
		this.listActividadFinancieraModel = listActividadFinancieraModel;
	}

	public String getTipoDocumentoBeneficiario() {
        return tipoDocumentoBeneficiario;
    }

    public void setTipoDocumentoBeneficiario(String tipoDocumentoBeneficiario) {
        this.tipoDocumentoBeneficiario = tipoDocumentoBeneficiario;
    }

    public String getNumDocumentoBeneficiario() {
        return numDocumentoBeneficiario;
    }

    public void setNumDocumentoBeneficiario(String numDocumentoBeneficiario) {
        this.numDocumentoBeneficiario = numDocumentoBeneficiario;
    }

    public String getNombreBeneficiario() {
        return nombreBeneficiario;
    }

    public void setNombreBeneficiario(String nombreBeneficiario) {
        this.nombreBeneficiario = nombreBeneficiario;
    }

    public String getMunicipioDepartamento() {
        return municipioDepartamento;
    }

    public void setMunicipioDepartamento(String municipioDepartamento) {
        this.municipioDepartamento = municipioDepartamento;
    }

    public String getTelefonoBeneficiario() {
        return telefonoBeneficiario;
    }

    public void setTelefonoBeneficiario(String telefonoBeneficiario) {
        this.telefonoBeneficiario = telefonoBeneficiario;
    }

	public List<DocumentosModel> getListDocumentosModel() {
		return listDocumentosModel;
	}

	public void setListDocumentosModel(List<DocumentosModel> listDocumentosModel) {
		this.listDocumentosModel = listDocumentosModel;
	}

    public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
    
    
    /*Datos temporales*/


}
