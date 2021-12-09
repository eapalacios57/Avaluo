package com.segurosbolivar.avaluos.modelo.ws.service.correo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RtaConsultarSolicitudDTO", propOrder = {
    "codAplicacion",
    "codLog",
    "codRespuesta",
    "descripcion",
    "fechaEnvio"
})
public class RtaConsultarSolicitudDTO {

    @XmlElement(required = true, nillable = true)
    protected String codAplicacion;
    protected long codLog;
    @XmlElement(required = true, nillable = true)
    protected String codRespuesta;
    @XmlElement(required = true, nillable = true)
    protected String descripcion;
    @XmlElement(required = true, nillable = true)
    protected String fechaEnvio;

    public String getCodAplicacion() {
        return codAplicacion;
    }

    public void setCodAplicacion(String value) {
        this.codAplicacion = value;
    }

    public long getCodLog() {
        return codLog;
    }

    public void setCodLog(long value) {
        this.codLog = value;
    }

    public String getCodRespuesta() {
        return codRespuesta;
    }

    public void setCodRespuesta(String value) {
        this.codRespuesta = value;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String value) {
        this.fechaEnvio = value;
    }

}