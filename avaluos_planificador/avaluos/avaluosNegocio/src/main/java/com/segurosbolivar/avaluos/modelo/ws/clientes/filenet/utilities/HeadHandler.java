package com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.utilities;

import java.net.InetAddress;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import com.segurosbolivar.avaluos.modelo.service.util.UtilConstantes;

public class HeadHandler implements SOAPHandler<SOAPMessageContext> {

    private static Logger logger = Logger.getLogger(HeadHandler.class.getSimpleName());

    private static final String WSHEADER_PART = "WSHeader";
    private String prefix;
    private String scheme;

    
    public HeadHandler(String prefix, String nameSpace) {
    	this.prefix = prefix;
    	this.scheme = nameSpace;
    }
    
    @Override
    public boolean handleMessage(SOAPMessageContext context) {
	boolean esSolicitud = (boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
	    try {
		SOAPMessage soapMsg = context.getMessage();
		SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
		SOAPHeader soapHeader = soapEnv.getHeader();
		if(soapHeader == null) {
		    soapHeader=soapEnv.addHeader();
		}
		SOAPElement wsHeader = soapHeader.addChildElement(WSHEADER_PART, prefix, scheme);
		SOAPElement ip = wsHeader.addChildElement("IP");
		SOAPElement businessUser = wsHeader.addChildElement("BusinessUser");
		ip.setTextContent(InetAddress.getLocalHost().getHostAddress());
		businessUser.setTextContent(UtilConstantes.BUSINESS_USER);
	    } catch (Exception e) {
		e.printStackTrace();
		logger.log(Level.SEVERE, e.getMessage());
	    }
	return esSolicitud;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
	return false;
    }

    @Override
    public void close(MessageContext context) {
    }

    @Override
    public Set<QName> getHeaders() {
	return Collections.emptySet();
    }

}
