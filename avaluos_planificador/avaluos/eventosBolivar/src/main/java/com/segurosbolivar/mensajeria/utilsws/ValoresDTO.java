
package com.segurosbolivar.mensajeria.utilsws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ValoresDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValoresDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bindato" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="codDato" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="valDato" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
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

    /**
     * Gets the value of the bindato property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBindato() {
        return bindato;
    }

    /**
     * Sets the value of the bindato property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBindato(byte[] value) {
        this.bindato = value;
    }

    /**
     * Gets the value of the codDato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodDato() {
        return codDato;
    }

    /**
     * Sets the value of the codDato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodDato(String value) {
        this.codDato = value;
    }

    /**
     * Gets the value of the valDato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValDato() {
        return valDato;
    }

    /**
     * Sets the value of the valDato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValDato(String value) {
        this.valDato = value;
    }

}
