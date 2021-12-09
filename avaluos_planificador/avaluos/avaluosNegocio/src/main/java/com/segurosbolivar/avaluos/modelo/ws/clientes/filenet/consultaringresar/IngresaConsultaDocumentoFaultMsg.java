
package com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.consultaringresar;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "opIngresaConsultaDocumentoFault", targetNamespace = "http://services.segurosbolivar.com/fileNet/cargar/")
public class IngresaConsultaDocumentoFaultMsg
    extends Exception
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private OpIngresaConsultaDocumentoFault faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public IngresaConsultaDocumentoFaultMsg(String message, OpIngresaConsultaDocumentoFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public IngresaConsultaDocumentoFaultMsg(String message, OpIngresaConsultaDocumentoFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.consultaringresar.OpIngresaConsultaDocumentoFault
     */
    public OpIngresaConsultaDocumentoFault getFaultInfo() {
        return faultInfo;
    }

}
