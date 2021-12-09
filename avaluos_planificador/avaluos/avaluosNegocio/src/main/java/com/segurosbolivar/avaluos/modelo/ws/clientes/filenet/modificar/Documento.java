
package com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.modificar;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Documento complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Documento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxonomia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="propiedades" type="{http://services.segurosbolivar.com/fileNet/modificar/}Propiedades"/>
 *         &lt;element name="archivo" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
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
@XmlType(name = "Documento", propOrder = {
    "idDocumento",
    "taxonomia",
    "propiedades",
    "archivo",
    "mimeType"
})
public class Documento {

    protected String idDocumento;
    @XmlElement(required = true)
    protected String taxonomia;
    @XmlElement(required = true)
    protected Propiedades propiedades;
    @XmlElement(required = true)
    @XmlMimeType("*/*")
    protected DataHandler archivo;
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
     * Obtiene el valor de la propiedad archivo.
     * 
     * @return
     *     possible object is
     *     {@link DataHandler }
     *     
     */
    public DataHandler getArchivo() {
        return archivo;
    }

    /**
     * Define el valor de la propiedad archivo.
     * 
     * @param value
     *     allowed object is
     *     {@link DataHandler }
     *     
     */
    public void setArchivo(DataHandler value) {
        this.archivo = value;
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
