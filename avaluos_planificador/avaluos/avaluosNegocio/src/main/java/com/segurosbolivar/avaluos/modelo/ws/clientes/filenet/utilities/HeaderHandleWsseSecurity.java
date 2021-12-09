package com.segurosbolivar.avaluos.modelo.ws.clientes.filenet.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.xml.crypto.dom.DOMStructure;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

public class HeaderHandleWsseSecurity implements SOAPHandler<SOAPMessageContext> {

    private static final String NS_PREFIX_ENVELOPE = "soapenv";
    private static final Logger log = Logger.getLogger(HeaderHandleWsseSecurity.class);
	
    private String rutaAlmacenLlavero;
    private String passwordAlmacenLlavero;
    private String passwordLlavero;
    private String aliasLlavero;
    
    /*private static ResourceBundle bundle;
	static {
		try {
			bundle = UtilPropiedades.leerProperties(UtilConstantes.RUTA_PROPERTIES);
		} catch (NegocioException e) {
			log.error("No se ha podido leer el archivo de propiedades", e);
		}
	}*/
    
    public HeaderHandleWsseSecurity(String rutaAlmacenLlavero, String passwordAlmacenLlavero, String aliasLlavero, String passwordLlavero) {
		super();
		this.rutaAlmacenLlavero = rutaAlmacenLlavero;
		this.passwordAlmacenLlavero = passwordAlmacenLlavero;
		this.aliasLlavero = aliasLlavero;
		this.passwordLlavero = passwordLlavero;
	}
	
