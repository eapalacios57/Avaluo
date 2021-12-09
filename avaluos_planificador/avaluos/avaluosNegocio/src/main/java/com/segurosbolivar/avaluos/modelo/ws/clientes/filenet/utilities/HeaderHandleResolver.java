package com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.utilities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

public class HeaderHandleResolver implements HandlerResolver {

	private String prefix;
	private String nameSpace;
	private static final String PREFIX = "ns";
	
	
    
    public HeaderHandleResolver() {
    }
    @SuppressWarnings("rawtypes")
    @Override
    public List<Handler> getHandlerChain(PortInfo portInfo) {
	List<Handler> handlerChain = new ArrayList<>();
	HeadHandler handler = new HeadHandler(PREFIX, portInfo.getServiceName().getNamespaceURI());
	handlerChain.add(handler);
	return handlerChain;
    }
    
    public void setService(Service service) {
    	QName qname = service.getServiceName();
    	this.prefix = "ns";
    	this.nameSpace = qname.getNamespaceURI();
    }

}
