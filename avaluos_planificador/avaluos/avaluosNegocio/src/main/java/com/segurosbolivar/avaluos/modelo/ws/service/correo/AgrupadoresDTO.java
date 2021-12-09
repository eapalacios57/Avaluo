package com.segurosbolivar.avaluos.modelo.ws.service.correo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.segurosbolivar.avaluos.modelo.ws.service.correo.ArrayOfTns1ValoresDTO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AgrupadoresDTO", propOrder = {
    "agrupador",
    "datos"
})
public class AgrupadoresDTO {

    @XmlElement(required = true, nillable = true)
    protected String agrupador;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfTns1ValoresDTO datos;

    public String getAgrupador() {
        return agrupador;
    }

    public void setAgrupador(String value) {
        this.agrupador = value;
    }

    public ArrayOfTns1ValoresDTO getDatos() {
        return datos;
    }

    public void setDatos(ArrayOfTns1ValoresDTO value) {
        this.datos = value;
    }

}