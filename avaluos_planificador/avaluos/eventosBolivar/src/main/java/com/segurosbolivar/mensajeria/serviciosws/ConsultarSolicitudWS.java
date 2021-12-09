
package com.segurosbolivar.mensajeria.serviciosws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numSolicitud" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="codAplicacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "numSolicitud",
    "codAplicacion"
})
@XmlRootElement(name = "consultarSolicitudWS")
public class ConsultarSolicitudWS {

    protected long numSolicitud;
    @XmlElement(required = true)
    protected String codAplicacion;

    /**
     * Gets the value of the numSolicitud property.
     * 
     */
    public long getNumSolicitud() {
        return numSolicitud;
    }

    /**
     * Sets the value of the numSolicitud property.
     * 
     */
    public void setNumSolicitud(long value) {
        this.numSolicitud = value;
    }

    /**
     * Gets the value of the codAplicacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodAplicacion() {
        return codAplicacion;
    }

    /**
     * Sets the value of the codAplicacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodAplicacion(String value) {
        this.codAplicacion = value;
    }

}
