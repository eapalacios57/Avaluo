
package com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.consultaringresar;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Documentos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Documentos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="retorno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Documento" type="{http://services.segurosbolivar.com/fileNet/cargar/}Documento" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Documentos", propOrder = {
    "retorno",
    "documento"
})
public class Documentos {

    protected String retorno;
    @XmlElement(name = "Documento")
    protected List<Documento> documento;

    /**
     * Obtiene el valor de la propiedad retorno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRetorno() {
        return retorno;
    }

    /**
     * Define el valor de la propiedad retorno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRetorno(String value) {
        this.retorno = value;
    }

    /**
     * Gets the value of the documento property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documento property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumento().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Documento }
     * 
     * 
     */
    public List<Documento> getDocumento() {
        if (documento == null) {
            documento = new ArrayList<Documento>();
        }
        return this.documento;
    }

}
