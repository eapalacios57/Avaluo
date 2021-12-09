
package com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.modificar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DocumentoMetaData complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DocumentoMetaData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxonomia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="propiedades" type="{http://services.segurosbolivar.com/fileNet/modificar/}Propiedades"/>
 *         &lt;element name="mimeType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentoMetaData", propOrder = {
    "idDocumento",
    "taxonomia",
    "propiedades",
    "mimeType"
})
public class DocumentoMetaData {

    protected String idDocumento;
    @XmlElement(required = true)
    protected String taxonomia;
    @XmlElement(required = true)
    protected Propiedades propiedades;
    @XmlElement(required = true)
    protected String mimeType;

    /**
     * Obtiene el valor de la propiedad idDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdDocumento() {
        return idDocumento;
    }

    /**
     * Define el valor de la propiedad idDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdDocumento(String value) {
        this.idDocumento = value;
    }

    /**
     * Obtiene el valor de la propiedad taxonomia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxonomia() {
        return taxonomia;
    }

    /**
     * Define el valor de la propiedad taxonomia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxonomia(String value) {
        this.taxonomia = value;
    }

    /**
     * Obtiene el valor de la propiedad propiedades.
     * 
     * @return
     *     possible object is
     *     {@link Propiedades }
     *     
     */
    public Propiedades getPropiedades() {
        return propiedades;
    }

    /**
     * Define el valor de la propiedad propiedades.
     * 
     * @param value
     *     allowed object is
     *     {@link Propiedades }
     *     
     */
    public void setPropiedades(Propiedades value) {
        this.propiedades = value;
    }

    /**
     * Obtiene el valor de la propiedad mimeType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Define el valor de la propiedad mimeType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMimeType(String value) {
        this.mimeType = value;
    }

}
