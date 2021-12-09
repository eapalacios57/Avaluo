
package com.segurosbolivar.mensajeria.utilsws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RespuestaSolicitudDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RespuestaSolicitudDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aplicacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codRespuesta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descRespuesta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idSolicitud" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RespuestaSolicitudDTO", propOrder = {
    "aplicacion",
    "codRespuesta",
    "descRespuesta",
    "idSolicitud"
},namespace="http://serviciosws.mensajeria.segurosbolivar.com")
public class RespuestaSolicitudDTO {

    @XmlElement(required = true, nillable = true)
    protected String aplicacion;
    @XmlElement(required = true, nillable = true)
    protected String codRespuesta;
    @XmlElement(required = true, nillable = true)
    protected String descRespuesta;
    protected long idSolicitud;

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
     * Gets the value of the codRespuesta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodRespuesta() {
        return codRespuesta;
    }

    /**
     * Sets the value of the codRespuesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodRespuesta(String value) {
        this.codRespuesta = value;
    }

    /**
     * Gets the value of the descRespuesta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescRespuesta() {
        return descRespuesta;
    }

    /**
     * Sets the value of the descRespuesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescRespuesta(String value) {
        this.descRespuesta = value;
    }

    /**
     * Gets the value of the idSolicitud property.
     * 
     */
    public long getIdSolicitud() {
        return idSolicitud;
    }

    /**
     * Sets the value of the idSolicitud property.
     * 
     */
    public void setIdSolicitud(long value) {
        this.idSolicitud = value;
    }

}
