
package com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.consultaringresar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Documento_Response complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Documento_Response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="propiedades" type="{http://services.segurosbolivar.com/fileNet/cargar/}Propiedades" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Documento_Response", propOrder = {
    "propiedades"
})
public class DocumentoResponse {

    protected Propiedades propiedades;

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

}
