
package com.segurosbolivar.mensajeria.serviciosws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.segurosbolivar.mensajeria.utilsws.RtaConsultarSolicitudDTO;


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
 *         &lt;element name="consultarSolicitudWSReturn" type="{http://utilsws.mensajeria.segurosbolivar.com}RtaConsultarSolicitudDTO"/>
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
    "consultarSolicitudWSReturn"
})
@XmlRootElement(name = "consultarSolicitudWSResponse")
public class ConsultarSolicitudWSResponse {

    @XmlElement(required = true)
    protected RtaConsultarSolicitudDTO consultarSolicitudWSReturn;

    /**
     * Gets the value of the consultarSolicitudWSReturn property.
     * 
     * @return
     *     possible object is
     *     {@link RtaConsultarSolicitudDTO }
     *     
     */
    public RtaConsultarSolicitudDTO getConsultarSolicitudWSReturn() {
        return consultarSolicitudWSReturn;
    }

    /**
     * Sets the value of the consultarSolicitudWSReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link RtaConsultarSolicitudDTO }
     *     
     */
    public void setConsultarSolicitudWSReturn(RtaConsultarSolicitudDTO value) {
        this.consultarSolicitudWSReturn = value;
    }

}
