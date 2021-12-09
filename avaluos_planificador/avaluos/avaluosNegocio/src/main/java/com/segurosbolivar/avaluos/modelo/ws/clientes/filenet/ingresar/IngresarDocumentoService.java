
package com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.ingresar;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "IngresarDocumentoService", targetNamespace = "http://services.segurosbolivar.com/fileNet/cargar/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IngresarDocumentoService {


    /**
     * 
     * @param parameters
     * @return
     *     returns com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.ingresar.OpIngresarDocumentoResponse
     * @throws IngresarDocumentoFaultMsg
     */
    @WebMethod(action = "http://localhost:9080/services/FilenetWS/opIngresarDocumento")
    @WebResult(name = "opIngresarDocumentoResponse", targetNamespace = "http://services.segurosbolivar.com/fileNet/cargar/", partName = "parameters")
    public OpIngresarDocumentoResponse opIngresarDocumento(
        @WebParam(name = "listaDocumento", targetNamespace = "http://services.segurosbolivar.com/fileNet/cargar/", partName = "parameters")
        ListaDocumento parameters)
        throws IngresarDocumentoFaultMsg
    ;

}