    @Override
    public boolean handleMessage(SOAPMessageContext context) {
    	String FORMATO_KEYSTORE_WLS = "JKS";
    	
    	Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outboundProperty.booleanValue()) {//es una peticion al WS

            try {
            	Date fechaActual = new Date();
            	String idRefBody = "id-"+fechaActual.getTime();
            	SOAPMessage msg = context.getMessage();
            	SOAPPart soapPart = msg.getSOAPPart();
                SOAPEnvelope envelope = soapPart.getEnvelope();
         
                SOAPHeader header = envelope.getHeader();
        		
                SOAPBody soapBody = envelope.getBody(); // soapMessage.getSOAPBody()

                /**/
                Document docBody;
                docBody = soapBody.extractContentAsDocument();
                soapBody.addDocument(docBody);
                soapBody.addAttribute(envelope.createName("Id", "wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd"), idRefBody);
                 /**/
                
                if(header == null) {
        			header=envelope.addHeader();
        		}
                
        		
                envelope.removeNamespaceDeclaration(envelope.getPrefix());
                envelope.removeNamespaceDeclaration("SOAP-ENV");
                envelope.removeNamespaceDeclaration("env");
                envelope.addNamespaceDeclaration(NS_PREFIX_ENVELOPE, "http://schemas.xmlsoap.org/soap/envelope/");
                
                envelope.setPrefix(NS_PREFIX_ENVELOPE);
                header.setPrefix(NS_PREFIX_ENVELOPE);
                soapBody.setPrefix(NS_PREFIX_ENVELOPE);
                
                
                // <wsse:Security> element adding to Header Part
                SOAPElement securityElement = header.addChildElement("Security", "wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
                securityElement.addNamespaceDeclaration("wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");

                                
                XMLSignatureFactory xmlSignatureFactory = XMLSignatureFactory.getInstance("DOM");

                //Digest method - <ds:SignatureMethod Algorithm="http://www.w3.org/2000/09/xmldsig#rsa-sha1"/>
                javax.xml.crypto.dsig.DigestMethod digestMethod = xmlSignatureFactory.newDigestMethod(DigestMethod.SHA1, null);
                
                List<String> prefixList = new ArrayList<String>();
                prefixList.add("");
                
                Transform envTransform = xmlSignatureFactory.newTransform("http://www.w3.org/2001/10/xml-exc-c14n#", new ExcC14NParameterSpec(prefixList));
                
                
                ArrayList<Transform> transformList = new ArrayList<Transform>();
                transformList.add(envTransform);
                
                /**/
                    //References <ds:Reference URI="#Body">
                    ArrayList<Reference> refList = new ArrayList<Reference>();
//                        Reference refTS   = xmlSignatureFactory.newReference("#"+timeStampID,  digestMethod, transformList, null, null);
                        Reference refBody = xmlSignatureFactory.newReference("#"+idRefBody, digestMethod, transformList, null, null);
//                    	  Reference ref = xmlSignatureFactory.newReference("", digestMethod, transformList, null, null);
                    refList.add(refBody);
//                    refList.add(refTS);
//                      refList.add(ref);
                    /**/
                    
                prefixList = new ArrayList<String>();
                prefixList.add(NS_PREFIX_ENVELOPE);
                
                javax.xml.crypto.dsig.CanonicalizationMethod cm = xmlSignatureFactory.newCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE, new ExcC14NParameterSpec(prefixList));
                
                javax.xml.crypto.dsig.SignatureMethod sm = xmlSignatureFactory.newSignatureMethod(SignatureMethod.RSA_SHA1, null);
                
                SignedInfo signedInfo = xmlSignatureFactory.newSignedInfo(cm, sm, refList);
        		
                KeyStore keyStore = KeyStore.getInstance(FORMATO_KEYSTORE_WLS);//("pkcs12", "SunJSSE");
//                InputStream filePfx = getClass().getClassLoader().getResourceAsStream("test_ambPruebas_2019_exportedCert.pfx");
//                keyStore.load(filePfx, "Bolivar2020".toCharArray());
//                filePfx.close();

                InputStream filePfx = new FileInputStream(new File(rutaAlmacenLlavero));//getClass().getClassLoader().getResourceAsStream("test_ambPruebas_2019_exportedCert.pfx");
                keyStore.load(filePfx, passwordAlmacenLlavero.toCharArray());
                filePfx.close();

                
                //keyStore.load(new FileInputStream("test_ambPruebas_2019_exportedCert.pfx"), "Bolivar2020".toCharArray());
                
                /*
                Enumeration<String> keyStoreAliasEnum = keyStore.aliases();
                PrivateKey privateKey = null;
                String alias = null;
                if ( keyStoreAliasEnum.hasMoreElements() ) {
                    alias = keyStoreAliasEnum.nextElement();
                    if (password != null) {
                        privateKey = (PrivateKey) keyStore.getKey(alias, password.toCharArray());
                    }
                }
                return privateKey;*/
                
                KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(aliasLlavero, new KeyStore.PasswordProtection(passwordLlavero.toCharArray()));
                
                
                X509Certificate cert = (X509Certificate) keyEntry.getCertificate();
                
                DOMSignContext signContext = new DOMSignContext(keyEntry.getPrivateKey(), securityElement);
                signContext.setDefaultNamespacePrefix("ec");
                signContext.putNamespacePrefix("http://www.w3.org/2000/09/xmldsig#", "ds");
                signContext.putNamespacePrefix("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", "wsu");

                /**/
                signContext.setIdAttributeNS(soapBody, "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", "Id");
//                signContext.setIdAttributeNS(timestamp, WSU_NS, "Id");
                /**/
                
                SOAPElement securityTokenReference = securityElement.addChildElement("SecurityTokenReference", "wsse");
//                securityTokenReference.addAttribute(envelope.createName("Id", "wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd"), "STR-BC0F2499FD1AC877DA160754606369993");
                
                //SOAPElement reference = securityTokenReference.addChildElement("Reference", "wsse");
                //reference.setAttribute("URI", "#"+certEncodedID); // <wsse:BinarySecurityToken wsu:Id="X509Token"

                
                SOAPElement x509DataElem = securityTokenReference.addChildElement("X509Data", "ds", "http://www.w3.org/2000/09/xmldsig#");
        		SOAPElement x509IssuerElem = x509DataElem.addChildElement("X509IssuerSerial", "ds");
        		x509IssuerElem.addChildElement("X509IssuerName", "ds").setValue(cert.getIssuerX500Principal().getName());
        		x509IssuerElem.addChildElement("X509SerialNumber", "ds").setValue(cert.getSerialNumber().toString());
        		
                
                
                //KeyInfoFactory keyFactory = KeyInfoFactory.getInstance();
                KeyInfoFactory keyFactory = xmlSignatureFactory.getKeyInfoFactory();
                DOMStructure domKeyInfo = new DOMStructure(securityTokenReference);
                
                
                
//                List<Object> x509Content = new ArrayList<>();
//        		X509IssuerSerial issuer = keyFactory.newX509IssuerSerial(cert.getIssuerX500Principal().getName(),cert.getSerialNumber());
//        		x509Content.add(issuer);
//        		X509Data xd = keyFactory.newX509Data(x509Content);
//                
                
        		javax.xml.crypto.dsig.keyinfo.KeyInfo keyInfo = keyFactory.newKeyInfo(java.util.Collections.singletonList(domKeyInfo));//, "KI-BC0F2499FD1AC877DA160754606369992");
                //KeyInfo keyInfo = keyFactory.newKeyInfo(Collections.singletonList(xd));
                
                javax.xml.crypto.dsig.XMLSignature signature = xmlSignatureFactory.newXMLSignature(signedInfo, keyInfo);//, null, "SIG-BC0F2499FD1AC877DA160754606371995", null);
//                signContext.setBaseURI("");
                
                signature.sign(signContext);
                
                //header.addChildElement(securityElem);
        		
        		if (msg != null) {
        			msg.saveChanges();
        		}
        		
            } catch (Exception e) {
                log.error("Error al firmar la peticion al WS: "+e.getLocalizedMessage(), e);
            }
        } else {//es una rta del WS
            // inbound
        }
        return true;
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
