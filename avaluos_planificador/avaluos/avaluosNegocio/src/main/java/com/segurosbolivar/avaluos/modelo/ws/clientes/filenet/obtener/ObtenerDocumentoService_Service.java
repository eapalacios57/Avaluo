
package com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.obtener;

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
@WebServiceClient(name = "ObtenerDocumentoService", targetNamespace = "http://services.segurosbolivar.com/fileNet/obtener/", wsdlLocation = "https://ambientepruebas.segurosbolivar.com/SBServicioFilenetSOAP/ObtenerDocumentoService/WEB-INF/wsdl/ObtenerDocumentoService.wsdl")
public class ObtenerDocumentoService_Service
    extends Service
{

    private final static URL OBTENERDOCUMENTOSERVICE_WSDL_LOCATION;
    private final static WebServiceException OBTENERDOCUMENTOSERVICE_EXCEPTION;
    private final static QName OBTENERDOCUMENTOSERVICE_QNAME = new QName("http://services.segurosbolivar.com/fileNet/obtener/", "ObtenerDocumentoService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://ambientepruebas.segurosbolivar.com/SBServicioFilenetSOAP/ObtenerDocumentoService/WEB-INF/wsdl/ObtenerDocumentoService.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        OBTENERDOCUMENTOSERVICE_WSDL_LOCATION = url;
        OBTENERDOCUMENTOSERVICE_EXCEPTION = e;
    }

    public ObtenerDocumentoService_Service() {
        super(__getWsdlLocation(), OBTENERDOCUMENTOSERVICE_QNAME);
    }

    public ObtenerDocumentoService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), OBTENERDOCUMENTOSERVICE_QNAME, features);
    }

    public ObtenerDocumentoService_Service(URL wsdlLocation) {
        super(wsdlLocation, OBTENERDOCUMENTOSERVICE_QNAME);
    }

    public ObtenerDocumentoService_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, OBTENERDOCUMENTOSERVICE_QNAME, features);
    }

    public ObtenerDocumentoService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ObtenerDocumentoService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ObtenerDocumentoService
     */
    @WebEndpoint(name = "ObtenerDocumentoWSSOAP")
    public ObtenerDocumentoService getObtenerDocumentoWSSOAP() {
        return super.getPort(new QName("http://services.segurosbolivar.com/fileNet/obtener/", "ObtenerDocumentoWSSOAP"), ObtenerDocumentoService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ObtenerDocumentoService
     */
    @WebEndpoint(name = "ObtenerDocumentoWSSOAP")
    public ObtenerDocumentoService getObtenerDocumentoWSSOAP(WebServiceFeature... features) {
        return super.getPort(new QName("http://services.segurosbolivar.com/fileNet/obtener/", "ObtenerDocumentoWSSOAP"), ObtenerDocumentoService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (OBTENERDOCUMENTOSERVICE_EXCEPTION!= null) {
            throw OBTENERDOCUMENTOSERVICE_EXCEPTION;
        }
        return OBTENERDOCUMENTOSERVICE_WSDL_LOCATION;
    }

}
