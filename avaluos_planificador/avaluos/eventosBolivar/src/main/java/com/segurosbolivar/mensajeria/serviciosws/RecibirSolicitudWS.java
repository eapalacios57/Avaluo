
package com.segurosbolivar.mensajeria.serviciosws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.segurosbolivar.mensajeria.utilsws.SolicitudDTO;


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
 *         &lt;element name="datosSolicitud" type="{http://utilsws.mensajeria.segurosbolivar.com}SolicitudDTO"/>
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
    "datosSolicitud"
})
@XmlRootElement(name = "recibirSolicitudWS")
public class RecibirSolicitudWS {

    @XmlElement(required = true)
    protected SolicitudDTO datosSolicitud;

    /**
     * Gets the value of the datosSolicitud property.
     * 
     * @return
     *     possible object is
     *     {@link SolicitudDTO }
     *     
     */
    public SolicitudDTO getDatosSolicitud() {
        return datosSolicitud;
    }

    /**
     * Sets the value of the datosSolicitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link SolicitudDTO }
     *     
     */
    public void setDatosSolicitud(SolicitudDTO value) {
        this.datosSolicitud = value;
    }

}
