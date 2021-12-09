
package com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.modificar;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ModificarDocumentoResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ModificarDocumentoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dtls" type="{http://services.segurosbolivar.com/fileNet/modificar/}Error" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="responseCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="result" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="metadata" type="{http://services.segurosbolivar.com/fileNet/modificar/}DocumentoMetaData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModificarDocumentoResponse", propOrder = {
    "dtls",
    "responseCode",
    "result",
    "metadata"
})
public class ModificarDocumentoResponse {

    protected List<Error> dtls;
    protected int responseCode;
    protected boolean result;
    protected DocumentoMetaData metadata;

    /**
     * Gets the value of the dtls property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dtls property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDtls().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Error }
     * 
     * 
     */
    public List<Error> getDtls() {
        if (dtls == null) {
            dtls = new ArrayList<Error>();
        }
        return this.dtls;
    }

    /**
     * Obtiene el valor de la propiedad responseCode.
     * 
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * Define el valor de la propiedad responseCode.
     * 
     */
    public void setResponseCode(int value) {
        this.responseCode = value;
    }

    /**
     * Obtiene el valor de la propiedad result.
     * 
     */
    public boolean isResult() {
        return result;
    }

    /**
     * Define el valor de la propiedad result.
     * 
     */
    public void setResult(boolean value) {
        this.result = value;
    }

    /**
     * Obtiene el valor de la propiedad metadata.
     * 
     * @return
     *     possible object is
     *     {@link DocumentoMetaData }
     *     
     */
    public DocumentoMetaData getMetadata() {
        return metadata;
    }

    /**
     * Define el valor de la propiedad metadata.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentoMetaData }
     *     
     */
    public void setMetadata(DocumentoMetaData value) {
        this.metadata = value;
    }

}
