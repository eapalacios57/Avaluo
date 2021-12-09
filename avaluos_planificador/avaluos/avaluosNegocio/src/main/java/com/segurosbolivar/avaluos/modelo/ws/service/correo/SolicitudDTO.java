package com.segurosbolivar.avaluos.modelo.ws.service.correo;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.segurosbolivar.avaluos.modelo.ws.service.correo.ArrayOfTns1AgrupadoresDTO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SolicitudDTO", propOrder = {
    "aplicacion",
    "evento",
    "grupo"
})
public class SolicitudDTO {

    @XmlElement(required = true, nillable = true)
    protected String aplicacion;
    @XmlElement(required = true, nillable = true)
    protected String evento;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfTns1AgrupadoresDTO grupo;

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String value) {
        this.aplicacion = value;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String value) {
        this.evento = value;
    }

    public ArrayOfTns1AgrupadoresDTO getGrupo() {
        return grupo;
    }

    public void setGrupo(ArrayOfTns1AgrupadoresDTO value) {
        this.grupo = value;
    }

}