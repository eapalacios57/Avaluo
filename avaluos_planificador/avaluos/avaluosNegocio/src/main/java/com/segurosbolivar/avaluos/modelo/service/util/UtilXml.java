package com.segurosbolivar.avaluos.modelo.service.util;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.asesoftware.util.exception.NegocioException;

public class UtilXml {

	private UtilXml() {
	}
	
	private static Logger logger = Logger.getLogger(UtilXml.class);

	public static Object conversorXmlaObjeto(String xml, Class<?> clazz) throws NegocioException {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource xmlOrigen = new InputSource();
			xmlOrigen.setCharacterStream(new StringReader(xml));
			Document documento = builder.parse(xmlOrigen);
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return jaxbUnmarshaller.unmarshal(documento);
		} catch (Exception e) {
		    e.printStackTrace();
		    throw new NegocioException(e.getMessage());
		}

	}

}
