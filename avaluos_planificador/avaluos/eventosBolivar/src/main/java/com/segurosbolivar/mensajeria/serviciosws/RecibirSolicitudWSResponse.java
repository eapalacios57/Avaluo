
package com.segurosbolivar.mensajeria.serviciosws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.segurosbolivar.mensajeria.utilsws.RespuestaSolicitudDTO;


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
 *         &lt;element name="recibirSolicitudWSReturn" type="{http://utilsws.mensajeria.segurosbolivar.com}RespuestaSolicitudDTO"/>
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
    "recibirSolicitudWSReturn"
})
@XmlRootElement(name = "recibirSolicitudWSResponse")
public class RecibirSolicitudWSResponse {

    @XmlElement(required = true)
    protected RespuestaSolicitudDTO recibirSolicitudWSReturn;

    /**
     * Gets the value of the recibirSolicitudWSReturn property.
     * 
     * @return
     *     possible object is
     *     {@link RespuestaSolicitudDTO }
     *     
     */
    public RespuestaSolicitudDTO getRecibirSolicitudWSReturn() {
        return recibirSolicitudWSReturn;
    }

    /**
     * Sets the value of the recibirSolicitudWSReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link RespuestaSolicitudDTO }
     *     
     */
    public void setRecibirSolicitudWSReturn(RespuestaSolicitudDTO value) {
        this.recibirSolicitudWSReturn = value;
    }

}
