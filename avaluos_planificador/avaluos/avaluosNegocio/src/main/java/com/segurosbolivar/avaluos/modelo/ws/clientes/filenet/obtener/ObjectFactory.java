
package com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.obtener;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.segurosbolivar.services.filenet.obtener package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.segurosbolivar.services.filenet.obtener
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OpObtenerDocumentoResponse }
     * 
     */
    public OpObtenerDocumentoResponse createOpObtenerDocumentoResponse() {
        return new OpObtenerDocumentoResponse();
    }

    /**
     * Create an instance of {@link ObtenerDocumentoResponse }
     * 
     */
    public ObtenerDocumentoResponse createObtenerDocumentoResponse() {
        return new ObtenerDocumentoResponse();
    }

    /**
     * Create an instance of {@link ObtenerDocumento }
     * 
     */
    public ObtenerDocumento createObtenerDocumento() {
        return new ObtenerDocumento();
    }

    /**
     * Create an instance of {@link WSHeader }
     * 
     */
    public WSHeader createWSHeader() {
        return new WSHeader();
    }

    /**
     * Create an instance of {@link Error }
     * 
     */
    public Error createError() {
        return new Error();
    }

}
