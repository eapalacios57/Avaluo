
package com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.borrar;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.segurosbolivar.services.filenet.eliminar package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.segurosbolivar.services.filenet.eliminar
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WSHeader }
     * 
     */
    public WSHeader createWSHeader() {
        return new WSHeader();
    }

    /**
     * Create an instance of {@link BorrarDocumento }
     * 
     */
    public BorrarDocumento createBorrarDocumento() {
        return new BorrarDocumento();
    }

    /**
     * Create an instance of {@link OpBorrarDocumentoResponse }
     * 
     */
    public OpBorrarDocumentoResponse createOpBorrarDocumentoResponse() {
        return new OpBorrarDocumentoResponse();
    }

    /**
     * Create an instance of {@link BorrarDocumentoResponse }
     * 
     */
    public BorrarDocumentoResponse createBorrarDocumentoResponse() {
        return new BorrarDocumentoResponse();
    }

    /**
     * Create an instance of {@link Propiedades }
     * 
     */
    public Propiedades createPropiedades() {
        return new Propiedades();
    }

    /**
     * Create an instance of {@link Error }
     * 
     */
    public Error createError() {
        return new Error();
    }

    /**
     * Create an instance of {@link Propiedad }
     * 
     */
    public Propiedad createPropiedad() {
        return new Propiedad();
    }

}
