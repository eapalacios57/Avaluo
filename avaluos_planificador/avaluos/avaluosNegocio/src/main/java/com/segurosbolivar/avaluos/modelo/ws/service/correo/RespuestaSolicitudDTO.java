package com.segurosbolivar.avaluos.modelo.ws.service.correo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RespuestaSolicitudDTO", propOrder = {
    "aplicacion",
    "codRespuesta",
    "descRespuesta",
    "idSolicitud"
},namespace="http://serviciosws.mensajeria.segurosbolivar.com")
public class RespuestaSolicitudDTO {

    @XmlElement(required = true, nillable = true)
    protected String aplicacion;
    @XmlElement(required = true, nillable = true)
    protected String codRespuesta;
    @XmlElement(required = true, nillable = true)
    protected String descRespuesta;
    protected long idSolicitud;

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String value) {
        this.aplicacion = value;
    }

    public String getCodRespuesta() {
        return codRespuesta;
    }

    public void setCodRespuesta(String value) {
        this.codRespuesta = value;
    }

    public String getDescRespuesta() {
        return descRespuesta;
    }

    public void setDescRespuesta(String value) {
        this.descRespuesta = value;
    }

    public long getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(long value) {
        this.idSolicitud = value;
    }

}