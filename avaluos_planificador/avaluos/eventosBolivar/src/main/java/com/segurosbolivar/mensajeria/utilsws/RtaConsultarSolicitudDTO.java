
package com.segurosbolivar.mensajeria.utilsws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RtaConsultarSolicitudDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RtaConsultarSolicitudDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codAplicacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codLog" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="codRespuesta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaEnvio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RtaConsultarSolicitudDTO", propOrder = {
    "codAplicacion",
    "codLog",
    "codRespuesta",
    "descripcion",
    "fechaEnvio"
})
public class RtaConsultarSolicitudDTO {

    @XmlElement(required = true, nillable = true)
    protected String codAplicacion;
    protected long codLog;
    @XmlElement(required = true, nillable = true)
    protected String codRespuesta;
    @XmlElement(required = true, nillable = true)
    protected String descripcion;
    @XmlElement(required = true, nillable = true)
    protected String fechaEnvio;

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

    /**
     * Gets the value of the codLog property.
     * 
     */
    public long getCodLog() {
        return codLog;
    }

    /**
     * Sets the value of the codLog property.
     * 
     */
    public void setCodLog(long value) {
        this.codLog = value;
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
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the fechaEnvio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaEnvio() {
        return fechaEnvio;
    }

    /**
     * Sets the value of the fechaEnvio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaEnvio(String value) {
        this.fechaEnvio = value;
    }

}
