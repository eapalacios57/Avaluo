
package com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.borrar;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "BorrarDocumentoService", targetNamespace = "http://services.segurosbolivar.com/fileNet/eliminar/", wsdlLocation = "https://ambientepruebas.segurosbolivar.com/SBServicioFilenetSOAP/BorrarDocumentoService/WEB-INF/wsdl/BorrarDocumentoService.wsdl")
public class BorrarDocumentoService_Service
    extends Service
{

    private final static URL BORRARDOCUMENTOSERVICE_WSDL_LOCATION;
    private final static WebServiceException BORRARDOCUMENTOSERVICE_EXCEPTION;
    private final static QName BORRARDOCUMENTOSERVICE_QNAME = new QName("http://services.segurosbolivar.com/fileNet/eliminar/", "BorrarDocumentoService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://ambientepruebas.segurosbolivar.com/SBServicioFilenetSOAP/BorrarDocumentoService/WEB-INF/wsdl/BorrarDocumentoService.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        BORRARDOCUMENTOSERVICE_WSDL_LOCATION = url;
        BORRARDOCUMENTOSERVICE_EXCEPTION = e;
    }

    public BorrarDocumentoService_Service() {
        super(__getWsdlLocation(), BORRARDOCUMENTOSERVICE_QNAME);
    }

    public BorrarDocumentoService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), BORRARDOCUMENTOSERVICE_QNAME, features);
    }

    public BorrarDocumentoService_Service(URL wsdlLocation) {
        super(wsdlLocation, BORRARDOCUMENTOSERVICE_QNAME);
    }

    public BorrarDocumentoService_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, BORRARDOCUMENTOSERVICE_QNAME, features);
    }

    public BorrarDocumentoService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BorrarDocumentoService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns BorrarDocumentoService
     */
    @WebEndpoint(name = "BorrarDocumentoWSSOAP")
    public BorrarDocumentoService getBorrarDocumentoWSSOAP() {
        return super.getPort(new QName("http://services.segurosbolivar.com/fileNet/eliminar/", "BorrarDocumentoWSSOAP"), BorrarDocumentoService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns BorrarDocumentoService
     */
    @WebEndpoint(name = "BorrarDocumentoWSSOAP")
    public BorrarDocumentoService getBorrarDocumentoWSSOAP(WebServiceFeature... features) {
        return super.getPort(new QName("http://services.segurosbolivar.com/fileNet/eliminar/", "BorrarDocumentoWSSOAP"), BorrarDocumentoService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (BORRARDOCUMENTOSERVICE_EXCEPTION!= null) {
            throw BORRARDOCUMENTOSERVICE_EXCEPTION;
        }
        return BORRARDOCUMENTOSERVICE_WSDL_LOCATION;
    }

}