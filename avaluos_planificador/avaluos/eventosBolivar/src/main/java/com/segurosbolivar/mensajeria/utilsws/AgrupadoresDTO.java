
package com.segurosbolivar.mensajeria.utilsws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.segurosbolivar.mensajeria.serviciosws.ArrayOfTns1ValoresDTO;


/**
 * <p>Java class for AgrupadoresDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AgrupadoresDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="agrupador" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="datos" type="{http://serviciosws.mensajeria.segurosbolivar.com}ArrayOf_tns1_ValoresDTO"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
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

    /**
     * Gets the value of the agrupador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgrupador() {
        return agrupador;
    }

    /**
     * Sets the value of the agrupador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgrupador(String value) {
        this.agrupador = value;
    }

    /**
     * Gets the value of the datos property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTns1ValoresDTO }
     *     
     */
    public ArrayOfTns1ValoresDTO getDatos() {
        return datos;
    }

    /**
     * Sets the value of the datos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTns1ValoresDTO }
     *     
     */
    public void setDatos(ArrayOfTns1ValoresDTO value) {
        this.datos = value;
    }

}
