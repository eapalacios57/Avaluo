
package com.segurosbolivar.mensajeria.utilsws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.segurosbolivar.mensajeria.serviciosws.ArrayOfTns1AgrupadoresDTO;


/**
 * <p>Java class for SolicitudDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SolicitudDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aplicacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="evento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="grupo" type="{http://serviciosws.mensajeria.segurosbolivar.com}ArrayOf_tns1_AgrupadoresDTO"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
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

    /**
     * Gets the value of the aplicacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAplicacion() {
        return aplicacion;
    }

    /**
     * Sets the value of the aplicacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAplicacion(String value) {
        this.aplicacion = value;
    }

    /**
     * Gets the value of the evento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEvento() {
        return evento;
    }

    /**
     * Sets the value of the evento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEvento(String value) {
        this.evento = value;
    }

    /**
     * Gets the value of the grupo property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTns1AgrupadoresDTO }
     *     
     */
    public ArrayOfTns1AgrupadoresDTO getGrupo() {
        return grupo;
    }

    /**
     * Sets the value of the grupo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTns1AgrupadoresDTO }
     *     
     */
    public void setGrupo(ArrayOfTns1AgrupadoresDTO value) {
        this.grupo = value;
    }

}
