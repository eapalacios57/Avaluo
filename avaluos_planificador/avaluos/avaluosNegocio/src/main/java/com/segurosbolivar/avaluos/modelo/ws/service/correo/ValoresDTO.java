package com.segurosbolivar.avaluos.modelo.ws.service.correo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValoresDTO", propOrder = {
    "bindato",
    "codDato",
    "valDato"
})
public class ValoresDTO {

    @XmlElement(required = true, nillable = true)
    protected byte[] bindato;
    @XmlElement(required = true, nillable = true)
    protected String codDato;
    @XmlElement(required = true, nillable = true)
    protected String valDato;

    public byte[] getBindato() {
        return bindato;
    }

    public void setBindato(byte[] value) {
        this.bindato = value;
    }

    public String getCodDato() {
        return codDato;
    }

    public void setCodDato(String value) {
        this.codDato = value;
    }

    public String getValDato() {
        return valDato;
    }

    public void setValDato(String value) {
        this.valDato = value;
    }

}